import java.util.Scanner;

public class Main{
	
	public static void main(String[] args){
		//default to 4 stations for now
		Helper helper = new Helper(4);
		boolean cont = true;
		while(cont){
			System.out.println("How many iterations would you like to run?");
			Scanner num = new Scanner(System.in);
			int iterations = num.nextInt();
			helper.execute(iterations);
			//helper.getReports();
			System.out.println("Done!");
			System.out.println("To run more iterations and print reports, press 1 and return.");
			Scanner nput = new Scanner(System.in);
			if(!nput.nextLine().equals("1")) cont = false;
		}	
		helper.getReports();
	}
}
