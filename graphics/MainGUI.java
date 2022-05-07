package it.unicatt.poo.games.battleships.graphics;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import it.unicatt.poo.games.battleships.constconfig.BattleshipsSettings;
import it.unicatt.poo.games.battleships.core.Board;
import it.unicatt.poo.games.battleships.core.Enemy;
import it.unicatt.poo.games.battleships.core.Player;
import it.unicatt.poo.games.battleships.exceptions.BoardOutOfRangeException;

public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1919070772263507160L;
	private static MainGUI instance;
	private PlayerBoardGUI playerBoard;
	private EnemyBoardGUI enemyBoard;
	private DialogPanelGUI dialogPanel;
	
	private MainGUI () {
		this.initialConfiguration();
	}
	
	private void initialConfiguration() {
		Player player = null;
		try {
			player = new Player (new Board(BattleshipsSettings.TABLE_ROWS, BattleshipsSettings.TABLE_COLUMNS));
			//player = new Player (Board.createDefaultBoard());
		} catch(BoardOutOfRangeException e) {
			
		}
		Enemy enemy = new Enemy(player);
		this.playerBoard = new PlayerBoardGUI(player);
		this.enemyBoard = new EnemyBoardGUI(enemy);
		this.dialogPanel = new DialogPanelGUI();
		this.setTitle("Battleships non è mai stato così Java");
		this.setSize(1280, 720);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		//c.weightx = 0.1;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 150;
		this.add(dialogPanel, c);
		
		c.weightx = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 150;
		c.ipady = 150;
		this.add(playerBoard, c);

		c.weightx = 0.1;
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 150;
		c.ipady = 150;
		this.add(enemyBoard, c);
	}
	
	public static MainGUI getInstance() {
		if(MainGUI.instance == null) {
			MainGUI.instance = new MainGUI();
		}
		return MainGUI.instance;
	}
	
	public static void playAgain() {
		MainGUI.instance.dispose();
		MainGUI.instance = null;
		MainGUI.play();
	}
	
	public static void play() {
		MainGUI frame = MainGUI.getInstance();
		frame.setVisible(true);
		frame.getEnemyBoardGUI().disableCells();
		frame.getPlayerBoardGUI().showBoard(true);
		frame.getEnemyBoardGUI().showBoard(false);
	}
	
	public PlayerBoardGUI getPlayerBoardGUI() {
		return playerBoard;
	}
	
	public EnemyBoardGUI getEnemyBoardGUI() {
		return enemyBoard;
	}
	
	
	public static void main(String... args) {
		MainGUI.play();
	}
}
