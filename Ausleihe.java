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
     * Map,die Räume und ihre im Rahmen der Planung zugeordneten Kunstwerke enthält. Der Raum wird 
     * als Schlüssel genommen (da eindeutig und nicht mehrmals vorhanden) und die
     * zugeordneten Kunstwerke als Liste von Kunstwerken zu einem Raum als Wert gespeichert.
     * Ob bei der Programmierung letztendlich eine HashMap realisiert wird, muss sich bei der Programmierung in SL3 erweisen. 
     * Auf jeden Fall wird aber ein Konstrukt benötigt, welches die Zuordnung der Kunstwerke zu einem Raum speichert. 
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

    // ==========================================================================
    // === Methoden
    // ==========================================================================    
     
    /**
     * Die Methode dient dazu, eine Minimallösung für unser Optimierungsproblem zu finden.
     * 
     * Sie versucht basierend auf den Angeboten der Partnermuseen und Räumen des Museums 
     * eine Zuordnung ausgewählter Kunstwerke vorzunehmen, sodass genau die Hälfte der Räume mit dem Schwerpunktthema besetzt sind.
     * Dabei erfolgt u.a. eine Prüfung ob die Kapazitäten der Räume ausreichend sind.
     * Der aktuelle Planungszustand wird im Attribut zugeordneteRaeumeKunstwerke gespeichert. 
     * 
     */
    public void zuordnenRaumMinimal()
    {
        /////////////////////////////////// Ideen und Übersicht
        // Bausteine für die Umsetzung:
        
        /**
        raumverwaltung.getKunstwerkVector(); // Vector der angebotenenKunstwerke
        angebotsverwaltung.getRaumVector(); // Vector der Räume
        
        raumverwaltung.anzahl(); // Anzahl Räume
        angebotsverwaltung.sizeAngebotsverwaltung(); // Anzahl angebotene Kunstwerke
        

        kunstwerk.setVerplant();   ====> TO DO? // habe ich eingepflegt - Alex
        kunstwerk.getVerplant();   ====> TO DO? // habe ich eingepflegt - Alex
        

        kunstwerk.setPlaziert();
        kunstwerk.getPlaziert();
        */

    }
        
    /**
     * Die Methode dient dazu, die Minimallösung für unser Optimierungsproblem zu optimieren. Für jeden Raum wird u.a. geprüft, 
     * ob noch weitere Kunstwerke in diesen Raum aufgenommen werden können.
     *  
     */
    public void zuordnenRaumOptimieren()
    {
        /////////////////////////////////// Ideen und Übersicht
        //siehe den Fundus oben bei zuordnenRaumMinimal

    }
        
    /**
     * Hierüber können andere Klassen eine Referenz auf den aktuellen Planungszustand in Form der Zuordnung Räume-Kunstwerke bekommen.
     * Hierbei wird kein Abbild übergeben, sondern es handelt sich um pass-by-reference. Das heißt es ist davon auszugehen, dass andere 
     * Klassen über das get in der Lage sind, die Werte der HashMap zu ändern. Dies werden wir jedoch nicht vornehmen, es geht uns in den 
     * anderen Klassen nur um die Möglichkeit für das Lesen.
     * 
     * @return zugeordneteRaeumeKunstwerke   aktuelle Raum-Kunstwerk-Zuordnung
     */
    public HashMap<Raum, List<Kunstwerk>> get_zugeordneteRaeumeKunstwerke() 
    {
        return zugeordneteRaeumeKunstwerke;
    }
      
}
