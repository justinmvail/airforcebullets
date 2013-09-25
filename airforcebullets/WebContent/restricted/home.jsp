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
        
        <link rel="stylesheet" href="../css/demo_table_jui.css" type="text/css" media="screen, projection"/>       
		<style>		
			td{
			 vertical-align:top;
			 padding:5px;
			 }
			.button{ width:70px; }		
		</style>
		
		<script type="text/javascript" src="../js/lib/dateFormat.js"></script>
		<script type="text/javascript" src="../js/lib/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="../js/loading.js"></script>	
		<script type="text/javascript" src="../js/error.js"></script>
		<script type="text/javascript" src="../js/home.js"></script>
		<script type="text/javascript" src="../js/heartbeat.js"></script>
	
	</head>
	<body>
		<div class="wrap">
		<%@ include file="../includes/header.jsp"%>
		    <div id="left"><!-- all of the page content goes here.-->
		    <h2>Home</h2>
		    <p>Welcome to Air Force Bullets.</p>
		        <table>
		        	<tr>
		        		<td colspan = "3" style="padding:0px 5px 15px 5px">
			        	<div id="notificationAccordion">  
			        		<h3>Notifications</h3> 			        		
			        		<div id="notificationTableDiv"></div> 
			        	</div>
			        	</td>
		        	</tr>
		        	<tr>
		        		<td colspan= "3" width="900px" style="padding:0px 5px 15px 5px">
		        		The following people are requesting to be viewers:<br>
<!-- 		        			<button id = "pendingApproveBtn" name = "pendingApproveBtn" class = "button" disabled>Approve</button> -->
<!-- 					        <button id = "pendingDenyBtn" name = "pendingDenyBtn" class = "button" disabled>Deny</button> -->
		        			<div id="pendingTableDiv"></div>		        			
		        		</td>	
					</tr>
					
		        	<tr>
		        		<td width="49%">
		        			The following people can view you.
<!-- 		        			<button id = "viewerDeleteBtn" name = "viewerDeleteBtn" class = "button" disabled>Delete</button> -->
					        <br>
		        			<div id="viewerTableDiv"></div>
		        		</td>
		        		<td width="1%">
		        		</td>
		        		<td width="49%">
		        			You can view the following people.
<!-- 		        			<button id = "vieweeViewBtn" name = "vieweeViewBtn" class = "button" disabled>View</button> -->
<!-- 					        <button id = "vieweeDeleteBtn" name = "vieweeDeleteBtn" class = "button" disabled>Delete</button> -->
					        <br>
							<div id="vieweeTableDiv"></div>				
						</td>
					</tr>
		        </table>
		    </div>	
		    <%@ include file="../includes/footer.html"%>
		</div>
		<form method="post" id="redirectBulletsForm" action="./userBullets.jsp">
			<input type="hidden" name="userID" id="userID" value="null" />
		</form>		
		<div id="loadingDialog" align= "center"></div>
		<div id="errorDialog" align= "center"></div>
	</body>
</html>

        
