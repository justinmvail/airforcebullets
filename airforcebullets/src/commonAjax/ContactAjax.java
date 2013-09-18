package commonAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.User;

import utility.Emailer;

import common.Constants;

/**
 * Servlet implementation class ContactAjax
 */
@WebServlet("/ContactAjax")
public class ContactAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactAjax() {
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
		PrintWriter out = response.getWriter();
		User user  = (User) request.getSession().getAttribute("user");
		String category = request.getParameter("category");
		Date date = new Date();
		category = category + ": " +  user.getDisplayName() + " on "+date;
		String message = "\""+request.getParameter("message")+"\"";
		
		try{
			Emailer.sendEmail(user.getEmail(),
									category,
									"AirForceBullets has received your message: "+message,
									Constants.EMAIL_ADDRESS,
									Constants.EMAIL_PASSWORD);
		}catch(Exception e){
			e.printStackTrace();
			out.println("{\"error\": \"error\"}");
			out.close();
			return;
		}
		
		out.println(Constants.EMPTY_JSON);
		out.close();
	}

}
