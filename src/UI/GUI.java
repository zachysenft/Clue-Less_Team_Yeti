// package UI;

// import javax.swing.SwingUtilities;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.imageio.ImageIO;
// import javax.swing.BorderFactory;
// import javax.swing.ImageIcon;
// import javax.swing.JButton;

// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.Graphics;
// import java.awt.Label;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseMotionListener;
// import java.awt.image.BufferedImage;
// import java.awt.event.MouseMotionAdapter;

// public class GUI {

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 createAndShowGUI(); 
//             }
//         });
//         //new MyPanel().updatepos(100, 50, 20, 20);
//     }

//     private static void createAndShowGUI() {
//         System.out.println("Created GUI on EDT? "+
//         SwingUtilities.isEventDispatchThread());
//         JFrame f = new JFrame("Single Player Game");
//         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//         f.add(new MyPanel());
        
//         f.pack();
//         f.setVisible(true);
//     } 
// }

	
// //Somehow make all players in all rooms and just set visible or invisible based on movements
	


//     class MyPanel extends JPanel {
//    	 public static int xVal=0, yVal=0, widthV=0, heightV=0;
//    	 public  Color colorV;
   	 
//    	 JLabel label = new JLabel();
//    	 int x = 8; //10;
//    	 int y = 15; //25;
//    	 int roomSize = 100; //120;
//    	 int hallsizeX = 30; //30;
//    	 int hallsizeY = 60; //60;

//         public MyPanel() {

//             setBorder(BorderFactory.createLineBorder(Color.black)); 
           
//         }


//    public Dimension getPreferredSize() {
//         return new Dimension(455, 455); //(525,525); 
//     }
   
//      //private  Graphics g;
//     //protected
//     public void paintComponent(Graphics g) {
    	
//     	JButton  red = new JButton();
//     	red.setForeground(Color.RED);
//     	red.setVisible(false);
//     	JButton  blue = new JButton();
//     	blue.setForeground(Color.BLUE);
//     	blue.setVisible(false);
//     	JButton  green = new JButton();
//     	green.setForeground(Color.GREEN);
//     	green.setVisible(false);
//     	JButton  yellow = new JButton();
//     	yellow.setForeground(Color.YELLOW);
//     	yellow.setVisible(false);   	
    	
    	
    	
//         super.paintComponent(g);       
//         g.drawString("This is the game board - Clue-Less Team Yeti ",10,20);
//         g.setColor(Color.white); //BLUE);
//         g.fillRect(0, 0, 525, 525);
//         g.setColor(Color.BLACK);
        
//         //Drawing all the rooms
//         g.drawRect(x, y, roomSize, roomSize); //120 by 120 rectangle offset x 10 and y 25 to begin
//         g.drawString("Study", 50, 50);  
//         g.drawRect(x, y+160, roomSize, roomSize); //g.drawRect(x, y+180, roomSize, roomSize); 
//         g.drawString("Library", 50, 230);
//         g.drawRect(x, y+320, roomSize, roomSize); //g.drawRect(x, y+360, roomSize, roomSize);
//         g.drawString("Conservatory", 30, 410);  //g.drawString("Conservatory", 30, 410);
//         g.drawRect(x+160, y, roomSize, roomSize); //g.drawRect(x+180, y, roomSize, roomSize);
//         g.drawString("Hall",200, 50);  //g.drawString("Hall",240, 50);
//         g.drawRect(x+160, y+160, roomSize, roomSize); //g.drawRect(x+180, y+180, roomSize, roomSize);
//         g.drawString("Billiard Room", 195, 230);   //g.drawString("Billiard Room", 215, 230);
//         g.drawRect(x+160, y+320, roomSize, roomSize);  //g.drawRect(x+180, y+360, roomSize, roomSize);
//         g.drawString("Dining Room", 355, 230);    //g.drawString("Dining Room", 395, 230);
//         g.drawRect(x+322, y, roomSize, roomSize);  //g.drawRect(x+360, y, roomSize, roomSize);
//         g.drawString("Lounge", 380, 50);   //g.drawString("Lounge", 410, 50);
//         g.drawRect(x+323, y+160, roomSize, roomSize);  //g.drawRect(x+360, y+180, roomSize, roomSize);
//         g.drawString("Ballroom", 205, 410);  //g.drawString("Ballroom", 225, 410);
//         g.drawRect(x+321, y+320, roomSize, roomSize);   //g.drawRect(x+360, y+360, roomSize, roomSize);
//         g.drawString("Kitchen", 355, 410);  //g.drawString("Kitchen", 410, 410);
        
