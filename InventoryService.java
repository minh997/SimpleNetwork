import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.Scanner;
public class InventoryService implements Runnable {
	
	private Socket s;
	private Scanner in;
	private PrintWriter out;
	private Inventory myInvetory;
	
	
	
	public InventoryService(Socket s, Inventory myInventory) {
		this.s = s;
		this.myInvetory = myInventory;
	}
	
	public void run() {
		try {
			in = new Scanner(s.getInputStream());
			out = new PrintWriter(s.getOutputStream());
			doService();
		} catch (IOException e) {
			out.println("error");
			out.flush();
		}
	}
	
	public void doService() throws IOException {
		while (true) {
			if (!in.hasNextLine()) {
				return;
			}
			String command = in.nextLine();
			String[] commandSplit = command.split(" ");
			
			
			if (commandSplit[0].equals("QUIT")) {
				out.println();
				out.flush();
				s.close();
				return;
			}else if (commandSplit[0].equals("ADD_ITEM")) {
				String item = commandSplit[1];
				Integer parameters = Integer.parseInt(commandSplit[2]);
				myInvetory.addItem(item, parameters);
				out.println("succeed ");
				out.flush();
			}else if (commandSplit[0].equals("GET_INVENTORY")) {
				String item = commandSplit[1];
				out.println("succeed " + myInvetory.checkInventory(item));
				out.flush();
			}else if (commandSplit[0].equals("REMOVE_ITEM")) {
				String item = commandSplit[1];
				Integer parameters = Integer.parseInt(commandSplit[2]);
				out.println("succeed "+myInvetory.getItem(item, parameters));
				out.flush();
			}else if (commandSplit[0].equals("GET_THRESHOLD")) {
				Integer parameters = Integer.parseInt(commandSplit[1]);
				out.println("succeed "+myInvetory.getThresholds(parameters));
				out.flush();
			}else {
				out.println("error");
				out.flush();
			}
		}
	}
	
}
