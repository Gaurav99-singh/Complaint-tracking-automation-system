package complaintapp.company_manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.database.DbConnection1;
import complaintapp.entry.CompanyManagerAdmin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

import java.awt.event.*;
import java.sql.*;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class UpdateEmployeeDetails extends JFrame implements ActionListener,WindowListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtemptype;
	private JTextField txtpass;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JTextField txtgender;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmployeeDetails frame = new UpdateEmployeeDetails();
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
	
	public UpdateEmployeeDetails() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateEmployeeDetails.class.getResource("/complaintapp/images/contactupdate.png")));
		con=DbConnection1.createConnection();
		setTitle("UPDATE EMPLOYEE DETAILS");
		this.addWindowListener(this);
		createComponents();
	}
	
	JButton btnupdate,btnsearch;
	JComboBox cmbexperience;
	JTextArea txtaddress;
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 972, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UPDATE EMPLOYEE DETAILS PAGE");
		lblNewLabel.setBorder(new LineBorder(new Color(189, 183, 107), 4, true));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(102, 205, 170));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBounds(131, 11, 649, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblid = new JLabel("Enter Id:");
		lblid.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblid.setOpaque(true);
		lblid.setBackground(new Color(169, 169, 169));
		lblid.setForeground(new Color(128, 0, 0));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblid.setBounds(45, 82, 223, 35);
		contentPane.add(lblid);
		
		txtid = new JTextField();
		txtid.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtid.setBackground(new Color(240, 248, 255));
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setForeground(new Color(255, 69, 0));
		txtid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtid.setBounds(314, 82, 326, 35);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		btnsearch = new JButton("SEARCH");
		btnsearch.setForeground(new Color(178, 34, 34));
		btnsearch.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnsearch.setBorder(new LineBorder(new Color(220, 20, 60), 5));
		btnsearch.setBackground(new Color(0, 128, 128));
		btnsearch.addActionListener(this);
		btnsearch.setBounds(685, 82, 208, 41);
		contentPane.add(btnsearch);
		
		JLabel lblpass = new JLabel("Password:");
		lblpass.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblpass.setOpaque(true);
		lblpass.setBackground(new Color(169, 169, 169));
		lblpass.setForeground(new Color(128, 0, 0));
		lblpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblpass.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblpass.setBounds(45, 186, 223, 35);
		contentPane.add(lblpass);
		
		JLabel lblname = new JLabel("Name:");
		lblname.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblname.setOpaque(true);
		lblname.setBackground(new Color(169, 169, 169));
		lblname.setForeground(new Color(128, 0, 0));
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblname.setBounds(45, 232, 223, 35);
		contentPane.add(lblname);
		
		JLabel lblemptype = new JLabel("Employee Type:");
		lblemptype.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblemptype.setOpaque(true);
		lblemptype.setBackground(new Color(169, 169, 169));
		lblemptype.setForeground(new Color(128, 0, 0));
		lblemptype.setHorizontalAlignment(SwingConstants.CENTER);
		lblemptype.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblemptype.setBounds(45, 134, 223, 35);
		contentPane.add(lblemptype);
		
		JLabel lbladdress = new JLabel("Address:");
		lbladdress.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lbladdress.setOpaque(true);
		lbladdress.setBackground(new Color(169, 169, 169));
		lbladdress.setForeground(new Color(128, 0, 0));
		lbladdress.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lbladdress.setHorizontalAlignment(SwingConstants.CENTER);
		lbladdress.setBounds(45, 282, 223, 35);
		contentPane.add(lbladdress);
		
		JLabel lblphone = new JLabel("Phone No:");
		lblphone.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblphone.setOpaque(true);
		lblphone.setBackground(new Color(169, 169, 169));
		lblphone.setForeground(new Color(128, 0, 0));
		lblphone.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblphone.setHorizontalAlignment(SwingConstants.CENTER);
		lblphone.setBounds(45, 360, 223, 35);
		contentPane.add(lblphone);
		
		JLabel lblemail = new JLabel("Email:");
		lblemail.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblemail.setOpaque(true);
		lblemail.setBackground(new Color(169, 169, 169));
		lblemail.setForeground(new Color(128, 0, 0));
		lblemail.setHorizontalAlignment(SwingConstants.CENTER);
		lblemail.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblemail.setBounds(45, 406, 223, 35);
		contentPane.add(lblemail);
		
		JLabel lblgender = new JLabel("Gender:");
		lblgender.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblgender.setOpaque(true);
		lblgender.setBackground(new Color(169, 169, 169));
		lblgender.setForeground(new Color(128, 0, 0));
		lblgender.setHorizontalAlignment(SwingConstants.CENTER);
		lblgender.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblgender.setBounds(45, 452, 224, 35);
		contentPane.add(lblgender);
		
		JLabel lblexperience = new JLabel("Experience:");
		lblexperience.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblexperience.setOpaque(true);
		lblexperience.setBackground(new Color(169, 169, 169));
		lblexperience.setForeground(new Color(128, 0, 0));
		lblexperience.setHorizontalAlignment(SwingConstants.CENTER);
		lblexperience.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblexperience.setBounds(45, 498, 223, 35);
		contentPane.add(lblexperience);
		
		txtemptype = new JTextField();
		txtemptype.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtemptype.setBackground(new Color(240, 248, 255));
		txtemptype.setHorizontalAlignment(SwingConstants.CENTER);
		txtemptype.setForeground(new Color(255, 69, 0));
		txtemptype.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtemptype.setEnabled(false);
		txtemptype.setEditable(false);
		txtemptype.setBounds(314, 134, 326, 35);
		contentPane.add(txtemptype);
		txtemptype.setColumns(10);
		
		txtpass = new JTextField();
		txtpass.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtpass.setBackground(new Color(240, 248, 255));
		txtpass.setHorizontalAlignment(SwingConstants.CENTER);
		txtpass.setForeground(new Color(255, 69, 0));
		txtpass.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtpass.setBounds(314, 190, 326, 35);
		contentPane.add(txtpass);
		txtpass.setColumns(10);
		
		txtname = new JTextField();
		txtname.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtname.setBackground(new Color(240, 248, 255));
		txtname.setHorizontalAlignment(SwingConstants.CENTER);
		txtname.setForeground(new Color(255, 69, 0));
		txtname.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtname.setBounds(314, 239, 326, 35);
		contentPane.add(txtname);
		txtname.addKeyListener(this);
		txtname.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtphone.setBackground(new Color(240, 248, 255));
		txtphone.setHorizontalAlignment(SwingConstants.CENTER);
		txtphone.setForeground(new Color(255, 69, 0));
		txtphone.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtphone.setBounds(314, 363, 332, 35);
		contentPane.add(txtphone);
		txtphone.addKeyListener(this);
		txtphone.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtemail.setBackground(new Color(240, 248, 255));
		txtemail.setHorizontalAlignment(SwingConstants.CENTER);
		txtemail.setForeground(new Color(255, 69, 0));
		txtemail.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtemail.setBounds(314, 410, 332, 35);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		txtgender = new JTextField();
		txtgender.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtgender.setBackground(new Color(240, 248, 255));
		txtgender.setHorizontalAlignment(SwingConstants.CENTER);
		txtgender.setForeground(new Color(255, 69, 0));
		txtgender.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtgender.setEnabled(false);
		txtgender.setEditable(false);
		txtgender.setBounds(314, 455, 218, 35);
		contentPane.add(txtgender);
		txtgender.setColumns(10);
		
		cmbexperience = new JComboBox();
		cmbexperience.setForeground(new Color(178, 34, 34));
		cmbexperience.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		cmbexperience.setBorder(new LineBorder(new Color(220, 20, 60), 5));
		cmbexperience.setBackground(new Color(0, 139, 139));
		cmbexperience.setModel(new DefaultComboBoxModel(new String[] {"SELECT:", "0-1 YEARS", "1-2 YEARS ", "2-3 YEARS", "3-4 YEARS", ">=5 YEARS"}));
		cmbexperience.setBounds(548, 496, 306, 35);
		contentPane.add(cmbexperience);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(314, 288, 486, 60);
		contentPane.add(scrollPane);
		
		txtaddress = new JTextArea();
		txtaddress.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtaddress.setBackground(new Color(240, 248, 255));
		txtaddress.setForeground(new Color(255, 69, 0));
		txtaddress.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		scrollPane.setViewportView(txtaddress);
		
		btnupdate = new JButton("UPDATE");
		btnupdate.setForeground(new Color(178, 34, 34));
		btnupdate.setBorder(new LineBorder(new Color(220, 20, 60), 5));
		btnupdate.setBackground(new Color(0, 128, 128));
		btnupdate.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnupdate.setBounds(251, 544, 298, 59);
		btnupdate.addActionListener(this);
		contentPane.add(btnupdate);
		
		txtexp = new JTextField();
		txtexp.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtexp.setBackground(new Color(240, 248, 255));
		txtexp.setHorizontalAlignment(SwingConstants.CENTER);
		txtexp.setForeground(new Color(255, 69, 0));
		txtexp.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtexp.setEnabled(false);
		txtexp.setEditable(false);
		txtexp.setBounds(314, 499, 198, 35);
		contentPane.add(txtexp);
		txtexp.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(UpdateEmployeeDetails.class.getResource("/complaintapp/images/update-software-computer-program-upgrade-business-technology-internet-concept-update-software-computer-program-upgrade-business-102191028.jpg")));
		lblNewLabel_1.setBounds(0, 0, 958, 614);
		contentPane.add(lblNewLabel_1);
	}
	
	public boolean checkEmpty()
	{
		if(id.isEmpty())
			return true;
		return false;
	}
	
	public boolean searchId()
	{
		String strearch="select * from employee where EmployeeId=?";
		try
		{
			ps=con.prepareStatement(strearch);
			ps.setString(1,txtid.getText());
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				txtemptype.setText(rs.getString("EmployeeType"));
				txtpass.setText(rs.getString("Password"));
				txtname.setText(rs.getString("Name"));
				txtaddress.setText(rs.getString("Address"));
				txtphone.setText(rs.getString("PhoneNo"));
				txtemail.setText(rs.getString("Email"));
				txtgender.setText(rs.getString("Gender"));
				txtexp.setText(rs.getString("Experience"));
				return true;
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException s)
			{
				s.printStackTrace();
			}
		}
		return false;
	}
	
	String pass,id,name,address,phone,email,experience;
	private JTextField txtexp;
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		pass=txtpass.getText().trim();
		id=txtid.getText().trim();
		name=txtname.getText().trim();
		address=txtaddress.getText().trim();
		phone=txtphone.getText().trim();
		email=txtemail.getText().trim();
		experience=(String) cmbexperience.getSelectedItem();
		
		Object obj=e.getSource();
		JButton jb=(JButton)obj;
		
		if(jb == btnsearch)
		{
			if(!(checkEmpty()))
			{
				if(searchId())
				{
					JOptionPane.showMessageDialog(this,"Data Found");
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No SuchId exists","ERROR",JOptionPane.ERROR_MESSAGE);
					txtid.setText("");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Enter ID","Mandatory",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(jb == btnupdate)
		{
			if(!(checkEmpty()))
			{
				if(!(checkEmptyAfterSearch()))
				{
					updateData();
					setFieldsEmpty();
				}
				else
				{
					JOptionPane.showMessageDialog(this,"Fill all the fields","Mandatory",JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Enter ID","Mandatory",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void setFieldsEmpty()
	{
		txtid.setText("");
		txtpass.setText("");
		txtname.setText("");
		txtaddress.setText("");
		txtemptype.setText("");
		txtgender.setText("");
		txtemail.setText("");
		txtphone.setText("");
		txtexp.setText("");
		cmbexperience.setModel(new DefaultComboBoxModel(new String[] {"SELECT:", "0-1 YEARS", "1-2 YEARS ", "2-3 YEARS", "3-4 YEARS", ">=5 YEARS"}));
	
	}
	
	public void updateData()
	{
		String strupdate="update employee set Password=?,Name=?,Address=?,PhoneNo=?,Email=?,Experience=? where EmployeeId=?";
		try 
		{
			ps=con.prepareStatement(strupdate);
			ps.setString(1,pass);
			ps.setString(2,name);
			ps.setString(3,address);
			ps.setString(4,phone);
			ps.setString(5,email);
			ps.setString(6,experience);
			ps.setString(7,id);
			int status=ps.executeUpdate();
			if(status>0)
			{
				JOptionPane.showMessageDialog(this,"Employee data Updated Successfully");
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
			}
			catch(SQLException ssd)
			{
				ssd.printStackTrace();
			}
		}
	}
	
	
	public boolean checkEmptyAfterSearch()
	{
		if(pass.isEmpty() || name.isEmpty() ||address.isEmpty() ||phone.isEmpty()||email.isEmpty()||experience.isEmpty())
			return true;
		
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
