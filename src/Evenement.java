
public class Evenement {
	private int id;
	private String name_event;
	private String date_event;
	private String nom_organisateur;
	
	public Evenement(int id, String name_event, String date_event, String nom_organisateur) {
		super();
		this.id = id;
		this.name_event = name_event;
		this.date_event = date_event;
		this.nom_organisateur = nom_organisateur;
	}

	public String getName_event() {
		return name_event;
	}

	@Override
	public String toString() {
		return "Evenement [id=" + id + ", name_event=" + name_event + ", date_event=" + date_event
				+ ", nom_organisateur=" + nom_organisateur + "]";
	}

	public void setName_event(String name_event) {
		this.name_event = name_event;
	}

	public String getDate_event() {
		return date_event;
	}

	public void setDate_event(String date_event) {
		this.date_event = date_event;
	}

	public String getNom_organisateur() {
		return nom_organisateur;
	}

	public void setNom_organisateur(String nom_organisateur) {
		this.nom_organisateur = nom_organisateur;
	}

	public int getId() {
		return id;
	}
	
	
}
