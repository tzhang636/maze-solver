package mazeSolver;

import java.awt.Color;
import java.awt.Point;
import java.util.Hashtable;
import java.util.PriorityQueue;

/**
 * 
 * @author Tom Zhang
 *
 * AStarAlgorithm is a derived class of Algorithm. The class represents one of the
 * pluggable algorithms that can be used to solve a particular maze. The class 
 * implements the abstract search function from the base class, and uses many
 * helper functions inside that function to find the search path. In addition,
 * this class uses a PriorityQueue of AStarSquares as its openList and a Hashtable 
 * of Points as key and AStarSquares as value as its closedList.
 */
public class AStarAlgorithm extends Algorithm {

    AStarVars aStarVars;
    private PriorityQueue<AStarSquare> openList;
    private Hashtable<Point, AStarSquare> closedList;

    /**
     * Initializes the member variables inside AStarAlgorithm.
     * Calls the base constructor first.
     * @param mazeImage - the BufferedImage representing the maze image
     * @param startPt - the Point representing the start point
     * @param endPt - the Point representing the end point
     * @param aStarVars - the AStarVars object containing relevant fields for AStarAlgorithm
     */
    protected AStarAlgorithm(AStarVars aStarVars) {
        this.aStarVars = aStarVars;
        this.openList = new PriorityQueue<AStarSquare>(1, new PQComparator());
        this.closedList = new Hashtable<Point, AStarSquare>(1);
    }
    
    /**
     * Implements the abstract search function inside Algorithm.
     */
    protected AStarSquare search() {

        // adds the start AStarSquare to the open list
        openList.add(new AStarSquare(getStartPt()));
        
        while (true) {
            
            // removes the AStarSquare with the smallest F from the open list
            AStarSquare currSq = openList.poll();

            // if the open list is empty, return (no path)
            if (currSq == null) {
                clearLists();
                return null;
            }
            
            // if we found our end AStarSquare, return (found path)
            if (currSq.getXyPt().equals(getEndPt())) {
            	clearLists();
                return currSq;
            }

            // puts currentAStarSquare's xyPt (key) and currentAStarSquare (value) into the closed list
            closedList.put(currSq.getXyPt(), currSq);

            // create and initialize an array of squares to pass into findValidAdjacentAStarSquares
            AStarSquare[] validAdjSqs = new AStarSquare[8];
            for (int i = 0; i < 8; ++i)
                validAdjSqs[i] = null;

            // find valid adjacent squares
            findValidAdjSqs(currSq, validAdjSqs);

            // do work on the valid adjacent squares
            modifyValidAdjSqs(currSq, validAdjSqs);
        }
    }

    /**
     * Clears both the open and closed lists.
     */
    private void clearLists() {
    	openList.clear();
    	closedList.clear();
    }
    
    /**
     * Finds the valid adjacent squares of a particular AStarSquare.
     * @param currSq - the AStarSquare representing the current AStarSquare
     * @param validAdjSqs - the AStarSquare array that will hold all the valid adjacent squares
     */
    private void findValidAdjSqs(AStarSquare currSq, AStarSquare[] validAdjSqs) {
        int i = 0;
        for (int offsetX = -1; offsetX <= 1; ++offsetX) {
            for (int offsetY = -1; offsetY <= 1; ++offsetY) {
                if (offsetX != 0 || offsetY != 0) {
                   validAdjSqs[i] = findValidAdjSqsHelper(currSq, offsetX, offsetY);
                   ++i;
                }
            }
        }
    }
    
