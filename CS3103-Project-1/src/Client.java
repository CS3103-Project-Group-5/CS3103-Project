import java.io.File;
import java.net.InetAddress;
import java.util.*;

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

        Scanner scanner = new Scanner (System.in);

        // Wait for a request of user
        int option = -1, chunkSize = 256 * 1024;;
        String input;
        long fileSize;
        ArrayList<PeerInfo> peerList;
        BitSet chunkList;
        TrackerMessage.MODE mode;

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
                    try {
                        printFileList(TrackerManager.getFileList());
                        break;
                    } catch (Exception e) {
                        System.out.println("Tracker error");
                        e.printStackTrace();
                        break;
                    }
                case 2:
                    System.out.println("Please input file name.");
                    input = scanner.nextLine();
                    fileSize = TrackerManager.getFileSize(input);
                    System.out.println("Filename: " + input + ", Filesize: " + fileSize);
                    break;

                case 3:
                    System.out.println("Please input file name.");
                    input = scanner.nextLine();
                    TrackerMessage msg = TrackerManager.getDownloadInfo(input);
                    fileSize = msg.getFileSize();
                    peerList = msg.getPeerList();
                    chunkList = new BitSet((int)Math.ceil(fileSize / (double)chunkSize)); // <-- need to load file
                    /* call peer class to do the p2p */
                    break;

                case 4:
                    System.out.println("Please input location of file to upload.");
                    input = scanner.nextLine();
                    File file = new File(input);
                    if (!file.exists()) {
                        System.out.println("File not found, exiting...");
                        return;
                    }
                    fileSize = file.length();
                    chunkList = new BitSet((int)Math.ceil(fileSize / (double)chunkSize));
                    chunkList.flip(0, chunkList.length());
                    TrackerManager.initializeUpload(input, fileSize);
                    break;


            }
        }

        scanner.close();
    }

    /* Helper Methods */
    private static long generateID() {
        Random rnd = new Random(506);
        return rnd.nextLong();
    }

    private static void printFileList(Set<String> list) {
        System.out.println("File list: ");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
