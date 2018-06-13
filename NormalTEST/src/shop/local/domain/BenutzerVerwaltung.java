package shop.local.domain;

import shop.local.domain.exceptions.BenutzernameOderPasswortFalschException;
import shop.local.valueobjects.Arbeiter;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.User;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;



public class BenutzerVerwaltung {

    private List<Kunde> kundenListe = new Vector<>();
    private List<Arbeiter> arbeiterListe = new Vector<>();


    private PersistenceManager pm = new FilePersistenceManager();

    public List<Arbeiter> getArbeiterListe() {
        return arbeiterListe;
    }

    public List<Kunde> getKundenListe() {
        return kundenListe;
    }

    //Ab hier Persistenz für Arbeiter
   	public void liesArbeiter(String datei) throws IOException {
		// PersistenzManager für Lesevorgänge öffnen
		pm.openForReading(datei);
		Arbeiter einArbeiter;
		do {
			einArbeiter = pm.ladeArbeiter();
			if (einArbeiter != null) {
				einfuegenA(einArbeiter.getName(), einArbeiter.getPasswort(),  einArbeiter.getNummer());
			}
		} while (einArbeiter != null);
		// Persistenz-Schnittstelle wieder schließen
		pm.close();
	}


	public void schreibeArbeiter(String datei) throws IOException  {
		// PersistenzManager für Schreibvorgänge öffnen
		pm.openForWriting(datei);
		Iterator<Arbeiter> it = arbeiterListe.iterator();
		while (it.hasNext()) {
			Arbeiter a = it.next();
			pm.speichereArbeiter(a);
		}

		// Persistenz-Schnittstelle wieder schließen
		pm.close();
	}

    public void einfuegenA(String name, String passwort, String nummer) {
        Arbeiter einArbeiter = new Arbeiter (name, passwort, nummer, "a");
        // das übernimmt der Vector
        arbeiterListe.add(einArbeiter);
    }

    //Ab hier Persistenz für Kunden

    public void liesKunde(String datei) throws IOException {
        // PersistenzManager für Lesevorgänge öffnen
        pm.openForReading(datei);
        Kunde einKunde;
        do {
            einKunde = pm.ladeKunde();
            if (einKunde != null) {
                einfuegenK(einKunde.getName(), einKunde.getPasswort(), einKunde.getNummer(), einKunde.getPlz(), einKunde.getWohnort(), einKunde.getStrasse(), einKunde.getLand());
            }
        } while (einKunde != null);
        // Persistenz-Schnittstelle wieder schließen
        pm.close();
    }


    public void schreibeKunde(String datei) throws IOException  {
        // PersistenzManager für Schreibvorgänge öffnen
        pm.openForWriting(datei);
        Iterator<Kunde> it = kundenListe.iterator();
        while (it.hasNext()) {
            Kunde k = it.next();
            pm.speichereKunde(k);
        }
        // Persistenz-Schnittstelle wieder schließen
        pm.close();
    }

    public void einfuegenK(String name, String passwort, String nummer, String plz, String wohnort, String strasse, String land) {
        Kunde einKunde = new Kunde (name, passwort, nummer, plz, wohnort, strasse, land, "k");
        // das übernimmt der Vector
        kundenListe.add(einKunde);
    }

    public User einloggen(String name, String passwort) throws BenutzernameOderPasswortFalschException {
   	    User user = null;
        for (User u: kundenListe) {
            if  (u.getName().equals(name) && u.getPasswort().equals(passwort)) {
                user = u;
                break;
            }
        }

        for (User u: arbeiterListe) {
            if (u.getName().equals(name) && u.getPasswort().equals(passwort)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            throw new BenutzernameOderPasswortFalschException();
        }
        return user;
    }
}
