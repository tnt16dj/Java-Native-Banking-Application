import java.lang.*; //including Java packages used by this program
import java.awt.*;
import javax.swing.*;

class TransactionConfirmationBO{

	private String AccountType;
	private String FromAccountType;
	private String ToAccountType;
	private String Action;
	private float AccountBalance;

	public TransactionConfirmationBO(String AcctType, String Act, float AcctBal)
	{
		AccountType = AcctType;
		Action = Act;
		AccountBalance = AcctBal;

		if(Action == "Deposit")
		{
			if(AccountBalance >= 0)
			{
				String confirmMSG = "Deposit Successful! Your "+AccountType+" Account balance is now "+AccountBalance+".";
				JOptionPane.showMessageDialog(null, confirmMSG, "Confirmation", JOptionPane.INFORMATION_MESSAGE); //implement Deposit to Acct
			}
			else if(AccountBalance <0)
			{
				JOptionPane.showMessageDialog(null, "The Deposit could not be made.  Please contact customer support.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(Action == "Withdraw")
		{
			if(AccountBalance >= 0)
			{
				String confirmMSG = "Withdraw Successful! Your "+AccountType+" Account balance is now "+AccountBalance+".";
				JOptionPane.showMessageDialog(null, confirmMSG, "Confirmation", JOptionPane.INFORMATION_MESSAGE); //implement Deposit to Acct
			}
			else if(AccountBalance < 0)
			{
				JOptionPane.showMessageDialog(null, "The Withdraw could not be made.  Please contact customer support.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}


	public TransactionConfirmationBO(String FromAcct, String ToAcct, String Act, float AcctBal)
	{
		FromAccountType = FromAcct;
		ToAccountType = ToAcct;
		Action = Act;
		AccountBalance = AcctBal;

		if(AccountBalance >= 0)
		{
			String confirmMSG = "Transfer from "+FromAccountType+" to "+ToAccountType+" Successful! Your "+ToAccountType+" Account balance is now "+AccountBalance+".";
			JOptionPane.showMessageDialog(null, confirmMSG, "Confirmation", JOptionPane.INFORMATION_MESSAGE); //implement Deposit to Acct
		}
		else if(AccountBalance < 0)
		{
			JOptionPane.showMessageDialog(null, "The Transfer could not be made.  Please contact customer support.", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}