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

public class InsertBulletAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertBulletAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		User user = (User)request.getSession().getAttribute("user");
		int userID = user.getId();
		Date date = null;
		Bullet returnBullet;
		try {
			date = DateUtils.getSqlDate(request.getParameter("date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bullet bullet = new Bullet(userID, date, request.getParameter("catagory"), request.getParameter("text"));
		BulletDAO bulletdao = new BulletDAOImpl();
		try {
			returnBullet = bulletdao.insert(bullet);
			Gson gson = new Gson();
			String json = gson.toJson(returnBullet);
			out.println(json);
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
