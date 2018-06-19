package shop.local.valueobjects;

import java.io.IOException;
import java.util.*;
import shop.local.valueobjects.Artikel;


public class Cart {

//    private Map<Artikel, Integer> wkInhalt = new HashMap<>();

    private ArrayList<Artikel> artikel;
    private ArrayList<CartEntry> entries;

    public Cart(){
        artikel = new ArrayList<>();
        entries = new ArrayList<>();
    }

    public void add(Artikel artikel, int anzahl) {
        entries.add(new CartEntry(artikel, anzahl));
    }

    public ArrayList<CartEntry> getArtikel() {
        return entries;
    }

    public void clear() {
        this.entries.clear();
    }

    public void remove(CartEntry cartart) {
        entries.remove(cartart);
    }
}
