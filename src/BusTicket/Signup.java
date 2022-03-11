package BusTicket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private static Signup frame;
	private Connection con;
	private Statement st;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Signup();
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
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(10, 53, 71, 23);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(91, 54, 234, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(352, 53, 71, 23);
		contentPane.add(lblLastName);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(432, 54, 234, 20);
		contentPane.add(textField_1);

		JLabel lblNewLabel_1 = new JLabel("Gender");
		lblNewLabel_1.setBounds(10, 87, 46, 17);
		contentPane.add(lblNewLabel_1);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setBounds(91, 87, 56, 17);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(162, 85, 71, 20);
		contentPane.add(rdbtnFemale);

		JRadioButton rdbtnOther = new JRadioButton("Other");
		rdbtnOther.setBounds(235, 84, 71, 20);
		contentPane.add(rdbtnOther);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnFemale);
		group.add(rdbtnOther);

		JLabel lblDateOfBirtjh = new JLabel("Date of Birth");
		lblDateOfBirtjh.setBounds(352, 87, 71, 17);
		contentPane.add(lblDateOfBirtjh);

		SpinnerModel value = new SpinnerNumberModel(1995, 1900, 2005, 1);
		JSpinner spinner = new JSpinner(value);
		spinner.setToolTipText("year");
		spinner.setBounds(432, 86, 71, 17);
		contentPane.add(spinner);

		SpinnerModel value1 = new SpinnerNumberModel(1, 1, 12, 1);
		JSpinner spinner_1 = new JSpinner(value1);
		spinner_1.setToolTipText("month");
		spinner_1.setBounds(514, 85, 71, 20);
		contentPane.add(spinner_1);

		SpinnerModel value2 = new SpinnerNumberModel(1, 1, 31, 1);
		JSpinner spinner_2 = new JSpinner(value2);
		spinner_2.setToolTipText("day");
		spinner_2.setBounds(595, 85, 71, 20);
		contentPane.add(spinner_2);

		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setBounds(10, 170, 71, 14);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(91, 167, 234, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Create Password");
		lblNewLabel_3.setBounds(10, 254, 110, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_3_1 = new JLabel("Confirm Password");
		lblNewLabel_3_1.setBounds(10, 300, 110, 14);
		contentPane.add(lblNewLabel_3_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(162, 251, 163, 20);
		contentPane.add(passwordField);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(162, 297, 163, 20);
		contentPane.add(passwordField_1);

		JLabel lblNewLabel_2_1 = new JLabel("Phone ");
		lblNewLabel_2_1.setBounds(10, 130, 71, 14);
		contentPane.add(lblNewLabel_2_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(91, 127, 234, 20);
		contentPane.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(91, 209, 234, 20);
		contentPane.add(textField_4);

		JLabel lblNewLabel_2_2 = new JLabel("Username");
		lblNewLabel_2_2.setBounds(10, 212, 71, 14);
		contentPane.add(lblNewLabel_2_2);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) {
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicket", "root", "aman2001");
						st = con.createStatement();
						String nm, gen = "", dob, phn, email, usernm;
						nm = textField.getText() + textField_1.getText();
						if (rdbtnNewRadioButton.isSelected())
							gen = "Male";
						else if (rdbtnFemale.isSelected())
							gen = "Female";
						else if (rdbtnOther.isSelected())
							gen = "Other";
						dob = spinner.getValue() + "-" + spinner_1.getValue() + "-" + spinner_2.getValue();
						phn = textField_3.getText();
						Pattern p1 = Pattern.compile("(6|7|8|9)\\d{9}");
						Matcher m1 = p1.matcher(phn);
						email = textField_2.getText();
						Pattern p2 = Pattern.compile("[A-Za-z0-9.A-Za-z0-9]+[@]+[a-z]+(.com)");
						Matcher m2 = p2.matcher(email);
						usernm = textField_4.getText();
						ResultSet r = st.executeQuery(
								"Select count(*) as total_row from Users where Username= '" + usernm + "';");
						int serial = 0;
						while (r.next())
							serial = r.getInt("total_row");
						char crtpass[] = passwordField.getPassword();
						char cnfrmpass[] = passwordField_1.getPassword();
						String password = String.valueOf(cnfrmpass);
						boolean pass = Arrays.equals(crtpass, cnfrmpass);
						if (m1.matches() && m2.matches() && serial == 0 && pass) {
							String s = "insert into Users values( '" + nm + "', '" + usernm + "', '" + password + "', '"
									+ gen + "', '" + dob + "', '" + phn + "', '" + email + "');";
							st.executeUpdate(s);
							JOptionPane.showMessageDialog(null, "Successfully Registered", "Info",
									JOptionPane.INFORMATION_MESSAGE);
							Login lg = new Login();
							lg.setVisible(true);
							dispose();

						} else {
							if (!m1.matches()) {
								JOptionPane.showMessageDialog(null, "mobile no invalid", "Error",
										JOptionPane.ERROR_MESSAGE);
								textField_3.setText("");
							}
							if (!m2.matches()) {
								JOptionPane.showMessageDialog(null, "Email invalid", "Error",
										JOptionPane.ERROR_MESSAGE);
								textField_2.setText("");
							}
							if (serial != 0) {
								JOptionPane.showMessageDialog(null, "username already exist, try new one", "Error",
										JOptionPane.ERROR_MESSAGE);
								textField_4.setText("");
							}
							if (!pass) {
								JOptionPane.showMessageDialog(null, "Password doesn't match, try new one", "Error",
										JOptionPane.ERROR_MESSAGE);
								passwordField.setText("");
								passwordField_1.setText("");
							}
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		});
		btnNewButton.setBounds(162, 344, 124, 23);
		contentPane.add(btnNewButton);

		JButton btnBactToLogin = new JButton("Bact to login");
		btnBactToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnBactToLogin) {

					Login l = new Login();
					l.setVisible(true);
					frame.dispose();
				}
			}
		});
		btnBactToLogin.setBounds(162, 394, 124, 23);
		contentPane.add(btnBactToLogin);

		JLabel lblNewLabel_4 = new JLabel("Signup Page");
		lblNewLabel_4.setFont(new Font("Ebrima", Font.BOLD, 15));
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(20, 11, 751, 31);
		contentPane.add(lblNewLabel_4);
	}

}
