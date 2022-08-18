package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import common.HelperClass;

public class Registracija extends JFrame {

	private JPanel contentPane;
	static HelperClass pomocnaKlasa = new HelperClass();
	private JTextField textKorisnickoIme;
	private JTextField textIme;
	private JTextField textPrezime;
	private JTextField textSifra;
	private JComboBox comboBoxPTT, comboBoxStruka;
	private JButton btnLogin, btnRegister;
	
	public void initComponents() {	
		JLabel lblRegistracija = new JLabel("Registracija");
		lblRegistracija.setForeground(Color.WHITE);
		lblRegistracija.setFont(new Font("Bahnschrift", Font.PLAIN, 19));
		lblRegistracija.setBounds(162, 10, 102, 24);
		contentPane.add(lblRegistracija);
		
		JLabel lblIme = new JLabel("Ime");
		lblIme.setForeground(Color.WHITE);
		lblIme.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblIme.setBounds(86, 68, 45, 13);
		contentPane.add(lblIme);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setForeground(Color.WHITE);
		lblPrezime.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblPrezime.setBounds(86, 91, 45, 13);
		contentPane.add(lblPrezime);
		
		JLabel lblKorisnickoIme = new JLabel("Korisni\u010Dko Ime");
		lblKorisnickoIme.setForeground(Color.WHITE);
		lblKorisnickoIme.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblKorisnickoIme.setBounds(86, 45, 89, 13);
		contentPane.add(lblKorisnickoIme);
		
		JLabel lblSifra = new JLabel("\u0160ifra");
		lblSifra.setForeground(Color.WHITE);
		lblSifra.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblSifra.setBounds(86, 110, 45, 13);
		contentPane.add(lblSifra);
		
		JLabel lblPTT = new JLabel("PTT");
		lblPTT.setForeground(Color.WHITE);
		lblPTT.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblPTT.setBounds(86, 133, 45, 13);
		contentPane.add(lblPTT);
		
		JLabel lblStruka = new JLabel("Struka");
		lblStruka.setForeground(Color.WHITE);
		lblStruka.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		lblStruka.setBounds(86, 156, 45, 13);
		contentPane.add(lblStruka);
		
		textKorisnickoIme = new JTextField();
		textKorisnickoIme.setBackground(Color.WHITE);
		textKorisnickoIme.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		textKorisnickoIme.setBounds(213, 44, 96, 19);
		contentPane.add(textKorisnickoIme);
		textKorisnickoIme.setColumns(10);
		
		textIme = new JTextField();
		textIme.setBackground(Color.WHITE);
		textIme.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		textIme.setBounds(213, 64, 96, 19);
		contentPane.add(textIme);
		textIme.setColumns(10);
		
		textPrezime = new JTextField();
		textPrezime.setBackground(Color.WHITE);
		textPrezime.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		textPrezime.setBounds(213, 88, 96, 19);
		contentPane.add(textPrezime);
		textPrezime.setColumns(10);
		
		textSifra = new JTextField();
		textSifra.setBackground(Color.WHITE);
		textSifra.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		textSifra.setBounds(213, 107, 96, 19);
		contentPane.add(textSifra);
		textSifra.setColumns(10);
		
		comboBoxPTT = new JComboBox();
		comboBoxPTT.setBackground(Color.LIGHT_GRAY);
		comboBoxPTT.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		comboBoxPTT.setBounds(213, 128, 96, 21);
		contentPane.add(comboBoxPTT);
		
		comboBoxStruka = new JComboBox();
		comboBoxStruka.setBackground(Color.LIGHT_GRAY);
		comboBoxStruka.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		comboBoxStruka.setModel(new DefaultComboBoxModel(new String[] {"Autolimar", "Mehanièar", "Laker"}));
		comboBoxStruka.setBounds(213, 156, 96, 21);
		contentPane.add(comboBoxStruka);
		
		btnRegister = new JButton("Registruj se");
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnRegister.setBounds(90, 204, 85, 21);
		contentPane.add(btnRegister);
		
		btnLogin = new JButton("Loguj se");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnLogin.setBounds(246, 202, 85, 21);
		contentPane.add(btnLogin);
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registracija frame = new Registracija();
					frame.setVisible(true);
					Registracija reg = (Registracija) pomocnaKlasa.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registracija() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Registracija.class.getResource("/resources/login.png")));
		setTitle("Registracija");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(184, 134, 11));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initComponents();
		ptt();
		createEvents();
	}
	
	public void ptt() {
		String sql = "SELECT * FROM MESTO";
		try {
			Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/prodaja_automobila","root","mikimaus147");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				comboBoxPTT.addItem(rs.getString("ptt"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	
	public void createEvents() {
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ime = textIme.getText();
				String prezime = textPrezime.getText();
	     		String username = textKorisnickoIme.getText();
				String password = textSifra.getText();
				String ptt = comboBoxPTT.getSelectedItem().toString();
				String struka = comboBoxStruka.getSelectedItem().toString();

			      Connection connect = pomocnaKlasa.DataBaseConnection();
				try { 
					Statement stm = connect.createStatement();
					String sql = "INSERT INTO ADMINISTRATOR (IME, PREZIME, USERNAME, PASSWORD, PTT, STRUKA) VALUES ('"+ime+"', '"+prezime+"', '"+username+"', '"+password+"', '"+Integer.valueOf(ptt)+"', '"+struka+"');";
				    stm.execute(sql);
				    
				    dispose();
				    Login LoginPage = new Login();
				    LoginPage.setVisible(true);
				    connect.close();
				    Login login = (Login) pomocnaKlasa.CenterWindow(LoginPage);
				    
	
				}   
				catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		    	Login LoginPage = new Login();
		    	LoginPage.setVisible(true);
		    	Login login = (Login) pomocnaKlasa.CenterWindow(LoginPage);
			}
		});	
	}
	
}
	
