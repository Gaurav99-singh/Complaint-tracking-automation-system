package complaintapp.company_manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.checkvalidationcode.*;
import complaintapp.database.DbConnection1;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import java.sql.*;
import java.awt.event.*;
import java.awt.Color;

public class AddEmployeeDetails extends JFrame implements ActionListener,KeyListener,WindowListener
{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private final ButtonGroup gender = new ButtonGroup();
	private JTextField txtid;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployeeDetails frame = new AddEmployeeDetails();
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
	
	private JTextArea txtaddress;
	private JComboBox cmbexperience,cmbcategory;
	private JRadioButton btnfemale,btnmale;
	
	CheckIfEmployeeExists cie;
	
	public AddEmployeeDetails()
	{
		setResizable(false);
		con=DbConnection1.createConnection();
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddEmployeeDetails.class.getResource("/complaintapp/images/adddetailstitle.png")));
		setTitle("ADD EMPLOYEE DETAILS");
		this.addWindowListener(this);
		createComponents();
	}
	
	int count = 0;
	public int fillId()
	{
		
		String strsee="Select * from employee where EmployeeType=?";
		try
		{
			ps=con.prepareStatement(strsee);
			ps.setString(1, category);
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
	
	private JButton btnadd;
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 988, 589);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblname = new JLabel("name:");
		lblname.setBounds(147, 196, 120, 23);
		contentPane.add(lblname);
		
		txtname = new JTextField();
		txtname.setToolTipText("Name must contain only alphabets.\r\nEx->JOHN");
		txtname.setBounds(351, 197, 96, 20);
		contentPane.add(txtname);
		txtname.addKeyListener(this);
		txtname.setColumns(10);
		
		JLabel lbladdress = new JLabel("address:");
		lbladdress.setBounds(147, 270, 76, 14);
		contentPane.add(lbladdress);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(309, 242, 279, 77);
		contentPane.add(scrollPane);
		
		txtaddress = new JTextArea();
		scrollPane.setViewportView(txtaddress);
		
		JLabel lblphone = new JLabel("phone no:");
		lblphone.setBounds(147, 350, 49, 14);
		contentPane.add(lblphone);
		
		txtphone = new JTextField();
		txtphone.setToolTipText("Phone number must contain only 10 digits.");
		txtphone.setBounds(304, 347, 96, 20);
		contentPane.add(txtphone);
		txtphone.addKeyListener(this);
		txtphone.setColumns(10);
		
		JLabel lblemail = new JLabel("email:");
		lblemail.setBounds(147, 399, 76, 14);
		contentPane.add(lblemail);
		
		txtemail = new JTextField();
		txtemail.setToolTipText("Email Id format->xyz@gmail.com");
		txtemail.setBounds(383, 397, 121, 17);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblexperience = new JLabel("Experience:");
		lblexperience.setHorizontalAlignment(SwingConstants.CENTER);
		lblexperience.setBounds(107, 431, 136, 14);
		contentPane.add(lblexperience);
		
		btnmale = new JRadioButton("Male");
		gender.add(btnmale);
		btnmale.setBounds(314, 470, 111, 23);
		contentPane.add(btnmale);
		
	    btnfemale = new JRadioButton("Female");
		gender.add(btnfemale);
		btnfemale.setBounds(526, 470, 111, 23);
		contentPane.add(btnfemale);
		
		btnadd = new JButton("ADD");
		btnadd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnadd.setBounds(362, 507, 178, 44);
		btnadd.addActionListener(this);
		contentPane.add(btnadd);
		
		cmbcategory = new JComboBox();
		cmbcategory.setModel(new DefaultComboBoxModel(new String[] {"SELECT CATEGORY", "CUSTOMER CARE EXECUTIVE", "SERVICE ENGINEER"}));
		cmbcategory.setBounds(560, 103, 243, 22);
		cmbcategory.addActionListener(this);
		contentPane.add(cmbcategory);
		
		JLabel ilbid = new JLabel("ID:");
		ilbid.setHorizontalAlignment(SwingConstants.CENTER);
		ilbid.setBounds(84, 103, 96, 14);
		contentPane.add(ilbid);
		
		txtid = new JTextField();
		txtid.setEnabled(false);
		txtid.setEditable(false);
		txtid.setToolTipText("Id format \r\nCustomer Care Executive-->CCE01,CCE02....  \r\nService Engineer-->SE01,SE01......");
		txtid.setBounds(248, 93, 96, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblpassword = new JLabel("Password:");
		lblpassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblpassword.setBounds(84, 124, 96, 20);
		contentPane.add(lblpassword);
		
		txtpassword = new JPasswordField();
		txtpassword.setToolTipText("PASSWORD must contain atleast 8 digits.");
		txtpassword.setBounds(248, 124, 120, 20);
		contentPane.add(txtpassword);
		
		cmbexperience = new JComboBox();
		cmbexperience.setModel(new DefaultComboBoxModel(new String[] {"SELECT", "0-1  years", "1-2  years", "2-3  years", "3-4 years", ">=5 years"}));
		cmbexperience.setBounds(362, 427, 214, 22);
		contentPane.add(cmbexperience);
		
		JLabel lblNewLabel = new JLabel("ADD EMPLOYEE DETAILS PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(216, 11, 487, 44);
		contentPane.add(lblNewLabel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == cmbcategory)
		{
			category=(String) cmbcategory.getSelectedItem();
			if(category.equalsIgnoreCase("CUSTOMER CARE EXECUTIVE"))
			{
				int i=fillId();
				i++;
				String formatted=String.format("%03d",i);
				id="CCE"+formatted;
				txtid.setText(id);
			}
			if(category.equalsIgnoreCase("SERVICE ENGINEER"))
			{
				int i=fillId();
				i++;
				String formatted=String.format("%03d",i);
				id="SE"+formatted;
				txtid.setText(id);
			}
		}
		
		if(e.getSource() == btnadd)
		{
			if(checkValidation())
			{
				if(password.length() >= 8)
				{
					if(phone.length() == 10)
					{
						if(!ispresent())
						{
							String strsql="insert into employee(EmployeeId, Password, EmployeeType, Name, Address, PhoneNo, Email,Gender, Experience) values(?,?,?,?,?,?,?,?,?)";
							try
							{
								ps=con.prepareStatement(strsql);
								ps.setString(1,id);
								ps.setString(2,password);
								ps.setString(3,category);
								ps.setString(4,name);
								ps.setString(5,address);
								ps.setString(6,phone);
								ps.setString(7,email);
								ps.setString(8,genderData);
								ps.setString(9,experience);
								
				                int row_status=ps.executeUpdate();
								
								if(row_status > 0)
								{
									JOptionPane.showMessageDialog(this, "Data Added","Add Employee Details",JOptionPane.INFORMATION_MESSAGE);
									
									txtid.setText("");
									txtpassword.setText("");
									txtname.setText("");
									txtemail.setText("");
									txtphone.setText("");
									txtaddress.setText("");
									gender.clearSelection();
									cmbcategory.setModel(new DefaultComboBoxModel(new String[] {"SELECT CATEGORY", "CUSTOMER CARE EXECUTIVE", "SERVICE ENGINEER"}));
									cmbexperience.setModel(new DefaultComboBoxModel(new String[] {"SELECT", "0-1  years", "1-2  years", "2-3  years", "3-4 years", ">=5 years"}));
									
								}
							}
							catch(SQLException se)
							{
								se.printStackTrace();
							}
							finally
							{
								if(ps!=null)
									try {
										ps.close();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							}
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
					JOptionPane.showMessageDialog(this,"Password must contain atleast 8 digits","MANDATORY",JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else
			{
				JOptionPane.showMessageDialog(this,"Fill All fields","MANDATORY",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public boolean ispresent()
	{
		cie=new CheckIfEmployeeExists(id,password,phone,email);
		int flag=0;
		if(cie.checkEmployeeid()==1)
		{
			flag=1;
			JOptionPane.showMessageDialog(this,"EmployeeId exists.Please Enter another","ERROR",JOptionPane.ERROR_MESSAGE);
			txtid.setText("");
		}
		if(cie.checkEmployeepass()==2)
		{
			flag=1;
			JOptionPane.showMessageDialog(this,"Password exists.Please Enter another","ERROR",JOptionPane.ERROR_MESSAGE);
			txtpassword.setText("");
		}
		if(cie.checkEmployeephone()==3)
		{
			flag=1;
			JOptionPane.showMessageDialog(this,"PhoneNo exists.Please Enter another","ERROR",JOptionPane.ERROR_MESSAGE);
			txtphone.setText("");
		}
		if(cie.checkEmployeeEmail()==4)
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
	
	private String id,password,name,address,phone,email,category,experience,genderData;
	public boolean checkValidation()
	{
        id=txtid.getText().trim();  //getting text from id 
		
		char[]pass=txtpassword.getPassword();    //getting password and converting character into string 
		password=String.valueOf(pass);
		System.out.println(password);
		
		name=txtname.getText().trim();
		address=txtaddress.getText();
		phone=txtphone.getText().trim();
		email=txtemail.getText().trim();
		
		category=(String)cmbcategory.getSelectedItem();
		experience=(String)cmbexperience.getSelectedItem();//cmbox returns object so convert into preferred type
		
		genderData="";
		if(btnmale.isSelected())
			genderData=btnmale.getText();
		if(btnfemale.isSelected())
			genderData=btnfemale.getText();
		
		if(id.isEmpty() || password.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty() || email.isEmpty() ||
				category.equalsIgnoreCase("Select category") || experience.equalsIgnoreCase("Select") ||
				genderData.isEmpty())
		{
			return false;
		}
		
		else
		{
			return true;
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		DbConnection1.closeConnection();
		JOptionPane.showMessageDialog(this,"Thank you");
		
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
	public void keyPressed(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
