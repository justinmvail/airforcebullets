package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Constants;

import objects.User;
import utility.Emailer;
import utility.Hasher;

import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;

public class UserCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO userDAO = new UserDAOImpl();
		try {
			if(userDAO.isEmailTaken(request.getParameter("email"))){
				request.setAttribute("email", "email is taken");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.jsp");
				dispatcher.forward(request, response);				
				return;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		User user = new User();
		user.setBase(request.getParameter("base"));
		user.setEmail(request.getParameter("email"));
		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));
		user.setRank(request.getParameter("rank"));
		user.setSquadron(request.getParameter("squadron"));
		user.setPassword(Hasher.getHash(request.getParameter("password")));
		try {
			userDAO.insert(user);
			user = userDAO.findUser(user.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int primaryKey = user.getId();
		String hashedPrimaryKey = Hasher.getHash(primaryKey);
		user.setIdHash(hashedPrimaryKey);
		try {
			userDAO.setHashId(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String link = "<a href=" + "'" + Constants.WEB_ADDRESS
				+ "/ActivationServlet" + "?id=" + hashedPrimaryKey + "'"
				+ ">Activate</a>";
		try {
			Emailer.sendEmail(user.getEmail(), Constants.EMAIL_WELCOME,
					Constants.EMAIL_MESSAGE + link, Constants.EMAIL_ADDRESS,
					Constants.EMAIL_PASSWORD);
			request.setAttribute("message", "Check Your Email");
			request.setAttribute("text", "Thank you for signing up for Air Force Bullets!  An email has been sent to your"
		         +"Air Force email account.  To activate your account, log into your Air Force email"
		         +"account and open the message you have received from Air Force Bullets.  Inside, you" 
		         +"will find a link that will activate your account.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "A problem occured!");
			request.setAttribute("text", "A Problem occured when creating your account.  This may have been due to a network error."
					+"  Please try to create your account again.  If the problem persists, please contact airforcebullets@gmail.com ");
			try {
				userDAO.delete(user);							
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkEmail.jsp");
		dispatcher.forward(request, response);	
	}
}
