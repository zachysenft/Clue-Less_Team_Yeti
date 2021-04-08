
/*
 * Zach update 4/8/21
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
	int index;

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

	public String printPlayer() {
		return "Player: " + this.playerName + "\nID: " + this.playerID + "\nOrder Number: " + this.orderNum;
	}

	public Card chooseCardToShow(String cardName) { //made this return a Card so it can get passed through
		for (int i = 0; i<this.playerCard.length;i++) {
			if (this.playerCard[i].getName().equalsIgnoreCase(cardName)) {
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
	
	//Purpose of int return is to be able to access the index of the card that matches
	
	public boolean canAnswerSuggestion(PlayerMessage.SuggestionOrAccusation sugg) {
		
		for(int i = 0; i < playerCard.length; i++) {
			
			if(sugg.character == playerCard[i].name || sugg.weapon == playerCard[i].name || sugg.loc == playerCard[i].name) {
				index = i;
				return true;
			}
			
		}
		return false;
	}
	
	//This method input will always be the index from result of canAnswerSuggestion, which should be called just prior to update index
	
	public Card chooseCardToShow(int index) {
		
		return playerCard[index];
		
	}

	//Zach... I developed a schematic of numbering for locations that will work for this algo
	//Returns where the player location should update to, then another method will update it.
	
	public int move(int curLoc, int destLoc) {
		
		if(curLoc == 1) {
			
			//In room 1, need destLoc to be 12 or 14 hallway...
			//Ensure that input is okay, then update and break
			if(destLoc > 15) {
				System.out.println("Invalid Input");
				//retry to call method
			}
			
			return destLoc;
			
		}
		
		
		
		
		return curLoc;
		
	}


}