//         //Drawing all the hallways
//         g.drawRect(x+roomSize/2 - 5, y+roomSize, hallsizeX, hallsizeY); //g.drawRect(x+roomSize/2 - 15, y+roomSize, hallsizeX, hallsizeY);
//         g.drawRect(x+160+roomSize/2 - 15, y+roomSize, hallsizeX, hallsizeY); //g.drawRect(x+180+roomSize/2 - 15, y+roomSize, hallsizeX, hallsizeY);
//         g.drawRect(x+320+roomSize/2 - 15, y+roomSize, hallsizeX, hallsizeY);
        
//         g.drawRect(x+roomSize/2 - 15, y+160+roomSize, hallsizeX, hallsizeY);  //g.drawRect(x+roomSize/2 - 15, y+180+roomSize, hallsizeX, hallsizeY);
//         g.drawRect(x+160+roomSize/2 - 15, y+160+roomSize, hallsizeX, hallsizeY);  //g.drawRect(x+180+roomSize/2 - 15, y+180+roomSize, hallsizeX, hallsizeY);
//         g.drawRect(x+320+roomSize/2 - 15, y+160+roomSize, hallsizeX, hallsizeY); // g.drawRect(x+360+roomSize/2 - 15, y+180+roomSize, hallsizeX, hallsizeY);
        
//         g.drawRect(x+roomSize, y+roomSize/2 - 15, hallsizeY, hallsizeX);  //g.drawRect(x+roomSize, y+roomSize/2 - 15, hallsizeY, hallsizeX);
//         g.drawRect(x+roomSize+161, y+roomSize/2 - 15, hallsizeY, hallsizeX);  //g.drawRect(x+roomSize+180, y+roomSize/2 - 15, hallsizeY, hallsizeX);
//         g.drawRect(x+roomSize, y+160+roomSize/2 - 15, hallsizeY, hallsizeX);  //g.drawRect(x+roomSize, y+180+roomSize/2 - 15, hallsizeY, hallsizeX);
//         g.drawRect(x+roomSize+162, y+160+roomSize/2 - 15, hallsizeY, hallsizeX);  //g.drawRect(x+roomSize+180, y+180+roomSize/2 - 15, hallsizeY, hallsizeX);
//         g.drawRect(x+roomSize, y+320+roomSize/2 - 15, hallsizeY, hallsizeX);  // g.drawRect(x+roomSize, y+360+roomSize/2 - 15, hallsizeY, hallsizeX);
//         g.drawRect(x+roomSize+161, y+320+roomSize/2 - 15, hallsizeY, hallsizeX);  //g.drawRect(x+roomSize+180, y+360+roomSize/2 - 15, hallsizeY, hallsizeX);
        
//         //Placing the players (dots)
//        // g.setColor(Color.blue);
//         //g.fillOval(100, 50, 20, 20);
//         //g.setColor(Color.white);
//         //g.fillOval(100, 50, 20, 20);
        
//         //g.fillOval(100, 50, 20, 20);
//         //g.setColor(Color.pink);
       
        
//     }
//    //public void updatepos(MyPanel mp, int x, int y, int z, int a ) {
// 	   public void draw() {  //Graphics g){
// 	   		Graphics g = this.getGraphics();
// 	        super.paint(g);
// 	        g.fillOval(xVal, yVal, widthV, heightV); //150, 20, 20);
// 	        g.setColor(colorV); //    Color.RED);
// 	    }
//    /*
// 	 public int getXVal() {
// 		  return xVal;
// 	  }
// 	 public void setXVal(int val) {
// 		  xVal = val;
// 	  }
// 	 public int getYVal() {
// 		  return yVal;
// 	  }
// 	 public void setYVal(int val) {
// 		  yVal = val;
// 	  }
// 	 public Color getColor() {
// 		  return colorV;
// 	  }
// 	 public void setColor(Color val) {
// 		  colorV = val;
// 	  }
// 	 public int getWidth() {
// 		  return widthV;
// 	  }
// 	 public void setWidth(int val) {
// 		  widthV = val;
// 	  }
// 	 public int getHeight() {
// 		  return heightV;
// 	  }
// 	 public void setHeight(int val) {
// 		  heightV = val;
// 	  }
	   
