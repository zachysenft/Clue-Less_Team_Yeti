
public class PlayerMessage extends Player {

	//playerID is inherited from Player class
	int messageID;
	
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	
	public int getMessageID() {
		return this.messageID;
	}
	
	
	//SuggestionOrAccusation sub-class
	class SuggestionOrAccusation extends PlayerMessage {
		
		String character;
		String loc; 
		String weapon;
		boolean AccusationFlag;
		
		public SuggestionOrAccusation(String character, String loc, String weapon, boolean AccusationFlag) { 
			this.character = character;
			this.loc = loc;
			this.weapon = weapon;
			this.AccusationFlag = AccusationFlag;
		}
		
		public void print() {
		
			if(AccusationFlag == true) {
				System.out.println(playerName + " has made an accusation!" + "\nID: " + messageID);
				System.out.println("Weapon: " + getWeapon());
				System.out.println("Location: " + getLoc());
				System.out.println("Accused : " + getCharacter());
			}
			else {
				System.out.println(playerName + " has made a suggestion!" + "\nID: " + messageID);
				System.out.println("Weapon: " + getWeapon());
				System.out.println("Location: " + getLoc());
				System.out.println("Accused : " + getCharacter());
			}
		}
		
		public void setCharacter(String character) {
			this.character = character;
		}
		
		public void setWeapon(String weapon) {
			this.weapon = weapon;
		}
		
		public void setLoc(String loc) {
			this.loc = loc;
		}
		
		public String getCharacter() {
			return this.character;
		}
		
		public String getWeapon() {
			return this.weapon;
		}

		public String getLoc() {
			return loc;
		}

		
	}
	
	//Matt Louis updates for the SuggestionResponse and MoveMsg sub classes:
	
	class SuggestionResponse extends PlayerMessage { //class for allowing players to respond to suggestions
		private Card card;
		public SuggestionResponse(Card card) {
			setCard(card);
		}
		public Card getCard() {
			return card;
		}
		public void setCard(Card card) {
			this.card = card;
		}
		
		
	}
	class MoveMsg extends PlayerMessage{ //class for telling players where they have moved to
		private Location location;
		
		public MoveMsg(Location location) {
			setLocation(location);
		}
		
		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}
		
		
	}
	
}
