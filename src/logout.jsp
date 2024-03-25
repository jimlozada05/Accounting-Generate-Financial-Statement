<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dao.*,java.util.*,table.*,java.sql.*,connect.*"%>

<%
Login log = new Login();
session.invalidate();
response.sendRedirect("login.jsp");

%>