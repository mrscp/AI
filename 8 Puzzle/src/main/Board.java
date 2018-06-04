package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import astar.AStarSearch;
import bfs.BFS;
import dfs.DFS;
import ess.BTextField;
import ess.State;
import greedy.Greedy;
import idfs.IDFS;
import interfaces.ActionFooter;
import interfaces.ActionRight;
import interfaces.FooterInterface;
import interfaces.LeftListener;
import ucs.UCS;

public class Board extends JPanel implements DocumentListener, ActionFooter, Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1604814511280438526L;
	
	private BTextField[] labels = new BTextField[9];
	private boolean menual = false;
	private int [][] init;
	private int algoCount = 1;
	
	private FooterInterface footer;
	public void setFooter(FooterInterface footer) {
		this.footer = footer;
	}

	private LeftListener left;
	public void setLeft(LeftListener left) {
		this.left = left;
	}
	
	private ActionRight right;
	public void setRight(ActionRight right) {
		this.right = right;
	}

	private Thread thread;
	private boolean running = false;
	private boolean stop = false;
	
	public Board(int [][] init){
		super();
		setSize(new Dimension(400,400));
		this.init = init;
		
		setLayout(new GridLayout(3,3));
		menual = true;
		for(Integer i = 0; i <= 8; i++){
			labels[i] = new BTextField("" + init[i/3][i%3]);
			labels[i].getDocument().addDocumentListener(this);

			labels[i].setBackgroundImage(""+init[i/3][i%3]);
			
			add(labels[i]);
			
		}
		menual = false;
	}
	
	public void enableBoard(boolean b){
		for(Integer i = 0; i <= 8; i++){
			labels[i].setEditable(b);
		}
	}
	
	public int[][] getState(){
		int[][] init = new int[3][3];
		for(Integer i = 0; i <= 8; i++){
			init[i/3][i%3] = Integer.parseInt(labels[i].getTextFieldText());
		}
		return init;
	}
	
	public void setLabels(int [][] ls){
		menual = true;
		for(Integer i = 0; i <= 8; i++){
			labels[i].setBackgroundImage(""+ls[i/3][i%3]);
			labels[i].setTextFieldText("" + ls[i/3][i%3]);
		}
		menual = false;
	}

	public void changeBG(){
		for(Integer i = 0; i <= 8; i++){
			labels[i].setBackgroundImage(""+init[i/3][i%3]);
			//labels[i].setBackground(Color.black);
		}
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if(menual == false){
			init = getState();
			changeBG();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) { }
	
	@Override
	public void run() {
		State temp = new State(init);
		String algo = left.getAlgorithm();
		ArrayList<String> actions = null;
		right.removeSteps();
		
		long startTime = 0;
		long stopTime = 0;
		
		if(algo.equals("AStar")){
			startTime = System.currentTimeMillis();
			actions = new AStarSearch(temp, left.getHeurestic());
			stopTime = System.currentTimeMillis();
		}else if(algo.equals("BFS")){
			startTime = System.currentTimeMillis();
			actions = new BFS(temp);
			stopTime = System.currentTimeMillis();
		}else if(algo.equals("UCS")){
			startTime = System.currentTimeMillis();
			actions = new UCS(temp);
			stopTime = System.currentTimeMillis();
		}else if(algo.equals("Greedy")){
			startTime = System.currentTimeMillis();
			actions = new Greedy(temp);
			stopTime = System.currentTimeMillis();
		}else if(algo.equals("DFS")){
			startTime = System.currentTimeMillis();
			actions = new DFS(temp);
			stopTime = System.currentTimeMillis();
		}else if(algo.equals("IDFS")){
			startTime = System.currentTimeMillis();
			actions = new IDFS(temp);
			stopTime = System.currentTimeMillis();
		}
		
		long elapsedTime = stopTime - startTime;
		
		right.addHistory(algoCount++ + ". " + algo + " [" + actions.size() + "steps, " + elapsedTime + "ms]" ); 
		int stepcount = 1;
		for(int i = 0; i < actions.size(); i++){
			try {
				if(stop != true){
					Thread.sleep(1000 * footer.getSpeed());
				}
				if(running == true){
					right.addStep(stepcount++ + ". " + actions.get(i));
					temp = temp.perform(actions.get(i));
					setLabels(temp.getPuzzle());
				}else if(stop != true){
					i-=1;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			if(stop != true){
				Thread.sleep(1000 * footer.getSpeed());
			}
			if(temp.isGoal()){
				JOptionPane.showMessageDialog(this, "Congratulations! \nThe 8 Puzzle has been solved");
			}else{
				if(stop != true){
					JOptionPane.showMessageDialog(this, "Sorry!\nThis program couldn't solve this 8 Puzzle!");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actionStop(null);
		
		if(stop == true){
			footer.enableButtons(true);
		}
	}
	int getInvCount()
	{
	    int inv_count = 0;
	    int array[] = new int[9];
	    int pos = 0;
	    for(int i = 0; i < 3; i++){
	    	for(int j = 0; j < 3; j++){
		    	if(init[i][j] > 0){
		    		array[pos++] = init[i][j];
		    	}
		    }
	    }
	    for (int i = 0; i < array.length-1; i++)
	        for (int j = i+1; j < array.length; j++)
	             if (array[i] > array[j])
	                  inv_count++;
	    return inv_count;
	}
	
	@Override
	public void actionStart(ActionEvent e) {
		enableBoard(false);
		footer.generateEnable(false);
		if(stop == true || running == false){
			init = getState();
		}
		if(getInvCount()%2!=0){
			JOptionPane.showMessageDialog(this, "Oops.....!\nThis sequence of 8 puzzle could not be solved!");
			footer.reset();
		}else{
			setLabels(init);
			stop = false;
			running = true;
				if(thread == null){
				thread = new Thread(this);
				thread.start();
			}
		}
	}
	
	@Override
	public void actionPause(ActionEvent e) {
		running = false;
	}
	
	@Override
	public void actionStop(ActionEvent e) {
		enableBoard(true);
		if(thread != null){
			footer.enableButtons(false);
			running = false;
			stop = true;
			thread = null;
			footer.reset();
			setLabels(init);
		}
	}
	
	@Override
	public void actionGenerate(ActionEvent e) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		//int max = 8, min = 0;
		while(list.size() < 9){
			int rand = random.nextInt(9);
			if(!list.contains(rand)){
				list.add(rand);
			}
		}
		for(int i = 0; i < 9; i++){
			init[i%3][i/3] = list.get(i);
		}
		setLabels(init);
	}
}
