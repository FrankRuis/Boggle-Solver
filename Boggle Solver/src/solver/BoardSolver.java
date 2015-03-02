package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import utils.PathComparator;
import wordtrie.WordTrie;

/**
 * Class for calculating all possible words
 * 
 * @author Frank
 */
public class BoardSolver {
	
	// The size of the board
	private int size;
	
	private Board board;
	
	// Word trie for finding valid words
	private WordTrie wordTrie;
	
	// List containing all paths to possible words linked with their score
	private List<Path> words;
	
	/**
	 * Constructor with scanner for user input to fill the board
	 * @param size The size of the board
	 * @param path The path to the dictionary file
	 */
	public BoardSolver(int size, String path) {
		this.wordTrie = new WordTrie(path);
		this.size = size;
		StringBuilder letters = new StringBuilder();
		
		// Get the letters for the board
		Scanner scanner = new Scanner(System.in);
		letters.append(scanner.next());
		scanner.close();

		// Create the board
		this.board = new Board(letters.toString().trim().toUpperCase(), size, path);
		this.words = new ArrayList<>();
		
		// Fill the wordlist
		this.getWords();
	}
	
	/**
	 * Constructor with string of letters to fill the board
	 * @param size The size of the board
	 * @param path The path to the dictionary file
	 * @param letters The letters to fill the board with
	 */
	public BoardSolver(int size, String path, String letters) {
		this.wordTrie = new WordTrie(path);
		this.size = size;

		// Create the board
		this.board = new Board(letters.toString().trim().toUpperCase(), size, path);
		this.words = new ArrayList<>();
		
		// Fill the wordlist
		this.getWords();
	}
	
	/**
	 * Get the board
	 * @return the board
	 */
	public Board getBoard() {
		return this.board;
	}
	
	/**
	 * Fill the wordlist
	 */
	public void getWords() {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				// Set the current cell as a root cell
				Cell root = board.getCell(x, y);
				
				// Go through all neighbours of the root cell
				for (Cell c : root.getNeighbours()) {
					// Create a new path
					Path path = new Path(root);
					
					// Add the neighbour to the path
					path.addCell(c);
					
					// Check if this path is a word
					if (wordTrie.isWord(path.getWord())) {
						// Add the word to the wordlist
						this.words.add(path);
					}
					
					// Check if this path can become a word
					if (wordTrie.isValidPath(path)) {
						// Follow the path further
						followPath(path);
					}
				}
			}
		}
	}
	
	/**
	 * Recursively go through all unvisited neighbours to check if a word can be formed
	 * @param p the path to start from
	 */
	public void followPath(Path path) {
		// Set the current cell to the last cell in the path
		Cell curCell = path.get(path.size() - 1);
		
		// For each neighbour of the current cell
		for (Cell c : curCell.getNeighbours()) {
			// If we haven't passed this neighbour yet
			if (!path.hasPassed(c)) {
				// Create a new path that is a copy of the current path
				Path branchPath = new Path(path.get(0));

				for (int i = 1; i < path.size(); i++) {
					branchPath.addCell(path.get(i));
				}

				// Add the neighbour to the new path
				branchPath.addCell(c);
				
				// Check if the new path is a word
				if (wordTrie.isWord(branchPath.getWord())) {
					// Add the word to the wordlist
					this.words.add(branchPath);
				}
				
				// Check if the newpath can become a word
				if (wordTrie.isValidPath(branchPath)) {
					// Follow the new path further
					followPath(branchPath);
				}
			}
		}
	}
	
	/**
	 * Get a list of the longest possible words
	 * @return the list of longest words
	 */
	public List<Path> getLongestWords() {
		List<Path> longestWords = new ArrayList<>();
		String longestWord = "";
		
		// Go through all words
		for (Path path : words) {
			// If the word is longer than the current longest word
			if (path.toString().length() >= longestWord.length()) {
				// Clear the list of longest words
				longestWords.clear();
				
				// Set the longest word to the current word
				longestWord = path.toString();
				
				// Add the word to the longest words list
				longestWords.add(path);
			}
		}
		
		// Return the list of longest words
		return longestWords;
	}
	
	/**
	 * Remove the given word from the list
	 * @param word
	 */
	public void removeWord(String word) {
		List<Path> remWords = new ArrayList<>();
		for (Path p : words) {
			if (p.toString().equals(word)) {
				remWords.add(p);
			}
		}
		
		for (Path p : remWords) {
			words.remove(p);
		}
	}

	/**
	 * Get the word list
	 */
	public List<Path> getWordList() {
		return this.words;
	}
	
	/**
	 * Sort the word list from small to large
	 */
	public void sortWordList() {
		Collections.sort(words, new PathComparator());
	}

	/**
	 * The main method
	 * @param args
	 */
	public static void main(String[] args) {	
		boolean printField = true;

		BoardSolver solver = new BoardSolver(5, "Data/TWL06.txt", "POALBOPRULOPSLJEEIAWRRATI");
		
		if (printField) {
			for (int y = 0; y < solver.size; y++) {
				for (int x = 0; x < solver.size; x++) {
					System.out.print(solver.getBoard().getCell(x, y).getCharacter() + "   ");
				}
				System.out.println();
			}
			
			System.out.println();
		}
		
		solver.sortWordList();
		List<Path> paths = solver.getWordList();
		
		for (Path s : paths) {
			System.out.println(s);
		}
		
		System.out.println("\nLongest word(s):");
		for (Path s : solver.getLongestWords()) {
			System.out.println(s + " - " + s.size());
		}
		
		System.out.println("\n" + solver.words.size() + " paths found.");
	}

}
