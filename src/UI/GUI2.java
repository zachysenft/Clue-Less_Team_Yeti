package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GUI2 {
	
	
	public static void main(String[] args) {
		
		//Must be run and updated from the JFrame, not inside a JPanel...
		
		JFrame f = new JFrame("Example Test");
		JButton b = new JButton("");
		JButton study = new JButton("Study");
		study.setFont(new Font("Arial", Font.BOLD, 10));
		JButton hall = new JButton("Hall");
		JButton lounge = new JButton("Lounge");
		JButton library = new JButton("Library");
		JButton billiard = new JButton("Billiard Room");
		JButton dining = new JButton("Dining Room");
		JButton conservatory = new JButton("Conservatory");
		JButton ballroom = new JButton("Ballroom");
		JButton kitchen = new JButton("Kitchen");
		JButton sl = new JButton("12");
		JButton sh = new JButton("14");
		JButton lc = new JButton("23");
		JButton lb = new JButton("25");
		JButton cb = new JButton("36");
		JButton hb = new JButton("45");
		JButton bb = new JButton("56");
		JButton bk = new JButton("69");
		JButton bd = new JButton("58");
		JButton hl = new JButton("47");
		JButton ld = new JButton("78");
		JButton dk = new JButton("89");
		
		//Creating all room buttons
		study.setBounds(25, 25, 75, 75);
		hall.setBounds(175, 25, 75, 75);
		lounge.setBounds(325, 25, 75, 75);
		library.setBounds(25, 175, 75, 75);
		billiard.setBounds(175, 175, 75, 75);
		dining.setBounds(325, 175, 75, 75);
		conservatory.setBounds(25, 325, 75, 75);
		ballroom.setBounds(175, 325, 75, 75);
		kitchen.setBounds(325, 325, 75, 75);
		
		//Creating all hallway buttons
		sl.setBounds(50, 100, 25, 75);	//vertical hall
		sh.setBounds(100, 50, 75, 25);	//horizontal hall
		lc.setBounds(50, 250, 25, 75);
		bb.setBounds(200, 250, 25, 75);
		lb.setBounds(100, 200, 75, 25);
		cb.setBounds(100, 350, 75, 25);
		hb.setBounds(200, 100, 25, 75);
		bk.setBounds(250, 350, 75, 25);
		bd.setBounds(250, 200, 75, 25);
		hl.setBounds(250, 50, 75, 25);
		ld.setBounds(350, 100, 25, 75);
		dk.setBounds(350, 250, 25, 75);
		
		
		b.setBounds(10, 90, 10, 10);
		b.setBackground(Color.green);
		
		//Adding all the buttons to the frame
		f.add(b);
		f.add(study);
		f.add(hall);
		f.add(lounge);
		f.add(library);
		f.add(billiard);
		f.add(dining);
		f.add(conservatory);
		f.add(ballroom);
		f.add(kitchen);
		f.add(sl);
		f.add(sh);
		f.add(lc);
		f.add(bb);
		f.add(hb);
		f.add(lb);
		f.add(cb);
		f.add(bk);
		f.add(bd);
		f.add(hl);
		f.add(ld);
		f.add(dk);
		
		f.setSize(450, 475);
		
		f.setLayout(null);
	   f.setVisible(true);
		
		
	}
	
	
}

