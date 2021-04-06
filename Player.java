import clueLess.Locations.LocationType;
import yeti_client_server.YetiClient;
import yeti_client.PlayersClientService;

/*
 * Zach update 4/4/21
 */

public class Player {
	// Creating attributes

	int playerID;
	String playerName;
	boolean activeFlag;
	boolean LostGameFlag;
	int orderNum;
	private Location location;
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

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}

	public Player printPlayer() {
		return "Player: " + this.playerName + "\nID: " + this.playerID + "\nOrder Number: " + this.orderNum;
	}

	public Card chooseCardToShow(String cardName) { //made this return a Card so it can get passed through
		for (int = 0; i<this.playerCard.length;i++) {
			if (this.playerCard[i].getName()cardName.equalsIgnoreCase(cardName)) {
				return this.playerCard[i];
			} 
		
	}
		System.out.println("You don't have a card with that name");
		return null;
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
