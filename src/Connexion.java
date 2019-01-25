import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JPasswordField;

public class Connexion extends JFrame {

	private JPanel contentPane;
	private JTextField txtPseudo;
	private JTextField txtPseudoInscription;
	private JTextField txtMail;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JPasswordField passwordField;
	private JLabel lblMessage;
	private JPasswordField passwordFieldInscription;
	private JLabel lblMessageInscription;

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
	
	public String Inscription(String pseudo, String email, String nom, String prenom, String mdp) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
		final String user = "root";
		final String password ="";
		String query = "insert into membres(Pseudo, Email, Nom, Prenom, Mdp) VALUES ('" + pseudo + "', '" + email + "', '" + nom + "', '" + prenom + "', '" + mdp + "')";
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
	
	public String Verif(String pseudo, String mdp)
	{
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
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
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEvnements = new JLabel("Ev\u00E9nements");
		lblEvnements.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEvnements.setBounds(356, 13, 164, 30);
		contentPane.add(lblEvnements);
		
		JLabel lblSeConnecter = new JLabel("Se connecter :");
		lblSeConnecter.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSeConnecter.setBounds(98, 103, 129, 16);
		contentPane.add(lblSeConnecter);
		
		JLabel lblPseudo = new JLabel("Pseudo :");
		lblPseudo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPseudo.setBounds(49, 150, 56, 16);
		contentPane.add(lblPseudo);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(159, 147, 116, 22);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMotDePasse.setBounds(49, 235, 93, 16);
		contentPane.add(lblMotDePasse);
		
		JButton btnEnvoyerConnexion = new JButton("Envoyer");
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
		btnEnvoyerConnexion.setBounds(98, 308, 97, 25);
		contentPane.add(btnEnvoyerConnexion);
		
		JLabel lblSinscrire = new JLabel("S'inscrire :");
		lblSinscrire.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSinscrire.setBounds(532, 103, 93, 16);
		contentPane.add(lblSinscrire);
		
		JLabel lblPseudoInscription = new JLabel("Pseudo : ");
		lblPseudoInscription.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPseudoInscription.setBounds(464, 150, 56, 16);
		contentPane.add(lblPseudoInscription);
		
		txtPseudoInscription = new JTextField();
		txtPseudoInscription.setBounds(594, 147, 116, 22);
		contentPane.add(txtPseudoInscription);
		txtPseudoInscription.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblEmail.setBounds(464, 201, 56, 16);
		contentPane.add(lblEmail);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNom.setBounds(464, 253, 56, 16);
		contentPane.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPrnom.setBounds(464, 308, 56, 16);
		contentPane.add(lblPrnom);
		
		txtMail = new JTextField();
		txtMail.setBounds(594, 198, 116, 22);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		txtNom = new JTextField();
		txtNom.setBounds(594, 250, 116, 22);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setBounds(594, 309, 116, 22);
		contentPane.add(txtPrenom);
		txtPrenom.setColumns(10);
		
		JLabel lblMotDePasse_1 = new JLabel("Mot de passe : ");
		lblMotDePasse_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMotDePasse_1.setBounds(464, 368, 105, 16);
		contentPane.add(lblMotDePasse_1);
		
		JButton btnEnvoyerInscription = new JButton("Envoyer");
		btnEnvoyerInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String lePseudo = txtPseudoInscription.getText();
				String leMail = txtMail.getText();
				String leNom = txtNom.getText();
				String lePrenom = txtPrenom.getText();
				String leMdp = passwordFieldInscription.getText();
				
				String message = Inscription(lePseudo, leMail, leNom, lePrenom, leMdp);
				lblMessageInscription.setText(message);
			}
		});
		btnEnvoyerInscription.setBounds(532, 434, 97, 25);
		contentPane.add(btnEnvoyerInscription);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(159, 232, 116, 22);
		contentPane.add(passwordField);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(31, 353, 377, 16);
		contentPane.add(lblMessage);
		
		passwordFieldInscription = new JPasswordField();
		passwordFieldInscription.setBounds(594, 365, 116, 22);
		contentPane.add(passwordFieldInscription);
		
		lblMessageInscription = new JLabel("");
		lblMessageInscription.setBounds(405, 503, 412, 16);
		contentPane.add(lblMessageInscription);
	}
}
