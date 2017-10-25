import java.util.*;
import java.io.*;

public class P2PClient {

	private static String mode;
	private static String fileName;
	private static ArrayList<PeerInfo> peerList;
	private static long selfID;
	private static long fileSize = -1;

	public static void main(String[] args) {
		mode = args[0];
		if (mode.equals("list")) {
			return Tracker.getFileList();
		}

		fileName = args[1];
		System.out.println("Initiating " + mode + " for file " + fileName);
	
		selfID = generateID();

		if (mode.equals("upload")) {
			try {
				File file = new File(fileName);
				fileSize = file.length();
			} catch (FileNotFoundException exp) {
				System.out.println("File not found, exiting...");
				return;
			} catch (IOException exp) {
				exp.printStackTrace();
				return;
			}
		}
		peerList = Tracker.getPeerList(cmd, fileName, fileSize);
	}

	private static long generateID() {
		Random rnd = new Random(506);
		return rnd.nextLong();
	}
}

class Tracker {

	private static final String TRACKER_ADDRESS = "trackerIP";
	private static final String TRACKER_PORT = "trackerPort";
	private ObjectOutputStream out;

	private Tracker() {
		Socket socket = new Socket(TRACKER_ADDRESS, TRACKER_PORT);
		out = new ObjectOutputStream(socket.getOutputStream());
	}
	
	private void send(TrackerMessage msg) {
		out.writeObject(msg);
	}

	private void receive() {
	}

	public static getPeerList() {
		Tracker tracker = new Tracker();
		TrackerMessage msg = new TrackerMessage();
		msg.setCmd();
		tracker.send(msg); //0 - getFileList; 1 - download; 2 - upload
	}

	public static getFileList() {
		Tracker tracker = new Tracker();
		TrackerMessage msg = new TrackerMessage();
		msg.setCmd(0);
		tracker.send(msg); //0 - getFileList; 1 - download; 2 - upload
	}

}
