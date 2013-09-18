package notificationAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Bullet;
import objects.Notification;
import objects.User;

import com.google.gson.Gson;

import dataAccess.BulletDAO;
import dataAccess.BulletDAOImpl;
import dataAccess.NotificationDAO;
import dataAccess.NotificationDAOImpl;

/**
 * Servlet implementation class GetNotificationAjax
 */
@WebServlet("/GetNotificationAjax")
public class GetNotificationAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNotificationAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		NotificationDAO notedao = new NotificationDAOImpl();
		List<Notification> notifications;
		try {
			notifications = notedao.findNotifications(userId);
			Collections.reverse(notifications);
			Gson gson = new Gson();
			String json = gson.toJson(notifications);
			out.println(json);
			out.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
