//*****************************************************
//**                                                 **
//** By: Christian Seepold,                          **
//** Thema: Kontaktbuch                              **
//** Version 1.0                                     **
//** Datum: 05.01.2025                               **
//** Status: ready                                   **
//**                                                 **
//*****************************************************


import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Listenstruktur für Kontakte implementieren
        Kontaktliste kontaktliste = laden();

        // Eingabeaufforderung Erstellen Anzeigen Suchen Beenden
        boolean sollWiederholen = true;
        while (sollWiederholen) {
            String eingabe = nichtLeereEingabe("Bitte Befehl eingeben: ERSTELLEN/ANZEIGEN/SUCHEN/BEENDEN");

            //Logik
            if (eingabe.equals("ERSTELLEN")) {
                System.out.println("Befehl: ERSTELLEN");
                String vorname = nichtLeereEingabe("Welcher Vorname?");
                String nachname = nichtLeereEingabe("Welcher Nachname?");
                String telefonnummer = nichtLeereEingabe("Welche Telefonnummer?");

                Kontakt kontakt = new Kontakt(vorname, nachname, telefonnummer);
                kontaktliste.hinzufuegen(new Kontakt(vorname, nachname, telefonnummer)); // neues Objekt vom Typ Kontakt hinzufuegen mit vorname, nachname, telefonnummer

            } else if (eingabe.equals("ANZEIGEN")) {
                System.out.println("Befehl: ANZEIGEN");
                gibAus(kontaktliste.elemente);          // Elemente der Kontaktliste ausgeben

            } else if (eingabe.equals("SUCHEN")) {
                System.out.println("Befehl: SUCHEN");
                String suchwort = nichtLeereEingabe(" Bitte gib das Suchwort ein:");
                suchen(kontaktliste.elemente, suchwort);

            } else if (eingabe.equals("BEENDEN")) {
                System.out.println("Befehl: BEENDEN");
                speichern(kontaktliste.elemente);
                sollWiederholen = false;
            }
        }
    }
    // Kontakte laden
    public static Kontaktliste laden(){
            Kontaktliste kontaktliste = new Kontaktliste();      // neues Objekt vom Typ Kontaktliste zum befuellen erstellen
            File file = new File("kontakte.txt");       // Datei oeffnen in der die Kontakte gespeichert sind

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader reader = new FileReader(file);
            String gesamterText = "";
            int zeichen = reader.read();                // Es können nur Dezimalwerte der Buchstaben ausgelesen werden
            while(zeichen != -1){                       // Wird -1 erreicht, kann damit kein weiteres Zeichen gefunden werden, dateiende
                gesamterText += (char) zeichen;
                zeichen = reader.read();
            }
            reader.close();
            String[] zeilenTrennen = gesamterText.split("\n");
            for (int i =0; i < zeilenTrennen.length; i++){
                String aktuelleZeile = zeilenTrennen[i];
                String [] daten = aktuelleZeile.split(":");

                kontaktliste.hinzufuegen(new Kontakt(daten[0],daten[1],daten[2]));
            }
        } catch (Exception e) {
            System.out.println("Kontakte konnten nicht geladen werden");
        }
        return kontaktliste;
    }

    // Kontakte speichern
    public static void speichern(Kontakt[] kontakte){
        File file = new File("kontakte.txt");
        try {
            if (!file.exists()){
            file.createNewFile();
            }

                FileWriter writer = new FileWriter(file);  //Konstruktor
                for ( int i = 0; i < kontakte.length; i++){
                    if (kontakte[i] != null) {
                        writer.write(kontakte[i].getVorname() + ":" + kontakte[i].getNachname() + ":" +
                            kontakte[i].getTelefonnummer() + "\n");
                    }
                }
            writer.close();
        } catch (Exception e) {
            System.out.println("Fehler");
        }
    }

    public static String nichtLeereEingabe(String eingabeAufforderung) {
        String eingabe = "";
        while (eingabe.trim().equals("")) {        // trim entfernt alle leerzeichen am anfang und ende, also prüfung ob etwas im Stirng enthalten ist
            eingabe = JOptionPane.showInputDialog(eingabeAufforderung);
        }
        return eingabe;
    }
    public static void gibAus(Kontakt[] kontakte){
        sortieren(kontakte);

        System.out.println("=========================================");
        for(int i = 0 ; i < kontakte.length; i++){      // Kontakte durchlaufen
            if (kontakte[i] != null){                   // Nicht leer
                Kontakt kontakt = kontakte[i];          // dann diesen ausgeben
                System.out.println("Vorname: " + kontakt.getVorname() + " Nachname: " + kontakt.getNachname() +
                        " Nummer: " + kontakt.getTelefonnummer());
            }
        }
        System.out.println("=========================================");
    }
    public static void suchen(Kontakt[] kontakte, String suchwort){
        sortieren(kontakte);

        System.out.println("=========================================");
        for(int i = 0 ; i < kontakte.length; i++){      // Kontakte durchlaufen
            if (kontakte[i] != null){                   // Nicht leer
                Kontakt kontakt = kontakte[i];          // dann diesen ausgeben
                if (kontakt.getVorname().contains(suchwort) || kontakt.getNachname().contains(suchwort) ||
                        kontakt.getTelefonnummer().contains(suchwort)) {
                    System.out.println("Vorname: " + kontakt.getVorname() + " Nachname: " + kontakt.getNachname() +
                            " Nummer: " + kontakt.getTelefonnummer());
                }
            }
        }
        System.out.println("=========================================");
    }
    public static void sortieren(Kontakt[] kontakte) {
        Arrays.sort(kontakte, (kontakt1, kontakt2) -> {
            if (kontakt1 == null && kontakt2 == null) {
                return 0;
            } else if (kontakt1 == null) {
                return 1;
            } else if (kontakt2 == null) {
                return -1;
            } else {
                return vergleicheKontakte(kontakt1, kontakt2);
            }
        });
    }

    public static int vergleicheKontakte(Kontakt a, Kontakt b) {
        int cmp = a.getVorname().compareToIgnoreCase(b.getVorname());
        if (cmp != 0) {
            return cmp;
        }
        cmp = a.getNachname().compareToIgnoreCase(b.getNachname());
        if (cmp != 0) {
            return cmp;
        }
        return a.getTelefonnummer().compareToIgnoreCase(b.getTelefonnummer());
    }
}