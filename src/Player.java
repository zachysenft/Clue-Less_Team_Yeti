//import clueLess.Locations.LocationType;
//import yeti_client_server.YetiClient;
//import yeti_client.PlayersClientService;

/*
 * Zach update 4/4/21
 */

public class Player {
	//Creating attributes
	
	int playerID;
	String playerName;
	boolean activeFlag;
	boolean LostGameFlag;
	int orderNum;
	private Locations location;
	private Card playerCard[];
	
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public int getPlayerID() {
		return this.playerID;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setPlayerActive() {
		this.activeFlag = true;
		System.out.println(this.playerName + " with ID: " + this.playerID + " has been activated.");
	}
	
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	public int getOrderNum() {
		return this.orderNum;
	}
	
	public void setLocation(Locations location) {
		this.location = location;
	}
	
	public Locations getLocation() {
		return this.location;
	}
	
	public Player printPlayer() {
		return "Player: " + this.playerName + "\nID: " + this.playerID + "\nOrder Number: " + this.orderNum;
	}
	
	public void setLostGameFlag() {
		this.LostGameFlag = true;
		System.out.println(this.playerName + " has lost the game.");
	}
	
	public boolean getLostGameFlag() {
		return this.LostGameFlag;
	}
	
	public void sendMessage() {
		YetiClient msg = new YetiClient();
		msg.broadcastAccusation(this);
		
	}

}
