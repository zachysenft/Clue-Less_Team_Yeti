package UI;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
import java.util.HashMap;
import java.util.Map;
import java.awt.event.MouseMotionAdapter;


class Token {
		
		int x, y, width, height;
		Color color = Color.RED;
		
		public Token (int x, int y, int width, int height, Color color) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.color = color;
			
		}
		
		public void paint(Graphics g) {
			g.setColor(color);
			g.fillOval(x, y, width, height);
		}		
	}

public class GUI extends JPanel {
	
	int x = 10;
	int y = 25;
	int roomSize = 120;
	int hallsizeX = 30;
	int hallsizeY = 60;
	
	private DrawCanvas canvas;
	private Token player1;
	private Token player2;
	private Token player3;
	private Token player4;
	private Token player5;
	private Token player6;


	public GUI () {
		
		player1 = new Token (330, 35, 20, 20, Color.RED);
		player2 = new Token (460, 165, 20, 20, Color.ORANGE);
		player3 = new Token (330, 475, 20, 20, Color.GRAY);
		player4 = new Token (150, 475, 20, 20, Color.GREEN);
		player5 = new Token (20, 345, 20, 20, Color.BLUE);
		player6 = new Token (20, 165, 20, 20, Color.MAGENTA);
		
		canvas = new DrawCanvas();
		canvas.setPreferredSize(new Dimension(525, 525));
		
		SwingUtilities.isEventDispatchThread();
     JFrame f = new JFrame("Single Player Game");
     f.setPreferredSize(new Dimension(600, 600));
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
     f.add(new DrawCanvas());
     f.pack();
     f.setVisible(true);
	}
	
	
	public Token getPlayer1() {
		return player1;
	}
	
	public Token getPlayer2() {
		return player2;
	}
	
	public Token getPlayer3() {
		return player3;
	}
	
	public Token getPlayer4() {
		return player4;
	}
	
	public Token getPlayer5() {
		return player5;
	}
	
	public Token getPlayer6() {
		return player6;
	}
	
