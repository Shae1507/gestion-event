import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ListeParticipants extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int id = 0;
					ListeParticipants frame = new ListeParticipants(id);
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
		String query = "select nom, prenom, email, pseudo, rang from membres, jonction, evenements where id= '"+ id +"'  evenements.id = jonction.id_evenement AND membres.id = jonction.id_membres";
		ResultSet res;
		ArrayList<Participant> participants = new ArrayList<Participant>();
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			res = st.executeQuery(query);
			System.out.println(query);
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

	/**
	 * Create the frame.
	 */
	public ListeParticipants(int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTable table;
		ArrayList<Participant> participants = new ArrayList<Participant>();
		participants = getParticipants(id);
		System.out.println(participants.get(0).toString());
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
	}

}
