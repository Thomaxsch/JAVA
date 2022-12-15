
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden. 
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Kunstgegenstände erfasst.
 * 
 * @author (Alexander Kipry) 
 * @version (11.12.2022)
 */
        
public class Kunstgegenstand extends Kunstwerk
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
     * Konstruktor für Objekte der Klasse Kunstgegenstand
     */
    public Kunstgegenstand()
    {
        // Instanzvariable initialisieren
        laenge = 0;
    }

    /**
     * Gibt die Länge des Kunstgegenstandes an.
     * 
     *
     * @return        Länge des Kunstgegenstandes in cm.
     */
    public int zeigeLaenge(int laenge)
    {
        return laenge;
    }
    
     /**
     * Gibt das Gewicht des Kunstgegenstandes an.
     * 
     *
     * @return        Gewicht des Kunstgegenstandes in KG.
     */
    public int zeigeGewicht(int gewicht)
    {
        return gewicht;
    }
    
    
}
