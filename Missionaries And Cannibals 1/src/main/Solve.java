package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class Solve {
	
	public static void main(String ares[]){
		State initial = new State();
		initial.setState(3, 3, false);
		Queue<State> que = new LinkedList<State>();
		
		HashMap<State, Boolean> visited = new HashMap<State, Boolean>();
		
		
		que.add(initial);
		
		while(!que.isEmpty()){
			State news = que.remove();
			if(!visited.containsKey(news)){
				visited.put(news, true);
				State m1 = news.move(1, 0);
				if(m1 != null && m1.isGoal()){
					m1.setActions(news.getActions() + " m1" );
					System.out.println(m1 + m1.getActions());
					break;
				}else if(m1 != null){
					m1.setActions(news.getActions() + " m1" );
					que.add(m1);
				}
				
				State m2 = news.move(2, 0);
				if(m2 != null && m2.isGoal()){
					m2.setActions(news.getActions() + " m2" );
					System.out.println(m2 + m2.getActions());
					break;
				}else if(m2 != null){
					m2.setActions(news.getActions() + " m2" );
					que.add(m2);
				}
				
				State c1 = news.move(0, 1);
				if(c1 != null && c1.isGoal()){
					c1.setActions(news.getActions() + " c1" );
					System.out.println(c1 + c1.getActions());
					break;
				}else if(c1 != null){
					c1.setActions(news.getActions() + " c1" );
					que.add(c1);
				}
				
				State c2 = news.move(0, 2);
				if(c2 != null && c2.isGoal()){
					c2.setActions(news.getActions() + " c2" );
					System.out.println(c2 + c2.getActions());
					break;
				}else if(c2 != null){
					c2.setActions(news.getActions() + " c2" );
					que.add(c2);
				}
				
				State m1c1 = news.move(1, 1);
				if(m1c1 != null && m1c1.isGoal()){
					m1c1.setActions(news.getActions() + " m1c1" );
					System.out.println(m1c1 + m1c1.getActions());
					break;
				}else if(m1c1 != null){
					m1c1.setActions(news.getActions() + " m1c1" );
					que.add(m1c1);
				}
			}
		}
	}
}
