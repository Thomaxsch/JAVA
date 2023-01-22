import java.util.Vector;

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
    private Zuordnung[] listeZuordnungen; 
    
    
    // ==========================================================================
    // === Konstruktor
    // ==========================================================================
       
    /**
     * Konstruktor für Objekte der Klasse Zuordnungsverwaltung
     */
    public Zuordnungsverwaltung(int in_arraylaenge)
    {
        listeZuordnungen = new Zuordnung[in_arraylaenge]; // Initialisierung des Arrays, sodass es 100 Elemente (vom Typ Zuordnung) aufnehmen kann.

    }
    
    // ==========================================================================
    // === Methoden
    // ==========================================================================
       
    /**
     * Gebe ein bestimmtes Element aus dem Vektor zurück.
     * 
     * @return Ein bestimmtes Element aus Vektor, also eine bestimmte geplante Ausleihe.
     */
    public Zuordnung getZuordnung(int n)
    {
        return listeZuordnungen[n];
    }

    /**
     * Lege die i-te Zuordnung in der Zuordnungsverwaltung neu an
     */
    public void addZuordnung(int arrayposition,
                            Kunstwerkverwaltung in_kunstwerkverwaltung,
                            Raumverwaltung in_raumverwaltung,
                            String in_schwerpunktthema,
                            double in_kostenobergrenze,
                            double in_qualitaetsgewicht)
    {
        listeZuordnungen[arrayposition]=new Zuordnung(in_kunstwerkverwaltung,in_raumverwaltung,in_schwerpunktthema,in_kostenobergrenze,in_qualitaetsgewicht);
    }
    
    /**
     * Leere die ausgewählte Zuordnung
     */
    public void deleteZuordnung(int n)
    {
        listeZuordnungen[n]=null;
    }
    
    
}
