<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="table.*,connect.*,dao.*,util.*,java.text.*" %>
<%
	JournalEntry je = new JournalEntry();
	int journal_id = Integer.parseInt(request.getParameter("journal_id"));
	int entry_id=Integer.parseInt(request.getParameter("entry_id"));
	
	je.setEntry_id(entry_id);
	je.setJournal_id(journal_id);
	je.setAccount_title(AccountTitle.valueOf(request.getParameter("account_title")));
	je.setDebit(Double.parseDouble(request.getParameter("debit")));
	je.setCredit(Double.parseDouble(request.getParameter("credit")));
	je.setDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")).getTime()));
	je.setCreator_id((Integer) session.getAttribute("login_id"));
	
	int old_debit=JournalEntryDAO.getDebitByGeneralJournal(je);
	int old_credit=JournalEntryDAO.getCreditByGeneralJournal(je);
	String old_acc=JournalEntryDAO.getAccountTitleByGeneralJournal(je);
	
	
	JournalEntryDAO.editGeneralJournal(je);
	JournalDAO.reupdateJournal(journal_id);
	

	
	GeneralLedgerDAO.updateEditLedgerByJournalIdAndAccountTitle(journal_id, je.getAccount_title(), je.getCreator_id(), je.getDebit(), je.getCredit(), old_debit, old_credit, old_acc);
	
	response.sendRedirect("../generaljournal.jsp?journal_id=" + journal_id);
%>