package com.aurionpro.test;

import java.util.Random;
import java.util.Scanner;

import com.aurionpro.model.PIG_Game;

public class PIG_GameTest {

	public static void playGame(PIG_Game game) {

		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Turn number: " + game.getTurn());
		System.out.println("Current Score: " + game.getPoints());

		while (game.getTurn() < 5) {

			System.out.println("Enter 1 for rolling, anything else for turning");
			int userInput = scanner.nextInt();

			handleInput(userInput, random, game);

		}

		System.out.println("Game Lost");
		return;

	}

	public static void handleInput(int userInput, Random random, PIG_Game game) {
		if (userInput == 1) {
			handleDiceRoll(random, game);
			return;
		}
		game.incrementTurn();
		System.out.println("Saved");
		System.out.println("Current Turns: " + game.getTurn());
		System.out.println("Current Points: " + game.getPoints());

	}

	public static void handleDiceRoll(Random random, PIG_Game game) {
		int diceFace = random.nextInt(5) + 1;
		System.out.println("Dice Face: " + diceFace);
		if (diceFace == 1) {
			game.incrementTurn();

			System.out.println("Lost all points");
			System.out.println("Turns: " + game.getTurn());
			game.setPoints(0);
			return;
		}
		int updatedPoints = game.getPoints() + diceFace;
		game.setPoints(updatedPoints);
		System.out.println("Current Points: " + game.getPoints());

		if (game.getPoints() >= 20) {
			System.out.println("Game Won");
			System.out.println("Turns: " + game.getTurn());

			System.exit(0);

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PIG_Game game = new PIG_Game();

		playGame(game);

	}

}
