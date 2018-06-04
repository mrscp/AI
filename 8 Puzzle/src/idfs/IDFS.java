package idfs;
import java.util.ArrayList;
import java.util.Stack;

import ess.State;

public class IDFS extends ArrayList<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8519115080184318612L;

	public IDFS(State init) {
		super();
		
		initial = new Node(init);
		Stack<Node> stack = new Stack<Node>();
		this.addAll(searchLabel(stack));
	}
	private Node initial;
	private ArrayList<State> visitedList = new ArrayList<State>();
	
	public ArrayList<String> searchLabel(Stack<Node> stack){
		
		Stack<Node> tempStack = new Stack<Node>();
		stack.push(initial);

		while(!stack.isEmpty())
		{
			Node top = stack.pop();
			System.out.println(top.getState());
			if(top.getState().isGoal()==true){
				return top;
			}
			
			if(visitedList.contains(top.getState()))
				continue;

			State up = top.getState().performUp();
			if(up!=null)
			{
				Node temp = new Node(up, top);
				temp.add("UP");
				tempStack.push(temp);
			}

			State down = top.getState().performDown();
			if(down!=null)
			{
				Node temp = new Node(down, top);
				temp.add("DOWN");
				tempStack.push(temp);
			}

			State left = top.getState().performLeft();
			if(left!=null)
			{
				Node temp = new Node(left, top);
				temp.add("LEFT");
				tempStack.push(temp);
			}

			State right = top.getState().performRight();
			if(right!=null)
			{
				Node temp = new Node(right, top);
				temp.add("RIGHT");
				tempStack.push(temp);
			}
			visitedList.add(top.getState());
		}
		stack = tempStack;
		
		return searchLabel(tempStack);
	}
}
