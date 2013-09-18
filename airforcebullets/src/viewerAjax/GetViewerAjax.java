package viewerAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.User;
import objects.Viewer;

import com.google.gson.Gson;

import common.Constants;

import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;
import dataAccess.ViewerDAO;
import dataAccess.ViewerDAOImpl;

/**
 * Servlet implementation class GetViewerAjax
 */
@WebServlet("/GetViewerAjax")
public class GetViewerAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetViewerAjax() {
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
		List<Viewer> viewers = null;
		
		ViewerDAO viewerdao = new ViewerDAOImpl();
		try {
			viewers = viewerdao.findViewers(userId);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		ArrayList<Integer>viewerIds = new ArrayList<Integer>();
		for(int i=0;i<viewers.size();i++){
			viewerIds.add(viewers.get(i).getViewerID());
		}
		UserDAO userdao = new UserDAOImpl();
		List<User> users = null;
		
		try {
			if(viewerIds.size()>0){
				users = userdao.findUsersIn(viewerIds);
				Gson gson = new Gson();
				String json = gson.toJson(users);
				out.println(json);
			}else out.println(Constants.EMPTY_JSON);
			out.close();
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
