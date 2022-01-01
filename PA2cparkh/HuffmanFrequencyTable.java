/*
 * Programming Assignment 2: Huffman Coding
 * TCSS 342 B
 * Professor Sakpal
 * @version Winter 2021
 * @author Rebekah Parkhurst
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Constructs a HuffmanFrequencyTable of a given String.
 * Stores Nodes of each Character and its frequency into a PriorityQueue.
 */
public class HuffmanFrequencyTable {
	
	/* Static String representations */
	final static String CHAR = "char";
	final static String FREQUENCY = "frequency";
	final static String CODE = "code";
	private StringBuilder condensedString = new StringBuilder();
	
	/* The PriorityQueue */
	PriorityQueue<HuffmanTreeNode> myPQ = new PriorityQueue<HuffmanTreeNode>(new HuffmanTreeNode(null,null));
	
	/* The String to encode */
	String myString;
	
	/* Huffman Table Constructor */
	HuffmanFrequencyTable(String theString) {
		myString = theString;
	}
	
	/* Gets the String */
	public String getString() {
		return myString;
	}
	
	/* Gets the condensedString */
	public String getCondensedString() {
		return condensedString.toString();
	}
	
	/* Converts the String to a char array */
	public char[] stringToCharArray(String string) {
		return string.toCharArray();
	}
	
	/* Returns a HashMap for character frequencies.
	 * Map key is Character, map value is Integer representing its frequency.
	 * @param string is the converted to char[] for mapping.
	 */
	public HashMap<Character, Integer> charFrequency(String string) {
		char[] chars = stringToCharArray(string);
		
		HashMap<Character,Integer> map = new HashMap<>();
		for (char c : chars) {
			if (map.containsKey(c)) {
				Integer i = map.get(c);
				map.put(c, i+1);
			}
			else map.put(c,1);
		}
		return map;
	}
	
	/* Counts the total bits before HuffmanCoding */
	public int countBitsBefore(String string) {
		int count = 0;
		HashMap<Character,Integer> chars = charFrequency(string);
		
		for (Character c : chars.keySet()) {
			for (int i=0; i<chars.get(c); i++) {
				count+=8;
			}
		}
		
		return count;
	}
	
	/* A helper method to condense the String to only one instance of each character */
	public String condensedString(String string) {
		char[] originalCharArray = stringToCharArray(string);
		ArrayList<Character> uniqueChars = new ArrayList<>();
		
		for (char c : originalCharArray) {
			if (!uniqueChars.contains(c)) {
				uniqueChars.add(c);
			}
		}
		
		StringBuilder builder = new StringBuilder();
		for (Character c : uniqueChars) {
			builder.append(c);
		}
		
		return builder.toString();
	}
	
	/* Returns the length of the encoded bit stream */
	public int getEncodedStringLength(String encodedString) {
		return encodedString.length();
	}
	
	/* Adds all the nodes in the condensed String to our Priority Queue */
	public PriorityQueue<HuffmanTreeNode> buildPriorityQueue(HashMap<Character,Integer> map, String string) {
		
		String condensed = condensedString(string);
		char[] chars = stringToCharArray(condensed);	
		for (char c : chars) {
			if (map.containsKey(c)) {
				condensedString.append(c);
				myPQ.insert(c,map.get(c));
			}
		}
		return myPQ;
	}
	
	/* Prints a table from a HashMap of characters and frequencies */
	public void printTable(HashMap<Character,Integer> frequencyMap, String string, HashMap<Character,String> streamMap, String encodedString) {
		System.out.println("% java Tester " + string);
		System.out.println("======================================");
		System.out.println(CHAR + "\t " + FREQUENCY + " \t " + CODE);
		System.out.println("--------------------------------------");
		
		char[] chars = stringToCharArray(condensedString(string));
		for (char c : chars) {
			if (frequencyMap.containsKey(c)) {
				System.out.println(c + "\t " + frequencyMap.get(c) + "\t \t" + streamMap.get(c));
			}
		}
		System.out.println("======================================");
		System.out.println("Encoded bit stream: ");
		System.out.println(encodedString);
		System.out.println("Total number of bits without Huffman coding (8-bits per character): " + countBitsBefore(myString));
		System.out.println("Total number of bits with Huffman coding: " + getEncodedStringLength(encodedString));
		double compressionRatio = (double) 100*getEncodedStringLength(encodedString)/countBitsBefore(myString);
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("Compression Ratio: " + df.format(compressionRatio) +"%");
	}

}
