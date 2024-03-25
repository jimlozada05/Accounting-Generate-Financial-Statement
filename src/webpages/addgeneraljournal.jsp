<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="table.*,connect.*,dao.*,util.*,java.text.*" %>
<%
	JournalEntry je = new JournalEntry();
	int journal_id = Integer.parseInt(request.getParameter("journal_id"));
	je.setCreator_id((Integer) session.getAttribute("login_id"));
	je.setJournal_id(journal_id);
	je.setAccount_title(AccountTitle.valueOf(request.getParameter("account_title")));
	je.setDebit(Double.parseDouble(request.getParameter("debit")));
	je.setCredit(Double.parseDouble(request.getParameter("credit")));
	je.setDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")).getTime()));
	je.setLine_id(Integer.parseInt(request.getParameter("line_id")));
	
	JournalEntryDAO.addGeneralJournal(je);
	JournalDAO.reupdateJournal(journal_id);
	
	GeneralLedgerDAO.updateLedgerByJournalIdAndAccountTitle(journal_id, je.getAccount_title(), je.getDebit(), je.getCredit(), je.getCreator_id());

	je.setAccount_title(AccountTitle.valueOf(request.getParameter("account_title2")));
	je.setDebit(Double.parseDouble(request.getParameter("debit2")));
	je.setCredit(Double.parseDouble(request.getParameter("credit2")));
	
	JournalEntryDAO.addGeneralJournal(je);
	JournalDAO.reupdateJournal(journal_id);
	
	GeneralLedgerDAO.updateLedgerByJournalIdAndAccountTitle(journal_id, je.getAccount_title(), je.getDebit(), je.getCredit(), je.getCreator_id());
	
	response.sendRedirect("../generaljournal.jsp?journal_id=" + journal_id);
	
%>