package BusTicket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Add_Bus_det extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private Connection con;
	private Statement st;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Bus_det frame = new Add_Bus_det();
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
	public Add_Bus_det() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Bus Details");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(245, 11, 329, 31);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Bus Name");
		lblNewLabel_1.setBounds(10, 62, 164, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Bus Number");
		lblNewLabel_1_1.setBounds(10, 98, 164, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Driver's Name");
		lblNewLabel_1_1_1.setBounds(10, 134, 164, 14);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Driver's license no.");
		lblNewLabel_1_1_1_1.setBounds(10, 173, 164, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("From(city)");
		lblNewLabel_1_1_1_1_1.setBounds(10, 217, 164, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Seat Capacity");
		lblNewLabel_1_1_1_1_1_1.setBounds(10, 305, 164, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);

		textField = new JTextField();
		textField.setBounds(130, 59, 598, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(130, 95, 598, 20);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(130, 131, 598, 20);
		contentPane.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(130, 170, 598, 20);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(130, 214, 598, 20);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(130, 302, 598, 20);
		contentPane.add(textField_5);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) {
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicket", "root", "aman2001");
						st = con.createStatement();
						String busName, busNo, driverName, license, frcity,tocity;
						busName = textField.getText();
						busNo = textField_1.getText();
						driverName = textField_2.getText();
						license = textField_3.getText();
						frcity = textField_4.getText();
						tocity = textField_6.getText();
						int seats = Integer.parseInt(textField_5.getText());
						String create = "create table " + busName
								+ " (SeatNo integer(10) primary key, Name varchar(40) not null, Gender varchar(10) not null , Age integer(10) not null, DateJ date not null, FromCity varchar(40) not null, ToCity varchar(40) not null, Phone varchar(15) not null );";
						if (busName == "" || busNo == "" || driverName == "" || license == "" || frcity == "" || tocity==""
								|| seats == 0) {
							JOptionPane.showMessageDialog(null, "Fields can't be empty", "Error",
									JOptionPane.INFORMATION_MESSAGE);
						}

						else {
							String query = "insert into bus values( '" + busName + "', '" + busNo + "', '"
									+ driverName + "', '"

									+ license + "', '" + frcity + "', '"+ tocity + "', '" + seats + "');";
							st.executeUpdate(query);
							st.executeUpdate(create);
							JOptionPane.showMessageDialog(null, "Successfully Registered", "Info",
									JOptionPane.INFORMATION_MESSAGE);
						}
						dispose();
						Admin ad = new Admin();
						ad.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(352, 341, 89, 23);
		contentPane.add(btnNewButton);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(130, 259, 598, 20);
		contentPane.add(textField_6);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("to(city)");
		lblNewLabel_1_1_1_1_1_2.setBounds(10, 262, 164, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1_2);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_1) {
				dispose();
				Admin ad = new Admin();
				ad.setVisible(true);
			}
			}
		});
		btnNewButton_1.setBounds(352, 387, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
