
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;



public class Client {

	public static void main(String[] args) throws IOException {
		Socket socket = null;
		PrintWriter out = null;
		Scanner in = null;
		Scanner readFile = null;
		try {
			socket = new Socket("localhost",8888);
			out = new PrintWriter(socket.getOutputStream());
			in = new Scanner(socket.getInputStream());
			readFile = new Scanner(new File("text.txt"));
			while (readFile.hasNextLine()) {
				String command = readFile.nextLine();
				out.println(command);
				out.flush();
				String response = in.nextLine();
				System.out.print(command + " ");
				System.out.println(response);
				Thread.sleep((long)Math.random()*1000);
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}catch (NoSuchElementException e) {}
		finally {
			socket.close();
			in.close();
			readFile.close();
		}
		

	}

}
