package it.unicatt.poo.games.battleships.core;

import it.unicatt.poo.games.battleships.beans.Coordinate;

public class Enemy extends GenericPlayer {
	
	public static final String ENEMY_ID = "Enemy";
	private EnemyIntelligence intelligence;
	
	public Enemy(Player player) {
		super(Board.createBoard(player.getBoard().getHeight(), player.getBoard().getWidth()));
		this.intelligence = new EnemyIntelligence(player.getBoard());
	}

	public Coordinate shootPlayer (Player player) {
		Coordinate target = intelligence.nextMove();
		this.shoot(player.getBoard(), target.getRow(), target.getColumn());
		return target;
	}
}
