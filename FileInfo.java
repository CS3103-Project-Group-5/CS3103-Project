import java.util.ArrayList;

public class FileInfo {
	private ArrayList<Long> peerIDList = new ArrayList<Long>();
	private int totalChunkNumber;
	private int fileSize;
	private final double CHUNK_SIZE = 256; 
	
	public FileInfo (long seederPeerID, int fileSize) {
		this.peerIDList.add(seeder);
		this.fileSize = fileSize;
		this.totalChunkNumber = (int)Math.ceil(filesize/CHUNK_SIZE);
	}

	public ArrayList<Long> getPeerIDList() {
		return peerIDList;
	}

	public void addPeerID(long peerID) {
		this.peerIDList.add(peerID);
	}

	public void deletePeerID(long peerID) {
		this.peerIDList.remove(peerID);
	}

	public int getFileSize() {
		return fileSize;
	}

	
}
