package complaintapp.entry;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.database.DbConnection1;
import complaintapp.service_engineer.UpdateComplaintStatus;
import complaintapp.service_engineer.ViewAllAssigned_NotResolvedComplaints;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

import java.awt.event.*;
import java.sql.*;
import java.awt.Toolkit;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class EmployeeLogin extends JFrame implements ActionListener,WindowListener,KeyListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpassword;
	private final ButtonGroup companypost = new ButtonGroup();
	public static String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeLogin frame = new EmployeeLogin();
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
	
	public EmployeeLogin() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(EmployeeLogin.class.getResource("/complaintapp/images/logintitle.png")));
		con=DbConnection1.createConnection();
		setTitle("LOG-IN");
		this.addWindowListener(this);
		createComponents();
	}
	
	JRadioButton rdcm,rdcce,rdse;
	JCheckBox chkpass;
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(128, 0, 0), 10));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("ENTER ID:");
		lblid.setBorder(new LineBorder(new Color(0, 128, 128), 5));
		lblid.setOpaque(true);
		lblid.setBackground(new Color(220, 220, 220));
		lblid.setForeground(new Color(128, 0, 0));
		lblid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(131, 150, 280, 59);
		contentPane.add(lblid);
		
		JLabel lblpassword = new JLabel("PASSWORD:");
		lblpassword.setOpaque(true);
		lblpassword.setBorder(new LineBorder(new Color(0, 139, 139), 5));
		lblpassword.setForeground(new Color(128, 0, 0));
		lblpassword.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblpassword.setBackground(new Color(220, 220, 220));
		lblpassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblpassword.setBounds(131, 262, 280, 59);
		contentPane.add(lblpassword);
		
		txtid = new JTextField();
		txtid.setBackground(new Color(220, 220, 220));
		txtid.setForeground(new Color(255, 0, 0));
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setFont(new Font("Snap ITC", Font.ITALIC, 25));
		txtid.setBorder(new LineBorder(new Color(0, 139, 139), 5));
		txtid.setBounds(523, 150, 280, 49);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtpassword = new JPasswordField();
		txtpassword.setBackground(new Color(220, 220, 220));
		txtpassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtpassword.setForeground(new Color(184, 134, 11));
		txtpassword.setFont(new Font("Snap ITC", Font.ITALIC, 25));
		txtpassword.setBorder(new LineBorder(new Color(0, 139, 139), 5));
		txtpassword.setBounds(523, 265, 280, 56);
		contentPane.add(txtpassword);
		
		chkpass = new JCheckBox("see password");
		chkpass.setBorder(new LineBorder(new Color(0, 128, 128), 3, true));
		chkpass.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 12));
		chkpass.setBackground(new Color(240, 248, 255));
		chkpass.setBounds(523, 325, 141, 23);
		chkpass.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) 
					{
						if(chkpass.isSelected())
						{
							txtpassword.setEchoChar((char)0);
						}
						else
						{
							txtpassword.setEchoChar('*');
						}
					}
			
				});
		contentPane.add(chkpass);
		
		rdcm = new JRadioButton("COMPANY MANAGER");
		rdcm.setForeground(new Color(128, 0, 0));
		rdcm.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		rdcm.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		rdcm.setBackground(new Color(220, 220, 220));
		rdcm.setHorizontalAlignment(SwingConstants.CENTER);
		companypost.add(rdcm);
		rdcm.setBounds(43, 383, 234, 41);
		contentPane.add(rdcm);
		
		rdcce = new JRadioButton("CUSTOMER CARE EXECUTIVE");
		rdcce.setForeground(new Color(128, 0, 0));
		rdcce.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		rdcce.setBorder(new LineBorder(new Color(0, 139, 139), 3));
		rdcce.setBackground(new Color(220, 220, 220));
		companypost.add(rdcce);
		rdcce.setBounds(302, 383, 322, 41);
		contentPane.add(rdcce);
		
		rdse = new JRadioButton("SERVICE ENGINEER");
		rdse.setForeground(new Color(128, 0, 0));
		rdse.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 15));
		rdse.setBackground(new Color(220, 220, 220));
		companypost.add(rdse);
		rdse.setBounds(646, 383, 225, 41);
		contentPane.add(rdse);
		
		JButton btnlogin = new JButton("LOG-IN");
		btnlogin.setBackground(new Color(192, 192, 192));
		btnlogin.setBorder(new LineBorder(new Color(0, 139, 139), 5));
		btnlogin.setForeground(new Color(128, 0, 0));
		btnlogin.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 30));
		btnlogin.setBounds(281, 473, 366, 78);
		btnlogin.addActionListener(this);
		contentPane.add(btnlogin);
		
		JLabel lblNewLabel = new JLabel("LOGIN PAGE");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 139, 139), 5));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(220, 220, 220));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(239, 37, 408, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(EmployeeLogin.class.getResource("/complaintapp/images/stock-photo-paper-tear-background-with-word-complaints-776659318.jpg")));
		lblNewLabel_1.setBounds(10, 11, 917, 577);
		contentPane.add(lblNewLabel_1);
	}
	
	public String password,emptype;

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		id=txtid.getText().trim();
		char[]pass=txtpassword.getPassword();  //extracting password as character array and converting into string type
		password=String.valueOf(pass);
		
		emptype="";
		if(rdcm.isSelected())
			emptype=rdcm.getText();
		if(rdcce.isSelected())
			emptype=rdcce.getText();
		if(rdse.isSelected())
			emptype=rdse.getText();
		
		if(!checkValidation())
		{
			if(id.equalsIgnoreCase("GAURAV") && password.equalsIgnoreCase("7786@GAuv#") && emptype.equals("COMPANY MANAGER"))
			{
				JOptionPane.showMessageDialog(this,"Welcome COMPANY MANAGER","LOGIN-IN SUCCESSFULL",JOptionPane.PLAIN_MESSAGE);
				txtid.setText("");
				txtpassword.setText("");
				companypost.clearSelection();
			}
			else if(emptype.equals("CUSTOMER CARE EXECUTIVE") && isPresent())
			{
				JOptionPane.showMessageDialog(this,"Welcome CUSTOMER CARE EXECUTIVE","LOGIN-IN SUCCESSFULL",JOptionPane.PLAIN_MESSAGE);
				txtid.setText("");
				txtpassword.setText("");
				companypost.clearSelection();
			}
			else if(emptype.equals("SERVICE ENGINEER") && isPresent())
			{
				JOptionPane.showMessageDialog(this,"Welcome SERVICE ENGINEER","LOGIN-IN SUCCESSFULL",JOptionPane.PLAIN_MESSAGE);
				//new ViewAllAssignedComplaints().setVisible(true);
				new UpdateComplaintStatus().setVisible(true);
				txtid.setText("");
				txtpassword.setText("");
				companypost.clearSelection();
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this,"INVALID USER-ID/PASSWORD/EMPLOYEETYPE","LOGIN UNSUCCESSFULL",JOptionPane.ERROR_MESSAGE);
				txtid.setText("");
				txtpassword.setText("");
				companypost.clearSelection();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"USER-ID/PASSWORD/EMPLOYEETYPE REQUIRED","MANDATORY",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean checkValidation()
	{
		if(id.isEmpty() || password.isEmpty() || emptype.isEmpty())
			return true;
		return false;
	}
	
	public boolean isPresent()
	{
		String strpresent="select * from employee where EmployeeId=? && Password=? && EmployeeType=?";
		try
		{
			ps=con.prepareStatement(strpresent);
			ps.setString(1,id);
			ps.setString(2,password);
			ps.setString(3,emptype);
			rs=ps.executeQuery();
			
			if(rs.next())
				return true;
		}
		catch(SQLException s)
		{
			s.printStackTrace();
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
			catch(SQLException sw)
			{
				sw.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
		JOptionPane.showMessageDialog(this,"THANK YOU");
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
