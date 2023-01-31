
/**
 * Die Klasse "Raum" ist eine Datenklasse. Sie beschreibt die verfuegbaren Räume des VAWi-Museums Essen. 
 * Jeder Raum verfuegt über eigene Bezeichnungsmerkmale und Dimensionen. Sie steht in einer Aggregation 
 * zu der Klasse "Raumverwaltung".
 * 
 * Mit Starten des Programms durch die Klasse Museum wird eine Eingabedatei mit Daten zu den verfuegbaren 
 * Raeumen des VAWi-Museums eingelesen und die Objekte der Klasse "Raum" angelegt. Zusaetzlich gibt es auch 
 * die manuelle Moeglichkeit Objekte der Klasse "Raum" anzulegen oder Eigenschaften von bereits angelegten 
 * Objekten zu aendern.
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
    
        
    //Konstruktoren der Klasse Raum
    /** Konstruktor fuer Objekte der Klasse Raum ohne Parameter.
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
    }

    /** Konstruktor fuer Objekte der Klasse Raum mit Parameter.
     *  @param in_nummer            Dieser int enspricht der Raumnummer.
     *  @param in_name              Dieser String entpricht der Raumbezeichnung.
     *  @param in_laengeRaum        Dieser int entspricht der Raumlaenge (der Nord- und Südwand) in cm.
     *  @param in_breiteRaum        Dieser int entspricht der Raumbreite (der Ost- und Westwand) in cm.
     *  @param in_hoeheRaum         Dieser int entspricht der Raumhoehe in cm.
     *  @param in_tuerNord          Dieser int entspricht der Türbreite an der Nordseite des Raums in cm.
     *  @param in_tuerOst           Dieser int entspricht der Türbreite an der Ostseite des Raums in cm.
     *  @param in_tuerSued          Dieser int entspricht der Türbreite an der Suedseite des Raums in cm.
     *  @param in_tuerWest          Dieser int entspricht der Türbreite an der Westseite des Raums in cm.
     */
    public Raum(int in_nummer,String in_name,int in_laengeRaum,int in_breiteRaum,int in_hoeheRaum,
                int in_tuerNord,int in_tuerOst, int in_tuerSued, int in_tuerWest)
    {
        setNummer(in_nummer);
        setName(in_name);
        setLaengeRaum(in_laengeRaum);
        setBreiteRaum(in_breiteRaum);
        setHoeheRaum(in_hoeheRaum);
        setTuerNord(in_tuerNord);
        setTuerOst(in_tuerOst);
        setTuerSued(in_tuerSued);
        setTuerWest(in_tuerWest);  
    }
    
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
    //Methoden relevant zur Aufhaengung von Bildern
    //=============================================================
    /** Zeigt die verfuegbare Strecke an der Wand Nord des Raums an.
     *  Diese Strecke entspricht der Laenge des Raums abzueglich der Tuerbreite Nord und dem Mindestabstand 
     *  von jeweils 100 cm zu beiden angrenzenden Raumecken.
     *  @return  Die verfuegbare Strecke an der Wand Nord in cm.
     */
    public int showWandNord()
    { 
        int wandNord;
        wandNord=laengeRaum-tuerNord-200;
        return wandNord;
    }
    
    /** Zeigt die verfuegbare Strecke an der Wand Ost des Raums an.
     *  Diese Strecke entspricht der Breite des Raums abzueglich der Tuerbreite Ost und dem Mindestabstand 
     *  von jeweils 100 cm zu beiden angrenzenden Raumecken.
     *  @return  Die verfuegbare Strecke an der Wand Ost in cm.
     */
    public int showWandOst()
    { 
        int wandOst;
        wandOst=breiteRaum-tuerOst-200;
        return wandOst;
    }
    
    /** Zeigt die verfuegbare Strecke an der Wand Sued des Raums an.
     *  Diese Strecke entspricht der Laenge des Raums abzueglich der Tuerbreite Sued und dem Mindestabstand 
     *  von jeweils 100 cm zu beiden angrenzenden Raumecken.
     *  @return  Die verfuegbare Strecke an der Wand Sued in cm.
     */
    public int showWandSued()
    { 
        int wandSued;
        wandSued=laengeRaum-tuerSued-200;
        return wandSued;
    }
   
    /** Zeigt die verfuegbare Strecke an der Wand West des Raums an.
     *  Diese Strecke entspricht der Breite des Raums abzueglich der Tuerbreite West und dem Mindestabstand 
     *  von jeweils 100 cm zu beiden angrenzenden Raumecken.
     *  @return  Die verfuegbare Strecke an der Wand West in cm.
     */
    public int showWandWest()
    { 
        int wandWest;
        wandWest=breiteRaum-tuerWest-200;
        return wandWest;
    }
          
    //========================================================================
    //Methoden relevant zur Planung von Kunstgegenstaenden/Kunstinstallationen
    //========================================================================
    /** Zeigt die verfuegbare Strecke zwischen Wand Ost und Wand West des Raums an.
     *  Diese Strecke entspricht der Laenge des Raums abzueglich dem Mindestabstand von jeweils 200 cm zu beiden 
     *  angrenzenden Waenden.
     *  @return  Die verfuegbare Strecke zwischen Wand Ost und Wand West in cm.
     */
    public int showVerfuegbareLaenge()
    { 
        int verfuegbareLaenge;
        verfuegbareLaenge=laengeRaum-400;
        return verfuegbareLaenge;
    }
    
    /** Zeigt die verfuegbare Strecke zwischen Wand Nord und Wand Sued des Raums an.
     *  Diese Strecke entspricht der Breite des Raums abzueglich dem Mindestabstand von jeweils 200 cm zu beiden 
     *  angrenzenden Waenden.
     *  @return  Die verfuegbare Strecke zwischen Wand Nord und Wand Suedt in cm.
     */
    public int showVerfuegbareBreite()
    { 
        int verfuegbareBreite;
        verfuegbareBreite=breiteRaum-400;
        return verfuegbareBreite;
    }
    
    //========================================================================
    //Methoden relevant zur Planung von allen Kunstwerken
    //========================================================================
    /** Zeigt die verfuegbare Hoehe des Raums fuer B an. Diese Strecke entspricht der Hoehe des Raums abzueglich eines 
     *  Mindestabstandes von jeweils 10 cm zu Boden und Decke (insgesamt 20 cm).
     *  @return Die verfuegbare Hoehe des Raums in cm.
     */
    public int showVerfuegbareHoeheBilder()
    {
        int verfuegbareHoeheBilder;
        verfuegbareHoeheBilder=hoeheRaum-20;
        return verfuegbareHoeheBilder;
    }  
    
    /** Zeigt die verfuegbare Hoehe des Raums fuer I und G an. Diese Strecke entspricht der Hoehe des Raums abzueglich eines 
     *  Mindestabstandes von 10 cm zur Decke.
     *  @return Die verfuegbare Hoehe des Raums in cm.
     */
    public int showVerfuegbareHoehe()
    {
        int verfuegbareHoehe;
        verfuegbareHoehe=hoeheRaum-10;
        return verfuegbareHoehe;
    } 
    
    //==============================
    //Weitere Methoden
    //==============================
    /** Gibt eine lesbare Beschreibung eines Raumes aus (fuer Austellungsdatei)
     *  @return Beschreibung
     */
    @Override
    public String toString()
    {
        return "Nummer: " + this.nummer + ", Raumbezeichnung: " + this.name + ", Breite: " + this.breiteRaum +
        ", Länge: " + this.laengeRaum + ", Höhe: " + this.getHoeheRaum();  
    }
    
    /** Gibt eine lesbare Beschreibung eines Raumes aus (fuer Museumsfuehrer und Ausleihdatei)
     *  @return Beschreibung
     */
    public String toStringKurz()
    {
        return "Nummer: " + this.nummer + ", Raumbezeichnung: " + this.name;  
    }
    
    
}