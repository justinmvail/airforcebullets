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
		<link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" /> 
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
				document.getElementById('email').focus();
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
				message = "Your login has failed. Please verify your "+
						  "credentials and try again.<br>Also, if you have not activated "+
							"your account, please do so.";
			}			
		%>
		<div class="wrap">		
			<%@include file="./includes/signHeader.jsp" %>	
		    <div id="left">		
		    <p style ="color:RED"><%=message%></p>
		    	<table>
					<tr>				
					<td width=25></td>
						<td>
							<div style="width: 360px" >
							<h2>Sign In </h2>
					        <p>Already a member of Air Force Bullets? <a href="forgotPassword.jsp">forgot password?</a></p>
					        <form name="signIn" action="SignInServlet" method="post">
					        <table>
					        	<tr>
					        		<td>
					        			Air Force Email:
					        		</td>					        		
					        		<td>
					        			<input type="text" name="email" id ="email" size="25">
					        		</td>
					        	</tr>
					        	<tr>
					        		<td>
					        			Password:
					        		</td>					        		
					        		<td>
					        			<input type="password" name="password" size="25">		        		
					        		</td>
					        	</tr>
					        	
					        	<tr>					        							        		
					        		<td align="center" colspan="2">
					        			<input type="submit" class="blueButton" value="Sign In">
					        		</td>
					        	</tr>
					        </table>
					        </form>
					       </div>
					    </td>
					    <td>
					    <div style="width: 100px" >
					    <h1>-OR-</h1>
					    </div>
					    </td>
						<td align="center" valign="top"><h2>Sign Up</h2>
					        <p>Want to Sign Up for Air Force Bullets?<br><br>
					        <form action="./signUp.jsp" method="post">
					        	<input type="submit" class="blueButton" value="Sign Up">
					        </form>
					        <br>
					        <img border="0" src="./images/emblem.jpg" width="225" height="115">				        
					    </td>
					</tr>
				</table> 
		        <br>		        
		    </div>	
		    <%@ include file="./includes/footer.html"%>
		</div>
	</body>
</html>

        
