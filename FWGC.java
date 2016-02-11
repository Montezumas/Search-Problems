
/**
 * Farmer, wolf, goat, cabbage game
 * environment is described by boolean[] with:
 * e[0] = farmer e[1] = wolf e[2] = goat e[3] = cabbage
 * false = west
 * true = east
 * 
 * @author Robby
 */
public class FWGC implements Problem {
		
	private boolean[] envi;
	
	public FWGC() {
		//all on the west side
		envi = new boolean[]{false,false,false,false};
	}
	
	public FWGC(boolean f, boolean w, boolean g, boolean c) {
		envi = new boolean[4];
		envi[0] = f;
		envi[1] = w;
		envi[2] = g;
		envi[3] = c;
	}

	@Override
	public boolean isGoalState() {
		return (envi[0] && envi[1] && envi[2] && envi[3]);
	}

	@Override
	public Problem[] succStates() {
		FWGC[] states = new FWGC[4];
		int i = 0;

		if(!(envi[0])){
			//farmer is west
			if(( (envi[1]) || (envi[2]) ) && ( (envi[2]) || (envi[3]) )) {
				//farmer can move east
				states[i] = new FWGC(!envi[0],envi[1],envi[2],envi[3]);
				i++;
			}
			if(!(envi[1]) && !(!(envi[2]) && !(envi[3]))) {
				//wolf can move east
				states[i] = new FWGC(!envi[0],!envi[1],envi[2],envi[3]);
				i++;
			}
			if(!(envi[2])) { 
				//goat can move east
				states[i] = new FWGC(!envi[0],envi[1],!envi[2],envi[3]);
				i++;
			}
			if(!(envi[3]) && !(!(envi[1]) && !(envi[2]))) { 
				//cabbage can move east
				states[i] = new FWGC(!envi[0],envi[1],envi[2],!envi[3]);
				i++;
			}
		}

		if(envi[0]){
			//farmer is east
			if(( !(envi[1]) || !(envi[2]) ) && ( !(envi[2]) || !(envi[3]) )) {
				//farmer can move west
				states[i] = new FWGC(!envi[0],envi[1],envi[2],envi[3]);
				i++;
			}
			if(envi[1] && !(envi[2] && envi[3])) {
				//wolf can move west
				states[i] = new FWGC(!envi[0],!envi[1],envi[2],envi[3]);
				i++;
			}
			if(envi[2]) { 
				//goat can move west
				states[i] = new FWGC(!envi[0],envi[1],!envi[2],envi[3]);
				i++;
			}
			if(envi[3] && !(envi[1] && envi[2])) { 
				//cabbage can move west
				states[i] = new FWGC(!envi[0],envi[1],envi[2],!envi[3]);
				i++;
			}
		}

		return states;
	}

	@Override
	public int calcCost(Problem f) {
		if(f == null) {
			//this is an initial state
			return 0;
		}
		return 1;
	}
	

	@Override
	public int getHeuristic(int flag) {
		// no heuristic implementation for this problem
		return 0;
	}

	@Override
	public void printState() {
		String f = "";
		String w = "";
		String g = "";
		String c = "";
		
		if(envi[0]) f = "east";
		else if (!envi[0]) f = "west";
		if(envi[1]) w = "east";
		else if (!envi[1]) w = "west";
		if(envi[2]) g = "east";
		else if (!envi[2]) g = "west";
		if(envi[3]) c = "east";
		else if (!envi[3]) c = "west";

		System.out.println("Farmer: " + f + " Wolf: " + w + " Goat: " + g + " Cabbage: " +c);
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		FWGC equal = (FWGC) o;
		return (equal.envi[0] == this.envi[0] &&
				equal.envi[1] == this.envi[1] &&
				equal.envi[2] == this.envi[2] &&
				equal.envi[3] == this.envi[3] );
	}

}
