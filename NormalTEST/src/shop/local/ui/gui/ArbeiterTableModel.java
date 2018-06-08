package shop.local.ui.gui;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import shop.local.valueobjects.Arbeiter;

public class ArbeiterTableModel extends AbstractTableModel{
    private List<Arbeiter> admin;
    private String[] spaltenNamen = { "Name", "Nummer"};

    public ArbeiterTableModel(List<Arbeiter> aktuelleArbeiter) {
        super();

        admin = new Vector<Arbeiter>();
        admin.addAll(aktuelleArbeiter);
    }

    public void setArtikel(List<Arbeiter> aktuelleArbeiter) {
        admin.clear();
        admin.addAll(aktuelleArbeiter);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return admin.size();
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
        Arbeiter chosenArbeiter = admin.get(row);
        switch (col) {
            case 0:
                return chosenArbeiter.getName();
            case 1:
                return chosenArbeiter.getNummer();
            default:
                return null;
        }
    }
}
