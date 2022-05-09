package it.unicatt.poo.games.battleships.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unicatt.poo.games.battleships.beans.Coordinate;
import it.unicatt.poo.games.battleships.beans.Ship;
import it.unicatt.poo.games.battleships.beans.ShipType;
import it.unicatt.poo.games.battleships.graphics.BoardGUICell;
import it.unicatt.poo.games.battleships.graphics.MainGUI;
import it.unicatt.poo.games.battleships.graphics.PlayerBoardGUICell;
import it.unicatt.poo.games.battleships.managers.ErrorManager;

public class PlaceShipActionListener implements ActionListener {

	private static Map<Ship, Integer> placedShips = new HashMap<Ship, Integer>();
	
	static {
		for(ShipType shipType : ShipType.values()) {
			placedShips.put(new Ship(shipType), shipType.getSize());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		PlayerBoardGUICell target = (PlayerBoardGUICell) e.getSource();
		if(isShipComponentPlaceable(target)) {
			Ship ship = findShipPlacing();
			placedShips.put(ship, placedShips.get(ship) - 1);
			target.getCoordinate().setShipComponent(ship.getShipComponents()[placedShips.get(ship)]);
			target.printCoordinate(true);
			target.setEnabled(false);
		}
		else {
			ErrorManager.placeShipError();
			Ship ship = findShipPlacing();
			placedShips.put(ship, ship.getType().getSize());
			List<BoardGUICell> cells= findShipComponentPlacing(ship);
			for(BoardGUICell cell: cells) {
				cell.getCoordinate().setShipComponent(null);
				cell.setEnabled(true);
				cell.printCoordinate(true);
			}
		}
		if(areAllShipsPlaced()) {
			for(ShipType shipType : ShipType.values()) {
				placedShips.put(new Ship(shipType), shipType.getSize());
			}
			MainGUI.getInstance().getPlayerBoardGUI().disableCells();
			MainGUI.getInstance().getEnemyBoardGUI().activeCells();
		}
	}
	
	private static Ship findShipPlacing() {
		Ship ship = null;
		for(Ship s : placedShips.keySet()) {
			if(placedShips.get(s) != 0 && placedShips.get(s) != s.getType().getSize()) {
				ship = s;
				break;
			}
		}
		if(ship == null) {
			for(Ship s : placedShips.keySet()){
				if(placedShips.get(s) != 0) {
					ship = s;
					break;
				}
			}
		}
		return ship;
	}
	
	private static List<BoardGUICell> findShipComponentPlacing(Ship ship) {
		List<BoardGUICell> shipComponentPlacing = new ArrayList<BoardGUICell>();
		for(BoardGUICell cell : MainGUI.getInstance().getPlayerBoardGUI().getBoardCells()) {
			if(cell.getCoordinate().getShipComponent() != null && cell.getCoordinate().getShipComponent().getShip().getType().equals(ship.getType())) {
				shipComponentPlacing.add(cell);
			}
		}
		return shipComponentPlacing;
	}
	
	private static boolean isShipComponentPlaceable(PlayerBoardGUICell cell) {
		boolean result = true;
		Coordinate target = cell.getCoordinate();
		if(target.getShipComponent() != null) {
			result = false;
		} else {
			Ship ship = findShipPlacing();
			List<BoardGUICell> shipComponentPlacing = findShipComponentPlacing(ship);
			if(shipComponentPlacing.size() == 1) {
				Coordinate coordinate = shipComponentPlacing.get(0).getCoordinate();
				if(target.getRow() != coordinate.getRow() && target.getColumn() != coordinate.getColumn()) {
					result = false;
				}
				else if(target.getRow() == coordinate.getRow() && target.getColumn() != coordinate.getColumn()) {
					if(target.getColumn() + 1 != coordinate.getColumn() && target.getColumn() - 1 != coordinate.getColumn()) {
						result = false;
					}
				}
				else if(target.getRow() != coordinate.getRow() && target.getColumn() == coordinate.getColumn()) {
					if(target.getRow() + 1 != coordinate.getRow() && target.getRow() - 1 != coordinate.getRow()) {
						result = false;
					}
				}
				else {
					result = false;
				}
			}
			else if(shipComponentPlacing.size() > 1) {
				// si potrebbe fare con le stream
				BoardGUICell first = shipComponentPlacing.get(0);
				boolean isHorizontal = true;
				boolean isVertical = true;
				for(BoardGUICell c : shipComponentPlacing) {
					if(c.getCoordinate().getRow() != first.getCoordinate().getRow()) {
						isHorizontal = false;
						break;
					}
				}
				for(BoardGUICell c : shipComponentPlacing) {
					if(c.getCoordinate().getColumn() != first.getCoordinate().getColumn()) {
						isVertical = false;
						break;
					}
				}
				if(isHorizontal){
					if(target.getRow() != first.getCoordinate().getRow()) {
						result = false;
					} else {
						boolean accept = false;
						for(BoardGUICell c : shipComponentPlacing) {
							if(c.getCoordinate().getColumn() + 1 == target.getColumn() || c.getCoordinate().getColumn() - 1 == target.getColumn()) {
								accept = true;
								break;
							}
						}
						result = accept;
					}
				}else if(isVertical) {
					if(target.getColumn() != first.getCoordinate().getColumn()) {
						result = false;
					} else {
						boolean accept = false;
						for(BoardGUICell c : shipComponentPlacing) {
							if(c.getCoordinate().getRow() + 1 == target.getRow() || c.getCoordinate().getRow() - 1 == target.getRow()) {
								accept = true;
								break;
							}
						}
						result = accept;
					}
				}
				else {
					result = false;
				}
			}
		}
		return result;
	}
	
	private static boolean areAllShipsPlaced() {
		boolean result = true;
		for(Ship ship : placedShips.keySet()) {
			if(placedShips.get(ship) != 0) {
				result = false;
				break;
			}
		}
		return result;
	}

}
