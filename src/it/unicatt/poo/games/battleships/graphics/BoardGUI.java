package it.unicatt.poo.games.battleships.graphics;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unicatt.poo.games.battleships.constconfig.BattleshipsSettings;
import it.unicatt.poo.games.battleships.core.Board;
import it.unicatt.poo.games.battleships.core.GenericPlayer;
import it.unicatt.poo.games.battleships.core.Enemy;
import it.unicatt.poo.games.battleships.core.Player;

public abstract class BoardGUI extends JPanel {
	
	private static final long serialVersionUID = 5697710868190756963L;
	
	private Board board;
	private BoardGUICell[] boardCells;
	
	public BoardGUI() { }
	
	public BoardGUI(GenericPlayer player, String identifier) {
		board = player.getBoard();
		this.boardCells = new BoardGUICell[BattleshipsSettings.TABLE_ROWS * BattleshipsSettings.TABLE_COLUMNS];
		this.initialCofiguration(player, identifier);
	}
	
	private void initialCofiguration(GenericPlayer player, String identifier) {
		this.setLayout(new GridLayout(board.getHeight() + 1, board.getWidth() + 1));
		this.add(new JLabel());
		
		for(int i = 0; i < BattleshipsSettings.TABLE_ROWS; i++) {
			JLabel label = new JLabel(board.getColumnLabels()[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			this.add(label);
		}
		for(int i = 0, counter = 0; i < BattleshipsSettings.TABLE_COLUMNS; i++) {
			JLabel label = new JLabel(board.getRowLabels()[i]);
			label.setHorizontalAlignment(JLabel.CENTER);
			this.add(label);
			for(int j = 0; j < board.getWidth(); j++) {
				this.boardCells[counter] = BoardGUI.selectType(identifier, board, i, j);
				this.add(boardCells[counter]);
				counter++;
			}
		}
	}
	
	private static BoardGUICell selectType (String identifier, Board board, int row, int column) {
		BoardGUICell result = null;
		if(identifier.equals(Player.PLAYER_ID)) {
			result =  new PlayerBoardGUICell(board.getCoordinateByRowAndColumn(row, column));
		}
		else if(identifier.equals(Enemy.ENEMY_ID)) {
			result =  new EnemyBoardGUICell(board.getCoordinateByRowAndColumn(row, column));
		}
		return result;
	}
	
	public void disableCells() {
		for(BoardGUICell cell: boardCells) {
			cell.setEnabled(false);
		}
	}
	
	public void activeCells() {
		for(BoardGUICell cell: boardCells) {
			cell.setEnabled(true);
		}
	}
	
	public void showBoard(boolean showShips) {
		for(BoardGUICell cell: boardCells) {
			cell.printCoordinate(showShips);
		}
	}
	
	public Board getBoard() {
		return board;
	}
	
	public BoardGUICell[] getBoardCells() {
		return boardCells;
	}
}