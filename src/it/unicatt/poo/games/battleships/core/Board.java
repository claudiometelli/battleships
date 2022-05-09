package it.unicatt.poo.games.battleships.core;

import java.io.Serializable;

import it.unicatt.poo.games.battleships.beans.Coordinate;
import it.unicatt.poo.games.battleships.beans.Ship;
import it.unicatt.poo.games.battleships.beans.ShipType;
import it.unicatt.poo.games.battleships.constconfig.BattleshipsSettings;
import it.unicatt.poo.games.battleships.exceptions.BoardOutOfRangeException;
import it.unicatt.poo.games.battleships.exceptions.PlaceShipException;
import it.unicatt.poo.games.battleships.utils.CommonUtils;

public class Board implements Serializable {

	private static final long serialVersionUID = -2668327823065403899L;
	
	public static final int MIN_ROWS = 5;
	public static final int MIN_COLUMNS = 5;
	public static final int MAX_ROWS = 20;
	public static final int MAX_COLUMNS = 20;
	
	private Coordinate[] coordinates;
	private String[] rowLabels;
	private String[] columnLabels;
	
	public Board(int rows, int columns) throws BoardOutOfRangeException {
		if(rows < Board.MIN_ROWS || columns < Board.MIN_COLUMNS || rows > Board.MAX_ROWS || columns > Board.MAX_COLUMNS) {
			throw new BoardOutOfRangeException(rows, columns);
		}
		this.rowLabels = new String[rows];
		for(int i = 0; i < rows; i++) {
			this.rowLabels[i] = Integer.toString(i + 1);
		}
		this.columnLabels = new String[columns];
		for(int i = 0; i < columns; i++) {
			this.columnLabels[i] = CommonUtils.getAlphabeticalStringFromIndex(i);
		}
		this.coordinates = new Coordinate[rows * columns];
		int counter = 0;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				coordinates[counter] = new Coordinate(i, j);
				counter++;
			}
		}
	}
	
	public static Board createDefaultBoard() {
		return Board.createBoard(BattleshipsSettings.TABLE_ROWS, BattleshipsSettings.TABLE_COLUMNS);
	}
	
	public Coordinate[] getCoordinates() {
		return coordinates;
	}
	
	public String[] getRowLabels() {
		return rowLabels;
	}
	
	public String[] getColumnLabels() {
		return columnLabels;
	}
	
	public int getHeight() {
		return this.rowLabels.length;
	}
	
	public int getWidth() {
		return this.columnLabels.length;
	}
	
	public Coordinate getCoordinateByRowAndColumn(int row, int column) {
		for(Coordinate coordinate : this.coordinates) {
			if(row == coordinate.getRow() && column == coordinate.getColumn()) {
				return coordinate;
			}
		}
		return null;
	}
	
	public void placeShipByPlayer(Ship ship, int startRow, String startColumn, boolean vertical) throws PlaceShipException {
		placeShip(ship, startRow - 1, CommonUtils.getAlphabeticalIndexFromString(startColumn), vertical);
	}
	
	private void placeShip(Ship ship, int startRow, int startColumn, boolean vertical) throws PlaceShipException {
		if(!vertical && startColumn > this.getWidth() - ship.getType().getSize()) {
			throw new PlaceShipException(startRow, startColumn);
		}
		if(vertical && startRow > this.getHeight() - ship.getType().getSize()) {
			throw new PlaceShipException(startRow, startColumn);
		}
		for(int i = 0; i < ship.getType().getSize(); i++) {
			if(vertical && getCoordinateByRowAndColumn(startRow + i, startColumn).getShipComponent() != null) {
				throw new PlaceShipException(startRow, startColumn);
			}
			else if(!vertical && getCoordinateByRowAndColumn(startRow, startColumn + i).getShipComponent() != null) {
				throw new PlaceShipException(startRow, startColumn);
			}
		}
		for(int i = 0; i < ship.getType().getSize(); i++) {
			if(vertical) {
				getCoordinateByRowAndColumn(startRow + i, startColumn).setShipComponent(ship.getShipComponents()[i]);
			}
			else {
				getCoordinateByRowAndColumn(startRow, startColumn + i).setShipComponent(ship.getShipComponents()[i]);
			}
		}
	}
	
	public String printBoard(boolean showShips) {
		int max_row_digits = (int) Math.log10(Double.valueOf(Board.MAX_ROWS));
		StringBuilder result = new StringBuilder("   ");
		for(String label: this.columnLabels) {
			result.append(label + "  ");
		}
		result.append("\n");
		for(Coordinate coordinate: this.coordinates) {
			if(coordinate.getColumn() == 0) {
				for(int i = 0; i < max_row_digits - Math.log10(Double.valueOf(coordinate.getRow() + 1)); i++) {
					result.append(" ");
				}
				result.append(coordinate.getRow() + 1);
			}
			result.append("[" + coordinate.printCoordinate(showShips) + "]");
			if(coordinate.getColumn() == this.columnLabels.length - 1) {
				result.append("\n");
			}
		}
		return result.toString();
	}
	
	public boolean shoot(int userRow, String userColumn) {
		boolean result = false;
		int userCol = CommonUtils.getAlphabeticalIndexFromString(userColumn);
		Coordinate target = getCoordinateByRowAndColumn(userRow, userCol);
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
	}
	
	public boolean areAllShipsDestroyed() {
		boolean result = true;
		for(Coordinate coordinate : coordinates) {
			if(coordinate.getShipComponent() != null && !coordinate.getShipComponent().getShip().isDestroyed()) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	public static Board createBoard(int numberOfRows, int numberOfColumns) {
		Board board = null;
		try {
			board = new Board(numberOfRows, numberOfColumns);
		} catch(BoardOutOfRangeException e) {
			System.out.println(e.printError());
		}
		for(ShipType shipType : ShipType.values()) {
			boolean not_placed = true;
			Ship ship = new Ship(shipType);
			while(not_placed) {
				try {
					int row = CommonUtils.getRandomIntegerBetweenRange(0, numberOfRows - 1);
					int column = CommonUtils.getRandomIntegerBetweenRange(0, numberOfColumns - 1);
					boolean vertical = (CommonUtils.getRandomIntegerBetweenRange(0, 1) == 0) ? false : true;
					board.placeShip(ship, row, column, vertical);
					not_placed = false;
				}
				catch(PlaceShipException e) {
					
				}
			}
		}
		return board;
	}
}
