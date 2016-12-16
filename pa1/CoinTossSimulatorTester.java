/*
  CoinTossSimulatorTester class
  this class serves to test the CoinTossSimulator class

 */

public class CoinTossSimulatorTester {

    public static void main(String[] arg) {
	int ind = 0;
	CoinTossSimulator toss = new CoinTossSimulator();
	int trials = 0;
	int head = 0;
	int tail = 0;
	int ht = 0;
	boolean correct = false;
	for(ind = 0; ind < 1000; ind++){
	    toss.run(ind);
	    trials += ind;
	    head = toss.getTwoHeads();
	    tail = toss.getTwoTails();
	    ht = toss.getHeadTails();
	    System.out.println("Number of trials: " + toss.getNumTrials());
	    System.out.println("Two-head tosses: " + head);
	    System.out.println("Two-tail tosses: " + tail);
	    System.out.println("One-head one-tail tosses: " + ht);
	    if(trials == toss.getNumTrials() && (trials == ht + tail + head)){
		correct = true;
	    }
	    else{
		correct = false;
	    }
	    System.out.println("Tosses add up correctly? " + correct);
	}
	toss.reset(); // test reset
	head = toss.getTwoHeads();
	tail = toss.getTwoTails();
	ht = toss.getHeadTails();
	trials = toss.getNumTrials();
	if((head + tail + ht + trials) == 0){
	    correct = true;
	}
	else {
	    correct = false;
	}
	System.out.println("Number of trials: " + trials);
	System.out.println("Two-head tosses: " + head);
	System.out.println("Two-tail tosses: " + tail);
	System.out.println("One-head one-tail tosses: " + ht);
	System.out.println("Tosses add up correctly? " + correct);

	// test after reset
	toss.run(10000);
	head = toss.getTwoHeads();
	tail = toss.getTwoTails();
	ht = toss.getHeadTails();
	trials = toss.getNumTrials();
	if((head + tail + ht) == trials){
	    correct = true;
	}
	else {
	    correct = false;
	}
	System.out.println("Number of trials: " + trials);
	System.out.println("Two-head tosses: " + head);
	System.out.println("Two-tail tosses: " + tail);
	System.out.println("One-head one-tail tosses: " + ht);
	System.out.println("Tosses add up correctly? " + correct);
	
    }
}