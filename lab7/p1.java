import java.util.*;

public class p1{
    public static void main(String[] args){
	String words = "What is the longest word";
	Scanner in = new Scanner(words);
	ArrayList<String> input = new ArrayList<String>();
	while(in.hasNext()){
	    input.add(in.next());
	}
	/*
	for(int i=0; i < input.size(); i++){
	    System.out.println(input.get(i));
	    }*/
	int max = 0;
	System.out.println("Len is " + process(input, max));
    }


    public static int process(ArrayList<String> input, int max){
	if(input.size() == 0){
	    return max;
	}
	for(int i=0; i < input.size(); i++){
	    System.out.println(input.get(i));
	}

	int len = input.get(0).length();
	max = len > max ? len : max;
	System.out.println(max);
	System.out.println("-----");
	input.remove(0);
	max = process(input, max);
  
	return max;
    }
}