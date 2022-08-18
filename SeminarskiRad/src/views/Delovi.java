package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import common.HelperClass;
import java.awt.Color;
import common.HelperClass;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Delovi extends JFrame {

	private JPanel contentPane;
	
	static HelperClass pomocnaKlasa = new HelperClass();
	
	private JButton btnMeni, btnUpisi;
	private JTextField textNaziv;
	private JTextField textMaterijal;
	private JTextField textCena;
	private JTable tableD;
	private JComboBox comboIDAutomobila, comboIDRadnika;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delovi frame = new Delovi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void errorMessage() {
		dispose();
		JOptionPane.showMessageDialog(null, "Popunite sva polja!");
	}
	
	public void getIDAutomobila() {
		String sql = "SELECT * FROM AUTOMOBIL";
	      Connection connect = pomocnaKlasa.DataBaseConnection();
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				comboIDAutomobila.addItem(rs.getString("ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getIDRadnika() {
		String sql = "SELECT * FROM RADNIK";
		// konekcija sa bazom podataka
	      Connection connect = pomocnaKlasa.DataBaseConnection();
		try {
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				comboIDRadnika.addItem(rs.getString("ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Delovi() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Delovi.class.getResource("/resources/delovi.png")));
		setTitle("Delovi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(184, 134, 11));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initComponents();
		displayTable();
		getIDAutomobila();
		getIDRadnika();
		createEvents();
	}
	
	public void displayTable() {

	      Connection connect = pomocnaKlasa.DataBaseConnection();
		try {
			
			String query = "SELECT * FROM Delovi";
			
			tableD.setModel(new DefaultTableModel());
			
			Statement st = connect.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) tableD.getModel(); 
			
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
	
	public void initComponents() {
		btnMeni = new JButton("Meni");
		btnMeni.setBackground(Color.LIGHT_GRAY);
		btnMeni.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnMeni.setBounds(341, 54, 85, 21);
		contentPane.add(btnMeni);
		
		btnUpisi = new JButton("Upi\u0161i");
		btnUpisi.setBackground(Color.LIGHT_GRAY);
		btnUpisi.setForeground(new Color(0, 0, 0));
		btnUpisi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnUpisi.setBounds(341, 22, 85, 21);
		contentPane.add(btnUpisi);
		
		JLabel lblIDDela = new JLabel("ID Automobila");
		lblIDDela.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblIDDela.setForeground(Color.WHITE);
		lblIDDela.setBounds(10, 26, 82, 13);
		contentPane.add(lblIDDela);
		
		comboIDAutomobila = new JComboBox();
		comboIDAutomobila.setBounds(89, 22, 85, 21);
		contentPane.add(comboIDAutomobila);
		
		comboIDRadnika = new JComboBox();
		comboIDRadnika.setBounds(89, 53, 85, 21);
		contentPane.add(comboIDRadnika);
		
		JLabel lblIDRadnika = new JLabel("ID Radnika");
		lblIDRadnika.setForeground(Color.WHITE);
		lblIDRadnika.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblIDRadnika.setBounds(23, 57, 69, 13);
		contentPane.add(lblIDRadnika);
		
		textNaziv = new JTextField();
		textNaziv.setBounds(89, 84, 85, 19);
		contentPane.add(textNaziv);
		textNaziv.setColumns(10);
		
		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblNaziv.setForeground(Color.WHITE);
		lblNaziv.setBounds(23, 87, 45, 13);
		contentPane.add(lblNaziv);
		
		textMaterijal = new JTextField();
		textMaterijal.setBounds(246, 22, 85, 19);
		contentPane.add(textMaterijal);
		textMaterijal.setColumns(10);
		
		textCena = new JTextField();
		textCena.setBounds(246, 54, 85, 19);
		contentPane.add(textCena);
		textCena.setColumns(10);
		
		JLabel lblMaterijal = new JLabel("Materijal");
		lblMaterijal.setForeground(Color.WHITE);
		lblMaterijal.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblMaterijal.setBounds(191, 25, 61, 13);
		contentPane.add(lblMaterijal);
		
		JLabel lblCena = new JLabel("Cena");
		lblCena.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblCena.setForeground(Color.WHITE);
		lblCena.setBounds(191, 57, 45, 13);
		contentPane.add(lblCena);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 113, 436, 148);
		contentPane.add(scrollPane);
		
		tableD = new JTable();
		tableD.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(tableD);
	}
	
	public void createEvents() {
		btnUpisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	

			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try { 
					String IDAutomobila = comboIDAutomobila.getSelectedItem().toString();
					String IDRadnika = comboIDRadnika.getSelectedItem().toString();
					String Naziv = textNaziv.getText();
					String Materijal = textMaterijal.getText();
					String Cena = textCena.getText();
					
					if(IDAutomobila.isEmpty() || IDRadnika.isEmpty() || Naziv.isEmpty() || Materijal.isEmpty() || Cena.isEmpty()) {
						errorMessage();
					} else {
						Statement stm = connect.createStatement();
					    String sql = "INSERT INTO DELOVI (ID_A, ID_R, NAZIV, MATERIJAL, CENA) VALUES ('"+Integer.valueOf(IDAutomobila)+"' , '"+Integer.valueOf(IDRadnika)+"' , '"+Naziv+"' , '"+Materijal+"' , '"+Cena+"')";
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
