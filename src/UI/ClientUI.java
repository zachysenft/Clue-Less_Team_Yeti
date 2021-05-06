package UI;

/*
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import clueless.BoardGame;
import clueless.Card;
import clueless.Location;
import clueless.Location.LocationType;
import clueless.Player;
import clueless.PlayerMessage;
import clueless.PlayerMessage.DealCardMessage;
import clueless.PlayerMessage.EndTurnMessage;
import clueless.PlayerMessage.MoveMsg;
import clueless.PlayerMessage.OtherMessage;
import clueless.PlayerMessage.SuggestionOrAccusation;
import clueless.PlayerMessage.SuggestionResponse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
*/


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import clueless.BoardGame;
import clueless.Card;
import clueless.Location;
import clueless.Location.LocationType;
import clueless.Player;
import clueless.PlayerMessage;
import clueless.PlayerMessage.DealCardMessage;
import clueless.PlayerMessage.EndTurnMessage;
import clueless.PlayerMessage.MoveMsg;
import clueless.PlayerMessage.OtherMessage;
import clueless.PlayerMessage.SuggestionOrAccusation;
import clueless.PlayerMessage.SuggestionResponse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;

public class ClientUI extends JFrame implements ActionListener {	
/*
	// Socket Related
	private static Socket clientSocket;
	private static int PORT;
	private PrintWriter out;
	private OutputStream outputStream;
	private static ObjectOutputStream objectOutputStream;
	InputStream inputStream;
	
	private static String turn;
	private static boolean gameEnded = false;
	private static boolean canMakeSuggestion = true;
	
	//list of images
	private static ImageIcon[] imgList = new ImageIcon[21];
	private static HashMap<String, Integer> nameToCardImageMap = new HashMap<>();
	
	
	// JFrame related
	private static JPanel contentPane;
	private JTextArea txtAreaLogs;
	private static JTextArea playersCard;
	private JLabel cardLabel;
	private JButton btnStart;
	private static JButton btnStartGame;
	private JPanel panelNorth;
	private JLabel lblChatClient;
	private JPanel panelNorthSouth;
	private JPanel panelNorthWest;
	private JPanel pannelCollection;
	private JPanel movePanel;
	private static JButton suggBtn;
	private static JButton moveBtn;
	private static JButton accusationBtn;
	private static JButton endBtn;
	private JLabel lblPort;
	private JLabel lblName;
	private JLabel cardsLabel;
	private static JPanel cardsPanel;
	private static JLabel imageLabel1;
	private static JLabel imageLabel2;
	private GridLayout gl1;
	private GridLayout gl2;
	private JLabel lblNames;
	private JPanel panelSouth;
	private static JButton btnSend;
	private JTextField txtMessage;
	private JTextField txtNickname;
	private JTextField txtPort;
	private static String clientName;
	private JComboBox<String> personList; 
    private JComboBox<String> weaponList; 
    private JComboBox<String> locationList;
    private JComboBox<String> moveLocation;
*/
	//boardGame image variables
	private static JPanel boardPanel = new JPanel();
	private static JButton studyBtn = new JButton();
	private static JButton hallBtn = new JButton();
	private static JButton loungeBtn= new JButton();
	private static JButton libraryBtn = new JButton();
	private static JButton billiardBtn = new JButton();
	private static JButton diningrmBtn = new JButton();
	private static JButton conservatoryBtn = new JButton();
	private static JButton ballrmBtn = new JButton();
	private static JButton kitchenBtn = new JButton();
	
	private static JButton diningKitchen= new JButton();
	private static JButton loungeDining = new JButton();
	private static JButton billiardBallrm = new JButton();
	private static JButton hallBilliard = new JButton();
	private static JButton librCons = new JButton();
	private static JButton studyLib = new JButton();
	private static JButton ballrmKitchen = new JButton();
	private static JButton consBallrm = new JButton();
	private static JButton billiardDining = new JButton();
	private static JButton libBilliard = new JButton();
	private static JButton hallLounge = new JButton();
	private static JButton studyHall = new JButton();
	
	private static MyPanel mp; // = new MyPanel();
	//location values
	private static int studyx1 = 60, studyx2=50, studyy1=90, studyy2=80, 
						hallx1 = 180, hallx2 = 190, hally1 = 90, hally2 = 90,
						loungex1 = 340, loungex2 = 360, loungey1 = 90, loungey2 = 90,
						libraryx1 = 60, libraryx2 = 70, libraryy1 = 210, libraryy2 = 230,
						billiardx1 = 180, billiardx2 = 190, billiardy1 = 210, billiardy2 = 210,
						diningrmx1 = 340, diningrmx2=350, diningrmy1=210, diningrmy2=210,
						conservatoryx1= 60, conservatoryx2 = 60, conservatoryy1= 380, conservatoryy2=370,
						ballroomx1= 210, ballroomx2 = 215, ballroomy1 = 380, ballroomy2 = 380,
						kitchenx1 = 340, kitchenx2 = 345, kitcheny1= 380, kitcheny2 = 380;
	// Socket Related
		private static Socket clientSocket;
		private static int PORT;
		private PrintWriter out;
		private OutputStream outputStream;
		private static ObjectOutputStream objectOutputStream;
		InputStream inputStream;
		
		private static String turn;
		private static String myCharName;
		private static boolean gameEnded = false;
		private static boolean canMakeSuggestion = true;
		private static ImageIcon[] imgList = new ImageIcon[21];
		private static HashMap<String, Integer> nameToCardImageMap = new HashMap<>();
		// JFrame related
		private GridLayout gl1;
		private JLabel cardsLabel;
		private static JPanel cardsPanel;
		private static JLabel imageLabel1;
		private static JLabel imageLabel2;
		private static JPanel contentPane;
		private JTextArea txtAreaLogs;
		private static JTextArea playersCard;
		private JLabel cardLabel;
		private JButton btnStart;
		private static JButton btnStartGame;
		private JPanel panelNorth;
		private static JLabel lblChatClient;
		private JPanel panelNorthSouth;
		private JPanel panelNorthWest;
		private JPanel pannelCollection;
		private JPanel movePanel;
		private static JButton suggBtn;
		private static JButton moveBtn;
		private static JButton accusationBtn;
		private static JButton endBtn;
		private JLabel lblPort;
		private JLabel lblName;
		private JLabel lblNames;
		private JPanel panelSouth;
		//private static JButton btnSend;
		private JTextField txtMessage;
		private JTextField txtNickname;
		private JTextField txtPort;
		private static String clientName;
		private JComboBox<String> personList; 
	    private JComboBox<String> weaponList; 
	    private JComboBox<String> locationList;
	    private JComboBox<String> moveLocation;
		
	    
	//private static BoardGame game;
	//private int numPlayers = 0;

