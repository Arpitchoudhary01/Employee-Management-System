package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/myLogin")
public class MyLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Get login credentials (replace with your form field names)
		int empno = Integer.parseInt(req.getParameter("empno"));
        String ename = req.getParameter("ename");
        
     // Database connection (replace with your details)
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Replace with your database driver, URL, username, and password
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "mysql@12345");

            // Check user existence (replace with your table and column names)s
            String checkSql = "SELECT * FROM Employee WHERE EMPNO = ? AND ENAME = ?";
            stmt = connection.prepareStatement(checkSql);
            stmt.setInt(1, empno);
            stmt.setString(2, ename);
            rs = stmt.executeQuery();

            PrintWriter out = resp.getWriter();
            
            if (rs.next()) {
                out.println("<html><body><h1>Login Successful!</h1></body></html>");
            } else {
                out.println("<html><body><h1>Login Failed! Invalid credentials.</h1></body></html>");
            }

            out.close();

        } catch (ClassNotFoundException | SQLException e) {
            ((Throwable) e).printStackTrace();
        }
	}

}
