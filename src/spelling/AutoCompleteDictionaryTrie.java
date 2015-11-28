package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //: Implement this method.
		word = word.toLowerCase();
		TrieNode currentNode = this.root;
		
		for(int i = 0; i < word.length(); i++){
			if (currentNode.getChild(word.charAt(i)) != null){
				currentNode = currentNode.getChild(word.charAt(i));
			}
			else{
				currentNode = currentNode.insert(word.charAt(i));
			}
		}
		if(currentNode.endsWord()){
			return false;
		}
		else{
			currentNode.setEndsWord(true);
			this.size++;
			return true;
		}
		
		
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //: Implement this method
	    return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // : Implement this method
		
		s = s.toLowerCase();
		TrieNode currentNode = root;
		for(int i = 0; i < s.length(); i++){
			if (!currentNode.getValidNextCharacters().contains(s.charAt(i))){
				return false;
			}
			else{
				currentNode = currentNode.getChild(s.charAt(i));
			}
		}
		if(currentNode.endsWord() && currentNode.getText().equals(s)){
			return true;
		}
		else{
			return false;
		}
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	prefix = prefix.toLowerCase();
    	List<String> completions = new LinkedList<String>(); 
    	TrieNode currentNode = root;
    	
 		for(int i = 0; i < prefix.length(); i++){
 			if (currentNode.getValidNextCharacters().contains(prefix.charAt(i))){
 				currentNode = currentNode.getChild(prefix.charAt(i));
 			}
 		}
    	
 		if(!currentNode.getText().equals(prefix)){
 			return completions;
 		}
 		
 		LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
 		queue.add(currentNode);
 		
 		TrieNode possibleCompletion;
 		while(!queue.isEmpty() && completions.size() < numCompletions){
 			
 			possibleCompletion = queue.removeFirst();
 			
 			if(possibleCompletion != null){
 				if(possibleCompletion.endsWord()){
 					completions.add(possibleCompletion.getText());
 				}	
 				Set<Character> keySet = possibleCompletion.getValidNextCharacters();
 				
 				for (char c : keySet){
 					queue.addLast(possibleCompletion.getChild(c));
 				}
 				
 			}
 			
 		}
 		
        return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}