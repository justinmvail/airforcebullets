package bulletAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import objects.Bullet;
import objects.User;

import dataAccess.BulletDAO;
import dataAccess.BulletDAOImpl;

/**
 * Servlet implementation class GetBulletsAjax
 */

public class GetBulletAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetBulletAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		PrintWriter out = response.getWriter();
		int userId = -1;
		if (request.getParameter("userID")==null){//this is for viewing your own bullets
			User user = (User) request.getSession().getAttribute("user");
			userId = user.getId();
		}else{
			userId = Integer.parseInt(request.getParameter("userID"));
			type = "active";
		}
		List<Bullet> bullets;
		
		BulletDAO bulletdao = new BulletDAOImpl();
		try {
			if(type.equals("all")){
				bullets = bulletdao.findBulletsByUser(userId);
			}else if(type.equals("archive")){
				bullets = bulletdao.findBulletsByUser(userId, true);
			}else{
				bullets = bulletdao.findBulletsByUser(userId, false);
			}
			Gson gson = new Gson();
			String json = gson.toJson(bullets);
			out.println(json);
			out.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
