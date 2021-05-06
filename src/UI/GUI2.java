package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GUI2 {
	
	
	public static void main(String[] args) {
		
		//Must be run and updated from the JFrame, not inside a JPanel...
		
		JFrame f = new JFrame("Example Test");
		JButton study = new JButton("Study");
		//study.setFont(new Font("Arial", Font.BOLD, 10));
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
		
		//Player buttons in the study
		JButton studymustard = new JButton("");
		studymustard.setBackground(Color.yellow);
		JButton studywhite = new JButton("");
		studywhite.setBackground(Color.white);
		JButton studyplum = new JButton("");
		studyplum.setBackground(Color.blue);
		JButton studypeacock = new JButton("");
		studypeacock.setBackground(Color.black);
		JButton studygreen = new JButton("");
		studygreen.setBackground(Color.green);
		JButton studyscarlet = new JButton("");
		studyscarlet.setBackground(Color.red);
		
		//Player buttons in the hall
		JButton hallmustard = new JButton();
		hallmustard.setBackground(Color.yellow);
		JButton hallwhite = new JButton();
		hallwhite.setBackground(Color.white);
		JButton hallplum = new JButton();
		hallplum.setBackground(Color.blue);
		JButton hallpeacock = new JButton();
		hallpeacock.setBackground(Color.black);
		JButton hallgreen = new JButton();
		hallgreen.setBackground(Color.green);
		JButton hallscarlet = new JButton();
		hallscarlet.setBackground(Color.red);
		
		//Player buttons in the lounge
		JButton loungemustard = new JButton();
		loungemustard.setBackground(Color.yellow);
		JButton loungewhite = new JButton();
		loungewhite.setBackground(Color.white);
		JButton loungeplum = new JButton();
		loungeplum.setBackground(Color.blue);
		JButton loungepeacock = new JButton();
		loungepeacock.setBackground(Color.black);
		JButton loungegreen = new JButton();
		loungegreen.setBackground(Color.green);
		JButton loungescarlet = new JButton();
		loungescarlet.setBackground(Color.red);
		
		//Player buttons in the conservatory
		JButton consmustard = new JButton();
		consmustard.setBackground(Color.yellow);
		JButton conswhite = new JButton();
		conswhite.setBackground(Color.white);
		JButton consplum = new JButton();
		consplum.setBackground(Color.blue);
		JButton conspeacock = new JButton();
		conspeacock.setBackground(Color.black);
		JButton consgreen = new JButton();
		consgreen.setBackground(Color.green);
		JButton consscarlet = new JButton();
		consscarlet.setBackground(Color.red);
		
		//Player buttons in the ballroom
		JButton ballmustard = new JButton();
		ballmustard.setBackground(Color.yellow);
		JButton ballwhite = new JButton();
		ballwhite.setBackground(Color.white);
		JButton ballplum = new JButton();
		ballplum.setBackground(Color.blue);
		JButton ballpeacock = new JButton();
		ballpeacock.setBackground(Color.black);
		JButton ballgreen = new JButton();
		ballgreen.setBackground(Color.green);
		JButton ballscarlet = new JButton();
		ballscarlet.setBackground(Color.red);
		
		//Player buttons in the kitchen
		JButton kitchenmustard = new JButton();
		kitchenmustard.setBackground(Color.yellow);
		JButton kitchenwhite = new JButton();
		kitchenwhite.setBackground(Color.white);
		JButton kitchenplum = new JButton();
		kitchenplum.setBackground(Color.blue);
		JButton kitchenpeacock = new JButton();
		kitchenpeacock.setBackground(Color.black);
		JButton kitchengreen = new JButton();
		kitchengreen.setBackground(Color.green);
		JButton kitchenscarlet = new JButton();
		kitchenscarlet.setBackground(Color.red);
		
		//Player buttons in the library
		JButton librarymustard = new JButton();
		librarymustard.setBackground(Color.yellow);
		JButton librarywhite = new JButton();
		librarywhite.setBackground(Color.white);
		JButton libraryplum = new JButton();
		libraryplum.setBackground(Color.blue);
		JButton librarypeacock = new JButton();
		librarypeacock.setBackground(Color.black);
		JButton librarygreen = new JButton();
		librarygreen.setBackground(Color.green);
		JButton libraryscarlet = new JButton();
		libraryscarlet.setBackground(Color.red);
		
		//Player buttons in the dining room
		JButton diningmustard = new JButton();
		diningmustard.setBackground(Color.yellow);
		JButton diningwhite = new JButton();
		diningwhite.setBackground(Color.white);
		JButton diningplum = new JButton();
		diningplum.setBackground(Color.blue);
		JButton diningpeacock = new JButton();
		diningpeacock.setBackground(Color.black);
		JButton dininggreen = new JButton();
		dininggreen.setBackground(Color.green);
		JButton diningscarlet = new JButton();
		diningscarlet.setBackground(Color.red);
		
		//Players in the billiard room
		JButton billiardmustard = new JButton();
		billiardmustard.setBackground(Color.yellow);
		JButton billiardwhite = new JButton();
		billiardwhite.setBackground(Color.white);
		JButton billiardplum = new JButton();
		billiardplum.setBackground(Color.blue);
		JButton billiardpeacock = new JButton();
		billiardpeacock.setBackground(Color.black);
		JButton billiardgreen = new JButton();
		billiardgreen.setBackground(Color.green);
		JButton billiardscarlet = new JButton();
		billiardscarlet.setBackground(Color.red);
		
		
		//Creating all room buttons, player buttons in them too
		study.setBounds(25, 25, 75, 75);
		studymustard.setBounds(25, 15, 10, 10);
		studywhite.setBounds(35, 15, 10, 10);
		studyplum.setBounds(45, 15, 10, 10);
		studypeacock.setBounds(55, 15, 10, 10);
		studygreen.setBounds(65, 15, 10, 10);
		studyscarlet.setBounds(75, 15, 10, 10);
		
		hall.setBounds(175, 25, 75, 75);
		hallmustard.setBounds(175, 15, 10, 10);
		hallwhite.setBounds(185, 15, 10, 10);
		hallplum.setBounds(195, 15, 10, 10);
		hallpeacock.setBounds(205, 15, 10, 10);
		hallgreen.setBounds(215, 15, 10, 10);
		hallscarlet.setBounds(225, 15, 10, 10);
		
		lounge.setBounds(325, 25, 75, 75);
		loungemustard.setBounds(325, 15, 10, 10);
		loungewhite.setBounds(335, 15, 10, 10);
		loungeplum.setBounds(345, 15, 10, 10);
		loungepeacock.setBounds(355, 15, 10, 10);
		loungegreen.setBounds(365, 15, 10, 10);
		loungescarlet.setBounds(375, 15, 10, 10);
		
		library.setBounds(25, 175, 75, 75);
		librarymustard.setBounds(15, 175, 10, 10);
		librarywhite.setBounds(15, 185, 10, 10);
		libraryplum.setBounds(15, 195, 10, 10);
		librarypeacock.setBounds(15, 205, 10, 10);
		librarygreen.setBounds(15, 215, 10, 10);
		libraryscarlet.setBounds(15, 225, 10, 10);
		
		billiard.setBounds(175, 175, 75, 75);
		billiardmustard.setBounds(175, 165, 10, 10);
		billiardwhite.setBounds(185, 165, 10, 10);
		billiardplum.setBounds(230, 165, 10, 10);
		billiardpeacock.setBounds(240, 165, 10, 10);
		billiardgreen.setBounds(175, 250, 10, 10);
		billiardscarlet.setBounds(185, 250, 10, 10);
		
		dining.setBounds(325, 175, 75, 75);
		diningmustard.setBounds(400, 175, 10, 10);
		diningwhite.setBounds(400, 185, 10, 10);
		diningplum.setBounds(400, 195, 10, 10);
		diningpeacock.setBounds(400, 205, 10, 10);
		dininggreen.setBounds(400, 215, 10, 10);
		diningscarlet.setBounds(400, 225, 10, 10);
		
		conservatory.setBounds(25, 325, 75, 75);
		consmustard.setBounds(25, 400, 10, 10);
		conswhite.setBounds(35, 400, 10, 10);
		consplum.setBounds(45, 400, 10, 10);
		conspeacock.setBounds(55, 400, 10, 10);
		consgreen.setBounds(65, 400, 10, 10);
		consscarlet.setBounds(75, 400, 10, 10);
		
		ballroom.setBounds(175, 325, 75, 75);
		ballmustard.setBounds(175, 400, 10, 10);
		ballwhite.setBounds(185, 400, 10, 10);
		ballplum.setBounds(195, 400, 10, 10);
		ballpeacock.setBounds(205, 400, 10, 10);
		ballgreen.setBounds(215, 400, 10, 10);
		ballscarlet.setBounds(225, 400, 10, 10);
		
		kitchen.setBounds(325, 325, 75, 75);
		kitchenmustard.setBounds(325, 400, 10, 10);
		kitchenwhite.setBounds(335, 400, 10, 10);
		kitchenplum.setBounds(345, 400, 10, 10);
		kitchenpeacock.setBounds(355, 400, 10, 10);
		kitchengreen.setBounds(365, 400, 10, 10);
		kitchenscarlet.setBounds(375, 400, 10, 10);
		
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
				
		
		//Adding rooms
		f.add(study);
		f.add(hall);
		f.add(lounge);
		f.add(library);
		f.add(billiard);
		f.add(dining);
		f.add(conservatory);
		f.add(ballroom);
		f.add(kitchen);
		
		//Adding hallways
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
		
		//Adding Player buttons in rooms
		f.add(studymustard);
		f.add(studywhite);
		f.add(studyplum);
		f.add(studypeacock);
		f.add(studygreen);
		f.add(studyscarlet);
		f.add(hallmustard);
		f.add(hallwhite);
		f.add(hallplum);
		f.add(hallpeacock);
		f.add(hallgreen);
		f.add(hallscarlet);
		f.add(loungemustard);
		f.add(loungewhite);
		f.add(loungeplum);
		f.add(loungepeacock);
		f.add(loungegreen);
		f.add(loungescarlet);
		f.add(consmustard);
		f.add(conswhite);
		f.add(consplum);
		f.add(conspeacock);
		f.add(consgreen);
		f.add(consscarlet);
		f.add(ballmustard);
		f.add(ballwhite);
		f.add(ballplum);
		f.add(ballpeacock);
		f.add(ballgreen);
		f.add(ballscarlet);
		f.add(kitchenmustard);
		f.add(kitchenwhite);
		f.add(kitchenplum);
		f.add(kitchenpeacock);
		f.add(kitchengreen);
		f.add(kitchenscarlet);
		f.add(librarymustard);
		f.add(librarywhite);
		f.add(libraryplum);
		f.add(librarypeacock);
		f.add(librarygreen);
		f.add(libraryscarlet);
		f.add(diningmustard);
		f.add(diningwhite);
		f.add(diningplum);
		f.add(diningpeacock);
		f.add(dininggreen);
		f.add(diningscarlet);
		f.add(billiardmustard);
		f.add(billiardwhite);
		f.add(billiardplum);
		f.add(billiardpeacock);
		f.add(billiardgreen);
		f.add(billiardscarlet);
		
		f.setSize(450, 475);
		
		f.setLayout(null);
	   f.setVisible(true);
		
		
	}
	
	
}

