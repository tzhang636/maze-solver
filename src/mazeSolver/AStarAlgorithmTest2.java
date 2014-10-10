package mazeSolver;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.junit.BeforeClass;
import org.junit.Test;

public class AStarAlgorithmTest2 {

    public static final int ASCENDSCALEFACTOR = 100;
    public static final int DESCENDSCALEFACTOR = 50;
    
    public static BufferedImage mazeImage;
    public static Point startPt;
    public static Point endPt;
    public static Color whiteColor;
    public static Color blackColor;
    public static Color grayColor;
    
    @BeforeClass
    public static void setup() {
        mazeImage = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
        whiteColor = new Color(255, 255, 255);
        blackColor = new Color(0, 0, 0);
        grayColor = new Color(128, 128, 128);
        mazeImage.setRGB(0, 0, blackColor.getRGB());
        mazeImage.setRGB(1, 0, whiteColor.getRGB());
        mazeImage.setRGB(2, 0, whiteColor.getRGB());
        mazeImage.setRGB(0, 1, grayColor.getRGB());
        mazeImage.setRGB(1, 1, whiteColor.getRGB());
        mazeImage.setRGB(2, 1, whiteColor.getRGB());
        mazeImage.setRGB(0, 2, whiteColor.getRGB());
        mazeImage.setRGB(1, 2, blackColor.getRGB());
        mazeImage.setRGB(2, 2, grayColor.getRGB());
        startPt = new Point(0, 0);
        endPt = new Point(2, 2);
    }
    
    private int getElevationChange(Point pt1, Point pt2) {
        Color pt1Color = new Color(mazeImage.getRGB(pt1.x, pt1.y));
        Color pt2Color = new Color(mazeImage.getRGB(pt2.x, pt2.y));      
        return pt1Color.getRed() - pt2Color.getRed();
    }
    
    @Test 
    public void testMoveCost() {
        Algorithm algorithm = new AStarAlgorithm(mazeImage, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, true);
        AStarSquare currSq = algorithm.search();
        int elevationChange;
        boolean diagonal;
        int costFactor;
        
        while (currSq.getParent() != null) {
            
            elevationChange = getElevationChange(currSq.getXyPt(), currSq.getParent().getXyPt());
            System.out.println(elevationChange);
            diagonal = (currSq.getParent().getXyPt().x != currSq.getXyPt().x && currSq.getParent().getXyPt().y != currSq.getXyPt().y);
            
            if (elevationChange >= 0) {
                if (diagonal)
                    costFactor = 141;
                else
                    costFactor = 100;
                System.out.println("Got here!");
                assertTrue(currSq.getG() == (currSq.getParent().getG() + costFactor + (elevationChange*ASCENDSCALEFACTOR)));
                
            }
            else {
                if (diagonal)
                    costFactor = 141;
                else
                    costFactor = 100;
                System.out.println("Got here!");
                assertTrue(currSq.getG() == (currSq.getParent().getG() + costFactor + (Math.abs(elevationChange)*DESCENDSCALEFACTOR)));
            }
            currSq = currSq.getParent();
        }
    }
    
    @Test
    public void testManhattanCost() {
        Algorithm algorithm = new AStarAlgorithm(mazeImage, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, true);
        AStarSquare endSq = algorithm.search();
        AStarSquare currSq = endSq;
        
        int elevationChange;
        
        int x_dist;
        int y_dist;
        int str_dist;
        
        while (currSq.getParent() != null) {
            
            x_dist = endSq.getXyPt().x - currSq.getXyPt().x;
            y_dist = endSq.getXyPt().y - currSq.getXyPt().y;
            str_dist = Math.abs(x_dist) + Math.abs(y_dist);
            
            elevationChange = getElevationChange(endSq.getXyPt(), currSq.getXyPt());

            if (elevationChange >= 0) {  
                assertTrue(currSq.getH() == (str_dist + (elevationChange*ASCENDSCALEFACTOR)));
            }
            else {
                assertTrue(currSq.getH() == (str_dist + (Math.abs(elevationChange)*DESCENDSCALEFACTOR)));
            }
            currSq = currSq.getParent();
        }
    }
    
