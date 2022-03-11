package BusTicket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static Login frame;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private Connection con;
	private Statement st;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Login Page");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Ebrima", Font.BOLD, 15));
		lblNewLabel_4.setBounds(158, 11, 213, 32);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(43, 53, 105, 34);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(43, 101, 105, 34);
		contentPane.add(lblPassword);

		textField = new JTextField();
		textField.setBounds(158, 60, 295, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(158, 108, 295, 20);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) {
					try {
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicket", "root", "aman2001");
						st = con.createStatement();
						ResultSet rk = st.executeQuery(
								"Select count(*) from users where username = '" + textField.getText() + "';");
						int i = 0;
						while (rk.next()) {
							i = rk.getInt("count(*)");
						}

						if (i != 0 || String.valueOf(passwordField.getPassword()) != "") {

							ResultSet rs = st.executeQuery(
									"Select * from users where Username = '" + textField.getText() + "';");
							String sk = "";
							while (rs.next()) {
								sk = rs.getString("Password");
							}
							char p[] = sk.toCharArray();
							char pass[] = passwordField.getPassword();
							if (Arrays.equals(pass, p)) {
								JOptionPane.showMessageDialog(null, "welcome", "login successfully",
										JOptionPane.ERROR_MESSAGE);
								dispose();
								Menu mm=new Menu();
								mm.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "incorrect password", "Error",
										JOptionPane.ERROR_MESSAGE);
								passwordField.setText("");
							}
						} else
							JOptionPane.showMessageDialog(null, " invalid username", "Error",
									JOptionPane.ERROR_MESSAGE);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});

		btnNewButton.setBounds(158, 160, 135, 23);
		contentPane.add(btnNewButton);

		JButton btnNotAUser = new JButton("Not a user? Register");
		btnNotAUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				if (e1.getSource() == btnNotAUser) {
					Signup sp = new Signup();
					sp.setVisible(true);
				}
			}
		});
		btnNotAUser.setBounds(158, 218, 143, 34);
		contentPane.add(btnNotAUser);

		JButton btnNewButton_1 = new JButton("Login as admin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNameValue="",passwordValue="";
				JLabel jUserName = new JLabel("User Name");
		        JTextField userName = new JTextField();
		        JLabel jPassword = new JLabel("Password");
		        JTextField password = new JPasswordField();
		        Object[] ob = {jUserName, userName, jPassword, password};
		        int result = JOptionPane.showConfirmDialog(null, ob, "Please input password for JOptionPane showConfirmDialog", JOptionPane.OK_CANCEL_OPTION);
		        if (result == JOptionPane.OK_OPTION) {
		             userNameValue = userName.getText();
		            passwordValue = password.getText();
		            //Here is some validation code
		        }
		        try {
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicket", "root", "aman2001");
	st = con.createStatement();
	ResultSet rks = st.executeQuery(
			"Select count(*) from admin where username = '" + userNameValue + "';");
	int i = 0;
	while (rks.next()) {
		i = rks.getInt("count(*)");
	}
	if(i!=0 && passwordValue!="") {
		ResultSet rst = st.executeQuery(
				"Select * from admin where Username = '" + userNameValue + "';");
		String sks ="";
		while (rst.next()) {
			sks = rst.getString("Pasword");
		}
		if (sks.equals(passwordValue)) {
			JOptionPane.showMessageDialog(null, "welcome", "login successfully",
					JOptionPane.OK_OPTION);
			dispose();
			Admin add=new Admin();
			add.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "incorrect password", "Error",
					JOptionPane.ERROR_MESSAGE);
			
		}
	}
	else JOptionPane.showMessageDialog(null, "invalid username or password", "Error",
			JOptionPane.ERROR_MESSAGE);
}catch(Exception exc) {
	System.out.println(exc.getMessage());
}
			}
		});
		btnNewButton_1.setBounds(158, 285, 143, 23);
		contentPane.add(btnNewButton_1);

	}
}
