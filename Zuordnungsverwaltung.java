import java.util.Vector;
import java.util.ArrayList;

/**
 * Hier haben wir eine Verwaltungsklasse, die die Gesamtheit der Zuordnungen erfasst.
 * 
 *  TO DO Formulierung Anpassen: Die Verwaltungsklasse "Zuordnungsverwaltung" enthält
    eine Liste von Zuordnungen,
    kann Zuordnungen anlegen und
    könnte Methoden anbieten, bestimmte Zuordnungen abzurufen
    (etwa Alle Zuordnungen zu einem Raum).
 * 
 * Kandidaten für Ausstellungen werden in der ArrayList "listeZuordnungen" verwaltet.
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
    
    public void ausgebenZuordnungsGuetenAufKonsole(){
        System.out.println("\n-------- Übersicht über die Gütenkriterien der Zuordnungen ---");
        
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            double [] ergebnisse= listeZuordnungen.get(z).getZuordnungsErgebnisse();
            System.out.println("Zuordnung Nr." + z + "|" + 
                                "#KWplatziert: " + listeZuordnungen.get(z).wieVieleKunstwerkePlatziert() +  "/" + kunstwerkverwaltung.sizeKunstwerkverwaltung()+  "|" +
                                "ØGueteRaumBelegung: " +  Math.floor(ergebnisse[0]*1000)/1000 +  "|" +        
                                "ØGueteRaumAttraktivitaet: " +  Math.floor(ergebnisse[1]*1000)/1000 +  "|" + 
                                "ØKombinierteGuete: " +  Math.floor(ergebnisse[2]*1000)/1000
                                ); // Math.floor rundet ab zum nächsthöchsten Integer. Durch obige Konstruktion können wir den double Wert auf 3 Nachkommastellen darstellen.      
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
