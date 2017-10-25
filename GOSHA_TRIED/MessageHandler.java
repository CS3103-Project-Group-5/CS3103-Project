import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class MessageHandler {

    public static String recoverString(byte[] message) {
        String result = null;

        try {
            result = new String(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Can't display list of available files.");
        }

        return result;
    }

    public static int extractMessageSize(byte[] message) {
        return ByteBuffer.wrap(message, 0, 4).getInt();
    }

    public static MessageType extractMessageType(byte[] message) {
        return MessageType.fromInteger(message[4]);
    }
}
