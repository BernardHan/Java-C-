public class PolynomialTester {
    public static void main(String[] args){
	Polynomial p1 = new Polynomial(new Term(3,5));
	System.out.println(p1.toFormattedString());

	Polynomial p2 = new Polynomial();
	System.out.println(p2.toFormattedString());

	Polynomial p3 = p1.add(p2);
	System.out.println(p3.toFormattedString());

	System.out.println("------------------");

	Polynomial p4 = new Polynomial(new Term(10, 0));
	System.out.println(p4.toFormattedString());
	Polynomial p5 = p3.add(p4);
	System.out.println(p5.toFormattedString());
	Polynomial p6 = new Polynomial(new Term(-7, 9));
	System.out.println(p6.toFormattedString());
	Polynomial p7 = new Polynomial(new Term(6, 5));
	System.out.println(p7.toFormattedString());
	Polynomial p8 = p5.add(p6);
	System.out.println(p8.toFormattedString());
	p8 = p8.add(p7);
	System.out.println(p8.toFormattedString());
	p1 = p5.add(p8);
	System.out.println(p1.toFormattedString());
	
	double result1 = p1.eval(3);
	System.out.println(result1);

	Polynomial p9 = new Polynomial();
	Polynomial p10 = new Polynomial(new Term(0, 3));
	Polynomial p11 = p10.add(new Polynomial(new Term(0, 7)));
	Polynomial p12 = new Polynomial(new Term(3, 2)).add(new Polynomial(new Term(-3, 2)));
	Polynomial p13 = new Polynomial(new Term(3, 2)).add(new Polynomial(new Term(-5, 2)));
	Polynomial p14 = p13.add(new Polynomial(new Term(2, 2)));

	System.out.println(p9.toFormattedString());
	System.out.println(p10.toFormattedString());
	System.out.println(p11.toFormattedString());
	System.out.println(p12.toFormattedString());
	System.out.println(p13.toFormattedString());
	System.out.println(p14.toFormattedString());
    }
}