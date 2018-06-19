package shop.local.ui.gui;

import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.CartEntry;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class CartTableModel extends AbstractTableModel {
    private List<CartEntry> cart;
    private String[] spaltenNamen = { "Nummer", "Bezeichnung", "Menge", "Preis"};

    public CartTableModel(List<CartEntry> aktuelleArtikel) {
        super();

        cart = new Vector<CartEntry>();
        cart.addAll(aktuelleArtikel);
    }

    public void setArtikel(List<CartEntry> aktuelleArtikel) {
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
        CartEntry chosenArtikel = cart.get(row);
        switch (col) {
            case 0:
                return chosenArtikel.getNummer();
            case 1:
                return chosenArtikel.getBezeichnung();
            case 2:
                return chosenArtikel.getAnzahl();
            case 3:
                return chosenArtikel.getPreis();
            default:
                return null;
        }
    }
}
