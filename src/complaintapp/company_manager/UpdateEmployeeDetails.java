package complaintapp.company_manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.database.DbConnection1;

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
		con=DbConnection1.createConnection();
		setTitle("UPDATE EMPLOYEE DETAILS");
		this.addWindowListener(this);
		createComponents();
	}
	
	JButton btnupdate,btnsearch;
	JTextArea txtaddress;
	JComboBox cmbexperience;
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 947, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UPDATE EMPLOYEE DETAILS PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(110, 28, 649, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblid = new JLabel("Enter Id:");
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblid.setBounds(45, 132, 156, 29);
		contentPane.add(lblid);
		
		txtid = new JTextField();
		txtid.setBounds(312, 132, 306, 29);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		btnsearch = new JButton("SEARCH");
		btnsearch.addActionListener(this);
		btnsearch.setBounds(670, 132, 194, 30);
		contentPane.add(btnsearch);
		
		JLabel lblpass = new JLabel("Password:");
		lblpass.setHorizontalAlignment(SwingConstants.CENTER);
		lblpass.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblpass.setBounds(81, 258, 187, 29);
		contentPane.add(lblpass);
		
		JLabel lblname = new JLabel("Name:");
		lblname.setHorizontalAlignment(SwingConstants.CENTER);
		lblname.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblname.setBounds(113, 298, 124, 29);
		contentPane.add(lblname);
		
		JLabel lblemptype = new JLabel("Employee Type:");
		lblemptype.setHorizontalAlignment(SwingConstants.CENTER);
		lblemptype.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblemptype.setForeground(new Color(0, 0, 0));
		lblemptype.setBounds(66, 213, 223, 22);
		contentPane.add(lblemptype);
		
		JLabel lbladdress = new JLabel("Address:");
		lbladdress.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lbladdress.setForeground(new Color(0, 0, 0));
		lbladdress.setHorizontalAlignment(SwingConstants.CENTER);
		lbladdress.setBounds(92, 338, 145, 29);
		contentPane.add(lbladdress);
		
		JLabel lblphone = new JLabel("Phone No:");
		lblphone.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblphone.setHorizontalAlignment(SwingConstants.CENTER);
		lblphone.setBounds(81, 378, 156, 29);
		contentPane.add(lblphone);
		
		JLabel lblemail = new JLabel("Email:");
		lblemail.setHorizontalAlignment(SwingConstants.CENTER);
		lblemail.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblemail.setBounds(82, 418, 155, 23);
		contentPane.add(lblemail);
		
		JLabel lblgender = new JLabel("Gender:");
		lblgender.setHorizontalAlignment(SwingConstants.CENTER);
		lblgender.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblgender.setBounds(92, 452, 129, 23);
		contentPane.add(lblgender);
		
		JLabel lblexperience = new JLabel("Experience:");
		lblexperience.setHorizontalAlignment(SwingConstants.CENTER);
		lblexperience.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblexperience.setBounds(75, 486, 176, 29);
		contentPane.add(lblexperience);
		
		txtemptype = new JTextField();
		txtemptype.setEnabled(false);
		txtemptype.setEditable(false);
		txtemptype.setBounds(347, 218, 271, 20);
		contentPane.add(txtemptype);
		txtemptype.setColumns(10);
		
		txtpass = new JTextField();
		txtpass.setBounds(347, 266, 271, 20);
		contentPane.add(txtpass);
		txtpass.setColumns(10);
		
		txtname = new JTextField();
		txtname.setBounds(347, 306, 283, 20);
		contentPane.add(txtname);
		txtname.addKeyListener(this);
		txtname.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setBounds(347, 386, 306, 20);
		contentPane.add(txtphone);
		txtphone.addKeyListener(this);
		txtphone.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(357, 423, 446, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		txtgender = new JTextField();
		txtgender.setEnabled(false);
		txtgender.setEditable(false);
		txtgender.setBounds(357, 457, 96, 20);
		contentPane.add(txtgender);
		txtgender.setColumns(10);
		
		cmbexperience = new JComboBox();
		cmbexperience.setModel(new DefaultComboBoxModel(new String[] {"SELECT:", "0-1 YEARS", "1-2 YEARS ", "2-3 YEARS", "3-4 YEARS", ">=5 YEARS"}));
		cmbexperience.setBounds(524, 493, 198, 22);
		contentPane.add(cmbexperience);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(347, 338, 406, 41);
		contentPane.add(scrollPane);
		
		txtaddress = new JTextArea();
		scrollPane.setColumnHeaderView(txtaddress);
		
		btnupdate = new JButton("UPDATE");
		btnupdate.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnupdate.setBounds(260, 538, 298, 42);
		btnupdate.addActionListener(this);
		contentPane.add(btnupdate);
		
		txtexp = new JTextField();
		txtexp.setBounds(347, 494, 145, 22);
		contentPane.add(txtexp);
		txtexp.setColumns(10);
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
		experience=txtexp.getText().trim();
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
		JOptionPane.showMessageDialog(this,"Thank YOU");
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
