package com.aurionpro.model;

import java.util.Scanner;

public class TicTacToe {

	 public static void main(String[] args) {
		 
		
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter name for Player 1 (X): ");
	        String name1 = scanner.nextLine();
	        System.out.print("Enter name for Player 2 (O): ");
	        String name2 = scanner.nextLine();

	        Player p1 = new Player(name1, CellValue.X);
	        Player p2 = new Player(name2, CellValue.O);

	        Game game = new Game(p1, p2);
	        game.play();
	        scanner.close();
	        
	    }

}
