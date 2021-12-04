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
import javax.swing.JOptionPane;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(CompanyManagerAdmin.class.getResource("/complaintapp/images/admin.jpg")));
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
		setBounds(100, 100, 1295, 679);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(Color.DARK_GRAY, 4));
		menuBar.setBackground(new Color(119, 136, 153));
		menuBar.setForeground(new Color(128, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu contactmenu = new JMenu("MANAGE EMPLOYEE");
		contactmenu.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		contactmenu.setHorizontalAlignment(SwingConstants.CENTER);
		contactmenu.setOpaque(true);
		contactmenu.setBackground(new Color(211, 211, 211));
		contactmenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		contactmenu.setForeground(new Color(128, 0, 0));
		menuBar.add(contactmenu);
		
		miaddcontact = new JMenuItem("ADD DETAILS");
		miaddcontact.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/contactcreate.png")));
		miaddcontact.setBorder(new LineBorder(new Color(220, 20, 60), 3));
		miaddcontact.setOpaque(true);
		miaddcontact.setBackground(new Color(169, 169, 169));
		miaddcontact.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		contactmenu.add(miaddcontact);
		miaddcontact.addActionListener(this);
	    
	    miupdatecontact = new JMenuItem("UPDATE DETAILS");
	    miupdatecontact.setOpaque(true);
	    miupdatecontact.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/contactupdate.png")));
	    miupdatecontact.setFont(new Font("Snap ITC", Font.ITALIC, 20));
	    miupdatecontact.setBorder(new LineBorder(new Color(220, 20, 60), 3));
	    miupdatecontact.setBackground(new Color(169, 169, 169));
	    contactmenu.add(miupdatecontact);
	    miupdatecontact.addActionListener(this);
		
	    mideletecontact = new JMenuItem("DELETE DETAILS");
	    mideletecontact.setOpaque(true);
	    mideletecontact.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/contactdelete.png")));
	    mideletecontact.setBorder(new LineBorder(new Color(220, 20, 60), 3));
	    mideletecontact.setBackground(new Color(169, 169, 169));
	    mideletecontact.setForeground(new Color(0, 0, 0));
		mideletecontact.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		contactmenu.add(mideletecontact);
		mideletecontact.addActionListener(this);
		
		JMenu searchmenu = new JMenu("VIEW EMPLOYEE");
		searchmenu.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		searchmenu.setOpaque(true);
		searchmenu.setBackground(new Color(211, 211, 211));
		searchmenu.setHorizontalAlignment(SwingConstants.CENTER);
		searchmenu.setForeground(new Color(128, 0, 0));
		searchmenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		menuBar.add(searchmenu);
		
		
		JMenuItem miseecce = new JMenuItem("VIEW ALL CUSTOMER CARE EXECUTIVES");
		miseecce.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/icons8-view-64.png")));
		miseecce.setBorder(new LineBorder(new Color(220, 20, 60), 3));
		miseecce.setOpaque(true);
		miseecce.setBackground(new Color(169, 169, 169));
		miseecce.setForeground(Color.BLACK);
		miseecce.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		miseecce.addActionListener(this);
		searchmenu.add(miseecce);
		
		
		JMenuItem miseese = new JMenuItem("VIEW ALL SERVICE ENGINEERS");
		miseese.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/icons8-view-64.png")));
		miseese.setBorder(new LineBorder(new Color(220, 20, 60), 3));
		miseese.setBackground(new Color(169, 169, 169));
		miseese.setOpaque(true);
		miseese.setForeground(new Color(0, 0, 0));
		miseese.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		searchmenu.add(miseese);
		
		JMenu mnviewcomp = new JMenu("VIEW COMPLAINTS");
		mnviewcomp.setOpaque(true);
		mnviewcomp.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		mnviewcomp.setBackground(new Color(211, 211, 211));
		mnviewcomp.setHorizontalAlignment(SwingConstants.CENTER);
		mnviewcomp.setForeground(new Color(128, 0, 0));
		mnviewcomp.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		menuBar.add(mnviewcomp);
		
		mibydate = new JMenuItem("DATE WISE");
		mibydate.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/icons8-search-64.png")));
		mibydate.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		mibydate.setBorder(new LineBorder(new Color(220, 20, 60), 3));
		mibydate.setBackground(new Color(169, 169, 169));
		mibydate.addActionListener(this);
		mnviewcomp.add(mibydate);
		
		mibycompid = new JMenuItem("COMPLAINT ID WISE");
		mibycompid.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/icons8-search-64.png")));
		mibycompid.setBackground(new Color(169, 169, 169));
		mibycompid.setBorder(new LineBorder(new Color(220, 20, 60), 3));
		mibycompid.setFont(new Font("Snap ITC", Font.ITALIC, 20));
		mibycompid.addActionListener(this);
		mnviewcomp.add(mibycompid);
		
	    mibycusid = new JMenuItem("CUSTOMER ID WISE");
	    mibycusid.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/icons8-search-64.png")));
	    mibycusid.setBorder(new LineBorder(new Color(220, 20, 60), 3));
	    mibycusid.setBackground(new Color(169, 169, 169));
	    mibycusid.setFont(new Font("Snap ITC", Font.ITALIC, 20));
	    mibycusid.addActionListener(this);
		mnviewcomp.add(mibycusid);
		
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.DARK_GRAY, 10));
		miseese.addActionListener(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("1- CM will create the account of CCE and SE.\r\n");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		lblNewLabel.setBackground(new Color(169, 169, 169));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(86, 135, 1104, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2- CM will be able to add, update, and delete account of both members.");
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		lblNewLabel_1.setBackground(new Color(169, 169, 169));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(86, 218, 1104, 69);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("3- CM will be able to see all complaints date wise.");
		lblNewLabel_2.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		lblNewLabel_2.setBackground(new Color(169, 169, 169));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(86, 311, 1104, 69);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("4- CM will be able to see all complaints compalint-id wise.");
		lblNewLabel_3.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		lblNewLabel_3.setBackground(new Color(169, 169, 169));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(86, 406, 1101, 69);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("5- CM will be able to see all complaints customer-id wise.");
		lblNewLabel_4.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		lblNewLabel_4.setBackground(new Color(169, 169, 169));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_4.setBounds(86, 507, 1104, 69);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("COMPANY MANAGER");
		lblNewLabel_5.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(new Color(211, 211, 211));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(0, 0, 139));
		lblNewLabel_5.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_5.setBounds(86, 26, 1099, 91);
		contentPane.add(lblNewLabel_5);
		
		JLabel label = new JLabel("New label");
		label.setBounds(0, 562, 1278, -559);
		contentPane.add(label);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(CompanyManagerAdmin.class.getResource("/complaintapp/images/loginbackground.jpg")));
		lblNewLabel_6.setBounds(10, 11, 1259, 587);
		contentPane.add(lblNewLabel_6);
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
		
		JOptionPane.showMessageDialog(this,"THANK YOU");
		new EmployeeLogin().setVisible(true);
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
