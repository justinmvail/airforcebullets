package dataAccess;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BaseDAO {

	public ArrayList<String> getBases() throws SQLException;
	
}
