package sim;
import sim.game.*;

public abstract class TestCase {
	final int ITERATIONS = 10000;
	int testsRun;
	int maxValSums;
	int moveTotal;
	int maxVal = 4;
	int maxMoves = 0;
	sim.game.Mover maxMover;
	
	double meanMaxValue;
	double meanMoves;
	
	public TestCase() {}
	
	public void runTests() {
		for (int i=0; i<ITERATIONS; i++) {
			runOneTest();
		}
		
		meanMaxValue = ((double) maxValSums) / testsRun;
		meanMoves = ((double) moveTotal) / testsRun;
	}
	
	public String resultsString() {
		return String.format("Tests Run: %d\nMax Value Reached: %d\nMean Moves: %.3f\nMean Max Value: %.3f\nMax Moves: %d", 
				new Object[] {testsRun, maxVal, meanMoves, meanMaxValue, maxMoves});
	}
	
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
		System.out.println(testsRun);
	}
	
	public abstract int makeDecision(sim.game.Mover mover);
	
	public void do_after_move(sim.game.Mover mover) {}
	
	public void do_after_test(sim.game.Mover mover) {}
	
	public boolean gameNotOver(sim.game.Mover mover) {
		if (mover.isOver()) {
			return false;
		} else {
			return true;
		}
	}	
}
