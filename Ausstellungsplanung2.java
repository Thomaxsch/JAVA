<<<<<<< HEAD
import java.util.HashMap;
import java.util.Vector;
=======
import java.util.*;
>>>>>>> 950fff71bafd37eb2fe012bb3e8440e50624eff0

/**
 * Beschreiben Sie hier die Klasse Ausstellungsplanung2.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Ausstellungsplanung2
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
<<<<<<< HEAD
    private HashMap<Raum, Kunstwerk> zugeordneteKunstwerke;
    private Raumverwaltung raeume;
    private Angebotsverwaltung kunstwerke;
=======
    private HashMap<Raum, List<Kunstwerk>> zugeordneteKunstwerke;
    private Raumverwaltung raeume;
    private Kunstwerkverwaltung kunstwerke;
>>>>>>> 950fff71bafd37eb2fe012bb3e8440e50624eff0

    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung2
     */
<<<<<<< HEAD
    public Ausstellungsplanung2(Raumverwaltung raeume, Angebotsverwaltung kunstwerke)
=======
    public Ausstellungsplanung2(Raumverwaltung raeume, Kunstwerkverwaltung kunstwerke)
>>>>>>> 950fff71bafd37eb2fe012bb3e8440e50624eff0
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
<<<<<<< HEAD
        
        for(Raum raum : raeume2) 
        {
=======
        Vector<Kunstwerk> kunstwerke2 = kunstwerke.sortAttraktivitaet();
        // Abstand der Kunstwerke von den Ecken eines Raums muss 1 Meter (100 cm) entsprechen
        int abstandEcke = 100;
        
        for(Raum raum : raeume2) 
        {
            // Ermitelt die Länge und Breite des aktuellen Raums
            int laengeNord = raum.getLaengeRaum();
            int laengeSued = raum.getLaengeRaum();
            int breiteOst  = raum.getBreiteRaum();
            int breiteWest = raum.getBreiteRaum();
            
            // Türbreiten der vier Seiten ermitteln;
            int tuerbreiteNord = raum.getTuerNord();
            int tuerbreiteSued = raum.getTuerSued();
            int tuerbreiteOst  = raum.getTuerOst();
            int tuerbreiteWest = raum.getTuerWest();
            
            // Höhe des Raums ermitteln
            int hoeheRaum = raum.getHoeheRaum();
            
            // verfügbare Nettolänge einer Wand berechnen: Nettolänge = Bruttolänge - (abstandEcke * 2) - Türbreite
            int nettoLaengeNord = laengeNord - (abstandEcke * 2) - tuerbreiteNord;
            int nettoLaengeSued = laengeSued - (abstandEcke * 2) - tuerbreiteSued;
            int nettoBreiteOst  = breiteOst  - (abstandEcke * 2) - tuerbreiteOst;
            int nettoBreiteWest = breiteWest - (abstandEcke * 2) - tuerbreiteWest;
            
            for(Kunstwerk kunstwerk : kunstwerke2)
            {
                
            }
            
            
            
>>>>>>> 950fff71bafd37eb2fe012bb3e8440e50624eff0
            System.out.println(raum);
        }
    }
}
