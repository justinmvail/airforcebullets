package personAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.User;
import utility.Hasher;

import common.Constants;

import dataAccess.BulletDAO;
import dataAccess.BulletDAOImpl;
import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;
import dataAccess.ViewerDAO;
import dataAccess.ViewerDAOImpl;

/**
 * Servlet implementation class DeleteAccountAjax
 */
@WebServlet("/DeleteAccountAjax")
public class DeleteAccountAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccountAjax() {
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
		
		PrintWriter out = response.getWriter(); 
		
		String password = request.getParameter("password");		
		
		String dbPassword ="";				
		UserDAO userdao = new UserDAOImpl();
		ViewerDAO viewerdao = new ViewerDAOImpl();
		BulletDAO bulletdao = new BulletDAOImpl();
		String passwordHash = Hasher.getHash(password);				
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			dbPassword = userdao.getPassword(user);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(dbPassword.equals(passwordHash)){
			try {
				bulletdao.deleteAll(user);
				viewerdao.deleteAllForUser(user);
				userdao.delete(user);
				request.getSession().setAttribute("user", null);				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			out.write(Constants.EMPTY_JSON);
		}else{
			out.write("{\"error\":\"You have entered the wrong Password.  You may not delete your account without providing the correct password.\"}");
		}	
	}

}
