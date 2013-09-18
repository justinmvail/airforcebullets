package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objects.Notification;
import objects.User;
import utility.ConnectionManager;

public class NotificationDAOImpl implements NotificationDAO{
	
	public ArrayList<Notification> findNotifications(int id) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT *"+
			" FROM notification"+
			" WHERE userID=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Notification> arl = new ArrayList<Notification>();
//		Notification note = new Notification();
		while (rs.next()) {
			Notification note = new Notification();
			note.setUserID(rs.getInt("userID"));
			note.setDate(rs.getDate("date"));
			note.setNotificationID(rs.getInt("notificationID"));
			note.setMessage(rs.getString("message"));	
			arl.add(note);
		}
		conn.close();
		return arl;
	}
	
	
	public void insertNotification(Notification note) throws SQLException{
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" INSERT INTO notification (userID, date, message)"+
			" VALUES (?, ?, ?)");
		ps.setInt(1, note.getUserID());
		ps.setDate(2, note.getDate());
		ps.setString(3, note.getMessage());
		ps.executeUpdate();
		conn.close();
	}
	
	
	public void deleteNotification(Notification note) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" DELETE"+
				" FROM notification" +
				" WHERE notificationID=?");		
		ps.setInt(1, note.getNotificationID());
		ps.executeUpdate();
		conn.close();		
	}	
	
	
	public void deleteNotifications(ArrayList<Integer> ids) throws SQLException {	
		Connection conn = ConnectionManager.getInstance().getConnection();
		for(Integer id:ids){
			PreparedStatement ps = conn.prepareStatement(
					" DELETE"+
					" FROM notification" +
					" WHERE notificationID=?");		
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		conn.close();
	}
	
	
	public void deleteNotifications(User user) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" DELETE"+
				" FROM notification" +
				" WHERE userID=?");		
		ps.setInt(1, user.getId());
		ps.executeUpdate();
		conn.close();		
	}	
	
}
