package shop.local.domain.exceptions;

import shop.local.valueobjects.Artikel;


public class ArtikelExistiertBereitsException extends Exception {

	private Artikel exArtikel;

	public ArtikelExistiertBereitsException(Artikel artikel)/*+ , String zusatzMsg */{
		super("Artikel mit der Nummer " + artikel.getNummer() + " existiert bereits."); /* + zusatzMsg*/

		this.exArtikel = artikel;
	}

	public Artikel getExArtikel() {
		return exArtikel;
	}
}
