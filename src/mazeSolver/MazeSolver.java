package mazeSolver;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import mazeSolver.Algorithm;

/**
 * 
 * @author Tom Zhang
 * 
 * The MazeSolver class provides the user interface to the library. The only
 * function that the user will call in MazeSolver is the solveMaze() function. 
 * That function, in turn, will be responsible for all error checking for the 
 * parameter inputs, and also for calling all helper functions that will 
 * solve the maze, including the read/write and algorithm functions. Note that
 * solveMaze() will be calling functions from outside the class. 
 */
public class MazeSolver {
    
	private static int threshold = 256;
    private String algorithmType;
    private Algorithm algorithm;
    private AStarVars aStarVars;
	
    /**
     * Constructs a default MazeSolver object.
     */
    public MazeSolver() {
        algorithmType = "astar";
        algorithm = null;
        aStarVars = new AStarVars();
    }
    
    /**
     * Responsible for all error checking on inputs, reading/writing the 
     * maze image and also solving the maze.
     * @param readFile - the String name of the file to read from
     * @param writeFile - the String name of the file to write from
     * @param startPt - the Point representing the start point
     * @param endPt - the Point representing the end point
     * @param color - the Color of the line that is to be drawn on the maze
     * @return a boolean that tells whether the maze is solvable or not
     * @throws Exception
     */
    public boolean solveMaze(String readFile, String writeFile, Point startPt, Point endPt, Color color) throws Exception {

        BufferedImage mazeImage = ImageReadWrite.Read(readFile);
        
        if (readFile == null || writeFile == null || startPt == null || endPt == null) {
            System.out.println("One or more input parameters are null.");
            throw new IllegalArgumentException();
        }
        
        if (!areStartEndPtsValid(mazeImage, startPt, endPt)) {
            System.out.println("Input start and/or end coordinates are invalid.");
            throw new IllegalArgumentException();
        }

        if (algorithmType.equals("astar")) {
            if (algorithm == null) {
                algorithm = new AStarAlgorithm(aStarVars);
            }
            setAlgorithmFields(mazeImage, startPt, endPt);
        }
        
        // TODO
        // NOTE: plug-in different algorithms here using else if statements, if
        // necessary

        AStarSquare currSq = algorithm.search();

        // we didn't find a path, return
        if (currSq == null) {
            System.out.println("No solution path found for input file.");
            return false;
        }

        // otherwise, trace back from currSq and draw the path onto the maze image
        drawPath(mazeImage, currSq, color);

        // writes the image into an output file specified by writeFile
        ImageReadWrite.Write(writeFile, mazeImage);
        
        return true;
    }
    
    /**
     * Gets the threshold value.
     * @return threshold
     */
    public static int getThreshold() {
    	return threshold; 
    }
    
    /**
     * Gets the algorithm type.
     * @return the algorithm type
     */
    protected String getAlgorithmType() {
		return algorithmType;
	}

	/**
     * Sets the threshold value for what is deemed walkable/unwalkable.
     * @param threshold - the threshold value to be set
     */
    public static void setThreshold(int threshold) throws Exception {
    	if (threshold < 0) {
    		System.out.println("Input threshold is invalid.");
    		throw new IllegalArgumentException();
    	}
    	MazeSolver.threshold = threshold;
    }
    
    /**
     * Sets the algorithm type.
     * Throws an exception if algorithm type is null or invalid.
     * @param algorithmType - a String representing the algorithm type
     * @throws Exception
     */
    public void setAlgorithmType(String algorithmType) throws Exception {
        if (algorithmType == null || !algorithmType.equals("astar")) {  
            System.out.println("Input algorithm type is invalid or null.");
            throw new IllegalArgumentException();
        }
        this.algorithmType = algorithmType;
    }
    
    /**
     * Sets the ascendScaleFactor inside aStarVars.
     * Note MazeSolver and AStarAlgorithm share the same aStarVars object.
     * @param aStarVars - the ascending scale factor
     */
    public void setAscendScaleFactor(int ascendScaleFactor) {
        this.aStarVars.setAscendScaleFactor(ascendScaleFactor);
    }
    
