package complaintapp.entry;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import complaintapp.service_engineer.UpdateComplaintStatus;
import complaintapp.service_engineer.ViewAllAssigned_NotResolvedComplaints;
import complaintapp.service_engineer.ViewAllComplaints;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Color;

import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class ServiceEngineerAdmin extends JFrame implements ActionListener,WindowListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceEngineerAdmin frame = new ServiceEngineerAdmin();
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
	public ServiceEngineerAdmin() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(ServiceEngineerAdmin.class.getResource("/complaintapp/images/admin.jpg")));
		setResizable(false);
		setTitle("SERVICE ENGINEER ADMIN");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		this.addWindowListener(this);
		createComponents();
	}

	JButton btnviewallcmp,btnupdate,btnassigned;
	private JLabel lblNewLabel_1;
	public void createComponents()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1311, 738);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 100, 0), 8));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SERVICE ENGINEER ADMIN PAGE");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 128), 5));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(189, 183, 107));
		lblNewLabel.setForeground(new Color(85, 107, 47));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(58, 11, 1165, 91);
		contentPane.add(lblNewLabel);
		
		btnupdate = new JButton("UPDATE COMPLAINT STATUS");
		btnupdate.setBorder(new LineBorder(new Color(220, 20, 60), 5));
		btnupdate.setBackground(new Color(0, 139, 139));
		btnupdate.setForeground(new Color(178, 34, 34));
		btnupdate.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		btnupdate.setBounds(247, 208, 775, 70);
		btnupdate.addActionListener(this);
		contentPane.add(btnupdate);
		
		btnassigned = new JButton("VIEW ALL ASSIGNED AND NOT RESOLVED COMPLAINTS");
		btnassigned.setBorder(new LineBorder(new Color(220, 20, 60), 5));
		btnassigned.setBackground(new Color(0, 128, 128));
		btnassigned.setForeground(new Color(178, 34, 34));
		btnassigned.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnassigned.setBounds(247, 376, 775, 70);
		btnassigned.addActionListener(this);
		contentPane.add(btnassigned);
		
	    btnviewallcmp = new JButton("VIEW ALL COMPLAINTS");
	    btnviewallcmp.setBorder(new LineBorder(new Color(220, 20, 60), 5));
	    btnviewallcmp.setBackground(new Color(0, 128, 128));
		btnviewallcmp.setForeground(new Color(178, 34, 34));
		btnviewallcmp.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnviewallcmp.setBounds(247, 544, 775, 70);
		btnviewallcmp.addActionListener(this);
		contentPane.add(btnviewallcmp);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ServiceEngineerAdmin.class.getResource("/complaintapp/images/complaint-pile-business-documents-desk-complaint-pile-business-documents-desk-100936147.jpg")));
		lblNewLabel_1.setBounds(10, 11, 1259, 676);
		contentPane.add(lblNewLabel_1);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object obj=e.getSource();
		JButton jb=(JButton)obj;
		
		if(jb == btnupdate)
		{
			UpdateComplaintStatus ucccs=new UpdateComplaintStatus();
			ucccs.setVisible(true);
		}
		
		if(jb == btnassigned)
		{
			ViewAllAssigned_NotResolvedComplaints varc=new ViewAllAssigned_NotResolvedComplaints();
			varc.setVisible(true);
		}
		
		if(jb == btnviewallcmp)
		{
			ViewAllComplaints vacc=new ViewAllComplaints();
			vacc.setVisible(true);
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		JOptionPane.showMessageDialog(this,"THANK YOU");
		this.dispose();
		new EmployeeLogin().setVisible(true);
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
