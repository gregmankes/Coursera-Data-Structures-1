package spelling;

import java.util.List;
import java.util.LinkedList;

public class Testing {
	private String dictFile = "data/words.small.txt"; 

	AutoCompleteDictionaryTrie emptyDict; 
	AutoCompleteDictionaryTrie smallDict;
	AutoCompleteDictionaryTrie largeDict;
	
	public static void main(String[] args) {
		Testing t = new Testing();
		t.setup();
//		List<String> completions = new LinkedList<String>();
//		System.out.println(completions.size());
	}
	
	public void setup(){
		emptyDict = new AutoCompleteDictionaryTrie();
		smallDict = new AutoCompleteDictionaryTrie();
		largeDict = new AutoCompleteDictionaryTrie();

		smallDict.addWord("Hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("hem");
		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");
		
		DictionaryLoader.loadDictionary(largeDict, dictFile);
		
		List<String> completions;
		completions = smallDict.predictCompletions("", 0);
		System.out.println(completions.size());

	}
}
