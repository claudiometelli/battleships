package it.unicatt.poo.games.battleships.exceptions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.unicatt.poo.games.battleships.graphics.MainGUI;
import it.unicatt.poo.games.battleships.utils.CommonUtils;

public class PlaceShipException extends Exception {
	
	private static final long serialVersionUID = -4921901785461936009L;
	private int row;
	private int column;
	
	public PlaceShipException(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public String printError() {
		return "You can't place the ship in position: [" + this.row + ", " + CommonUtils.getAlphabeticalStringFromIndex(column) + "]";
	}
	
	public void printErrorToVideo() {
		JOptionPane.showMessageDialog(
				(JFrame)MainGUI.getInstance(),
				"You can't place the ship in position: [" + this.row + ", " + CommonUtils.getAlphabeticalStringFromIndex(column) + "]",
				"Error Placing your ship!",
				JOptionPane.ERROR_MESSAGE);
	}
}
