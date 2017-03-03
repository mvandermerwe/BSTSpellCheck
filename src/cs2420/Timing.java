package cs2420;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.TreeSet;

/**
 * Class to test our BinarySearchTree performance through empirical timing
 * analysis.
 * 
 * @author Mark Van der Merwe and JJ Garzella
 *
 */
public class Timing {

	// Gives us random integers to use during testing.
	static Random generator = new Random();

	final static int BIGN = 10000;
	final static int INCREMENTN = 500;

	final static int TESTNUM = 100000;
	final static int SMALLTESTNUM = 50;

	public static void main(String[] args) {
		/*
		 * Test #1. Compare binary search tree performance of a randomly created
		 * tree vs. in order tree.
		 */
		BinarySearchTree<Integer> inOrder = new BinarySearchTree<>();
		BinarySearchTree<Integer> randomOrder = new BinarySearchTree<>();

		// Run in order adding test.
		StringBuilder addData = new StringBuilder();
		StringBuilder containsData = new StringBuilder();
		for (int n = 1; n < BIGN; n += INCREMENTN) {
			// Clear so we can test new build size.
			inOrder.clear();

			long inOrderAddTime = insertInOrder(inOrder, n);
			addData.append(n + "," + inOrderAddTime + "\n");

			long containsInOrderTestTime = contains(inOrder, n);
			containsData.append(n + "," + containsInOrderTestTime + "\n");
		}
		sendToFile(addData, "inOrderAdd.csv");
		sendToFile(containsData, "inOrderContains.csv");

		// Run in order adding test.
		addData = new StringBuilder();
		containsData = new StringBuilder();
		for (int n = 1; n < BIGN; n += INCREMENTN) {
			// Clear so we can test new build size.
			randomOrder.clear();

			long randomAddTime = insertRandom(randomOrder, n);
			addData.append(n + "," + randomAddTime + "\n");

			long containsRandomTestTime = contains(randomOrder, n);
			containsData.append(n + "," + containsRandomTestTime + "\n");
		}
		sendToFile(addData, "randomAdd.csv");
		sendToFile(containsData, "randomContains.csv");

		/*
		 * Test #2: Run same tests on Java's TreeSet (a self balancing binary
		 * search tree) and compare with data above!
		 */
		TreeSet<Integer> inOrderTreeSet = new TreeSet<>();
		TreeSet<Integer> randomTreeSet = new TreeSet<>();

		// Run in order adding test for TreeSet.
		addData = new StringBuilder();
		containsData = new StringBuilder();
		for (int n = 1; n < BIGN; n += INCREMENTN) {
			// Clear so we can test new build size.
			inOrderTreeSet.clear();

			long inOrderAddTime = testTreeSetInOrder(inOrderTreeSet, n);
			addData.append(n + "," + inOrderAddTime + "\n");

			long containsInOrderTestTime = contains(inOrderTreeSet, n);
			containsData.append(n + "," + containsInOrderTestTime + "\n");
		}
		sendToFile(addData, "inOrderAddTreeSet.csv");
		sendToFile(containsData, "inOrderContainsTreeSet.csv");

		// Run random order adding test for TreeSet.
		addData = new StringBuilder();
		containsData = new StringBuilder();
		for (int n = 1; n < BIGN; n += INCREMENTN) {
			// Clear so we can test new build size.
			randomTreeSet.clear();

			long randomAddTime = testTreeSetRandom(randomTreeSet, n);
			addData.append(n + "," + randomAddTime + "\n");

			long containsRandomTestTime = contains(randomTreeSet, n);
			containsData.append(n + "," + containsRandomTestTime + "\n");
		}
		sendToFile(addData, "randomAddTreeSet.csv");
		sendToFile(containsData, "randomContainsTreeSet.csv");

	}

	/**
	 * Test BinarySearchTree performance by adding N elements in order.
	 * 
	 * @param binarySearchTree
	 *            - tree to add to.
	 * @param N
	 *            - num of elements to add.
	 * @return time (in nanoseconds) for the numbers to be added.
	 */
	public static long insertInOrder(BinarySearchTree<Integer> binarySearchTree, int N) {
		long sum = 0;

		for (int numTest = 0; numTest < SMALLTESTNUM; numTest++) {
			binarySearchTree.clear();
			long startTime = System.nanoTime();
			for (int num = 0; num < N; num++) {
				binarySearchTree.add(num);
			}
			long endTime = System.nanoTime();
			
			sum += (endTime - startTime);
		}
		

		return sum / (N*SMALLTESTNUM);
	}

