package dfs;
import java.util.ArrayList;
import java.util.Stack;

import ess.State;


public class DFS extends ArrayList<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8519115080184318612L;

	public DFS(State init) {
		super();
		
		Node initial = new Node(init);

		Stack<Node> stack = new Stack<Node>();

		ArrayList<State> visitedList = new ArrayList<State>();

		stack.push(initial);

		while(!stack.isEmpty())
		{
			Node top = stack.pop();

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
				stack.push(temp);
			}

			State down = top.getState().performDown();
			if(down!=null)
			{
				Node temp = new Node(down, top);
				temp.add("DOWN");
				stack.push(temp);
			}

			State left = top.getState().performLeft();
			if(left!=null)
			{
				Node temp = new Node(left, top);
				temp.add("LEFT");
				stack.push(temp);
			}

			State right = top.getState().performRight();
			if(right!=null)
			{
				Node temp = new Node(right, top);
				temp.add("RIGHT");
				stack.push(temp);
			}
			visitedList.add(top.getState());
		}
	}
}
