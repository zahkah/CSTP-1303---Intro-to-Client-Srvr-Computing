package udp;
import java.io.*;
import java.net.*;

public class UDPServer {
	 public static void main(String args[]){
		   	try {
	    		DatagramSocket socket = new DatagramSocket(9090);
	    		byte[] sendData = new byte[1024];
	    		byte[] receiveData = new byte[1024];
	    		
	    		while(true) {
	    			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	    			socket.receive(receivePacket);
	    			String stringReceiveData = new String(receivePacket.getData());
	    			System.out.println(stringReceiveData);
	    			String stringSendData = "Hello Client!";
	    			sendData = stringSendData.getBytes();
	    			InetAddress clientAddress = receivePacket.getAddress();
	    			int clientPort = receivePacket.getPort();
	    			DatagramPacket sendPacket = new DatagramPacket(sendData, 
		    				sendData.length,  clientAddress, clientPort);
	    			socket.send(sendPacket);
	    			
	    		}
	    	
	    		
	    	} catch (Exception e) {
	    		//
	    	} 
	 }
}
