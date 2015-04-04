import java.lang.*; //including Java packages used by this program
import java.sql.*;

class Transaction
{
	private int CustomerID;
	private float TransactionAmount;
	private String TransactionType;
	private String AccountType;

	public Transaction(int CustID, String AcctType, String TransType, float TransAmt) {
		CustomerID = CustID;
		AccountType = AcctType;
		TransactionType = TransType;
		TransactionAmount = TransAmt;
	}

	public boolean RecordTransaction()
	{
		if(AccountType == "Checking")
				{
				boolean tranStatus = false;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "INSERT INTO Transactions (AcctNo,TransType,TransAmt,TransDate)"
					                     +"VALUES ((SELECT AcctNo FROM CheckingAccount WHERE AcctNo ="
					                     +"(SELECT CheckingAcct FROM Account WHERE CustID = "+CustomerID+")),"
					                     +"'"+TransactionType+"',"+TransactionAmount+", CURRENT_TIMESTAMP);";
					Stmt.executeUpdate(SQL_Command);

					tranStatus = true;
					Stmt.close();
					ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {   tranStatus = false;
				    System.out.println("SQLException: " + e);
				    while (e != null)
					 {   System.out.println("SQLState: " + e.getSQLState());
						 System.out.println("Message: " + e.getMessage());
						 System.out.println("Vendor: " + e.getErrorCode());
						 e = e.getNextException();
						 System.out.println("");
					 }
				}
				catch (java.lang.Exception e)
			    {     tranStatus = false;
					  System.out.println("Exception: " + e);
					  e.printStackTrace ();
				}
			    return tranStatus;
			}
			else if(AccountType == "Savings")
				{
				boolean tranStatus = false;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "INSERT INTO Transactions (AcctNo,TransType,TransAmt,TransDate)"
					                     +"VALUES ((SELECT AcctNo FROM SavingsAccount WHERE AcctNo ="
					                     +"(SELECT SavingsAcct FROM Account WHERE CustID = "+CustomerID+")),"
					                     +"'"+TransactionType+"',"+TransactionAmount+", CURRENT_TIMESTAMP);";
					Stmt.executeUpdate(SQL_Command);

					tranStatus = true;
					Stmt.close();
					ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {   tranStatus = false;
				    System.out.println("SQLException: " + e);
				    while (e != null)
					 {   System.out.println("SQLState: " + e.getSQLState());
						 System.out.println("Message: " + e.getMessage());
						 System.out.println("Vendor: " + e.getErrorCode());
						 e = e.getNextException();
						 System.out.println("");
					 }
				}
				catch (java.lang.Exception e)
			    {     tranStatus = false;
					  System.out.println("Exception: " + e);
					  e.printStackTrace ();
				}
			    return tranStatus;
			}
			else
			return false;
		}
}