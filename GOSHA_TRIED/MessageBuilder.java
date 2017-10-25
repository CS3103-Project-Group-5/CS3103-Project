import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MessageBuilder {
    public static byte[] buildDirectoryListingRequestMessage() {
        int length = 5;

        byte[] message = new byte[length];
        // Write the size of message
        System.arraycopy(toByteArray(length), 0, message, 0, 4);
        // Write the type of message
        message[4] = (byte) MessageType.DirectoryListingRequest.getValue();

        return message;
    }

    public static byte[] buildDirectoryListingReplyMessage(String fileNames) {
        int length = 5 + fileNames.length();

        byte[] message = new byte[length];
        // Write the size of message
        System.arraycopy(toByteArray(length), 0, message, 0, 4);
        // Write the type of message
        message[4] = (byte) MessageType.DirectoryListingRequest.getValue();
        // Write the provided filenames
        System.arraycopy(toByteArray(fileNames), 0, message, 5, fileNames.length());

        return message;
    }

    private static byte[] toByteArray(int value) {
        return ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(value).array();
    }

    private static byte[] toByteArray(String value) {
        byte[] message = null;

        try {
            message = value.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Can't encode provided string.");
        }

        return message;
    }
}
