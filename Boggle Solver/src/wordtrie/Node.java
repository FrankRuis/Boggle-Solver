package wordtrie;

import java.util.HashMap;
import java.util.Map;

/**
 * A node in a word trie
 * 
 * @author Frank
 */
public class Node {
	
	// The character of this node
	private final Character character;
	
	// Marks whether or not this is the end of a word
	private boolean endWord;
	
	// A map containing all children that mark a valid path
	private Map<Character, Node> children;
	
	private Object value;

	/**
	 * Initialize the node
	 * @param character
	 */
	public Node(Character character) {
		children = new HashMap<>();
		
		// Set this node's character to be the given character
		this.character = character;
	}
	
	/**
	 * Add a child to this node
	 * @param character the character of the child to add
	 * @return the node of the newly created child
	 */
	public Node addChild(Character character) {
		// If there is not child for the given character
		if (!children.containsKey(character)) {
			// Create a new child with the given character
			Node newChild = new Node(character);
			
			// Add the child to the list of children
			children.put(character, newChild);
			
			// Return the new child
			return newChild;
		} else {
			// Return the existing child
			return children.get(character);
		}
	}

	/**
	 * Get the child with the given character
	 * @param character the character of the child to get
	 * @return the child with the given character
	 */
	public Node getChild(Character character) {
		return children.get(character);
	}
	
	/**
	 * @return this node's character
	 */
	public Character getCharacter() {
		return character;
	}

	/**
	 * @return whether or not this is the end of a word
	 */
	public boolean isEndWord() {
		return endWord;
	}

	/**
	 * @param endWord whether or not this is the end of a word
	 */
	public void setEndWord(boolean endWord) {
		this.endWord = endWord;
	}
	
	/**
	 * @return A map containing this node's children
	 */
	public Map<Character,Node> getChildren() {
		return this.children;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
