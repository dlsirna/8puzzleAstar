
// Course: CS3642
// Student name: Dylan Sirna
// Student ID: 000778291
// Assignment #: #1
// Due Date: 09/25/2020
//
//I wrote all the functions of this program to be compatible with any square n by n matrix, unfortunately the 
//program does find the solution fast enough sometimes for even a 9 square puzzle let alone a 4 by 4 16 square puzzle
//I spent most of my time trying to improve my Astar algorithm, it never worked well enough for me to spend time building a GUI.
import java.util.PriorityQueue;

import java.util.Objects;
public class Astar8Puzzle {

	public static PriorityQueue<Puzzle> PuzzlePriorityQueue = new PriorityQueue<>();

	public static void main(String args[]) {
//test puzzles
		int[][] test = { { 2, 3, 1 }, { 4, 0, 5 }, { 6, 7, 8 } };
		int[][] test1 = { { 2, 3, 1, 4}, { 5, 0, 6,7 }, { 8, 9, 10, 11 },{ 12, 13, 14, 15} };
		int[][] test2 = { { 8, 1, 2}, { 0, 4, 3 }, { 7, 6, 5 } };
		int[][] test4 = { { 999, 999, 999 }, { 999, 999, 999 }, {999, 999, 999 } };

 
		//input puzzle inserted in below line
		PuzzlePriorityQueue.add(new Puzzle("", TilesOutOfPlace(test),test));
		//input puzzle inserted in above line
		Puzzle temp =new Puzzle(PuzzlePriorityQueue.remove());
		

        // Add items to the Priority Queue
        if(check(temp.getState())%2==0) 
        	
        {

        	while(TilesOutOfPlace(temp.getState()) != 0) {
        		System.out.println("The Tile currently popped from the priority queue is shown above^--------- " + temp);
	
        		 GenerateSolutionsTree(temp);

        		temp = 	PuzzlePriorityQueue.remove();


        	
        	}System.out.println("Solution is :"+ temp);
        	//the name of the puzzle is the combination of moves to find the solution
        
        }


        
        
	}

		 

		//the heuristic i was setting up for my a* algorithm was tiles out of place as well as a level of tree to equal the cost of a move
	


	

	

	// simply printing the elements of our input puzzle
	public static void displayPuzzle(int[][] result) {
		System.out.println("-------------------------------------------------");
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print("|\t" + result[i][j] + "\t|");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
	}

	// check the if the puzzle can be solved, by seeing if there is an even number
	// of inversions in the set
	public static int check(int[][] result) {

		int count = 0;
		for (int i = 0; i < result.length; i++) // iterates the rows of the 8 Puzzle
		{
			for (int j = 0; j < result[i].length; j++) // iterates the columns of the 8 Puzzle
			{

				for (int k = i; k < result.length; k++) // k=i because we don't care if the value of our number is
														// larger than the numbers above our value or to the left if you
														// one to think about it one dimensionally
				{
					for (int l = j; l < result[k].length; l++) // l=j for the same reason k=i, because we only care if
																// the values to our right are less than our current
																// value.
					{
						int number = result[i][j];
						// System.out.println(number + ">" + result[k][l]);
						if (number != 0 && result[k][l] != 0 && number > result[k][l])
						{
							
							count++;
							

						}

					}

				}

			}

		}

		if (count % 2 == 0) {
			System.out.println("Puzzle is Solveable");
		} else
			System.out.println("Puzzle is not Solveable");
		return count;
	}

	// this is leveraging the fact that inversions = 0 is in order, so we use our
	// check function,
	public static Boolean Solved(int[][] result) {
		Boolean Solved = false;
		if (check(result) == 0) {
			System.out.println("Puzzle is Solved");
			Solved = true;
		} else
			System.out.println("Puzzle is not Solved");

		return Solved;
	}
	
	
	//----------------------------------------------------------------------movement functions---------------------------------------------------------------------------------------------------------------------------------
	
	//valid move functions, we are of course assuming a square matrix as input
	
