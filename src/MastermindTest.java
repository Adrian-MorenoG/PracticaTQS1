import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MastermindTest {
	private Mastermind mastermind;
	private MockSecretCode mockSecretCode;
	private MockPlayer mockPlayer;
	private GameBoard gameBoard;

	
	@Before
	public void setUp() throws Exception {
		//We initialize each time to work with the same instance when testing		
		mastermind = new Mastermind();
		mockSecretCode = new MockSecretCode();
		mockPlayer = new MockPlayer();
		gameBoard = new GameBoard();
	}
	
	@Test
	public void addCodeGameBoardTest() {
		Code code1 = new Code("ABCD");
		Code code2 = new Code("DCBA");
		Code code3 = new Code("AAAA");

		//We add the code to the gameBoard.
		mastermind.addCodeToGameBoard(code1, gameBoard);
		
		//We get the list of codes.
		ArrayList<Code> codeRecords = gameBoard.getCodeRecords();
		
		//We check that its added.
		assertEquals(code1, codeRecords.get(0));
		
		codeRecords = new ArrayList<Code>();
		
		codeRecords.add(code1);
		codeRecords.add(code2);
		codeRecords.add(code3);

		//We add the codes to the gameBoard.
		mastermind.addCodeToGameBoard(code2, gameBoard);
		mastermind.addCodeToGameBoard(code3, gameBoard);

		//We check that the list of codes is the expected.
		assertEquals(codeRecords, gameBoard.getCodeRecords());
	}
	
	@Test
	public void addClueGameBoardTest() {
		Clue clue1 = new Clue("xo--");
		Clue clue2 = new Clue("xxxx");
		Clue clue3 = new Clue("xoox");

		//We add the clue to the gameBoard.
		mastermind.addClueToGameBoard(clue1, gameBoard);
		
		//We get the list of clues.
		ArrayList<Clue> clueRecords = gameBoard.getClueRecords();
		
		//We check that its added.
		assertEquals(clue1, clueRecords.get(0));

		clueRecords = new ArrayList<Clue>();

		clueRecords.add(clue1);
		clueRecords.add(clue2);
		clueRecords.add(clue3);

		//We add the clues to the gameBoard.
		mastermind.addClueToGameBoard(clue2, gameBoard);
		mastermind.addClueToGameBoard(clue3, gameBoard);

		//We check that the list of clues is the expected.
		assertEquals(clueRecords, gameBoard.getClueRecords());		
	}
	
	@Test
	public void increaseAttemptsTest() {
		//Quantity of attempts that we expect.
		int expectedAttempts = 1;
		
		//We call the function that increase attempts.
		mastermind.increaseAttempts();
		
		//We get the attempts after increase
		int realAttempts = mastermind.getAttempts();
		
		//We check that expectedAttemps are equals realAttempts
		assertEquals(expectedAttempts, realAttempts);
	}

	@Test
	public void hasAttemptsTest() {
		//White box testing - Decision coverage

		// We expect that it has attempts
		boolean expectedHasAttempts = true;
		
		//We get if it has attempts
		boolean hasAttemps = mastermind.hasAttempts();
		
		//We check if it has attempts, we expect a true because we don't set any attempt.
		assertEquals(expectedHasAttempts, hasAttemps);

		// We expect that it has not attempts
		expectedHasAttempts = false;
		
		//We set attempts to MAX
		mastermind.setAttempts(Mastermind.MAX_ATTEMPTS);
		
		//We get if it has attempts
		hasAttemps = mastermind.hasAttempts();
		
		//We check if it has attempts, we expect a false because we set all attempts.
		assertEquals(expectedHasAttempts, hasAttemps);
	}
	
	//MOCKOBJECT - MOCKCODISECRET
	@Test
	public void enterCodeTest() {
		//DECISION COVERAGE + MOCK OBJECT

		//If
		//Insert the mock so we can test the functions
		String secret = "BRBR";
		mockSecretCode.setSecretCode(secret);
		mastermind.setSecretCode(mockSecretCode);
		//Code we'll test
		String code = "BRBR";
		mastermind.enterCode(code);
		//Check the result which should be true in this case because it's the same code than the secret
		assertTrue(mastermind.hasFinished());

		//Else
		//Insert the mock so we can test the functions
		secret = "VVVV";
		mockSecretCode.setSecretCode(secret);
		mastermind.setSecretCode(mockSecretCode);
		//Code we'll test
		code = "RRRR";
		mastermind.enterCode(code);
		//Check the result which should be false in this case because it is not the same code than the secret
		assertFalse(mastermind.hasFinished());
	}
	
	//MOCKOBJECT - MOCKPLAYER AND MOCKSECRETCODE - TWO MOCKOBJECTS
	@Test
	public void elJugadorJugaTest() {
		
		//The player uses all his attempts and loses the game.
		
		//We use the SecretCode MockObject to set our secretCode.
		mockSecretCode.setSecretCode("BRWY");
		mastermind.setSecretCode(mockSecretCode);
		
		//We fill the record of plays of out player (MockPlayer).
		mockPlayer.addPlayerPlay("OOOO");
		mockPlayer.addPlayerPlay("SJDU");
		mockPlayer.addPlayerPlay("VRWB");
		mockPlayer.addPlayerPlay("BBRR");
		mockPlayer.addPlayerPlay("123");
		mockPlayer.addPlayerPlay("OOOO");
		mockPlayer.addPlayerPlay("RRBB");
		mockPlayer.addPlayerPlay("BRWO");
		mockPlayer.addPlayerPlay("12345");
		mockPlayer.addPlayerPlay("RWYB");
		mockPlayer.addPlayerPlay("YYYY");
		mockPlayer.addPlayerPlay("BBBB");
		mockPlayer.addPlayerPlay("0201");
		mockPlayer.addPlayerPlay("WWBB");
		mockPlayer.addPlayerPlay("BBWW");
		mockPlayer.addPlayerPlay("1");
		mockPlayer.addPlayerPlay("BBWW");
		
		mastermind.setPlayer(mockPlayer);
		mastermind.playerPlaysGame();
		
		//We check that he loose.
		assertFalse(mastermind.hasWinned());
		
		//Reset objects...
		mastermind = new Mastermind();
		mockPlayer = new MockPlayer();
		
		//The player uses all his attempts and wins the game.
		
		//We use the SecretCode MockObject to set our secretCode.
		mockSecretCode.setSecretCode("BRWY");
		mastermind.setSecretCode(mockSecretCode);
		
		//We fill the record of plays of out player (MockPlayer).
		mockPlayer.addPlayerPlay("OOOO");
		mockPlayer.addPlayerPlay("SJDU");
		mockPlayer.addPlayerPlay("VRWB");
		mockPlayer.addPlayerPlay("BBRR");
		mockPlayer.addPlayerPlay("123");
		mockPlayer.addPlayerPlay("OOOO");
		mockPlayer.addPlayerPlay("RRBB");
		mockPlayer.addPlayerPlay("BRWO");
		mockPlayer.addPlayerPlay("12345");
		mockPlayer.addPlayerPlay("RWYB");
		mockPlayer.addPlayerPlay("YYYY");
		mockPlayer.addPlayerPlay("BBBB");
		mockPlayer.addPlayerPlay("0201");
		mockPlayer.addPlayerPlay("WWBB");
		mockPlayer.addPlayerPlay("BBWW");
		mockPlayer.addPlayerPlay("1");
		mockPlayer.addPlayerPlay("BRWY");
		
		mastermind.setPlayer(mockPlayer);
		mastermind.playerPlaysGame();
		
		//We check that he wins.
		assertTrue(mastermind.hasWinned());
		
		//Reset objects...
		mastermind = new Mastermind();
		mockPlayer = new MockPlayer();
		
		//The player uses some attempts and wins the game.
		
		//We use the SecretCode MockObject to set our secretCode.
		mockSecretCode.setSecretCode("BRWY");
		mastermind.setSecretCode(mockSecretCode);
		
		//We fill the record of plays of out player (MockPlayer).
		mockPlayer.addPlayerPlay("OOOO");
		mockPlayer.addPlayerPlay("SJDU");
		mockPlayer.addPlayerPlay("VRWB");
		mockPlayer.addPlayerPlay("BBRR");
		mockPlayer.addPlayerPlay("123");
		mockPlayer.addPlayerPlay("OOOO");
		mockPlayer.addPlayerPlay("RRBB");
		mockPlayer.addPlayerPlay("BRWO");
		mockPlayer.addPlayerPlay("BRWY");
				
		mastermind.setPlayer(mockPlayer);
		mastermind.playerPlaysGame();
		
		//We check that he wins.
		assertTrue(mastermind.hasWinned());
		
	}
}
