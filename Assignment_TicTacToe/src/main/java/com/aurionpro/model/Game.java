package com.aurionpro.model;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player[] players;
    private int currentTurn;

    public Game(Player p1, Player p2) {
        this.board = new Board();
        this.players = new Player[]{p1, p2};
        this.currentTurn = 0;
    }

    private void switchTurn() {
    	// neat trick to switch players in array of 2.
    	// just subtract current from 1.
    	// 1-0(c)=1--> 1-1(c)=0
        currentTurn = 1 - currentTurn;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.printBoard();
            Player currentPlayer = players[currentTurn];
            System.out.print(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + "). Enter position (1-9): ");

            int move;
            try {
            	
            	
                move = scanner.nextInt()- 1;
                
             // to handle string input from user!
            } catch (Exception e) {
            	 scanner.nextLine();
                System.out.println("Invalid input. Try again.");
                continue;
            }

            if (move < 0 || move >= 9) {
                System.out.println("Invalid position. Try again.");
                continue;
            }

            if (!board.updateCell(move, currentPlayer.getSymbol())) {
                System.out.println("Cell already taken. Try again.");
                continue;
            }

            CellValue winner = board.checkWinner();
            if (winner != CellValue.EMPTY) {
                board.printBoard();
                //since current player made latest move so obv they are winner
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            if (board.isFull()) {
                board.printBoard();
                System.out.println("It's a draw!");
                break;
            }

            switchTurn();
        }

        scanner.close();
    }
}

