package sim;

import sim.game.Mover;

public class LeftUpRight extends TestCase{
	int[] current_move = {3, 0, 2};
	int index = 0;
	int repeats = 0;
	
	public LeftUpRight(){
		name = "Left, Up, Right";
	}

	// Cycles through left, up, right
	@Override
	public int makeDecision(Mover mover) {
		if (index > 2) {
			index -= 3;
		}
		return current_move[index++];
	}
	
	// Very strict game-over, where if left right or up can't be achieved it ends
	public boolean gameNotOver(sim.game.Mover mover) {
		if (mover.isOver()) {
			return false;
		}
		if (repeats == 3) {
			return false;
		}
		return true;
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
		LeftUpRight test = new LeftUpRight();
		test.runTests();
		System.out.println(test.resultsString());
		
	}
}
