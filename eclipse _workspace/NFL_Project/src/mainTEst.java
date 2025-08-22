
import java.io.*;

public class mainTEst {
	public static void main(String[] args) {
		Clock c1 = new Clock(9, 23, 'a');  

		System.out.println(c1);

		c1.addTime(2, 40);  // add 2 hr, 40 mins

		System.out.println(c1);

		c1.aheadOneHour(); 

		System.out.println(c1);
		
		Clock c2 = new Clock();  // default is midnight

		System.out.println(c2);

		c2.addTime(0,90); 

		System.out.println(c2);

		c2.backOneHour(); 

		System.out.println(c2);

		c2.backOneHour();

		System.out.println(c2);

		c2.addTime(0, 1000);

		System.out.println(c2);
    }
	
	
	/*
	public static void processFile(String scoresFileName) {
		try {
			File inFile = new File(scoresFileName);
	
			FileReader  fileReader = new FileReader(inFile); 
			BufferedReader bufferReader = new BufferedReader(fileReader);
	
			String inputStr = bufferReader.readLine();
			
			while(inputStr != null) {
				String lineStrings [] = inputStr.split(",");
				
				
				if(lineStrings.length > 1) {
					String LastName = lineStrings[0];
					String FirstName = lineStrings[1];
					int sum = 0;
					for(int i=2;i<lineStrings.length;i++) {
						int score = Integer.parseInt(lineStrings[i]);
						sum+=score;
					}
					System.out.println(FirstName+" "+sum);
					inputStr = bufferReader.readLine();
				}
				else {
					inputStr = bufferReader.readLine();
				}
				
			}
		
		}
		catch (FileNotFoundException ioe){
			System.out.println("File was not found. Look for errors in following Stack Trace:");
			
		} catch (IOException e) {
			System.out.println("Something went wrong:");
			e.printStackTrace();
		}
	}
	*/

}
