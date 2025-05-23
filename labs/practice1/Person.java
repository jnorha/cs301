
public class Person {
	private String name;
	private double weight;
	private int heightFeet;
	private int heightInches;

	//constructors
	public Person(String pName, double pWeight, int pHeightFeet, int pHeightInches) {
		setName(pName);
		setWeight(pWeight);
		setHeightFeet(pHeightFeet);
		setHeightInches(pHeightInches);
	}

	public String getName() {
		return name;
	}

	public void setName(String pName) {
		this.name = pName;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double pWeight) {
		if(pWeight < 0 || pWeight > 1000) {
			System.out.println("Wrong value of weight!");
			this.weight = 100;
		}
		this.weight = pWeight;
	}

	public int getHeightFeet() {
		return heightFeet;
	}

	public void setHeightFeet(int heightFeet) {
		if(heightFeet <0 || heightFeet >=10) {
			System.out.println("Wrong value of height (feet)!");
			this.heightFeet = 0;
		}

		this.heightFeet = heightFeet;
	}

	public int getHeightInches() {
		return heightInches;
	}

	public void setHeightInches(int heightInches) {
		if(heightInches <0 || heightFeet >=12) {
			System.out.println("Wrong value of height (inches)!");
			this.heightInches = 1;
		}
		this.heightInches = heightInches;
	}

	
	/**
	 * Method:	calculateBMIValue
	 * Function:calculate one's BMI value based on the weight, height values
	 * @param	none. Just use the attributes
	 * @return	BMI value
	 */	
	public double calculateBMIValue() {
		//Calculate the BMI value
		//Convert height into inches
		int height = this.heightFeet*12+this.heightInches;
		double BMIValue = this.weight*703/Math.pow(height,2);
		
		return BMIValue;			
		
	}
	
	/**
	 * Method:	judgeHealthStatus
	 * Function:judge one's health status based on the bmi value
	 * 			Underweight: Less than 18.5
	 * 			Normal: Between 18.5 and 24.9
	 * 			Overweight: Over 25
	 * @param	double BMIValue
	 * @return	the health status
	 */	
	public String judgeHealthStatus(double BMIValue) {
		//Judge one's health status based on the BMI value	
		String healthStatus = "";
		if(BMIValue < 18.5) {
			healthStatus = "underweight";
		}
		else if (BMIValue <25){
			healthStatus = "normal";
		}
		else { //BMIValue >= 25
			healthStatus = "overweight";
		}
		
		return healthStatus;
	}

	/**
	 * Method:	printHealthReport
	 * Function:print one's health status
	 * @param	none. Just use the attributes and call related methods for calculating
	 * 			BMI values and judging the health status
	 * @return	none. Just print the health report
	 */
	public void printHealthReport() {
		//Print out name, weight, height, BMI value, and health status
		String heightString=this.heightFeet+"'"+this.heightInches+"''";
		double BMIValue = calculateBMIValue();
		String healthStatus = judgeHealthStatus(BMIValue);
		System.out.printf("%24s %10s %10s %12s %15s \n", "name", "weight", "height", "BMI Value", "health status");
		System.out.printf("%24s %10.1f %10s %12.2f %15s \n", name, weight, heightString, BMIValue,healthStatus);

		
	}

}