// 	*/   
	   
	   
	   
// 	   /*
// 	    * for (int i = 0; i < 5; i++) {
//     for(int j = 0; j < 5; j++) {
//         JButton button = new JButton(); 
//        //JLabel lbl; // = new JLabel();
//        if (i==0 && j==1) { studyHall.setIcon(studyHallH);  studyHall.setSize(10,10); boardPanel.add(studyHall);}
    		   
//        else if (i==0 && j==3) { hallLounge.setIcon(hallLoungeH);  hallLounge.setSize(10,10); boardPanel.add( hallLounge);}
    	  
//        else if(i==2 && j==1) { libBilliard.setIcon(libBillH);  libBilliard.setSize(10,10); boardPanel.add(libBilliard);}
    	   
//        else if(i==2 && j==3) { billiardDining.setIcon(billDiningH);  billiardDining.setSize(10,10); boardPanel.add(billiardDining);}
//     	else if(i==4 && j==1) { consBallrm.setIcon(consBallH);  consBallrm.setSize(10,10); boardPanel.add(consBallrm);}
//     	else if	(i==4 && j==3) {ballrmKitchen.setIcon(ballKitchenH);  ballrmKitchen.setSize(10,10); boardPanel.add(ballrmKitchen);}
//     	else if (i==1 && j==0) {studyLib.setIcon(studyLibH);  studyLib.setSize(10,10);  boardPanel.add(studyLib);}
//     	else if (i==1 && j==2) {hallBilliard.setIcon(hallBillH);  hallBilliard.setSize(10,10);  boardPanel.add(hallBilliard);}
//     	else if (i==1 && j==4) {loungeDining.setIcon(loungeDinH);  loungeDining.setSize(10,10);  boardPanel.add(loungeDining);}
//     	else if (i==3 && j==0) {librCons.setIcon(libConsH);  librCons.setSize(10,10);  boardPanel.add(librCons);}
//     	else if (i==3 && j==2) {billiardBallrm.setIcon(billBallH);  billiardBallrm.setSize(10,10); boardPanel.add(billiardBallrm);} 
//     	else if (i==3 && j==4) {diningKitchen.setIcon(dinKitchenH);  diningKitchen.setSize(10,10); boardPanel.add(diningKitchen);} 
//     	   //lbl = new JLabel(vHall);
//     	   //button.setIcon(vHall); button.setSize(20,20);
//     	   //boardPanel.add(button);
//        else if (i==0 && j==0){ studyBtn.setIcon(study);  studyBtn.setSize(10,10); boardPanel.add(studyBtn);}
//        else if (i==0 && j==2){ hallBtn.setIcon(hall);  hallBtn.setSize(10,10);  boardPanel.add(hallBtn);}
//        else if (i==0 && j==4){ loungeBtn.setIcon(lounge);  loungeBtn.setSize(10,10); boardPanel.add(loungeBtn);}
//        else if (i==2 && j==0){ libraryBtn.setIcon(library);  libraryBtn.setSize(10,10); boardPanel.add(libraryBtn);}
//        else if (i==2 && j==2){ billiardBtn.setIcon(billiardrm);  billiardBtn.setSize(10,10); boardPanel.add(billiardBtn);}
//        else if (i==2 && j==4){ diningrmBtn.setIcon(diningrm);  diningrmBtn.setSize(10,10); boardPanel.add(diningrmBtn);}
//        else if (i==4 && j==0){ conservatoryBtn.setIcon(conservatory);  conservatoryBtn.setSize(10,10); boardPanel.add(conservatoryBtn);}
//        else if (i==4 && j==2){ ballrmBtn.setIcon(ballrm);  ballrmBtn.setSize(10,10); boardPanel.add(ballrmBtn); }
//        else if (i==4 && j==4){ kitchenBtn.setIcon(kitchen);  kitchenBtn.setSize(10,10);  boardPanel.add(kitchenBtn);}
//        else {boardPanel.add(button);} 
//        //String string =plcname[i][j]; // +  "" + j;
//         //lbl = new JLabel(string); //.setText(string);
       
