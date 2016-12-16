// Name: Zhaoyang Han
// USC loginid: zhaoyanh
// CS 455 PA2
// FALL 2016

import java.util.*;
import java.lang.Character;
import java.lang.Iterable;

/*
  PolynomialCalculator is a class for user interface, user can type in help, print, create, add, eval... commands to manipulate polynomials.

 */

public class PolynomialCalculator {
    private static Polynomial[] polynomials = new Polynomial[10]; // the initial 10 polynomials
    private static String operation = ""; // to store the string command
    //private ArrayList<int> pairs = new ArrayList<int> ();;
    private static ArrayList<Integer> index = new ArrayList<Integer> (); // to store the index from the command

    // Main is for getting user input, then an overlevel of all the calls of checking and operations
    public static void main(String[] args){
	Arrays.fill(polynomials, new Polynomial(new Term()));
	Scanner in = new Scanner(System.in);
	while(!operation.equals("quit")){
	    index.clear();
	    System.out.print("cmd> ");
	    String command = in.nextLine();
	    command = command.trim(); // get rid of the whitespaces on two ends

	    if(check(command)) {
		switch(operation) {
		case "create": 
		    create();
		    break;
		case "print":
		    print();
		    break;
		case "add":
		    add();
		    break;
		case "eval":
		    eval();
		    break;
		case "help":
		    help();
		    break;
		default:
		    break;
		}
	    }
	}
    }
    
    // check is to check if command is valid, and if index exceed, if certain operation has wrong number of index input...etc
    private static boolean check(String command){
	Scanner parser = new Scanner(command);
	if(!parser.hasNext()){
	    System.out.println("ERROR: empty command");
	    return false;
	}
	operation = parser.next(); // take the operation command
	String temp = "";
	
	// check if the operation input is all char, and convert them all into lower case
	for(char c : operation.toCharArray()){
	    if(!Character.isLetter(c)){
		System.out.println("ERROR: Illegal command. Type 'help' for command options");
		return false;
	    }
	    c = Character.toLowerCase(c);
	    temp += c;
	}
	operation = temp;

	// Check if this operation name existed in the accepted operations
	if(!checkOperation()){
	    // check if the operation existed
	    System.out.println("ERROR: Illegal command. Type 'help' for command options");
	    return false;
	}

	// parse all the indexes out
	while(parser.hasNextInt()){
	    index.add(parser.nextInt());
	}
	
	// detect if certain operation has wrong index input
	if(index.size() == 0 && !operation.equals("quit") && !operation.equals("help")){
	    System.out.println("ERROR: No polynomial index indicated");
	    return false;
	}
	else if(operation.equals("quit") && index.size() != 0){
	    System.out.println("ERROR: operation 'quit' does not need index");
	    return false;
	}
	else if(operation.equals("add") && index.size() < 3){
	    System.out.println("ERROR: operation 'add' needs three indexes or more");
	    return false;
	}
	else if(!operation.equals("add") && !operation.equals("quit") && !operation.equals("help") && index.size() != 1){
	    System.out.println("ERROR: this operation needs one index");
	    return false;
	}

	// detect if index input exceed allowed range
	for(int i : index) {
	    if(i < 0 || i > 9) {
		System.out.println("ERROR: index range is [0, 9]");
		return false;
	    }
	}

	return true;
    }


    // this method is to check if the input operation accepted
    private static boolean checkOperation(){
	String[] operations = new String[] {"create", "print", "add", "eval", "quit", "help"};
	for(String s : operations) {
	    if(s.equals(operation)){
		return true;
	    }
	}
	return false;
    }

    // create operation
    private static void create(){
	ArrayList<Double> coeffs = new ArrayList<Double>();
	ArrayList<Integer> exps = new ArrayList<Integer>();
	Scanner input = new Scanner(System.in);
	System.out.println("Enter a space-seperated sequence of coeff-power pairs:");

	Scanner in = new Scanner(input.nextLine());

	while(in.hasNextDouble()){
	    // find coeff
	    coeffs.add(in.nextDouble());
	    // find exp
	    if(!in.hasNextInt()){
		System.out.println("WARNING: missing exponent detected, the last digit input now is ignored");
		coeffs.remove(coeffs.size() - 1);
		break;
	    }
	    else{
		exps.add(in.nextInt());
	    }
	}
	
	if(coeffs.size() != exps.size()) {
	    System.out.println("ERROR: coeffs and exps are not paired, abort");
	    return;
	}
	


	int ind = index.get(0); // get the indicated polynomial
	polynomials[ind] = new Polynomial();
	
	// the actual create process
	for(int i = 0; i < coeffs.size(); i++){
	    double coeff = coeffs.get(i);
	    int exp = exps.get(i);
	    if(exp < 0) {
		System.out.println("WARNING: negative exponent detected, now using absolute value");
		exp *= -1;
	    }

	    Term term = new Term(coeff, exp);
	    polynomials[ind] = polynomials[ind].add(new Polynomial(term));
	}

	return;
    }

    // print method, to call the polynomial's toFormattedString() method
    private static void print(){
	int ind = index.get(0); // should have only one index
	System.out.println(polynomials[ind].toFormattedString());
    }

    // add method add two polys into one
    private static void add(){
	int resultInd = index.get(0);
	int skip = 0;
	polynomials[resultInd] = polynomials[index.get(1)].add(polynomials[index.get(2)]);
	if(index.size() > 3){
	    for(int ind : index) {
		if(skip > 2){
		    polynomials[resultInd] = polynomials[resultInd].add(polynomials[ind]);
		}
		skip++;
	    }
	}
    }

    // eval method apply a value to the polynomials
    private static void eval(){
	System.out.print("Enter a floating point value for x: ");
	Scanner in = new Scanner(System.in);
	double x = 0;
	if(in.hasNextDouble()) {
	    x = in.nextDouble();
	}
	/*
	if(in.hasNext()) {
	    System.out.println("ERROR: just need one number");
	    return;
	}*/
	int ind = index.get(0);
	System.out.println(polynomials[ind].eval(x));
    }


    private static void help() {
	System.out.println("Operations are not case-sensitive, and index number must be in [0, 9]. Available operations:");
      
	System.out.println("-create: take one index number, hit enter, then enter any even digits to create a polynomial in the format: (coefficient, exponent)");
	System.out.println();
	System.out.println("-print: take one index number, the corresponding polynomial will be printed");
	System.out.println();
	System.out.println("-add: take three or more index numbers, ex: add 2 0 1, poly2 = poly0 + poly1");
	System.out.println();
	System.out.println("-eval: take one index number, and evaluate this polynomial with a value you specify");
	System.out.println();
	System.out.println("-quit: quit this user interface");
	System.out.println();
	System.out.println("-help: show this page");
    }
}