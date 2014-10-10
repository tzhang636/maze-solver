package mazeSolver;

import java.awt.BorderLayout;  
import java.awt.Dimension;  
import java.awt.GridLayout;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  

import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JOptionPane;  
import javax.swing.JPanel;  
import javax.swing.JSplitPane;

public class GUIExample implements ActionListener{  

    
    public GUIExample() {
        JFrame window = new JFrame("MazeSolver");  
        window.setSize(1000, 700); 
        
        JSplitPane splitPane = new JSplitPane();
        
        JPanel myPanel = initializePanel();  
        initializeButtons(myPanel);
                
        window.setContentPane(splitPane);
        window.setVisible(true);  
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        
        /*JSplitPane splitPane = new JSplitPane();
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(100, 50);*/

    }  

    private void initializeButtons(JPanel myPanel) {  
        JButton button = new JButton("maze0");  
        button.addActionListener(this);  
        myPanel.add(button, 0); 
        
        
        button = new JButton("maze1");
        button.addActionListener(this);
        myPanel.add(button, 1);  
    }  

    private JPanel initializePanel() {  
        JPanel myPanel = new JPanel();  
        myPanel.setPreferredSize(new Dimension(1000,700));  
        myPanel.setLayout(new GridLayout(9, 9));
        return myPanel;
    }         

    @Override 
    public void actionPerformed(ActionEvent e) {  
        JOptionPane.showMessageDialog(null,   
                    "I was clicked by "+e.getActionCommand(),   
                    "Title here", JOptionPane.INFORMATION_MESSAGE);   
    }  

    public static void main(String[] args) {      
        new GUIExample();  
    }  
}  

