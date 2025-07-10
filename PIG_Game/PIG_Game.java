package com.aurionpro.model;

import java.util.Random;
import java.util.Scanner;

public class PIG_Game {
	
	private int turn;
	private int points;

	
	public PIG_Game() {
		turn=1;
		points=0;
	}


	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void incrementTurn() {
		int current=getTurn();
		setTurn(current+1);
		
	}

	}
