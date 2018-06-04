package astar;

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
	
	public void calculateCost(String heurestic) {
		cost = gn() + hn(heurestic);
	}
	public int gn()
	{
		return size();
	}
	
	public int hn(String heurestic)
	{
		
		int sum = 0;
		if(heurestic.equals("Manhattan")){
			for(int i = 0; i <= 8; i++){
				int a[][] = state.getPuzzle();
				if(a[i/3][i%3] == 0) continue;
				else{
					int x = (i/3) - (a[i/3][i%3]/3);
					if(x < 0)
						x *= -1;
					int y = (i%3) - (a[i/3][i%3]%3);
					if(y < 0)
						y *= -1;
					
					sum += x+y;
				}
			}
		}else if(heurestic.equals("Euclidean")){
			for(int i = 0; i <= 8; i++){
				int a[][] = state.getPuzzle();
				if(a[i/3][i%3] == 0) continue;
				else{
					int x = (i/3) - (a[i/3][i%3]/3);
					int y = (i%3) - (a[i/3][i%3]%3);
					sum += Math.sqrt((x*x)+(y*y));
				}
			}
		}else if(heurestic.equals("Misplaced")){
			for(int i = 0; i <= 8; i++){
				int a[][] = state.getPuzzle();
				if(a[i/3][i%3] == 0) continue;
				else{
					if(a[i/3][i%3] == i){
						continue;
					}else{
						sum++;
					}
				}
			}
		}
		
		return sum;
	}
	@Override
	public int compareTo(Node o) {
		return this.getCost() - o.getCost();
	}

}
