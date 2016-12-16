import java.util.Scanner;
import javax.swing.JFrame;


/*
  CoinSimViewer class
  A class that contains main method, responsible for user prompting, creating and setting JFrame, creating component.

 */
public class CoinSimViewer {
    public static void main(String[] arg){
	Scanner in = new Scanner(System.in);
	int trails = 0;
	while (trails <= 0) {
	    System.out.print("Enter number of trails: ");
	    trails = in.nextInt();
	    if(trails <= 0) {
		System.out.println("ERROR: Number entered must be greater than 0.");
	    }
	}
	JFrame frame = new JFrame();
	frame.setSize(650, 550);
	frame.setTitle("CoinSim");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the program when closing the frame view

	CoinSimComponent component = new CoinSimComponent(trails);
	component.tossCoin(); // call the component let the coin simulator run

	frame.add(component);
	frame.setVisible(true);
    }




}