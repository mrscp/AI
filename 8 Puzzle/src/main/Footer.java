package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.ActionFooter;
import interfaces.FooterInterface;

public class Footer extends JPanel implements ActionListener, FooterInterface {
	
	private static final long serialVersionUID = 3501215232125545439L;
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton generate = new JButton("Generate");
	private ActionFooter actionFooter;
	private JTextField speed = new JTextField("1");
	
	public void setActionFooter(ActionFooter af){
		actionFooter = af;
	}
	
	public Footer(){
		super();
		start.addActionListener(this);
		stop.addActionListener(this);
		generate.addActionListener(this);
		speed.setPreferredSize(new Dimension(50,30));
		
		add(generate);
		add(new JLabel("Animation Speed (Second): "));
		add(speed);
		add(start);
		add(stop);
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public Integer getSpeed() {
		if(isInteger(speed.getText())){
			int i = Integer.parseInt(speed.getText());
			if(i < 0){
				i = 1;
			}else if(i > 5){
				i = 5;
			}
			return i;
		}else{
			return 1;
		}
	}
	
	@Override
	public void reset(){
		start.setText("Start");
		enableButtons(true);
	}
	
	@Override
	public void enableButtons(Boolean b){
		start.setEnabled(b);
		stop.setEnabled(b);
		generateEnable(b);		
	}
	
	@Override
	public void generateEnable(boolean b){
		generate.setEnabled(b);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.equals(start)){
			if(button.getText() == "Start"){
				start.setText("Pause");
				actionFooter.actionStart(e);
			}else{
				start.setText("Start");
				actionFooter.actionPause(e);
			}
		}else if(e.getSource().equals(stop)){
			start.setText("Start");
			actionFooter.actionStop(e);
		}else if(e.getSource().equals(generate)){
			actionFooter.actionGenerate(e);
		}
	}
}
