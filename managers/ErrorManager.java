package it.unicatt.poo.games.battleships.managers;

import javax.swing.JOptionPane;

import it.unicatt.poo.games.battleships.graphics.MainGUI;

public class ErrorManager {
	
	public static void placeShipError() {
		JOptionPane.showMessageDialog(MainGUI.getInstance(),
				"You can't place your ship here!",
				"Errore!",
				JOptionPane.ERROR_MESSAGE);
	}
	
}
