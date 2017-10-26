import java.util.*;

public class TrackerMessage {

	private int cmd; //0 - getFileList; 1 - download; 2 - upload
	private long peerID;
	private String fileName;
	private int fileSize;
	private ArrayList<PeerInfo> peerList;
	private Set<String> fileList;
	
	public TrackerMessage() {
	}

	public int getCmd() {
		return this.cmd;
	}
	
	public long getPeerID() {
		return this.peerID;
	}

	public String getFileName(){
		return this.fileName;
	}

	public int getFileSize() {
		return this.fileSize;
	}

	public ArrayList<PeerInfo> getPeerList() {
		return this.peerList;
	}

	public Set<String> getFileList() {
		return this.fileList;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	
	public void setPeerID(long id) {
		this.peerID = id;
	}

	public void setFileName(String name) {
		this.fileName = name;
	}

	public void setFileSize(int size) {
		this.fileSize = size;
	}

	public void setPeerList(ArrayList<PeerInfo> list) {
		this.peerList = list;
	}

	public void setFileList(Set<String> list) {
		this.fileList = list;
	}
	
}
