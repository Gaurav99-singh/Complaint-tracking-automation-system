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
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewAllAssigned_NotResolvedComplaints.class.getResource("/complaintapp/images/icons8-view-64.png")));
		
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
		setBounds(100, 100, 971, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SEE ASSIGNED COMPLAINTS");
		lblNewLabel.setBorder(new LineBorder(new Color(46, 139, 87), 5, true));
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(250, 128, 114));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(149, 11, 666, 64);
		contentPane.add(lblNewLabel);
		
		JButton btnview = new JButton("VIEW ASSIGNED COMPLAINTS");
		btnview.setBorder(new LineBorder(new Color(85, 107, 47), 5));
		btnview.setForeground(new Color(0, 0, 128));
		btnview.setBackground(new Color(255, 127, 80));
		btnview.addActionListener(this);
		btnview.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnview.setBounds(239, 468, 477, 64);
		contentPane.add(btnview);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(240, 128, 128));
		scrollPane.setBorder(new LineBorder(new Color(139, 0, 0), 3));
		scrollPane.setForeground(new Color(255, 0, 0));
		scrollPane.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 15));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 101, 917, 340);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(255, 0, 0));
		table.setFont(new Font("Stencil", Font.ITALIC, 15));
		table.setBorder(new LineBorder(new Color(85, 107, 47)));
		table.setBackground(new Color(0, 128, 128));
		scrollPane.setViewportView(table);
		
		JTableHeader header=table.getTableHeader();//returns reference of header
		header.setBackground(Color.GRAY);
		header.setForeground(Color.ORANGE);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
		
		String[]colnames= {"NAME","ADDRESS","PHONE","PRODUCT","COMPLAINT_TEXT"};
		model.setColumnIdentifiers(colnames);//create columns of our table
		
		table.setModel(model);	
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewAllAssigned_NotResolvedComplaints.class.getResource("/complaintapp/images/complaint-text-wall-152228268.jpg")));
		lblNewLabel_1.setBounds(0, 0, 957, 543);
		contentPane.add(lblNewLabel_1);
	}
	
	public void createRows()
	{
		int flag=0;
		SeeComplaintsBean c=null;
		String strsql="select Complaintid from assigncomplaint where EmployeeId=? && Complaint_Status=?";
		try
		{
			ps=con.prepareStatement(strsql);
			ps.setString(1,EmployeeLogin.id);
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
