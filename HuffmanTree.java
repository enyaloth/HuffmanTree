/*
 * @version Winter 2021
 * @author Rebekah Parkhurst
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/*
 * Constructs a HuffmanTree from a Priority Queue.
 * Contains methods to create bit streams, encode, decode, and helper methods.
 */

public class HuffmanTree<K> {
	
	/* A counter for internal nodes */
	private int myCount = 0;
	
	/* The PriorityQueue to build the tree */
	private PriorityQueue<K> myPQ = new PriorityQueue(new HuffmanTreeNode(null,null));
	private HuffmanTreeNode<K> myTreeNode;
	
	/* A StringBuilder to create the Bit Stream */
	private StringBuilder myBitStream = new StringBuilder();
	private StringBuilder encodedBitStream = new StringBuilder();
	private StringBuilder condensedString = new StringBuilder();
	String myCondensedString;
	String myEncodedString;
	
	/* A HashMap store store each Huffman Bit Representation with its corresponding Character */
	private HashMap<Character,String> myMap = new HashMap<>();
	
	HuffmanFrequencyTable myTable;

	/* HuffmanTree Constructor */
	public <K> HuffmanTree(String condensedString, HuffmanFrequencyTable theTable) {
		myTable = theTable;
		myCondensedString = condensedString;
		HuffmanTreeNode<K> left;
		HuffmanTreeNode<K> right;
	}
	
	/* Returns the map which stores the bit streams */
	public HashMap getHuffMap() {
		return myMap;
	}
	
	/* Returns the encoded bit stream */
	public String getMyBitStream() {
		return encodedBitStream.toString();
	}

	
	/* Returns the root of the HuffmanTree */
	public HuffmanTreeNode getRoot() {
		return myTreeNode;
	}
	
	/* Counts the internal root nodes */
	public int countNodes() {
		myCount++;
		return myCount;
	}
	
	/* Builds the Huffman Tree */
	public void buildHuffmanTree(PriorityQueue<HuffmanTreeNode> thePQ) {
		
		while (thePQ.getSize()>1) {		
			String currentCount = "N" + countNodes();
		
			HuffmanTreeNode left = (HuffmanTreeNode) thePQ.removeMin();
			HuffmanTreeNode right = (HuffmanTreeNode) thePQ.removeMin();	
			HuffmanTreeNode root = new HuffmanTreeNode(currentCount,(left.getFrequency()+right.getFrequency()));
			root.left = left;
			root.right = right;	
	
			thePQ.addNode(root);
		}
		if (thePQ.getSize()==1) {
			myTreeNode = thePQ.removeMin();
			createBitStream(myTreeNode, myBitStream);
			encodeBitStream(encodedBitStream);
		}
	}
	
	/*
	 * Recursively Traverses the HuffmanTree to create a bit stream for each value.
	 * Stores the bit streams in myMap with the key as the corresponding Character.
	 * @param HuffmanTreeNode
	 * @param StringBuilder
	 */	
	private void createBitStream(HuffmanTreeNode<K> node, StringBuilder myStream) {

		try {
			if (node.left == null && node.right == null) {
				myMap.put((Character) node.getChar(), myStream.toString());
		
			} else {
				myStream.append('0');	
				createBitStream(node.left, myStream);	
				myStream.setLength(myStream.length() - 1);
		
				myStream.append('1');
				createBitStream(node.right, myStream);
				myStream.setLength(myStream.length() - 1);
			}
		}
		catch (NullPointerException e) {
			return;
		}
	}
	
	/* Encodes a String into a bitStream */
	public String encodeBitStream(StringBuilder bitStream) {
		String string = myTable.getString();
		HashMap map = myTable.charFrequency(string);
		char[] chars = string.toCharArray();
		for (char c : chars) {	
				bitStream.append(myMap.get(c));
		}	
		myEncodedString = bitStream.toString();
		return bitStream.toString();
	}
	
	/* Takes a map containing all character bitStream pairs and uses the String as a path to traverse the HuffmanTree and return the correct leafs.*/
	public void decodeBitStream() {
		ArrayList<HuffmanTreeNode> myDecodedNodes = new ArrayList<>();
		for (Character chars : myMap.keySet()) {

			HuffmanTreeNode<K> root = myTreeNode;
			
			char[] stream = myMap.get(chars).toCharArray();
			for (char c : stream) {
				if (c==('0')) {
					root = root.left;
				}
				else if (c==('1')) {
					root = root.right;
				}
			}
			myDecodedNodes.add(root);
		}
		
		for (HuffmanTreeNode node : myDecodedNodes) {
			System.out.print("["+myMap.get(node.getChar()) + "=" + node.getChar()+ "*" + node.getFrequency() + "] ");
		}
		
		System.out.println();
		System.out.print("Decoded String: ");
		char[] decodeChars = myEncodedString.toCharArray();
		
		StringBuilder decodeStringBuilder = new StringBuilder();
		for (String s : myMap.values()) {
		}
		
		for (int i=0; i<decodeChars.length-1; i++) {
			//System.out.print(decodeChars[i] + " ");
			if (myMap.containsValue(decodeStringBuilder.toString())) {
				for (Entry<Character,String> entry : myMap.entrySet()) {
					if (entry.getValue().equals(decodeStringBuilder.toString())) {
						System.out.print(entry.getKey());
					}
				}
				decodeStringBuilder.setLength(0);
				i--;
			} else {
				decodeStringBuilder.append(decodeChars[i]);
			}
		}
	}
	
	/* Prints an InOrder traversal of the Huffman Tree used for testing purposes */
	void printInorder(HuffmanTreeNode<K> node) { 
        if (node == null) return; 
  
        printInorder(node.left); 
        System.out.print(node.getFrequency() + " "); 
        printInorder(node.right); 
    } 
	
	/* Prints a PreOrder traversal of the Huffman Tree used for testing purposes */
	void printPreorder(HuffmanTreeNode<K> node) { 
        if (node == null) return; 
  
        System.out.print(node.getFrequency() + " "); 
        printPreorder(node.left); 
        printPreorder(node.right); 
    } 
	
	
	
}
