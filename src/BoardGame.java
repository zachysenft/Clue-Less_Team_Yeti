import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;


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
    const String [] players = {"Miss Scarlet", "Col. Mustard", "Mrs. White", "Mr. Green", "Mrs. Peacock", "Prof. Plum"};
    const int [] startingHallways = {47, 78, 69, 36, 23, 12};
    const String [] rooms = {"Study", "Library", "Conservatory", "Hall", "Billiard Room", "Ballroom", "Lounge", "Dining Room", "Kitchen"};
    const String [] weapons = {"Revolver", "Dagger", "Lead Pipe", "Rope", "Candlestick", "Wrench"};

    
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
        	if (accAnswer.equalsIgnoreCase("yes")) {
        		System.out.println("Enter a character: ");
        		String accChar = input.next();
        		System.out.println("Enter a location: ");
        		String accLocation = input.next();
        		System.out.println("Enter a weapon");
        		String accWeapon = input.next();
        		PlayerMessage accusation = new PlayerMessage.SuggestionOrAccusation(accChar, accLocation, accWeapon, true);
			
			String winChar = "";
			String winLocation = "";
			String winWeapon = "";
			
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
        			activePlayers.get(currPlayerIndex).set
        			endGame();
        		} else { //if you were wrong
        			activePlayers.get(currPlayerIndex).setLostGameFlag();
        			System.out.println(activePlayers.get(currPlayerIndex).getPlayerName() + " has lost the game.")
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
        
        } 
        
    
    
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
            
            newGame();
            
        }
        
    }
    
    
    public ArrayList<Card> generateWinCondition(){

        
        int rndPlayer = new Random().nextInt(players.length);
        int rndRoom = new Random().nextInt(rooms.length);
        int rndWeapon = new Random().nextInt(weapons.length);
        
        winCondition.put("Player", players[rndPlayer]);
        winCondition.put("Room", rooms[rndRoom]);
        winCondition.put("Weapon", weapons[rndWeapon]);
        
        Card[] array_cards = createCards();
        ArrayList<Card> cards = new ArrayList<Card>(array_cards);
        cards.remove(rndPlayer);
        cards.remove(rndRoom + 12);
        cards.remove(rndWeapon + 6);

        return cards;
        
    }
    
    public void dealCards(ArrayList<Card> cards) {
   	 
   	 
   	 for(int i = 0; i < cards.size(); i++) {
   		 
   		 int x = i % activePlayers.size();
   		 activePlayers.get(x).addCard(cards.get(i));
   		 
   		 
   	 }
   	 
   	 
    }
    
    public void storeWinCondinDB(){
        
        String player = winCondition.get("Player");
        String room = winCondition.get("Room");
        String weapon = winCondition.get("Weapon");
        
        try{  
		Class.forName("com.mysql.jdbc.Driver");  
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
		stmt1.setInt(5, 5);
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
    
    public void displayAllCards(){
        
        for (int i = 0; i < activePlayers.size(); i++){
            
            Card [] cards = activePlayers[i].getPlayerCard();
            for (int j = 0; j < cards.size(); j++){
                
                cards[i].printCard();
                
            }
            
        }
        
    }
  
    
    /** test driver code 
     public static void main(String []args){
        BoardGame obj = new BoardGame();
        //System.out.println(obj.gameID);
        System.out.println(obj.generateWinCondition());
        obj.storeWinCondinDB();
     }
     **/
}