	/**
	 * Launch the application.
	 */
    /*   * Moved to BoardGame class **
private static Map<String, Integer> idToLocName = new HashMap<String, Integer>(){{
    	
    	put("Study", 1);
    	put("Library", 2);
    	put("Conservatory", 3);
    	put("Hall", 4);
    	put("Billiard Room", 5);
    	put("Ballroom", 6);
    	put("Lounge", 7);
    	put("Dining Room", 8);
    	put("Kitchen", 9);
    	put("Hallway 12", 12);
    	put("Hallway 23", 23);
    	put("Hallway 14", 14);
    	put("Hallway 25", 25);
    	put("Hallway 36", 36);
    	put("Hallway 47", 47);
    	put("Hallway 45", 45);
    	put("Hallway 56", 56);
    	put("Hallway 58", 58);
    	put("Hallway 69", 69);
    	put("Hallway 78", 78);
    	put("Hallway 89", 89);
    	
    	// Starting locations
    	put("ScarletStart",101);
    	put("MustardStart",102);
    	put("WhiteStart",103);
    	put("GreenStart",104);
    	put("PeacockStart",105);
    	put("PlumStart",106);
    	
    
    	
    
    }};
    */
    
    
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUI frame = new ClientUI();
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame); 
					//Logs
					System.setOut(new PrintStream(new TextAreaOutput(frame.txtAreaLogs)));
					frame.setVisible(true);
					//frame.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientUI(String blb) {} 
	public ClientUI() { loadImges();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(500, 500, 770, 500);---------------------------
setBounds(500, 500, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10,10,10,10)); //(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		//panel in the North side
		panelNorth = new JPanel();   
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		lblChatClient = new JLabel("PLAYER");
		lblChatClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatClient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelNorth.add(lblChatClient, BorderLayout.NORTH);

		//panel North-South
		panelNorthSouth = new JPanel();
		//panelNorth.add(panelNorthSouth, BorderLayout.SOUTH); --------------------------
		panelNorthSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));		
	
	
		//endBtn = new JButton("End Turn");---------------------
		//endBtn.addActionListener(this);-------------
		//panelNorthSouth.add(endBtn); -----------------
JPanel namePortJoinPane = new JPanel();
namePortJoinPane.setLayout(new BoxLayout(namePortJoinPane, BoxLayout.PAGE_AXIS));
		
		
		lblName = new JLabel("Nickname");
		//panelNorthSouth.add(lblName); //--------------------
		
namePortJoinPane.add(lblName);

		txtNickname = new JTextField();
		txtNickname.setColumns(10);
		//panelNorthSouth.add(txtNickname); ----------------------
namePortJoinPane.add(txtNickname);
namePortJoinPane.add(Box.createRigidArea(new Dimension(0, 5)));		

/*
		lblPort = new JLabel("Port");
		//panelNorthSouth.add(lblPort); //--------------------------
		
namePortJoinPane.add(lblPort);

		txtPort = new JTextField();
		//panelNorthSouth.add(txtPort);  //------------------------
		txtPort.setColumns(10); 
		
namePortJoinPane.add(txtPort);
namePortJoinPane.add(Box.createRigidArea(new Dimension(0, 5)));
*/

		btnStart = new JButton("JOIN");
		//panelNorthSouth.add(btnStart); //-----------------------
		
namePortJoinPane.add(btnStart);
panelNorthSouth.add(namePortJoinPane);

		btnStart.addActionListener(this);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		//Button to start Game ---
		btnStartGame =  new JButton("START GAME");
		//panelNorthSouth.add(btnStartGame); ---------------------------------

		btnStartGame.addActionListener(this);
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStartGame.setForeground(Color.BLUE);
		btnStartGame.setEnabled(false); //------------------ off until player registered

panelNorthSouth.add(Box.createRigidArea(new Dimension(10, 0)));
panelNorthSouth.add(btnStartGame);		
//JPanel northSouthPane = new JPanel();
//northSouthPane.setLayout(new BoxLayout(northSouthPane, BoxLayout.PAGE_AXIS));
//northSouthPane.add(btnStartGame);
//northSouthPane.add(panelNorthSouth);
panelNorth.add(panelNorthSouth, BorderLayout.SOUTH); // EAST);// ??????????????? ????????

		
		
	 //--------------------------------------
	//ADDED for TEST 
	    //pannelCollection = new JPanel(new GridLayout(10, 1, 10, 5));
		panelNorthWest = new JPanel(); //new GridLayout(2,0));//(10, 1, 10, 5)); //new JPanel();
		//panelNorth.add(panelNorthWest, BorderLayout.WEST); //-----------------------
		panelNorthWest.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		//lblName = new JLabel("Suggestions");
		//panelNorthWest.add(lblName);
		String[] person = { "Person", "Colonel Mustard","Mrs. White", "Professor Plum","Mrs. Peacock","Mr. Green","Miss Scarlet"};
		String[] weapon = {"Weapon", "Dagger", "Rope", "Lead Pipe", "Candlestick", "Revolver", "Wrench"};
		String[] location = {"Location", "Kitchen", "Ballroom", "Dining Room", "Lounge", "Hall", "Conservatory", "Billiard Room", "Library", "Study"};
	    personList = new JComboBox<String>(person);
	    weaponList = new JComboBox<String>(weapon);
	    locationList = new JComboBox<String>(location);
	    personList.setVisible(true);
	    panelNorthWest.add(personList);
	    panelNorthWest.add(weaponList);
	    panelNorthWest.add(locationList);
	
JPanel listPane = new JPanel();
listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
//listPane.add(endBtn);
panelNorthWest.add(listPane);

 
	    suggBtn = new JButton("Suggestion");
	    //panelNorthWest.add(suggBtn);-------------------------
	    
listPane.add(suggBtn);
listPane.add(Box.createRigidArea(new Dimension(0, 5)));

	    suggBtn.addActionListener(this);
	    suggBtn.setEnabled(false);
	    accusationBtn = new JButton("ACCUSE"); //accuse------------
	    accusationBtn.setBounds(20,20,20,20);
	    accusationBtn.setForeground(Color.RED); //may not needed -------------
	    accusationBtn.setEnabled(false);
