package sim;

import sim.game.Mover;

public class LeftUpRightDown2 extends TestCase{
	int[] current_move = {2, 2, 0, 0, 3, 3, 1, 1};
	int index = 0;
	
	public LeftUpRightDown2(){
		name = "Left, Left, Up, Up, Right, Right, Down, Down";
	}

	// Cycles through left, up, right, down
	@Override
	public int makeDecision(Mover mover) {
		if (index > 7) {
			index -= 8;
		}
		return current_move[index++];
	}

	public static void main(String[] args) {
		LeftUpRightDown2 test = new LeftUpRightDown2();
		test.runTests();
		System.out.println(test.resultsString());
		System.out.println(test.maxBoardString());
	}
}
