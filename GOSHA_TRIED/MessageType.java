public enum MessageType {
    DirectoryListingRequest(0);

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
                return DirectoryListingRequest;
        }
        return null;
    }
}
