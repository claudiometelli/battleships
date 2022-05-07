package it.unicatt.poo.games.battleships.graphics;

import java.awt.Font;

import javax.swing.JLabel;

public class DialogWindowGUI extends JLabel {
	
	private static final long serialVersionUID = 838010405894253330L;

	public DialogWindowGUI() {
		this.initalConfiguration();
	}
	
	public DialogWindowGUI(String text) {
		super(text);
		this.initalConfiguration();
	}
	
	private void initalConfiguration() {
		this.setHorizontalAlignment(CENTER);
		this.setFont(new Font("Century Schoolbook", Font.PLAIN, 22));
	}
}
