package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import common.HelperClass;
import javax.swing.SwingConstants;

public class Meni extends JFrame {

	private JPanel contentPane;
	private JButton btnAutoPlac;
	static HelperClass pomocnaKlasa = new HelperClass();
	private JButton btnRadnik;
	private JButton btnAutomobili;
	private JButton btnDelovi;
	private JButton btnBaza;
	private JButton btnIzlogujse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Meni frame = new Meni();
					frame.setVisible(true);
					Meni meni = (Meni) pomocnaKlasa.CenterWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initComponents() {
		
		btnAutoPlac = new JButton("AutoPlac");
		btnAutoPlac.setBackground(Color.LIGHT_GRAY);
		btnAutoPlac.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnAutoPlac.setBounds(10, 45, 85, 21);
		contentPane.add(btnAutoPlac);
		
		btnRadnik = new JButton("Radnik");
		btnRadnik.setBackground(Color.LIGHT_GRAY);
		btnRadnik.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnRadnik.setBounds(10, 107, 85, 21);
		contentPane.add(btnRadnik);
		
		btnAutomobili = new JButton("Automobili");
		btnAutomobili.setBackground(Color.LIGHT_GRAY);
		btnAutomobili.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnAutomobili.setBounds(10, 164, 85, 21);
		contentPane.add(btnAutomobili);
		
		btnDelovi = new JButton("Delovi");
		btnDelovi.setBackground(Color.LIGHT_GRAY);
		btnDelovi.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnDelovi.setBounds(105, 45, 85, 21);
		contentPane.add(btnDelovi);
		
		btnBaza = new JButton("Podaci");
		btnBaza.setBackground(Color.LIGHT_GRAY);
		btnBaza.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnBaza.setBounds(105, 107, 85, 21);
		contentPane.add(btnBaza);
		
		btnIzlogujse = new JButton("Izloguj se");
		btnIzlogujse.setBackground(Color.LIGHT_GRAY);
		btnIzlogujse.setFont(new Font("Bahnschrift", Font.PLAIN, 9));
		btnIzlogujse.setBounds(341, 232, 85, 21);
		contentPane.add(btnIzlogujse);
		
		JLabel lblNewLabel = new JLabel("New label");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/resources/Auto1.jpg"));
		lblNewLabel.setIcon(img);
		lblNewLabel.setBounds(218, 58, 208, 153);
		contentPane.add(lblNewLabel);
		
	}
	
	/**
	 * Create the frame.
	 */
	public Meni() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Meni.class.getResource("/resources/meni.png")));
		setTitle("Meni");
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
		btnAutoPlac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		    	Autoplac ap = new Autoplac();
		    	ap.setVisible(true);
		    	Autoplac autoplac = (Autoplac) pomocnaKlasa.CenterWindow(ap);
				}
			});
		btnRadnik.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				   Radnik r = new Radnik();
				   r.setVisible(true);
				   Radnik radnik = (Radnik) pomocnaKlasa.CenterWindow(r);
			}
		});
		
		btnAutomobili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				   Automobil au = new Automobil();
				   au.setVisible(true);
				   Automobil automobil = (Automobil) pomocnaKlasa.CenterWindow(au);
			}
		});
		
		btnDelovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				   Delovi d = new Delovi();
				   d.setVisible(true);
				   Delovi delovi = (Delovi) pomocnaKlasa.CenterWindow(d);
			}
		});
		
		btnBaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				   Baza b = new Baza();
				   b.setVisible(true);
				   Baza baza = (Baza) pomocnaKlasa.CenterWindow(b);
			}
		});
		
		btnIzlogujse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login frame = new Login();
				frame.setVisible(true);
				Login login = (Login) pomocnaKlasa.CenterWindow(frame);
			}
		});
	}
}