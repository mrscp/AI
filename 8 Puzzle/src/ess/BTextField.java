package ess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class BTextField extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7811897782905650630L;
	private JTextField textField = new JTextField("I");

	public BTextField(String text){
		super();
		setLayout(new BorderLayout());
		textField.setText(text);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setFont(new Font("Serif", Font.BOLD, 48));
		textField.setOpaque(false);
		textField.setForeground(Color.WHITE);
		
		add(textField, BorderLayout.CENTER);
	}
	
	
	public void setBackgroundImage(String image){
		if(image.equals("0")){
			setIcon(null);
		}else{
			setIcon(new ImageIcon(new ImageIcon("images/"+image+".jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		}
	}
	
	public Document getDocument(){
		return textField.getDocument();
	}
	
	public String getTextFieldText(){
		return textField.getText();
	}
	
	public void setTextFieldText(String s){
		textField.setText(s);
	}
	
	public void setEditable(Boolean b){
		textField.setEditable(b);
	}
}
