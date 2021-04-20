package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import me.alexpanov.net.FreePortFinder;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import clueless.BoardGame;
import clueless.Card;
import clueless.Player;
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
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ServerUI extends JFrame implements ActionListener {	

	// Socket Related
//	public static SimpleDateFormat formatter = new SimpleDateFormat("[MM/hh/yy hh:mm a]");
	public static SimpleDateFormat formatter = new SimpleDateFormat("[hh:mm a]");
	private static HashMap<String, PrintWriter> connectedClients = new HashMap<>();
	private static HashMap<String, ObjectOutputStream> connectedPlayers = new HashMap<>();
	private static HashMap<String, Player> nameToPlayerMap = new HashMap<>();
	private static HashMap<String, Integer> nameToIdMap = new HashMap<>();
	private static final int MAX_CONNECTED = 6;
	private static int PORT;
	private static ServerSocket server;
	private static volatile boolean exit = false;
	private static int numPlayers = 1;
	
	//public static BoardGame bg = new BoardGame();
	// JFrame related
	private JPanel contentPane;
	private JTextArea txtAreaLogs;
	private JButton btnStart;
	private JLabel lblServer;
	private static ObjectInputStream myreader = null;
	private static ObjectOutputStream mywriter = null;
	public static int num=0;
	private static BoardGame boardGame;
	public static ArrayList<Card> cardList;
	private static ArrayList<Player> players;
	private static ServerUI frame;
	private static boolean gameStarted = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					ServerUI frame = new ServerUI(); 
					
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SwingUtilities.updateComponentTreeUI(frame);
					//Logs
					System.setOut(new PrintStream(new TextAreaOutput(frame.txtAreaLogs)));
					frame.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerUI() { 
		boardGame = new BoardGame();
		players = new ArrayList<Player>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblServer = new JLabel("SERVER");
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblServer.setFont(new Font("Tahoma", Font.PLAIN, 40));
		contentPane.add(lblServer, BorderLayout.NORTH);

		btnStart = new JButton("START");
		btnStart.addActionListener(this);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(btnStart, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		txtAreaLogs = new JTextArea();
		txtAreaLogs.setBackground(Color.BLACK);
		txtAreaLogs.setForeground(Color.WHITE);
		txtAreaLogs.setLineWrap(true);
		scrollPane.setViewportView(txtAreaLogs);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			if(btnStart.getText().equals("START")) {
				exit = false;
				getPort();
				start();
				btnStart.setText("STOP");
			}else {
				addToLogs("Server stopped...");
				exit = true;
				btnStart.setText("START");
			}
		}
		
		//Refresh UI
		refreshUIComponents();
	}
	
	public void refreshUIComponents() {
		lblServer.setText("SERVER" + (!exit ? ": "+PORT:""));
	}

	public static void start() {
		new Thread(new ServerHandler()).start();
	}

	public static void stop() throws IOException {
		if (!server.isClosed()) server.close();
	}

	//private static void broadcastMessage(String message) {
	private static void broadcastMessage(PlayerMessage msg) throws IOException {
		/*
		for (PrintWriter p: connectedClients.values()) {
			p.println(message);
		} */
		for (ObjectOutputStream p: connectedPlayers.values()) {
			p.writeObject(msg);
		}
	}
	
	private static OtherMessage createOtherMessage(String msg) {
		OtherMessage otherMsg = new OtherMessage();
		otherMsg.setMessage(msg);
		return otherMsg;
	}
	
	private static void  handleSuggestionAccusation(PlayerMessage msg, String name) throws IOException {
		SuggestionOrAccusation sugg = (SuggestionOrAccusation) msg;
		String pl = sugg.getCharacter();
		String weapon = sugg.getWeapon();
		String loc = sugg.getLoc();
		
		 //case where it is accusation
		  if (sugg.getAccusationFlag()) {
		  	//call function BoarGame
			//boolean check = boardGame.checkAccusation(Player, room, weapon);
			//if (!check) {
				//player.setAccussationflag(false)
			//}
		  }
		  else {  //it is suggestion			
			Player plyr = nameToPlayerMap.get(name);
			if (plyr.getLocation().getLocationName().equalsIgnoreCase(loc)) {
				OtherMessage om = createOtherMessage("suggestion made on "+ pl + " with "+ weapon + " on "+ loc);
				broadcastMessage(om);
				//Request the other player to show card
				//should be while loop
				SuggestionResponse resp = new SuggestionResponse();
				resp.setMessage("Do you want to disprove "+ name +"'s Suggestion?");
				int nextPlyr = (nameToIdMap.get(name)) % players.size(); // + 1) % numPlayer; Maping id to name???
				String nextName = players.get(nextPlyr).getPlayerName();
				sendMessageToAPlayer(nextName, resp);
			} 
			else {
				
				String response = "You can not make suggestion at this location";
				OtherMessage reply = createOtherMessage(response);
				sendMessageToAPlayer(name, reply);
			}
			/*
			 
			 */
		  }
		 
	}
	
	//Sending players their Cards
	private static void dealCardsToPlayers() throws IOException {
		int i = 0;
		for (ObjectOutputStream p: connectedPlayers.values()) {
			DealCardMessage dealCard = new DealCardMessage();
			dealCard.setCards(players.get(i).getPlayerCard());
			i++;
			p.writeObject(dealCard);
		}
	}
	
	private static void sendMessageToAPlayer(String name, PlayerMessage msg) throws IOException {
		ObjectOutputStream p = connectedPlayers.get(name);
		p.writeObject(msg);
	}
	
	public static void addToLogs(String message) {
		System.out.printf("%s %s\n", formatter.format(new Date()), message);
	}

	private static int getPort() {
		int port = 5005; //FreePortFinder.findFreeLocalPort();
		PORT = port;
		return port;
	}
	public static int cardNumIndex() {
		int val = num;
		num++;
		
		return val;
				
	}
	/*public static String plyrsCard(){
		
		
	} */
	
	private static class ServerHandler implements Runnable{
		@Override
		public void run() {
			try {
				server = new ServerSocket(PORT);
				addToLogs("Server started on port: " + PORT);
				addToLogs("Now listening for connections...");
				while(!exit) {
					if (connectedClients.size() <= MAX_CONNECTED){
						new Thread(new ClientHandler(server.accept())).start();
					}
				}
			}
			catch (Exception e) {
				addToLogs("\nError occured: \n");
				addToLogs(Arrays.toString(e.getStackTrace()));
				addToLogs("\nExiting...");
			}
		} 
	}
	
	// Start of Client Handler
	private static class ClientHandler implements Runnable {
		private Socket socket;
		private PrintWriter out;
		
		private OutputStream outputStream;
		private ObjectOutputStream objectOutputStream; // = new ObjectOutputStream(outputStream);
		private ObjectInputStream objectInputStream; //= new ObjectInputStream(inputStream);
		private InputStream inputStream;             // = socket.getInputStream();
		private BufferedReader in;
		private String name;
		
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run(){  //inside void main
			addToLogs("Client connected: " + socket.getInetAddress());
			try {
				outputStream = socket.getOutputStream();
				objectOutputStream = new ObjectOutputStream(outputStream);
				inputStream = socket.getInputStream();
				objectInputStream = new ObjectInputStream(inputStream);

				//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//out = new PrintWriter(socket.getOutputStream(), true);
				for(;;) {
					//name = in.readLine();
					PlayerMessage pMsg = new PlayerMessage();
					pMsg = (PlayerMessage) objectInputStream.readObject();
					if (pMsg.getMessageType().equalsIgnoreCase("Other")) {
						OtherMessage oMsg = (OtherMessage) pMsg;
						name = oMsg.getMessage(); 
					}
					if (name == null) {
						return;
					}/*
					synchronized (connectedClients) {
						if (!name.isEmpty() && !connectedClients.keySet().contains(name)) break;
						else out.println("INVALIDNAME");
					}*/
					synchronized (connectedPlayers) {
						if (!name.isEmpty() && !connectedPlayers.keySet().contains(name)) break;
						else out.println("INVALIDNAME");
					}
				}
				//out.println("Welcome to Clueless Game, " + name.toUpperCase() + "!");
				OtherMessage otherMsg = createOtherMessage("Welcome to Clueless Game, " + name.toUpperCase() + "!"); 
				objectOutputStream.writeObject(otherMsg); 
				
				addToLogs(name.toUpperCase() + " has joined.");
				//broadcastMessage("[SYSTEM] " + name.toUpperCase() + " has joined.");
				OtherMessage other = createOtherMessage("[SYSTEM] " + name.toUpperCase() + " has joined.");
				broadcastMessage(other);
				connectedClients.put(name, out);
				
				connectedPlayers.put(name, objectOutputStream);
				
				Player player = new Player(name, numPlayers);
				players.add(player);
				nameToPlayerMap.put(name, player);  //Add to name-player map
				nameToIdMap.put(name, numPlayers);
				//String message;
				PlayerMessage pMsg;
				//out.println("You may join the game now...");
				numPlayers++;
				
				/*/--------------------------
				Object msg = myreader.readObject();
				while ( (String) msg != null && !exit) {
					if ( !((String) msg).isEmpty()) {
						//if (message.toLowerCase().equals("/quit")) break;
						String response = bg.handleMessage(msg);
						broadcastMessage(String.format("[%s] %s", name, response));
				}
				-----------------------*/
				//if (numPlayers == 3)
				//{
					//bg.dealCards(cards);
				//	numPlayers= 0;
					//message = "deal cards";
				//}
				//else {
				//	message = in.readLine();
				//}
				
				//while( (message=in.readLine()) != null && !exit) {
				  while( (pMsg = (PlayerMessage) objectInputStream.readObject()) != null && !exit) {
					
					  String type = pMsg.getMessageType();
					  if (type.equalsIgnoreCase("SuggestionAccusation")) {
						  handleSuggestionAccusation(pMsg, name);
					  }
					  else if (type.equalsIgnoreCase("DealCards")) {
						  if (gameStarted) {
							  OtherMessage oMsg = createOtherMessage("Game has already been Started");
							  broadcastMessage(oMsg);
						  }	else {
							  
							gameStarted = true;
							boardGame.handCards(players); //set initial location as well???
							//broadcastMessage("player0 card "+ players.get(0).getPlayerCard().size());
							dealCardsToPlayers();
							}
					  }else if (type.equalsIgnoreCase("other")) {
						  OtherMessage oms = (OtherMessage) pMsg;
						  OtherMessage oMsg = createOtherMessage(name + ": "+ oms.getMessage());
						  broadcastMessage(oMsg);
					  } else if(type.equalsIgnoreCase("SuggestionResponse")) {
						  String resp = "The suggestion is disproved by "+ name;
						  OtherMessage resMsg = createOtherMessage(resp);
						  broadcastMessage(resMsg);
					  }
					  
					  /*
					  if (!message.isEmpty()) {
						if (message.toLowerCase().equals("/quit")) break;
						
						//check if the message is deal card
						if (message.equalsIgnoreCase("start game"))
						{
							if (gameStarted)
								broadcastMessage("Game has already been Started");
							else {
								gameStarted = true;
								boardGame.handCards(players);
								//broadcastMessage("player0 card "+ players.get(0).getPlayerCard().size());
								dealCardsToPlayers();
							}
						}
						
						else {
							//String response = bg.handleMessage(message);
							broadcastMessage(String.format("[%s] %s", name, message));
						}
						
					}    */
				} 
			} catch (Exception e) {
				addToLogs(e.getMessage());
			} finally {
				if (name != null) {
					addToLogs(name + " is leaving");
					connectedClients.remove(name);
	connectedPlayers.remove(name);
					//broadcastMessage(name + " has left");
	OtherMessage oMsg = createOtherMessage(name + " has left");
	try {
		broadcastMessage(oMsg);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		addToLogs(e.getMessage());
	}
				}
			}
		}
	}

}
