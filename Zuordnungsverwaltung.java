import java.util.Vector;
import java.util.ArrayList;

/**
 * Hier haben wir eine Verwaltungsklasse, die die Gesamtheit der Zuordnungen erfasst. Solche Kandidaten für Ausstellungen werden in der ArrayList "listeZuordnungen" verwaltet.<pre>
 * 
 *  Die Verwaltungsklasse "Zuordnungsverwaltung" enthält diese Liste von Zuordnungen,
 *    -- kann mittels der public Methode fuelleZuordnungsverwaltung Zuordnungen anlegen,
 *    -- bietet die public Methode getZuordnung an, damit andere Methoden die Referenz auf eine bestimmte Zuordnung aus der Zuordungsliste erhalten,
 *    -- hat eine public Methode ausgebenZuordnungsGuetenAufKonsole zur Darstellung von Ergebnisaspekten aller Zuordnungen im Log (d.h. der Konsole),
 *    -- kann einzelne Zuordnungen vergessen, indem es die Referenz auf Null setzt, damit gescheiterte Minimallösungen nicht weiter verfolgt werden,
 *    -- oder alle Zuordnungen vergessen, um ganz neu und von Grund auf mit der Ausstellungssuche zu beginnen (z.B. nachdem ein Parameter wie das Qualitätsgewicht geändert wurde)</pre>
 * 
 * @author Thomas Scheidt 
 * @version 2023
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
    // === Methoden zur Generierung, Suche und Darstellung
    // ==========================================================================
       
    /**
     * Fülle die Zuordnungsverwaltung mit der vorgegebenen Anzahl an frischen Zuordnungs-Objekten. 
     * 
     * @param in_anzahlZuordnungen     wie viele potentielle Ausstellungskandidaten (Zuordnungen) soll es geben
     */
    public void fuelleZuordnungsverwaltung (int in_anzahlZuordnungen)
    {
        anzahlZuordnungen=in_anzahlZuordnungen;
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            listeZuordnungen.add(new Zuordnung(kunstwerkverwaltung,raumverwaltung,ausstellungsplanung)); // übergibt an die Zuordnungsobjekte auch einige Referenzen
            ausstellungsplanung.printLog(
               "Lege an Zuordnung Nr."+Integer.toString(z) +
               " für bis zu " + Integer.toString(kunstwerkverwaltung.sizeKunstwerkverwaltung()) +
               " Kunstwerke und " + Integer.toString(raumverwaltung.anzahlRaeume()) + " Räume");
        }
    }
    
    /**
     * Gebe ein bestimmtes Element aus der ArrayList zurück.
     * 
     * @param n     der Index der Zuordnung in der Zuordnungsliste
     * @return      ein bestimmtes Element aus der ArrayList, also eine bestimmte geplante Ausleihe.
     */
    public Zuordnung getZuordnung(int n)
    {
        return listeZuordnungen.get(n);
    }
    
    /**
     * Darstellung von Ergebnisaspekten aller Zuordnungen im Log (d.h. der Konsole).
     */
    public void ausgebenZuordnungsGuetenAufKonsole()
    {
        ausstellungsplanung.printLog("\n-------- Übersicht über die Gütenkriterien der Zuordnungen ---");
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            double [] ergebnisse= getZuordnung(z).getZuordnungsErgebnisse();
            ausstellungsplanung.printLog("Zuordnung Nr." + z + "|" + 
                                "#KWplatziert: " + getZuordnung(z).wieVieleKunstwerkePlatziert() +  "/" + kunstwerkverwaltung.sizeKunstwerkverwaltung()+  "|" +
                                "#Valid.-prob.:" + getZuordnung(z).getAnzahlValidierungsprobleme() + "|" +
                                "ØGueteRaumBelegung%: " +  Math.floor(ergebnisse[0]*1000)/10 +  "|" +        
                                "ØGueteRaumAttraktivitaet%: " +  Math.floor(ergebnisse[1]*1000)/10 +  "|" + 
                                "ØKombinierteGuete%: " +  Math.floor(ergebnisse[2]*1000)/10 +  "|" +
                                // Math.floor rundet ab zum nächsten Integer. Durch obige Konstruktion können wir den double Wert auf 1 Nachkommastelle darstellen. 
                                "Budgetverbrauch: " + (int) ergebnisse[3] + " €");                
        }
    }
    
    // ==========================================================================
    // === Methoden zum Zurücksetzen/Überspringen von Zuordnungen
    // ==========================================================================
       
    /**
     * Setze für die ausgewählte Zuordnung "null". Damit kann die Ausstellungsplanung sicherstellen, dass nicht Zuordnungen ausgebaut werden, für die es keine Minimallösung gab,
     * oder diese gar für die beste Zuordnung in Frage kommen (werden aus der Suche nach der besten Zuordnung genommen).
     * 
     * @param z    der Index der Zuordnung in der Zuordnungsliste
     */
    public void setzeZuordnungNull(int z)
    {
        listeZuordnungen.set(z,null); //setzt die Zuordnung zu null
    }
    
    /**
     * Entferne alle Zuordnungen, um ganz clean anfangen zu können.
     */
    public void deleteZuordnungen()
    {
        listeZuordnungen.removeAll(listeZuordnungen); // löscht alle Elemente die in Klammern stehen aus listeZuordnungen => alle Elemente der Liste
    }
}
