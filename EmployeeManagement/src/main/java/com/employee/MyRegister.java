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

@WebServlet("/myRegister")
public class MyRegister extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int empno = Integer.parseInt(req.getParameter("empno"));
        String ename = req.getParameter("ename");
        String job = req.getParameter("job");
        int mgr = Integer.parseInt(req.getParameter("mgr"));
        String hiredate = req.getParameter("hiredate").toString();
        float sal = Integer.parseInt(req.getParameter("sal"));
        float comm = Integer.parseInt(req.getParameter("comm"));
        int deptno = Integer.parseInt(req.getParameter("deptno"));
		
        Connection connection = null;
        PreparedStatement stmt = null;
        int rs;
        
        try {
            // Replace with your database driver, URL, username, and password
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet", "root", "mysql@12345");

            // Check user existence (replace with your table and column names)
            String checkSql = "INSERT INTO Employee (empno, ename, job, mgr, hiredate, sal, comm, deptNo)"
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            stmt = connection.prepareStatement(checkSql);
            stmt.setInt(1, empno);
            stmt.setString(2, ename);
            stmt.setString(3, job);
            stmt.setInt(4, mgr);
            stmt.setString(5, hiredate);
            stmt.setFloat(6, sal);
            stmt.setFloat(7, comm);
            stmt.setInt(8, deptno);
            
            rs = stmt.executeUpdate();

            PrintWriter out = resp.getWriter();
            
            out.println("Successfully Register");
            out.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
	}

}

	
