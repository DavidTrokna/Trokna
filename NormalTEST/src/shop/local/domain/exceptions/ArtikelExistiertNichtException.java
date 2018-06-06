package shop.local.domain.exceptions;


import shop.local.valueobjects.Artikel;

public class ArtikelExistiertNichtException extends Exception{

	public ArtikelExistiertNichtException(Artikel art)/*+ , String zusatzMsg */{
		super("Artikel " + art.getTitel() + " existiert nicht." ); /* + zusatzMsg*/
	}

	public ArtikelExistiertNichtException(int artNummer)/*+ , String zusatzMsg */{
		super("Artikel mit Nummer " + artNummer + " existiert nicht." ); /* + zusatzMsg*/
	}

}
