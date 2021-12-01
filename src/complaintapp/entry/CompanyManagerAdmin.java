package complaintapp.entry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import complaintapp.company_manager.AddEmployeeDetails;
import complaintapp.company_manager.DeleteEmployeeDetails;
import complaintapp.company_manager.SeeAllcustomerCareExecutives;
import complaintapp.company_manager.UpdateEmployeeDetails;
import complaintapp.company_manager.ViewComplaintsDateWise;
import complaintapp.customer_care_executive.SeeAllServiceEngineer;
import complaintapp.customer_care_executive.ViewComplaints_ComplaintidWise;
import complaintapp.customer_care_executive.ViewComplaints_CustomeridWise;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class CompanyManagerAdmin extends JFrame implements ActionListener,WindowListener
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyManagerAdmin frame = new CompanyManagerAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CompanyManagerAdmin() 
	{
		//setIconImage(Toolkit.getDefaultToolkit().getImage(CompanyManagerAdmin.class.getResource("/contactapp/images/admin.jpg")));
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("COMPANY MANAGER ADMIN");
		this.addWindowListener(this);    //to handle close button  
		createComponents();
		
	}
	
	
	JMenuItem miaddcontact,mideletecontact,miupdatecontact,mibydate,mibycompid,mibycusid;
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//dispose in place of exit so that child frame closes and parent window remains open
		setBounds(100, 100, 920, 739);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(70, 130, 180), 4));
		menuBar.setBackground(new Color(211, 211, 211));
		menuBar.setForeground(new Color(128, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu contactmenu = new JMenu("Manage Employee");
		contactmenu.setBorder(new LineBorder(Color.BLACK, 5));
		contactmenu.setHorizontalAlignment(SwingConstants.CENTER);
		contactmenu.setOpaque(true);
		contactmenu.setBackground(new Color(211, 211, 211));
		contactmenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		contactmenu.setForeground(new Color(128, 0, 0));
		menuBar.add(contactmenu);
		
		miaddcontact = new JMenuItem("ADD DETAILS");
		miaddcontact.setBorder(new LineBorder(new Color(128, 0, 0), 4));
		miaddcontact.setOpaque(true);
		miaddcontact.setBackground(new Color(169, 169, 169));
		//miaddcontact.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/contactapp/images/contactcreate.png")));
		miaddcontact.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		contactmenu.add(miaddcontact);
		miaddcontact.addActionListener(this);
	    
	    miupdatecontact = new JMenuItem("UPDATE DETAILS");
	    //miupdatecontact.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/contactapp/images/contactupdate.png")));
	    miupdatecontact.setFont(new Font("Snap ITC", Font.ITALIC, 20));
	    miupdatecontact.setBorder(new LineBorder(new Color(128, 0, 0), 4));
	    miupdatecontact.setBackground(new Color(169, 169, 169));
	    miupdatecontact.addActionListener(this);
	    contactmenu.add(miupdatecontact);
		
	    mideletecontact = new JMenuItem("DELETE DETAILS");
	    mideletecontact.setBorder(new LineBorder(new Color(128, 0, 0), 4));
	    mideletecontact.setBackground(new Color(169, 169, 169));
	    mideletecontact.setForeground(new Color(0, 0, 0));
	    //mideletecontact.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/contactapp/images/contactdelete.png")));
		mideletecontact.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		contactmenu.add(mideletecontact);
		mideletecontact.addActionListener(this);
		
		JMenu searchmenu = new JMenu("VIEW EMPLOYEE");
		searchmenu.setForeground(new Color(128, 0, 0));
		searchmenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		menuBar.add(searchmenu);
		
		
		JMenuItem miseecce = new JMenuItem("VIEW ALL CUSTOMER CARE EXECUTIVES");
		miseecce.setForeground(new Color(255, 0, 0));
		miseecce.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		miseecce.addActionListener(this);
		searchmenu.add(miseecce);
		
		
		JMenuItem miseese = new JMenuItem("VIEW ALL SERVICE ENGINEERS");
		miseese.setForeground(new Color(255, 0, 0));
		miseese.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		searchmenu.add(miseese);
		
		JMenu mnviewcomp = new JMenu("VIEW COMPLAINTS");
		mnviewcomp.setHorizontalAlignment(SwingConstants.CENTER);
		mnviewcomp.setForeground(new Color(128, 0, 0));
		mnviewcomp.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		menuBar.add(mnviewcomp);
		
		mibydate = new JMenuItem("DATE WISE");
		mibydate.addActionListener(this);
		mnviewcomp.add(mibydate);
		
		mibycompid = new JMenuItem("COMPLAINT ID WISE");
		mibycompid.addActionListener(this);
		mnviewcomp.add(mibycompid);
		
	    mibycusid = new JMenuItem("CUSTOMER ID WISE");
	    mibycusid.addActionListener(this);
		mnviewcomp.add(mibycusid);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		miseese.addActionListener(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("1- CM will create the account of CCE and SE.\r\n");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(113, 81, 793, 107);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2- CM will be able to add, update, and delete account of both members.");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(32, 168, 884, 119);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("3- CM will be able to see all complaints date wise.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(42, 298, 863, 89);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("4- CM will be able to see all complaints compalint-id wise.");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(53, 407, 863, 89);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("5- CM will be able to see all complaints customer-id wise.");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_4.setBounds(43, 507, 863, 89);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("                             COMPANY MANAGER");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(0, 0, 139));
		lblNewLabel_5.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_5.setBounds(196, 27, 710, 68);
		contentPane.add(lblNewLabel_5);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String caption=e.getActionCommand();
		
		if(caption.equalsIgnoreCase("ADD DETAILS"))
		{
			AddEmployeeDetails addemp=new AddEmployeeDetails();
			addemp.setVisible(true);
		}
		if(caption.equalsIgnoreCase("UPDATE DETAILS"))
		{
			UpdateEmployeeDetails upcont=new UpdateEmployeeDetails();
			upcont.setVisible(true);
		}
		if(caption.equalsIgnoreCase("DELETE DETAILS"))
		{
			DeleteEmployeeDetails delcont=new DeleteEmployeeDetails();
			delcont.setVisible(true);
		}
		if(caption.equalsIgnoreCase("VIEW ALL CUSTOMER CARE EXECUTIVES"))
		{
			SeeAllcustomerCareExecutives scce=new SeeAllcustomerCareExecutives();
			scce.setVisible(true);
		}
		if(caption.equalsIgnoreCase("VIEW ALL SERVICE ENGINEERS"))
		{
			SeeAllServiceEngineer sse=new SeeAllServiceEngineer();
			sse.setVisible(true);
		}
		if(caption.equalsIgnoreCase("DATE WISE"))
		{
			ViewComplaintsDateWise  sve=new ViewComplaintsDateWise();
			sve.setVisible(true);
		}
		if(caption.equalsIgnoreCase("COMPLAINT ID WISE"))
		{
			ViewComplaints_ComplaintidWise vcw=new ViewComplaints_ComplaintidWise();
			vcw.setVisible(true);
		}
		if(caption.equalsIgnoreCase("CUSTOMER ID WISE"))
		{
			ViewComplaints_CustomeridWise cus=new ViewComplaints_CustomeridWise();
			cus.setVisible(true);
		}
			
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		
		//this.dispose();
		//new EmployeeLogin().setVisible(true);      //to open login frame when we close admin frame
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
