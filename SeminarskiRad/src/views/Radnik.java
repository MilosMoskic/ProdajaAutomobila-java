package views;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import common.HelperClass;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class Radnik extends JFrame {

	static HelperClass pomocnaKlasa = new HelperClass();
	
	private JPanel contentPane;

	private JButton btnMeni, btnUpisi;
	private JTextField textIme;
	private JTextField textPrezime;
	private JTable tableR;
	private JComboBox comboBox;
	
	public void errorMessage() {
		dispose();
		JOptionPane.showMessageDialog(null, "Popunite sva polja!");
	}
	
	public void displayTable() {

	      Connection connect = pomocnaKlasa.DataBaseConnection();
		try {
			
			String query = "SELECT * FROM radnik";
			
			tableR.setModel(new DefaultTableModel());
			
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tableR.getModel(); 
			
			int columns = rsmd.getColumnCount();
			String[] colName = new String[columns];
			
			for(int i = 0; i<columns; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			
			while(rs.next()) {
				model.addRow(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
				
			}
			connect.close();
			
			
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Radnik frame = new Radnik();
					frame.setVisible(true);
					Radnik radnik = (Radnik) pomocnaKlasa.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public Radnik() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Radnik.class.getResource("/resources/radnik.png")));
		setTitle("Radnici");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(184, 134, 11));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initComponents();
		displayTable();
		createEvents();
	}
	
	public void initComponents() {
		btnMeni = new JButton("Meni");
		btnMeni.setBackground(Color.LIGHT_GRAY);
		btnMeni.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnMeni.setBounds(46, 209, 85, 21);
		contentPane.add(btnMeni);
		
		btnUpisi = new JButton("Upi\u0161i");
		btnUpisi.setBackground(Color.LIGHT_GRAY);
		btnUpisi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnUpisi.setBounds(46, 166, 85, 21);
		contentPane.add(btnUpisi);
		
		textIme = new JTextField();
		textIme.setBounds(75, 35, 96, 19);
		contentPane.add(textIme);
		textIme.setColumns(10);
		
		textPrezime = new JTextField();
		textPrezime.setBounds(75, 78, 96, 19);
		contentPane.add(textPrezime);
		textPrezime.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(181, 0, 255, 263);
		contentPane.add(scrollPane);
		
		tableR = new JTable();
		tableR.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(tableR);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblIme.setForeground(Color.WHITE);
		lblIme.setBounds(20, 39, 45, 13);
		contentPane.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setForeground(Color.WHITE);
		lblPrezime.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblPrezime.setBounds(20, 82, 45, 13);
		contentPane.add(lblPrezime);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Muški", "Ženski"}));
		comboBox.setBounds(75, 116, 96, 21);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Pol");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNewLabel.setBounds(20, 120, 45, 13);
		contentPane.add(lblNewLabel);
	}
	
	public void createEvents() {
		btnUpisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				// konekcija sa bazom podataka
			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try { 
					String ime = textIme.getText();
					String prezime = textPrezime.getText();
					String pol = comboBox.getSelectedItem().toString();
					
					if(ime.isEmpty() || prezime.isEmpty()) {
						errorMessage();
					} else {
						Statement stm = connect.createStatement();
					    String sql = "INSERT INTO RADNIK (IME, PREZIME, POL) VALUES ('"+ime+"' , '"+prezime+"' , '"+pol+"')";
					    stm.execute(sql);
					    
					    displayTable();
					    
					    connect.close();
					}
					
				    
	
				}   
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}	
			}
		});
		
		btnMeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				   Meni m = new Meni();
				   m.setVisible(true);
				   Meni meni = (Meni) pomocnaKlasa.CenterWindow(m);
			}
		});
	}
}
