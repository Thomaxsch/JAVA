
/**
 * Bei dieser Klasse handelt es sich um eine abstrakte Parent-Klasse, welche die wesentlichen Informationen aller Kunstwerke beeinhaltet. 
 * Auf die Parent-Klasse wird durch die Child-Klassen zugegriffen, um die jeweiligen Objekte zu erstellen. 
 * 
 * @author (Alexander Kipry) 
 * @version (16.12.2022)
 */
    abstract class Kunstwerk
{ //Attribute der Klasse Kunstwerk
    /** Lfd-Nummer des Kunstwerkes aus dem Angebot */
    private short laufendeNummer;
    /** Bezeichnung, bzw Name des jeweiligen Kunstwerkes */
    private String bezeichnung; 
    /**  Name des Künstlers, der das Kunstwerk erstellt hat */
    private String kuenstler;
    /** Jahr, in welchem das Kunstwerk hergestellt wurde */
    private String jahr; 
    /** Thema, in welches Herr Schneider das Kunstwerk eingeordnet hat */
    private String thema;
    /** Bewertung der Attraktivität des Kunstwerkes durch Herrn Schneider in % */
    private int attraktivitaetswert;
    /** Kosten für die Ausleihe des Kunstwerkes in Euro */
    private int kosten; 
    /** Angabe des Museums, welches das Kunstwerk zur Verfügung stellt */
    private String verleihendesMuseum;
    /** Höhe des Kunstwerkes in cm */
    private int hoehe;
    /** Breite des Kunstwerkes in cm */
    private int breite; 
    
    /**
     * Konstruktor für Objekte der Klasse Kunstwerk ohne Parameter
     */
    public Kunstwerk()
    {       
    }
    
    /**
     * Konstruktor für Objekte der Klasse Kunstwerk mit Parametern
     * 
     * @param laufendeNummer        Dieser short entspricht der laufenden Nummer des Kunstwerkes
     * @param bezeichnung           Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param kuenstler             Dieser String entspricht dem Namen des Künstlers.
     * @param jahr                  Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param thema                 Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param attraktivitaetswert   Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider)
     * @param kosten                Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param verleihendesMuseum    Dieser String entpricht dem Namen & der Adresse des verleihenden Museums
     * @param hoehe                 Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param breite                Dieser int entspricht der Breite des Kunstwerkes in cm. 
     */
    
    public Kunstwerk(short laufendeNummer,String bezeichnung, String kuenstler, String jahr, String thema, int attraktivitaetswert, int kosten, String verleihendesMuseum, int hoehe, int breite)
    {  
    }
    
    //Methoden der Klasse Kunstwerk
    /** 
     * Diese Methode ueberschreibt das laufendeNummer Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_laufendeNummer laufende Nummer des Kunstwerkes (short)
     */
    public void setLaufendeNummer(short in_laufendeNummer)
    {
        laufendeNummer=in_laufendeNummer;
    }
    
    /** 
     * Diese Methode ueberschreibt das bezeichnung Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_bezeichnung Bezeichnung des Kunstwerkes (String)
     */
    
    public void setBezeichnung(String in_bezeichnung)
    {
        bezeichnung=in_bezeichnung;
    }
    /** 
     * Diese Methode ueberschreibt das kuenstler Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_kuenstler Bezeichnung des Kuenstlers (String)
     */
    public void setKuenstler(String in_kuenstler)
    {
        kuenstler=in_kuenstler;
    }
    
    /** 
     * Diese Methode ueberschreibt das jahr Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_jahr Jahresangabe des Kunstwerkes (String)
     */
    public void setJahr(String in_jahr)
    {
        jahr = in_jahr;
    }
    
    /** 
     * Diese Methode ueberschreibt das thema Attributes eines "Kunstwerk" Objektes
     * 
     * @param in_thema thema des Kunstwerkes (String)
     */
    
    public void setThema(String in_thema)
    {
        thema = in_thema;
    }
    
    /** 
     * Diese Methode ueberschreibt das attraktivitaetswert Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_attraktivitaet attraktivitaetswert des Kunstwerkes in % (int)
     */
    
    public void setAttraktivitaet (int in_attraktivitaet)
    {
        attraktivitaetswert = in_attraktivitaet;
    }
    /**
     * Diese Methode ueberschreibt das kosten Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_kosten kosten für die Ausleihe des Kunstwerkes in € (int)
     * 
     */
    public void setKosten (int in_kosten)
    {
        kosten = in_kosten;
    }
    
    /** 
     * Diese Methode ueberschreibt das verleihendesMuseum Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_verleihendesMuseum Name und Adresse des verleihenden Museums
     */
    public void setVerleihendesMuseum (String in_verleihendesMuseum)
    {
        verleihendesMuseum = in_verleihendesMuseum;
    }
    
     /** 
     * Diese Methode ueberschreibt das hoehe Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_hoehe Hoehe des Kunstwerkes in cm.
     */
    public void setHoehe (int in_hoehe)
    {
        hoehe = in_hoehe;
    }
    
     /** 
     * Diese Methode ueberschreibt das breite Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_breite Breite des Kunstwerkes in cm.
     */
    public void setBreite (int in_breite)
    {
        breite = in_breite;
    }
    
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
