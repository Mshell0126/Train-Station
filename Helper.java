import java.util.*;

public class Helper{
	private Train train;
	private Circuit stations;
	private Circuit selector;
	private Random random;
	private Label[] label;
	private int numStations;
	private int ticket;
	private int iteration;
	
		
	public Helper(int n){
		iteration = 0;
		train = new Train();
		this.random = new Random();
		this.label = Label.values();
		this.ticket = 987654321;
		
		if(n > 26){
			System.out.println("The number of stations is not to exceed 26 in this version. Stations set to 26.");
			this.numStations = 26;
		}
		else this.numStations = n;
		
		stations = new Circuit();
		selector = new Circuit(train, "selector");
		
		selector.enqueue(makeStation(0));//this adds the control station to train circuit.
		
		for (int i = 1; i <= numStations; i++){
			Station s = makeStation(i);
			selector.enqueue(s);//selector should have one more station than "stations"
			stations.enqueue(s);
		}
		selector.setStation();
		stations.setStation();
		elapse();
	}
//Station and Passenger factory methods	
	public static Station makeStation(int i){
		return new Station(i);
	}
	
	//base picker for recursive  
	public Label pickStation(){
		return label[random.nextInt(numStations) + 1];
	}
	
	public Label pickStation(Label l){ //this function is not needed if passengers can be round-trip riders
		Label temp = pickStation();
		if (!l.equals(temp)){return temp;}
		return pickStation(l);
	}

	private boolean spawn(Station s){
			boolean check = s.enqueue(new Passenger(pickStation(s.getLabel()), ticket));
			if(check) ticket++;
			if(!check) System.out.println("Queue is full at station " + s.getLabel().name() + ".");
			return check;
		}
	
	public void makePassengers(Station s){
		
		if(iteration <= 1){			
			int x = (2 * train.getCapacity()) / numStations;
			while(x > 0){
				x = x - 1;
				spawn(s);
			}
		}
		else{
			while(s.hasSpace()){
			spawn(s);
			}
		}
		return;
	}

	private void elapse(){
		iteration ++;
		Station current = stations.getStation();
		makePassengers(current);
		while(!current.equals(stations.peek())){
			stations.toNext();
			makePassengers(stations.getStation());
		}
		stations.toNext();
	}
	

	
	public void execute(){
		System.out.println("EXECUTE LINE 112 HELPER");
		execute(1);
	}
	
	//the code will be more reliable if a line is added to ensure that the current station is control before the loop in execute()
	public void execute(int n){
		if(n <= 0)return;
		
		while(!selector.nextStation().isControl()){
			//System.out.println(selector.getStation().getName());
			selector.toNext();
			
		}
		selector.toNext();
		elapse();
		execute(n-1);
	}
	
	public void getReports(){
		Station current = stations.getStation();
		while(!current.equals(stations.peek())){
			stations.getStation().report();
			stations.toNext();
			System.out.println("-----------------------------------------------");
			System.out.println("===============================================");
			System.out.println("-----------------------------------------------");
			
		}
		stations.getStation().report();
		stations.toNext();
			
	}	
}
