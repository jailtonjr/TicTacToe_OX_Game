/**
 * 
 */
package com.epam.academy;

/**
 * @author Jailton_Louzada
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		int userPosition = 0;
		Game myGame = new Game();
		
		
		do {
			
			
			if (myGame.validPositionBoardBasedRules(userPosition)) {
				//print board
			}else {
				System.out.println("Position is busy");
			}
				
			
		} while (myGame.isPlaying());
		
	}

}
