package shop.local.valueobjects;

public class Einzelartikel extends Artikel {

    private int psize;

    public Einzelartikel(int artNummer, String name, int bestand, float preis) {
        super(name, artNummer, bestand, preis);
        this.psize = 1;
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
