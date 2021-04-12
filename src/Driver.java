package clueLess;

import clueLess.Locations.LocationType;
import clueLess.Suggestions.Weapon;
//import clueLess.Locations.Location;
//import clueLess.Suggestion.Weapon;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import yeti_client_server.YetiClient;
//import yeti_client.PlayersClientService;

public class Driver {

	public static void main(String[] args) {
		
		// create a player 
		Player player1 = new Player();
		player1.setPlayerNm("Player1");
		player1.setPlayerID(123);
		Locations location = new Locations();
		location.setLocation(LocationType.ROOM);
		player1.setLocation(location);
		
		
		//Create a room (for the suggestion
		Room room = new Room();
		room.setRoomId(1);
		room.setRoomName("roomXYZ");
		
		
		//Create a suggestion
		System.out.println("*****  Player1 is making suggestion *****");
		Suggestions suggestion = new Suggestions(101,
				                               player1,
				                               room,
				                               Weapon.ROPE,
				                               false);
		
		//Make a suggestion (by this player)
		YetiClient player = new YetiClient();
		player.makePlayerAccusation(suggestion);
		
		
		//Player 2 is listening to the subscirbed Msg
		System.out.println();
		System.out.println("***** Player2 is listening to suggestions ***** ");
		Player player2 = new Player();
		player2.setPlayerNm("Player2");
		player2.setPlayerID(124);
		Locations location2 = new Locations();
		location2.setLocation(LocationType.HALLWAY);
		player2.setLocation(location);
		player2.subscribeMsg();
		
		
		

	}

}
