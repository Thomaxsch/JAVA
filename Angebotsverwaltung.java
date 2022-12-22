// Importiert die Klasse Vector
import java.util.Vector;

/**
 * Bei der Klasse Angebotsverwaltung handelt es sich um eine Verwaltungsklasse, welche diverse Funktionen in Bezug auf die Klassen "Kunstinstallationen", "Bild" und "Kunstgegenstand" durchführen kann. 
 * So kann diese die o.g. Klassen nach Objekten durchsuchen und gezielt darauf zugreifen.  
 * Die Angebotsverwaltung ist zudem geeignet die aus dem Angebot hervorgehenden Kunstwerke nach Attraktivität zu sortieren
 * Aufgrunddessen ist sie in der Lage die Klasse der "Ausstellungsplanung" zu entlasten. 
 * 
 * Die Ausstellungsplanung kann sich folglich auf die Angebotsverwaltung beziehen, um daraus die richtige Auswahl der Ausstellung und Ausleihe zu treffen. 
 * 
 * @author (Alexander Kipry) 
 * @version (18.12.2022)
*/

public class Angebotsverwaltung
{
    /** In diesem Attribut werden die einzelnen Kunstwerke aus dem Angebot mittels der der Containerklasse Vektor verwaltet. */
    private Vector<Kunstwerk> kunstwerkVector;
   
    /**
     * Konstruktor für Objekte der Klasse Angebotsverwaltung. Dieser Konstruktor erzeugt ein leeres Angebot, welches später über Methoden mit Kunstwerken befüllt werden kann. 
     */
    public Angebotsverwaltung()
    {
       //anlegen eines neuen Vectorobjektes
       kunstwerkVector = new Vector<Kunstwerk>();
    }
    //Methoden für die Verwaltung der Klasse Angebotsverwaltung
    /**
     * Diese Methode fügt der Angebotsverwaltung ein Kunstwerk hinzu.
     * 
     * @param kunstwerk  Kunstwerk (Kunsinstallation, Bild, Kunstgegenstand) der hinzugefügt werden soll
     */
    public void addKunstwerk(Kunstwerk kunstwerk)
    {
        //Code zum Hinzufügen eines Kunstwerkes (Kunstinstallation, Bild, Kunstgegenstand)
    }
    /** 
     * Diese Methode entfernt aus der Angebotsverwaltung ein Kunstwerk. 
     * 
     * @param kunstwerk Kunstwerk das gelöscht werden soll
     */
    public void removeKunstwerk(Kunstwerk kunstwerk)
    {
        //Code zum Entfernen eines Kunstwerkes (Kunstinstallation, Bild, Kunstgegenstand)
    }
    /** Ermittelt die Anzahl an Kunstwerken in der Angebotsverwaltung .
     *  
     *  @return Die Anzahl der Objekte der Kunstwerk-Klassen in der Angebotsverwaltung. 
     */
    public int sizeAngebotsverwaltung()
    {
        return kunstwerkVector.size();
    }
    /** Loescht alle Kunstwerke aus der Angebotsverwaltung.
     * 
     */
    public void clearAngebotsverwaltung()
    {
        //Code zum Löschen aller Kunstwerke aus der Angebotsverwaltung
    }
    
    /**
     * Methode zum Aufrufen aller Kunstwerke innerhalb der Vektorliste 
     * 
     * @return gibt alle Kunstwerke aus dem Vector zurück. 
     */
    public Vector<Kunstwerk> getKunstwerk()
    {
        return kunstwerkVector;
    }
    


}


