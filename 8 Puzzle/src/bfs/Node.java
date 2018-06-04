package bfs;

import java.util.ArrayList;

import ess.State;


public class Node extends ArrayList<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6699381214817096141L;
	
	private State state;
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Node(State init) {
		super();
		this.state = init;
	}
	
	public Node(State init, ArrayList<String> list) {
		this(init);
		this.addAll(list);
	}
}
