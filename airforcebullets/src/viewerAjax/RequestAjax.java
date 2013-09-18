package viewerAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Notification;
import objects.User;
import objects.Viewer;
import utility.DateUtils;

import common.Constants;

import dataAccess.NotificationDAO;
import dataAccess.NotificationDAOImpl;
import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;
import dataAccess.ViewerDAO;
import dataAccess.ViewerDAOImpl;

/**
 * 
 * Servlet implementation class RequestAjax
 */

@WebServlet

("/RequestAjax")
public class RequestAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RequestAjax() {
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		int userID = user.getId();
		int id = Integer.parseInt(request.getParameter("id"));
		List<Viewer> viewees =	null;
		ViewerDAO viewerdao = new ViewerDAOImpl();
		try {
			viewees = viewerdao.findAllViewees(userID);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		if (userID == id) {
			out.println("{\"error\":\"You have tried to request viewing privileges for yourself.  This is unneccessary.  To view your bullets, simply click on the 'Bullets' button at the top of the page.\"}");
			return;
		}
		for (int i = 0; i < viewees.size(); i++) {
			int vieweeId = viewees.get(i).getUserID();
			if (vieweeId == id) {
				out.println("{\"error\":\"You have already requested viewing privileges for this user.\"}");;
				return;
			}
		}
		Viewer viewer =	new Viewer(id, userID);
		UserDAO userdao = new UserDAOImpl();
		User viewee = null;
		try {
			viewee = userdao.findUser(id);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		NotificationDAO notedao = new NotificationDAOImpl();
		String vieweeNameString = viewee.getDisplayName();
		Date date = DateUtils.getCurrentSqlDate();
		Notification note = new Notification(userID, date, "You have requested to view "+vieweeNameString+".");
		try {
			viewerdao.insert(viewer);
			notedao.insertNotification(note);
			out.println(Constants.EMPTY_JSON);
			out.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

	}
}