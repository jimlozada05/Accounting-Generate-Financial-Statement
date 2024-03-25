<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="table.*,connect.*,dao.*,util.*,java.text.*,java.sql.*,java.util.*" %>
<%

	int journal_id=Integer.parseInt(request.getParameter("journal_id"));
	String type="finish";
	
	int status=JournalDAO.editFinish(journal_id, type);
	int creator_id=Integer.parseInt(request.getParameter("creator_id"));
	
	List<GeneralLedger> List = GeneralLedgerDAO.getARecordByJournal(Integer.parseInt(request.getParameter("journal_id")));
	request.setAttribute("List", List);
	
	double expense = 0;
	double revenue = 0;
	double networth = 0;
	double capital = 0;
	double drawing = 0;
	double end_capital = 0;
	double asset = 0;
	double liability = 0;
	double total_asset = 0;
	double total_liability = 0;
	double acc_dep = 0;
	double operating_activity = 0;
	double current_asset = 0;
	double current_liability = 0;
	double investing_activity = 0;
	double ia_purchased = 0;
	double ia_sold = 0;
	double financing_activity = 0;
	double cash = 0;
	double cash_flow = 0;
	
	
	ExpenseTitle[] expenseTitle = ExpenseTitle.values();
	RevenueTitle[] revenueTitle = RevenueTitle.values();
	AssetTitle[] assetTitle = AssetTitle.values();
	LiabilityTitle[] liabilityTitle = LiabilityTitle.values();
	CurrentAssetTitle[] currentAssetTitle = CurrentAssetTitle.values();
	CurrentLiabilityTitle[] currentLiabilityTitle = CurrentLiabilityTitle.values();
	
	for(GeneralLedger list: List){
		for(int i = 0; i < expenseTitle.length; i++){
			if(list.getAccount_title().toString() == expenseTitle[i].toString()){
				expense = expense + Math.abs(list.getTotal()); 
			}
		}
		
		for(int i = 0; i < revenueTitle.length; i++){
			if(list.getAccount_title().toString() == revenueTitle[i].toString()){
				revenue = revenue + Math.abs(list.getTotal()); 
			}
		}
		
		if(list.getAccount_title() == AccountTitle.CAPITAL){
			capital = capital + Math.abs(list.getTotal()); 
		}
		
		if(list.getAccount_title() == AccountTitle.DRAWING){
			drawing = drawing + Math.abs(list.getTotal()); 
		}
		
		for(int i = 0; i < assetTitle.length; i++){
			if(list.getAccount_title().toString() == assetTitle[i].toString()){
				asset = asset + Math.abs(list.getTotal()); 
			}
		}
		
		for(int i = 0; i < liabilityTitle.length; i++){
			if(list.getAccount_title().toString() == liabilityTitle[i].toString()){
				liability = liability + Math.abs(list.getTotal()); 
			}
		}
		
		if(list.getAccount_title() == AccountTitle.ACCUMULATED_DEPRECIATION){
			acc_dep = acc_dep + Math.abs(list.getTotal()); 
		}
		
		for(int i = 0; i < currentAssetTitle.length; i++){
			if(list.getAccount_title().toString() == currentAssetTitle[i].toString()){
				current_asset = current_asset + Math.abs(list.getTotal()); 
			}
		}
		
		for(int i = 0; i < currentLiabilityTitle.length; i++){
			if(list.getAccount_title().toString() == currentLiabilityTitle[i].toString()){
				current_liability = current_liability + Math.abs(list.getTotal()); 
			}
		}
		
		if(list.getAccount_title() == AccountTitle.CASH){
			cash = cash + Math.abs(list.getTotal()); 
		}
		
	}
	
	List<JournalEntry> entries = JournalEntryDAO.getAllRecordWithCash(Integer.parseInt(request.getParameter("journal_id")));
	request.setAttribute("entries", entries);
	for(JournalEntry entry: entries){
		
		for(int i = 0; i < assetTitle.length; i++){
			if(entry.getAccount_title().toString() == assetTitle[i].toString() && (entry.getAccount_title() != AccountTitle.CASH) && (entry.getAccount_title().toString() != currentAssetTitle[i].toString()) ){
				ia_purchased = ia_purchased + (Math.abs(entry.getDebit()) * -1); 
			}
		}
	}
	
	
	
	
	networth = revenue - expense;	
	end_capital = capital + networth - drawing;
	total_asset = asset - (acc_dep * 2);
	total_liability = liability + end_capital;
	operating_activity = (networth + acc_dep) + (current_liability - current_asset);
	investing_activity = ia_purchased + ia_sold;
	financing_activity = capital - drawing;
	cash_flow = operating_activity + investing_activity + financing_activity;
	
	
	
	status=TrialBalanceDAO.insertTotalTrialBalance(journal_id, creator_id);
	
	status=IncomeStatementDAO.addIncomeStatement(journal_id, networth, creator_id);
	
	status=OwnerEquityDAO.addOwnerEquity(journal_id, end_capital, creator_id);
	
	status=FinancialPositionDAO.addFinancialPosition(journal_id, total_asset, total_liability, creator_id);
	
	status=OperatingActivityDAO.addOperatingActivity(journal_id, operating_activity, creator_id);
	
	status=InvestingActivityDAO.addInvestingActivity(journal_id, investing_activity, creator_id);
	
	status=FinancingActivityDAO.addFinancingActivity(journal_id, financing_activity, creator_id);
	
	status=CashFlowDAO.addCashFlow(journal_id, cash, cash_flow, creator_id);
	
	if(status > 0)	{
		response.sendRedirect("../generaljournalhome.jsp?");
	}
	
	
	

%>