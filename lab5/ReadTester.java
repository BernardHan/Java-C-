//import java.util.Scanner;
//import java.lang.String;
import java.util.*;

public class ReadTester {
    public static void main(String[] args) {
	while(true) {
	    Scanner in = new Scanner(System.in);
	    System.out.print("Enter a space separated list of numbers: ");
	    String list = in.nextLine();
	    list = list.trim();
	    String[] integers = list.split(" ");
	    System.out.println(Arrays.toString(integers));
	    /*
	    for(String inte: integers) {
		System.out.println(inte);

		}*/
	    int[] result = new int[list.length()];
	    String output = "The numbers were: [";
	    for (int ind = 0; ind < integers.length; ind++) {
		integers[ind] = integers[ind].replaceAll("\\s+","");
		if(integers[ind] != " " && integers[ind] != ""){
		    //result[ind] = Integer.parseInt(integers[ind]);
		
		    output += integers[ind] + ", ";
		}
		
	    }
	    if(integers.length != 0){
		output = output.substring(0, output.length()-2);
	   
	    }
	    output += "]";
	    System.out.println(output);
	}
    }
}