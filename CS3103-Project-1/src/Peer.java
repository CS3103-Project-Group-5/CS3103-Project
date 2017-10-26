import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.UUID;

public class Peer {
    private static final int BUFFER_SIZE = 256;

    private AsynchronousSocketChannel socketChannel = null;
    private byte[] buffer = new byte[BUFFER_SIZE];

    public final UUID peerID;
    public final InetAddress peerIP;
    public final int peerPort;

    public Peer(UUID peerID, InetAddress inetAddress, int peerPort) {
        this.peerID = peerID;
        this.peerIP = inetAddress;
        this.peerPort = peerPort;
    }

    public void connect() {
        if (socketChannel == null) {
            try {
                socketChannel = AsynchronousSocketChannel.open();
                socketChannel.connect(new InetSocketAddress(peerIP, peerPort));
            } catch (Exception e) {
                System.err.println("Can't connect to a peer with the following ID: " + peerID.toString());
                return;
            }

            // ...
        }
    }

    public void sendMessage(byte[] message) {

    }
}
