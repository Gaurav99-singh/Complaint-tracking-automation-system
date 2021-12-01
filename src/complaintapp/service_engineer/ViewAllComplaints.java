package complaintapp.service_engineer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import complaintapp.beans.SeeComplaintsBean;
import complaintapp.database.DbConnection1;
import complaintapp.entry.EmployeeLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;


public class ViewAllComplaints extends JFrame implements ActionListener,KeyListener,WindowListener
{

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DefaultTableModel model;
	private ArrayList<SeeComplaintsBean> complaintlist;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllComplaints frame = new ViewAllComplaints();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private DefaultTableModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create the frame.
	 */
	public ViewAllComplaints() 
	{
		con=DbConnection1.createConnection();
		model=new DefaultTableModel();
		complaintlist=new ArrayList<SeeComplaintsBean>();
		setTitle("VIEW ALL COMPLAINTS PAGE");
		//setExtendedState(Frame.MAXIMIZED_BOTH);
		this.addWindowListener(this);
		createComponents();
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 994, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VIEW ALL COMPLAINTS PAGE");
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(190, 33, 562, 52);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 108, 926, 370);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		JTableHeader header=table.getTableHeader();//returns reference of header
		header.setBackground(Color.CYAN);
		header.setForeground(Color.RED);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
		
		String[]colnames= {"Complaintid","Customerid","ProductName","Complaint_text","Complaint_Date","AssignDate","Resolve_Status","Resolve_Date","Remarks"};
		model.setColumnIdentifiers(colnames);//create columns of our table
		
		table.setModel(model);
		
		JButton btnview = new JButton("VIEW ");
		btnview.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnview.setBounds(220, 489, 550, 58);
		btnview.addActionListener(this);
		contentPane.add(btnview);
		
	}
	
	public void createRows()
	{
		int flag=0;
		SeeComplaintsBean c=null;
		String strsql="select assigncomplaint.Complaintid,complaint.Customerid,complaint.ProductName,complaint.Complaint_text,complaint.Complaint_Date,assigncomplaint.AssignDate,complaint.Resolve_Status,complaint.Resolve_Date,assigncomplaint.Remarks from assigncomplaint inner join complaint where complaint.Complaintid=assigncomplaint.Complaintid && EmployeeId=?";
		try
		{
			ps=con.prepareStatement(strsql);
			ps.setString(1,"SE001");
			System.out.println("Hello service engineer");
			rs=ps.executeQuery();
			while(rs.next())
			{
				flag=1;
				c=new SeeComplaintsBean(rs.getString("Complaintid"),rs.getString("Customerid"),rs.getString("ProductName"),
						rs.getString("Complaint_text"),rs.getString("Complaint_Date"),rs.getString("AssignDate"),rs.getString("Resolve_Status"),
						rs.getString("Resolve_Date"),rs.getString("Remarks"));
				complaintlist.add(c);
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
			JOptionPane.showMessageDialog(this,"NO COMPLAINT ASSIGNED","MESSAGE",JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this,"SEE COMPLAINT ASSIGNED","MESSAGE",JOptionPane.PLAIN_MESSAGE);
			for(SeeComplaintsBean st:complaintlist) 
			{
				
				String[]rowdata= {st.getCmpid(),st.getCusid(),st.getProduct(),st.getCmptxt(),
						st.getCmpDate(),st.getAssigndate(),st.getResolvestatus(),st.getResolvedate(),st.getRemarks()};
				model.addRow(rowdata);  //creating Jtable rows
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		createRows();
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
}
