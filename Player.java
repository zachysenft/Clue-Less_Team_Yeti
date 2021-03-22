import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


import com.sun.jdi.connect.spi.Connection;


/*
 * Player Class for Team Yeti's Clue-Less game.
 * 
 * Date: 3/22/2021
 */



public class Player {
	
	//Creating attributes
	int playerID;
	String playerNm;
	boolean activeFlg;
	int orderNbr;
	Locations locationID;
	Suggestions[] suggestionList;
	
		
	//Constructors initialization
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public int getPlayerID(Player player) {
		return player.playerID;
	}
	
	public void setPlayerNm(String playerNm) {
		this.playerNm = playerNm;
	}
	
	public String getPlayerNm(Player player) {
		return player.playerNm;
	}
	
	//This constructor should be used to set the boolean value of activeFlg to true when called and passed a created playerID
	public String activatePlayer(int playerID) {
		this.activeFlg = true;
		return "Player: " + playerID + " has been activated.";
	}
	
	public void setOrderNbr(int orderNbr) {
		this.orderNbr = orderNbr;
	}
	
	public int getOrderNbr(Player player) {
		return player.orderNbr;
	}
	
	public void setLocation(Locations locationID) {
		this.locationID = locationID;
	}
	
	public Locations getLocation(Player player) {
		return player.locationID;
	}
	
	//Array of Suggestions the player has made
	public void suggestionMade(Player player, Suggestions suggestion) {
		
		suggestion.append(suggestion, player.suggestionList);
		
	}
	
	
	
	
	 public void testDBConnection() {
    		String username = "ADMIN";
    		String password = "TeamYeti2021";          
    		String server = "jdbc:oracle:thin:@adb.us-ashburn-1.oraclecloud.com:1522:yrmks4zvyivxii7_db202103201405_low.adb.oraclecloud.com";  
    		//java.security.Security.setProperty("networkaddress.cache.ttl" , "0");

    		// With AutoCloseable, the connection is closed automatically.
    		try {
      			Class.forName("oracle.jdbc.driver.OracleDriver");
      			Connection con = (Connection) DriverManager.getConnection(server, username, password);
      			System.out.print("Connection Successful");
    		}   
    		catch (SQLException e)
   			 {System.out.print(e);}
   		catch (ClassNotFoundException e2)
    			{System.out.print(e2);}
 	}
	
}