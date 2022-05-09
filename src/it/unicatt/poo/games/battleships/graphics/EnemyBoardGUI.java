package it.unicatt.poo.games.battleships.graphics;

import it.unicatt.poo.games.battleships.beans.Coordinate;
import it.unicatt.poo.games.battleships.core.Enemy;

public class EnemyBoardGUI extends BoardGUI {
	
	private static final long serialVersionUID = 8735840008961987463L;
	
	private Enemy enemy;
	
	public EnemyBoardGUI(Enemy enemy) {
		super(enemy, Enemy.ENEMY_ID);
		this.enemy = enemy;
	}
	
	public Enemy getEnemy() {
		return enemy;
	}
	
	public void makeYourMove() {
		Coordinate target = MainGUI.getInstance().getEnemyBoardGUI().getEnemy().shootPlayer(MainGUI.getInstance().getPlayerBoardGUI().getPlayer());
		for(BoardGUICell cell : MainGUI.getInstance().getPlayerBoardGUI().getBoardCells()) {
			if(cell.getCoordinate() == target) {
				cell.printCoordinate(true);
			}
		}
	}
}
