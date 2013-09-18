package personAjax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objects.User;
import utility.Emailer;
import utility.Hasher;

import common.Constants;

import dataAccess.UserDAO;
import dataAccess.UserDAOImpl;

/**
 * Servlet implementation class UpdateEmailAjax
 */
@WebServlet("/UpdateEmailAjax")
public class UpdateEmailAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmailAjax() {
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
		
		String password = request.getParameter("password");		
		String email = request.getParameter("email");
		
		String dbPassword ="";				
		UserDAO userdao = new UserDAOImpl();
		String passwordHash = Hasher.getHash(password);				
		User user = (User) request.getSession().getAttribute("user");
		String oldEmail = user.getEmail();
		String hashedPrimaryKey = Hasher.getHash(user.getId());
		
		try {
			dbPassword = userdao.getPassword(user);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(dbPassword.equals(passwordHash)){
			try {
				user.setEmail(email);
				user.setIdHash(hashedPrimaryKey);
				userdao.update(user);
				userdao.deactivateUser(user);
				request.getSession().setAttribute("user",user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String link = "<a href=" + "'" + Constants.WEB_ADDRESS
						+ "/ActivationServlet" + "?id=" + hashedPrimaryKey + "'"
						+ ">Activate</a>";
				Emailer.sendEmail(user.getEmail(), Constants.EMAIL_WELCOME,
						Constants.EMAIL_MESSAGE + link, Constants.EMAIL_ADDRESS,
						Constants.EMAIL_PASSWORD);
				out.write(Constants.EMPTY_JSON);
			}catch(Exception e){
				user.setEmail(oldEmail);
				try {
					userdao.update(user);
					userdao.activateUser(Hasher.getHash(user.getId()));
					out.write("{\"error\":\"Your email could not be updated.  If the problem persists, please use the contact page to report the bug.\"}");
					return;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
			//out.write(Constants.EMPTY_JSON);
		}else{
			out.write("{\"error\":\"You have entered the wrong Password.  You may not update your password until you provide the correct old Password.\"}");
		}	
	}
}
