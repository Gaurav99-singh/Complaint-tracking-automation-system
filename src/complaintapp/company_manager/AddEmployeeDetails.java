package complaintapp.company_manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.checkvalidationcode.*;
import complaintapp.database.DbConnection1;
import complaintapp.entry.CompanyManagerAdmin;

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
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

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
		setBounds(100, 100, 1063, 668);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(128, 0, 0), 10));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblname = new JLabel("name:");
		lblname.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		lblname.setOpaque(true);
		lblname.setForeground(new Color(165, 42, 42));
		lblname.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		lblname.setBackground(new Color(245, 222, 179));
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setBounds(113, 230, 203, 41);
		contentPane.add(lblname);
		
		txtname = new JTextField();
		txtname.setHorizontalAlignment(SwingConstants.CENTER);
		txtname.setForeground(new Color(0, 100, 0));
		txtname.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtname.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		txtname.setBackground(new Color(245, 222, 179));
		txtname.setToolTipText("Name must contain only alphabets.\r\nEx->JOHN");
		txtname.setBounds(392, 231, 279, 44);
		contentPane.add(txtname);
		txtname.addKeyListener(this);
		txtname.setColumns(10);
		
		JLabel lbladdress = new JLabel("address:");
		lbladdress.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		lbladdress.setOpaque(true);
		lbladdress.setForeground(new Color(165, 42, 42));
		lbladdress.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		lbladdress.setBackground(new Color(245, 222, 179));
		lbladdress.setHorizontalAlignment(SwingConstants.CENTER);
		lbladdress.setBounds(113, 299, 203, 41);
		contentPane.add(lbladdress);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(392, 288, 423, 77);
		contentPane.add(scrollPane);
		
		txtaddress = new JTextArea();
		txtaddress.setForeground(new Color(0, 100, 0));
		txtaddress.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtaddress.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		txtaddress.setBackground(new Color(245, 222, 179));
		scrollPane.setViewportView(txtaddress);
		
		JLabel lblphone = new JLabel("phone no:");
		lblphone.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		lblphone.setOpaque(true);
		lblphone.setForeground(new Color(165, 42, 42));
		lblphone.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		lblphone.setBackground(new Color(245, 222, 179));
		lblphone.setHorizontalAlignment(SwingConstants.CENTER);
		lblphone.setBounds(113, 379, 203, 41);
		contentPane.add(lblphone);
		
		txtphone = new JTextField();
		txtphone.setHorizontalAlignment(SwingConstants.CENTER);
		txtphone.setForeground(new Color(0, 100, 0));
		txtphone.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtphone.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		txtphone.setBackground(new Color(245, 222, 179));
		txtphone.setToolTipText("Phone number must contain only 10 digits.");
		txtphone.setBounds(392, 376, 279, 44);
		contentPane.add(txtphone);
		txtphone.addKeyListener(this);
		txtphone.setColumns(10);
		
		JLabel lblemail = new JLabel("email:");
		lblemail.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		lblemail.setOpaque(true);
		lblemail.setForeground(new Color(165, 42, 42));
		lblemail.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		lblemail.setBackground(new Color(245, 222, 179));
		lblemail.setHorizontalAlignment(SwingConstants.CENTER);
		lblemail.setBounds(113, 443, 203, 44);
		contentPane.add(lblemail);
		
		txtemail = new JTextField();
		txtemail.setHorizontalAlignment(SwingConstants.CENTER);
		txtemail.setForeground(new Color(0, 100, 0));
		txtemail.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtemail.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		txtemail.setBackground(new Color(245, 222, 179));
		txtemail.setToolTipText("Email Id format->xyz@gmail.com");
		txtemail.setBounds(392, 443, 279, 44);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblexperience = new JLabel("Experience:");
		lblexperience.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		lblexperience.setOpaque(true);
		lblexperience.setForeground(new Color(165, 42, 42));
		lblexperience.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		lblexperience.setBackground(new Color(245, 222, 179));
		lblexperience.setHorizontalAlignment(SwingConstants.CENTER);
		lblexperience.setBounds(113, 504, 203, 43);
		contentPane.add(lblexperience);
		
		btnmale = new JRadioButton("Male");
		btnmale.setForeground(new Color(165, 42, 42));
		btnmale.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		btnmale.setBackground(new Color(233, 150, 122));
		gender.add(btnmale);
		btnmale.setBounds(113, 554, 192, 42);
		contentPane.add(btnmale);
		
	    btnfemale = new JRadioButton("Female");
	    btnfemale.setForeground(new Color(165, 42, 42));
	    btnfemale.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
	    btnfemale.setBorder(new LineBorder(new Color(95, 158, 160), 4, true));
	    btnfemale.setBackground(new Color(233, 150, 122));
		gender.add(btnfemale);
		btnfemale.setBounds(393, 555, 192, 41);
		contentPane.add(btnfemale);
		
		btnadd = new JButton("SUBMIT");
		btnadd.setBackground(new Color(169, 169, 169));
		btnadd.setForeground(new Color(178, 34, 34));
		btnadd.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 25));
		btnadd.setBounds(733, 551, 237, 44);
		btnadd.addActionListener(this);
		contentPane.add(btnadd);
		
		cmbcategory = new JComboBox();
		cmbcategory.setBackground(new Color(72, 209, 204));
		cmbcategory.setBorder(new LineBorder(new Color(188, 143, 143), 3, true));
		cmbcategory.setModel(new DefaultComboBoxModel(new String[] {"SELECT CATEGORY", "CUSTOMER CARE EXECUTIVE", "SERVICE ENGINEER"}));
		cmbcategory.setBounds(684, 101, 243, 43);
		cmbcategory.addActionListener(this);
		contentPane.add(cmbcategory);
		
		JLabel lblid = new JLabel("EMPLOYEE ID:");
		lblid.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		lblid.setOpaque(true);
		lblid.setForeground(new Color(165, 42, 42));
		lblid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		lblid.setBackground(new Color(245, 222, 179));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(113, 100, 203, 44);
		contentPane.add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setForeground(new Color(0, 100, 0));
		txtid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtid.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		txtid.setBackground(new Color(245, 222, 179));
		txtid.setEnabled(false);
		txtid.setEditable(false);
		txtid.setToolTipText("Id format \r\nCustomer Care Executive-->CCE01,CCE02....  \r\nService Engineer-->SE01,SE01......");
		txtid.setBounds(392, 101, 279, 43);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblpassword = new JLabel("Password:");
		lblpassword.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		lblpassword.setOpaque(true);
		lblpassword.setForeground(new Color(165, 42, 42));
		lblpassword.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		lblpassword.setBackground(new Color(245, 222, 179));
		lblpassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblpassword.setBounds(113, 165, 203, 44);
		contentPane.add(lblpassword);
		
		txtpassword = new JPasswordField();
		txtpassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtpassword.setForeground(new Color(0, 100, 0));
		txtpassword.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtpassword.setBorder(new LineBorder(new Color(119, 136, 153), 3, true));
		txtpassword.setBackground(new Color(245, 222, 179));
		txtpassword.setToolTipText("PASSWORD must contain atleast 8 digits.");
		txtpassword.setBounds(392, 165, 279, 44);
		contentPane.add(txtpassword);
		
		cmbexperience = new JComboBox();
		cmbexperience.setBackground(new Color(72, 209, 204));
		cmbexperience.setBorder(new LineBorder(new Color(188, 143, 143), 3, true));
		cmbexperience.setModel(new DefaultComboBoxModel(new String[] {"SELECT", "0-1  years", "1-2  years", "2-3  years", "3-4 years", ">=5 years"}));
		cmbexperience.setBounds(393, 504, 278, 43);
		contentPane.add(cmbexperience);
		
		JLabel lblNewLabel = new JLabel("ADD EMPLOYEE DETAILS PAGE");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 128, 128), 4, true));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(189, 183, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(112, 29, 844, 61);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AddEmployeeDetails.class.getResource("/complaintapp/images/writing-note-showing-fill-details-business-photo-showcasing-add-information-empty-space-document-concept-165196319.jpg")));
		lblNewLabel_1.setBounds(10, 11, 1029, 608);
		contentPane.add(lblNewLabel_1);
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
