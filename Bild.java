
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
     * 
     * @param bezeichnung           Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param kuenstler             Dieser String entspricht dem Namen des Künstlers.
     * @param jahr                  Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param thema                 Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param attraktivitaetswert   Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider).
     * @param kosten                Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param ausleihendesMuseum    Dieser String entpricht dem Namen des ausleihenden Museums
     * @param hoehe                 Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param breite                Dieser int entspricht der Breite des Kunstwerkes in cm. 
     * @param minTemp               Dieser int entspricht der minimalen Temperatur in einem Raum, welche für das Kunstwerk zulässig ist, in °C.
     * @param maxTemp               Dieser int entspricht der maximalen Temperatur in einem Raum, welche für das Kunstwerk zulässig ist, in °C.
     * @param minLuft               Dieser int entspricht der minimalen Luftfeuchtigkeit in einem Raum, welche für das Kunstwerk zulässig ist, in %.
     * @param maxLuft               Dieser int entspricht der minimalen Luftfeuchtigkeit in einem Raum, welche für das Kunstwerk zulässig ist, in %.
       */
    
    
      public Bild ()
    {
        
    }
    /**
     * Gibt die Mindesttemperatur wieder, die für das Bild zulässig ist.
     * 
     * @return Mindestemperatur in °C.
     */
    public int zeigeMinTemp(int minTemp)
    {
        return minTemp;
    }
     /**
     * Gibt die Maximaltemperatur wieder, die für das Bild zulässig ist.
     * 
     * @return Maximaltemperatur in °C.
     */
    public int zeigeMaxTemp(int maxTemp)
    {
        return maxTemp;
    }
     /**
     * Gibt die Mindest-Luftfeuchtigkeit wieder, die für das Bild zulässig ist.
     * 
     * @return Mindest-Luftfeuchtigkeit in %.
     */
    public int zeigeMinLuft(int minLuft)
    {
        return minLuft;
    }
     /**
     * Gibt die Maximal-Luftfeuchtigkeit wieder, die für das Bild zulässig ist.
     * 
     * @return Maximal-Luftfeuchtigkeit in %.
     */
    public int zeigeMaxLuft(int maxLuft)
    {
        return maxLuft;
    }
    }
    
