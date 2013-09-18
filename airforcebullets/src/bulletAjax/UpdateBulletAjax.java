package bulletAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dataAccess.BulletDAO;
import dataAccess.BulletDAOImpl;

import objects.Bullet;
import objects.User;
import utility.DateUtils;

/**
 * Servlet implementation class UpdateBulletAjax
 */

public class UpdateBulletAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateBulletAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		int userID = user.getId();
		int bulletID = Integer.parseInt(request.getParameter("id"));
		String bulletText = request.getParameter("text");
		String catagory = request.getParameter("catagory");
		Date date = null;
		try {
			date = DateUtils.getSqlDate(request.getParameter("date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//int userId, int id, String bulletText, String catagory, Date accomplishedDate
		Bullet bullet = new Bullet(userID, bulletID, bulletText, catagory, date);
		BulletDAO bulletdao = new BulletDAOImpl();
		try {
			bulletdao.update(bullet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String json = gson.toJson(bullet);
		out.println(json);
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
