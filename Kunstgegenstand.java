
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden. 
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Kunstgegenstände erfasst.
 * 
 * @author (Alexander Kipry) 
 * @version (11.12.2022)
 */
public class Kunstgegenstand extends Kunstwerk
{
    public int x; 
    /**
     * Konstruktor für Objekte der Klasse Kunstgegenstand
     */
    public Kunstgegenstand()
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
        return x;
    }
}
