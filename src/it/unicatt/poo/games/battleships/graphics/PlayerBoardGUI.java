package it.unicatt.poo.games.battleships.graphics;

import it.unicatt.poo.games.battleships.core.Player;

public class PlayerBoardGUI extends BoardGUI {
	
	private static final long serialVersionUID = 7479269195255454870L;
	
	private Player player;
	
	public PlayerBoardGUI(Player player) {
		super(player, Player.PLAYER_ID);
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
}
