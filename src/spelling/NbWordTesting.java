package spelling;

import java.util.ArrayList;
import java.util.List;

public class NbWordTesting {
	public static void main(String[] args) {
		String testString = "am";
		Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "data/dict.txt");
        NearbyWords nw = new NearbyWords(d);

        List<String> d1 = nw.distanceOne(testString, true);	
        
		List<String> retList = new ArrayList<String>();
		
        nw.deletions(testString,retList, true);	

        System.out.println(d1.toString());
        System.out.println(retList.toString());

    }
}
