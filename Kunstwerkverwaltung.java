// Importiert die Klasse Vector
import java.util.Vector;
import java.util.*;
import java.util.Comparator;

/**
 * Bei der Klasse Kunstwerkverwaltung handelt es sich um eine Verwaltungsklasse, welche diverse Funktionen in Bezug auf die Klassen "Kunstinstallationen", "Bild" und "Kunstgegenstand" durchführen kann. 
 * So kann diese die o.g. Klassen nach Objekten durchsuchen und gezielt darauf zugreifen.  
 * Die Kunstwerkverwaltung ist zudem geeignet die Logikklassen gezielt durch Methoden zu unterstützen. 
 * Aufgrunddessen ist sie in der Lage die Klasse der "Ausstellungsplanung" zu entlasten. 
 * 
 * Die Ausstellungsplanung kann sich folglich auf die Kunstwerkverwaltung beziehen, um daraus die richtige Auswahl der Ausstellung und Ausleihe zu treffen. 
 * 
 * @author (Alexander Kipry) 
 * @version (12.01.2023)
*/

public class Kunstwerkverwaltung
{
    /** In diesem Attribut werden die einzelnen Kunstwerke aus dem Angebot mittels der der Containerklasse Vektor verwaltet. */
    private Vector<Kunstwerk> kunstwerkVector;
   
    /**
     * Konstruktor für Objekte der Klasse Kunstwerkverwaltung. Dieser Konstruktor erzeugt ein leeres Angebot, welches später über Methoden mit Kunstwerken befüllt werden kann. 
     */
    public Kunstwerkverwaltung()
    {
       //anlegen eines neuen Vectorobjektes
       kunstwerkVector = new Vector<Kunstwerk>();
    }
    //Methoden für die Vektorliste der Klasse Kunstwerkverwaltung 
    /**
     * Diese Methode fügt dem Vector der Kunstwerkverwaltung ein Kunstwerk hinzu.
     * 
     * @param kunstwerk  Kunstwerk (Kunsinstallation, Bild, Kunstgegenstand) der hinzugefügt werden soll
     */
    public void addKunstwerk(Kunstwerk in_kunstwerk)
    {
        kunstwerkVector.add(in_kunstwerk);
    }
    
    /** 
     * Diese Methode entfernt aus dem Vektor der Kunstwerkverwaltung ein Kunstwerk. 
     * 
     * @param kunstwerk Kunstwerk das gelöscht werden soll
     */
    public void removeKunstwerk(Kunstwerk in_kunstwerk)
    {
        kunstwerkVector.remove(in_kunstwerk);
    }
    
    /** Ermittelt die Anzahl an Kunstwerken in der Vektorliste der Kunstwerkverwaltung .
     *  
     *  @return Die Anzahl der Objekte der Kunstwerk-Klassen in der Kunstwerkverwaltung. 
     */
    public int sizeKunstwerkverwaltung()
    {
        return kunstwerkVector.size();
    }
    
    /** Loescht alle Kunstwerke aus dem Vektor der Kunstwerkverwaltung.
     * 
     */
    public void clearKunstwerkverwaltung()
    {
        kunstwerkVector.clear();
    }
    
    /**
     * Methode zur Umsetzung des Aufrufens aller Kunstwerke innerhalb der Vektorliste 
     * 
     * @return gibt die Möglichkeit zum Zugriff auf alle Kunstwerke aus dem Vector zurück. 
     */
    public void showKunstwerke()
    {
        for(Kunstwerk kw : kunstwerkVector)
        {
            System.out.println(kw);
        }
    }
    
    /**
     * Methode zum Sortieren der Kunstwerke aus dem Vector nach deren Kosten. 
     * 
     * @return Gibt eine nach Kosten sortierte Liste der Kunstwerke innerhalb des Vectors zurück. 
     */
    public void sortKosten()
    {
        Collections.sort(kunstwerkVector);
        for (Kunstwerk kw: kunstwerkVector){
            System.out.println(kw);//Code zum sortieren der Kunstwerke nach den Kosten --> Collections.sort mit Parameter kunstwerk.kosten 
        }
    }
    
    /**
     * Methode zum Sortieren der Kunstwerke aus dem Vector nach deren Attrakvitaet. 
     * 
     * @return Gibt eine nach Attraktivitaet sortierte Liste der Kunstwerke innerhalb des Vectors zurück.
     */
    public Vector<Kunstwerk> sortAttraktivitaet()
    {
        return kunstwerkVector;//Code zum sortieren der Kunstwerke nach der Attraktivitaet --> Collections.sort mit Parameter kunstwerk.attraktivitaetswert 
    }
    
}


