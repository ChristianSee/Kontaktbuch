import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Sortierung {

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

