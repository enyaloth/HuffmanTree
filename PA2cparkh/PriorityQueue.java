/*
 * Programming Assignment 2: Huffman Coding
 * TCSS 342 B
 * Professor Sakpal
 * @version Winter 2021
 * @author Rebekah Parkhurst
 */

/*
 * Builds a PriorityQueue by extending the ArrayHeap.
 * Contains a toString() and accompanying helper methods.
 */

public class PriorityQueue<K> extends ArrayHeap<K> {
	
	ArrayHeap<HuffmanTreeNode<K>> myArrayHeap;
	private K[] myArray;
	HuffmanTreeNode<K> node;
	String string;
	int priority;
	
	/* Constructs a PriorityQueue */
	public PriorityQueue(HuffmanTreeNode theNode) {
		super(theNode);
		myArrayHeap = new ArrayHeap(node);
	}
	
	/* sets the Priority of the HuffmanTreeNode for Queue Insertion */
	private void setPriority(int thePriority) {
		priority = thePriority;
	}
	
	/* Gets the Priority */
	private int getPriority() {
		return priority;
	}
	
	/* 
	 * Adds a HuffmanTreeNode to the PriorityQueue 
	 * @Param object is the Node's character
	 * @Param priority is the Node's frequency
	 */
	public void insert(Character object, int priority) {
		HuffmanTreeNode<K> temp = new HuffmanTreeNode(object,priority);
		myArrayHeap.add(temp);
	}
	
	public void insertString(String object, int priority) {
		HuffmanTreeNode<K> temp = new HuffmanTreeNode(object,priority);
		myArrayHeap.add(temp);
	}
	
	public void addNode(HuffmanTreeNode<K> object) {
		myArrayHeap.add(object);
	}
	
	/* Removes the min HuffmanTreeNode from the PriorityQueue */
	public K removeMin() {
		K temp = myArrayHeap.removeMin();
		return temp;
	}
	
	/* Returns the size of elements in the PriorityQueue */
	public int getSize() {
		return myArrayHeap.getSize();
	}
	
	/* Overrides the toString() method to print the array elements */
	@Override
	public String toString() {	
		return myArrayHeap.toString();
	}

	
}

