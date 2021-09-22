package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.BranchModel;

/**
 * This interface uses the delegation design pattern where instead of having
 * the TerminalTransactions class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the 
 * controller class (in this case Bank).
 * 
 * TerminalTransactions calls the methods that we have listed below but 
 * Bank is the actual class that will implement the methods.
 */
public interface TerminalTransactionsDelegate {
	public void databaseSetup();
	
	public void deleteDrug(int medicalId);
	public void insertDrug(int id,String ingredient,String storage_detail);
	public void showBranch();
	public void showDrugwithcon(String cond);
	public void showDrugcolumn(String cond);
	public void updateBranch(int branchId, String name,String detail);
	public void handlejoint();
	public void handlegroupby(String cond);
	public void handlecount();
	public void nested();
	public void division();
	public void showdrugingre();
	
	public void terminalTransactionsFinished();
}
