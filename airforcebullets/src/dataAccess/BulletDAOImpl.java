package dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Bullet;
import objects.User;
import utility.ConnectionManager;

public class BulletDAOImpl implements BulletDAO {
	
	public ArrayList<Bullet> findBulletsByUser(int id) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT bullet.bulletID, bullet.userID, bullet.bulletText,"+
			" bullet.catagory, bullet.accomplishedDate, bullet.entryDate"+
			" FROM bullet"+			
			" WHERE userID=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		while (rs.next()) {
			Bullet bullet = new Bullet();
			bullet.setId(rs.getInt("bulletID"));
			bullet.setUserid(rs.getInt("userID"));
			bullet.setBulletText(rs.getString("bulletText"));
			bullet.setCatagory(rs.getString("catagory"));
			bullet.setAccomplishedDate(rs.getDate("accomplishedDate"));
			bullet.setEntryDate(rs.getDate("entryDate"));
			bullets.add(bullet);
		}
		conn.close();
		return bullets;
	}
	
	public ArrayList<Bullet> findBulletsByUser(int id, boolean archived) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT bullet.bulletID, bullet.userID, bullet.bulletText,"+
			" bullet.catagory, bullet.accomplishedDate, bullet.entryDate"+
			" FROM bullet"+			
			" WHERE userID=?"+
			" AND archived=?");
		ps.setInt(1, id);
		ps.setBoolean(2, archived);
		ResultSet rs = ps.executeQuery();
		ArrayList<Bullet> bullets = new ArrayList<Bullet>();
		while (rs.next()) {
			Bullet bullet = new Bullet();
			bullet.setId(rs.getInt("bulletID"));
			bullet.setUserid(rs.getInt("userID"));
			bullet.setBulletText(rs.getString("bulletText"));
			bullet.setCatagory(rs.getString("catagory"));
			bullet.setAccomplishedDate(rs.getDate("accomplishedDate"));
			bullet.setEntryDate(rs.getDate("entryDate"));
			bullets.add(bullet);
		}
		conn.close();
		return bullets;
	}

	
	public Bullet findBullet(int id) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT bullet.bulletID, bullet.userID, bullet.bulletText,"+
			" bullet.catagory, bullet.accomplishedDate, bullet.entryDate"+
			" FROM bullet"+			
			" WHERE bulletID=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Bullet bullet = new Bullet();
		while (rs.next()) {			
			bullet.setId(rs.getInt("bulletID"));
			bullet.setUserid(rs.getInt("userID"));
			bullet.setBulletText(rs.getString("bulletText"));
			bullet.setCatagory(rs.getString("catagory"));
			bullet.setAccomplishedDate(rs.getDate("accomplishedDate"));
			bullet.setEntryDate(rs.getDate("entryDate"));			
		}
		conn.close();
		return bullet;
	}

	
	public Bullet insert(Bullet bullet) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" INSERT INTO bullet (userID, bulletText, catagory, accomplishedDate, entryDate, archived)" +
				" VALUES (?, ?, ?, ?, ?, ?)");
		ps.setInt(1, bullet.getUserid());
		ps.setString(2,  bullet.getBulletText());
		ps.setString(3, bullet.getCatagory());
		ps.setDate(4, bullet.getAccomplishedDate());
		ps.setDate(5, bullet.getEntryDate());
		ps.setBoolean(6, false);
		ps.executeUpdate();
		PreparedStatement getId = conn.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet rs = getId.executeQuery();
		int bulletIndex = -1;
		while(rs.next()){
			bulletIndex = rs.getInt("last_insert_id()");			
		}		
		conn.close();
		return findBullet(bulletIndex);
	}

	
	public void update(Bullet bullet) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE bullet"+
				" SET bulletText=?, catagory=?, accomplishedDate=?" +
				" WHERE bullet.bulletID=?");				
		ps.setString(1, bullet.getBulletText());
		ps.setString(2, bullet.getCatagory());
		ps.setDate(3,  bullet.getAccomplishedDate());
		ps.setInt(4,  bullet.getId());		
		ps.executeUpdate();
		conn.close();
	}

	//deletes multiple bullets by arraylist of ids
	public void delete(ArrayList<Integer> ids) throws SQLException {		
		Connection conn = ConnectionManager.getInstance().getConnection();
		for(Integer id:ids){
			PreparedStatement ps = conn.prepareStatement(
					" DELETE"+
					" FROM bullet" +
					" WHERE bullet.bulletID=?");		
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		conn.close();
	}
	
	//currently unused i think 5/13/2013
	public void delete(Bullet bullet) throws SQLException {		
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" DELETE"+
				" FROM bullet" +
				" WHERE bullet.bulletID=?");		
		ps.setInt(1, bullet.getId());
		ps.executeUpdate();
		conn.close();
	}
	
	public void deleteAll(User user) throws SQLException {		
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" DELETE"+
				" FROM bullet" +
				" WHERE userID=?");		
		ps.setInt(1, user.getId());
		ps.executeUpdate();
		conn.close();
	}
	
	public void archiveAll(User user, boolean bool) throws SQLException{
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE bullet"+
				" SET archived=?"+
				" WHERE bullet.userID=?");	
		ps.setBoolean(1, bool);
		ps.setInt(2, user.getId());
		ps.executeUpdate();
		conn.close();
	}
	
	
	public void archiveByDate(User user, Date date) throws SQLException{
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE bullet"+
				" SET archived=1"+
				" WHERE bullet.userID=?"+
				" AND accomplishedDate <= ?");				
		ps.setInt(1, user.getId());
		ps.setDate(2, date);
		ps.executeUpdate();
		
		PreparedStatement ps2 = conn.prepareStatement(
				" UPDATE bullet"+
				" SET archived=0"+
				" WHERE bullet.userID=?"+
				" AND accomplishedDate > ?");				
		ps2.setInt(1, user.getId());
		ps2.setDate(2, date);
		ps2.executeUpdate();
		
		conn.close();
	}


}
