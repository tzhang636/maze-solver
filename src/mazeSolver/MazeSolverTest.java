package mazeSolver;

import java.awt.Point;
import org.junit.Test;

public class MazeSolverTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAlgorithm() throws Exception {

        // algorithm input not valid
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.setAlgorithmType("this is not an algorithm type");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAlgorithm() throws Exception {

        // algorithm input null
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.setAlgorithmType(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHeuristic() throws Exception {

        // heuristic input invalid
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.setAlgorithmType("this is not an heuristic type");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullHeuristic() throws Exception {

        // heuristic input null
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.setAlgorithmType(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStart() throws Exception {

        // start input not valid
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.solveMaze("c:/temp/maze0.bmp", "c:/temp/maze0_solved.bmp", 
                new Point(500, 500), new Point(500,500));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullStart() throws Exception {

        // start input null
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.solveMaze("c:/temp/maze0.bmp", "c:/temp/maze0_solved.bmp", 
                null, new Point(500,500));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEnd() throws Exception {

        // end input not valid
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.solveMaze("c:/temp/maze0.bmp", "c:/temp/maze0_solved.bmp", 
                new Point(0, 0), new Point(500,500));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNullEnd() throws Exception {

        // end input null
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.solveMaze("c:/temp/maze0.bmp", "c:/temp/maze0_solved.bmp", 
                new Point(0, 0), null);
    }
}
