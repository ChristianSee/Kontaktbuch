public class Kontaktliste {

    public Kontakt[] elemente = new Kontakt[10];    //Array anlegen, das die Elemente enthält

    //Objektmethode "hinzufuegen"
    public void hinzufuegen(Kontakt kontakt) {
        boolean wurdeEingefuegt = false;            // konnte ein Element eingefügt werden?

        // Ist noch Platz im Array? Jede Stelle im Array durchgehen
        for (int i = 0; i < elemente.length; i++) {
            if (elemente[i] == null) {              // falls leere Stelle gefunden
                elemente[i] = kontakt;              // dann kontakt einfügen
                wurdeEingefuegt = true;
                break;
            }
        }
        if (wurdeEingefuegt == false){      // es wurde nichts eingefuegt, da keine leere Stelle
            Kontakt[] neueElemente = new Kontakt[elemente.length + 10]; // neues leeres Array mit 10 Stellen mehr
            arrayKopieren(elemente, neueElemente);                      // Elemente aus altem ins neue Array kopieren
            neueElemente[elemente.length] = kontakt;                    // kontakt der hinzugefügt werden soll Zeile6
            elemente = neueElemente;                                    // elemente-array mit neuem array ersetzen, damit neues array erstellt, dass mehr Platz hat
        }
    }
    //Objektmehode "entfernen"
    public void entfernen (Kontakt kontakt) {
        for (int i =0 ; i < elemente.length; i++) {
            if (elemente[i] != null) {                      // Schublade nicht leer?, sonst nichts zu vergleichen..
                Kontakt zumVergleich = elemente[i];         // dann Kontakte miteinander vergleichen
                if (kontakt.getVorname().equals(zumVergleich.getVorname()) &&
                        kontakt.getNachname().equals(zumVergleich.getNachname()) &&
                        kontakt.getTelefonnummer().equals(zumVergleich.getTelefonnummer())) {// true wenn equals gleich sind
                        // Also Stelle i nicht leer und dieses Element stimmt mit Element kontakt Z25 überein
                    elemente[i] = null;         // Element loeschen
                    break;                      // z.B. bei doppeltem Eintrag nur einen loeschen, nicht alle
                }
            }
        }
    }

    //Objektmethode array kopieren, wenn array voll
    public void arrayKopieren(Kontakt[] a, Kontakt[] b) {
        int zaehlerb = 0;
        for (int i = 0; i < a.length; i++) {    // Schleife um alle Elemente aus Kontakt-Array a durchzugehen
            if (a[i] != null) {                 // ist a an Stelle i nicht leer?
                b[zaehlerb] = a[i];             // Inhalt von a in Array b schreiben
                zaehlerb++;
            }
        }
    }
}