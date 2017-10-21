package plagiarismDetector.datastructure;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * A data structure maps words to their synonyms
 * @author LArchCS
 *
 */
public class SynonymsMap {
	Map<String, HashSet<String>> map;

	/**
	 * initialize HashMap<String, HashSet<String>>()
	 */
	public SynonymsMap() {
		this.map = new HashMap<String, HashSet<String>>();
	}

	/**
	 * returns the map
	 * @return Map<String, HashSet<String>>
	 */
	public Map<String, HashSet<String>> getMap() {
		return this.map;
	}

	/**
	 * adds a list of synonyms into Map<String, HashSet<String>>
	 * @param words
	 */
	public void addSyns(List<String> words) {
		HashSet<String> synsSet = new HashSet<String>(words);
		for (String word : words) {
	        if (!map.containsKey(word)) {
	        	map.put(word, synsSet);
	        } else {
	        	map.get(word).addAll(synsSet);
	        }
	    }
	}

	/**
	 * checks if the input two words are same or synonyms
	 * @param word1
	 * @param word2
	 * @return boolean
	 */
	public boolean areSyns(String word1, String word2) {
		if (word1.equals(word2)) {
			return true;
		} else if (map.containsKey(word1) && map.containsKey(word2)) {
			return map.get(word1).contains(word2) && map.get(word2).contains(word1);
		}
		return false;
	}

	@Override
	public String toString() {
		return map.toString();
	}
}