    /**
     * Sets the descendScaleFactor inside aStarVars.
     * Note MazeSolver and AStarAlgorithm share the same aStarVars object.
     * @param descendScaleFactor - the descending scale factor
     */
    public void setDescendScaleFactor(int descendScaleFactor) {
        this.aStarVars.setDescendScaleFactor(descendScaleFactor);
    }
    
    /**
     * Sets the heuristicType inside aStarVars.
     * Note MazeSolver and AStarAlgorithm share the same aStarVars object.
     * @param heuristicType - the heuristic type
     * @throws Exception
     */
    public void setHeuristicType(String heuristicType) throws Exception {
        this.aStarVars.setHeuristicType(heuristicType);
    }
    
    /**
     * Sets the blackPassable field inside aStarVars.
     * Note MazeSolver and AStarAlgorithm share the same aStarVars object.
     * @param blackPassable - boolean to be set
     */
    public void setBlackPassable(boolean blackPassable) {
        this.aStarVars.setBlackPassable(blackPassable);
    }
    
    /**
     * Sets the whitePassable field inside aStarVars.
     * Note MazeSolver and AStarAlgorithm share the same aStarVars object.
     * @param whitePassable - boolean to be set
     */
    public void setWhitePassable(boolean whitePassable) {
        this.aStarVars.setWhitePassable(whitePassable);
    }

    /**
     * Sets all the fields inside Algorithm.
     * @param mazeImage - the maze image
     * @param startPt - the start point
     * @param endPt - the end point
     */
    private void setAlgorithmFields(BufferedImage mazeImage, Point startPt, Point endPt) {
        algorithm.setMazeImage(mazeImage);
        algorithm.setStartPt(startPt);
        algorithm.setEndPt(endPt);
    }
    
    /**
     * Checks to see if the start and end points are valid.
     * The points are valid if both points are inside the maze and at a walkable pixel.
     * @param mazeImage - the BufferedImage of the maze
     * @param startPt - the start point
     * @param endPt - the end point
     * @return a boolean that returns true if valid, false it not
     */
    private boolean areStartEndPtsValid(BufferedImage mazeImage, Point startPt, Point endPt) {
    	return inBounds(mazeImage, startPt) && 
               inBounds(mazeImage, endPt) && 
               isWalkable(mazeImage, startPt) && 
               isWalkable(mazeImage, endPt);
    }
    
    /**
     * Checks to see if the given point is within the bounds of the image.
     * @param mazeImage - the BufferedImage of the maze
     * @param pt - the Point to check
     * @return A boolean that returns true if inBounds, false if not
     */
    private boolean inBounds(BufferedImage mazeImage, Point pt) {
        return pt.x >= 0 && pt.y >= 0 && pt.x < mazeImage.getWidth() && pt.y < mazeImage.getHeight();
    }
    
    /**
     * Checks to see if the given point is walkable.
     * Decision is based on BlackPassable and WhitePassable.
     * @param mazeImage - the BufferedImage of the maze
     * @param pt - the Point to check
     * @return a boolean that returns true if walkable, false if not
     */
    private boolean isWalkable(BufferedImage mazeImage, Point pt) {
        Color ptColor = new Color(mazeImage.getRGB(pt.x, pt.y));
        if (!aStarVars.isBlackPassable() && ptColor.getRed() == 0)
            return false;
        if (!aStarVars.isWhitePassable() && ptColor.getRed() == 255)
            return false;
        if (ptColor.getRed() >= threshold)
        	return false;	
        return true;
    }
    
    /**
     * Draws the path onto a BufferedImage by tracing back from the end AStarSquare
     * @param image - the BufferedImage on which to draw the path
     * @param currSq - the end AStarSquare
     */
    private void drawPath(BufferedImage mazeImage, AStarSquare currSq, Color color) {
        while (currSq != null) {
            mazeImage.setRGB(currSq.getXyPt().x, currSq.getXyPt().y, color.getRGB());
            currSq = currSq.getParent();
        }
    }

}
