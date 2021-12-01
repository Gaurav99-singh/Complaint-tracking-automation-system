package complaintapp.service_engineer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import complaintapp.database.DbConnection1;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import complaintapp.beans.*;
import complaintapp.entry.*;

public class ViewAllAssigned_NotResolvedComplaints extends JFrame implements ActionListener,KeyListener,WindowListener
{

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAllAssigned_NotResolvedComplaints frame = new ViewAllAssigned_NotResolvedComplaints();
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
	private PreparedStatement ps,ps1;
	private ResultSet rs,rs1;
	String seid=EmployeeLogin.id;
	
	private ArrayList<SeeComplaintsBean>complaintlist;
	
	private DefaultTableModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ViewAllAssigned_NotResolvedComplaints() 
	{
		
		setTitle("SEE ASSIGNED COMPLAINTS");
		this.addWindowListener(this);
		con=DbConnection1.createConnection();
		model=new DefaultTableModel();
		complaintlist=new ArrayList<SeeComplaintsBean>();
		createComponents();
	}
	
	private DefaultTableModel model;
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 995, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SEE ASSIGNED COMPLAINTS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(189, 26, 570, 64);
		contentPane.add(lblNewLabel);
		
		JButton btnview = new JButton("VIEW ASSIGNED COMPLAINTS");
		btnview.addActionListener(this);
		btnview.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnview.setBounds(234, 466, 548, 53);
		contentPane.add(btnview);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 101, 917, 340);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JTableHeader header=table.getTableHeader();//returns reference of header
		header.setBackground(Color.CYAN);
		header.setForeground(Color.RED);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
		
		String[]colnames= {"NAME","ADDRESS","PHONE_NO","PRODUCT_NAME","COMPLAINT_TEXT"};
		model.setColumnIdentifiers(colnames);//create columns of our table
		
		table.setModel(model);	
	}
	
	public void createRows()
	{
		int flag=0;
		SeeComplaintsBean c=null;
		String strsql="select Complaintid from assigncomplaint where EmployeeId=? && Complaint_Status=?";
		try
		{
			ps=con.prepareStatement(strsql);
			ps.setString(1,"SE001");
			ps.setString(2,"ASSIGNED");
			System.out.println("Hello service engineer");
			rs=ps.executeQuery();
			while(rs.next())
			{
				flag=1;
				System.out.print(rs.getString("Complaintid"));
				SeeComplaintsBean s=null;
				String strsee="select customer.Name,customer.Address,customer.Phoneno,complaint.ProductName, complaint.Complaint_text from complaint inner join customer where complaint.Customerid=customer.Customerid && complaint.Complaintid=?";
				try
				{
					ps1=con.prepareStatement(strsee);
					ps1.setString(1,rs.getString("Complaintid"));
					rs1=ps1.executeQuery();
					
					while(rs1.next())
					{
						flag=1;
						s=new SeeComplaintsBean(rs1.getString("customer.Name"),rs1.getString("customer.Address"),rs1.getString("customer.Phoneno"),rs1.getString("complaint.ProductName"),rs1.getString("complaint.Complaint_text") );
						complaintlist.add(s);
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
			JOptionPane.showMessageDialog(this,"NO COMPLAINT ASSIGNED","MESSAGE",JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this,"SEE COMPLAINT ASSIGNED","MESSAGE",JOptionPane.PLAIN_MESSAGE);
			for(SeeComplaintsBean st:complaintlist)
			{
				
				String[]rowdata= {st.getName(),st.getAddress(),st.getPhone(),st.getProduct(),st.getCmptxt()};
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
