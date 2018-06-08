package shop.local.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;

import shop.local.domain.EShop;
import shop.local.domain.exceptions.ArtikelExistiertBereitsException;
import shop.local.domain.exceptions.ArtikelExistiertNichtException;
import shop.local.domain.exceptions.BenutzernameOderPasswortFalschException;
import shop.local.domain.exceptions.KundeExistiertBereitsException;
import shop.local.valueobjects.Arbeiter;
import shop.local.valueobjects.Artikel;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.User;

public class EShopGUI extends JFrame {
    private EShop shop;
    private JButton addButton;
    private JButton inButton;
    private JButton buyButton;
    private JTextField numTextFeld;
    private JTextField bezeichnungTextFeld;
    private JTextField nameTextFeld;
    private JTextField searchTextField;
    private JTextField loginNameTextField;
    private JTextField loginPasswordTextField;
    private JTextField amountTextFeld;
    private JTextField pwTextFeld;
    private JScrollPane artikelScrollPane;
    private JScrollPane arbeiterScrollPane;
    private JScrollPane kundenScrollPane;
    private JList artikelListe;
    private JTable artikelTabelle;
    private JTable arbeiterTabelle;
    private JTable kundenTabelle;
    private JTextField plzTextFeld;
    private JTextField prizeTextFeld;
    private JTextField ortTextFeld;
    private JTextField strTextFeld;
    private JTextField landTextFeld;

    private User eingeloggterBenutzer = null;

    public EShopGUI(String titel) {
        super(titel);

        try {
            shop = new EShop("EShop");
            initializeLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void initializeTest () {
        JTabbedPane adminTabbedPane = new JTabbedPane();

        JPanel suchPanel = new JPanel();
        suchPanel.setBorder(BorderFactory.createTitledBorder("Suche"));

        GridBagLayout gridBagLayout = new GridBagLayout();
        suchPanel.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;

        JLabel searchLabel = new JLabel("Suchbegriff:");
        c.gridx = 0;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(searchLabel, c);
        suchPanel.add(searchLabel);

        searchTextField = new JTextField();
        searchTextField.setToolTipText("Wonach soll gesucht werden?");
        c.gridx = 1;
        c.weightx = 0.6;
        gridBagLayout.setConstraints(searchTextField, c);
        suchPanel.add(searchTextField);

        JButton searchButton = new JButton("Los!");
        searchButton.addActionListener(new SuchActionListener());
        c.gridx = 2;
        c.weightx = 0.2;
        gridBagLayout.setConstraints(searchButton, c);
        suchPanel.add(searchButton);

        adminTabbedPane.addTab("Suchen", suchPanel);
        adminTabbedPane.setMnemonicAt(0, KeyEvent.VK_S);

        this.setLayout(new BorderLayout());
        this.add(adminTabbedPane, BorderLayout.CENTER);

        this.setSize(1280, 720);
        this.setVisible(true);
    }*/

    private void initializeLogin() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createTitledBorder("LOGIN"));

        JPanel loginButtonPanel = new JPanel();

        GridBagLayout gridBagLayout = new GridBagLayout();
        loginPanel.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel nameLabel = new JLabel("Name:");
        c.gridx = 0;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(nameLabel, c);
        loginPanel.add(nameLabel);

        loginNameTextField = new JTextField();
        c.gridx = 1;
        c.weightx = 0.6;
        gridBagLayout.setConstraints(loginNameTextField, c);
        loginPanel.add(loginNameTextField);

        JLabel passwordLabel = new JLabel("Passwort:");
        c.gridx = 0;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(passwordLabel, c);
        loginPanel.add(passwordLabel);

        loginPasswordTextField = new JTextField();
        c.gridx = 1;
        c.weightx = 0.6;
        gridBagLayout.setConstraints(loginPasswordTextField, c);
        loginPanel.add(loginPasswordTextField);

        JButton loginButton = new JButton("Anmelden");
        loginButton.addActionListener(new LoginActionListener());
        c.gridx = 2;
        c.weightx = 0.2;
        c.anchor = GridBagConstraints.SOUTH;
        gridBagLayout.setConstraints(loginButton, c);
        loginButtonPanel.add(loginButton);

        this.setLayout(new BorderLayout());
        this.add(loginPanel, BorderLayout.CENTER);
        this.add(loginButtonPanel, BorderLayout.SOUTH);

        this.setSize(1280, 720);
        this.setVisible(true);
    }