listPane.add(accusationBtn);
listPane.add(Box.createRigidArea(new Dimension(0, 5)));	   

		//panelNorthWest.add(accusationBtn);------------------------
	    accusationBtn.addActionListener(this);
	   // panelNorth.add(panelNorthWest, BorderLayout.WEST);  //----------------------------------
	    
endBtn = new JButton("End Turn");
endBtn.addActionListener(this);

//listPane.add(endBtn);


	    movePanel = new JPanel(new GridLayout(2,0));
	    movePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	    String[] move = {"Move", "Kitchen", "Ballroom", "Dining Room", "Lounge", "Hall", "Conservatory", "Billiard Room", "Library", "Study",
	    		"Study-Hall Hallway", "Hall-Lounge Hallway", "Study-Library Hallway","Hall-Billiard Hallway",
	    		"Lounge-Dining Hallway","Library-Billiard Hallway","Billiard-Dining Hallway","Library-Conservatory Hallway",
	    		"Billiard-Ballroom Hallway","Dining-Kitchen Hallway","Conservatory-Ballroom Hallway","Ballroom-Kitchen Hallway"};
	    moveLocation = new JComboBox<String>(move); //More locations ???
	    moveLocation.setVisible(true);
	    movePanel.add(moveLocation);
	    moveBtn = new JButton("Move");
	    //panelNorth.add(moveBtn);
	    
	    movePanel.add(moveBtn);
	   
	    moveBtn.addActionListener(this);
	    moveBtn.setEnabled(false);
	    // panelNorth.add(movePanel, BorderLayout.EAST); //contentPane.add(movePanel, BorderLayout.EAST);//-----------------------
	    
	    //pannelCollection.add(movePanel);
	    //panelNorth.add(pannelCollection, BorderLayout.WEST);// panelNorthWest, BorderLayout.WEST);
	    
JPanel panelForMoveSuggAccu = new JPanel();
panelForMoveSuggAccu.setLayout(new BoxLayout(panelForMoveSuggAccu, BoxLayout.PAGE_AXIS));	   
panelForMoveSuggAccu.add(panelNorthWest);
panelForMoveSuggAccu.add(movePanel);
panelForMoveSuggAccu.add(Box.createRigidArea(new Dimension(0, 5)));

panelForMoveSuggAccu.add(endBtn);

FlowLayout fLayout = new FlowLayout(FlowLayout.RIGHT, 0, 5);
fLayout.setHgap(50);
JPanel northComponents = new JPanel(fLayout); //new FlowLayout(FlowLayout.RIGHT, 5, 5));
northComponents.add(panelForMoveSuggAccu);
JScrollPane scrollPane = new JScrollPane();
northComponents.add(scrollPane);

//fLayout.setHgap(50);   //ja--------------------- ???????????????
//northComponents.add(panelNorthSouth); //-------ja   ?????????????????


panelNorth.add(northComponents, BorderLayout.WEST);

//panelNorth.add(panelForMoveSuggAccu, BorderLayout.WEST);

		//*------------------------------------
//JScrollPane scrollPane = new JScrollPane();
//contentPane.add(scrollPane, BorderLayout.NORTH);//  .SOUTH);
//JPanel northEast = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
//northEast.add(comp)


//panelNorthWest.add(scrollPane);
//northComponents.add(scrollPane);

txtAreaLogs = new JTextArea(10, 70);
txtAreaLogs.setBackground(Color.BLACK);
txtAreaLogs.setForeground(Color.WHITE);
txtAreaLogs.setLineWrap(true);
scrollPane.setViewportView(txtAreaLogs);
	//-------------------------------------



JPanel imagePanel = new JPanel(); //(new GridLayout(0,10));
//imagePanel.add(new MyPanel());
mp = new MyPanel();
//mp.draw();
//mp.repaint();
imagePanel.add(mp);  //new MyPanel());
//imagePanel.setLayout(new GridBagLayout());

contentPane.add(imagePanel, BorderLayout.CENTER);

/*
//*
try {
	BufferedImage img = ImageIO.read(getClass().getResource("/imageFiles/boardGame.png"));
	ImageIcon icon = new ImageIcon(img);
	JLabel imageLabel = new JLabel(icon);
	imagePanel.add(imageLabel);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   
imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
*/




//--------------------------------------------------------------------------------------



boardPanel = new JPanel();
boardPanel.setLayout(new java.awt.GridLayout(5, 5));
boardPanel.setSize(100, 100);
boardPanel.setBackground(Color.WHITE);
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
ImageIcon bcircle = null;

ImageIcon dinKitchenH = null;
ImageIcon loungeDinH = null;
ImageIcon billBallH = null;
ImageIcon hallBillH = null;
ImageIcon libConsH = null;
ImageIcon studyLibH = null;
ImageIcon ballKitchenH = null;
ImageIcon consBallH = null;
ImageIcon billDiningH = null;
ImageIcon libBillH = null;
ImageIcon hallLoungeH= null;
ImageIcon studyHallH = null;
try {
	BufferedImage imgdin = ImageIO.read(getClass().getResource("/cardimages/dining-kitchen.png")); //    hHall.png"));
	dinKitchenH = new ImageIcon(imgdin);
	
	BufferedImage imglou = ImageIO.read(getClass().getResource("/cardimages/lounge-dining.png")); //vHall.png"));
	loungeDinH = new ImageIcon(imglou);
	
	BufferedImage imgbill = ImageIO.read(getClass().getResource("/cardimages/billiard-ballrm.png")); //    hHall.png"));
	billBallH = new ImageIcon(imgbill);
	
	BufferedImage imghallB = ImageIO.read(getClass().getResource("/cardimages/hall-billiard.png")); //vHall.png"));
	hallBillH = new ImageIcon(imghallB);
	
	BufferedImage imglib = ImageIO.read(getClass().getResource("/cardimages/libr-cons.png")); //    hHall.png"));
	libConsH = new ImageIcon(imglib);
	
	BufferedImage imgstdy = ImageIO.read(getClass().getResource("/cardimages/study-lib.png")); //vHall.png"));
	studyLibH = new ImageIcon(imgstdy);
	
	BufferedImage imgball = ImageIO.read(getClass().getResource("/cardimages/ballroom-kitchen.png")); //    hHall.png"));
	ballKitchenH = new ImageIcon(imgball);
	
	BufferedImage imgcons = ImageIO.read(getClass().getResource("/cardimages/conservatory-ballrm.png")); //vHall.png"));
	consBallH= new ImageIcon(imgcons);
	
	BufferedImage imgbidin = ImageIO.read(getClass().getResource("/cardimages/billiard-Dining.png")); //vHall.png"));
	billDiningH = new ImageIcon(imgbidin);
	
	BufferedImage imglibb = ImageIO.read(getClass().getResource("/cardimages/library-billiard.png"));
	libBillH = new ImageIcon(imglibb);
	
	BufferedImage imglng = ImageIO.read(getClass().getResource("/cardimages/hall-lounge.png"));
	hallLoungeH = new ImageIcon(imglng);

	BufferedImage imgstdyH = ImageIO.read(getClass().getResource("/cardimages/study-hall.png"));
	 studyHallH = new ImageIcon(imgstdyH);
	
	
	
	
	
	
	
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

	BufferedImage cimage = ImageIO.read(getClass().getResource("/cardimages/blueCircle.png"));
	bcircle = new ImageIcon(cimage);

} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

