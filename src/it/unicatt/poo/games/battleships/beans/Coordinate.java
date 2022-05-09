package it.unicatt.poo.games.battleships.beans;

import java.io.Serializable;

public class Coordinate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1814930451623406992L;
	
	private static final String INITIALIZED = "-";
	private static final String MISS = "O";
	private static final String HIT = "X";
	
	private int row;
	private int column;
	private boolean hit;
	private boolean miss;
	private ShipComponent shipComponent;
	
	public Coordinate(int row, int column) {
		this.row = row;
		this.column = column;
		this.hit = false;
		this.miss = false;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public boolean isMiss() {
		return miss;
	}
	
	public void setMiss(boolean miss) {
		this.miss = miss;
	}
	
	public ShipComponent getShipComponent() {
		return shipComponent;
	}
	
	public void setShipComponent(ShipComponent shipComponent) {
		this.shipComponent = shipComponent;
	}
	
	public String printCoordinate(boolean showShips) {
		String result = null;
		if(showShips) {
			if(this.shipComponent == null && this.isMiss()) {
				result = Coordinate.MISS;
			}
			else if(this.shipComponent == null){
				result = Coordinate.INITIALIZED;
			}
			else{
				result = Character.toString(shipComponent.getShip().getType().getName().charAt(0));
			}
		}
		else {
			if(this.isMiss()) {
				result = Coordinate.MISS;
			}
			else if(this.isHit() && shipComponent.getShip().isDestroyed()) {
				result = Character.toString(shipComponent.getShip().getType().getName().charAt(0));
			}
			else if(this.isHit()) {
				result = Coordinate.HIT;
			}
			else {
				result = Coordinate.INITIALIZED;
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Row: " + row + "Column: " + column;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj != null && obj instanceof Coordinate) {
			Coordinate coordinate = (Coordinate) obj;
			if(this.row == coordinate.getRow() && this.column == coordinate.getColumn()) {
				result = true;
			}
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return (row + 1) * 100 + column + 1;
	}
	
}
