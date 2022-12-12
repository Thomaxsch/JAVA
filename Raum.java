
/**
 * Die Klasse Raum beschreibt die verfügbaren Räume des VAWi-Museums Essen.  
 * 
 * @author (Carla Saradeth) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Raum
{
    // Attribute der Objekte der Klasse Raum
    /** Objekte haben eine laufende Nummer. */
    private int nummer;
    
    /** Objekte eine Bezeichnung. */
    private String name;
    
    /** Objekte haben eine Laenge (der Nord- und Südwand) in cm. */
    private int laengeInCm;
    
    /** Objekte haben eine Breite (der Ost- und Westwand) in cm. */
    private int breiteInCm;
   
    /** Objekte haben eine Hoehe in cm. */
    private int hoeheInCm;
    
    /** Objekte haben eine Tuerbreite Nordseite in cm. */
    private int tuerNordInCm;
    
    /** Objekte eine Tuerbreite Ostseite in cm. */
    private int tuerOstInCm;
    
    /** Objekte eine Tuerbreite Suedseite in cm. */
    private int tuerSuedInCm;
    
    /** Objekte haben eine Tuerbreite Westseite in cm. */
    private int tuerWestInCm;

    /**
     * Konstruktor für Objekte der Klasse Raum
     */
    public Raum()
    {
        // Instanzvariable initialisieren
        
    }

   
    // Methoden für Objekte der Klasse Raum. 
    /**
     * Die Nummer des Objektes ermitteln.
     * @return   die Nummer des Objektes
     */
    
    public int getNummer(int nummer)
    {
        return nummer;
    }
        
    public String getName(String nummer)
    {
        return name;
    }
    
}
