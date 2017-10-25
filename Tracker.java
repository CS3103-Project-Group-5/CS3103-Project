import java.io.*;
import java.net.*;
import java.util.*;

class P2PTracker {

	private HashMap<Long, PeerInfo> peerMap = new HashMap<Long, PeerInfo>();
	private HashMap<String, FileInfo> fileList = new HashMap<String, FileInfo>();


	public static void main(String[] args) throws IOException {
		int port = 1234;
		byte[] buffer = new byte[256];
		TrackerMessage message;

		ServerSocket welcomeSocket = new ServerSocket(port);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Connected to a client ... ");
			ObjectInputStream ois = new ObjectInputStream(connectionSocket.getInputStream()); 
			ObjectOutputStream oos = new ObjectOutputStream(connectionSocket.getOutputStream());
			message = (TrackerMessage)ois.readObject(); //The first thing client sends to tracker is its own ip address for registration

			
		}
	}

	private void createNewPeerRecord(PeerInfo newPeer) {
		peerMap.put(new Long(currentPeerID), newPeer);
	}

	private void associatePeerWithFile(long peerID, String fileName) {
		FileInfo targetFile = fileList.get(fileName);
		targetFile.addPeerID(peerID);
	}

	private void detachPeerFromFiles(long peerID) {
		PeerInfo targetPeer = peerMap.get(new Long(PeerID));
		String targetFileName = targetPeer.getFileName();
		FileInfo targetFileInfo = fileList.get(targetFileName);
		targetFileInfo.deletePeerID(peerID);
	}
}