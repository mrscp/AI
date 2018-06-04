package greedy;

import java.util.ArrayList;
import java.util.PriorityQueue;

import ess.State;


public class Greedy extends ArrayList<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3387730719127018626L;

	public Greedy(State init){
		super();
		addAll(aStarSearch(init));
	}
	
	public static ArrayList<String> aStarSearch(State init)
	{
		// Create a node
		Node initial = new Node(init);
		initial.calculateCost();
		
		// create a Queue
		PriorityQueue<Node> PQ = new PriorityQueue<Node>();
		// create a visited List

		ArrayList<State> visitedList = 
				new ArrayList<State>();

		// add the initial node to the queue
		PQ.add(initial);

		// main loop for BFS
		while(!PQ.isEmpty())
		{
			// pop from the front of the queue
			Node top = PQ.poll();

			// first check goal
			if(top.getState().isGoal()==true)
				return top;

			// if it is already in visited list do nothing
			if(visitedList.contains(top.getState()))
				continue;

			// expand
			State up = top.getState().performUp();
			if(up!=null)
			{
				Node temp = new Node(up, top);
				temp.add("UP");
				temp.calculateCost();
				PQ.add(temp);
			}

			State down = top.getState().performDown();
			if(down!=null)
			{
				Node temp = new Node(down, top);
				temp.add("DOWN");
				temp.calculateCost();
				PQ.add(temp);
			}

			State left = top.getState().performLeft();
			if(left!=null)
			{
				Node temp = new Node(left, top);
				temp.add("LEFT");
				temp.calculateCost();
				PQ.add(temp);
			}

			State right = top.getState().performRight();
			if(right!=null)
			{
				Node temp = new Node(right, top);
				temp.add("RIGHT");
				temp.calculateCost();
				PQ.add(temp);
			}

			// add the top to visited list
			visitedList.add(top.getState());
		}
		return null;
	}
}
