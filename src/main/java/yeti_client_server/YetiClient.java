package yeti_client_server;

import clueLess.Player;
import clueLess.Suggestions;
import generated_code.GameServicesGrpc;
import generated_code.GameServicesGrpc.GameServicesBlockingStub;
import generated_code.Players.Confirmation;
import generated_code.Players.PlayerID;
import generated_code.Players.RoomInfo;
import generated_code.Players.Suggestion;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class YetiClient {

		private ManagedChannel chann;
		  public YetiClient() {
		  
			  this.chann = ManagedChannelBuilder.forAddress("localhost", 9090)
		                .usePlaintext()
		                .build();
		  }
		
		
		public void makePlayerAccusation(Suggestions suggest) {
			 //ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
		     //           .usePlaintext()
		     //           .build();
			 GameServicesBlockingStub stub = GameServicesGrpc.newBlockingStub(this.chann);
			 
			 RoomInfo room_info = RoomInfo.newBuilder().setRoomId(suggest.getRoom().getRoomId())
					                                   .setRoomName(suggest.getRoom().getRoomName()).build();
			Suggestion suggestion = Suggestion.newBuilder()
					.setSuggestionId(suggest.getSuggestionID())
					.setPlayerId(suggest.getPlayerID())
					.setIsAccusation(suggest.getIsAccusation())
					.setRoom(room_info)
					.setWeapon("Rope")
					.build();
			 
			 Confirmation confirmation = stub.makeSuggestion(suggestion);
			 int value = confirmation.getConfCode();
			 if (value == 0)
			 {
				 System.out.println("Suggestion is approved");
				 //broadcastAccusation();
				 
			 }
			 else
				 System.out.println("Suggestion is not accepted");
			 this.chann.shutdownNow();
	 }
		
		public void broadcastAccusation(Player player) {
			GameServicesBlockingStub stub = GameServicesGrpc.newBlockingStub(this.chann);
			PlayerID pid = PlayerID.newBuilder().setPlayerId(player.getPlayerID()).build();
			Suggestion sugg = stub.subscribe(pid);
			
			//System.out.println("The following suggestions broadcasted to all players:\t");
			System.out.println("SuggestionId = "+ sugg.getSuggestionId() + "\t");
			System.out.println("PlayerID = "+ sugg.getPlayerId() + "\t");
			System.out.println("Room ID = "+ sugg.getRoom().getRoomId() + "\t");
			System.out.println("Room Name = "+ sugg.getRoom().getRoomName() + "\t");
			System.out.println("Weapon = "+ sugg.getWeapon());
			
			this.chann.shutdownNow();
			
		}

}
