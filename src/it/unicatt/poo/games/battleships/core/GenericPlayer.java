package it.unicatt.poo.games.battleships.core;

import it.unicatt.poo.games.battleships.beans.Coordinate;

public abstract class GenericPlayer {
	
	private Board board;
	
	public GenericPlayer() { }
	
	public GenericPlayer(Board board) {
		this.board = board;
	}
	
	public boolean shoot(Board board, int row, int column) {
		boolean result = false;
		Coordinate target = board.getCoordinateByRowAndColumn(row, column);
		if(target.getShipComponent() == null) {
			target.setMiss(true);
		}
		else {
			boolean hit = true;
			target.setHit(hit);
			target.getShipComponent().setHit(hit);
			result = hit;
		}
		return result;
	};
	
	public Board getBoard() {
		return board;
	}
}
