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
     * Konstruktor für Objekte der Klasse Kunstwerkverwaltung. Dieser Konstruktor erzeugt ein leeren Vector / Angebot, welches später über Methoden mit Kunstwerken befüllt werden kann. 
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
        if (!kunstwerkVector.contains(in_kunstwerk)){ // das Kunstwerk kann sich nur einmal im Vector befinden. 
            kunstwerkVector.add(in_kunstwerk);
        }
        else {
            System.out.println("Kunstwerk ist bereits in der Kunstwerkverwaltung enthalten. Es kann nicht erneut hinzugefuegt werden");
        }
        
    }
    
    /** 
     * Diese Methode entfernt aus dem Vektor der Kunstwerkverwaltung ein Kunstwerk. 
     * 
     * @param kunstwerk Kunstwerk das gelöscht werden soll
     */
    public void removeKunstwerk(Kunstwerk in_kunstwerk)
    {
        if (kunstwerkVector.contains(in_kunstwerk)){ // das Kunstwerk muss bereits im Vector vorhanden sein, um es daraus zu entfernen.
            kunstwerkVector.removeElement(in_kunstwerk);
        }
        else {
            System.out.println("Kunstwerk ist nicht in der Kunstwerkverwaltung enthalten. Es kann nicht entfernt werden");
        }
    }   
    
    /** Ermittelt die Anzahl an Kunstwerken in der Vektorliste der Kunstwerkverwaltung .
     *  
     *  @return Die Anzahl der Objekte der Kunstwerk-Klassen in der Kunstwerkverwaltung. 
     */
    public int sizeKunstwerkverwaltung()
    {
        return kunstwerkVector.size();
    }
    
    /** 
     * Loescht alle Kunstwerke aus dem Vektor der Kunstwerkverwaltung.
     */
    public void clearKunstwerkverwaltung()
    {
        kunstwerkVector.clear();
    }
    
    /**
     * Methode zur Umsetzung des Aufrufens aller Kunstwerke innerhalb der Vektorliste
     * @return den Vector der Kunstwerkverwaltung
     */
    public Vector <Kunstwerk> getKunstwerkVector()
    {
        return kunstwerkVector;
    }
    
     /**
     * Gibt eine textuelle Beschreibung aller Kunstwerke im Vector aus 
     */
    public void showKunstwerke()
    {
        for(Kunstwerk kw : kunstwerkVector) 
        {
            System.out.println(kw); // hierbei wird automatisch auf die "toString" Methode aus der Klasse Kunstwerk zugegriffen
        }
    } 
    
    //Methoden für die Zuordnung
    /**
     * Methode zum Sortieren der Kunstwerke aus dem Vector nach deren Attrakvitaet. 
     * 
     * @return den Vector sortiert nach der Attraktvitaet in absteigender Reihenfolge. 
     */
    public Vector <Kunstwerk> sortAttraktivitaet()
    {
        Collections.sort(kunstwerkVector, Collections.reverseOrder()); 
        //das "reverseOrder" sorgt dafür, dass das Kunstwerk mit der höchsten Attraktivitaet zu erst angezeigt wird. 
        return kunstwerkVector;
        }   
    
    /**
     * Methode zum Ermitteln eines Kunstwerkes anhand seiner laufendenNummer
     * 
     * @return das Kunstwerk zur laufendenNummer
     */
    public Vector <Kunstwerk> showKunstwerkZuLaufendeNummer(short in_laufendeNummer) {
    boolean found = false;
    for (Kunstwerk kw : kunstwerkVector) {
        if (kw.getLaufendeNummer() == in_laufendeNummer) 
        {
            System.out.println(kw);
            found = true;
        }   
    }   
    if(!found)
        {
        System.out.println("Es wurde kein Kunstwerk zur LaufendenNummer gefunden");
        return null;
        }
        return kunstwerkVector;
    }  
    
    /**
     * Methode, welche überprüft, ob noch genügend Platz für den Kunstgegenstand in einem Raum übrig ist
     * 
     * @return false = kein Platz vorhanden. true = ausreichend Platz vorhanden. 
     */
    public boolean checkPlatzfürKG (int in_hoehe, int in_breite)
    {
        return false; //muss noch geschrieben werden
    }
    /**
     * Methode, welche überprüft, ob noch genügend Platz für das Bild in einem Raum übrig ist
     * 
     * @return false = kein Platz vorhanden. true = ausreichend Platz vorhanden
     */
    public boolean checkPlatzfürBild (int in_hoehe, int in_breite)
    {
        return false; //muss noch geschrieben werden
    }
    
}


