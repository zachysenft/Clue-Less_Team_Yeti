
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
		
		//suggestionID is inherited from PlayerMessage - use messageID because its essentially the same purpose
		Player character;
		//location is inherited from Player
		Location location; //you can make an accusation from anywhere, in this case you would need to pass through a location
		String weapon;
		//Player is inherited from Player...?
		//^ I agree we may not need the player: Player property
		boolean AccusationFlag;
		//suggestions need to be stored and check the other players cards...
		
		public SuggestionOrAccusation(Player character, Location location, String weapon, boolean AccusationFlag) { //added this constructor
			this.character = character;
			this.location = location;
			this.weapon = weapon;
			this.AccusationFlag = AccusationFlag
		}
		public void print() {
		
			if(AccusationFlag == true) {
				System.out.println(playerName + " has made an accusation!" + "\nID: " + messageID);
				System.out.println("Weapon: " + getWeapon());
				System.out.println("Location: " + getLocation());
				System.out.println("Accused : " + getCharacter());
			}
			else {
				System.out.println(playerName + " has made a suggestion!" + "\nID: " + messageID);
				System.out.println("Weapon: " + getWeapon());
				System.out.println("Location: " + getLocation());
				System.out.println("Accused : " + getCharacter());
			}
		}
		
		public void setCharacter(Player character) {
			this.character = character;
		}
		
		public Player getCharacter() {
			return this.character;
		}
		
		public String getWeapon() {
			return this.weapon;
		}

		public Location getLocation() {
			return location;
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
