package ca.ubc.cs304.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.BranchModel;
import ca.ubc.cs304.model.DrugModel;

/**
 * The class is only responsible for handling terminal text inputs. 
 */
public class TerminalTransactions {
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	private static final int INVALID_INPUT = Integer.MIN_VALUE;
	private static final int EMPTY_INPUT = 0;
	
	private BufferedReader bufferedReader = null;
	private TerminalTransactionsDelegate delegate = null;

	public TerminalTransactions() {
	}
	
	/**
	 * Sets up the database to have a branch table with two tuples so we can insert/update/delete from it.
	 * Refer to the databaseSetup.sql file to determine what tuples are going to be in the table.
	 */
	public void setupDatabase(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;
		
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;
		
		while(choice != 1 && choice != 2) {
			System.out.println("If you have a table called dug in your database it will be dropped and a new Branch table will be created.\nIf you want to proceed, enter 1; if you want to quit, enter 2.");
			
			choice = readInteger(false);
			
			if (choice != INVALID_INPUT) {
				switch (choice) {
				case 1:  
					delegate.databaseSetup(); 
					break;
				case 2:  
					handleQuitOption();
					break;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.\n");
					break;
				}
			}
		}
	}

	/**
	 * Displays simple text interface
	 */ 
	public void showMainMenu(TerminalTransactionsDelegate delegate) {
		this.delegate = delegate;
		
	    bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int choice = INVALID_INPUT;
		
		while (choice != 5) {
			System.out.println();
			System.out.println("1. insertion:Insert drug");
			System.out.println("2. deletion:Delete drug");
			System.out.println("3. update:Update drug inf");
			System.out.println("4. Show drug details");
			System.out.println("5. Selection:Show drug with conditions ");
			System.out.println("6. joint drug with manager");
			System.out.println("7. Aggreation fun:count # of drugs");
			System.out.println("8. Retrieve max medical_id managed by each manager");
			System.out.println("9. Division: find the manager holds all drugs");
			System.out.println("10. Show drug medical_id");
			System.out.println("11. projection: select the text #column from drug");
			System.out.println("12. joint: joint drug and manager with conditions");
			System.out.println("13. nested group by: Retrieve max medical_id managed by each manager with conditions  ");
			System.out.println("14. Quit");
			System.out.print("Please choose one of the above 14 options: ");

			choice = readInteger(false);

			System.out.println(" ");

			if (choice != INVALID_INPUT) {
				switch (choice) {
				case 1:  
					handleInsertOption(); 
					break;
				case 2:  
					handleDeleteOption(); 
					break;
				case 3: 
					handleUpdateOption();
					break;
				case 4:  
					delegate.showBranch(); 
					break;
				case 5:
					handleselectionOption();
					break;
				case 6:
					delegate.handlejoint();
					break;
				case 7:
					delegate.handlecount();
					break;
				case 8:
					delegate.nested();
					break;
				case 9:
					delegate.division();
					break;
				case 10:
					delegate.showdrugingre();
					break;
				case 11:
						handleprojection();
						break;
				case 12:
						handlejoint();
						break;
				case 13:
						handlegroupby();
						break;
				case 14:
					handleQuitOption();
					break;
				default:
					System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
					break;
				}
			}
		}		
	}
	private void handleselectionOption() {
		String tablename =null;
		while(tablename == null || tablename.length()<=0){
			System.out.print("Please enter the condition you wish to select");
			tablename = readLine().trim();
		}
		delegate.showDrugwithcon(tablename);

		};
	private void handleprojection() {
		String tablename =null;
		while(tablename == null || tablename.length()<=0){
			System.out.print("Please enter the column you wish to select from drug");
			tablename = readLine().trim();
		}
		delegate.showDrugcolumn(tablename);

	};
	public void handlejoint(){
		String tablename =null;
		while(tablename == null || tablename.length()<=0){
			System.out.print("Please enter the condition you wish to joint from drug and manager");
			tablename = readLine().trim();
		}
		delegate.handlejoint();
	}
	public void handlegroupby(){
		String tablename =null;
		while(tablename == null || tablename.length()<=0){
			System.out.print("Please enter the condition you wish to group by");
			tablename = readLine().trim();
		}
		delegate.handlegroupby(tablename);
	}



	private void handleDeleteOption() {
		int branchId = INVALID_INPUT;
		while (branchId == INVALID_INPUT) {
			System.out.print("Please enter the drug ID you wish to delete: ");
			branchId = readInteger(false);
			if (branchId != INVALID_INPUT) {
				delegate.deleteDrug(branchId);
			}
		}
	}
	
	private void handleInsertOption() {
		int id = INVALID_INPUT;
		while (id == INVALID_INPUT) {
			System.out.print("Please enter the drug ID you wish to insert: ");
			id = readInteger(false);
		}
		
		String strorage_detail = null;
		while (strorage_detail == null || strorage_detail.length() <= 0) {
			System.out.print("Please enter the drug storage_detail you wish to insert: ");
			strorage_detail = readLine().trim();
		}
		
		// branch address is allowed to be null so we don't need to repeatedly ask for the address
		System.out.print("Please enter the drug ingredient you wish to insert: ");
		String ingredient = readLine().trim();
		if (ingredient.length() == 0) {
			ingredient = null;
		}
//
//		String city = null;
//		while (city == null || city.length() <= 0) {
//			System.out.print("Please enter the branch city you wish to insert: ");
//			city = readLine().trim();
//		}
//
//		int phoneNumber = INVALID_INPUT;
//		while (phoneNumber == INVALID_INPUT) {
//			System.out.print("Please enter the branch phone number you wish to insert: ");
//			phoneNumber = readInteger(true);
//		}
		
	//	BranchModel model = new BranchModel(address,
											//city,
											//id,
											//name,
											//phoneNumber);
	//	DrugModel model = new DrugModel(id,ingredient,strorage_detail,);

	//	delegate.insertBranch(model);
		delegate.insertDrug(id,ingredient,strorage_detail);
	}
	
	private void handleQuitOption() {
		System.out.println("Good Bye!");
		
		if (bufferedReader != null) {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException!");
			}
		}
		
		delegate.terminalTransactionsFinished();
	}
	
	private void handleUpdateOption() {
		int id = INVALID_INPUT;
		while (id == INVALID_INPUT) {
			System.out.print("Please enter the medical ID you wish to update: ");
			id = readInteger(false);
		}
		
		String name = null;
		while (name == null || name.length() <= 0) {
			System.out.print("Please enter the column you wish to update: ");
			name = readLine().trim();
		}
		String detail = null;
		while (detail == null || detail.length() <= 0) {
			System.out.print("Please enter the new inf you wish to update: ");
			detail = readLine().trim();
		}

		delegate.updateBranch(id, name,detail);
	}
	
	private int readInteger(boolean allowEmpty) {
		String line = null;
		int input = INVALID_INPUT;
		try {
			line = bufferedReader.readLine();
			input = Integer.parseInt(line);
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		} catch (NumberFormatException e) {
			if (allowEmpty && line.length() == 0) {
				input = EMPTY_INPUT;
			} else {
				System.out.println(WARNING_TAG + " Your input was not an integer");
			}
		}
		return input;
	}
	
	private String readLine() {
		String result = null;
		try {
			result = bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		return result;
	}
}
