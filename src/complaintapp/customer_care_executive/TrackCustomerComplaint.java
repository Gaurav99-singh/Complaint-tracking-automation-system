package complaintapp.customer_care_executive;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.beans.SeeComplaintsBean;
import complaintapp.database.DbConnection1;
import complaintapp.entry.CustomerCareExecutiveAdmin;
import complaintapp.entry.EmployeeLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.*;
import java.sql.*;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;


public class TrackCustomerComplaint extends JFrame implements ActionListener,WindowListener,KeyListener{

	private JPanel contentPane;
	private JTextField txtcomplaintid;
	private JTextField txtcustomerid;
	private JTextField txtcustomername;
	private JTextField txtprodname;
	private JTextField txtcomptext;
	private JTextField txtstatus;
	private JTextField txtremark;
	
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
					TrackCustomerComplaint frame = new TrackCustomerComplaint();
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
	public TrackCustomerComplaint() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(TrackCustomerComplaint.class.getResource("/complaintapp/images/icons8-track-order-50.png")));
		setTitle("TRACK CUSTOMER COMPLAINT");
		createComponents();
		con=DbConnection1.createConnection();	
		this.addWindowListener(this);
		
	}
	
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 964, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TRACK CUSTOMER COMPLAINT PAGE");
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new LineBorder(new Color(47, 79, 79), 5));
		lblNewLabel.setBackground(new Color(255, 228, 196));
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(151, 11, 663, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER COMPLAINT ID:");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(new Color(250, 128, 114));
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_1.setBackground(new Color(245, 222, 179));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(65, 126, 317, 52);
		contentPane.add(lblNewLabel_1);
		
		txtcomplaintid = new JTextField();
		txtcomplaintid.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtcomplaintid.setBackground(new Color(240, 248, 255));
		txtcomplaintid.setHorizontalAlignment(SwingConstants.CENTER);
		txtcomplaintid.setForeground(new Color(255, 69, 0));
		txtcomplaintid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtcomplaintid.setBounds(427, 126, 270, 52);
		contentPane.add(txtcomplaintid);
		txtcomplaintid.setColumns(10);
		
		JButton btnview = new JButton("VIEW");
		btnview.setForeground(new Color(47, 79, 79));
		btnview.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnview.setBorder(new LineBorder(new Color(178, 34, 34), 5));
		btnview.setBackground(new Color(189, 183, 107));
		btnview.setBounds(745, 130, 164, 48);
		btnview.addActionListener(this);
		contentPane.add(btnview);
		
		txtcustomerid = new JTextField();
		txtcustomerid.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtcustomerid.setBackground(new Color(240, 248, 255));
		txtcustomerid.setHorizontalAlignment(SwingConstants.CENTER);
		txtcustomerid.setForeground(new Color(255, 69, 0));
		txtcustomerid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtcustomerid.setEnabled(false);
		txtcustomerid.setEditable(false);
		txtcustomerid.setHorizontalAlignment(SwingConstants.CENTER);
		txtcustomerid.setBounds(427, 204, 297, 36);
		contentPane.add(txtcustomerid);
		txtcustomerid.setColumns(10);
		
		txtcustomername = new JTextField();
		txtcustomername.setEnabled(false);
		txtcustomername.setEditable(false);
		txtcustomername.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtcustomername.setBackground(new Color(240, 248, 255));
		txtcustomername.setHorizontalAlignment(SwingConstants.CENTER);
		txtcustomername.setForeground(new Color(255, 69, 0));
		txtcustomername.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtcustomername.setBounds(427, 303, 297, 36);
		contentPane.add(txtcustomername);
		txtcustomername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CUSTOMER ID:");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(new Color(250, 128, 114));
		lblNewLabel_2.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_2.setBackground(new Color(245, 222, 179));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(65, 200, 317, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CUSTOMER NAME:");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setForeground(new Color(250, 128, 114));
		lblNewLabel_3.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_3.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_3.setBackground(new Color(245, 222, 179));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(65, 299, 317, 36);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("PRODUCT NAME:\r\n");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setForeground(new Color(250, 128, 114));
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_4.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_4.setBackground(new Color(245, 222, 179));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(65, 346, 317, 36);
		contentPane.add(lblNewLabel_4);
		
		txtprodname = new JTextField();
		txtprodname.setEnabled(false);
		txtprodname.setEditable(false);
		txtprodname.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtprodname.setBackground(new Color(240, 248, 255));
		txtprodname.setHorizontalAlignment(SwingConstants.CENTER);
		txtprodname.setForeground(new Color(255, 69, 0));
		txtprodname.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtprodname.setBounds(427, 350, 297, 36);
		contentPane.add(txtprodname);
		txtprodname.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("COMPLAINT TEXT:");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setForeground(new Color(250, 128, 114));
		lblNewLabel_5.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_5.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_5.setBackground(new Color(245, 222, 179));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(65, 393, 317, 36);
		contentPane.add(lblNewLabel_5);
		
		txtcomptext = new JTextField();
		txtcomptext.setEnabled(false);
		txtcomptext.setEditable(false);
		txtcomptext.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtcomptext.setBackground(new Color(240, 248, 255));
		txtcomptext.setHorizontalAlignment(SwingConstants.CENTER);
		txtcomptext.setForeground(new Color(255, 69, 0));
		txtcomptext.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtcomptext.setBounds(427, 397, 297, 36);
		contentPane.add(txtcomptext);
		txtcomptext.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("COMPLAINT STATUS:");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setForeground(new Color(250, 128, 114));
		lblNewLabel_6.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_6.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_6.setBackground(new Color(245, 222, 179));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(65, 440, 317, 36);
		contentPane.add(lblNewLabel_6);
		
		txtstatus = new JTextField();
		txtstatus.setEnabled(false);
		txtstatus.setEditable(false);
		txtstatus.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtstatus.setBackground(new Color(240, 248, 255));
		txtstatus.setHorizontalAlignment(SwingConstants.CENTER);
		txtstatus.setForeground(new Color(255, 69, 0));
		txtstatus.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtstatus.setBounds(427, 444, 297, 36);
		contentPane.add(txtstatus);
		txtstatus.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("REMARK:");
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setForeground(new Color(250, 128, 114));
		lblNewLabel_7.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_7.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_7.setBackground(new Color(245, 222, 179));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(65, 513, 317, 38);
		contentPane.add(lblNewLabel_7);
		
		txtremark = new JTextField();
		txtremark.setEnabled(false);
		txtremark.setEditable(false);
		txtremark.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtremark.setBackground(new Color(240, 248, 255));
		txtremark.setHorizontalAlignment(SwingConstants.CENTER);
		txtremark.setForeground(new Color(255, 69, 0));
		txtremark.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtremark.setBounds(427, 501, 297, 93);
		contentPane.add(txtremark);
		txtremark.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("EMPLOYEE ID:");
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.setForeground(new Color(250, 128, 114));
		lblNewLabel_8.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_8.setBorder(new LineBorder(new Color(47, 79, 79), 3));
		lblNewLabel_8.setBackground(new Color(245, 222, 179));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(65, 252, 317, 36);
		contentPane.add(lblNewLabel_8);
		
		txtempid = new JTextField();
		txtempid.setEnabled(false);
		txtempid.setEditable(false);
		txtempid.setBorder(new LineBorder(new Color(25, 25, 112), 3));
		txtempid.setBackground(new Color(240, 248, 255));
		txtempid.setHorizontalAlignment(SwingConstants.CENTER);
		txtempid.setForeground(new Color(255, 69, 0));
		txtempid.setFont(new Font("Stencil", Font.BOLD | Font.ITALIC, 20));
		txtempid.setBounds(427, 252, 297, 37);
		contentPane.add(txtempid);
		txtempid.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(TrackCustomerComplaint.class.getResource("/complaintapp/images/funny-help-desk-customer-service-scene-fun-kiosk-troll-monster-beast-taking-complaints-helping-people-not-47413701.jpg")));
		lblNewLabel_9.setBounds(0, 0, 943, 599);
		contentPane.add(lblNewLabel_9);
	}
	
	String compid;
	private JTextField txtempid;
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		compid=txtcomplaintid.getText().trim();
		
		if(!empty())
		{
			int flag=0;
			String strsql="select EmployeeId, Complaintid, Complaint_Status, Remarks from assigncomplaint where Complaintid=?";
			try
			{
				ps=con.prepareStatement(strsql);
				ps.setString(1,compid);
				rs=ps.executeQuery();
				if(rs.next())
				{
					flag=1;
					txtempid.setText(rs.getString("EmployeeId"));
					txtstatus.setText(rs.getString("Complaint_Status"));
					txtremark.setText(rs.getString("Remarks"));
					String strsee="select customer.CustomerId,customer.Name,complaint.ProductName, complaint.Complaint_text from complaint inner join customer where complaint.Customerid=customer.Customerid && complaint.Complaintid=?";
					try
					{
						ps1=con.prepareStatement(strsee);
						ps1.setString(1,compid); 
						rs1=ps1.executeQuery();
						if(rs1.next())
						{
							flag=1;
							txtcustomerid.setText(rs1.getString("Customerid"));
							txtcustomername.setText(rs1.getString("Name"));
							txtcomptext.setText(rs1.getString("Complaint_text"));
							txtprodname.setText(rs1.getString("ProductName"));
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
				JOptionPane.showMessageDialog(this,"NO SUCH COMPLAINT EXISTS","MESSAGE",JOptionPane.PLAIN_MESSAGE);
				txtcomplaintid.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"SEE COMPLAINT ASSIGNED","MESSAGE",JOptionPane.PLAIN_MESSAGE);
				txtcomplaintid.setText("");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Please enter complaint id","MANDATORY",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public boolean empty()
	{
		if(compid.isEmpty())
			return true;
		return false;
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
