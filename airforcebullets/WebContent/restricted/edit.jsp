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
        <%@include file="../includes/headerImport.html" %>	
        
        <%@ page import="dataAccess.BaseDAO, dataAccess.BaseDAOImpl"%>
		<%@ page import="dataAccess.RankDAO, dataAccess.RankDAOImpl"%>
		<%@ page import="java.util.List"%>
		<style>
			table, td, th
			{
/*  			border: solid black 1px;  */
		border:none; 
			}
			td
			{
			padding:5px;
			}
		</style>		
		<script type="text/javascript" src="../js/lib/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="../js/loading.js"></script>		
		<script type="text/javascript" src="../js/validator.js"></script>
		<script type="text/javascript" src="../js/error.js"></script>
		<script type="text/javascript" src="../js/edit.js"></script>
		<script type="text/javascript" src="../js/heartbeat.js"></script>
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
		</script>
    </head>
    
    <body id ="home">
        <div class="wrap">
			<%@ include file="../includes/header.jsp"%>
			
		    <div id="left"><!-- all of the page content goes here.-->
		        <h2>Edit Account</h2>
				<p>This page allows you to change your user information.
		        <table>
				<tr>
					<td rowspan=7 width="10px"></td>
					<td>First Name:</td>
					<td><input type="text" id="firstname"></td>
					<td rowspan=7 width="50px"></td>
					<td rowspan=4 width="400px" valign="top" style="padding:20px 5px 5px 40px">
					Please be careful when editing your account information.  This information will
					 be reflected across the entire application.  You will still retain all of the
					  same viewer/viewee relationships, but others will now see the information as you have updated it here.   </td>
				</tr>
				
				<tr>
					<td>Middle Name:</td>
					<td><input type="text" id="middlename"></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" id="lastname"></td>
				</tr>
				<tr>
					<td>Rank:</td>
					<td><input type="text" id="rank"></td>
				</tr>
				<tr>
					<td>Squadron:</td>
					<td><input type="text" id="squadron"></td>					
				</tr>
				<tr>
					<td>Base:</td>
					<td><input type="text" id="base"></td>
					<td valign="bottom" align = "center" style="padding:5px 5px 5px 40px">You may also:</td>
				</tr>
				
				
				<tr>
					<td colspan="2" align="center">
						<br><input type ="submit" class="blueButton" value = "Edit" onClick= "isPageValid()">
					</td>
					<td>
						<table>
							<tr>
								<td width = "133px" align = "right" style="padding:5px 5px 5px 30px"><button id = "passwordButton" name = "passwordButton" class="blueButton" style ="width:120px">Change Password</button></td>
								<td width = "133px" align = "center"><button id = "emailButton" name = "emailButton" class="blueButton">Change Email</button></td>
								<td width = "133px" align = "left"><button id = "deleteButton" name = "deleteButton" class="blueButton" style ="width:110px">Delete Account</button></td>
							</tr>
						</table>
					</td>					
				</tr>
				</table>
				
				<br>        
    		</div>
    		<div id="passwordDialog" title="Change Password">
				<table>
					<tr>
						<td>
							old password:
						</td>
						<td>
							<input style="width:135px" type="password" id="oldpassword"></input>
						</td>					
					</tr>
					<tr>
						<td>
							new password:
						</td>
						<td>
							<input style="width:135px" type="password" id="password1"></input>
						</td>						
					</tr>
					<tr>
						<td>
							new password:
						</td>
						<td>
							<input style="width:135px" type="password" id="password2"></input>
						</td>						
					</tr>
					<tr>
						<td colspan="2" align="center">
							<br>
							<input type="submit" id= "passwordsubmit"value="Change Password">
						</td>					
					</tr>
				</table>
			</div>
			<div id="emailDialog" title="Change Email">
			<ul>
				<li>Below you may change the email address that is associated with your account.</li>
				<li> You will not be able to use Air Force Bullets until you re-activate your account using the new email address.  
			</ul>				
 
				<table>
					<tr>
						<td>
							password:
						</td>
						<td>
							<input style="width:180px" type="password" id="emailPassword"></input>
						</td>					
					</tr>
					<tr>
						<td>
							email:
						</td>
						<td>
							<input style="width:180px" type="text" id="email"></input>
						</td>						
					</tr>
					<tr>
						<td>
							email:
						</td>
						<td>
							<input style="width:180px" type="text" id="email2"></input>
						</td>						
					</tr>
					<tr>
						<td colspan="2" align="center">							
						</td>					
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" id= "emailsubmit"value="Change Email">
						</td>					
					</tr>
				</table>
			</div>
			<div id="deleteDialog" title="Delete Account">				
				<p>This is not like Facebook!  Once you delete your account, it can not be undone.  If you would like to delete your account, enter your password and click delete.
				<table>
					<tr>
						<td>
							password:
						</td>
						<td>
							<input style="width:180px" type="password" id="deletePassword"></input>
						</td>					
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" id= "deleteSubmit"value="Delete Account">
						</td>					
					</tr>
				</table>				
			</div>
			
	        <%@ include file="../includes/footer.html"%>
	    </div>	    
	    <div id="loadingDialog" align= "center"></div>
	    <div id="errorDialog" align= "center"></div>
    </body>
</html>

