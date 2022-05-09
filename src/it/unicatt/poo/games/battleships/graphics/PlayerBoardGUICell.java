package it.unicatt.poo.games.battleships.graphics;

import it.unicatt.poo.games.battleships.actions.PlaceShipActionListener;
import it.unicatt.poo.games.battleships.beans.Coordinate;

public class PlayerBoardGUICell extends BoardGUICell{
	
	private static final long serialVersionUID = -4558595616560739241L;

	public PlayerBoardGUICell(Coordinate coordinate) {
		super(coordinate);
		super.addActionListener(new PlaceShipActionListener());
	}
}
