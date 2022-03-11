package BusTicket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

public class ticket extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private Connection con;
	private Statement st;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ticket frame = new ticket();
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
	public ticket() {
		String BusNameValue="",PNameValue="",PhoneValue="";
		JLabel jBusName = new JLabel("Bus Name");
        JTextField BusName = new JTextField();
        JLabel jPName = new JLabel("Passenger Name");
        JTextField PName = new JTextField();
        JLabel jPhone = new JLabel("Phone Number");
        JTextField Phone = new JTextField();
        
        Object[] ob = {jBusName, BusName, jPName, PName,jPhone,Phone};
        int result = JOptionPane.showConfirmDialog(null, ob, "Please input the details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
             BusNameValue = BusName.getText();
            PNameValue = PName.getText();
            PhoneValue=Phone.getText();
            //Here is some validation code
        }
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seat Number");
		lblNewLabel.setBounds(10, 32, 106, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 106, 106, 14);
		contentPane.add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 142, 106, 14);
		contentPane.add(lblGender);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 180, 106, 14);
		contentPane.add(lblAge);
		
		JLabel lblDateOfJourney = new JLabel("Date Of Journey");
		lblDateOfJourney.setBounds(10, 221, 106, 14);
		contentPane.add(lblDateOfJourney);
		
		JLabel lblDepartueFrom = new JLabel("Departue from");
		lblDepartueFrom.setBounds(10, 257, 106, 14);
		contentPane.add(lblDepartueFrom);
		
		JLabel lblArrivalAt = new JLabel("Arrival At");
		lblArrivalAt.setBounds(10, 292, 106, 14);
		contentPane.add(lblArrivalAt);
		
		JLabel lblBusName = new JLabel("Bus Name");
		lblBusName.setBounds(10, 70, 106, 14);
		contentPane.add(lblBusName);
		
		textField = new JTextField();
		textField.setBounds(141, 29, 426, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(141, 67, 426, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(141, 103, 426, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(141, 139, 426, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(141, 177, 426, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(141, 218, 426, 20);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(141, 254, 426, 20);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(141, 289, 426, 20);
		contentPane.add(textField_7);
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicket", "root", "aman2001");
			st = con.createStatement();
			String s="select * from "+BusNameValue+" where Name='"+PNameValue+"' AND Phone='"+PhoneValue+"';";
			ResultSet r = st.executeQuery(s);
			while (r.next()) {
			textField.setText(""+r.getInt("SeatNo"));
			textField_1.setText(BusNameValue);
			textField_2.setText(r.getString("Name"));
			textField_3.setText(r.getString("Gender"));
			textField_4.setText(""+r.getInt("Age"));
			textField_5.setText(""+r.getDate("DateJ"));
			textField_6.setText(r.getString("FromCity"));
			textField_7.setText(r.getString("ToCity"));
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnNewButton) {
					Menu m=new Menu();
					m.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton.setBounds(271, 334, 89, 23);
		contentPane.add(btnNewButton);
	}
}