	public static  int[][] moveRight(int[][] result)
	{
		System.out.println("moveRight");

		int RightBound = result[1].length-1;
		int Cordinates[] = ZeroLocation(result);
		int SpaceX = Cordinates[0];
		int SpaceY = Cordinates[1];

		
		
		
		if(SpaceX+1 <= RightBound) 
		{
			
			result[SpaceY][SpaceX] = result[SpaceY][SpaceX+1];
			result[SpaceY][SpaceX+1] = 0;
			
			
		}
			else  //this is to make this impossible move infinitely costly
				{
			
						result[0][0] = 999;
						
				}
						
					
		
				
		
		return result;
		
		
		
		
		
		
		
		
			
		
	}
	
	
	public static  int[][] moveLeft(int[][] result)
	{
		System.out.println("moveLeft");

		int LeftBound = 0;
		int Cordinates[] = ZeroLocation(result);
		int SpaceX = Cordinates[0];
		int SpaceY = Cordinates[1];

		
		
		
		if(SpaceX-1 >= LeftBound) 
		{
			//System.out.println(result[SpaceY][SpaceX-1]);
			result[SpaceY][SpaceX] = result[SpaceY][SpaceX-1];
			result[SpaceY][SpaceX-1] = 0;
			
			
		}
		else  //this is to make this impossible move infinitely costly
		{
	
				result[0][0] = 999;
				
		}
		return result;
		
		
		
		
		
		
		
		
			
		
	}
	public static  int[][] moveDown(int[][] result)
	{
		System.out.println("moveDown");

		int LowerBound = result.length-1;
		int Cordinates[] = ZeroLocation(result);
		int SpaceX = Cordinates[0];
		int SpaceY = Cordinates[1];

		
		
		
		if(SpaceY+1 <= LowerBound) 
		{
			//System.out.println(result[SpaceY+1][SpaceX]);
			result[SpaceY][SpaceX] = result[SpaceY+1][SpaceX];
			result[SpaceY+1][SpaceX] = 0;
			
			
		}
		else  //this is to make this impossible move infinitely costly
		{
	
				result[0][0] = 999;
				
		}
		return result;
		
		
		
		
		
		
		
		
			
		
	}
	public static  int[][] moveUp(int[][] result)
	{
		System.out.println("moveUp");
		//System.out.println("rows = "+ result.length);
		//System.out.println("columns = "+ result[1].length);
		int RightBound = 0;
		int Cordinates[] = ZeroLocation(result);
		int SpaceX = Cordinates[0];
		int SpaceY = Cordinates[1];
		//System.out.println("x = " + SpaceX);
		//System.out.println("y = " +SpaceY);
		
		
		
		if(SpaceY-1 >= RightBound) 
		{
			//System.out.println(result[SpaceY-1][SpaceX]);
			result[SpaceY][SpaceX] = result[SpaceY-1][SpaceX];
			result[SpaceY-1][SpaceX] = 0;
			
			
		}		else  //this is to make this impossible move infinitely costly
		{
			
			result[0][0] = 999;
			
	}

		return result;
		
		
		
		
		
		
		
		
			
		
	}
	//returns the x y location of our empty tile x maps to j and y maps to i      the location of our empty space = result[y][x]
	public static  int[]  ZeroLocation(int[][] result){
		int[] cordinates = new int[2];
		for (int i = 0; i < result.length; i++) 
	{
		
		for (int j = 0; j < result[i].length; j++) 
		{
	
			if(result[i][j] == 0 ) {
				cordinates[0] = j;
				cordinates[1] = i;
				
			}
		}
		
	}
	
	return cordinates;
	}
	
	 static int[][] deepCopy(int[][] matrix) {
	    return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray($ -> matrix.clone());
	}
	//this was what was going to generate the next combination of moves and add it to our priority queue
	
