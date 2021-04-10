/*
 * Matt Louis update 4/8/21 9pm
 */

public class Player {
	// Creating attributes

	int playerID;
	String playerName;
	boolean activeFlag;
	boolean LostGameFlag;
	int orderNum;
	private Location location;
	private int locationID;
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
	
	

	//We can use the isOccupied() method I added to the location class to check if someone can move there or not
	public boolean updateLocation(Location curLoc, Location destLoc) {
		if (destLoc.isOccupied()) {
			System.out.println("Move failed, location is occupied");
			location = curLoc;
			return false;
		} 
		if(move(curLoc.locationID,destLoc.locationID)!=-1) { //checks if valid destination location was passed through
			System.out.println("Player location updated to: "+ destLoc);
			destLoc.addPlayer(this); //updates player array list
			curLoc.removePlayer(this);
			location = destLoc;		//added this not sure if needed but i don't see where it's updated in player attribute
			return true;
		}
		return false;
	}
	
	public int [] validDestLocations(Location curLoc, Location destLoc) { //added to show the player the potential locations they can move to
		int [] validLocs = new int[4]; //stores up to 4 (in case you are in room 5)
		int validIndex = 0; //tracks adding to validIndex
		int [] HALLWAYS = {14,47,12,45,78,25,58,23,56,89,36,69}; //hardcoded hallway/room ids
		int [] ROOMS = {1,2,3,4,5,6,7,8,9};
		String curLocIDStr = String.valueOf(curLoc.getLocationID()); 
		if (curLoc.getLocationType().equals("ROOM")){ //if you are in a room, track potential hallways to move to
			for (int i=0; i<HALLWAYS.length;i++) {
				String tempHallway = String.valueOf(HALLWAYS[i]);
				if (tempHallway.contains(curLocIDStr)){ //if the hallway is next to the room, add to array
					validLocs[validIndex] = HALLWAYS[i]; 
					validIndex++;
				}
			}
			//need to add secret passage rooms as options
		if (curLoc.getLocationID()==3) {
			validLocs[validIndex] = 7;
		} else if (curLoc.getLocationID()==7) {
			validLocs[validIndex] = 3;
		} else if (curLoc.getLocationID()==9) {
			validLocs[validIndex] = 1;
		} else if (curLoc.getLocationID()==1) {
			validLocs[validIndex] = 9;
		}
		
		//hallways
		if (curLoc.getLocationType().equals("HALLWAYS")) {
			for (int j=0; j<ROOMS.length;j++) {
				String tempRoom = String.valueOf(ROOMS[j]);
				if (tempRoom.contains(curLocIDStr)) { //if room is next to hallway, add to array
					validLocs[validIndex] = ROOMS[j];
					validIndex++;
				}
			}
		}
		}
		return validLocs;
	}
	
	
	public int move(int curLoc, int destLoc) {
		
		if(curLoc == 1) {
			//In room 1, need destLoc to be 12 or 14 hallway...
			if(destLoc == 12 || destLoc == 14) {
				return destLoc;
			}
			return -1;
		}
		
		if(curLoc == 2) {
			//In room 2, need destLoc to be 12, 25, or 23 hallway
			if(destLoc == 12 || destLoc == 25 || destLoc == 23) {
				return destLoc;
			}
			return -1;
		}
		
		if(curLoc == 3) {
			//In room 3, need destLoc to be 23 or 36
			if(destLoc == 23 || destLoc == 36) {
				return destLoc;
			}
			return -1;
		}
		
		if(curLoc == 4) {
			//In room 4, need destLoc 14, 47, 45
			if(destLoc == 14 || destLoc == 45 || destLoc == 47) {
				return destLoc;
			}
			return -1;
		}
		
		if(curLoc == 5) {
			//In room 5, need destLoc 45, 58, 56, 25
			if(destLoc == 45 || destLoc == 58 || destLoc == 56 || destLoc == 25) {
				return destLoc;
			}
			return -1;
		}
		
		if(curLoc == 6) {
			//In room 6, need destLoc 36, 56, 69
			if(destLoc == 36 || destLoc == 56 || destLoc == 69) {
				return destLoc;
			}
			return -1;
		}
		
		if(curLoc == 7) {
			//In room 7, need destLoc 47, 78
			if(destLoc == 47 || destLoc == 78) {
				return destLoc;
			}
			return -1;
		}
		
		if(curLoc == 8) {
			//In room 8, need destLoc 78, 58, 89
			if(destLoc == 78 || destLoc == 58 || destLoc == 89) {
				return destLoc;
			}
			return -1;
		}
		
		if(curLoc == 9) {
			//In room 9, need destLoc 69 or 89
			if(destLoc == 69 || destLoc == 89) {
				return destLoc;
			}
			return -1;
		}
		
		//All rooms done, need to do all twelve hallways now.
		//Need to add in if a hallway is preoccupied by checking other player's locationID's
		
		if(curLoc == 14) {
			//In hallway 14, need destLoc 1 or 4
			if(destLoc == 1 || destLoc == 4) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 47) {
			//In hallway 47, need destLoc 4 or 7
			if(destLoc == 4 || destLoc == 7) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 12) {
			//In hallway 12, need destLoc 1 or 2
			if(destLoc == 1 || destLoc == 2) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 45) {
			//In hallway 45, need destLoc 4 or 5
			if(destLoc == 4 || destLoc == 5) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 78) {
			//In hallway 45, need destLoc 4 or 5
			if(destLoc == 7 || destLoc == 8) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 25) {
			//In hallway 25, need destLoc 2 or 5
			if(destLoc == 2 || destLoc == 5) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 58) {
			//In hallway 58, need destLoc 5 or 8
			if(destLoc == 5 || destLoc == 8) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 23) {
			//In hallway 23, need destLoc 2 or 3
			if(destLoc == 2 || destLoc == 3) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 56) {
			//In hallway 23, need destLoc 2 or 3
			if(destLoc == 5 || destLoc == 6) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 89) {
			//In hallway 89, need destLoc 8 or 9
			if(destLoc == 8 || destLoc == 9) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 36) {
			//In hallway 36, need destLoc 3 or 6
			if(destLoc == 3 || destLoc == 6) {
				return destLoc;
			}
			return -1;
		}
		if(curLoc == 69) {
			//In hallway 89, need destLoc 8 or 9
			if(destLoc == 6 || destLoc == 9) {
				return destLoc;
			}
			return -1;
		}
		
		
		return curLoc;
		
	}


}
