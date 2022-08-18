package views;

import java.awt.BorderLayout;

import common.HelperClass;

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
import javax.swing.JComboBox;

public class Automobil extends JFrame {

	private JPanel contentPane;
	
	private JButton btnMeni, btnUpisi;
	
	static HelperClass pomocnaKlasa = new HelperClass();
	private JTextField textNaziv;
	private JTextField textMarka;
	private JTextField textGodiste;
	private JTextField textCena;
	private JLabel lblNaziv;
	private JLabel lblMarka;
	private JLabel lblGodiste;
	private JLabel lblCena;
	private JComboBox comboBox;
	private JTable tableA;
	private JScrollPane scrollPane;
	
	public void errorMessage() {
		dispose();
		JOptionPane.showMessageDialog(null, "Popunite sva polja!");
	}
	
	public void displayTable() {

	      Connection connect = pomocnaKlasa.DataBaseConnection();
		try {
			String query = "SELECT * FROM AUTOMOBIL";
			
			tableA.setModel(new DefaultTableModel());
			
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tableA.getModel(); 
			
			int columns = rsmd.getColumnCount();
			String[] colName = new String[columns];
			
			for(int i = 0; i<columns; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			
			while(rs.next()) {
				model.addRow(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
				
			}
			connect.close();
			
			
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}
	
	public void getIDAutoplaca() {
		String sql = "SELECT * FROM AUTOPLAC";
		

	      Connection connect = pomocnaKlasa.DataBaseConnection();
		try {
	
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				comboBox.addItem(rs.getString("ID"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Automobil frame = new Automobil();
					frame.setVisible(true);
					Automobil automobil = (Automobil) pomocnaKlasa.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Automobil() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Automobil.class.getResource("/resources/automobil.png")));
		setTitle("Automobili");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(184, 134, 11));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initComponents();
		displayTable();
		getIDAutoplaca();
		createEvents();
	}
	
	
	public void initComponents() {
		btnMeni = new JButton("Meni");
		btnMeni.setBackground(Color.LIGHT_GRAY);
		btnMeni.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnMeni.setBounds(34, 232, 85, 21);
		contentPane.add(btnMeni);
		
		btnUpisi = new JButton("Upi\u0161i");
		btnUpisi.setBackground(Color.LIGHT_GRAY);
		btnUpisi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnUpisi.setBounds(34, 201, 85, 21);
		contentPane.add(btnUpisi);
		
		textNaziv = new JTextField();
		textNaziv.setBounds(67, 20, 96, 19);
		contentPane.add(textNaziv);
		textNaziv.setColumns(10);
		
		textMarka = new JTextField();
		textMarka.setBounds(67, 49, 96, 19);
		contentPane.add(textMarka);
		textMarka.setColumns(10);
		
		textGodiste = new JTextField();
		textGodiste.setBounds(67, 78, 96, 19);
		contentPane.add(textGodiste);
		textGodiste.setColumns(10);
		
		textCena = new JTextField();
		textCena.setBounds(67, 107, 96, 19);
		contentPane.add(textCena);
		textCena.setColumns(10);
		
		lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNaziv.setForeground(Color.WHITE);
		lblNaziv.setBounds(10, 24, 45, 13);
		contentPane.add(lblNaziv);
		
		lblMarka = new JLabel("Marka");
		lblMarka.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblMarka.setForeground(Color.WHITE);
		lblMarka.setBounds(10, 53, 45, 13);
		contentPane.add(lblMarka);
		
		lblGodiste = new JLabel("Godi\u0161te");
		lblGodiste.setForeground(Color.WHITE);
		lblGodiste.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblGodiste.setBounds(10, 82, 45, 13);
		contentPane.add(lblGodiste);
		
		lblCena = new JLabel("Cena");
		lblCena.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblCena.setForeground(Color.WHITE);
		lblCena.setBounds(10, 111, 45, 13);
		contentPane.add(lblCena);
		
		comboBox = new JComboBox();
		comboBox.setBounds(67, 136, 96, 21);
		contentPane.add(comboBox);
		
		JLabel lblIDAutoPlaca = new JLabel("ID Autoplaca");
		lblIDAutoPlaca.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
		lblIDAutoPlaca.setForeground(Color.WHITE);
		lblIDAutoPlaca.setBounds(10, 142, 71, 13);
		contentPane.add(lblIDAutoPlaca);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(173, 0, 263, 263);
		contentPane.add(scrollPane);
		
		tableA = new JTable();
		tableA.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(tableA);
	}
	
	public void createEvents() {
		btnUpisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	

			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try { 
					String Naziv = textNaziv.getText();
					String Marka = textMarka.getText();
					String Godiste = textGodiste.getText();
					String Cena = textCena.getText();
					String IDAp = comboBox.getSelectedItem().toString();
					
					if(Naziv.isEmpty() || Marka.isEmpty() || Godiste.isEmpty() || Cena.isEmpty()) {
						errorMessage();
					} else {
						Statement stm = connect.createStatement();
					    String sql = "INSERT INTO AUTOMOBIL (NAZIV, MARKA, GODISTE, CENA, ID_AUTO) VALUES ('"+Naziv+"' , '"+Marka+"', '"+Godiste+"' , '"+Cena+"' , '"+IDAp+"')";
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