    /**
     * Helper function called by findValidAdjSqs that determines if an adjacent AStarSquare
     * of the current AStarSquare is valid or not.
     * @param currSq - the AStarSquare representing the current AStarSquare
     * @param offsetX - the offset of the adjacent AStarSquare in the x direction
     * @param offsetY - the offset of the adjacent AStarSquare in the y direction
     * @return an AStarSquare representing the adjacent AStarSquare, if valid, and null, if not valid
     */
    private AStarSquare findValidAdjSqsHelper(AStarSquare currSq, int offsetX, int offsetY) {
        int mazeImageWidth = getMazeImage().getWidth();
        int mazeImageHeight = getMazeImage().getHeight();
        boolean inBounds = true;
        
        if (offsetX == -1)
            inBounds = currSq.getXyPt().x + offsetX >= 0; 
        else if (offsetX == 1)
            inBounds = currSq.getXyPt().x + offsetX < mazeImageWidth;
        if (offsetY == -1)
            inBounds = inBounds && currSq.getXyPt().y + offsetY >= 0;
        else if (offsetY == 1)
            inBounds = inBounds && currSq.getXyPt().y + offsetY < mazeImageHeight;
        
        // if the adjacent AStarSquare is not in bounds, then return null
        if (!inBounds)
            return null;
        
        int currSqX = currSq.getXyPt().x;
        int currSqY = currSq.getXyPt().y;
        Point adjPt = new Point(currSqX + offsetX, currSqY + offsetY);
        Color adjSqColor = new Color(getMazeImage().getRGB(adjPt.x, adjPt.y));
                
        // if either black and/or white not passable and we find a square of that color, return null
        if (!aStarVars.isBlackPassable() && adjSqColor.getRed() == 0)
                return null;
        if (!aStarVars.isWhitePassable() && adjSqColor.getRed() == 255)
                return null;
        
        // if the color of the adjacent square is >= threshold, then return null
        if (adjSqColor.getRed() >= MazeSolver.getThreshold())
        	return null;
              
        // if the adjacent AStarSquare is on the closed list, return null
        // otherwise, return a new AStarSquare
        AStarSquare onClosedList = closedList.get(adjPt); 
        if (onClosedList == null)
            return new AStarSquare(adjPt);
        return null;
    }

    /**
     * Modifies the array of valid adjacent squares based on the AStar algorithm.
     * @param currSq - the AStarSquare representing the current AStarSquare
     * @param validAdjSqs - the AStarSquare array that will hold all the valid adjacent squares
     */
    private void modifyValidAdjSqs(AStarSquare currSq, AStarSquare[] validAdjSqs) {

        // iterate over the array validAdjSqs
        for (int i = 0; i < 8; ++i) {

            // the adjacent AStarSquare is diagonal if i is 0, 2, 5, or 7
            boolean diagonal = (i == 0 || i == 2 || i == 5 || i == 7);
            
            // if a valid adjacent AStarSquare is found
            if (validAdjSqs[i] != null) {

                // if the valid adjacent AStarSquare is not on the open list
                if (!openList.contains(validAdjSqs[i])) {
                    
                    // set the parent of the valid adjacent AStarSquare to the current AStarSquare
                    validAdjSqs[i].setParent(currSq);
                    
                    // sets the move cost, heuristic cost, and total cost of the valid adjacent AStarSquare
                    setMoveCost(validAdjSqs[i], diagonal);
                    setHeuristicCost(validAdjSqs[i]);
                    setTotalCost(validAdjSqs[i]);

                    // adds the valid adjacent AStarSquare to the open list
                    openList.add(validAdjSqs[i]);             
                }

                // else the valid adjacent AStarSquare is already on the open list
                else {
                    
                    // if the valid adjacent AStarSquare is diagonal to the current AStarSquare
                    // add 141 to the G value of the current AStarSquare
                    // otherwise add 100 to it
                    double thisPathG = diagonal ? currSq.getG() + 141 : currSq.getG() + 100;

                    // if the movement cost of this path to the valid adjacent AStarSquare is less 
                    // than the original G value of the valid adjacent AStarSquare
                    if (thisPathG < validAdjSqs[i].getG()) {
                        
                        // set the parent of the valid adjacent AStarSquare to the current AStarSquare
                        validAdjSqs[i].setParent(currSq);

                        // removes this AStarSquare from the open list for updating
                        openList.remove(validAdjSqs[i]);

                        // resets this AStarSquare's G and F
                        setMoveCost(validAdjSqs[i], diagonal);
                        setTotalCost(validAdjSqs[i]);

                        // adds this AStarSquare back to the open List
                        openList.add(validAdjSqs[i]);
                    }
                }
            }
        }
    }
    
