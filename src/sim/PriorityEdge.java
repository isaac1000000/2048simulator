package sim;

import sim.game.Mover;

public class PriorityEdge extends TestCase{
	int[] move_order = {2, 0, 3, 1};
	int index = 0;
	int repeats = 0;
	
	public PriorityEdge(){
		name = "Priority: Left, Up, Right, Down";
	}

	// Prioritizes up, then left, right, down
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
		PriorityEdge test = new PriorityEdge();
		test.runTests();
		System.out.println(test.resultsString());
		System.out.println(test.maxBoardString());
	}
}
