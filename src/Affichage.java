import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.mysql.cj.result.Row;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;

public class Affichage extends JFrame {

	private JPanel contentPane;
	private JTextField txtNameEvent;
	private JTextField txtDateEvent;
	private JTextField txtNameOrga;
	private JLabel lblMessage;
	private JLabel lblInscription;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String rang = "1";
					String pseudo = "";
					Affichage frame = new Affichage(rang, pseudo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ArrayList<Evenement> afficheInfos() {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
		final String user = "root";
		final String password ="";
		String query = "select * from evenements";
		ResultSet res;
		ArrayList<Evenement> evenements = new ArrayList<Evenement>();
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			res = st.executeQuery(query);
			System.out.println(query);
			while(res.next()) {
				Evenement e = new Evenement(res.getInt(1), res.getString(2), res.getString(3), res.getString(4));
				evenements.add(e);
			}
			return evenements;
		}
		catch(Exception e) {
			System.out.println("problème intervenu");
			return new ArrayList<>();
		}
	}
	
	public boolean AjoutEvent(String nomEvent, String DateEvent, String nomOrga)
	{
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		
		String query = "insert into evenements(name_event, date_event, nom_organisateur) values(?,?,?)";
		//ResultSet res;
		
		try 
		{
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, nomEvent);
			st.setString(2, DateEvent);
			st.setString(3, nomOrga);
			st.execute();
			st.close();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Erreur "+e.getMessage());
			return false;
		}
		
	}
	
	public boolean UpdateEvent(String name_event, String date_event, String name_Orga, int id) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		
		String query = "update evenements set name_event = ?, date_event = ?, nom_organisateur = ? where id= ?";
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setString( 1, name_event );
			stmt.setString( 2, date_event );
			stmt.setString(3, name_Orga);
			stmt.setInt(4, id);
			stmt.executeUpdate();
			stmt.close();
			
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean DeleteEvent(int id) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		
		String query = "delete from evenements where id= ?";
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement stmt = con.prepareStatement(query);

			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public int GetId(String name_event, String date_event, String nom_organisateur) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
		final String user = "root";
		final String password ="";
		String query = "select id from evenements where name_event = '"+ name_event + "'  AND date_event = '" + date_event + "' AND nom_organisateur = '" + nom_organisateur +"' ";
		ResultSet res;
		int id;
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			res = st.executeQuery(query);
			res.next();
			id = res.getInt(1);
			return id;
		}
		catch(Exception e){
			return 404;
		}
	}
	
	public int getIdMembre(String pseudo) 
	{
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		ResultSet res;
		int id_membre;
		
		String firstQuery = "select id from membres where pseudo = '" + pseudo + "'";
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			res = st.executeQuery(firstQuery);
			res.next();
			id_membre = res.getInt(1);
			
			return id_membre;
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	public boolean Inscription(int id_membre, int id_event) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		
		String query = "insert into jonction(id_evenement, id_membre) values(?,?)";
		//ResultSet res;
		
		try 
		{
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, id_event);
			st.setInt(2, id_membre);
			st.execute();
			st.close();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Erreur "+e.getMessage());
			return false;
		}
	}
	
	public ArrayList<String> getEvents(int id) {
		System.out.println("je rentre dans getEvents");
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		ResultSet res;
		ArrayList<String> evenements = new ArrayList<String>();
		
		String query = "select distinct name_event from evenements, jonction, membres where jonction.id_membre = '" + id + "' AND jonction.id_evenement = evenements.id";
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			System.out.println(query);
			res = st.executeQuery(query);
			while(res.next()) 
			{
				evenements.add(res.getString(1));
			}
			
			return evenements;
		}
		catch(Exception e) {
			return new ArrayList<String>();
		}
	}

	/**
	 * Create the frame.
	 */
	public Affichage(String rang, String pseudo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		JTable table;
		ArrayList<Evenement> evenements = new ArrayList<Evenement>();
		evenements = afficheInfos();
		System.out.println(evenements.get(0).toString());
		Object rows[][] = new Object[evenements.size()][];
		int index = 0;
		for (int i = 0; i < rows.length; i++) {
			rows[index] = new Object[] {
					evenements.get(i).getId(),
					evenements.get(i).getName_event(),
					evenements.get(i).getDate_event(),
					evenements.get(i).getNom_organisateur()
			};
			index ++;
		}
		
		String columns[] = {"ID", "Nom evenement", "Date evenement", "Nom Organisateur"};
		JScrollPane jsp;
		
		DefaultTableModel model = new DefaultTableModel(rows, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(rang.equals("1")) {
					return false;
				}
				else {
					return column == 0 ? false : true;
				}
			}
			
		};
		contentPane.setLayout(null);
		
		table = new JTable(model);
		jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(5, 54, 872, 324);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setLocation(10, 20);
		//setSize(150,100);
		contentPane.add(jsp);
		
		JButton btnDconnexion = new JButton("D\u00E9connexion");
		btnDconnexion.setBounds(766, 515, 111, 25);
		btnDconnexion.setForeground(SystemColor.windowBorder);
		btnDconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion();
				connexion.setVisible(true);
			}
		});
		contentPane.add(btnDconnexion);
		
		JLabel lblNomEvenement = new JLabel("Nom Evenement : ");
		lblNomEvenement.setBounds(12, 457, 111, 16);
		if(rang.equals("2")) {
			contentPane.add(lblNomEvenement);
		}
		txtNameEvent = new JTextField();
		txtNameEvent.setBounds(117, 454, 116, 22);
		if(rang.equals("2")) {
			contentPane.add(txtNameEvent);
		}
		txtNameEvent.setColumns(10);
		
		JLabel lblDateEvenement = new JLabel("Date evenement :");
		lblDateEvenement.setBounds(245, 457, 111, 16);
		if(rang.equals("2")) {
			contentPane.add(lblDateEvenement);
		}
		
		txtDateEvent = new JTextField();
		txtDateEvent.setBounds(351, 454, 116, 22);
		if(rang.equals("2")) {
			contentPane.add(txtDateEvent);
		}
		txtDateEvent.setColumns(10);
		
		JLabel lblNomOrganisateur = new JLabel("Nom Organisateur :");
		lblNomOrganisateur.setBounds(479, 457, 127, 16);
		if(rang.equals("2")) {
			contentPane.add(lblNomOrganisateur);
		}
		
		txtNameOrga = new JTextField();
		txtNameOrga.setBounds(597, 457, 116, 22);
		if(rang.equals("2")) {
			contentPane.add(txtNameOrga);
		}
		txtNameOrga.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String LeNomEvent = txtNameEvent.getText();
				String LaDate = txtDateEvent.getText();
				String LeNomOrga = txtNameOrga.getText();
				
				boolean ajout = AjoutEvent(LeNomEvent, LaDate, LeNomOrga);
				int id = GetId(LeNomEvent, LaDate, LeNomOrga) ;
				if(ajout)
				{
					lblMessage.setText("Ajout réalisé avec succès.");
					model.insertRow(rows.length, new Object[] { id, LeNomEvent, LaDate, LeNomOrga } );
					model.fireTableDataChanged();
					txtNameEvent.setText("");
					txtDateEvent.setText("");
					txtNameOrga.setText("");
				}
				else
				{
					lblMessage.setText("Une erreur est parvenue et l'ajout n'a pas été réalisé. Recommencez.");
				}
			}
		});
		btnAjouter.setBounds(725, 457, 97, 25);
		if(rang.equals("2")) {
			contentPane.add(btnAjouter);
		}
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(12, 515, 701, 16);
		contentPane.add(lblMessage);
		
		JLabel lblEvenements = new JLabel("Evenements");
		lblEvenements.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEvenements.setBounds(368, 13, 169, 25);
		contentPane.add(lblEvenements);
		
		JButton btnConsulter = new JButton("Consulter");
		btnConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListeParticipants liste = new ListeParticipants( (int) table.getValueAt(table.getSelectedRow(), 0));
				dispose();
			}
		});
		btnConsulter.setBounds(259, 391, 97, 25);
		if(rang.equals("2")) {
			contentPane.add(btnConsulter);
		}
		
		JButton btnSinscrire = new JButton("S'inscrire");
		btnSinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = getIdMembre(pseudo);
				
				if(Inscription((int) table.getValueAt(table.getSelectedRow(), 0), id )) {
					lblInscription.setText("Inscription réussie.");
				}
				else {
					lblInscription.setText("Erreur parvenue. Réessayez.");
				}
				
			}
		});
		btnSinscrire.setBounds(752, 391, 97, 25);
		if(rang.equals("1")) {
			contentPane.add(btnSinscrire);
		}
		
		
		
		lblInscription = new JLabel("");
		lblInscription.setBounds(657, 427, 220, 16);
		contentPane.add(lblInscription);
		
		JLabel lblLesvnementsAuquels = new JLabel("Les \u00E9v\u00E9nements auxquels vous \u00EAtes inscrit");
		lblLesvnementsAuquels.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLesvnementsAuquels.setBounds(33, 391, 301, 16);
		if(rang.equals("1"))
		{
			contentPane.add(lblLesvnementsAuquels);
		}
		
		JTable tableEvent;
		int id = getIdMembre(pseudo);
		System.out.println(id);
		ArrayList<String> list = new ArrayList<String>();
		list = getEvents(id);
		Object ranges[][] = new Object[list.size()][];
		int ind = 0;
		for (int i = 0; i < ranges.length; i++) {
			ranges[ind] = new Object[] {
					list.get(i)
			};
			ind ++;
		}
		
		String column[] = {"Li"};
		JScrollPane jspe;
		DefaultTableModel mod = new DefaultTableModel(ranges, column);
		
		tableEvent = new JTable(mod);
		tableEvent.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		tableEvent.setLocation(10, 20);
		jspe = new JScrollPane(tableEvent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jspe.setBounds(205, 54, 50, 50);
		
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.isEditing()) {
				     table.getCellEditor().stopCellEditing();
				}
				String newNameEvent = (String) table.getValueAt(table.getSelectedRow(), 1);
				String newDateEvent = (String) table.getValueAt(table.getSelectedRow(), 2);
				String newNameOrga = (String) table.getValueAt(table.getSelectedRow(), 3);
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				boolean success = UpdateEvent(newNameEvent, newDateEvent, newNameOrga, id);
				if(success) {
					lblMessage.setText("Modification bien effectuée.");
				}
				else {
					lblMessage.setText("Erreur intervenue. La modification n'a pas été effectuée.");
				}
			}
		});
		btnModifier.setBounds(15, 391, 97, 25);
		if(rang.equals("2")) {
			contentPane.add(btnModifier);
		}
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = (int) table.getValueAt(table.getSelectedRow(), 0);
				boolean success = DeleteEvent(id);
				if(success) {
					lblMessage.setText("Suppression effectuée.");
					System.out.println(table.getRowCount());
					table.remove(table.getSelectedRow());
				}
				else {
					lblMessage.setText("Erreur intervenue. La suppression n'a pas été effectuée.");
				}
			}
		});
		btnSupprimer.setBounds(136, 391, 97, 25);
		
		 if(rang.equals("2")){
		 	contentPane.add(btnSupprimer);
		 }
		 
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
