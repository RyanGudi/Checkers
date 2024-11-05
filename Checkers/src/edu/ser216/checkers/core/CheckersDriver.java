package edu.ser216.checkers.core;

import edu.ser216.checkers.ui.CheckersGUI;
import edu.ser216.checkers.ui.CheckersTextConsole;
import edu.ser216.checkers.ui.CheckersViewer;
import javafx.application.Application;

import java.util.Scanner;

/**
 * This class provides the entry to run the checkers game. It also provides the main game play loop
 * that calls methods from the two user-created classes (CheckersGameLogic and CheckersTextConsole).
 *
 * The contents of this file should not be changed.
 *
 * @author Acuna, Baron
 * @version 1.0
 */
public class CheckersDriver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CheckersGame game = new CheckersGameLogic(scan);
        CheckersViewer console = new CheckersTextConsole();
        CheckersComputerPlayer computer = new CheckersComputerPlayer();
        
        System.out.println("Would you like to use a GUI?(y/n)");
        String answer = scan.next();
        if(answer.equals("n")) {
            while (game.getWinningPlayer() == '_') {
                System.out.println(console.printBoard(game));
                game.doTurn();
                game.nextTurn();
            }
            game.onEnd();
        }else {
        	Application.launch(CheckersGUI.class,new String[] {});
        }

        
        //basic game play loop.
        /*
        while (game.getWinningPlayer() == '_') {
            System.out.println(console.printBoard(game));
            game.doTurn();
            game.nextTurn();
        }
        game.onEnd();
        
                	while(game.getWinningPlayer() == '_') {
            	System.out.println(console.printBoard(game));
            	game.doTurn();
            	game.nextTurn();
            	System.out.println(console.printBoard(game));
            	computer.doTurn(game);
            }
        */
    }
    
}
