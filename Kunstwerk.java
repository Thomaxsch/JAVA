
/**
 * Bei dieser Klasse handelt es sich um eine abstrakte Parent-Klasse, welche die wesentlichen Informationen aller Kunstwerke beeinhaltet. 
 * Auf die Parent-Klasse wird durch die Child-Klassen zugegriffen, um die jeweiligen Objekte zu erstellen. 
 * 
 * @author (Alexander Kipry) 
 * @version (11.12.2022)
 */
    abstract class Kunstwerk
{ //Attribute der Klasse Kunstwerk
    /** Bezeichnung, bzw Name des jeweiligen Kunstwerkes */
    public String bezeichnung; 
    /**  Name des Künstlers, der das Kunstwerk erstellt hat */
    public String kuenstler;
    /** Jahr, in welchem das Kunstwerk hergestellt wurde */
    public String jahr; 
    /** Thema, in welches Herr Schneider das Kunstwerk eingeordnet hat */
    public String thema;
    /** Bewertung der Attraktivität des Kunstwerkes durch Herrn Schneider in % */
    public int attraktivitaetswert;
    /** Kosten für die Ausleihe des Kunstwerkes in Euro */
    public int kosten; 
    /** Angabe des Museums, welches das Kunstwerk zur Verfügung stellt */
    public String ausleihendesMuseum;
    /** Höhe des Kunstwerkes in cm */
    public int hoehe;
    /** Breite des Kunstwerkes in cm */
    public int breite; 
    
    /**
     * Konstruktor für Objekte der Klasse Kunstwerk
     * 
     * @param bezeichnung           Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param kuenstler             Dieser String entspricht dem Namen des Künstlers.
     * @param jahr                  Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param thema                 Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param attraktivitaetswert   Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider)
     * @param kosten                Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param verleihendesMuseum    Dieser String entpricht dem Namen des verleihenden Museums
     * @param hoehe                 Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param breite                Dieser int entspricht der Breite des Kunstwerkes in cm. 
     */
    public Kunstwerk(String bezeichnung, String kuenstler, String jahr, String thema, int attraktivitaetswert, int kosten, String verleihendesMuseum, int hoehe, int breite)
    {
        // Instanzvariable initialisieren
        
    }
    
    //Methoden der Klasse Raum
     /**
     * Gibt die Bezeichnung des Kunstwerkes wieder.
     * 
     * @return        Bezeichnung/Name des Kunstwerkes.
     */
    public String zeigeBezeichnung(String bezeichnung)
    {
        return bezeichnung;
    }
    /**
     * Gibt den Namen des Künstlers wieder.
     * 
     * @return Name des Künstlers.
    */
    public String zeigeKuenstler(String kuenstler)
    {
        return kuenstler;
    }
    /**
     * Gibt das Jahr, in dem das Kunstwerk erstellt wurde wieder. 
     * 
     * @return Jahresangabe .
     */
    public String zeigeJahr(String jahr)
    {
        return jahr;
    }
    /**
     * Gibt das Thema / den Schwerpunkt des Kunstwerkes wieder.
     * 
     * @return Thema des Kunstwerkes.
     */
    public String thema (String thema)
    {
        return thema; 
    }
    /**
     * Gibt den Attraktivitaetswert des Kunstwerkes wieder.
     * 
     * @return Attraktivität des Kunstwerkes in %.
     */
    public int zeigeAttraktivitaet(int attraktivitaetswert)
    {
        return attraktivitaetswert;
    }
    /**
     * Gibt die anfallenden Kosten für die Ausleihe des Kunstwerkes wieder.
     * 
     * @return Preis für das Ausleihen in €.
     */
    public int zeigeKosten (int kosten)
    {
        return kosten;
    }
    /**
     * Gibt den Namen des verleihenden Museums wieder.
     * 
     * @return Namen des verleihenden Museums.
     */
    public String zeigeVerleihendesMuseum(String verleihendesMuseum)
    {
        return verleihendesMuseum;
    }
    /**
     * Gibt die Höhe des Kunstwerkes wieder.
     * 
     * @return Höhe des Kunstwerkes in cm.
     */
    public int zeigeHoehe(int hoehe)
    {
        return hoehe;
    }
     /**
     * Gibt die Breite des Kunstwerkes wieder.
     * 
     * @return Breite des Kunstwerkes in cm.
     */
    public int zeigeBreite(int breite)
    {
        return breite;
    }
}
