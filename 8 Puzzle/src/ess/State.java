package ess;


public class State {
	private int[][] puzzle=new int[3][3];

	public int[][] getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(int[][] puzzle) {
		this.puzzle = puzzle;
	}
	
	public State(){}
	
	public State(int[][] puzzle){
		setPuzzle(puzzle);
	}

	public String toString() {
		String temp = "";
		for(int i=0;i<puzzle.length;i++)
		{
			for(int j=0;j<puzzle.length;j++)
			{
				temp  = temp+ puzzle[i][j]+" ";
			}
			temp = temp + "\n";
		}
		return temp;
	}	
	
	public boolean isGoal()
	{
		int count=0;
		for(int i=0;i<puzzle.length;i++)
		{
			for(int j=0;j<puzzle.length;j++)
			{
				if(puzzle[i][j]!=count)
					return false;
				count++;
			}
			
		}
		return true;
	}
	
	public int[] getPosition(){
		int pos[] = {0,0};
		boolean found =false;
		
		//row
		for(pos[0]=0;pos[0]<=2;pos[0]++)
		{		
			//col
			for(pos[1]=0;pos[1]<=2;pos[1]++)
				if(puzzle[pos[0]][pos[1]]==0)
					{
						found =true;
						break;
					}
			if(found==true)
				break;
		}
		
		return pos;
	}
	
	public State performUp()
	{
		// first find the position of zero
		int pos[] = getPosition();
		int row = pos[0];
		int col = pos[1];
		// if row of zero is 0 you can not perform up
		if(row==0) 
			return null;
		
		State up = new State();
		// copy the original puzzle into up
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				up.getPuzzle()[i][j]=puzzle[i][j];
		
		up.getPuzzle()[row][col]=puzzle[row-1][col];
		up.getPuzzle()[row-1][col]=0;
		return up;
	}
	
	public State performDown()
	{
		// first find the position of zero
				int pos[] = getPosition();
				int row = pos[0];
				int col = pos[1];
		// if row of zero is 2 you can not 
		// perform down
		if(row==2) 
			return null;
		
		State down = new State();
		// copy the original puzzle into up
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				down.getPuzzle()[i][j]=puzzle[i][j];
		
		down.getPuzzle()[row][col]=puzzle[row+1][col];
		down.getPuzzle()[row+1][col]=0;
		return down;
	}

	public State performLeft()
	{
		// first find the position of zero
				int pos[] = getPosition();
				int row = pos[0];
				int col = pos[1];
		// if col of zero is 0 you can not 
		// perform left
		if(col==0) 
			return null;
		
		State left = new State();
		// copy the original puzzle into up
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				left.getPuzzle()[i][j]=puzzle[i][j];
		
		left.getPuzzle()[row][col]=puzzle[row][col-1];
		left.getPuzzle()[row][col-1]=0;
		return left;
	}
	public State performRight()
	{
		// first find the position of zero
				int pos[] = getPosition();
				int row = pos[0];
				int col = pos[1];
		// if col of zero is 2 you can not 
		// perform left
		if(col==2) 
			return null;
		
		State right = new State();
		// copy the original puzzle into up
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				right.getPuzzle()[i][j]=puzzle[i][j];
		
		right.getPuzzle()[row][col]=puzzle[row][col+1];
		right.getPuzzle()[row][col+1]=0;
		return right;
	}
	
	public State perform(String action){
		if(action.equals("LEFT")) return performLeft();
		else if(action.equals("RIGHT")) return performRight();
		else if(action.equals("UP")) return performUp();
		else if(action.equals("DOWN")) return performDown();
		else return null;
	}
	
	public boolean equals(Object o)
	{
		State s = (State)o;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(s.getPuzzle()[i][j]!=
				puzzle[i][j])
					return false;
		return true;
	}
		
	
	
}
