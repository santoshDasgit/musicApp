<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="javax.servlet.http.Cookie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome to the Music Web App</h1>
	<a href="Login.jsp">Login</a>


	<%
	// Get the ServletContext object
	javax.servlet.ServletContext context = getServletContext();

	// Retrieve the initialization database parameter by its name
	String dburl = context.getInitParameter("dburl");
	String dbuser = context.getInitParameter("dbuser");
	String dbpass = context.getInitParameter("dbpass");

	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection(dburl, dbuser, dbpass);
	%>


	<%
	Cookie[] cookies = request.getCookies();

	String username = null;
	String type = null;
	boolean loggedIn = false;

	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
		username = cookie.getValue();
		loggedIn = true;
		break;
			}
		}
		if (username != null) {
			PreparedStatement ps = con.prepareStatement("select * from user where email = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			type = rs.getString("type");
		if (rs.getString("type").equals("user")) {
			response.sendRedirect("Userdash.jsp");
		}
			}
		}
	}

	if (!loggedIn) {
		// Redirect to the login page if not logged in
		response.sendRedirect("Login.jsp");
	}
	%>
	
	<h1><%= type %></h1>
</body>
</html>