    /**
     * Gets the elevation change in pixel color between two points.
     * @param pt1 - the first Point
     * @param pt2 - the second Point
     * @return the difference between the red values of the two points, since image is grayscale
     */
    private int getElevationChange(Point pt1, Point pt2) {
        Color pt1Color = new Color(getMazeImage().getRGB(pt1.x, pt1.y));
        Color pt2Color = new Color(getMazeImage().getRGB(pt2.x, pt2.y));      
        return pt1Color.getRed() - pt2Color.getRed();
    }

    /**
     * Sets the move cost of a particular AStarSquare.
     * Note that the cost is weighted by a scale factor depending on the elevation change.
     * @param sq - the AStarSquare whose move cost needs to be set
     * @param diagonal - whether it's diagonal to its parent AStarSquare
     */
    private void setMoveCost(AStarSquare sq, boolean diagonal) { 
        int elevationChange = getElevationChange(sq.getXyPt(), sq.getParent().getXyPt());
        int costFactor = (diagonal) ? 141: 100;
        if (elevationChange >= 0)
            sq.setG(sq.getParent().getG() + costFactor + (elevationChange*aStarVars.getAscendScaleFactor()));
        else
            sq.setG(sq.getParent().getG() + costFactor + (Math.abs(elevationChange)*aStarVars.getDescendScaleFactor()));
    }

    /**
     * Sets the heuristic cost of a particular AStarSquare.
     * Heuristic can be manhattan, diagonal, or euclidean.
     * Note that the cost is weighted by a scale factor depending on the elevation change.
     * @param sq - the AStarSquare whose heuristic cost needs to be set
     */
    private void setHeuristicCost(AStarSquare sq) {
        int x_dist = getEndPt().x - sq.getXyPt().x;
        int y_dist = getEndPt().y - sq.getXyPt().y;
        int str_dist = Math.abs(x_dist) + Math.abs(y_dist);
        int diag_dist = Math.min(Math.abs(x_dist), Math.abs(y_dist));     
        int elevationChange = getElevationChange(getEndPt(), sq.getXyPt());
        
        if (aStarVars.getHeuristicType().equals("manhattan")) {
            if (elevationChange >= 0)
                sq.setH(str_dist + (elevationChange*aStarVars.getAscendScaleFactor()));
            else
                sq.setH(str_dist + (Math.abs(elevationChange)*aStarVars.getDescendScaleFactor()));
        }
        else if (aStarVars.getHeuristicType().equals("diagonal")) {
            if (elevationChange >= 0)
                sq.setH((1.41*diag_dist + (str_dist-2*diag_dist)) + (elevationChange*aStarVars.getAscendScaleFactor()));
            else
                sq.setH((1.41*diag_dist + (str_dist-2*diag_dist)) + (Math.abs(elevationChange)*aStarVars.getDescendScaleFactor()));
        }
        else {  // euclidean
            if (elevationChange >= 0)
                sq.setH(Math.sqrt(x_dist*x_dist + y_dist*y_dist) + (elevationChange*aStarVars.getAscendScaleFactor()));
            else
                sq.setH(Math.sqrt(x_dist*x_dist + y_dist*y_dist) + (Math.abs(elevationChange)*aStarVars.getDescendScaleFactor()));
        }       
    }

    /**
     * Sets the total cost of a particular AStarSquare (move cost + heuristic cost).
     * @param sq - the AStarSquare whose total cost needs to be set
     */
    private void setTotalCost(AStarSquare sq) {
        sq.setF(sq.getG() + sq.getH());
    }
}
