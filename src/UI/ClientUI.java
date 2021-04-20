package UI;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import clueless.BoardGame;
import clueless.Card;
import clueless.PlayerMessage;
import clueless.PlayerMessage.DealCardMessage;
import clueless.PlayerMessage.OtherMessage;
import clueless.PlayerMessage.SuggestionOrAccusation;
import clueless.PlayerMessage.SuggestionResponse;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.Date;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class ClientUI extends JFrame implements ActionListener {	

	// Socket Related
	private static Socket clientSocket;
	private static int PORT;
	private PrintWriter out;
	private OutputStream outputStream;
	private static ObjectOutputStream objectOutputStream;
	InputStream inputStream;

	// JFrame related
	private static JPanel contentPane;
	private JTextArea txtAreaLogs;
	private static JTextArea playersCard;
	private JLabel cardLabel;
	private JButton btnStart;
	private JButton btnStartGame;
	private JPanel panelNorth;
	private JLabel lblChatClient;
	private JPanel panelNorthSouth;
	private JPanel panelNorthWest;
	private JPanel pannelCollection;
	private JPanel movePanel;
	private JButton suggBtn;
	private JButton moveBtn;
	private JButton accusationBtn;
	private JButton myaccusationBtn;
	private JLabel lblPort;
	private JLabel lblName;
	private JLabel lblNames;
	private JPanel panelSouth;
	private JButton btnSend;
	private JTextField txtMessage;
	private JTextField txtNickname;
	private JTextField txtPort;
	private String clientName;
	private JComboBox<String> personList; 
    private JComboBox<String> weaponList; 
    private JComboBox<String> locationList;
	
	
	//private static BoardGame game;
	//private int numPlayers = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//game = new BoardGame();
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
		
		lblName = new JLabel("Nickname");
		panelNorthSouth.add(lblName);
		
		//JLabel lbl = new JLabel("Select one");
	    //lbl.setVisible(true);
		
	 //--------------------------------------
	//ADDED for TEST 
	    //pannelCollection = new JPanel(new GridLayout(10, 1, 10, 5));
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
	    myaccusationBtn = new JButton("Accuse");
	    myaccusationBtn.setBounds(20,20,20,20);
	    panelNorthWest.add(myaccusationBtn);
	    myaccusationBtn.addActionListener(this);
	    panelNorth.add(panelNorthWest, BorderLayout.WEST);
	    
	    movePanel = new JPanel(new GridLayout(2,0));
	    moveBtn = new JButton("Move");
	    //panelNorth.add(moveBtn);
	    accusationBtn = new JButton("Accusation");
	    movePanel.add(moveBtn);
	    movePanel.add(accusationBtn);
	    moveBtn.addActionListener(this);
	    accusationBtn.addActionListener(this);
	    panelNorth.add(movePanel); //contentPane.add(movePanel, BorderLayout.EAST);
	    //pannelCollection.add(movePanel);
	    //panelNorth.add(pannelCollection, BorderLayout.WEST);// panelNorthWest, BorderLayout.WEST);
	    
	    
	    
	    
//---------------------------------------
	    
		txtNickname = new JTextField();
		txtNickname.setColumns(10);
		panelNorthSouth.add(txtNickname);

		lblPort = new JLabel("Port");
		panelNorthSouth.add(lblPort);

		txtPort = new JTextField();
		panelNorthSouth.add(txtPort);
		txtPort.setColumns(10);

		btnStart = new JButton("JOIN");
		panelNorthSouth.add(btnStart);
		btnStart.addActionListener(this);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		//Button to start Game ---
		btnStartGame =  new JButton("START GAME");
		panelNorthSouth.add(btnStartGame);
		btnStartGame.addActionListener(this);
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStartGame.setForeground(Color.BLUE);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		txtAreaLogs = new JTextArea();
		txtAreaLogs.setBackground(Color.BLACK);
		txtAreaLogs.setForeground(Color.WHITE);
		txtAreaLogs.setLineWrap(true);
		//-------------------
		//ArrayList<Card> plCards = ServerUI.plyrsCard();
		//int index = sui.cardNumIndex();
		//String plCard = ServerUI.plyrsCard(); //game.getRandomCards(index); //, plCards);
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
		//----------------
		scrollPane.setViewportView(txtAreaLogs);

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
	}
	
	private static void showPane(String info) throws IOException {
		int result = JOptionPane.showConfirmDialog(contentPane, //showMessageDialog(contentPane, 
                     info, "Suggestion Response", JOptionPane.YES_NO_OPTION);
            
		
		SuggestionResponse suggResp = new SuggestionResponse();
		String resp ="";
		if (JOptionPane.YES_OPTION == result) {
			resp = "Yes";
			//select card and send it
		}		
		else
			resp = "No";
		
		suggResp.setMessage(resp);
		objectOutputStream.writeObject(suggResp);
		
	}
	
	public static void setCards(PlayerMessage msg) {
		/*
		String str[] = cards.split(",");
		int i=0;
		for (String card: str) {
			if (i==0) {
				i++;
				continue;
			}
		   playersCard.append(card + "\n");  //setText(cards); 
		}
		*/
		DealCardMessage cardMsg = (DealCardMessage) msg;
		ArrayList<Card> c = cardMsg.getCards();
		
		if (c.size() > 0) {
			for (Card card: c) {
			   playersCard.append(card.getName() + "\n");  //setText(cards); 
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
			if(btnStart.getText().equals("JOIN")) {
				btnStart.setText("LEAVE");
				start();
			}else {
				btnStart.setText("JOIN");
				stop();
			}
		}else if(e.getSource() == btnSend) {
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
		}else if (e.getSource() == suggBtn) {
			//
			String player = (String)personList.getSelectedItem().toString();
			String weapon = (String)weaponList.getSelectedItem().toString();
			String location = (String)locationList.getSelectedItem().toString();
			
			//create Suggestion
			SuggestionOrAccusation sugg = new SuggestionOrAccusation(player, location, weapon, false);
			//sugg.setMessageType("suggestion");
			try {
				objectOutputStream.writeObject(sugg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//out.println("Suggestion on player "+ player + "weapon = "+ weapon + " location= "+location);	
			//----------------------------
			//Handle suggestion here
		} else if (e.getSource() == moveBtn) {
			//get player name
			String location = JOptionPane.showInputDialog("Enter Location");
			String playerName = clientName;
			out.println(playerName + "has moved to "+ location);
		} else if (e.getSource() == myaccusationBtn) {
			String accusation = JOptionPane.showInputDialog("Enter Person, Weapon, location");
			String [] accus = accusation.split(",");
			out.println(clientName + "has accused" + accus[0] + "of committing the crime in the " + accus[2] + " with a " + accus[1]);
			stop();
		} else if(e.getSource() == btnStartGame) {
			//String start = "start game";
			//out.println(start);
			//btnStartGame.setEnabled(false);
			DealCardMessage dlCrdMsg = new DealCardMessage();
			try {
				objectOutputStream.writeObject(dlCrdMsg);
				btnStartGame.setEnabled(false);
			} catch (IOException e1) {
				System.out.println("Client: Exception in Sending DealCardMessage");
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
			PORT = Integer.parseInt(txtPort.getText().trim());  //default port 5005
			//final int PORT = 5005;
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
			try { System.out.println("Game Ended");
				clientSocket.close();
			} catch (IOException e1) {}
		}
	}

	public static void addToLogs(String message) {
		System.out.printf("%s %s\n", ServerUI.formatter.format(new Date()), message);
		//System.out.println("message");
	}

	private static class Listener implements Runnable {
		private BufferedReader in;
		
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
						setCards(plmsg);
					}
					//Reading Other messages
					else if (plmsg.getMessageType().equalsIgnoreCase("Other")) {
						OtherMessage other = (OtherMessage) plmsg;
						if ((other.getMessage()) != null) {
							addToLogs(other.getMessage());
						}
					}
					else if (plmsg.getMessageType().equalsIgnoreCase("SuggestionResponse")) {
						SuggestionResponse suggResp = (SuggestionResponse) plmsg;
						String resp = suggResp.getMessage();
						showPane(resp);
					}
					/*
					read = in.readLine();
					if (!(read.isEmpty()) && read.contains("CARDS"))  //This is the card players get
					{
						//String cardlists = read.replace("CARDS", "");//read.substring(firstSpace + 1);
						setCards(read);
					}
					else if (read != null && !(read.isEmpty())) addToLogs(read); */
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