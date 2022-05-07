package it.unicatt.poo.games.battleships.beans;

public class ShipComponent {
	
	private Ship ship;
	private boolean hit;

	
	public ShipComponent(Ship ship) {
		this.ship = ship;
		this.hit = false;
	}
	
	public Ship getShip() {
		return ship;
	}
	
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	
	public boolean isHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	

}
