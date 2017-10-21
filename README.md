# N-Gram-Plagiarism-Detector
&#x1F53A;Notice: this is an assignment I received. If you receive similar assignment, please do not use code in this repository.<br /><br />
Using N-gram comparison algorithm to detect plagiarism and synonyms between files. For example, if:
```
syns.txt:
run sprint jog

file1.txt:
go for a run

file2.txt:
go for a jog
```

The Plagiarism rate between file1 and file2, using syns.txt, will be: 100%.

## File structure
```
N-Gram-Plagiarism-Detector/
	<.txt files> used by test cases
	plagiarismDetector/
		PlagiarismDetector.java
		datastructure/
			NTuple.java
			SynonymsMap.java
		utils/
			DetectorUtils.java
			FileUtils.java
		test/
			Tests.java
```

There are 6 java files in this program.
- ```plagiarismDetector.java```
This .java contains the main function. It is responsible to interact with user, validate user input, print information and provide input format sample, check file existence & readability, and report plagiarism rate.
- ```datastructure/NTuple.java```
This is the n-tuple data structure, which holds a n-size-LinkedList of words. It is responsible to form the basic comparison units, and provides function to compare itself to other NTuple.
- ```datastructure/SynonymsMap.java```
This is the map structure to map words with their synonyms. It is also responsible to answer if two words are synonyms.
- ```utils/FileUtils.java```
This File Utility class is to provide basic file operation functions – read file, generate SynonymsMap from file, and generate lists of NTuples from file.
- ```utils/DetectorUtils.java```
This Detector Utility class is to take files selected by users (passed in by plagiarismDetector.java), compare files, and compute plagiarism rate.
- ```test/Tests.java```
This is a test file, contains –
6 unit tests;
4 default file tests and 2 breakLine file tests;
3 additional file tests.

## Compile and Run files
Simply unzip, compile, and run java files.
```
javac plagiarismDetector/PlagiarismDetector.java
javac plagiarismDetector/test/Tests.java
java plagiarismDetector/PlagiarismDetector
java plagiarismDetector/test/Tests
```

If run `Tests.java`, 15 True will be displayed indicating the 15 test cases.
If run `PlagiarismDetector.java`, you will see:
```
Welcome to Plagiarism Detector!

This program takes 4 parameters:
1. file name for a list of synonyms
2. input file 1
3. input file 2
4. (optional) the number N, the tuple size. If not supplied, the default should be N=3.

Please type in the parameters seperated by <SPACE> or <DELIMITER>. For example:
syns.txt | file1.txt | file2.txt | 3
Or enter EXIT to exit
```
The simplest way to test the code is to copy and paste in your console
```
syns.txt | file1.txt | file2.txt | 3
```
