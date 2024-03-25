<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="table.*,connect.*,dao.*,util.*" %>
<%
	
	double total_debit=Double.parseDouble(request.getParameter("total_debit"));
	double total_credit=Double.parseDouble(request.getParameter("total_credit"));
	int total_acc=Integer.parseInt(request.getParameter("total_acc"));
	int creator_id=Integer.parseInt(request.getParameter("creator_id"));
	String type=request.getParameter("type");
	
	Journal j = new Journal();
	
	j.setTotal_debit(total_debit);
	j.setTotal_credit(total_credit);
	j.setTotal_acc(total_acc);
	j.setCreator_id(creator_id);
	j.setType(type);
	
	int status= JournalDAO.addGroup(j);
	if (status>0){
		response.sendRedirect("../generaljournal.jsp?journal_id="+status);
	}
%>