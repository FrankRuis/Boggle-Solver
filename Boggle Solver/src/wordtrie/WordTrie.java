package wordtrie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import solver.Path;

/**
 * Read the dictionary file and create a word trie
 * 
 * @author Frank
 */
public class WordTrie {

	// The root node of the trie
	private final Node root;
	
	/**
	 * Read all words from the dictionary file and add them to the trie.
	 * @param path the path to the dictionary file
	 */
	public WordTrie(String path) {
		// Create the root node
		this.root = new Node(null);
		
		try {
			// Open the dictionary file with a buffered reader
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String word;
			// Go through all words in the dictionary file
			while ((word = reader.readLine()) != null) {
				// Add the word to the trie if it not an empty string
				if (!word.isEmpty()) {
					this.add(word);
				}
			}
			
			// Close the buffered reader
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create an empty word trie
	 */
	public WordTrie() {
		// Create the root node
		this.root = new Node(null);
	}
	
	/**
	 * Add the word to the trie
	 * @param w the word to add
	 */
	public void add(String w) {
		// Make the word lower case and trim whitespace
		char[] charArray = w.toLowerCase().trim().toCharArray();
		
		// Set the current node to the root node
		Node curNode = root;
		
		// Go through all letters of the word
		for (char c : charArray) {
			// Add the letter to the current node as a child, set the current node to be that child
			curNode = curNode.addChild(c);
		}
		
		// The end of the word has been reached, set the current node to be the end of the word
		curNode.setEndWord(true);
		curNode.setValue(w);
	}
	
	/**
	 * Get the end node of the given word
	 * @param w the word ending to get
	 * @return the node that marks the end of the given word
	 */
	public Node getWord(String w) {
		// Make the word lower case and trim whitespace
		char[] charArray = w.toLowerCase().trim().toCharArray();
		
		// Set the current node to the root node
		Node curNode = root;
		
		// Go through all letters in the word
		for (char c : charArray) {
			// Set the current node to the current letter of the word
			curNode = curNode.getChild(c);
			
			// If the current node is null, the word does not exist in the trie
			if (curNode == null) {
				// Stop the loop
				break;
			}
		}
		
		// Return the node marking the end of the given word (null if no word is found)
		return curNode;
	}
	
	/**
	 * Check if the given word is a word in this trie
	 * @param w the word to check
	 * @return true if the word is valid, else false
	 */
	public boolean isWord(String w) {
		// Make the word lower case and trim whitespace
		char[] charArray = w.toLowerCase().trim().toCharArray();
		
		// Set the current node to the root node
		Node curNode = root;
		
		// Go through all letters in the word
		for (char c : charArray) {
			// Set the current node to the current letter of the word
			curNode = curNode.getChild(c);
			
			// If the current node is null, the word does not exist in the trie
			if (curNode == null) {
				// The given word is invalid
				return false;
			}
		}
		
		// Return true if the node marks the end of a word, else return false
		return curNode.isEndWord();
	}
	
	/**
	 * Check if the given path exists in this tree
	 * @param p the path to check
	 * @return true if the path is valid, else false
	 */
	public boolean isValidPath(Path p) {
		// Set the current node to the root node
		Node curNode = root;
		
		// Go through all letters in the path's word
		for (int i = 0; i < p.getWord().length(); i++) {
			// Set the current node to the current letter of the word
			curNode = curNode.getChild(p.getWord().toLowerCase().charAt(i));
			
			// If the current node is null, the path does not exist in the tree
			if (curNode == null) {
				// The path is invalid
				return false;
			}
		}
		
		// The path is valid
		return true;
	}
	
	/**
	 * @return The root node
	 */
	public Node getRoot() {
		return this.root;
	}
	
	/**
	 * @return A list containing all values in the trie
	 */
	public List<String> getAllWords() {
		List<String> values = new ArrayList<>();
		this.fillValueList(root, values);
		
		// Return the list filled with values of endpoints in the trie
		return values;
	}
	
	/**
	 * Recursively fill the given list with the values of nodes that are endpoints
	 * @param n The node to check
	 * @param values The list of values to fill
	 */
	protected void fillValueList(Node n, List<String> values) {
		// Check each child of the given node
		for (Node node : n.getChildren().values()) {
			// If this is in endpoint, add the value to the list
			if (node.isEndWord()) {
				values.add((String) node.getValue());
			}
			
			// Recursively check the children of each of the child nodes
			this.fillValueList(node, values);
		}
	}
}
