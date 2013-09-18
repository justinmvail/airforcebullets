package objects;

import java.sql.Date;

public class Notification {
	
	private int userID;
	private int notificationID;
	private Date date;
	private String message;
	
	public Notification(){}
	
	public Notification(int userID, Date date, String message){
		setUserID(userID);
		setDate(date);
		setMessage(message);
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int i) {
		this.userID = i;
	}
	public int getNotificationID() {
		return notificationID;
	}
	public void setNotificationID(int i) {
		this.notificationID = i;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	
	public String toString(){
		return getUserID()+" : "+getNotificationID()+" : "+getDate()+" : "+getMessage();
	}
}
