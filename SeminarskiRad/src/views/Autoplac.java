package views;

import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;

import common.HelperClass;

public class Autoplac extends JFrame {

	private JPanel contentPane;
	private JTable tableAP;

	static HelperClass pomocnaKlasa = new HelperClass();
	private JLabel lblNaziv;
	private JLabel lblAdresa;
	private JLabel lblPTT;
	private JTextField textNaziv;
	private JTextField textAdresa;
	private JComboBox comboBox;
	private JButton btnMeni;
	private JButton btnUpisi;
	private JScrollPane scrollPane_1;
	
	public void errorMessage() {
		dispose();
		JOptionPane.showMessageDialog(null, "Popunite sva polja!");
	}
	
	public void displayTable() {

	      Connection connect = pomocnaKlasa.DataBaseConnection();
		try {
			
			String query = "SELECT * FROM autoplac";
			
			tableAP.setModel(new DefaultTableModel());
			
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tableAP.getModel(); 
			
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
					Autoplac frame = new Autoplac();
					frame.setVisible(true);
					Autoplac autoplac = (Autoplac) pomocnaKlasa.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void initComponents() {
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 154, 436, 109);
		contentPane.add(scrollPane_1);
		
		tableAP = new JTable();
		tableAP.setBackground(Color.LIGHT_GRAY);
		scrollPane_1.setViewportView(tableAP);
		tableAP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		
		lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNaziv.setBounds(39, 10, 45, 13);
		contentPane.add(lblNaziv);
		
		lblAdresa = new JLabel("Adresa");
		lblAdresa.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblAdresa.setBounds(39, 43, 45, 13);
		contentPane.add(lblAdresa);
		
		lblPTT = new JLabel("PTT");
		lblPTT.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblPTT.setBounds(39, 78, 45, 13);
		contentPane.add(lblPTT);
		
		textNaziv = new JTextField();
		textNaziv.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		textNaziv.setBounds(152, 10, 96, 19);
		contentPane.add(textNaziv);
		textNaziv.setColumns(10);
		
		textAdresa = new JTextField();
		textAdresa.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		textAdresa.setBounds(152, 39, 96, 19);
		contentPane.add(textAdresa);
		textAdresa.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		comboBox.setBounds(152, 73, 96, 21);
		contentPane.add(comboBox);
		
		btnMeni = new JButton("Meni");
		btnMeni.setBackground(Color.LIGHT_GRAY);
		btnMeni.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnMeni.setBounds(315, 25, 85, 21);
		contentPane.add(btnMeni);
		
		btnUpisi = new JButton("Upi\u0161i");
		btnUpisi.setBackground(Color.LIGHT_GRAY);
		btnUpisi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnUpisi.setBounds(315, 59, 85, 21);
		contentPane.add(btnUpisi);
	}
	public void ptt() {
		String sql = "SELECT * FROM MESTO";

	      Connection connect = pomocnaKlasa.DataBaseConnection();
		try {
			
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				comboBox.addItem(rs.getString("ptt"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Autoplac() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Autoplac.class.getResource("/resources/automobil.png")));
		setTitle("Autoplacevi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(184, 134, 11));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initComponents();
		ptt();
		displayTable();
		createEvents();
	}
	public void createEvents() {

		btnUpisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				// konekcija sa bazom podataka
			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try { 
					String ptt = comboBox.getSelectedItem().toString();
					String naziv = textNaziv.getText();
					String adresa = textAdresa.getText();
					
					if(naziv.isEmpty() || adresa.isEmpty()) {
						errorMessage();
					} else {
						Statement stm = connect.createStatement();
					    String sql = "INSERT INTO AUTOPLAC (PTT, NAZIV, ADRESA) VALUES ('"+Integer.valueOf(ptt)+"' , '"+naziv+"' , '"+adresa+"')";
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

