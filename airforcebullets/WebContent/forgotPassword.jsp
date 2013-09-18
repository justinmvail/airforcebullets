<!DOCTYPE html>
<html>    
    <head>
    	<title>Air Force Bullets</title>
	<meta name="description" content="Air Force Bullets is bullet/accomplishment tracking software.  It is free to sign up and use.">
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
		<style>
		table, td, th{
			padding:10px;
			border-spacing: 10px;
		}		
		input{
			width:170px;
		}
		</style>	
		<script>		
			$( document ).ready(function() {
				document.getElementById('passwordEmail').focus();
			});
			
		</script>
	</head>
	<body>
		<%
			//used for server side validation
			String message;
			if(request.getAttribute("signInMessage")==null){
				message = "";
			}else{
				message = "That email address does not exist.  Please enter you correct Air Force email address.";
			}			
		%>
		<div class="wrap">		
			<%@include file="./includes/signHeader.jsp" %>	
		    <div id="left">		
		    <p style ="color:RED"><%=message%></p>
		    	<table>
					<tr>
						<td>
							<div>
							<h2>Password Reset</h2>
					        <p>Please enter your email address and click submit.  
					           An email will be sent to your Air Force Email account containing a
					           link that will log you into your account and allow you to change 
					           your password. You may close this page.</p>
					        <form action="ForgotPasswordServlet" method="post">
					        <table>
					        	<tr>
					        		<td>
					        			Air Force Email:
					        		</td>					        		
					        		<td>
					        			<input type="text" name="passwordEmail" id ="passwordEmail" size="25">
					        		</td>
					        	</tr>
					        	<tr>					        							        		
					        		<td align="center" colspan="2">
					        			<input type="submit" class="blueButton" value="Submit">
					        		</td>
					        	</tr>
					        </table>
					        </form>
					       </div>
					    </td>
					   
					</tr>
				</table> 
		        <br>		        
		    </div>	
		    <%@ include file="./includes/footer.html"%>
		</div>
	</body>
</html>

        
