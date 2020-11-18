import java.net.*;
import java.io.*;
import java.util.*;

public class UDPClient {
	public static void main(String[] args) {
    //Add input object
	  Scanner input = new Scanner(System.in);
	    
		// args give message contents and server hostname
		DatagramSocket aSocket = null;
		
    //Prompt user to type in IP Address
    System.out.print("Type in your local IP Address: ");
    String IPString = input.nextLine();

    //Prompt user to type in port number
    System.out.print("Type in the port number: ");
    int portNum = Integer.parseInt(input.nextLine());

    //Prompt user to type the message
    System.out.print("Type in your message: ");
    String message = input.nextLine();
    System.out.println(message);
    try {
      aSocket = new DatagramSocket();
      byte [] m = message.getBytes(); //message turn to byte form
      InetAddress aHost = InetAddress.getByName(IPString); //Check this one
      int serverPort = portNum; //DO NOT HARD CODE
      DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort); //NEEDS CHECKING
      aSocket.send(request);
      byte [] buffer = new byte[1000];
      DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
      aSocket.receive(reply);
      System.out.println("Reply: " + new String(reply.getData()));
    }
    catch (SocketException e) {
      System.out.println("Socket: " + e.getMessage());
    }
    catch (IOException e) {
      System.out.println("IO: " + e.getMessage());
    }
    finally {
      if (aSocket != null) {
        aSocket.close();
        input.close();
      }
    }
	}
}
