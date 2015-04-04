import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;


//Implementing the Balance View of Tab 1
class BalancePanel extends JPanel
{
	private final JTable table;
	private int CustomerID;
	private float[] AcctBalList;
	//private Account userAcct;

	public BalancePanel(int CustID)
	{
		CustomerID = CustID;
		//checkingBalance = Acct.getCheckingBalance();  //Have Control Obj Perform these.
		//savingsBalance = Acct.getSavingsBalance();

		RetrieveBalanceControl getBal = new RetrieveBalanceControl(CustomerID);
		AcctBalList = getBal.setBalances(CustomerID);

		Object[] columnNames = {"Acount Type", "Balance"}; //prepare two arrays for a table
		Object[][] componentNames = {{"Checking",AcctBalList[0]}, {"Savings", AcctBalList[1]}};

		table = new JTable(componentNames, columnNames); //initialize a Table object
		table.setPreferredScrollableViewportSize(new Dimension(335, 185));
		table.setEnabled(false);

	    //Create the scroll pane and add it to the table.
	    JScrollPane scrollPane = new JScrollPane(table);

	    //Add the scroll pane to this window.
	    add(scrollPane);
   }

}