// Name: Zhaoyang Han
// USC loginid: zhaoyanh
// CS 455 PA3
// Fall 2016

import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;

    public boolean[][] mazeData;
    public MazeCoord startLoc;
    public MazeCoord endLoc;

    private LinkedList<MazeCoord> path = new LinkedList<MazeCoord> ();;

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param endLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc)
   {
       // store the data
       this.mazeData = mazeData;
       this.startLoc = startLoc;
       this.endLoc = endLoc;
   }


   /**
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
       return mazeData.length;
   }

   
   /**
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
       return mazeData[0].length;
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
       int row = loc.getRow();
       int col = loc.getCol();
       
      return mazeData[row][col];
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return startLoc;
   }
   
   
   /**
   Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return endLoc;
   }

   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location.  If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

       return path;

   }


   /**
      Find a path through the maze if there is one.  Client can access the
      path found via getPath method.
      @return whether path was found.
    */
   public boolean search()  {
       path.clear(); // initilize
       if(hasWallAt(startLoc) || hasWallAt(endLoc)){
	   // if start or end point is a wall, then no path
	   return false;
       }

       int rows = numRows();
       int cols = numCols();
       int[][] visit = new int[rows][cols]; // visit array

       boolean found = searchHelper(visit, startLoc, rows, cols);

       //insert the start and end point to the path
       if(found){
	   path.addFirst(startLoc);
	   path.addLast(endLoc);
       }

       return found;
   }

    private boolean searchHelper(int[][] visit, MazeCoord unit, int rows, int cols){
	int row = unit.getRow();
	int col = unit.getCol();
	if(hasWallAt(unit)){
	    // this location is a wall
	    return false;
	}
	if(visit[row][col] == 1){
	    // this location is visited
	    return false;
	}
	if(unit.equals(endLoc)){
	    // destination found
	    return true;
	}

	visit[row][col] = 1; // mark as vistied
	
	if(endLoc.getCol() > startLoc.getCol() || endLoc.getRow() > startLoc.getRow()){ 
	    // choose the better order to make the recursive search, check all the adjacent units
	    if(row < rows - 1 && searchHelper(visit, new MazeCoord(row + 1, col), rows, cols) ||
	       col < cols - 1 && searchHelper(visit, new MazeCoord(row, col + 1), rows, cols) ||
	       row > 0 && searchHelper(visit, new MazeCoord(row - 1, col), rows, cols) ||
	       col > 0 && searchHelper(visit, new MazeCoord(row, col - 1), rows, cols))
	       
		{
		    
		    path.addFirst(unit);
		    return true;
		}
	}
	else{
	    // same as above, just changed search order
	    if(row > 0 && searchHelper(visit, new MazeCoord(row - 1, col), rows, cols) ||
	       col > 0 && searchHelper(visit, new MazeCoord(row, col - 1), rows, cols) ||
	       row < rows - 1 && searchHelper(visit, new MazeCoord(row + 1, col), rows, cols) ||
	       col < cols - 1 && searchHelper(visit, new MazeCoord(row, col + 1), rows, cols))
		{
		    
		    path.addFirst(unit);
		    return true;
		}
	}

	return false;
    }

}
