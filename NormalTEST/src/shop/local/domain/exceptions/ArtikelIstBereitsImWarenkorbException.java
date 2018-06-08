package shop.local.domain.exceptions;


import shop.local.valueobjects.Artikel;

public class ArtikelIstBereitsImWarenkorbException extends Exception {


    public ArtikelIstBereitsImWarenkorbException(Artikel artikel) {
        super("Der Artikel " + artikel.getBezeichnung() + " / " + artikel.getNummer() + " ist bereits im Warenkorb");
    }

}
