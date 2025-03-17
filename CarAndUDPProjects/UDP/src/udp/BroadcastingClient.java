package udp;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class BroadcastingClient {
    private static DatagramSocket socket = null;

    public static void main(String[] args) throws IOException {
        List<InetAddress> broadcastAddresses = listAllBroadcastAddresses();
        for (InetAddress address : broadcastAddresses) {
            System.out.println("Broadcasting to: " + address.toString());
            broadcast("Hello", address);
        }
    }

    public static void broadcast(String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);
        byte[] buffer = broadcastMessage.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4445);
        socket.send(packet);
        System.out.println("Sent: " + broadcastMessage);
        socket.close();
    }

    public static List<InetAddress> listAllBroadcastAddresses() throws SocketException {
        List<InetAddress> broadcastList = new ArrayList<>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                continue;
            }

            networkInterface.getInterfaceAddresses()
                .stream()
                .map(interfaceAddress -> interfaceAddress.getBroadcast())
                .filter(Objects::nonNull)
                .forEach(broadcastList::add);
        }

        return broadcastList;
    }
}
