package http;

import java.io.*;
import java.net.*;


public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(80);
			boolean isStopped = false;
			
			while(!isStopped) {
				Socket clientSocket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(clientSocket);
				clientThread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Port 9092 is already opened! Please use another port.");
		}

	}

}