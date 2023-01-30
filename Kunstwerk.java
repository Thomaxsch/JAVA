import java.lang.Integer;

/**
 * Bei dieser Klasse handelt es sich um eine abstrakte Parent-Klasse, welche die wesentlichen Informationen aller Kunstwerke definiert. 
 * Somit werden hierrüber neben den Attributen, die auf alle Arten von Kunstwerken, also der Child-Klassen (Kunstinstallationen, Bild, Kunstgegenstand), ebenfalls ein Konstruktor und Methoden definiert.
 * Es wurde sich hierbei für eine abstrakte Klasse entschieden, da ein Kunstwerk immer einen “Typ” (Kunstinstallationen, Bild, Kunstgegenstand) haben muss .
 * Somit ist ein Erzeugen eines Objekts “Kunstwerk” nicht sinnvoll. 
 * Mit Starten des Programms durch die Klasse Museum wird eine Eingabedatei mit Daten zu den verfügbaren Angeboten der Kunstwerke eingelesen.
 * Die Objekte der jeweiligen Child-Klassen werden dadurch angelegt.
 * Dabei wird auf die Parent-Klasse durch die Child-Klassen zugegriffen, um die jeweiligen Objekte zu erstellen. 
 * Zusätzlich gibt es die Möglichkeit Objekte der Child-Klassen manuell anzulegen oder zu ändern. 
 * 
 * @author (Alexander Kipry) 
 * @version (12.01.2023)
 */
    abstract class Kunstwerk implements Comparable<Kunstwerk> // "Comparable" damit durch die Kunstwerkverwaltung ein Vergleich von Attributen vorgenommen werden kann. 
{ //Attribute der Klasse Kunstwerk
    /** Lfd-Nummer des Kunstwerkes aus dem Angebot */
    private short laufendeNummer;
    
    /** Beschreibt die Art des Kunstwerkes (B, I, G) */
    private char art;
    
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
    
    /** Angabe des Namens des Museums, welches das Kunstwerk zur Verfügung stellt */
    private String verleihendesMuseum;
    
