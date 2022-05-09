package it.unicatt.poo.games.battleships.graphics;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

import it.unicatt.poo.games.battleships.beans.Coordinate;
import it.unicatt.poo.games.battleships.constconfig.BattleshipsSettings;

public abstract class BoardGUICell extends JButton {
	
	private static final long serialVersionUID = 1622411001420791926L;
	private static final Color INITIALIZED = new Color(140, 140, 140);
	private static final Color INITIALIZED_SHIP = new Color(100, 100, 240);
	private static final Color MISS = new Color(169, 241, 255);
	private static final Color HIT = new Color(240, 100, 100);
	
	private Coordinate coordinate;
	
	public BoardGUICell(Coordinate coordinate) {
		this.coordinate = coordinate;
		this.initialConfiguration();
	}
	
	private void initialConfiguration() {
		Dimension initialSize = new Dimension(BattleshipsSettings.BUTTON_DIMENSIONX, BattleshipsSettings.BUTTON_DIMENSIONY);
		this.setMinimumSize(initialSize);
		this.setPreferredSize(initialSize);
	}
	
	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public void printCoordinate(boolean showShips) {
		if(showShips) {
			if(this.getCoordinate().getShipComponent() == null && this.getCoordinate().isMiss()) {
				this.setBackground(BoardGUICell.MISS);
				this.setText("");
				
			}
			else if(this.getCoordinate().getShipComponent() == null){
				this.setBackground(BoardGUICell.INITIALIZED);
				this.setText("");
			}
			else if(this.getCoordinate().getShipComponent().isHit()){
				this.setBackground(BoardGUICell.HIT);
				this.setText(Character.toString(this.getCoordinate().getShipComponent().getShip().getType().getName().charAt(0)));
			}
			else {
				this.setBackground(BoardGUICell.INITIALIZED_SHIP);
				this.setText(Character.toString(this.getCoordinate().getShipComponent().getShip().getType().getName().charAt(0)));
			}
		}
		else {
			if(this.getCoordinate().isMiss()) {
				this.setBackground(BoardGUICell.MISS);
			}
			else if(this.getCoordinate().isHit() && this.getCoordinate().getShipComponent().getShip().isDestroyed()) {
				this.setBackground(BoardGUICell.HIT);
				this.setText(Character.toString(this.getCoordinate().getShipComponent().getShip().getType().getName().charAt(0)));
			}
			else if(this.getCoordinate().isHit()) {
				this.setBackground(BoardGUICell.HIT);
			}
			else {
				this.setBackground(BoardGUICell.INITIALIZED);
			}
		}
	}
	
}
