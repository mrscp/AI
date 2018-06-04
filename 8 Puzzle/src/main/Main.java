package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1554119310077614846L;
	//private int [][] init={{1,4,2},{0,3,7},{6,8,5}};
	//int [][] init={{1,4,7},{6,0,2},{8,5,3}};
	//int [][] init={{8,1,2},{0,4,3},{7,6,5}};//not solvable
	private int [][] init={{0,1,2},{3,4,5},{6,7,8}};
	
	private Board board;
	private Footer footer;
	private Left left;
	private Right right;

	public Main(){
		super("8 Puzzle");
		setLayout(new BorderLayout());
		
		board = new Board(init);
		
		footer = new Footer();
		left = new Left();
		left.setPreferredSize(new Dimension(200, 400));
		right = new Right();
		right.setPreferredSize(new Dimension(200, 400));
		
		footer.setActionFooter(board);
		board.setFooter(footer);
		board.setLeft(left);
		board.setRight(right);
		
		JLabel top = new JLabel("Please, stop the puzzle to set a new initial state, and click on the field to change the value!");
		top.setHorizontalAlignment(SwingConstants.CENTER);
		top.setFont(new Font(top.getFont().getFontName(), top.getFont().getStyle(), 18));
		top.setForeground(Color.red);
		
		add(top, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		add(footer, BorderLayout.SOUTH);
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);
		
		pack();
		setSize(1000,600);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	new Main();
	        }
	    });
	}
}
