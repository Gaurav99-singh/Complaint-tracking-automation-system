package complaintapp.customer_care_executive;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.database.DbConnection1;
import complaintapp.entry.CompanyManagerAdmin;
import complaintapp.entry.CustomerCareExecutiveAdmin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

import java.awt.event.*;
import java.sql.*;
import complaintapp.checkvalidationcode.*;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class AddCustomer_ComplaintDetails extends JFrame implements ActionListener,WindowListener,KeyListener
{

	private JPanel contentPane;
	private JTextField txtcustomerid;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomer_ComplaintDetails frame = new AddCustomer_ComplaintDetails();
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
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	CheckIfCustomerExists cie;
	
	public AddCustomer_ComplaintDetails() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddCustomer_ComplaintDetails.class.getResource("/complaintapp/images/adddetailstitle.png")));
		con=DbConnection1.createConnection();
		setTitle("ADD CUSTOMER DETAILS");
		this.addWindowListener(this);
		createComponents();
	}
	int count = 0;
	public int fillCustomer_ComplaintId()
	{
		
		String strsee="Select * from complaint";
		try
		{
			ps=con.prepareStatement(strsee);
			rs=ps.executeQuery();
			count=0;
			while(rs.next())
			{
				count++;
			}
		}
		catch(SQLException d)
		{
			d.printStackTrace();
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			catch(SQLException ss)
			{
				ss.printStackTrace();
			}
		}
		return count;
	}

	private JTextArea txtaddress;
	JButton btnadd,btngenerateid;
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1011, 702);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD CUSTOMER DETAILS PAGE");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		lblNewLabel.setBackground(new Color(169, 169, 169));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 140, 0));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(169, 0, 600, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblcustomerid = new JLabel("CUSTOMER ID:");
		lblcustomerid.setForeground(new Color(255, 165, 0));
		lblcustomerid.setOpaque(true);
		lblcustomerid.setBorder(new LineBorder(new Color(178, 34, 34), 2));
		lblcustomerid.setBackground(new Color(0, 128, 128));
		lblcustomerid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcustomerid.setHorizontalAlignment(SwingConstants.CENTER);
		lblcustomerid.setBounds(60, 70, 264, 38);
		contentPane.add(lblcustomerid);
		
		txtcustomerid = new JTextField();
		txtcustomerid.setHorizontalAlignment(SwingConstants.CENTER);
		txtcustomerid.setForeground(new Color(255, 140, 0));
		txtcustomerid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtcustomerid.setBorder(new LineBorder(new Color(178, 34, 34), 3));
		txtcustomerid.setBackground(new Color(189, 183, 107));
		txtcustomerid.setEnabled(false);
		txtcustomerid.setEditable(false);
		txtcustomerid.setBounds(372, 70, 306, 39);
		contentPane.add(txtcustomerid);
		txtcustomerid.setColumns(10);
		
		JLabel lblname = new JLabel("ENTER NAME:");
		lblname.setForeground(new Color(255, 165, 0));
		lblname.setOpaque(true);
		lblname.setBorder(new LineBorder(new Color(178, 34, 34), 2));
		lblname.setBackground(new Color(0, 128, 128));
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblname.setBounds(60, 119, 264, 38);
		contentPane.add(lblname);
		
		txtname = new JTextField();
		txtname.setHorizontalAlignment(SwingConstants.CENTER);
		txtname.setForeground(new Color(255, 140, 0));
		txtname.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtname.setBorder(new LineBorder(new Color(178, 34, 34), 3));
		txtname.setBackground(new Color(189, 183, 107));
		txtname.setBounds(372, 120, 306, 37);
		contentPane.add(txtname);
		txtname.addKeyListener(this);
		txtname.setColumns(10);
		
		JLabel lbladdress = new JLabel("ADDRESS:");
		lbladdress.setForeground(new Color(255, 165, 0));
		lbladdress.setOpaque(true);
		lbladdress.setBorder(new LineBorder(new Color(178, 34, 34), 2));
		lbladdress.setBackground(new Color(0, 128, 128));
		lbladdress.setHorizontalAlignment(SwingConstants.CENTER);
		lbladdress.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lbladdress.setBounds(60, 166, 264, 38);
		contentPane.add(lbladdress);
		
		JLabel lblphone = new JLabel("PHONE NO:");
		lblphone.setForeground(new Color(255, 165, 0));
		lblphone.setOpaque(true);
		lblphone.setBorder(new LineBorder(new Color(178, 34, 34), 2));
		lblphone.setBackground(new Color(0, 128, 128));
		lblphone.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblphone.setHorizontalAlignment(SwingConstants.CENTER);
		lblphone.setBounds(60, 235, 264, 38);
		contentPane.add(lblphone);
		
		JLabel lblemail = new JLabel("EMAIL:");
		lblemail.setForeground(new Color(255, 165, 0));
		lblemail.setOpaque(true);
		lblemail.setBorder(new LineBorder(new Color(178, 34, 34), 2));
		lblemail.setBackground(new Color(0, 128, 128));
		lblemail.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblemail.setHorizontalAlignment(SwingConstants.CENTER);
		lblemail.setBounds(60, 290, 264, 37);
		contentPane.add(lblemail);
		
		txtphone = new JTextField();
		txtphone.setHorizontalAlignment(SwingConstants.CENTER);
		txtphone.setForeground(new Color(255, 140, 0));
		txtphone.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtphone.setBorder(new LineBorder(new Color(178, 34, 34), 3));
		txtphone.setBackground(new Color(189, 183, 107));
		txtphone.setBounds(372, 235, 306, 49);
		contentPane.add(txtphone);
		txtphone.addKeyListener(this);
		txtphone.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setHorizontalAlignment(SwingConstants.CENTER);
		txtemail.setForeground(new Color(255, 140, 0));
		txtemail.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtemail.setBorder(new LineBorder(new Color(178, 34, 34), 3));
		txtemail.setBackground(new Color(189, 183, 107));
		txtemail.setBounds(372, 290, 306, 37);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(372, 168, 498, 64);
		contentPane.add(scrollPane);
		
	    txtaddress = new JTextArea();
		txtaddress.setForeground(new Color(255, 140, 0));
		txtaddress.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtaddress.setBorder(new LineBorder(new Color(178, 34, 34), 3));
		txtaddress.setBackground(new Color(189, 183, 107));
	    scrollPane.setViewportView(txtaddress);
	    
	    JPanel contentPane1 = new JPanel();
	    contentPane1.setBackground(new Color(233, 150, 122));
	    contentPane1.setBounds(29, 347, 915, 318);
	    setLocationRelativeTo(null);
	    contentPane.add(contentPane1);
	    contentPane1.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("ADD COMPLAINT DETAILS PAGE");
	    lblNewLabel_1.setOpaque(true);
	    lblNewLabel_1.setForeground(new Color(178, 34, 34));
	    lblNewLabel_1.setBorder(new LineBorder(new Color(255, 140, 0), 5));
	    lblNewLabel_1.setBackground(new Color(169, 169, 169));
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblNewLabel_1.setBounds(148, 11, 612, 38);
	    contentPane1.add(lblNewLabel_1);
	    
	    JLabel lblcomplaintid = new JLabel("COMPLAINT ID:");
	    lblcomplaintid.setBorder(new LineBorder(new Color(153, 102, 0), 3));
	    lblcomplaintid.setOpaque(true);
	    lblcomplaintid.setBackground(new Color(204, 204, 153));
	    lblcomplaintid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblcomplaintid.setForeground(new Color(0, 0, 0));
	    lblcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
	    lblcomplaintid.setBounds(158, 58, 249, 37);
	    contentPane1.add(lblcomplaintid);
	    
	    txtcomplaintid = new JTextField();
	    txtcomplaintid.setForeground(new Color(250, 128, 114));
	    txtcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
	    txtcomplaintid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 15));
	    txtcomplaintid.setBorder(new LineBorder(new Color(128, 0, 0), 3));
	    txtcomplaintid.setBackground(new Color(245, 222, 179));
	    txtcomplaintid.setEnabled(false);
	    txtcomplaintid.setEditable(false);
	    txtcomplaintid.setBounds(481, 55, 206, 40);
	    contentPane1.add(txtcomplaintid);
	    txtcomplaintid.setColumns(10);
	    
	    JLabel lblNewLabel_2 = new JLabel("CUSTOMER ID:");
	    lblNewLabel_2.setBorder(new LineBorder(new Color(153, 102, 0), 3));
	    lblNewLabel_2.setOpaque(true);
	    lblNewLabel_2.setBackground(new Color(204, 204, 153));
	    lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblNewLabel_2.setForeground(new Color(0, 0, 0));
	    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_2.setBounds(158, 106, 249, 38);
	    contentPane1.add(lblNewLabel_2);
	    
	    txtcustomerid1 = new JTextField();
	    txtcustomerid1.setBackground(new Color(245, 222, 179));
	    txtcustomerid1.setForeground(new Color(250, 128, 114));
	    txtcustomerid1.setHorizontalAlignment(SwingConstants.CENTER);
	    txtcustomerid1.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 15));
	    txtcustomerid1.setBorder(new LineBorder(new Color(128, 0, 0), 3));
	    txtcustomerid1.setEnabled(false);
	    txtcustomerid1.setEditable(false);
	    txtcustomerid1.setBounds(481, 106, 206, 38);
	    contentPane1.add(txtcustomerid1);
	    txtcustomerid1.setColumns(10);
	    
	    JLabel lblNewLabel_3 = new JLabel("PRODUCT NAME:");
	    lblNewLabel_3.setBorder(new LineBorder(new Color(153, 102, 0), 3));
	    lblNewLabel_3.setOpaque(true);
	    lblNewLabel_3.setBackground(new Color(204, 204, 153));
	    lblNewLabel_3.setForeground(new Color(0, 0, 0));
	    lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblNewLabel_3.setBounds(158, 157, 249, 34);
	    contentPane1.add(lblNewLabel_3);
	    
	    txtproduct = new JTextField();
	    txtproduct.setForeground(new Color(250, 128, 114));
	    txtproduct.setHorizontalAlignment(SwingConstants.CENTER);
	    txtproduct.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 15));
	    txtproduct.setBorder(new LineBorder(new Color(128, 0, 0), 3));
	    txtproduct.setBackground(new Color(245, 222, 179));
	    txtproduct.setBounds(481, 153, 206, 38);
	    contentPane1.add(txtproduct);
	    txtproduct.setColumns(10);
	    
	    JLabel lblcomplainttext = new JLabel("COMPLAINT TEXT:");
	    lblcomplainttext.setBorder(new LineBorder(new Color(153, 102, 0), 3));
	    lblcomplainttext.setOpaque(true);
	    lblcomplainttext.setBackground(new Color(204, 204, 153));
	    lblcomplainttext.setForeground(new Color(0, 0, 0));
	    lblcomplainttext.setHorizontalAlignment(SwingConstants.CENTER);
	    lblcomplainttext.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblcomplainttext.setBounds(153, 212, 254, 38);
	    contentPane1.add(lblcomplainttext);
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane_1.setBounds(481, 202, 446, 74);
	    contentPane1.add(scrollPane_1);
	    
	    txtcomplainttext = new JTextArea();
	    txtcomplainttext.setForeground(new Color(250, 128, 114));
	    txtcomplainttext.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 15));
	    txtcomplainttext.setBorder(new LineBorder(new Color(128, 0, 0), 3));
	    txtcomplainttext.setBackground(new Color(245, 222, 179));
	    scrollPane_1.setViewportView(txtcomplainttext);
	    
	    btnadd = new JButton("ADD DETAILS");
	    btnadd.setForeground(new Color(250, 128, 114));
	    btnadd.setBorder(new LineBorder(new Color(47, 79, 79), 5));
	    btnadd.setBackground(new Color(112, 128, 144));
	    btnadd.setBounds(287, 282, 300, 34);
	    contentPane1.add(btnadd);
	    btnadd.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
	    btnadd.addActionListener(this);
	    
	    btngenerateid = new JButton("GENERATE ID");
	    btngenerateid.setForeground(new Color(255, 127, 80));
	    btngenerateid.setBorder(new LineBorder(new Color(47, 79, 79), 5));
	    btngenerateid.setBackground(new Color(112, 128, 144));
	    btngenerateid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    btngenerateid.setBounds(715, 70, 272, 38);
	    btngenerateid.addActionListener(this);
	    contentPane.add(btngenerateid);
	    
	    JLabel lblNewLabel_4 = new JLabel("");
	    lblNewLabel_4.setIcon(new ImageIcon(AddCustomer_ComplaintDetails.class.getResource("/complaintapp/images/complaint-concept-against-barbwire-170339026.jpg")));
	    lblNewLabel_4.setBounds(0, 0, 997, 674);
	    contentPane.add(lblNewLabel_4);
	}
	
	private String id,compid;
	
	public void setCustomer_ComplaintId()
	{
		int i=fillCustomer_ComplaintId();
		i++;
		String formatted=String.format("%03d",i);
		id="CUSTOMER"+formatted;
		compid="CMP"+formatted;
		txtcustomerid.setText(id);
		txtcomplaintid.setText(compid);
		txtcustomerid1.setText(id);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj1=e.getSource();
		JButton jb1=(JButton)obj1;
		
		if(jb1 == btngenerateid)
		{
			setCustomer_ComplaintId();
		}
		
		
		if(jb1 == btnadd)
		{
			if(checkValidation())
			{
				if(phone.length() == 10)
				{
					if(!ispresent())
					{
						insertCustomerData();
						insertComplaintData();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Phone no must have only 10 digits",
							"Phone Number",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Fill All fields","MANDATORY",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public void insertCustomerData()
	{
		String strinsert="insert into customer(Customerid, Name, Address, Phoneno, Email) values(?,?,?,?,?)";
		try
		{
			ps=con.prepareStatement(strinsert);
			ps.setString(1,id);
			ps.setString(2,name);
			ps.setString(3,address);
			ps.setString(4,phone);
			ps.setString(5,email);
			
            int row_status=ps.executeUpdate();
			
			if(row_status > 0)
			{
				txtcustomerid.setText("");
				txtname.setText("");
				txtemail.setText("");
				txtphone.setText("");
				txtaddress.setText("");
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
			}
			catch(SQLException s)
			{
				s.printStackTrace();
			}
		}
	}
	
	public void insertComplaintData()
	{
		java.util.Date d=new java.util.Date();
		long date=d.getTime();
		java.sql.Date ssd=new java.sql.Date(date);
		String strinsert="insert into complaint(Complaintid, Customerid, ProductName, Complaint_text, Complaint_Date) values(?,?,?,?,?)";
		try
		{
			ps=con.prepareStatement(strinsert);
			ps.setString(1,compid);
			ps.setString(2,id);
			ps.setString(3,productname);
			ps.setString(4,complaint_text);
			ps.setDate(5,ssd);
			
            int row_status=ps.executeUpdate();
			
			if(row_status > 0)
			{ 
				txtcomplaintid.setText("");
				txtcustomerid1.setText("");
				txtproduct.setText("");
				txtcomplainttext.setText("");
				JOptionPane.showMessageDialog(this, "Data Added","Added Customer and Complaints Details",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
			}
			catch(SQLException s)
			{
				s.printStackTrace();
			}
		}
	}
	
	private String name,address,phone,email,productname,complaint_text;
	private JTextField txtcomplaintid;
	private JTextField txtcustomerid1;
	private JTextField txtproduct;
	private JTextArea txtcomplainttext;
	
	public boolean checkValidation()
	{
        id=txtcustomerid.getText().trim();  //getting text from id 
		name=txtname.getText().trim();
		address=txtaddress.getText();
		phone=txtphone.getText().trim();
		email=txtemail.getText().trim();
		productname=txtproduct.getText().trim();
		complaint_text=txtcomplainttext.getText().trim();

		if(id.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty() || productname.isEmpty() || complaint_text.isEmpty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean ispresent()
	{

        id=txtcustomerid.getText().trim();  //getting text from id 
		phone=txtphone.getText().trim();
		email=txtemail.getText().trim();
		cie=new CheckIfCustomerExists(id,email,phone);
		int flag=0;
		if(cie.checkCustomerid()==1)
		{
			flag=1;
			JOptionPane.showMessageDialog(this,"CustomerId exists.","ERROR",JOptionPane.ERROR_MESSAGE);
			txtcustomerid.setText("");
			txtcomplaintid.setText("");
			txtcustomerid1.setText("");
		}
		if(cie.checkCustomerPhone()==2)
		{
			flag=1;
			JOptionPane.showMessageDialog(this,"Phone NO exists.Please Enter another","ERROR",JOptionPane.ERROR_MESSAGE);
			txtphone.setText("");
		}
		if(cie.checkCustomerEmail()==3)
		{
			flag=1;
			JOptionPane.showMessageDialog(this,"EmailId exists.Please Enter another","ERROR",JOptionPane.ERROR_MESSAGE);
			txtemail.setText("");
		}
		if(flag==1)
			return true;
		else
			return false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		Object obj=e.getSource(); //when two or more text fields are to be checked then getsource
		JTextField jt=(JTextField)obj; //typecast into required field which we want to check
		char c=e.getKeyChar();
		
		if(jt == txtname)
		{
			if(!((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || (c==KeyEvent.VK_ENTER) || (c==KeyEvent.VK_TAB)
					||(Character.isLetter(c)) ))
			{
				e.consume();
				JOptionPane.showMessageDialog(this,"Name requires only alphabets","MANDATORY",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(jt == txtphone)
		{
			if(!((c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE) || (c==KeyEvent.VK_ENTER) || (c==KeyEvent.VK_TAB) 
					||(Character.isDigit(c)) ))
			{
				e.consume();
				JOptionPane.showMessageDialog(this,"Phone no Required only digits","MANDATORY",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		DbConnection1.closeConnection();
		JOptionPane.showMessageDialog(this,"THANK YOU","EXIT",JOptionPane.INFORMATION_MESSAGE);
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
