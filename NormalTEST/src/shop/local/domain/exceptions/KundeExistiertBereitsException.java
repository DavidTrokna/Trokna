package shop.local.domain.exceptions;

import shop.local.valueobjects.Kunde;

public class KundeExistiertBereitsException extends Exception {
    private Kunde nKunde;

    public KundeExistiertBereitsException(Kunde kunde)/*+ , String zusatzMsg */ {
        super("Ein Kunde mit der Nummer " + kunde.getNummer() + " existiert bereits."); /* + zusatzMsg*/

        this.nKunde = kunde;
    }
}
