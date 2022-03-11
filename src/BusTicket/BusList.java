package BusTicket;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton backbt;
	private Connection con;
	private Statement st;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BusList b=new BusList();
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public BusList() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BusTicket", "root", "aman2001");
			st = con.createStatement();

			JTable table = new JTable();

			Object[] columns = { "Bus Name", "Bus Number", "Driver's Name", "Driver' license no.", "From(city)", "To(city)","seating capacity"};
			model = new DefaultTableModel();

			ResultSet rs = st.executeQuery("Select * from bus ;");

			JFrame li = new JFrame();
			li.setTitle("Update Bus Details ");
			li.setVisible(true);
			li.setSize(1700, 800);
			li.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			li.setLayout(new FlowLayout());

			model.setColumnIdentifiers(columns);
			table.setModel(model);

			table.setBackground(Color.white);
			table.setForeground(Color.black);
			table.setGridColor(Color.red);
			table.setBounds(0, 0, 1500, 600);
			table.setAutoCreateRowSorter(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(200);
			table.getColumnModel().getColumn(4).setPreferredWidth(200);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			li.add(table);

			JScrollPane pane = new JScrollPane();
			pane.setForeground(Color.red);
			pane.setBackground(Color.white);
			// pane.setBounds(1650, 0, 50,800 );
			li.add(pane);

			Object[] row = new Object[7];
			while (rs.next()) {
				row[0] = rs.getString("Bus_Name");
				row[1] = rs.getString("Bus_No");
				row[2] = rs.getString("Driver_Name");
				row[3] = rs.getString("License_no");
				row[4] = rs.getString("FromCity");
				row[5] = rs.getString("ToCity");
				row[6] = rs.getInt("Seats");
				model.addRow(row);
			}
			
			backbt = new JButton();
			backbt.setText("Back");
			backbt.setBounds(100, 700, 80, 25);
			backbt.setBackground(Color.BLUE);
			backbt.setFocusable(false);
			backbt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == backbt) {
					
					Admin ad = new Admin();
					ad.setVisible(true);
					dispose();
				}
				}
			});
			li.add(backbt);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
}
