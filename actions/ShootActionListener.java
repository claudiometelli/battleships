package it.unicatt.poo.games.battleships.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import it.unicatt.poo.games.battleships.constconfig.BattleshipsSettings;
import it.unicatt.poo.games.battleships.graphics.BoardGUICell;
import it.unicatt.poo.games.battleships.graphics.MainGUI;
import it.unicatt.poo.games.battleships.managers.MessageManager;

public class ShootActionListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		BoardGUICell targetCell = (BoardGUICell) e.getSource();
		int row = targetCell.getCoordinate().getRow();
		int column = targetCell.getCoordinate().getColumn();
		boolean shootResult = MainGUI.getInstance().getPlayerBoardGUI().getPlayer().shoot(MainGUI.getInstance().getEnemyBoardGUI().getEnemy().getBoard(), row, column);
		targetCell.printCoordinate(false);
		if(shootResult && targetCell.getCoordinate().getShipComponent().getShip().isDestroyed()) {
			for(BoardGUICell cell : MainGUI.getInstance().getEnemyBoardGUI().getBoardCells()) {
				if(cell.getCoordinate().getShipComponent() != null &&
						cell.getCoordinate().getShipComponent().getShip().getType() == targetCell.getCoordinate().getShipComponent().getShip().getType()) {
					cell.printCoordinate(shootResult);
				}
			}
		}
		targetCell.setEnabled(false);
		try {
			Thread.sleep(BattleshipsSettings.SHIFT_TIME);
			MainGUI.getInstance().getEnemyBoardGUI().makeYourMove();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if(MainGUI.getInstance().getPlayerBoardGUI().getBoard().areAllShipsDestroyed() && MainGUI.getInstance().getEnemyBoardGUI().getBoard().areAllShipsDestroyed()) {
			int res = MessageManager.drawMessage();
			playOrQuit(res);
		} else if(MainGUI.getInstance().getPlayerBoardGUI().getBoard().areAllShipsDestroyed()) {
			int res = MessageManager.gameOverMessage();
			playOrQuit(res);
		} else if(MainGUI.getInstance().getEnemyBoardGUI().getBoard().areAllShipsDestroyed()) {
			int res = MessageManager.winMessage();
			playOrQuit(res);
		}
	}
	
	private static void playOrQuit(int res) {
		if(res == JOptionPane.YES_OPTION) {
			MainGUI.playAgain();
		} else {
			MessageManager.quitGame();
		}
	}
}
