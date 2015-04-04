import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;


//Implementing the Deposit Functionality of Tab 2
class DepositPanel extends JPanel implements ListSelectionListener, ActionListener
{
	private JTextField selectedAccountField;
	private int CustomerID;
    private JList accountList;
    private JTextField DepositAmt;
    private String selectedAccount;
    private DepositControl DepCtrl;

    public DepositPanel(int CustID)
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

	        JLabel DepositAmtLabel = new JLabel("Amount to Deposit: ");
	        gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(DepositAmtLabel, gbc, 0, 2, 1, 1);

            DepositAmt = new JTextField(14);
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(DepositAmt, gbc, 1, 2, 1, 1);

			JButton DepositButton= new JButton("Complete Deposit");
			DepositButton.addActionListener(this);
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(DepositButton, gbc, 1, 3, 1, 1);

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

	  	if (arg.equals("Complete Deposit")) { //determine which button is clicked
	  	//System.out.println("Name: "+arg);
	  	String userDeposit = DepositAmt.getText();
	  	float deposit = new Float(userDeposit);

	  	if(deposit > 0)
	  	{
	  		DepCtrl = new DepositControl(CustomerID, selectedAccount, deposit);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Amount must be greater than $0.00, your transaction has not been processed. Please try again!", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	  }
	}
}
