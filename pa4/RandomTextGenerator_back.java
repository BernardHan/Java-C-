// Name: Zhaoyang Han
// USC loginid: zhaoyanh
// CS 455 PA4
// Fall 2016

import java.util.*;


public class RandomTextGenerator {
    private final int prefixLength;
    private final int numWords;
    private final ArrayList<String> source;
    private final String out;
    private Prefix prefix;
    //private Map<String, Integer> availableWords;
    private ArrayList<String> availableWords;

    final private static int lineLimit = 80;

    public RandomTextGenerator(int prefixLength, int numWords, ArrayList<String> source, String out) {
	this.prefixLength = prefixLength;
	this.numWords = numWords;
	this.source = source;
	this.out = out;
	prefix = new Prefix(prefixLength, source); // build initial prefix
	//availableWords = new HashMap<String, Integer> (); // initialilze a avialble map
	availableWords = new ArrayList<String> ();
    }

    public void generateArticle(){
	// total [numWords] words
	// 80 chars each line
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
		// generate each line, remember to update wordCount
		line += nextWord;
		lineCount += nextWord.length();
		wordCount++;
	       
		// get a new nextWord at end of each loop
		nextWord = generateWord();

		// append space before it if it is not end of the line
		if(lineCount + nextWord.length() + 1 <= lineLimit) {
		    nextWord = " " + nextWord;
		}
	    }
	    // add new line to line
	    line += "\n";
	    // update article
	    article += line;
	}

	System.out.println(article);
	

    }

    private String generateWord() {
	String result = "";
	boolean hasAvailable = true; // when it's false && wordCount < numWords, get a new prefix
	int randNum = 0;
	hasAvailable = findNextWords();
	while(!hasAvailable) {
	    // if failed, get a new prefix
	    prefix.getNew();
	    // update available words again
	    hasAvailable = findNextWords();
	}

	randNum = GenText.randGen.nextInt(availableWords.size());
	result = availableWords.get(randNum);
	// need to update the prefix
	prefix.update(result);

	return result;

    }
    

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

    private int findSubArray(int sourceInd){
	String word = "";
	int resultInd = -1;
	for(int i = 1; i < prefixLength; i++){
	    word = prefix.prefix.get(i);
	    if(!word.equals(source.get(++sourceInd))){
		return resultInd;
	    }
	}
	resultInd = sourceInd;
	return resultInd;
    }

    /*
    private void availableUpdate(String nextWord) {
	int wordFreq = 0;
	// test if this word is already in the map
	if(availableWords.containsKey(nextWord)) {
	    // if yes, then take out the value, add by 1, and put again
	    wordFreq = availableWords.get(nextWord) + 1;
	    // put again to update
	    availableWords.put(nextWord, wordFreq);
	}
	else {
	    // if the map does not have the word, simply put in it
	    wordFreq = 1;
	    availableWords.put(nextWord, wordFreq);
	}
	}*/

}