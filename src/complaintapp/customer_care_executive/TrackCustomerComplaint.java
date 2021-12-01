package complaintapp.customer_care_executive;

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
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.*;
import java.sql.*;


public class TrackCustomerComplaint extends JFrame implements ActionListener,WindowListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtcomplaintid;
	private JTextField txtcustomerid;
	private JTextField txtcustomername;
	private JTextField txtprodname;
	private JTextField txtcomptext;
	private JTextField txtstatus;
	private JTextField txtremark;
	
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
					TrackCustomerComplaint frame = new TrackCustomerComplaint();
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
	public TrackCustomerComplaint() 
	{
		setTitle("TRACK CUSTOMER COMPLAINT");
		createComponents();
		con=DbConnection1.createConnection();	
		this.addWindowListener(this);
		
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 979, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TRACK CUSTOMER COMPLAINT PAGE");
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(154, 33, 663, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER COMPLAINT ID:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(91, 160, 144, 25);
		contentPane.add(lblNewLabel_1);
		
		txtcomplaintid = new JTextField();
		txtcomplaintid.setBounds(420, 162, 204, 23);
		contentPane.add(txtcomplaintid);
		txtcomplaintid.setColumns(10);
		
		JButton btnview = new JButton("VIEW");
		btnview.setBounds(801, 161, 89, 23);
		btnview.addActionListener(this);
		contentPane.add(btnview);
		
		txtcustomerid = new JTextField();
		txtcustomerid.setEnabled(false);
		txtcustomerid.setEditable(false);
		txtcustomerid.setHorizontalAlignment(SwingConstants.CENTER);
		txtcustomerid.setBounds(161, 234, 251, 25);
		contentPane.add(txtcustomerid);
		txtcustomerid.setColumns(10);
		
		txtcustomername = new JTextField();
		txtcustomername.setEnabled(false);
		txtcustomername.setEditable(false);
		txtcustomername.setBounds(156, 302, 241, 20);
		contentPane.add(txtcustomername);
		txtcustomername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CUSTOMER ID:");
		lblNewLabel_2.setBounds(54, 239, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CUSTOMER NAME:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 305, 138, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PRODUCT NAME:\r\n");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(52, 368, 96, 14);
		contentPane.add(lblNewLabel_4);
		
		txtprodname = new JTextField();
		txtprodname.setEnabled(false);
		txtprodname.setEditable(false);
		txtprodname.setBounds(178, 365, 204, 36);
		contentPane.add(txtprodname);
		txtprodname.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("COMPLAINT TEXT:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(54, 449, 109, 14);
		contentPane.add(lblNewLabel_5);
		
		txtcomptext = new JTextField();
		txtcomptext.setEnabled(false);
		txtcomptext.setEditable(false);
		txtcomptext.setBounds(197, 446, 215, 36);
		contentPane.add(txtcomptext);
		txtcomptext.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("COMPLAINT STATUS:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(488, 283, 155, 36);
		contentPane.add(lblNewLabel_6);
		
		txtstatus = new JTextField();
		txtstatus.setEnabled(false);
		txtstatus.setEditable(false);
		txtstatus.setBounds(708, 286, 204, 36);
		contentPane.add(txtstatus);
		txtstatus.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("REMARK:");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(470, 382, 185, 38);
		contentPane.add(lblNewLabel_7);
		
		txtremark = new JTextField();
		txtremark.setEnabled(false);
		txtremark.setEditable(false);
		txtremark.setBounds(697, 370, 215, 93);
		contentPane.add(txtremark);
		txtremark.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("EMPLOYEE ID:");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(488, 239, 155, 14);
		contentPane.add(lblNewLabel_8);
		
		txtempid = new JTextField();
		txtempid.setEnabled(false);
		txtempid.setEditable(false);
		txtempid.setBounds(677, 228, 165, 31);
		contentPane.add(txtempid);
		txtempid.setColumns(10);
	}
	
	String compid;
	private JTextField txtempid;
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		compid=txtcomplaintid.getText().trim();
		
		if(!empty())
		{
			int flag=0;
			String strsql="select EmployeeId, Complaintid, Complaint_Status, Remarks from assigncomplaint where Complaintid=?";
			try
			{
				ps=con.prepareStatement(strsql);
				ps.setString(1,compid);
				rs=ps.executeQuery();
				if(rs.next())
				{
					flag=1;
					txtempid.setText(rs.getString("EmployeeId"));
					txtstatus.setText(rs.getString("Complaint_Status"));
					txtremark.setText(rs.getString("Remarks"));
					String strsee="select customer.CustomerId,customer.Name,complaint.ProductName, complaint.Complaint_text from complaint inner join customer where complaint.Customerid=customer.Customerid && complaint.Complaintid=?";
					try
					{
						ps1=con.prepareStatement(strsee);
						ps1.setString(1,compid); 
						rs1=ps1.executeQuery();
						if(rs1.next())
						{
							flag=1;
							txtcustomerid.setText(rs1.getString("Customerid"));
							txtcustomername.setText(rs1.getString("Name"));
							txtcomptext.setText(rs1.getString("Complaint_text"));
							txtprodname.setText(rs1.getString("ProductName"));
						}
					}
					catch(SQLException sdf)
					{
						sdf.printStackTrace();
					}
					finally
					{
						try
						{
							if(ps1!=null)
								ps1.close();
							if(rs1!=null)
								rs1.close();
						}
						catch(SQLException a)
						{
							a.printStackTrace();
						}
					}
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
			
			if(flag == 0)
			{
				JOptionPane.showMessageDialog(this,"NO SUCH COMPLAINT EXISTS","MESSAGE",JOptionPane.PLAIN_MESSAGE);
				txtcomplaintid.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"SEE COMPLAINT ASSIGNED","MESSAGE",JOptionPane.PLAIN_MESSAGE);
				txtcomplaintid.setText("");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Please enter complaint id","MANDATORY",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public boolean empty()
	{
		if(compid.isEmpty())
			return true;
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
