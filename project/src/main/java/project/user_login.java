package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Resultset;

@WebServlet("/user_login_servlet")
public class user_login extends HttpServlet {
	Connection con;
	ResultSet rs;
	PreparedStatement ps;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 resp.setContentType("text/html;charset=UTF-8");

		String username = req.getParameter("username");
		String userpass = req.getParameter("password");

		PrintWriter out = resp.getWriter();

		javax.servlet.ServletContext context = getServletContext();

		// Retrieve the initialization database parameter by its name
		String dburl = context.getInitParameter("dburl");
		String dbuser = context.getInitParameter("dbuser");
		String dbpass = context.getInitParameter("dbpass");

		// Database connection and insertion
		try {
		

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(dburl, dbuser, dbpass);

			ps = con.prepareStatement("select * from user where email = ?");
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next()) {
				if (userpass.equals(rs.getString("password"))) {
//					session logic 
			          // Create a cookie for the user
		            Cookie cookie = new Cookie("user", username);

		            // Set the cookie's max age to a suitable value for permanent login (e.g., one week)
		            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days in seconds

		            // Add the cookie to the response
		            resp.addCookie(cookie);
					
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, resp);
					
				} else {
					out.println("ERROR : " + "Password not match" + "</br>" + "" + "<a " + "href=" + "'"
							+ "http://localhost:8080/project/Login.jsp" + "'>" + "GO BACK " + "</a>");
				}
			} else {
				out.println("ERROR : " + "Email not found" + "</br>" + "" + "<a " + "href=" + "'"
						+ "http://localhost:8080/project/Login.jsp" + "'>" + "GO BACK " + "</a>");
			}


			con.close();
		} catch (Exception e) {
			e.printStackTrace();

			// Get the complete URL of the current page, including query parameters
			String currentURL = "http://localhost:8080/project/Login.html";

			out.println(
					userpass+username+"ERROR : " + e + "</br>" + "" + "<a " + "href=" + "'" + currentURL + "'>" + "GO BACK " + "</a>");

		}

	}
}