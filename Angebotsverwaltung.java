
/**
 * Bei der Klasse Angebotsverwaltung handelt es sich um eine Verwaltungsklasse, welche diverse Funktionen in Bezug auf die Klassen "Kunstinstallationen", "Bild" und "Kunstgegenstand" durchführen kann.
 * So kann diese die o.g. Klassen nach Objekten durchsuchen und gezielt darauf zugreifen. 
 * 
 * @author (Alexander Kipry) 
 * @version (16.12.2022)
*/

public class Angebotsverwaltung
{
    //erstmal erstellt um eine Beziehung zu den Klassen herzustellen. Angestrebt ist jedoch eine Aggregations(enthält-Beziehung).
    
    private Kunstinstallationen kunstinstallation;
    private Bild bild;
    private Kunstgegenstand kunstgegenstand;

    /**
     * Konstruktor für Objekte der Klasse Angebotsverwaltung
     */
    public Angebotsverwaltung()
    {
        // Instanzvariable initialisieren
    }

    /**
     * Diese Methode durchsucht die Objekte nach einer bestimmten Kunstinstallation und zeigt dieses an.
     */
    public static void sucheKunstinstallation()
    {
        //Code zum Durchsuchen der Kunstinstallationen 
    }
    
    /**
     * Diese Methode durchsucht die Objekte nach einem bestimmten Bild und zeigt dieses an.
     */
    public static void sucheBild()
    {
        //Code zum Durchsuchen der Bilder 
    }
    
    /**
     * Diese Methode durchsucht die Objekte nach einem bestimmten Kunstgegenstand und zeigt dieses an.
     */
    public static void sucheKunstgegenstand()
    {
        //Code zum Durchsuchen der Kunstgegenstaende 
    }
}


