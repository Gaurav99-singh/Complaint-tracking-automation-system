package complaintapp.service_engineer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.beans.SeeComplaintsBean;
import complaintapp.database.DbConnection1;
import complaintapp.entry.EmployeeLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.sql.*;
import java.awt.event.*;
import javax.swing.ScrollPaneConstants;

public class UpdateComplaintStatus extends JFrame implements ActionListener,WindowListener,KeyListener
{

	private JPanel contentPane;
	private JTextField txtcomplaintid;
	private JTextField txtcustomername;
	private JTextField txtphone;
	private JTextField txtaddress;
	private JTextField txtprodname;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Connection con;
	private PreparedStatement ps,ps1;
	private ResultSet rs,rs1;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateComplaintStatus frame = new UpdateComplaintStatus();
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
	public UpdateComplaintStatus()
	{
		con=DbConnection1.createConnection();
		this.addWindowListener(this);
		setTitle("CHANGE COMPLAINT STATUS");
		createComponents();
	}
	
	JButton btnupdate,btnsearch;
	JTextArea txtcomptext,txtremarks; 
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 999, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHANGE STATUS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(281, 29, 470, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblcomplaintid = new JLabel("ENTER COMPLAINT ID:");
		lblcomplaintid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomplaintid.setBounds(22, 114, 338, 34);
		contentPane.add(lblcomplaintid);
		
		txtcomplaintid = new JTextField();
		txtcomplaintid.setBounds(408, 118, 310, 34);
		contentPane.add(txtcomplaintid);
		txtcomplaintid.setColumns(10);
		
		JLabel lblcustomername = new JLabel("CUSTOMER NAME:");
		lblcustomername.setHorizontalAlignment(SwingConstants.CENTER);
		lblcustomername.setBounds(43, 267, 96, 14);
		contentPane.add(lblcustomername);
		
		JLabel lblphone = new JLabel("PHONE NO:");
		lblphone.setHorizontalAlignment(SwingConstants.CENTER);
		lblphone.setBounds(54, 344, 70, 14);
		contentPane.add(lblphone);
		
		JLabel lbladdress = new JLabel("ADDRESS:");
		lbladdress.setHorizontalAlignment(SwingConstants.CENTER);
		lbladdress.setBounds(43, 417, 81, 14);
		contentPane.add(lbladdress);
		
		txtcustomername = new JTextField();
		txtcustomername.setEnabled(false);
		txtcustomername.setEditable(false);
		txtcustomername.setBounds(203, 264, 145, 20);
		contentPane.add(txtcustomername);
		txtcustomername.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setEnabled(false);
		txtphone.setEditable(false);
		txtphone.setBounds(203, 341, 145, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setEnabled(false);
		txtaddress.setEditable(false);
		txtaddress.setBounds(203, 414, 157, 20);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JLabel lblprodnm = new JLabel("PRODUCT NAME:");
		lblprodnm.setHorizontalAlignment(SwingConstants.CENTER);
		lblprodnm.setBounds(528, 267, 111, 14);
		contentPane.add(lblprodnm);
		
		JLabel lblcomptext = new JLabel("COMPLAINT TEXT:");
		lblcomptext.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomptext.setBounds(528, 344, 123, 14);
		contentPane.add(lblcomptext);
		
		txtprodname = new JTextField();
		txtprodname.setEnabled(false);
		txtprodname.setEditable(false);
		txtprodname.setBounds(732, 264, 96, 20);
		contentPane.add(txtprodname);
		txtprodname.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(732, 341, 227, 49);
		contentPane.add(scrollPane);
		
		txtcomptext = new JTextArea();
		txtcomptext.setEnabled(false);
		txtcomptext.setEditable(false);
		scrollPane.setViewportView(txtcomptext);
		
	    btnupdate = new JButton("UPDATE");
	    btnupdate.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnupdate.setBounds(406, 524, 183, 23);
		btnupdate.addActionListener(this);
		contentPane.add(btnupdate);
		
		btnsearch = new JButton("SEARCH");
		btnsearch.setBounds(791, 117, 157, 37);
		btnsearch.addActionListener(this);
		contentPane.add(btnsearch);
		
		JLabel lblremarks = new JLabel("REMARKS:");
		lblremarks.setHorizontalAlignment(SwingConstants.CENTER);
		lblremarks.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblremarks.setBounds(494, 432, 167, 34);
		contentPane.add(lblremarks);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(732, 432, 227, 86);
		contentPane.add(scrollPane_1);
		
		txtremarks = new JTextArea();
		scrollPane_1.setViewportView(txtremarks);
	}
	
	String cmpstatus;
	public boolean search()
	{
		int flag=0;
		String strsearch="select * from assigncomplaint where Complaintid=? && EmployeeId=?";
		try
		{
			ps=con.prepareStatement(strsearch);
			ps.setString(1,id);
			System.out.println(EmployeeLogin.id);
			ps.setString(2,EmployeeLogin.id);
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				cmpstatus=rs.getString("Complaint_Status");
				System.out.println(rs.getString("EmployeeId")+" "+rs.getString("Complaintid")+" "+rs.getString("Complaint_Status"));
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
	
	public void fillAllFields()
	{
		String strfill="select customer.Name,customer.Address,customer.Phoneno,complaint.ProductName,complaint.Complaint_text from complaint inner join customer where complaint.Customerid=customer.Customerid && complaint.Complaintid=?";
		try
		{
			ps=con.prepareStatement(strfill);
			ps.setString(1,id);
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				txtcustomername.setText(rs.getString("Name"));
				txtaddress.setText(rs.getString("Address"));
				txtphone.setText(rs.getString("Phoneno"));
				txtprodname.setText(rs.getString("ProductName"));
				txtcomptext.setText(rs.getString("Complaint_text"));
			}
			
		}
		catch(SQLException sd)
		{
			sd.printStackTrace();
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
			catch(SQLException ef)
			{
				ef.printStackTrace();
			}
		}
	}
	
	String id,remark,name;
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj=e.getSource();
		JButton jb=(JButton)obj;
		id=txtcomplaintid.getText().trim();
		
		
		if(jb == btnsearch)
		{
			if(id.isEmpty())
			{
				JOptionPane.showMessageDialog(this,"FILL COMPLAINT ID","MANDATORY",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				if(search())
				{
					if(cmpstatus.equalsIgnoreCase("ASSIGNED"))
					{
						JOptionPane.showMessageDialog(this,"Complaint id is assigned to this service engineer only but not resolved");
						fillAllFields();
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Complaint id is assigned to this service engineer only and it is resolved","RESOLVED",JOptionPane.INFORMATION_MESSAGE);
						txtcomplaintid.setText("");
						txtcustomername.setText("");
						txtaddress.setText("");
						txtphone.setText("");
						txtprodname.setText("");
						txtcomptext.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"No such complaint id assigned to this engineer","ERROR",JOptionPane.WARNING_MESSAGE);
					txtcomplaintid.setText("");
				}
			}
		}
		
		if(jb == btnupdate)
		{
			if(id.isEmpty())
			{
				JOptionPane.showMessageDialog(this,"FILL COMPLAINT ID","MANDATORY",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				name=txtcustomername.getText().trim();
				if(!(name.isEmpty()))
				{
					remark=txtremarks.getText().trim();
					if(!(remark.isEmpty()))
					{
						updateAssignStatus();
						updateComplaintStatus();
						JOptionPane.showMessageDialog(this,"UPADTED STATUS","UPDATED",JOptionPane.INFORMATION_MESSAGE);
						txtcomplaintid.setText("");
						txtcustomername.setText("");
						txtaddress.setText("");
						txtphone.setText("");
						txtprodname.setText("");
						txtcomptext.setText("");
						txtremarks.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(this,"ADD REMARKS","MADATORY",JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"CLICK ON SEARCH BUTTON FIRST","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
	
	public void updateAssignStatus()
	{
		String strupdate="update assigncomplaint set Complaint_Status=?,Remarks=? where Complaintid=?";
		try 
		{
			ps=con.prepareStatement(strupdate);
			ps.setString(1,"CLOSED");
			ps.setString(2,remark);
			ps.setString(3,id);
			int status=ps.executeUpdate();
			if(status>0)
			{
				JOptionPane.showMessageDialog(this,"COMPLAINT STATUS CHANGED TO CLOSED IN ASSIGNCOMPLAINT TABLE","STATUS CHANGED",JOptionPane.INFORMATION_MESSAGE);
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
			catch(SQLException sd)
			{
				sd.printStackTrace();
			}
		}
	}
	
	public void updateComplaintStatus()
	{
		java.util.Date d=new java.util.Date();
		long date=d.getTime();
		java.sql.Date ssd=new java.sql.Date(date);
		String strupdate="update complaint set Resolve_Status=?,Resolve_Date=? where Complaintid=?";
		try 
		{
			ps=con.prepareStatement(strupdate);
			ps.setString(1,"CLOSED");
			ps.setDate(2,ssd);
			ps.setString(3,id);
			int status=ps.executeUpdate();
			if(status>0)
			{
				JOptionPane.showMessageDialog(this,"COMPLAINT STATUS CHANGED TO CLOSED IN COMPLAINT TABLE","STATUS CHANGED",JOptionPane.INFORMATION_MESSAGE);
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
			catch(SQLException sd)
			{
				sd.printStackTrace();
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		
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
