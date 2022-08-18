package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import common.HelperClass;
import java.awt.SystemColor;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtKorisnickoIme;
	private JPasswordField txtLozinka;
	private JButton btnLogin, btnRegister;
	static HelperClass pomocnaKlasa = new HelperClass();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					Login login = (Login) pomocnaKlasa.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public void initComponents() {
		JLabel lblUlogujteSe = new JLabel("Dobrodo\u0161li, ulogujte se!");
		lblUlogujteSe.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
		lblUlogujteSe.setHorizontalAlignment(SwingConstants.LEFT);
		lblUlogujteSe.setBounds(118, 10, 185, 20);
		contentPane.add(lblUlogujteSe);
		
		JLabel Username = new JLabel("Korisni\u010Dko ime:");
		Username.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		Username.setBounds(88, 56, 91, 13);
		contentPane.add(Username);
		
		JLabel Password = new JLabel("\u0160ifra:");
		Password.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		Password.setBounds(88, 90, 51, 13);
		contentPane.add(Password);
		
		txtKorisnickoIme = new JTextField();
		txtKorisnickoIme.setBackground(Color.WHITE);
		txtKorisnickoIme.setBounds(207, 52, 96, 19);
		contentPane.add(txtKorisnickoIme);
		txtKorisnickoIme.setColumns(10);
		
		btnLogin = new JButton("Uloguj se");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnLogin.setBounds(158, 145, 85, 21);
		contentPane.add(btnLogin);
		
		btnRegister = new JButton("Registruj se");
		btnRegister.setBackground(Color.LIGHT_GRAY);
		btnRegister.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnRegister.setBounds(158, 232, 85, 21);
		contentPane.add(btnRegister);
		
		JLabel tekstRegistracija = new JLabel("Ukoliko nemate profil, registrujte se!");
		tekstRegistracija.setFont(new Font("Bahnschrift", Font.PLAIN, 12));
		tekstRegistracija.setHorizontalAlignment(SwingConstants.LEFT);
		tekstRegistracija.setBounds(108, 210, 195, 13);
		contentPane.add(tekstRegistracija);
		
		txtLozinka = new JPasswordField();
		txtLozinka.setBackground(Color.WHITE);
		txtLozinka.setBounds(207, 86, 96, 19);
		contentPane.add(txtLozinka);
	}
	
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/resources/login.png")));
		setTitle("Log In");
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


	public void createEvents() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtKorisnickoIme.getText();
				char[] password = txtLozinka.getPassword();
				String pw = "";
				for(int i = 0; i < password.length; i++ ) {
					pw+=password[i];
				}

				Connection connect = pomocnaKlasa.DataBaseConnection();
				try{			      
					Statement stm = connect.createStatement();
			      	String sql = "select * from ADMINISTRATOR where USERNAME = '"+username+"' and PASSWORD = '"+pw+"'";
			      	ResultSet rs = stm.executeQuery(sql);
			      

			      	if(rs.next()) {
			      		dispose();
			      		Meni m = new Meni();
			      		m.setVisible(true);
			      		Meni meni = (Meni) pomocnaKlasa.CenterWindow(m);
			      	}
			      	else {
			      		dispose();
			      		Greska Greska = new Greska();
			      		Greska.setVisible(true);
			      		Greska login = (Greska) pomocnaKlasa.CenterWindow(Greska);
			      		txtKorisnickoIme.setText("");
			    	 	txtLozinka.setText("");
			      	}
			      	
			      	connect.close();
			    	}
					catch(Exception eis){ System.out.println(e);}
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Registracija RegPage = new Registracija();
				RegPage.setVisible(true);
				Registracija  reg = (Registracija) pomocnaKlasa.CenterWindow(RegPage);
			}
		});
	}
}
