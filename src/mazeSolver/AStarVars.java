package mazeSolver;

/**
 * 
 * @author Tom Zhang
 * 
 * The AStarVars object contains all the fields that are specific
 * to the AStarAlgorithm class. This object is meant to be hidden
 * from the user and only used internally within the MazeSolver API. 
 *
 */
public class AStarVars {

    private String heuristicType;
    private int ascendScaleFactor;
    private int descendScaleFactor;
    private boolean blackPassable;
    private boolean whitePassable;
    
    /**
     * Constructs a new AStarVars object with
     * the default values.
     */
    protected AStarVars() {
        heuristicType = "manhattan";
        ascendScaleFactor = 0;
        descendScaleFactor = 0;
        blackPassable = true;
        whitePassable = true;
    }
    
    /**
     * Gets the heuristic type.
     * @return heuristicType
     */
    protected String getHeuristicType() {
        return heuristicType;
    }
    
    /**
     * Gets the ascending scale factor.
     * @return ascendScaleFactor
     */
    protected int getAscendScaleFactor() {
        return ascendScaleFactor;
    }
    
    /**
     * Gets the descending scale factor.
     * @return descendScaleFactor
     */
    protected int getDescendScaleFactor() {
        return descendScaleFactor;
    }
    
    /**
     * Returns whether black pixels are passable.
     * @return blackPassable
     */
    protected boolean isBlackPassable() {
        return blackPassable;
    }
    
    /**
     * Returns whether white pixels are passable.
     * @return whitePassable
     */
    protected boolean isWhitePassable() {
        return whitePassable;
    }
    
    /**
     * Sets the heuristic type.
     * @param heuristicType - the heuristic type
     * @throws Exception
     */
    protected void setHeuristicType(String heuristicType) throws Exception {
        if (heuristicType == null || (!heuristicType.equals("manhattan") && 
                !heuristicType.equals("diagonal") && 
                !heuristicType.equals("euclidean"))) {
            System.out.println("Input heuristic is invalid or null.");
            throw new IllegalArgumentException();
        }
        this.heuristicType = heuristicType;
    }
    
    /**
     * Sets the ascending scale factor.
     * @param ascendScaleFactor - the ascending scale factor
     */
    protected void setAscendScaleFactor(int ascendScaleFactor) {
        this.ascendScaleFactor = ascendScaleFactor;
    }
    
    /**
     * Sets the descending scale factor.
     * @param descendScaleFactor - the descending scale factor
     */
    protected void setDescendScaleFactor(int descendScaleFactor) {
        this.descendScaleFactor = descendScaleFactor;
    }
    
    /**
     * Sets whether black pixels are passable.
     * @param blackPassable - boolean that tells whether black are passable
     */
    protected void setBlackPassable(boolean blackPassable) {
        this.blackPassable = blackPassable;
    }
    
    /**
     * Sets whether white pixels are passable.
     * @param whitePassable - boolean that tells whether white pixels are passable
     */
    protected void setWhitePassable(boolean whitePassable) {
        this.whitePassable = whitePassable;
    }
    
}
