package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;

public class MulticastingReceiver {
    private static final String MULTICAST_ADDRESS = "230.0.0.0";
    private static final int PORT = 4446;

    public static void main(String[] args) {
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(PORT);
            InetAddress localAddress = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localAddress);
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            SocketAddress groupAddress = new InetSocketAddress(group, PORT);

            socket.joinGroup(groupAddress, networkInterface);

            while (true) {
                System.out.println("Ready to receive multicast packets!");
                byte[] recvBuf = new byte[15000];
                DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
                socket.receive(packet);

                System.out.println("Packet received from: " + packet.getAddress().getHostAddress());
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message: " + message);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    InetAddress localAddress = InetAddress.getLocalHost();
                    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localAddress);
                    InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
                    SocketAddress groupAddress = new InetSocketAddress(group, PORT);

                    socket.leaveGroup(groupAddress, networkInterface);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                socket.close();
            }
        }
    }
}