    /** Anschrift des Museums */
    private String anschrift;
    
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
     * @param in_laufendeNummer        Dieser short entspricht der laufenden Nummer des Kunstwerkes.
     * @param in_art                   Dieser char entspricht der Art des Kunstwerkes (Bild "B", Kunstinstallation "I", Kunstgegenstand "G")
     * @param in_bezeichnung           Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param in_kuenstler             Dieser String entspricht dem Namen des Künstlers.
     * @param in_jahr                  Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param in_thema                 Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param in_attraktivitaetswert   Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider)
     * @param in_kosten                Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param in_verleihendesMuseum    Dieser String entpricht dem Namen des verleihenden Museums
     * @param in_anschrift             Dieser String entspricht der Adresse des verleihenden Museums (Straße, Hnr, Plz, Ort)
     * @param in_hoehe                 Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param in_breite                Dieser int entspricht der Breite des Kunstwerkes in cm. 
     */
    
    public Kunstwerk(short in_laufendeNummer, char in_art, String in_bezeichnung, String in_kuenstler, String in_jahr, String in_thema, 
    int in_attraktivitaetswert, int in_kosten, String in_verleihendesMuseum,String in_anschrift, int in_hoehe, int in_breite)
    {  
    }
    
    //Methoden der Klasse Kunstwerk
    
    //Setter-Methoden
    /** 
     * Diese Methode ueberschreibt das laufendeNummer Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_laufendeNummer laufende Nummer des Kunstwerkes (short)
     */
    public void setLaufendeNummer(short in_laufendeNummer)
    {
        laufendeNummer= in_laufendeNummer;
    }

    /** 
     * Diese Methode ueberschreibt das art Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_art Typbezeichnung des Kunstwerkes (Bild "B", Kunstinstallation "I", Kunstgegenstand "G")
     */
    public void setArt(char in_art)
    {
        art = in_art;
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
     * Diese Methode ueberschreibt das thema Attribut eines "Kunstwerk" Objektes
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
     * Diese Methode ueberschreibt das Anschrift Attribut eines "Kunstwerk" Objektes
     * 
     * @param in_Anschrift Name und Adresse des verleihenden Museums
     */
    public void setAnschrift (String in_Anschrift)
    {
        anschrift = in_Anschrift;
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

    //getter-Methoden
      /** 
     * Diese Methode ermittelt das laufendeNummer Attribut eines "Kunstwerk" Objektes
     * 
     * @return die laufende Nummer des Kunstwerkes
     */
    public short getLaufendeNummer()
    {
        return laufendeNummer;
    }
    
      /**
     * Diese Methode ermittelt das Art Attribut eines Kunstwerk Objektes 
     * 
     * @return die Art des Kunstwerkes (Kunstinstallation, Bild, Kunstgegenstand)
     */
    public char getArt ()
    {
        return art;
    }
     /**
     * Gibt die Bezeichnung des Kunstwerkes wieder.
     * 
     * @return  die Bezeichnung/ den Namen des Kunstwerkes.
     */
    public String getBezeichnung()
    {
        return bezeichnung;
    }
    
    /**
     * Gibt den Namen des Künstlers wieder.
     * 
     * @return den Namen des Künstlers.
    */
    public String getKuenstler()
    {
        return kuenstler;
    }
    
    /**
     * Gibt das Jahr, in dem das Kunstwerk erstellt wurde wieder. 
     * 
     * @return die Jahresangabe, in welchem das Kunstwerk erstellt wurde.
     */
    public String getJahr()
    {
        return jahr;
    }
    
    /**
     * Gibt das Thema / den Schwerpunkt des Kunstwerkes wieder.
     * 
     * @return das Thema des Kunstwerkes.
     */
    public String getThema ()
    {
        return thema; 
    }
    
    /**
     * Gibt den Attraktivitaetswert des Kunstwerkes wieder.
     * 
     * @return die Attraktivität des Kunstwerkes in %.
     */
    public int getAttraktivitaet()
    {
        return attraktivitaetswert;
    }
    
    /**
     * Gibt die anfallenden Kosten für die Ausleihe des Kunstwerkes wieder.
     * 
     * @return den Preis für das Ausleihen in €.
     */
    public int getKosten ()
    {
        return kosten;
    }
    
    /**
     * Gibt den Namen des verleihenden Museums wieder.
     * 
     * @return den Namen des verleihenden Museums.
     */
    public String getVerleihendesMuseum()
    {
        return verleihendesMuseum;
    }
    
    /**
     * Gibt die Anschrift des verleihenden Museums wieder.
     * 
     * @return die Anschrift des verleihenden Museums (Straße, Hnr, Plz, Ort) 
     */
    public String getAnschrift()
    {
        return anschrift;
    }
    
    /**
     * Gibt die Höhe des Kunstwerkes wieder.
     * 
     * @return die Höhe des Kunstwerkes in cm.
     */
    public int getHoehe()
    {
        return hoehe;
    }
    
     /**
     * Gibt die Breite des Kunstwerkes wieder.
     * 
     * @return die Breite des Kunstwerkes in cm.
     */
    public int getBreite()
    {
        return breite;
    }
    
    /**
     * Methode gibt eine lesbare Beschreibung eines Kunstwerks aus
     * 
     * @return eine modifizierte textuelle Beschreibung der Kunstwerke
     */
    @Override
    public String toString()
    { 
        return "Attraktivität: " + this.getAttraktivitaet() + ", Thema: " + this.thema + ", Nr: " + this.laufendeNummer + 
        ", Art des Kunstwerks: " + this.art + ", Bezeichnung: " + this.bezeichnung + ", Breite " + this.breite;
    }
    
     /**
     * Methode, welche eine modifizierte textuelle Beschreibung der Kunstwerke für den Museumsführer ausgibt.
     * 
     * @return eine textueller Beschreibung für den Museumsführer.
     */
    public String outputMuseumsführer()
    {
        return "Name des Kunstwerks: " + this.getBezeichnung() + "  aus dem Jahr " + this.getJahr() + System.lineSeparator() +
        "Künstler: " + this.getKuenstler() + System.lineSeparator() + 
        "Zur Verfügung gestellt durch das Partnermuseum: " + this.getVerleihendesMuseum() + ", " + this.getAnschrift();
    }
    
     /**
     * Methode, welche eine modifizierte textuelle Beschreibung der Kunstwerke für die Ausleihdatei ausgibt.
     * 
     * @return eine textueller Beschreibung für die Ausleihdatei.
     */
    public String outputAusleihdatei()
    {
        return "Partnermuseum: " + this.getVerleihendesMuseum() + ", Nummer: " + this.getLaufendeNummer() + ", Art des Kunstwerks: " + 
        this.getArt() + ", Name des Kunstwerks: " + this.getBezeichnung();
    }
    
    /**
     * Methode, welche eine modifizierte textuelle Beschreibung der Kunstwerke für die Raumdatei ausgibt.
     * 
     * @return eine textueller Beschreibung für die Raumausgabedatei.
     */
    public String outputRaumdatei()
    {
        return "Nummer: " + this.getLaufendeNummer() + ", Art des Kunstwerks: " + this.getArt() + ", Name des Kunstwerks: " 
        + this.getBezeichnung() + ", Thema: " + this.getThema();
    }
    
    /**
     * Methode, welche vorgibt, dass die Attraktivitaet zwischen Kunstwerken verglichen werden kann und 
     * somit die Kunstwerkverwaltung in der Lage ist Kunstwerke nach deren Attraktivitaet zu sortieren. 
     * 
     * @return die Übergabe der verglichenen Attraktivitaetswerte
     */
    @Override
    public int compareTo(Kunstwerk kw)
    {
        return Integer.compare(this.attraktivitaetswert, kw.attraktivitaetswert);
    }
     /*
    @Override
    public int compareTo(Kunstwerk k) 
    {
        return Integer.compare(this.kosten, k.kosten);
    }
    */
}

