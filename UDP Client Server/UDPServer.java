import java.net.*;
import java.io.*;
import java.util.*;

public class UDPServer {
	public static void main(String[] args) {
    //Add input object
    Scanner input = new Scanner(System.in);

    //Prompt user to type in port number
    System.out.print("Type in the port number: ");
    int portNum = Integer.parseInt(input.nextLine());
    System.out.println("Port Number: " + portNum);

		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket(portNum); //Port number taking user input
			while (true) { //Runs server forever
				byte [] buffer = new byte[1000];
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				
				aSocket.receive(request);
				
				System.out.println("Message received: " + new String(request.getData()));
				
				DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());/////
				
				aSocket.send(reply);
				
			}
		}
		catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
		finally {
			if (aSocket != null) {
				aSocket.close();
			}
		}
	}
}
