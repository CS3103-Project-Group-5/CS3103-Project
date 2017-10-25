import java.io.*;
import java.net.*;
import java.util.*;

class P2PTracker {

	private long currentPeerID = 1;
	private HashMap<Long, PeerInfo> peerMap = new HashMap<Long, PeerInfo>();
	private HashMap<String, FileInfo> fileList = new HashMap<String, FileInfo>();


	public static void main(String[] args) throws IOException {
		int port = 1234;
		byte[] buffer = new byte[256];

		ServerSocket welcomeSocket = new ServerSocket(port);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			System.out.println("Connected to a client ... ");
			BufferedInputStream bis = new BufferedInputStream(connectionSocket.getInputStream()); //process incoming bytes
			BufferedOutputStream bos = new BufferedOutputStream(connectionSocket.getOutputStream()); //process outgoing bytes
			bis.read(buffer); //The first thing client sends to tracker is its own ip address for registration
			String IPAddressOfClient = buffer.getBytes().toString();
			bos.write("ack".toBytes()); //send an ack
			


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