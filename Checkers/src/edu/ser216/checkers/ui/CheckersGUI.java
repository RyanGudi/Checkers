package edu.ser216.checkers.ui;

import edu.ser216.checkers.core.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.ColumnConstraints;
/**
 * 
 * @author ryang
 * @version 1.0
 * Uses Logic from CheckersGameLogic and creates a GUI representation
 * of the checkers game
 */
public class CheckersGUI extends Application{
	/**
	 * Sets final Tile Size
	 */
	public static final int TILE_SIZE = 100;
	/**
	 * Sets Final Board Height
	 */
	public static final int HEIGHT = 8;
	/**
	 * Sets Final Board Width
	 */
	public static final int WIDTH = 8;
	/**
	 * Pane to hold board
	 */
	GridPane gridpane = new GridPane();
	/**
	 * Game Logic
	 */
	CheckersGameLogic game = new CheckersGameLogic();
	/**
	 * Computer Logic
	 */
    CheckersComputerPlayer computer = new CheckersComputerPlayer();
    /**
     * Mimic GUI board for Game Logic
     */
	Checker[][] board = new Checker[8][8];
	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * Initializes the board
	 */
	public void start(Stage stage) {

		//Create a Grid Pane to hold circles
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				ColumnConstraints col = new ColumnConstraints();
				col.setHalignment(HPos.CENTER);
				gridpane.getColumnConstraints().add(col);
				Tile tile = new Tile((x+y) % 2 == 0);
				gridpane.add(tile, x, y);
				Checker piece = new Checker();
				if(y <= 2 && (x+y) % 2 != 0) {
					piece.setColorBlack(false);
					gridpane.add(piece, x, y);
					board[x][(y-7)*-1] = piece;
				}
				if(y >= 5 && (x + y) % 2 !=0) {
					piece.setColorBlack(true);
					gridpane.add(piece, x, y);
					board[x][(y-7)*-1] = piece;
				}
			}
		}
		
		//Create a Scene and place it into the stage
		Scene scene = new Scene(gridpane,800,800);
		stage.setTitle("Checkers Game");
		stage.setScene(scene);
		stage.show();
	}
	/**
	 * Tries move checks with gamelogic if valid move and conducts move if it is
	 * @param piece
	 */
	public void tryMove(Checker piece) {
		boolean hop = false;
		int XR1 = 0, XC1 = 0;
		int[] playerMoves = new int[4];
		playerMoves[0] = piece.getOldX();
		playerMoves[1] = piece.getOldY();
		playerMoves[2] = piece.getNewX();
		playerMoves[3] = piece.getNewY();
		if(game.doTurn(playerMoves)) {
			hop = game.getHop();
			XR1 = game.getXR1();
			XC1 = game.getXC1();
			if(hop) {
				//System.out.println("XC1: " + XC1 + "XR1: " + XR1);
				piece.move(playerMoves[2], playerMoves[3]);
				board[gridpane.getColumnIndex(piece)][gridpane.getRowIndex(piece)] = null;
				gridpane.getChildren().remove(board[XC1][XR1]);
				gridpane.getChildren().remove(piece);
				game.nextTurn();
			}else {
			piece.move(playerMoves[2], playerMoves[3]);
			gridpane.getChildren().remove(piece);
			game.nextTurn();
			}
		}
	}
	/**
	 * 
	 * @param pixels pixels
	 * @return index
	 */
	public int toBoard(double pixels) {
		return (int)(pixels) / TILE_SIZE;
	}
	/**
	 * 
	 * @author ryang
	 *	Creates tile extends rectangle
	 */
	public class Tile extends Rectangle{
		public boolean hasPiece;
		public Tile(boolean black) {
			setWidth(TILE_SIZE);
			setHeight(TILE_SIZE);
			if(black) {
				setFill(Color.BLACK);
			}else {
				setFill(Color.WHITE);
			}
			
		}
	}
	/**
	 * 
	 * @author ryang
	 *	Creates Checker piece extends circle
	 */
	public class Checker extends Circle{
		public double oldX,oldY, newX, newY;
		boolean black;
		public Checker() {
			setRadius(40);
			setCenterX(50);
			setCenterY(50);
			setOnMousePressed(e -> {
				oldX = e.getSceneX();
				oldY = e.getSceneY();
			});
			setOnMouseDragged(e -> {
				newX = e.getSceneX();
				newY = e.getSceneY();
			});
			setOnMouseReleased(e -> {
				tryMove(this);
			});
		}
		/**
		 * 
		 * @return oldX
		 */
		public int getOldX() {
			return toBoard(oldX);
		}
		/**
		 * 
		 * @return OldY
		 */
		public int getOldY() {
			int result = toBoard(oldY);
			result = (result - 7) * -1;
			return result;
		}
		/**
		 * 
		 * @return NewX
		 */
		public int getNewX() {
			return toBoard(newX);
		}
		/**
		 * 
		 * @return NewY
		 */
		public int getNewY() {
			int result = toBoard(newY);
			result = (result - 7) * -1;
			return result;
		}
		/**
		 * 
		 * @param black black
		 * Sets Color
		 */
		public void setColorBlack(boolean black) {
				if(black) {
				setFill(Color.BLACK);
				this.black = true;
				}else {
				setFill(Color.RED);
				this.black = false;
				}
		}
		/**
		 * 
		 * @param x x 
		 * @param y y
		 * Move Piece by deleting and creating a new one
		 */
		public void move(int x, int y) {
			Checker newPiece = new Checker();
			if(black) {
			setFill(Color.BLACK);
			newPiece.setColorBlack(true);
			}else {
			setFill(Color.RED);
			newPiece.setColorBlack(false);
			}
			gridpane.add(newPiece, x, (y - 7) * -1);
			board[x][y] = newPiece;
		}
	}
}