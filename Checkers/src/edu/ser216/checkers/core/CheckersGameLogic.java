//package core;
package edu.ser216.checkers.core;
import java.util.Scanner;
/**
 * 
 * @author Ryan Gaudier
 * @version 1.0
 * Creates a Checker Board and implements CheckersGame's methods to create
 * a checkers game that can moves,jumps,and 2 players
 * Doesn't implement double jump or kinging
 *
 */
public class CheckersGameLogic implements CheckersGame{
	/**
	 * Creates a 2D array of char with the size of a checkers board to represent the game
	 */
	public char board[][] = new char[8][8];
	/**
	 * char winner and playerTurn represent the who wins and whos turn it is
	 */
	public char winner,playerTurn;
	/**
	 * scanner to read moves from the user
	 */
	public Scanner keyboard;
	
	/**
	 * Constructor places pieces at standard checkers starting
	 * and starts the game on player x's turn
	 */
	public boolean hop;
	public int XR1,XC1;
	
	public CheckersGameLogic() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 8; j++) {
				if((i + j) % 2 == 0){
					board[i][j] = 'x';
				}else {
					board[i][j] = '_';
				}
			}
		}
		for(int i = 3; i < 5; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = '_';
			}
		}
		for(int i = 5; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if((i + j) % 2 == 0){
					board[i][j] = 'o';
				}else {
					board[i][j] = '_';
				}
			}
		}
		winner = '_';
		playerTurn = 'x';
	}
	/**
	 * 
	 * @param input passes a scanner into the object created to take user input
	 * Constructor places pieces at standard checkers starting
	 * and starts the game on player x's turn
	 */
	public CheckersGameLogic(Scanner input) {
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 8; j++) {
				if((i + j) % 2 == 0){
					board[i][j] = 'x';
				}else {
					board[i][j] = '_';
				}
			}
		}
		for(int i = 3; i < 5; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = '_';
			}
		}
		for(int i = 5; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if((i + j) % 2 == 0){
					board[i][j] = 'o';
				}else {
					board[i][j] = '_';
				}
			}
		}
		keyboard = input;
		winner = '_';
		playerTurn = 'x';
	}
	/**
	 * Sets winner of game
	 * @param winner winner
	 */
	public void setWinner(char winner) {
		if(winner == 'x' || winner == 'o') {
			this.winner = winner;
		}else {
			System.out.println("Not a valid winner");
		}
	}
	/**
	 * @param row,column input points towards a square on the board
	 * @return returns the char at the location
	 */
	public char getSquare(int row, int column) {
		if(row < 0 && row > 7 && column < 0 && column > 7 ) {
			throw new IllegalArgumentException("Not a valid Square");
		}
		return board[row][column];
	}
	/**
	 * @param row,column,content passes into method to set char at the square on the board
	 */
	public void setSquare(int row,int column, char content) {
		if(row < 0 && row > 7 && column < 0 && column > 7 && (content != 'o' || content != 'x')) {
			throw new IllegalArgumentException("Not a valid Square");
		}
		board[row][column] = content;
	}
	/**
	 * @return returns the winning player
	 */
	public char getWinningPlayer() {
		return winner;
	}
	/**
	 * Sets winning player
	 * @param winner winner
	 */
	public void setWinningPlayer(char winner) {
		if(winner == 'o'|| winner == 'x') {
			this.winner = winner;
		}
			return;
	}
	/**
	 * Sets the turn to the opposite person
	 */
	public void nextTurn() {
		if(playerTurn == 'x') {
			System.out.println("Player o's Turn:");
			playerTurn = 'o';
		}else if(playerTurn == 'o') {
			System.out.println("Player x's Turn:");
			playerTurn = 'x';
		}
	}
	/**
	 * Takes in user input and checks if the move is valid
	 * If valid carries out the move and updates the board
	 * If the move isn't valid it repeats until a valid move is scanned
	 * If no valid moves, sets winner
	 */
	public void doTurn() {
		boolean validMove = false;
		String[] xMoves = getPlayerXPossibleMoves();
		String[] oMoves = getPlayerOPossibleMoves();
		int R1,C1,R2,C2;
		String move = "";
		
		if(xMoves[0] == null) {
			winner = 'o';
			return;
		}else if(oMoves[0] == null) {
			winner = 'x';
			return;
		}
		while(validMove == false) {
		System.out.println("Player " + playerTurn +" enter a valid input Ex) " + '"' + "3a-4b" + '"');
		move = keyboard.next();
		if(playerTurn == 'x') {
			for(int i = 0; i < xMoves.length;i++) {
				if(xMoves[0] == null) {
					winner = 'o';
					break;
				}else if(move.equals(xMoves[i])){
					validMove = true;
					break;
				}
			}
		}else if(playerTurn == 'o'){
			for(int i = 0; i < oMoves.length;i++) {
				if(oMoves[0] == null) {
					winner = 'x';
					break;
				}else if(move.equals(oMoves[i])){
					validMove = true;
					break;
				}
			}
		}else {
			System.out.println("Player Error");
		}
		}//End of While
		R1 = (Integer.parseInt(String.valueOf(move.charAt(0))) - 1);
		R2 = (Integer.parseInt(String.valueOf(move.charAt(3))) - 1);
		switch((move.charAt(1))) {
		case 'a':C1 = 0;
		break;
		case 'b':C1 = 1;
		break;
		case 'c':C1 = 2;
		break;
		case 'd':C1 = 3;
		break;
		case 'e':C1 = 4;
		break;
		case 'f':C1 = 5;
		break;
		case 'g':C1 = 6;
		break;	
		case 'h':C1 = 7;
		break;
		default: C1 = -1;
		break;
		}
		switch((move.charAt(4))) {
		case 'a':C2 = 0;
		break;
		case 'b':C2 = 1;
		break;
		case 'c':C2 = 2;
		break;
		case 'd':C2 = 3;
		break;
		case 'e':C2 = 4;
		break;
		case 'f':C2 = 5;
		break;
		case 'g':C2 = 6;
		break;	
		case 'h':C2 = 7;
		break;
		default:C2 = -1;
		break;
		}
		if(playerTurn == 'x') {
			if((R2 == (R1 + 1) && C2 == (C1 + 1)) || (R2 == (R1 + 1) && C2 == (C1 - 1))) {
				System.out.println("Move Made");
				setSquare(R2,C2,'x');
				setSquare(R1,C1,'_');
			}else if(R2 == (R1 + 2) && C2 == (C1 + 2)) {
				System.out.println("Hop Made");
				setSquare(R2,C2,'x');
				setSquare(R1,C1,'_');
				setSquare(R1 + 1,C1 + 1, '_');
			}else if(R2 == (R1 + 2) && C2 == (C1 - 2)) {
				System.out.println("Hop Made");
				setSquare(R2,C2,'x');
				setSquare(R1,C1,'_');
				setSquare(R1 + 1,C1 - 1, '_');
			}
		}else if(playerTurn == 'o') {
			if((R2 == (R1 - 1) && C2 == (C1 + 1)) || (R2 == (R1 - 1) && C2 == (C1 - 1))) {
				System.out.println("Move Made");
				setSquare(R2,C2,'o');
				setSquare(R1,C1,'_');
			}else if(R2 == (R1 - 2) && C2 == (C1 + 2)) {
				System.out.println("Hop Made");
				setSquare(R2,C2,'o');
				setSquare(R1,C1,'_');
				setSquare(R1 - 1, C1 + 1,'_');
			}else if(R2 == (R1 - 2) && C2 == (C1 - 2)) {
				setSquare(R2,C2,'o');
				setSquare(R1,C1,'_');
				setSquare(R1 - 1, C1 - 1, '_');
			}
		}else {
			System.out.println("Player Error");
		}
	}
	/**
	 * 
	 * @param playerMoves playerMoves
	 * @return if move is valid
	 * GUI doTurn
	 */
	public boolean doTurn(int[] playerMoves) {
		String[] xMoves = getPlayerXPossibleMoves();
		String[] oMoves = getPlayerOPossibleMoves();
		int R1,C1,R2,C2;
	
		if(xMoves[0] == null) {
			winner = 'o';
			return false;
		}else if(oMoves[0] == null) {
			winner = 'x';
			return false;
		}
		C1 = playerMoves[0];
		R1 = playerMoves[1];
		C2 = playerMoves[2];
		R2 = playerMoves[3];
		
		if(playerTurn == 'x') {
			if((R2 == (R1 + 1) && C2 == (C1 + 1)) && getSquare(R1,C1) == 'x'  && getSquare(R2,C2) == '_'|| (R2 == (R1 + 1) && C2 == (C1 - 1)) && getSquare(R1,C1) == 'x' && getSquare(R2,C2) == '_') {
				System.out.println("Move Made");
				setSquare(R2,C2,'x');
				setSquare(R1,C1,'_');
				hop = false;
				return true;
			}else if(R2 == (R1 + 2) && C2 == (C1 + 2) && getSquare(R2,C2) == '_' && getSquare(R1 + 1, C1 + 1) == 'o') {
				System.out.println("Hop Made");
				hop = true;
				setSquare(R2,C2,'x');
				setSquare(R1,C1,'_');
				setSquare(R1 + 1,C1 + 1, '_');
				XR1 = R1 + 1;
				XC1 = C1 + 1;
				return true;
			}else if(R2 == (R1 + 2) && C2 == (C1 - 2) && getSquare(R2,C2) == '_' && getSquare(R1 + 1, C1 - 1) == 'o') {
				System.out.println("Hop Made");
				hop = true;
				setSquare(R2,C2,'x');
				setSquare(R1,C1,'_');
				setSquare(R1 + 1,C1 - 1, '_');
				XR1 = R1 + 1;
				XC1 = C1 - 1;
				hop = true;
				return true;
			}
		}else if(playerTurn == 'o') {
			if((R2 == (R1 - 1) && C2 == (C1 + 1)) && getSquare(R1,C1) == 'o' && getSquare(R2,C2) == '_' || (R2 == (R1 - 1) && C2 == (C1 - 1)) && getSquare(R1,C1) == 'o' && getSquare(R2,C2) == '_') {
				System.out.println("Move Made");
				setSquare(R2,C2,'o');
				setSquare(R1,C1,'_');
				hop = false;
				return true;
			}else if(R2 == (R1 - 2) && C2 == (C1 + 2) && getSquare(R2,C2) == '_' && getSquare(R1 - 1, C1 + 1) == 'x') {
				System.out.println("Hop Made");
				hop = true;
				setSquare(R2,C2,'o');
				setSquare(R1,C1,'_');
				setSquare(R1 - 1, C1 + 1,'_');
				XR1 = R1 - 1;
				XC1 = C1 + 1;
				return true;
			}else if(R2 == (R1 - 2) && C2 == (C1 - 2) && getSquare(R2,C2) == '_' && getSquare(R1 - 1, C1 - 1) == 'x') {
				System.out.println("Hop Made");
				hop = true;
				setSquare(R2,C2,'o');
				setSquare(R1,C1,'_');
				setSquare(R1 - 1, C1 - 1, '_');
				XR1 = R1 - 1;
				XC1 = C1 - 1;
				return true;
			}
		}else {
			System.out.println("Player Error");
		}
		return false;
		
	}
	/**
	 * Loops through the board and finds all valid moves for each piece at x
	 * Stores each valid move into an array
	 * @return returns an array of strings filled with valid moves
	 */
	public String[] getPlayerXPossibleMoves() {
		String[] validMoves = new String[36];
		int count = 0;
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 8; j++) {
				if(j + 1 < 8) {
					if(getSquare(i,j) == 'x' && getSquare(i + 1, j + 1) == '_') {
						switch(j) {
						case 0:validMoves[count] = (i + 1) + "a-" + (i + 2) + "b";
							break;
						case 1:validMoves[count] = (i + 1) + "b-" + (i + 2) + "c";
							break;
						case 2:validMoves[count] = (i + 1) + "c-" + (i + 2) + "d";
							break;
						case 3:validMoves[count] = (i + 1) + "d-" + (i + 2) + "e";
							break;
						case 4:validMoves[count] = (i + 1) + "e-" + (i + 2) + "f";
							break;
						case 5:validMoves[count] = (i + 1) + "f-" + (i + 2) + "g";
							break;
						case 6:validMoves[count] = (i + 1) + "g-" + (i + 2) + "h";
							break;
						default:
						}
						count++;
					}		
				}
				if(j - 1 >= 0) {
					if(getSquare(i,j) == 'x' && getSquare(i + 1, j - 1) == '_') {
						switch(j) {
						case 1:validMoves[count] = (i + 1) + "b-" + (i + 2) + "a";
							break;
						case 2:validMoves[count] = (i + 1) + "c-" + (i + 2) + "b";
							break;
						case 3:validMoves[count] = (i + 1) + "d-" + (i + 2) + "c";
							break;
						case 4:validMoves[count] = (i + 1) + "e-" + (i + 2) + "d";
							break;
						case 5:validMoves[count] = (i + 1) + "f-" + (i + 2) + "e";
							break;
						case 6:validMoves[count] = (i + 1) + "g-" + (i + 2) + "f";
							break;
						case 7:validMoves[count] = (i + 1) + "h-" + (i + 2) + "g";
							break;
						default:
						}
						count++;
					}
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				if(j + 2 < 8) {
					if(getSquare(i,j) == 'x' && getSquare(i + 1, j + 1) == 'o' && getSquare(i + 2, j + 2) == '_') {
						switch(j) {
						case 0:validMoves[count] = (i + 1) + "a-" + (i + 3) + "c";
							break;
						case 1:validMoves[count] = (i + 1) + "b-" + (i + 3) + "d";
							break;
						case 2:validMoves[count] = (i + 1) + "c-" + (i + 3) + "e";
							break;
						case 3:validMoves[count] = (i + 1) + "d-" + (i + 3) + "f";
							break;
						case 4:validMoves[count] = (i + 1) + "e-" + (i + 3) + "g";
							break;
						case 5:validMoves[count] = (i + 1) + "f-" + (i + 3) + "h";
							break;
						default:
						}
						count++;
					}
				}
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				if(j - 2 >= 0) {
					if(getSquare(i,j) == 'x' && getSquare(i + 1, j - 1) == 'o' && getSquare(i + 2, j - 2) == '_') {
						switch(j) {
						case 2:validMoves[count] = (i + 1) + "c-" + (i + 3) + "a";
							break;
						case 3:validMoves[count] = (i + 1) + "d-" + (i + 3) + "b";
							break;
						case 4:validMoves[count] = (i + 1) + "e-" + (i + 3) + "c";
							break;
						case 5:validMoves[count] = (i + 1) + "f-" + (i + 3) + "d";
							break;
						case 6:validMoves[count] = (i + 1) + "g-" + (i + 3) + "e";
							break;
						case 7:validMoves[count] = (i + 1) + "h-" + (i + 3) + "f";
							break;
						default:
						}
						count++;
					}
				}
			}
		}
		return validMoves;
	}
	/**
	 * Loops through the board and finds all valid moves for each piece at o
	 * Stores each valid move into an array
	 * @return returns an array of strings filled with valid moves
	 */
	public String[] getPlayerOPossibleMoves() {
		String[] validMoves = new String[36];
		int count = 0;
		for(int i = 7; i > 0; i--) {
			for(int j = 0; j < 8; j++) {
				if(j + 1 < 8) {
					if(getSquare(i,j) == 'o' && getSquare(i - 1, j + 1) == '_') {
						switch(j) {
						case 0:validMoves[count] = (i + 1) + "a-" + (i) + "b";
							break;
						case 1:validMoves[count] = (i + 1) + "b-" + (i) + "c";
							break;
						case 2:validMoves[count] = (i + 1) + "c-" + (i) + "d";
							break;
						case 3:validMoves[count] = (i + 1) + "d-" + (i) + "e";
							break;
						case 4:validMoves[count] = (i + 1) + "e-" + (i) + "f";
							break;
						case 5:validMoves[count] = (i + 1) + "f-" + (i) + "g";
							break;
						case 6:validMoves[count] = (i + 1) + "g-" + (i) + "h";
							break;
						default:
						}
						count++;
					}
				}
				if(j - 1 >= 0) {
					if(getSquare(i,j) == 'o' && getSquare(i - 1, j - 1) == '_') {
						switch(j) {
						case 1:validMoves[count] = (i + 1) + "b-" + (i) + "a";
							break;
						case 2:validMoves[count] = (i + 1) + "c-" + (i) + "b";
							break;
						case 3:validMoves[count] = (i + 1) + "d-" + (i) + "c";
							break;
						case 4:validMoves[count] = (i + 1) + "e-" + (i) + "d";
							break;
						case 5:validMoves[count] = (i + 1) + "f-" + (i) + "e";
							break;
						case 6:validMoves[count] = (i + 1) + "g-" + (i) + "f";
							break;
						case 7:validMoves[count] = (i + 1) + "h-" + (i) + "g";
							break;
						default:
						}
						count++;
					}
				}
				
			}
		}
		for(int i = 7; i > 1; i--) {
			for(int j = 0; j < 6; j++) {
				if(j + 2 < 8) {
					if(getSquare(i,j) == 'o' && getSquare(i - 1, j + 1) == 'x' && getSquare(i - 2, j + 2) == '_') {
						switch(j) {
						case 0:validMoves[count] = (i + 1) + "a-" + (i - 1) + "c";
							break;
						case 1:validMoves[count] = (i + 1) + "b-" + (i - 1) + "d";
							break;
						case 2:validMoves[count] = (i + 1) + "c-" + (i - 1) + "e";
							break;
						case 3:validMoves[count] = (i + 1) + "d-" + (i - 1) + "f";
							break;
						case 4:validMoves[count] = (i + 1) + "e-" + (i - 1) + "g";
							break;
						case 5:validMoves[count] = (i + 1) + "f-" + (i - 1) + "h";
							break;
						default:
						}
						count++;
					}
				}
			}
		}
		for(int i = 7; i > 1; i--) {
			for(int j = 2; j < 8; j++) {
				if(j - 2 >= 0) {
					if(getSquare(i,j) == 'o' && getSquare(i - 1, j - 1) == 'x' && getSquare(i - 2, j - 2) == '_') {
						switch(j) {
						case 2:validMoves[count] = (i + 1) + "c-" + (i - 1) + "a";
							break;
						case 3:validMoves[count] = (i + 1) + "d-" + (i - 1) + "b";
							break;
						case 4:validMoves[count] = (i + 1) + "e-" + (i - 1) + "c";
							break;
						case 5:validMoves[count] = (i + 1) + "f-" + (i - 1) + "d";
							break;
						case 6:validMoves[count] = (i + 1) + "g-" + (i - 1) + "e";
							break;
						case 7:validMoves[count] = (i + 1) + "h-" + (i - 1) + "f";
							break;
						default:
						}
						count++;
					}
				}
			}
		}
		return validMoves;
	}
	/**
	 * 
	 * @return hop
	 */
	public boolean getHop() {
		return hop;
	}
	/**
	 * 
	 * @return XR1
	 * R1 to be deleted
	 */
	public int getXR1() {
		return XR1;
	}
	/**
	 * 
	 * @return XC1
	 * C1 to be deleted
	 */
	public int getXC1() {
		return XC1;
	}
	/**
	 * Prints out the winner
	 */
	public void onEnd() {
		System.out.println("Winner is : " + winner);
	}
	/**
	 * Gets Player Turn
	 */
	public char getTurn() {
		return playerTurn;
	}
}
