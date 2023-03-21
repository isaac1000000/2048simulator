package sim.game;

/*
 * Handles individual moves, essentially just controls a single board
 */
public class Mover {
	Board board;
	private int maxVal;
	private boolean over;
	private int moves;
	public BoardArchive archive;
	// Used for simulations in case a certain pattern "traps" itself
	private boolean sameAsLast;
	public int[] movesTried;
	
	public Mover() {
		this(4,4);
	}
	public Mover(int r, int c) {
		this.board = new Board(r, c);
		this.maxVal = 4;
		this.over = false;
		this.movesTried = new int[4];
		this.archive = new BoardArchive();
	}
	
	/*
	 * Enums are not working for me for some reason, seems to be a problem with eclipse.
	 * It doesn't cause a large enough problem for me to investigate.
	 * Instead of that, we're going to use this system:
	 * 0: UP
	 * 1: DOWN
	 * 2: RIGHT
	 * 3: LEFT
	 * 
	 */
	public void move(int dir) {
		int[][] lastGameBoard = new int[board.getNumRows()][board.getNumCols()];

		// Makes a copy of the game board before moving so we can compare later
		for (int i=0; i<board.getNumRows(); i++) {
			System.arraycopy(board.gameBoard[i], 0, lastGameBoard[i], 0, board.getNumCols());
		}
		switch (dir){
		case 0:
			board.up();
			break;
		case 1:
			board.down();
			break;
		case 2:
			board.left();
			break;
		case 3:
			board.right();
			break;
		default:
			break;
		}
		
		boolean emptyTile = false;
		boolean sameAsLast = true;
		int currentValue;
		for (int i=0; i<board.getNumRows(); i++) {
			for (int j=0; j<board.getNumCols(); j++) {
				currentValue = board.gameBoard[i][j];
				if (currentValue == 0) { // Case empty tile is found
					emptyTile = true;
				}
				if (currentValue != lastGameBoard[i][j]) { // Case a tile has changed
					sameAsLast = false;
				}
				if (currentValue > maxVal) { // Case a bigger max value exists
					maxVal = currentValue;
					archive.setMaxVal(currentValue);
				}
			}
		}
		
		// Gives the chance to try all directions before the game is marked over
		boolean allTried = true;
		if (emptyTile != true) {
			movesTried[dir] = 1;
			for (int i=0; i<movesTried.length; i++) {
				if (movesTried[i] == 0) {
					allTried = false;
				}
			}
			if (allTried == true) {
				this.over = true;
			}
		} else {
			for (int i=0; i<movesTried.length; i++) {
				movesTried[i] = 0;
			}
		}
		// Adds a tile if a valid move was made
		if (sameAsLast == false) {
			if (emptyTile == true) {
				board.addTile();
			}
			this.moves += 1;
			this.archive.addArchive(lastGameBoard);
		}
		this.sameAsLast = sameAsLast;
			
	}
	
	public int getMaxVal() {
		return maxVal;
	}
	public boolean isOver() {
		return this.over;
	}
	public boolean isSameAsLast() {
		return this.sameAsLast;
	}
	public Board getBoard() {
		return this.board;
	}
	public int getMoves() {
		return this.moves;
	}
}
