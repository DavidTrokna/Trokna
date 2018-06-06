package shop.local.domain.exceptions;


import shop.local.valueobjects.Artikel;

public class BenutzernameOderPasswortFalschException extends Exception {

    public BenutzernameOderPasswortFalschException() {
        super("Der Benutzername oder das Password ist Falsch." );
    }
}