    private void initializeCustomer() {
        JMenuBar mBar = new JMenuBar();

        JMenu fileMenu = new FileMenu();
        mBar.add(fileMenu);

        JMenu helpMenu = new HelpMenu();
        mBar.add(helpMenu);

        this.setJMenuBar(mBar);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel suchPanel = new JPanel();
        suchPanel.setBorder(BorderFactory.createTitledBorder("Suche"));

        GridBagLayout gridBagLayout = new GridBagLayout();
        suchPanel.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;

        JLabel searchLabel = new JLabel("Suchbegriff:");
        c.gridx = 0;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(searchLabel, c);
        suchPanel.add(searchLabel);

        searchTextField = new JTextField();
        searchTextField.setToolTipText("Wonach soll gesucht werden?");
        c.gridx = 1;
        c.weightx = 0.6;
        gridBagLayout.setConstraints(searchTextField, c);
        suchPanel.add(searchTextField);

        JButton searchButton = new JButton("Los!");
        searchButton.addActionListener(new SuchActionListener());
        c.gridx = 2;
        c.weightx = 0.2;
        gridBagLayout.setConstraints(searchButton, c);
        suchPanel.add(searchButton);

        int noOfRows = 12;
        JPanel insertPanel = new JPanel();
        insertPanel.setBorder(BorderFactory.createTitledBorder("Warenkorb"));
        insertPanel.setLayout(new GridLayout(noOfRows, 1));
        insertPanel.add(new JLabel("Nummer"));
        numTextFeld = new JTextField();
        insertPanel.add(numTextFeld);
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel("Menge"));
        amountTextFeld = new JTextField();
        insertPanel.add(amountTextFeld);

