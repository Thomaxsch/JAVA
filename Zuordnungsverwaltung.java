import java.util.Vector;
import java.util.ArrayList;

/**
 * NEU:
 * Empfehlen würden wir eine eigene Datenklasse für die einzelne Zuordnung von Raum und Kunstwerk (z.B. „Zuordnung“ oder eben „Ausleihe“) und eine 
Verwaltungsklasse, die die Gesamtheit der Zuordnungen umfasst.
 * 
 * 
 * OLD:
 * 
 * Die Ausleihverwaltung ist eine Verwaltungsklasse. Kandidaten für Ausstellungen werden in der Klasse Ausleihverwaltung in einem array mit (aktuell vorgesehen) zwei Einträgen verwaltet.
 * Das erste Ausleihe-Objekt des Arrays dient zur Umsetzung der Kandidatensuche und enthält auch das Mapping/die Zuordnung von Räumen und Bildern der vorgesehen Ausstellung. 
 * 
 * Das zweite (oder weitere) Ausleihe-Objekte des Arrays der Ausleihverwaltung bleibt hier zunächst ungenutzt, aber kann von der Methode "optimiereAusstellung" der Ausstellungsplanung genutzt
 * werden. Es kann nötig werden, dass das Mapping/die Zuordnung von Räumen und Bildern der vorgesehen Ausstellung nicht alleine "in place" im ersten Objekt "Ausleihe" optimiert werden kann.
 * Dies kann zum Beispiel der Fall sein, wenn man komplexere Vertauschungen in der Zuordnung vornimmt, die sich jedoch als schlechter als die bisherige Lösung herausstellen, sodass man die
 * letzte Zuordnung wiederherstellen möchte. Jedenfalls bietet sich hier die Möglichkeit verschiedene Planungszustände zu speichern; es kann umgesetzt werden, dass man Planungszustände 
 * miteinander vergleicht und schlechtere Planungen verwirft. [aktuell deutet sich an, dass wir ohne dies auskommen können]
 * 
 * /**Die Verwaltungsklasse "Zuordnungsverwaltung" enthält
    eine Liste von Zuordnungen,
    kann Zuordnungen anlegen und
    könnte Methoden anbieten, bestimmte Zuordnungen abzurufen
    (etwa Alle Zuordnungen zu einem Raum).
 * 
 * 
 * @author Thomas Scheidt 
 * @version 19.12.2022
 */
public class Zuordnungsverwaltung
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
    
    // Deklaration eines Arrays für die Zuordnungen:
    private ArrayList <Zuordnung> listeZuordnungen; 
    private int anzahlZuordnungen;
    private Raumverwaltung raumverwaltung;
    private Kunstwerkverwaltung kunstwerkverwaltung;
    private Ausstellungsplanung ausstellungsplanung;
    
    // ==========================================================================
    // === Konstruktor
    // ==========================================================================
       
    /**
     * Konstruktor für Objekte der Klasse Zuordnungsverwaltung
     */
    public Zuordnungsverwaltung (Raumverwaltung in_raumverwaltung,Kunstwerkverwaltung in_kunstwerkverwaltung,Ausstellungsplanung in_ausstellungsplanung)
    {
        listeZuordnungen = new ArrayList <Zuordnung>(); // Initialisierung der Liste
        
        raumverwaltung = in_raumverwaltung;
        kunstwerkverwaltung = in_kunstwerkverwaltung;
        ausstellungsplanung = in_ausstellungsplanung;
    }
    
    // ==========================================================================
    // === Methoden
    // ==========================================================================
       
    /**
     * Gebe ein bestimmtes Element aus der ArrayList zurück.
     * 
     * @return Ein bestimmtes Element aus der ArrayList, also eine bestimmte geplante Ausleihe.
     */
    public Zuordnung getZuordnung(int n)
    {
        return listeZuordnungen.get(n);
    }
    
    /**
     * Fülle die Zuordnungsverwaltung mit anzahlZuordnungen frischen Zuordnungs-Objekten.  
     */
    public void fuelleZuordnungsverwaltung (int in_anzahlZuordnungen){
        anzahlZuordnungen=in_anzahlZuordnungen;
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            listeZuordnungen.add(new Zuordnung(kunstwerkverwaltung,raumverwaltung,
                                              ausstellungsplanung.getSchwerpunktthema(),ausstellungsplanung.getKostenobergrenze(),ausstellungsplanung.getQualitaetsgewicht()));
            System.out.println(
               "Lege an Zuordnung Nr."+Integer.toString(z) +
               " für bis zu " + Integer.toString(kunstwerkverwaltung.sizeKunstwerkverwaltung()) +
               " Kunstwerke und " + Integer.toString(raumverwaltung.anzahlRaeume()) + " Räume");
        }
        
    }
    
    // ==========================================================================
    // === Methoden zum Zurücksetzen/Überspringen von Zuordnungen
    // ==========================================================================
       
    /**
     * Setze für die ausgewählte Zuordnung "null"
     */
    public void setzeZuordnungNull(int z)
    {
        listeZuordnungen.set(z,null); //setzt die Zuordnung zu null
    }
    
    /**
     * Entferne alle Zuordnungen
     */
    public void deleteZuordnungen()
    {
        listeZuordnungen.removeAll(listeZuordnungen); // löscht alle Elemente die in Klammern stehen aus listeZuordnungen => alle Elemente der List
    }
}
