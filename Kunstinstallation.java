
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden.
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Kunstinstallationen erfasst. 
 * Es lassen sich hierrüber manuell Objekte anlegen/ändern.
 * Jedoch sollen über die Einstiegsklasse "Museum" über einen Datenimport die Objekte erstellt werden, welches sich hierbei den Konstruktoren bedienen.
 * 
 * @author (Alexander Kipry) 
 * @version (20.12.2022)
 */
public class Kunstinstallation extends Kunstwerk
{
    //Attribute der Klasse Kunstinstallationen
   /** Länge der Kunstinstallation in cm */
    private int laenge;
    
    /** Gewicht der Kunstinstallation in KG */
    private int gewicht;
    
    //Konstruktoren der Klasse Kunstinstallationen
    /**
     * Konstruktor für ein Objekt der Klasse Kunstinstallationen ohne Parameter
     */
    public Kunstinstallation ()
    {
    }
    
    /**
     * Konstruktor für ein Objekt der Klasse Kunstinstallationen mit Parametern
     * 
     * @param laufendeNummer            Dieser short entspricht der laufenden Nummer des Kunstwerkes
     * @param art                       Dieser char entspricht der Art des Kunstwerkes (Bild "B", Kunstinstallation "I", Kunstgegenstand "G")
     * @param bezeichnung               Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param in_kuenstler              Dieser String entspricht dem Namen des Künstlers.
     * @param jahr                      Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param thema                     Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param attraktivitaetswert       Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider).
     * @param kosten                    Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param verleihendesMuseum        Dieser String entpricht dem Namen & der Adresse des verleihenden Museums
     * @param hoehe                     Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param breite                    Dieser int entspricht der Breite des Kunstwerkes in cm. 
     * @param plaziert                  Dieser boolean gibt über true und false an, ob das Kunstwerk bereits in einem Raum plaziert wurde. 
     * @param laenge                    Dieser int entspricht der Länge der Kunstinstallation in cm.
     * @param gewicht                   Dieser int entspricht dem Gewicht der Kunstinstallation in KG.
     */
    public Kunstinstallation (short laufendeNummer, char art, String bezeichnung, String kuenstler, String jahr, String thema, int attraktivitaetswert, int kosten, String verleihendesMuseum,
                                String anschrift, int hoehe, int breite, /*boolean plaziert,*/ int laenge, int gewicht)
    {
        setLaufendeNummer(laufendeNummer);
        setArt(art);
        setBezeichnung(bezeichnung);
        setKuenstler(kuenstler);
        setJahr(jahr);
        setAttraktivitaet(attraktivitaetswert);
        setKosten(kosten);
        setVerleihendesMuseum(verleihendesMuseum);
        //setPlaziert(plaziert);
        setHoehe(hoehe);
        setBreite(breite);
        setLaenge(laenge);
        setGewicht(gewicht);
    }
    
     //setter - Methoden
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
    
    //getter - Methoden
    /**
     * Gibt die Länge der Kunstinstallation an.
     * 
     *
     * @return        laenge der Kunstinstallation in cm.
     */
    public int getLaenge()
    {
        return laenge;
    }
    
    /**
     * Gibt das Gewicht der Kunstinstallation an.
     * 
     *
     * @return        Gewicht der Kunstinstallation in kg.
     */
    public int zeigeGewicht()
    {
        return gewicht;
    }
}
