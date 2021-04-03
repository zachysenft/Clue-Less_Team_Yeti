package clueLess;


import clueLess.Locations.LocationType;
import yeti_client_server.YetiClient;

//import yeti_client.PlayersClientService;

public class Player {
	//Creating attributes
		int playerID;
		String playerNm;
		boolean activeFlg;
		int orderNbr;
		private LocationType locationID;
		
			
		//Constructors initialization
		public void setPlayerID(int playerID) {
			this.playerID = playerID;
		}
		public int getPlayerID() {
			return this.playerID;
		}
		public void setPlayerNm(String playerNm) {
			this.playerNm = playerNm;
		}
		
		//This constructor should be used to set the boolean value of activeFlg to true when called and passed a created playerID
		public String activatePlayer(int playerID) {
			this.activeFlg = true;
			return "Player: " + playerID + " has been activated.";
		}
		
		public void setOrderNbr(int orderNbr) {
			this.orderNbr = orderNbr;
		}
		
		public void setLocation(Locations locationID) {
			this.locationID = locationID.getLocation();
		}
		
		public void subscribeMsg() {
			YetiClient msg = new YetiClient();
			msg.broadcastAccusation(this);
			
		}

}
