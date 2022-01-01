/*
 * Programming Assignment 2: Huffman Coding
 * TCSS 342 B
 * Professor Sakpal
 * @version Winter 2021
 * @author Rebekah Parkhurst
 */
import java.util.HashMap;

/*
 * The main method for testing the Huffman Coding Program.
 */

public class Tester {
	public static void main(String[] args) {
		
		String testString = null;
		
		try {
			/* The first argument passed is the testString */
			testString = args[0];
			
			
			/* Construct class objects */
			HuffmanFrequencyTable table = new HuffmanFrequencyTable(testString);
			HuffmanTree<HuffmanTreeNode> testTree = new HuffmanTree(testString, table);
			HashMap<Character, Integer> testMap = table.charFrequency(testString);
			PriorityQueue<HuffmanTreeNode> myPQ = table.buildPriorityQueue(testMap, testString);

			/* Build the HuffmanTree from the PriorityQueue */
			testTree.buildHuffmanTree(myPQ);
			
			/* Print the HuffmanFrequencyTable */
			table.printTable(testMap, testString, testTree.getHuffMap(), testTree.getMyBitStream());
			
			/* Test the HuffmanTree with Traversals */
			System.out.println("\nTest Huffman Tree with PreOrder & InOrder Traversals");
			System.out.print("HuffmanTree PreOrder Traversal: ");
			testTree.printPreorder(testTree.getRoot());
			System.out.print("\nHuffmanTree InOrder Traversal: ");
			testTree.printInorder(testTree.getRoot());
			
			/* Test the Decoded values */
			System.out.print("\n\n"
					+ "Testing Decode: ");
			testTree.decodeBitStream();
			
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("No arguments have been passed!");
			testString = null;
		}
		
		


	}
}
