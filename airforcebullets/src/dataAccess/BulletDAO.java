package dataAccess;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.Bullet;
import objects.User;


public interface BulletDAO {

	public ArrayList<Bullet> findBulletsByUser(int id)throws SQLException;
	
	public ArrayList<Bullet> findBulletsByUser(int id, boolean archived)throws SQLException;
	
	public Bullet findBullet(int id) throws SQLException;
	
	public Bullet insert(Bullet bullet) throws SQLException;
	
	public void update(Bullet bullet) throws SQLException;
	
	public void delete(ArrayList<Integer> ids) throws SQLException;
	
	public void delete(Bullet bullet) throws SQLException;
	
	public void deleteAll(User user) throws SQLException;
	
	public void archiveAll(User user, boolean bool) throws SQLException;
	
	public void archiveByDate(User user, Date date) throws SQLException;
	
}
