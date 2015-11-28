package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // : Add a constructor
	public DictionaryLL(){
		this.dict = new LinkedList<String>();
	}

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// : Implement this method
    	word = word.toLowerCase();
//    	if(!isWord(word)){
//	    	dict.add(word);
//	        return true;
//    	}
    	dict.add(word);
    	return true;
    }


    /** Return the number of words in the dictionary */
    public int size(){
        // : Implement this method
    	return dict.size();
	}

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //: Implement this method
    	s = s.toLowerCase();
    	for(String word : this.dict){
    		if(s.equals(word)){
    			return true;
    		}
    	}
    	
        return false;
    }

    
}
