package complaintapp.customer_care_executive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import complaintapp.beans.SeeComplaintsBean;
import complaintapp.database.DbConnection1;

import java.sql.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class ViewComplaints_ComplaintidWise extends JFrame implements ActionListener,WindowListener,KeyListener{

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private DefaultTableModel model;
	private ArrayList<SeeComplaintsBean> complaintlist;
	private JTextField txtcmpid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewComplaints_ComplaintidWise frame = new ViewComplaints_ComplaintidWise();
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
	public ViewComplaints_ComplaintidWise()
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewComplaints_ComplaintidWise.class.getResource("/complaintapp/images/icons8-view-24.png")));
		setTitle("VIEW COMPLAINTS COMPLAINTID WISE");
		con=DbConnection1.createConnection();
		model=new DefaultTableModel();
		complaintlist=new ArrayList<SeeComplaintsBean>();
		this.addWindowListener(this);
		createComponents();
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 971, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VIEW COMPLAINTS COMPLAINT-ID WISE PAGE");
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		lblNewLabel.setBackground(new Color(245, 222, 179));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(63, 11, 789, 62);
		contentPane.add(lblNewLabel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 246, 937, 238);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(255, 69, 0));
		table.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 15));
		table.setBorder(new LineBorder(new Color(75, 0, 130)));
		table.setBackground(new Color(245, 222, 179));
		scrollPane.setViewportView(table);
		JTableHeader header=table.getTableHeader();//returns reference of header
		header.setBackground(Color.GRAY);
		header.setForeground(Color.RED);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
		
		String[]colnames= {"CmpId","Customerid","Product","CmpText","CmpDate","Assign","Resolve_Status","Feedback"};
		model.setColumnIdentifiers(colnames);//create columns of our table
		
		table.setModel(model);
		
		JButton btnview = new JButton("VIEW");
		btnview.setForeground(new Color(178, 34, 34));
		btnview.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		btnview.setBackground(new Color(233, 150, 122));
		btnview.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnview.setBounds(699, 134, 184, 47);
        btnview.addActionListener(this);
		contentPane.add(btnview);
		
		JLabel lblcomplaintid = new JLabel("ENTER COMPLAINT ID:");
		lblcomplaintid.setForeground(new Color(0, 128, 128));
		lblcomplaintid.setOpaque(true);
		lblcomplaintid.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		lblcomplaintid.setBackground(new Color(240, 128, 128));
		lblcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomplaintid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcomplaintid.setBounds(25, 134, 313, 47);
		contentPane.add(lblcomplaintid);
		
		txtcmpid = new JTextField();
		txtcmpid.setForeground(new Color(255, 215, 0));
		txtcmpid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtcmpid.setHorizontalAlignment(SwingConstants.CENTER);
		txtcmpid.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		txtcmpid.setBackground(new Color(189, 183, 107));
		txtcmpid.setBounds(385, 134, 261, 47);
		contentPane.add(txtcmpid);
		txtcmpid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewComplaints_ComplaintidWise.class.getResource("/complaintapp/images/complaints-neon-sign-brick-wall-background-87865969.jpg")));
		lblNewLabel_1.setBounds(0, 0, 957, 532);
		contentPane.add(lblNewLabel_1);
	}

	String cmpid;
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		cmpid=txtcmpid.getText().trim();
		
		if(cmpid.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"PLEASE ENTER COMPLAINT ID","MANDATORY",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			int flag=0;
			SeeComplaintsBean c=null;
			String strselect="select * from complaint where Complaintid=?";
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
				ps.setString(1,cmpid);
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
				JOptionPane.showMessageDialog(this,"NO SUCH COMPLAINT ID EXISTS","MESSAGE",JOptionPane.PLAIN_MESSAGE);
				txtcmpid.setText("");
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
				txtcmpid.setText("");
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
