package shop.local.valueobjects;

public class CartEntry {

    private Artikel artikel;
    private int anzahl;

    public CartEntry(Artikel artikel, int anzahl) {
        this.artikel = artikel;
        this.anzahl = anzahl;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) { this.artikel = artikel; }

    public String getBezeichnung() {
        return artikel.getBezeichnung();
    }

    public int getNummer() {
        return artikel.getNummer();
    }

    public float getPreis() {
        return artikel.getPreis();
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    //public void setCartAnzahl() { this.anzahl = 0; }

}
