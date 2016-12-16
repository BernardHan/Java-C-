// Name: Zhaoyang Han
// USC loginid: zhaoyanh
// CS 455 PA4
// Fall 2016

import java.util.*;
import java.io.*;

/*
  This class contains the main method

  GenText is responsible to make all the error checkings,
  and pass the necessary parameters to create a text generator,

  then call the generator to create random text

 */


public class GenText {
    private static int prefixLength;
    private static int numWords;
    private static String source;
    private static String out;
    public static Random randGen;
    private static int debug = 0;

    public static void main(String[] args) {

	// check args input
	if(errCheckNum(args) == 1){
	    return;
	}
	// check files, and make the source file into arraylist
	ArrayList<String> wordsList = errCheckFile();
	if(wordsList.size() == 0){
	    return;
	}

	// random generator is different for debug mode
	if(debug == 1){
	    randGen = new Random(1);
	}
	else {
	    randGen = new Random();
	}


	// start operation
	RandomTextGenerator generator = new RandomTextGenerator(prefixLength, numWords, wordsList, out, debug);
	// start generating text
	generator.generateArticle();
    }

    
    // this method is to check errors related to file, and also extract all words from source file into an arrayList
    public static ArrayList<String> errCheckFile() {
	ArrayList<String> wordsList = new ArrayList<String>();
	
	try{
	    Scanner stream = new Scanner(new File(source));
	    while(stream.hasNext()){
		wordsList.add(stream.next());
	    }
	    // close file
	    stream.close();
	    // check if user input about prefixlength is too small
	    if(prefixLength >= wordsList.size()) {
		wordsList.clear();
		System.out.println("PrefixLength must be smaller than the number of words in sourceFile.");
		return wordsList;
	    }
	}
	catch(FileNotFoundException e) {
	    System.out.println("SourceFile does not exist.");
	    return wordsList;
	}

	return wordsList;
    }

    // this method is to check if the number of args correct, of the integer argument meets the preconditions, and also record all necessary parameters
    public static int errCheckNum(String[] args) {
	try{
	    if(!args[0].equals("-d")) {
		prefixLength = Integer.parseInt(args[0]);
		numWords = Integer.parseInt(args[1]);
		source = args[2];
		out = args[3];
		debug = 0;
	    }
	    else {
		prefixLength = Integer.parseInt(args[1]);
		numWords = Integer.parseInt(args[2]);
		source = args[3];
		out = args[4];
		debug = 1;
	    }
	}
	catch (NumberFormatException e){
	    // not enough arguements provided
	    System.out.println("Usage: java GenText [-d] prefixLength numWords sourceFile outFile");
	    return 1;
	}
	catch (ArrayIndexOutOfBoundsException e){
	    // not rnough arguements provided
	    System.out.println("Usage: java GenText [-d] prefixLength numWords sourceFile outFile");
	    return 1;
	}
	
	if(prefixLength < 1 || numWords < 0) {
	    System.out.println("prefixLength cannot be smaller than 1, numWords cannot be negative");
	    System.out.println("Usage: java GenText [-d] prefixLength numWords sourceFile outFile");
	    return 1;
	}
	return 0;

    }


}