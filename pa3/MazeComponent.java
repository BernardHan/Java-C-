// Name: Zhaoyang Han
// USC loginid: zhaoyanh
// CS 455 PA3
// Fall 2016

import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;
/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
       this.maze = maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
       Graphics2D g2 = (Graphics2D) g;
       Rectangle unit; //one block is one unit
       int x = START_X; //the coordinates of the unit, will change with position
       int y = START_Y;

       int rows = maze.numRows();
       int cols = maze.numCols();

       g2.setColor(Color.black);
       g2.drawRect(START_X, START_Y, BOX_WIDTH * cols, BOX_HEIGHT * rows);
       g2.drawRect(START_X-1, START_Y, BOX_WIDTH * cols, BOX_HEIGHT * rows);
       g2.drawRect(START_X+1, START_Y, BOX_WIDTH * cols, BOX_HEIGHT * rows);
       g2.drawRect(START_X, START_Y-1, BOX_WIDTH * cols, BOX_HEIGHT * rows);
       g2.drawRect(START_X, START_Y+1, BOX_WIDTH * cols, BOX_HEIGHT * rows);

       for(int row = 0; row < rows; row++){
	   for(int col = 0; col < cols; col++){
	       if(maze.mazeData[row][col] == true){
		   // if this unit is a wall
		   g2.setColor(Color.black);
	       }
	       else{
		   // if this unit is not wall
		   g2.setColor(Color.white);
	       }

	       unit = new Rectangle(x, y, BOX_WIDTH, BOX_HEIGHT);
	       g2.fill(unit);
	       x += BOX_WIDTH; // update horizontally
	   }
	   x = START_X; // initial x, and update vertically
	   y += BOX_HEIGHT;
       }
       
       // fill in the entry and exit
       int start_row = maze.startLoc.getRow();
       int start_col = maze.startLoc.getCol();
       x = START_X + INSET + BOX_WIDTH * start_col; // the coordinates of start point
       y = START_Y + INSET + BOX_HEIGHT * start_row;

       g2.setColor(Color.yellow);
       unit = new Rectangle(x, y, BOX_WIDTH - 2 * INSET, BOX_HEIGHT - 2 * INSET);
       g2.fill(unit);

       int exit_row = maze.endLoc.getRow();
       int exit_col = maze.endLoc.getCol();

       x = START_X + INSET + BOX_WIDTH * exit_col; // the coordinates of end point
       y = START_Y + INSET + BOX_HEIGHT * exit_row;

       g2.setColor(Color.green);
       unit = new Rectangle(x, y, BOX_WIDTH - 2 * INSET, BOX_HEIGHT - 2 * INSET);
       g2.fill(unit);


       // test if path found, if found, draw the path
       if(maze.getPath().size() != 0){
	   drawPath(g2);
       }
   }


    /*
      This private method will be called when there is a path existed


     */
    private void drawPath(Graphics2D g2){
	g2.setColor(Color.blue);
	LinkedList<MazeCoord> path = maze.getPath();
	ListIterator<MazeCoord> iter = path.listIterator();
	
	MazeCoord unit = iter.next(); // starter
	int row_prev = unit.getRow(); //set up two coordinates for the line
	int col_prev = unit.getCol();
	int row_curr;
	int col_curr;
	
	while(iter.hasNext()){
	    unit = iter.next();
	    row_curr = unit.getRow();
	    col_curr = unit.getCol();
	    int x1, y1, x2, y2;
	  
	    // calculating the start and end for this line
	    x1 = START_X + BOX_WIDTH * col_prev + BOX_WIDTH / 2;
	    y1 = START_Y + BOX_HEIGHT * row_prev + BOX_HEIGHT / 2;
	    x2 = START_X + BOX_WIDTH * col_curr + BOX_WIDTH / 2;
	    y2 = START_Y + BOX_HEIGHT * row_curr + BOX_HEIGHT / 2;
	    
	    g2.drawLine(x1, y1, x2, y2);

	    // update the start point
	    row_prev = row_curr;
	    col_prev = col_curr;
	}
    }
}



