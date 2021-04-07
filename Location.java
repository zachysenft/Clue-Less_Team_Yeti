package clueLess;

public class Location {
 
  int locationID;
  String locationName;
  LocationType locationType;
  
  public enum LocationType {
    ROOM,
    HALLWAY
  }
  
  public Locations(int id, String name, LocationType type) {
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
 
}
