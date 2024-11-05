//package ui;
//import core.*;
package edu.ser216.checkers.ui;
import edu.ser216.checkers.core.CheckersGame;
/**
 * 
 * @author Ryan Gaudier
 * @version 1.0
 * Console UI for checkersGame class
 * Prints out the board and moves made
 */
public class CheckersTextConsole implements CheckersViewer {
	/**
	 * @param game, passes in a game that wants to be printed
	 * @return returns a string representation of the board in the CheckerGame class
	 */
	public String printBoard(final CheckersGame game) {
		StringBuilder result = new StringBuilder();
		for(int i = 7; i >= 0; i--) {
			result.append(i + 1);
			result.append("|");
			for(int j = 0; j < 8; j++) {
				result.append(game.getSquare(i, j));
				result.append("|");
			}
			result.append("\n");
		}
		
		for(int i = 0; i < 8; i++) {
			switch(i) {
			case 0:
				result.append("  a ");
				break;
			case 1:
				result.append("b ");
				break;
			case 2:
				result.append("c ");
				break;
			case 3:
				result.append("d ");
				break;
			case 4:
				result.append("e ");
				break;
			case 5:
				result.append("f ");
				break;
			case 6:
				result.append("g ");
				break;
			case 7:
				result.append("h");
				break;
			}
		}
		String finalResult = result.toString();
		return finalResult;
	}
}
