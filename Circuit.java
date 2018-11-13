import java.util.*;

public class Circuit extends LinkedList<Station> implements Queue<Station>{
	public Station station;
	public boolean enabled;
	public Train train;
	
	
	public Circuit(){
		
	}
	
	public Circuit(Train t){
		train = t;
	}
	
	public Circuit(Train t, String c){
		if (c.equals("selector")) enabled = true;
		train = t;
	}
	
	
	public void enqueue(Station s){
		add(s);
		
	}
	
	public void setStation(){
		station = remove();
	}
	
	public Station nextStation(){
		return peek();
	}
	
	public void toNext(){
		add(station);
		setStation();
		if(enabled){
			exchangePassengers();
		}
		return;
	}
	
	private void exchangePassengers(){
		if(station.isControl()){
			train.examinePassengers(station);
			return;
		}
		else{
			train.dockPassengers(station);

			while(station.hasPassengers() && !train.isFull()){
				train.board(station.dequeue());
			}
		}
		return;
	}
	
	public Station getStation(){
		return station;
	}
	
	public String whatStation(){
		return station.getName();
	}
	
}


