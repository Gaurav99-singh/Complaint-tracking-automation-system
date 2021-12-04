package complaintapp.company_manager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.security.Key;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

import complaintapp.database.DbConnection1;
import complaintapp.entry.CompanyManagerAdmin;

import java.sql.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class DeleteEmployeeDetails extends JFrame implements ActionListener,WindowListener{

	private JPanel contentPane;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmployeeDetails frame = new DeleteEmployeeDetails();
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
	
	
	public DeleteEmployeeDetails() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteEmployeeDetails.class.getResource("/complaintapp/images/contactdelete.png")));
		setResizable(false);
		con=DbConnection1.createConnection();
		setTitle("DELETE EMPLOYEE DETAILS");
		this.addWindowListener(this);
		createComponents();
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 926, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(85, 107, 47), 10));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("ENTER ID:");
		lblid.setOpaque(true);
		lblid.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblid.setBackground(new Color(189, 183, 107));
		lblid.setForeground(new Color(255, 99, 71));
		lblid.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(354, 185, 206, 52);
		contentPane.add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setForeground(new Color(60, 179, 113));
		txtid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtid.setBorder(new LineBorder(new Color(119, 136, 153), 3));
		txtid.setBackground(new Color(211, 211, 211));
		txtid.setToolTipText("Id format \r\nCustomer Care Executive-->CCE01,CCE02....  \r\nService Engineer-->SE01,SE01......");
		txtid.setBounds(269, 296, 397, 52);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btndelete = new JButton("DELETE");
		btndelete.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		btndelete.setBackground(new Color(0, 128, 128));
		btndelete.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btndelete.setForeground(new Color(255, 69, 0));
		btndelete.setBounds(354, 435, 206, 46);
		btndelete.addActionListener(this);
		contentPane.add(btndelete);
		
		JLabel lblNewLabel = new JLabel("DELETE EMPLOYEE DETAILS PAGE");
		lblNewLabel.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(189, 183, 107));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setBounds(169, 37, 623, 84);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(DeleteEmployeeDetails.class.getResource("/complaintapp/images/companymanageradminpic.jpg")));
		lblNewLabel_1.setBounds(0, 0, 922, 526);
		contentPane.add(lblNewLabel_1);
	}

	String id;
	@Override
	public void actionPerformed(ActionEvent e)
	{
		id=txtid.getText().trim();
		if(id.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Enter Id","Mandatory",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			if(searchData())
			{
				int ch=JOptionPane.showConfirmDialog(this,"Do you wish to delete this employeeid details "+id);
				if(ch == 0)
				{
					int status=deleteData(id);
					if(status>0)
					{
						JOptionPane.showMessageDialog(this,"Employee Details Deleted Successfully");
						txtid.setText("");
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this,"No such id exists","ERROR",JOptionPane.WARNING_MESSAGE);
				txtid.setText("");
			}
		}
	}
	
	public int deleteData(String id)  //to delete data and return status
	{
		int status=0;
		if(searchData())
		{
			String delsql="delete from employee where EmployeeId=?";
			try
			{
				ps=con.prepareStatement(delsql);
				ps.setString(1, id);
				status=ps.executeUpdate();
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
				catch(SQLException sde)
				{
					sde.printStackTrace();
				}
			}
		}
		return status;
	}
	
	public boolean searchData() //to search data
	{
		String strsql="select EmployeeId from employee where EmployeeId=?";
		try
		{
			ps=con.prepareStatement(strsql);
			ps.setString(1,id);
			rs=ps.executeQuery();
			
			if(rs.next())
				return true;
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
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
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
