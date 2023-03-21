package sim.game;

/*
 * Keeps an archive of all moves made on a board
 */
public class BoardArchive {
	BoardArchiveNode head;
	private int maxVal;
	
	public BoardArchive() {
		this.maxVal = 4;
	};
	
	public void addArchive(int[][] archive) {
		BoardArchiveNode boardArchive = new BoardArchiveNode(archive, head);
		head = boardArchive;
	}
	
	public void printArchive() {
		printArchiveRec(head);
	}
	private void printArchiveRec(BoardArchiveNode curr) {
		if (curr == null) {
			return;
		}
		System.out.println(Board.gameBoardToString(curr.archive));
		printArchiveRec(curr.next);
	}
	
	public int getMaxVal() {
		return maxVal;
	}
	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}
}
