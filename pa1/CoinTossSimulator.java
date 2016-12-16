import java.util.Random;
/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {

    private int trials; //number of trails user indicates
    private int head; //two headed tosses
    private int tail; //two tailed tosses
    private int ht; //one headed and one tailed tosses
   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
       trials = 0;
       head = 0;
       tail = 0;
       ht = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this
      without a reset() between them add these trials to the simulation
      already completed.
      
      @param numTrials  number of trials to for simulation; must be >= 0
    */
   public void run(int numTrials) {
       trials += numTrials;
       Random simulator = new Random();
       int value = -1;
       for (int ind = 0; ind < numTrials; ind++) {
	   value = simulator.nextInt(4); //value will be generated from 0-3, 0 means two head, 3 means two tail, and 1&2 means one head one tail
	   switch(value) {
	   case 0:
	       head++;
	       break;
	   case 3:
	       tail++;
	       break;
	   default:
	       ht++;
	       break;
	   }
       }
       
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return trials;
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return head;
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return tail;
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return ht;
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
       trials = 0;
       head = 0;
       tail = 0;
       ht = 0;
   }

}
