/*
 * Player Class for Team Yeti's Clue-Less game.
 * 
 * Date: 3/18/2021
 */



public class Player {
	
	//Creating attributes
	int playerID;
	String playerNm;
	boolean activeFlg;
	int orderNbr;
	Locations locationID;
	
		
	//Constructors initialization
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public void setPlayerNm(String playerNm) {
		this.playerNm = playerNm;
	}
	
	//This constructor should be used to set the boolean value of activeFlg to true when called and passed a created playerID
	public String activatePlayer(int playerID) {
		this.activeFlg = true;
		return "Player: " + playerID + " has been activated.";
	}
	
	public void setOrderNbr(int orderNbr) {
		this.orderNbr = orderNbr;
	}
	
	public void setLocation(Locations locationID) {
		this.locationID = locationID;
	}
	
}
