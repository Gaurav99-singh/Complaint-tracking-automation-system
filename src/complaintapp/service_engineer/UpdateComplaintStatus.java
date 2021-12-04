package complaintapp.service_engineer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.beans.SeeComplaintsBean;
import complaintapp.database.DbConnection1;
import complaintapp.entry.CustomerCareExecutiveAdmin;
import complaintapp.entry.EmployeeLogin;
import complaintapp.entry.ServiceEngineerAdmin;

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
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateComplaintStatus.class.getResource("/complaintapp/images/icons8-change-24.png")));
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
		setBounds(100, 100, 968, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CHANGE STATUS");
		lblNewLabel.setForeground(new Color(75, 0, 130));
		lblNewLabel.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(165, 42, 42));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(252, 11, 470, 64);
		contentPane.add(lblNewLabel);
		
		JLabel lblcomplaintid = new JLabel("ENTER COMPLAINT ID:");
		lblcomplaintid.setForeground(new Color(0, 0, 128));
		lblcomplaintid.setOpaque(true);
		lblcomplaintid.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblcomplaintid.setBackground(new Color(139, 69, 19));
		lblcomplaintid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomplaintid.setBounds(10, 96, 338, 40);
		contentPane.add(lblcomplaintid);
		
		txtcomplaintid = new JTextField();
		txtcomplaintid.setForeground(new Color(0, 128, 128));
		txtcomplaintid.setFont(new Font("Stencil", Font.ITALIC, 20));
		txtcomplaintid.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		txtcomplaintid.setBackground(new Color(189, 183, 107));
		txtcomplaintid.setBounds(392, 96, 330, 40);
		contentPane.add(txtcomplaintid);
		txtcomplaintid.setColumns(10);
		
		JLabel lblcustomername = new JLabel("CUSTOMER NAME:");
		lblcustomername.setForeground(new Color(0, 0, 128));
		lblcustomername.setOpaque(true);
		lblcustomername.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblcustomername.setBackground(new Color(139, 69, 19));
		lblcustomername.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcustomername.setHorizontalAlignment(SwingConstants.CENTER);
		lblcustomername.setBounds(10, 182, 276, 40);
		contentPane.add(lblcustomername);
		
		JLabel lblphone = new JLabel("PHONE NO:");
		lblphone.setForeground(new Color(0, 0, 128));
		lblphone.setOpaque(true);
		lblphone.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblphone.setBackground(new Color(139, 69, 19));
		lblphone.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblphone.setHorizontalAlignment(SwingConstants.CENTER);
		lblphone.setBounds(10, 233, 276, 40);
		contentPane.add(lblphone);
		
		JLabel lbladdress = new JLabel("ADDRESS:");
		lbladdress.setForeground(new Color(0, 0, 128));
		lbladdress.setOpaque(true);
		lbladdress.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lbladdress.setBackground(new Color(139, 69, 19));
		lbladdress.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lbladdress.setHorizontalAlignment(SwingConstants.CENTER);
		lbladdress.setBounds(10, 284, 276, 40);
		contentPane.add(lbladdress);
		
		txtcustomername = new JTextField();
		txtcustomername.setEnabled(false);
		txtcustomername.setEditable(false);
		txtcustomername.setForeground(new Color(0, 128, 128));
		txtcustomername.setFont(new Font("Stencil", Font.ITALIC, 20));
		txtcustomername.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		txtcustomername.setBackground(new Color(189, 183, 107));
		txtcustomername.setBounds(314, 186, 247, 40);
		contentPane.add(txtcustomername);
		txtcustomername.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setEnabled(false);
		txtphone.setEditable(false);
		txtphone.setForeground(new Color(0, 128, 128));
		txtphone.setFont(new Font("Stencil", Font.ITALIC, 20));
		txtphone.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		txtphone.setBackground(new Color(189, 183, 107));
		txtphone.setBounds(314, 233, 247, 40);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtaddress = new JTextField();
		txtaddress.setEnabled(false);
		txtaddress.setEditable(false);
		txtaddress.setForeground(new Color(0, 128, 128));
		txtaddress.setFont(new Font("Stencil", Font.ITALIC, 20));
		txtaddress.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		txtaddress.setBackground(new Color(189, 183, 107));
		txtaddress.setBounds(314, 284, 247, 40);
		contentPane.add(txtaddress);
		txtaddress.setColumns(10);
		
		JLabel lblprodnm = new JLabel("PRODUCT NAME:");
		lblprodnm.setForeground(new Color(0, 0, 128));
		lblprodnm.setOpaque(true);
		lblprodnm.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblprodnm.setBackground(new Color(139, 69, 19));
		lblprodnm.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblprodnm.setHorizontalAlignment(SwingConstants.CENTER);
		lblprodnm.setBounds(10, 335, 276, 40);
		contentPane.add(lblprodnm);
		
		JLabel lblcomptext = new JLabel("COMPLAINT TEXT:");
		lblcomptext.setForeground(new Color(0, 0, 128));
		lblcomptext.setOpaque(true);
		lblcomptext.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblcomptext.setBackground(new Color(139, 69, 19));
		lblcomptext.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcomptext.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomptext.setBounds(10, 406, 276, 40);
		contentPane.add(lblcomptext);
		
		txtprodname = new JTextField();
		txtprodname.setEnabled(false);
		txtprodname.setEditable(false);
		txtprodname.setForeground(new Color(0, 128, 128));
		txtprodname.setFont(new Font("Stencil", Font.ITALIC, 20));
		txtprodname.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		txtprodname.setBackground(new Color(189, 183, 107));
		txtprodname.setBounds(314, 335, 247, 40);
		contentPane.add(txtprodname);
		txtprodname.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(314, 392, 355, 107);
		contentPane.add(scrollPane);
		
		txtcomptext = new JTextArea();
		txtcomptext.setEnabled(false);
		txtcomptext.setEditable(false);
		txtcomptext.setForeground(new Color(0, 128, 128));
		txtcomptext.setFont(new Font("Stencil", Font.ITALIC, 20));
		txtcomptext.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		txtcomptext.setBackground(new Color(189, 183, 107));
		scrollPane.setViewportView(txtcomptext);
		
	    btnupdate = new JButton("UPDATE");
	    btnupdate.setForeground(new Color(139, 0, 0));
	    btnupdate.setBorder(new LineBorder(new Color(0, 128, 128), 5));
	    btnupdate.setBackground(new Color(189, 183, 107));
	    btnupdate.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnupdate.setBounds(337, 521, 276, 51);
		btnupdate.addActionListener(this);
		contentPane.add(btnupdate);
		
		btnsearch = new JButton("SEARCH");
		btnsearch.setForeground(new Color(139, 0, 0));
		btnsearch.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnsearch.setBorder(new LineBorder(new Color(0, 128, 128), 5));
		btnsearch.setBackground(new Color(189, 183, 107));
		btnsearch.setBounds(761, 96, 157, 37);
		btnsearch.addActionListener(this);
		contentPane.add(btnsearch);
		
		JLabel lblremarks = new JLabel("REMARKS:");
		lblremarks.setForeground(new Color(0, 0, 128));
		lblremarks.setOpaque(true);
		lblremarks.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblremarks.setBackground(new Color(139, 69, 19));
		lblremarks.setHorizontalAlignment(SwingConstants.CENTER);
		lblremarks.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblremarks.setBounds(679, 182, 167, 34);
		contentPane.add(lblremarks);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(662, 233, 286, 142);
		contentPane.add(scrollPane_1);
		
		txtremarks = new JTextArea();
		txtremarks.setForeground(new Color(0, 128, 128));
		txtremarks.setFont(new Font("Stencil", Font.ITALIC, 20));
		txtremarks.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		txtremarks.setBackground(new Color(189, 183, 107));
		scrollPane_1.setViewportView(txtremarks);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(UpdateComplaintStatus.class.getResource("/complaintapp/images/complaint-concept-against-barbwire-170339026.jpg")));
		lblNewLabel_1.setBounds(0, 0, 954, 583);
		contentPane.add(lblNewLabel_1);
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
