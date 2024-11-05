package edu.ser216.checkers.test;
import edu.ser216.checkers.core.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
public class CheckersComputerPlayerTest {
	private CheckersComputerPlayer computer;
	@Before
	public void setUp() throws Exception{
		computer = new CheckersComputerPlayer();
	}
	/**
	 * Test Has First Move
	 */
	@Test
	public void testDoTurn() {
		String[] test = computer.getPlayerOPossibleMoves();
		assertFalse(test[0] == null);
	}

}
