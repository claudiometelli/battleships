package it.unicatt.poo.games.battleships.core;

import it.unicatt.poo.games.battleships.exceptions.BoardOutOfRangeException;
import it.unicatt.poo.games.battleships.utils.CommonUtils;

public class Player extends GenericPlayer {
	
	public static final String PLAYER_ID = "Player";
	private String playerName;
	
	public Player(Board board) {
		super(board);
		this.playerName = "Captain";
	}
	
	public Player(String name, Board board) {
		super(board);
		this.playerName = name;
	}
	
	public Player(String name, int rows, int columns) throws BoardOutOfRangeException {
		super(new Board(rows, columns));
		this.playerName = name;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public boolean shootEnemy(Enemy enemy, int row, String column) {
		return this.shoot(enemy.getBoard(), row - 1, CommonUtils.getAlphabeticalIndexFromString(column));
	}
	
}
