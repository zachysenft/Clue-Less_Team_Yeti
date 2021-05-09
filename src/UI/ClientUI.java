package UI;


import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import clueless.PlayerMessage.EndGameMessage;
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
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
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
import javax.swing.Popup;

public class ClientUI extends JFrame implements ActionListener {	

	// Socket Related
	private static Socket clientSocket;
	//private static int PORT;
	
	private OutputStream outputStream;
	private static ObjectOutputStream objectOutputStream;
	InputStream inputStream;
	
	private static String turn;
	private static boolean gameEnded = false;
	private static boolean canMakeSuggestion = true;
	
	// JFrame related
	private static JPanel contentPane;
	private JTextArea txtAreaLogs;
	private static JTextArea playersCard;
	private static JTextArea cheatSheet;
	//private JLabel cardLabel;
	private JButton btnStart;
	private static JButton btnStartGame;
	private JPanel panelNorth;
	private static JLabel lblChatClient;
	private JPanel panelNorthSouth;
	private JPanel panelNorthWest;
	//private JPanel pannelCollection;
	private JPanel movePanel;
	private static JButton suggBtn;
	private static JButton moveBtn;
	private static JButton accusationBtn;
	private static JButton endBtn;
	//private JLabel lblPort;
	private JLabel lblName;
	//private JLabel lblNames;
	private JPanel panelSouth;
	private static JButton btnSend;
	private static JButton viewImg;
	private JTextField txtMessage;
	private JTextField txtNickname;
	private JTextField txtPort;
	private static String clientName;
	private JComboBox<String> personList; 
    private JComboBox<String> weaponList; 
    private JComboBox<String> locationList;
    private JComboBox<String> moveLocation;
    private static HashMap<String, Integer> nameToCardImageMap = new HashMap<>();
    private static ImageIcon[] imgList = new ImageIcon[21];
    private static JPanel plyrCards;
	/**
	 * Launch the application.
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
	public ClientUI() { 
		loadImges();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 770, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10,10,10,10)); //(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelNorth = new JPanel();   
		contentPane.add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new BorderLayout(0, 0));

		lblChatClient = new JLabel("PLAYER");
		lblChatClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblChatClient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelNorth.add(lblChatClient, BorderLayout.NORTH);

		panelNorthSouth = new JPanel();
		panelNorth.add(panelNorthSouth, BorderLayout.SOUTH);
		panelNorthSouth.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));		
		
		endBtn = new JButton("End Turn");
		endBtn.addActionListener(this);
		panelNorthSouth.add(endBtn);
		endBtn.setEnabled(false);
		lblName = new JLabel("Nickname");
		panelNorthSouth.add(lblName);
		
		panelNorthWest = new JPanel(new GridLayout(2,0));//(10, 1, 10, 5)); //new JPanel();
		panelNorth.add(panelNorthWest, BorderLayout.WEST);
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
	    suggBtn = new JButton("Suggestion");
	    panelNorthWest.add(suggBtn);
	    suggBtn.addActionListener(this);
	    suggBtn.setEnabled(false);
	    accusationBtn = new JButton("Accuse");
	    accusationBtn.setBounds(20,20,20,20);
	    accusationBtn.setEnabled(false);
	    panelNorthWest.add(accusationBtn);
	    accusationBtn.addActionListener(this);
	    panelNorth.add(panelNorthWest, BorderLayout.WEST);
	    
	    movePanel = new JPanel(new GridLayout(2,0));
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
	    
	    panelNorth.add(movePanel, BorderLayout.EAST); //contentPane.add(movePanel, BorderLayout.EAST);
	    
		txtNickname = new JTextField();
		txtNickname.setColumns(10);
		panelNorthSouth.add(txtNickname);

		/*lblPort = new JLabel("Port");
		panelNorthSouth.add(lblPort);

		txtPort = new JTextField();
		panelNorthSouth.add(txtPort);
		txtPort.setColumns(10);*/

		btnStart = new JButton("JOIN");
		panelNorthSouth.add(btnStart);
		btnStart.addActionListener(this);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		//Button to start Game ---
		btnStartGame =  new JButton("START GAME");
		panelNorthSouth.add(btnStartGame);
		btnStartGame.addActionListener(this);
		btnStartGame.setEnabled(false);
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStartGame.setForeground(Color.BLUE);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		txtAreaLogs = new JTextArea();  //txtAreaLogs = new JTextArea(10, 70);
		txtAreaLogs.setBackground(Color.BLACK);
		txtAreaLogs.setForeground(Color.WHITE);
		txtAreaLogs.setLineWrap(true);
		
		playersCard = new JTextArea(5,20);
		playersCard.setBackground(Color.BLACK);
		playersCard.setForeground(Color.WHITE);
		playersCard.setLineWrap(true);
		playersCard.setText(""); //plCard );
		
		
		cheatSheet = new JTextArea(5,20);
		cheatSheet.setBackground(Color.WHITE);
		cheatSheet.setForeground(Color.BLACK); 
		cheatSheet.setLineWrap(true);
		cheatSheet.setText("CHEAT SHEET\n");
		JScrollPane cheatSheatPane = new JScrollPane();
		contentPane.add(cheatSheatPane, BorderLayout.EAST);
		cheatSheatPane.setViewportView(cheatSheet);
		 
		
		JScrollPane scrollPane1 = new JScrollPane();
		
