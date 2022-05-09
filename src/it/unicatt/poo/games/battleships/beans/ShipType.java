package it.unicatt.poo.games.battleships.beans;

import java.io.Serializable;

public enum ShipType implements Serializable {
	/*
	 * 
	 */
	
	CARRIER("Carrier", 5),
	BATTLESHIP("Battleship", 4),
	DESTROYER("Destroyer", 3),
	SUBMARINE("Submarine", 3),
	PATROL_BOAT("Patrol Boat", 2);
	
	private static final long serialVersionUID = -7246623127467554352L;
	
	private String name;
	private int size;
	
	private ShipType(String name, int size) {
		this.name = name;
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}

}
