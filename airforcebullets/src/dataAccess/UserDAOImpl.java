package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.User;
import utility.ConnectionManager;
import utility.StringUtil;

public class UserDAOImpl implements UserDAO {

	public User findUser(String email) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT user.userID, user.firstName, user.lastName,"+
			" user.middleName, user.squadron, user.email, base.baseName, rank.rank"+
			" FROM user"+
			" INNER JOIN base"+
			" ON user.baseID=base.baseID"+
			" INNER JOIN rank"+
			" ON user.rankID=rank.rankID"+
			" WHERE email=?");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		User user = new User();
		while (rs.next()) {
			user.setId(rs.getInt("userID"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setMiddleName(rs.getString("middleName"));
			user.setSquadron(rs.getString("squadron"));
			user.setEmail(rs.getString("email"));
			user.setBase(rs.getString("baseName"));
			user.setRank(rs.getString("rank"));
		}
		conn.close();
		return user;
	}
	
	

	public User findUser(int id) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT user.userID, user.firstName, user.lastName,"+
			" user.middleName, user.squadron, user.email, base.baseName, rank.rank"+
			" FROM user"+
			" INNER JOIN base"+
			" ON user.baseID=base.baseID"+
			" INNER JOIN rank"+
			" ON user.rankID=rank.rankID"+
			" WHERE userID=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		User user = new User();
		while (rs.next()) {
			user.setId(rs.getInt("userID"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setMiddleName(rs.getString("middleName"));
			user.setSquadron(rs.getString("squadron"));
			user.setEmail(rs.getString("email"));
			user.setBase(rs.getString("baseName"));
			user.setRank(rs.getString("rank"));
		}
		conn.close();
		return user;
	}
	
	public ArrayList<User> findUsers(String firstName, String lastName, String rank, String base) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		//need to get the rank id first
		int rankID = getRankId(rank, conn);
		int baseID = getBaseId(base, conn);
		
		PreparedStatement ps = null;
		
		if(rankID==0 && baseID==0){
			ps = conn.prepareStatement(
					" SELECT user.userID, user.firstName, user.lastName,"+
					" user.middleName, user.squadron, user.email, base.baseName, rank.rank, rank.rankID"+
					" FROM user"+
					" INNER JOIN base"+
					" ON user.baseID=base.baseID"+
					" INNER JOIN rank"+
					" ON user.rankID=rank.rankID"+
					" WHERE user.firstName LIKE ?"+
					" AND user.lastName LIKE ?"+
					" AND user.active = 1");
				ps.setString(1, firstName);
				ps.setString(2,  lastName);
		}else if (rankID==0){
			ps = conn.prepareStatement(
					" SELECT user.userID, user.firstName, user.lastName,"+
					" user.middleName, user.squadron, user.email, base.baseName, rank.rank, rank.rankID"+
					" FROM user"+
					" INNER JOIN base"+
					" ON user.baseID=base.baseID"+
					" INNER JOIN rank"+
					" ON user.rankID=rank.rankID"+
					" WHERE user.firstName LIKE ?"+
					" AND user.lastName LIKE ?"+
					" AND user.baseID LIKE ?"+
					" AND user.active = 1");
				ps.setString(1, firstName);
				ps.setString(2,  lastName);
				ps.setInt(3, baseID);
		}else if (baseID==0){
			ps = conn.prepareStatement(
					" SELECT user.userID, user.firstName, user.lastName,"+
					" user.middleName, user.squadron, user.email, base.baseName, rank.rank, rank.rankID"+
					" FROM user"+
					" INNER JOIN base"+
					" ON user.baseID=base.baseID"+
					" INNER JOIN rank"+
					" ON user.rankID=rank.rankID"+
					" WHERE user.firstName LIKE ?"+
					" AND user.lastName LIKE ?"+
					" AND user.rankID LIKE ?"+
					" AND user.active = 1");
				ps.setString(1, firstName);
				ps.setString(2,  lastName);
				ps.setInt(3, rankID);
		}else{
			ps = conn.prepareStatement(
				" SELECT user.userID, user.firstName, user.lastName,"+
				" user.middleName, user.squadron, user.email, base.baseName, rank.rank, rank.rankID"+
				" FROM user"+
				" INNER JOIN base"+
				" ON user.baseID=base.baseID"+
				" INNER JOIN rank"+
				" ON user.rankID=rank.rankID"+
				" WHERE user.firstName LIKE ?"+
				" AND user.lastName LIKE ?"+
				" AND user.rankID LIKE ?"+
				" AND user.baseID LIKE ?"+
				" AND user.active = 1");
			ps.setString(1, firstName);
			ps.setString(2,  lastName);
			ps.setInt(3, rankID);
			ps.setInt(4, baseID);
		}		
		ResultSet rs = ps.executeQuery();
		ArrayList<User> users = new ArrayList<User>();		
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("userID"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setMiddleName(rs.getString("middleName"));
			user.setSquadron(rs.getString("squadron"));
			user.setEmail(rs.getString("email"));
			user.setBase(rs.getString("baseName"));
			user.setRank(rs.getString("rank"));
			user.setRankID(rs.getInt("rankID"));
			users.add(user);
		}
		conn.close();
		return users;
	}
	

		
	public ArrayList<User> findUsersIn(ArrayList<Integer>userIds)
			throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		String statement = " SELECT user.userID, user.firstName, user.lastName,"+
				" user.middleName, user.squadron, user.email, base.baseName, rank.rank, rank.rankID"+
				" FROM user"+
				" INNER JOIN base"+
				" ON user.baseID=base.baseID"+
				" INNER JOIN rank"+
				" ON user.rankID=rank.rankID"+
				" WHERE user.userID"+
				" IN ("+StringUtil.makeInStatement(userIds)+")";
		PreparedStatement ps = conn.prepareStatement(statement);
		//ps.setString(1, StringUtil.makeInStatement(userIds));		
		ResultSet rs = ps.executeQuery();
		ArrayList<User> users = new ArrayList<User>();		
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("userID"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setMiddleName(rs.getString("middleName"));
			user.setSquadron(rs.getString("squadron"));
			user.setEmail(rs.getString("email"));
			user.setBase(rs.getString("baseName"));
			user.setRank(rs.getString("rank"));
			user.setRankID(rs.getInt("rankID"));
			users.add(user);
		}
		conn.close();
		return users;
	}

	public void insert(User user) throws SQLException {
		
		Connection conn = ConnectionManager.getInstance().getConnection();
		int baseID = getBaseId(user.getBase(), conn);
		int rankID = getRankId(user.getRank(), conn);
		
		//Now that all the proper data has been retrieved, insert the employee
		PreparedStatement ps = conn.prepareStatement(
				" INSERT INTO user (firstName, middleName, lastName, squadron,"+
				" baseID, rankID, email, password)" +
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1,user.getFirstName());
		ps.setString(2,  user.getMiddleName());
		ps.setString(3, user.getLastName());
		ps.setString(4, user.getSquadron());
		ps.setInt(5,baseID);
		ps.setInt(6,rankID);
		ps.setString(7, user.getEmail());
		ps.setString(8, user.getPassword());
		ps.executeUpdate();
		conn.close();
	}

	public void update(User user) throws SQLException {
		
		Connection conn = ConnectionManager.getInstance().getConnection();
		int baseID = getBaseId(user.getBase(), conn);
		int rankID = getRankId(user.getRank(), conn);
		
		//Now that all the proper data has been retrieved, update the employee
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE user"+
				" SET squadron=?, baseID=?, rankID=?, email=?" +
				" WHERE user.userID=?");		
		ps.setString(1, user.getSquadron());
		ps.setInt(2,baseID);
		ps.setInt(3,rankID);
		ps.setString(4, user.getEmail());
		ps.setInt(5, user.getId());
		ps.executeUpdate();
		conn.close();
	}
	
	public void updateName(User user) throws SQLException {
		
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE user"+
				" SET firstName=?, middleName=?, lastName=?" +
				" WHERE user.userID=?");		
		ps.setString(1, user.getFirstName());
		ps.setString(2, user.getMiddleName());
		ps.setString(3, user.getLastName());
		ps.setInt(4, user.getId());
		ps.executeUpdate();
		conn.close();		
	}

	public void delete(User user) throws SQLException {

		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" DELETE"+
				" FROM user" +
				" WHERE user.userID=?");		
		ps.setInt(1, user.getId());
		ps.executeUpdate();
		conn.close();
		
	}	

	public void updatePassword(User user) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE user"+
				" SET password=?" +
				" WHERE user.userID=?");		
		ps.setString(1, user.getPassword());		
		ps.setInt(2, user.getId());
		ps.executeUpdate();
		conn.close();
	}
	
	public String getPassword(User user) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT password"+
			" FROM user"+
			" WHERE userID=?");
		ps.setInt(1, user.getId());
		ResultSet rs = ps.executeQuery();
		String password = "";
		while (rs.next()) {
			password = rs.getString("password");
		}
		conn.close();
		return password;
	}
	
	

	public void activateUser(String hash) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE user"+
				" SET active=?, activationHash=?" +
				" WHERE user.activationHash=?");		
		ps.setInt(1, 1);
		ps.setString(2, null);
		ps.setString(3, hash);
		ps.executeUpdate();
		conn.close();
	}
	
	public void deactivateUser(User user) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE user"+
				" SET active=null, activationHash=?" +
				" WHERE userID=?");		
		ps.setString(1, user.getIdHash());
		ps.setInt(2, user.getId());
		ps.executeUpdate();
		conn.close();
	}

	public void setHashId(User user) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" UPDATE user"+
				" SET activationHash=?" +
				" WHERE user.userID=?");		
		ps.setString(1, user.getIdHash());		
		ps.setInt(2, user.getId());
		ps.executeUpdate();
		conn.close();		
	}
	
	public boolean isHashValid(String hash) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" SELECT user.userID"+
				" FROM user" +
				" WHERE user.activationHash=?");		
		ps.setString(1, hash);		
		ResultSet rs = ps.executeQuery();		
		while(rs.next()){
			conn.close();
			return true;
		}
		conn.close();
		return false;		
	}
	

	public boolean canSignIn(String email, String password) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" SELECT user.userID"+
				" FROM user" +
				" WHERE user.email=?" +
				" AND user.password=?" +
				" AND user.active=1");		
		ps.setString(1, email);	
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();		
		while(rs.next()){
			conn.close();
			return true;
		}
		conn.close();
		return false;
		
