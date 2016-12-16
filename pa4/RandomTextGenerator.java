// Name: Zhaoyang Han
// USC loginid: zhaoyanh
// CS 455 PA4
// Fall 2016

import java.util.*;
import java.io.*;
/*
  This class is main logic for this assignment

  it create random text based on prefix, prefix will be call/created here

 */

public class RandomTextGenerator {
    private final int prefixLength;
    private final int numWords;
    private final ArrayList<String> source;
    private final String out;
    private final int debug;
    private Prefix prefix;
    //private Map<String, Integer> availableWords;
    private ArrayList<String> availableWords;

    final private static int lineLimit = 80;


    // the constructor, record these parameters for later use
    public RandomTextGenerator(int prefixLength, int numWords, ArrayList<String> source, String out, int debug) {
	this.prefixLength = prefixLength;
	this.numWords = numWords;
	this.source = source; // source file arrayList
	this.out = out; // output file name
	this.debug = debug;
	prefix = new Prefix(prefixLength, source); // build initial prefix
	if(debug == 1){
	    // debug mode, display the initial prefix
	    System.out.println("DEBUG: chose a new initial prefix: " + prefix.prefix.toString());
	}
	//availableWords = new HashMap<String, Integer> (); // initialilze a avialble map
	availableWords = new ArrayList<String> ();
    }


    // this is the method which creates the random text, it calls several sub helper methods
    public void generateArticle(){
	String article = "";
	String line = "";
	int wordCount = 0; // when hit numWords, then generating finish
	int lineCount = 0; // when hit lineLimit, then finish current line
	
	String nextWord = "";

	// get first word to put in the line, since first word is garanteed
	nextWord = generateWord();

	while(wordCount < numWords){
	    lineCount = 0;
	    line = "";
	    while(lineCount + nextWord.length() <= lineLimit && wordCount < numWords) {
		// if this is the start of a line, remove the space before the word
		if(lineCount == 0 && nextWord.charAt(0) == ' '){
		    nextWord = nextWord.substring(1);
		}
		// generate each line, remember to update wordCount
		line += nextWord;
		lineCount += nextWord.length();
		wordCount++;
	       
		// get a new nextWord at end of each loop
		nextWord = generateWord();

		// append space before it
		nextWord = " " + nextWord;
	    }
	    // add new line to line
	    if(wordCount < numWords){
		line += "\n";
	    }
	    // update article
	    article += line;
	}

	printToFile(article);

    }

    // this method is called by above method whenever the line needs a new word, it decides which word to be chosen from the successor. it calls another helper method called findNextWords(), if the call fails, meanning the prefix is the end of file, need a new one
    private String generateWord() {
	String result = "";
	boolean hasAvailable = true; // when it's false && wordCount < numWords, get a new prefix
	int randNum = 0;
	
	if(debug == 1){
	    System.out.println("DEBUG: prefix: " + prefix.prefix.toString());
	}

	hasAvailable = findNextWords();
	while(!hasAvailable) {
	    // if failed, get a new prefix
	    prefix.getNew();

	    if(debug == 1) {
		System.out.println("DEBUG: successors: <END OF FILE>");
		System.out.println("DEBUG: chose a new initial prefix: " + prefix.prefix.toString());
		System.out.println("DEBUG: prefix: " + prefix.prefix.toString());
	    }

	    // update available words again
	    hasAvailable = findNextWords();
	}
	
	randNum = GenText.randGen.nextInt(availableWords.size()); // create a random number as index
	result = availableWords.get(randNum); // get the random word from successor

	if(debug == 1){
	    System.out.println("DEBUG: successors: " + availableWords.toString());
	    System.out.println("DEBUG: word generated: " + result);
	}

	// need to update the prefix
	prefix.update(result);

	return result;

    }
    
    // this method basically update / create successor based on prefix
    private boolean findNextWords() {
	boolean success = false;
	availableWords.clear();
	String firstWord = prefix.prefix.get(0); // get the first word of prefix
	String nextWord = ""; // the word chosen to be in the map
	int foundInd = -1;
	for(int i = 0; i < source.size(); i++){
	    // if we find the first word 
	    if(firstWord.equals(source.get(i))){
		// match the first word in prefix, now verify if it's the subarray
		foundInd = findSubArray(i); // now foundInd is either -1, or the ind of end of prefix
		if(foundInd != -1 && foundInd < source.size() - 1){
		    // if indeed found the subarray, then the chosen word would be the next in source
		   

		    nextWord = source.get(foundInd + 1);
		    // up date the map
		    //availableUpdate(nextWord); 
		    availableWords.add(nextWord);
		    // update the i
		    i = foundInd; // don't add 1, because nextWord still also another prefix array
		}
	    }
	}
	if(availableWords.size() != 0) {
	    success = true;
	}
	// if not success, means prefix is end of file, need a new prefix
	return success;
    }
    // this is a helper method called by the above method, it verify if the prefix array follows the current index position, and if true, return the last index position of the prefix in the source file, otherwise return -1
    private int findSubArray(int sourceInd){
	String word = "";
	int resultInd = -1;
	for(int i = 1; i < prefixLength; i++){
	    word = prefix.prefix.get(i);
	    if(sourceInd + prefixLength > source.size() || !word.equals(source.get(++sourceInd))){
		return resultInd;
	    }
	}
	resultInd = sourceInd;
	return resultInd;
    }

    private void printToFile(String article) {
	try{
	    PrintWriter outFile = new PrintWriter(out);
	    outFile.print(article);
	    outFile.close();
	}
	catch (Exception e){
	    System.out.println("Output file cannot be written.");
	}

    }
}