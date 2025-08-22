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
		//ArrayList<Guest> todayGuests = guestReadIn("GuestLog.csv");
		
		ArrayList<Group> groupQueue = guestReadInTwo("GuestLog.csv");
		// make a linked list of all guests
		//sort by hour arrived
		//todayGuests.sort(Comparator.comparing(Guest -> Guest.getGroup()));
		
		
		/*
		LinkedList<Guest> guestLinked = new LinkedList<Guest>();
		for(int i=0;i<todayGuests.size();i++) {
			guestLinked.insertAtEnd(todayGuests.get(i));
		}
		*/
		
		//check
		//todayGuests.printList();
		
		//______________________________________________________//
		//Can use remove on the ArrayList to pop out the groups
		//create a queue of the groups
		
		for (int j=0;j<groupQueue.size();j++) {
			Group inQueue = (Group) groupQueue.get(j);
			System.out.println("Group number "+ j + " in the group queue is "+ inQueue.getName() + " with " + inQueue.size() +" members. The first member is "+ ((Guest) inQueue.members.getNodeAt(0).value).getName());
		}
		//test adding to a new queue
		
		/*
		MyQueue<Guest> guestQueue = new MyQueue<Guest>();
		guestQueue.add(todayGuests.get(1));
		
		System.out.println("Second Guest is: " + guestQueue.peek().getName() +" who is arriving at " + guestQueue.peek().getArrival());
		
		
		//build an ArrayList of queues
		ArrayList<MyQueue> rideList = compileRides(guestLinked);
		
		for(int p=0;p<rideList.get(0).size;p++) {
			System.out.println("First Guest is: " + todayGuests.get(0).getName() +" who is arriving at " + todayGuests.get(0).getArrival());
		}
		
		*/

	}
	/*
	//make a static class for the group
	//time comparison I did find online
	 private static class Group {
	        final String groupName;
	        final LinkedList<Guest> members = new LinkedList<>();
	        LocalTime latestArrival = LocalTime.MAX;   // updated as members added

	        Group(String id) { 
	        	this.groupName = id; 
	        	}

	        int size() { 
	        	return members.size; 
	        	}
	        
	        void add(Guest g)    {
	            members.insertAtEnd(g);
	            if (g.getArrival().isBefore(latestArrival)) latestArrival = g.getArrival();
	        }
	    }
	    */
		
		
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
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
		
				String inputStr = bufferReader.readLine();
				inputStr = bufferReader.readLine();
		
				while(inputStr != null ) {
					//Create an object for each record read in
					String lineStrings [] = inputStr.split(",");
					//System.out.println(lineStrings[0]+" "+lineStrings[1]+" "+lineStrings[2]+" "+lineStrings[3]+" "+lineStrings[4]+" "+lineStrings[5]+" "+lineStrings[6]+" "+lineStrings[7]+" ");
					String guestName = lineStrings[0];
					String timeString = lineStrings[1];
					LocalTime arrivalTime = LocalTime.parse(timeString, formatter);
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
		 * Method: guestLogIn()
		 * Function: reads the guest log and builds guest queues
		 * @returns single arraylist of all guests
		 */
		public static ArrayList<Group> guestReadInTwo(String fileName) {
			
			ArrayList<Group> groupQueue = new ArrayList<>();
			
			try {
				File inFile = new File(fileName);
		
				FileReader  fileReader = new FileReader(inFile); 
				BufferedReader bufferReader = new BufferedReader(fileReader);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
		
				String inputStr = bufferReader.readLine();
				inputStr = bufferReader.readLine();
		
				while(inputStr != null ) {
					//Create an object for each record read in
					String lineStrings [] = inputStr.split(",");
					//System.out.println(lineStrings[0]+" "+lineStrings[1]+" "+lineStrings[2]+" "+lineStrings[3]+" "+lineStrings[4]+" "+lineStrings[5]+" "+lineStrings[6]+" "+lineStrings[7]+" ");
					String guestName = lineStrings[0];
					String timeString = lineStrings[1];
					LocalTime arrivalTime = LocalTime.parse(timeString, formatter);
					String groupName = lineStrings[2];
					
					Guest guestForGroup = new Guest(guestName, arrivalTime, groupName);
					//System.out.println(teamForList.getTeamName()); 
					
					Group nextGroup = null;
					//for (int j=0;j<groupQueue.size;j++) {
					for(Group g : groupQueue) {
						//Group inQueue = (Group) groupQueue.getNodeAt(g).value;
						if(g.getName().equals(guestForGroup.getGroup())){
							nextGroup = g;
							break;
						}
					}
					if(nextGroup == null) {
						String groupName2 = guestForGroup.getGroup();
						nextGroup = new Group(groupName2);
						groupQueue.add(nextGroup);
					}
					
					nextGroup.add(guestForGroup);
					
					inputStr = bufferReader.readLine();
				}
				
				return groupQueue;
				
			}
			catch (IOException ioe){
				System.out.println("something went wrong with reading the guest log file");
				return groupQueue;
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
			LinkedList<Group> groupQueue = new LinkedList<Group>(); 
			//make linkedlist objects for each group
			
			
			
			//System.out.println("nextup value "+ nextUp.value.getGroup());
			
			while(guestLinked.size!=0) {
				ListNode<Guest> nextUp = guestLinked.deleteHead();
				Group nextGroup = null;
				for (int j=0;j<groupQueue.size;j++) {
					Group inQueue = (Group) groupQueue.getNodeAt(j).value;
					if(inQueue.getName().equals(nextUp.value.getGroup())){
						nextGroup = inQueue;
						break;
					}
				}
				if(nextGroup == null) {
					String groupName = nextUp.value.getGroup();
					nextGroup = new Group(groupName);
					groupQueue.insertAtEnd(nextGroup);
				}
				
				nextGroup.add(nextUp.value);
				
				/*
				MyQueue<Guest> group = new MyQueue<Guest>();
				//group.insertAtEnd(nextUp);
				
				if(groupQueue.size()==0) {
					groupQueue.add(group);
				}
				for(int j=0;j<groupQueue.size();j++) {
					MyQueue<Guest> currentGroup =  (MyQueue<Guest>) groupQueue.getNodeAt(j).value;
					System.out.println("first in group list size " + currentGroup.size);
					
					for(int p=0;p<currentGroup.size;p++) {
						Guest inAGroup = (Guest) currentGroup.getNodeAt(p).value;
						System.out.println("current group contains "+ inAGroup.getGroup());
					}
					ListNode<Guest> firstInGroup =  currentGroup.getNodeAt(0);
					
					System.out.println("Node value: " + firstInGroup.value.getClass());
				
					
					if(nextUp.value.getGroup().equals(((Guest) firstInGroup.value).getGroup())) {
						group.insertAtEnd(guestLinked.deleteHead());
					}
					System.out.println("Current Group Name is "+ ((Guest) firstInGroup.value).getGroup());
				}
				*/
				
				//nextUp = nextUp.next;
				
				Group firstGroup = (Group) groupQueue.getNodeAt(0).value;
				
				System.out.println("Group Queue Size is " + groupQueue.size + " first groups name is " + firstGroup.getName() + " " + firstGroup.members.getNodeAt(0));
				
			}
			
			for (int j=0;j<groupQueue.size;j++) {
				Group inQueue = (Group) groupQueue.getNodeAt(j).value;
				System.out.println("Group number "+ j + " in the group queue is "+ inQueue.getName() + " with " + inQueue.size() +" members. The first member is "+ ((Guest) inQueue.members.getNodeAt(0).value).getName());
			}
			
			
			
			
			
			
			return rideList;
		}

	
}