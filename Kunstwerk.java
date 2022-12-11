
/**
<<<<<<< HEAD
 * Beschreiben Sie hier die Klasse Kunstwerk.
 * 
<<<<<<< HEAD
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Kunstwerk
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;
    private int y;
    
=======
 * @author (Alexander Kipry) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Kunstwerk
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;
>>>>>>> 49797233eff024eb354304d84e6ac2a92bdd38a5

    /**
     * Konstruktor für Objekte der Klasse Kunstwerk
     */
    public Kunstwerk()
    {
        // Instanzvariable initialisieren
        x = 0;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public int beispielMethode(int y)
    {
        // tragen Sie hier den Code ein
        return x + y;
    }
=======
 * Bei dieser Klasse handelt es sich um eine abstrakte Parent-Klasse, welche die wesentlichen Informationen aller Kunstwerke beeinhaltet. 
 * Auf die Parent-Klasse wird durch die Child-Klassen zugegriffen, um die jeweiligen Objekte zu erstellen. 
 * 
 * @author (Alexander Kipry) 
 * @version (11.12.2022)
 */
abstract class Kunstwerk
{
    /** 
     * Bezeichnung, bzw Name des jeweiligen Kunstwerkes 
     */
    public String bezeichnung; 
    /** 
     * Name des Künstlers, der das Kunstwerk erstellt hat 
     */
    public String kuenstler;
    /** 
     * Jahr, in welchem das Kunstwerk hergestellt wurde
     */
    public String jahr; 
    /** 
     * Thema, in welches Herr Schneider das Kunstwerk eingeordnet hat
     */
    public String thema;
    /**
     * Bewertung der Attraktivität des Kunstwerkes durch Herrn Schneider in %
     */
    public int attraktivitaetswert;
    /** 
     * Kosten für die Ausleihe des Kunstwerkes in Euro
     */
    public int kosten; 
    /**
     * Angabe des Museums, welches das Kunstwerk zur Verfügung stellt 
     */
    public String ausleihendesMuseum;
    /**
     * Höhe des Kunstwerkes in cm
     */
    public int hoehe;
    /**
     * Breite des Kunstwerkes in cm;
     */
    public int breite; 
>>>>>>> 4a6772f4139e352c35acd466b20b0b081692ed40
}
