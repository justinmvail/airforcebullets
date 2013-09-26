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
    	<%
    	String message = (String) request.getAttribute("message"); 
    	String text = (String) request.getAttribute("text");
    	%>
		<div class="wrap">
		<%@ include file="./includes/signHeader.jsp"%>
		    <div id="left"><!-- all of the page content goes here.-->
		        <h2><%=message%></h2>
		        <p><%=text%></p>
		    </div>
		    <%@ include file="./includes/footer.html"%>	
		</div>
	</body>
</html>



