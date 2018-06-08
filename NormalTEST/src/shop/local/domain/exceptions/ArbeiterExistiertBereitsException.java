package shop.local.domain.exceptions;

import shop.local.valueobjects.Arbeiter;

public class ArbeiterExistiertBereitsException extends Exception {
    private Arbeiter nArbeiter;

    public ArbeiterExistiertBereitsException(Arbeiter arbeiter)/*+ , String zusatzMsg */ {
        super("Ein Arbeiter mit der Nummer " + arbeiter.getNummer() + " existiert bereits."); /* + zusatzMsg*/

        this.nArbeiter = arbeiter;
    }
}
