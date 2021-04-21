package clueless;


import java.util.*;

public class Room extends Location {

 private boolean available;
 private ArrayList<Player> player;
 
 public Room(int id, String name, boolean avail) {
   super(id, name, LocationType.ROOM);
   this.available = avail;
   this.player = new ArrayList<Player>();
 }
 
 public Room(int id, String name) {
   super(id, name, LocationType.ROOM);
   available = true;
   this.player = new ArrayList<Player>();
 }
 
 public boolean isAvailable() {
   return available;
 }
 
}