	/**
	 * Test BinarySearchTree performance by adding N elements in random order.
	 * Numbers are determined as a random value between 0 and N.
	 * 
	 * @param binarySearchTree
	 *            - tree to add to.
	 * @param N
	 *            - num of elements to add.
	 * @return time (in nanoseconds) for the numbers to be added.
	 */
	public static long insertRandom(BinarySearchTree<Integer> binarySearchTree, int N) {
		long sum = 0;

		for (int numTest = 0; numTest < SMALLTESTNUM; numTest++) {
			binarySearchTree.clear();
			for (int num = 0; num < N; num++) {
				int numberToInsert = generator.nextInt(N);

				long startTime = System.nanoTime();
				binarySearchTree.add(numberToInsert);
				long endTime = System.nanoTime();

				sum += (endTime - startTime);
			}
		}

		return sum / (N * SMALLTESTNUM);
	}

	/**
	 * Test the contains performance of our BinarySearchTree by seeing how long
	 * it takes the binary tree to find a random element between 0 and N.
	 * 
	 * @param binarySearchTree
	 *            - tree to search on.
	 * @param N
	 *            - number of elements to search for/ highest bound for elements
	 *            in the tree.
	 * @return time (in nanoseconds) for the numbers to be searched for.
	 */
	public static long contains(BinarySearchTree<Integer> binarySearchTree, int N) {
		long sum = 0;

		for (int num = 0; num < TESTNUM; num++) {
			int numberToFind = generator.nextInt(N);

			long startTime = System.nanoTime();
			binarySearchTree.contains(numberToFind);
			long endTime = System.nanoTime();

			sum += (endTime - startTime);
		}

		return sum / TESTNUM;
	}

	/**
	 * Performance tester for the Java tree set. Adds N random variables between
	 * 0 and N.
	 * 
	 * @param treeSet
	 *            - set to insert into.
	 * @param N
	 *            - number of variables to add/upper bound.
	 * @return time (in nanoseconds) for the numbers to be added.
	 */
	public static long testTreeSetRandom(TreeSet<Integer> treeSet, int N) {
		long sum = 0;

		for (int numTest = 0; numTest < SMALLTESTNUM; numTest++) {
			treeSet.clear();
			for (int num = 0; num < N; num++) {
				int numberToInsert = generator.nextInt(N);

				long startTime = System.nanoTime();
				treeSet.add(numberToInsert);
				long endTime = System.nanoTime();

				sum += (endTime - startTime);
			}
		}

		return sum / (N * SMALLTESTNUM);
	}

	/**
	 * Performance tester for the Java tree set. Adds N random variables between
	 * 0 and N.
	 * 
	 * @param treeSet
	 *            - set to insert into.
	 * @param N
	 *            - number of variables to add/upper bound.
	 * @return time (in nanoseconds) for the numbers to be added.
	 */
	public static long testTreeSetInOrder(TreeSet<Integer> treeSet, int N) {
		long sum = 0;

		for (int numTest = 0; numTest < SMALLTESTNUM; numTest++) {
			long startTime = System.nanoTime();
			for (int num = 0; num < N; num++) {
				treeSet.add(num);
			}
			long endTime = System.nanoTime();
			
			sum += (endTime - startTime);
		}

		
		return sum / (N * SMALLTESTNUM);
	}

	/**
	 * Test the contains performance of Java's TreeSet by seeing how long it
	 * takes the binary tree to find a random element between 0 and N.
	 * 
	 * @param binarySearchTree
	 *            - tree to search on.
	 * @param N
	 *            - number of elements to search for/ highest bound for elements
	 *            in the tree.
	 * @return time (in nanoseconds) for the numbers to be searched for.
	 */
	public static long contains(TreeSet<Integer> treeSet, int N) {
		long sum = 0;

		for (int num = 0; num < TESTNUM; num++) {
			int numberToFind = generator.nextInt(N);

			long startTime = System.nanoTime();
			treeSet.contains(numberToFind);
			long endTime = System.nanoTime();

			sum += (endTime - startTime);
		}

		return sum / (long) TESTNUM;
	}

	private static void sendToFile(StringBuilder fileData, String filename) {
		try {
			FileWriter csvWriter = new FileWriter(filename);
			csvWriter.write(fileData.toString());
			csvWriter.close();
		} catch (IOException e) {
			System.out.println("Unable to write to file. Here is the test data, though:");
			System.out.print(fileData.toString());
		}
	}

}