String [][] plcname = { {"Study","", "Hall", "", "Lounge"}, {"", "", "", "",""}, {"Library", "", "Billiard Room","","Dining Room"},{"","","","",""},{"Conservatory", "","Ballroom", "", "Kitchen"}};
for (int i = 0; i < 5; i++) {
    for(int j = 0; j < 5; j++) {
        JButton button = new JButton(); 
       //JLabel lbl; // = new JLabel();
       if ((i==0 && j==1) || (i==0 && j==3) || (i==2 && j==1) || (i==2 && j==3) ||(i==4 && j==1) || (i==4 && j==3)){
    	   //lbl = new JLabel(hHall);
    	   button.setIcon(hHall); button.setSize(20,20);
    	   boardPanel.add(button);
        }
       else if( (i==1 && j==0) || (i==1 && j==2) || (i==1 && j==4) ||(i==3 && j==0) || (i==3 && j==2) ||(i==3 && j==4) ) {
    	   //lbl = new JLabel(vHall);
    	   button.setIcon(vHall); button.setSize(20,20);
    	   boardPanel.add(button);
       }
       else if (i==0 && j==0){ studyBtn.setIcon(study);  studyBtn.setSize(10,10); boardPanel.add(studyBtn);}
       else if (i==0 && j==2){ hallBtn.setIcon(hall);  hallBtn.setSize(10,10);  boardPanel.add(hallBtn);}
       else if (i==0 && j==4){ loungeBtn.setIcon(lounge);  loungeBtn.setSize(10,10); boardPanel.add(loungeBtn);}
       else if (i==2 && j==0){ libraryBtn.setIcon(library);  libraryBtn.setSize(10,10); boardPanel.add(libraryBtn);}
       else if (i==2 && j==2){ billiardBtn.setIcon(billiardrm);  billiardBtn.setSize(10,10); boardPanel.add(billiardBtn);}
       else if (i==2 && j==4){ diningrmBtn.setIcon(diningrm);  diningrmBtn.setSize(10,10); boardPanel.add(diningrmBtn);}
       else if (i==4 && j==0){ conservatoryBtn.setIcon(conservatory);  conservatoryBtn.setSize(10,10); boardPanel.add(conservatoryBtn);}
       else if (i==4 && j==2){ ballrmBtn.setIcon(ballrm);  ballrmBtn.setSize(10,10); boardPanel.add(ballrmBtn); }
       else if (i==4 && j==4){ kitchenBtn.setIcon(kitchen);  kitchenBtn.setSize(10,10);  boardPanel.add(kitchenBtn);}
       else {boardPanel.add(button);} 
       //String string =plcname[i][j]; // +  "" + j;
        //lbl = new JLabel(string); //.setText(string);
       
       // button.setLabel(string);
        //button.setName(string);
        //button.addActionListener(
         //   new ActionListener() {
          //      public void actionPerformed(ActionEvent e) {
                    //TicTacToe.buttonClicked(button);
                    //TicTacToe.gameRules(button);
        // }
         //   });
       // button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       
        //panel.add(lbl);
    }
}

//contentPane.add(boardPanel, BorderLayout.CENTER);

















//JPanel boardgui = new JPanel(new BorderLayout());

//LayoutManager overlay = new OverlayLayout(imagePanel);
//imagePanel.setLayout(overlay);
//imagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//JLabel picImglabel1 = new JLabel("TE");
//picImglabel1.setForeground(Color.GREEN);
//picImglabel1.setFont(new Font("SansSerif", Font.BOLD, 16));
//picImglabel1.setLocation(520, 520);
//picImglabel1.setAlignmentX(0.9f);
//picImglabel1.setAlignmentY(0.9f);
//imagePanel.add(picImglabel1);
/*
JLabel lbl123 = null;

try {
	BufferedImage img = ImageIO.read(getClass().getResource("/imageFiles/boardGame.png"));
	ImageIcon icon = new ImageIcon(img);
	lbl123 = new JLabel(icon);add(lbl123);
	//lbl123.setLayout(new FlowLayout(FlowLayout.TRAILING));//.CENTER));
	lbl123.add(picImglabel1);
	//imagePanel.add(imageLabel);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
imagePanel.add(lbl123);
contentPane.add(imagePanel, BorderLayout.CENTER);
//imagePanel.setLayout(new OverlayLayout(imagePanel));
//JLabel picImglabel1 = new JLabel();
//JLabel picImglabel1 = new JLabel("Centered Text", label1.LEFT);
//picImglabel1.setText("CHAR NAME"); //, picImglabel1.LEFT);
//picImglabel1.setForeground(Color.GREEN);
//picImglabel1.setFont(new Font("SansSerif", Font.BOLD, 16));
//label1.setAlignmentX(0.5f);
//label1.setAlignmentY(0.5f);
//picImglabel1.setLocation(20, 20);
//imagePanel.add(picImglabel1);

*/


//--------------------------------------------------------------------------------






















//contentPane.add(imageLabel, BorderLayout.CENTER);
//contentPane.add(lbl123, BorderLayout.CENTER);
//contentPane.revalidate();
//contentPane.repaint();
//add(imageLabel);
//}
		
		//cardsPanel = new JPanel(); cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gl1 = new GridLayout(0,2); 
		gl1.setVgap(5); // .setHgap(5);
		gl1.setHgap(5);
		cardsPanel = new JPanel(gl1);
		//cardsPanel.setLayout(gl1);
	
		//ImageIcon icon1 = getCardImage("/imageFiles/card_library.png");//
		//ImageIcon icon2 = getCardImage("/imageFiles/card_white.png");
		//imageLabel1 = new JLabel(); //(icon1);//new ImageIcon());
		//imageLabel2 = new JLabel(); //(icon2); 
		//cardsPanel.add(imageLabel1);
		//cardsPanel.add(imageLabel2);
		contentPane.add(cardsPanel, BorderLayout.WEST);

		
