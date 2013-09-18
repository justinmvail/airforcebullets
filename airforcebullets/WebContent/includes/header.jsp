<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="objects.User"%>
<%User user = (User)request.getSession(true).getAttribute("user");
  String displayName = user.getDisplayName();%>
	<div id="logo">
		<h1><a href="./home.jsp" title="Home">AIR FORCE BULLETS - BETA</a></h1>
		<p><%=displayName%></p>
	</div>
	<ul id="nav">
		<li><a href="./home.jsp">Home</a></li>
		<li><a href="./bullets.jsp">Bullets</a></li>
		<li><a href="./search.jsp">Search Users</a></li>
		<li><a href="./edit.jsp">Edit Account</a></li>		
		<li><a href="./contact.jsp">Contact</a></li>
		<li><a href="../LogOutServlet">Logout</a></li>
	</ul>
