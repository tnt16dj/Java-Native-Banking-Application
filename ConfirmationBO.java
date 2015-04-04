import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;

//Implementation of the Confirmation Boundary Object
class ConfirmationBO extends JFrame
{
   private ConfirmTabPanel Tab_BO;

   public ConfirmationBO(ConfirmTabPanel TabPnl)
   {
      setTitle("Your Account");
      setSize(450, 275);
      //get screen size and set the location of the frame
      Toolkit tk = Toolkit.getDefaultToolkit();
      Dimension d = tk.getScreenSize();
      int screenHeight = d.height;
      int screenWidth = d.width;
      setLocation( screenWidth / 2 - 180, screenHeight / 4);

      addWindowListener (new WindowAdapter()  //handle window closing event
         {  public void windowClosing (WindowEvent e)
            { System.exit(0);
            }
         });

      Tab_BO  = TabPnl;
	  Container contentPane = getContentPane(); //add a panel to a frame
	  contentPane.add(Tab_BO);
	  show();
	}
}

//Implementing a Tabbed Panel.
class ConfirmTabPanel extends JPanel
{
   private JTabbedPane tabbedPane;
   private JPanel tabPanel_1, tabPanel_2, tabPanel_3, tabPanel_4;

   public ConfirmTabPanel(BalancePanel BalPnl, DepositPanel DepPnl, WithdrawPanel WthPnl, TransferPanel TrnPnl)
   {
	  tabbedPane = new JTabbedPane(); //initialize a JTabbedPane object
	  tabbedPane.setTabPlacement(JTabbedPane.LEFT);

      tabPanel_1 = BalPnl;
      tabPanel_2 = DepPnl;
      tabPanel_3 = WthPnl;
      tabPanel_4 = TrnPnl;

      tabbedPane.addTab("Balances", tabPanel_1); //add GUI components to Tabbed Pane
      tabbedPane.addTab("Deposit", tabPanel_2);
      tabbedPane.addTab("Withdraw", tabPanel_3);
      tabbedPane.addTab("Transfer", tabPanel_4);

      tabbedPane.setSelectedIndex(0);
	  tabbedPane.setPreferredSize(new Dimension(425,225));  //set the Dimension for the Tab Pane !!!!

      add(tabbedPane);
   }
}