package ca.ubc.cs304.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ca.ubc.cs304.model.*;
import ca.ubc.cs304.ui.Distributor;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * This class handles all database related transactions
 */
public class DatabaseConnectionHandler {
	// Use this version of the ORACLE_URL if you are running the code off of the server
//	private static final String ORACLE_URL = "jdbc:oracle:thin:@dbhost.students.cs.ubc.ca:1522:stu";
	// Use this version of the ORACLE_URL if you are tunneling into the undergrad servers
	private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
	private static final String EXCEPTION_TAG = "[EXCEPTION]";
	private static final String WARNING_TAG = "[WARNING]";
	
	public Connection connection = null;
	
	public DatabaseConnectionHandler() {
		try {
			// Load the Oracle JDBC driver
			// Note that the path could change for new drivers
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}


	public void deleteDrug(int branchId) {
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM drug WHERE medical_id = ?");
			ps.setInt(1, branchId);
			
			int rowCount = ps.executeUpdate();
			if (rowCount == 0) {
				System.out.println(WARNING_TAG + " Drug " + branchId + " does not e1" +
						"exist!");
			}
			
			connection.commit();
	
			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	public String[] selectcolumn(String cond){
		ArrayList<String> result = new ArrayList<String>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT "+ cond +" FROM drug");

			while(rs.next()) {
				String model = rs.getString(cond);
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new String[result.size()]);
	}
	public Maxid[] groupby(String cond){
		ArrayList<Maxid> result = new ArrayList<Maxid>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select max(m.medical_id) maxid from manager m where m.medical_id in (select d.medical_id from drug d where d.medical_id = m.medical_id) group by "+ cond);


			while(rs.next()) {
				Maxid model = new Maxid(rs.getInt("maxid"));
				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Maxid [result.size()]);
	}




	
	public void insertBranch(int id, String ingredent, String detail) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Drug VALUES (?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, ingredent);
			ps.setString(3, detail);

//			if (model.getPhoneNumber() == 0) {
//				ps.setNull(5, java.sql.Types.INTEGER);
//			} else {
//				ps.setInt(5, model.getPhoneNumber());
//			}

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	public void insertDis(int id, Connection connection) {
		try {
//			try {
//				Class.forName("oracle.jdbc.driver.OracleDriver");
//				Connection con = DriverManager.
//						getConnection("jdbc:oracle:thin:@localhost:1522:stu"
//								,"ora_yangyuji","a82719170");
//
//				Statement stmt = con.createStatement();
//				System.out.println("Created DB Connection....");
//			} catch (ClassNotFoundException eee) {
//				// TODO Auto-generated catch block
//				eee.printStackTrace();
//			} catch (SQLException ee) {
//				// TODO Auto-generated catch block
//				ee.printStackTrace();
//			}

			PreparedStatement ps = connection.prepareStatement("INSERT INTO Distributor VALUES ?");
			ps.setInt(1, id);

			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	public void insertorder(int order_num,int order_date) {
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO place_order_r3 VALUES (?,?)");
			ps.setInt(1, order_num);
			ps.setInt(2, order_date);


			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			//System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}
	public void removeorder(int order_num){
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM place_order_r3 WHERE order_num =" + order_num);
			//ps.setInt(1, order_num);


			ps.executeUpdate();
			connection.commit();

			ps.close();
		} catch (SQLException e) {
			//System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}
	}

	public DrugModel[] getBranchInfo() {
		ArrayList<DrugModel> result = new ArrayList<DrugModel>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM drug");
		
//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}
			
			while(rs.next()) {
				DrugModel model = new DrugModel(rs.getInt("medical_id"),
													rs.getString("ingredient"),
													rs.getString("storage_detail"));

				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}	
		
		return result.toArray(new DrugModel[result.size()]);
	}
	public Manager[] getdivision(){

		ArrayList<Manager> result = new ArrayList<Manager>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Manager m1 WHERE NOT EXISTS((SELECT d.medical_id FROM drug d) MINUS (select m2.medical_id FROM Manager m2 where m1.manager_id = m2.manager_id))");

//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}

			while(rs.next()) {
				Manager model = new Manager(rs.getInt("manager_id"),
						rs.getInt("medical_id"),
						rs.getString("phone_num"));

				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Manager[result.size()]);
	}


//		ArrayList<DrugModel> result = new ArrayList<DrugModel>();
//
//		try {
//			Statement stmt = connection.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM drug d WHERE EXISTS " +
//					"(SELECT * FROM manager m WHERE d.medical_id = m.medical_id)");
//
//			while(rs.next()) {
//				DrugModel model = new DrugModel(rs.getInt("medical_id"),
//						rs.getString("ingredient"),
//						rs.getString("storage_detail"));
//
//				result.add(model);
//			}
//
//			rs.close();
//			stmt.close();
//		} catch (SQLException e) {
//			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
//		}
//
//		return result.toArray(new DrugModel[result.size()]);
//	}
public Maxid[] nested(){
		ArrayList<Maxid> result = new ArrayList<Maxid>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select max(m.medical_id) maxid from manager m where m.medical_id in (select d.medical_id from drug d where d.medical_id = m.medical_id) group by manager_id");


			while(rs.next()) {
					Maxid model = new Maxid(rs.getInt("maxid"));
					result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Maxid [result.size()]);
	}






	public DrugModel[] getBranchInfowithcond(String cond) {
		ArrayList<DrugModel> result = new ArrayList<DrugModel>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM drug WHERE "+ cond);

//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}

			while(rs.next()) {
				DrugModel model = new DrugModel(rs.getInt("medical_id"),
						rs.getString("ingredient"),
						rs.getString("storage_detail"));

				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new DrugModel[result.size()]);
	}
	public Drugmanager[] getjointinf() {
		ArrayList<Drugmanager> result = new ArrayList<Drugmanager>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM drug d, manager m WHERE m.medical_id = d.medical_id");
			//System.out.println(rs.wasNull());
			//manager m WHERE d.medical_id=m.medical_id

//    		// get info on ResultSet
//    		ResultSetMetaData rsmd = rs.getMetaData();
//
//    		System.out.println(" ");
//
//    		// display column names;
//    		for (int i = 0; i < rsmd.getColumnCount(); i++) {
//    			// get column name and print it
//    			System.out.printf("%-15s", rsmd.getColumnName(i + 1));
//    		}

			while(rs.next()) {

				Drugmanager model = new Drugmanager(rs.getInt("medical_id"),
						rs.getString("ingredient"),
						rs.getString("storage_detail"),
						rs.getInt("manager_id"),
						rs.getString("phone_num")
				);

				result.add(model);

			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Drugmanager[result.size()]);
	}
	public void updateBranch(int id, String name,String detail) {
		try {
		  PreparedStatement ps = connection.prepareStatement("update drug set " + name + " = ? where medical_id = ?");
		  ps.setInt(2,id);
		  ps.setString(1,detail);
//		  ps.setString(1,name);
				  //("UPDATE drug SET " + name + " = " + detail  +" WHERE medical_id = "+id);

		
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println(WARNING_TAG + " drug " + id + " does not exist!");
		  }
	
		  connection.commit();
		  
		  ps.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			rollbackConnection();
		}	
	}


	public int getcount() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM drug d");
			int h = -1;
			while (rs.next()) {
				h = rs.getInt(1);
			}
			return h;

		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return -1;
		}

	}


	public boolean login(String username, String password) {
		try {
			if (connection != null) {
				connection.close();
			}
	
			connection = DriverManager.getConnection(ORACLE_URL, username, password);
			connection.setAutoCommit(false);
	
			System.out.println("\nConnected to Oracle!");
			//main(new String[0]);


			return true;
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
			return false;
		}
	}

//	public static void main(String[] args) {
//		Distributor dis = new Distributor(this);
//		dis.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		dis.setSize(1000, 1000);
//		dis.setVisible(true);
//		dis.setTitle("distributor");
//	}

	private void rollbackConnection() {
		try  {
			connection.rollback();	
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
	
	public void databaseSetup() {
		dropBranchTableIfExists();
		
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("CREATE TABLE branch (branch_id integer PRIMARY KEY, branch_name varchar2(20) not null, branch_addr varchar2(50), branch_city varchar2(20) not null, branch_phone integer)");
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
		
//		BranchModel branch1 = new BranchModel("123 Charming Ave", "Vancouver", 1, "First Branch", 1234567);
//		insertBranch(branch1);
//
//		BranchModel branch2 = new BranchModel("123 Coco Ave", "Vancouver", 2, "Second Branch", 1234568);
//		insertBranch(branch2);
	}

	public Order[] getorder(){
		ArrayList<Order> result = new ArrayList<Order>();

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM place_order_r3 ");

			while(rs.next()) {
				Order model = new Order(rs.getInt("order_num"),
						rs.getInt("order_date"));

				result.add(model);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}

		return result.toArray(new Order[result.size()]);
	}


	private void dropBranchTableIfExists() {
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select table_name from user_tables");
			
			while(rs.next()) {
				if(rs.getString(1).toLowerCase().equals("branch")) {
					stmt.execute("DROP TABLE branch");
					break;
				}
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(EXCEPTION_TAG + " " + e.getMessage());
		}
	}
}
