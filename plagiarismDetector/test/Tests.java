package plagiarismDetector.test;

import java.io.File;

import plagiarismDetector.utils.DetectorUtils;
import plagiarismDetector.utils.FileUtils;

/**
 * 6 unit tests, 4 default file tests, 2 breakLine file tests, and 3 additional file tests
 * @author LArchCS
 *
 */
public class Tests {
	public static void main(String[] args) {
		// 6 unit tests
		testMapGeneration();
		testTupleGeneration1();
		testTupleGeneration2();
		testTupleGeneration3();
		testTupleGeneration4();
		testTupleGeneration5();
		// 4 default tests and 2 breakLine file tests
		defaultFileTest1();
		defaultFileTest2();
		defaultFileTest3();
		defaultFileTest4();
		compareBrokenFile1();
		compareBrokenFile2();
		// 3 additional file tests
		additionalFileTest1();
		additionalFileTest2();
		additionalFileTest3();
	}

	public static void testMapGeneration() {
		File f = new File("syns1.txt");
		System.out.println(FileUtils.generateSynonymsMapFromFile(f).getMap().keySet().size() == 9);
	}

	// "  go \n for \n a \n run" should be [[go], [for] [a], [run]] if N == 1
	public static void testTupleGeneration1() {
		File f = new File("file1_breakLine.txt");
		System.out.println(FileUtils.generateNTuplesFromFile(f, 1).size() == 4);
	}

	// "  go \n for \n a \n run" should be [[go, for], [for, a], [a, run]] if N == 2
	public static void testTupleGeneration2() {
		File f = new File("file1_breakLine.txt");
		System.out.println(FileUtils.generateNTuplesFromFile(f, 2).size() == 3);
	}

	// "  go \n for \n a \n run" should be [[go, for, a], [for, a, run]] if N == 3
	public static void testTupleGeneration3() {
		File f = new File("file2_breakLine.txt");
		System.out.println(FileUtils.generateNTuplesFromFile(f, 3).size() == 2);
	}

	// "  go \n for \n a \n run" should be [[go, for, a, run]] if N == 4
	public static void testTupleGeneration4() {
		File f = new File("file2_breakLine.txt");
		System.out.println(FileUtils.generateNTuplesFromFile(f, 4).size() == 1);
	}

	// "  go \n for \n a \n run" should be [] if N == 5
	public static void testTupleGeneration5() {
		File f = new File("file2_breakLine.txt");
		System.out.println(FileUtils.generateNTuplesFromFile(f, 5).size() == 0);
	}

	public static void compareBrokenFile1() {
		File f0 = new File("syns.txt");
		File f1 = new File("file1_breakLine.txt");
		File f2 = new File("file2_breakLine.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 1);
	}


	public static void compareBrokenFile2() {
		File f0 = new File("syns0.txt");
		File f1 = new File("file1_breakLine.txt");
		File f2 = new File("file2_breakLine.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 0.5);
	}


	public static void defaultFileTest1() {
		File f0 = new File("syns.txt");
		File f1 = new File("file1.txt");
		File f2 = new File("file2.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 1);
	}


	public static void defaultFileTest2() {
		File f0 = new File("syns0.txt");
		File f1 = new File("file1.txt");
		File f2 = new File("file2.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 0.5);
	}

	public static void defaultFileTest3() {
		File f0 = new File("syns.txt");
		File f1 = new File("file1.txt");
		File f2 = new File("syns0.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 0);
	}

	// test 3 empty files
	public static void defaultFileTest4() {
		File f0 = new File("syns0.txt");
		File f1 = new File("syns0.txt");
		File f2 = new File("syns0.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 0);
	}

	// test 2 same files
	public static void additionalFileTest1() {
		File f0 = new File("syns.txt");
		File f1 = new File("sample1.txt");
		File f2 = new File("sample1.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 1);
	}

	// test 2 different files
	public static void additionalFileTest2() {
		File f0 = new File("syns.txt");
		File f1 = new File("sample1.txt");
		File f2 = new File("sample3.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 0);
	}

	// test 2 different files with a synonyms file
	// also test handling Punctuations
	public static void additionalFileTest3() {
		File f0 = new File("syns1.txt");
		File f1 = new File("sample3.txt");
		File f2 = new File("sample4.txt");
		System.out.println(DetectorUtils.detectPlagiarism(f0, f1, f2, 3) == 1);
	}
}
