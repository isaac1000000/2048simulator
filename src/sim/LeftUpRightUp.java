package sim;

import sim.game.Mover;

public class LeftUpRightUp extends TestCase{
	int[] current_move = {2, 0, 3};
	int index = 0;
	int repeats = 0;
	int direction = 1;
	
	public LeftUpRightUp(){
		name = "Left, Up, Right, Up and Allow Down When Needed";
	}

	// Cycles through left, up, right, up
	@Override
	public int makeDecision(Mover mover) {
		if (repeats == 3){
			return 1;
		}
		int res = current_move[index];
		index += direction;
		if (index == 2 || index== 0) {
			direction *= -1;
		}
		return res;
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
		LeftUpRightUp test = new LeftUpRightUp();
		test.runTests();
		System.out.println(test.resultsString());
		System.out.println(test.maxBoardString());	
		test.printMaxBoardHistory();
	}
}
