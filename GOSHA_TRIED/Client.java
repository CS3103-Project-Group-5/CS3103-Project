import java.net.InetAddress;
import java.util.Scanner;
import java.util.UUID;

public class Client {
    private final UUID TRACKER_ID = UUID.randomUUID();
    private final InetAddress TRACKER_IP = InetAddress.getByName("%TRACKER_IP%");
    private final int TRACKER_PORT = 1234;

    private UUID myID;
    private NetworkManager networkManager;

    public Client() throws Exception{
        // Generate unique ID
        myID = UUID.randomUUID();

        // Init aux var
        networkManager = new NetworkManager();

        // Create UDP socket to communicate with the tracker
        networkManager.addUDPConnection(TRACKER_ID);

        // Init temp vars
        byte[] msg;

        Scanner scanner = new Scanner (System.in);

        // Wait for a request of user
        int option = -1;
        while (option != 5) {
            System.out.println("Select an option:\n");
            System.out.println("1. Query the centralised server for list of files available.\n");
            System.out.println("2. Query the centralised server for a specific file.\n");
            System.out.println("3. Download a file by specifying the filename.\n");
            System.out.println("4. Inform availability of a new file.\n");
            System.out.println("5. Exit.\n");

            option = scanner.nextInt();
            switch (option) {
                case 1:
                    msg = MessageBuilder.buildDirectoryListingRequestMessage();
                    // ...
            }
        }

        scanner.close();
    }
}
