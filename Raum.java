
/**
 * Die Klasse "Raum" ist eine Datenklasse. Sie beschreibt die verfügbaren Räume des VAWi-Museums Essen. 
 * Jeder Raum verfuegt über eigene Bezeichnungsmerkmale und Dimensionen.  Sie steht in einer Aggregation 
 * zu der Klasse "Raumverwaltung".
 * 
 * Mit Starten des Programms durch die Klasse Museum wird eine Eingabedatei mit Daten zu den verfuegbaren 
 * Raeumen des VAWi-Museums eingelesen und die Objekte der Klasse "Raum" angelegt. Zusaetzlich gibt es auch 
 * die manuelle Moeglichkeit Objekte der Klasse "Raum" anzulegen oder Eigenschaften von bereits angelegten 
 * Objekten zu aendern.
 * 
 * Neben Methoden zum Setzen und Ermittlen der Attribute von Objekten dieser Klasse, enthält die Klasse auch 
 * Methoden zur Unterstützung der Klasse "Ausstellungsplanung": 
 * 
 * Im Gegensatz zu der Belegungsplanung von Kunstinstallationen, welche nur alleine einem Raum zugeordnet sein koennen,
 * arbeitet die Belegungsplanung von Kunstgegenstaenden und Bilder mit einer sich anpassender Kapazitaet der Raeume
 * durch die bisdahin vollzogene Belegungsplanung. Die Methoden zur Berechnung der neuen Kapazitaet sind in dieser
 * Klasse verortet. Zudem verfuegt diese Klasse ueber Methoden zur Uebepruefung, ob die Kunstwerke je Raum den 
 * thematischen Anforderungen der Museumsleitung entsprechen.
 * 
 * Dafuer benutzt die Klasse Informationen aus den Klassen "Bild", "Kunstgegenstand", "Kunstinstallation" und 
 * "Ausstellungsplanung".
 * 
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
    
      
    // Methoden der Klasse Raum
    // Set-/Get-Methoden der Attribute von Objekten der Klasse Raum
    /** Die Nummer des Raums setzen/aendern.
     *  @param in_nummer    Die neue Nummer des Raums. (int)
     */
    
        public void setNummer(int in_nummer)
    {   
        // Schreibt den Eingabewert in das Attribut nummer.
        nummer = in_nummer;
    }
    /** Die Nummer des Raumes ermittlen.
     *  @return  Die Nummer des Raums.
     */
    
        public int getNummer()
    { 
        return nummer;
    }
    
    /** Den Namen des Raums setzen/aendern.
     *  @param in_name    Der neue Name des Raums. (String)
     */
    
        public void setName(String in_name)
    {   
        // Schreibt den Eingabewert in das Attribut name.
        name = in_name;
    }
    /** Den Namen des Raumes ermittlen.
     *  @return  Der Namen des Raums.
     */
    
        public String getName()
    { 
        return name;
    }
    
    /** Die Laenge des Raums setzen/aendern.
     *  @param in_laengeRaumInCm    Die neue Laenge des Raums. (int)
     */
    
        public void setLaengeRaumInCm(int in_laengeRaumInCm)
    {   
        // Schreibt den Eingabewert in das Attribut laengeRaumInCm.
        laengeRaumInCm = in_laengeRaumInCm;
    }
    /** Die Laenge des Raumes ermittlen.
     *  @return  Die Laenge des Raums.
     */
    
        public int getLaengeRaumInCm()
    { 
        return laengeRaumInCm;
    }
    
    /** Die Breite des Raums setzen/aendern.
     *  @param in_breiteRaumInCm    Die neue Breite des Raums. (int)
     */
    
        public void setBreiteRaumInCm(int in_breiteRaumInCm)
    {   
        // Schreibt den Eingabewert in das Attribut breiteRaumInCm.
        breiteRaumInCm = in_breiteRaumInCm;
    }
    /** Die Breite des Raumes ermittlen.
     *  @return  Die Breite des Raums.
     */
    
        public int getBreiteRaumInCm()
    { 
        return breiteRaumInCm;
    }
    
    /** Die Hoehe des Raums setzen/aendern.
     *  @param in_hoeheRaumInCm    Die neue Hoehe des Raums. (int)
     */
    
        public void setHoeheRaumInCm(int in_hoeheRaumInCm)
    {   
        // Schreibt den Eingabewert in das Attribut hoeheRaumInCm.
        hoeheRaumInCm = in_hoeheRaumInCm;
    }
    /** Die Hoehe des Raumes ermittlen.
     *  @return  Die Hoehe des Raums.
     */
    
        public int getHoeheRaumInCm()
    { 
        return hoeheRaumInCm;
    }
    
    /** Die Tuerbreite der Nordseite des Raums setzen/aendern.
     *  @param in_tuerNordInCm    Die neue Tuerbreite der Nordseite. (int)
     */
    
        public void setTuerNordInCm(int in_tuerNordInCm)
    {   
        // Schreibt den Eingabewert in das Attribut tuerNordInCm.
        tuerNordInCm = in_tuerNordInCm;
    }
    /** Die Tuerbreite der Nordseite des Raumes ermittlen.
     *  @return  Die Tuerbreite der Nordseite des Raums.
     */
    
        public int getTuerNordInCm()
    { 
        return tuerNordInCm;
    }
    
    /** Die Tuerbreite der Ostseite des Raums setzen/aendern.
     *  @param in_tuerOstInCm    Die neue Tuerbreite der Ostseite. (int)
     */
    
        public void setTuerOstInCm(int in_tuerOstInCm)
    {   
        // Schreibt den Eingabewert in das Attribut tuerOstInCm.
        tuerOstInCm = in_tuerOstInCm;
    }
    /** Die Tuerbreite der Ostseite des Raumes ermittlen.
     *  @return  Die Tuerbreite der Ostseite des Raums.
     */
    
        public int getTuerOstInCm()
    { 
        return tuerOstInCm;
    }
    
    /** Die Tuerbreite der Suedseite des Raums setzen/aendern.
     *  @param in_tuerSuedInCm    Die neue Tuerbreite der Suedseite. (int)
     */
    
        public void setTuerSuedInCm(int in_tuerSuedInCm)
    {   
        // Schreibt den Eingabewert in das Attribut tuerSuedInCm.
        tuerSuedInCm = in_tuerSuedInCm;
    }
    /** Die Tuerbreite der Suedseite des Raumes ermittlen.
     *  @return  Die Tuerbreite der Suedseite des Raums.
     */
    
        public int getTuerSuedInCm()
    { 
        return tuerSuedInCm;
    }
    
    /** Die Tuerbreite der Westseite des Raums setzen/aendern.
     *  @param in_tuerWestInCm    Die neue Tuerbreite der Westseite. (int)
     */
    
        public void setTuerWestInCm(int in_tuerWestInCm)
    {   
        // Schreibt den Eingabewert in das Attribut tuerWestInCm.
        tuerWestInCm = in_tuerWestInCm;
    }
    /** Die Tuerbreite der Westeite des Raumes ermittlen.
     *  @return  Die Tuerbreite der Westseite des Raums.
     */
    
        public int getTuerWestInCm()
    { 
        return tuerWestInCm;
    }
    
    //Methoden zur Unterstützung der Klasse Ausstellungsplanung
    //Methoden relevant zur Planung von Bildern (nur fuer Bilder ist die Tuerbreite relevant)
    /** Ermittelt die verfuegbare Strecke der Wand Nord. 
     *  @return  Die verfuegbare Strecke an der Wand Nord des Raums.
     */
    //  Strecke abzueglich der Breite des Bildes, das dem Raum an Wand Nord hinzuegfuegt wurde.
    //  1m Abstand zu Ecken und zwischen Bildern muss hier beruecksichtigt werden.
    
        public int rechneWandNord()
    {   
        //Code einfuegen für weitere Berechnungsdurchlaeufe (jeweils neu nach erfolgter Belegung durch Bilder).
        //Zugriff auf public Methoden von Klasse Bild durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausstellungsplanung durch Punktoperator         
        return laengeRaumInCm-tuerNordInCm;
    }    
    /** Ermittelt die verfuegbare Strecke der Wand Ost. 
     *  @return  Die verfuegbare Strecke an der Wand Ost des Raums.
     */
    //  Strecke abzueglich der Breite des Bildes, das dem Raum an Wand Ost hinzuegfuegt wurde.
    //  1m Abstand zu Ecken und zwischen Bildern muss hier beruecksichtigt werden.
    
        public int rechneWandOst()
    {   
        //Code einfuegen für weitere Berechnungsdurchlaeufe (jeweils neu nach erfolgter Belegung durch Bilder).
        //Zugriff auf public Methoden von Klasse Bild durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausstellungsplanung durch Punktoperator 
        return breiteRaumInCm-tuerOstInCm;
    }   
    /** Ermittelt die verfuegbare Strecke der Wand Sued 
     *  @return  Die verfuegbare Strecke an der Wand Sued des Raums.
     */
    //  Strecke abzueglich der Breite des Bildes, das dem Raum an Wand Sued hinzuegfuegt wurde.
    //  1m Abstand zu Ecken und zwischen Bildern muss hier beruecksichtigt werden.
    
        public int rechneWandSued()
    {   
        //Code einfuegen für weitere Berechnungsdurchlaeufe (jeweils neu nach erfolgter Belegung durch Bilder).
        //Zugriff auf public Methoden von Klasse Bild durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausstellungsplanung durch Punktoperator 
        return laengeRaumInCm-tuerSuedInCm;
    }
    /** Ermittelt die verfuegbare Strecke der Wand West. 
     *  @return  Die verfuegbare Strecke an der Wand West des Raums.
     */
    //  Strecke abzueglich der Breite des Bildes, das dem Raum an Wand West hinzuegfuegt wurde.
    //  1m Abstand zu Ecken und zwischen Bildern muss hier beruecksichtigt werden.
    
        public int rechneWandWest()
    {   
        //Code einfuegen für weitere Berechnungsdurchlaeufe (jeweils neu nach erfolgter Belegung durch Bilder).
        //Zugriff auf public Methoden von Klasse Bild durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausstellungsplanung durch Punktoperator 
        return breiteRaumInCm-tuerWestInCm;
    } 
    
    //Methoden relevant zur Planung von Kunstgegenstaenden
    /** Ermittelt die verfuegbare Laenge des Raums.
     *  @return  Die verfuegbare Laenge der Raums.
     */
    //  Laenge des Raums abzueglich der Breite oder Laenge des Kunstgegenstandes je nach Ausrichtung der Kunstgegenstandes.
    //  2m Abstand zu Waenden und 1m zwischen Kunstgegenstaenden muessen hier beruecksichtigt werden.
    
        public int RechneLaenge()
    {
        //Code einfuegen für weitere Berechnungsdurchlaeufe (jeweils neu nach erfolgter Belegung durch Kunstgegenstaende).
        //Zugriff auf public Methoden von Klasse Kunstgegenstand durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausstellungsplanung durch Punktoperator 
        return laengeRaumInCm;
    }
    /** Ermittelt die verfuegbare Breite des Raums.
     *  @return  Die verfuegbare Breite der Raums.
     */
    //  Breite des Raums abzueglich der Breite oder Laenge des Kunstgegenstandes je nach Ausrichtung der Kunstgegenstandes.
    //  2m Abstand zu Waenden und 1m zwischen Kunstgegenstaenden muessen hier beruecksichtigt werden.
    
        public int RechneBreite()
    {
        //Code einfuegen für weitere Berechnungsdurchlaeufe (jeweils neu nach erfolgter Belegung durch Kunstgegenstaende).
        //Zugriff auf public Methoden von Klasse Kunstgegenstand durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausstellungsplanung durch Punktoperator 
        return breiteRaumInCm;
    }
    
    //Methode zur Ueberpruefung der Themenverteilung im Raum
    /** Ueberprueft, ob mindestens ein Kunstwerk im Raum dem Schwerpunktthema enstpricht.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
        public boolean pruefeMin1Schwerpuntkthema()
    {
        //Code einfuegen
        //Zugriff auf public Methoden von Klasse Bild, Kunstgegenstand und Kunstinstallation durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausstellungsplanung durch Punktoperator 
        //"true" falls Schwerpunktthema pro Raum >= 1
        //"false" falls verschiedene Themen pro Raum < 1
        return true;
    }
    /** Ueberprueft, ob die Kunstwerke im Raum maximal drei verschiedene Themen vetreten.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
        public boolean pruefeMax3Themen()
    {
        //Code einfuegen
        //Zugriff auf public Methoden von Klasse Bild, Kunstgegenstand und Kunstinstallation durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausstellungsplanung durch Punktoperator 
        //"true" falls verschiedene Themen pro Raum <= 3
        //"false" falls verschiedene Themen pro Raum > 3
        return true;
    }
   
}
