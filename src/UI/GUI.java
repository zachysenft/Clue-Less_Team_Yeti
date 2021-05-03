package UI;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

public class GUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });
        
    }

    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Single Player Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    } 
}

	
//Somehow make all players in all rooms and just set visible or invisible based on movements
	


    class MyPanel extends JPanel {
   	 
   	 
   	 int x = 10;
   	 int y = 25;
   	 int roomSize = 120;
   	 int hallsizeX = 30;
   	 int hallsizeY = 60;

        public MyPanel() {

            setBorder(BorderFactory.createLineBorder(Color.black));
            
        }


   public Dimension getPreferredSize() {
        return new Dimension(525,525);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        g.drawString("This is the game board - Clue-Less Team Yeti ",10,20);
        g.setColor(Color.BLUE);
        //g.fillRect(250, 250, 200, 200);
        g.setColor(Color.BLACK);
        
        //Drawing all the rooms
        g.drawRect(x, y, roomSize, roomSize); //120 by 120 rectangle offset x 10 and y 25 to begin
        g.drawString("Study", 50, 50);
        g.drawRect(x, y+180, roomSize, roomSize);
        g.drawString("Library", 50, 230);
        g.drawRect(x, y+360, roomSize, roomSize);
        g.drawString("Conservatory", 30, 410);
        g.drawRect(x+180, y, roomSize, roomSize);
        g.drawString("Hall",240, 50);
        g.drawRect(x+180, y+180, roomSize, roomSize);
        g.drawString("Billiard Room", 215, 230);
        g.drawRect(x+180, y+360, roomSize, roomSize);
        g.drawString("Dining Room", 395, 230);
        g.drawRect(x+360, y, roomSize, roomSize);
        g.drawString("Lounge", 410, 50);
        g.drawRect(x+360, y+180, roomSize, roomSize);
        g.drawString("Ballroom", 225, 410);
        g.drawRect(x+360, y+360, roomSize, roomSize);
        g.drawString("Kitchen", 410, 410);
        
        //Drawing all the hallways
        g.drawRect(x+roomSize/2 - 15, y+roomSize, hallsizeX, hallsizeY);
        g.drawRect(x+180+roomSize/2 - 15, y+roomSize, hallsizeX, hallsizeY);
        g.drawRect(x+360+roomSize/2 - 15, y+roomSize, hallsizeX, hallsizeY);
        
        g.drawRect(x+roomSize/2 - 15, y+180+roomSize, hallsizeX, hallsizeY);
        g.drawRect(x+180+roomSize/2 - 15, y+180+roomSize, hallsizeX, hallsizeY);
        g.drawRect(x+360+roomSize/2 - 15, y+180+roomSize, hallsizeX, hallsizeY);
        
        g.drawRect(x+roomSize, y+roomSize/2 - 15, hallsizeY, hallsizeX);
        g.drawRect(x+roomSize+180, y+roomSize/2 - 15, hallsizeY, hallsizeX);
        g.drawRect(x+roomSize, y+180+roomSize/2 - 15, hallsizeY, hallsizeX);
        g.drawRect(x+roomSize+180, y+180+roomSize/2 - 15, hallsizeY, hallsizeX);
        g.drawRect(x+roomSize, y+360+roomSize/2 - 15, hallsizeY, hallsizeX);
        g.drawRect(x+roomSize+180, y+360+roomSize/2 - 15, hallsizeY, hallsizeX);
        
        //Placing the players (dots)
        g.setColor(Color.blue);
        g.fillOval(100, 50, 20, 20);
        g.setColor(Color.pink);
        g.fillOval(100, 250, 20, 20);
        
    }  
} 