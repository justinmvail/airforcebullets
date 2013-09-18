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
 * Servlet implementation class DeleteViewerRelationshipAjax
 */
@WebServlet("/DeleteViewerRelationshipAjax")
public class DeleteViewerRelationshipAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteViewerRelationshipAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		int userId = user.getId();
		ArrayList arl = StringUtil.getArrayListFromString(request.getParameter("viewerIds"));
		ArrayList<Viewer> viewersToDelete = new ArrayList<Viewer>();
		for(int i=0;i<arl.size();i++){
			viewersToDelete.add(new Viewer(userId, Integer.parseInt((String)arl.get(i))));
		}
		ViewerDAO viewerdao = new ViewerDAOImpl();
		NotificationDAO notedao = new NotificationDAOImpl();
		UserDAO userdao = new UserDAOImpl();
		try {
			for(int i = 0;i<viewersToDelete.size();i++){
				viewerdao.delete(viewersToDelete.get(i));
				User viewer = userdao.findUser(viewersToDelete.get(i).getViewerID());
				Notification noteViewee = new Notification(userId, DateUtils.getCurrentSqlDate(), "You have removed "+viewer.getDisplayName()+" as a viewer.");
				notedao.insertNotification(noteViewee);
				Notification noteViewer = new Notification(viewersToDelete.get(i).getViewerID(), DateUtils.getCurrentSqlDate(), user.getDisplayName()+" has rescinded your viewing privileges.");
				notedao.insertNotification(noteViewer);
			}						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
