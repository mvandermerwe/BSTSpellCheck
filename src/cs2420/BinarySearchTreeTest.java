package cs2420;

import static org.junit.Assert.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of our BinarySearchTree implementation.
 * 
 * @author jjgarzella and Mark Van der Merwe
 *
 */
public class BinarySearchTreeTest {

	BinarySearchTree.Node<Integer> sampleNode;
	BinarySearchTree.Node<Integer> oneNode;

	BinarySearchTree<Integer> sampleTree;
	BinarySearchTree<Integer> oneNodeTree;
	BinarySearchTree<Integer> emptyTree;
	
	
	BinarySearchTree<Integer> treeToRemoveRoot;
	BinarySearchTree<Integer> treeToRemoveBranch;

	List<Integer> listOfNumbers;

	/**
	 * Create some nodes and trees to work with.
	 */
	@Before
	public void setUp() {
		sampleNode = new BinarySearchTree.Node<>(7);
		sampleNode.insert(5);
		sampleNode.insert(9);
		sampleNode.insert(11);

		oneNode = new BinarySearchTree.Node<>(18);

		sampleTree = new BinarySearchTree<>(2);
		sampleTree.add(5);
		sampleTree.add(-1);
		sampleTree.add(7);
		sampleTree.add(4);
		sampleTree.add(3);

		oneNodeTree = new BinarySearchTree<>(4);
		emptyTree = new BinarySearchTree<>();

		Integer[] numbersToAdd = { 10, 20, 30 };
		listOfNumbers = Arrays.asList(numbersToAdd);
		
		treeToRemoveRoot = new BinarySearchTree<>(100);
		// add sample values on the left
		treeToRemoveRoot.add(5); 
		treeToRemoveRoot.add(3);
		treeToRemoveRoot.add(7);		

		treeToRemoveBranch = new BinarySearchTree<>(100);
		// add sample values on the left
		treeToRemoveBranch.add(5); 
		treeToRemoveBranch.add(3);
		treeToRemoveBranch.add(7);
		// add values we will mess with on the right
		treeToRemoveBranch.add(150);
		treeToRemoveBranch.add(175);
		treeToRemoveBranch.add(125);
		

	}

	// --------- INNER NODE CLASS TESTS ----------

	@Test
	public void testNodeConstructor() {
		BinarySearchTree.Node<Integer> node = new BinarySearchTree.Node<>(5);
		assertTrue(5 == node.data);
	}

	@Test
	public void testHeight() {
		assertEquals(3, sampleNode.height());
		assertEquals(1, oneNode.height());
	}

	@Test
	public void testContains() {
		assertTrue(sampleNode.contains(9));
		assertTrue(sampleNode.contains(5));

		assertFalse(sampleNode.contains(10));

		assertTrue(oneNode.contains(18));
	}

	@Test
	public void testInsert() {
		oneNode.insert(2);
		assertTrue(2 == oneNode.left.data);

		sampleNode.insert(10);
		assertEquals(4, sampleNode.height());

	}

	// -------- Binary Search Tree Tests --------

	@Test
	public void testBSTConstructor() {
		BinarySearchTree<Integer> newTree = new BinarySearchTree<>(1);
		assertTrue(1 == newTree.root.data);
		assertEquals(1, newTree.size());
	}

	@Test
	public void testAdd() {
		assertTrue(sampleTree.add(8));
		assertTrue(sampleTree.contains(8));
		assertEquals(7, sampleTree.size());

		assertTrue(oneNodeTree.add(130));
		assertTrue(sampleTree.contains(130));
		assertEquals(2, sampleTree.size());

		assertTrue(emptyTree.add(1));
		assertTrue(sampleTree.contains(1));
		assertEquals(1, sampleTree.size());

		assertFalse(oneNodeTree.add(4));

		// test for null
		try {
			oneNodeTree.add(null);
			fail("Failed to throw exception on null add");
		} catch (NullPointerException e) {
			// test passes!!
		}
	}

