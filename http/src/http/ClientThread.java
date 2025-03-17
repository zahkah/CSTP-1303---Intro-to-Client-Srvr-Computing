package http;


import java.io.*;
import java.net.*;

public class ClientThread extends Thread { 
	
	 private Socket socket;
	 private BufferedReader in;
	 private PrintWriter out;
	 final static String CRLF = "\r\n";
	 private boolean isStop;
	 
	 public ClientThread(Socket clientSocket) {
	        this.socket = clientSocket; 
	        this.isStop = false;
	        
	 }
	 public void run() { 
		 
		 try {
			 while (!isStop) { 
				 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				 out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				 String htmlFile = "";
		         String line;
		         while(true) {
			         line = in.readLine();
		             if (line.equals(CRLF) || line.equals("")) { // End of header is reached?
		                 break; // If yes, break
		             }
		             
		             if(line.contains("GET")) {
		            	 int beginIndex = line.indexOf("/");
		            	 int endIndex = line.indexOf(" HTTP");
		            	 htmlFile = line.substring(beginIndex + 1, endIndex);
		             }
		             System.out.println(htmlFile);	
		         }
		         
		         processRequest(htmlFile);
		         
		         closeConnection();
			 }


		 } catch(Exception e) {
			 
		 } 
	 }
	 
	 public void processRequest(String htmlFile) throws Exception { 
		 File file = new File(htmlFile);
		 if (file.exists()) { 
			 BufferedReader reader = new BufferedReader(new FileReader(file));
			 out.print("HTTP/1.0 200 OK" + CRLF);
	         out.print("Server: java tiny web server" + CRLF);
	         out.print("Content-Type: text/html" + CRLF);
	         out.print("Content-Length: " + file.length() + CRLF);
	         out.println("Content-Type: text/html; charset=iso-8859-1" + CRLF);
	         
	         String line = "";
	         while ((line = reader.readLine()) != null) { // Read a line from the html file
	                out.println(line); // Write the line to the socket connection
	         }	         
			 
		 } else {
			 
	        out.print("HTTP/1.1 404 Not Found" + CRLF);
	        out.print("Server: java tiny web server" + CRLF);
	        out.print("Connection: close" + CRLF);
	        out.println("Content-Type: text/html; charset=iso-8859-1" + CRLF);
	        
            out.println("<html><head>");
            out.println("<title>404 Not Found</title>");
            out.println("</head><body>");
            out.println("<h1>Not Found</h1>");
            out.println("<p>The requested URL /" + htmlFile + " was not found on this server.</p>");
            out.println("</body></html>");
            out.println(CRLF);
		 } 
	 }
	 
	 private void closeConnection() {
	        try {
	            out.close(); // Close output stream
	            in.close(); // Close input stream
	            socket.close(); // Close socket
	            isStop = true; // Set isStop to true in order to exit the while loop
	        } catch (Exception e) {
	            System.out.println(e.toString());
	        }
	 }
}