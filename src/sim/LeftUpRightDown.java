package sim;

import sim.game.Mover;

public class LeftUpRightDown extends TestCase{
	int[] current_move = {2, 0, 3, 1};
	int index = 0;
	
	public LeftUpRightDown(){
		name = "Left, Up, Right, Down";
	}

	// Cycles through left, up, right, down
	@Override
	public int makeDecision(Mover mover) {
		if (index > 3) {
			index -= 4;
		}
		return current_move[index++];
	}
	
	public static void main(String[] args) {
		LeftUpRightDown test = new LeftUpRightDown();
		test.runTests();
		System.out.println(test.resultsString());
		System.out.println(test.maxBoardString());
	}
}
