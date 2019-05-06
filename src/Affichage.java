import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.ImageIcon;
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		ResultSet res;
		ArrayList<String> evenements = new ArrayList<String>();
		
		String query = "select distinct name_event from evenements, jonction where jonction.id_membre = " + id + " AND jonction.id_evenement = evenements.id";
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
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}

	/**
	 * Create the frame.
	 */
	public Affichage(String rang, String pseudo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1550,1000);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

		JTable table;
		ArrayList<Evenement> evenements = new ArrayList<Evenement>();
		evenements = afficheInfos();
		System.out.println(evenements.get(0).toString());
		Object rows[][] = new Object[evenements.size()][];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new Object[] {
					evenements.get(i).getId(),
					evenements.get(i).getName_event(),
					evenements.get(i).getDate_event(),
					evenements.get(i).getNom_organisateur()
			};
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
		jsp.setBounds(45, 273, 1438, 345);
		table.getTableHeader().setFont(new Font("Tahoma", Font.ITALIC, 20));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(20);
		table.setLocation(10, 20);
		//setSize(150,100);
		contentPane.add(jsp);
		
		JButton btnDconnexion = new JButton("D\u00E9connexion");
		btnDconnexion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDconnexion.setBounds(1338, 879, 158, 44);
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
		lblNomEvenement.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNomEvenement.setBounds(74, 742, 166, 25);
		if(rang.equals("2")) {
			contentPane.add(lblNomEvenement);
		}
		txtNameEvent = new JTextField();
		txtNameEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNameEvent.setBounds(252, 742, 148, 26);
		if(rang.equals("2")) {
			contentPane.add(txtNameEvent);
		}
		txtNameEvent.setColumns(10);
		
		JLabel lblDateEvenement = new JLabel("Date evenement :");
		lblDateEvenement.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDateEvenement.setBounds(437, 745, 190, 19);
		if(rang.equals("2")) {
			contentPane.add(lblDateEvenement);
		}
		
		txtDateEvent = new JTextField();
		txtDateEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtDateEvent.setBounds(625, 744, 148, 26);
		if(rang.equals("2")) {
			contentPane.add(txtDateEvent);
		}
		txtDateEvent.setColumns(10);
		
		JLabel lblNomOrganisateur = new JLabel("Nom Organisateur :");
		lblNomOrganisateur.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNomOrganisateur.setBounds(803, 746, 184, 21);
		if(rang.equals("2")) {
			contentPane.add(lblNomOrganisateur);
		}
		
		txtNameOrga = new JTextField();
		txtNameOrga.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtNameOrga.setBounds(1007, 745, 148, 25);
		if(rang.equals("2")) {
			contentPane.add(txtNameOrga);
		}
		txtNameOrga.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		btnAjouter.setBounds(1206, 732, 127, 44);
		if(rang.equals("2")) {
			contentPane.add(btnAjouter);
		}
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMessage.setBounds(266, 915, 791, 25);
		contentPane.add(lblMessage);
		
		JButton btnConsulter = new JButton("Consulter");
		btnConsulter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListeParticipants liste = new ListeParticipants((int) table.getValueAt(table.getSelectedRow(), 0), pseudo);
				liste.setVisible(true);
				dispose();
			}
		});
		btnConsulter.setBounds(884, 651, 130, 45);
		if(rang.equals("2")) {
			contentPane.add(btnConsulter);
		}
		
		JButton btnSinscrire = new JButton("S'inscrire");
		btnSinscrire.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = getIdMembre(pseudo);
				
				if(Inscription(id, (int) table.getValueAt(table.getSelectedRow(), 0) )) {
					lblInscription.setText("Inscription réussie.");
				}
				else {
					lblInscription.setText("Erreur parvenue. Réessayez.");
				}
				
			}
		});
		btnSinscrire.setBounds(1367, 632, 116, 44);
		if(rang.equals("1")) {
			contentPane.add(btnSinscrire);
		}
		
		lblInscription = new JLabel("");
		lblInscription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInscription.setBounds(732, 915, 325, 25);
		contentPane.add(lblInscription);
		
		JLabel lblLesvnementsAuquels = new JLabel("Les \u00E9v\u00E9nements auxquels vous \u00EAtes inscrit");
		lblLesvnementsAuquels.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblLesvnementsAuquels.setBounds(111, 671, 399, 22);
		if(rang.equals("1")){
			contentPane.add(lblLesvnementsAuquels);
		}
		
		JTable tableEvent;
		int id = getIdMembre(pseudo);
		System.out.println(id);
		ArrayList<String> list = getEvents(id);
		System.out.println("list : " + list);
		Object ranges[][] = new Object[list.size()][];
		for (int i = 0; i < ranges.length; i++) {
			ranges[i] = new Object[] {
					list.get(i)
			};
		}
		
		String column[] = {"Nom de l'événement"};
		JScrollPane jspe;
		DefaultTableModel mod = new DefaultTableModel(ranges, column);

		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1532, 122);
		contentPane.add(panel);
		
		JLabel lblImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/bandeau.png")).getImage();
		lblImg.setIcon(new ImageIcon(img));
		panel.add(lblImg);
		
		tableEvent = new JTable(mod);
		jspe = new JScrollPane(tableEvent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jspe.setBounds(12, 706, 594, 217);
		tableEvent.getTableHeader().setFont(new Font("Tahoma", Font.ITALIC, 20));
		tableEvent.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tableEvent.setRowHeight(20);
		tableEvent.setLocation(10, 20);
		if(rang.equals("1")){
		 	contentPane.add(jspe);
		}
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		btnModifier.setBounds(1069, 651, 116, 44);
		if(rang.equals("2")) {
			contentPane.add(btnModifier);
		}
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		btnSupprimer.setBounds(1242, 651, 136, 44);
		
		if(rang.equals("2")){
		 	contentPane.add(btnSupprimer);
		}

	 	JLabel label = new JLabel("Gestion des \u00E9v\u00E9nements");
	 	label.setForeground(new Color(255, 69, 0));
	 	label.setFont(new Font("Tahoma", Font.BOLD, 70));
	 	label.setBounds(406, 151, 927, 76);
	 	contentPane.add(label);
		 
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
