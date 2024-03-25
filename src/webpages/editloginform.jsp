<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.*,java.util.*,table.*,java.sql.*,connect.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit User Form</title>
</head>
<body>
	<%
	Login log= LoginDAO.getLoginByID((Integer) session.getAttribute("login_id"));
	request.setAttribute("login", log);
	%>
		<%
		String Login_id = request.getParameter("login_id");
		%>
	<form action="editlogin" method="post" enctype="multipart/form-data">
	<input type="hidden" name="login_id" value="<%=log.getLogin_id()%>">
		
		<label>Profile</label>
		<img src="webpages/getloginimage.jsp?login_id=<%=log.getLogin_id()%>" style="border:dashed 5px;border-color:black;height:100px;width:100px;" alt="Profile Picture"/><br/><br/>
		
		<label>Username: </label>
		<input type="text" name="username" value="<%=log.getUsername()%>"><br/><br/>

		<label>Password</label>
		<input type="text" name="password" value="<%=log.getPassword()%>"><br/><br/>

		
		<button type="submit">Edit</button>
	</form>
</body>
</html>