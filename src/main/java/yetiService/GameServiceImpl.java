package yetiService;

import generated_code.GameServicesGrpc.GameServicesImplBase;
import generated_code.Players.Confirmation;
import generated_code.Players.PlayerID;
import generated_code.Players.RoomInfo;
import generated_code.Players.Suggestion;
import io.grpc.stub.StreamObserver;

public class GameServiceImpl extends GameServicesImplBase {

		private int suggID;
		private int playerID;
		private String weapon;
		private int roomID;
		private String roomName;
	@Override
	public void makeSuggestion(Suggestion request, StreamObserver<Confirmation> responseObserver) {
		this.suggID = request.getSuggestionId();
		this.playerID = request.getPlayerId();
		this.weapon = request.getWeapon();
		this.roomID = request.getRoom().getRoomId();
		this.roomName = request.getRoom().getRoomName();
		
		//TODO validate input parameters
		
		Confirmation confirm = Confirmation.newBuilder()
				.setConfCode(0)
				.build();
		
		responseObserver.onNext(confirm);
		responseObserver.onCompleted();
	}

	@Override
	public void subscribe(PlayerID request, StreamObserver<Suggestion> responseObserver) {
		
		//TODO Send this info based on is accusation value
		// isAccusation = ture => for specific player
		// isAccusation = false => for all players
		int suggID = request.getPlayerId() + 1;
		RoomInfo ri = RoomInfo.newBuilder().setRoomId(this.roomID).setRoomName(this.roomName).build();
		Suggestion sugg = Suggestion.newBuilder()
				.setSuggestionId(this.suggID)
				.setPlayerId(this.playerID)
				.setWeapon(this.weapon)
				.setRoom(ri)
				.setIsAccusation(false)
				.build();
		
		responseObserver.onNext(sugg);
		responseObserver.onCompleted();
	}
	
	

}
