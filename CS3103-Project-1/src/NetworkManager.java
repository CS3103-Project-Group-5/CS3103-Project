import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class NetworkManager {
    // Remove one of that
    private final int MAX_UDP_PACKET_SIZE = 65536;
    private final int MAX_BUFFER_SIZE = 256;

    private static final HashMap<UUID, Peer> peers = new HashMap<>();

    public void addUDPConnection(UUID trackerID) throws Exception {
        sockets.put(trackerID, new DatagramSocket());
    }

    public static void sendHandshake(UUID peerID) {
        Peer peer = peers.get(peerID);

        byte[] message = MessageBuilder.buildHandshakeMessage();
        peer.sendMessage(message);
    }

    public void sendUDPMessage(UUID socketID, InetAddress ip, int port, byte[] message) {
        DatagramSocket socket = (DatagramSocket) sockets.get(socketID);
        DatagramPacket packet = new DatagramPacket(message, message.length, ip, port);

        try {
            socket.send(packet);
        } catch (IOException e) {
            System.err.println("Unable to send the UDP message.");
        }
    }

    public byte[] receiveUDPMessage(UUID socketID) {
        byte[] buffer = new byte[MAX_UDP_PACKET_SIZE];

        DatagramSocket socket = (DatagramSocket) sockets.get(socketID);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            System.err.println("Unable to receive an UDP message.");
        }

        byte[] result = new byte[packet.getLength()];
        System.arraycopy(buffer, 0, result, 0, packet.getLength());

        return result;
    }

    public byte[] receiveTCPMessage(UUID socketID) {
        Socket socket = (Socket) sockets.get(socketID);
        ArrayList<Byte> data = new ArrayList<>();

        byte[] buffer = new byte[MAX_BUFFER_SIZE];
        int bytes, messageSize;

        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // ...
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void acceptConnection(UUID socketID, int port) {
        AsynchronousServerSocketChannel socket = (AsynchronousServerSocketChannel) sockets.get(socketID);

        // TODO handle read instead of accept
        try {
            // Bind the socket to the port number
            socket.bind(new InetSocketAddress(port));

            // Accept an incoming connection
            socket.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                @Override
                public void completed(AsynchronousSocketChannel channel, Void attachment) {
                    // Accept another incoming connection
                    socket.accept(null, this);

                    // Create buffer for an incoming messages
                    ByteBuffer buffer = ByteBuffer.allocate(MAX_BUFFER_SIZE);

                    channel.read(buffer);
                }

                @Override
                public void failed(Throwable exc, Void attachment) {

                }
            })
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}