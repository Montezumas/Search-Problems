import java.util.ArrayList;

/**
 * Main class to to execute searches with user input
 * 
 * @author Robby
 *
 */
public class Driver {

	public static void main(String args[]) {
		// check if there are enough arguments
		if (args.length < 2) {
			System.out.println(
					"Error: not enough arguments.\nPlese run with arguments: "
					+ "[8puzzle or fwgc] [bfs or dfs or ids or aStar] (-P [8puzzle config]) (-H [man or oop]) (-D)");
			return;
		}

		// required data
		String problem = args[0];
		String search = args[1];

		// data that should be flagged in the arguments
		int[][] envi = null;
		String heuristic = null;
		boolean debug = false;

		// check argument for flags with data
		for (int i = 2; i < args.length; i++) {
			switch (args[i]) {
			case "-P":
				// environment to get from arguments
				envi = new int[3][3];
				// list to make sure there are no duplicates in puzzle
				// configuration
				ArrayList<Integer> inState = new ArrayList<Integer>();

				// read the configuration arguments into an 8puzzle
				for (int j = 0; j < 3; j++) {
					for (int k = 0; k < 3; k++) {
						int entry = Integer.parseInt(args[i + 1 + inState.size()]);
						if (inState.contains(entry) || entry > 8 || entry < 0) {
							System.out.println("Invalid 8-puzzle configuration");
							return;
						}
						inState.add(entry);
						envi[j][k] = entry;
					}
				}
				break;
			case "-H":
				heuristic = args[i + 1];
				break;
			case "-D":
				debug = true;
				break;
			}
		}

		if (heuristic == null && search.equals("aStar")) {
			System.out.println("Please include a heuristic choice in the arguments for aStar search");
			return;
		}

		if (envi == null && problem.equals("8puzzle")) {
			System.out.println("Please include an 8puzzle config in the arguments");
			return;
		}

		// empty initial state
		Problem initialState;

		// debugging info
		int expanded = 0;

		if (problem.equals("8puzzle")) {
			initialState = new Puzzle8(envi);

			if (search.equals("bfs")) {
				expanded = Search.BFS(initialState, 0);
			} else if (search.equals("dfs")) {
				expanded = Search.DFS(initialState);
			} else if (search.equals("ids")) {
				expanded = Search.IDS(initialState);
			} else if (search.equals("aStar")) {
				if (heuristic.equals("oop")) {
					expanded = Search.BFS(initialState, 1);
				} else if (heuristic.equals("man")) {
					expanded = Search.BFS(initialState, 2);
				}
			} else {
				System.out.println("Invalid search type argument");
			}
		} else if (problem.equals("fwgc")) {
			initialState = new FWGC();

			if (search.equals("bfs")) {
				expanded = Search.BFS(initialState, 0);
			} else if (search.equals("dfs")) {
				expanded = Search.DFS(initialState);
			} else if (search.equals("ids")) {
				expanded = Search.IDS(initialState);
			} else if (search.equals("aStar")) {
				if (heuristic.equals("oop")) {
					expanded = Search.BFS(initialState, 1);
				} else if (heuristic.equals("man")) {
					expanded = Search.BFS(initialState, 2);
				}
			} else {
				System.out.println("Invalid search type argument");
			}
		} else {
			System.out.println("Invalid puzzle type argument");
		}

		if (debug) {
			System.out.println("Debug: " + expanded + " nodes expanded during search");
		}
	}

}