	@Test
	public void testAddAll() {

		assertTrue(sampleTree.addAll(listOfNumbers));
		assertFalse(sampleTree.addAll(listOfNumbers));

		assertTrue(oneNodeTree.addAll(listOfNumbers));
		assertFalse(oneNodeTree.addAll(listOfNumbers));

		assertTrue(emptyTree.addAll(listOfNumbers));
		assertFalse(emptyTree.addAll(listOfNumbers));

		// test for null
		listOfNumbers.add(1, null);
		try {
			oneNodeTree.addAll(listOfNumbers);
			fail("Failed to throw exception on null addAll");
		} catch (NullPointerException e) {
			// test passes!!
		}
	}

	@Test
	public void testClear() {
		sampleTree.clear();
		assertEquals(0, sampleTree.size());
		assertTrue(null == sampleTree.root);

		oneNodeTree.clear();
		assertEquals(0, oneNodeTree.size());
		assertTrue(null == oneNodeTree.root);

		emptyTree.clear();
		assertEquals(0, emptyTree.size());
		assertTrue(null == emptyTree.root);
	}

	@Test
	public void contains() {
		assertTrue(sampleTree.contains(4));
		assertFalse(sampleTree.contains(1024));

		assertTrue(oneNodeTree.contains(4));
		assertFalse(oneNodeTree.contains(-253));

		assertFalse(emptyTree.contains(9));

		try {
			sampleTree.contains(null);
			fail("Failed to throw exception on null contains");
		} catch (NullPointerException e) {
			// test passes!!
		}

	}

	@Test
	public void testContainsAll() {
		assertFalse(sampleTree.containsAll(listOfNumbers));
		sampleTree.addAll(listOfNumbers);
		assertTrue(sampleTree.containsAll(listOfNumbers));

		assertFalse(oneNodeTree.containsAll(listOfNumbers));
		oneNodeTree.addAll(listOfNumbers);
		assertTrue(oneNodeTree.containsAll(listOfNumbers));

		assertFalse(emptyTree.containsAll(listOfNumbers));
		emptyTree.addAll(listOfNumbers);
		assertTrue(emptyTree.containsAll(listOfNumbers));

		listOfNumbers.add(1, null);
		try {
			oneNodeTree.containsAll(listOfNumbers);
			fail("Failed to throw exception on null containsAll");
		} catch (NullPointerException e) {
			// test passes!!
		}
	}

	@Test
	public void testFirst() {
		assertTrue(-1 == sampleTree.first());
		assertTrue(4 == oneNodeTree.first());

		try {
			emptyTree.first();
			fail("Failed to throw exeption on getting from empty tree");
		} catch (NoSuchElementException e) {
			// test passes!!
		}
	}

	@Test
	public void testLast() {
		assertTrue(7 == sampleTree.last());
		assertTrue(4 == sampleTree.last());

		try {
			emptyTree.last();
			fail("Failed to throw exeption on getting from empty tree");
		} catch (NoSuchElementException e) {
			// test passes!!
		}
	}

	@Test
	public void testIsEmpty() {
		assertFalse(sampleTree.isEmpty());
		assertFalse(oneNodeTree.isEmpty());
		assertTrue(emptyTree.isEmpty());
	}

	// ---------- REMOVE TESTS ----------
	
	/**
	 * The getSuccessorParent method is used to
	 * find the successor of 
	 */
	@Test
	public void testGetSuccessorParent() {
		
	}
	
	/**
	 * Because of how many cases the remove method
	 * has, it requires a better testing suite
	 * than all of the other methods.
	 * 
	 * This first one tests the standard cases than
	 * each other test does.
	 * 
	 * Most of these tests are based on the "successor"
	 * We define the successor to be the least node
	 * that is greater than N.
	 * 
	 * To remove, we find the successor, switch the 
	 * value of the successor to the node to be removed,
	 * and delete the successor.
	 */
	@Test
	public void testRemoveBasic() {
		assertTrue(sampleTree.remove(4));
		assertFalse(sampleTree.contains(4));
		assertFalse(sampleTree.remove(2048));

		assertFalse(oneNodeTree.remove(4096));
		assertTrue(oneNodeTree.remove(4));
		assertTrue(oneNodeTree.isEmpty());
		

		assertFalse(emptyTree.remove(8192));

		try {
			sampleTree.remove(null);
			fail("Failed to throw exception when removing null");
		} catch (NullPointerException e) {
			// test passes!!
		}
	}
	
