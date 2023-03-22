package sim;

import sim.game.Mover;

public class LeftUpRightDown extends TestCase{
	int[] current_move = {3, 0, 2, 1};
	int index = 0;
	int repeats = 0;
	
	public LeftUpRightDown(){
		name = "Left, Up, Right, Down";
	}

	// Cycles through left, up, right
	@Override
	public int makeDecision(Mover mover) {
		if (index > 3) {
			index -= 4;
		}
		return current_move[index++];
	}

	public void do_after_move(sim.game.Mover mover) {
		if (mover.isSameAsLast()) {
			this.repeats++;
		} else {
			this.repeats = 0;
		}
	}
	
	public void do_after_test(sim.game.Mover mover) {
		this.repeats = 0;
	}
	
	public static void main(String[] args) {
		LeftUpRightDown test = new LeftUpRightDown();
		test.runTests();
		System.out.println(test.resultsString());
		System.out.println(test.maxBoardString());
	}
}