playersCard = new JTextArea(5,20);
playersCard.setBackground(Color.WHITE);// .BLACK);
playersCard.setForeground(Color.BLACK); //.WHITE);
playersCard.setLineWrap(true);
playersCard.setText("CHEAT SHEET\n"); 
//cardLabel = new JLabel("Player card");
//JPanel cardPanel = new JPanel();
//cardPanel.add(playersCard);
//cardPanel.add(cardLabel);

JScrollPane scrollPane1 = new JScrollPane();
contentPane.add(scrollPane1, BorderLayout.EAST);
scrollPane1.setViewportView(playersCard);		
		
	
		
		/*
		try {
			cardsPanel = new JPanel();
			gl1 = new GridLayout(2,1); 
			cardsPanel.setLayout(gl1);
			contentPane.add(cardsPanel, BorderLayout.WEST);
			BufferedImage img1 = ImageIO.read(getClass().getResource("/imageFiles/card_library.png"));
			BufferedImage img2 = ImageIO.read(getClass().getResource("/imageFiles/card_white.png"));
			ImageIcon icon1 = getCardImage("/imageFiles/card_library.png");//  new ImageIcon(img1);
			ImageIcon icon2 = getCardImage("/imageFiles/card_white.png"); //new ImageIcon(img2);
			imageLabel2 = new JLabel(icon1);
			imageLabel1 = new JLabel(icon2); 
			//gl1.addLayoutComponent("what", imageLabel1);gl1.addLayoutComponent("what2", imageLabel2);
			cardsPanel.add(imageLabel2); cardsPanel.add(imageLabel1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */ /*
		cardsPanel = new JPanel();
		//cardsPanel.setLayout(new GridBagLayout());
		
		gl1 = new GridLayout(2,1);
		cardsPanel.setLayout(gl1);
		imageLabel1 = new JLabel();//new ImageIcon());
		imageLabel2 = new JLabel(); //(new ImageIcon()); 
		gl1.addLayoutComponent("img1", imageLabel1);
		gl1.addLayoutComponent("img1", imageLabel2);
		//cardsPanel.add(imageLabel1);
		//cardsPanel.add(imageLabel2);
		//JScrollPane scrollPane1 = new JScrollPane();
		//contentPane.add(scrollPane1, BorderLayout.WEST);
		contentPane.add(cardsPanel, BorderLayout.WEST);
		 */
		//scrollPane1.add(cardsPanel);
		//cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//cardsLabel = new JLabel(new ImageIcon());
		//cardsPanel.add(cardsLabel);
		
		
		//ArrayList<Card> plCards = ServerUI.plyrsCard();
		//int index = sui.cardNumIndex();
		//String plCard = ServerUI.plyrsCard(); //game.getRandomCards(index); //, plCards);
		/* -------------------------------		
		playersCard = new JTextArea(5,20);
		playersCard.setBackground(Color.BLACK);
		playersCard.setForeground(Color.WHITE);
		playersCard.setLineWrap(true);
		playersCard.setText(""); //plCard );
		//cardLabel = new JLabel("Player card");
		//JPanel cardPanel = new JPanel();
		//cardPanel.add(playersCard);
		//cardPanel.add(cardLabel);
		
		JScrollPane scrollPane1 = new JScrollPane();
		contentPane.add(scrollPane1, BorderLayout.WEST);
		scrollPane1.setViewportView(playersCard);
		*/ //---------------------------------------------------
		//scrollPane.setViewportView(txtAreaLogs); //-----------------------------
        /* -------------------------------
		panelSouth = new JPanel();
		FlowLayout fl_panelSouth = (FlowLayout) panelSouth.getLayout();
		fl_panelSouth.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelSouth, BorderLayout.SOUTH);

		txtMessage = new JTextField();
		panelSouth.add(txtMessage);
		txtMessage.setColumns(50);

		btnSend = new JButton("SEND");
		btnSend.addActionListener(this);
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelSouth.add(btnSend); *///---------------------------------------------
	}
	
	/*/////////////////////////
	public void drawRectangles(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
 
        // code to draw rectangles goes here...
 
    }
 
    public void paint(Graphics g) {
        super.paint(g);
        drawRectangles(g);
    }
    
    
    *////////////////
	
	
	private void loadImges() {
	    
		ImageIcon icon1 = getCardImage("/cardimages/ballroom.png");   //("/imageFiles/ballroom.png");
		imgList[0]= icon1;
		nameToCardImageMap.put("Ballroom", 0);
		
		ImageIcon icon2 = getCardImage("/cardimages/billiard.png"); //("/imageFiles/billiard.png");
		imgList[1]= icon2;
		nameToCardImageMap.put("Billiard Room", 1);
		
		ImageIcon icon3 = getCardImage("/cardimages/candlestick2.png"); //("/imageFiles/candlestick.png");
		imgList[2]= icon3;
		nameToCardImageMap.put("Candlestick", 2);
		
		ImageIcon icon4 = getCardImage("/cardimages/conservatory.png"); //("/imageFiles/conservatory.png");
		imgList[3]= icon4;
		nameToCardImageMap.put("Conservatory", 3);
		
		ImageIcon icon5 = getCardImage("/cardimages/dagger2.png");  //("/imageFiles/dagger.png");
		imgList[4]= icon5;
		nameToCardImageMap.put("Dagger", 5);
		
		ImageIcon icon6 = getCardImage("/cardimages/diningroom.png");   //("/imageFiles/diningroom.png");
		imgList[5]= icon6;
		nameToCardImageMap.put("Dining Room", 6);
		
		ImageIcon icon7 = getCardImage("/cardimages/green2.png");  //("/imageFiles/green.png");
		imgList[6]= icon7;
		nameToCardImageMap.put("Mr. Green", 7);
		
		ImageIcon icon8 = getCardImage("/cardimages/hall.png");  //("/imageFiles/hall.png");
		imgList[7]= icon8;
		nameToCardImageMap.put("Hall",8);
		
		ImageIcon icon9 = getCardImage("/cardimages/kitchen.png");  //("/imageFiles/kitchen.png");
		imgList[8]= icon9;
		nameToCardImageMap.put("Kitchen",9);
		
		ImageIcon icon10 = getCardImage("/cardimages/library.png");   //("/imageFiles/library.png");
		imgList[9]= icon10;
		nameToCardImageMap.put("Library",10);
		
		ImageIcon icon11 = getCardImage("/cardimages/lounge.png");  //("/imageFiles/lounge.png");
		imgList[10]= icon11;
		nameToCardImageMap.put("Lounge", 10);
		
		ImageIcon icon12 = getCardImage("/cardimages/mustard2.png");  //("/imageFiles/mustard.png");
		imgList[11]= icon12;
		nameToCardImageMap.put("Colonel Mustard", 11);
		
		ImageIcon icon13 = getCardImage("/cardimages/peacock2.png");  //("/imageFiles/peacock.png");
		imgList[12]= icon13;
		nameToCardImageMap.put("Mrs. Peacock", 12);
		
		ImageIcon icon14 = getCardImage("/cardimages/pipe2.png");  //("/imageFiles/pipe.png");
		imgList[13]= icon14;
		nameToCardImageMap.put("Lead Pipe", 13);
		
		ImageIcon icon15 = getCardImage("/cardimages/plum2.png");  //("/imageFiles/plum.png");
		imgList[14]= icon15;
		nameToCardImageMap.put("Professor Plum", 14);
		
		ImageIcon icon16 = getCardImage("/cardimages/revolver2.png");  //("/imageFiles/revolver.png");
		imgList[15]= icon16;
		nameToCardImageMap.put("Revolver",15);
		
		ImageIcon icon17 = getCardImage("/cardimages/rope2.png");  //("/imageFiles/rope.png");
		imgList[16]= icon17;
		nameToCardImageMap.put("Rope", 16);
		
		ImageIcon icon18 = getCardImage("/cardimages/scarlet2.png");  //("/imageFiles/scarlet.png");
		imgList[17]= icon18;
		nameToCardImageMap.put("Miss Scarlet", 17);
		
		ImageIcon icon19 = getCardImage("/cardimages/study.png");   //("/imageFiles/study.png");
		imgList[18]= icon19;
		nameToCardImageMap.put("Study", 18);
		
		ImageIcon icon20 = getCardImage("/cardimages/white2.png");  //("/imageFiles/white.png");
		imgList[19]= icon20;
		nameToCardImageMap.put("Mrs. White", 19);
		
		ImageIcon icon21 = getCardImage("/cardimages/wrench2.png");  //("/imageFiles/wrench.png");
		imgList[20]= icon21;
		nameToCardImageMap.put("Wrench", 20);
		
	}
	
	public ImageIcon getCardImage(String cardName) {
		try {
			BufferedImage img = ImageIO.read(getClass().getResource(cardName));
			ImageIcon image = new ImageIcon(img);
			//ImageIcon icon = new ImageIcon(img);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; //new ImageIcon();
	}
	
	
	private static void setRoomColor(String charname, String location) {
		mp.widthV = 20;
    	mp.heightV = 10;
    	
		if (charname.toLowerCase().contains("scarlet")) 
			mp.colorV = Color.BLUE;
		else if (charname.toLowerCase().contains("mustard"))
			mp.colorV = Color.GREEN;
		else if (charname.toLowerCase().contains("white")) 
			mp.colorV = Color.YELLOW;
		else if (charname.toLowerCase().contains("plum"))
			mp.colorV = Color.ORANGE;
		else if (charname.toLowerCase().contains("peacock"))
			mp.colorV = Color.DARK_GRAY;
		else if (charname.toLowerCase().contains("green"))
			mp.colorV = Color.RED;
		
		//default case
		else
			mp.colorV = Color.BLACK;
		
		if (location.toLowerCase().contains("hall")) {
			MyPanel.xVal = hallx1;
			MyPanel.yVal = hally1;
	    }
		else if(location.toLowerCase().contains("lounge")) {
			MyPanel.xVal = loungex1;
			MyPanel.yVal = loungey1;
		}
		else if(location.toLowerCase().contains("library")) {
			MyPanel.xVal = libraryx1;
			MyPanel.yVal = libraryy1;
		}
		else if(location.toLowerCase().contains("billiard")) {
			MyPanel.xVal = billiardx1;
			MyPanel.yVal = billiardy1;
		}
		else if(location.toLowerCase().contains("dining")) {
			MyPanel.xVal = diningrmx1;
			MyPanel.yVal = diningrmy1;
		}
		else if(location.toLowerCase().contains("conservatory")) {
			MyPanel.xVal = conservatoryx1;
			MyPanel.yVal = conservatoryy1;
		}
		else if(location.toLowerCase().contains("ball")) {
			MyPanel.xVal = ballroomx1;
			MyPanel.yVal = ballroomy1;
		}
		else if(location.toLowerCase().contains("kitchen")) {
			MyPanel.xVal = kitchenx1;
			MyPanel.yVal = kitcheny1;
		}
		mp.draw();
	}
	
	private static void showPane(String info) throws IOException {
		int result = JOptionPane.showConfirmDialog(contentPane, //showMessageDialog(contentPane, 
                     info, "Suggestion Response", JOptionPane.YES_NO_OPTION);
            
		
		SuggestionResponse suggResp = new SuggestionResponse();
		String resp ="";
		if (JOptionPane.YES_OPTION == result) {
			resp = "Yes";
			//select card and send it maybe
		}		
		else
			resp = "No";
		
		suggResp.setMessage(resp);
		objectOutputStream.writeObject(suggResp);		
	}
	
	public static  void setCards(PlayerMessage msg) {
		//BufferedImage img = ClientUIImageIO.read(getClass().getResource(cardName));
		/*
		String cName = "/imageFiles/card_library.png";
		String cName2 = "/imageFiles/ballroom.png";
		Image myimgicon = new ImageIcon(cName).getImage();
		ImageIcon image = new ClientUI("nm").getCardImage(cName); //new ImageIcon(cName);
		ImageIcon image2 = new ClientUI("mn").getCardImage(cName2);
		*/
		//JLabel jLabel = new JLabel(myimgicon);
		//JLabel jLabel2 = new JLabel(image2);
		//ImageIcon image2 = new ImageIcon(cName2);
		//imageLabel1.setIcon(null);
		//imageLabel2.setIcon(null);
		//imageLabel1.setIcon(image); imageLabel1.revalidate(); imageLabel1.repaint(); 
		//imageLabel2.setIcon(image2); imageLabel2.revalidate(); imageLabel2.repaint();
		//imageLabel1 = new JLabel(image);
		//imageLabel2 = new JLabel(image2); //.setIcon(image2);
		//cardsPanel.add(imageLabel1);
		//cardsPanel.add(imageLabel2);
		//cardsPanel.revalidate();
		//cardsPanel.repaint();
		
		//cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		DealCardMessage cardMsg = (DealCardMessage) msg;
		ArrayList<Card> c = cardMsg.getCards();
		if (c.size() > 0) {
			for (Card card: c) {
			  String cname = card.getName(); // + "\n");  //setText(cards); 
			  int i = nameToCardImageMap.get(cname); 
			  
			  JLabel ilabel = new JLabel(imgList[i]);
			  cardsPanel.add(ilabel);
			  cardsPanel.revalidate();
		      cardsPanel.repaint();
			} 
		}
		
		
		
		
		
		
		
		
		
		/*
		DealCardMessage cardMsg = (DealCardMessage) msg;
		ArrayList<Card> c = cardMsg.getCards();
		if (c.size() > 0) {
			for (Card card: c) {
			   playersCard.append(card.getName() + "\n");  //setText(cards); 
			}
		} 
		Location loc = cardMsg.getStartLocation();
		//player.setLocation(loc);
		 */
		
	}
	private static OtherMessage createOtherMessage(String msg) {
		OtherMessage otherMsg = new OtherMessage();
		otherMsg.setMessage(msg);
		return otherMsg;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			btnStartGame.setEnabled(true);
			if(btnStart.getText().equals("JOIN")) {
				btnStart.setText("LEAVE");
				start();
			}else {
				btnStart.setText("JOIN");
				stop();
			}
		}
		/*
		else if(e.getSource() == btnSend) {
			String message = txtMessage.getText().trim();
			if(!message.isEmpty()) {
				//out.println(message);
				OtherMessage msg = createOtherMessage(message);
				try {
					objectOutputStream.writeObject(msg);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				txtMessage.setText("");
			}
		} */
		else if (e.getSource() == suggBtn) {
			//
			String player = (String)personList.getSelectedItem().toString();
			String weapon = (String)weaponList.getSelectedItem().toString();
			String location = (String)locationList.getSelectedItem().toString();
			
			//create Suggestion
			SuggestionOrAccusation sugg = new SuggestionOrAccusation(player, location, weapon, false);
			
			//set location type (Hall or room)
			if (location.toLowerCase().contains("hallway"))
				sugg.setLocationType(LocationType.HALLWAY);
			else
				sugg.setLocationType(LocationType.ROOM);
			
			try {
				objectOutputStream.writeObject(sugg);
				canMakeSuggestion = false;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			suggBtn.setEnabled(false);
		} 
		
		else if (e.getSource() == moveBtn) {
			/*
			String location = (String)moveLocation.getSelectedItem().toString();
			//String location = JOptionPane.showInputDialog("Enter Location");
			//String playerName = clientName;
			int locId = locationIDMap.get(location);
			Location loc = new Location(locId, location, LocationType.ROOM); //??????
			MoveMsg moveMsg = new MoveMsg(loc);
			try {
				objectOutputStream.writeObject(moveMsg);
			} catch (IOException e1) {
				e1.printStackTrace();
			}  */
			//get player name
			//String location = JOptionPane.showInputDialog("Enter Location");
			//String[] roomArr = {"Kitchen", "Ballroom", "Dining Room", "Lounge", "Hall", "Conservatory", "Billiard Room", "Library", "Study"};
			//String[] hallwayArr = {"Hallway 14", "Hallway 47", "Hallway 78", "Hallway 89", "Hallway 69", "Hallway 36", "Hallway 23", "Hallway 12", "Hallway 45", "Hallway 58", "Hallway 56", "Hallway 25"}; 
			//ArrayList<String> room = new ArrayList<>(Arrays.asList(roomArr));
			//ArrayList<String> hallway = new ArrayList<>(Arrays.asList(hallwayArr));
			
			
			
			String location = moveLocation.getSelectedItem().toString();
			String playerName = clientName;
			LocationType loctype;
			//String ms = playerName + " has moved to "+ location;
			//OtherMessage msg = createOtherMessage(ms);
			//if (room.contains(location) == true){
			//	loctype = LocationType.ROOM;
			//}
			if (location.toLowerCase().contains("hallway")) 
				loctype = LocationType.HALLWAY;
			else {
				 //loctype = LocationType.HALLWAY;
				loctype = LocationType.ROOM;
			}
			int locid = BoardGame.getLocationID(location);
			Location loc = new Location (locid, location, loctype);
			MoveMsg move = new MoveMsg(loc);
			//move.setLocation(loc);
			try {
				objectOutputStream.writeObject(move);
				canMakeSuggestion = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			moveBtn.setEnabled(false);
			if (loctype == LocationType.HALLWAY) {
				suggBtn.setEnabled(false);
			}
		} 
		
		else if (e.getSource() == accusationBtn) {
			String player = (String)personList.getSelectedItem().toString();
			String weapon = (String)weaponList.getSelectedItem().toString();
			String location = (String)locationList.getSelectedItem().toString();
			SuggestionOrAccusation accuse = new SuggestionOrAccusation(player, location, weapon, true);
			//sugg.setMessageType("suggestion");
			try {
				objectOutputStream.writeObject(accuse);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} 
		else if(e.getSource() == btnStartGame) {
			//String start = "start game";
			//out.println(start);
			//btnStartGame.setEnabled(false);
			moveBtn.setEnabled(true);
			suggBtn.setEnabled(true);
			accusationBtn.setEnabled(true);
			endBtn.setEnabled(true);
			
			DealCardMessage dlCrdMsg = new DealCardMessage();
			try {
				objectOutputStream.writeObject(dlCrdMsg);
				btnStartGame.setEnabled(false);
			} catch (IOException e1) {
				System.out.println("Client: Exception in Sending DealCardMessage");
				e1.printStackTrace();
			}
		}	
		
		else if(e.getSource() == endBtn) {
			
			EndTurnMessage endTurnMsg = new EndTurnMessage();
			try {
				objectOutputStream.writeObject(endTurnMsg);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//Refresh UI
		refreshUIComponents();
	}

	public void refreshUIComponents() {

	}

	public void start() {  //inside main
		try {
			//PORT = Integer.parseInt(txtPort.getText().trim());  //default port 5005
			final int PORT = 5005;
			clientName = txtNickname.getText().trim();  //??????
			clientSocket = new Socket("localhost", PORT);
			//out = new PrintWriter(clientSocket.getOutputStream(), true);
			
			outputStream = clientSocket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);

			new Thread(new Listener()).start();
			//send name
			//out.println(clientName);  //???
			
			OtherMessage om = createOtherMessage(clientName);
			objectOutputStream.writeObject(om);

		} catch (Exception err) {
			addToLogs("[ERROR] "+err.getLocalizedMessage());
		}
	}

	public void stop(){
		if(!clientSocket.isClosed()) {
			try { 
				System.out.println("See you");
				clientSocket.close();
			} catch (IOException e1) {}
		}
	}

	public static void addToLogs(String message) {
		System.out.printf("%s %s\n", ServerUI.formatter.format(new Date()), message);
		//System.out.println("message");
	}

	public static void turnOnOffButtons() {
		if (!gameEnded) {
			if (clientName.equalsIgnoreCase(turn)) {
				//if (!btnSend.isEnabled()) {btnSend.setEnabled(true);}
				if (canMakeSuggestion) {
					if (!suggBtn.isEnabled()) {suggBtn.setEnabled(true);}
				}
				if (!moveBtn.isEnabled())  {moveBtn.setEnabled(true);}
				if (!accusationBtn.isEnabled()) { accusationBtn.setEnabled(true);}
				if (!endBtn.isEnabled()) { endBtn.setEnabled(true);}
				if (btnStartGame.isEnabled())  {btnStartGame.setEnabled(false);}
				
			} else {
				//if (btnSend.isEnabled()) {btnSend.setEnabled(false);}
				if (suggBtn.isEnabled()) {suggBtn.setEnabled(false);}
				if (moveBtn.isEnabled())  {moveBtn.setEnabled(false);}
				if (btnStartGame.isEnabled())  {btnStartGame.setEnabled(false);}
				if (accusationBtn.isEnabled()) {accusationBtn.setEnabled(false);}	
				if (endBtn.isEnabled()) {endBtn.setEnabled(false);}
			}
		} else {
    			//if (btnSend.isEnabled()) {btnSend.setEnabled(false);}
    			if (suggBtn.isEnabled()) {suggBtn.setEnabled(false);}
    			if (moveBtn.isEnabled())  {moveBtn.setEnabled(false);}
   		 	if (btnStartGame.isEnabled())  {btnStartGame.setEnabled(false);}
   			if (accusationBtn.isEnabled()) {accusationBtn.setEnabled(false);} 
   			if (endBtn.isEnabled()) {endBtn.setEnabled(false);}
   		}
	}
	
	private static class Listener implements Runnable {
		//private BufferedReader in;
		
		InputStream inputStream; 
		ObjectInputStream objectInputStream;

		@Override
		public void run() {
			try {
				inputStream = clientSocket.getInputStream();
				objectInputStream = new ObjectInputStream(inputStream);

				//in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				//String read;
				PlayerMessage plmsg;
				for(;;) {
					//Reading DealCards message
					plmsg = (PlayerMessage) objectInputStream.readObject();
					if (plmsg.getMessageType().equalsIgnoreCase("DealCards")) {
						DealCardMessage dcards = (DealCardMessage) plmsg;
						setCards(plmsg);
						lblChatClient.setText(clientName + "("+dcards.getPlayerCharName()+")");
						myCharName = dcards.getPlayerCharName();
						turn = dcards.getPlayerTurn(); 
						if (turn != null)
							turnOnOffButtons();									
					}
					//Reading Other messages
					else if (plmsg.getMessageType().equalsIgnoreCase("Other")) {
						OtherMessage other = (OtherMessage) plmsg;
						if ((other.getMessage()) != null) {
							String otherMsg = other.getMessage();
							
							if (otherMsg.contains("id")) { //this should happen only once
								String id = otherMsg.substring(3);
								int plyrID = Integer.parseInt(id);
								//btnStartGame.setEnabled(true);
								//player.setPlayerID(plyrID);
								//player.setPlayerName(clientName);
							}
							else if (otherMsg.contains("disprovedCard")) {
								String disprovedCard = otherMsg.substring(13);
								playersCard.append(disprovedCard);
							}
							else {
								turn = other.getPlayerTurn();
								addToLogs(otherMsg); 
								if (turn != null)
									turnOnOffButtons();
								}
						}
					}
					else if (plmsg.getMessageType().equalsIgnoreCase("SuggestionResponse")) {
						SuggestionResponse suggResp = (SuggestionResponse) plmsg;
						String resp = suggResp.getMessage();
						turn = suggResp.getPlayerTurn();
						if (turn != null)
							turnOnOffButtons();
						showPane(resp);	
					}
					else if (plmsg.getMessageType().equalsIgnoreCase("End Turn")) {
						EndTurnMessage other = (EndTurnMessage) plmsg;
						if ((other.getMessage()) != null) {
							turn = other.getPlayerTurn();
							addToLogs(other.getMessage());
							if (turn != null)
								turnOnOffButtons();
						}
					}
					else if (plmsg.getMessageType().equalsIgnoreCase("End Game")) {
						turn="other";
						turnOnOffButtons();	
						gameEnded = true;
					}
					else if (plmsg.getMessageType().equalsIgnoreCase("Move")) {
						//turn="other";
						//turnOnOffButtons();	
						//gameEnded = true;
						MoveMsg mvMsg = (MoveMsg) plmsg;
						String msg = mvMsg.getMessage();
						String nickname = msg.substring(0, msg.indexOf(':'));
				    	String location = msg.substring(msg.indexOf(':') +1);
				    	addToLogs(location);
				    	addToLogs(nickname);
				    	setRoomColor(nickname, location);
						//kitchenBtn.setBackground(Color.BLUE);
				    	//mp.xVal = 340; //210; //340; //180; //60; //340; //180; //60; //Val(100);
				    	//mp.yVal = 380; //210; //90
				    	//mp.widthV = 20;
				    	//mp.heightV = 10;
				    	//mp.setWidth(100);
				    	//mp.setHeight(100);
				    	//mp.draw();
				    	//mp.repaint();
					}
				}
			} catch (IOException e) {
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("CLIENT: Error in sending PlayerMessage ");
				e.printStackTrace();				 
				
			}
		}

	}
}