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
import java.sql.*;
import java.awt.event.*;

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
		setResizable(false);
		con=DbConnection1.createConnection();
		setTitle("DELETE EMPLOYEE DETAILS");
		this.addWindowListener(this);
		createComponents();
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 971, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("ENTER ID:");
		lblid.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBounds(127, 288, 206, 52);
		contentPane.add(lblid);
		
		txtid = new JTextField();
		txtid.setToolTipText("Id format \r\nCustomer Care Executive-->CCE01,CCE02....  \r\nService Engineer-->SE01,SE01......");
		txtid.setBounds(479, 288, 221, 52);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btndelete = new JButton("DELETE");
		btndelete.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btndelete.setForeground(new Color(0, 0, 0));
		btndelete.setBounds(324, 470, 190, 46);
		btndelete.addActionListener(this);
		contentPane.add(btndelete);
		
		JLabel lblNewLabel = new JLabel("DELETE CONTACT PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(168, 80, 508, 64);
		contentPane.add(lblNewLabel);
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
		JOptionPane.showMessageDialog(this,"THANK YOU FOR USING THIS PAGE","EXIT",JOptionPane.INFORMATION_MESSAGE);
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
