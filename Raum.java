
/**
 * Die Klasse Raum ist eine Datenklasse. Sie beschreibt die verfügbaren Räume des VAWi-Museums Essen. 
 * Jeder Raum verfuegt über eigene Bezeichnungsmerkmale und Dimensionen.  Sie steht in einer Aggregation 
 * zu der Klasse Raumverwaltung.
 * 
 * Mit Starten des Programms durch die Klasse Museum wird eine Eingabedatei mit Daten zu den verfuegbaren 
 * Raeumen des VAWi-Museums eingelesen und die Objekte der Klasse Raum angelegt.
 * 
 * Es gibt auch die manuelle Moeglichkeit Objekte der Klasse Raum anzulegen oder Eigenschaften von bereits 
 * angelegten Objekten zu aendern.
 * 
 * 
 * @author Carla Saradeth 
 * @version Dez 20222
 */
public class Raum
{
    // Attribute der Klasse Raum
    /** Raeume haben eine laufende Nummer. */
    private int nummer;
    
    /** Raeume haben einen Name. */
    private String name;
    
    /** Raeume haben eine Laenge (der Nord- und Südwand) in cm. */
    private int laengeRaumInCm;
    
    /** Raeume haben eine Breite (der Ost- und Westwand) in cm. */
    private int breiteRaumInCm;
   
    /** Raeume haben eine Hoehe in cm. */
    private int hoeheRaumInCm;
    
    /** Raeume haben eine Tuerbreite Nordseite in cm. */
    private int tuerNordInCm;
    
    /** Raeume eine Tuerbreite Ostseite in cm. */
    private int tuerOstInCm;
    
    /** Raeume eine Tuerbreite Suedseite in cm. */
    private int tuerSuedInCm;
    
    /** Raeume haben eine Tuerbreite Westseite in cm. */
    private int tuerWestInCm;

    
    //Konstruktoren der Klasse Raum
     /**
     * Konstruktor fuer Objekte der Klasse Raum ohne Parameter.
     */
    public Raum()
    {  
        //Initialisieren von Attributen
        setNummer(0);
        setName("");
        setLaengeRaumInCm(0);
        setBreiteRaumInCm(0);
        setHoeheRaumInCm(0);
        setTuerNordInCm(0);
        setTuerOstInCm(0);
        setTuerSuedInCm(0);
        setTuerWestInCm(0);      
    }

    /**
     * Konstruktor fuer Objekte der Klasse Raum mit Parameter.
     * 
     * @param nummer            Dieser int enspricht der Raumnummer.
     * @param name              Dieser String entpricht der Raumbezeichnung.
     * @param laengeRaumInCm    Dieser int entspricht der Raumlaenge (der Nord- und Südwand) in cm.
     * @param breiteRaumInCm    Dieser int entspricht der Raumbreite (der Ost- und Westwand) in cm.
     * @param hoeheRaumInCm     Dieser int entspricht der Raumhoehe in cm.
     * @param tuerNordInCm      Dieser int entspricht der Türbreite an der Nordseite des Raums in cm.
     * @param tuerOstInCm       Dieser int entspricht der Türbreite an der Ostseite des Raums in cm.
     * @param tuerSuedInCm      Dieser int entspricht der Türbreite an der Suedseite des Raums in cm.
     * @param tuerWestInCm      Dieser int entspricht der Türbreite an der Westseite des Raums in cm.
     */
    public Raum(int nummer,String name,int laengeRaumInCm,int breiteRaumInCm,int hoeheRaumInCm,
                int tuerNordInCm,int tuerOstInCm, int tuerSuedInCm, int tuerWestInCm)
    {
        //Initialisieren von Attributen
        setNummer(nummer);
        setName(name);
        setLaengeRaumInCm(laengeRaumInCm);
        setBreiteRaumInCm(breiteRaumInCm);
        setHoeheRaumInCm(hoeheRaumInCm);
        setTuerNordInCm(tuerNordInCm);
        setTuerOstInCm(tuerOstInCm);
        setTuerSuedInCm(tuerSuedInCm);
        setTuerWestInCm(tuerWestInCm);  
    }
    
      
    // Methoden für Objekte der Klasse Raum.
    /** Die Nummer des Raums setzen/aendern.
     * @param in_nummer    Die neue Nummer des Raums. (int)
     */
    
