package shop.local.valueobjects;

public class Einzelartikel extends Artikel {

    private String bezeichnung;
    private int artNummer;
    private int bestand;
    private float preis;
    private int psize;

    public Einzelartikel(int artNummer, String name, int bestand, float preis, int psize ) {
        super(name, artNummer, bestand, preis);
        this.artNummer = artNummer;
        this.bezeichnung = name;
        this.bestand = bestand;
        this.preis = preis;
        this.psize = 1;
    }


    public int getArtNummer() {
        return artNummer;
    }

    public void setArtNummer(int artNummer) {
        this.artNummer = artNummer;
    }

    public String getName() {
        return bezeichnung;
    }

    public void setName(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
    }

    public float getPreis() {
        return preis;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public int getPsize() {
        return psize;
    }

    public void setPsize (int psize) {
        this.psize = psize;
    }

    public void verringereBestand(int menge) {
        bestand -= menge;
    }

    @Override
    public int compareTo(Artikel o) {
        Integer nummer = this.artNummer;
        return nummer.compareTo(o.getBestand());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Artikel) {
            Artikel andererArtikel = (Artikel) obj;
            return this.artNummer == andererArtikel.getNummer();
        }
        return false;
    }


}
