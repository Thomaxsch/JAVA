
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
 * Zusaetzlich zu den festen Attributen der Raeume (welche aus der Eingabedatei gelesen werden), hat die 
 * Klasse "Raumverwaltung" Attribute, die sich durch die Zuordnungen von Kunstwerken zu Raeumen aendern. 
 * Diese Attribute stellen eine Grundlage zur Planung für die Klasse "Ausstellungsplanung" dar.
 * 
 * Des Weiteren verfuegt die Klasse "Raum" ueber Methoden zur Ueberpruefung, ob die Kunstwerke je Raum den 
 * thematischen Anforderungen der Museumsleitung entsprechen. Dafuer benutzt die Klasse Informationen aus 
 * den Klassen "Kunstwerk", "Bild", "Kunstgegenstand", "Kunstinstallation" und "Ausleihe".
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
    private int laengeRaum;
    
    /** Raeume haben eine Breite (der Ost- und Westwand) in cm. */
    private int breiteRaum;
   
    /** Raeume haben eine Hoehe in cm. */
    private int hoeheRaum;
    
    /** Raeume haben eine Tuerbreite Nordseite in cm. */
    private int tuerNord;
    
    /** Raeume eine Tuerbreite Ostseite in cm. */
    private int tuerOst;
    
    /** Raeume eine Tuerbreite Suedseite in cm. */
    private int tuerSued;
    
    /** Raeume haben eine Tuerbreite Westseite in cm. */
    private int tuerWest;
    
    /** Raeume haben eine aktuelle verfuegbare Strecke Wand Nord in cm. */
    private int wandNord;
    
    /** Raeume haben eine aktuelle verfuegbare Strecke Wand Ost in cm. */
    private int wandOst;
    
    /** Raeume haben eine aktuelle verfuegbare Strecke Wand Sued in cm. */
    private int wandSued;
    
    /** Raeume haben eine aktuelle verfuegbare Strecke Wand West in cm. */
    private int wandWest;
    
    /** Raeume haben eine aktuell verfuegbare Strecke zwischen Wand Ost und Wand West in cm. */
    private int verfuegbareLaenge;
    
    /** Raeume haben eine aktuell verfuegbare Strecke zwischen Wand Nord und Wand Sued in cm. */
    private int verfuegbareBreite;
    
    
    //Konstruktoren der Klasse Raum
    /**
     * Konstruktor fuer Objekte der Klasse Raum ohne Parameter.
     */
    public Raum()
    {  
        //Initialisieren von Attributen
        setNummer(0);
        setName("");
        setLaengeRaum(0);
        setBreiteRaum(0);
        setHoeheRaum(0);
        setTuerNord(0);
        setTuerOst(0);
        setTuerSued(0);
        setTuerWest(0); 
        setWandNord(0);
        setWandOst(0);
        setWandSued(0);
        setWandWest(0);
        setVerfuegbareLaenge(0);
        setVerfuegbareBreite(0);
    }

    /**
     * Konstruktor fuer Objekte der Klasse Raum mit Parameter.
     * 
     * @param nummer            Dieser int enspricht der Raumnummer.
     * @param name              Dieser String entpricht der Raumbezeichnung.
     * @param laengeRaum        Dieser int entspricht der Raumlaenge (der Nord- und Südwand) in cm.
     * @param breiteRaum        Dieser int entspricht der Raumbreite (der Ost- und Westwand) in cm.
     * @param hoeheRaum         Dieser int entspricht der Raumhoehe in cm.
     * @param tuerNord          Dieser int entspricht der Türbreite an der Nordseite des Raums in cm.
     * @param tuerOst           Dieser int entspricht der Türbreite an der Ostseite des Raums in cm.
     * @param tuerSued          Dieser int entspricht der Türbreite an der Suedseite des Raums in cm.
     * @param tuerWest          Dieser int entspricht der Türbreite an der Westseite des Raums in cm.
     */
    public Raum(int nummer,String name,int laengeRaum,int breiteRaum,int hoeheRaum,
                int tuerNord,int tuerOst, int tuerSued, int tuerWest)
    {
        //Initialisieren von Attributen
        setNummer(nummer);
        setName(name);
        setLaengeRaum(laengeRaum);
        setBreiteRaum(breiteRaum);
        setHoeheRaum(hoeheRaum);
        setTuerNord(tuerNord);
        setTuerOst(tuerOst);
        setTuerSued(tuerSued);
        setTuerWest(tuerWest);  
        setWandNord(laengeRaum-tuerNord);
        setWandOst(breiteRaum-tuerOst);
        setWandSued(laengeRaum-tuerSued);
        setWandWest(breiteRaum-tuerWest);
        setVerfuegbareLaenge(laengeRaum);
        setVerfuegbareBreite(breiteRaum);
    }
    
    // Methoden der Klasse Raum
    //=============================================================
    // Set-/Get-Methoden der Attribute von Objekten der Klasse Raum
    //=============================================================
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
     *  @param in_laengeRaum   Die neue Laenge des Raums in cm. (int)
     */
    
        public void setLaengeRaum(int in_laengeRaum)
    {   
        // Schreibt den Eingabewert in das Attribut laengeRaumInCm.
        laengeRaum = in_laengeRaum;
    }
    /** Die Laenge des Raumes ermittlen.
     *  @return  Die Laenge des Raums in cm.
     */
    
        public int getLaengeRaum()
    { 
        return laengeRaum;
    }
    
    /** Die Breite des Raums setzen/aendern.
     *  @param in_breiteRaum    Die neue Breite des Raums in cm. (int)
     */
    
        public void setBreiteRaum(int in_breiteRaum)
    {   
        // Schreibt den Eingabewert in das Attribut breiteRaumInCm.
        breiteRaum = in_breiteRaum;
    }
    /** Die Breite des Raumes ermittlen.
     *  @return  Die Breite des Raums in cm.
     */
    
        public int getBreiteRaum()
    { 
        return breiteRaum;
    }
    
    /** Die Hoehe des Raums setzen/aendern.
     *  @param in_hoeheRaum    Die neue Hoehe des Raums in cm. (int)
     */
    
        public void setHoeheRaum(int in_hoeheRaum)
    {   
        // Schreibt den Eingabewert in das Attribut hoeheRaumInCm.
        hoeheRaum = in_hoeheRaum;
    }
    /** Die Hoehe des Raumes ermittlen.
     *  @return  Die Hoehe des Raums in cm.
     */
    
        public int getHoeheRaum()
    { 
        return hoeheRaum;
    }
    
    /** Die Tuerbreite der Nordseite des Raums setzen/aendern.
     *  @param in_tuerNord    Die neue Tuerbreite der Nordseite in cm. (int)
     */
    
        public void setTuerNord(int in_tuerNord)
    {   
        // Schreibt den Eingabewert in das Attribut tuerNordInCm.
        tuerNord = in_tuerNord;
    }
    /** Die Tuerbreite der Nordseite des Raumes ermittlen.
     *  @return  Die Tuerbreite der Nordseite des Raums in cm.
     */
    
        public int getTuerNord()
    { 
        return tuerNord;
    }
    
    /** Die Tuerbreite der Ostseite des Raums setzen/aendern.
     *  @param in_tuerOst    Die neue Tuerbreite der Ostseite in cm. (int)
     */
    
        public void setTuerOst(int in_tuerOst)
    {   
        // Schreibt den Eingabewert in das Attribut tuerOstInCm.
        tuerOst= in_tuerOst;
    }
    /** Die Tuerbreite der Ostseite des Raumes ermittlen.
     *  @return  Die Tuerbreite der Ostseite des Raums in cm.
     */
    
        public int getTuerOst()
    { 
        return tuerOst;
    }
    
    /** Die Tuerbreite der Suedseite des Raums setzen/aendern.
     *  @param in_tuerSued   Die neue Tuerbreite der Suedseite in cm. (int)
     */
    
        public void setTuerSued(int in_tuerSued)
    {   
        // Schreibt den Eingabewert in das Attribut tuerSuedInCm.
        tuerSued = in_tuerSued;
    }
    /** Die Tuerbreite der Suedseite des Raumes ermittlen.
     *  @return  Die Tuerbreite der Suedseite des Raums in cm.
     */
    
        public int getTuerSued()
    { 
        return tuerSued;
    }
    
    /** Die Tuerbreite der Westseite des Raums setzen/aendern.
     *  @param in_tuerWest    Die neue Tuerbreite der Westseite in cm. (int)
     */
    
        public void setTuerWest(int in_tuerWest)
    {   
        // Schreibt den Eingabewert in das Attribut tuerWestInCm.
        tuerWest = in_tuerWest;
    }
    /** Die Tuerbreite der Westeite des Raumes ermittlen.
     *  @return  Die Tuerbreite der Westseite des Raums in cm.
     */
    
        public int getTuerWest()
    { 
        return tuerWest;
    }
    
    //=============================================================
    //Methoden relevant zur Planung von Bildern
    //=============================================================
    /* Die folgenden Methoden dienen zur Aenderung der akuell verfuegbaren Strecke an den jeweiligen Waenden des Raums. 
     * Die Attribut wandNord und wandSued sind initial die Laenge des Raums abzueglich der 
     * Tuerbreite (falls an der Wand Nord oder Wand Sued eine Tuer vorhanden ist). 
     * Die Attribut wandOst und wandWest sind initial die Breitedes Raums abzueglich der 
     * Tuerbreite (falls an der Wand Nord oder Wand Sued eine Tuer vorhanden ist)
     * Nach jeder Zurordnung eines Bildes an die Waende wird die Breite des Bildes zusaetzlich von der 
     * Strecke abgezogen. Der neue Wert wird in das Attribut wandNord/wandOst/wandSued/wandWest geschrieben, 
     * es stellt die neue aktuell verfuegbare Strecke an der jeweiligen Wand dar. Diese wird nun bei 
     * weiteren Zuordnungen von Bildern benutzt.
     * Zusammengefasst: 
     *   - Strecke der Wand abzueglich der Tuerbreite und Breite des Bildes bzw. der Bilder.
     *   - 1m Abstand zu Raumecken und 1m zwischen Bildern untereinander muessen hier zusaetzlich beruecksichtigt werden.
     *   - Zugriff auf public Methoden von Klasse Ausleihe durch Punktoperator 
     *   - Zugriff auf public Methoden von Klasse Bild durch Punktoperator
     */
        /** Die aktuell verfuegbare Strecke an der Wand Nord des Raums setzen/aendern.
     *  @param in_wandNord    Die neue aktuelle verfuegbare Strecke an der Wand Nord in cm. (int)
     */
       
        public void setWandNord(int in_wandNord)
    {   
        // Schreibt den Eingabewert in das Attribut wandNord.
        wandNord = in_wandNord;
    }
    /** Die aktuell verfuegbare Strecke an der Wand Nord des Raums ermittlen.
     *  @return  Die neue aktuelle verfuegbare Strecke an der Wand Nord in cm.
     */
    
        public int getWandNord()
    { 
        return wandNord;
    }
    
    /** Die aktuell verfuegbare Strecke an der Wand Ost des Raums setzen/aendern.
     *  @param in_wandOst    Die neue aktuelle verfuegbare Strecke an der Wand Ost in cm. (int)
     */
    
        public void setWandOst(int in_wandOst)
    {   
        // Schreibt den Eingabewert in das Attribut wandOst.
        wandOst = in_wandOst;
    }
    /** Die aktuell verfuegbare Strecke an der Wand Ost des Raums ermittlen.
     *  @return  Die neue aktuelle verfuegbare Strecke an der Wand Ost in cm.
     */
    
        public int getWandOst()
    { 
        return wandOst;
    }
    
    /** Die aktuell verfuegbare Strecke an der Wand Sued des Raums setzen/aendern.
     *  @param in_wandSued    Die neue aktuelle verfuegbare Strecke an der Wand Sued in cm. (int)
     */
    
        public void setWandSued(int in_wandSued)
    {   
        // Schreibt den Eingabewert in das Attribut wandSued.
        wandSued = in_wandSued;
    }
    /** Die aktuell verfuegbare Strecke an der Wand Sued des Raums ermittlen.
     *  @return  Die neue aktuelle verfuegbare Strecke an der Wand Sued in cm.
     */
    
        public int getWandSued()
    { 
        return wandSued;
    }
    
    /** Die aktuell verfuegbare Strecke an der Wand West des Raums setzen/aendern.
     *  @param in_wandWest    Die neue aktuelle verfuegbare Strecke an der Wand West in cm. (int)
     */
    
        public void setWandWest(int in_wandWest)
    {   
        // Schreibt den Eingabewert in das Attribut wandWest.
        wandWest = in_wandWest;
    }
    /** Die aktuell verfuegbare Strecke an der Wand West des Raums ermittlen.
     *  @return  Die neue aktuelle verfuegbare Strecke an der Wand West in cm.
     */
    
        public int getWandWest()
    { 
        return wandWest;
    }
     
    //=============================================================
    //Methoden relevant zur Planung von Kunstgegenstaenden
    //=============================================================
    /* Die folgenden Methoden dienen zur Aenderung der akuell verfuegbaren Laenge oder Breite der Raeume. 
     * Das Attribut verfuegbareLaenge ist initial die Laenge des Raums (also die Strecke zwischen Wand Ost und Wand West).
     * Das Attribut verfuegbareBreite ist initial die Breite des Raums (also die Strecke zwischen Wand Nord und Wand Sued).
     * Nach jeder Zurordnung eines Kunstgegenstandes in dem Raum, wird entweder die Laenge oder Breite des Kunstgegenstandes
     * (abhaening von der Ausrichtung des Gegenstandes)zusaetzlich von der verfuegbaren Laenge oder Breite abgezogen.
     * Der neue Wert wird in das Attribut verfuegbareLaenge/verfuegbareBreite geschrieben. Diese wird nun bei weiteren 
     * Zuordnungen von Kunstgegenstaenden benutzt.
     * Zusammengefasst: 
     *   - Laenge/Breite des Raums abzueglich der Laenge oder Breite des Kunstgegenstandes oder der Kunstgegenstaende.
     *   - 2m Abstand zu Waenden und 1m zwischen Kunstgegenstaenden untereinander muessen hier beruecksichtigt werden.
     *   - Zugriff auf public Methoden von Klasse Ausleihe durch Punktoperator 
     *   - Zugriff auf public Methoden von Klasse Kunstgegenstand durch Punktoperator
     */
        /** Die aktuell verfuegbare Strecke zwischen Wand Ost und Wand West setzen/aendern.
     *  @param in_wandWest    Die neue aktuelle verfuegbare Strecke zwischen Wand Ost und Wand West  in cm. (int)
     */
    
        public void setVerfuegbareLaenge(int in_verfuegbareLaenge)
    {   
        // Schreibt den Eingabewert in das Attribut verfuegbareLaenge.
        verfuegbareLaenge = in_verfuegbareLaenge;
    }
    /** Die aktuell verfuegbare Strecke zwischen Wand Ost und Wand West des Raums ermittlen.
     *  @return  Die neue aktuelle verfuegbare Strecke zwischen Wand Ost und Wand West in cm.
     */
    
        public int getVerfuegbareLaenge()
    { 
        return verfuegbareLaenge;
    }
    
    /** Die aktuell verfuegbare Strecke zwischen Wand Nord und Wand Sued setzen/aendern.
     *  @param in_wandWest    Die neue aktuelle verfuegbare Strecke zwischen Wand Nord und Wand Sued in cm. (int)
     */
    
        public void setVerfuegbareBreite(int in_verfuegbareBreite)
    {   
        // Schreibt den Eingabewert in das Attribut verfuegbareBreite.
        verfuegbareBreite = in_verfuegbareBreite;
    }
    /** Die aktuell verfuegbare Strecke zwischen Wand Nord und Wand Sued des Raums ermittlen.
     *  @return  Die neue aktuelle verfuegbare Strecke zwischen Wand Nord und Wand Suedt in cm.
     */
    
        public int getVerfuegbareBreite()
    { 
        return verfuegbareBreite;
    }
    
    //=============================================================
    //Methoden zur Ueberpruefung der Themenverteilung im Raum
    //=============================================================
        /** Ueberprueft, ob mindestens ein Kunstwerk im Raum dem Schwerpunktthema enstpricht.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
        public boolean pruefeMin1Schwerpuntkthema()
    {
        //Code einfuegen
        //Zugriff auf public Methoden von Klasse Bild, Kunstgegenstand und Kunstinstallation durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausleihe durch Punktoperator 
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
        //Zugriff auf public Methoden von Klasse Ausleihe durch Punktoperator 
        //"true" falls verschiedene Themen pro Raum <= 3
        //"false" falls verschiedene Themen pro Raum > 3
        return true;
    }
    /** Ueberprueft, ob noch ein weiteres Thema in einen Raum passt.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
        public boolean pruefeWeiteresThema()
    {
        //Code einfuegen
        //Zugriff auf public Methoden von Klasse Bild, Kunstgegenstand und Kunstinstallation durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausleihe durch Punktoperator 
        //"true" falls verschiedene Themen pro Raum <= 2
        //"false" falls verschiedene Themen pro Raum > 2
        return true;
    }
}
