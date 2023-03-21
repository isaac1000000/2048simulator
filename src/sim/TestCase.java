package sim;
import sim.game.*;

public abstract class TestCase {
	String name;

	final int ITERATIONS = 10000;
	int testsRun;
	int maxValSums;
	int moveTotal;
	int maxVal = 4;
	int maxMoves = 0;
	sim.game.Mover maxMover;
	
	double meanMaxValue;
	double meanMoves;
	
	public TestCase() {
		name = "Generic Test Case";
	}
	
	// Runs all the tests, based on the number of iterations that have been selected
	public void runTests() {
		System.out.println(name);
		for (int i=0; i<ITERATIONS; i++) {
			runOneTest();
		}
		
		meanMaxValue = ((double) maxValSums) / testsRun;
		meanMoves = ((double) moveTotal) / testsRun;
		System.out.println();
	}
	
	// Used to print results of the tests
	public String resultsString() {
		return String.format("Max Value Reached: %d\nMean Moves: %.3f\nMean Max Value: %.3f\nMax Moves: %d", 
				new Object[] {maxVal, meanMoves, meanMaxValue, maxMoves});
	}

	// Returns a snapshot of the maximum board
	public String maxBoardString(){
		return maxMover.getBoard().toString();
	}

	public void printMaxBoardHistory(){
		maxMover.archive.printArchive();
	}
	
	// You guessed it! Runs a single test and marks results
	public void runOneTest() {
		sim.game.Mover mover = new sim.game.Mover();
		int moveDecision;
		while (gameNotOver(mover)) {
			moveDecision = makeDecision(mover);
			mover.move(moveDecision);
			do_after_move(mover);
		}
		testsRun++;
		int maxVal = mover.getMaxVal();
		maxValSums += maxVal;
		moveTotal += mover.getMoves();
		if (maxVal > this.maxVal) {
			this.maxVal = maxVal;
			this.maxMover = mover;
		}
		if (mover.getMoves() > this.maxMoves) {
			this.maxMoves = mover.getMoves();
		}
		do_after_test(mover);
		System.out.print("\rTests Run: " + testsRun);
	}
	
	// Will be overridden to include decision-making at each move for each test
	public abstract int makeDecision(sim.game.Mover mover);
	
	// Sometimes you need to check what's going on after each move
	public void do_after_move(sim.game.Mover mover) {}
	
	// Sometimes you need to check what's going on after each test
	public void do_after_test(sim.game.Mover mover) {}
	
	// A default method for checking if the game is over, but usually overridden
	public boolean gameNotOver(sim.game.Mover mover) {
		if (mover.isOver()) {
			return false;
		} else {
			return true;
		}
	}	
}
