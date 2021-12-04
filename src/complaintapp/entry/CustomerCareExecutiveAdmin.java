package complaintapp.entry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import complaintapp.company_manager.AddEmployeeDetails;
import complaintapp.company_manager.DeleteEmployeeDetails;
import complaintapp.company_manager.UpdateEmployeeDetails;
import complaintapp.company_manager.ViewComplaintsDateWise;
import complaintapp.customer_care_executive.AddCustomer_ComplaintDetails;
import complaintapp.customer_care_executive.AssignComplaint;
import complaintapp.customer_care_executive.FeedbackForm;
import complaintapp.customer_care_executive.SeeAllServiceEngineer;
import complaintapp.customer_care_executive.TrackCustomerComplaint;
import complaintapp.customer_care_executive.ViewComplaints_ComplaintidWise;
import complaintapp.customer_care_executive.ViewComplaints_CustomeridWise;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class CustomerCareExecutiveAdmin extends JFrame implements ActionListener,WindowListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerCareExecutiveAdmin frame = new CustomerCareExecutiveAdmin();
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

	public CustomerCareExecutiveAdmin() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerCareExecutiveAdmin.class.getResource("/complaintapp/images/admin.jpg")));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(CompanyManagerAdmin.class.getResource("/contactapp/images/admin.jpg")));
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("CUSTOMER CARE EXECUTIVE ADMIN");
		this.addWindowListener(this);    //to handle close button  
		createComponents();
		
	}
	
	
	JMenuItem mibydate,mibycompid,mibycusid;
	JButton btncompdetails,btnfeedback,btntrack,btnassign;
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//dispose in place of exit so that child frame closes and parent window remains open
		setBounds(100, 100, 1321, 737);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(85, 107, 47), 4));
		menuBar.setBackground(new Color(119, 136, 153));
		menuBar.setForeground(new Color(128, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu searchmenu = new JMenu("VIEW EMPLOYEE");
		searchmenu.setIcon(null);
		searchmenu.setOpaque(true);
		searchmenu.setBorder(new LineBorder(new Color(178, 34, 34), 3));
		searchmenu.setBackground(new Color(220, 220, 220));
		searchmenu.setForeground(new Color(128, 0, 0));
		searchmenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		menuBar.add(searchmenu);
		
		JMenuItem miseese = new JMenuItem("VIEW ALL SERVICE ENGINEERS");
		miseese.setIcon(new ImageIcon(CustomerCareExecutiveAdmin.class.getResource("/complaintapp/images/icons8-view-64.png")));
		miseese.setOpaque(true);
		miseese.setBorder(new LineBorder(new Color(0, 139, 139), 5));
		miseese.setBackground(new Color(192, 192, 192));
		miseese.setForeground(new Color(178, 34, 34));
		miseese.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		searchmenu.add(miseese);
		
		JMenu mnviewcomp = new JMenu("VIEW COMPLAINTS");
		mnviewcomp.setOpaque(true);
		mnviewcomp.setBorder(new LineBorder(new Color(178, 34, 34), 3));
		mnviewcomp.setBackground(new Color(220, 220, 220));
		mnviewcomp.setHorizontalAlignment(SwingConstants.CENTER);
		mnviewcomp.setForeground(new Color(128, 0, 0));
		mnviewcomp.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		menuBar.add(mnviewcomp);
		
		mibydate = new JMenuItem("DATE WISE");
		mibydate.setOpaque(true);
		mibydate.setBorder(new LineBorder(new Color(0, 128, 128), 5));
		mibydate.setBackground(new Color(192, 192, 192));
		mibydate.setForeground(new Color(178, 34, 34));
		mibydate.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		mibydate.setIcon(new ImageIcon(CustomerCareExecutiveAdmin.class.getResource("/complaintapp/images/icons8-search-64.png")));
		mibydate.addActionListener(this);
		mnviewcomp.add(mibydate);
		
		mibycompid = new JMenuItem("COMPLAINT ID WISE");
		mibycompid.setIcon(new ImageIcon(CustomerCareExecutiveAdmin.class.getResource("/complaintapp/images/icons8-search-64.png")));
		mibycompid.setForeground(new Color(178, 34, 34));
		mibycompid.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		mibycompid.setOpaque(true);
		mibycompid.setBackground(new Color(192, 192, 192));
		mibycompid.setBorder(new LineBorder(new Color(0, 128, 128), 5));
		mibycompid.addActionListener(this);
		mnviewcomp.add(mibycompid);
		
	    mibycusid = new JMenuItem("CUSTOMER ID WISE");
	    mibycusid.setBackground(new Color(192, 192, 192));
	    mibycusid.setBorder(new LineBorder(new Color(0, 128, 128), 5));
	    mibycusid.setForeground(new Color(178, 34, 34));
	    mibycusid.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
	    mibycusid.setIcon(new ImageIcon(CustomerCareExecutiveAdmin.class.getResource("/complaintapp/images/icons8-search-64.png")));
	    mibycusid.setOpaque(true);
	    mibycusid.addActionListener(this);
		mnviewcomp.add(mibycusid);
		
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(178, 34, 34), 5));
		miseese.addActionListener(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("4- CCE will be able to see all complaints date wise.");
		lblNewLabel_2.setBorder(new LineBorder(new Color(85, 107, 47), 3));
		lblNewLabel_2.setBackground(new Color(169, 169, 169));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(49, 303, 1210, 61);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("5- CCE will be able to see all complaints compalint-id wise.");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBorder(new LineBorder(new Color(85, 107, 47), 3));
		lblNewLabel_3.setBackground(new Color(169, 169, 169));
		lblNewLabel_3.setForeground(new Color(220, 20, 60));
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(49, 375, 1210, 61);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("6- CCE will be able to see all complaints customer-id wise.");
		lblNewLabel_4.setBorder(new LineBorder(new Color(85, 107, 47), 3));
		lblNewLabel_4.setBackground(new Color(169, 169, 169));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setForeground(new Color(220, 20, 60));
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_4.setBounds(49, 447, 1211, 61);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("CUSTOMER CARE EXECUTIVE");
		lblNewLabel_5.setBorder(new LineBorder(new Color(85, 107, 47), 3));
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(new Color(222, 184, 135));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(139, 69, 19));
		lblNewLabel_5.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_5.setBounds(49, 11, 1210, 68);
		contentPane.add(lblNewLabel_5);
		
		btncompdetails = new JButton("ADD CUSTOMER COMPLAINTS DETAILS");
		btncompdetails.setForeground(new Color(0, 128, 128));
		btncompdetails.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btncompdetails.setBackground(new Color(210, 180, 140));
		btncompdetails.setBorder(new LineBorder(new Color(178, 34, 34), 5, true));
		btncompdetails.setBounds(130, 519, 413, 51);
		btncompdetails.addActionListener(this);
		contentPane.add(btncompdetails);
		
		btnassign = new JButton("ASSIGN COMPLAINT");
		btnassign.setForeground(new Color(0, 128, 128));
		btnassign.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnassign.setBackground(new Color(210, 180, 140));
		btnassign.setBorder(new LineBorder(new Color(178, 34, 34), 5, true));
		btnassign.setBounds(782, 519, 413, 51);
		btnassign.addActionListener(this);
		contentPane.add(btnassign);
		
		btntrack = new JButton("TRACK CUSTOMER COMPLAINT");
		btntrack.setBackground(new Color(210, 180, 140));
		btntrack.setForeground(new Color(0, 128, 128));
		btntrack.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btntrack.setBorder(new LineBorder(new Color(178, 34, 34), 5, true));
		btntrack.setBounds(130, 593, 418, 51);
		btntrack.addActionListener(this);
		contentPane.add(btntrack);
		
		btnfeedback = new JButton("FEEDBACK FORM");
		btnfeedback.setForeground(new Color(0, 128, 128));
		btnfeedback.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnfeedback.setBackground(new Color(210, 180, 140));
		btnfeedback.setBorder(new LineBorder(new Color(178, 34, 34), 5, true));
		btnfeedback.addActionListener(this);
		btnfeedback.setBounds(782, 593, 413, 51);
		contentPane.add(btnfeedback);
		
		JLabel lblNewLabel_6 = new JLabel("3- Complaint will be passed to the SE by the CCE.");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setBorder(new LineBorder(new Color(85, 107, 47), 3));
		lblNewLabel_6.setBackground(new Color(169, 169, 169));
		lblNewLabel_6.setForeground(new Color(220, 20, 60));
		lblNewLabel_6.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_6.setBounds(49, 231, 1210, 61);
		contentPane.add(lblNewLabel_6);
		
		JTextArea txtrCustomerWill = new JTextArea();
		txtrCustomerWill.setBorder(new LineBorder(new Color(85, 107, 47), 3));
		txtrCustomerWill.setBackground(new Color(169, 169, 169));
		txtrCustomerWill.setForeground(new Color(220, 20, 60));
		txtrCustomerWill.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		txtrCustomerWill.setText("1- Customer will be able to register their complaint regarding the product purchased by them \r\nfrom that company, by phone.");
		txtrCustomerWill.setBounds(49, 87, 1210, 61);
		contentPane.add(txtrCustomerWill);
		
		JTextPane txtpnComplaintWill = new JTextPane();
		txtpnComplaintWill.setBorder(new LineBorder(new Color(85, 107, 47), 3));
		txtpnComplaintWill.setBackground(new Color(169, 169, 169));
		txtpnComplaintWill.setForeground(new Color(220, 20, 60));
		txtpnComplaintWill.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		txtpnComplaintWill.setText("2- Complaint will be noted by the CCE and a unique complaint number will be allotted to each customer.");
		txtpnComplaintWill.setBounds(49, 159, 1210, 61);
		contentPane.add(txtpnComplaintWill);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CustomerCareExecutiveAdmin.class.getResource("/complaintapp/images/pencil-calendar-bookmark-date-business-details-89468550.jpg")));
		lblNewLabel.setBounds(10, 11, 1261, 636);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String caption=e.getActionCommand();
		
		/*Object obj=e.getSource();
		JButton jb=(JButton)obj;*/
		
		if(e.getSource() == btncompdetails)
		{
			AddCustomer_ComplaintDetails accd=new AddCustomer_ComplaintDetails();
			accd.setVisible(true);
		}
		if(e.getSource() == btntrack)
		{
			TrackCustomerComplaint tcc=new TrackCustomerComplaint();
			tcc.setVisible(true);
		}
		if(e.getSource() == btnassign)
		{
			AssignComplaint ac=new AssignComplaint();
            ac.setVisible(true);
		}
		if(e.getSource() == btnfeedback)
		{
			FeedbackForm feed=new FeedbackForm();
			feed.setVisible(true);
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
		JOptionPane.showMessageDialog(this,"THANK YOU","EXIT",JOptionPane.INFORMATION_MESSAGE);

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

