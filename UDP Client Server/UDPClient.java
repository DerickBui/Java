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
	    System.out.print("Type in the port number (1-65535): ");
	    int portNum = Integer.parseInt(input.nextLine());
	    
	    while ((portNum < 1) && (portNum > 65535)) { //Check ranges for the port number
	    	System.out.print("Port number entered out of range, please try again (1-65535): ");
	    	portNum = Integer.parseInt(input.nextLine());
	    }
	
	    try {
	    	//Send message to server
	    	aSocket = new DatagramSocket();
	    	
	    	InetAddress aHost = InetAddress.getByName(IPString); //Use Input from IPString
	    	
	    	if (aHost.isReachable(5000)) { //Give 5 seconds to connect the IP Address before terminating program
	    		while (true) { //Runs client forever
		    		//Prompt user to type the message
				    System.out.print("Type in your message: ");
				    String message = input.nextLine();
				    
				    System.out.print("Do you want to send the message you typed to the server? (y/n): ");
				    String confirmation = input.nextLine(); //User confirmation to send message to server
			    	
				    if (confirmation.equals("y")) { 
				    	byte [] m = message.getBytes(); //Message turn to byte form
				    	
				    	int serverPort = portNum; //Use Input from portNum
				    	DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
				    	aSocket.send(request);
				    	
				    	//Receive message back
				    	byte [] buffer = new byte[1000];
				    	DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				    	aSocket.receive(reply);
				    	System.out.println("Reply: " + new String(reply.getData()));
			    	}
		    	}
	    	} 
	    	else {
	    		System.out.println("Address not reachable, terminating client");
	    	}
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
