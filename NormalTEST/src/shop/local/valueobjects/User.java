package shop.local.valueobjects;
// User unterteilen in Arbeiter und Kunde \ Vererbung der Basisdaten
public abstract class User {
	private String name;
	private String passwort;
	private String nummer;
	private String usertyp;

    public User(String name, String passwort, String nummer, String usertyp) {
		this.name = name;
		this.passwort = passwort;
		this.nummer = nummer;
		this.usertyp = usertyp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNummer() {
		return nummer;
	}

	public String getUsertyp() {
		return usertyp;
	}

	public String getPasswort() {
		return passwort;
	}
}
