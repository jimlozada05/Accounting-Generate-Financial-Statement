<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="dao.*,java.util.*,table.*,java.sql.*,connect.*,util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
	JournalEntry je=JournalEntryDAO.getGeneralJournalByID(Integer.parseInt(request.getParameter("entry_id")));
	request.setAttribute("JournalEntry",je);%>

<form method="POST" action="webpages/editgeneraljournal.jsp">
	
	<div class="modal-body">
		<input type="hidden" name="entry_id" value="<%=je.getEntry_id()%>">
		<input type="hidden" name="journal_id" value="<%=je.getJournal_id()%>">
	
		<div class="form-group">
			<label class="control-label">Account Title:</label>
			<select name="account_title" class="form-control" required>
			<option value="<%=je.getAccount_title()%>"><%=je.getAccount_title()%></option>
				<c:forEach items="${AccountTitle.values()}" var="accountTitle">
					<option value="${accountTitle}">${accountTitle.str}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="form-group">
			<label class="control-label">Date:</label>
			<input type="date" name="date" class="form-control" value="<%=je.getDate() %>" required>
		</div>
		
		<div class="form-group">
			<label class="control-label">Debit:</label>
			<input type="number" step=".0001" name="debit" class="form-control" value="<%=je.getDebit() %>" required>
		</div>
		
		<div class="form-group">
			<label class="control-label">Credit:</label>
			<input type="number" step=".0001" name="credit" class="form-control" value="<%=je.getCredit()%>" required>
		</div>
			<input type="hidden" name="creator_id" value="<%=je.getCreator_id()%>">
	</div>
	
	<div class="modal-footer">
		<button type="submit" class="btn btn-primary">Edit</button>
		<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	</div>
	
</form>