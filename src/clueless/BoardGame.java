
package clueless;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

import UI.ServerUI;

//import Card;
//import Card.cardLists;

//import Card.cardLists;
//import PlayerMessage.SuggestionOrAccusation;

import java.sql.DriverManager;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.*; 



public class BoardGame{
	
	
    ArrayList<Player> activePlayers = new ArrayList<Player>();
    ArrayList<Player> inactivePlayers = new ArrayList<Player>();
    ArrayList<Room> room_list = new ArrayList<Room>();
    int gameID = 0;
    Dictionary<String, String> winCondition = new Hashtable<String, String>();
    private final static String [] players = {"Miss Scarlet", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
    private final static int [] startingHallways = {47, 78, 69, 36, 23, 12};
    private final static  String [] rooms = {"Study", "Library", "Conservatory", "Hall", "Billiard Room", "Ballroom", "Lounge", "Dining Room", "Kitchen"};
    private final static String [] weapons = {"Revolver", "Dagger", "Lead Pipe", "Rope", "Candlestick", "Wrench"};
    private static PlayerMessage.SuggestionOrAccusation suggorAccu;
    private static ArrayList<Card> dealCards = new ArrayList<Card>();
    private static boolean flag = true;
    
    public BoardGame() {
		ArrayList<Card> arr =  generateWinCondition();
		storeWinCondinDB();
	}
    
    public void newGame() {
        
        Scanner input = new Scanner(System.in);
        System.out.println("How many players are there?: ");
        
        int numPlayers = input.nextInt();

        
        for (int i = 0; i < numPlayers; i++){
            
            Player x = new Player();
            this.addPlayer(x);
            x.setPlayerActive();
            x.setPlayerID(i);
            x.setPlayerName(players[i]);
            x.setOrderNum(i);
            x.setLocation(new Hallway(startingHallways[i], "Hallway" + startingHallways[i], true));
            
        }
        
        //generate win condition
        ArrayList<Card> cards = generateWinCondition();
        storeWinCondinDB();
        //deal cards to players
        dealCards(cards);
        
        boolean gameWon = false;
        int currPlayerIndex = -1; //tracks who's turn it is
        

        //instantiate rooms and rest of hallways - zach
        for(int i = 0; i < rooms.length; i++) {
      	  room_list.add(new Room(i+1, rooms[i]));
        }
        
        Hallway Hallway14 = new Hallway(14, "Hallway 14");
        Hallway Hallway25 = new Hallway(25, "Hallway 25");
        Hallway Hallway45 = new Hallway(45, "Hallway 45");
        Hallway Hallway56 = new Hallway(56, "Hallway 56");
        Hallway Hallway58 = new Hallway(58, "Hallway 58");
        Hallway Hallway89 = new Hallway(89, "Hallway 89");
        // going to add the rest here just so they have names....
        Hallway Hallway47 = new Hallway(47, "Hallway 47");
        Hallway Hallway12 = new Hallway(12, "Hallway 12");
        Hallway Hallway78 = new Hallway(47, "Hallway 78");
        Hallway Hallway23 = new Hallway(23, "Hallway 23");
        Hallway Hallway36 = new Hallway(36, "Hallway 36");
        Hallway Hallway69 = new Hallway(69, "Hallway 69");
        if(numPlayers < 6) {
      	  int x = numPlayers; //4 players means last 2 hallways in array have not been instantiated
      	  
      	  for(int i = x; i < 6; i++) {
      		  Hallway addHallway = new Hallway(startingHallways[i], "Hallway " + startingHallways[i]);
      	  }
      	  
        }
        
        
        //instantiate inactive players - Ashley
	int numInactivePlayers = players.length - numPlayers;
	
	for (int i = 0; i < numInactivePlayers; i++){
            
            Player x = new Player();
            this.addInactivePlayer(x);
            x.setPlayerName(players[i + numPlayers]);
            x.setLocation(new Hallway(startingHallways[i + numPlayers], "Hallway" + startingHallways[i + numPlayers], false));
            
        }
	
        //-Dawit and Matt
        //while game is not won - make accusation (y/n), option to move (y/n), option to make sugg (y/n)
        //check if suggestion is disprovable, choose card to show, end turn
        //PowerPoint - abby
        
        while (gameWon !=true) {
        	currPlayerIndex++;
        	currPlayerIndex = currPlayerIndex % activePlayers.size();
        	currPlayerIndex = checkLostGame(currPlayerIndex);
        	while (activePlayers.get(currPlayerIndex).getLostGameFlag() == true) { //if the current player lost the game
        		System.out.println(activePlayers.get(currPlayerIndex).getPlayerName() + "'s turn is skipped because he/she lost the game");
            	currPlayerIndex++;
            	currPlayerIndex = currPlayerIndex % activePlayers.size();
        	}
        	System.out.println(activePlayers.get(currPlayerIndex).getPlayerName() +"'s turn to play!");
 
        	
        	//MAKING ACCUSATION
        	
        	System.out.println("Would you like to make an accusation? (yes/no)");
        	String accAnswer = input.next();
        	String accChar=null;
        	String winChar = "";
			String winLocation = "";
			String winWeapon = "";
			String accLocation = null;
			String accWeapon = null;
			
        	if (accAnswer.equalsIgnoreCase("yes")) {
        		System.out.println("Enter a character: ");
        		accChar = input.next();
        		System.out.println("Enter a location: ");
        		accLocation = input.next();
        		System.out.println("Enter a weapon");
        		accWeapon = input.next();
        		PlayerMessage pm = new PlayerMessage();
        		//suggorAccu = new SuggestionOrAccusation(accChar, accLocation, accWeapon, true);
        		//PlayerMessage.SuggestionOrAccusation sugAcc = pm.new SuggestionOrAccusation(accChar, accLocation, accWeapon, true);
			
			
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/TeamYeti","root","teamyeti");  
				//here TeamYeti is database name, root is username and teamyeti is password  
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("select Player, Room, Weapon from CLUELESS_SUGGESTIONS where Winning_Flg='T'");
				rs.next();
				winChar = rs.getString(1);
				winLocation = rs.getString(2);
				winWeapon = rs.getString(3);

				con.close();  
				}catch(Exception e){ System.out.println(e);}  
				}  
			
        		if (accChar == winChar && accLocation == winLocation && accWeapon == winWeapon) {
        			//if statement for if the accusation matches what's in the database
        			//activePlayers.get(currPlayerIndex).set
        			endGame();
        		} else { //if you were wrong
        			activePlayers.get(currPlayerIndex).setLostGameFlag();
        			System.out.println(activePlayers.get(currPlayerIndex).getPlayerName() + " has lost the game.");
        			System.out.println("You will now only be able to disprove suggestions.");
        		}
        		
        	}
        	
        	//MOVE OPTION
        	
        	if (this.activePlayers.get(currPlayerIndex).getLostGameFlag() == true) {
        		//do nothing/exit while loop iteration
        	} else { //if player hasn't lost the game
        		System.out.println("You are currently in "); 
        		System.out.println("Would you like to move somewhere? (yes/no)");
        		String moveAns = input.next();
        		if (moveAns.equalsIgnoreCase("yes")) { //if they want to move somewhere
        			System.out.println("Where would you like to move?");
        			String moveLoc = input.next(); // will use name for this... gonna be a little weird with hallways
        			Location destLoc = null;
        			if (moveLoc.startsWith("Hallway")) { //if they want to move to a hallway
        				if (moveLoc.endsWith("14")) {
        					destLoc = Hallway14;
        				} else if (moveLoc.endsWith("47")) {
        					destLoc = Hallway47;
        				} else if (moveLoc.endsWith("12")) {
        					destLoc = Hallway12;
        				} else if (moveLoc.endsWith("45")) {
        					destLoc = Hallway45;
        				} else if (moveLoc.endsWith("78")) {
        					destLoc = Hallway78;
        				} else if (moveLoc.endsWith("25")) {
        					destLoc = Hallway25;
        				} else if (moveLoc.endsWith("58")) {
        					destLoc = Hallway58;
        				} else if (moveLoc.endsWith("23")) {
        					destLoc = Hallway23;
        				} else if (moveLoc.endsWith("56")) {
        					destLoc = Hallway56;
        				} else if (moveLoc.endsWith("89")) {
        					destLoc = Hallway89;
        				} else if (moveLoc.endsWith("36")) {
        					destLoc = Hallway36;
        				} else if (moveLoc.endsWith("69")) {
        					destLoc = Hallway69;
        				}
  
        				
        				
        			} else { //they move to a room
        				if (moveLoc.equalsIgnoreCase("Study")) {
        					destLoc = room_list.get(0);
        			} 	else if (moveLoc.equalsIgnoreCase("Library")) {
        					destLoc = room_list.get(1);
        			}	else if (moveLoc.equalsIgnoreCase("Conservatory")) {
							destLoc = room_list.get(2);
        			} 	else if (moveLoc.equalsIgnoreCase("Hall")) {
							destLoc = room_list.get(3);
        			} 	else if (moveLoc.equalsIgnoreCase("Billiard Room")) {
							destLoc = room_list.get(4);
        			} 	else if (moveLoc.equalsIgnoreCase("Ball Room")) {
							destLoc = room_list.get(5);
        			} 	else if (moveLoc.equalsIgnoreCase("Lounge")) {
							destLoc = room_list.get(6);
        			} 	else if (moveLoc.equalsIgnoreCase("Dining Room")) {
							destLoc = room_list.get(7);
        			} 	else if (moveLoc.equalsIgnoreCase("Kitchen")) {
							destLoc = room_list.get(8);
        			
        		} 
        				
        			}
        			activePlayers.get(currPlayerIndex).updateLocation(destLoc);
        			System.out.println(activePlayers.get(currPlayerIndex).getPlayerName() +" has been updated to "+ activePlayers.get(currPlayerIndex).getLocation());
        } else { //do nothing, they don't want to move
        	
        }
        	// INSERT SUGGESTION CODE HERE
        	
        	}
        }
        
