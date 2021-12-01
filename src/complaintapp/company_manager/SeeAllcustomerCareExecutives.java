package complaintapp.company_manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import complaintapp.beans.SeeComplaintsBean;
import complaintapp.beans.ViewEmployeeBean;
import complaintapp.database.DbConnection1;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import java.sql.*;
import java.util.*;
import java.awt.event.*;


public class SeeAllcustomerCareExecutives extends JFrame implements KeyListener,WindowListener,ActionListener{

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private ArrayList<ViewEmployeeBean>employeelist;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeAllcustomerCareExecutives frame = new SeeAllcustomerCareExecutives();
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
	
	private DefaultTableModel getModel()
	{
		return null;
	}
	
	public SeeAllcustomerCareExecutives()
	{
		con=DbConnection1.createConnection();
		model=new DefaultTableModel();
		employeelist=new ArrayList<ViewEmployeeBean>();
		setTitle("VIEW ALL CUSTOMER CARE EXECUTIVE ");
		createComponents();
		this.addWindowListener(this);	
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 971, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VIEW ALL CUSTOMER CARE EXECUTIVE PAGE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(93, 25, 774, 60);
		contentPane.add(lblNewLabel);
		
		JButton btnview = new JButton("VIEW");
		btnview.setBounds(316, 397, 232, 40);
		btnview.addActionListener(this);
		contentPane.add(btnview);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(53, 137, 840, 230);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JTableHeader header=table.getTableHeader();
		header.setBackground(Color.CYAN);
		header.setForeground(Color.RED);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
		
		String[]colnames= {"EMPLOYEE ID","NAME","ADDRESS","PHONE NO","EMAIL","GENDER","EXPERIENCE"};
		model.setColumnIdentifiers(colnames);//create columns of our table
		
		table.setModel(model);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		createRows();
	}

	public void createRows()
	{
		int flag=0;
		ViewEmployeeBean v=null;
		String strselect="select * from employee where EmployeeType=?";
		try
		{
			ps=con.prepareStatement(strselect);
			ps.setString(1,"CUSTOMER CARE EXECUTIVE");
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				flag=1;
				v=new ViewEmployeeBean(rs.getString("EmployeeId"),rs.getString("Name"),rs.getString("Address"),
						rs.getString("PhoneNo"),rs.getString("Email"),rs.getString("Gender"),rs.getString("Experience"));
				employeelist.add(v);
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
			JOptionPane.showMessageDialog(this,"NO DATA","MESSAGE",JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(this,"VIEW ALL CUSTOMER CARE EXECUTIVES","MESSAGE",JOptionPane.PLAIN_MESSAGE);
			for(ViewEmployeeBean st:employeelist)
			{
				
				String[]rowdata= {st.getEmployeeId(),st.getName(),st.getAddress(),st.getPhoneNo(),st.getEmail(),st.getGender(),st.getExperience()};
				model.addRow(rowdata);  //creating Jtable rows
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
		// TODO Auto-generated method stub
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
