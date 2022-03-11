package BusTicket;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addPassenger extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private static Signup frame;
	private Connection con;
	private Statement st;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addPassenger frame = new addPassenger();
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
	public addPassenger() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 331);
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
		textField_1.setBounds(454, 54, 234, 20);
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

		JLabel lblDateOfBirtjh = new JLabel("Date 0f Journey");
		lblDateOfBirtjh.setBounds(352, 87, 93, 17);
		contentPane.add(lblDateOfBirtjh);

		int month = Integer.parseInt((java.time.LocalDate.now().toString()).substring(5, 7));
		SpinnerModel value1 = new SpinnerNumberModel(month, month, 12, 1);
		JSpinner spinner_1 = new JSpinner(value1);
		spinner_1.setToolTipText("month");
		spinner_1.setBounds(454, 110, 46, 20);
		contentPane.add(spinner_1);

		int dt = Integer.parseInt((java.time.LocalDate.now().toString()).substring(8, 10));
		SpinnerModel value2 = new SpinnerNumberModel(dt, dt, 31, 1);
		JSpinner spinner_2 = new JSpinner(value2);
		spinner_2.setToolTipText("day");
		spinner_2.setBounds(533, 110, 46, 20);
		contentPane.add(spinner_2);

		JLabel lblNewLabel_2 = new JLabel("From(city)");
		lblNewLabel_2.setBounds(10, 170, 71, 14);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(91, 167, 234, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2_1 = new JLabel("Age");
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

		JLabel lblNewLabel_2_2 = new JLabel("To(city)");
		lblNewLabel_2_2.setBounds(10, 212, 71, 14);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_4 = new JLabel("Month");
		lblNewLabel_4.setBounds(464, 85, 46, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Date");
		lblNewLabel_4_1.setBounds(547, 85, 46, 14);
		contentPane.add(lblNewLabel_4_1);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) {
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicket", "root", "aman2001");
						st = con.createStatement();
						String nm, gen = "", doj, age, fr, to, phn;
						nm = textField.getText() + textField_1.getText();
						if (rdbtnNewRadioButton.isSelected())
							gen = "Male";
						else if (rdbtnFemale.isSelected())
							gen = "Female";
						else if (rdbtnOther.isSelected())
							gen = "Other";
						doj = Integer.parseInt((java.time.LocalDate.now().toString()).substring(0, 4)) + "-"
								+ spinner_1.getValue() + "-" + spinner_2.getValue();
						age = textField_3.getText();
						fr = textField_2.getText();
						to = textField_4.getText();
						phn = textField_5.getText();
						Pattern p1 = Pattern.compile("(6|7|8|9)\\d{9}");
						Matcher m1 = p1.matcher(phn);
						String q = "select count(*) from bus where FromCity ='" + fr + "' AND ToCity ='" + to + "';";
						ResultSet r = st.executeQuery(q);
						int serial = 0;
						int temp = serial;
						String s = "";
						while (r.next())
							serial = r.getInt("count(*)");
						if (serial != 0) {
							int serial1 = 0;
							String q1 = "select Bus_Name from bus where FromCity ='" + fr + "' AND ToCity ='" + to + "';";
							ResultSet r1 = st.executeQuery(q1);
							while (r1.next()) {
								s = r1.getString("Bus_Name");
								String s1 = "select count(SeatNo) from " + s;
								ResultSet r2 = st.executeQuery(s1);

								while (r2.next())
									serial1 = r2.getInt("count(SeatNo)");
								String s2 = "select Seats from bus where Bus_Name= '" + s + "'";
								ResultSet r3 = st.executeQuery(s2);
								int serial2 = 0;
								while (r3.next())
									serial2 = r3.getInt("Seats");
								if (serial1 < serial2)
									break;
								else
									temp--;
								if (temp == 0) {
									JOptionPane.showMessageDialog(null, "No seats available", "Error",
											JOptionPane.ERROR_MESSAGE);
									dispose();
									Menu m = new Menu();
									m.setVisible(true);
								}

							}
							if (m1.matches()) {
								String query = "insert into " + s + " values( '" + (serial1 + 1) + "', '" + nm + "', '"+ gen + "', '" + age + "', '"+ doj + "', '" + fr + "', '"+to+"', '" + phn + "');";
								st.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Successfull,Your BusName is "+s, "info", JOptionPane.ERROR_MESSAGE);
								dispose();
								Menu me = new Menu();
								me.setVisible(true);
							} else if (!m1.matches()) {
								JOptionPane.showMessageDialog(null, "Invalid phone no", "error",
										JOptionPane.ERROR_MESSAGE);
								textField_5.setText("");
							}

						} else {
							JOptionPane.showMessageDialog(null, "No service available", "info",
									JOptionPane.ERROR_MESSAGE);
							dispose();
							Menu mes = new Menu();
							mes.setVisible(true);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(454, 166, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnBack) {
					dispose();
					Menu mes = new Menu();
					mes.setVisible(true);
				}
			}
		});
		btnBack.setBounds(454, 208, 89, 23);
		contentPane.add(btnBack);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(91, 250, 234, 20);
		contentPane.add(textField_5);

		JLabel lblNewLabel_2_2_1 = new JLabel("Phone");
		lblNewLabel_2_2_1.setBounds(10, 253, 71, 14);
		contentPane.add(lblNewLabel_2_2_1);
	}
}
