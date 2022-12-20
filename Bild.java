
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden. 
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Bilder erfasst.
 * Es lassen sich hierrüber manuell Objekte anlegen/ändern, jedoch sollen über die Einstiegsklasse "Museum" über einen Datenimport die Objekte erstellt werden.
 * 
 * @author (Alexander Kipry) 
 * @version (18.12.2022)
 */

public class Bild extends Kunstwerk
{
    //Attribute der Klasse Bild
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
    
    //Konstruktoren der Klasse Bild
    /**
     * Konstruktor für Objekte der Klasse Bild ohne Parameter
     */
    
    public Bild ()
    {
    }
    
    /**
     * Konstruktor für Objekte der Klasse Bild mit Parametern
     *
     * @param laufendeNummer        Dieser short entspricht der laufenden Nummer des Kunstwerkes.
     * @param art                   Dieser char entspricht der Art des Kunstwerkes (Bild "B", Kunstinstallation "I", Kunstgegenstand "G")
     * @param bezeichnung           Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param kuenstler             Dieser String entspricht dem Namen des Künstlers.
     * @param jahr                  Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param thema                 Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param attraktivitaetswert   Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider).
     * @param kosten                Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param verleihendesMuseum    Dieser String entpricht dem Namen & der Adresse des verleihenden Museums (Name & Adresse ).
     * @param hoehe                 Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param breite                Dieser int entspricht der Breite des Kunstwerkes in cm. 
     * @param plaziert              Dieser boolean gibt über true und false an, ob das Kunstwerk bereits in einem Raum plaziert wurde. 
     * @param minTemp               Dieser int entspricht der minimalen Temperatur in einem Raum, welche für das Kunstwerk zulässig ist, in °C.
     * @param maxTemp               Dieser int entspricht der maximalen Temperatur in einem Raum, welche für das Kunstwerk zulässig ist, in °C.
     * @param minLuft               Dieser int entspricht der minimalen Luftfeuchtigkeit in einem Raum, welche für das Kunstwerk zulässig ist, in %.
     * @param maxLuft               Dieser int entspricht der minimalen Luftfeuchtigkeit in einem Raum, welche für das Kunstwerk zulässig ist, in %.
   
       */

      public Bild (short laufendeNummer, char art, String bezeichnung, String kuenstler, String jahr, String thema, int attraktivitaetswert, int kosten, String verleihendesMuseum, int hoehe, int breite,boolean plaziert, int minTemp,int maxTemp, int minLuft, int maxLuft)
    { 
        setLaufendeNummer(laufendeNummer);
        setBezeichnung(bezeichnung);
        setKuenstler(kuenstler);
        setJahr(jahr);
        setAttraktivitaet(attraktivitaetswert);
        setKosten(kosten);
        setVerleihendesMuseum(verleihendesMuseum);
        setHoehe(hoehe);
        setBreite(breite);
        setMinTemp(minTemp);
        setMaxTemp(maxTemp);
        setMinLuft(minLuft);
        setMaxLuft(maxLuft);
    }
    //Methoden der Klasse Bild
    /**
     * Diese Methode ueberschreibt das minTemp Attribut eines Bildes
     * 
     * @param in_minTemp Mindesttemperatur des Bildes in °C
     */
    
    public void setMinTemp(int in_minTemp)
    {
        minTemp=in_minTemp;
    }
    /**
     * Diese Methode ueberschreibt das maxTemp Attribut eines Bildes
     * 
     * @param in_maxTemp Maximaltemperatur des Bildes in °C
     */
    
    public void setMaxTemp(int in_maxTemp)
    {
        maxTemp=in_maxTemp;
    }
    /**
     * Diese Methode ueberschreibt das minLuft Attribut eines Bildes
     * 
     * @param in_minLuft Minimale Luftfeuchtigkeit des Bildes in %.
     */
    
    public void setMinLuft(int in_minLuft)
    {
        minLuft=in_minLuft;
    }
    /**
     * Diese Methode ueberschreibt das maxLuft Attribut eines Bildes
     * 
     * @param in_maxLuft Maximale Luftfeuchtigkeit des Bildes in %.
     */
    
    public void setMaxLuft(int in_maxLuft)
    {
        maxLuft=in_maxLuft;
    }
    
    /**
     * Gibt die Mindesttemperatur wieder, die für das Bild zulässig ist.
     * 
     * @return Mindestemperatur in °C.
     */
    public int zeigeMinTemp()
    {
        return minTemp;
    }
     /**
     * Gibt die Maximaltemperatur wieder, die für das Bild zulässig ist.
     * 
     * @return Maximaltemperatur in °C.
     */
    public int zeigeMaxTemp()
    {
        return maxTemp;
    }
     /**
     * Gibt die Mindest-Luftfeuchtigkeit wieder, die für das Bild zulässig ist.
     * 
     * @return Mindest-Luftfeuchtigkeit in %.
     */
    public int zeigeMinLuft()
    {
        return minLuft;
    }
     /**
     * Gibt die Maximal-Luftfeuchtigkeit wieder, die für das Bild zulässig ist.
     * 
     * @return Maximal-Luftfeuchtigkeit in %.
     */
    public int zeigeMaxLuft()
    {
        return maxLuft;
    }
    }
    
