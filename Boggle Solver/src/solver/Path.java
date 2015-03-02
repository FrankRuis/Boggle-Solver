package solver;

import java.util.LinkedList;
import java.util.List;

/**
 * A path through Cell objects
 * 
 * @author Frank
 */
public class Path {
	
	// A list containing the cells this path has passed
	private List<Cell> passedCells;
	
	// The word formed by the passed cells
	private String word;
	
	// The points this path is word without multipliers
	private int points;
	
	// The multiplier for this path
	private int multiplier;
	
	/**
	 * Constructor
	 * @param c the first cell of the path
	 */
	public Path(Cell cell) {
		// Use a linked list to keep insertion order
		this.passedCells = new LinkedList<>();
		
		// Set the points to the value of the first cell
		this.points = (CharVal.valueOf(cell.getCharacter().toString())).getVal();
		
		// Set the multiplier to the multiplier of the first cell
		this.multiplier = cell.getMultiplier();
		
		// Add the first cell to the list of passed cells
		this.passedCells.add(cell);
		
		// Add the letter of the first cell to this path's word
		this.word = cell.getCharacter().toString();
	}
	
	/**
	 * Add a cell to the passed cells list
	 * @param c the cell to add
	 */
	public void addCell(Cell c) {
		// Add the cell to the list of passed cells
		this.passedCells.add(c);
		
		// Add the letter of the cell to the word
		this.word += c.getCharacter();
		
		// Add the value of the cell to the points
		points += (CharVal.valueOf(c.getCharacter().toString())).getVal();
		
		// Add the cell's multiplier to this path's multiplier if it is larger than 1
		if (c.getMultiplier() > 1) {
			multiplier += c.getMultiplier();
		}
	}
	
	/**
	 * Check if this path has passed the given cell
	 * @param cell
	 * @return true if the cell was passed, false if not
	 */
	public boolean hasPassed(Cell cell) {
		return passedCells.contains(cell);
	}
	
	/**
	 * Get the a cell in this path 
	 * @param i The index of the cell to get
	 * @return The desired cell
	 */
	public Cell get(int i) {
		return passedCells.get(i);
	}
	
	/**
	 * @return The size of the path
	 */
	public int size() {
		return passedCells.size();
	}
	
	/**
	 * @return the list of passed cells
	 */
	public List<Cell> getPassedCells() {
		return passedCells;
	}
	
	/**
	 * @return this word formed by this path
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * @return this path's points
	 */
	public int getPoints() {
		return (points*multiplier);
	}
	
	@Override
	public String toString() {
		return this.word;
	}

}
