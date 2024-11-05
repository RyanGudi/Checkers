package edu.ser216.checkers.core;
import java.util.Random;
/**
 * 
 * @author ryang
 *@version 1.0
 *Creates a Computer that can play against a player with randomly generated moves
 */
public class CheckersComputerPlayer extends CheckersGameLogic{
	/**
	 * Random variable to randomly generate a move
	 */
	Random rand = new Random();
	/**
	 * Conducts a computer turn based on a given board and makes a move
	 * @param board board
	 */
	public void doTurn(CheckersGame board) {
		String[] oMoves = getPlayerOPossibleMoves();
		int moves = 0;
		int R1,C1,R2,C2;
		for(int i = 0; i < oMoves.length; i++) {
			if(oMoves[i] != null) {
				moves++;
			}
		}
		if(moves == 0) {
			board.setWinningPlayer('x');
			return;
		}else if(board.getWinningPlayer() != '_') {
			return;
		}
		int random = rand.nextInt(moves);
		
		R1 = (Integer.parseInt(String.valueOf(oMoves[random].charAt(0))) - 1);
		R2 = (Integer.parseInt(String.valueOf(oMoves[random].charAt(3))) - 1);
		switch(oMoves[random].charAt(1)) {
		case 'a':C1 = 0;break;
		case 'b':C1 = 1;break;
		case 'c':C1 = 2;break;
		case 'd':C1 = 3;break;
		case 'e':C1 = 4;break;
		case 'f':C1 = 5;break;
		case 'g':C1 = 6;break;	
		case 'h':C1 = 7;break;
		default: C1 = -1;break;
		}
		switch(oMoves[random].charAt(4)) {
		case 'a':C2 = 0;break;
		case 'b':C2 = 1;break;
		case 'c':C2 = 2;break;
		case 'd':C2 = 3;break;
		case 'e':C2 = 4;break;
		case 'f':C2 = 5;break;
		case 'g':C2 = 6;break;	
		case 'h':C2 = 7;break;
		default:C2 = -1;break;
		}
		if((R2 == (R1 - 1) && C2 == (C1 + 1)) || (R2 == (R1 - 1) && C2 == (C1 - 1))) {
			System.out.println("Computer Move Made");
			board.setSquare(R2,C2,'o');
			board.setSquare(R1,C1,'_');
		}else if(R2 == (R1 - 2) && C2 == (C1 + 2)) {
			System.out.println("Computer Hop Made");
			board.setSquare(R2,C2,'o');
			board.setSquare(R1,C1,'_');
			board.setSquare(R1 - 1, C1 + 1,'_');
		}else if(R2 == (R1 - 2) && C2 == (C1 - 2)) {
			board.setSquare(R2,C2,'o');
			board.setSquare(R1,C1,'_');
			board.setSquare(R1 - 1, C1 - 1, '_');
		}
		board.nextTurn();
	}
}