	public void move (String charName, String loc) {
		Token player = null;
		int newx = 0, newy = 0;
		
		 if (charName.equals("Miss Scarlet")) {
			player = player1;
			if (loc.equals( "Study")) { newx = 20; newy = 60;}
			else if (loc.equals( "Library")) { newx = 20; newy = 240;}
			else if (loc.equals( "Conservatory")) { newx = 20; newy = 420;}
			else if (loc.equals( "Hall")) { newx = 200; newy = 60;}
			else if (loc.equals( "Billiard Room")) {newx = 200; newy = 240;}
			else if (loc.equals( "Ballroom")) {newx = 200; newy = 420;}
			else if (loc.equals( "Lounge")) { newx = 380; newy = 60;}
			else if (loc.equals( "Dining Room")) { newx = 380; newy = 240;}
			else if (loc.equals( "Kitchen")) { newx = 380; newy = 420;}			
			else if (loc.equals( "Study-Hall Hallway")) { newx = 150; newy = 75;}
			else if (loc.equals( "Hall-Lounge Hallway")) {newx = 330; newy = 75;}
			else if (loc.equals( "Study-Library Hallway")) { newx = 60; newy = 165;}
			else if (loc.equals( "Hall-Billiard Hallway")) {newx = 240; newy = 165;}
			else if (loc.equals( "Lounge-Dining Hallway")) { newx = 420; newy = 165;}
			else if (loc.equals( "Library-Billiard Hallway")) {newx = 150; newy = 255;}
			else if (loc.equals( "Billiard-Dining Hallway")) {newx = 330; newy = 255;}
			else if (loc.equals( "Library-Conservatory Hallway")) {newx = 60; newy = 345;}
			else if (loc.equals( "Billiard-Ballroom Hallway")) {newx = 240; newy = 345;}
			else if (loc.equals( "Dining-Kitchen Hallway")) { newx =420; newy = 345;}
			else if (loc.equals( "Conservatory-Ballroom Hallway")) {newx = 150; newy = 435;}
			else if (loc.equals( "Ballroom-Kitchen Hallway")) {newx = 330; newy = 435;}
		}
		else if (charName.equals("Colonel Mustard")) {
			player = player2;
			if (loc.equals( "Study"))  { newx = 60; newy = 60;}
			else if (loc.equals( "Library")) { newx = 60; newy = 240;}
			else if (loc.equals( "Conservatory")) {newx = 60; newy = 420;}
			else if (loc.equals( "Hall")) {newx = 240; newy = 60;}
			else if (loc.equals( "Billiard Room")) { newx = 240; newy = 240;}
			else if (loc.equals( "Ballroom")) {newx = 240; newy = 420;}
			else if (loc.equals( "Lounge")) {newx = 420; newy = 60;}
			else if (loc.equals( "Dining Room")) {newx = 420; newy = 240;}
			else if (loc.equals( "Kitchen")) {newx = 420; newy = 420;}
			else if (loc.equals( "Study-Hall Hallway")) {newx = 150; newy = 75;}
			else if (loc.equals( "Hall-Lounge Hallway")) {newx = 330; newy = 75;}
			else if (loc.equals( "Study-Library Hallway")) {newx = 60; newy = 165;}
			else if (loc.equals( "Hall-Billiard Hallway")) {newx = 240; newy = 165;}
			else if (loc.equals( "Lounge-Dining Hallway")) {newx = 420; newy = 165;}
			else if (loc.equals( "Library-Billiard Hallway")) {newx = 150; newy = 255;}
			else if (loc.equals( "Billiard-Dining Hallway")) {newx = 330; newy = 255;}
			else if (loc.equals( "Library-Conservatory Hallway")) {newx = 60; newy = 345;}
			else if (loc.equals( "Billiard-Ballroom Hallway")) {newx = 240; newy = 345;}
			else if (loc.equals( "Dining-Kitchen Hallway")) {newx = 420; newy = 345;}
			else if (loc.equals( "Conservatory-Ballroom Hallway")) {newx = 150; newy = 435;}
			else if (loc.equals( "Ballroom-Kitchen Hallway")) {newx = 330; newy = 435;}
		}
		else if (charName.equals("Mrs. White")) {
			player = player3;
			
			if (loc.equals( "Study")) {newx = 100; newy = 60;}
			else if (loc.equals( "Library")) {newx = 100; newy = 240;}
			else if (loc.equals( "Conservatory")) {newx = 100; newy = 420;}
			else if (loc.equals( "Hall")) {newx = 280; newy = 60;}
			else if (loc.equals( "Billiard Room")) {newx = 280; newy = 240;}
			else if (loc.equals( "Ballroom")) {newx = 280; newy = 420;}
			else if (loc.equals( "Lounge")) { newx = 460; newy = 60;}
			else if (loc.equals( "Dining Room")) {newx = 460; newy = 240;}
			else if (loc.equals( "Kitchen")) {newx = 460; newy = 420;}
			else if (loc.equals( "Study-Hall Hallway")) { newx = 150; newy = 75;}
			else if (loc.equals( "Hall-Lounge Hallway")) {newx = 330; newy = 75;}
			else if (loc.equals( "Study-Library Hallway")) { newx = 60; newy = 165;}
			else if (loc.equals( "Hall-Billiard Hallway")) { newx = 240; newy = 165;}
			else if (loc.equals( "Lounge-Dining Hallway")) { newx = 420; newy = 165;}
			else if (loc.equals( "Library-Billiard Hallway")) { newx = 150; newy = 255;}
			else if (loc.equals( "Billiard-Dining Hallway")) {newx = 330; newy = 255;}
			else if (loc.equals( "Library-Conservatory Hallway")) {newx = 60; newy = 345;}
			else if (loc.equals( "Billiard-Ballroom Hallway")) { newx = 240; newy = 345;}
			else if (loc.equals( "Dining-Kitchen Hallway")) { newx = 420; newy = 345;}
			else if (loc.equals( "Conservatory-Ballroom Hallway")) {newx = 150; newy = 435;}
			else if (loc.equals( "Ballroom-Kitchen Hallway")) { newx = 330; newy = 435;}
		}
		else if (charName.equals("Mr. Green")) {
			player = player4;
			if (loc.equals( "Study")) { newx = 20; newy = 100;}
			else if (loc.equals( "Library")) { newx = 20; newy = 280;}
			else if (loc.equals( "Conservatory")) { newx = 20; newy = 460;}
			else if (loc.equals( "Hall")) { newx = 200; newy = 100;}
			else if (loc.equals( "Billiard Room")) { newx = 200; newy = 280;}
			else if (loc.equals( "Ballroom")) { newx = 200; newy = 460;}
			else if (loc.equals( "Lounge")) { newx = 380; newy = 100;}
			else if (loc.equals( "Dining Room")) { newx = 380; newy = 280;}
			else if (loc.equals( "Kitchen")) { newx = 380; newy = 460;}
			else if (loc.equals( "Study-Hall Hallway")) {newx = 150; newy = 75;}
			else if (loc.equals( "Hall-Lounge Hallway")) { newx = 330; newy = 75;}
			else if (loc.equals( "Study-Library Hallway")) { newx = 60; newy = 165;}
			else if (loc.equals( "Hall-Billiard Hallway")) { newx = 240; newy = 165;}
			else if (loc.equals( "Lounge-Dining Hallway")) { newx = 420; newy = 165;}
			else if (loc.equals( "Library-Billiard Hallway")) { newx = 150; newy = 255;}
			else if (loc.equals( "Billiard-Dining Hallway")) { newx = 330; newy = 255;}
			else if (loc.equals( "Library-Conservatory Hallway")) { newx = 60; newy = 345;}
			else if (loc.equals( "Billiard-Ballroom Hallway")) { newx = 240; newy = 345;}
			else if (loc.equals( "Dining-Kitchen Hallway")) { newx = 420; newy = 345;}
			else if (loc.equals( "Conservatory-Ballroom Hallway")) { newx = 150; newy = 435;}
			else if (loc.equals( "Ballroom-Kitchen Hallway")) { newx = 330; newy = 435;}
		}
		else if (charName.equals("Mrs. Peacock")) {
			player = player5;
			if (loc.equals( "Study")) { newx = 60; newy = 100;}
			else if (loc.equals( "Library")) { newx = 60; newy = 280;}
			else if (loc.equals( "Conservatory")) { newx = 60; newy = 460;}
			else if (loc.equals( "Hall")) { newx = 240; newy = 100;}
			else if (loc.equals( "Billiard Room")) { newx = 240; newy = 280;}
			else if (loc.equals( "Ballroom")) { newx = 240; newy = 460;}
			else if (loc.equals( "Lounge")) { newx = 420; newy = 100;}
			else if (loc.equals( "Dining Room")) { newx = 420; newy = 280;}
			else if (loc.equals( "Kitchen")) { newx = 420; newy = 460;}
			else if (loc.equals( "Study-Hall Hallway")) { newx = 150; newy = 75;}
			else if (loc.equals( "Hall-Lounge Hallway")) { newx = 330; newy = 75;}
			else if (loc.equals( "Study-Library Hallway")) { newx = 60; newy = 165;}
			else if (loc.equals( "Hall-Billiard Hallway")) { newx = 240; newy = 165;}
			else if (loc.equals( "Lounge-Dining Hallway")) { newx = 420; newy = 165;}
			else if (loc.equals( "Library-Billiard Hallway")) { newx = 150; newy = 255;}
			else if (loc.equals( "Billiard-Dining Hallway")) { newx = 330; newy = 255;}
			else if (loc.equals( "Library-Conservatory Hallway")) { newx = 60; newy = 345;}
			else if (loc.equals( "Billiard-Ballroom Hallway")) { newx = 240; newy = 345;}
			else if (loc.equals( "Dining-Kitchen Hallway")) { newx = 420; newy = 345;}
			else if (loc.equals( "Conservatory-Ballroom Hallway")) { newx = 150; newy = 435;}
			else if (loc.equals( "Ballroom-Kitchen Hallway")) { newx = 330; newy = 435;}
		}
		else if (charName.equals("Professor Plum")) {
			player = player6;
			if (loc.equals( "Study")) { newx = 100; newy = 100;}
			else if (loc.equals( "Library")) { newx = 100; newy = 280;}
			else if (loc.equals( "Conservatory")) { newx = 100; newy = 460;}
			else if (loc.equals( "Hall")) { newx = 280; newy = 100;}
			else if (loc.equals( "Billiard Room")) { newx = 280; newy = 280;}
			else if (loc.equals( "Ballroom")) { newx = 280; newy = 460;}
			else if (loc.equals( "Lounge")) { newx = 460; newy = 100;}
			else if (loc.equals( "Dining Room")) { newx = 460; newy = 280;}
			else if (loc.equals( "Kitchen")) { newx = 460; newy = 460;}
			else if (loc.equals( "Study-Hall Hallway")) { newx = 150; newy = 75;}
			else if (loc.equals( "Hall-Lounge Hallway")) { newx = 330; newy = 75;}
			else if (loc.equals( "Study-Library Hallway")) { newx = 60; newy = 165;}
			else if (loc.equals( "Hall-Billiard Hallway")) { newx = 240; newy = 165;}
			else if (loc.equals( "Lounge-Dining Hallway")) { newx = 420; newy = 165;}
			else if (loc.equals( "Library-Billiard Hallway")) { newx = 150; newy = 255;}
			else if (loc.equals( "Billiard-Dining Hallway")) { newx = 330; newy = 255;}
			else if (loc.equals( "Library-Conservatory Hallway")) { newx = 60; newy = 345;}
			else if (loc.equals( "Billiard-Ballroom Hallway")) { newx = 240; newy = 345;}
			else if (loc.equals( "Dining-Kitchen Hallway")) { newx = 420; newy = 345;}
			else if (loc.equals( "Conservatory-Ballroom Hallway")) { newx = 150; newy = 435;}
			else if (loc.equals( "Ballroom-Kitchen Hallway")) { newx = 330; newy = 435;}
		  }
		int savedX = player.x;
		player.x = newx;
		int savedY = player.y;
		player.y = newy;
		canvas.repaint(savedX, savedY, player.width, player.height);
		canvas.repaint(player.x, player.y, player.width, player.height);
	}
	
	public void move(Token player, int newx, int newy) {
		
		int savedX = player.x;
		player.x = newx;
		int savedY = player.y;
		player.y = newy;
		canvas.repaint(savedX, savedY, player.width, player.height);
		canvas.repaint(player.x, player.y, player.width, player.height);
	} 
		
		class DrawCanvas extends JPanel {
	        @Override
	        public void paintComponent(Graphics g) {
	           super.paintComponent(g);
	           //setBackground(Color.BLUE);
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
//	           g.setColor(Color.blue);
//	           g.fillOval(100, 50, 20, 20);
//	           g.setColor(Color.pink);
//	           g.fillOval(100, 250, 20, 20);
	           setBorder(BorderFactory.createLineBorder(Color.black));
	           player1.paint(g);
	           player2.paint(g);
	           player3.paint(g);
	           player4.paint(g);
	           player5.paint(g);
	           player6.paint(g);
	        }
	     }
	
 public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
         public void run() {
             //createAndShowGUI(); 
         	new GUI();
         }
     });
     
 }

 public static void createAndShowGUI() {
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



} 