/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program
import java.awt.*;
import javax.swing.*;

class LoginControl
{
	private String UName, PWord;
	private BalancePanel Bal_BO;
	private DepositPanel Dep_BO;
	private WithdrawPanel Wth_BO;
	private TransferPanel Trn_BO;
	private ConfirmTabPanel Tab_BO;
	private ConfirmationBO Cnf_BO;
	private Account Acct;
	private int CustID;

    public LoginControl(String Username, String Password)
    {
		UName = Username;
		PWord = Password;

		if(PWord.length() < 8) //add in an OR condition for email not in proper format...
		{
			JOptionPane.showMessageDialog(null, "Passwords are a minimum of 8 positions.  Please try again.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			Acct = new Account(UName, PWord);
			CustID = Acct.signIn();

			if (CustID != 0)
			{
				//JOptionPane.showMessageDialog(null, "Login Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
				Bal_BO = new BalancePanel(CustID);
				Dep_BO = new DepositPanel(CustID);
				Wth_BO = new WithdrawPanel(CustID);
				Trn_BO = new TransferPanel(CustID);
				Tab_BO = new ConfirmTabPanel(Bal_BO, Dep_BO, Wth_BO, Trn_BO);
				Cnf_BO = new ConfirmationBO(Tab_BO);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Login Failed.  Please check Username and Password and try again!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
