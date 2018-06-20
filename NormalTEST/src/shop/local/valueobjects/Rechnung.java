package shop.local.valueobjects;

import shop.local.valueobjects.Cart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rechnung {
    private Kunde kunde;
    private ArrayList<Artikel> artikel;
    private List<CartEntry> cart;
    private Date date;

    public Rechnung(Kunde k, Cart cart) {
        this.kunde = k;
        this.date = new Date();
        this.cart = cart.getArtikel();
    }

    public String getMessage() {
        return ("Sie, Herr/Frau " + kunde.getName() + "haben am " + date + "folgende Waren im Gesamtwert von " + getBetrag() + " Euro gekauft:");
    }

    public List<CartEntry> getCart() {
        return cart;
    }

    public Date getDate() {
        return date;
    }

    public float getBetrag() {
        float betrag = 0;
        for (CartEntry position: cart) {
            Artikel artikel = position.getArtikel();
            int anzahl = position.getAnzahl();
            betrag += anzahl * artikel.getPreis();
        }
        return betrag;
    }
}