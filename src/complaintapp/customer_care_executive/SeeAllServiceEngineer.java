package complaintapp.customer_care_executive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import complaintapp.beans.ViewEmployeeBean;
import complaintapp.database.DbConnection1;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class SeeAllServiceEngineer extends JFrame implements ActionListener,WindowListener,KeyListener{

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
					SeeAllServiceEngineer frame = new SeeAllServiceEngineer();
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
	
	public SeeAllServiceEngineer() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(SeeAllServiceEngineer.class.getResource("/complaintapp/images/icons8-view-24.png")));
		con=DbConnection1.createConnection();
		model=new DefaultTableModel();
		employeelist=new ArrayList<ViewEmployeeBean>();
		setTitle("VIEW ALL SERVICE ENGINEER ");
		createComponents();
		this.addWindowListener(this);	
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 969, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VIEW ALL SERVICE ENGINEER PAGE");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		lblNewLabel.setBackground(new Color(188, 143, 143));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(170, 11, 628, 60);
		contentPane.add(lblNewLabel);
		
		JButton btnview = new JButton("VIEW");
		btnview.setForeground(new Color(178, 34, 34));
		btnview.setOpaque(true);
		btnview.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		btnview.setBackground(new Color(188, 143, 143));
		btnview.setHorizontalAlignment(SwingConstants.CENTER);
		btnview.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 30));
		btnview.setBounds(359, 484, 232, 76);
		btnview.addActionListener(this);
		contentPane.add(btnview);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 124, 924, 286);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(255, 140, 0));
		table.setFont(new Font("Stencil", Font.ITALIC, 15));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(new Color(105, 105, 105));
		scrollPane.setViewportView(table);
		
		JTableHeader header=table.getTableHeader();
		header.setBackground(Color.GRAY);
		header.setForeground(Color.YELLOW);
		header.setFont(new Font("Comic Sans Ms",Font.BOLD,20));
		
		String[]colnames= {"EMPLOYEE ID","NAME","ADDRESS","PHONE NO","EMAIL","GENDER","EXPERIENCE"};
		model.setColumnIdentifiers(colnames);//create columns of our table
		
		table.setModel(model);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(SeeAllServiceEngineer.class.getResource("/complaintapp/images/customer-services-complaint-concept-small-wooden-cube-bearing-word-standing-desk-alongside-land-line-52154940.jpg")));
		lblNewLabel_1.setBounds(0, 0, 957, 597);
		contentPane.add(lblNewLabel_1);
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
			ps.setString(1,"SERVICE ENGINEER");
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
			JOptionPane.showMessageDialog(this,"VIEW ALL SERVICE ENGINEER","MESSAGE",JOptionPane.PLAIN_MESSAGE);
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
