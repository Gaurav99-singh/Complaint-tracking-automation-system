package complaintapp.customer_care_executive;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.database.DbConnection1;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.sql.*;
import complaintapp.beans.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class AssignComplaint extends JFrame implements ActionListener,WindowListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignComplaint frame = new AssignComplaint();
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
	
	public AssignComplaint() 
	{
		setTitle("ASSIGN COMPLAINTS");
		con=DbConnection1.createConnection();
		this.addWindowListener(this);
		createComponents();
		
	}
	JButton btnassign;
	
	JComboBox<Service_EngineerBean>cmbservice;
	JComboBox<String>cmbcomplaint;
	
	public void fillServiceCombo()
	{
		String strfill="select EmployeeId,Name from employee where EmployeeType=?";
		try
		{
			ps=con.prepareStatement(strfill);
			ps.setString(1,"SERVICE ENGINEER");
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				String name=rs.getString("Name");
				String engid=rs.getString("EmployeeId");
				cmbservice.addItem(new Service_EngineerBean(engid,name));
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
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void fillComplaintCombo()
	{
		String strfill1="select Complaintid from complaint where Assign_Status=?";
		try
		{
			ps=con.prepareStatement(strfill1);
			ps.setString(1,"NOT ASSIGNED");
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				String compid=rs.getString("Complaintid");
				cmbcomplaint.addItem(new String(compid));
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
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 987, 583);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ASSIGN COMPLAINTS PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(266, 27, 451, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblservice = new JLabel("SELECT SERVICE ENGINEER:");
		lblservice.setHorizontalAlignment(SwingConstants.CENTER);
		lblservice.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 21));
		lblservice.setBounds(36, 138, 436, 27);
		contentPane.add(lblservice);
		
		JLabel lblcomplaint = new JLabel("SELECT A COMPLAINT:");
		lblcomplaint.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomplaint.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcomplaint.setBounds(60, 289, 380, 27);
		contentPane.add(lblcomplaint);
		
		btnassign = new JButton("ASSIGN");
		btnassign.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnassign.setBounds(270, 419, 380, 52);
		btnassign.addActionListener(this);
		contentPane.add(btnassign);
		
		cmbservice = new JComboBox<Service_EngineerBean>();
		//cmbservice.setModel(new DefaultComboBoxModel(new String[] {"SELECT SERVICE ENGINEER:"}));
		cmbservice.setBounds(530, 144, 380, 22);
		fillServiceCombo();
		contentPane.add(cmbservice);
		
		cmbcomplaint = new JComboBox<String>();
		cmbcomplaint.setModel(new DefaultComboBoxModel(new String[] {"SELECT COMPLAINT ID:"}));
		cmbcomplaint.setBounds(530, 281, 380, 35);
		fillComplaintCombo();
		contentPane.add(cmbcomplaint);
	}
	
	String cmb;
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Service_EngineerBean seb=(Service_EngineerBean)cmbservice.getSelectedItem();
		cmb=(String)cmbcomplaint.getSelectedItem();
		String engid=seb.getEngineerId();
		
		if(cmb.equalsIgnoreCase("SELECT COMPLAINT ID:"))
		{
			JOptionPane.showMessageDialog(this,"Please Select a Complaint Id","MANDATORY",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			java.util.Date d=new java.util.Date();
			long date=d.getTime();
			java.sql.Date sd=new java.sql.Date(date);
			String strinsert="insert into assigncomplaint(EmployeeId, Complaintid,Complaint_Status,Date) values(?,?,?,?)";
			try
			{
				ps=con.prepareStatement(strinsert);
				ps.setString(1,seb.getEngineerId());
				ps.setString(2,cmb);
				ps.setString(3,"ASSIGNED");
				ps.setDate(4,sd);
				int row_status=ps.executeUpdate();
				
				if(row_status > 0)
				{
					JOptionPane.showMessageDialog(this, "Complaint Assigned to "+seb.getEngineerName(),"ASSIGN COMPLAINT",JOptionPane.INFORMATION_MESSAGE);
					changeAssignStatus();
					cmbcomplaint.setModel(new DefaultComboBoxModel(new String[] {"SELECT COMPLAINT ID:"}));
					fillComplaintCombo();
				}
			}
			catch(SQLException sed)
			{
				sed.printStackTrace();
			}
			finally
			{
				try
				{
					if(ps!=null)
						ps.close();
				}
				catch(SQLException s)
				{
					s.printStackTrace();
				}
			}
		}
	}
	
	
	public void changeAssignStatus()
	{
		String strupdate="update complaint set Assign_Status=? where Complaintid=?";
		try
		{
			ps=con.prepareStatement(strupdate);
			ps.setString(1,"ASSIGNED");
			ps.setString(2, cmb);
			int status=ps.executeUpdate();
			if(status>0)
			{
				JOptionPane.showMessageDialog(this,"Status changed in Complaint Table");
			}	
		}
		catch(SQLException ee)
		{
			ee.printStackTrace();
		}
		finally
		{
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException ff)
			{
				ff.printStackTrace();
			}
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
		JOptionPane.showMessageDialog(this,"Thank You");
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
