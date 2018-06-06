package shop.local.domain;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Cart;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Rechnung;

import java.util.List;
import java.util.Vector;

public class ShoppingServices {

    private ArtikelVerwaltung artikelVw;
    private List<Artikel> artikelBestand = new Vector<Artikel>();

    public ShoppingServices(ArtikelVerwaltung artikelVw) {
        this.artikelVw = artikelVw;
    }

    public List<Artikel> getCartContent(Cart cart) {
        return cart.getArtikel();
    }

    public void inCart(int artNummer, int menge, Cart cart) {
        List<Artikel> artikelBestand = artikelVw.getArtikelBestand();
        for (Artikel art : artikelBestand) {
            if (art.getNummer() == artNummer) {
                //art.verringereBestand(menge); /nur wenn Artikel später auch rückgeführt werden
                cart.add(art, menge);
            }
        }

    }

    public float buyCart(Kunde k) {

        List<Artikel> artikelBestand = artikelVw.getArtikelBestand();
        float betrag = 0;
        int artNummer;
//        boolean leerCart = false;
        Cart cart = k.getWarenkorb();

        for (Artikel cartart : cart.getArtikel()) {
            betrag += cartart.getPreis() * cartart.getBestand();
            artNummer = cartart.getNummer();
//            leerCart = true;
            for (Artikel art : artikelBestand) {
                if (art.getNummer() == artNummer) {
                    artikelVw.verringereBestand(art, cartart.getBestand());
                    break;
                }
            }

        }

        // Wenn erfolgreich:
        cart.clear();

        return betrag;
    }

    public void cartRemove (int nr, Cart cart) {
        Artikel a = null;
        for (Artikel cartart : cart.getArtikel()) {
            if (cartart.getNummer() == nr) {
                a = cartart;
            }
        }
        cart.remove(a);
    }

    public void cartChange(int nr, int menge, Cart warenkorb) {
        Artikel a = null;
        for (Artikel cartart : warenkorb.getArtikel()) {
            if (cartart.getNummer() == nr) {
                a = cartart;
            }
        }
        a.setBestand(menge);
    }

    public Rechnung erstelleRechnung(Kunde kunde, List<Artikel> cart, String date, float betrag) {
        Rechnung re = new Rechnung(kunde, cart, date, betrag);
        return re;
    }
}