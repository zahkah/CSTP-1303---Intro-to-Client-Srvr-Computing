package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastingClient {
    private static final String MULTICAST_ADDRESS = "230.0.0.0"; // Example multicast address
    private static final int PORT = 4446;

    public static void main(String[] args) throws IOException {
        InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
        try (MulticastSocket socket = new MulticastSocket()) {
            String message = "Hello multicast";
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
            socket.send(packet);
            System.out.println("Sent: " + message);
        }
    }
}
