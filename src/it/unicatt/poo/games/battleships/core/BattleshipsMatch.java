//package it.unicatt.poo.games.battleships.core;
//
//import java.util.Scanner;
//
//import it.unicatt.poo.games.battleships.beans.Ship;
//import it.unicatt.poo.games.battleships.beans.ShipType;
//import it.unicatt.poo.games.battleships.exceptions.BoardOutOfRangeException;
//import it.unicatt.poo.games.battleships.exceptions.PlaceShipException;
//
//public class BattleshipsMatch {
//	
//	private Player player;
//	private Enemy cpu;
//	
//	public BattleshipsMatch(Player player) throws BoardOutOfRangeException {
//		this.player = player;
//		this.cpu = new Enemy(player);
//	}
//	
//	public static void main(String[] args) {
//		
//		System.out.println("Hello! Welcome to battleships game!\nPlease enter your name: ");
//		Scanner scanner = new Scanner(System.in);
//		String name = scanner.next();
//		System.out.println("Hello " + name + ".\nPlease enter the height and width of the battlefield.\n" + 
//							"Height(MIN: " + Board.MIN_ROWS + ", MAX: " + Board.MAX_ROWS + "): ");
//		// Input: height, width
//		int height = 0;
//		boolean valid_input = false;
//		while(!valid_input) {
//			if(scanner.hasNextInt()) {
//				height = scanner.nextInt();
//				if(height < Board.MIN_ROWS || height > Board.MAX_ROWS) {
//					System.out.println("Please insert values between " + Board.MIN_ROWS + " and "  + Board.MAX_ROWS + ".");
//				}
//				else {
//					valid_input = true;
//				}
//			}
//			else {
//				System.out.println("Please insert a number.");
//				scanner.next();
//			}
//		}
//		System.out.println("Width(MIN: " + Board.MIN_COLUMNS + ", MAX: " + Board.MAX_COLUMNS + "): ");
//		int width = 0;
//		valid_input = false;
//		while(!valid_input) {
//			if(scanner.hasNextInt()) {
//				width = scanner.nextInt();
//				if(width < Board.MIN_ROWS || width > Board.MAX_ROWS) {
//					System.out.println("Please insert values between " + Board.MIN_ROWS + " and "  + Board.MAX_ROWS + ".");
//				}
//				else {
//					valid_input = true;
//				}
//			}
//			else {
//				System.out.println("Please insert a number.");
//				scanner.next();
//			}
//		}
//		// Player, enemy, starting match
//		Player player = null;
//		Enemy enemy = null;
//		BattleshipsMatch match = null;
//		try {
//			player = new Player(name, height, width);
//			enemy = new Enemy(player);
//			match = new BattleshipsMatch(player);
//		}catch(BoardOutOfRangeException e) {
//			System.out.println(e.printError());
//			System.exit(0);
//		}
//		System.out.println(player.getPlayerName() + ", are you ready? This is your battlefield! Place your ships with accuracy!\n" + player.getPlayerBoard().printBoard(true));
//		//placing ships
//		for(ShipType ship: ShipType.values()) {
//			boolean valid_ship = false;
//			while(!valid_ship) {
//				try {
//					System.out.println("Place your " + ship.getName() + ". It is size " + ship.getSize() + ".");
//					System.out.println("Insert the coordinate referred to the rows.");
//					int coordinate_row = 0;
//					valid_input = false;
//					while(!valid_input) {
//						if(scanner.hasNextInt()) {
//							coordinate_row = scanner.nextInt();
//							valid_input = true;
//						}
//						else {
//							System.out.println("Please insert a number.");
//							scanner.next();
//						}
//					}
//					System.out.println("Insert the coordinate referred to the columns.");
//					String coordinate_column = "";
//					valid_input = false;
//					while(!valid_input) {
//							coordinate_column = scanner.next();
//							for(String column_title : player.getPlayerBoard().getColumnLabels()) {
//								if(coordinate_column.toLowerCase().equals(column_title.toLowerCase())) {
//									valid_input = true;
//									break;
//								}
//							}
//							if(!valid_input) {
//								System.out.println("Please insert a valid coordinate for column.");
//							}
//					}
//					System.out.println("Is it vertical? Tap 'Yes' or 'No'.");
//					String vertical = "";
//					valid_input = false;
//					while(!valid_input) {
//						vertical = scanner.next();
//						if(!vertical.toLowerCase().equals("yes") && !vertical.toLowerCase().equals("no")) {
//							System.out.println("Please insert a 'Yes' or 'No'.");
//						}
//						else {
//							valid_input = true;
//						}
//					}
//					boolean vertical_bool = (vertical.toLowerCase().equals("yes")) ? true : false;
//					System.out.println(coordinate_row);
//					System.out.println(coordinate_column);
//					System.out.println(vertical_bool);
//					player.getPlayerBoard().placeShipByPlayer(new Ship(ship), coordinate_row, coordinate_column, vertical_bool);
//					System.out.println("Now your situation is:\n" + player.getPlayerBoard().printBoard(true));
//					valid_ship = true;
//				} catch(PlaceShipException e) {
//					System.out.println("Please insert a valid coordinate to place your " + ship.getName() + ".");
//				}
//			}
//		}
//		
//		System.out.println(player.getPlayerName() + ":\n" + player.getPlayerBoard().printBoard(true) + "CPU:\n" + enemy.getEnemyBoard().printBoard(true));
//		while(!player.getPlayerBoard().areAllShipsDestroyed() && !enemy.getEnemyBoard().areAllShipsDestroyed()) {
//			System.out.println("Captain " + player.getPlayerName() + ". Where are we going to shoot? Please insert a valid coordinate for the row.");
//			String coordinate_row_str = "";
//			valid_input = false;
//			while(!valid_input) {
//					coordinate_row_str = scanner.next();
//					for(String row_title : player.getPlayerBoard().getRowLabels()) {
//						if(coordinate_row_str.toLowerCase().equals(row_title.toLowerCase())) {
//							valid_input = true;
//							break;
//						}
//					}
//					if(!valid_input) {
//						System.out.println("Please insert a valid coordinate for column.");
//					}
//			}
//			int coordinate_row = Integer.valueOf(coordinate_row_str);
//			System.out.println("Captain " + player.getPlayerName() + ". Where are we going to shoot? Please insert a valid coordinate for the column.");
//			String coordinate_column = "";
//			valid_input = false;
//			while(!valid_input) {
//					coordinate_column = scanner.next();
//					for(String column_title : player.getPlayerBoard().getColumnLabels()) {
//						if(coordinate_column.toLowerCase().equals(column_title.toLowerCase())) {
//							valid_input = true;
//							break;
//						}
//					}
//					if(!valid_input) {
//						System.out.println("Please insert a valid coordinate for column.");
//					}
//			}
//			boolean hit = player.shootEnemy(enemy, coordinate_row, coordinate_column);
//			String shoot_result = (hit) ? "HIT!" : "MISS!";
//			System.out.println("SHOOOOOOOT!\n" + enemy.getEnemyBoard().printBoard(false) + shoot_result);
//			hit = enemy.shootPlayer(player);
//			shoot_result = (hit) ? "HIT!" : "MISS!";
//			System.out.println("CPU is going to shoot.\nSHOOOOOOOT!\n" + player.getPlayerBoard().printBoard(false) + shoot_result);
//		}
//		System.out.println("END GAME");
//		if(player.getPlayerBoard().areAllShipsDestroyed() && enemy.getEnemyBoard().areAllShipsDestroyed()) {
//			System.out.println("This is a draw! Incredible!");
//		}else if(player.getPlayerBoard().areAllShipsDestroyed()){
//			System.out.println("CPU WON THE GAME! : (");
//		}
//		else{
//			System.out.println("YOU WON! Thank you captain " + player.getPlayerName());
//		}
//		System.out.println("Thank you for playing battleships, captain " + player.getPlayerName() + "!");
//		System.exit(0);
//	}
//}
