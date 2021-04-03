package clueLess;

public class Locations {
	
	public enum LocationType {
		ROOM,
		HALLWAY
	}
	
	
	private LocationType location;

	//public Locations(Location location) {
	//	this.location = location;
	//}

	public LocationType getLocation() {
		return location;
	}


	public void setLocation(LocationType location) {
		this.location = location;
	}
	
	/*
	public class Room {
		int locationID;
	}
	
	public class Hallway {
		int locationID;
	}
	
	//Need to create all rooms and hallways prior to creating player objects so we can simply set them to equal one.
	Room test = new Room();
	*/

}
