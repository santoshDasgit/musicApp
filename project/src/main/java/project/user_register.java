package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user_reg_servlet")
public class user_register extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
		// TODO Auto-generated method stub

//	user detail get from register.html 
		
		
		 rsp.setContentType("text/html;charset=UTF-8");
		 
		String uname = req.getParameter("uname");
		String uemail = req.getParameter("uemail");
		String upass = req.getParameter("upass");
		String upass2 = req.getParameter("upass2");
		long uphone = Long.parseLong(req.getParameter("uph"));
		
		javax.servlet.ServletContext context = getServletContext();
		

		// Retrieve the initialization database parameter by its name
		String dburl = context.getInitParameter("dburl");
		String dbuser = context.getInitParameter("dbuser");
		String dbpass = context.getInitParameter("dbpass");

		// Database connection and insertion
		try {
			String url = dburl;
			String user = dbuser;
			String password = dbpass;
			
		    Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "INSERT INTO user (name, phone ,email , password ,type) VALUES (?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, uname);
			statement.setLong(2, uphone);
			statement.setString(3, uemail);
			statement.setString(4, upass);
			statement.setString(5, "user");
		    String currentURL = "http://localhost:8080/project/Register.html";
		    
			PrintWriter out = rsp.getWriter();
			if (!upass.equals(upass2)) {
				out.println("ERROR : Password doesn't match please try again ! "+" </br>"+""
						+ "<a "
						+ "href="
						+ "'"
						+currentURL
						+ "'>"
						+ "GO BACK "
						+ "</a>");
			}
			else {
				
				out.println("SUCCESSFULLY REGISTER PLEASE LOGIN !" + "</br>"+""
						+ "<a "
						+ "href="
						+ "'"
						+ "http://localhost:8080/project/Login.jsp"
						+ "'>"
						+ " LOG IN "
						+ "</a>");
				statement.executeUpdate();
			}
			
			

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		    // Get the complete URL of the current page, including query parameters
	        String currentURL = "http://localhost:8080/project/Register.html";
	        
	     
	        

			PrintWriter out = rsp.getWriter();
			out.println("ERROR : "+ e+"</br>"+""
					+ "<a "
					+ "href="
					+ "'"
					+currentURL
					+ "'>"
					+ "GO BACK "
					+ "</a>");
			
		}

	

	}
}
