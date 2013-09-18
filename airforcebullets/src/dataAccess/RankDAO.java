package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RankDAO {
	
	public ArrayList<String> getShortRankList() throws SQLException;
	
	public ArrayList<String> getRankList() throws SQLException;

}
