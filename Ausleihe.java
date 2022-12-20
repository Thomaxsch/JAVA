import java.util.*;
/**
 * Die Klasse Ausleihe ist die zentrale Logikklasse. Hier wird eine Ausstellung/Ausleihe gesucht und optimiert. Ein Schwerpunktthema und eine Kostenobergrenze 
 * aus der Klasse Ausstellungsplanung werden dabei berücksichtigt.  
 * 
 * @author Thomas Scheidt 
 * @version 19.12.2022
 */
public class Ausleihe
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
    
    /////attraktivitaetSumme=0;
    /////kostenSumme=
    
    /**
     * Map,die die Räume und die zugeordneten Kunstwerke enthält. Der Raum wird 
     * als Schlüssel genommen (da eindeutig und nicht mehrmals vorhanden) und die
     * zugeordneten Kunstwerke als Liste von Kunstwerken zu einem Raum als Wert gespeichert.
     */
    private HashMap<Raum, List<Kunstwerk>> zugeordneteRaeumeKunstwerke;
        
    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
      
    /**
     * Konstruktor für Objekte der Klasse Ausleihe
     */
    public Ausleihe()
    {
        
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
