package com.ems.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/insert")
public class inserServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	PrintWriter out = response.getWriter();
	
	
	
	
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("uid");
		String password = request.getParameter("psw");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			java.sql.Connection conn = DriverManager.getConnection(
					  "jdbc:mysql://aws.connect.psdb.cloud/ems?sslMode=VERIFY_IDENTITY",
					  "f4kgmlkcgg1nj2gu3wl3", "pscale_pw_fohYq855B06VthlpvUeOCfNR4pOdAOYJEE7TwzCUx5e");
			java.sql.PreparedStatement ps = conn.prepareStatement("insert into user_details (Name, email, phone, username,password) values (?,? ,?, ?,?);");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setInt(3, Integer.parseInt(phone));
			ps.setString(4, username);
			ps.setString(5, password);
			
			
			int count = ps.executeUpdate();
			if(count > 0) {
				
				response.setContentType("text/html");
				out.print("<h3 style ='color:green'> User registration successful page<h3>");
				System.out.println("success");
				
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);

				
			}else {
				
				response.setContentType("text/html");
				out.print("<h3 style ='color:red'> User registration unsuccessful page<h3>");
				System.out.println("success");
				RequestDispatcher rd = request.getRequestDispatcher("studentInsert.jsp");
				rd.include(request, response);
				
			}

			
		}catch(Exception e){
			out.print("not working....    ");
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
