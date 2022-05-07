package it.unicatt.poo.games.battleships.core;

import java.util.ArrayList;
import java.util.List;

import it.unicatt.poo.games.battleships.beans.Coordinate;
import it.unicatt.poo.games.battleships.beans.ShipComponent;
import it.unicatt.poo.games.battleships.utils.CommonUtils;

public class EnemyIntelligence {
	
	private Board enemyBoard;
	
	public EnemyIntelligence(Board board) {
		this.enemyBoard = board;
	}
	
	
	public Coordinate nextMove() {
		Coordinate result = null;
		List<Coordinate> hitNotDestroyed = new ArrayList<Coordinate>();
		//Scanning board
		for(Coordinate coordinate : enemyBoard.getCoordinates()) {
			if(coordinate.getShipComponent() != null) {
				ShipComponent shipComponent= coordinate.getShipComponent();
				if(shipComponent.isHit() && !shipComponent.getShip().isDestroyed()) {
					hitNotDestroyed.add(coordinate);
				}
			}
			else {
				
			}
		}
		// Se vuoto a caso, altrimenti a caso tra i possibili
		if(hitNotDestroyed.isEmpty()) {
			Coordinate coordinate = enemyBoard.getCoordinateByRowAndColumn(CommonUtils.getRandomIntegerBetweenRange(0, enemyBoard.getHeight() - 1), CommonUtils.getRandomIntegerBetweenRange(0, enemyBoard.getWidth() - 1));
			while(coordinate.isHit() || coordinate.isMiss()) {
				int row = CommonUtils.getRandomIntegerBetweenRange(0, enemyBoard.getHeight() - 1);
				int column = CommonUtils.getRandomIntegerBetweenRange(0, enemyBoard.getWidth() - 1);
				coordinate = enemyBoard.getCoordinateByRowAndColumn(row, column);
			}
			result = coordinate;
		}
		else {
			List<Coordinate> possibleCoordinates = this.findPossibleCoordinates(hitNotDestroyed);
			result = possibleCoordinates.get(CommonUtils.getRandomIntegerBetweenRange(0, possibleCoordinates.size() - 1));
		}
		return result;
	}
	
	private List<Coordinate> findPossibleCoordinates(List<Coordinate> hitNotDestroyed){
		List<Coordinate> possibleCoordinates = new ArrayList<Coordinate>();
		for(Coordinate coordinate : hitNotDestroyed) {
			if(coordinate.getRow() != 0) {
				Coordinate up = enemyBoard.getCoordinateByRowAndColumn(coordinate.getRow() - 1, coordinate.getColumn());
				if(!up.isHit() && !up.isMiss() && !possibleCoordinates.contains(up)) {
					possibleCoordinates.add(up);
				}
			}
			if(coordinate.getRow() != enemyBoard.getHeight() - 1) {
				Coordinate down = enemyBoard.getCoordinateByRowAndColumn(coordinate.getRow() + 1, coordinate.getColumn());
				if(!down.isHit() && !down.isMiss() && !possibleCoordinates.contains(down)) {
					possibleCoordinates.add(down);
				}
			}
			if(coordinate.getColumn() != 0) {
				Coordinate left = enemyBoard.getCoordinateByRowAndColumn(coordinate.getRow(), coordinate.getColumn() - 1);
				if(!left.isHit() && !left.isMiss() && !possibleCoordinates.contains(left)) {
					possibleCoordinates.add(left);
				}
			}
			if(coordinate.getColumn() != enemyBoard.getWidth() - 1) {
				Coordinate right = enemyBoard.getCoordinateByRowAndColumn(coordinate.getRow(), coordinate.getColumn() + 1);
				if(!right.isHit() && !right.isMiss() && !possibleCoordinates.contains(right)) {
					possibleCoordinates.add(right);
				}
			}
		}
		return possibleCoordinates;
	}
}
