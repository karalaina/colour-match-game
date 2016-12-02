
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JFrame implements ActionListener {
  Color[] colours; 
  JButton[][] buttons; 
  int numClicks;
  
  public GameBoard(String title, int columns, int rows) {
    //constructor takes in game title and number of columns and rows for the coloured squares 
    //adds components to the GUI
    super(title);                            
    setDefaultCloseOperation(EXIT_ON_CLOSE); 
    int width = (columns*50)+((columns-1)*5)+35;
    int height = (rows*50)+((rows-1)*5)+120;
    setSize(width, height); 
    setLayout(null);
    colours = new Color[] {new Color(18,180,180), new Color(222,31,98), new Color(186,9,244), new Color(10,28,243)};
        
    JPanel p = new JPanel();
    p.setLocation(10, 10+(55*rows));
    p.setSize((55*columns),60);
    p.setBackground(Color.white);
    add(p);
    
    JLabel rowLabel = new JLabel("Row: ");
    p.add(rowLabel);
    JLabel colLabel = new JLabel("Column: ");
    p.add(colLabel);
    JLabel numLabel = new JLabel("Moves: ");
    p.add(numLabel);
    
    buttons = new JButton[rows][columns];
    this.initializeBoard();
  }
  
  public void initializeBoard() {
    //sets up the inital state of the board
    int r_counter = 0;
    int c_counter = 0;
    while (c_counter < buttons[0].length) {
      while (r_counter < buttons.length) { 
        buttons[r_counter][c_counter] = new JButton();
        buttons[r_counter][c_counter].setLocation(10+(55*c_counter),10+(55*r_counter));
        buttons[r_counter][c_counter].setSize(50,50);  
        buttons[r_counter][c_counter].setBackground(setColour());
        add(buttons[r_counter][c_counter]);
        buttons[r_counter][c_counter].addActionListener(this);
        r_counter++;
      }
      r_counter = 0;
      c_counter++;
    }  
  }
  public void resetBoard() {
    //resets the board to random colours and resets counters 
    for(int row=0; row<buttons.length; row++) {
      for (int col=0; col<buttons[0].length; col++) {
        buttons[row][col].setBackground(setColour());
      }
    }
    JPanel panel = (JPanel)this.getContentPane().getComponent(0);
    Component[] components = panel.getComponents();
    JLabel rowLabel = (JLabel)components[0];
    JLabel colLabel = (JLabel)components[1];
    JLabel numLabel = (JLabel)components[2];
    rowLabel.setText("Row: ");
    colLabel.setText("Column: ");
    numLabel.setText("Moves: ");
    numClicks = 0;
  }
        
  private Color setColour() {
    //returns a random colour from the colours array
    int r = (int)(Math.random()*4);
    return colours[r];
  }   
  public void actionPerformed(ActionEvent event){
    //controls actions when a button is clicked including changing counters and colours 
    numClicks++;
    JPanel panel = (JPanel)this.getContentPane().getComponent(0);
    Component[] components = panel.getComponents();
    JLabel rowLabel = (JLabel)components[0];
    JLabel colLabel = (JLabel)components[1];
    JLabel numLabel = (JLabel)components[2];
    
    for(int row=0; row<buttons.length; row++) {
      for (int col=0; col<buttons[0].length; col++) {
        if (event.getSource() == buttons[row][col]) {
          rowLabel.setText("Row: "+(row+1));
          colLabel.setText("Column: "+(col+1));
          numLabel.setText("Moves: "+numClicks);
          this.toggleColour(buttons[row][col]);
          if((row-1) >= 0)
            this.toggleColour(buttons[row-1][col]);
          if((row+1) < buttons.length)
            this.toggleColour(buttons[row+1][col]);
          if((col-1) >= 0)
            this.toggleColour(buttons[row][col-1]);
          if((col+1) < buttons[0].length)
            this.toggleColour(buttons[row][col+1]);
          if(isComplete()) {
            this.displayVictoryFrame();
            this.resetBoard();
          }
        }
      }
    }
  }
  public void toggleColour(JButton b) {
    //changes the colour of a button parameter passed in to the next colour in the colours array
    for (int i=0;i<colours.length;i++) {
      if (b.getBackground() == colours[i]){
        switch (i) {
          case 0: b.setBackground(colours[1]); break;
          case 1: b.setBackground(colours[2]); break;
          case 2: b.setBackground(colours[3]); break;
          case 3: b.setBackground(colours[0]); break;
        }
        break;
      }
    }
  }  
  public boolean isComplete() {
    //checks to see if all buttons on the board are the same colour, if so returns true, else returns false
    boolean flag = true;
    Color topCorner = buttons[0][0].getBackground();
    for(int row=0; row<buttons.length; row++) {
      for (int col=0; col<buttons[0].length; col++) {
        if (buttons[row][col].getBackground() != topCorner)
          flag = false;
      }
    }
    return flag;
  }
  
  public void displayVictoryFrame() {
    //displays the victory frame
    VictoryFrame v = new VictoryFrame("Victory!", numClicks);
    v.setVisible(true);
    
  }
  
  public static void main(String[] args) {
    GameBoard frame = new GameBoard("Colour Game", 4, 4) ; 
    frame.setVisible(true); 
  }
}