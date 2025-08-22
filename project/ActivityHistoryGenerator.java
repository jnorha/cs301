import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Generates activityHistory.csv with headers:
 * hero,date,type,duration,heartRate,distance,elevHigh,elevLow,elevChange,reps,weightInLbs
 *
 * Usage (all args optional):
 *   java ActivityHistoryGenerator --out activityHistory.csv --perHero 100000 --heroes 10 --seed 4242
 *   java ActivityHistoryGenerator --fromHeroesCsv heroes.csv --perHero 5000
 */
public class ActivityHistoryGenerator {

    // ----- Config (overridable by CLI) -----
    private static String outCsv = "activityHistory.csv";
    private static int activitiesPerHero = 100_000;
    private static int fallbackHeroCount = 10;
    private static Long seed = 4242L;
    private static String heroesCsvPath = "heroes.csv";   // optional; if not found, fallback to Hero_###
    private static boolean useHeroesCsvIfPresent = true;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String[] TYPES = {"run", "bike", "swim", "weights"};

    // last 2 years from "today" in America/Chicago
    private static final ZoneId ZONE = ZoneId.of("America/Chicago");
    private static final LocalDate END_DATE = LocalDate.now(ZONE);
    private static final LocalDate START_DATE = END_DATE.minusYears(2);

    public static void main(String[] args) throws Exception {
        parseArgs(args);

        Random rng = (seed == null) ? new Random() : new Random(seed);
        List<String> heroes = loadHeroes(useHeroesCsvIfPresent ? heroesCsvPath : null, fallbackHeroCount);

        System.out.printf("Writing %,d activities for %d hero(es) ⇒ %s%n",
                activitiesPerHero * heroes.size(), heroes.size(), outCsv);

        try (BufferedWriter w = Files.newBufferedWriter(Paths.get(outCsv), StandardCharsets.UTF_8);
             PrintWriter pw = new PrintWriter(w)) {

            // header
            pw.println("hero,date,type,duration,heartRate,distance,elevHigh,elevLow,elevChange,reps,weightInLbs");

            // To avoid huge RAM when shuffling, we’ll stream in hero-round-robin with minor random jitter.
            for (String hero : heroes) {
                for (int i = 0; i < activitiesPerHero; i++) {
                    Row row = makeRow(hero, rng);
                    pw.println(row.toCsv());
                }
            }
        }

        System.out.println("Done.");
    }

