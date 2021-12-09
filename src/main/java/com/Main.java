package com;

import com.gameplay.Game;
import com.gameplay.IGame;

public class Main {
	public static void main(String args[]) {
		System.out.println("Welcome to Fifa Simulation");
		IGame game = new Game();
		game.startGame();
	}
}
