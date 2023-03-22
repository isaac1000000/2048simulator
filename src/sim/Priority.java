package sim;

import sim.game.Mover;

public class Priority extends TestCase{
	int[] move_order = {0, 3, 2, 1};
	int index = 0;
	int repeats = 0;
	
	public Priority(){
		name = "Priority: Up, Left, Right, Down";
	}

	// Cycles through left, up, right
	@Override
	public int makeDecision(Mover mover) {
		if (mover.isSameAsLast()) {
			return move_order[index++];
		} else{
			index = 0;
			return move_order[index];
		}
	}
	
	public void do_after_test(sim.game.Mover mover) {
		this.index = 0;
	}
	
	public static void main(String[] args) {
		Priority test = new Priority();
		test.runTests();
		System.out.println(test.resultsString());
		System.out.println(test.maxBoardString());
		test.printMaxBoardHistory();
	}
}