        public void setNummer(int in_nummer)
    {   
        nummer = in_nummer;
    }
    
    /** Die Namen des Raums setzen/aendern.
     * @param in_name    Der neue Name des Raums. (String)
     */
    
        public void setName(String in_name)
    {   
        name = in_name;
    }
    
    /** Die Laenge des Raums setzen/aendern..
     * @param in_laengeRaumInCm    Die neue Laenge des Raums. (int)
     */
    
        public void setLaengeRaumInCm(int in_laengeRaumInCm)
    {   
        laengeRaumInCm = in_laengeRaumInCm;
    }
    
    /** Die Breite des Raums setzen/aendern..
     * @param in_breiteRaumInCm    Die neue Breite des Raums. (int)
     */
    
        public void setBreiteRaumInCm(int in_breiteRaumInCm)
    {   
        breiteRaumInCm = in_breiteRaumInCm;
    }
    
    /** Die Hoehe des Raums setzen/aendern..
     * @param in_hoeheRaumInCm    Die neue Hoehe des Raums. (int)
     */
    
        public void setHoeheRaumInCm(int in_hoeheRaumInCm)
    {   
        hoeheRaumInCm = in_hoeheRaumInCm;
    }
     
    /** Die Tuerbreite der Nordseite des Raums setzen/aendern..
     * @param in_tuerNordInCm    Die neue Tuerbreite der Nordseite. (int)
     */
    
        public void setTuerNordInCm(int in_tuerNordInCm)
    {   
        tuerNordInCm = in_tuerNordInCm;
    }
    
    /** Die Tuerbreite der Ostseite des Raums setzen/aendern..
     * @param in_tuerOstInCm    Die neue Tuerbreite der Ostseite. (int)
     */
    
        public void setTuerOstInCm(int in_tuerOstInCm)
    {   
        tuerOstInCm = in_tuerOstInCm;
    }
    
    /** Die Tuerbreite der Suedseite des Raums setzen/aendern..
     * @param in_tuerSuedInCm    Die neue Tuerbreite der Suedseite. (int)
     */
    
        public void setTuerSuedInCm(int in_tuerSuedInCm)
    {   
        tuerSuedInCm = in_tuerSuedInCm;
    }
    
    /** Die Tuerbreite der Westseite des Raums setzen/aendern..
     * @param in_tuerWestInCm    Die neue Tuerbreite der Westseite. (int)
     */
    
        public void setTuerWestInCm(int in_tuerWestInCm)
    {   
        tuerWestInCm = in_tuerWestInCm;
    }
    
    /** Ermittle die verfuegbare Strecke der Wand Nord. (relevant für die Aufhängung von Bildern)
     * @return  Die verfuegbare Strecke an der Wand Nord des Raums.
     */
    
        public int RechneWandNord()
    {   
        return laengeRaumInCm-tuerNordInCm;
    }
        
    /** Ermittle die verfuegbare Strecke der Wand Ost. (relevant für die Aufhängung von Bildern)
     * @return  Die verfuegbare Strecke an der Wand Ost des Raums.
     */
    
        public int RechneWandOst()
    {   
        return breiteRaumInCm-tuerOstInCm;
    }   
    
    /** Ermittle die verfuegbare Strecke der Wand Nord. (relevant für die Aufhängung von Bildern)
     * @return  Die verfuegbare Strecke an der Wand Sued des Raums.
     */
    
        public int RechneWandSued()
    {   
        return laengeRaumInCm-tuerSuedInCm;
    }
    
    /** Ermittle die verfuegbare Strecke der Wand West. (relevant für die Aufhängung von Bildern)
     * @return  Die verfuegbare Strecke an der Wand West des Raums.
     */
    
        public int RechneWandWest()
    {   
        return breiteRaumInCm-tuerWestInCm;
    } 
}
