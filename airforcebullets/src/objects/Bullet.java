package objects;

import java.sql.Date;

public class Bullet {
	
	private int bulletID;
	private int userID;
	private String bulletText;
	private String catagory;
	private Date accomplishedDate;
	private Date entryDate;
	
	public Bullet(int id, int userId, String bulletText, Date accomplishedDate, Date entryDate){
		setId(id);
		setUserid(userId);
		setBulletText(bulletText);
		setAccomplishedDate(accomplishedDate);
		setEntryDate(entryDate);
	}
	
	public Bullet(int userId, int id, String bulletText, String catagory, Date accomplishedDate){
		setId(id);
		setUserid(userId);
		setBulletText(bulletText);
		setCatagory(catagory);
		setAccomplishedDate(accomplishedDate);
	}
	
	public Bullet(int userId, Date accomplishedDate, String catagory, String bulletText){
		setUserid(userId);
		
		//following lines are to get the todays sql date
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());
		setEntryDate(sqlDate);		
		
		setCatagory(catagory);
		setAccomplishedDate(accomplishedDate);
		setBulletText(bulletText);
	}
	
	
	
	public Bullet(){
		
	}
	
	
	public int getId() {
		return bulletID;
	}
	public void setId(int id) {
		this.bulletID = id;
	}
	public int getUserid() {
		return userID;
	}
	public void setUserid(int userid) {
		this.userID = userid;
	}
	public String getBulletText() {
		return bulletText;
	}
	public void setBulletText(String bulletText) {
		this.bulletText = bulletText;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public Date getAccomplishedDate() {
		return accomplishedDate;
	}
	public void setAccomplishedDate(Date accomplishedDate) {
		this.accomplishedDate = accomplishedDate;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	public String toString(){
		String str = "";
		str+= getId()+" - ";
		str+= getUserid()+" - ";
		str+= getBulletText()+" - ";
		str+= getAccomplishedDate()+" - ";
		str+= getEntryDate();
		return str;
	}

}
