import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Hay-ride scheduler built around plain-old linked lists (java.util.LinkedList).
 * ─────────────────────────────────────────────────────────────────────────────
 * CSV format expected:  name,arrivalTime,group
 * • arrivalTime is “HH:mm” in 24-hour format.
 *
 * Main idea
 * =========
 * 1.  Read every row → Guest object.
 * 2.  Put each guest into a Group object (Map<groupId,Group>).
 * 3.  Build a queue (LinkedList<Group>) ordered by each group’s
 *     *earliest* arrival time.
 * 4.  Pop groups off that queue into a Ride (capacity 10).
 *     ▸ If the next group will not fit, depart immediately – even if
 *       seats are still empty (rule 4b).
 *     ▸ If the ride becomes exactly full after adding a group, depart
 *       (rule 4a).
 * 5.  Repeat until all groups have ridden.
 */
public class HayRideScheduler {

    // ────────────────────────────── Domain ──────────────────────────────── //

    private static class Guest {
        final String name;
        final LocalTime arrival;
        final String groupId;

        Guest(String name, LocalTime arrival, String groupId) {
            this.name = name;
            this.arrival = arrival;
            this.groupId = groupId;
        }
    }

    private static class Group {
        final String id;
        final LinkedList<Guest> members = new LinkedList<>();
        LocalTime earliestArrival = LocalTime.MAX;   // updated as members added

        Group(String id) { this.id = id; }

        int size()           { return members.size(); }
        void add(Guest g)    {
            members.add(g);
            if (g.arrival.isBefore(earliestArrival)) earliestArrival = g.arrival;
        }
    }

    private static class Ride {
        final LinkedList<Group> groups = new LinkedList<>();
        final LocalTime departureTime;
        Ride(LinkedList<Group> groups, LocalTime departureTime) {
            this.groups.addAll(groups);
            this.departureTime = departureTime;
        }
        int passengerCount() {
            return groups.stream().mapToInt(g -> g.size()).sum();
        }
    }

    // ──────────────────────────── Scheduler ────────────────────────────── //

    private static List<Ride> scheduleRides(Path csvPath) throws IOException {
        Map<String, Group> byGroup = readCsv(csvPath);

        /* Put groups in arrival order (rule 2). */
        List<Group> sorted = new ArrayList<>(byGroup.values());
        sorted.sort(Comparator.comparing(g -> g.earliestArrival));

        LinkedList<Group> queue = new LinkedList<>(sorted);
        List<Ride> rides      = new ArrayList<>();

        while (!queue.isEmpty()) {
            int capacityLeft = 10;
            LinkedList<Group> onThisRide = new LinkedList<>();

            /* Try to add groups sequentially until either:
               – capacity exhausted (rule 4a), or
               – next group doesn’t fit (rule 4b).                           */
            while (!queue.isEmpty()) {
                Group next = queue.peek();
                if (next.size() <= capacityLeft) {
                    onThisRide.add(queue.poll());              // load them
                    capacityLeft -= next.size();
                    if (capacityLeft == 0) break;              // rule 4a
                } else {
                    break;                                     // rule 4b
                }
            }
            // Departure time = latest arrival among all guests on board.
            LocalTime depart = onThisRide.stream()
                                         .flatMap(g -> g.members.stream())
                                         .map(g -> g.arrival)
                                         .max(LocalTime::compareTo)
                                         .orElse(LocalTime.MIDNIGHT);
            rides.add(new Ride(onThisRide, depart));
        }
        return rides;
    }

    // ───────────────────────────── I/O helpers ──────────────────────────── //

    private static Map<String, Group> readCsv(Path path) throws IOException {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("H:mm");
        Map<String, Group> byGroup = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue; // skip empty lines
                String[] parts = line.split(",", -1);
                if (parts.length != 3)
                    throw new IllegalArgumentException("Bad CSV line: " + line);
                String name   = parts[0].trim();
                LocalTime at  = LocalTime.parse(parts[1].trim(), fmt);
                String group  = parts[2].trim();

                byGroup.computeIfAbsent(group, Group::new)
                       .add(new Guest(name, at, group));
            }
        }
        return byGroup;
    }

    // ─────────────────────────────── Demo ───────────────────────────────── //

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: java HayRideScheduler guests.csv");
            System.exit(1);
        }
        List<Ride> rides = scheduleRides(Path.of(args[0]));

        int rideNo = 1;
        for (Ride r : rides) {
            System.out.printf("Ride #%d  (depart %s)  – %d passengers\n",
                              rideNo++, r.departureTime, r.passengerCount());
            for (Group g : r.groups) {
                System.out.printf("   Group %s (%d): ", g.id, g.size());
                g.members.forEach(m -> System.out.print(m.name + " "));
                System.out.println();
            }
            System.out.println();
        }
    }
}
