
package clueless;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
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
    private final static String [] players = {"Colonel Mustard", "Mrs. White", "Professor Plum", " Mrs. Peacock","Mr. Green", "Miss Scarlet"};
    private final static int [] startingHallways = {47, 78, 69, 36, 23, 12};
    private final static  String [] startingSpots = {"ScarletStart","MustardStart","WhiteStart","GreenStart","PeacockStart","PlumStart"};
    private final static String [] assignedCharactersOrder = {"Miss Scarlet", "Colonel Mustard","Mrs. White","Mr. Green","Mrs. Peacock","Professor Plum"}; //assigns to players
    private final static  String [] rooms = {"Kitchen", "Ballroom", "Dining Room", "Lounge", "Hall", "Conservatory", "Billiard Room", "Library", "Study"};
    private final static String [] weapons = {"Dagger", "Rope", "Lead Pipe","Candlestick","Revolver", "Wrench"};
    private static PlayerMessage.SuggestionOrAccusation suggorAccu;
    private static ArrayList<Card> dealCards = new ArrayList<Card>();
    private static ArrayList<Card> winCards = new ArrayList<Card>();
    private static boolean flag = true;
    
    private static Map<String, Integer> locationNameToIDMap = new HashMap<String, Integer>(){{	
    	put("Study", 1);
    	put("Library", 2);
    	put("Conservatory", 3);
    	put("Hall", 4);
    	put("Billiard Room", 5);
    	put("Ballroom", 6);
    	put("Lounge", 7);
    	put("Dining Room", 8);
    	put("Kitchen", 9);    	
    	put("Study-Library Hallway", 12);
    	put("Library-Conservatory Hallway", 23);
    	put("Study-Hall Hallway", 14);
    	put("Library-Billiard Hallway", 25);
    	put("Conservatory-Ballroom Hallway", 36);
    	put("Hall-Lounge Hallway", 47);
    	put("Hall-Billiard Hallway", 45);
    	put("Billiard-Ballroom Hallway", 56);
    	put("Billiard-Dining Hallway", 58);
    	put("Ballroom-Kitchen Hallway", 69);
    	put("Lounge-Dining Hallway", 78);
    	put("Dining-Kitchen Hallway", 89);
    	
    	// Starting locations
    	put("ScarletStart",101);
    	put("MustardStart",102);
    	put("WhiteStart",103);
    	put("GreenStart",104);
    	put("PeacockStart",105);
    	put("PlumStart",106);
    }};
    
    public BoardGame() {
		ArrayList<Card> arr =  generateWinCondition();
		storeWinCondinDB();
	}
    
    //returns the winning cards 
    public ArrayList<Card> getWinCards() {
    	return winCards;
    }
 
        
    public int checkLostGame(int currIndex) {
    	int newCurrIndex = currIndex;
    	while (this.activePlayers.get(newCurrIndex).getLostGameFlag() == true) {
    		newCurrIndex = newCurrIndex++;
    		newCurrIndex = newCurrIndex % this.activePlayers.size();
    
    	} 
    		return newCurrIndex;
    }
    
    public static int getLocationID(String locationName) {
    	return locationNameToIDMap.get(locationName);
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
        winCards.add(dealCards.get(rndPlayer));  //win card1 (get it before removed)
        dealCards.remove(rndPlayer);
        winCards.add(dealCards.get(rndWeapon + 5));  //win card2
        dealCards.remove(rndWeapon + 5);
        winCards.add(dealCards.get(rndRoom + 10));  // win card3
        dealCards.remove(rndRoom + 10);
     
        //shuffle(0,5);     //shffle person
        //shuffle(6,10);   //shuffle weapon
        //shuffle(11, 18); //shuffle room

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
/*
	public void dealCards(ArrayList<Card> cards) { 
   	 
   	 for(int i = 0; i < cards.size(); i++) {
   		 
   		 int x = i % activePlayers.size();
   		 activePlayers.get(x).addCard(cards.get(i));	 
   	  }
	} 
	*/
   	 
	public void handCards(ArrayList<Player> player) {
		for(int i = 0; i < dealCards.size(); i++) {
	   		 
	   		 int x = i % player.size();
	   		 player.get(x).addCard(dealCards.get(i));	 
	   	  }
		//Assign initial location. Can be changed later
		int j = 0;
		int k = 100; //indices of starting spots
		for (Player p: player) {
			Location rm = new Hallway(k+1, startingSpots[j], false);
			String playerCharacter = assignedCharactersOrder[j];
			p.setCharacterName(playerCharacter);
			p.setLocation(rm);
			j++;
			k++;
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
        
    	//clearDatabase();
    	
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
		String clearTable = "DELETE FROM CLUELESS_SUGGESTIONS";
		PreparedStatement clrStmt = con.prepareStatement(clearTable);
		clrStmt.executeUpdate();
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
		}catch(Exception e){ System.out.println("Error while inserting to the DB \n" + e);}  
		}  
           
    public boolean checkAcussation(String ply, String loc, String wpn) {
        
        boolean results = false;
        try{  
          Class.forName("com.mysql.cj.jdbc.Driver");  
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/TeamYeti","root","teamyeti");   
          Statement stmt=con.createStatement();         
          
          ResultSet rs=stmt.executeQuery(
             "select Player, Room, Weapon from CLUELESS_SUGGESTIONS WHERE SUGGESTION_ID = 1 AND MADE_BY = 7");    
          rs.next();
          if (rs.getString("Player").equalsIgnoreCase(ply) 
                && rs.getString("Room").equalsIgnoreCase(loc) 
                && rs.getString("Weapon").equalsIgnoreCase(wpn)) {
        	  results = true;
        	  }
          	con.close();
          
          	//return results;
        }
        catch(Exception e){ System.out.println(e);}
        
        return results;
      }
    
 /*
    public void displayAllCards(){
        
        for (int i = 0; i < activePlayers.size(); i++){
            
            ArrayList<Card> cards = activePlayers.get(i).getPlayerCard();
            for (int j = 0; j < cards.size(); j++){
                
                cards.get(i).printCard();
                
            }
            
        }
        
    }
 */ 
    
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
