package mazeSolver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author zhang156
 *
 * The controller class is responsible for initializing all action listeners by calling
 * them inside the init() function.
 */
@SuppressWarnings("serial")
public class MazeSolverController extends JApplet {

	private MazeSolver mazeSolver;
	private MazeSolverView mazeSolverView;	
	private Point startPt;
	private Point endPt;
	private int numRetry;
	private String[] mazeNames;
	
	/**
	 * Initializes all fields inside the class, as well as calls all the listener functions
	 * inside the view class in order to add the action listeners to the (buttons/fields/list).
	 */
	public void init() {
		
		mazeSolver = new MazeSolver();
		mazeSolverView = new MazeSolverView(this);
		startPt = new Point(-1, -1);
		endPt = new Point(-1, -1);
		numRetry = 0;
		mazeNames = new String[7];
		
		mazeSolverView.setReadyState();
		this.setSize(new Dimension(1000, 1000));
		
		mazeSolverView.addUpdateListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
		        JList list = (JList)event.getSource();
		        if (((String)list.getSelectedValue()).equals("megamaze")) {
		        	mazeSolverView.setIOPicture("maze0", "input");
		        	mazeSolverView.setMegamazeStartState();
		        }
		        else {
			        mazeSolverView.setIOPicture((String)list.getSelectedValue(), "input");
		        	mazeSolverView.setReadyState();
		        }
				mazeSolverView.clearFields();
				mazeSolver = new MazeSolver();
			}
		});
		
		mazeSolverView.addMaze0Listener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setMegamazeEditState();
				JTextField maze0Field = (JTextField)event.getSource();
				mazeNames[0] = maze0Field.getText();
			}
		});
		
		mazeSolverView.addMaze1Listener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setMegamazeEditState();
				JTextField maze1Field = (JTextField)event.getSource();
				mazeNames[1] = maze1Field.getText();
			}
		});
		
		mazeSolverView.addMaze2Listener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setMegamazeEditState();
				JTextField maze2Field = (JTextField)event.getSource();
				mazeNames[2] = maze2Field.getText();
			}
		});
		
		mazeSolverView.addMaze3Listener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setMegamazeEditState();
				JTextField maze3Field = (JTextField)event.getSource();
				mazeNames[3] = maze3Field.getText();
			}
		});
		
		mazeSolverView.addMaze4Listener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setMegamazeEditState();
				JTextField maze4Field = (JTextField)event.getSource();
				mazeNames[4] = maze4Field.getText();
			}
		});
		
		mazeSolverView.addMaze5Listener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setMegamazeEditState();
				JTextField maze5Field = (JTextField)event.getSource();
				mazeNames[5] = maze5Field.getText();
			}
		});
		
		mazeSolverView.addMaze6Listener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setMegamazeEditState();
				JTextField maze6Field = (JTextField)event.getSource();
				mazeNames[6] = maze6Field.getText();
			}
		});
		
		mazeSolverView.addGenerateListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setGenerateImageState();
				BufferedImage[] mazeImages = new BufferedImage[7];
				for (int i=0; i<7; ++i)
					mazeImages[i] = null;
				
				int imageWidth = 0;
				int imageHeight = 464;
				
				for (int i=0; i<7; ++i) {
					if (mazeNames[i] != null) {
						try {
							mazeImages[i] = ImageReadWrite.Read("/home/zhang156/workspace/MazeSolver1.3/input/" + mazeNames[i] + ".bmp");
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "One or more of your input maze names is invalid. " +
									"Please pick from the 7 above megamaze");
						}
					}
				}
				
				for (int i=0; i<7; ++i) { 
					if (mazeImages[i] != null) {
						imageWidth += mazeImages[i].getWidth();
					}
				}
				
				BufferedImage megamaze = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
				int xCpy = 0;
				for (int i=0; i<7; ++i) {
					if (mazeImages[i] != null) {
						for (int x=0; x<mazeImages[i].getWidth(); ++x) {
							for (int y=0; y<mazeImages[i].getHeight(); ++y) {
				                megamaze.setRGB(x+xCpy, y, mazeImages[i].getRGB(x, y));
							}
						}
						xCpy += mazeImages[i].getWidth();
					}
				}
				
				try {
					ImageReadWrite.Write("/home/zhang156/workspace/MazeSolver1.3/input/megamaze.bmp", megamaze);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mazeSolverView.setIOPicture("megamaze", "input");
				mazeSolverView.setMegamazeReadyState();
			}
		});
		
		mazeSolverView.addManhattanListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				try {
					mazeSolver.setHeuristicType("manhattan");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		mazeSolverView.addDiagonalListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				try {
					mazeSolver.setHeuristicType("diagonal");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		mazeSolverView.addEuclideanListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				try {
					mazeSolver.setHeuristicType("euclidean");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		mazeSolverView.addAscendListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				JTextField ascendField = (JTextField)event.getSource();
				String ascendText = ascendField.getText();
				mazeSolver.setAscendScaleFactor(Integer.parseInt(ascendText));
			}
		});
		
		mazeSolverView.addDescendListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				JTextField descendField = (JTextField)event.getSource();
				String descendText = descendField.getText();
				mazeSolver.setDescendScaleFactor(Integer.parseInt(descendText));
			}
		});
		
		mazeSolverView.addThresholdListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				JTextField thresholdField = (JTextField)event.getSource();
				String thresholdText = thresholdField.getText();
				try {
					MazeSolver.setThreshold(Integer.parseInt(thresholdText));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Your threshold input is invalid. " +
							"Please enter a value between 0 and 255 inclusive.");
				}
			}
		});
		
		mazeSolverView.addStartXListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				JTextField startXField = (JTextField)event.getSource();
				String startXText = startXField.getText();
				startPt.x = Integer.parseInt(startXText);
			}
		});
		
		mazeSolverView.addStartYListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				JTextField startYField = (JTextField)event.getSource();
				String startYText = startYField.getText();
				startPt.y = Integer.parseInt(startYText);
			}
		});
		
		mazeSolverView.addEndXListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				JTextField endXField = (JTextField)event.getSource();
				String endXText = endXField.getText();
				endPt.x = Integer.parseInt(endXText);
			}
		});
		
		mazeSolverView.addEndYListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setReadyState();
				JTextField endYField = (JTextField)event.getSource();
				String endYText = endYField.getText();
				endPt.y = Integer.parseInt(endYText);
			}
		});
		
		mazeSolverView.addSolvableListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setRunningState();
						
				String readFile;
				if (numRetry > 0) {
					readFile = "/home/zhang156/workspace/MazeSolver1.3/output/" + 
							mazeSolverView.getList().getSelectedValue() + ".bmp";
				}
				else {
					readFile = "/home/zhang156/workspace/MazeSolver1.3/input/" + 
							mazeSolverView.getList().getSelectedValue() + ".bmp";
				}
				String writeFile = "/home/zhang156/workspace/MazeSolver1.3/output/" +
						mazeSolverView.getList().getSelectedValue() + ".bmp";				
				boolean solvable = false;
				boolean exception = false;
				
				try {
					Color color;
					if (numRetry == 0)
						color = Color.red;
					else if (numRetry == 1)
						color = Color.blue;
					else if (numRetry == 2)
						color = Color.green;
					else
						color = Color.yellow;
					solvable = mazeSolver.solveMaze(readFile, writeFile, startPt, endPt, color);
				} catch (Exception e) {
					mazeSolverView.setReadyState();
					JOptionPane.showMessageDialog(null, "Start and/or end coordinates invalid. Please try again.");
					exception = true;
				}
				if (solvable && !exception)
					mazeSolverView.setSolvableState();
				else if (!solvable && !exception) {
					mazeSolverView.setReadyState();
					JOptionPane.showMessageDialog(null, "There is no path through the maze using the given settings." +
							"Please try again with different settings.");
				}
			}
		});
		
		mazeSolverView.addSolveListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setRunningState();
				
				String imageName = (String)mazeSolverView.getList().getSelectedValue();
				mazeSolverView.setIOPicture((String)imageName, "output");
				
				if (numRetry == 3) {
					numRetry = 0;
					mazeSolverView.setSolvedCantRetryState();
				}
				else
					mazeSolverView.setSolvedCanRetryState();
			}
		});
		
		mazeSolverView.addRetryListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setRunningState();
				++numRetry;
				mazeSolverView.setReadyState();
			}
		});
		
		mazeSolverView.addRefreshListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mazeSolverView.setRunningState();
				
				String imageName = (String)mazeSolverView.getList().getSelectedValue();
				mazeSolverView.clearFields();		
				mazeSolver = new MazeSolver();
				numRetry = 0;
				
				if (((String)mazeSolverView.getList().getSelectedValue()).equals("megamaze")) {
					mazeSolverView.setIOPicture("maze0", "input");
					mazeSolverView.setMegamazeStartState();
				}
				else {
					mazeSolverView.setIOPicture((String)imageName, "input");
					mazeSolverView.setReadyState();
				}
			}
		});
	}
}
