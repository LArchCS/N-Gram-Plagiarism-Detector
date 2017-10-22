package plagiarismDetector.utils;

import java.io.File;
import java.util.List;

import plagiarismDetector.datastructure.NTuple;
import plagiarismDetector.datastructure.SynonymsMap;

/**
 * Provides methods to compare files, and calculate plagiarism rate
 * @author LArchCS
 *
 */
public class DetectorUtils {

	/**
	 * compares two files, and returns plagiarism rate
	 * @param synFile
	 * @param file1
	 * @param file2
	 * @param capacity
	 * @return double
	 */
	public static double detectPlagiarism(File synFile, File file1, File file2, int capacity) {
		SynonymsMap synMap = FileUtils.generateSynonymsMapFromFile(synFile);
		List<NTuple> nTuples1 = FileUtils.generateNTuplesFromFile(file1, capacity);
		List<NTuple> nTuples2 = FileUtils.generateNTuplesFromFile(file2, capacity);
		return getPlagiarismRate(nTuples1, nTuples2, synMap);
	}

	/**
	 * calculates plagiarism rate between two lists of nTuples
	 * @param nTuples1
	 * @param nTuples2
	 * @param synMap
	 * @return double
	 */
	public static double getPlagiarismRate(List<NTuple> nTuples1, List<NTuple> nTuples2, SynonymsMap synMap) {
		if (nTuples1.size() == 0)
            		return 0;

    		double count = 0;
        	for (NTuple nTuple1 : nTuples1) {
            		for (NTuple nTuple2 : nTuples2) {
                		if (nTuple1.isSame(nTuple2, synMap)) {
                    			count += 1;
                			break;
                		}
            		}
        	}
       	 	return count / nTuples1.size();
    	}
}
