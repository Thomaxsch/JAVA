
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden. 
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Bilder erfasst.
 * 
 * @author (Alexander Kipry) 
 * @version (12.11.2022)
 */
public class Bild extends Kunstwerk
{
    /**
     * Minimaltemperatur des Bildes in °C
     */
    private int minTemp;
    
    /**
     * Maximaltemperatur des Bildes in °C
     */
    private int maxTemp;
    /**
     * Anforderung der minimalen Luftfeuchtigkeit des Bildes in %
     */
    private int minLuft;
    /**
     * Anforderung der maximalen Luftfeuchtigkeit des Bildes in %
     */
    private int maxLuft;
    
    
    /**
     * Konstruktor für Objekte der Klasse Bild
     */
    public Bild()
    {
        // Instanzvariable initialisieren
        minTemp = 0;
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
        return minTemp;
    }
}
