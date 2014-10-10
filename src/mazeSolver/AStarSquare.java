package mazeSolver;

import java.awt.Point;

/**
 * 
 * @author Tom Zhang
 * The AStarSquare class represents a pixel in the maze image. Each AStarSquare holds
 * a movement cost (G), which is the cost from the start pixel to itself, a 
 * heuristic cost (F), which provides a rough estimate of how far the pixel
 * is from the end pixel, and a total cost (H), which is the sum of G and F. 
 * Each AStarSquare also contains a point (xyPt), which represents the pixel 
 * coordinates on the image and its parent AStarSquare. Note that the AStarSquare class
 * only represents pixels in the maze image when the ASTAR algorithm is used.
 * Other algorithms may require a squares with new variables.
 */
public class AStarSquare {

    private double F;
    private double G;
    private double H;
    private Point xyPt;
    private AStarSquare parent;

    /**
     * Initializes a AStarSquare upon construction.
     * @param xyPt - the Point representing the pixel coordinates in the maze image
     */
    protected AStarSquare(Point xyPt) {
        this.F = 0;
        this.G = 0;
        this.H = 0;
        this.xyPt = new Point(xyPt);
        this.parent = null;
    }
    
    /**
     * Gets F.
     * @return F
     */
    protected double getF() {
        return F;
    }

    /**
     * Sets F.
     * @param F
     */
    protected void setF(double F) {
        this.F = F;
    }

    /**
     * Gets G.
     * @return G
     */
    protected double getG() {
        return G;
    }

    /**
     * Sets G.
     * @param G
     */
    protected void setG(double G) {
        this.G = G;
    }

    /**
     * Gets H.
     * @return H
     */
    protected double getH() {
        return H;
    }

    /**
     * Sets H.
     * @param H
     */
    protected void setH(double H) {
        this.H = H;
    }

    /**
     * Gets the point representing the pixel coordinates of the AStarSquare.
     * @return the point representing the pixel coordinates of the AStarSquare
     */
    protected Point getXyPt() {
        return xyPt;
    }

    /**
     * Sets the point representing the pixel coordinates of the AStarSquare.
     * @param xyPt - the point representing the pixel coordinates of the AStarSquare
     */
    protected void setXyPt(Point xyPt) {
        this.xyPt = xyPt;
    }

    /**
     * Gets the AStarSquare's parent.
     * @return an AStarSquare that represents the parent of the AStarSquare
     */
    protected AStarSquare getParent() {
        return parent;
    }

    /**
     * Sets the AStarSquare's parent.
     * @param parent - an AStarSquare that represents the parent of the AStarSquare
     */
    protected void setParent(AStarSquare parent) {
        this.parent = parent;
    }
    
    /**
     * Overrides the hashCode function inside the Object class.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((xyPt == null) ? 0 : xyPt.hashCode());
        return result;
    }

    /**
     * Overrides the equals function inside the Object class.
     * Necessary to check whether the open list contains a AStarSquare or not,
     * and also finds a AStarSquare in the open list that needs to be removed.
     * Note that equals only checks whether the xyPt is the same or not
     * between two AStarSquare objects.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AStarSquare other = (AStarSquare) obj;
        if (xyPt == null) {
            if (other.xyPt != null)
                return false;
        } else if (!xyPt.equals(other.xyPt))
            return false;
        return true;
    }
}
