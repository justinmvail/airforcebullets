package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.User;
import objects.Viewer;
import utility.ConnectionManager;

public class ViewerDAOImpl implements ViewerDAO {


	public ArrayList<Viewer> findViewers(int id) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT viewer.userID, viewer.viewerID, viewer.active"+
			" FROM viewer"+			
			" WHERE userID=?"+
			" AND viewer.active='1'");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Viewer> viewers = new ArrayList<Viewer>();
		while (rs.next()) {
			Viewer viewer = new Viewer();
			viewer.setUserID(rs.getInt("userID"));
			viewer.setViewerID(rs.getInt("viewerID"));
			viewer.setActive(rs.getBoolean("active"));
			viewers.add(viewer);
		}
		conn.close();
		return viewers;
	}
	
	public ArrayList<Viewer> findPendingViewers(int id) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT viewer.userID, viewer.viewerID, viewer.active"+
			" FROM viewer"+			
			" WHERE userID=?"+
			" AND active='0'");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Viewer> viewers = new ArrayList<Viewer>();
		while (rs.next()) {
			Viewer viewer = new Viewer();
			viewer.setUserID(rs.getInt("userID"));
			viewer.setViewerID(rs.getInt("viewerID"));
			viewer.setActive(rs.getBoolean("active"));
			viewers.add(viewer);
		}
		conn.close();
		return viewers;
	}

	public ArrayList<Viewer> findViewees(int id) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT viewer.userID, viewer.viewerID, viewer.active"+
			" FROM viewer"+			
			" WHERE viewerID=?"+
			" AND active='1'");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Viewer> viewers = new ArrayList<Viewer>();
		while (rs.next()) {
			Viewer viewer = new Viewer();
			viewer.setUserID(rs.getInt("userID"));
			viewer.setViewerID(rs.getInt("viewerID"));
			viewer.setActive(rs.getBoolean("active"));
			viewers.add(viewer);
		}
		conn.close();
		return viewers;
	}
	
	public ArrayList<Viewer> findAllViewees(int id) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT viewer.userID, viewer.viewerID, viewer.active"+
			" FROM viewer"+			
			" WHERE viewerID=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Viewer> viewers = new ArrayList<Viewer>();
		while (rs.next()) {
			Viewer viewer = new Viewer();
			viewer.setUserID(rs.getInt("userID"));
			viewer.setViewerID(rs.getInt("viewerID"));
			viewer.setActive(rs.getBoolean("active"));
			viewers.add(viewer);
		}
		conn.close();
		return viewers;
	}
	

	public void insert(Viewer viewer) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" INSERT INTO viewer (userID, viewerID, active)" +
				" VALUES (?, ?, ?)");
		ps.setInt(1, viewer.getUserID());
		ps.setInt(2, viewer.getViewerID());
		ps.setBoolean(3, viewer.isActive());
		ps.executeUpdate();
		conn.close();	
		
	}

	public void delete(Viewer viewer) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" DELETE"+
				" FROM viewer" +
				" WHERE viewer.userID=?"+
				" AND viewer.viewerID=?");		
		ps.setInt(1, viewer.getUserID());
		ps.setInt(2, viewer.getViewerID());
		ps.executeUpdate();
		conn.close();		
	}
	
	public void deleteAllForUser(User user) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" DELETE"+
				" FROM viewer" +
				" WHERE viewer.userID=?"+
				" OR viewer.viewerID=?");		
		ps.setInt(1, user.getId());
		ps.setInt(2, user.getId());
		ps.executeUpdate();
		conn.close();		
	}
	
	//refactor at some point to use the viewer object!!!  Send method an arraylist of viewers and delete them that way.
	public void activate(int userID, String viewerIds) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" UPDATE viewer"+
			" SET active = '1'"+			
			" WHERE viewerID"+
			" IN "+"("+viewerIds+")"+
			" AND userID=?");
		ps.setInt(1, userID);
		ps.execute();
		
		conn.close();
	}
}
