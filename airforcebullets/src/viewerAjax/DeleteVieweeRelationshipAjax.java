package viewerAjax;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Notification;
import objects.User;
import objects.Viewer;
import utility.DateUtils;
import utility.StringUtil;
import dataAccess.NotificationDAO;
import dataAccess.NotificationDAOImpl;
import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;
import dataAccess.ViewerDAO;
import dataAccess.ViewerDAOImpl;

/**
 * Servlet implementation class DeleteVieweeRelationshipAjax
 */
@WebServlet("/DeleteVieweeRelationshipAjax")
public class DeleteVieweeRelationshipAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVieweeRelationshipAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		ArrayList arl = StringUtil.getArrayListFromString(request.getParameter("vieweeIds"));
		ArrayList<Viewer> vieweesToDelete = new ArrayList<Viewer>();
		for(int i=0;i<arl.size();i++){
			Viewer viewer = new Viewer();
			viewer.setUserID(Integer.parseInt((String)arl.get(i)));
			viewer.setViewerID(userId);
			vieweesToDelete.add(viewer);
		}
		ViewerDAO viewerdao = new ViewerDAOImpl();
		NotificationDAO notedao = new NotificationDAOImpl();
		UserDAO userdao = new UserDAOImpl();
		try {
			for(int i = 0;i<vieweesToDelete.size();i++){
				viewerdao.delete(vieweesToDelete.get(i));
				User viewee = userdao.findUser(vieweesToDelete.get(i).getUserID());
				Notification noteViewee = new Notification(userId, DateUtils.getCurrentSqlDate(), "You have rescinded your viewing privileges over "+viewee.getDisplayName()+".");
				notedao.insertNotification(noteViewee);
				Notification noteViewer = new Notification(vieweesToDelete.get(i).getUserID(), DateUtils.getCurrentSqlDate(), user.getDisplayName()+" has rescinded viewing privileges over you.");
				notedao.insertNotification(noteViewer);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
}
