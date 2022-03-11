package BusTicket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Cancel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Connection con;
	private Statement st;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cancel frame = new Cancel();
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
	public Cancel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter bus name");
		lblNewLabel.setBounds(10, 11, 96, 26);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(141, 14, 203, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterPassengerName = new JLabel("Enter passenger name");
		lblEnterPassengerName.setBounds(10, 65, 125, 26);
		contentPane.add(lblEnterPassengerName);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(141, 65, 203, 20);
		contentPane.add(textField_1);
		
		JLabel lblEnterPhoneNo = new JLabel("enter phone no");
		lblEnterPhoneNo.setBounds(10, 118, 125, 26);
		contentPane.add(lblEnterPhoneNo);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(141, 121, 203, 20);
		contentPane.add(textField_2);
		
		JLabel lblEnterSeatNo = new JLabel("enter seat no");
		lblEnterSeatNo.setBounds(10, 177, 125, 26);
		contentPane.add(lblEnterSeatNo);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(141, 180, 203, 20);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton)
				{
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicket", "root", "aman2001");
						st = con.createStatement();
					String bn,pn;
				    int seat;
				    bn=textField.getText();
				    pn=textField_1.getText();
				    seat=Integer.parseInt(textField_3.getText());
				    String q = "select count(*) from "+bn+" where  Name ='" + pn + "' AND SeatNo ='" + seat + "';";
					ResultSet r = st.executeQuery(q);
					int serial = 0;
					int temp = serial;
					while (r.next())
						serial = r.getInt("count(*)");
					if(serial!=0) {
						String dlt="delete from "+bn+" where SeatNo="+serial+";";
						String upd="update "+bn+" set SeatNo=SeatNo-1 where SeatNo="+(serial+1);
						st.executeUpdate(dlt);
						st.executeUpdate(upd);
						JOptionPane.showMessageDialog(null, "Successfull", "info", JOptionPane.ERROR_MESSAGE);
						dispose();
						Menu me = new Menu();
						me.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect data", "info", JOptionPane.ERROR_MESSAGE);
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField_3.setText("");
					}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					}
			}
		});
		btnNewButton.setBounds(153, 227, 89, 23);
		contentPane.add(btnNewButton);
	}

}
