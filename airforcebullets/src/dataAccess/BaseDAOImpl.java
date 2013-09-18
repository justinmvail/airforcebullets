package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.ConnectionManager;

public class BaseDAOImpl implements BaseDAO {

	@Override
	public ArrayList<String> getBases() throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT base.baseName"+
			" FROM base");		
		ResultSet rs = ps.executeQuery();
		ArrayList<String> bases = new ArrayList<String>();
		while (rs.next()) {			
			bases.add(rs.getString("baseName"));
		}
		conn.close();
		return bases;
	}

}
