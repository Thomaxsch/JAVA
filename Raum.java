
/**
 * Die Klasse Raum ist eine Datenklasse. Sie beschreibt die verfügbaren Räume des VAWi-Museums Essen. 
 * Jeder Raum verfügt über eigene Bezeichnungsmerkmale und Dimensionen. 
 * 
 * @author Carla Saradeth 
 * @version Dez 20222
 */
public class Raum
{
    // Attribute der Objekte der Klasse Raum
    /** Raeume haben eine laufende Nummer. */
    private int nummer;
    
    /** Raeume haben eine Bezeichnung bzw. Name. */
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

    /**
     * Konstruktor für Objekte der Klasse Raum
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
        // Instanzvariable initialisieren
        
    }

   
    // Methoden für Objekte der Klasse Raum.
    /** Die Nummer des Raums ermitteln.
     * @return  die Nummer des Objektes
     */
    
        public int getNummer(int nummer)
    {
        return nummer;
    } 
    
    /** Den Namen des Raums ermitteln.
     * @return  den Namen des Raums
     */
        public String getName(String name)
    {
        return name;
    }
    
    /** Die Laenge des Raums ermitteln.
     * @return  die Laenge des Raums
     */
        public int getlaengeRaumInCm(int laengeRaumInCm)
    {
        return laengeRaumInCm; 
    }
    
    /** Die Breite des Raums ermitteln.
     * @return  die Breite des Raums
     */
        public int getbreiteRaumInCm(int breiteRaumInCm)
    {
        return breiteRaumInCm; 
    }
    
    /** Die Hoehe des Raums ermitteln.
     * @return  die Hoehe des Raums
     */
        public int gethoeheRaumInCm(int hoeheRaumInCm)
    {
        return hoeheRaumInCm; 
    }
    
    /** Die Tuerbreite der Nordseite des Raums ermitteln.
     * @return  die Tuerbreite der Nordseite
     */
        public int tuerNordInCm(int tuerNordInCm)
    {
        return tuerNordInCm; 
    }
    
    /** Die Tuerbreite der Ostseite des Raums ermitteln.
     * @return  die Tuerbreite der Ostseite
     */
        public int tuerOstInCm(int tuerOstInCm)
    {
        return tuerOstInCm; 
    }
    
    /** Die Tuerbreite der Suedseite des Raums ermitteln.
     * @return  die Tuerbreite der Suedseite
     */
        public int tuerSuedInCm(int tuerSuedInCm)
    {
        return tuerSuedInCm; 
    }
    
    /** Die Tuerbreite der Westseite des Raums ermitteln.
     * @return  die Tuerbreite der Westseite
     */
        public int tuerWestInCm(int tuerWestInCm)
    {
        return tuerWestInCm; 
    }
    
    /* Noch zu erstellende Methoden:
     * 
     * Die verfügbare Strecke der Wand Nord berechnen (relevant für die Aufhängung von Bildern).
     * WandNord = laengeRaumInCm – tuerNordInCm 
     * 
     * Die verfügbare Strecke der Wand Nord berechnen (relevant für die Aufhängung von Bildern).
     * WandOst = breiteRaumInCm – tuerOstInCm 
     * 
     * Die verfügbare Strecke der Wand Nord berechnen (relevant für die Aufhängung von Bildern).
     * WandSued = laengeRaumInCm – tuerSuedInCm 
     * 
     * Die verfügbare Strecke der Wand Nord berechnen (relevant für die Aufhängung von Bildern).
     * WandWest = breiteRaumInCm – tuerWestInCm 
     * 
     */
}
