package solver;


/**
 * The ruzzle board
 * 
 * @author Frank
 */
public class Board {
	
	// The width and height of the board
	private final int SIZE;
	
	// The Ruzzle board containing all letters
	private Cell[][] board;
	
	// The letters on the board
	private String letters;
	
	/**
	 * Initialize the board
	 */
	public Board(String s, int n, String path) {
		this.letters = s;
		this.SIZE = n;
		this.board = new Cell[SIZE][SIZE];
		
		int i = 0;
		// Use the given string of letters to fill the board with cells
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				// Create a new cell with the x and y coördinates
				Cell cell = new Cell(x,y);
				
				// Set the character of the cell to the given letter
				cell.setCharacter(letters.charAt(i));
				i++;
				
				// Add the cell to the board
				board[x][y] = cell;
			}
		}
		
		// Set the neighbours for each cell
		setNeighbours();
	}
	
	/**
	 * For each cell, add the adjacent cells to the neighbours list
	 */
	public void setNeighbours() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				// Check if the cell is on the edge of the board
				int topRow = (y != 0) ? -1 : 0;
				int bottomRow = (y != SIZE - 1) ? 1 : 0;
				int leftCollumn = (x != 0) ? -1 : 0;
				int rightCollumn = (x != SIZE - 1) ? 1 : 0;
				
				// Loop trough all adjacent cells and add them to the neigbours list
				for (int i = topRow; i <= bottomRow; i++ ) {
					for (int j = leftCollumn; j <= rightCollumn; j++) {
						// Make sure the cell doesn't add itself as neighbour
						if (!(i == 0 && j == 0)) {
							// Add the cell to the neighbours list
							getCell(x, y).addNeighbour(getCell(x + j, y + i));
						}
					}
				}
				
			}
		}
	}
	
	/**
	 * Get the board
	 * @return the board array
	 */
	public Cell[][] getBoard() {
		return this.board;
	}
	
	/**
	 * @param x
	 * @param y
	 * @return the cell at the given (x, y) position
	 */
	public Cell getCell(int x, int y) {
		return board[x][y];
	}

}