        //} 
        
   /*/---------------- TODO 
    public String getRandomCards(int index) { // ArrayList<Card> card) {
    	 generateWinCondition();
    	
    	String s="";
    	for (int i = index; i < dealCards.size(); i=i + 3) {
    		Card cards = dealCards.get(i);
       		 s += cards.getName() + "\n";
    	}
       
    	return s;
    }
    
    public String handleMessage (String msg) {   //(Object msg) {
    	//if (msg instanceof PlayerMessage)
    		return "good"; //TODO
    	
    	//return "not good";
    }
    */ //----------------
    public int checkLostGame(int currIndex) {
    	int newCurrIndex = currIndex;
    	while (this.activePlayers.get(newCurrIndex).getLostGameFlag() == true) {
    		newCurrIndex = newCurrIndex++;
    		newCurrIndex = newCurrIndex % this.activePlayers.size();
    
    	} 
    		return newCurrIndex;
    }
    
    public static void endGame() {
        
        System.out.println("Game Over!");
        
        try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/TeamYeti","root","teamyeti");  
		//here TeamYeti is database name, root is username and teamyeti is password  
		Statement stmt=con.createStatement();  

		stmt.executeUpdate("delete from CLUELESS_SUGGESTIONS");

		con.close();  
		}catch(Exception e){ System.out.println(e);}  
		  
        
        
