package shop.local.ui.cui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import shop.local.domain.EShop;
import shop.local.domain.exceptions.ArtikelExistiertBereitsException;
import shop.local.domain.exceptions.ArtikelExistiertNichtException;
import shop.local.domain.exceptions.BenutzernameOderPasswortFalschException;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.User;
import shop.local.valueobjects.Arbeiter;


public class EShopClientCUI {

	private EShop shop;
	private BufferedReader in;

	private User eingeloggterBenutzer = null;
	
	// private User eingeloggterUser = null;
	
	public EShopClientCUI(String datei) throws IOException {
		shop = new EShop(datei);

		// Stream-Objekt fuer Texteingabe ueber Konsolenfenster erzeugen
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Ausgabe des Menüs.
	 */

	private void gibMenueAus(User user) {	//Menuausgabe mit Argumenten, benötigt zur Identifikation des Usertypen
		System.out.println("");
		System.out.print("         \n  Artikel auflisten:  'a'");
//		System.out.print("         \n  Artikel suchen:  'f'");
		System.out.print("         \n  Artikel sortieren nach Titel:  'r'");
		System.out.print("         \n  Artikel sortieren nach Nummer:  'n'");
		if (user instanceof Kunde) {
			System.out.print(" 		   \n  Artikel in Warenkorb legen: 'i'");
			System.out.print("		   \n  Warenkorb ausgeben: 'w'");
			System.out.print("		   \n  Artikel in Warenkorb verändern: 'v'");
			System.out.print("		   \n  Artikel kaufen: 'k'");
			System.out.print("		   \n  Artikel aus Warenkorb entfernen: 'l'");
			System.out.print("		   \n  Warenkorb leeren  : 'c'");
		}
		if (user instanceof Arbeiter) {
			System.out.print("         \n  Artikel loeschen: 'd'");
			System.out.print("         \n  Artikel einfuegen: 'e'");
			System.out.print("         \n  Bestand anpassen  'b'");
			System.out.print("         \n  User anlegen:  'u'");
		}
		System.out.print("         \n  Ausloggen:  'j'");
		System.out.print("         \n  ---------------------");
		System.out.println("       \n  Beenden:        'q'");
		System.out.print("> "); // Prompt
		System.out.flush(); // ohne NL ausgeben

	}



	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zum Einlesen von Benutzereingaben.
	 */
	private String liesEingabe() throws IOException {
		// einlesen von Konsole
		return in.readLine();
	}
	
	private int liesZahl() throws IOException {
		// einlesen von Konsole
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		return n;
	}
	
	private float liesKommaZahl() throws IOException {
		// einlesen von Konsole
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		float i = Float.parseFloat(br.readLine());
		return i;
	}

	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zur Verarbeitung von Eingaben
	 * und Ausgabe von Ergebnissen.
	 */
	private void verarbeiteEingabe(String line, User user) throws IOException {

		String nummer;
		int nr;
		String titel;
		int menge;
		List<Artikel> liste;

		int bestand;
		float preis;
		// Eingabe bearbeiten:
		switch (line) {
			case "a":
				liste = shop.gibAlleArtikel();
				gibArtikellisteAus(liste);
				break;
			case "d":
				if (user instanceof Arbeiter) {
					// lies die notwendigen Parameter, einzeln pro Zeile
					System.out.print("Artikelnummer > ");
					nummer = liesEingabe();
					nr = Integer.parseInt(nummer);
					try {
						shop.loescheArtikel(nr);
					} catch (ArtikelExistiertNichtException e) {
						e.printStackTrace();
					}
				}
				break;
			case "e": // Artikel einfügen
				if (user instanceof Arbeiter) {
					System.out.print("Artikelnummer > ");
					nummer = liesEingabe();
					nr = Integer.parseInt(nummer);
					System.out.print("Artikelbezeichnung  > ");
					titel = liesEingabe();
					System.out.print("Artikelbestand > ");
					bestand = liesZahl();
					System.out.print("Artikelpreis > ");
					preis = liesKommaZahl();
					try {
						shop.fuegeArtikelEin(titel, nr, bestand, preis);
						System.out.println("Der Artikel wurde eingefügt.");
						shop.einlagerung(eingeloggterBenutzer, nr, bestand);
					} catch (ArtikelExistiertBereitsException e) {
						// Hier Fehlerbehandlung...
						System.out.println("Fehler beim Einfügen.");
//						e.printStackTrace();
						System.err.println(e.getMessage());
					}
				}
				break;
			case "u":
				if (user instanceof Arbeiter) {
					String input;
					String name;
					String passwort;
					String num;
					String plz;
					String ort;
					String strasse;
					String land;
					String usertype;

					System.out.print("Soll ein Arbeiter (a) oder ein Kunde (k) erstellt werden? > ");
					input = liesEingabe();
					switch (input) {
						case "a":
						usertype = "a";
						System.out.print("Name > ");
						name = liesEingabe();
						System.out.print("Passwort > ");
						passwort = liesEingabe();
						System.out.print("Nummer > ");
						num = liesEingabe();
						shop.newA(name, passwort, num, usertype);
						break;
						case "k":
						System.out.print("Name > ");
						name = liesEingabe();
						System.out.print("Passwort > ");
						passwort = liesEingabe();
						System.out.print("Nummer > ");
						num = liesEingabe();
						System.out.print("Postleitzahl > ");
						plz = liesEingabe();
						System.out.print("Wohnort > ");
						ort = liesEingabe();
						System.out.print("Strasse > ");
						strasse = liesEingabe();
						System.out.print("Land > ");
						land = liesEingabe();
						shop.newK(name, passwort, num, plz, ort, strasse, land);
						break;
					}
				}
				break;
/*			case "f": //suchen
				System.out.print("Artikelbezeichnung  > ");
				titel = liesEingabe();
			liste = shop.sucheNachTitel(titel);
			gibArtikellisteAus(liste);
				break;*/
			case "r": //sortieren nach Name

				liste = shop.gibAlleArtikel();
				sortArtikellisteName(liste);
				break;
			case "n": // sortieren nach Nummer
				liste= shop.gibAlleArtikel();
				sortArtikellisteNummer(liste);
				break;
			case "b":
				if (user instanceof Arbeiter) {
				System.out.print("Artikelnummer  > ");
				nummer = liesEingabe();
				nr = Integer.parseInt(nummer);
				System.out.print("Menge  > ");
				try {
					menge = liesZahl();
				} catch (IOException e) {
					break;
				}

					try {
						shop.setMenge(nr, menge);
					} catch (ArtikelExistiertNichtException e) {
						System.err.println(e.getMessage());
						break;
					}
					System.out.println("Der Bestand wurde angepasst.");
				/*
				 * Exception zum laufen kriegen.
				 */
/*			try {
				shop.setMenge(nr, menge);
				System.out.println("Der Bestand wurde erh�ht.");
			} catch (ArtikelExistiertNichtException e) {
								// Hier Fehlerbehandlung...
				System.out.println("Fehler beim Bestand erh�hen.");
				e.printStackTrace();
			}
*/			}
				break;
/*			case "s":
				if (user instanceof Arbeiter) {
			*//*FileDialog fd = new FileDialog(new Frame(), "Speichern...");
			fd.setDirectory(System.getProperty("user.dir"));
			fd.setFile("*.txt");
			fd.setVisible(true);*//*
					shop.schreibeArtikel();
				}
				break;*/
			case "w": //Ausgabe des Warenkorbs
				if (user instanceof Kunde) {
					liste = shop.gibWarenkorb(((Kunde) eingeloggterBenutzer).getWarenkorb());
					for (Artikel art : liste) {
						System.out.println(art);
					}
				}
				break;
			case "l":
				if (user instanceof Kunde) {
					System.out.print("Artikelnummer  > ");
					nummer = liesEingabe();
					nr = Integer.parseInt(nummer);
					shop.removeFromCart(nr, ((Kunde) eingeloggterBenutzer).getWarenkorb());
					System.out.print(" \n Der Artikel wurde aus Ihrem Warenkorb entfernt. \n ");
				}
			case "c":
				if (user instanceof Kunde) {
					shop.clearCart();
					break;
				}
			case "k":
				if (user instanceof Kunde) {
					float betrag;
					List<Artikel> cart;
					betrag = shop.kaufeArtikel((Kunde) eingeloggterBenutzer);
					cart = shop.gibWarenkorb(((Kunde) eingeloggterBenutzer).getWarenkorb());
					if (betrag == 0) {
						System.out.println("Der Warenkorb ist leer.");
					} else {
						System.out.println(shop.rechnung(((Kunde) eingeloggterBenutzer), cart, (shop.getDateAsString()), betrag));
					}
					break;
				}
			case "i": // in Warenkorb
				if (user instanceof Kunde) {
					System.out.print("Artikelnummer > ");
					nummer = liesEingabe();
					nr = Integer.parseInt(nummer);
					System.out.print("Menge  > ");
					menge = liesZahl();
					if (eingeloggterBenutzer instanceof Kunde) {
						shop.addToCart(nr, menge, ((Kunde) eingeloggterBenutzer).getWarenkorb());
					}
					System.out.print(" \n Der Artikel wurde zu Ihrem Warenkorb hinzugefuegt. \n ");
					break;
				}
			case "v": //Warenkorb verändern
				if (user instanceof Kunde) {
					System.out.print("Artikelnummer > ");
					nummer = liesEingabe();
					nr = Integer.parseInt(nummer);
					System.out.print("Menge  > ");
					menge = liesZahl();
					try {
						shop.changeCart(nr, menge, ((Kunde) eingeloggterBenutzer).getWarenkorb());
					} catch (ArtikelExistiertNichtException e) {
						e.printStackTrace();
					}
					break;
				}
			case "j": // ausloggen
				System.out.print("Ausloggen...");
				eingeloggterBenutzer = null;
				break;
/*			default:
				System.out.println("");
				System.out.println("Ungueltige Eingabe.");
				System.out.println("");*/
		}
	}

	// Methode zum Artikel nach Namen sortieren
	private void sortArtikellisteName(List<Artikel> liste) {
		ArrayList<String> Artikelnames = new ArrayList<String>();
		Iterator<Artikel> it = liste.iterator();
		while (it.hasNext()) {
			Artikel aktArtikel = it.next();
			Artikelnames.add("Bezeichnung: "+aktArtikel.getTitel()+"	Nr:"+aktArtikel.getNummer()+"	/Bestand: "+aktArtikel.getBestand()+" /Preis: "+aktArtikel.getPreis() +" Euro");
		}
		Collections.sort(Artikelnames);
		for (String index:Artikelnames) {
			System.out.println(index);
		}
	}

	// Methode zum Artikel nach Nummer sortieren
	private void sortArtikellisteNummer(List<Artikel> liste) {
		ArrayList<String> Artikelnummern = new ArrayList<String>();
		Iterator<Artikel> it = liste.iterator();
		while (it.hasNext()) {
			Artikel aktArtikel = it.next();
			Artikelnummern.add("Nr: "+aktArtikel.getNummer()+" /Bezeichnung: "+aktArtikel.getTitel()+"	/Bestand: "+aktArtikel.getBestand()+" /Preis: "+aktArtikel.getPreis() +" Euro" );
		}
		Collections.sort(Artikelnummern);
		for (String index:Artikelnummern) {
			System.out.println(index);
		}
	}
	/* (non-Javadoc)
	 * 
	 * Interne (private) Methode zum Ausgeben von Artikellisten.
	 *
	 */
	private void gibArtikellisteAus(List<Artikel> liste) {
		if (liste != null) {
			if (liste.isEmpty()) {
				System.out.println("Liste ist leer.");
			} else {
				for (Artikel artikel: liste) {
					System.out.println(artikel);
				}
			}
		}
	}

	/*public User login(String name, String passwort){
		String input = "";
		try {
			input = liesEingabe();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	} */
	/**
	 * Methode zur Ausführung der Hauptschleife:
	 * - Menü ausgeben, vorher Login zur Identifikation des Usertypen
	 */
	public void run() {
		// Variable für Eingaben von der Konsole
		String input = "";
//        Login login = new Login();
//        login.run();
//		String usertyp = login.getUsertyp();
		do {
			try {
				System.out.print("Name > ");
				String name = liesEingabe();
				System.out.print("Passwort > ");
				String passwort = liesEingabe();
				eingeloggterBenutzer = shop.einloggen(name, passwort);
			} catch (IOException e) {
				e.printStackTrace();

			} catch (BenutzernameOderPasswortFalschException e) {
				e.printStackTrace();
			}
		} while (eingeloggterBenutzer == null);
		if (eingeloggterBenutzer != null) {
			System.out.println("Hallo, Herr/Frau " + eingeloggterBenutzer.getName());
			System.out.println("Dein Usertyp: " + eingeloggterBenutzer.getUsertyp());
			// Hauptschleife der Benutzungsschnittstelle
			do {
				gibMenueAus(eingeloggterBenutzer);
				try {
					input = liesEingabe();
					verarbeiteEingabe(input, eingeloggterBenutzer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (!input.equals("q")&&!input.equals("j"));
			try {
				shop.schreibeDaten();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (input.equals("j")) {
				System.out.println("");
				run();
			}
		}
	}

	/**
	 * Die main-Methode...
	 */
	public static void main(String[] args) {
		EShopClientCUI cui;
		try {
			cui = new EShopClientCUI("EShop");
			cui.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
