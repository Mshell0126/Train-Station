import java.util.*;
/*
if you made a bigger application you could make a class,
"Cars", to replace this one and then make a class "Train" as a
Queue of Cars. Train would go from station to station like this
one but would perform loading and unloading functions for each
car in sequence etc.
*/
public class Train extends LinkedList<Passenger> {
	private int capacity;
	private Stack<Passenger> coach;
	public Train(){
		capacity = 40;
		coach = new Stack<>();
		
	}
	
	public void board(Passenger p){
		if (!isFull()) coach.push(p);
		return;
		
	}
	
	public boolean isFull(){
		if (capacity > coach.size())return false;
		return true;
	}
	
	public int emptySeats(){
		int temp = capacity - coach.size();
		return temp;
	}
	
	
	public void dockPassengers(Station s){
		//System.out.println(s.getName());
		Stack<Passenger> temp = new Stack<>();
		while(coach.size() > 0){
			Passenger x = coach.pop();
			if (x.getDestination().equals(s.getLabel())) s.dock(x);
			else temp.push(x);
		}
		coach = temp;
	}
	
	public void examinePassengers(Station c){
		Stack<Passenger> temp = new Stack<>();
		while(coach.size() > 0){
			Passenger p = coach.pop();
			temp.push(p);
			c.enqueue(p);			
		}
		//System.out.println("Train currently has "+ temp.size() + " passengers on board.");
		c.compare();
		
		coach = temp;
	}
	
	public int getCapacity(){
		return capacity;
	}
}
