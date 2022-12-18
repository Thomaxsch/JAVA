import java.util.*;
/**
 * Die Klasse Ausstellungsplanung ist die zentrale Logikklasse. In dieser Klasse wird auf 
 * Grundlage eines Schwerpunktthemas und einer optionalen Kostenobergrenze eine möglichst
 * attraktive Ausstellung geplant und festgehalten. 
 * 
 * @author Mischa Marchlewski 
 * @version 17.12.2022
 */
public class Ausstellungsplanung2
{
    /**
     * Variable um das Schwerpunktthema der Ausstellung festzulegen.
     */
    private String schwerpunktthema;
    /**
     * Legt die Kostenobergrenze für alle Kunstwerke für eine Ausstellung fest.
     */
    private double kostenobergrenze;
    /**
     * Map,die die Räume und die zugeordneten Kunstwerke enthält. Der Raum wird 
     * als Schlüssel genommen (da eindeutig und nicht mehrmals vorhanden) und die
     * zugeordneten Kunstwerke als Liste von Kunstwerken zu einem Raum als Wert gespeichert.
     */
    private HashMap<Raum, List<Kunstwerk>> zugeordneteRaeumeKunstwerke;
    
    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung. Wird verwendet, wenn kein 
     * Schwerpunktthema und keine Kostenobergrenze festgelegt wird.
     */
    public Ausstellungsplanung2()
    {
        
    }
    
    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung. Wird verwendet, wenn ein 
     * Schwerpunktthema aber keine Kostenobergrenze festgelegt wird.
     */
    public Ausstellungsplanung2(String schwerpunktthema)
    {
        
    }
    
      /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung. Wird verwendet, wenn kein 
     * Schwerpunktthema aber eine Kostenobergrenze festgelegt wird.
     */
    public Ausstellungsplanung2(double kostenobergrenze)
    {
        
    }
    
    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung. Als Parameter können ein
     * Schwerpunktthema und eine Kostenobergrenze übergeben werden.
     */
    public Ausstellungsplanung2(String schwerpunktthema, double kostenobergrenze)
    {
        
    }
    
    /**
     * Über diese Methode kann das Attribut schwerpunktthema geändert/gesetzt werden.
     * 
     * @param schwerpunktthema  neues Schwerpunktthema als Text
     */
    public void setSchwerpunktthema(String schwerpunktthema) 
    {
        
    }
    
    /**
     * Über diese Methode kann das Schwerpunktthema der Ausstellung abgefragt werden.
     * 
     * @return schwerpunktthema   Wert des Attributtes schwerpunktthema
     */
    public String getSchwerpunktthema() 
    {
        return this.schwerpunktthema;
    }
    
    /**
     * Methode zum nachträglichen Ändern/Setzen der Kostenobergrenze
     * 
     * @param kostenobergrenze  neue Kostenobergrenze
     */
    public void setKostenobergrenze(double kostenobergrenze)
    {
        
    }
    
    /**
     * Methode zum Abfragen der Kostenobergrenze
     * 
     * @return kostenobergrenze   Wert des Attributs kostenobergrenze
     */
    public double getKostenobergrenze()
    {
        return this.kostenobergrenze;
    }
    
    /**
     * Methode, die die Angebote der Partnermuseen und die Räume des Museums als
     * Parameter entgegennimmt und eine Zuordnung der Kunstwerke aus den Angeboten
     * zu den entsprechenden Räumen vornimmt. Dabei erfolgt eine Prüfung ob die Kapazitäten 
     * der Räume ausreichend sind und stellt die Kunstwerke entsprechend zusammen. Das 
     * Ergebnis wird im Attribut zugeordneteRaeumeKunstwerke gespeichert.Dabei wird auch
     * geprüft ob in einem auch nicht mehr als drei verschiedene Themen vorhanden sind.
     * 
     * @param  raeume    Raumobjekte aus der Raumverwaltung
     * @param  angebot   Kunstwerke aus der Angebotsverwaltung
     */
    public void zuordnenRaum(Raumverwaltung raeume, Angebotsverwaltung angebot)
    {
        // tragen Sie hier den Code ein
        
    }
    
    /**
     * Hilfsmethode zum Sortieren der Angebote nach Attraktivität, wenn kein Schwerpunkt-
     * thema und keine Kostenobergrenze vorliegt.
     * 
     * @param  a                  Angebote aus der Angebotsverwaltung
     * @return s                  sortierte Kunstwerke nach Attraktivität
     */
    private List<Angebotsverwaltung> sortiereNachAttraktivaet(Angebotsverwaltung a)
    {
        List<Angebotsverwaltung> s = new LinkedList<Angebotsverwaltung>();
        return s;
    }
    
    
    
    
}
