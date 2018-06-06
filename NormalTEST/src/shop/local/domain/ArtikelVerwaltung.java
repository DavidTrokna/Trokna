package shop.local.domain;
import java.io.IOException;
import java.util.*;
import shop.local.domain.exceptions.ArtikelExistiertBereitsException;
import shop.local.domain.exceptions.ArtikelExistiertNichtException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.User;


public class ArtikelVerwaltung {
	// Verwaltung des Artikelbestands in einer verketteten Liste
	private List<Artikel> artikelBestand = new Vector<Artikel>();
	// Persistenz-Schnittstelle, die für die Details des Dateizugriffs verantwortlich ist
	private PersistenceManager pm = new FilePersistenceManager();



	public void liesArtikel(String datei) throws IOException {
		// PersistenzManager für Lesevorgänge öffnen
		pm.openForReading(datei);
		Artikel einArtikel;
		do {
			einArtikel = pm.ladeArtikel();
			if (einArtikel != null) {
				try {
					einfuegen(einArtikel);
				} catch (ArtikelExistiertBereitsException e) {
					// Dürfte nicht passieren
				}
			}
		} while (einArtikel != null);
		// Persistenz-Schnittstelle wieder schließen
		pm.close();
	}


	public void schreibeArtikel(String datei) throws IOException  {
		// PersistenzManager für Schreibvorgänge öffnen
		pm.openForWriting(datei);
		Iterator<Artikel> it = artikelBestand.iterator();
		while (it.hasNext()) {
			Artikel a = it.next();
			pm.speichereArtikel(a);
		}

		// Persistenz-Schnittstelle wieder schließen
		pm.close();
	}

	public void liesHistorie(String datei) throws IOException {
		pm.openForWriting(datei);
	}

	public void schreibeHistorie(String datei) throws IOException {
		pm.openForWriting(datei);
		Iterator<Artikel> it = artikelBestand.iterator();
		while (it.hasNext()) {
			Artikel a = it.next();
			pm.speichereArtikel(a);
		}
		pm.close();
	}

	//Artikel hinzufügen
	public void einfuegen(Artikel einArtikel) throws ArtikelExistiertBereitsException {
		if (artikelBestand.contains(einArtikel)) {
			throw new ArtikelExistiertBereitsException(einArtikel);
		}
		// das übernimmt der Vector
		artikelBestand.add(einArtikel);
	}

	//Artikel löschen
	public void loeschen(Artikel einArtikel) {
		// das übernimmt der Vector
		artikelBestand.remove(einArtikel);
	}

	//Artikel suchen
	public List<Artikel> sucheArtikel(String titel) {
		List<Artikel> suchErg = new Vector<Artikel>();

		Iterator<Artikel> it = artikelBestand.iterator();
		while (it.hasNext()) {
			Artikel aktArtikel = it.next();
			if (aktArtikel.getTitel().equals(titel)) {
				suchErg.add(aktArtikel);
			}
		}

		return suchErg;
	}

	//Rückgabe des Artikelbestandes
	public List<Artikel> getArtikelBestand() {
		//Collections.sort(artikelBestand);
		return artikelBestand;
	}

	public Artikel gibArtikel(int artNummer) throws ArtikelExistiertNichtException {
		for (Artikel art : artikelBestand) {
			if (art.getNummer() == artNummer) {
				return art;
			}
		}
		throw new ArtikelExistiertNichtException(artNummer);
	}

	// setBestand anstatt erhöhe/verringere Bestand
	public void verändereBestand(Artikel einArtikel, int menge) throws ArtikelExistiertNichtException {
		if (!artikelBestand.contains(einArtikel)) {
			throw new ArtikelExistiertNichtException(einArtikel);
		} else {
			einArtikel.setBestand(menge);
		}
	}

	public void verringereBestand (Artikel einArtikel, int menge) {
		if (!artikelBestand.contains(einArtikel)) {

		} else {
			einArtikel.verringereBestand(menge);
		}
	}

	public void artEinlagern(User eingeloggterBenutzer, int nr, int bestand) {
	}
}