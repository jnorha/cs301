/**
*	Date of creation:7/22/25
*	Author:Josh Daniels
**/

//import java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class HappyFarm {
	public static void main(String[] args) {
		// first read file into an ArrayList in order to build queues based on file contents
		ArrayList<Guest> todayGuests = guestReadIn("GuestLog.csv");
		// make a linked list of all guests
		//sort by hour arrived
		todayGuests.sort(Comparator.comparing(Guest -> Guest.getGroup()));
		
		LinkedList<Guest> guestLinked = new LinkedList<Guest>();
		for(int i=0;i<todayGuests.size();i++) {
			guestLinked.insertAtEnd(todayGuests.get(i));
		}
		
		//check
		//todayGuests.printList();
		System.out.println("First Guest is: " + todayGuests.get(0).getName() +" who is arriving at " + todayGuests.get(0).getArrival());
		
		//test adding to a new queue
		
		MyQueue<Guest> guestQueue = new MyQueue<Guest>();
		guestQueue.add(todayGuests.get(1));
		
		System.out.println("Second Guest is: " + guestQueue.peek().getName() +" who is arriving at " + guestQueue.peek().getArrival());
		
		
		//build an ArrayList of queues
		ArrayList<MyQueue> rideList = compileRides(guestLinked);
		
		for(int p=0;p<rideList.get(0).size;p++) {
			System.out.println("First Guest is: " + todayGuests.get(0).getName() +" who is arriving at " + todayGuests.get(0).getArrival());
		}
		
		

	}
		
		
// METHODS
		
		/**
		 * Method: guestLogIn()
		 * Function: reads the guest log and builds guest queues
		 * @returns single arraylist of all guests
		 */
		public static ArrayList<Guest> guestReadIn(String fileName) {
			
			ArrayList<Guest> allGuests = new ArrayList<>();
			
			try {
				File inFile = new File(fileName);
		
				FileReader  fileReader = new FileReader(inFile); 
				BufferedReader bufferReader = new BufferedReader(fileReader);
		
				String inputStr = bufferReader.readLine();
				inputStr = bufferReader.readLine();
		
				while(inputStr != null ) {
					//Create an object for each record read in
					String lineStrings [] = inputStr.split(",");
					//System.out.println(lineStrings[0]+" "+lineStrings[1]+" "+lineStrings[2]+" "+lineStrings[3]+" "+lineStrings[4]+" "+lineStrings[5]+" "+lineStrings[6]+" "+lineStrings[7]+" ");
					String guestName = lineStrings[0];
					String timeString = lineStrings[1];
					LocalTime arrivalTime = LocalTime.parse(timeString);
					String groupName = lineStrings[2];
					
					Guest guestForList = new Guest(guestName, arrivalTime, groupName);
					//System.out.println(teamForList.getTeamName());
					allGuests.add(guestForList); 
					inputStr = bufferReader.readLine();
				}
				
				return allGuests;
				
			}
			catch (IOException ioe){
				System.out.println("something went wrong with reading the guest log file");
				return allGuests;
			}
			
		}
		
		
		/**
		 * Method: compileRides()
		 * function: takes a list of guests and breaks it into a list of separate queues for each ride
		 * @param guestList
		 * @return ArrayList of ride queues
		 */
		public static ArrayList<MyQueue> compileRides(LinkedList<Guest> guestLinked){
			ArrayList<MyQueue> rideList = new ArrayList<MyQueue>();
			
			//set a dynamic number of rides to send out. Starting with number of guests / 5 meaning each ride would flexably fit 5 riders
			int numRides = (guestLinked.size / 3);
			
			//make a queue of all groups
			MyQueue<MyQueue> groupQueue = new MyQueue<MyQueue>(); 
			//make linkedlist objects for each group
			
			ListNode<Guest> nextUp = guestLinked.getNodeAt(0);
			
			while(guestLinked.size!=0) {
				MyQueue<Guest> group = new MyQueue<Guest>();
				
				if(groupQueue.size==0) {
					group.insertAtEnd(guestLinked.deleteHead());
				}
				for(int j=0;j<groupQueue.size();j++) {
					MyQueue<Guest> currentGroup =  (MyQueue<Guest>) groupQueue.getNodeAt(j).value;
					String currentGroupName = ((Guest) currentGroup.getNodeAt(0).value).getGroup();
					if(nextUp.value.getGroup().equals(currentGroupName)) {
						group.insertAtEnd(guestLinked.deleteHead());
					}
					System.out.println("Current Group Name is "+ currentGroupName);
				}
				nextUp = nextUp.next;
				groupQueue.insertAtEnd(group);
				System.out.println("Group Queue Size is " + groupQueue.size);
			}
			
			
			//while the linked list of riders still has memebers waiting for a ride
			/*
			while (guestLinked.size!=0) {
				int numRideGuests = 0;
				
				
				MyQueue<Guest> ride = new MyQueue<Guest>();
				ListNode<Guest> nextUp = guestLinked.getNodeAt(0);
				//make temp list of grouped riders
				List<Guest> groupX = new ArrayList<Guest>();
				
				
				while(nextUp.value.getGroup().equals(((Guest) nextUp.next.value).getGroup()) && guestLinked.head.value != null) {
					//System.out.println(nextUp.value.getGroup());
					groupX.add((Guest) guestLinked.deleteAfter(0).value);
					groupX.add((Guest) guestLinked.deleteHead().value);
					;
					;
				}
				System.out.println(groupX.size());
					
					
					
				if(groupX.size() + ride.size < 10) {
					for(int i=0;i<groupX.size();i++) {
						ride.insertAtEnd(groupX.get(i));
				}
					
				
				}
				rideList.add(ride);
				
			}
			*/
			
			
			
			
			
			
			return rideList;
		}

	
}
