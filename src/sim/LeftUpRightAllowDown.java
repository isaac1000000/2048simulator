package sim;

import sim.game.Mover;

public class LeftUpRightAllowDown extends TestCase{
	int[] current_move = {3, 0, 2};
	int index = 0;
	int repeats = 0;
	
	public LeftUpRightAllowDown(){
		name = "Left, Up, Right, but Allow Down When Needed";
	}

	// Cycles through left, up, right
	@Override
	public int makeDecision(Mover mover) {
		if (repeats == 2){
			return 1;
		}
		if (index > 2) {
			index -= 3;
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
		LeftUpRightAllowDown test = new LeftUpRightAllowDown();
		test.runTests();
		System.out.println(test.resultsString());
		System.out.println(test.maxBoardString());	
	}
}
