package plagiarismDetector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import plagiarismDetector.utils.DetectorUtils;


/**
 * interacts and validates user input, handles I/O exceptions, and reports plagiarism rate
 * @author LArchCS
 *
 */
public class PlagiarismDetector {
	static int N;
	static File synFile;
	static File file1;
	static File file2;
	static List<String> errorList;

	public static void main(String[] args) {
		resetFields();
		printIntro();

		// asks for and validates user input
		Scanner CONSOLE = new Scanner(System.in);
		while (true) {
			printFormat();
			String input = CONSOLE.nextLine();
			if (input.equals("EXIT")) {
				CONSOLE.close();
				return;
			}
			if (!validateInput(input)) {
				printError();
				resetFields();
			} else {
				if (N < 0) {
					N = 3;
					printErrorInteger();
				}
				break;
			}
		}
		CONSOLE.close();

		// reports Plagiarism rate
		double rate = DetectorUtils.detectPlagiarism(synFile, file1, file2, N);
		printPlagiarismRate(rate);
	}

	/**
	 * initializes static fields
	 */
	public static void resetFields() {
		N = -1;
		file1 = null;
		file2 = null;
		synFile = null;
		errorList = new ArrayList<String>();
	}

	/**
	 * validates user inputs, and updates static fields
	 * @param input
	 * @return boolean
	 */
	public static boolean validateInput(String input) {
		String s = String.join(" ", input.trim().split("\\|"));
		String[] parameters = s.split("\\s+");
		if (parameters.length < 3 || parameters.length > 4) {
			return false;
		}
		synFile = validateFile(parameters[0]);
		file1 = validateFile(parameters[1]);
		file2 = validateFile(parameters[2]);
		if (parameters.length == 4) {
			N = validateInteger(parameters[3]);
		}
		return errorList.isEmpty();
	}

	/**
	 * validates file path, handles I/O exception
	 * @param fileName
	 * @return File
	 */
	public static File validateFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.canRead()) {
			return file;
		}
		errorList.add(fileName);
		return null;
	}

	/**
	 * validates user input for N (the size of nTuple)
	 * @param input
	 * @return int
	 */
	public static int validateInteger(String input) {
		try {
            int capacity = Integer.parseInt(input);
            return capacity;
        } catch (NumberFormatException e) {
            return -1;
        }
	}

	/**
	 * prints intro message
	 */
	public static void printIntro() {
		System.out.println("Welcome to Plagiarism Detector!\n");
		System.out.println("This program takes 4 parameters:");
		System.out.println("1. file name for a list of synonyms");
		System.out.println("2. input file 1");
		System.out.println("3. input file 2");
		System.out.println("4. (optional) the number N, the tuple size. If not supplied, the default should be N=3.");
	}

	/**
	 * provides user input format
	 */
	public static void printFormat() {
		System.out.println("\nPlease type in the parameters seperated by <SPACE> or <DELIMITER>. For example:");
		System.out.println("syns.txt | file1.txt | file2.txt | 3");
		System.out.println("Or enter EXIT to exit");
	}

	/**
	 * prints plagiarism rate
	 * @param rate
	 */
	public static void printPlagiarismRate(double rate) {
		System.out.printf("\nThe plagiarism rate between %s and %s is: %1.2f%%\n", file1.getName(), file2.getName(), rate * 100);
	}

	/**
	 * prints error message, including failed file paths
	 */
	public static void printError() {
		System.out.println("\nInvalid Input");
		for (String fileName: errorList) {
			System.out.println("Invalid File Name: " + fileName);
		}
		System.out.println("Try again");
	}

	/**
	 * notifies the user that N is set to default
	 */
	public static void printErrorInteger() {
		System.out.println("\nSet N to default (3)");
	}
}
