package notificationAjax;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dataAccess.NotificationDAO;
import dataAccess.NotificationDAOImpl;

import objects.User;
import utility.StringUtil;

/**
 * Servlet implementation class DeleteNotificationAjax
 */
@WebServlet("/DeleteNotificationAjax")
public class DeleteNotificationAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNotificationAjax() {
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
		User user = (User) request.getSession().getAttribute("user");
		String strBullets = request.getParameter("viewerIds");
		int userId = user.getId();
		
		NotificationDAO notedao = new NotificationDAOImpl();
		try {
			notedao.deleteNotifications(StringUtil.getIntegerArrayListFromString(strBullets));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
