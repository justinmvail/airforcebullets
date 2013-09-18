package objects;

public class Viewer {

	private int userID;
	private int viewerID;
	private boolean active;
	
	public Viewer(int userID, int viewerID){
		setUserID(userID);
		setViewerID(viewerID);
	}
	
	public Viewer(int userID, int viewerID, boolean active){
		setUserID(userID);
		setViewerID(viewerID);
		setActive(active);
	}
	
	public Viewer(){};
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getViewerID() {
		return viewerID;
	}
	public void setViewerID(int viewID) {
		this.viewerID = viewID;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}	
	
	public String toString(){
		return " userID = "+userID +" viewerID = "+viewerID +" active = "+active;
	}
}
