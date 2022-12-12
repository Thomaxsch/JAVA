
/**
 * Über diese Klasse werden sämtliche Informationen zu den Partnermuseen erfasst.
 * Darunter fallen der Name und die Adresse der Museen, um den Logistiktransport zu ermöglichen. 
 * 
 * @author (Alexander Kipry) 
 * @version (12.11.2022)
 */
public class Partnermuseum
{
    /**
     * Name des Partnermuseums
     */
    public String namePM;
    
    /** 
     * Straße der Anschrift des Partnermuseums
     */
    
    public String straße;
    
    /**
     * Hausnummer der Anschrift
     */
    
    public int hausnummer;
    
    /**
     * Postleitzahl der Stadt des Partnermuseums
     */
    
    public int plz;
    
    /**
     * Stadt, in der das Partnermuseum ansäßig ist 
     */
    
    public String stadt;

    /**
     * Konstruktor für Objekte der Klasse Partnermuseum
     */
    public Partnermuseum()
    {
        // Instanzvariable initialisieren
        plz = 0;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public String zeigePM(int anschrift)
    {
        // tragen Sie hier den Code ein
        return namePM;       
    }
}
