import java.sql.Time;
public class Passenger {
	public Label start;
	public Label destination; //stores an enum A-Z to be used to compare to a stations enum to see if they match
	public int ticketNumber;

	//obsolete?
	public Time departure;
	public Time arrival;
	
	public Passenger(Label d, int t){
		this.destination = d;
		ticketNumber = t;
		//this constructor assumes that Station will 
		//assign start during enqueue.
	}
	
	public Passenger(Label s, Label d, int t){
		this.start = s;
		this.destination = d;
		ticketNumber = t;
	}
	
	public void setDeparture(Time t){
		this.departure = t;
	}
	
	public void setArrival(Time t){
		this.arrival = t;
	}
	
	public Time getDeparture(){
		return departure;
	}
	
	public Time getArrival(){
		return arrival;
	}
	
	public Label getStart(){
		return start;
	}
	
	public Label getDestination(){
		return destination;
	}
	
	public void setStart(Label l){
		this.start = l;
	}
	
	public void setDestination(Label l){
		this.destination = l;
		System.out.println("Passenger line 53 setDestination()");
	}
	
	public String toString(){
		return "Ticket# " + ticketNumber + ": Start:" + getStart().name() + "|| Target:" + getDestination().name();
	}
	
	public int getTicket(){
		return ticketNumber;
	}

}