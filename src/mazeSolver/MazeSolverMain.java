package mazeSolver;

import java.awt.Point;

public class MazeSolverMain {

    public static void main(String[] args) throws Exception {
        String readFile0 = "/home/zhang156/workspace/MazeSolver1.3/input/maze0.bmp";
        String readFile1 = "/home/zhang156/workspace/MazeSolver1.3/input/maze1.bmp";
        String readFile2 = "/home/zhang156/workspace/MazeSolver1.3/input/maze2.bmp";
        
        String writeFile0 = "/home/zhang156/workspace/MazeSolver1.3/output/maze0_solved.bmp";
        String writeFile1 = "/home/zhang156/workspace/MazeSolver1.3/output/maze1_solved.bmp";
        String writeFile2 = "/home/zhang156/workspace/MazeSolver1.3/output/maze2_solved.bmp";
        
        Point startPt0 = new Point(0, 0);
        Point startPt1 = new Point(0, 0);
        Point startPt2 = new Point(0, 30);
        
        Point endPt0 = new Point(319, 239);
        Point endPt1 = new Point(319, 239);
        Point endPt2 = new Point(210, 0);
        
        MazeSolver mazeSolver = new MazeSolver();
        mazeSolver.solveMaze(readFile0, writeFile0, startPt0, endPt0);
        mazeSolver.solveMaze(readFile1, writeFile1, startPt1, endPt1);
        mazeSolver.solveMaze(readFile2, writeFile2, startPt2, endPt2);
        
        // ----------------------------------------------------------------------------
        
        String readFile3 = "/home/zhang156/workspace/MazeSolver1.3/input/colorado.bmp";
        String readFile4 = "/home/zhang156/workspace/MazeSolver1.3/input/mountains.bmp";
        String readFile5 = "/home/zhang156/workspace/MazeSolver1.3/input/redwoodpoint.bmp";
        String readFile6 = "/home/zhang156/workspace/MazeSolver1.3/input/southtahoe.bmp";
        String readFile7 = "/home/zhang156/workspace/MazeSolver1.3/input/tahoe.bmp";
        String readFile8 = "/home/zhang156/workspace/MazeSolver1.3/input/pyramidpeak.bmp";
        String readFile9 = "/home/zhang156/workspace/MazeSolver1.3/input/mountainview.bmp";
        
        String writeFile3 = "/home/zhang156/workspace/MazeSolver1.3/output/colorado_solved.bmp";
        String writeFile4 = "/home/zhang156/workspace/MazeSolver1.3/output/mountains_solved.bmp";
        String writeFile5 = "/home/zhang156/workspace/MazeSolver1.3/output/redwoodpoint_solved.bmp";
        String writeFile6 = "/home/zhang156/workspace/MazeSolver1.3/output/southtahoe_solved.bmp";
        String writeFile7 = "/home/zhang156/workspace/MazeSolver1.3/output/tahoe_solved.bmp";
        String writeFile8 = "/home/zhang156/workspace/MazeSolver1.3/output/pyramidpeak_solved.bmp";
        String writeFile9 = "/home/zhang156/workspace/MazeSolver1.3/output/mountainview_solved.bmp";
        
        Point startPt3 = new Point(20, 20);
        Point startPt4 = new Point(20, 20);
        Point startPt5 = new Point(20, 20);
        Point startPt6 = new Point(20, 20);
        Point startPt7 = new Point(20, 20);
        Point startPt8 = new Point(20, 20);
        Point startPt9 = new Point(20, 20);
        
        Point endPt3 = new Point(355, 455);
        Point endPt4 = new Point(355, 455);
        Point endPt5 = new Point(355, 455);
        Point endPt6 = new Point(355, 455);
        Point endPt7 = new Point(355, 455);
        Point endPt8 = new Point(355, 455);
        Point endPt9 = new Point(355, 455);
        
        MazeSolver mapSolver = new MazeSolver();
        mapSolver.setHeuristicType("euclidean");
        mapSolver.setAscendScaleFactor(150);
        mapSolver.setDescendScaleFactor(60);
        mapSolver.setBlackPassable(true);
        mapSolver.setWhitePassable(true);
        
        mapSolver.solveMaze(readFile3, writeFile3, startPt3, endPt3);
        mapSolver.solveMaze(readFile4, writeFile4, startPt4, endPt4);
        mapSolver.solveMaze(readFile5, writeFile5, startPt5, endPt5);
        mapSolver.solveMaze(readFile6, writeFile6, startPt6, endPt6);
        mapSolver.solveMaze(readFile7, writeFile7, startPt7, endPt7);
        mapSolver.solveMaze(readFile8, writeFile8, startPt8, endPt8);
        mapSolver.solveMaze(readFile9, writeFile9, startPt9, endPt9);
         
    }

}
