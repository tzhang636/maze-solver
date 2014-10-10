package mazeSolver;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import org.junit.Test;

public class AStarTest {
    
    public static final int ASCENDSCALEFACTOR = 100;
    public static final int DESCENDSCALEFACTOR = 50;
    
    @Test
    public void test1x1() {
        
        // 1x1 all black image
        // no movement
        // (0,0) to (0,0)
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, Color.black.getRGB());
        Point startPt = new Point(0, 0);
        Point endPt = new Point(0, 0);

        AStarAlgorithm astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        AStarSquare currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(startPt));
        assertTrue(currSq.getXyPt().equals(endPt));
    }

    @Test
    public void tests2x2() {

        // 2x2 all black image
        // d movement
        // (0,0) to (1,1)
        BufferedImage image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                image.setRGB(i, j, Color.black.getRGB());
            }
        }
        Point startPt = new Point(0, 0);
        Point endPt = new Point(1, 1);

        AStarAlgorithm astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        AStarSquare currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));

        // walls at (1,0) and (0,1)
        // d movement
        // (0,0) to (1,1)
        image.setRGB(0, 1, Color.white.getRGB());
        image.setRGB(1, 0, Color.white.getRGB());

        astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));

        // wall at (1,1)
        // h movement
        // (0,0) to (1,0)
        image.setRGB(0, 1, Color.black.getRGB());
        image.setRGB(1, 0, Color.black.getRGB());
        image.setRGB(1, 1, Color.white.getRGB());

        endPt.x = 1;
        endPt.y = 0;

        astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));

        // wall at (1,1)
        // v movement
        // (0,0) to (0,1)

        endPt.x = 0;
        endPt.y = 1;

        astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));
    }

    
    @Test
    public void tests3x3() {

        // 3x3 all black image
        // d movement
        // (0,0) to (2,2)
        BufferedImage image = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                image.setRGB(i, j, Color.black.getRGB());
            }
        }

        Point startPt = new Point(0, 0);
        Point endPt = new Point(2, 2);

        AStarAlgorithm astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        AStarSquare currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(1, 1)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));

        // walls at (1,0), (2,0), (1,1), (2,1)
        // v/d/h movement
        // (0,0) to (2,2)
        image.setRGB(1, 0, Color.white.getRGB());
        image.setRGB(2, 0, Color.white.getRGB());
        image.setRGB(1, 1, Color.white.getRGB());
        image.setRGB(2, 1, Color.white.getRGB());

        astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(1, 2)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(0, 1)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));

        // walls at (0,1), (0,2), (1,1), (1,2)
        // v/d movement
        // (0,0) to (2,2)
        image.setRGB(1, 0, Color.black.getRGB());
        image.setRGB(2, 0, Color.black.getRGB());
        image.setRGB(2, 1, Color.black.getRGB());

        image.setRGB(0, 1, Color.white.getRGB());
        image.setRGB(0, 2, Color.white.getRGB());
        image.setRGB(1, 2, Color.white.getRGB());

        astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(2, 1)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(1, 0)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));

        // walls at (1,0), (2,0), (0,1), (2,1)
        // d movement
        // (0,0) to (2,2)
        image.setRGB(1, 0, Color.white.getRGB());
        image.setRGB(2, 0, Color.white.getRGB());
        image.setRGB(0, 1, Color.white.getRGB());
        image.setRGB(2, 1, Color.white.getRGB());

        image.setRGB(1, 1, Color.black.getRGB());
        image.setRGB(0, 2, Color.black.getRGB());
        image.setRGB(1, 2, Color.black.getRGB());

        astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        currSq = astar.search();

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(1, 1)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));

        // walls at (2,0), (1,1), (0,2), (1,2), (2,1)
        // no movement
        // (0,0) to (2,2)
        image.setRGB(2, 0, Color.white.getRGB());
        image.setRGB(1, 1, Color.white.getRGB());
        image.setRGB(0, 2, Color.white.getRGB());
        image.setRGB(1, 2, Color.white.getRGB());
        image.setRGB(2, 1, Color.white.getRGB());
        
        image.setRGB(1, 0, Color.black.getRGB());
        image.setRGB(0, 1, Color.black.getRGB());

        astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        currSq = astar.search();

        assertTrue(currSq == null);
    }

    @Test
    public void testTraversable() {

        // tests which squares in the maze are walkable
        BufferedImage image = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (i == j)
                    image.setRGB(i, j, Color.white.getRGB());
                else
                    image.setRGB(i, j, Color.black.getRGB());
            }
        }

        Point startPt = new Point(0, 0);
        Point endPt = new Point(2, 2);

        AStarAlgorithm astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        AStarSquare currSq = astar.search(); 

        while (currSq != null) {
            assertTrue(currSq.getXyPt().x != currSq.getXyPt().y);
            currSq = currSq.getParent();
        }
    }

    @Test
    public void testUniquelyIdentified() {

        // tests that a square is not equal to its parent
        BufferedImage image = new BufferedImage(3, 3,
                BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                image.setRGB(i, j, Color.black.getRGB());
            }
        }

        Point startPt = new Point(0, 0);
        Point endPt = new Point(2, 2);

        AStarAlgorithm astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        AStarSquare currSq = astar.search(); 
        AStarSquare thisSq;
        
        while (currSq != null) {
            assertTrue(!currSq.equals(currSq.getParent()));
            thisSq = currSq.getParent();
            while (thisSq != null) {
                assertTrue(!currSq.equals(thisSq));
                thisSq = thisSq.getParent();
            }
            currSq = currSq.getParent();
        }

    }

    @Test
    public void testStartEnd() {

        // tests whether a particular square is the start or end square or not
        BufferedImage image = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                image.setRGB(i, j, Color.black.getRGB());
            }
        }

        Point startPt = new Point(0, 0);
        Point endPt = new Point(2, 2);

        AStarAlgorithm astar = new AStarAlgorithm(image, startPt, endPt, 
                "manhattan", ASCENDSCALEFACTOR, DESCENDSCALEFACTOR, true, false);
        AStarSquare currSq = astar.search(); 

        assertTrue(currSq.getXyPt().equals(endPt));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(new Point(1,1)));
        currSq = currSq.getParent();
        assertTrue(currSq.getXyPt().equals(startPt));
    }
  
}
