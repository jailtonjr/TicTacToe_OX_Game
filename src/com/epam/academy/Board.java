/**
 * 
 */
package com.epam.academy;

/**
 * @author Jailton_Louzada
 *  Board operations
 */
public class Board {
	private char [][] board;
	private final int BOARD_SIZE = 3;

	public Board() {
		createBoard();
		initBoard();
	}

	public int getBoardSize() {
		return this.BOARD_SIZE;
	}

	public char[][] getBoard() {
		return this.board;
	}

	//Just creates a board with defined size (must be a square matrix)
	public void createBoard( ){
		this.board = new char [BOARD_SIZE][BOARD_SIZE];
	}

	// Print the board
	public void printBoard() {
		System.out.println("-------------");

		for (int i = 0; i < BOARD_SIZE; i++) {
			System.out.print("| ");
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(this.board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	//Defines each position of the board with an number
	private void initBoard() {
		int count = 1;
		for (int line = 0; line < BOARD_SIZE; line++) {
			for (int cols = 0; cols < BOARD_SIZE; cols++) {
				this.board[line][cols] = Integer.toString(count).charAt(0);
				count++;
			}
		}
	}

}
