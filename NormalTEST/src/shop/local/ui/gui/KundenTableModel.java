package shop.local.ui.gui;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import shop.local.valueobjects.Kunde;

public class KundenTableModel extends AbstractTableModel{
    private List<Kunde> customers;
    private String[] spaltenNamen = { "Name", "Nummer", "Postleitzahl", "Ort", "Strasse", "Land" };

    public KundenTableModel(List<Kunde> aktuelleKunden) {
        super();

        customers = new Vector<Kunde>();
        customers.addAll(aktuelleKunden);
    }

    public void setArtikel(List<Kunde> aktuelleKunden) {
        customers.clear();
        customers.addAll(aktuelleKunden);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return customers.size();
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
        Kunde chosenKunde = customers.get(row);
        switch (col) {
            case 0:
                return chosenKunde.getName();
            case 1:
                return chosenKunde.getNummer();
            case 2:
                return chosenKunde.getPlz();
            case 3:
                return chosenKunde.getWohnort();
            case 4:
                return chosenKunde.getStrasse();
            case 5:
                return chosenKunde.getLand();
            default:
                return null;
        }
    }
}
