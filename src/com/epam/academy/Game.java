package com.epam.academy;

public class Game {
	private Board myBoard;
	private Player currentPlayer;
	static GameStatus statusGame;

	public Game() {
		//Composition of Board and Player
		this.myBoard = new Board();
		this.currentPlayer = new Player();
		myBoard.printBoard();
		setStatusGame(GameStatus.PLAYING);

		
	}

	public GameStatus getStatusGame() {
		return statusGame;
	}

	public void setStatusGame(GameStatus statusGame) {
		Game.statusGame = statusGame;
	}

	/*	Validates board positions based on the rules of the game:
		- Slot is free? check it
		- Is winner?
		ELSE
		- Tied game?
		- Slot is Busy or wrong
		- wrong slot
	 *
	 */
	public boolean validPositionBoardBasedRules(int position) {
		for (int line = 0; line < myBoard.getBoardSize(); line++) {
			for (int cols = 0; cols < myBoard.getBoardSize(); cols++) {
				if (myBoard.getBoard()[line][cols] == Integer.toString(position).charAt(0)) { // Slot free
					// player is playing..
					myBoard.getBoard()[line][cols] = currentPlayer.getPlayer();
					// check if the player is winner
					if (isWinner()) {
						//end of the game
						setStatusGame(GameStatus.END_GAME);
						return true;
					}else {
						// invert the player
						turnRound();
					}
					// Slot free player played
					return true;
				}
			}
		}
		// Check if the game is tied
		if (checkTiedGame()) {
			setStatusGame(GameStatus.TIED_GAME);
		}
		// Slot is busy or 
		return false;
	}

	// Turn the round for the next Player
	private void turnRound() {
		if (currentPlayer.getPlayer() == 'X') {
			currentPlayer.setPlayer('O');
		} else currentPlayer.setPlayer('X');
	}

	//check if there is any winner matching on lines or cols
	private boolean isWinner() {
		return (checkDiagonals() || checkLinesAndCols());
	}

	//check if there is winner on diagonals
	private boolean checkDiagonals() {
		int countPrimaryDiagonalMatches = 0;
		int countSecondaryDiagonalMatches = 0;
		//check diagonal primary
		for (int i = 0; i < myBoard.getBoardSize(); i++) {
			for (int j = 0; j < myBoard.getBoardSize(); j++) {
				// Slot on the board is used by the current player
				if (myBoard.getBoard()[i][j] == currentPlayer.getPlayer()) {
					// is a primary diagonal
					if (i==j) {
						countPrimaryDiagonalMatches++;
					}
					//check secondary diagonal
					if ((i+j) == (myBoard.getBoardSize()-1)) {
						countSecondaryDiagonalMatches++;
					}
				}
			}
		}
		// the winner won by primary diagonal
		if (countPrimaryDiagonalMatches == myBoard.getBoardSize()) {
			return true;
		} 
		// the winner won by secondary diagonal
		if (countSecondaryDiagonalMatches == myBoard.getBoardSize()) {
			return true;
		}
		return false;
	}

	//check if there is a winner on the columns or lines
	private boolean checkLinesAndCols() {
		int countColsMatches = 0;
		int countLinesMatches = 0;
		//check diagonal primary
		for (int i = 0; i < myBoard.getBoardSize(); i++) {
			for (int j = 0; j < myBoard.getBoardSize(); j++) {
				// Slot on the board is used by the current player
				if (myBoard.getBoard()[i][j] == currentPlayer.getPlayer()) {
					// Checking lines
					countLinesMatches++;
				}
				if (myBoard.getBoard()[j][i] == currentPlayer.getPlayer()) {
					// Checking cols
					countColsMatches++;
				}
			}
			// check after each line if there is a match sequence
			if (countLinesMatches == myBoard.getBoardSize()) {
				return true;
			}else {countLinesMatches = 0; }

			// check after each column if there is a match sequence
			if (countColsMatches == myBoard.getBoardSize()) {
				return true;
			}else {countColsMatches = 0; }
		}

		return false;
	}

	// Check if the game is tied (all slots are busy)
	public boolean checkTiedGame() {
		int count = 0;
		for (int line = 0; line < myBoard.getBoardSize(); line++) {
			for (int cols = 0; cols < myBoard.getBoardSize(); cols++) {
				//check if all slots are busy
				if (myBoard.getBoard()[line][cols] == 'X' || myBoard.getBoard()[line][cols] == 'O') {
					count++;
				}
			}
		}
		// If all slots are busy (X or O) the game is tied.
		return (count == myBoard.getBoardSize() * myBoard.getBoardSize());
	}

	enum GameStatus {
		PLAYING,
		TIED_GAME,
		END_GAME
	}
}
