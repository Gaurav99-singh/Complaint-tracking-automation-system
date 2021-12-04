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
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewComplaints_CustomeridWise.class.getResource("/complaintapp/images/icons8-view-64.png")));
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
		setBounds(100, 100, 1051, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VIEW COMPLAINTS CUSTOMER-ID WISE PAGE");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(0, 0, 205));
		lblNewLabel.setBorder(new LineBorder(new Color(255, 127, 80), 5));
		lblNewLabel.setBackground(new Color(102, 205, 170));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(86, 11, 789, 62);
		contentPane.add(lblNewLabel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(23, 211, 965, 320);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 228, 181));
		table.setBorder(new LineBorder(new Color(47, 79, 79)));
		table.setForeground(new Color(250, 128, 114));
		table.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 15));
		scrollPane.setViewportView(table);
		JTableHeader header=table.getTableHeader();//returns reference of header
		header.setBackground(Color.GRAY);
		header.setForeground(Color.PINK);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
		
		String[]colnames= {"Complaintid","Customerid","ProductName","Complaint_text","Complaint_Date","Assign_Status","Resolve_Status","Feedback_Status"};
		model.setColumnIdentifiers(colnames);//create columns of our table
		
		table.setModel(model);
		
		JButton btnview = new JButton("VIEW");
		btnview.setForeground(new Color(255, 140, 0));
		btnview.setBorder(new LineBorder(new Color(0, 0, 128), 3));
		btnview.setBackground(new Color(189, 183, 107));
		btnview.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnview.setBounds(772, 109, 197, 47);
        btnview.addActionListener(this);
		contentPane.add(btnview);
		
		JLabel lblcomplaintid = new JLabel("ENTER CUSTOMER ID:");
		lblcomplaintid.setOpaque(true);
		lblcomplaintid.setForeground(new Color(47, 79, 79));
		lblcomplaintid.setBorder(new LineBorder(new Color(72, 61, 139), 3));
		lblcomplaintid.setBackground(new Color(222, 184, 135));
		lblcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomplaintid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcomplaintid.setBounds(23, 109, 329, 47);
		contentPane.add(lblcomplaintid);
		
		txtcusid = new JTextField();
		txtcusid.setForeground(new Color(255, 99, 71));
		txtcusid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtcusid.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtcusid.setBackground(new Color(222, 184, 135));
		txtcusid.setBounds(391, 109, 297, 47);
		contentPane.add(txtcusid);
		txtcusid.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewComplaints_CustomeridWise.class.getResource("/complaintapp/images/conceptual-display-customer-complaints-business-concept-expression-dissatisfaction-consumer-s-behalf-hands-pointing-226561628.jpg")));
		lblNewLabel_1.setBounds(0, 0, 1037, 575);
		contentPane.add(lblNewLabel_1);
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

