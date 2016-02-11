
/**
 * 8-Puzzle game
 * environment is described by int[][]
 * 0 is an empty space
 * 
 * @author Robby
 */
public class Puzzle8 implements Problem {
		
	private int[][] envi;
	
	public Puzzle8() {
		//easy
//		this.envi = new int[][] {
//			{0,1,3},
//			{4,2,5},
//			{7,8,6}
//		};
		
		//hard
		this.envi = new int[][] {
			{8,6,7},
			{2,5,4},
			{3,0,1}
		};
	}
	
	public Puzzle8(int[][] env) {
		this.envi = env;
	}

	@Override
	public boolean isGoalState() {
		int[][] goal = new int[][]{
			{1,2,3},
			{4,5,6},
			{7,8,0}
		};
		boolean result = true;
		
		outerLoop:
		for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j++) {
				if(envi[i][j] != goal[i][j]) {
					result = false;
					break outerLoop;
				}
			}
		}
		
		return result;
	}

	@Override
	public Problem[] succStates() {
		Puzzle8[] succ = new Puzzle8[4];
		
		//coordinates of the zero
		int zx = 0;
		int zy = 0;
		
		//find the coordinates
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 3; x++) {
				if(envi[y][x] == 0) {
					zx = x;
					zy = y;
				}
			}
		}
				
		/* 
		 * Calculations for positions where the 0 might go,
		 * each of them can be paired with zx/zy.
		 * e.g. [upy][zx] is a move up
		 * [zy][rightx] is a move right
		 */
		int upy = zy - 1;
		int downy = zy + 1;
		int leftx = zx - 1;
		int rightx = zx + 1;
		
		int[][] temp;
		
		/* 
		 * an index in the successor array is defined by,
		 * 0: up
		 * 1: down
		 * 2: left
		 * 3: right
		 * if index is null the move is not valid
		 */
		if(upy > -1 ) {
			temp = new int[3][3];
			for(int i = 0; i < 3; i ++) {
				for(int j = 0; j < 3; j++) {
					if(i == upy && j == zx) {
						temp[i][j] = 0;
					} else if(i == zy && j == zx) {
						temp[i][j] = envi[upy][zx];
					} else{
						temp[i][j] = envi[i][j];
					}
				}
			}
			succ[0] = new Puzzle8(temp);
		}
		
		if(downy < 3) {
			temp = new int[3][3];
			for(int i = 0; i < 3; i ++) {
				for(int j = 0; j < 3; j++) {
					if(i == downy && j == zx) {
						temp[i][j] = 0;
					} else if(i == zy && j == zx) {
						temp[i][j] = envi[downy][zx];
					} else{
						temp[i][j] = envi[i][j];
					}
				}
			}
			succ[1] = new Puzzle8(temp);
		}
		
		if(leftx > -1) {
			temp = new int[3][3];
			for(int i = 0; i < 3; i ++) {
				for(int j = 0; j < 3; j++) {
					if(i == zy && j == leftx) {
						temp[i][j] = 0;
					} else if(i == zy && j == zx) {
						temp[i][j] = envi[zy][leftx];
					} else{
						temp[i][j] = envi[i][j];
					}
				}
			}
			succ[2] = new Puzzle8(temp);
		}
		
		if(rightx < 3) {
			temp = new int[3][3];
			for(int i = 0; i < 3; i ++) {
				for(int j = 0; j < 3; j++) {
					if(i == zy && j == rightx) {
						temp[i][j] = 0;
					} else if(i == zy && j == zx) {
						temp[i][j] = envi[zy][rightx];
					} else{
						temp[i][j] = envi[i][j];
					}
				}
			}
			succ[3] = new Puzzle8(temp);
		}
	
		return succ;
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
		if(flag == 1) {
			return outOfPlace();
		} else if(flag == 2){
			return manhatan();
		}
		
		return 0;
	}

	@Override
	public void printState() {
		for(int i = 0; i < 3; i++) {
			System.out.print("|");
			for(int j = 0; j < 3; j++) {
				if(envi[i][j] == 0)
					System.out.print(" " + "|");
				else
					System.out.print(envi[i][j] + "|");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private int outOfPlace() {
		int oop = 0;
		
		int[][] goal = new int[][]{
			{1,2,3},
			{4,5,6},
			{7,8,0}
		};
		
		for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j++) {
				if(envi[i][j] != goal[i][j]) {
					oop++;
				}
			}
		}
		
		return oop;
	}

	private int manhatan() {
		int manDist = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				switch(envi[i][j]) {
				case 1:
					manDist += Math.abs(0 - i) + Math.abs(0 - j);
					break;
				case 2:
					manDist += Math.abs(0 - i) + Math.abs(1 - j);
					break;
				case 3:
					manDist += Math.abs(0 - i) + Math.abs(2 - j);
					break;
				case 4:
					manDist += Math.abs(1 - i) + Math.abs(0 - j);
					break;
				case 5:
					manDist += Math.abs(1 - i) + Math.abs(1 - j);
					break;
				case 6:
					manDist += Math.abs(1 - i) + Math.abs(2 - j);
					break;
				case 7:
					manDist += Math.abs(2 - i) + Math.abs(0 - j);
					break;
				case 8:
					manDist += Math.abs(2 - i) + Math.abs(1 - j);
					break;
				case 0:
					// not considering 0's
					break;
				default:
					System.out.println("Error in manhatan switch");
					break;
				}
			}
		}
	
		return manDist;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		Puzzle8 equal = (Puzzle8) o;
		
		for(int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j++) {
				if(envi[i][j] != equal.envi[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
