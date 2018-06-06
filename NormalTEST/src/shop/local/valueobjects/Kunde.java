package shop.local.valueobjects;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Kunde extends User {

	private String plz;

	private String strasse;

	private String wohnort;

	private String land;

	private Cart cart = new Cart();

		//Konstruktor des Kunden,
    public Kunde(String name, String passwort, String nr, String plz,  String wohnort ,String strasse, String land, String usertype ) {
    	super(name, passwort, nr, "k");

		this.plz = plz;
		this.strasse = strasse;
		this.wohnort = wohnort;
		this.land = land;

//		this.cart = new Warenkorb();
	}
    
	// Methoden zum Setzen und Lesen der Kunden-Eigenschaften

	public Cart getWarenkorb(){
		return cart;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getWohnort() {
		return wohnort;
	}

	public void setWohnort(String wohnort) {
    	this.wohnort = wohnort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
    	this.land = land;
	}

	// Weitere Dienste der Kunden-Objekte
}
