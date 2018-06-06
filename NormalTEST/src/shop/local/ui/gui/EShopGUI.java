package shop.local.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;

import shop.local.domain.EShop;
import shop.local.domain.exceptions.ArtikelExistiertBereitsException;
import shop.local.domain.exceptions.BenutzernameOderPasswortFalschException;
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
    private JTextField searchTextField;
    private JTextField loginNameTextField;
    private JTextField loginPasswordTextField;
    private JTextField amountTextFeld;
    private JScrollPane scrollPane;
    private JList artikelListe;
    private JTable artikelTabelle;
    private JTextField prizeTextFeld;

    private User eingeloggterBenutzer = null;

    public EShopGUI(String titel) {
        super(titel);

        try {
            shop = new EShop("EShop");
            initializeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeAll() {
        initializeLogin();

    }

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
        // TODO: richtiges Verhalten zuordnen
        c.gridx = 2;
        c.weightx = 0.2;
        c.anchor = GridBagConstraints.SOUTH;
        gridBagLayout.setConstraints(loginButton, c);
        loginButtonPanel.add(loginButton);

        this.setLayout(new BorderLayout());
        this.add(loginPanel, BorderLayout.CENTER);
        this.add(loginButtonPanel, BorderLayout.SOUTH);

        this.setSize(1600, 900);
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
        // TODO: richtiges Verhalten zuordnen
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
                    } catch (NumberFormatException e2) {
                        System.err.println(e2.getMessage());
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
        scrollPane = new JScrollPane(artikelTabelle);

        this.getContentPane().removeAll();

        this.setLayout(new BorderLayout());
        this.add(suchPanel, BorderLayout.SOUTH);
        this.add(insertPanel, BorderLayout.EAST);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(1600, 900);
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
        // TODO: richtiges Verhalten zuordnen
        c.gridx = 2;
        c.weightx = 0.2;
        gridBagLayout.setConstraints(searchButton, c);
        suchPanel.add(searchButton);

        int noOfRows = 12;
        JPanel insertPanel = new JPanel();
        insertPanel.setBorder(BorderFactory.createTitledBorder("Einfügen"));
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
                    } catch (ArtikelExistiertBereitsException e1) {
                        System.err.println(e1.getMessage());
                    } catch (NumberFormatException e2) {
                        System.err.println(e2.getMessage());
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
        scrollPane = new JScrollPane(artikelTabelle);


        // Komponenten aus vorigem Layout entfernen
        this.getContentPane().removeAll();

        // Neue Komponenten zu neuem Layout hinzufügen
        this.setLayout(new BorderLayout());
        this.add(suchPanel, BorderLayout.SOUTH);
        this.add(insertPanel, BorderLayout.EAST);
        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(1600, 900);
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
                    else if (eingeloggterBenutzer instanceof Kunde) {
                        initializeCustomer();
                    }

                } catch (BenutzernameOderPasswortFalschException bpfe) {
                    // TODO: JOptionPane
                    System.err.println(bpfe.getMessage());
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
            super("File");
            JMenuItem mi = new JMenuItem("Save");
            mi.addActionListener(this);
            this.add(mi);
            this.addSeparator();
            mi = new JMenuItem("Quit");
            mi.addActionListener(this);
            this.add(mi);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String command = ae.getActionCommand();
            System.out.println(command);

            if (command.equals("Save")) {
                try {
                    shop.schreibeDaten();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (command.equals("Quit")) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        }
    }

    class HelpMenu extends JMenu implements ActionListener {

        JDialog team = new CreateDialog();

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
            showDialog();
        }

        private class CreateDialog extends JDialog {

            private JDialog dialog() {
                JDialog dialog = new JDialog(new JFrame(), "Das Team:", false);
                JLabel label = new JLabel("Dieses Projekt wurde von Daniel Smolen und Henrik Wilkens für Programmieren 2 erstellt.");
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setPreferredSize(new Dimension(200, 140));
                dialog.add(label);
                // dieser befahl sorgt dafür, dass sich das Fenster an den Inhalt anpasst
                dialog.pack();
                return dialog;
            }
        }

        public void showDialog() {
            // wenn "team" schon geöffnet wurde, wird es in der Vordergrund geholt, sonst neu geöffnet
            if (team.isShowing()) {
                team.toFront();
            } else {
                team.setVisible(true);
            }
        }
    }
}