//Matt Louis 4/5/21 
//class is used as the cards for any given player
//stored on the player class on the array playerCard
public class Card{ 
	private String type;
	private String name;
	
	public Card(String type, String name) {
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

	
}