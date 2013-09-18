package personAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.User;

import com.google.gson.Gson;

import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;

/**
 * Servlet implementation class SearchAjax
 */
@WebServlet("/SearchAjax")
public class SearchAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName").trim();
		String lastName = request.getParameter("lastName").trim();
		String rank = request.getParameter("rank").trim();
		String base = request.getParameter("base").trim();
		
		if(firstName.equals("")){
			firstName = "%";
		}
		if(lastName.equals("")){
			lastName = "%";
		}
		

		
		PrintWriter out = response.getWriter(); 
		
		UserDAO userdao = new UserDAOImpl();
		List<User> users = null;
		
		try {
			users = userdao.findUsers(firstName, lastName, rank, base);
			Gson gson = new Gson();
			String json = gson.toJson(users);		
			out.println(json);
		
			//out.println(Constants.EMPTY_JSON);
		
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
