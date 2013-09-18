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
        
        
        <script type="text/javascript" src="../js/loading.js"></script>	
        <script type="text/javascript" src="../js/error.js"></script>
        <script type="text/javascript" src="../js/contact.js"></script>
        <script type="text/javascript" src="../js/heartbeat.js"></script>
        
    </head>
    <body>
		<div class="wrap">	
	        <%@include file="../includes/header.jsp" %>	
		    <div id="left"><!-- all of the page content goes here.-->
		        <h2>Contact</h2>
		        <p>If you would like to contact Air Force Bullets, fill out the
		         form below and click send.</p>
<!-- 					<p>If you would like to contact Air Force Bullets, please use airforcebullets@gmail.com</p> -->
				<br>
		        <br>
			        Reason for contact:   
			        <select name="reason" id = "reason">
						 <option value="Bug Report">Bug Report</option>
						 <option value="Suggestion">Suggestion</option>
						 <option value="Comment">Comment</option>
						 <option value="Complaint">Complaint</option>
					</select>
			        <br>
			        <br>
			        <textarea name="message" id="message" style="width: 900px; height: 100px" rows="10" cols="5"></textarea>
			        <br>
			        <br>
			        <button class="blueButton" onclick = "sendEmail()">Send</button>
		    </div>	
		    <%@include file="../includes/footer.html" %>
		</div>		
		<div id="loadingDialog" align= "center"></div>
		<div id="errorDialog" align= "center"></div>
		<div id="emailDialog" title="Email Sent">
  			<p>Your email has been sent successfully. Thank you for your feedback.</p>
		</div>
	</body>
</html>