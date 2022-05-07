package it.unicatt.poo.games.battleships.exceptions;

import it.unicatt.poo.games.battleships.core.Board;

public class BoardOutOfRangeException extends Exception {
	
	private static final long serialVersionUID = -8919550431472678714L;
	private int rows;
	private int columns;
	
	public BoardOutOfRangeException(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
	}
	
	public String printError() {
		return "Your Board is " + rows + " rows and " + columns + " columns.\nYour board must respect the following dimensions: MAX " + Board.MAX_ROWS + " ROWS, MAX " + Board.MAX_COLUMNS + " COLUMNS.";
	}
}
