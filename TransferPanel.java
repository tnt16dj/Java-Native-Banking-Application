import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;

//Implementing the Transfer Functionality of Tab 4
class TransferPanel extends JPanel implements ListSelectionListener, ActionListener
{
	private JTextField selectedFromTextField;
	private JTextField selectedToTextField;
    private JList accountList, accountList2;
    private int CustomerID;
    private JTextField TransferAmt;
    private String selectedFromAccount;
    private String selectedToAccount;
    private TransferControl TrnsfCtrl;

    public TransferPanel(int CustID)
      {
			CustomerID = CustID;
			//userAcct = Acct;

			selectedFromTextField = new JTextField(14);
			selectedToTextField = new JTextField(14);
			JLabel FromtextFieldLabel = new JLabel("From Account Selected:");
			JLabel TotextFieldLabel = new JLabel("To Account Selected:");

			//initializing a list
			accountList = new JList( new String [] {"Checking", "Savings"});
			accountList2 = new JList( new String [] {"Checking", "Savings"});
			accountList.setFixedCellWidth(150);
			accountList2.setFixedCellWidth(150);

            accountList.setVisibleRowCount(2); //set the visible rows
            accountList2.setVisibleRowCount(2);
	        accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        accountList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JLabel listLabelFrom = new JLabel("Select From Account:"); //Account to transfer from.
	        JLabel listLabelTo = new JLabel("Select To Account:");   //Account to transfer to.
	        JPanel FromPanel = new JPanel();
	        JPanel ToPanel = new JPanel();

	        //declare and initialize a SCROLL PANE
	        JScrollPane listScroll = new JScrollPane(accountList);
	        JScrollPane listScroll2 = new JScrollPane(accountList2);
	        FromPanel.add(listScroll); //add the scroll pane to a panel
	        ToPanel.add(listScroll2);

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
			add(listLabelFrom, gbc, 0, 0, 1, 1);

			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
			add(FromPanel, gbc, 1, 0, 1, 1);
	        accountList.addListSelectionListener(this); //register an event listener

	        gbc.fill = GridBagConstraints.NONE;
	        gbc.weightx = 100;
	        gbc.weighty = 100;
	        add(FromtextFieldLabel, gbc, 0, 1, 1, 1);

	        gbc.fill = GridBagConstraints.NONE;
	        gbc.weightx = 100;
	        gbc.weighty = 100;
	        add(selectedFromTextField, gbc, 1, 1, 1, 1);

	        gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
			add(listLabelTo, gbc, 0, 2, 1, 1);

			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
			add(ToPanel, gbc, 1, 2, 1, 1);
	        accountList2.addListSelectionListener(this); //register an event listener

	        gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
			add(TotextFieldLabel, gbc, 0, 3, 1, 1);

			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
	        add(selectedToTextField, gbc, 1, 3, 1, 1);

	        JLabel TransferAmtLabel = new JLabel("Amount to Transfer: ");
	        gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(TransferAmtLabel, gbc, 0, 4, 1, 1);

            TransferAmt = new JTextField(14);
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(TransferAmt, gbc, 1, 4, 1, 1);

			JButton TransferButton = new JButton("Complete Transfer");
			TransferButton.addActionListener(this);
			gbc.fill = GridBagConstraints.NONE;
			gbc.weightx = 100;
			gbc.weighty = 100;
            add(TransferButton, gbc, 1, 5, 1, 1);

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

      public void valueChanged(ListSelectionEvent evt)   //added logic to determine which list is generating the event and catch it.
      {
         JList source = (JList)evt.getSource();
         if(source == accountList)
         {
         	selectedFromAccount = (String)source.getSelectedValue();
         	selectedFromTextField.setText(selectedFromAccount);
         }
         else if(source == accountList2)
         {
         	selectedToAccount = (String)source.getSelectedValue();
         	selectedToTextField.setText(selectedToAccount);
      	 }
	 }

      public void actionPerformed(ActionEvent evt)  //event handling
	  {
		//Object source = evt.getSource(); //get who generates this event
		String arg = evt.getActionCommand();

	  	if (arg.equals("Complete Transfer")) { //determine which button is clicked
	  	//System.out.println("Name: "+arg);
	  	String userTransfer = TransferAmt.getText();
	  	float transfer = new Float(userTransfer);

	  	if((transfer > 0) && (selectedFromAccount != selectedToAccount))
	  	{
			TrnsfCtrl = new TransferControl(CustomerID, selectedFromAccount, selectedToAccount, transfer);
		}
		else if(transfer <= 0)
		{
			JOptionPane.showMessageDialog(null, "Amount must be greater than $0.00, your transaction has not been processed. Please try again!", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "From Account and To Account must be different before you can make a transfer.  Please try again!", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
	  	}
	}
}