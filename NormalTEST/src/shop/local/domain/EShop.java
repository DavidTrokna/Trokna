package shop.local.domain;

import java.io.IOException;
import java.util.List;
import java.util.Date;
import java.text.*;

import shop.local.domain.exceptions.*;
import shop.local.valueobjects.*;


public class EShop {
    // Präfix für Namen der Dateien, in der die Bibliotheksdaten gespeichert sind
    private String datei = "";

    private ArtikelVerwaltung artikelVw;
    private BenutzerVerwaltung benutzerVw;
    private ShoppingServices shoppingS;

    public EShop(String datei) throws IOException {
        this.datei = datei;

        // Artikelbestand und Userbestand aus Dateien einlesen
        benutzerVw = new BenutzerVerwaltung();
        artikelVw = new ArtikelVerwaltung();
        //artikelVw.liesHistorie(datei + "_Historie.txt");
        artikelVw.liesArtikel(datei + "_A.txt");
        benutzerVw.liesKunde(datei + "_Kunden.txt");
        benutzerVw.liesArbeiter(datei + "_Arbeiter.txt");

        shoppingS = new ShoppingServices(artikelVw);
    }

    public String getDateAsString() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(new Date());
    }

    //Ausgabe aller Artikel über ArtikelVW
    public List<Artikel> gibAlleArtikel() {
        return artikelVw.getArtikelBestand();
    }

    public List<Arbeiter> gibAlleArbeiter() {
        return benutzerVw.getArbeiterListe();
    }

    public List<Kunde> gibAlleKunden() {
        return benutzerVw.getKundenListe();
    }

    //neuen Artikel einfügen
    public void fuegeArtikelEin(String bezeichnung, int nummer, int menge, float preis) throws ArtikelExistiertBereitsException {
        Artikel einArtikel = new Artikel (bezeichnung, nummer, menge, preis);
        artikelVw.einfuegen(einArtikel);
    }

    //löschen eines Artikels aus dem Bestand
    public void loescheArtikel(int artNummer) throws ArtikelExistiertNichtException {
        Artikel art = artikelVw.gibArtikel(artNummer);
        if (art != null) {
            artikelVw.loeschen(art);
        } else {
            throw new ArtikelExistiertNichtException(art);
        }
    }

    // Methode um die Menge eines Artikels anzupassen
    public void setMenge(int artNummer, int menge) throws ArtikelExistiertNichtException {
        Artikel artikel = artikelVw.gibArtikel(artNummer);
        artikelVw.verändereBestand(artikel, menge);
    }

    //speichern der Artikel und User in Textdateien
    public void schreibeDaten() throws IOException {
        artikelVw.schreibeHistorie(datei + "_Historie.txt");
        artikelVw.schreibeArtikel(datei + "_A.txt");
        benutzerVw.schreibeArbeiter(datei + "_Arbeiter.txt");
        benutzerVw.schreibeKunde(datei + "_Kunden.txt");
    }

    // Gibt den Inhalt des Warenkorbs aus
    public List<CartEntry> gibWarenkorb(Cart cart) {
        return shoppingS.getCartContent(cart);
    }

    // Entfernt einen Artikel aus dem Warenkorb
    public void removeFromCart(int nr, Cart cart) {
        shoppingS.cartRemove(nr, cart);
    }

    // Leert den Warenkorb
    public void clearCart() {
//        shoppingS.clearCart();
    }

    // Fügt einen Artikel dem Warenkorb hinzu
    public void addToCart(int artNummer, int artMenge, Cart cart) throws ArtikelExistiertNichtException {
                shoppingS.inCart(artNummer, artMenge, cart);
    }

    // "Kauft" alle Artikel im Warenkorb
    public Rechnung kaufeArtikel(Kunde k) {
        return shoppingS.buyCart(k);
    }

    // Methode zum einloggen des Users
    public User einloggen(String name, String passwort) throws BenutzernameOderPasswortFalschException {
        return benutzerVw.einloggen(name, passwort);
    }

    // Methode zum erstellen von Arbeitern
    public void newA (String name, String passwort, String num) throws ArbeiterExistiertBereitsException{
        benutzerVw.einfuegenA(name, passwort, num);
    }

    // Methode zum erstellen von Kunden
    public void newK (String name, String passwort, String num, String plz, String ort, String strasse, String land) throws KundeExistiertBereitsException {
        benutzerVw.einfuegenK(name, passwort, num, plz, ort, strasse, land);
    }

    public void einlagerung(User eingeloggterBenutzer, int nr, int bestand) {
        artikelVw.artEinlagern(eingeloggterBenutzer, nr, bestand);
    }

    public void changeCart(int nr, int menge, Cart warenkorb) throws ArtikelExistiertNichtException {
        shoppingS.cartChange(nr, menge, warenkorb);
    }

    /*public Rechnung rechnung(Kunde kunde, List<Artikel> cart, String date, float betrag) {
        return shoppingS.erstelleRechnung(kunde, cart, date, betrag);
    }*/

    public List<Artikel> sucheNachBezeichnung(String bezeichnung) {
        return artikelVw.sucheArtikel(bezeichnung);
    }
}