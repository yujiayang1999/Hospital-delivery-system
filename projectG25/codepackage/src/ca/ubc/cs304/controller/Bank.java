package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.Distributor;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.TerminalTransactions;

/**
 * This is the main controller class that will orchestrate everything.
 */
public class Bank implements LoginWindowDelegate, TerminalTransactionsDelegate {

	private DatabaseConnectionHandler dbHandler = null;
	private LoginWindow loginWindow = null;
	public Distributor dis;

	public Bank() {
		dbHandler = new DatabaseConnectionHandler();
	}
	
	private void start() {
		loginWindow = new LoginWindow();
		loginWindow.showFrame(this);
	}
	
	/**
	 * LoginWindowDelegate Implementation
	 * 
     * connects to Oracle database with supplied username and password
     */ 
	public void login(String username, String password) {
		boolean didConnect = dbHandler.login(username, password);

		if (didConnect) {
			// Once connected, remove login window and start text transaction flow


			loginWindow.dispose();





			//   gui.pack();
			//dis.setTitle("Student to do list");

			TerminalTransactions transaction = new TerminalTransactions();
			transaction.setupDatabase(this);
			transaction.showMainMenu(this);
		} else {
			loginWindow.handleLoginFailed();

			if (loginWindow.hasReachedMaxLoginAttempts()) {
				loginWindow.dispose();
				System.out.println("You have exceeded your number of allowed attempts");
				System.exit(-1);
			}
		}
	}
	
	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Insert a branch with the given info
	 */
    public void insertDrug(int id, String ingredent, String detail) {
    	dbHandler.insertBranch( id, ingredent,detail);
    }

	@Override
	public void showDrugcolumn(String cond) {
		String[] models = dbHandler.selectcolumn(cond);

		for (int i = 0; i < models.length; i++) {
			String model = models[i];

				System.out.printf("%-10.10s", model);

			System.out.println();
		}
	}



	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Delete branch with given branch ID.
	 */ 
    public void deleteDrug(int branchId) {
    	dbHandler.deleteDrug(branchId);
    }
    
    /**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Update the branch name for a specific ID
	 */

    public void updateBranch(int branchId, String name,String detail) {
    	dbHandler.updateBranch(branchId, name, detail);
    }
	@Override
	public void nested() {

		Maxid[] models = dbHandler.nested();

		for (int i = 0; i < models.length; i++) {
			Maxid model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getMaxid());
			System.out.println();
		}
	}

	@Override
	public void handlegroupby(String cond) {

		Maxid[] models = dbHandler.groupby(cond);

		for (int i = 0; i < models.length; i++) {
			Maxid model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getMaxid());
			System.out.println();
		}
	}

	@Override
	public void division() {

		Manager[] models = dbHandler.getdivision();

		for (int i = 0; i < models.length; i++) {
			Manager model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getManager_id());
			System.out.printf("%-40.40s", model.getMedical_id());
			System.out.printf("%-50.50s", model.getPhone_num());



			System.out.println();
		}
	}

	@Override
	public void handlejoint() {
		Drugmanager[] models = dbHandler.getjointinf();

		for (int i = 0; i < models.length; i++) {
			Drugmanager model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getMedical_id());
			System.out.printf("%-20.20s", model.getIngredient());
			System.out.printf("%-30.30s", model.getStorage_detail());
			System.out.printf("%-40.40s", model.getManager_id());
			System.out.printf("%-50.50s", model.getPhone_num());



			System.out.println();
		}
	}

	@Override
	public void handlecount() {
		int a = dbHandler.getcount();
		System.out.print(a);
	}

	/**
	 * TermainalTransactionsDelegate Implementation
	 * 
	 * Displays information about varies bank branches.
	 */
    public void showBranch() {
    	DrugModel[] models = dbHandler.getBranchInfo();
    	
    	for (int i = 0; i < models.length; i++) {
    		DrugModel model = models[i];
    		
    		// simplified output formatting; truncation may occur
    		System.out.printf("%-10.10s", model.getMedical_id());
    		System.out.printf("%-20.20s", model.getIngredient());
    		System.out.printf("%-30.30s", model.getStorage_detail());

    		
    		System.out.println();
    	}
    }

	@Override
	public void showdrugingre() {
		DrugModel[] models = dbHandler.getBranchInfo();

		for (int i = 0; i < models.length; i++) {
			DrugModel model = models[i];
			System.out.printf("%-10.10s", model.getMedical_id());
			// simplified output formatting; truncation may occur


			System.out.println();
		}
	}

	public void showDrugwithcon(String cond) {
		DrugModel[] models = dbHandler.getBranchInfowithcond(cond);
		for (int i = 0; i < models.length; i++) {
			DrugModel model = models[i];

			// simplified output formatting; truncation may occur
			System.out.printf("%-10.10s", model.getMedical_id());
			System.out.printf("%-20.20s", model.getIngredient());
			System.out.printf("%-30.30s", model.getStorage_detail());


			System.out.println();
		}
	}

	/**
	 * TerminalTransactionsDelegate Implementation
	 * 
     * The TerminalTransaction instance tells us that it is done with what it's 
     * doing so we are cleaning up the connection since it's no longer needed.
     */ 
    public void terminalTransactionsFinished() {
    	dbHandler.close();
    	dbHandler = null;
    	
    	System.exit(0);
    }
    
    /**
	 * TerminalTransactionsDelegate Implementation
	 * 
     * The TerminalTransaction instance tells us that the user is fine with dropping any existing table
     * called branch and creating a new one for this project to use
     */ 
	public void databaseSetup() {
		dbHandler.databaseSetup();;
		
	}
    
	/**
	 * Main method called at launch time
	 */
	public static void main(String args[]) {
		Bank bank = new Bank();
		bank.start();
	}
}
