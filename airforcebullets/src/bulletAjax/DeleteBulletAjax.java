package bulletAjax;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.BulletDAO;
import dataAccess.BulletDAOImpl;

public class DeleteBulletAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public DeleteBulletAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//comment
		BulletDAO bulletdao = new BulletDAOImpl();
		String ids = request.getParameter("ids");
		String[] arrayIds = ids.split(",");
		ArrayList<Integer> arlIds = new ArrayList<Integer>();
		for(String id: arrayIds){
			arlIds.add(Integer.parseInt(id));
		}
		try {
			bulletdao.delete(arlIds);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
