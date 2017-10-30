public enum MessageType {
    UpdatedChunklistRequest(0),
    ChunkRequest(1),
    ChunkData(2);


    private int value;
    MessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MessageType fromInteger(int value) {
        switch(value) {
            case 0:
                return UpdatedChunklistRequest;
            case 1:
                return ChunkRequest;
            case 2:
                return ChunkData;
        }
        return null;
    }
}
