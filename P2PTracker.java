import java.io.*;
import java.net.*;
import java.util.*;

class P2PTracker {

	private HashMap<Long, PeerInfo> peerMap = new HashMap<Long, PeerInfo>();
	private HashMap<String, FileInfo> fileList = new HashMap<String, FileInfo>();
	private FINAL int NUM_PEERS_TO_SEND = 10;


	public static void main(String[] args) throws IOException {
		int port = 1234, clientPort;
		String clientIP;
		byte[] buffer = new byte[256];
		TrackerMessage incomingMessage, outgoingMessage;

		ServerSocket welcomeSocket = new ServerSocket(port);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Connected to a client ... ");
			ObjectInputStream ois = new ObjectInputStream(connectionSocket.getInputStream()); 
			ObjectOutputStream oos = new ObjectOutputStream(connectionSocket.getOutputStream());
			clientIP = connectionSocket.getHostAddress();
			clientPort = connectionSocket.getPort();

			while (true) {
			incomingMessage = (TrackerMessage)ois.readObject();
			outgoingMessage = processMessage(incomingMessage);

			}

			
		}
	}

	private TrackerMessage processMessage(TrackerMessage incomingMessage, String peerIP, int peerPort) {
		int cmd = incomingMessage.getCmd();
		long peerID = incomingMessage.getPeerID();
		outgoingMessage = new TrackerMessage();

		if (!clientInPeerMap) {
			createNewPeerRecord()
		}

		switch(cmd) {
			case 0: 
				outgoingMessage.setFileList(fileList.keySet());
				break;

			case 1:
				String requestedFile = incomingMessage.getFileName();
				ArrayList<PeerInfo> peerListToSend = getPeerInfoListToSend(requestedFile);
				outgoingMessage.setPeerList(peerListToSend);
				associatePeerWithFile(peerID, requestedFile);
				break;

			case 2:
				String newFileName = incomingMessage.getFileName();
				int newFileSize = incomingMessage.getFileSize();

		}
	

	private boolean clientInPeerMap(long peerID) {
		returns peerMap.contains(peerID);
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

	private ArrayList<PeerInfo> getPeerInfoListToSend(String fileName) {
		FileInfo requiredFile = fileList.get(fileName);
		ArrayList<Long> peerIDList = Collections.shuffle(requiredFile.getPeerIDList());
		ArrayList<PeerInfo> peerInfoList = new ArrayList<PeerInfo>();
		int possibleNumPeerToSend = Math.min(peerIDList.size(), NUM_PEERS_TO_SEND);
		for (int i = 0; possibleNumPeerToSend; i++) {
			peerInfoList.add(peerMap.get(peerID.get(i)));
		}
		return peerInfoList
	}

	private boolean peerIDListGreaterThanNumPeersToSend(int size) {
		return size>NUM_PEERS_TO_SEND;
	}
}