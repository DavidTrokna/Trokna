package shop.local.valueobjects;

public class Artikel implements Comparable<Artikel> {

	private String bezeichnung;
	private int artNummer;
	private int bestand;
	private float preis;
	
	public Artikel(String name, int artNummer, int bestand, float preis) {
		this.bezeichnung = name;
		this.artNummer = artNummer;
		this.bestand = bestand;
		this.preis = preis;
	}
	
	@Override
	public String toString() {
		//String verfuegbarkeit = this.bestand>0 ? "verfuegbar" : "ausverkauft";
		
		return ("Nr: " + artNummer + " | Bezeichnung: " + bezeichnung + " | Bestand: " + bestand + " | Preis: " + preis + " Euro");
	}
	
	public int getNummer() {
		return artNummer;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}
	
	public int getBestand() {
		return bestand;
	}

	public void setBestand(int menge) {
		bestand = menge;
	}

	public void verringereBestand(int menge) {
		bestand -= menge;
	}

	public float getPreis() {
		return preis;
	}
	
	public void setPreis(int preis) {
		bestand += preis;
	}

	// Quicksort suchen und anpassen
	@Override
	public int compareTo(Artikel o) {
		Integer nummer = this.artNummer;
		return nummer.compareTo(o.getBestand());		
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof  Artikel) {
			Artikel andererArtikel = (Artikel) obj;
			return this.artNummer == andererArtikel.artNummer;
		}
		return false;
	}
}