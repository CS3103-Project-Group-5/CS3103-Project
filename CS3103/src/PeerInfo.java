
public class PeerInfo {
	private long peerID;
	private String peerIP;
	private int peerPort;
	
	public PeerInfo(long peerID, String peerIP, int peerPort){
		this.peerID = peerID;
		this.peerIP = peerIP;
		this.peerPort = peerPort;
	}
	
	public long getPeerID(){
		return this.peerID;
	}
	public String getPeerIP(){
		return this.peerIP;
	}
	public int getPeerPort(){
		return this.peerPort;
	}
	
}
