
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden.
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Kunstinstallationen erfasst. 
 * Es lassen sich hierrüber manuell Objekte anlegen, jedoch sollen über die Einstiegsklasse "Museum" über einen Datenimport die Objekte erstellt werden.
 * 
 * @author (Alexander Kipry) 
 * @version (16.12.2022)
 */
public class Kunstinstallationen extends Kunstwerk
{
    //Attribute der Klasse Kunstinstallationen
   /**
    * Länge der Kunstinstallation in cm
    */
    private int laenge;
    /**
     * Gewicht der Kunstinstallation in KG
     */
    private int gewicht;
    
    //Konstruktoren der Klasse Kunstinstallationen
    /**
     * Konstruktor für ein Objekt der Klasse Kunstinstallationen ohne Parameter
     */
    public Kunstinstallationen ()
    {
    }
    
    /**
     * Konstruktor für ein Objekt der Klasse Kunstinstallationen mit Parametern
     * 
     * @param bezeichnung               Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param in_kuenstler                 Dieser String entspricht dem Namen des Künstlers.
     * @param jahr                      Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param thema                     Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param attraktivitaetswert       Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider).
     * @param kosten                    Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param verleihendesMuseum        Dieser String entpricht dem Namen & der Adresse des verleihenden Museums
     * @param hoehe                     Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param breite                    Dieser int entspricht der Breite des Kunstwerkes in cm. 
     * @param laenge                    Dieser int entspricht der Länge der Kunstinstallation in cm.
     * @param gewicht                   Dieser int entspricht dem Gewicht der Kunstinstallation in KG.
     */
    public Kunstinstallationen (String bezeichnung, String kuenstler, String jahr, String thema, int attraktivitaetswert, int kosten, String verleihendesMuseum, int hoehe, int breite, int laenge, int gewicht)
    {
        setBezeichnung(bezeichnung);
        setKuenstler(kuenstler);
        setJahr(jahr);
        setAttraktivitaet(attraktivitaetswert);
        setKosten(kosten);
        setVerleihendesMuseum(verleihendesMuseum);
        setHoehe(hoehe);
        setBreite(breite);
        setLaenge(laenge);
        setGewicht(gewicht);
    }
     //Methoden der Klasse Kunstinstallationen
    /**
     * Diese Methode ueberschreibt das laenge Attribut einer Kunstinstallation
     * 
     * @param in_laenge Laenge der Kunstinstallation in cm
     */
    
    public void setLaenge(int in_laenge)
    {
        laenge = in_laenge;
    }
    
    /**
     * Diese Methode ueberschreibt das gewicht Attribut einer Kunstinstallation
     * 
     * @param in_gewicht Gewicht der Kunstinstallation in KG
     */
    
    public void setGewicht(int in_gewicht)
    {
        gewicht = in_gewicht;
    }
    
    /**
     * Gibt die Länge der Kunstinstallation an.
     * 
     *
     * @return        laenge der Kunstinstallation in cm.
     */
    public int zeigeLaenge(int laenge)
    {
        return laenge;
    }
    /**
     * Gibt das Gewicht der Kunstinstallation an.
     * 
     *
     * @return        Gewicht der Kunstinstallation in kg.
     */
    public int zeigeGewicht(int gewicht)
    {
        return gewicht;
    }
}