    private static void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--out":
                    outCsv = args[++i];
                    break;
                case "--perHero":
                    activitiesPerHero = Integer.parseInt(args[++i]);
                    break;
                case "--heroes":
                    fallbackHeroCount = Integer.parseInt(args[++i]);
                    break;
                case "--seed":
                    seed = Long.parseLong(args[++i]);
                    break;
                case "--fromHeroesCsv":
                    heroesCsvPath = args[++i];
                    useHeroesCsvIfPresent = true;
                    break;
                case "--ignoreHeroesCsv":
                    useHeroesCsvIfPresent = false;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown arg: " + args[i]);
            }
        }
    }

    private static List<String> loadHeroes(String path, int fallbackCount) {
        if (path != null) {
            Path p = Paths.get(path);
            if (Files.exists(p)) {
                try (BufferedReader br = Files.newBufferedReader(p, StandardCharsets.UTF_8)) {
                    List<String> names = new ArrayList<>();
                    String line;
                    boolean first = true;
                    while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty()) continue;
                        // CSV: take first column as hero name
                        String[] cols = splitCsvLine(line);
                        if (first) { // skip header row heuristically if it looks like a header
                            first = false;
                            String h0 = cols[0].toLowerCase();
                            if (h0.contains("name") || h0.contains("hero")) continue;
                        }
                        names.add(cols[0].trim());
                    }
                    if (!names.isEmpty()) {
                        System.out.printf("Loaded %d hero name(s) from %s%n", names.size(), path);
                        return names;
                    }
                } catch (IOException ignored) { }
            }
        }
        // fallback synthetic heroes
        List<String> names = new ArrayList<>(fallbackCount);
        for (int i = 1; i <= fallbackCount; i++) names.add(String.format("Hero_%03d", i));
        return names;
    }

    private static String[] splitCsvLine(String line) {
        // Simple split for basic CSV (no embedded commas/quotes in names expected here)
        return line.split(",", -1);
    }

    private static Row makeRow(String hero, Random rng) {
        String type = TYPES[rng.nextInt(TYPES.length)];
        LocalDate date = randDate(rng);
        int durationMin = 30 + rng.nextInt(211); // 30..240

        double distanceKm;
        int heart;
        int elevLow, elevHigh, elevChange;
        String reps = "", weightInLbs = "";

        switch (type) {
            case "run": {
                // pace: 4.5–9.0 min/km
                double pace = 4.5 + rng.nextDouble() * (9.0 - 4.5);
                distanceKm = clamp2(durationMin / pace, 0.5, 60.0);
                heart = 130 + rng.nextInt(61); // 130..190
                int[] elevs = elevationPair(rng);
                elevLow = elevs[0]; elevHigh = elevs[1];
                elevChange = elevHigh - elevLow;
                break;
            }
            case "bike": {
                // speed: 15–35 km/h
                double speed = 15 + rng.nextDouble() * (35 - 15);
                distanceKm = clamp2((durationMin / 60.0) * speed, 5.0, 200.0);
                heart = 115 + rng.nextInt(56); // 115..170
                int[] elevs = elevationPair(rng);
                elevLow = elevs[0]; elevHigh = elevs[1];
                elevChange = elevHigh - elevLow;
                break;
            }
            case "swim": {
                // speed: 1.5–4.0 km/h
                double speed = 1.5 + rng.nextDouble() * (4.0 - 1.5);
                distanceKm = clamp2((durationMin / 60.0) * speed, 0.2, 8.0);
                heart = 110 + rng.nextInt(51); // 110..160
                int[] elevs = elevationPair(rng);
                elevLow = elevs[0]; elevHigh = elevs[1];
                elevChange = elevHigh - elevLow;
                break;
            }
            case "weights":
            default: {
                distanceKm = 0.0;
                heart = 90 + rng.nextInt(71); // 90..160
                elevLow = 0; elevHigh = 0; elevChange = 0;
                int r = 30 + rng.nextInt(271); // 30..300 total reps
                int wt = 10 + rng.nextInt(241); // 10..250 lbs
                reps = Integer.toString(r);
                weightInLbs = Integer.toString(wt);
            }
        }

        return new Row(hero,
                date.format(DATE_FMT),
                type,
                Integer.toString(durationMin),
                Integer.toString(heart),
                String.format(Locale.US, "%.2f", distanceKm),
                Integer.toString(elevHigh),
                Integer.toString(elevLow),
                Integer.toString(elevChange),
                reps,
                weightInLbs);
    }

    private static double clamp2(double v, double lo, double hi) {
        return Math.max(lo, Math.min(hi, v));
    }

    private static int[] elevationPair(Random rng) {
        int elevLow = rng.nextInt(2001); // 0..2000 m
        int gain = rng.nextInt(1201);    // 0..1200 m
        int elevHigh = elevLow + gain;
        return new int[]{elevLow, elevHigh};
    }

    private static LocalDate randDate(Random rng) {
        long start = START_DATE.toEpochDay();
        long end = END_DATE.toEpochDay();
        long day = start + (long) (rng.nextDouble() * (end - start + 1));
        return LocalDate.ofEpochDay(day);
    }

    private static final class Row {
        final String hero, date, type, duration, heartRate, distance, elevHigh, elevLow, elevChange, reps, weightInLbs;
        Row(String hero, String date, String type, String duration, String heartRate, String distance,
            String elevHigh, String elevLow, String elevChange, String reps, String weightInLbs) {
            this.hero = hero; this.date = date; this.type = type; this.duration = duration;
            this.heartRate = heartRate; this.distance = distance; this.elevHigh = elevHigh;
            this.elevLow = elevLow; this.elevChange = elevChange; this.reps = reps; this.weightInLbs = weightInLbs;
        }
        String toCsv() {
            // non-quoted since we produce simple numeric/text without commas
            return String.join(",",
                    hero, date, type, duration, heartRate, distance, elevHigh, elevLow, elevChange, reps, weightInLbs);
        }
    }
}
