package complaintapp.customer_care_executive;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.database.DbConnection1;

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
		setBounds(100, 100, 1002, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD CUSTOMER DETAILS PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(170, 0, 599, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblcustomerid = new JLabel("CUSTOMER ID:");
		lblcustomerid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcustomerid.setHorizontalAlignment(SwingConstants.CENTER);
		lblcustomerid.setBounds(31, 46, 307, 31);
		contentPane.add(lblcustomerid);
		
		txtcustomerid = new JTextField();
		txtcustomerid.setEnabled(false);
		txtcustomerid.setEditable(false);
		txtcustomerid.setBounds(369, 55, 367, 22);
		contentPane.add(txtcustomerid);
		txtcustomerid.setColumns(10);
		
		JLabel lblname = new JLabel("ENTER NAME:");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblname.setBounds(75, 88, 219, 25);
		contentPane.add(lblname);
		
		txtname = new JTextField();
		txtname.setBounds(369, 88, 367, 25);
		contentPane.add(txtname);
		txtname.addKeyListener(this);
		txtname.setColumns(10);
		
		JLabel lbladdress = new JLabel("ADDRESS:");
		lbladdress.setHorizontalAlignment(SwingConstants.CENTER);
		lbladdress.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lbladdress.setBounds(75, 124, 219, 31);
		contentPane.add(lbladdress);
		
		JLabel lblphone = new JLabel("PHONE NO:");
		lblphone.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblphone.setHorizontalAlignment(SwingConstants.CENTER);
		lblphone.setBounds(75, 166, 178, 31);
		contentPane.add(lblphone);
		
		JLabel lblemail = new JLabel("EMAIL:");
		lblemail.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblemail.setHorizontalAlignment(SwingConstants.CENTER);
		lblemail.setBounds(62, 208, 178, 20);
		contentPane.add(lblemail);
		
		txtphone = new JTextField();
		txtphone.setBounds(322, 174, 150, 23);
		contentPane.add(txtphone);
		txtphone.addKeyListener(this);
		txtphone.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(303, 210, 213, 18);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(343, 124, 393, 44);
		contentPane.add(scrollPane);
		
	    txtaddress = new JTextArea();
	    scrollPane.setViewportView(txtaddress);
	    
	    JPanel contentPane1 = new JPanel();
	    contentPane1.setBounds(10, 239, 968, 414);
	    setLocationRelativeTo(null);
	    contentPane.add(contentPane1);
	    contentPane1.setLayout(null);
	    
	    JLabel lblNewLabel_1 = new JLabel("ADD COMPLAINT DETAILS PAGE");
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblNewLabel_1.setBounds(259, 11, 536, 46);
	    contentPane1.add(lblNewLabel_1);
	    
	    JLabel lblcomplaintid = new JLabel("COMPLAINT ID:");
	    lblcomplaintid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblcomplaintid.setForeground(new Color(0, 0, 0));
	    lblcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
	    lblcomplaintid.setBounds(40, 68, 326, 19);
	    contentPane1.add(lblcomplaintid);
	    
	    txtcomplaintid = new JTextField();
	    txtcomplaintid.setEnabled(false);
	    txtcomplaintid.setEditable(false);
	    txtcomplaintid.setBounds(397, 68, 159, 19);
	    contentPane1.add(txtcomplaintid);
	    txtcomplaintid.setColumns(10);
	    
	    JLabel lblNewLabel_2 = new JLabel("CUSTOMER ID:");
	    lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblNewLabel_2.setBounds(106, 106, 206, 19);
	    contentPane1.add(lblNewLabel_2);
	    
	    txtcustomerid1 = new JTextField();
	    txtcustomerid1.setEnabled(false);
	    txtcustomerid1.setEditable(false);
	    txtcustomerid1.setBounds(407, 106, 149, 23);
	    contentPane1.add(txtcustomerid1);
	    txtcustomerid1.setColumns(10);
	    
	    JLabel lblNewLabel_3 = new JLabel("PRODUCT NAME:");
	    lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblNewLabel_3.setBounds(95, 148, 238, 23);
	    contentPane1.add(lblNewLabel_3);
	    
	    txtproduct = new JTextField();
	    txtproduct.setBounds(389, 154, 167, 19);
	    contentPane1.add(txtproduct);
	    txtproduct.setColumns(10);
	    
	    JLabel lblcomplainttext = new JLabel("COMPLAINT TEXT:");
	    lblcomplainttext.setHorizontalAlignment(SwingConstants.CENTER);
	    lblcomplainttext.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    lblcomplainttext.setBounds(106, 193, 238, 19);
	    contentPane1.add(lblcomplainttext);
	    
	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    scrollPane_1.setBounds(397, 193, 376, 64);
	    contentPane1.add(scrollPane_1);
	    
	    txtcomplainttext = new JTextArea();
	    scrollPane_1.setViewportView(txtcomplainttext);
	    
	    btnadd = new JButton("ADD DETAILS");
	    btnadd.setBounds(287, 284, 300, 31);
	    contentPane1.add(btnadd);
	    btnadd.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
	    btnadd.addActionListener(this);
	    
	    btngenerateid = new JButton("GENERATE ID");
	    btngenerateid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
	    btngenerateid.setBounds(746, 54, 232, 23);
	    btngenerateid.addActionListener(this);
	    contentPane.add(btngenerateid);
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
		JOptionPane.showMessageDialog(this,"Thank You");
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
