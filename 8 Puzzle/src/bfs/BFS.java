package bfs;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ess.State;


public class BFS extends ArrayList<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8519115080184318612L;

	public BFS(State init) {
		super();
		
		Node initial = new Node(init);

		Queue<Node> Q = new LinkedList<Node>();

		ArrayList<State> visitedList = new ArrayList<State>();

		Q.add(initial);

		while(!Q.isEmpty())
		{
			Node top = Q.poll();

			if(top.getState().isGoal()==true){
				this.addAll(top);
				break;
			}
			
			if(visitedList.contains(top.getState()))
				continue;

			State up = top.getState().performUp();
			if(up!=null)
			{
				Node temp = new Node(up, top);
				temp.add("UP");
				Q.add(temp);
			}

			State down = top.getState().performDown();
			if(down!=null)
			{
				Node temp = new Node(down, top);
				temp.add("DOWN");
				Q.add(temp);
			}

			State left = top.getState().performLeft();
			if(left!=null)
			{
				Node temp = new Node(left, top);
				temp.add("LEFT");
				Q.add(temp);
			}

			State right = top.getState().performRight();
			if(right!=null)
			{
				Node temp = new Node(right, top);
				temp.add("RIGHT");
				Q.add(temp);
			}
			visitedList.add(top.getState());
		}
	}
}
