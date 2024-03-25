<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="table.*,connect.*,dao.*" %>
<%
	
	int journal_id=Integer.parseInt(request.getParameter("journal_id"));
	
	Journal j = new Journal();
	

	j.setJournal_id(journal_id);
	
	int status= JournalDAO.deleteGroup(j);
	if(status==0){
		response.sendRedirect("../generaljournalhome.jsp");
	}


%>