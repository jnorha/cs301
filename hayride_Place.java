
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
		
		ArrayList<Group> groupQueue = guestReadinGroups("GuestLog.csv");
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
			//System.out.println("Group number "+ j + " in the group queue is "+ inQueue.getName() + " with " + inQueue.size() +" members. The first member is "+ ((Guest) inQueue.members.getNodeAt(0).value).getName());
		}
		//test adding to a new queue
		
		
		//build an ArrayList of queues
		ArrayList<MyQueue> rideList = compileRides(groupQueue);
		
		for (int j=0;j<rideList.size();j++) {
			MyQueue ride =  rideList.get(j);
			System.out.println("Ride #"+(j+1));
			int rideGuests = 0;
			LocalTime departureTime = null;
			for (int k=0;k<ride.size;k++) {
				int groupSize = ((Group) ride.getNodeAt(k).value).size();
				if(k==(ride.size-1)) {
					departureTime = ((Group) ride.getNodeAt(k).value).getLatest();
				}
				rideGuests += groupSize;
			}
			//account for the edge cases where the ride is NOT at capacity but leaves when the NEXT group to come in arrives
			if(rideGuests<10 && j!=(rideList.size()-1)) {
				Group nextUpGroup = ((Group) rideList.get(j+1).getNodeAt(0).value);
				departureTime = ((LocalTime) nextUpGroup.latest);
			}
			System.out.println("DepartureTime: "+ departureTime);
			
			System.out.println("Number of Guests: " + rideGuests);
			for (int y=0;y<ride.size;y++) {
				Group group = ((Group) ride.getNodeAt(y).value);
				for (int h=0;h<group.size();h++) {
					Guest rider = (Guest) group.members.getNodeAt(h).value;
					System.out.println(rider.getName() + "  "+rider.getArrival()+"  "+rider.getGroup());
				}
				
				//System.out.println("Ride number "+ j + " in the ride queue is "+ ((Group) ride.getNodeAt(y).value).getName() + " with " + ((Group) ride.getNodeAt(y).value).size() + " members. The first member is "+ ((Guest) ((Group) ride.getNodeAt(y).value).members.getNodeAt(0).value).getName());
			}
			System.out.println("  ");
			System.out.println("  ");
		}
		
		


	}
	/* NOTE: leaving this comment here since it's what lead me to then make the group class on its own
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
		//This one I saved in case the file to group ArrayList didn't work well. This was the first one I had tried. 
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
		 * Method: guestReadinGroups()
		 * Function: reads the guest log and builds guest queues
		 * @returns single arraylist of all guests
		 */
		public static ArrayList<Group> guestReadinGroups(String fileName) {
			
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
					String guestName = lineStrings[0].trim();
					String timeString = lineStrings[1].trim();
					LocalTime arrivalTime = LocalTime.parse(timeString, formatter);
					String groupName = lineStrings[2].trim();
					
					Guest guestForGroup = new Guest(guestName, arrivalTime, groupName);
					//System.out.println(teamForList.getTeamName()); 
					
					Group nextGroup = null;
					//for (int j=0;j<groupQueue.size;j++) {
					for(Group g : groupQueue) {
						//Group inQueue = (Group) groupQueue.getNodeAt(g).value;
						if(g.getName().equalsIgnoreCase(guestForGroup.getGroup())){
							//System.out.println("Reading Guest "+guestForGroup.getName()+" whos existing group is "+ guestForGroup.getGroup());
							nextGroup = g;
							break;
						}
					}
					if(nextGroup == null) {
						String groupName2 = guestForGroup.getGroup();
						nextGroup = new Group(groupName2);
						groupQueue.add(nextGroup);
						//System.out.println("Reading Guest "+guestForGroup.getName()+" whos NOT existing group is "+ guestForGroup.getGroup());
					}
					
					//System.out.println("Adding Guest "+guestForGroup.getName()+" to group "+ nextGroup.getName());
					nextGroup.add(guestForGroup);
					
					inputStr = bufferReader.readLine();
				}
				
				/* Check the groups and their membership
				for (int j=0;j<groupQueue.size();j++) {
					Group inQueue = (Group) groupQueue.get(j);
					System.out.println("Group number "+ j + " in the group queue is "+ inQueue.getName() + " with " + inQueue.size() +" members. It's Members are: ");
					for(int g=0;g<inQueue.size();g++) {
						Guest rider = (Guest) inQueue.members.getNodeAt(g).value;
						System.out.println(rider.getName() + "  "+rider.getArrival()+"  "+rider.getGroup());
					}
				}
				*/
				
				
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
		public static ArrayList<MyQueue> compileRides(ArrayList<Group> groupList){
			ArrayList<MyQueue> rideList = new ArrayList<MyQueue>();
			
			//set a dynamic number of rides to send out. Starting with number of guests / 5 meaning each ride would flexably fit 5 riders
			int numRides = (groupList.size() / 3);
			
			//start to move through all groups in the group list and add them to new MyQueue rides
			while(groupList.size()!=0) {
				//set up ride to populate with groups
				MyQueue<Group> ride = new MyQueue<Group>();
				//set ride capacity to 10 seats
				int capacity = 10;
				
				//while there are still groups needing a ride, grab first in array, assign it to a ride, or break out to previous loop if capacity is too small
				while(groupList.size()!=0) {
					Group nextGroup = groupList.get(0);
					if(nextGroup.size() <= capacity) {
						ride.add(nextGroup);
						groupList.remove(0);
						capacity = capacity - nextGroup.size();
						if (capacity == 0 || capacity < 0) {
							break;
						}
					}
					else {
						break;
					}
				}
				
				//check
				
				
				rideList.add(ride);
			}
			
			
			return rideList;
		}

	
}