//        // button.setLabel(string);
//         //button.setName(string);
//         //button.addActionListener(
//          //   new ActionListener() {
//           //      public void actionPerformed(ActionEvent e) {
//                     //TicTacToe.buttonClicked(button);
//                     //TicTacToe.gameRules(button);
//         // }
//          //   });
//        // button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       
//         //panel.add(lbl);
//     }
// }
// ImageIcon dinKitchenH = null;
// ImageIcon loungeDinH = null;
// ImageIcon billBallH = null;
// ImageIcon hallBillH = null;
// ImageIcon libConsH = null;
// ImageIcon studyLibH = null;
// ImageIcon ballKitchenH = null;
// ImageIcon consBallH = null;
// ImageIcon billDiningH = null;
// ImageIcon libBillH = null;
// ImageIcon hallLoungeH= null;
// ImageIcon studyHallH = null;
// try {
// 	BufferedImage imgdin = ImageIO.read(getClass().getResource("/cardimages/dining-kitchen.png")); //    hHall.png"));
// 	dinKitchenH = new ImageIcon(imgdin);
	
// 	BufferedImage imglou = ImageIO.read(getClass().getResource("/cardimages/lounge-dining.png")); //vHall.png"));
// 	loungeDinH = new ImageIcon(imglou);
	
// 	BufferedImage imgbill = ImageIO.read(getClass().getResource("/cardimages/billiard-ballrm.png")); //    hHall.png"));
// 	billBallH = new ImageIcon(imgbill);
	
// 	BufferedImage imghallB = ImageIO.read(getClass().getResource("/cardimages/hall-billiard.png")); //vHall.png"));
// 	hallBillH = new ImageIcon(imghallB);
	
// 	BufferedImage imglib = ImageIO.read(getClass().getResource("/cardimages/libr-cons.png")); //    hHall.png"));
// 	libConsH = new ImageIcon(imglib);
	
// 	BufferedImage imgstdy = ImageIO.read(getClass().getResource("/cardimages/study-lib.png")); //vHall.png"));
// 	studyLibH = new ImageIcon(imgstdy);
	
// 	BufferedImage imgball = ImageIO.read(getClass().getResource("/cardimages/ballroom-kitchen.png")); //    hHall.png"));
// 	ballKitchenH = new ImageIcon(imgball);
	
// 	BufferedImage imgcons = ImageIO.read(getClass().getResource("/cardimages/conservatory-ballrm.png")); //vHall.png"));
// 	consBallH= new ImageIcon(imgcons);
	
// 	BufferedImage imgbidin = ImageIO.read(getClass().getResource("/cardimages/billiard-Dining.png")); //vHall.png"));
// 	billDiningH = new ImageIcon(imgbidin);
	
// 	BufferedImage imglibb = ImageIO.read(getClass().getResource("/cardimages/library-billiard.png"));
// 	libBillH = new ImageIcon(imglibb);
	
// 	BufferedImage imglng = ImageIO.read(getClass().getResource("/cardimages/hall-lounge.png"));
// 	hallLoungeH = new ImageIcon(imglng);

// 	BufferedImage imgstdyH = ImageIO.read(getClass().getResource("/cardimages/study-hall.png"));
// 	 studyHallH = new ImageIcon(imgstdyH);
	 
// 	 private static JButton diningKitchen= new JButton();
// 	private static JButton loungeDining = new JButton();
// 	private static JButton billiardBallrm = new JButton();
// 	private static JButton hallBilliard = new JButton();
// 	private static JButton librCons = new JButton();
// 	private static JButton studyLib = new JButton();
// 	private static JButton ballrmKitchen = new JButton();
// 	private static JButton consBallrm = new JButton();
// 	private static JButton billiardDining = new JButton();
// 	private static JButton libBilliard = new JButton();
// 	private static JButton hallLounge = new JButton();
// 	private static JButton studyHall = new JButton();
// 	    */
// }
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
	
		
		/*public void move(int newx, int newy) {
			
			x = newx;
			y = newy;
			
		}*/
		
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
