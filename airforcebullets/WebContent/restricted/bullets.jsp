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
        <%@ page import = "objects.User" %>
        <link rel="stylesheet" href="../css/demo_table_jui.css" type="text/css" media="screen, projection"/>
        <style>
			table, td, tr{ border:none; }
			td{ padding:5px; }
			.button{ width:50px; }
			
			textarea {
			    display:block;     
			    width:340px;
			    height:90px;
			    padding:0;
			    border:none;
			    margin:0 auto;
			    overflow:auto;
			 }
			
		</style>
        <script type="text/javascript" src="../js/lib/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="../js/lib/ZeroClipboard.js"></script>
        <script type="text/javascript" src="../js/lib/TableTools.min.js"></script>
        <script type="text/javascript" src="../js/error.js"></script>
        <script type="text/javascript" src="../js/loading.js"></script>
        <script type="text/javascript" src="../js/heartbeat.js"></script>
        <script>		
			<%
			User thisUser = (User)session.getAttribute("user");
			String userName = thisUser.getFullName();
			%>
			var userName = '<%=userName%>';	
		</script>
		<script type="text/javascript" src="../js/bullets.js"></script>
		
		
	</head>
	<body>		
		<div class="wrap">
			<%@include file="../includes/header.jsp" %>	
		    <div id="left"><!-- all of the page content goes here.-->
		        <h2>Bullets</h2>		        				
		        <p>Below, you can create, read, update and delete bullets as you see fit.  You have the ability to sort columns by clicking the header and you can filter your results using the search box.  Also, you can download a PDF copy of your bullets sorted and filtered as you see fit by clicking save to PDF. </p>
		        <table>      
		        	<tr>  
		        		<td width=920>
		        			<table>
		        				<tr>
		        					<td width=700>
					        			<div id="radio" >
										    <input type="radio" id="activeRadio" name="radio" checked="checked" /><label for="activeRadio">Active</label>
										    <input type="radio" id="archiveRadio" name="radio" /><label for="archiveRadio">Archived</label>
										    <input type="radio" id="allRadio" name="radio" /><label for="allRadio">All</label>
										 </div>
									</td>
									<td>
										<button id="archiveBullets" name="archiveBullets">Archive/Unarchive Bullets</button>
									</td>
								</tr>
							 </table>
							 
							 <br>
				        	 <div id="table"></div>
				        </td>
			        </tr>
		        </table>		
			</div>		
			
			<%@include file="../includes/footer.html" %>	
		</div>		
		<div id="dialog-form" title="Create new bullet">  
			<form>			
				<table>					
					<tr>
					    <td><label for="category">Category:</label></td>
					    <td><input type="text" name="category" id="category"/></td>
					</tr>
					<tr>
						<td><label for="date">Date:</label></td>
					    <td><input type="text" name="date" id="date" readonly="readonly"/></td>
					</tr>
					<tr>
					    <td><label for="bullet">Bullet:</label></td>
					    <td><textarea name="bullet" id="bullet"></textarea></td>
					</tr>
				</table>
			
			</form>
		</div>
		<div id="archiveDialog" title="Archive Bullets">
			<p>Below you may change which bullets are in your archive.  You have three options:<br>  
			(1)You may archive all of your bullets.<br>  (2)You may unarchive all of your bullets.<br>  
			(3)You may provide a date.  All bullets older(inclusively) than the provided date are archived and
			all bullets newer than the date are active.  (Note: Today's date is default) </p>
		 	<div id="archiveDate" align ="center"></div>
		</div>
		<div id="loadingDialog" align= "center"></div>	
		<div id="errorDialog" align= "center"></div>	
	</body>
</html>