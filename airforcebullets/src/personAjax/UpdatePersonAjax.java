package personAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Notification;
import objects.User;
import utility.DateUtils;

import common.Constants;
import dataAccess.NotificationDAO;
import dataAccess.NotificationDAOImpl;
import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;

/**
 * Servlet implementation class UpdatePerson
 */
@WebServlet("/UpdatePerson")
public class UpdatePersonAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePersonAjax() {
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
		
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String rank = request.getParameter("rank");
		String squadron = request.getParameter("squadron");
		String base = request.getParameter("base");
		
		User user = (User) request.getSession().getAttribute("user");
		
		user.setFirstName(firstName);
		user.setMiddleName(middleName);
		user.setLastName(lastName);
		user.setRank(rank);
		user.setSquadron(squadron);
		user.setBase(base);
		
		UserDAO userdao = new UserDAOImpl();
		NotificationDAO notedao = new NotificationDAOImpl();
		Notification note = new Notification(user.getId(), DateUtils.getCurrentSqlDate(), "Your Account information has been updated successfully.");
		try {
			userdao.updateName(user);
			userdao.update(user);
			notedao.insertNotification(note);
			request.getSession().setAttribute("user", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		out.write(Constants.EMPTY_JSON);
	}

}
