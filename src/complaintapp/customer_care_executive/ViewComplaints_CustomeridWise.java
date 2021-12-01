package complaintapp.customer_care_executive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import complaintapp.beans.SeeComplaintsBean;
import complaintapp.database.DbConnection1;

public class ViewComplaints_CustomeridWise extends JFrame implements ActionListener,WindowListener,KeyListener{

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DefaultTableModel model;
	private ArrayList<SeeComplaintsBean> complaintlist;
	private JTextField txtcusid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewComplaints_CustomeridWise frame = new ViewComplaints_CustomeridWise();
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
	public ViewComplaints_CustomeridWise()
	{
		setTitle("VIEW COMPLAINTS CUSTOMER-ID WISE");
		con=DbConnection1.createConnection();
		model=new DefaultTableModel();
		complaintlist=new ArrayList<SeeComplaintsBean>();
		this.addWindowListener(this);
		createComponents();
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 936, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VIEW COMPLAINTS CUSTOMER-ID WISE PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(87, 51, 789, 62);
		contentPane.add(lblNewLabel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(23, 211, 889, 391);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		JTableHeader header=table.getTableHeader();//returns reference of header
		header.setBackground(Color.CYAN);
		header.setForeground(Color.RED);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
		
		String[]colnames= {"Complaintid","Customerid","ProductName","Complaint_text","Complaint_Date","Assign_Status","Resolve_Status","Feedback_Status"};
		model.setColumnIdentifiers(colnames);//create columns of our table
		
		table.setModel(model);
		
		JButton btnview = new JButton("VIEW");
		btnview.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnview.setBounds(725, 124, 171, 62);
        btnview.addActionListener(this);
		contentPane.add(btnview);
		
		JLabel lblcomplaintid = new JLabel("ENTER CUSTOMER ID:");
		lblcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomplaintid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcomplaintid.setBounds(24, 136, 297, 47);
		contentPane.add(lblcomplaintid);
		
		txtcusid = new JTextField();
		txtcusid.setBounds(368, 138, 261, 47);
		contentPane.add(txtcusid);
		txtcusid.setColumns(10);
	}

	String cusid;
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		cusid=txtcusid.getText().trim();
		
		if(cusid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"PLEASE ENTER CUSTOMER ID","MANDATORY",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			int flag=0;
			SeeComplaintsBean c=null;
			String strselect="select * from complaint where Customerid=?";
			try
			{
				
				if(!complaintlist.isEmpty())
					complaintlist.clear();//clearing data from arraylist
					
					
					/*model.getDataVector().removeAllElements();
					revalidate();*/
				
				    if(model.getRowCount() > 0)
				    {
				    	for(int i=model.getRowCount()-1;i>=0;i--)
				    	{
				    		model.removeRow(i);
				    	}
				    }
				    
				ps=con.prepareStatement(strselect);
				ps.setString(1,cusid);
				rs=ps.executeQuery();
				
				while(rs.next())
				{
					flag=1;
					c=new SeeComplaintsBean(rs.getString("Complaintid"),rs.getString("Customerid"),rs.getString("ProductName"),
							rs.getString("Complaint_text"),rs.getString("Complaint_Date"),rs.getString("Assign_Status"),rs.getString("Resolve_Status"),
						rs.getString("Feedback_Status"));
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
				JOptionPane.showMessageDialog(this,"NO SUCH CUSTOMER ID EXISTS","MESSAGE",JOptionPane.PLAIN_MESSAGE);
				txtcusid.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"SEE ALL COMPLAINTS","MESSAGE",JOptionPane.PLAIN_MESSAGE);
				for(SeeComplaintsBean st:complaintlist) 
				{
					
					String[]rowdata= {st.getCmpid(),st.getCusid(),st.getProduct(),st.getCmptxt(),
							st.getCmpDate(),st.getAssignstatus(),st.getResolvestatus(),st.getFeedback()};
					model.addRow(rowdata);  //creating Jtable rows
				}
				txtcusid.setText("");
			}
		}
		
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