        Scanner input = new Scanner(System.in);
        System.out.println("New Game? Y or N: ");
        
        String choice = input.nextLine();
        
        if (choice == "N"){
            
            return;
            
        }
        
        if (choice == "Y"){
            
            //newGame();
            
        }
        
    }
    
    
    public ArrayList<Card> generateWinCondition(){
        
    	Random ran = new Random();
        int rndPlayer = ran.nextInt(players.length);
        int rndRoom = ran.nextInt(rooms.length);
        int rndWeapon = ran.nextInt(weapons.length);
        
        winCondition.put("Player", players[rndPlayer]);
        winCondition.put("Room", rooms[rndRoom]);
        winCondition.put("Weapon", weapons[rndWeapon]);
        
        //Card card = new Card("","");
        //Card.cardLists[] array_cards = Card.cardLists.values(); //cardLists.createCards();
        //ArrayList<Card> cards =    new Card().createCard(); // (ArrayList<Card.cardLists>) Arrays.asList(array_cards);//ArrayList<Card.cardLists>(array_cards);
     
		//ArrayList<Card> cards = new ArrayList<Card>();
		//ArrayList<Card> dealCards = new ArrayList<Card>();
        for (Card.cardLists myVar: Card.cardLists.values()) {
        	Card singleCard = myVar.createCard();
        	dealCards.add(singleCard);
        }

        dealCards.remove(rndPlayer);
        dealCards.remove(rndRoom + 11);
        dealCards.remove(rndWeapon + 4);
      
        shuffle(0,5);     //shffle person
        shuffle(6,10);   //shuffle weapon
        shuffle(11, 18); //shuffle room
        return dealCards;
        
    }
    
    private void shuffle(int start, int end) {
    	
    	Random rand = new Random();
    	for (int i=start; i<end ;i++)
        {
            // Random for remaining positions.
            int r = i + (rand.nextInt(end-i));
            Card temp = dealCards.get(r);
            dealCards.set(r,dealCards.get(i));
            dealCards.set(i,temp);
        }	
	}

	public void dealCards(ArrayList<Card> cards) { 
   	 
   	 for(int i = 0; i < cards.size(); i++) {
   		 
   		 int x = i % activePlayers.size();
   		 activePlayers.get(x).addCard(cards.get(i));	 
   	  }
	} 
   	 
	public void handCards(ArrayList<Player> players) {
		for(int i = 0; i < dealCards.size(); i++) {
	   		 
	   		 int x = i % players.size();
	   		 players.get(x).addCard(dealCards.get(i));	 
	   	  }
		//Assign initial location. Can be changed later
		int j = 0;
		for (Player p: players) {
			Location rm = new Room(j+1, rooms[j], false);
			p.setLocation(rm);
			j++;
		}
	}
	
     public void clearDatabase() {
   
   	try {  
  	 Class.forName("com.mysql.jdbc.Driver");  
  	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/TeamYeti","root","teamyeti");  
  	//here TeamYeti is database name, root is username and teamyeti is password  
   	Statement stmt=con.createStatement();  

   	stmt.executeUpdate("delete from CLUELESS_SUGGESTIONS");

   	con.close();  
	  }
 	 catch(Exception e){ System.out.println(e);}  
	}
	
    public void storeWinCondinDB(){
        
	clearDatabase();
	    
        String player = winCondition.get("Player");
        String room = winCondition.get("Room");
        String weapon = winCondition.get("Weapon");
        
        try{  
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/TeamYeti","root","teamyeti");  
		//here TeamYeti is database name, root is username and teamyeti is password  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from CLUELESS_SUGGESTIONS");  
		/*while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) + "  "+ rs.getString(4) + "  " + rs.getInt(5)  + "  " + rs.getString(6)  + "  " + rs.getString(7) );*/
        
        String sql = "insert into CLUELESS_SUGGESTIONS (Suggestion_ID, Player, Room, Weapon, Made_By, Winning_flg, Is_Accusation) values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt1 = con.prepareStatement(sql);
		stmt1.setInt(1, 1);
		stmt1.setString(2, player);
		stmt1.setString(3, room);
		stmt1.setString(4, weapon);
		stmt1.setInt(5, 7);
		stmt1.setString(6, "T");
		stmt1.setString(7, "F");
		stmt1.executeUpdate();

		con.close();  
		}catch(Exception e){ System.out.println(e);}  
		}  
        
    
    public boolean isPlayerActive(Player player){
        
        return activePlayers.contains(player);
        
    }
	
   
    public void addPlayer(Player player){
        
        activePlayers.add(player);
        
    }
	
    public void addInactivePlayer(Player player){
	    
	 inactivePlayers.add(player);
	    
    }
    
   
    
    public ArrayList<Player> getActivePlayers(){
    	
    	return activePlayers;
    	
    }
    
    public boolean checkAcussation(String ply, String loc, String wpn) {
        
        boolean results = false;
        try{  
          Class.forName("com.mysql.cj.jdbc.Driver");  
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/TeamYeti","root","teamyeti");   
          Statement stmt=con.createStatement();  
          ResultSet rs=stmt.executeQuery(
             "select Player, Room, Weapon from CLUELESS_SUGGESTIONS WHERE SUGGESTION_ID = 1 AND MADE_BY = 7"); 
          
          if (rs.getString("Player").equals(ply) 
                && rs.getString("Room").equals(loc) 
                && rs.getString("Weapon").equals(wpn)) {
            results = true;}
          con.close();
          
          return
            results;
        }
        catch(Exception e){ System.out.println(e);}
        
        return results;
      }
    
    public void displayAllCards(){
        
        for (int i = 0; i < activePlayers.size(); i++){
            
            ArrayList<Card> cards = activePlayers.get(i).getPlayerCard();
            for (int j = 0; j < cards.size(); j++){
                
                cards.get(i).printCard();
                
            }
            
        }
        
    }
  
    
    /** test driver code */
     public static void main(String []args){
    	 //BoardGame obj = new BoardGame();
    	 //ArrayList<Card>  crd = obj.generateWinCondition();
        //*BoardGame obj = new BoardGame();
        //System.out.println(obj.gameID);
    	 //System.out.println(dealCards.size());
    	 //for(Card cd : dealCards)
    	//	 System.out.println(cd.getName() + " "+ cd.getType());
        //obj.storeWinCondinDB(); 
    	
     } //**/
     
}
