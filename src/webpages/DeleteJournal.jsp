<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="table.*,connect.*,dao.*,util.*,java.text.*" %>
<%
	int journal_id=Integer.parseInt(request.getParameter("journal_id"));
	int entry_id=Integer.parseInt(request.getParameter("entry_id"));
	
	
	JournalEntry je=new JournalEntry();
	je.setAccount_title(AccountTitle.valueOf(request.getParameter("account_title")));
	je.setJournal_id(journal_id);
	je.setEntry_id(entry_id);
	
	int old_debit=JournalEntryDAO.getDebitByGeneralJournal(je);
	int old_credit=JournalEntryDAO.getCreditByGeneralJournal(je);
	String old_acc=JournalEntryDAO.getAccountTitleByGeneralJournal(je);
	
	
	JournalDAO.reupdateJournal(journal_id);
	GeneralLedgerDAO.updateDeleteLedgerByJournalIdAndAccountTitle(journal_id, je.getAccount_title(), old_debit, old_credit, old_acc);
	
	JournalEntryDAO.deleteGeneralJournal(je);
	response.sendRedirect("../generaljournal.jsp?journal_id=" + journal_id);
%>