package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class Guitest {
    public Guitest() {
        JFrame frame = new JFrame();
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new java.awt.GridLayout(5, 5));
       
        ImageIcon hHall = null;
        ImageIcon vHall = null;
        ImageIcon study = null;
        ImageIcon hall = null;
        ImageIcon lounge = null;
        ImageIcon library = null;
        ImageIcon billiardrm = null;
        ImageIcon diningrm = null;
        ImageIcon conservatory = null;
        ImageIcon ballrm = null;
        ImageIcon kitchen = null;
        
        try {
        	BufferedImage img = ImageIO.read(getClass().getResource("/cardimages/studyLoc.png")); //    hHall.png"));
        	study = new ImageIcon(img);
        	
        	BufferedImage img2 = ImageIO.read(getClass().getResource("/cardimages/hallLoc.png")); //vHall.png"));
        	hall = new ImageIcon(img2);
        	
        	BufferedImage img3 = ImageIO.read(getClass().getResource("/cardimages/loungeLoc.png")); //    hHall.png"));
        	lounge = new ImageIcon(img3);
        	
        	BufferedImage img4 = ImageIO.read(getClass().getResource("/cardimages/libraryLoc.png")); //vHall.png"));
        	library = new ImageIcon(img4);
        	
        	BufferedImage img5 = ImageIO.read(getClass().getResource("/cardimages/billiardLoc.png")); //    hHall.png"));
        	billiardrm = new ImageIcon(img5);
        	
        	BufferedImage img6 = ImageIO.read(getClass().getResource("/cardimages/diningroomLoc.png")); //vHall.png"));
        	diningrm = new ImageIcon(img6);
        	
        	BufferedImage img7 = ImageIO.read(getClass().getResource("/cardimages/conservatoryLoc.png")); //    hHall.png"));
        	conservatory = new ImageIcon(img7);
        	
        	BufferedImage img8 = ImageIO.read(getClass().getResource("/cardimages/ballroomLoc.png")); //vHall.png"));
        	ballrm = new ImageIcon(img8);
        	
        	BufferedImage img9 = ImageIO.read(getClass().getResource("/cardimages/kitchenLoc.png")); //vHall.png"));
        	kitchen= new ImageIcon(img9);
        	
        	BufferedImage imghall = ImageIO.read(getClass().getResource("/cardimages/hHall.png"));
        	hHall = new ImageIcon(imghall);
        	
        	BufferedImage imgvhall = ImageIO.read(getClass().getResource("/cardimages/vHall.png"));
        	vHall = new ImageIcon(imgvhall);

        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }

        String [][] plcname = { {"Study","", "Hall", "", "Lounge"}, {"", "", "", "",""}, {"Library", "", "Billiard Room","","Dining Room"},{"","","","",""},{"Conservatory", "","Ballroom", "", "Kitchen"}};
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++) {
                JButton button = new JButton(); 
               JLabel lbl; // = new JLabel();
               if ((i==0 && j==1) || (i==0 && j==3) || (i==2 && j==1) || (i==2 && j==3) ||(i==4 && j==1) || (i==4 && j==3)){
            	   lbl = new JLabel(hHall);
            	   button.setIcon(hHall);
                }
               else if( (i==1 && j==0) || (i==1 && j==2) || (i==1 && j==4) ||(i==3 && j==0) || (i==3 && j==2) ||(i==3 && j==4) ) {
            	   lbl = new JLabel(vHall);
            	   button.setIcon(vHall);
               }
               else if (i==0 && j==0){ button.setIcon(study);}
               else if (i==0 && j==2){ button.setIcon(hall);}
               else if (i==0 && j==4){ button.setIcon(lounge);}
               else if (i==2 && j==0){ button.setIcon(library);}
               else if (i==2 && j==2){ button.setIcon(billiardrm);}
               else if (i==2 && j==4){ button.setIcon(diningrm);}
               else if (i==4 && j==0){ button.setIcon(conservatory);}
               else if (i==4 && j==2){ button.setIcon(ballrm);}
               else if (i==4 && j==4){ button.setIcon(kitchen);}
                String string =plcname[i][j]; // +  "" + j;
                lbl = new JLabel(string); //.setText(string);
               
                //button.setLabel(string);
                //button.setName(string);
                //button.addActionListener(
                 //   new ActionListener() {
                  //      public void actionPerformed(ActionEvent e) {
                            //TicTacToe.buttonClicked(button);
                            //TicTacToe.gameRules(button);
                // }
                 //   });
               // button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardPanel.add(button);
                //panel.add(lbl);
            }

        }

        frame.add(boardPanel);
        frame.setSize(300,300);
        frame.setVisible(true);




    }
    public static void main(String[] args) {
    	Guitest gt = new Guitest();
    	
    	
    }
    
}