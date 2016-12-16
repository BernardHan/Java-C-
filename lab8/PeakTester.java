/* 
 * Contains hasPeak method and
 * tests it on a bunch of hardcoded test cases, printing out test
 * data, actual results, and a FAILED message if actual results don't
 * match expected results.
 */

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class PeakTester {


    /*
     * see lab assgt for specification of hasPeak method.
     */
    public static boolean hasPeak(LinkedList<Integer> list) {
	int up = 0;
	int down = 0;
	boolean peak = false;

	if(list.size() == 0){
	    return peak;
	}

	ListIterator<Integer> iter = list.listIterator();
	int last = iter.hasNext() ? iter.next() : -1;
	
	while(iter.hasNext()){
	    int current = iter.next();
	    if(current < last && up == 0) {
		return peak;
	    }
	    else if(current > last && down == 1){
		return peak;
	    }

	    if(current < last) {
		down = 1;
	    }
	    else{
		up = 1;
	    }

	    last = current;
	}
	
	peak = up * down == 1 ? true : false;

	return peak;

    }



    public static void main(String args[]) {

	testPeak("", false);
	testPeak("3", false);
	testPeak("3 4", false);
	testPeak("4 2", false);
	testPeak("3 4 5", false);
	testPeak("5 4 3", false);
	testPeak("3 4 5 3", true);
	testPeak("3 4 5 3 2", true);
	testPeak("3 7 9 11 8 4 3 1", true);
	testPeak("3 5 4", true);
	testPeak("4 3 5", false);
	testPeak("2 4 3 5", false);
	testPeak("5 2 4 3 5", false);
	testPeak("5 2 4 3", false);
	testPeak("2 4 3 5 2", false);  // 2 peaks
    }

    
    
    /*  Assumes the following format for list strings (first one
     *     is empty list):
     *   "", "3", "3 4", "3 4 5", etc.
     */
    public static LinkedList<Integer> makeList(String listString) {
	Scanner strscan = new Scanner(listString);

	LinkedList<Integer> list = new LinkedList<Integer>();

	// strscan.skip("\\[");  // consume the "["
	while (strscan.hasNextInt()) {
	    list.add(strscan.nextInt());
	}

	return list;
    }


    /* Test hasPeak method on a list form of input given in listString
     * Prints test data, result, and whether result matched expectedResult
     *
     * listString is a string form of a list given as a space separated
     * sequence of ints.  E.g.,
     *  "" (empty string), "3" (1 elmt string), "2 4" (2 elmt string), etc.
     *
     */
    public static void testPeak(String listString, boolean expectedResult) {

	LinkedList<Integer> list = makeList(listString);

	boolean result = hasPeak(list);
	System.out.print("List: " + list
			   + " hasPeak? " + result);
	if (result != expectedResult) {
	    System.out.print("...hasPeak FAILED");
	}
	System.out.println();
    }
}
