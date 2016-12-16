// Name: Zhaoyang Han
// USC loginid: zhaoyanh
// CS 455 PA2
// Fall 2016


import java.util.ArrayList;
//import java.lang.Math;
/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
*/
public class Polynomial {

    private ArrayList<Term> poly;
    /**
       Creates the 0 polynomial
    */
    public Polynomial() {
	//poly.add(new Term()); // make a 0,0 term
	poly = new ArrayList<Term> ();
	assert isValidPolynomial();
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {
	poly = new ArrayList<Term> ();
	if(term.getCoeff() != 0){
	    poly.add(term);
	}
	assert isValidPolynomial();
    }


    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
     */
    public Polynomial add(Polynomial b) {
	int exp1;
	int exp2;
	double coeff1;
	double coeff2;
	// start merge
	int ind1 = 0;
	int ind2 = 0;
	Term term1;
	Term term2;
	ArrayList<Term> temp = new ArrayList<Term> ();
	while(ind1 < poly.size() && ind2 < b.poly.size()){
	    term1 = poly.get(ind1);
	    term2 = b.poly.get(ind2);
	    exp1 = term1.getExpon();
	    exp2 = term2.getExpon();
	    coeff1 = term1.getCoeff();
	    coeff2 = term2.getCoeff();
	    
	    // add the larger one into the array
	    if(exp1 > exp2) {
		if(coeff1 != 0){
		    temp.add(term1/*new Term(coeff1, exp1)*/);
		}
		ind1++;

	    }
	    else if(exp1 == exp2) {
		if(coeff1 + coeff2 != 0) {
		    temp.add(new Term(coeff1 + coeff2, exp1));
		}
		ind1++;
		ind2++;

	    }
	    else{
		if(coeff2 != 0){
		    temp.add(term2/*new Term(coeff2, exp2)*/);
		}
		ind2++;
	    }
	}

	// add the rest into the list
	//continue from ind1
	while(ind1 < poly.size()){
	    term1 = poly.get(ind1);
	    if(term1.getCoeff() != 0){
		temp.add(term1/*new Term(term1.getCoeff(), term1.getExpon())*/);}
	    ind1++;
	}
 
	while(ind2 < b.poly.size()) {
	    term2 = b.poly.get(ind2);
	    if(term2.getCoeff() != 0){
		temp.add(term2/*new Term(term2.getCoeff(), term2.getExpon())*/);}
	    ind2++;
	}
       

	Polynomial result = new Polynomial();
	result.poly = temp;

	assert isValidPolynomial();
	assert b.isValidPolynomial();
	assert result.isValidPolynomial();

	return result;  // dummy code.  just to get stub to compile
    }


    /**
       Returns the value of the poly at a given value of x.
     */
    public double eval(double x) {
	double result = 0;
	for(Term term : poly) {
	    result += term.getCoeff() * Math.pow(x, term.getExpon());
	}

	assert isValidPolynomial();

	return result;
    }


    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
    */
    public String toFormattedString() {
	if(poly.size() == 0){
	    return "0.0";
	}
	else {
	    String result = "";
	    for(Term term : poly){
		double coeff = term.getCoeff();
		int exp = term.getExpon();
		if(exp == 0) {

		    result += coeff;
		}
		else if(exp == 1){
		    if(coeff != 1 && coeff != -1){
			result += coeff + "x";
		    }
		    else {
			if(coeff == 1){
			    result += "x";
			}
			else {
			    result += "-x";
			}
		    }
		}
		else{
		    if(coeff != 1 && coeff != -1){
			result += coeff + "x^" + exp;
		    }
		    else{
			if(coeff == 1){
			    result += "x^" + exp;
			}
			else {
			    result += "-x^" + exp;
			}
		    }
		}
		
		// add space and + as long as not the last one
		if(term != poly.get(poly.size() - 1)){
		    result += " + ";
		}
	  
	    }

	    assert isValidPolynomial();

	    return result;

	}
    }


    // **************************************************************
    //  PRIVATE METHOD(S)

    /**
       Returns true iff the poly data is in a valid state.

       
    */
    private boolean isValidPolynomial() {
	// if zero, then true
	if(poly.size() == 0){
	    return true;
	}
	int exp = poly.get(0).getExpon();
	double coeff = poly.get(0).getCoeff();
	int previousExp = exp;
	// if coeff is 0 or exp less than 0, then invalid
	if(coeff == 0 || exp < 0) {
	    return false;
	}
	int ind = 0;
	for(Term term : poly){
	    if(ind != 0) {
		// skip the first one
	    
		exp = term.getExpon();
		coeff = term.getCoeff();
		// if coeff is 0, exp less than 0 or exp is not from large to small order, then invalid
		if(coeff == 0 || exp < 0 || previousExp <= exp){
		    return false;
		}
		previousExp = exp;
	    }
	    ind++;
	}
	return true;
    }


    // **************************************************************
    //  PRIVATE INSTANCE VARIABLE(S)

    /*
       Representation invariants:
       1. The exp cannot be negative
       2. For a non-zero poly all the terms must be in decreasing order by exponent
       3. There is no repetitive exp terms
       4. 0 term is not allowed in the polynomial object


     */
}
