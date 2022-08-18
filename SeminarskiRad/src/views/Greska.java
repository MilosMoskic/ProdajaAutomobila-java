package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import common.HelperClass;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

public class Greska extends JDialog {

	static HelperClass pomocnaKlasa = new HelperClass();
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Greska dialog = new Greska();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			Greska login = (Greska) pomocnaKlasa.CenterWindow(dialog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Greska() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Greska.class.getResource("/resources/warning.png")));
		setTitle("Greška");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(184, 134, 11));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPogresno = new JLabel("Username ili password je pogresan!");
			lblPogresno.setForeground(Color.WHITE);
			lblPogresno.setFont(new Font("Bahnschrift", Font.PLAIN, 16));
			lblPogresno.setBounds(76, 55, 269, 33);
			contentPanel.add(lblPogresno);
		}
		{
			JLabel lblNewLabel = new JLabel("Poku\u0161ajte ponovo ili iza\u0111ite iz aplikacije");
			lblNewLabel.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(97, 152, 234, 13);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton TryAgainButton = new JButton("Poku\u0161aj ponovo");
				TryAgainButton.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				TryAgainButton.setActionCommand("OK");
				buttonPane.add(TryAgainButton);
				getRootPane().setDefaultButton(TryAgainButton);
				TryAgainButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
				    	Login hPage = new Login();
				    	hPage.setVisible(true);
				    	Login login = (Login) pomocnaKlasa.CenterWindow(hPage);
					}
				});
				TryAgainButton.setActionCommand("OK");
				buttonPane.add(TryAgainButton);
				getRootPane().setDefaultButton(TryAgainButton);
			}
			{
				JButton ExitButton = new JButton("Iza\u0111i");
				ExitButton.setFont(new Font("Bahnschrift", Font.PLAIN, 10));
				ExitButton.setActionCommand("Cancel");
				buttonPane.add(ExitButton);
				ExitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				ExitButton.setActionCommand("Cancel");
				buttonPane.add(ExitButton);
			}
		}
	}
}