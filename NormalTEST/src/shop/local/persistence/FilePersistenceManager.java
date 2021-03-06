package shop.local.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import shop.local.valueobjects.*;



public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;
	
	public void openForReading(String datei) throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(datei));
	}

	public void openForWriting(String datei) throws IOException {
		writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
	}

	public boolean close() {
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return false;
			}
		}

		return true;
	}



	public Artikel ladeArtikel() throws IOException {
		// Titel einlesen
		String name = liesZeile();
		if (name == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		// Nummer einlesen ...
		String nummerString = liesZeile();
		// ... und von String in int konvertieren
		int nummer = Integer.parseInt(nummerString);
		
		String bestandString = liesZeile();
		// ... und von String in int konvertieren
		int bestand = Integer.parseInt(bestandString);
		
		String preisString = liesZeile();
		// ... und von String in int konvertieren
		float preis = Float.parseFloat(preisString);

		String psizeString = liesZeile();

		int psize = Integer.parseInt(psizeString);

		String packet = liesZeile();
		if (packet == "e"){
			return new Einzelartikel(nummer, name, bestand, preis, psize);
		} else {
			return new Massengutartikel(nummer, name, bestand, preis, psize);
		}

		// neues Artikel-Objekt anlegen und zurückgeben
		//return new Artikel(name, nummer, bestand, preis, psize);
	}

	public Arbeiter ladeArbeiter() throws IOException {
		String name = liesZeile();
		if (name == null) {
			return null;
		}

		String passwort = liesZeile();

		// Titel einlesen
		String nr = liesZeile();

		// Nummer einlesen ...

		String usertype = liesZeile();


		// neues Arbeiter-Objekt anlegen und zurückgeben
		return new Arbeiter(name, passwort, nr, usertype);
	}
	public Kunde ladeKunde() throws IOException {
		// Titel einlesen
		String name = liesZeile();
		if (name == null) {
			// keine Daten mehr vorhanden
			return null;
		}
		// Passwort einlesen ...
		String passwort = liesZeile();

		String nr = liesZeile();

		String plz = liesZeile();

		String wohnort = liesZeile();

		String strasse = liesZeile();

		String land = liesZeile();

		String usertype = liesZeile();


		// neues Kunden-Objekt anlegen und zurückgeben
		return new Kunde(name, passwort, nr, plz, wohnort, strasse, land, usertype);
	}


	public boolean speichereArtikel(Artikel a) throws IOException {
		// Titel, Nummer und Verf�gbarkeit schreiben
		schreibeZeile(a.getBezeichnung());
		schreibeZeile(a.getNummer() + "");
		schreibeZeile(Integer.valueOf(a.getBestand()).toString());
		schreibeZeile(Float.valueOf(a.getPreis()).toString());

		return true;
	}

	public boolean speichereArbeiter(Arbeiter a) throws IOException {
		// Attribute des Arbeiters schreiben
		schreibeZeile(a.getName());
		schreibeZeile(a.getPasswort());
		schreibeZeile(a.getNummer());
		schreibeZeile(a.getUsertyp());

		return true;
	}

	public boolean speichereKunde(Kunde k) throws IOException {
		// Attribute des Kundens schreiben
		schreibeZeile(k.getName());
		schreibeZeile(k.getPasswort());
		schreibeZeile(k.getNummer());
		schreibeZeile(k.getPlz());
		schreibeZeile(k.getWohnort());
		schreibeZeile(k.getStrasse());
		schreibeZeile(k.getLand());
		schreibeZeile(k.getUsertyp());

		return true;
	}

	/*
	 *  Wenn später mal eine Kundenverwaltung ergänzt wird:

	public Kunde ladeKunde() throws IOException {
		// TODO: Implementieren
		return null;
	}

	public boolean speichereKunde(Kunde k) throws IOException {
		// TODO: Implementieren
		return false;
	}

	*/
	
	/*
	 * Private Hilfsmethoden
	 */
	
	private String liesZeile() throws IOException {
		if (reader != null)
			return reader.readLine();
		else
			return "";
	}

	private void schreibeZeile(String daten) {
		if (writer != null)
			writer.println(daten);
	}
}
