package udp;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String args[]){
    	try {
    		DatagramSocket clientSocket = new DatagramSocket(0);
    		byte[] sendData = new byte[1024];
    		byte[] receiveData = new byte[1024];
    		
    		InetAddress serverAddress = InetAddress.getByName("localhost");
    		
    		String stringSendData = "Hello Server!";
    		sendData = stringSendData.getBytes();
    		DatagramPacket sendPacket = new DatagramPacket(sendData, 
    				sendData.length, serverAddress, 9090);
    		clientSocket.send(sendPacket);
    		
    		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    		clientSocket.receive(receivePacket); 
    		
    		// After we received a packet
    		receiveData = receivePacket.getData();
    		String stringReceiveData = new String(receiveData);
    		
    		System.out.println(stringReceiveData);
    		clientSocket.close();
    		
    	} catch (Exception e) {
    		//
    	}
    	
    	
    }

}
