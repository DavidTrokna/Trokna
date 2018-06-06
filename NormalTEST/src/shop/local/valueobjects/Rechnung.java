package shop.local.valueobjects;

import shop.local.valueobjects.Cart;

import java.util.List;

public class Rechnung {
    private Kunde k;
    private List<Artikel> cart;
    private String date;
    private float betrag;

    public Rechnung(Kunde k, List<Artikel> cart, String date, float betrag) {
        this.k = k;
        this.cart = cart;
        this.date = date;
        this.betrag = betrag;
    }
    public String bon() {
        return ("Sie, Herr/Frau " + k.getName() + "haben am " + date + "folgende Waren im Gesamtwert von " + betrag + " Euro gekauft: ");
/*        for (Artikel cartart : cart) {
            System.out.println(cartart);
        }*/
    }
}