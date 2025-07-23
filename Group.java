
import java.time.*;

public class Group {
        private String name;
        final LinkedList<Guest> members = new LinkedList<>();
        LocalTime latest = LocalTime.MAX;

        Group(String groupName) { 
        	this.name = groupName; 
        	}
        
        public String getName(){
			return this.name;
		}
        

        void add(Guest g) {
            members.insertAtEnd(g);
            if (g.getArrival().isBefore(latest)) latest = g.getArrival();
        }
        int size() { 
        	return members.size; 
        	}
        
        
    }
