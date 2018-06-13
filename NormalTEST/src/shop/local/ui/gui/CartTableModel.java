package shop.local.ui.gui;

import shop.local.valueobjects.Artikel;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class CartTableModel extends AbstractTableModel {
    private List<Artikel> cart;
    private String[] spaltenNamen = { "Nummer", "Bezeichnung", "Menge", "Preis"};

    public CartTableModel(List<Artikel> aktuelleArtikel) {
        super();

        cart = new Vector<Artikel>();
        cart.addAll(aktuelleArtikel);
    }

    public void setArtikel(List<Artikel> aktuelleArtikel) {
        cart.clear();
        cart.addAll(aktuelleArtikel);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return cart.size();
    }

    @Override
    public int getColumnCount() {
        return spaltenNamen.length;
    }

    @Override
    public String getColumnName(int col) {
        return spaltenNamen[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Artikel chosenArtikel = cart.get(row);
        switch (col) {
            case 0:
                return chosenArtikel.getNummer();
            case 1:
                return chosenArtikel.getBezeichnung();
            case 2:
                return chosenArtikel.getBestand();
            case 3:
                return chosenArtikel.getPreis();
            default:
                return null;
        }
    }
}
