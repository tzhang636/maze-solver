package mazeSolver;

import java.util.Comparator;

/**
 * 
 * @author Tom Zhang
 * The PQComparator class is used to arrange the AStarSquares within the priority queue
 * (the open list) inside the AStarAlgorithm class. This class arranges the 
 * AStarSquares based on their F (heuristic) values. The AStarSquare with the least 
 * F value will be the top element inside the priority queue.
 */
public class PQComparator implements Comparator<Object> {

    /**
     * Compares two (AStarSquare) objects and returns a 
     * positive integer if the first is greater than the second,
     * a negative integer if the first is less than the second,
     * and zero if they are equal.
     */
    public int compare(Object o1, Object o2) {
        
        double o1F = ((AStarSquare) o1).getF();
        double o2F = ((AStarSquare) o2).getF();

        if (o1F > o2F)
            return 1;
        else if (o1F < o2F)
            return -1;
        else
            return 0;
    }
}
