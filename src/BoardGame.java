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
        
        //instantiate rooms and rest of hallways - zach
        //instantiate inactive players - Ashley
        //-Dawit and Matt
        //while game is not won - make accusation (y/n), option to move (y/n), option to make sugg (y/n)
        //check if suggestion is disprovable, choose card to show, end turn
        //PowerPoint - abby
        
        
        
    }
    
    public void endGame() {
        
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
   		 activePlayers(x).playerCards.add(cards(i)));
   		 
   		 
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