package main;

public class State {
	private boolean b;
	private int m;
	private int c;
	private String actions = "";
	
	
	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public boolean isB() {
		return b;
	}

	public int getM() {
		return m;
	}

	public int getC() {
		return c;
	}

	public String toString(){
		return m + " " + c + " " + b;
	}
	
	public void setState(int m, int c, boolean b){
		this.b = b;
		this.m = m;
		this.c = c;
	}
	
	public boolean isGoal(){
		if(m == 0 && c == 0 && b == true){
			return true;
		}
		return false;
	}
	
	public State move(int m, int c){
		State news = new State();
		if(this.b == false){
			news.setState(this.m - m, this.c - c, true);
		}else{
			news.setState(this.m + m, this.c + c, false);
		}
		
		if(
				news.getC() < 0 || news.getM() < 0 || news.getC() > 3 || news.getM() > 3 
				|| (news.getC() <= 0 && news.getM() > 0 && news.getM()!=3) 
				|| (news.getC() >= 3 && news.getM() < 3 && news.getM() != 0)
			){
			return null;
		}
		if((news.getM()==1&&news.getC()==2) || (news.getM()==2&&news.getC()==1)){
			return null;
		}
		return news;
	}
	
	public boolean equals(Object o){
		State state = (State)o;
		return this == state;
		
	}
}
