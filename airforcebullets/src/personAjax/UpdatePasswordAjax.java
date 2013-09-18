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
import utility.Hasher;

import common.Constants;

import dataAccess.NotificationDAO;
import dataAccess.NotificationDAOImpl;
import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;

/**
 * Servlet implementation class UpdatePasswordAjax
 */
@WebServlet("/UpdatePasswordAjax")
public class UpdatePasswordAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordAjax() {
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
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("password");
		String dbPassword ="";
		
		UserDAO userdao = new UserDAOImpl();
		NotificationDAO notedao = new NotificationDAOImpl();
		
		String oldPasswordHash = Hasher.getHash(oldPassword);
				
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			dbPassword = userdao.getPassword(user);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(dbPassword.equals(oldPasswordHash)){
			user.setPassword(Hasher.getHash(newPassword));
			try {
				Notification note = new Notification(user.getId(), DateUtils.getCurrentSqlDate(), "Your password has been updated sucessfully.");
				notedao.insertNotification(note);
				userdao.updatePassword(user);
				request.getSession().setAttribute("user", user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.write(Constants.EMPTY_JSON);
		}else{
			out.write("{\"error\":\"You have entered the wrong Password.  You may not update your password until you provide the correct old Password.\"}");
		}		
	}
}
