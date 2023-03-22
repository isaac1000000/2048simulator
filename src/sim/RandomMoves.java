package sim;

import sim.game.Mover;
import java.util.Random;

public class RandomMoves extends TestCase{
	Random rand = new Random();

	public RandomMoves(){
		name = "Random Moves";
	}

	// Chooses a random move
	@Override
	public int makeDecision(Mover mover) {
		return rand.nextInt(4);
	}

	public static void main(String[] args) {
		RandomMoves test = new RandomMoves();
		test.runTests();
		System.out.println(test.resultsString());
		System.out.println(test.maxBoardString());
	}
}
