
public class Person {
	private String name;
	private double weight;
	private int heightFeet;
	private int heightInches;

	public double calculateBMIValue() {
		int height=(heightFeet*12) + heightInches;
		// Bug Check
		// System.out.print("Check inches: " + height_just_inches);
		double BMIValue =weight*703 / Math.pow(height,2);
		
		return BMIValue;
	}
	public String judgeHeathStatus(double BMIValue) {
		String healthStatus = "";
		if (BMIValue < 18.5) {
			healthStatus = "underweight";
		}
		else if (BMIValue > 25) {
			healthStatus = "overweight";
		}
		else {
			healthStatus = "normal";
		}
		return healthStatus;
	}
	public void printHeathReport() {
		String heightFull=heightFeet+"'" + heightInches + "\"";
		double BMIValue = calculateBMIValue();
		String healthStatus = judgeHeathStatus(BMIValue);
		//Print out name, weight, height, BMI value, and health status
		System.out.printf("%24s %10s %10s %12s %15s \n", "Name", "Weight", "Height", "BMI Value", "Health Status");
		// System.out.println(); This can be replaced by \n
		System.out.printf("%24s %10.1f %10s %12.2f %15s \n", name, weight, heightFull, BMIValue, healthStatus);
	}
}

