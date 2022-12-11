
/**
<<<<<<< HEAD
 * Beschreiben Sie hier die Klasse Kunstinstallationen.
 * 
 * @author (Alexander Kipry) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Kunstinstallationen
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;

    /**
     * Konstruktor für Objekte der Klasse Kunstinstallationen
     */
    public Kunstinstallationen()
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
=======
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden.
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Kunstinstallationen erfasst. 
 * 
 * @author (Alexander Kipry) 
 * @version (11.12.2022)
 */
public class Kunstinstallationen extends Kunstwerk
{
   /**
    * Länge der Kunstinstallation oder des Kunstgegenstandes in cm
    */
    public int laenge;
    /**
     * Gewicht der Kunstinstallation oder des Kunstgegenstandes in KG
     */
    public int gewicht;
    
    /**
     * Konstruktor für Objekte der Klasse Kunstinstallationen
     */
    public Kunstinstallationen()
    {
        // Instanzvariable initialisieren
        laenge = 0;
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
        return laenge;
>>>>>>> 4a6772f4139e352c35acd466b20b0b081692ed40
    }
}