    @Test
    public void testDiagonalCost() {
        Algorithm algorithm = new AStarAlgorithm(mazeImage, startPt, endPt, 
                "diagonal", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, true);
        AStarSquare endSq = algorithm.search();
        AStarSquare currSq = endSq;
        
        int elevationChange;
        
        int x_dist;
        int y_dist;
        int str_dist;
        int diag_dist;
        
        while (currSq.getParent() != null) {
            
            x_dist = endSq.getXyPt().x - currSq.getXyPt().x;
            y_dist = endSq.getXyPt().y - currSq.getXyPt().y;
            str_dist = Math.abs(x_dist) + Math.abs(y_dist);
            diag_dist = Math.min(Math.abs(x_dist), Math.abs(y_dist));  
            
            elevationChange = getElevationChange(endSq.getXyPt(), currSq.getXyPt());

            if (elevationChange >= 0) {  
                assertTrue(currSq.getH() == (1.41*diag_dist + (str_dist-2*diag_dist)) + (elevationChange*ASCENDSCALEFACTOR));
            }
            else {
                assertTrue(currSq.getH() == (1.41*diag_dist + (str_dist-2*diag_dist)) + (Math.abs(elevationChange)*DESCENDSCALEFACTOR));
            }
            currSq = currSq.getParent();
        }
    }
    
    @Test
    public void testEuclideanCost() {
        Algorithm algorithm = new AStarAlgorithm(mazeImage, startPt, endPt, 
                "euclidean", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, true);
        AStarSquare endSq = algorithm.search();
        AStarSquare currSq = endSq;
        
        int elevationChange;
        
        int x_dist;
        int y_dist;
        
        while (currSq.getParent() != null) {
            
            x_dist = endSq.getXyPt().x - currSq.getXyPt().x;
            y_dist = endSq.getXyPt().y - currSq.getXyPt().y;
            
            elevationChange = getElevationChange(endSq.getXyPt(), currSq.getXyPt());

            if (elevationChange >= 0) {  
                assertTrue(currSq.getH() == (Math.sqrt(x_dist*x_dist + y_dist*y_dist) + (elevationChange*ASCENDSCALEFACTOR)));
            }
            else {
                assertTrue(currSq.getH() == (Math.sqrt(x_dist*x_dist + y_dist*y_dist) + (Math.abs(elevationChange)*DESCENDSCALEFACTOR)));
            }
            currSq = currSq.getParent();
        }
    }
    
    @Test
    public void testBlackNotPassable() {
        mazeImage.setRGB(0, 0, grayColor.getRGB());
        mazeImage.setRGB(1, 0, grayColor.getRGB());
        mazeImage.setRGB(2, 0, whiteColor.getRGB());
        mazeImage.setRGB(0, 1, whiteColor.getRGB());
        mazeImage.setRGB(1, 1, blackColor.getRGB());
        mazeImage.setRGB(2, 1, grayColor.getRGB());
        mazeImage.setRGB(0, 2, whiteColor.getRGB());
        mazeImage.setRGB(1, 2, whiteColor.getRGB());
        mazeImage.setRGB(2, 2, grayColor.getRGB());
        startPt = new Point(0, 0);
        endPt = new Point(2, 2);
        
        Algorithm algorithm = new AStarAlgorithm(mazeImage, startPt, endPt, "manhattan", 0, 0, false, true);
        AStarSquare currSq = algorithm.search();
        
        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(2,1)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(1,0)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));
    }
    
    @Test
    public void testWhiteNotPassable() {
        mazeImage.setRGB(0, 0, grayColor.getRGB());
        mazeImage.setRGB(1, 0, grayColor.getRGB());
        mazeImage.setRGB(2, 0, blackColor.getRGB());
        mazeImage.setRGB(0, 1, blackColor.getRGB());
        mazeImage.setRGB(1, 1, whiteColor.getRGB());
        mazeImage.setRGB(2, 1, grayColor.getRGB());
        mazeImage.setRGB(0, 2, blackColor.getRGB());
        mazeImage.setRGB(1, 2, blackColor.getRGB());
        mazeImage.setRGB(2, 2, grayColor.getRGB());
        startPt = new Point(0, 0);
        endPt = new Point(2, 2);
        
        Algorithm algorithm = new AStarAlgorithm(mazeImage, startPt, endPt, "manhattan", 0, 0, true, false);
        AStarSquare currSq = algorithm.search();
        
        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(2,1)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(1,0)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));
    }
    
}
