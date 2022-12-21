
/**
 * Die Ausleihverwaltung ist eine Verwaltungsklasse. Kandidaten für Ausstellungen werden in der Klasse Ausleihverwaltung in einem array mit (aktuell vorgesehen) zwei Einträgen verwaltet.
 * Das erste Ausleihe-Objekt des Arrays dient zur Umsetzung der Kandidatensuche und enthält auch das Mapping/die Zuordnung von Räumen und Bildern der vorgesehen Ausstellung. 
 * 
 * Das zweite (oder weitere) Ausleihe-Objekte des Arrays der Ausleihverwaltung bleibt hier zunächst ungenutzt, aber kann von der Methode "optimiereAusstellung" der Ausstellungsplanung genutzt
 * werden. Es kann nötig werden, dass das Mapping/die Zuordnung von Räumen und Bildern der vorgesehen Ausstellung nicht alleine "in place" im ersten Objekt "Ausleihe" optimiert werden kann.
 * Dies kann zum Beispiel der Fall sein, wenn man komplexere Vertauschungen in der Zuordnung vornimmt, die sich jedoch als schlechter als die bisherige Lösung herausstellen, sodass man die
 * letzte Zuordnung wiederherstellen möchte. Jedenfalls bietet sich hier die Möglichkeit verschiedene Planungszustände zu speichern; es kann umgesetzt werden, dass man Planungszustände 
 * miteinander vergleicht und schlechtere Planungen verwirft. [aktuell deutet sich an, dass wir ohne dies auskommen können]
 * 
 * 
 * @author Thomas Scheidt 
 * @version 19.12.2022
 */
public class Ausleihverwaltung
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
     
    private Ausleihe[] listeAusstellungen; // array für die Ausleihen
    
    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
       
    /**
     * Konstruktor für Objekte der Klasse Ausleihverwaltung
     */
    public Ausleihverwaltung()
    {
        listeAusstellungen = new Ausleihe[2]; // zwei Objekte vom Typ Ausleihe im Array
    }
    
    /**
     * Gebe ein bestimmtes Element aus dem Vektor zurück.
     * 
     * @return Ein bestimmtes Element aus Vektor, also eine bestimmte geplante Ausleihe.
     */
    public Ausleihe get_AusleiheVonAusleihverwaltung(int n)
    {
        return listeAusstellungen[n];
    }

    /**
     * Drop die schlechtere Planung, sodass die beste Zuordnung an Stelle [0] ist
     *  
     */
    public void drop_schlechtesteAusleihe()
    {
        ;
    }
    
}
