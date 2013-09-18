package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.Notification;
import objects.User;

public interface NotificationDAO {
	
	public ArrayList<Notification> findNotifications(int id) throws SQLException;
	
	public void insertNotification(Notification note) throws SQLException;
	
	public void deleteNotification(Notification note) throws SQLException;
	
	public void deleteNotifications(ArrayList<Integer> ids) throws SQLException;
	
	public void deleteNotifications(User user) throws SQLException;

}
