package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;

public class ActivationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ActivationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hashID = request.getParameter("id");
		UserDAO userDAO = new UserDAOImpl();
		try {
			if(userDAO.isHashValid(hashID)){
				userDAO.activateUser(hashID);
				response.sendRedirect("");
			}else{
				response.sendRedirect("error.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
