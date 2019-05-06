import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;

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
					String pseudo = "";
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
		final String url = "jdbc:mysql://localhost/m2l_bd1" +"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC"; 
		final String user = "root";
		final String password ="";
		String query = "select nom, prenom, email, pseudo, rang from membres, jonction, evenements where evenements.id= "+ id +" AND evenements.id = jonction.id_evenement AND membres.id = jonction.id_membre";
		ResultSet res;
		ArrayList<Participant> participants = new ArrayList<Participant>();
		
		try {
			Class.forName(driver).newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			Statement st = con.createStatement();
			System.out.println(query);
			res = st.executeQuery(query);
			System.out.println("après l'exécution");
			while(res.next()) {
				Participant e = new Participant(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5));
				participants.add(e);
			}
			return participants;
		}
		catch(Exception e) {
			System.out.println("problème intervenu dans getParticipants");
			return new ArrayList<>();
		}
	}

	/**
	 * Create the frame.
	 */
	public ListeParticipants(int id, String pseudo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1550,1000);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTable table;
		ArrayList<Participant> participants = new ArrayList<Participant>();
		participants = getParticipants(id);
		Object rows[][] = new Object[participants.size()][];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = new Object[] {
					participants.get(i).getNom(),
					participants.get(i).getPrenom(),
					participants.get(i).getEmail(),
					participants.get(i).getPseudo(),
					participants.get(i).getRang()
			};
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
		jsp.setBounds(91, 357, 1348, 442);
		table.getTableHeader().setFont(new Font("Tahoma", Font.ITALIC, 20));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(20);
		table.setLocation(10, 20);
		contentPane.add(jsp);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Affichage affichage = new Affichage("2", pseudo);
				affichage.setVisible(true);
			}
		});
		btnRetour.setBounds(1363, 865, 125, 37);
		contentPane.add(btnRetour);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1532, 139);
		contentPane.add(panel);
		
		JLabel lblImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/bandeau.png")).getImage();
		lblImg.setIcon(new ImageIcon(img));
		panel.add(lblImg);
		
		JLabel label = new JLabel("Gestion des \u00E9v\u00E9nements");
		label.setForeground(new Color(255, 69, 0));
		label.setFont(new Font("Tahoma", Font.BOLD, 70));
		label.setBounds(332, 198, 927, 76);
		contentPane.add(label);
	}
}
