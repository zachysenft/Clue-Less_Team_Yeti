package clueless;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerMessage implements Serializable{

	private static final long serialVersionUID = 1;
	private final Map<String, Serializable> fields = new HashMap<String, Serializable>();
	private int messageID;
	private String messageType;
	private String playerTurn;
	
	public PlayerMessage() {}
	//playerID is inherited from Player class
	
	public void setPlayerTurn(String turn) {
		this.playerTurn = turn;
	}
	
	public String getPlayerTurn() {
		return this.playerTurn;
	}
	
	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	
	public int getMessageID() {
		return this.messageID;
	}
	public String getMessageType() {
		return this.messageType;
	}
	public void setMessageType(String type) {
		this.messageType = type;
	}
	

	//SuggestionOrAccusation sub-class
	public static class SuggestionOrAccusation extends PlayerMessage {
		
		private static final long serialVersionUID = 2;
		String character;
		String loc; 
		String weapon;
		boolean AccusationFlag;
		
		public SuggestionOrAccusation(String character, String loc, String weapon, boolean AccusationFlag) { 
			
			this.character = character;
			this.loc = loc;
			this.weapon = weapon;
			this.AccusationFlag = AccusationFlag;
			this.setMessageType("SuggestionAccusation");
		}
		
		public void print() {
		
			if(AccusationFlag == true) {
				//System.out.println(playerName + " has made an accusation!" + "\nID: " + messageID);
				System.out.println("Weapon: " + getWeapon());
				System.out.println("Location: " + getLoc());
				System.out.println("Accused : " + getCharacter());
			}
			else {
				//System.out.println(playerName + " has made a suggestion!" + "\nID: " + messageID);
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

		public boolean getAccusationFlag() {
			return this.AccusationFlag;
		}
		
	}
	
	//Matt Louis updates for the SuggestionResponse and MoveMsg sub classes:
	
	public static class SuggestionResponse extends PlayerMessage { 
	
		private static final long serialVersionUID = 3;
	//class for allowing players to respond to suggestions
		private Card card;
		private String message;
		
		public SuggestionResponse(Card card) {
			setCard(card);
			this.setMessageType("SuggestionResponse");
		}
		public SuggestionResponse() {
			this.setMessageType("SuggestionResponse");
		}
		public Card getCard() {
			return card;
		}
		public void setCard(Card card) {
			this.card = card;
		}
		
		public void setMessage(String msg) {
			this.message = msg;
		}
		
		public String getMessage() {
			return this.message;
		}
		
	}
	public static class MoveMsg extends PlayerMessage{ 
		 
		private static final long serialVersionUID = 4;
	//class for telling players where they have moved to
		private Location location;
		
		public MoveMsg() {
			this.setMessageType("Move");
		}
		
		public MoveMsg(Location location) {
			setLocation(location);
			this.setMessageType("Move");
		}
		
		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
		}
		
		
	}
	
	public static class DealCardMessage extends PlayerMessage {
		
		private static final long serialVersionUID = 5;
		private ArrayList<Card> cards; 
		private String message;
		
		public DealCardMessage() {
			cards = new ArrayList<Card>();
			this.setMessageType("DealCards");
			
		}
		
		public void setCards(ArrayList<Card> card) {
			this.cards = card;
		}
		
		public ArrayList<Card> getCards() {
			return this.cards;
		}
		
		public void setMessage(String msg) {
			this.message = msg;
		}
		
		public String getMessage() {
			return this.message;
		}
	}
	
	public static class OtherMessage extends PlayerMessage {
		
		private static final long serialVersionUID = 6;
		
		private String message;
		
		public OtherMessage() {
			this.setMessageType("Other");
		}
		
		public void setMessage(String msg) {
			this.message = msg;
		}
		
		public String getMessage() {
			return this.message;
		}
	}
	
public static class EndTurnMessage extends PlayerMessage {
		
		private static final long serialVersionUID = 7;
		
		private String message;
		
		public EndTurnMessage() {
			this.setMessageType("End Turn");
		}
		
		public void setMessage(String msg) {
			this.message = msg;
		}
		
		public String getMessage() {
			return this.message;
		}
	}
	
	
	
}
