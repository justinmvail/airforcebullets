package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.User;
import objects.Viewer;

public interface ViewerDAO {

	public ArrayList<Viewer> findViewers(int id)throws SQLException;
	
	public ArrayList<Viewer> findPendingViewers(int id) throws SQLException;
	
	public ArrayList<Viewer> findViewees(int id) throws SQLException;
	
	public ArrayList<Viewer> findAllViewees(int id) throws SQLException;
	
	public void insert(Viewer viewer) throws SQLException;
	
	public void delete(Viewer viewer) throws SQLException;
	
	public void deleteAllForUser(User user) throws SQLException;
	
	public void activate(int userID,  String viewerIds) throws SQLException;
	
}
