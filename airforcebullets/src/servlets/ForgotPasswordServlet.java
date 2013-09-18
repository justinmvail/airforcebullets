package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Constants;

import objects.User;
import utility.Emailer;
import utility.Hasher;
import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
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
		String email = request.getParameter("passwordEmail");
		UserDAO userDAO = new UserDAOImpl();
		try {
			User user = userDAO.findUser(email);
			if(!user.getEmail().isEmpty()){
				int userId = user.getId();
				String passwordFront = email.substring(0, 5);
				String password = passwordFront+userId;
				String passwordHash = Hasher.getHash(password);
				String link = "<a href=" + "'" + Constants.WEB_ADDRESS
						+ "/ResetPasswordServlet" + "?password=" + passwordHash + "&"+ "email="+ email + "'"
						+ ">Reset</a>";
				try {
					Emailer.sendEmail(email,
						Constants.EMAIL_WELCOME,
						"View as HTML - You have received this email because you requested to reset your password.  If you did not request to reset your password, please disregard the email.  Otherwise, click on the Reset link at the end of this message.  After you do so, your temporary password is "+password+"<br> Click on the link to reset your password... "  + link,
						Constants.EMAIL_ADDRESS,
						Constants.EMAIL_PASSWORD
					);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect("checkEmailPassword.jsp");
			}else{
				request.setAttribute("signInMessage", "failed");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/forgotPassword.jsp");
				dispatcher.forward(request, response);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
