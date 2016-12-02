
import javax.swing.*; 
import java.awt.event.*; 

/**A frame that simply displays a friendly message and a number.
  * Contains a button for disposing of the frame.*/
public class VictoryFrame extends JFrame{
  
  /**Constructor takes in a title for the window 
    * and a number to be displayed. Builds components and 
    * puts them in the GUI. Does not display the gui.*/
  public VictoryFrame(String title,int score){
    super(title);
    setLayout(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    //button for disposing (works like clicking the close button)
    JButton endButton = new JButton("Play again");
    endButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    add(endButton);
    
    //label for displaying the message with a number
    JLabel label = new JLabel("You Won in "+score+" moves!");
    label.setSize(150,20);
    label.setLocation(10,30);
    endButton.setLocation(15,60);
    endButton.setSize(120,30);
    add(label);
    
    
    setSize(170,150);
  }   
}