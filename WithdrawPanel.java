import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;

//Implementing the Withdraw Functionality of Tab 3
class WithdrawPanel extends JPanel implements ListSelectionListener, ActionListener
{
	private JTextField selectedAccountField;
    private JList accountList;
    private int CustomerID;
    private JTextField WithdrawAmt;
    private String selectedAccount;
    private WithdrawControl WthCtrl;


    public WithdrawPanel(int CustID)
      {
		  	CustomerID = CustID;
		  	//userAcct = Acct;

			selectedAccountField = new JTextField(14);
			JLabel textFieldLabel = new JLabel("Account Selected:");

			//initializing a list
			accountList = new JList( new String [] {"Checking", "Savings"});
			accountList.setFixedCellWidth(150);

            accountList.setVisibleRowCount(2); //set the visible rows
	        accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JLabel listLabel = new JLabel("Select an Account:");
	        JPanel p = new JPanel();

	        //declare and initialize a SCROLL PANE
	        JScrollPane listScroll = new JScrollPane(accountList);
	        p.add(listScroll); //add the scroll pane to a panel

	        //declare and initialize a GridBagLayout object
	        GridBagLayout gbl = new GridBagLayout();
	        setLayout(gbl); //let the layout manager be GridBagLayout
	        //declare and initialize a GridBagConstraints object
            GridBagConstraints gbc = new GridBagConstraints();

			Border raised = BorderFactory.createRaisedBevelBorder();
	        setBorder(raised);

       		//apply GridBagConstraints to a GUI component and add it to this panel
	        gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
			add(listLabel, gbc, 0, 0, 1, 1);

			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
			add(p, gbc, 1, 0, 1, 1);
	        accountList.addListSelectionListener(this); //register an event listener

	        gbc.fill = GridBagConstraints.NONE;
	        gbc.weightx = 100;
	        gbc.weighty = 100;
	        add(textFieldLabel, gbc, 0, 1, 1, 1);

	        gbc.fill = GridBagConstraints.NONE;
	        gbc.weightx = 100;
	        gbc.weighty = 100;
	        add(selectedAccountField, gbc, 1, 1, 1, 1);

	        JLabel WithdrawAmtLabel = new JLabel("Amount to Withdraw: ");
	        gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(WithdrawAmtLabel, gbc, 0, 2, 1, 1);

            WithdrawAmt = new JTextField(14);
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(WithdrawAmt, gbc, 1, 2, 1, 1);

			JButton WithdrawButton= new JButton("Complete Withdrawal");
			WithdrawButton.addActionListener(this);
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(WithdrawButton, gbc, 1, 3, 1, 1);

	     }

	     //overload method ADD with GridBagConstraints
	  public void add(Component c, GridBagConstraints gbc, int x, int y, int w, int h)
	  {
	  	gbc.gridx = x;
	  	gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
	    add(c, gbc);
	  }
         //method for ListSelectionEvent handling

      public void valueChanged(ListSelectionEvent evt)
      {
         JList source = (JList)evt.getSource();
         selectedAccount = (String)source.getSelectedValue();
         selectedAccountField.setText(selectedAccount);
      }

      public void actionPerformed(ActionEvent evt)  //event handling
	  {
		//Object source = evt.getSource(); //get who generates this event
		String arg = evt.getActionCommand();

	  	if (arg.equals("Complete Withdrawal")) { //determine which button is clicked
	  	//System.out.println("Name: "+arg);
	  	String userWithdrawal = WithdrawAmt.getText();
	  	float withdrawal = new Float(userWithdrawal);

	  	if(withdrawal > 0)
	  	{
			WthCtrl = new WithdrawControl(CustomerID, selectedAccount, withdrawal);
			//Float newBalance = WthCtrl.setBalance(CustomerID);

			//String confirmMSG = "Your new "+selectedAccount+" Account balance is "+newBalance+".";

	  		//JOptionPane.showMessageDialog(null, confirmMSG, "Confirmation", JOptionPane.INFORMATION_MESSAGE); //implement Withdrawal to Acct
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Amount must be greater than $0.00, your transaction has not been processed. Please try again!", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	  	}
	}
}