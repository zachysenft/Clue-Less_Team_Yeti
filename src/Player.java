
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

	//Matt... I developed a schematic of numbering for locations that will work for this algo
	//Returns where the player location should update to, then another method will update it.
	//include an if in driver method to retry if returned is 0.
	//Need to add in conditionals of hallways being occupied
	
	public int move(int curLoc, int destLoc) {
		
		if(curLoc == 1) {
			//In room 1, need destLoc to be 12 or 14 hallway...
			if(destLoc == 12 || destLoc == 14) {
				return destLoc;
			}
			return 0;
		}
		
		if(curLoc == 2) {
			//In room 2, need destLoc to be 12, 25, or 23 hallway
			if(destLoc == 12 || destLoc == 25 || destLoc == 23) {
				return destLoc;
			}
			return 0;
		}
		
		if(curLoc == 3) {
			//In room 3, need destLoc to be 23 or 36
			if(destLoc == 23 || destLoc == 36) {
				return destLoc;
			}
			return 0;
		}
		
		if(curLoc == 4) {
			//In room 4, need destLoc 14, 47, 45
			if(destLoc == 14 || destLoc == 45 || destLoc == 47) {
				return destLoc;
			}
			return 0;
		}
		
		if(curLoc == 5) {
			//In room 5, need destLoc 45, 58, 56, 25
			if(destLoc == 45 || destLoc == 58 || destLoc == 56 || destLoc == 25) {
				return destLoc;
			}
			return 0;
		}
		
		if(curLoc == 6) {
			//In room 6, need destLoc 36, 56, 69
			if(destLoc == 36 || destLoc == 56 || destLoc == 69) {
				return destLoc;
			}
			return 0;
		}
		
		if(curLoc == 7) {
			//In room 7, need destLoc 47, 78
			if(destLoc == 47 || destLoc == 78) {
				return destLoc;
			}
			return 0;
		}
		
		if(curLoc == 8) {
			//In room 8, need destLoc 78, 58, 89
			if(destLoc == 78 || destLoc == 58 || destLoc == 89) {
				return destLoc;
			}
			return 0;
		}
		
		if(curLoc == 9) {
			//In room 9, need destLoc 69 or 89
			if(destLoc == 69 || destLoc == 89) {
				return destLoc;
			}
			return 0;
		}
		
		//All rooms done, need to do all twelve hallways now.
		//Need to add in if a hallway is preoccupied by checking other player's locationID's
		
		
		
		
		
		
		
		
		return curLoc;
		
	}


}
