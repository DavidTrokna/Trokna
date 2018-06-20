package shop.local.valueobjects;

public class Massengutartikel extends Artikel {

    private int psize;

    public Massengutartikel(int artNummer, String name, int bestand, float preis, int psize ) {
        super(name, artNummer, bestand, preis);
        this.psize = psize;
    }

    public int getPsize() {
        return psize;
    }

    public void setPsize (int psize) {
        this.psize = psize;
    }

//    @Override
//    public int compareTo(Artikel o) {
//        Integer nummer = this.artNummer;
//        return nummer.compareTo(o.getBestand());
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof  Artikel) {
//            Artikel andererArtikel = (Artikel) obj;
//            return this.artNummer == andererArtikel.getNummer();
//        }
//        return false;
//    }

}
