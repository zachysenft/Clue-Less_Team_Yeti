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
import clueless.Suggestions;

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
import java.util.Map;
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
	private static int numPlayers = 1; //also players ID
	private static int numInactivePlayers = 1; //number of players who have the lost game flag = true
	private static String suggestionMaker;  //Name of player who made last Suggestion
	private static int numPlayersDisproveSugg = 0; //number of players who got chance to disprove suggestion
	private static SuggestionOrAccusation currentSuggestion; //last suggestion
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
	
	private static void  handleEndTurn(EndTurnMessage move, String pName) throws IOException {
		
		//int nextPlyr = (nameToIdMap.get(pName)) % players.size();
		String nextName = nextTurn(pName); //players.get(nextPlyr).getPlayerName();
		OtherMessage ms = createOtherMessage(pName + " ended turn. " + nextName + "'s turn.");
		ms.setPlayerTurn(nextName);
		broadcastMessage(ms);
	}
	
	private static void  handleMove(MoveMsg move, String pName) throws IOException {
		
		//MoveMsg move = (MoveMsg) msg;
		Location location = move.getLocation();
		String loc = location.getLocationName();
		int locId = location.getLocationID();
		Player plyr = nameToPlayerMap.get(pName);
		
		boolean isOccupied = false;
		for (Map.Entry playername : nameToPlayerMap.entrySet()){
			
			Player thisplayer = (Player)playername.getValue();
			/*if(thisplayer.getLocation() == null) {
				System.out.println(playername + " IS NULL");
				break;
			}*/
			String thisplayerlocation = thisplayer.getLocation().getLocationName();
			//System.out.println(playername + " " + thisplayerlocation);
			//String playerlocation = (String)playername.getValue().getLocation();
			if (loc.equalsIgnoreCase(thisplayerlocation) && thisplayer.getLostGameFlag()==false) {
				//System.out.println(playername + " occupies " + thisplayerlocation);
				isOccupied = true;
			}			
		}
		
		if (plyr.move(locId) == -1) {
			
			OtherMessage error = createOtherMessage("You cannot move to " + loc);
			error.setPlayerTurn(pName);
			sendMessageToAPlayer(pName, error);
			
		}
		//if (location.getLocationType() == LocationType.HALLWAY && location.isOccupied()) {
		else if (location.getLocationType() == LocationType.HALLWAY && isOccupied) {
			OtherMessage error = createOtherMessage (loc + " is occupied. Please select another destination.");
			error.setPlayerTurn(pName);
			sendMessageToAPlayer(pName, error);
			
		}
		else {
			
			//String loc = plyr.getLocation().getLocationName();
			OtherMessage ms = createOtherMessage(pName + " moved to " + loc);
			plyr.setLocation(location);
			//Set next player turn
			//int nextPlyr = (nameToIdMap.get(pName)) % players.size(); // + 1) % numPlayer; Maping id to name???
			//String nextName = players.get(nextPlyr).getPlayerName();
			broadcastMessage(ms);
			/*
			if (location.getLocationType() == LocationType.HALLWAY) {
	   			String nextName = nextTurn(pName);
				ms.setPlayerTurn(nextName);
				broadcastMessage(ms);
			}
			else {
				ms.setPlayerTurn(pName); //player still have turn
				broadcastMessage(ms);
			}*/
		}	
	}
	
	private static String nextTurn(String plyrName) {
		int nextPlyr = 0;
		int count = 0;  //To keep the loop from going to infinity (Should Not happen though)
		
		//check if player has been disabled before setting next Player
		boolean disablePlyr = true;
		do {
			nextPlyr = (nameToIdMap.get(plyrName)) % players.size();
			disablePlyr = players.get(nextPlyr).getLostGameFlag();
			plyrName = players.get(nextPlyr).getPlayerName();
			count++;
		} while (disablePlyr == true && count < players.size()-1);
		String nextName = players.get(nextPlyr).getPlayerName();
		return nextName;
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
		
		//Next player to disprove suggestion
		//int nextPlyr = (nameToIdMap.get(name)) % players.size(); // + 1) % numPlayer; Maping id to name???
		//players.get(nextPlyr).getPlayerName();
		
		 //case where it is accusation
		  if (sugg.getAccusationFlag()) {
			  
			Player plyr = nameToPlayerMap.get(name);
			OtherMessage om = createOtherMessage(name + " accuses that "+ pl + " committed the crime with a "+ weapon + " in the "+ loc);
			broadcastMessage(om);
			boolean check = boardGame.checkAcussation(pl, loc, weapon);
			if (!check) {
				plyr.setLostGameFlag();
				numInactivePlayers++;
				OtherMessage ar = createOtherMessage("The accusation is incorrect." + plyr.getPlayerName() + " loses");
// 				String nextName = nextTurn(name);
// 				ar.setPlayerTurn(nextName);
				broadcastMessage(ar);
				//send message for End of the Game
				EndGameMessage egm = new EndGameMessage();
				egm.setMessage("You lose");
				sendMessageToAPlayer(name, egm);
				String nextName = nextTurn(name);
				ar.setPlayerTurn(nextName);
				if (numPlayers - numInactivePlayers == 1) {
					OtherMessage win =  createOtherMessage("GAME OVER! " + nextName + " wins!");
					broadcastMessage(win);
				}
				
			}
			else {
				OtherMessage ar = createOtherMessage("The accusation is correct!" + plyr.getPlayerName() + " wins!");
				ar.setPlayerTurn("GAME OVER"); 
				broadcastMessage(ar);		
			}
		  }	
		  
		  else {  //it is suggestion			
			Player plyr = nameToPlayerMap.get(name);
			suggestionMaker = name;    //Save this name if we have to pass it for disproving suggestion
			currentSuggestion = sugg;  //Save this Suggestion as the last suggestion
			numPlayersDisproveSugg = 0;
			//check if a player's last suggestion was at this location
			if (plyr.getPlayerPrevSuggLocation() == null ||
					!(plyr.getPlayerPrevSuggLocation().equalsIgnoreCase(plyr.getLocation().getLocationName())) ) {
				
				if (plyr.getLocation().getLocationName().equalsIgnoreCase(loc)) {
					OtherMessage om = createOtherMessage("suggestion made on "+ pl + " with "+ weapon + " on "+ loc);
					plyr.setPlayerPrevSuggLocation(plyr.getLocation().getLocationName()); 
					// move suggested char to location here
					// should in theory set the location to the player who's making the suggestion's location
					if (plyr.getCharacterName().equalsIgnoreCase(pl)) {
						// do nothing, character is already in the room of the player
					} else { //if an active player's character needs to be moved
					for (Map.Entry playername: nameToPlayerMap.entrySet()){
						Player thisplayer = (Player) playername.getValue();
						if (thisplayer.getCharacterName().equalsIgnoreCase(pl)) {
							thisplayer.setLocation(plyr.getLocation());
							OtherMessage ms = createOtherMessage(thisplayer.getPlayerName() + " has been moved to " + plyr.getLocation().getLocationName());
							broadcastMessage(ms);
						}
					}
				}

					broadcastMessage(om);
					
					//Request the other player to show card
					//should be while loop
					SuggestionResponse resp = new SuggestionResponse();
					resp.setMessage("Do you want to disprove "+ name +"'s Suggestion?");
					
					//Next player to disprove suggestion
					int nextPlyr = (nameToIdMap.get(name)) % players.size(); // + 1) % numPlayer; Maping id to name???
					String nextName = players.get(nextPlyr).getPlayerName();
					resp.setPlayerTurn(nextName);
					numPlayersDisproveSugg++;
					sendMessageToAPlayer(nextName, resp);
				} 
				else {
						
					String response = "You can not make suggestion at this location";
					OtherMessage reply = createOtherMessage(response);
					reply.setPlayerTurn(name);
					sendMessageToAPlayer(name, reply);
				}
			}
			else {
				String response = "You Already made Suggestion at this Location";
				OtherMessage reply = createOtherMessage(response);
				reply.setPlayerTurn(name);
				sendMessageToAPlayer(name, reply);
			}
		  }
	}
	
	private static void disproveSuggestion(String playerName) throws IOException {
		SuggestionResponse resp = new SuggestionResponse();
		int nextPlyr = (nameToIdMap.get(playerName)) % players.size(); // + 1) % numPlayer; Maping id to name???
		String nextName = players.get(nextPlyr).getPlayerName();
		resp.setMessage("Do you want to disprove "+ suggestionMaker +"'s Suggestion?");
		resp.setPlayerTurn(nextName); 
		numPlayersDisproveSugg++;
		sendMessageToAPlayer(nextName, resp);
		
	}
	
	private static Card getSuggDisproveCard(String pName) { 
		Player player = nameToPlayerMap.get(pName);
		Card person = new Card(currentSuggestion.getCharacter(), "Person");
		Card weapon = new Card (currentSuggestion.getWeapon(), "Weapon");
		Card loc = new Card(currentSuggestion.getLoc(), "room");
		Card c = player.disprove(person, weapon, loc);
		return c;
	}
	
	private static String getNextTurnAfterSuggestion() {
		int nextPlyr = (nameToIdMap.get(suggestionMaker)) % players.size(); // + 1) % numPlayer; Maping id to name???
		String nextName = players.get(nextPlyr).getPlayerName();
		return nextName;
		
	}
	
	private static void movePlayerToSuggestedRoom() {
		String plyrName = suggestionMaker; // currentSuggestion.getCharacter(); //should be player's name
		Player plyr = nameToPlayerMap.get(plyrName);
		Location newLocation = new Location(BoardGame.getLocationID(currentSuggestion.getLoc()),
				currentSuggestion.getLoc(), currentSuggestion.getLocationType());
		plyr.setLocation(newLocation);
	}
	
	//Sending players their Cards
	private static void dealCardsToPlayers() throws IOException {
		int i = 0;
		DealCardMessage dealCard;
		for (ObjectOutputStream p: connectedPlayers.values()) {
			dealCard = new DealCardMessage();
			dealCard.setPlayerTurn(players.get(0).getPlayerName());
			dealCard.setCards(players.get(i).getPlayerCard());
			dealCard.setStartLocation(players.get(i).getLocation());
			dealCard.setPlayerCharName(players.get(i).getCharacterName());
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
				OtherMessage idMsg = createOtherMessage("id "+ numPlayers);
				objectOutputStream.writeObject(idMsg);
				
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
				
				
				
				//while( (message=in.readLine()) != null && !exit) {
				  while( (pMsg = (PlayerMessage) objectInputStream.readObject()) != null && !exit) {
					
					  String type = pMsg.getMessageType();
					  if (type.equalsIgnoreCase("SuggestionAccusation")) {
						  handleSuggestionAccusation(pMsg, name);
					  }
					  else if (type.equalsIgnoreCase("DealCards")) {
						  if (gameStarted) {
							  OtherMessage oMsg = createOtherMessage("Game has already been Started");
							  //broadcastMessage(oMsg);
							  oMsg.setPlayerTurn(name);
							  sendMessageToAPlayer(name, oMsg);
						  }	
						  else {
							  
							gameStarted = true;
							boardGame.handCards(players); //set initial location as well???
							dealCardsToPlayers();
							}
					  }
					  else if (type.equalsIgnoreCase("other")) {
						  OtherMessage oms = (OtherMessage) pMsg;
						  OtherMessage oMsg = createOtherMessage(name + ": "+ oms.getMessage());
						  oMsg.setPlayerTurn(name);
						  broadcastMessage(oMsg);
					  } 
					  else if(type.equalsIgnoreCase("SuggestionResponse")) {
						  SuggestionResponse suggResponse = (SuggestionResponse) pMsg;
						  String resp = suggResponse.getMessage();
						  if (resp.equalsIgnoreCase("yes")) {
							  Card card = getSuggDisproveCard(name);
							  if (card != null) {
								  //movePlayerToSuggestedRoom();
								  String approvedMsg =  card.getName();
								  OtherMessage resMsg = createOtherMessage(approvedMsg + " is incorrect.");
								  //String nxtTurn = getNextTurnAfterSuggestion(); 
								  //resMsg.setPlayerTurn(nxtTurn);
								  //sendMessageToAPlayer(suggestionMaker, resMsg);
								  OtherMessage notifyAll = createOtherMessage("Suggestion was disproved by " + name);
								  //notifyAll.setPlayerTurn(nxtTurn);
								  broadcastMessage(notifyAll); //if we need to tell others??
								  sendMessageToAPlayer(suggestionMaker, resMsg);
								 								  
							  } else {
								  String errorMsg =  "You don't have a card to disprove Suggestion ";
								  OtherMessage erMsg = createOtherMessage(errorMsg);
								  //String nxtTurn = getNextTurnAfterSuggestion(); 
								  //erMsg.setPlayerTurn(nxtTurn);
								  sendMessageToAPlayer(name, erMsg);
								  //erMsg.setMessage(name + " Can not disprove Suggestion");
								  //broadcastMessage(erMsg);
								  if (numPlayersDisproveSugg < (players.size() - 1))
									  disproveSuggestion(name);
							  }					  
							  
						  } else {  //ask the next player to disprove
							  if (numPlayersDisproveSugg < (players.size() - 1))
								  disproveSuggestion(name);
							  else { //No one diprove suggestion
								  OtherMessage re = createOtherMessage("No one disproved the Suggestion");
								  String nxtPlyrTurn = getNextTurnAfterSuggestion();
								  re.setPlayerTurn(nxtPlyrTurn);
								  broadcastMessage(re);
								  
								//EndTurnMessage endturn = new EndTurnMessage();
								  //handleEndTurn(endturn, name);
							  }
						  }   
					  }
					  else if(type.equalsIgnoreCase("Move")) {
						  MoveMsg moveMsg = (MoveMsg) pMsg;
						  handleMove(moveMsg, name);
					  }
					  
					  else if(type.equalsIgnoreCase("End Turn")) {
						  EndTurnMessage endMsg = (EndTurnMessage) pMsg;
						  handleEndTurn(endMsg, name);  
					  }
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
					//int nextPlyr = (nameToIdMap.get(name)) % players.size();
					//String nextName = players.get(nextPlyr).getPlayerName();
					//players.remove(nextPlyr-1);
					//oMsg.setPlayerTurn(nextName);
					try {
						broadcastMessage(oMsg);
					} catch (IOException e) {
						addToLogs(e.getMessage());
					}
				}
			}
		}
	}

}