	public static void GenerateSolutionsTree(Puzzle n) {
		int temp[][] = deepCopy(n.getState());
		//displayPuzzle(temp);
		
		int[][] right = moveRight(deepCopy(temp));
		if(right[0][0]!= 999) {
		
		
		PuzzlePriorityQueue.add(new Puzzle(n.getName()+"r", TilesOutOfPlace(right)+depth(n.getName()+1),right));
		}
		int[][] left = moveLeft(deepCopy(temp));
		if(left[0][0]!= 999) {
		
		PuzzlePriorityQueue.add(new Puzzle(n.getName()+"l", TilesOutOfPlace(left)+depth(n.getName()+1),left));
		}
		

		int[][] Up = moveUp(deepCopy((temp)));
		if(Up[0][0]!= 999) {
		
		PuzzlePriorityQueue.add(new Puzzle(n.getName()+"u", TilesOutOfPlace(Up)+depth(n.getName()+1),Up));
		}
		int[][] Down = moveDown(deepCopy(temp));
		if(Down[0][0]!= 999) {
		
		
		PuzzlePriorityQueue.add(new Puzzle(n.getName()+"d", AStarTotalPathCost(Down,n.getName()),Down));
		}
		
			

		
	}
  //this is h(n)
	public static int TilesOutOfPlace(int[][] result) {
		int outOfPlace = 0;
		int count = 1;
		for (int i = 0; i < result.length; i++) 
		{
			for (int j = 0; j < result[i].length; j++) 
			{
				if(result[i][j] != 0 && result[i][j] != count) 
				{
					outOfPlace ++;
				}
				if(result[i][j] != 0 ) {
					count++;
				}
			}
			
		}
		return outOfPlace;
	
	}
		
	//this is g(n)
	private static int depth(String n) {
		
		return n.length();
	}
	//this is my F(n)    A* Cost evaluation function F(n) = g(n) + h(n)
	public static int AStarTotalPathCost(int[][] temp, String Pathdepth) {
		int HeuristicCost;
		int pathDepthCost;
		//this is the h(n)
		HeuristicCost = TilesOutOfPlace(temp);
		//this is the g(n)
		pathDepthCost = depth(Pathdepth) + 1;
		//this is my F(n)
		int TotalHuristicCost = HeuristicCost + pathDepthCost; 
		return TotalHuristicCost;
		
	}


	public static void AddRightMoveToTree(Puzzle n,int[][] temp) {

		PuzzlePriorityQueue.add(new Puzzle(n.getName()+"R", TilesOutOfPlace(temp),temp));
		
	}
	
	
	public static void AddLeftMoveToTree(Puzzle n,int[][] temp) {

		PuzzlePriorityQueue.add(new Puzzle(n.getName()+"L", TilesOutOfPlace(temp),temp));		

	}
	
	
	public static void AddUpToTree(Puzzle n,int[][] temp) {
		
		PuzzlePriorityQueue.add(new Puzzle(n.getName()+"U", TilesOutOfPlace(temp),temp));		
	}
	
	
	public static void AddDownToTree(Puzzle n,int[][] temp) {
		
		PuzzlePriorityQueue.add(new Puzzle(n.getName()+"D", TilesOutOfPlace(temp),temp));		
	}
		
		
				
	//}
	
	//-----------------------------------------tree-------------------------






	
	

}

//this is to compare 2 objects based on their heuristic cost

class Puzzle implements Comparable<Puzzle> {
    private String name;
    private double Heuristic;
    private int[][] State;

    public Puzzle(String name, double Heuristic, int[][] State) {
        this.name = name;
        this.Heuristic = Heuristic;
        this.State = State;
    }

    public Puzzle(Puzzle T) {
    	name = T.name;
    	Heuristic = T.Heuristic;
    	State = T.State;
	}
	public int[][] getState() {
        return State;
    }

    public void setState(int[][] State) {
        this.State = State;
    }
	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeuristic() {
        return Heuristic;
    }

    public void setHeuristic(double Heuristic) {
        this.Heuristic = Heuristic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle Puzzle = (Puzzle) o;
        return Double.compare(Puzzle.Heuristic, Heuristic) == 0 &&
                Objects.equals(name, Puzzle.name);
    }



    @Override
    public String toString() {
        return "Puzzle{" +
                "name='" + name + '\'' +
                ", Heuristic=" + Heuristic +
                '}' + '\'' +
                ", State=" + displayPuzzle(State) +
                '}';
    }

    private String displayPuzzle(int[][] result) {
		
    	System.out.println("-------------------------------------------------");
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print("|\t" + result[i][j] + "\t|");
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------");
    	return "shown";
	}

	// Compare two Puzzle objects by their Heuristic
    @Override
    public int compareTo(Puzzle Puzzle) {
        if(this.getHeuristic() > Puzzle.getHeuristic()) {
            return 1;
        } else if (this.getHeuristic() < Puzzle.getHeuristic()) {
            return -1;
        } else {
            return 0;
        }
    }
}




