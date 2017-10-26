import java.util.*;
import java.io.*;
import java.net.*;

public class P2PClient {

	private static String mode;
	private static String fileName;
	private static ArrayList<PeerInfo> peerList;
	private static long selfID;
	private static long fileSize = -1;

	public static void main(String[] args) {
		mode = args[0];
		if (mode.equals("exit")) return;
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
	private ServerSocket serverSocket = null;
	private ObjectOutputStream out;
	private ObjectOutputStream in;

	private Tracker() {
		runServer();
//		Socket socket = new Socket(TRACKER_ADDRESS, TRACKER_PORT);
//		out = new ObjectOutputStream(socket.getOutputStream());
//		in = new ObjectInputStream(socket.getInputStream());
		
	}

	private void runServer(){
		serverSocket = new ServerSocket(TRACKER_PORT)
		Socket = serverSocket.accept();
		in = new ObjectInputStream(socket.getInputStream());

	}
	
	
	private void send(TrackerMessage msg) {
		out.writeObject(msg);
	}

	private ArrayList<String> receiveFN() {
		return in.readObject();
		
	}
	private ArrayList<Long> receivePL() {
		return resultMsg = in.readObject();
		
	}

	public static ArrayList<Long> getPeerList(int cmd, String fileName, int fileSize) {
		Tracker tracker = new Tracker();
		TrackerMessage msg = new TrackerMessage();
		msg.setCmd(cmd);
		msg.setFileName(fileName);
		if (cmd == 2) 
			msg.setFileSize(fileSize);

		tracker.send(msg); //0 - getFileList; 1 - download; 2 - upload
		//receive
		return tracker.receivePL();

	}

	public static Arraylist<String> getFileList() {
		Tracker tracker = new Tracker();
		TrackerMessage msg = new TrackerMessage();
		msg.setCmd(0);
		tracker.send(msg); //0 - getFileList; 1 - download; 2 - upload
		return tracker.receiveFN();
	}

}
