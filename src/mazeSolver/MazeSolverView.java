package mazeSolver;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author zhang156
 *
 * This is the view class for the GUI implementation of MazeSolver. This interface 
 * is divided into three parts: a list on the left, a picture label on the top right, 
 * and a button panel on the bottom right. The interface also implements the MVC 
 * architectural pattern.
 */
public class MazeSolverView {

	private String[] imageNames = { "maze0", "maze1", "maze2", "colorado", 
									"mountains", "redwoodpoint", "southtahoe", 
									"tahoe", "pyramidpeak", "mountainview", "megamaze" };
	
	private JList list = new JList(imageNames);
	private JLabel picture = new JLabel();
    private JPanel buttonPanel = new JPanel();
	private JSplitPane splitPaneRight;
	private JSplitPane splitPaneLeft;
	
	private JButton manhattanButton = new JButton("Manhattan");
	private JButton diagonalButton = new JButton("Diagonal");
	private JButton euclideanButton = new JButton("Euclidean");
	private JButton solvableButton = new JButton("TestSolvable");
	private JButton solveButton = new JButton("Solve");
	private JButton retryButton = new JButton("Retry");
	private JButton refreshButton = new JButton("Refresh");

	private JTextField ascendField = new JTextField("Ascending Scaling Factor", 10);
	private JTextField descendField = new JTextField("Descending Scaling Factor", 10);
	private JTextField thresholdField = new JTextField("Walkable Threshold", 10);
	private JTextField startXField = new JTextField("Start X-Coord", 10);
	private	JTextField startYField = new JTextField("Start Y-Coord", 10);
	private JTextField endXField = new JTextField("End X-Coord", 10);
	private JTextField endYField = new JTextField("End Y-Coord", 10);
	
	private JTextField maze0Field = new JTextField("maze to add", 10);
	private JTextField maze1Field = new JTextField("maze to add", 10);
	private JTextField maze2Field = new JTextField("maze to add", 10);
	private JTextField maze3Field = new JTextField("maze to add", 10);
	private JTextField maze4Field = new JTextField("maze to add", 10);
	private JTextField maze5Field = new JTextField("maze to add", 10);
	private JTextField maze6Field = new JTextField("maze to add", 10);
	private JButton generateButton = new JButton("Generate Image");
	
	/**
	 * Constructs a MazeSolverView object that initializes the GUI.
	 * @param control - the corresponding controller applet that the view will be added to
	 */
	protected MazeSolverView(MazeSolverController control) {
		
		// create a list of images
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		
	    // create a new picture and set the font to be italic and centered
        picture.setHorizontalAlignment(JLabel.CENTER);
        picture.setVerticalAlignment(JLabel.CENTER);
				
        // create scroll panes for both the list and picture
        JScrollPane listScrollPane = new JScrollPane(list);
        JScrollPane pictureScrollPane = new JScrollPane(picture);
		
        // add buttons for buttonPanel
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        JPanel mazeSelectionPanel;
        mazeSelectionPanel = new JPanel();
        mazeSelectionPanel.setLayout(new BoxLayout(mazeSelectionPanel, BoxLayout.Y_AXIS));
        mazeSelectionPanel.add(maze0Field);
        mazeSelectionPanel.add(maze1Field);
        mazeSelectionPanel.add(maze2Field);
        mazeSelectionPanel.add(maze3Field);
        mazeSelectionPanel.add(maze4Field);
        mazeSelectionPanel.add(maze5Field);
        mazeSelectionPanel.add(maze6Field);
        mazeSelectionPanel.add(generateButton);
        buttonPanel.add(mazeSelectionPanel);
       
        JPanel heuristicPanel = new JPanel();
        JPanel textboxPanel = new JPanel();
        JPanel solvePanel = new JPanel();
        
        heuristicPanel.setLayout(new BoxLayout(heuristicPanel, BoxLayout.Y_AXIS));
        textboxPanel.setLayout(new BoxLayout(textboxPanel, BoxLayout.Y_AXIS));
        solvePanel.setLayout(new BoxLayout(solvePanel, BoxLayout.Y_AXIS));
        
        heuristicPanel.add(manhattanButton);
		heuristicPanel.add(diagonalButton);
		heuristicPanel.add(euclideanButton);
		
		textboxPanel.add(ascendField);
		textboxPanel.add(descendField);
		textboxPanel.add(thresholdField);
		textboxPanel.add(startXField);
		textboxPanel.add(startYField);
		textboxPanel.add(endXField);
		textboxPanel.add(endYField);
		
		solvePanel.add(solvableButton);
		solvePanel.add(solveButton);
		solvePanel.add(retryButton);
		solvePanel.add(refreshButton);
		
		buttonPanel.add(heuristicPanel);
		buttonPanel.add(textboxPanel);
		buttonPanel.add(solvePanel);

        // splits the pane vertically
		splitPaneRight = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pictureScrollPane, buttonPanel);
        splitPaneRight.setOneTouchExpandable(true);
		splitPaneRight.setDividerLocation(500);
		
        // provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(100, 100);
        listScrollPane.setMinimumSize(minimumSize);
        pictureScrollPane.setMinimumSize(minimumSize);
        
		// sets the preferred size for the split pane
		splitPaneRight.setPreferredSize(new Dimension(1000, 800));
		setIOPicture(imageNames[list.getSelectedIndex()], "input");
		
		// splits the pane horizontally
		splitPaneLeft = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, splitPaneRight);
        splitPaneLeft.setOneTouchExpandable(true);
		splitPaneLeft.setDividerLocation(150);
		
		control.getContentPane().add(splitPaneLeft);
	}
	
	/**
	 * Handles the action from a change in list selection.
	 * @param a - listener
	 */
	protected void addUpdateListener(ListSelectionListener a) {
		list.addListSelectionListener(a);
	}
	
	/**
	 * Handles the action from the manhattan button.
	 * @param a - listener
	 */
	protected void addManhattanListener(ActionListener a) {
		manhattanButton.addActionListener(a);
	}
	
	/**
	 * Handles the action from the diagonal button.
	 * @param a - listener
	 */
	protected void addDiagonalListener(ActionListener a) {
		diagonalButton.addActionListener(a);
	}
	
	/**
	 * Handles the action from the euclidean button.
	 * @param a - listener
	 */
	protected void addEuclideanListener(ActionListener a) {
		euclideanButton.addActionListener(a);
	}
	
	/**
	 * Handles the action from the testsolvable button.
	 * @param a - listener
	 */
	protected void addSolvableListener(ActionListener a) {
		solvableButton.addActionListener(a);
	}
	
	/**
	 * Handles the action from the solve button.
	 * @param a - listener
	 */
	protected void addSolveListener(ActionListener a) {
		solveButton.addActionListener(a);
	}
	
	/**
	 * Handles the action from the retry button.
	 * @param a - listener
	 */
	protected void addRetryListener(ActionListener a) {
		retryButton.addActionListener(a);
	}
	
	/**
	 * Handles the action from the refresh button.
	 * @param a - listener
	 */
	protected void addRefreshListener(ActionListener a) {
		refreshButton.addActionListener(a);
	}
	
	/**
	 * Handles the action from the ascend text box.
	 * @param a - listener
	 */
	protected void addAscendListener(ActionListener a) {
		ascendField.addActionListener(a);
	}
	
	/**
	 * Handles the action from the descend text box.
	 * @param a - listener
	 */
	protected void addDescendListener(ActionListener a) {
		descendField.addActionListener(a);
	}
	
	/**
	 * Handles the action from the threshold text box.
	 * @param a - listener
	 */
	protected void addThresholdListener(ActionListener a) {
		thresholdField.addActionListener(a);
	}
	
	/**
	 * Handles the action from the startX text box.
	 * @param a - listener
	 */
	protected void addStartXListener(ActionListener a) {
		startXField.addActionListener(a);
	}
	
	/**
	 * Handles the action from the startY text box.
	 * @param a - listener
	 */
	protected void addStartYListener(ActionListener a) {
		startYField.addActionListener(a);
	}
	
	/**
	 * Handles the action from the endX text box.
	 * @param a - listener
	 */
	protected void addEndXListener(ActionListener a) {
		endXField.addActionListener(a);
	}
	
	/**
	 * Handles the action from the endY text box.
	 * @param a - listener
	 */
	protected void addEndYListener(ActionListener a) {
		endYField.addActionListener(a);
	}
	
	/**
	 * Handles the action from the maze0 text box.
	 * @param a - listener
	 */
	protected void addMaze0Listener(ActionListener a) {
		maze0Field.addActionListener(a);
	}
	
	/**
	 * Handles the action from the maze1 text box.
	 * @param a - listener
	 */
	protected void addMaze1Listener(ActionListener a) {
		maze1Field.addActionListener(a);
	}
	
	/**
	 * Handles the action from the maze2 text box.
	 * @param a - listener
	 */
	protected void addMaze2Listener(ActionListener a) {
		maze2Field.addActionListener(a);
	}
	
	/**
	 * Handles the action from the maze3 text box.
	 * @param a - listener
	 */
	protected void addMaze3Listener(ActionListener a) {
		maze3Field.addActionListener(a);
	}
	
	/**
	 * Handles the action from the maze4 text box.
	 * @param a - listener
	 */
	protected void addMaze4Listener(ActionListener a) {
		maze4Field.addActionListener(a);
	}
	
	/**
	 * Handles the action from the maze5 text box.
	 * @param a - listener
	 */
	protected void addMaze5Listener(ActionListener a) {
		maze5Field.addActionListener(a);
	}
	
	/**
	 * Handles the action from the maze6 text box.
	 * @param a - listener
	 */
	protected void addMaze6Listener(ActionListener a) {
		maze6Field.addActionListener(a);
	}
	
	/**
	 * Handles the action from the generate button.
	 * @param a - listener
	 */
	protected void addGenerateListener(ActionListener a) {
		generateButton.addActionListener(a);
	}
	
	/**
	 * Sets the megamaze start state.
	 */
	protected void setMegamazeStartState() {
		manhattanButton.setEnabled(false);
		diagonalButton.setEnabled(false);
		euclideanButton.setEnabled(false);
		solvableButton.setEnabled(false);
		solveButton.setEnabled(false);
		retryButton.setEnabled(false);
		refreshButton.setEnabled(false);
		
		ascendField.setEditable(false);
		descendField.setEditable(false);
		thresholdField.setEditable(false);
		startXField.setEditable(false);
		startYField.setEditable(false);
		endXField.setEditable(false);
		endYField.setEditable(false);
		
		maze0Field.setEditable(true);
		maze1Field.setEditable(true);
		maze2Field.setEditable(true);
		maze3Field.setEditable(true);
		maze4Field.setEditable(true);
		maze5Field.setEditable(true);
		maze6Field.setEditable(true);
		generateButton.setEnabled(false);
	}
	
	/**
	 * Sets the megamaze edit state.
	 */
	protected void setMegamazeEditState() {
		manhattanButton.setEnabled(false);
		diagonalButton.setEnabled(false);
		euclideanButton.setEnabled(false);
		solvableButton.setEnabled(false);
		solveButton.setEnabled(false);
		retryButton.setEnabled(false);
		refreshButton.setEnabled(false);
		
		ascendField.setEditable(false);
		descendField.setEditable(false);
		thresholdField.setEditable(false);
		startXField.setEditable(false);
		startYField.setEditable(false);
		endXField.setEditable(false);
		endYField.setEditable(false);
		
		maze0Field.setEditable(true);
		maze1Field.setEditable(true);
		maze2Field.setEditable(true);
		maze3Field.setEditable(true);
		maze4Field.setEditable(true);
		maze5Field.setEditable(true);
		maze6Field.setEditable(true);
		generateButton.setEnabled(true);
	}
	
	/**
	 * Sets the generate image state.
	 */
	protected void setGenerateImageState() {
		manhattanButton.setEnabled(false);
		diagonalButton.setEnabled(false);
		euclideanButton.setEnabled(false);
		solvableButton.setEnabled(false);
		solveButton.setEnabled(false);
		retryButton.setEnabled(false);
		refreshButton.setEnabled(false);
		
		ascendField.setEditable(false);
		descendField.setEditable(false);
		thresholdField.setEditable(false);
		startXField.setEditable(false);
		startYField.setEditable(false);
		endXField.setEditable(false);
		endYField.setEditable(false);	
		
		maze0Field.setEditable(false);
		maze1Field.setEditable(false);
		maze2Field.setEditable(false);
		maze3Field.setEditable(false);
		maze4Field.setEditable(false);
		maze5Field.setEditable(false);
		maze6Field.setEditable(false);
		generateButton.setEnabled(false);
	}
	
	/**
	 * sets the megamaze ready state.
	 */
	protected void setMegamazeReadyState() {
		manhattanButton.setEnabled(true);
		diagonalButton.setEnabled(true);
		euclideanButton.setEnabled(true);
		solvableButton.setEnabled(true);
		solveButton.setEnabled(false);
		retryButton.setEnabled(false);
		refreshButton.setEnabled(true);
		
		ascendField.setEditable(true);
		descendField.setEditable(true);
		thresholdField.setEditable(true);
		startXField.setEditable(true);
		startYField.setEditable(true);
		endXField.setEditable(true);
		endYField.setEditable(true);	
		
		maze0Field.setEditable(false);
		maze1Field.setEditable(false);
		maze2Field.setEditable(false);
		maze3Field.setEditable(false);
		maze4Field.setEditable(false);
		maze5Field.setEditable(false);
		maze6Field.setEditable(false);
		generateButton.setEnabled(false);
	}
	
	/**
	 * Sets the ready state.
	 */
	protected void setReadyState() {
		manhattanButton.setEnabled(true);
		diagonalButton.setEnabled(true);
		euclideanButton.setEnabled(true);
		solvableButton.setEnabled(true);
		solveButton.setEnabled(false);
		retryButton.setEnabled(false);
		refreshButton.setEnabled(true);
		
		ascendField.setEditable(true);
		descendField.setEditable(true);
		thresholdField.setEditable(true);
		startXField.setEditable(true);
		startYField.setEditable(true);
		endXField.setEditable(true);
		endYField.setEditable(true);	
		
		maze0Field.setEditable(false);
		maze1Field.setEditable(false);
		maze2Field.setEditable(false);
		maze3Field.setEditable(false);
		maze4Field.setEditable(false);
		maze5Field.setEditable(false);
		maze6Field.setEditable(false);
		generateButton.setEnabled(false);
	}
	
	/**
	 * Sets the solvable state.
	 */
	protected void setSolvableState() {
		manhattanButton.setEnabled(true);
		diagonalButton.setEnabled(true);
		euclideanButton.setEnabled(true);
		solvableButton.setEnabled(true);
		solveButton.setEnabled(true);
		retryButton.setEnabled(false);
		refreshButton.setEnabled(true);
		
		ascendField.setEditable(true);
		descendField.setEditable(true);
		thresholdField.setEditable(true);
		startXField.setEditable(true);
		startYField.setEditable(true);
		endXField.setEditable(true);
		endYField.setEditable(true);
		
		maze0Field.setEditable(false);
		maze1Field.setEditable(false);
		maze2Field.setEditable(false);
		maze3Field.setEditable(false);
		maze4Field.setEditable(false);
		maze5Field.setEditable(false);
		maze6Field.setEditable(false);
		generateButton.setEnabled(false);
	}
	
	/**
	 * Sets the retry state.
	 */
	protected void setSolvedCanRetryState() {
		manhattanButton.setEnabled(false);
		diagonalButton.setEnabled(false);
		euclideanButton.setEnabled(false);
		solvableButton.setEnabled(false);
		solveButton.setEnabled(false);
		retryButton.setEnabled(true);
		refreshButton.setEnabled(true);
		
		ascendField.setEditable(false);
		descendField.setEditable(false);
		thresholdField.setEditable(false);
		startXField.setEditable(false);
		startYField.setEditable(false);
		endXField.setEditable(false);
		endYField.setEditable(false);
		
		maze0Field.setEditable(false);
		maze1Field.setEditable(false);
		maze2Field.setEditable(false);
		maze3Field.setEditable(false);
		maze4Field.setEditable(false);
		maze5Field.setEditable(false);
		maze6Field.setEditable(false);
		generateButton.setEnabled(false);
	}
	
	/**
	 * Sets the cant retry state.
	 */
	protected void setSolvedCantRetryState() {
		manhattanButton.setEnabled(false);
		diagonalButton.setEnabled(false);
		euclideanButton.setEnabled(false);
		solvableButton.setEnabled(false);
		solveButton.setEnabled(false);
		retryButton.setEnabled(false);
		refreshButton.setEnabled(true);
		
		ascendField.setEditable(false);
		descendField.setEditable(false);
		thresholdField.setEditable(false);
		startXField.setEditable(false);
		startYField.setEditable(false);
		endXField.setEditable(false);
		endYField.setEditable(false);
		
		maze0Field.setEditable(false);
		maze1Field.setEditable(false);
		maze2Field.setEditable(false);
		maze3Field.setEditable(false);
		maze4Field.setEditable(false);
		maze5Field.setEditable(false);
		maze6Field.setEditable(false);
		generateButton.setEnabled(false);
	}
	
	/**
	 * Sets the running state.
	 */
	protected void setRunningState() {
		manhattanButton.setEnabled(false);
		diagonalButton.setEnabled(false);
		euclideanButton.setEnabled(false);
		solvableButton.setEnabled(false);
		solveButton.setEnabled(false);
		retryButton.setEnabled(false);
		refreshButton.setEnabled(false);
		
		ascendField.setEditable(false);
		descendField.setEditable(false);
		thresholdField.setEditable(false);
		startXField.setEditable(false);
		startYField.setEditable(false);
		endXField.setEditable(false);
		endYField.setEditable(false);
		
		maze0Field.setEditable(false);
		maze1Field.setEditable(false);
		maze2Field.setEditable(false);
		maze3Field.setEditable(false);
		maze4Field.setEditable(false);
		maze5Field.setEditable(false);
		maze6Field.setEditable(false);
		generateButton.setEnabled(false);
	}
	
	/**
	 * Sets the picture inside the picture label.
	 * @param imageName - the name of the image to be set
	 * @param IO - whether its from the input or output folder
	 */
	protected void setIOPicture (String imageName, String IO) {
	    File file = new File("/home/zhang156/workspace/MazeSolver1.3/" + IO + "/" + imageName + ".bmp");
	    Image image = null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
		ImageIcon icon = new ImageIcon(image);
		picture.setIcon(icon);
	}
    
	/**
	 * Gets the list. 
	 * @return list
	 */
    protected JList getList() {
		return list;
	}
    
    /**
     * Clears all text fields inside the GUI into their default values.
     */
    protected void clearFields() {
    	ascendField.setText("Ascending Scaling Factor");
    	descendField.setText("Descending Scaling Factor");
    	thresholdField.setText("Walkable Threshold");
    	startXField.setText("Start X-Coord");
    	startYField.setText("Start Y-Coord");
    	endXField.setText("End X-Coord");
    	endYField.setText("End Y-Coord");
    	
    	maze0Field.setText("maze to add");
    	maze1Field.setText("maze to add");
    	maze2Field.setText("maze to add");
    	maze3Field.setText("maze to add");
    	maze4Field.setText("maze to add");
    	maze5Field.setText("maze to add");
    	maze6Field.setText("maze to add");
    }
    
}
