// Name: Zhaoyang Han
// USC loginid: zhaoyanh
// CS 455 PA4
// Fall 2016

import java.util.*;

/*
  This class creates the prefix arrayList;


 */
final public class Prefix {
    public ArrayList<String> prefix;
    final private ArrayList<String> source;
    final private int prefixLength;

    public Prefix(int prefixLength, ArrayList<String> source) {
	prefix = new ArrayList<String>();
	this.source = source;
	this.prefixLength = prefixLength;
	getNew(); // get a new prefix, which is the initial prefix
    }

    /*
      update the prefix array with a newly selected word after removing the first
     */
    public void update(String newWord) {
	prefix.remove(0);
	prefix.add(newWord);
    }

    public void getNew() {
	// first clear prefix
	prefix.clear();
	int randNum = GenText.randGen.nextInt(source.size() - prefixLength); // generate a random number based on the size of source file, the last word cannot be the end of file
	for(int i = 0; i < prefixLength; i++) {
	    prefix.add(source.get(randNum++));
	}
    }


}