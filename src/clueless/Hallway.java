package clueless;



public class Hallway extends Location {
  
  private boolean occupied;
  private Player player;
  
  public Hallway(int id, String name, boolean occupied) {
    super(id, name, LocationType.HALLWAY);
    this.occupied = occupied;
    this.player = null;
  }
  
  public Hallway(int id, String name) {
    super(id, name, LocationType.HALLWAY);
    this.occupied = false;
    this.player = null;
  }
  
  public boolean isOccupied() {
    return occupied;
  }
  
  public Player getPlayer() {
    return player;
  }
  
  public void updateOccupiedFlg() {
    if (player == null) {
      occupied = false;
    } else {
      occupied = true;
    }
  }
}