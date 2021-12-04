package complaintapp.customer_care_executive;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.database.DbConnection1;
import complaintapp.entry.CustomerCareExecutiveAdmin;
import complaintapp.entry.EmployeeLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;

import java.sql.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class FeedbackForm extends JFrame implements ActionListener,KeyListener,WindowListener
{
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps,ps1;
	private ResultSet rs,rs1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedbackForm frame = new FeedbackForm();
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
	public FeedbackForm() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(FeedbackForm.class.getResource("/complaintapp/images/icons8-feedback-16.png")));
		con=DbConnection1.createConnection();
		setTitle("FEEDBACK FORM");
		this.addWindowListener(this);
		createComponents();
	}
	
	public void fillCombo()
	{
		String strsql="select * from complaint where Resolve_Status=? && Feedback_Status=?";
		try
		{
			ps=con.prepareStatement(strsql); //create communication
			ps.setString(1,"CLOSED");
			ps.setString(2, "NOT TAKEN");
			rs=ps.executeQuery();//fire the query
			while(rs.next())
			{
				String cusid=rs.getString("Customerid");
				cmbcustomerid.addItem(new String(cusid));
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
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	
	JComboBox<String>cmbcustomerid;
	JTextArea txtfeedback;
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 971, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FEEDBACK FORM PAGE");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(47, 79, 79));
		lblNewLabel.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		lblNewLabel.setBackground(new Color(222, 184, 135));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(182, 25, 482, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblfeedbacktext = new JLabel("FEEDBACK TEXT:");
		lblfeedbacktext.setBackground(new Color(222, 184, 135));
		lblfeedbacktext.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblfeedbacktext.setOpaque(true);
		lblfeedbacktext.setHorizontalAlignment(SwingConstants.CENTER);
		lblfeedbacktext.setForeground(new Color(47, 79, 79));
		lblfeedbacktext.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblfeedbacktext.setBounds(31, 256, 343, 52);
		contentPane.add(lblfeedbacktext);
		
		cmbcustomerid = new JComboBox<String>();
		cmbcustomerid.setForeground(new Color(255, 127, 80));
		cmbcustomerid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		cmbcustomerid.setBorder(new LineBorder(new Color(178, 34, 34), 5));
		cmbcustomerid.setBackground(new Color(211, 211, 211));
		cmbcustomerid.setModel(new DefaultComboBoxModel(new String[] {"SELECT CUSTOMER ID:"}));
		cmbcustomerid.setBounds(436, 127, 405, 52);
		fillCombo();
		contentPane.add(cmbcustomerid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(426, 242, 460, 153);
		contentPane.add(scrollPane);
		
		txtfeedback = new JTextArea();
		txtfeedback.setForeground(new Color(255, 127, 80));
		txtfeedback.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtfeedback.setBorder(new LineBorder(new Color(178, 34, 34), 5));
		txtfeedback.setBackground(new Color(211, 211, 211));
		txtfeedback.addKeyListener(this);
		scrollPane.setViewportView(txtfeedback);
		
		JButton btnsubmit = new JButton("SUBMIT");
		btnsubmit.setForeground(new Color(178, 34, 34));
		btnsubmit.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		btnsubmit.setBackground(new Color(189, 183, 107));
		btnsubmit.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnsubmit.setBounds(282, 493, 374, 60);
		btnsubmit.addActionListener(this);
		contentPane.add(btnsubmit);
		
		JLabel lblcustomerid = new JLabel("SELECT CUSTOMER ID:");
		lblcustomerid.setOpaque(true);
		lblcustomerid.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblcustomerid.setBackground(new Color(222, 184, 135));
		lblcustomerid.setForeground(new Color(47, 79, 79));
		lblcustomerid.setHorizontalAlignment(SwingConstants.CENTER);
		lblcustomerid.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblcustomerid.setBounds(31, 127, 343, 52);
		contentPane.add(lblcustomerid);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FeedbackForm.class.getResource("/complaintapp/images/feedback-business-quality-opinion-service-communication-concept-feedback-business-quality-opinion-service-communication-concept-112663339.jpg")));
		lblNewLabel_1.setBounds(0, 0, 957, 593);
		contentPane.add(lblNewLabel_1);
	}

	String feedbacktxt,cusid;
	@Override
	public void actionPerformed(ActionEvent e)
	{		
		feedbacktxt=txtfeedback.getText().trim();
		cusid=(String) cmbcustomerid.getSelectedItem();
		
		if(feedbacktxt.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Please fill feedback","Mandatory",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			insertItems();
			changeStatus();
			txtfeedback.setText("");
			cmbcustomerid.setModel(new DefaultComboBoxModel(new String[] {"SELECT CUSTOMER ID:"}));
			fillCombo();
		}
	}
	
	public void insertItems()
	{
		int flag=0;
		String strselect="select * from complaint inner join assigncomplaint where complaint.Complaintid=assigncomplaint.Complaintid && Customerid=?";
		try
		{
			ps=con.prepareStatement(strselect);
			ps.setString(1,cusid);
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				flag=1;
				java.util.Date d=new java.util.Date();
				long date=d.getTime();
				java.sql.Date ssd=new java.sql.Date(date);
				String strinsert="insert into feedback(Complaintid, Customerid, Cceid, Seid, Feedback_Text, Date) values(?,?,?,?,?,?)";
				try
				{
					ps1=con.prepareStatement(strinsert);
					ps1.setString(1,rs.getString("Complaintid"));
					ps1.setString(2,cusid);
					ps1.setString(3,"CCE001");
					ps1.setString(4,rs.getString("EmployeeId"));
					ps1.setString(5,feedbacktxt);
					ps1.setDate(6,ssd);
					
	                int row_status=ps1.executeUpdate();
					
					if(row_status > 0)
					{
						JOptionPane.showMessageDialog(this, "Feedback Added","Feedback",JOptionPane.INFORMATION_MESSAGE);
						
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
						if(ps1!=null)
							ps1.close();
					}
					catch(SQLException s)
					{
						s.printStackTrace();
					}
				}
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
				if(rs!=null)
					rs.close();
			}
			catch(SQLException s)
			{
				s.printStackTrace();
			}
		}
	}
	
	public void changeStatus()
	{
		String strchange="update complaint set Feedback_Status=? where Customerid=?";
		try 
		{
			ps=con.prepareStatement(strchange);
			ps.setString(1,"TAKEN");
			ps.setString(2,cusid);
			
			int status=ps.executeUpdate();
			if(status>0)
			{
				JOptionPane.showMessageDialog(this,"Feedback Status Updated Successfully in complaint table");
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(ps!=null)
					ps.close();
			}
			catch(SQLException ssd)
			{
				ssd.printStackTrace();
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
	public void keyTyped(KeyEvent e) 
	{
		
		
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
