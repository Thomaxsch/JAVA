import java.util.HashMap;
import java.util.Vector;

/**
 * Beschreiben Sie hier die Klasse Ausstellungsplanung2.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Ausstellungsplanung2
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private HashMap<Raum, Kunstwerk> zugeordneteKunstwerke;
    private Raumverwaltung raeume;
    private Angebotsverwaltung kunstwerke;

    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung2
     */
    public Ausstellungsplanung2(Raumverwaltung raeume, Angebotsverwaltung kunstwerke)
    {
        this.raeume = raeume;
        this.kunstwerke = kunstwerke;        
    }
    
    
    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void generiereAusstellung()
    {
        Vector<Raum> raeume2 = raeume.getRaumVector();
        
        for(Raum raum : raeume2) 
        {
            System.out.println(raum);
        }
    }
}
