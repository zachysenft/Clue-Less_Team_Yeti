import java.util.ArrayList;

public class Location {
 
  int locationID;
  String locationName;
  LocationType locationType;
  ArrayList<Player> playerList = new ArrayList<Player>();
  
  public enum LocationType {
    ROOM,
    HALLWAY
  }
  
  public Location(int id, String name, LocationType type) {
    this.locationID = id;
    this.locationName = name;
    this.locationType = type;
  }
  
  public int getLocationID() {
    return locationID;
  }
  
  public String getLocationName() {
    return locationName;
  }
  
  public LocationType getLocationType() {
    return locationType;
  }
  
  public void addPlayer(Player newPlayer) {
	  playerList.add(newPlayer);
  }
  public void removePlayer(Player remPlayer) {
	  playerList.remove(remPlayer);
  }
  public boolean isOccupied() { //Having trouble implementing this method, not sure if i'm checking the location type correctly
	  if (this.locationType.equals("HALLWAY") && playerList.size()>0){ //if the location is hallway and there's a player
		  return true; //it's occupied
	  } else { 
		  return false;
	  }
  }
}
