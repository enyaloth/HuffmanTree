/*
 * Programming Assignment 2: Huffman Coding
 * TCSS 342 B
 * Professor Sakpal
 * @version Winter 2021
 * @author Rebekah Parkhurst
 */

import java.util.Arrays;

/*
 * Constructs an Array implementation of a minHeap.
 * Contains a toString() and accompanying helper methods.
 */

public class ArrayHeap<K> {
	
	private static final int INITIAL_SIZE = 0;
	private K[] myArray;
	private HuffmanTreeNode<K> myNode;
	
	/* Constructs an empty ArrayHeap */
	public ArrayHeap(HuffmanTreeNode<K> theNode) {
		myArray = (K[]) new Object[INITIAL_SIZE];
		theNode = myNode;
	}

	/* 
	 * Returns the left Child Index of a given index in the array,
	 * or -1 if the index has no left child
	 */ 
	public int getLeftChildIndex(int theIndex) {

		int n = myArray.length;
		int index = (2*theIndex + 1);
		if (n>index) {
			return index;
		}
		else return -1;
	}
	
	/*
	 * Returns the right child index of a given index in the array,
	 * or -1 if the index has no right child
	 */
	public int getRightChildIndex(int theIndex) {
		int n = myArray.length;
		int index = (2*theIndex + 2);
		if (n>index) {
			return index;
		}
		else return -1;
	}
	
	/*
	 * Returns the parent index of a given index in the array,
	 * or -1 if the index is the root node
	 */
	public int getParentIndex(int theIndex) {
		try {
			return ((theIndex-1)/2);
		}
		catch (NullPointerException e) {
			return -1;
		}
	}
	
	/* Returns the size of the ArrayHeap */
	public int getSize() {
		return myArray.length;
	}
	
	/* Adds an element to a heap as a leaf, then heapifies */
	public void add(K object) {
		myArray = Arrays.copyOf(myArray, myArray.length+1);
		myArray[myArray.length-1] = object;
		heapifyAdd();
	}
	
	/* 
	 * Swaps elements in the Array
	 * @param myArray
	 * @param elem1 represents index
	 * @param elem2 represents index
	 */
	public void swap(K[] myArray, int elem1, int elem2) {		
	K k = myArray[elem1];
	myArray[elem1] = myArray[elem2];
	myArray[elem2] = k;
	}
	
	
	/* Removes and returns the minimum element. In a min heap, the min element will always be at index 0 */
	public <T> T removeMin() {
		try {
			// Create minNode from 0th Element
			HuffmanTreeNode<K> minNode = (HuffmanTreeNode<K>) myArray[0];
			
			// Swap the root with the last leaf
			swap(myArray,0,myArray.length-1);	
					
			// Placeholder array 
			Object[] placeholderArray = (K[]) Arrays.copyOfRange(myArray, 0, myArray.length-1);
					
			// Repoint myArray to the updated array after removal
			myArray = (K[]) placeholderArray;
			
			heapifyRemove();
			
			return (T)minNode;
		}
		catch (NullPointerException e) {
			return null;
		}
	}
	
	/* Heapifies during an add */
	public void heapifyAdd() {
		int n = myArray.length;
		for (int i=0; i<=(n/2-1); i++) {	
			try {		
				if (((HuffmanTreeNode) myArray[i]).getFrequency() > ((HuffmanTreeNode) myArray[getLeftChildIndex(i)]).getFrequency()) {		
					swap(myArray,i,getLeftChildIndex(i));
					i=0;
				}
				else if (((HuffmanTreeNode) myArray[i]).getFrequency() > ((HuffmanTreeNode) myArray[getRightChildIndex(i)]).getFrequency()) {		
					swap(myArray,i,getRightChildIndex(i));
					i=0;
				}
			}
			catch (ArrayIndexOutOfBoundsException a) {
				continue;
			}
		}
	}
	
	/* Heapifies during a remove */
	public void heapifyRemove() {
		int n = myArray.length;
		int log = (int) (Math.log(n)/Math.log(2));
		
		// when tree is just two elements, can't compare siblings, edge case
		if (n==2) {
			if (((HuffmanTreeNode) myArray[0]).getFrequency() > ((HuffmanTreeNode) myArray[1]).getFrequency()) {
			swap(myArray,0,1);
			}
		}
		
		// when tree is greater than two elements, perform comparisons for swap. This will catch out of bounds exceptions for arrays of size 0 and 1 and continue since no swap is needed in those cases.
		for (int i=0, j=0; i<=log; i++) {	
			try {		
				int smallerChild;
				if (((HuffmanTreeNode) myArray[getLeftChildIndex(j)]).getFrequency() <= ((HuffmanTreeNode) myArray[getRightChildIndex(j)]).getFrequency() && ((HuffmanTreeNode) myArray[getLeftChildIndex(j)]).getFrequency() < ((HuffmanTreeNode) myArray[j]).getFrequency()) {		
				
						smallerChild = getLeftChildIndex(j);
						swap(myArray,j,smallerChild);
						j = getLeftChildIndex(j);
				}
				else if (((HuffmanTreeNode) myArray[getLeftChildIndex(j)]).getFrequency() >= ((HuffmanTreeNode) myArray[getRightChildIndex(j)]).getFrequency() && ((HuffmanTreeNode) myArray[getRightChildIndex(j)]).getFrequency() < ((HuffmanTreeNode) myArray[j]).getFrequency()) {
					smallerChild = getRightChildIndex(j);
					swap(myArray,j,smallerChild);
					j = getRightChildIndex(j);
				}
			}
			catch (ArrayIndexOutOfBoundsException a) {
				continue;
			}
		}
	}
	
	
	/* Overrides the toString() to print the ArrayHeap elements ordered by index */
	@Override
	public String toString() {
		StringBuilder arrayString = new StringBuilder();
		arrayString.append("[");
		for (K node : myArray) {
			try {
				arrayString.append(((HuffmanTreeNode) node).getChar() + "-" + ((HuffmanTreeNode) node).getFrequency() + ", ");
			}
			catch (NullPointerException e) {
				break;
			}
		}
		arrayString.append("]");
		return arrayString.toString();
	}


}
