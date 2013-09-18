package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utility.ConnectionManager;

public class RankDAOImpl implements RankDAO {

	public ArrayList<String> getShortRankList() throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT rank.rankShort"+
			" FROM rank"+
			" ORDER BY rank.rankID");		
		ResultSet rs = ps.executeQuery();
		ArrayList<String> ranks = new ArrayList<String>();
		while (rs.next()) {			
			ranks.add(rs.getString("rankShort"));
		}
		conn.close();
		return ranks;
	}

	@Override
	public ArrayList<String> getRankList() throws SQLException {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = conn.prepareStatement(
			" SELECT rank.rank"+
			" FROM rank"+
			" ORDER BY rank.rankID");		
		ResultSet rs = ps.executeQuery();
		ArrayList<String> ranks = new ArrayList<String>();
		while (rs.next()) {			
			ranks.add(rs.getString("rank"));
		}
		conn.close();
		return ranks;
	}	
}
