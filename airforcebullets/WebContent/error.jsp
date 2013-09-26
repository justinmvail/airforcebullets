<!DOCTYPE html>
<html>    
    <head>
    	<title>Air Force Bullets</title>
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
    	
		<link rel="shortcut icon" href="http://airforcebullets.com/images/favicon.ico">
					
		<link rel="stylesheet" href="css/main.css" type="text/css" media="screen, projection"/>	
		<link rel="stylesheet" href="../css/main.css" type="text/css" media="screen, projection"/>	
		
	</head>
	<body>
	
		<div class="wrap">		
			<div id="logo">
				<h1><a>AIR FORCE BULLETS</a></h1>				
			</div>
			
			<ul id="nav">
				<li><a></a></li>
			</ul>
			
		    <div id="left">	
		    	<br>
		    	<h2>Error</h2>
		        <p>There has been an error.  This may be do to a bad redirect or an internal server error.  If the problem continues, please use the contact page to briefly describe to events that lead to the error.  If you cannot reach the contact page, please send your message to airforcebullets@gmail.com.  Use your browser back button to navigate away from this page.</p>		        
		    </div>	
		    <%@ include file="./includes/footer.html"%>
		</div>
	</body>
</html>

        
