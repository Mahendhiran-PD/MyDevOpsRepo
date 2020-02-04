package com.wipro.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.util.DBUtil;
@WebServlet("/user")
public class UserServlet extends HttpServlet {

	Connection con;
	Statement st;
	ResultSet rs;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		long phone = Long.parseLong(request.getParameter("phone"));
		String option = request.getParameter("option");

		try {
			con = DBUtil.getConnection();
			st = con.createStatement();
			
			if (option.equals("Register")) {

				st.executeUpdate("insert into userdetails values('" + username + "','" + password + "','" + email + "',"
						+ phone + ")");
				response.sendRedirect("Success.html");

			}
			if (option.equals("Update")) {

				st.executeUpdate("update userdetails set password='" + password + "', email='" + email + "',phone="
						+ phone + " where username='" + username + "'");
				response.sendRedirect("Success.html");

			}
			if (option.equals("Delete")) {

				st.executeUpdate("delete from userdetails where username='" + username + "'");
				response.sendRedirect("Success.html");

			}
			if (option.equals("Show")) {

				rs = st.executeQuery("select * from userdetails");
				
				if (rs != null) {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();
					out.println("<table border='1'>");
					out.println("<th>Name</th><th>Password</th><th>Email</th><th>Phone No.</th>");
					while (rs.next()) {
						out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td><td>"
								+ rs.getString(3) + "</td><td>" + rs.getLong(4) + "</td></tr>");
					}
					out.print("</table>");
				}
			}
		} catch (Exception e) {
			response.sendRedirect("Error.html");
		}

	}

}
