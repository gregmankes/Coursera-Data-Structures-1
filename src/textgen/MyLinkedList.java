package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// : Implement this method
		this.head = new LLNode(null);
		this.tail = new LLNode(null);
		this.size = 0;
		
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// : Implement this method
		
		if (element == null){
			throw new NullPointerException("Element passed into set is null");
		}
		
		LLNode<E> addedNode = new LLNode(element);
		addedNode.next = tail;
		addedNode.prev = addedNode.next.prev;
		addedNode.prev.next = addedNode;
		addedNode.next.prev = addedNode;
		
		this.size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// : Implement this method.
		if (head.next == tail){
			throw new IndexOutOfBoundsException("List is empty or something wrong with add");
		}
		else if(index > size-1){
			throw new IndexOutOfBoundsException("Index is out of bounds of list");
		}
		else{
			if (index < 0){
				throw new IndexOutOfBoundsException("No negative index");
			}
			LLNode currentNode = head.next;
			for (int i = 0; i < index; i++){
				currentNode = currentNode.next;
			}
			return (E) currentNode.data;
		}
		
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public void add(int index, E element ) 
	{
		// : Implement this method
		LLNode oldNode = head.next;
		
		if (element == null){
			throw new NullPointerException("Element passed into set is null");
		}
		
		if(index > size){
			throw new IndexOutOfBoundsException("Index is out of bounds of list");
		}
		else{
			if (index < 0){
				throw new IndexOutOfBoundsException("No negative index");
			}
			for (int i = 0; i < index; i++){
				oldNode = oldNode.next;
			}
			LLNode newNode = new LLNode(element,oldNode.prev,oldNode);
			
			oldNode.prev.next = newNode;
			oldNode.prev = newNode;
			this.size++;
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// : Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed 
	 */
	public E remove(int index) 
	{
		// : Implement this method
		LLNode currentNode = head.next;
		
		if(index > size-1){
			throw new IndexOutOfBoundsException("Tried to remove an element that is higher than the upper bound");
		}
		else{
			if (index < 0){
				throw new IndexOutOfBoundsException("Tried to remove an element that is lower than the lower bound");
			}
			for (int i = 0; i < index; i++){
				currentNode = currentNode.next;
			}
		
			currentNode.next.prev = currentNode.prev;
			currentNode.prev.next = currentNode.next;
			currentNode.next = null;
			currentNode.prev = null;
			
			this.size--;
			return (E) currentNode.data;
		}
		
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// : Implement this method
		LLNode node = head.next;
		
		if (element == null){
			throw new NullPointerException("Element passed into set is null");
		}
		
		if(index > size-1){
			throw new IndexOutOfBoundsException("Index is out of bounds of list");
		}
		else{
			if (index < 0){
				throw new IndexOutOfBoundsException("No negative index");
			}
			for (int i = 0; i < index; i++){
				node = node.next;
			}
			E data = (E)node.data;
			node.data = element;
			
			return data;
		}
	}   
	
	
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// : Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode prevNode, LLNode nextNode) {
		this.data = e;
		this.next = nextNode;
		this.prev = prevNode;
	}

}
