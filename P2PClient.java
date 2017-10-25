import java.util.*;

public class P2PClient {

	private String mode;
	private String fileName;
	private ArrayList<PeerInfo> peerList;

	public static void main(String[] args) {
		mode = args[0];
		if (mode.equals("_list")) {
			return Tracker.getFileList();
		}

		fileName = args[1];
		System.out.println("Initiating " + mode + " for file " + fileName);

		peerList = Tracker.getPeerList();
	}
}

class Tracker {

	private static final String TRACKER_ADDRESS = "trackerIP";
	private static final String TRACKER_PORT = "trackerPort";
	private Socket socket;

	private Tracker() {
		socket = new Socket(TRACKER_ADDRESS, TRACKER_PORT);
	}

	private void send() {
	}

	private void receive() {
	}

	public static getPeerList() {
	}

	public static getFileList() {
		Tracker tracker = new Tracker();

		tracker.send(query);
	}

}
