import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
	public static void main (String[] args) throws IOException {
		Inventory myInventory = new Inventory();
		ServerSocket serverSocket = new ServerSocket(8888);
		System.out.println("Waiting for client to connect");
		
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("Client connected");
			InventoryService myInventoryService = new InventoryService(socket, myInventory);
			Thread thread = new Thread(myInventoryService);
			thread.start();
			
		}
		
	}
	
}
