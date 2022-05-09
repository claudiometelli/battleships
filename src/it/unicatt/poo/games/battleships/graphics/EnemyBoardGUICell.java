package it.unicatt.poo.games.battleships.graphics;

import it.unicatt.poo.games.battleships.actions.ShootActionListener;
import it.unicatt.poo.games.battleships.beans.Coordinate;

public class EnemyBoardGUICell extends BoardGUICell {
	
	private static final long serialVersionUID = 8554630704126285961L;

	public EnemyBoardGUICell(Coordinate coordinate) {
		super(coordinate);
		super.addActionListener(new ShootActionListener());
	}
}
