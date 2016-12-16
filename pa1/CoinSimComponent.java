import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Color;

/*
  CoinSimComponent class
  This class inherits from JComponent class for overriding the paintComponent method.
  It is responsible for running the coin simulator, and containing the paintComponent method, which gets called when first draw frame and windows size changes.

 */


public class CoinSimComponent extends JComponent {
    private int trails;
    private String headString;
    private String tailString;
    private String htString;
    private int head;
    private int tail;
    private int ht;

    // CONSTANTS
    private static final double LONGEST = 0.85; // the propotion of the longest bar in the frame
    private static final Color RED = Color.red;
    private static final Color BLUE = Color.blue;
    private static final Color GREEN = Color.green;
    private static final int BAR_WIDTH_DIVIDER = 10; // the bar width constant

    // constructor, receiving the number of trails from CoinSimViewer
    public CoinSimComponent(int num) {
	trails = num;
    }

    // tossCoin method run the CoinTossSumulator program, and store the two heads, two tails, one head & one tail data, and creating the label for each bar
    public void tossCoin(){
	CoinTossSimulator toss = new CoinTossSimulator();
	toss.run(trails);
	head = toss.getTwoHeads();
	tail = toss.getTwoTails();
	ht = toss.getHeadTails();
	
	headString = "Two Heads: " + head + " (" + (head * 100) / trails + "%)";
	tailString = "Two Tails: " + tail + " (" + (tail * 100) / trails + "%)";
	htString = "A Head and a Tail: " + ht + " (" + (ht * 100) / trails + "%)";
    }

    // paintComponent method, it gets called when windows size changes, and hence generates corresponding height for each bar, make sure each bar and each label on the right place
    public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g;
	
	int frameWidth = this.getWidth(); // get current windows width
	int frameHeight = this.getHeight(); // get current windows height

	
	
	int headHeight = (int)(frameHeight * LONGEST * (float)head / trails); //heights for each data bar
	int tailHeight = (int)(frameHeight * LONGEST * (float)tail / trails);
	int htHeight = (int)(frameHeight * LONGEST * (float)ht / trails);
	

	/*
	System.out.println(headHeight);
	System.out.println(htHeight);
	System.out.println(tailHeight);
	*/

	int barWidth= frameWidth / BAR_WIDTH_DIVIDER; // unlike the assignment description, I choose to let the width change according to the windows size changing
	int space = (frameWidth - 3 * barWidth) / 4; // the space between each bar
	int headX = space; // the left position of each bar
	int htX = headX + barWidth + space;
	int tailX = htX + barWidth + space;

	int bottom = (int)(frameHeight * 0.9); // the relative position of the bottom left of the string

	Bar headBar = new Bar(bottom, headX, barWidth, headHeight, 2.0, RED, headString);
	Bar htBar = new Bar(bottom, htX, barWidth, htHeight, 2.0, GREEN, htString);
	Bar tailBar = new Bar(bottom, tailX, barWidth, tailHeight, 2.0, BLUE, tailString);

	headBar.draw(g2);
	tailBar.draw(g2);
	htBar.draw(g2);
    }
}