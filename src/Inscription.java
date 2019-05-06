import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorChooserUI;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Inscription extends JFrame {

	private JPanel contentPane;
	private JTextField txtPseudoInscription;
	private JTextField txtMail;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JPasswordField passwordFieldInscription;
	private JLabel lblMessageInscription;
	private JTextField txtTel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inscription frame = new Inscription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public String Inscription(String pseudo, String mdp, String nom, String prenom, String email, String telephone) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
		final String user = "root";
		final String password ="";
		String query = "insert into membres(pseudo, mdp, nom, prenom, email, telephone) VALUES ('" + pseudo + "', '" + mdp + "', '" + nom + "', '" + prenom + "', '" + email + "', '" + telephone +"')";
		String message = "";
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			System.out.println(query);
			st.executeUpdate(query);
			return message = "Opération réussie. Vous pouvez maintenant vous connecter.";
		}
		catch (Exception e){
			return message = "Une erreur est intervenue. Veuillez réessayer.";
		}
	}

	/**
	 * Create the frame.
	 */
	public Inscription() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1550,1000);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(326, 323, 914, 526);
		panel.setBackground(SystemColor.activeCaption);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(566, 116, 69, 30);
		panel.add(lblNom);
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNom.setBounds(690, 113, 178, 43);
		panel.add(txtNom);
		txtNom.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtMail.setBounds(203, 347, 178, 43);
		panel.add(txtMail);
		txtMail.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(39, 349, 94, 32);
		panel.add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblPseudoInscription = new JLabel("Pseudo : ");
		lblPseudoInscription.setBounds(39, 234, 106, 31);
		panel.add(lblPseudoInscription);
		lblPseudoInscription.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		txtPseudoInscription = new JTextField();
		txtPseudoInscription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPseudoInscription.setBounds(203, 231, 178, 43);
		panel.add(txtPseudoInscription);
		txtPseudoInscription.setColumns(10);
		
		JLabel lblSinscrire = new JLabel("Cr\u00E9er un compte");
		lblSinscrire.setBounds(281, 13, 354, 43);
		panel.add(lblSinscrire);
		lblSinscrire.setFont(new Font("Tahoma", Font.BOLD, 40));
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setBounds(39, 110, 91, 43);
		panel.add(lblPrnom);
		lblPrnom.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblMotDePasse_1 = new JLabel("Mot de passe : ");
		lblMotDePasse_1.setBounds(480, 236, 170, 26);
		panel.add(lblMotDePasse_1);
		lblMotDePasse_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
		passwordFieldInscription = new JPasswordField();
		passwordFieldInscription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordFieldInscription.setBounds(690, 232, 178, 40);
		panel.add(passwordFieldInscription);
		
		txtPrenom = new JTextField();
		txtPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPrenom.setBounds(203, 113, 178, 43);
		panel.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.window);
		panel_1.setBounds(0, 0, 1532, 141);
		contentPane.add(panel_1);
		
		JLabel lblImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/bandeau.png")).getImage();
		panel_1.add(lblImg);
		lblImg.setIcon(new ImageIcon(img));
		
		lblMessageInscription = new JLabel("");
		lblMessageInscription.setBounds(372, 818, 854, 31);
		contentPane.add(lblMessageInscription);
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone :");
		lblTlphone.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTlphone.setBounds(505, 352, 167, 32);
		panel.add(lblTlphone);
		
		txtTel = new JTextField();
		txtTel.setBounds(690, 350, 178, 43);
		panel.add(txtTel);
		txtTel.setColumns(10);
		
		JButton btnEnvoyerInscription = new JButton("Envoyer");
		btnEnvoyerInscription.setBounds(395, 451, 123, 43);
		btnEnvoyerInscription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(btnEnvoyerInscription);
		
		JLabel label = new JLabel("Gestion des \u00E9v\u00E9nements");
		label.setForeground(new Color(255, 69, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 70));
		label.setBounds(345, 186, 927, 76);
		contentPane.add(label);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connexion connexion = new Connexion();
				connexion.setVisible(true);
				dispose();
			}
		});
		btnRetour.setForeground(new Color(105, 105, 105));
		btnRetour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRetour.setBounds(1331, 897, 123, 43);
		contentPane.add(btnRetour);
		
		btnEnvoyerInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String lePseudo = txtPseudoInscription.getText();
				String leMail = txtMail.getText();
				String leNom = txtNom.getText();
				String lePrenom = txtPrenom.getText();
				String leMdp = passwordFieldInscription.getText();
				String leTel = txtTel.getText();
				
				String message = Inscription(lePseudo, leMdp, leNom, lePrenom,leMail, leTel);
				lblMessageInscription.setText(message);
				Affichage affichage = new Affichage("1", lePseudo);
				affichage.setVisible(true);
				dispose();
			}
		});
	}
}
