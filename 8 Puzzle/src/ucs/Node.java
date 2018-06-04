package ucs;

import java.util.ArrayList;

import ess.State;


public class Node extends ArrayList<String> implements Comparable<Node>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4812077641753047066L;
	
	private State state;
	
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	int cost = Integer.MAX_VALUE;
	public int getCost() {
		return cost;
	}
	
	public Node(State init){
		super();
		state = init;
	}
	
	public Node(State init, ArrayList<String> list){
		this(init);
		addAll(list);
	}
	
	public void calculateCost() {
		cost = gn()+hn();
	}
	public int gn()
	{
		return size();
	}
	
	public int hn()
	{
		return 0;
	}
	@Override
	public int compareTo(Node o) {
		return this.getCost() - o.getCost();
	}

}
