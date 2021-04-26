package clueless;

import java.util.ArrayList;

/*
 * Matt Louis update 4/8/21 9pm
 */

public class Player {
	// Creating attributes

	int playerID;
	String playerName;
	String characterName; //created to track the name of the character
	boolean activeFlag;
	boolean LostGameFlag;
	int orderNum;
	private Location location;
	private int locationID;
	private ArrayList<Card> playerCards;
	private int index;
	private String previousSuggLocation;

	public Player(String name, int id) {
		this.playerName = name;
		this.playerID = id;
		this.playerCards = new ArrayList<Card>();
		this.LostGameFlag = false;
	}
	
	public Player() {
		//default constructor
		this.playerCards = new ArrayList<Card>();
		this.LostGameFlag = false;
	}
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

	public ArrayList<Card> getPlayerCard() {
		//return new ArrayList<Card>(); //for now
		return this.playerCards;
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
	
	public void setPlayerPrevSuggLocation(String suggLoc) {
		this.previousSuggLocation = suggLoc;
	}
	
	public String getPlayerPrevSuggLocation() {
		return this.previousSuggLocation;
	}
	
	public String printPlayer() {
		return "Player: " + this.playerName + "\nID: " + this.playerID + "\nOrder Number: " + this.orderNum;
	}

	public Card chooseCardToShow(String cardName) { //made this return a Card so it can get passed through
		for (int i = 0; i<this.playerCards.size();i++) {
			if (this.playerCards.get(i).getName().equalsIgnoreCase(cardName)) {
				return this.playerCards.get(i);
			} 	
	}
		//System.out.println("You don't have a card with that name");
		return null;
	}

	public void setLostGameFlag() {
		this.LostGameFlag = true;
		//System.out.println(this.playerName + " has lost the game.");
	}

	public boolean getLostGameFlag() {
		return this.LostGameFlag;
	}
	
	//Purpose of int return is to be able to access the index of the card that matches
	
	public boolean canAnswerSuggestion(PlayerMessage.SuggestionOrAccusation sugg) {
		
		for(int i = 0; i < playerCards.size(); i++) {
			
			if(sugg.character == playerCards.get(i).getName() || sugg.weapon == playerCards.get(i).getName() || sugg.loc == playerCards.get(i).getName()) {
				index = i;
				return true;
			}
			
		}
		return false;
	}
	
	//This method input will always be the index from result of canAnswerSuggestion, which should be called just prior to update index
	
	public Card chooseCardToShow(int index) {
		
		return playerCards.get(index);
		
	}
	public void addCard(Card card) {
		this.playerCards.add(card);
	}
	
	//use disprove method not chooseCardToShow or canAnswerSuggestion
	
	public Card disprove(String person, String weapon, String location) {
		
		for(int i = 0; i < playerCards.size(); i++) {
			
			if(playerCards.get(i).getName() == person) {
				return playerCards.get(i);
			}
			else if(playerCards.get(i).getName() == weapon) {
				return playerCards.get(i);
			}
			else if(playerCards.get(i).getName() == location) {
				return playerCards.get(i);
			}
			
		}
		
		return null;
	}

	
	//going to use this method in target increment
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

	//We can use the isOccupied() method I added to the location class to check if someone can move there or not
	public boolean updateLocation(Location destLoc) {
		if (destLoc.isOccupied()) {
			System.out.println("Move failed, location is occupied");
			return false;
		} 
		if(move(destLoc.locationID)!=-1) { //checks if valid destination location was passed through
			System.out.println("Player location updated to: "+ destLoc);
			destLoc.addPlayer(this); //updates player array list
			this.location.removePlayer(this);
			this.location = destLoc;		//added this not sure if needed but i don't see where it's updated in player attribute
			return true;
		}
		return false;
	}
	
	
	 public Card disprove(Card person, Card weapon, Card location) {
			
	   	 for(int i = 0; i < this.playerCards.size(); i++) {
	   				
	   		 if(person.getName().equalsIgnoreCase(this.playerCards.get(i).getName())) {
	   			 
	   			 return this.playerCards.get(i);   					 
	   		 }
	   		 
	   		 if(weapon.getName().equalsIgnoreCase(this.playerCards.get(i).getName())) {
	   			 
	   			 return this.playerCards.get(i);	 
	   		 } 
	   		 if(location.getName().equalsIgnoreCase(this.playerCards.get(i).getName())) {
	   			 
	   			 return this.playerCards.get(i); 
	   		 } 
	   	 }
	   	 
	   	 return null;
	   	 
	    }
	
	 
	public int move(int destLoc) {
		
		if(this.location.getLocationID() == 1) {
			//In room 1, need destLoc to be 12 or 14 hallway or 9 (secret pasasge)
			if(destLoc == 12 || destLoc == 14 || destLoc == 9) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 2) {
			//In room 2, need destLoc to be 12, 25, or 23 hallway
			if(destLoc == 12 || destLoc == 25 || destLoc == 23) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 3) {
			//In room 3, need destLoc to be 23 or 36, or 7 (secret passage)
			if(destLoc == 23 || destLoc == 36 || destLoc == 7 ) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 4) {
			//In room 4, need destLoc 14, 47, 45
			if(destLoc == 14 || destLoc == 45 || destLoc == 47) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 5) {
			//In room 5, need destLoc 45, 58, 56, 25
			if(destLoc == 45 || destLoc == 58 || destLoc == 56 || destLoc == 25) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 6) {
			//In room 6, need destLoc 36, 56, 69
			if(destLoc == 36 || destLoc == 56 || destLoc == 69) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 7) {
			//In room 7, need destLoc 47, 78 or 3 (secret passage)
			if(destLoc == 47 || destLoc == 78 || destLoc == 3) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 8) {
			//In room 8, need destLoc 78, 58, 89
			if(destLoc == 78 || destLoc == 58 || destLoc == 89) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 9) {
			//In room 9, need destLoc 69 or 89
			if(destLoc == 69 || destLoc == 89 || destLoc == 1) {
				return destLoc;
			}
			return -1;
		}
		
		//All rooms done, need to do all twelve hallways now.
		//Need to add in if a hallway is preoccupied by checking other player's locationID's
		
		if(this.location.getLocationID() == 14) {
			//In hallway 14, need destLoc 1 or 4
			if(destLoc == 1 || destLoc == 4) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 47) {
			//In hallway 47, need destLoc 4 or 7
			if(destLoc == 4 || destLoc == 7) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 12) {
			//In hallway 12, need destLoc 1 or 2
			if(destLoc == 1 || destLoc == 2) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 45) {
			//In hallway 45, need destLoc 4 or 5
			if(destLoc == 4 || destLoc == 5) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 78) {
			//In hallway 45, need destLoc 4 or 5
			if(destLoc == 7 || destLoc == 8) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 25) {
			//In hallway 25, need destLoc 2 or 5
			if(destLoc == 2 || destLoc == 5) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 58) {
			//In hallway 58, need destLoc 5 or 8
			if(destLoc == 5 || destLoc == 8) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 23) {
			//In hallway 23, need destLoc 2 or 3
			if(destLoc == 2 || destLoc == 3) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 56) {
			//In hallway 23, need destLoc 2 or 3
			if(destLoc == 5 || destLoc == 6) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 89) {
			//In hallway 89, need destLoc 8 or 9
			if(destLoc == 8 || destLoc == 9) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 36) {
			//In hallway 36, need destLoc 3 or 6
			if(destLoc == 3 || destLoc == 6) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 69) {
			//In hallway 89, need destLoc 8 or 9
			if(destLoc == 6 || destLoc == 9) {
				return destLoc;
			}
			return -1;
		}
		//adding locations for starting squares
		if(this.location.getLocationID() == 101) {
			//In starting spot for scarlet, need hallway 47
			if(destLoc == 47 ) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 102) {
			//In starting spot for mustard, need hallway 78
			if(destLoc == 78) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 103) {
			//In starting spot for white, need hallway 69
			if(destLoc == 69) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 104) {
			//In starting spot for green, need hallway 36
			if(destLoc == 36) {
				return destLoc;
			}
			return -1;
		}
		
		if(this.location.getLocationID() == 105) {
			//In starting spot for peacock, need hallway 23
			if(destLoc == 23) {
				return destLoc;
			}
			return -1;
		}
		if(this.location.getLocationID() == 106) {
			//In starting spot for plum, need hallway 23
			if(destLoc == 12) {
				return destLoc;
			}
			return -1;
		}
		
		
		return -1;
		
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}


}
