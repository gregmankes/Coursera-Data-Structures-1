/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.awt.HeadlessException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// : Add more tests here
		assertEquals(list1.head,list1.head.next.prev);
		assertEquals((Integer)42, list1.get(1));
		
		// test lower bound
		try{
			list1.remove(-1);
			fail("removed a negative index");
		}catch(IndexOutOfBoundsException e){
			
		}
		
		// test upper bound
		try{
			list1.remove(list1.size());
			fail("Tried to remove upper bound index");
		}catch(IndexOutOfBoundsException e){
			
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // : implement this test
		boolean a = list1.add(0);
		boolean b = list1.add(1);
		
		// Tests that the elements are truly at the end of the list
		assertEquals((Integer)0, list1.get(list1.size()-2));
		assertEquals((Integer)1, list1.get(list1.size()-1));
		
		try{
			shortList.add(shortList.size()+1, "D");
			fail("Tried to add an index that was passed the upper bound");
		}catch (IndexOutOfBoundsException e){
			
		}
		
		try {
			shortList.add( null);
			fail("Tried to add a null element ");
		} catch(NullPointerException e){
			
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// : implement this test
		int before = list1.size();
		
		list1.add(0);
		before++;
		
		assertEquals((Integer)before, (Integer)list1.size());
		
		int after = list1.size();
		list1.remove(list1.size()-1);
		after--;
		
		assertEquals((Integer)after, (Integer)list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // : implement this test
		
////		 test adding at an index of an empty list
//		try{
//	    	emptyList.add(0, 0);
//	    	fail("Check out of bounds");
//	    }catch (IndexOutOfBoundsException e){
//	    	
//	    }
		
		// test adding to a negative index
		try{
	    	shortList.add(-1, "A");
	    	fail("Check out of bounds");
	    }catch (IndexOutOfBoundsException e){
	    	
	    }
		
		// test with too large index
//		try{
//	    	shortList.add(shortList.size(), "A");
//	    	fail("Check out of bounds");
//	    }catch (IndexOutOfBoundsException e){
//	    	
//	    }
		
		try {
			shortList.add(0, null);
			fail("Tried to add a null element at index 0");
		} catch(NullPointerException e){
			
		}
		
		String a = shortList.get(0);
		// Test that the element is actually added at the correct index
		shortList.add(0, "C");
		assertEquals("C",shortList.get(0));
		// Test that the old element is moved back
		assertEquals(a,shortList.get(1));
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // : implement this test
	    
		// test with empty list
		try{
	    	emptyList.set(0, 0);
	    	fail("Check out of bounds");
	    }catch (IndexOutOfBoundsException e){
	    	
	    }
		
		// test with negative index
		try{
	    	shortList.set(-1, "A");
	    	fail("Check out of bounds");
	    }catch (IndexOutOfBoundsException e){
	    	
	    }
		
		// test with too large index
		try{
	    	shortList.set(shortList.size(), "A");
	    	fail("Check out of bounds");
	    }catch (IndexOutOfBoundsException e){
	    	
	    }
		
//		try{
//			shortList.set(0, null);
//			fail("Setting element on shortList to null didn't throw exception");
//		}catch(NullPointerException e){
//			
//		}
		
		String a = shortList.get(0);
		//Test the set's return element feature
		assertEquals(a,shortList.set(0, "C"));
		//Test that the element is actually set
		assertEquals("C",shortList.get(0));
	}
	
	
	// TODO: Optionally add more test methods.
	
}
