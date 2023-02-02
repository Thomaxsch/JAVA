
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden. 
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Bilder erfasst.
 * Es lassen sich hierrüber manuell Objekte anlegen/ändern, jedoch sollen über die Einstiegsklasse "Museum" über einen Datenimport die Objekte erstellt werden.
 * 
 * @author (Alexander Kipry) 
 * @version (20.12.2022)
 */

public class Bild extends Kunstwerk
{
    //Attribute der Klasse Bild
    /** Minimaltemperatur des Bildes in °C */
    private int minTemp;
    
    /** Maximaltemperatur des Bildes in °C */
    private int maxTemp;
    
    /** Anforderung der minimalen Luftfeuchtigkeit des Bildes in % */
    private int minLuft;
    
    /** Anforderung der maximalen Luftfeuchtigkeit des Bildes in % */
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
     * @param in_laufendeNummer        Dieser short entspricht der laufenden Nummer des Kunstwerkes.
     * @param in_art                   Dieser char entspricht der Art des Kunstwerkes (Bild "B", Kunstinstallation "I", Kunstgegenstand "G")
     * @param in_bezeichnung           Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param in_kuenstler             Dieser String entspricht dem Namen des Künstlers.
     * @param in_jahr                  Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param in_thema                 Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param in_attraktivitaetswert   Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider).
     * @param in_kosten                Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param in_verleihendesMuseum    Dieser String entpricht dem Namen des verleihenden Museums.
     * @param in_anschrift             Dieser String entspricht der Anschrift des verleihenden Museums (Straße, Hnr, Plz, Ort). 
     * @param in_hoehe                 Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param in_breite                Dieser int entspricht der Breite des Kunstwerkes in cm. 
     * @param in_minTemp               Dieser int entspricht der minimalen Temperatur in einem Raum, welche für das Kunstwerk zulässig ist, in °C.
     * @param in_maxTemp               Dieser int entspricht der maximalen Temperatur in einem Raum, welche für das Kunstwerk zulässig ist, in °C.
     * @param in_minLuft               Dieser int entspricht der minimalen Luftfeuchtigkeit in einem Raum, welche für das Kunstwerk zulässig ist, in %.
     * @param in_maxLuft               Dieser int entspricht der minimalen Luftfeuchtigkeit in einem Raum, welche für das Kunstwerk zulässig ist, in %.
   
       */

      public Bild (short in_laufendeNummer, char in_art, String in_bezeichnung, String in_kuenstler, String in_jahr, String in_thema, 
      int in_attraktivitaetswert, int in_kosten, String in_verleihendesMuseum,String in_anschrift, int in_hoehe, int in_breite,
      int in_minTemp,int in_maxTemp, int in_minLuft, int in_maxLuft)
    { 
        setLaufendeNummer(in_laufendeNummer);
        setArt(in_art);
        setBezeichnung(in_bezeichnung);
        setKuenstler(in_kuenstler);
        setJahr(in_jahr);
        setThema(in_thema);
        setAttraktivitaet(in_attraktivitaetswert);
        setKosten(in_kosten);
        setVerleihendesMuseum(in_verleihendesMuseum);
        setAnschrift(in_anschrift);
        setHoehe(in_hoehe);
        setBreite(in_breite);
        setMinTemp(in_minTemp);
        setMaxTemp(in_maxTemp);
        setMinLuft(in_minLuft);
        setMaxLuft(in_maxLuft);
    }
    
    //Methoden der Klasse Bild
    //setter-Methoden
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
    
    //getter-Methoden
    /**
     * Gibt die Mindesttemperatur wieder, die für das Bild zulässig ist.
     * 
     * @return die Mindestemperatur in °C.
     */
    public int getMinTemp()
    {
        return minTemp;
    }
     /**
     * Gibt die Maximaltemperatur wieder, die für das Bild zulässig ist.
     * 
     * @return die Maximaltemperatur in °C.
     */
    public int getMaxTemp()
    {
        return maxTemp;
    }
    
     /**
     * Gibt die Mindest-Luftfeuchtigkeit wieder, die für das Bild zulässig ist.
     * 
     * @return die Mindest-Luftfeuchtigkeit in %.
     */
    public int getMinLuft()
    {
        return minLuft;
    }
    
     /**
     * Gibt die Maximal-Luftfeuchtigkeit wieder, die für das Bild zulässig ist.
     * 
     * @return die Maximal-Luftfeuchtigkeit in %.
     */
    public int getMaxLuft()
    {
        return maxLuft;
    }
    /**
     * Gibt die Maße des Bildes aus.
     * 
     * @return die Maße des Bildes in einem String.
     */
    @Override
    public String outputMaße()
    {
        return ", Höhe: " + super.getHoehe() + ", Breite: " + super.getBreite();
    }
    }
    
