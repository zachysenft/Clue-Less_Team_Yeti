
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
		String weapon;
		//Player is inherited from Player...?
		boolean AccusationFlag;
		
		//suggestions need to be stored and check the other players cards...
		public void print() {
		
			if(AccusationFlag == true) {
				System.out.println(playerName + " has made an accusation!" + "\nID: " + messageID);
				System.out.println("Weapon: " + getWeapon());
				System.out.println("Location: " + super.getLocation());
				System.out.println("Accused : " + getCharacter());
			}
			else {
				System.out.println(playerName + " has made a suggestion!" + "\nID: " + messageID);
				System.out.println("Weapon: " + getWeapon());
				System.out.println("Location: " + super.getLocation());
				System.out.println("Accused : " + getCharacter());
			}
		}
		
		public void setCharacter(Player character) {
			this.character = character;
		}
		
		public Player getCharacter() {
			return this.character;
		}
		
		public void setWeapon(String weapon) {
			this.weapon = weapon;
		}
		
		public String getWeapon() {
			return this.weapon;
		}
		
		
	}
	
	
}
