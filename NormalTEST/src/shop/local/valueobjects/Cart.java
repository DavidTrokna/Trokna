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

    public ArrayList<Artikel> getArtikel() {
        return artikel;
    }

    public void clear() {
        this.artikel.clear();
    }

    public void remove(Artikel cartart) {
        artikel.remove(cartart);
    }
}
