
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden.
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Kunstinstallationen erfasst. 
 * 
 * @author (Alexander Kipry) 
 * @version (15.12.2022)
 */
public class Kunstinstallationen extends Kunstwerk
{
   /**
    * Länge der Kunstinstallation oder des Kunstgegenstandes in cm
    */
    private int laenge;
    /**
     * Gewicht der Kunstinstallation oder des Kunstgegenstandes in KG
     */
    private int gewicht;
    
    /**
     * Konstruktor für Objekte der Klasse Kunstinstallationen
     */
    public Kunstinstallationen(int laenge, int gewicht)
    {
    }

    /**
     * Gibt die Länge der Kunstinstallation an.
     * 
     *
     * @return        laenge der Kunstinstallation in cm.
     */
    public int zeigeLänge(int laenge)
    {
        return laenge;
    }
    /**
     * Gibt das Gewicht der Kunstinstallation an.
     * 
     *
     * @return        Gewicht der Kunstinstallation in kg.
     */
    public int beispielMethode(int gewicht)
    {
        return gewicht;
    }
}
