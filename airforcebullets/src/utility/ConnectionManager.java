package utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.*;


public class ConnectionManager {
	
	private static ConnectionManager connManager = null;
	private DataSource dataSource;
	
	private ConnectionManager(){
		try {
	        dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + Constants.JNDI);
	    } catch (NamingException e) {
	        // Handle error that it's not configured in JNDI.
	    }		
	}
	
	public static ConnectionManager getInstance(){
		if(connManager==null){
			connManager = new ConnectionManager();
		}
		return connManager;
	}

	public Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
