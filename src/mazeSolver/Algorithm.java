package mazeSolver;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Tom Zhang
 * 
 * Algorithm is an abstract base class to all the different types 
 * of algorithms used to solve the maze. Algorithm contains a single
 * abstract function, search(), which all the derived classes must
 * implement. Note that derived classes of Algorithm do not have 
 * access to Algorithm's member variables. Instead, they must use
 * the provided protected getters and setters. The Algorithm class
 * is not a part of the user interface and hidden entirely from the user.
 */
public abstract class Algorithm {

    private BufferedImage mazeImage;
    private Point startPt;
    private Point endPt;

    /**
     * Abstract search function which all derived classes of Algorithm must implement.
     * Searches for a path from the start point to the end point.
     * @return the AStarSquare representing the endPt
     * Note: Other algorithms will require a generic square to be returned
     */
    protected abstract AStarSquare search();
    
    /**
     * Gets the maze image.
     * @return a BufferedImage of the maze image
     */
    protected BufferedImage getMazeImage() {
        return mazeImage;
    }
    
    /**
     * Gets the start point.
     * @return a Point representing the start pixel
     */
    protected Point getStartPt() {
        return startPt;
    }

    /**
     * Gets the end point.
     * @return a Point representing the end pixel
     */
    protected Point getEndPt() {
        return endPt;
    }

    /**
     * Sets the maze image.
     * @param mazeImage - a BufferedImage of the maze image
     */
    protected void setMazeImage(BufferedImage mazeImage) {
        this.mazeImage = mazeImage;
    }

    /**
     * Sets the start point.
     * @param startPt - a Point representing the start pixel
     */
    protected void setStartPt(Point startPt) {
        this.startPt = startPt;
    }

    /**
     * Sets the end point.
     * @param endPt - a Point representing the end pixel
     */
    protected void setEndPt(Point endPt) {
        this.endPt = endPt;
    }
    
}
