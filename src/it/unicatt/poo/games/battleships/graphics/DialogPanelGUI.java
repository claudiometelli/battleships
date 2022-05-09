package it.unicatt.poo.games.battleships.graphics;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class DialogPanelGUI extends JPanel {
	
	private static final long serialVersionUID = -3678988956433982106L;
	
	DialogWindowGUI playerLabel;
	DialogWindowGUI enemyLabel;
	
	public DialogPanelGUI() {
		this.setLayout(new GridLayout(2, 1));
		this.playerLabel = new DialogWindowGUI("Player");
		this.enemyLabel = new DialogWindowGUI("Enemy");
		this.add(playerLabel);
		this.add(enemyLabel);
	}
	
}
