package complaintapp.company_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import complaintapp.beans.SeeComplaintsBean;
import complaintapp.database.DbConnection1;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

public class ViewComplaintsDateWise extends JFrame implements ActionListener,WindowListener,KeyListener {

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
					ViewComplaintsDateWise frame = new ViewComplaintsDateWise();
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
	public ViewComplaintsDateWise()
	{
		setTitle("VIEW COMPLAINTS DATE WISE");
		con=DbConnection1.createConnection();
		model=new DefaultTableModel();
		complaintlist=new ArrayList<SeeComplaintsBean>();
		this.addWindowListener(this);
		createComponents();
		
	}

	JDateChooser dateChooser;
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 936, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VIEW COMPLAINTS DATE WISE PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(135, 51, 622, 62);
		contentPane.add(lblNewLabel);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(110, 124, 432, 62);
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd"); //Month=M,minute=m.
		dateChooser.setBounds(145, 124, 353, 48);
		contentPane.add(dateChooser);
		
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
		btnview.setBounds(613, 124, 171, 62);
        btnview.addActionListener(this);
		contentPane.add(btnview);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int flag=0;
		SeeComplaintsBean c=null;
		java.util.Date dt=dateChooser.getDate();//util date converting to sql date
		java.sql.Date sd=new java.sql.Date(dt.getTime());
		String strselect="select * from complaint where Complaint_Date=?";
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
			ps.setDate(1,sd);
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
			JOptionPane.showMessageDialog(this,"NO COMPLAINTS","MESSAGE",JOptionPane.PLAIN_MESSAGE);
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
