
public class Participant {
	private String nom;
	private String prenom;
	private String email;
	private String pseudo;
	private int rang;
	
	public Participant(String nom, String prenom, String email, String pseudo, int rang) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.pseudo = pseudo;
		this.rang = rang;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
}
