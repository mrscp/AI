package interfaces;

import java.awt.event.ActionEvent;

public interface ActionFooter {
	public void actionStart(ActionEvent e);
	public void actionPause(ActionEvent e);
	public void actionStop(ActionEvent e);
	public void actionGenerate(ActionEvent e);
}
