package edu.ser216.checkers.test;
import edu.ser216.checkers.core.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class CheckersGameLogicTest {
	private CheckersGame game1;
	private CheckersGame game2;
	@Before
	public void setUp() throws Exception{
		game1 = new CheckersGameLogic();
	}
	
	/**
	 * Test for Valid Scanner Inputs
	 */
	@Test
	public void testDoTurn() {
		Scanner scan;
		String userInput = String.format("3a-4b", System.lineSeparator(),System.lineSeparator());
		ByteArrayInputStream input = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(input);
		scan = new Scanner(System.in);
		game2 = new CheckersGameLogic(scan);
		game2.doTurn();
	}
	/**
	 * Test for Invalid Move
	 */
	@Test (expected = NoSuchElementException.class)
	public void testDoTurn2() {
		Scanner scan;
		String userInput = String.format("3b-4c", System.lineSeparator(),System.lineSeparator());
		ByteArrayInputStream input = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(input);
		scan = new Scanner(System.in);
		game2 = new CheckersGameLogic(scan);
		Exception exception = assertThrows(NoSuchElementException.class, () -> {game2.doTurn();});
	}
	/**
	 * Test for Invalid Input
	 */
	@Test (expected = NoSuchElementException.class)
	public void testDoTurn3() {
		Scanner scan;
		String userInput = String.format("320598", System.lineSeparator(),System.lineSeparator());
		ByteArrayInputStream input = new ByteArrayInputStream(userInput.getBytes());
		System.setIn(input);
		scan = new Scanner(System.in);
		game2 = new CheckersGameLogic(scan);
		Exception exception = assertThrows(NoSuchElementException.class, () -> {game2.doTurn();});
	}
	/**
	 * Test for Valid Inputs
	 */
	@Test
	public void testSetWinner() {	
		game1.setWinningPlayer('o');
		assertTrue("False",game1.getWinningPlayer() == 'o');
		game1.setWinningPlayer('x');
		assertTrue("False",game1.getWinningPlayer() == 'x');
	}
	
	/**
	 * Test for Number Input
	 */
	@Test
	public void testSetWinner2() {
		game1.setWinningPlayer('1');
		assertTrue("Integer not valid",game1.getWinningPlayer() == '1');
	}
	/**
	 * Test for Wrong Letter
	 */
	@Test
	public void testSetWinner3() {
		game1.setWinningPlayer('M');
		assertTrue("Not Valid Player",game1.getWinningPlayer() == 'M');
	}
	/**
	 * Test For Valid Input
	 */
	@Test
	public void testSetSquare() {
		game1.setSquare(0, 0, 'o');
		assertTrue("False",game1.getSquare(0, 0) == 'o');
		game1.setSquare(7, 7, 'x');
		assertTrue("False",game1.getSquare(7, 7) == 'x');
	}
	/**
	 * Test for Invalid Bounds
	 */
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testSetSquare2() {
		Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {game1.setSquare(-1, -1, 'o');});
		exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {game1.setSquare(8, 8, 'o');});
	}
	/**
	 * Test for invalid player
	 */
	@Test
	public void testSetSquare3() {
		game1.setSquare(0, 0, 'T');
		assertTrue("Not Valid Player", game1.getSquare(0, 0) == 'T');
	}
	/**
	 * Valid Squares
	 */
	@Test
	public void testGetSquare() {
		game1.setSquare(0, 0, 'o');
		assertTrue("False",game1.getSquare(0, 0) == 'o');
		game1.setSquare(7, 7,'x');
		assertTrue("False", game1.getSquare(7, 7) == 'x');
	}
	/**
	 * Test for Invalid Bounds
	 */
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void testGetSquare2() {
		Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class,() ->{game1.getSquare(-1, -1);});
		exception = assertThrows(ArrayIndexOutOfBoundsException.class,() ->{game1.getSquare(8, 8);});
	}
	/**
	 * Test for Proper Turn Swap
	 */
	@Test
	public void testNextTurn() {
		assertTrue(game1.getTurn() == 'x');
		game1.nextTurn();
		assertTrue(game1.getTurn() == 'o');
	}
}
