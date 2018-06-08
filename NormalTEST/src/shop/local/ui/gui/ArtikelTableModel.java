package shop.local.ui.gui;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import shop.local.valueobjects.Artikel;

public class ArtikelTableModel extends AbstractTableModel {
     private List<Artikel> artikel;
     private String[] spaltenNamen = { "Nummer", "Bezeichnung", "Menge", "Preis"};

     public ArtikelTableModel(List<Artikel> aktuelleArtikel) {
         super();

         artikel = new Vector<Artikel>();
         artikel.addAll(aktuelleArtikel);
     }

     public void setArtikel(List<Artikel> aktuelleArtikel) {
         artikel.clear();
         artikel.addAll(aktuelleArtikel);
         fireTableDataChanged();
     }

     @Override
    public int getRowCount() {
         return artikel.size();
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
         Artikel chosenArtikel = artikel.get(row);
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