        //Platzhalter
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel());

        inButton = new JButton("In Warenkorb");
        inButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer nummer = Integer.parseInt(numTextFeld.getText());
                Integer amount = Integer.parseInt(amountTextFeld.getText());
                if (!(nummer == null) && !(amount == null)) {
                    try {
                        shop.addToCart(nummer, amount, ((Kunde) eingeloggterBenutzer).getWarenkorb());
                        numTextFeld.setText("");
                        amountTextFeld.setText("");
                    } catch (ArtikelExistiertNichtException aene) {
                        JOptionPane.showMessageDialog(rootPane, aene.getMessage());
                    }
                    catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(rootPane, nfe.getMessage());
                    }
                }
            }
        });
        insertPanel.add(new JLabel());
        insertPanel.add(inButton);

        buyButton = new JButton("Kaufen");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop.kaufeArtikel((Kunde) eingeloggterBenutzer);
            }
        });
        insertPanel.add(new JLabel());
        insertPanel.add(buyButton);

        // Aus warenkorb entfernen, Warenkorb leeren, warenkorb anzeigen, warenkorb verändern

        java.util.List<Artikel> artikel = shop.gibAlleArtikel();
        ArtikelTableModel tModel = new ArtikelTableModel(artikel);
        artikelTabelle = new JTable(tModel);
        artikelScrollPane = new JScrollPane(artikelTabelle);

        this.getContentPane().removeAll();

        this.setLayout(new BorderLayout());
        this.add(suchPanel, BorderLayout.SOUTH);
        this.add(insertPanel, BorderLayout.EAST);
        this.add(artikelScrollPane, BorderLayout.CENTER);

        this.setSize(1280, 720);
        this.setVisible(true);
    }

    private void initializeAdmin() {
        JMenuBar mBar = new JMenuBar();

        JMenu fileMenu = new FileMenu();
        mBar.add(fileMenu);

        JMenu helpMenu = new HelpMenu();
        mBar.add(helpMenu);

        this.setJMenuBar(mBar);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //new TabbedPane
        JTabbedPane insertTabbedPane = new JTabbedPane();
        JTabbedPane contentTabbedPane = new JTabbedPane();

        JPanel suchPanel = new JPanel();
        suchPanel.setBorder(BorderFactory.createTitledBorder("Suche"));

        GridBagLayout gridBagLayout = new GridBagLayout();
        suchPanel.setLayout(gridBagLayout);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;

        JLabel searchLabel = new JLabel("Suchbegriff:");
        c.gridx = 0;
        c.weightx = 0.1;
        c.anchor = GridBagConstraints.EAST;
        gridBagLayout.setConstraints(searchLabel, c);
        suchPanel.add(searchLabel);

        searchTextField = new JTextField();
        searchTextField.setToolTipText("Wonach soll gesucht werden?");
        c.gridx = 1;
        c.weightx = 0.6;
        gridBagLayout.setConstraints(searchTextField, c);
        suchPanel.add(searchTextField);

        JButton searchButton = new JButton("Los!");
        searchButton.addActionListener(new SuchActionListener());
        c.gridx = 2;
        c.weightx = 0.2;
        gridBagLayout.setConstraints(searchButton, c);
        suchPanel.add(searchButton);

        int noOfRows = 12;
        JPanel insertPanel = new JPanel();
        insertPanel.setBorder(BorderFactory.createTitledBorder("Artikel einfügen"));
        insertPanel.setLayout(new GridLayout(noOfRows, 1));
        insertPanel.add(new JLabel("Nummer"));
        numTextFeld = new JTextField();
        insertPanel.add(numTextFeld);
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel("Bezeichnung"));
        bezeichnungTextFeld = new JTextField();
        insertPanel.add(bezeichnungTextFeld);
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel("Menge"));
        amountTextFeld = new JTextField();
        insertPanel.add(amountTextFeld);
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel("Preis in Euro"));
        prizeTextFeld = new JTextField();
        insertPanel.add(prizeTextFeld);

        //Platzhalter
        insertPanel.add(new JLabel());
        insertPanel.add(new JLabel());

        addButton = new JButton("Einfügen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bezeichnung = bezeichnungTextFeld.getText();
                Integer nummer = Integer.parseInt(numTextFeld.getText());
                Integer amount = Integer.parseInt(amountTextFeld.getText());
                Float prize = Float.parseFloat(prizeTextFeld.getText());
                if (!bezeichnung.isEmpty() && !(nummer == null) && !(amount == null) && !(prize == null)) {
                    try {
                        shop.fuegeArtikelEin(bezeichnung, nummer, amount, prize);
                        bezeichnungTextFeld.setText("");
                        numTextFeld.setText("");
                        amountTextFeld.setText("");
                        prizeTextFeld.setText("");
                    } catch (ArtikelExistiertBereitsException aebe) {
                        JOptionPane.showMessageDialog(rootPane, aebe.getMessage());
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(rootPane, nfe.getMessage());
                    }
                }
            }
        });
        insertPanel.add(new JLabel());
        insertPanel.add(addButton);

        //Nutzer anlegen und Bestand ändern

        java.util.List<Artikel> artikel = shop.gibAlleArtikel();
        ArtikelTableModel tModel = new ArtikelTableModel(artikel);
        artikelTabelle = new JTable(tModel);
        artikelScrollPane = new JScrollPane(artikelTabelle);

        //Tab für User
        int numOfRows = 16;
        JPanel userPanel = new JPanel();
        userPanel.setBorder(BorderFactory.createTitledBorder("User anlegen"));
        userPanel.setLayout(new GridLayout(numOfRows, 1));
        userPanel.add(new JLabel("Name"));
        nameTextFeld = new JTextField();
        userPanel.add(nameTextFeld);
        userPanel.add(new JLabel());
        userPanel.add(new JLabel());
        userPanel.add(new JLabel("Passwort"));
        pwTextFeld = new JTextField();
        userPanel.add(pwTextFeld);
        userPanel.add(new JLabel());
        userPanel.add(new JLabel());
        userPanel.add(new JLabel("Nummer"));
        numTextFeld = new JTextField();
        userPanel.add(numTextFeld);
        userPanel.add(new JLabel());
        userPanel.add(new JLabel());
        userPanel.add(new JLabel("Postleitzahl"));
        plzTextFeld = new JTextField();
        userPanel.add(plzTextFeld);
        userPanel.add(new JLabel());
        userPanel.add(new JLabel());
        userPanel.add(new JLabel("Ort"));
        ortTextFeld = new JTextField();
        userPanel.add(ortTextFeld);
        userPanel.add(new JLabel());
        userPanel.add(new JLabel());
        userPanel.add(new JLabel("Strasse"));
        strTextFeld = new JTextField();
        userPanel.add(strTextFeld);
        userPanel.add(new JLabel());
        userPanel.add(new JLabel());
        userPanel.add(new JLabel("Land"));
        landTextFeld = new JTextField();
        userPanel.add(landTextFeld);

        /*//Platzhalter
        userPanel.add(new JLabel());
        userPanel.add(new JLabel());*/

        addButton = new JButton("Anlegen");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextFeld.getText();
                String passwort = pwTextFeld.getText();
                String nummer = numTextFeld.getText();
                String plz = plzTextFeld.getText();
                String ort = ortTextFeld.getText();
                String strasse = strTextFeld.getText();
                String land = landTextFeld.getText();
                if (!name.isEmpty() && !passwort.isEmpty() && !nummer.isEmpty()) {
                    try {
                        shop.newK(name, passwort, nummer, plz, ort, strasse, land);
                        nameTextFeld.setText("");
                        pwTextFeld.setText("");
                        numTextFeld.setText("");
                        numTextFeld.setText("");
                        numTextFeld.setText("");
                        numTextFeld.setText("");
                        numTextFeld.setText("");

                        //TODO: Irgendwie noch entscheiden, ob ein Kunde oder eine Arbeiter angelegt werden muss
                    } catch (KundeExistiertBereitsException kebe) {
                        JOptionPane.showMessageDialog(rootPane, kebe.getMessage());
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(rootPane, nfe.getMessage());
                    }
                }
            }
        });
        userPanel.add(new JLabel());
        userPanel.add(addButton);

        java.util.List<Arbeiter> admins = shop.gibAlleArbeiter();
        ArbeiterTableModel aModel = new ArbeiterTableModel(admins);
        arbeiterTabelle = new JTable(aModel);
        arbeiterScrollPane = new JScrollPane(arbeiterTabelle);

        java.util.List<Kunde> customers = shop.gibAlleKunden();
        KundenTableModel kModel = new KundenTableModel (customers);
        kundenTabelle = new JTable(kModel);
        kundenScrollPane = new JScrollPane(kundenTabelle);

        // Komponenten aus vorigem Layout entfernen
        this.getContentPane().removeAll();

        insertTabbedPane.addTab("Artikel", insertPanel);
        insertTabbedPane.setMnemonicAt(0, KeyEvent.VK_A);
        insertTabbedPane.addTab("User", userPanel);
        insertTabbedPane.setMnemonicAt(1, KeyEvent.VK_U);

        contentTabbedPane.addTab("Artikel", artikelScrollPane);
        contentTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        contentTabbedPane.addTab("Arbeiter", arbeiterScrollPane);
        contentTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        contentTabbedPane.addTab("Kunden", kundenScrollPane);
        contentTabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        // Neue Komponenten zu neuem Layout hinzufügen
        this.setLayout(new BorderLayout());
        this.add(suchPanel, BorderLayout.SOUTH);
        this.add(insertTabbedPane, BorderLayout.EAST);
        this.add(contentTabbedPane, BorderLayout.CENTER);

        this.setSize(1280, 720);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EShopGUI gui = new EShopGUI("ESHOP");
            }
        });
    }

    class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = loginNameTextField.getText();
            String password = loginPasswordTextField.getText();
            if (name.isEmpty()||password.isEmpty()) {
                loginNameTextField.setText("");
                loginPasswordTextField.setText("");
            } else {
                try {
                    eingeloggterBenutzer = shop.einloggen(name, password);

                    if (eingeloggterBenutzer instanceof Arbeiter) {
                        initializeAdmin();
                    }
                    else {
                        initializeCustomer();
                    }

                } catch (BenutzernameOderPasswortFalschException bpfe) {
                    // die kommende Zeile nehmen um Meldungen als Pop-Up zu zeigen. Ggf. "rootPane" zu einem anderen Frame ändern.
                    JOptionPane.showMessageDialog(rootPane, bpfe.getMessage());
                }


            }
        }
    }

    class SuchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String suchbegriff = searchTextField.getText();
            java.util.List<Artikel> suchErgebnis;
            if (suchbegriff.isEmpty()) {
                suchErgebnis = shop.gibAlleArtikel();
            } else {
                suchErgebnis = shop.sucheNachBezeichnung(suchbegriff);
            }
            Collections.sort(suchErgebnis, (artikel1, artikel2) -> artikel1.getNummer() - artikel2.getNummer());

            ArtikelTableModel tModel = (ArtikelTableModel) artikelTabelle.getModel();
            tModel.setArtikel(suchErgebnis);

            searchTextField.setText("");
        }
    }

    class FileMenu extends JMenu implements ActionListener {

        public FileMenu() {
            super("Menü");
            JMenuItem mi = new JMenuItem("Abmelden");
            mi.addActionListener(this);
            this.add(mi);
            this.addSeparator();
            mi = new JMenuItem("Speichern");
            mi.addActionListener(this);
            this.add(mi);
            this.addSeparator();
            mi = new JMenuItem("Beenden");
            mi.addActionListener(this);
            this.add(mi);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            System.out.println(command);

            if (command.equals("Abmelden")) {
                rootPane.getContentPane().removeAll();
                initializeLogin();
            }
            else if (command.equals("Speichern")) {
                try {
                    shop.schreibeDaten();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (command.equals("Beenden")) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        }
    }

    class HelpMenu extends JMenu implements ActionListener {

        public HelpMenu() {
            super("Hilfe");
            JMenu m = new JMenu("Über");
            JMenuItem mi = new JMenuItem("Team");
            mi.addActionListener(this);
            m.add(mi);
            this.add(m);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Klick auf Menüpunkt '" + e.getActionCommand() + "'.");
            JOptionPane.showMessageDialog(this, "Dieses Projekt wurde von Serkan Kayman, Daniel Smolen und Henrik Wilkens für Programmieren 2 erstellt.");
        }
    }
}