	/** 
	 * Tests removal of the root node, when there is a successor
	 * which has no children, and is a child of the root node (the node to delete)
	 */
	@Test
	public void testRemoveRootSuccessorHasNoChildrenAndIsChildOfThisNode() {
		treeToRemoveRoot.add(101);
		
		
		assertTrue(treeToRemoveRoot.remove(100));
		assertFalse(treeToRemoveRoot.contains(100));
		assertTrue(101 == treeToRemoveRoot.root.data);
	}
	
	/**
	 * Tests removal of the root node, when there is a successor
	 * which has no children and is not a child of the root node
	 */
	public void testRemoveRootSuccessorHasNoChildrenAndIsNotChildOfThisNode() {
		treeToRemoveRoot.add(102);
		treeToRemoveRoot.add(101);
		treeToRemoveRoot.add(103);
		
		assertTrue(treeToRemoveRoot.remove(100));
		assertFalse(treeToRemoveRoot.contains(100));
		assertTrue(101 == treeToRemoveRoot.root.data);
	}
	
	/**
	 * Tests removal of the root node, when there is a successor
	 * with a child, and the successor is the direct child of the
	 * root node
	 */
	@Test
	public void testRemoveRootSuccessorHasChildAndIsChildOfThisNode() {
		treeToRemoveRoot.add(101);
		treeToRemoveRoot.add(102);
		
		assertTrue(treeToRemoveRoot.remove(100));
		assertFalse(treeToRemoveRoot.contains(100));
		assertTrue(101 == treeToRemoveRoot.root.data);
		assertTrue(102 == treeToRemoveRoot.root.right.data);
	}
	
	/**
	 * Tests removal of the root node, when there is a successor node,
	 * the successor has a child (on the right), but is not a direct
	 * child of the root node
	 */
	@Test
	public void testRemoveRootSuccessorHasChildAndIsNotChildOfThisNode() {
		treeToRemoveRoot.add(105);
		treeToRemoveRoot.add(101);
		treeToRemoveRoot.add(104);
		
		assertTrue(treeToRemoveRoot.remove(100));
		assertFalse(treeToRemoveRoot.contains(100));
		assertTrue(101 == treeToRemoveRoot.root.data);
		assertTrue(105 == treeToRemoveRoot.root.right.data);
		assertTrue(104 == treeToRemoveRoot.root.right.left.data);
	}
	
	/**
	 * Test removing the root when it is the largest node int the set
	 * i.e. the successor of the root is the same as the root
	 */
	@Test
	public void testRemoveRootNoSuccessor() {
		assertTrue(treeToRemoveRoot.remove(100));
		assertFalse(treeToRemoveRoot.contains(100));
		assertTrue(5 == treeToRemoveRoot.root.data);
	}
	
	/**
	 * Test removing a leaf with no children from the tree
	 */
	@Test
	public void testRemoveLeaf() {
		assertTrue(treeToRemoveRoot.remove(3));
		assertFalse(treeToRemoveRoot.contains(3));

		assertTrue(treeToRemoveRoot.remove(7));
		assertFalse(treeToRemoveRoot.contains(3));

		assertEquals(2, treeToRemoveRoot.root.height());
	}
	
	/**
	 * Tests removal of a non-root node when the node to be deleted
	 * has a successor with no children, and the successor is a child
	 * of the node to be deleted
	 */
	@Test
	public void testRemoveBranchSuccessorHasNoChildrenAndIsChildOfThisNode() {
		
		assertTrue(treeToRemoveBranch.remove(150));
		assertFalse(treeToRemoveBranch.contains(150));
		assertTrue(treeToRemoveBranch.contains(125));
		assertTrue(treeToRemoveBranch.contains(175));

	}
	
