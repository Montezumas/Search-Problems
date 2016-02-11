
import java.util.Scanner;

public class Tester {
	
	public static void main(String args[]) {
		//FWGCTest();
		//Puzzle8Test();
		SearchTest();
	}
	
	public static void SearchTest() {
		//FWGC fwgc = new FWGC();	
		Puzzle8 p8 = new Puzzle8();
		//24156.0
		//System.out.println(Search.BFS(fwgc, 0) + " Nodes expanded");
		System.out.println(Search.BFS(p8, 2) + " Nodes expanded");
	}

	public static void FWGCTest() {
		Scanner kb = new Scanner(System.in);
		FWGC init = new FWGC();		

		while(!init.isGoalState()) {
			System.out.println("Current state is:");
			init.printState();
			
			FWGC[] succStates = (FWGC[]) init.succStates();
			int choice;
		
			System.out.println("\nPossible successor states");
			for(int i = 0; i < succStates.length; i++) {
				if(succStates[i] != null) {
					System.out.print(i+": ");
					succStates[i].printState();
				}
			}
			
			System.out.print("\nWhich option do you choose? ");
			choice = kb.nextInt();
			
			init = succStates[choice];
		}
		
		System.out.println("Congrats you win!\n");
		
		kb.close();
	}
	
	public static void Puzzle8Test() {
		Scanner kb = new Scanner(System.in);
		Puzzle8 game = new Puzzle8();
		String input = "";
		Puzzle8[] succ;
		
		while(!game.isGoalState()) {
			System.out.println("Current state: ");
			game.printState();
			
			System.out.println("Here are the possible moves:");
			
			succ = (Puzzle8[]) game.succStates();

			for(int i = 0; i < succ.length; i++) {
				if(succ[i] != null) {
					succ[i].printState();
				}
			}
			
			System.out.print("Where do you want to move the space? ");
			input = kb.nextLine();
			
			if(input.equals("up")) {
				game = succ[0];
			} else if(input.equals("down")) {
				game = succ[1];
			} else if(input.equals("left")) {
				game = succ[2];
			} else if(input.equals("right")) {
				game = succ[3];
			}
		}
		
		System.out.println("Congrats you win!\n");
		
		kb.close();
	}
	
}