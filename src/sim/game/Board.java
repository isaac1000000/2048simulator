package sim.game;

import java.util.Random;

/*
 * The board that the game is played on
 */
public class Board {
	int [][] gameBoard;
	private int rows;
	private int cols;
	private Random rand = new Random();

	public Board() {
		this(4, 4);
	}
	public Board(int r, int c) {
		this.rows = r;
		this.cols = c;
		gameBoard = new int[r][c];
		addTile();
		addTile();
	}
	
	// Adds a tile of random value, either 2(90%) or 4(10%)
	// This is really ugly consider reworking
	public void addTile() {
		// 10% chance of getting a 4 on any tile
		int val = (rand.nextInt(10) / 9) * 2 + 2;
		
		int row;
		int col;
		do {
			row = rand.nextInt(rows);
			col = rand.nextInt(cols);
		} while (gameBoard[row][col] != 0);
		gameBoard[row][col] = val;
		
	}
	
	public static String gameBoardToString(int[][] arr) {
		String res = "";
		for (int i=0; i<arr.length; i++)
		{
			for (int j=0; j<arr[0].length; j++) {
				res += String.format("[%4d]", arr[i][j]);
			}
			res += "\n";
		}
		return res;
	}
	
	public String toString() {
		return gameBoardToString(gameBoard);
	}
	
	// Moves the board upwards once. All other moves follow this same principle
	public void up() {
		int[] newCol;
		int curr, diff;
		// Runs for each column
		for (int j=0; j<cols; j++) {
			newCol = new int[rows];
			curr = 0;
			// First "i" for loop combines all like tiles
			for (int i=0; i<rows; i++) {
				if (gameBoard[i][j] == 0) {
					continue;
				} else {
					diff = 1;
					// Skips zeroes
					while(i + diff <= rows-1 && gameBoard[i+diff][j] == 0) {
						diff++;
					}
					// If the line end is reached without a non-zero number add the current val
					if (i + diff > rows-1) {
						newCol[curr] = gameBoard[i][j];
						continue;
					}
					// If two values match combine them into one tile
					if (gameBoard[i][j] == gameBoard[i+diff][j]) {
						newCol[curr] = gameBoard[i][j] * 2;
						curr++;
						i += diff;
					// If the values don't match then add the current tile and allow the next tile to be tested
					} else {
						newCol[curr] = gameBoard[i][j];
						curr++;
						i += diff-1;
					}
				}
			}
			// Inserts results of newCol into the old column
			for (int i=0; i<rows; i++) {
				gameBoard[i][j] = newCol[i];
			}
		}
	}
	
	// Moves the board downwards once
	public void down() {
		int[] newCol;
		int curr, diff;
		// Runs for each column
		for (int j=0; j<cols; j++) {
			newCol = new int[rows];
			curr = 0;
			// First "i" for loop combines all like tiles
			for (int i=rows-1; i>=0; i--) {
				if (gameBoard[i][j] == 0) {
					continue;
				} else {
					diff = 1;
					while(i - diff >= 0 && gameBoard[i-diff][j] == 0) {
						diff++;
					}
					if (i - diff < 0) {
						newCol[curr] = gameBoard[i][j];
						continue;
					}
					if (gameBoard[i][j] == gameBoard[i-diff][j]) {
						newCol[curr] = gameBoard[i][j] * 2;
						curr++;
						i -= diff;
					} else {
						newCol[curr] = gameBoard[i][j];
						curr++;
						i -= diff - 1;
					}
				}
			}
			// Inserts results of newCol into the old column
			for (int i=rows-1; i>=0; i--) {
				gameBoard[i][j] = newCol[rows-i-1];
			}

		}
	}
	
	// Moves board once leftward
	public void left() {
		int[] newRow;
		int curr, diff;
		for (int i=0; i<rows; i++) {
			newRow = new int[cols];
			curr = 0;
			// Combines like tiles
			for (int j=0; j<cols; j++) {
				if (gameBoard[i][j] == 0) {
					continue;
				} else {
					diff = 1;
					while (j + diff <= cols - 1 && gameBoard[i][j + diff] == 0) {
						diff++;
					}
					if (j+diff > cols-1) {
						newRow[curr] = gameBoard[i][j];
						continue;
					}
					if (gameBoard[i][j] == gameBoard[i][j+diff]) {
						newRow[curr] = gameBoard[i][j] * 2;
						curr++;
						j += diff;
					} else {
						newRow[curr] = gameBoard[i][j];
						curr++;
						j += diff - 1;
					}
				}
			}
			// Inserts results of newRow into the old row
			for (int j=0; j<cols; j++) {
				gameBoard[i][j] = newRow[j];
			}
		}
	}
	
	// Moves board once rightward
	public void right() {
		int[] newRow;
		int curr, diff;
		for (int i=0; i<rows; i++) {
			newRow = new int[cols];
			curr = 0;
			// Combines like tiles
			for (int j=cols-1; j>=0; j--) {
				if (gameBoard[i][j] == 0) {
					continue;
				} else {
					diff = 1;
					while (j - diff >= 0 && gameBoard[i][j - diff] == 0) {
						diff++;
					}
					if (j-diff < 0) {
						newRow[curr] = gameBoard[i][j];
						continue;
					}
					if (gameBoard[i][j] == gameBoard[i][j-diff]) {
						newRow[curr] = gameBoard[i][j] * 2;
						curr++;
						j -= diff;
					} else {
						newRow[curr] = gameBoard[i][j];
						curr++;
						j -= diff - 1;
					}
				}
			}
			// Inserts results of newRow into the old row
			for (int j=cols-1; j>=0; j--) {
				gameBoard[i][j] = newRow[cols - j - 1];
			}
		}
	}
	
	public int getNumRows() {
		return rows;
	}
	public int getNumCols() {
		return cols;
	}

}
