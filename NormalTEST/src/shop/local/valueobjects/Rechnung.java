package shop.local.valueobjects;

import shop.local.valueobjects.Cart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rechnung {
    private Kunde kunde;
    private ArrayList<Artikel> artikel;
    private ArrayList<CartEntry> cart;
    private Date date;
    private float betrag;

    public Rechnung(Kunde k, Cart cart) {
        this.kunde = kunde;
        //this.artikel = cart.getArtikel();
        this.date = new Date();
        this.betrag = betrag;
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



//    public String bon() {
//        return ("Sie, Herr/Frau " + kunde.getName() + "haben am " + date + "folgende Waren im Gesamtwert von " + betrag + " Euro gekauft: ");
/*        for (Artikel cartart : cart) {
            System.out.println(cartart);
        }*/
    }
}