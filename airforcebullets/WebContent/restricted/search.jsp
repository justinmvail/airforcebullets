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
        <link rel="stylesheet" href="../css/demo_table_jui.css" type="text/css" media="screen, projection"/> 
		<style>
			table, td, th
			{
			border:none;
			}
			td
			{
			padding:5px;
			}
			button{ width:210px; }
			input{
				width:142px;
			}
		</style>
		<script type="text/javascript" src="../js/lib/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="../js/loading.js"></script>
		<script type="text/javascript" src="../js/search.js"></script>
		<script type="text/javascript" src="../js/error.js"></script>
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
		
			
		$( document ).ready(function() {
			document.getElementById('firstname').focus();
		});
		</script>
	</head>
	<body>
		<div class="wrap">
			<%@include file="../includes/header.jsp" %>
		    <div id="left"><!-- all of the page content goes here.-->
		        <h2>Search Users</h2>
		        <p>Below, you can search users and request viewership.</p>
		        <table>
		        	<tr>
		        		<td valign = "top" style="padding:8px 5px 5px 5px">
					        <table>
								<tr>
									<td>First Name:</td>
									<td><input type="text" id="firstname" name="firstname" onKeydown="Javascript: if (event.keyCode==13) searchUsers();"></td>
								</tr>
								<tr>
									<td>Last Name:</td>
									<td><input type="text" id="lastname" name="lastname" onKeydown="Javascript: if (event.keyCode==13) searchUsers();"></td>
								</tr>		
								<tr>
									<td>Rank:</td>
									<td><input type="text" id = "rank" name="rank" onKeydown="Javascript: if (event.keyCode==13) searchUsers();"></td>
								</tr>
								<tr>
									<td>Base:</td>
									<td><input type="text" id="base" name="base" onKeydown="Javascript: if (event.keyCode==13) searchUsers();"></td>
								</tr>		
								<tr >
									<td colspan = "2" align="center">
										<br>
										<input class="blueButton" type ="submit" value = "Find" onClick = "searchUsers()">
									</td>		
								</tr>
							</table>
						</td>
						<td valign="top" style="border: solid grey 0px; border-left-width:2px; padding:5px 0px 5px 5px">						
							<table>
								<tr>
									<td width="655px" valign="top">
										<div id="table" ></div>
									</td>
								</tr>
							</table>
						</td>
					</tr>					
				</table>		        
		    </div>
		    <div id="loadingDialog" align= "center"></div>
		    <div id="errorDialog" align= "center"></div>
		    <%@include file="../includes/footer.html" %>	
		</div>
	</body>
</html>
