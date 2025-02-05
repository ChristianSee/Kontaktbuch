public class Kontakt {
    // Kontaktklasse erstellen
        private String vorname = "";
        private String nachname = "";
        private String telefonnummer = "";

        public Kontakt(String vornameEin, String nachnameEin, String telefonnummerEin) { //Konstruktor
            vorname = vornameEin;
            nachname = nachnameEin;
            telefonnummer = telefonnummerEin;
        }

        public void setVorname(){
            this.vorname = vorname;
        }
        public String getVorname(){
            return vorname;
        }
        public void setNachname(String nachname) {
            this.nachname = nachname;
        }
        public String getNachname(){
            return nachname;
        }
        public void setTelefonnummer(String telefonnummer) {
            this.telefonnummer = telefonnummer;
        }
        public String getTelefonnummer(){
            return telefonnummer;
        }
}
