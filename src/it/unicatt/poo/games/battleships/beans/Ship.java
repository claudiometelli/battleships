package it.unicatt.poo.games.battleships.beans;

import java.io.Serializable;

public class Ship implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1558440662471188510L;
	
	private ShipType type;
	private ShipComponent[] shipComponents;
	
	public Ship(ShipType type) {
		this.type = type;
		this.shipComponents = new ShipComponent[type.getSize()];
		for(int i = 0; i < type.getSize(); i++) {
			shipComponents[i] = new ShipComponent(this);
		}
	}
	
	public ShipType getType() {
		return type;
	}
	
	public ShipComponent[] getShipComponents() {
		return shipComponents;
	}
	
	public void setShipComponents(ShipComponent[] shipComponents) {
		this.shipComponents = shipComponents;
	}
	
	public int getTotalHits() {
		int count = 0;
		for(int i = 0; i < type.getSize(); i++) {
			if(this.shipComponents[i].isHit()) {
				count++;
			}
		}
		return count;
	}
	
	public boolean isDestroyed() {
		if(this.getTotalHits() == this.type.getSize())
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for(ShipComponent shipComponent: shipComponents) {
			result.append(shipComponent.toString() + ";");
		}
		result.append("\nTotal Hits: " + getTotalHits() + " / " + type.getSize());
		return result.toString();
	}
	
}