	/**
	 * Tests the removal of a non-root node when this node to be deleted
	 * has a successor with no children, and the successor is not a child
	 * of this node.
	 */
	@Test
	public void testRemoveBranchSuccessorHasNoChildrenAndIsNotChildOfThisNode() {
		
		treeToRemoveBranch.add(170);
		
		assertTrue(treeToRemoveBranch.remove(150));
		assertFalse(treeToRemoveBranch.contains(150));
		assertTrue(treeToRemoveBranch.contains(170));
		assertTrue(treeToRemoveBranch.contains(125));
	}
	
	/**
	 * Tests the removal of a non-root node when it there is a successor,
	 * the successor has a child (on the right), and the successor is
	 * a child of the node to be deleted
	 */
	@Test
	public void testRemoveBranchSuccessorHasChildAndIsChildOfThisNode() {
		treeToRemoveBranch.add(190);
		
		assertTrue(treeToRemoveBranch.remove(150));
		assertFalse(treeToRemoveBranch.contains(150));
		assertTrue(treeToRemoveBranch.contains(190));
		assertTrue(treeToRemoveBranch.contains(125));

	}
	
	/**
	 * Tests the removal of a non-root node when it has a successor, the 
	 * successor has a child, and the successor is not a child of the node to be deleted
	 */
	@Test
	public void testRemoveBranchSuccessorHasChildAndIsNotChildOfThisNode() {
		treeToRemoveBranch.add(170);
		treeToRemoveBranch.add(171);
		
		assertTrue(treeToRemoveBranch.remove(150));
		assertFalse(treeToRemoveBranch.contains(150));
		assertTrue(treeToRemoveBranch.contains(170));
		assertTrue(treeToRemoveBranch.contains(171));
		assertTrue(treeToRemoveBranch.contains(125));
	}
	
	@Test
	public void testRemoveBranchNoSuccessor() {
		treeToRemoveBranch.add(170);
		
		assertTrue(treeToRemoveBranch.remove(175));
		assertFalse(treeToRemoveBranch.contains(175));
		assertTrue(treeToRemoveBranch.contains(170));
		
	}
	
	// -------- END TESTS FOR REMOVE METHOD --------
	
	@Test
	public void testRemoveAll() {
		assertFalse(sampleTree.removeAll(listOfNumbers));
		sampleTree.addAll(listOfNumbers);
		assertTrue(sampleTree.removeAll(listOfNumbers));

		assertFalse(oneNodeTree.removeAll(listOfNumbers));
		oneNodeTree.addAll(listOfNumbers);
		assertTrue(oneNodeTree.removeAll(listOfNumbers));

		assertFalse(emptyTree.removeAll(listOfNumbers));
		emptyTree.addAll(listOfNumbers);
		assertTrue(emptyTree.removeAll(listOfNumbers));

		listOfNumbers.add(1, null);
		try {
			oneNodeTree.removeAll(listOfNumbers);
			fail("Failed to throw exception on null containsAll");
		} catch (NullPointerException e) {
			// test passes!!
		}
	}

	@Test
	public void testSize() {
		assertEquals(6, sampleTree.size());
		assertEquals(1, oneNodeTree.size());
		assertEquals(0, emptyTree.size());
	}

	@Test
	public void testToArrayList() {
		List<Integer> sampleList = sampleTree.toArrayList();
		assertEquals(6, sampleList.size());
		assertEquals("[-1, 2, 3, 4, 5, 7]", sampleList.toString());

		List<Integer> oneNodeList = oneNodeTree.toArrayList();
		assertEquals(1, oneNodeList.size());
		assertTrue(oneNodeList.contains(4));

		List<Integer> emptyList = emptyTree.toArrayList();
		assertEquals(0, emptyList.size());
	}
}
