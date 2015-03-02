package solver;

import java.util.ArrayList;
import java.util.List;

/**
 * A cell for the boggle board
 * 
 * @author Frank
 */
public class Cell {

	/**
	 * The standard multiplier of a cell
	 */
	public static final int STD_MULTIPLIER = 1;
	
	// The x coordinate of the cell
	private int x;
	
	// The y coordinate of the cell
	private int y;
	
	// The character of the cell
	private Character character;
	
	// The list of this cell's neighbouring cells
	private List<Cell> neighbours = new ArrayList<>();
	
	// The multiplier of the cell
	private int multiplier;
	
	/**
	 * Constructor to initialize the cell
	 * @param x the x coordinate of the cell
	 * @param y the y coordinate of the cell
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;

		this.multiplier = STD_MULTIPLIER;
	}

	/**
	 * @return the character in this cell
	 */
	public Character getCharacter() {
		return character;
	}


	/**
	 * @param character the character to put in this cell
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}


	/**
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x coordinate to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y coordinate to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the list of neighbours
	 */
	public List<Cell> getNeighbours() {
		return neighbours;
	}

	/**
	 * @param neighbours the list of neighbours to set
	 */
	public void setNeighbours(List<Cell> neighbours) {
		this.neighbours = neighbours;
	}

	/**
	 * @return the multiplier
	 */
	public int getMultiplier() {
		return multiplier;
	}

	/**
	 * @param multiplier the multiplier to set
	 */
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	/**
	 * @param cell the neighbouring cell to add
	 */
	public void addNeighbour(Cell cell) {
		this.neighbours.add(cell);
	}
	
	/**
	 * @param cell the neighbouring cell to remove
	 */
	public void removeNeigbour(Cell cell) {
		this.neighbours.remove(cell);
	}
	
}
