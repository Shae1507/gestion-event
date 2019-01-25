import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ListeParticipants extends JFrame {

	private JPanel contentPane;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int id = 0;
					String pseudo ="";
					ListeParticipants frame = new ListeParticipants(id, pseudo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ArrayList<Participant> getParticipants(int id) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
		final String user = "root";
		final String password ="";
		String query = "select distinct nom, prenom, email, pseudo, rang from membres, jonction, evenements where evenements.id= '"+ id +"' AND evenements.id = jonction.id_evenement AND membres.id = jonction.id_membre";
		ResultSet res;
		ArrayList<Participant> participants = new ArrayList<Participant>();
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			System.out.println(query);
			res = st.executeQuery(query);
			while(res.next()) {
				Participant e = new Participant(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5));
				participants.add(e);
			}
			return participants;
		}
		catch(Exception e) {
			System.out.println("problème intervenu");
			return new ArrayList<>();
		}
	}
	
	public int getID(String pseudo) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
		final String user = "root";
		final String password ="";
		String query = "select id from membres where pseudo = '" + pseudo + "'";
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
	
	public boolean suppInscrit(int idMembre, int idEvent) {
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String url = "jdbc:mysql://localhost/gestion_evenements" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		final String user = "root";
		final String password ="";
		
		String query = "delete from jonction where id_membre = '" + idMembre + "' AND id_evenement = '" + idEvent + "'";
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement stmt = con.prepareStatement(query);
			System.out.println(query);

			stmt.executeUpdate();
			stmt.close();
			
			return true;
		}
		catch(Exception e){
			return false;
		}
	}

	/**
	 * Create the frame.
	 */
	public ListeParticipants(int id, String pseudo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTable table;
		ArrayList<Participant> participants = new ArrayList<Participant>();
		participants = getParticipants(id);
		Object rows[][] = new Object[participants.size()][];
		int index = 0;
		for (int i = 0; i < rows.length; i++) {
			rows[index] = new Object[] {
					participants.get(i).getNom(),
					participants.get(i).getPrenom(),
					participants.get(i).getEmail(),
					participants.get(i).getPseudo(),
					participants.get(i).getRang()
			};
			index ++;
		}
		
		String columns[] = {"Nom", "Prenom", "Email", "Pseudo", "Rang"};
		JScrollPane jsp;
		
		DefaultTableModel model = new DefaultTableModel(rows, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return true;
			}
			
		};
		contentPane.setLayout(null);
		table = new JTable(model);
		jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(10, 25, 862, 324);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		table.setLocation(10, 20);
		contentPane.add(jsp);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				String rang = "2";
				Affichage affichage = new Affichage(rang, pseudo);
			}
		});
		btnRetour.setBounds(773, 474, 97, 25);
		contentPane.add(btnRetour);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idMembre = getID((String) table.getValueAt(table.getSelectedRow(), 3));
				
				if(suppInscrit(idMembre, id))
				{
					lblMessage.setText("Suppression réussie.");
				}
				else {
					lblMessage.setText("Une erreur est intervenue. Veuillez réessayer.");
				}
			}
		});
		btnSupprimer.setBounds(20, 361, 97, 25);
		contentPane.add(btnSupprimer);
		
		lblMessage = new JLabel("");
		lblMessage.setBounds(22, 413, 422, 16);
		contentPane.add(lblMessage);
		
		setVisible(true);
	}
}
