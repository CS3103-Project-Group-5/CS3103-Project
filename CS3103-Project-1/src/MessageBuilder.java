import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class MessageBuilder {
    public static byte[] buildSendUpdatedChunkListMessage() {
        int length = 5;

        byte[] message = new byte[length];
        // Write the size of message
        System.arraycopy(toByteArray(length), 0, message, 0, 4);
        // Write the type of message
        message[4] = (byte) MessageType.UpdatedChunklistRequest.getValue();

        return message;
    }

    public static byte[] buildRequestForChunkMessage(int chunkIndex) {
        int length = 9;

        byte[] message = new byte[length];
        // Write the size of message
        System.arraycopy(toByteArray(length), 0, message, 0, 4);
        // Write the type of message
        message[4] = (byte) MessageType.ChunkRequest.getValue();
        // Write the provided filenames
        System.arraycopy(toByteArray(chunkIndex), 0, message, 5, 4);

        return message;
    }

    public static byte[] buildDataForChunkMessage(byte[] data) {
        int length = 5 + data.length;

        byte[] message = new byte[length];
        // Write the size of message
        System.arraycopy(toByteArray(length), 0, message, 0, 4);
        // Write the type of message
        message[4] = (byte) MessageType.ChunkData.getValue();
        // Write the provided filenames
        System.arraycopy(data, 0, message, 5, data.length);

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
