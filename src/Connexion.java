import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Color;

public class Connexion extends JFrame {

	private JPanel contentPane;
	private JTextField txtPseudo;
	private JPasswordField passwordField;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion frame = new Connexion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String Verif(String pseudo, String mdp)
	{
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		String query = "select nom, rang from membres where pseudo = '"+pseudo+"' and mdp = '"+mdp+"' ";
		ResultSet res;
		String rang;
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			res = st.executeQuery(query);
			res.next();
			rang = res.getString(2);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage()+"\n"+query);
			rang = "Problème intervenu.";
		}
		return rang;
	}

	/**
	 * Create the frame.
	 */
	public Connexion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1550,1000);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEvnements = new JLabel("Gestion des \u00E9v\u00E9nements");
		lblEvnements.setForeground(new Color(255, 69, 0));
		lblEvnements.setFont(new Font("Tahoma", Font.BOLD, 70));
		lblEvnements.setBounds(317, 197, 927, 76);
		contentPane.add(lblEvnements);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setBounds(0, 0, 1532, 141);
		contentPane.add(panel);
		
		JLabel lblImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/bandeau.png")).getImage();
		lblImg.setIcon(new ImageIcon(img));
		panel.add(lblImg);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(407, 319, 710, 508);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPseudo = new JLabel("Pseudo :");
		lblPseudo.setBounds(195, 172, 86, 25);
		panel_1.add(lblPseudo);
		lblPseudo.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblSeConnecter = new JLabel("Se connecter \u00E0 votre compte :");
		lblSeConnecter.setBounds(64, 33, 605, 49);
		panel_1.add(lblSeConnecter);
		lblSeConnecter.setFont(new Font("Tahoma", Font.BOLD, 40));
		
		txtPseudo = new JTextField();
		txtPseudo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPseudo.setBounds(344, 169, 143, 36);
		panel_1.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(344, 257, 143, 33);
		panel_1.add(passwordField);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(136, 265, 165, 25);
		panel_1.add(lblMotDePasse);
		lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JButton btnEnvoyerConnexion = new JButton("Envoyer");
		btnEnvoyerConnexion.setBounds(286, 392, 143, 52);
		panel_1.add(btnEnvoyerConnexion);
		btnEnvoyerConnexion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMessage.setForeground(new Color(255, 0, 0));
		lblMessage.setBounds(12, 457, 686, 38);
		panel_1.add(lblMessage);
		
		JLabel lblNewLabel = new JLabel("Pas encore de compte ? Cr\u00E9ez-en un ici :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(450, 855, 383, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnInscription = new JButton("Cr\u00E9er un compte");
		btnInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inscription inscription = new Inscription();
				inscription.setVisible(true);
				dispose();
			}
		});
		btnInscription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnInscription.setBounds(871, 852, 201, 37);
		contentPane.add(btnInscription);
		
		btnEnvoyerConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String lePseudo = txtPseudo.getText();
				String leMdp = passwordField.getText();
				String rang = Verif(lePseudo, leMdp);
				if(rang.equals("1")) {
					lblMessage.setText("Connexion réussie.");
					dispose();
					Affichage affichage = new Affichage(rang, lePseudo);
				}
				else if(rang.equals("2")) {
					lblMessage.setText("Connexion admin réussie.");
					dispose();
					Affichage affichage = new Affichage(rang, lePseudo);
				}
				else if (rang.equals("Problème intervenu.")){
					lblMessage.setText("Erreur intervenue, merci de réessayer.");
				}
				else {
					lblMessage.setText("Utilisateur inconnu.");
				}
			}
		});
	}
}
