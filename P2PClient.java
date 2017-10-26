import java.util.*;
import java.io.*;
import java.net.*;

public class P2PClient {

/* Usage
	java P2PClient list 
		- return list of files recorded on tracker
	java P2PClient upload myfile
		- return a list of peers to upload file
		- upload
	java P2PClient download hisfile
		- return a list of peers to download from
		- download
*/

	private static TrackerMessage.MODE mode;
	private static String fileName;
	private static Set<PeerInfo> peerList;
	private static long selfID;
	private static long fileSize = -1;

	public static void main(String[] args) {
		String m = args[0];
		try {
			mode = MODE.valueOf(m.toUpperCase());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		if (mode.equals(TrackerMessage.MODE.LIST)) {
			try {
				printFileList(Tracker.getFileList());
			} catch (Exception e) {
				System.out.println("Tracker error");
				e.printStackTrace();
			}
		}

		fileName = args[1];
		System.out.println("Initiating " + mode + " for file " + fileName);
	
		selfID = generateID();
		int cmd = 1;

		if (mode.equals(TrackerMessage.MODE.UPLOAD)) {
			cmd = 2;
			File file = new File(fileName);
			if (!file.exists()) {
				System.out.println("File not found, exiting...");
				return;
			}
			fileSize = file.length();
		}

		try {
			peerList = Tracker.getPeerList(cmd, fileName, fileSize);
		} catch (Exception e) {
			System.out.println("Tracker error");
			e.printStackTrace();
		}

		start();
	}

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
/*
	private static void start() {
		while {
			new Peer();
		}
	}
	*/
}

/*
class Peer implements Thread {

	

}
*/

class Tracker {

	private static final String TRACKER_ADDRESS = "trackerIP";
	private static final int TRACKER_PORT = 888;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	private Tracker() throws Exception {
		socket = new Socket(TRACKER_ADDRESS, TRACKER_PORT);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}

	private void close() throws Exception {
		socket.close();
	}
	
	private void send(TrackerMessage msg) throws Exception {
		out.writeObject(msg);
	}

	private TrackerMessage receive() throws Exception {
		return (TrackerMessage)in.readObject();
	}

	public static Set<PeerInfo> getPeerList(int cmd, String fileName, long fileSize) throws Exception {
		Tracker tracker = new Tracker();
		TrackerMessage msg = new TrackerMessage();
		msg.setCmd(cmd);
		msg.setFileName(fileName);
		if (cmd == 2) 
			msg.setFileSize(fileSize);
		tracker.send(msg); //0 - getFileList; 1 - download; 2 - upload
		Set<PeerInfo> list = tracker.receive().getPeerList();
		tracker.close();
		return list;
	}

	public static Set<String> getFileList() throws Exception {
		Tracker tracker = new Tracker();
		TrackerMessage msg = new TrackerMessage();
		msg.setCmd(0);
		tracker.send(msg); //0 - getFileList; 1 - download; 2 - upload
		Set<String> list = tracker.receive().getFileList();
		tracker.close();
		return list;
	}

}
