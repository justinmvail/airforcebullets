<!DOCTYPE html>
<html>    
    <head>
    	<%
    	response.setHeader("Cache-Control","no-cache");
    	response.setHeader("Pragma","no-cache");
    	response.setDateHeader ("Expires", -1);
    	%>
    	<meta http-equiv="cache-control" content="max-age=0" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
		<meta http-equiv="pragma" content="no-cache" />
	    <%@ page import="dataAccess.UserDAO, dataAccess.UserDAOImpl"%>
	    <%@ page import="objects.User"%>
        <%@include file="../includes/headerImport.html" %>
        <link rel="stylesheet" href="../css/demo_table_jui.css" type="text/css" media="screen, projection"/>
        <style>
			table, td, tr{ border:none; }
			td{ padding:5px; }			
			.button{ width:50px; }
		</style>
		<%
		int userID = Integer.parseInt(request.getParameter("userID"));
		UserDAO baseDAO = new UserDAOImpl();
		User thisUser = baseDAO.findUser(userID);
		String userName = thisUser.getFullName();
		String preparedUserName;
		if(userName.endsWith("s")){
			preparedUserName = userName+"'";			
		}else{
			preparedUserName = userName+"'s";
		}
		%>
		<script>
		var bulletID = <%=userID%>;//this is for viewing another users bullets
		var userName = '<%=userName%>';
		</script>
        <script type="text/javascript" src="../js/lib/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../js/lib/ZeroClipboard.js"></script>
        <script type="text/javascript" src="../js/lib/TableTools.min.js"></script>
        <script type="text/javascript" src="../js/loading.js"></script>
        <script type="text/javascript" src="../js/userBullets.js"></script>
        <script type="text/javascript" src="../js/heartbeat.js"></script>
	</head>
	<body>
		<div class="wrap">
			<%@include file="../includes/header.jsp" %>
		    <div id="left"><!-- all of the page content goes here.-->
		        <h2>Bullets</h2>
		        <p>You are viewing <%=preparedUserName%> bullets. </p>
		        <table>
		        	<tr>  
		        		<td width=920>				        
				        <div id="table"></div>
				        </td>
			        </tr>
		        </table>
			</div>			
			<%@include file="../includes/footer.html" %>	
		</div>		
	</body>
</html>