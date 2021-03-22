package yeti_client_server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import yetiService.GameServiceImpl;

public class YetiServer {

	public static void main(String[] args) throws IOException, InterruptedException {

			int port = 9090;
			Server server = ServerBuilder.
					forPort(port).addService(new GameServiceImpl()).build();
			
			server.start();
			System.out.println("Server Started at port "+ port);
		
			
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	            System.out.println("Received Shutdown Request");
	            server.shutdown();
	            System.out.println("Successfully, Stopped Shutdown the server");
	        }));
			
			server.awaitTermination();
		}

}
