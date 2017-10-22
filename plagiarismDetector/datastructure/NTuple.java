package plagiarismDetector.datastructure;

import java.util.List;
import java.util.ArrayList;

/**
 * A data structure contains N words as a tuple
 * @author LArchCS
 *
 */
public class NTuple {
    private int capacity;
    private List<String> words;

    /**
     * initializes a NTuple of size capacity
     * @param capacity
     */
    public NTuple(int capacity) {
    	this.capacity = capacity;
        this.words = new ArrayList<String>();
    }

    /**
     * adds word into the NTuple until reaches its capacity
     * @param word
     * @return void
     */
    public void addWord(String word) {
    	if (this.words.size() < this.capacity) {
    		this.words.add(word);
    	}
    }

    /**
     * returns the wordsList stored in the nTuple
     * @return List<String>
     */
    public List<String> getWords() {
        return this.words;
    }

    /*
     * sets the capacity of nTuple
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * gets the capacity of nTuple
     * @return int
     */
    public int getCapacity() {
        return capacity;
    }


    /**
     * checks if 2 nTuples are same without considering synonyms
     * @param other
     * @return boolean
     */
    public boolean isSame(NTuple other) {
        return isSame(other, null);
    }

    /**
     * checks whether the 2 NTuple contains same or synonymous contents
	 * @param other
	 * @param synMap
	 * @return boolean
	 */
    public boolean isSame(NTuple other, SynonymsMap synMap) {
       List<String> words1 = this.getWords();
       List<String> words2 = other.getWords();

       if (words1.size() != words2.size())
           return false;

       for (int i = 0; i < capacity; i++) {
           String word1 = words1.get(i);
           String word2 = words2.get(i);
           if (synMap != null) {
        	   if (!synMap.areSyns(word1, word2)) {
                   	return false;
        	   }
           } else if (!word1.equals(word2)) {
        	   return false;
           }
       }
       return true;
    }

    @Override
    public String toString() {
        return words.toString();
    }
}
