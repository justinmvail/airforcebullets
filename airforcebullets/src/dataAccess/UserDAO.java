package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;

import objects.User;

public interface UserDAO{
		
	public User findUser(String email) throws SQLException;
	
	public User findUser(int ID) throws SQLException;
	
	public ArrayList<User> findUsers(String firstName, String lastName, String rank, String base) throws SQLException;
	
	public ArrayList<User> findUsersIn(ArrayList<Integer> userIds)throws SQLException;
	
	public void insert(User user) throws SQLException;
	
	public void update(User user) throws SQLException;
	
	public void updateName(User user) throws SQLException;
	
	public void delete(User user) throws SQLException;
	
	public void updatePassword(User user) throws SQLException;
	
	public String getPassword(User user) throws SQLException;
	
	public void setHashId(User user)throws SQLException;
	
	public boolean isHashValid(String hash)throws SQLException;
	
	public boolean canSignIn(String email, String password)throws SQLException;
	
	public void activateUser(String hash) throws SQLException;
	
	public void deactivateUser(User user) throws SQLException;
	
	public boolean isEmailTaken(String email) throws SQLException;

}
