package plagiarismDetector.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import plagiarismDetector.datastructure.NTuple;
import plagiarismDetector.datastructure.SynonymsMap;

/**
 * Provides methods to generate SynonymsMap and nTuples from files
 * @author LArchCS
 *
 */
public class FileUtils {

	/**
	 * generates a SynonymsMap from the input sync file
	 * @param file
	 * @return SynonymsMap
	 */
	public static SynonymsMap generateSynonymsMapFromFile(File file) {
		SynonymsMap synMap = new SynonymsMap();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
	        while (line != null) {
	        	String[] words = line.trim().toLowerCase().split("\\s+");
	        	List<String> wordsList = Arrays.asList(words);
	        	cleanUpList(wordsList);
	            synMap.addSyns(wordsList);
	            line = br.readLine();
	        }
	        br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return synMap;
	}

	/**
	 * generates a list of nTuples from input file
	 * @param file
	 * @param capacity
	 * @return List<NTuple>
	 */
	public static List<NTuple> generateNTuplesFromFile(File file, int capacity) {
		List<NTuple> nTuples = new ArrayList<NTuple>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
	        List<String> wordsList = new LinkedList<String>();
	        while (line != null) {
	        	String[] words = line.trim().toLowerCase().split("\\s+");
	        	List<String> newWordsList = Arrays.asList(words);
	        	wordsList.addAll(newWordsList);
	        	cleanUpList(wordsList);
	            addNTuplesFromList(wordsList, capacity, nTuples);
	            wordsList = getLeftOverList(wordsList, capacity);
	            line = br.readLine();
	        }
	        br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nTuples;
	}

	/**
	 * adds tuples from words list into nTuples, also checks redundancy
	 * @param words
	 * @param capacity
	 * @param nTuples
	 */
	protected static void addNTuplesFromList(List<String> words, int capacity, List<NTuple> nTuples) {
		for (int i = 0; i <= words.size() - capacity; i++) {
            NTuple nTuple = new NTuple(capacity);
            for (int j = i ; j < i + capacity; j++) {
                nTuple.addWord(words.get(j));
            }
            if (nTuples.isEmpty() || !nTuples.get(nTuples.size() - 1).isSame(nTuple)) {
            	nTuples.add(nTuple);
            }
        }
	}

	/**
	 * handles broken lines like "go \n for \t\n a \n run  ", and leftover words from the given linkedList
	 * @param words
	 * @param capacity
	 * @return List<String>
	 */
	protected static List<String> getLeftOverList(List<String> words, int capacity) {
		return words.subList(Math.max(0, words.size() - capacity), words.size());
	}

	/**
	 * removes non alpha/digit chars at two ends of word
	 * @param word
	 * @return String
	 */
	protected static String cleanUpWord(String word) {
		if (word.length() == 0) {
			return word;
		}
		char c = word.charAt(0);
		if (!(Character.isDigit(c) || Character.isAlphabetic(c))) {
			word = word.substring(1, word.length());
		}
		if (word.length() == 0) {
			return word;
		}
		c = word.charAt(word.length() - 1);
		if (!(Character.isDigit(c) || Character.isAlphabetic(c))) {
			word = word.substring(0, word.length() - 1);
		}
		return word;
	}

	/**
	 * Removes empty string in LinkedList. Should exist a better way to clean up, for now it is fine
	 * @param words
	 */
	protected static void cleanUpList(List<String> words) {
		for (int i = 0; i < words.size(); i++) {
			words.set(i, cleanUpWord(words.get(i)));
		}
		int index = 0;
		for (int i = 0; i < words.size(); i++) {
			String entry = words.get(index);
			if (entry.equals("")) {
				words.remove(index);
			} else {
				index += 1;
			}
		}
	}
}
