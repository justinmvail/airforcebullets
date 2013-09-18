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
		<%@ page import="dataAccess.BaseDAO, dataAccess.BaseDAOImpl"%>
		<%@ page import="dataAccess.RankDAO, dataAccess.RankDAOImpl"%>
		<%@ page import="java.util.List"%>
	
	<style>
	th,tr,td {
		padding: 5px;
		
	}
	table {
		border-spacing: 5px;
	}
	input,select {
		width: 250px;
	}
	</style>
	<script type="text/javascript" src="./js/validator.js"></script>
	<script type="text/javascript" src="./js/signIn.js"></script>
	<script type="text/javascript" src="./js/error.js"></script>
	<script>
		//list of all bases to be used in autocomplete		
		var bases = [		             
 			<%BaseDAO baseDAO = new BaseDAOImpl();
 			  List<String> bases = baseDAO.getBases();
 			  for (int i = 0;i < bases.size();i++) {
 			  	if(i==bases.size()-1){%>
 			  		<%='"'+bases.get(i)+'"'%>
 			  	<%}else{%>
 					<%='"'+bases.get(i)+'"'+","%>
 				<%}%>
 			<%}%>
 		];
		
		var ranks = [		             
	   		<%RankDAO rankDAO = new RankDAOImpl();
	  		  List<String> ranks = rankDAO.getRankList();
	  		  for (int i = 0;i < ranks.size();i++) {
		  		if(i==ranks.size()-1){%>
 			  		<%='"'+ranks.get(i)+'"'%>
 			  	<%}else{%>
 					<%='"'+ranks.get(i)+'"'+","%>
 				<%}%>
 			<%}%>
	  	];
		
		//jquery autocomplete bases
		$(function() {
			$( "#base" ).autocomplete({    			
				source: bases
			});
		});
	
		//jquery autocomplete ranks
		$(function() {
			$( "#rank" ).autocomplete({    			
				source: ranks
			});
		});
				
		$( document ).ready(function() {
			document.getElementById('email').focus();
		});	
	</script>
	
	</head>

	<body>
	<%
		//used for server side validation
		String message;
		if(request.getAttribute("email")==null){
			message = "";
		}else{
			message = "That email address is already associated with an account."+
						" Please ensure that you enter the correct email address."+
						" If the problem persists, please contact airforcebullets@gmail.com";
		}			
	%>
	
	<div class="wrap">
		<%@include file="./includes/signHeader.jsp"%>
		<div id="left">
			<p style ="color:RED"><%=message%></p>
			<!-- all of the page content goes here.-->
			<h2>Sign Up</h2>
			<p>After you submit the following information, a message will be
				sent to your Air Force email account. Inside that email, there is a
				link that activates your Air Force Bullets account. Please answer
				accurately. This information will help other users find you.</p>
			<br>
			<form name="signUp" action="UserCreationServlet" method="post">
			<table>
				<tr>
				<td width=75></td>
					<td>
						<table>
							<tr>
								<td>Air Force Email:</td>
								<td><input type="text" name="email" id="email"></td>
							</tr>
							<tr>
								<td>Rank:</td>
								<td><input type="text" name="rank" id="rank"></td>
							</tr>
							<tr>
								<td>First Name:</td>
								<td><input type="text" name="firstName" id="firstName"></td>
							</tr>
							<tr>
								<td>Middle Name:</td>
								<td><input type="text" name="middleName" id="middleName"></td>
							</tr>
							<tr>
								<td>Last Name:</td>
								<td><input type="text" name="lastName" id="lastName"></td>
							</tr>
							<tr>
								<td>Base:</td>
								<td><input type="text" name="base" id="base"></td>
							</tr>
							<tr>
								<td>Squadron:</td>
								<td><input type="text" name="squadron" id="squadron"></td>
							</tr>
							<tr>
								<td>Password:</td>
								<td><input type="password" name="password" id="password"></td>
							</tr>
							<tr>
								<td>Re-enter Password:</td>
								<td><input type="password" name="passwordTwo" id="passwordTwo"></td>
							</tr>
						</table>						
					</td>
					<td width=75></td>

					<td>
						<table>

							<tr>
								<td><img border="0" src="./images/emblem.jpg" width="175"
									height="150"></td>
							</tr>
							<tr>
								<td align="center">
									<p>
										<input type="button" class="blueButton" value="Sign Up Now" onclick="isPageValid()">
									</p>
								</td>
							</tr>
						</table>						
					</td>					
				</tr>
			</table>
			</form>
		</div>
		<div id="errorDialog" align= "center"></div>
		<%@include file="./includes/footer.html"%>
	</div>
</body>
</html>