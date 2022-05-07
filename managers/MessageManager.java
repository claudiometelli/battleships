package it.unicatt.poo.games.battleships.managers;

import javax.swing.JOptionPane;

import it.unicatt.poo.games.battleships.graphics.MainGUI;

public class MessageManager {
	
	public static int winMessage() {
		JOptionPane.showMessageDialog(MainGUI.getInstance(),
				"YOU WIN!",
				"Winner!",
				JOptionPane.INFORMATION_MESSAGE);
		return JOptionPane.showConfirmDialog(MainGUI.getInstance(),
				"Wanna play again?",
				"Winner!",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
	}
	
	public static int gameOverMessage() {
		JOptionPane.showMessageDialog(MainGUI.getInstance(),
				"YOU LOSE!",
				"Oh, no!",
				JOptionPane.INFORMATION_MESSAGE);
		return JOptionPane.showConfirmDialog(MainGUI.getInstance(),
				"Wanna play again?",
				": (",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
	}
	
	public static int drawMessage() {
		JOptionPane.showMessageDialog(MainGUI.getInstance(),
				"OMG! This is a draw!",
				"Incredible!",
				JOptionPane.INFORMATION_MESSAGE);
		return JOptionPane.showConfirmDialog(MainGUI.getInstance(),
				"Wanna play again?",
				": (",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
	}
	
	public static void quitGame() {
		JOptionPane.showMessageDialog(MainGUI.getInstance(),
				"Thank You for playing!",
				": )",
				JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
}
