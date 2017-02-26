/**
 * 
 */
package cs2420;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * @author jjgarzella
 *
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {

	protected Node<Type> root;
	private int size = 0;
	
	/**
	 * FIXME: comments
	 *
	 * Pictorially, a node is:
	 *
	 *       left data  right
	 *       --------------- 
	 *     <--+   |  5  |  +--->
	 *       --------------- 
	 *  
	 *  Note, while a 5 is used above any "Type" could be contained in the node
	 */
	static class Node<Type> {
		
		Type data;
		Node<Type> left;
		Node<Type> right;

		// FIXME: write a constructor that simplifies building an initial node
		Node(Type the_data);

		/**
		 * 
		 * This function must be written recursively.
		 *
		 * Height is defined as the 1 plus the maximum height of the left vs
		 * right sub tree
		 * 
		 * @return the height from this node to its leaves
		 * 
		 * 
		 */
		int height();

		/**
		 * recursive determine if the item is in this node or the nodes after
		 * 
		 * @param item
		 *            - needle
		 * @return true if item in tree
		 */
		boolean contains(Type item);

		/**
		 * recursive - add a node
		 * 
		 * @param item
		 *            - data to add
		 * @return
		 */
		void insert(Type item);

	}
	
	public BinarySearchTree(Type item) {
		root = new Node<>(item);
		size = 1;
	}
	
	public BinarySearchTree() {
		// The defaults of the properties are already in proper
		// state for an empty tree
	}
	
	@Override
	public boolean add(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}

}
