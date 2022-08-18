package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;

import common.HelperClass;

public class Baza extends JFrame {

	private String tabela;
	private JPanel contentPane;
	private JButton btnObrisi, btnAutoplacevi, btnAdministratori, btnDelovi, btnRadnici, btnAutomobili;
	private JTable tabelaB;
	private int selectedID;

	static HelperClass pomocnaKlasa = new HelperClass();
	
	private JButton btnMeni;
	private JTable tableB;
	private JButton btnOsvezi;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Baza frame = new Baza();
					frame.setVisible(true);
					Baza bp = (Baza) pomocnaKlasa.CenterWindow(frame);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Baza() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Baza.class.getResource("/resources/bazap.png")));
		setTitle("Podaci");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(184, 134, 11));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initComponents();
		createEvents();
	}

	public void initComponents() {
		btnMeni = new JButton("Meni");
		btnMeni.setBackground(Color.LIGHT_GRAY);
		btnMeni.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnMeni.setBounds(341, 11, 85, 21);
		contentPane.add(btnMeni);
		
		btnAutoplacevi = new JButton("Autoplacevi");
		btnAutoplacevi.setBackground(Color.LIGHT_GRAY);
		btnAutoplacevi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnAutoplacevi.setBounds(10, 10, 85, 21);
		contentPane.add(btnAutoplacevi);
		
		btnRadnici = new JButton("Radnici");
		btnRadnici.setBackground(Color.LIGHT_GRAY);
		btnRadnici.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnRadnici.setBounds(10, 41, 85, 21);
		contentPane.add(btnRadnici);
		
		btnAutomobili = new JButton("Automobili");
		btnAutomobili.setBackground(Color.LIGHT_GRAY);
		btnAutomobili.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnAutomobili.setBounds(105, 10, 85, 21);
		contentPane.add(btnAutomobili);
		
		btnDelovi = new JButton("Delovi");
		btnDelovi.setBackground(Color.LIGHT_GRAY);
		btnDelovi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnDelovi.setBounds(105, 41, 85, 21);
		contentPane.add(btnDelovi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 126, 436, 137);
		contentPane.add(scrollPane);
		
		btnObrisi = new JButton("Obri\u0161i");
		btnObrisi.setBackground(Color.LIGHT_GRAY);
		btnObrisi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnObrisi.setBounds(341, 42, 85, 21);
		contentPane.add(btnObrisi);
		
		tableB = new JTable();
		tableB.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(tableB);
		
		btnAdministratori = new JButton("Administratori");
		btnAdministratori.setBackground(Color.LIGHT_GRAY);
		btnAdministratori.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnAdministratori.setBounds(10, 69, 85, 21);
		contentPane.add(btnAdministratori);
		
		btnOsvezi = new JButton("Osve\u017Ei");
		btnOsvezi.setBackground(Color.LIGHT_GRAY);
		btnOsvezi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnOsvezi.setBounds(341, 67, 85, 21);
		contentPane.add(btnOsvezi);
		
		ListSelectionModel rowSelectionModel = tableB.getSelectionModel();

		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {

		ListSelectionModel lsm = (ListSelectionModel)e.getSource();

		if(lsm.isSelectionEmpty()) {
		}
		else {
		int selRow = tableB.getSelectedRow();
		selectedID = Integer.parseInt(tableB.getModel().getValueAt(selRow, 0).toString());
			}
		}
		});
	}
	
	public void createEvents() {
		btnMeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				   Meni m = new Meni();
				   m.setVisible(true);
				   Meni meni = (Meni) pomocnaKlasa.CenterWindow(m);
			}
		});
		
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/prodaja_automobila","root","mikimaus147");
					String query = "DELETE FROM "+ tabela +" WHERE id = "+ selectedID +"";
					Statement st = connect.createStatement();
					st.execute(query);
					connect.close();
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
			}
		});
		
		btnAutoplacevi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// konekcija sa bazom podataka
			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try {
					String query = "SELECT *  FROM AUTOPLAC";
								
					//Refreshovanje tabele
					tableB.setModel(new DefaultTableModel());
					
					tabela = "AUTOPLAC";
					
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tableB.getModel(); 
					
					int columns = rsmd.getColumnCount();
					String[] colName = new String[columns];
					
					for(int i = 0; i<columns; i++) {
						colName[i] = rsmd.getColumnName(i+1);
					}
					model.setColumnIdentifiers(colName);																		
					while(rs.next()) {
						model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});							
					}
					connect.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAdministratori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// konekcija sa bazom podataka
			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try {
					String query = "SELECT *  FROM ADMINISTRATOR";
								
					//Refreshovanje tabele
					tableB.setModel(new DefaultTableModel());
					
					tabela = "ADMINISTRATOR";
					
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tableB.getModel(); 
					
					int columns = rsmd.getColumnCount();
					String[] colName = new String[columns];
					
					for(int i = 0; i<columns; i++) {
						colName[i] = rsmd.getColumnName(i+1);
					}
					model.setColumnIdentifiers(colName);																		
					while(rs.next()) {
						model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7)});							
					}
					connect.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnDelovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// konekcija sa bazom podataka
			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try {
					String query = "SELECT *  FROM DELOVI";
						
					//Refreshovanje tabele
					tableB.setModel(new DefaultTableModel());
					
					tabela = "DELOVI";
					
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tableB.getModel(); 
					
					int columns = rsmd.getColumnCount();
					String[] colName = new String[columns];
					
					for(int i = 0; i<columns; i++) {
						colName[i] = rsmd.getColumnName(i+1);
					}
					model.setColumnIdentifiers(colName);																		
					while(rs.next()) {
						model.addRow(new String[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});							
					}
					connect.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnRadnici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// konekcija sa bazom podataka
			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try {
					
					String query = "SELECT *  FROM RADNIK";
								
					//Refreshovanje tabele
					tableB.setModel(new DefaultTableModel());
					
					tabela = "RADNIK";
					
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tableB.getModel(); 
					
					int columns = rsmd.getColumnCount();
					String[] colName = new String[columns];
					
					for(int i = 0; i<columns; i++) {
						colName[i] = rsmd.getColumnName(i+1);
					}
					model.setColumnIdentifiers(colName);																		
					while(rs.next()) {
						model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4)});							
					}
					connect.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnAutomobili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// konekcija sa bazom podataka
			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try {
					
					String query = "SELECT * FROM AUTOMOBIL";
								
					//Refreshovanje tabele
					tableB.setModel(new DefaultTableModel());
					
					tabela = "AUTOMOBIL";
					
					Statement st = connect.createStatement();
					ResultSet rs = st.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tableB.getModel(); 
					
					int columns = rsmd.getColumnCount();
					String[] colName = new String[columns];
					
					for(int i = 0; i<columns; i++) {
						colName[i] = rsmd.getColumnName(i+1);
					}
					model.setColumnIdentifiers(colName);																		
					while(rs.next()) {
						model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});							
					}
					connect.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOsvezi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				   Baza b = new Baza();
				   b.setVisible(true);
				   Baza baza = (Baza) pomocnaKlasa.CenterWindow(b);
			}
		});
	}
}