//		Connection conn = ConnectionManager.getInstance().getConnection();
//		PreparedStatement ps = conn.prepareStatement(
//				" SELECT user.userID"+
//				" FROM user" +
//				" WHERE user.email=?");		
//		ps.setString(1, email);
//		ResultSet rs = ps.executeQuery();		
//		while(rs.next()){
//			conn.close();
//			return true;
//		}
//		conn.close();
//		return false;		
	}	
	
	public boolean isEmailTaken(String email) throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
				" SELECT user.userID"+
				" FROM user" +
				" WHERE user.email=?");		
		ps.setString(1, email);	
		ResultSet rs = ps.executeQuery();		
		while(rs.next()){
			conn.close();
			return true;
		}
		conn.close();
		return false;		
	}
	
	//private methods--
	private int getBaseId(String base, Connection conn) throws SQLException{
		int baseID = 0;		
		PreparedStatement ps = conn.prepareStatement(
				" SELECT base.baseID"+
				" FROM base"+
				" WHERE baseName = ?");
		ps.setString(1,  base);
		ResultSet rs = ps.executeQuery();	
		while (rs.next()) {
			baseID = rs.getInt("baseID");
		}
		return baseID;
	}
	
	private int getRankId(String rank, Connection conn) throws SQLException{
		int rankID = 0;		
		PreparedStatement ps = conn.prepareStatement(
				" SELECT rankID"+
				" FROM rank"+
				" WHERE rank = ?");
		ps.setString(1,  rank);
		ResultSet rs = ps.executeQuery();	
		while (rs.next()) {
			rankID = rs.getInt("rankID");
		}
		return rankID;
	}
	
	private int getBaseIfNotBlank(String base, Connection conn) throws SQLException{
		int baseID = 0;		
		PreparedStatement ps = conn.prepareStatement(
				" SELECT base.baseID"+
				" FROM base"+
				" WHERE baseName = ?");
		ps.setString(1,  base);
		ResultSet rs = ps.executeQuery();	
		while (rs.next()) {
			baseID = rs.getInt("baseID");
		}
		return baseID;
	}

}
