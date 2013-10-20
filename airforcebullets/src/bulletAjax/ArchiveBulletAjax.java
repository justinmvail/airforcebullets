package bulletAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.Bullet;
import objects.User;
import utility.DateUtils;

import com.google.gson.Gson;
import common.Constants;

import dataAccess.BulletDAO;
import dataAccess.BulletDAOImpl;

/**
 * Servlet implementation class ArchiveBulletAjax
 */
@WebServlet("/ArchiveBulletAjax")
public class ArchiveBulletAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArchiveBulletAjax() {
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
		
		User user = (User)request.getSession().getAttribute("user");
		int userID = user.getId();
		String option = request.getParameter("option");
		
		BulletDAO bulletdao = new BulletDAOImpl();
		try {
			if(option.equals("archiveAll")){
				bulletdao.archiveAll(user, true);
			}else if(option.equals("unarchiveAll")){
				bulletdao.archiveAll(user, false);
			}else{				
				try {
					bulletdao.archiveByDate(user, DateUtils.getSqlDate(request.getParameter("option")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();		
		out.println(Constants.EMPTY_JSON);
		out.close();
	}

}
