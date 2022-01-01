/*
 * @version Winter 2021
 * @author Rebekah Parkhurst
 */

/*
 * Builds a HuffmanTreeNode with an Object char, Integer frequency, and left-right references.
 * Contains a compareTo() and accompanying helper methods.
 */

public class HuffmanTreeNode<K> implements Comparable<HuffmanTreeNode<K>> {

	private Integer myFrequency;
	private K myChar;
	private String myString;
	public HuffmanTreeNode<K> left;
	public HuffmanTreeNode<K> right; 
	
	/* A constructor for the HuffmanTreeNode */
	public HuffmanTreeNode(K theChar, Integer theFrequency) {
		myChar = theChar;
		myFrequency = theFrequency;
		left = right = null;
	}

	/* Sets the node's character */
	public void setChar(K theChar) {
		myChar = theChar;
	}
	
	/* Gets the node's character */
	public K getChar() {
		return myChar;
	}
	
	/* Gets the node's String*/
	public String getString() {
		return myString;
	}
	
	/* Sets the node's frequency */
	private void setFrequency(Integer theFrequency) {
		myFrequency = theFrequency;
	}
	
	/* Gets the node's frequency */
	public Integer getFrequency() {
		return myFrequency;
	}
	
	/* compareTo compares the frequency of nodes */
	@Override
	public int compareTo(HuffmanTreeNode theNode) {
		if (getFrequency() > theNode.getFrequency()) {
			return 1;
		} else if (getFrequency().equals(theNode.getFrequency())) {
			return 0;
		}
		else return -1;
	}

	
}
