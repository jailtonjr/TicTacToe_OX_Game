package com.epam.academy;

public class Player {
	private char player;


	public Player() {
		// set first player as X
		setPlayer('X');
	}


	public char getPlayer() {
		return this.player;
	}

	public void setPlayer(char player) {
		this.player = player;
	}

}
