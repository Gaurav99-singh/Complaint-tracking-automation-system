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
		setBounds(100, 100, 920, 739);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(70, 130, 180), 4));
		menuBar.setBackground(new Color(211, 211, 211));
		menuBar.setForeground(new Color(128, 0, 0));
		setJMenuBar(menuBar);
		
		JMenu searchmenu = new JMenu("VIEW EMPLOYEE");
		searchmenu.setForeground(new Color(128, 0, 0));
		searchmenu.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		menuBar.add(searchmenu);
		
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
		
		JLabel lblNewLabel = new JLabel("1- Customer will be able to register their complaint regarding the product purchased by them from that company, by phone.");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 96, 633, 61);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("2- Complaint will be noted by the CCE and a unique complaint number will be allotted to each customer.");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 155, 884, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("4- CCE will be able to see all complaints date wise.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(20, 254, 674, 47);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("5- CCE will be able to see all complaints compalint-id wise.");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBounds(30, 312, 761, 81);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("6- CCE will be able to see all complaints customer-id wise.");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_4.setBounds(20, 386, 776, 61);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("          CUSTOMER CARE EXECUTIVE");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(0, 0, 139));
		lblNewLabel_5.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_5.setBounds(254, 11, 603, 68);
		contentPane.add(lblNewLabel_5);
		
		btncompdetails = new JButton("ADD CUSTOMER COMPLAINTS DETAILS");
		btncompdetails.setBounds(65, 481, 292, 47);
		btncompdetails.addActionListener(this);
		contentPane.add(btncompdetails);
		
		btnassign = new JButton("ASSIGN COMPLAINT");
		btnassign.setBounds(607, 481, 299, 47);
		btnassign.addActionListener(this);
		contentPane.add(btnassign);
		
		btntrack = new JButton("TRACK CUSTOMER COMPLAINT");
		btntrack.setBounds(62, 573, 295, 57);
		btntrack.addActionListener(this);
		contentPane.add(btntrack);
		
		btnfeedback = new JButton("FEEDBACK FORM");
		btnfeedback.addActionListener(this);
		btnfeedback.setBounds(614, 583, 292, 47);
		contentPane.add(btnfeedback);
		
		JLabel lblNewLabel_6 = new JLabel("3- Complaint will be passed to the SE by the CCE.");
		lblNewLabel_6.setForeground(new Color(255, 0, 0));
		lblNewLabel_6.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_6.setBounds(30, 216, 876, 47);
		contentPane.add(lblNewLabel_6);
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

