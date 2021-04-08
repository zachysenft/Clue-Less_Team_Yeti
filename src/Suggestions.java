package clueLess;

public class Suggestions {
	
	 private int suggestionID;
	 private Player player;
	 private Room roomInfo;
	 private Weapon weapon;
	 private boolean isAccusation;
	  
	  public enum Weapon {
		  ROPE,
		  LEADPIPE,
		  KNIFE,
		  WRENCH,
		  CANDLESTICK,
		  REVOLVER
	  }
	  
	  public Suggestions(int suggestionID, Player player, Room roomInfo, Weapon weapon, boolean isAccusation)
	  {
	    this.suggestionID = suggestionID;
	    this.player = player;
	    this.roomInfo = roomInfo;
	    this.weapon = weapon;
	    this.isAccusation = isAccusation;
	  }
	  
	  public int getSuggestionID()
	  { return suggestionID; }
	  
	  public void setSuggestionID(int suggestionID)
	  { this.suggestionID = suggestionID; }
	  
	  public Player getPlayer()
	    
	  { return player; }

	  public void setPlayer(Player player)
	  { this.player = player; }
	  
	  public int getPlayerID()
	  {  return this.player.getPlayerID(); }
	  
	  public Room getRoom()
	  { return roomInfo; }

	  public void setRoom(Room room)
	  { this.roomInfo = room; }
	  
	  public Weapon getWeapon()
	  { return weapon; }
	  
	  public void setWeapon(Weapon weapon)
	  { this.weapon = weapon; }
	  
	  public boolean getIsAccusation()
	  { return isAccusation; }
	  
	  public void setIsAccusation(boolean isAccusation)
	  { this.isAccusation = isAccusation; }
	  
	  public String toString()
	  {
	    return "Suggestion " + suggestionID + ": " + player + " committed the crime in " + roomInfo + " using a " + weapon + ".";
	  }
	  
	  public void toPrint()
	  {
	    System.out.print("Suggestion " + suggestionID + ": " + player + " committed the crime in " + roomInfo + " using a " + weapon + ".");
	  }
}

