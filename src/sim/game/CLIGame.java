package sim.game;

import java.util.Scanner;

/*
 * Runs a playable version of the game in the console
 */
public class CLIGame {	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		Mover mover;
		String input;
		do {
			mover = new Mover();
			System.out.println("-------New Game-------");
			System.out.println(mover.board);
			do {
				System.out.print("enter move >> ");
				input = kb.next();
				if (input.equals("up")) {
					mover.move(0);
				} else if (input.equals("down")) {
					mover.move(1);
				} else if (input.equals("left")) {
					mover.move(2);
				} else if (input.equals("right")) {
					mover.move(3);
				} else if (input.equals("history")) {
					mover.archive.printArchive();
					continue;
				} else if (input.equals("quit")) {
					kb.close();
					return;
				}
				clearConsole();
				System.out.println(mover.board);
			} while(mover.isOver() == false);
			System.out.println("Your highest tile: " + mover.getMaxVal());
		} while(true);
	}
	
	// A really stupid fix to a really unimportant problem
	public static void clearConsole() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
}