		viewImg = new JButton("View Cards");        
		JPanel cardviews = new JPanel();
		cardviews.setLayout(new BoxLayout(cardviews, BoxLayout.PAGE_AXIS));
		cardviews.add(viewImg);
		
		viewImg.addActionListener(this);
        viewImg.setEnabled(false);
		
		cardviews.add(scrollPane1);
		contentPane.add(cardviews, BorderLayout.WEST);
		
		//contentPane.add(scrollPane1, BorderLayout.WEST);
		scrollPane1.setViewportView(playersCard);
		scrollPane.setViewportView(txtAreaLogs);

		/*
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
		panelSouth.add(btnSend);
		*/
	}

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
		nameToCardImageMap.put("Dagger", 4);
		
		ImageIcon icon6 = getCardImage("/cardimages/diningroom.png");   //("/imageFiles/diningroom.png");
		imgList[5]= icon6;
		nameToCardImageMap.put("Dining Room", 5);
		
		ImageIcon icon7 = getCardImage("/cardimages/green2.png");  //("/imageFiles/green.png");
		imgList[6]= icon7;
		nameToCardImageMap.put("Mr. Green", 6);
		
		ImageIcon icon8 = getCardImage("/cardimages/hall.png");  //("/imageFiles/hall.png");
		imgList[7]= icon8;
		nameToCardImageMap.put("Hall",7);
		
		ImageIcon icon9 = getCardImage("/cardimages/kitchen.png");  //("/imageFiles/kitchen.png");
		imgList[8]= icon9;
		nameToCardImageMap.put("Kitchen",8);
		
		ImageIcon icon10 = getCardImage("/cardimages/library.png");   //("/imageFiles/library.png");
		imgList[9]= icon10;
		nameToCardImageMap.put("Library", 9);
		
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
	
	private static void showAccusationCards(PlayerMessage msg) {
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(5);
		JPanel accCards = new JPanel(layout); //new GridLayout());	
		EndGameMessage cardMsg = (EndGameMessage) msg;
		ArrayList<Card> c = cardMsg.getCards();
		if (c.size() > 0) {
			for (Card card: c) {
			  String cname = card.getName(); // + "\n");  //setText(cards); 
			  int i = nameToCardImageMap.get(cname); 
			  Image image = imgList[i].getImage(); // transform it 
			  Image newimg = image.getScaledInstance(175, 175,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			  ImageIcon imageIcon = new ImageIcon(newimg);
			  JLabel ilabel = new JLabel(imageIcon); //(imgList[i]);
			  accCards.add(ilabel);
			}			
			  JOptionPane.showMessageDialog(null, 
                        accCards, 
                        "Winning Cards",
                        JOptionPane.ERROR_MESSAGE); 
			 
		}
	}
	
	public static void setCards(PlayerMessage msg) {
		DealCardMessage cardMsg = (DealCardMessage) msg;
		ArrayList<Card> c = cardMsg.getCards();
		
		GridLayout layout = new GridLayout(2, 3);
		layout.setHgap(5);
		layout.setVgap(5);
		plyrCards = new JPanel(layout);
		
		if (c.size() > 0) {
			for (Card card: c) {
				//Add list of cards to textArea
				 playersCard.append(card.getName() + "\n");  
				 
				 //Create Cards image
				 String cname = card.getName(); // + "\n");  //setText(cards); 
				 int i = nameToCardImageMap.get(cname); 
				 Image image = imgList[i].getImage(); // transform it 
				 Image newimg = image.getScaledInstance(175, 175,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				 ImageIcon imageIcon = new ImageIcon(newimg);
				 JLabel ilabel = new JLabel(imageIcon); //(imgList[i]);
				 plyrCards.add(ilabel);
							
			}
		} 
		
		
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
		/*else if(e.getSource() == btnSend) {
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
			
			
			String location = moveLocation.getSelectedItem().toString();
			//String playerName = clientName;
			LocationType loctype;
			
			if (location.toLowerCase().contains("hallway")) 
				loctype = LocationType.HALLWAY;
			else {
				 
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
		else if(e.getSource() == viewImg) {
			JOptionPane.showMessageDialog(null, //accCards,
                    plyrCards, //CardDetailPanel(cards),
                    "Player Cards",
                    JOptionPane.INFORMATION_MESSAGE); 
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
						viewImg.setEnabled(true);
						DealCardMessage dcards = (DealCardMessage) plmsg;
						setCards(plmsg);
						lblChatClient.setText(clientName + "("+dcards.getPlayerCharName()+")");
						turn = dcards.getPlayerTurn(); 
						if (turn != null)
							turnOnOffButtons();									
					}
					//Reading Other messages
					else if (plmsg.getMessageType().equalsIgnoreCase("Other")) {
						OtherMessage other = (OtherMessage) plmsg;
						if ((other.getMessage()) != null) {
							String otherMsg = other.getMessage();
							if (otherMsg.contains("You can not make suggestion")) { //this should happen only once
								//String id = otherMsg.substring(3);
								//int plyrID = Integer.parseInt(id);
								canMakeSuggestion = true;
								turn = other.getPlayerTurn();
								addToLogs(otherMsg);
								if (turn != null)
									turnOnOffButtons();
								}
							else if (otherMsg.contains("disprovedCard")) {
								String disprovedCard = otherMsg.substring(13);
								cheatSheet.append(disprovedCard +"\n");
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
						showAccusationCards(plmsg);
						turn="other";
						turnOnOffButtons();	
						gameEnded = true;
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
