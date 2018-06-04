package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import interfaces.LeftListener;

public class Left extends JPanel implements LeftListener, ItemListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2848787253907319669L;
	
	private JRadioButton bfs = new JRadioButton("BFS");
	private JRadioButton astar = new JRadioButton("A* Search");
	private JRadioButton ucs = new JRadioButton("UCS");
	private JRadioButton greedy = new JRadioButton("Greedy");
	private JRadioButton dfs = new JRadioButton("DFS");
	private JRadioButton idfs = new JRadioButton("IDFS");
	
	private ButtonGroup bgh = new ButtonGroup();
	private JRadioButton manhattan = new JRadioButton("Manhattan");
	private JRadioButton euclidean = new JRadioButton("Euclidean");
	private JRadioButton misplaced = new JRadioButton("Misplaced");
	
	
	public Left(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel heading = new JLabel("Algorithm");
		heading.setPreferredSize(new Dimension(100, 100));
		Font font = new Font("Serif", Font.BOLD, 18);
		heading.setFont(font);
		ButtonGroup bg = new ButtonGroup();
		
		astar.setSelected(true);
		astar.addItemListener(this);
		ucs.addItemListener(this);
		greedy.addItemListener(this);
		
		bg.add(bfs);
		bg.add(astar);
		bg.add(ucs);
		bg.add(greedy);
		bg.add(dfs);
		bg.add(idfs);
		
		add(heading);
		add(astar);
		add(bfs);
		add(ucs);
		add(greedy);
		add(dfs);
		add(idfs);
		
		
		manhattan.setSelected(true);
		
		bgh.add(manhattan);
		bgh.add(euclidean);
		bgh.add(misplaced);
		
		setHeuristicSelectable(true);
		JLabel heurestic = new JLabel("Heuristics");
		heurestic.setFont(font);
		add(heurestic);
		add(manhattan);
		add(euclidean);
		add(misplaced);
	}
	
	public void setHeuristicSelectable(boolean b){
		Enumeration<AbstractButton> enumeration = bgh.getElements();
		while (enumeration.hasMoreElements()) {
		    enumeration.nextElement().setEnabled(b);
		}
	}
	
	@Override
	public String getAlgorithm() {
		if(bfs.isSelected()){
			return "BFS";
		}else if(astar.isSelected()){
			return "AStar";
		}else if(ucs.isSelected()){
			return "UCS";
		}else if(greedy.isSelected()){
			return "Greedy";
		}else if(dfs.isSelected()){
			return "DFS";
		}else if(idfs.isSelected()){
			return "IDFS";
		}
		return null;
	}
	
	@Override
	public String getHeurestic() {
		if(manhattan.isSelected()){
			return "Manhattan";
		}else if(euclidean.isSelected()){
			return "Euclidean";
		}else if(misplaced.isSelected()){
			return "Misplaced";
		}
		return null;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(astar.isSelected()){
			setHeuristicSelectable(true);
		}else{
			setHeuristicSelectable(false);
		}
	}

}
