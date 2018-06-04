package main;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import interfaces.ActionRight;

public class Right extends JPanel implements ActionRight{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1781923412334534501L;
	
	private DefaultListModel<String> historyModel = new DefaultListModel<String>();
	private DefaultListModel<String> stepsModel = new DefaultListModel<String>();
	
	public Right(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel heading = new JLabel("History");
		heading.setPreferredSize(new Dimension(100, 30));
		Font font = new Font("Serif", Font.BOLD, 18);
		heading.setFont(font);
		
		JList<String> history = new JList<String>(historyModel);
		history.setSelectionModel(new NoSelectionModel());
		
		JList<String> steps = new JList<String>(stepsModel);
		steps.setSelectionModel(new NoSelectionModel());
		
		
		add(heading);
		add(new JScrollPane(history));
		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setFont(font);
		lblSteps.setPreferredSize(new Dimension(100, 30));
		add(lblSteps);
		add(new JScrollPane(steps));
	}
	
	private static class NoSelectionModel extends DefaultListSelectionModel {
		 /**
		 * 
		 */
		private static final long serialVersionUID = 287838006959264479L;

		@Override
		   public void setAnchorSelectionIndex(final int anchorIndex) {}

		   @Override
		   public void setLeadAnchorNotificationEnabled(final boolean flag) {}

		   @Override
		   public void setLeadSelectionIndex(final int leadIndex) {}

		   @Override
		   public void setSelectionInterval(final int index0, final int index1) { }
		 }

	@Override
	public void addHistory(String s) {
		historyModel.add(0, s);
	}
	@Override
	public void addStep(String s) {
		stepsModel.add(0, s);
	}
	@Override
	public void removeSteps() {
		stepsModel.removeAllElements();
	}
}
