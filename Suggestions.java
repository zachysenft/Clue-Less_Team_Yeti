public class Suggestions
{
  int suggestionID;
  String player;
  String room;
  String weapon;
  boolean isAccusation;
  
  public Suggestions(int suggestionID, String player, String room, String weapon, boolean isAccusation)
  {
    this.suggestionID = suggestionID;
    this.player = player;
    this.room = room;
    this.weapon = weapon;
    this.isAccusation = isAccusation;
  }
  
  public int getSuggestionID()
  { return suggestionID; }
  
  public void setSuggestionID(int suggestionID)
  { this.suggestionID = suggestionID; }
  
  public String getPlayer()
    
  { return player; }

  public void setPlayer(String player)
  { this.player = player; }
  
  public String getRoom()
  { return room; }

  public void setRoom(String room)
  { this.room = room; }
  
  public String getWeapon()
  { return weapon; }
  
  public void setWeapon(String weapon)
  { this.weapon = weapon; }
  
  public boolean getIsAccusation()
  { return isAccusation; }
  
  public void setIsAccusation(boolean isAccusation)
  { this.isAccusation = isAccusation; }
  
  public String toString()
  {
    return "Suggestion " + suggestionID + ": " + player + " committed the crime in " + room + " using a " + weapon + ".";
  }
  
  public void toPrint()
  {
    System.out.print("Suggestion " + suggestionID + ": " + player + " committed the crime in " + room + " using a " + weapon + ".");
  }
}
