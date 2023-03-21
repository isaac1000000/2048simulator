package sim.game;

/*
 * Stores a single board state
 */
public class BoardArchiveNode {
	final int[][] archive;
	final BoardArchiveNode next;
	
	public BoardArchiveNode(int[][] archive) {
		this(archive, null);
	}
	public BoardArchiveNode(int[][] archive, BoardArchiveNode next) {
		this.archive = archive;
		this.next = next;
	}
}
