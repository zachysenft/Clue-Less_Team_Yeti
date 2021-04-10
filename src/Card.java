//Matt Louis 4/5/21 
//class is used as the cards for any given player
//stored on the player class on the array playerCard
public class Card{ 
	String type;
	String name;
	
	public Card(String name, String type) {
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	public void printCard() {
		System.out.println("Name: "+ this.name);
		System.out.println("Type: "+ this.type);
	}
	
	public enum cardLists {
		MUSTARD("Colonel Mustard", "person"), WHITE("Mrs. White", "person"), PLUM(
				"Professor Plum", "person"), PEACOCK("Mrs. Peacock", "person"), GREEN(
				"Mr. Green", "person"), SCARLET("Miss Scarlet", "person"), DAGGER(
				"Dagger", "weapon"), ROPE("Rope", "weapon"), PIPE("Lead Pipe",
				"weapon"), CANDLESTICK("Candlestick", "weapon"), REVOLVER(
				"Revolver", "weapon"), WRENCH("Wrench", "weapon"), KITCHEN(
				"Kitchen", "room"), BALLROOM("Ballroom", "room"), DININGROOM(
				"Dining Room", "room"), LOUNGE("Lounge", "room"), HALL("Hall",
				"room"), CONSERVATORY("Conservatory", "room"), BILLIARD(
				"Billiard Room", "room"), LIBRARY("Library", "room"), STUDY(
				"Study", "room");
		
		private String cardName;
		private String cardType;
		
		private cardLists(String cardName, String cardValue) {
			this.cardName = cardName;
			this.cardType = cardValue;
		}
		
		public cardLists[] createCard() {
			return cardLists.values();
		}
		
		
	}


	
}