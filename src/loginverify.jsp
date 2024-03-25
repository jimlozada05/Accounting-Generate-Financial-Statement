<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.*,table.*,java.sql.*,java.util.*,connect.*"%>



<%

String un=request.getParameter("username");
String pw=request.getParameter("password");
try{
Connection conn = DBConnect.getConnection();
PreparedStatement ps= conn.prepareStatement("SELECT * FROM login Where username=? and password=?");
ps.setString(1, un);
ps.setString(2, pw);
ResultSet rs=ps.executeQuery();
if(rs.next()==true){
	int login_id=rs.getInt("login_id");
	String username=rs.getString("username");
	String password=rs.getString("password");
	//establish session
			session.setAttribute("login_id", login_id); //attributeName, attributeValue
			session.setAttribute("username", username); //attributeName, attributeValue
			session.setAttribute("password", password); //attributeName, attributeValue
			response.sendRedirect("index.jsp");
			
			
	}
	else
		response.sendRedirect("login.jsp");
}
catch (Exception e){
	e.printStackTrace();
}
%>

