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

	@Test
	public void remove() {
		assertTrue(sampleTree.remove(4));
		assertFalse(sampleTree.contains(4));
		assertFalse(sampleTree.remove(2048));

		assertTrue(oneNodeTree.remove(4));
		assertTrue(oneNodeTree.isEmpty());
		assertFalse(oneNodeTree.remove(4096));

		assertFalse(emptyTree.remove(8192));

		try {
			sampleTree.remove(null);
			fail("Failed to throw exception when removing null");
		} catch (NullPointerException e) {
			// test passes!!
		}
	}

	@Test
	public void removeAll() {
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
