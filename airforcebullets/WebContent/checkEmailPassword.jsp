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
        <%@include file="./includes/signHeaderImport.html" %>
    </head>
    <body>	
		<div class="wrap">
		<%@ include file="./includes/signHeader.jsp"%>
		    <div id="left"><!-- all of the page content goes here.-->
		        <h2>Change You Password</h2>
		        <p>An email has been sent to your
		         Air Force email account.  To reset your password, log into your Air Force email
		         account and open the message you have received from Air Force Bullets.  Inside, you 
		         will find a link.  When you click it, you will be taken to the reset password page.  
		         You may close this window as it is no longer necessary.</p>
		    </div>
		    <%@ include file="./includes/footer.html"%>	
		</div>
	</body>
</html>



