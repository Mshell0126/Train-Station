import java.util.*;


public class Station{
	public boolean control;
	public Label label;
	private int capacity;
	public int timeToNext;
	public Queue<Passenger> queue;
	public Queue<Passenger> arrived;
	
	public Station(int l){
		this(l, 50, 100);
		//System.out.println("Station line 12");
	}
	
	public Station(int l, int t){
		this(l, t, 100);
		//System.out.println("Station line 17");
	}
	
	public Station(int l, int t, int c){
		this.label = Label.values()[l] ;
		
		this.timeToNext = t;//obsolete
		
		this.capacity = c;
		queue = new LinkedList<>();
		arrived = new LinkedList<>();

		if(l == 0){//if label is control:
			control = true;
			capacity = 0;
			timeToNext = 0;
			//System.out.println("Station line 22");
		}
	}
	

	
	public boolean enqueue(Passenger p){
		if(control){
		return true;
		}
		if (capacity > queue.size()) {
			p.setStart(label);
			queue.add(p);
			return true;
		}
		else return false;
	}
	
	
	public Passenger dequeue(){
		return queue.remove();
	}
	
	public void dock(Passenger x){
		if(!control)arrived.add(x);
		
	}
	
	public Label getLabel(){
		return label;
	}
	
	public boolean hasPassengers(){
		if(queue.size() > 0)return true;
		return false;
	}
	
	public String getName(){
		return "Station " + label.name();
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public boolean hasSpace(){
		if(capacity > queue.size()) return true;
		return false;
	}
	
	public boolean isControl(){
		return control;
	}
	////////////////////////////////////////////
	
	public void compare(){
		if(!control)return;
		Passenger[] previous = new Passenger[40];
		int i = 0;
		int count = 0;
		while(arrived.size() > 0){
			previous[i] = arrived.remove();
		}
		while(hasPassengers()){
			Passenger temp = queue.remove();
			for(Passenger p: previous){
				if(p.getTicket() == temp.getTicket()) count++;
			}
			arrived.add(temp);
		}
		//System.out.println("Train has " + count + " passenger(s) left from previous lap.");
		////////////
		return;
	}
	
	public void report(){
		Queue<Passenger> list = arrived;
		System.out.println("This is " + getName() + ".");
		System.out.println("--==--==--==--==--==--==--==--==-==--==--==--==--");
		while(list.size() > 0){
			System.out.println(list.remove().toString());
		}
	}
	
	
}