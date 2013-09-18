package common;

public class Constants {

	public static final String JNDI = "jdbc/db";
	
	public static final String EMAIL_ADDRESS = "airforcebullets@gmail.com";
	public static final String EMAIL_PASSWORD = "timeflie$";
	public static final String EMAIL_WELCOME = "Welcome to Air Force Bullets";
	public static final String EMAIL_MESSAGE = "View As HTML --- Click Activate to start using Air Force Bullets. "+
											   "When you do, you will be taken to the sign in screen. "+
											   "From there, simply login and start making bullets! ... ";
	
	
	//this one is for local environment
//	public static final String WEB_ADDRESS = "http://localhost:8080/airforcebullets";
	
	//this is for production
	public static final String WEB_ADDRESS = "http://airforcebullets.com";
	
	public static final String[]unristrictedPages = {"index.jsp", "signUp.jsp","checkEmail.jsp", "ActivationServlet"};
	
	public static String REQUESTED_VIEWER= "requestedViewer";
	public static String VIEWER= "viewer";
	public static String VIEWEE= "viewee";
	
	public static String EMPTY_JSON= "{}";
}
