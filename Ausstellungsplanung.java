import java.util.*;

/**
 * Diese Klasse enthält die Planungslogik auf hohem Level und ist daher eine Geschäftslogikklasse 
 * (während sich die Klasse Zuordnung um die meisten Details kümmert - teils unterstützt durch Kunstwerkveraltung und Raumverwaltung).
 * 
 * - Hier wird die Zuordnungsverwaltung (ZV) initiert und angefordert, dass die ZV so viele Zuordnungen anlegt, wie hier im Parameter anzahlZuordnungen spezifiziert sind.
 * - Es wird gesteuert, dass nach Zuordnungsmappings für die Minimallösung bzw. die Lösungserweiterung gesucht wird:
 * -- Minimallösungen werden gesucht, wenn ein Schwerpunktthema angegeben ist (Schwerpunkt in genau der Hälfte der Räume). 
 * -- Danach werden die Minimallösungen erweitert, indem weitere Platzierungen in den Räumen versuchen werden. Dies wird ohne Schwerpunktthema unmittelbar begonnen.
 * - Schließlich wird eine Methode zum Vergleich von Zuordnungen implementiert, um im eher wahrscheinlichen Fall mehrerer Lösungen die optimale Lösung auszuwählen.
 * - Die Klasse Ausgabedatei benötigt Zugang zum besten Mapping Räume-Kunstwerke, wozu wir eine public Methode anbieten.
 * - Außerdem gibt es hier in Form von getter und setter die Verwaltung für folgende Parameter: 1) Schwerpunktthema 2) Kostenobergrenze 3) Qualitätsgewicht 
 * 
 * @author Thomas Scheidt
 * @version 19.12.2022
 */
public class Ausstellungsplanung
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
    
    private String schwerpunktthema = ""; //Variable um das Schwerpunktthema der Ausstellung festzulegen (mit Default).
                                            //Wenn kein Schwerpunkt gesetzt werden soll, kann leer ("")  übergeben werden.
    private double kostenobergrenze = 999999999; //Legt die Kostenobergrenze der Ausstellung fest (mit Default)
                                            // Wenn keine Kostenobergrenze gesetzt werden soll, dann den Wert von ca 1 Milliarde (neun Mal die 9) übergeben.
    private double qualitaetsgewicht = 0.5; //Gewichtung der Qualität (Werte von 0 bis 1; das Gewicht der Quantität ergibt sich umgekehrt als 1 minus qualitaetsgewicht).
                                           // default 0.5
    
    private Raumverwaltung raumverwaltung;
    private Kunstwerkverwaltung kunstwerkverwaltung;
    private Zuordnungsverwaltung zuordnungsverwaltung;
    
    private int anzahlZuordnungen = 2;
    
    // ==========================================================================
    // === Konstruktor
    // ==========================================================================
   
    /**
     * Konstruktor für Objekte der Klasse Austellungsplanung
     */
    public Ausstellungsplanung(Raumverwaltung in_raumverwaltung, Kunstwerkverwaltung in_kunstwerkverwaltung) 
    {
        raumverwaltung=in_raumverwaltung;
        kunstwerkverwaltung=in_kunstwerkverwaltung;
        
        // Initieerung der Zuordnungsverwaltung
        zuordnungsverwaltung = new Zuordnungsverwaltung(raumverwaltung,kunstwerkverwaltung,this);
    }
    
    // ==========================================================================
    // === Methoden
    // ==========================================================================
    
    /**
     * Methode, um anzustoßen und auf hohem Level zu steuern, dass Zuordnungskandidaten für die Ausstellung erzeugt werden
     * 
     * Die Klasse Zuordnungsverwaltung verwaltet Zuordnungen (d.h. die Kandidaten / verschiedene Planungszustände und insbesondere deren Mappings) in einer 
     * verschachtelten ArrayList. Wir versuchen unabhängig voneinander so viele Mappings erzeugen zu lassen, wie im hiesigen Attribut anzahlZuordnungen bestimmt wird.
     * 
     */
    
    public void generiereAusstellungen()
    {
        // Falls aus vorherigen Durchläufen der Methode schon Zuordnungen bestehen, verwerfen wir diese und fangen wieder ganz von vorne an:
        zuordnungsverwaltung.deleteZuordnungen();
        
        // Erzeuge anzahlZuordnungen neue Zuordnungen in der Zuordnungsverwaltung. Diese Zuordnungen enthalten noch kein Mapping Kunstwerke-Räume.
        zuordnungsverwaltung.fuelleZuordnungsverwaltung(anzahlZuordnungen);
        
        // Jetzt suchen wir nach Mappings Kunstwerke-Räume:
                
        if (schwerpunktthema!="") // mit Schwerpunktthema müssen wir erst versuchen, eine minimale Zuordnung zu finden
        {
            System.out.println("Ein Schwerpunktthema wurde vorgegeben. Wir müssen zuerst versuchen, mindestens eine minimale Zuordnung zu finden");
            
            findeMinimaleAusstellungskandidaten(); // <- die Methode versucht für jede Zuordnung eine Minimallösung zu finden
            wurdeMinimaleAusstellungGefunden(); // <- die Methode prüft, ob es mindestens eine Minimallösung gab
            
            if (wurdeMinimaleAusstellungGefunden()){
                System.out.println("\nERFOLG: Es war uns möglich, mindestens eine minimale Zuordnung zu finden");
                setzeInvalideMinimalloesungenNull(); // Zuordnungen die keine Minimallösung darstellen setzen wir zu null, um diese nachfolgend zu überspringen
                System.out.println("Wir versuchen alle gefundenen Minimallösungen zu erweitern.");
                erweitereAusstellungskandidaten();
            }
            else
            {
                System.out.println("\nMISSERFOLG: Wir konnten überhaupt keine minimale Zuordnung erreichen.");
                System.out.println("Bitte wählen Sie ein anderes Schwerpunktthema oder geben Sie keines vor"); // hiernach ist unsere Methode neu aufzurufen
            }
        }
        else // ohne Schwerpunktthema können wir direkt versuchen, die Ausstellung auszubauen
        {
            System.out.println("ohne Schwerpunktthema können wir direkt versuchen, die Ausstellung auszubauen");
            erweitereAusstellungskandidaten();
        }
        
        // Zusammenfassung der Ergebnisse:
        
    }
    
    /**
     *
     */       
      
    private void findeMinimaleAusstellungskandidaten() 
    {
        for (int i=0;i<anzahlZuordnungen;i++)
        {
            System.out.println("\n-------- Unser " + i +". Versuch eine Minimallösung zu finden ---");
            zuordnungsverwaltung.getZuordnung(i).versucheMinimalloesungZuFinden();
            System.out.println("***** Zuordnungsversuch " + i +" abgeschlossen *****");
        }
    }
    
    /**
     * 
     */
    
    private boolean wurdeMinimaleAusstellungGefunden()
    {
        boolean wurdeGefunden=false;
        for (int i=0;i<anzahlZuordnungen;i++)
        {
            if (zuordnungsverwaltung.getZuordnung(i).wurdeMinimalloesungErreicht()) 
            {
                wurdeGefunden=true;
                break;
            }
        }
        
        return wurdeGefunden;
    }
    
    /**
     * 
     */
    
    private void erweitereAusstellungskandidaten()
    {
        
        for (int i=0;i<anzahlZuordnungen;i++)
        {
            System.out.println("\n-------- Wir versuchen die Ausstellung zu optimieren für Zuordnung " + i +" ---");
            zuordnungsverwaltung.getZuordnung(i).versucheLoesungserweiterung();
            System.out.println("***** Ausstellungsoptimierung für Zuordnung " + i +" beendet *****");
        }
    }
    
    /**
     * Die Klasse Ausgabedatei benötigt Zugang zum besten Mapping Räume-Kunstwerke, wozu wir diese Methode anbieten.
     */
    
    public ArrayList <ArrayList <Kunstwerk>> getBestesMapping ()
    {
        
        return zuordnungsverwaltung.getZuordnung(vergleicheAusstellungskandidatenWaehleBeste()) // den Index der besten Zuordnung ansteuern
                                    .getDenRaeumenZugeordneteKunstwerke(); // und sich von dieser besten Zuordnung nur das Mapping ausgeben lassen
    }
    
    /**
     * Suche nach dem (Index des) besten Mappings
     */
    
    private int vergleicheAusstellungskandidatenWaehleBeste()
    {
        System.out.println("\n-------- Suche nach bester Ausstellung ---");
        
        // Nehmen wir an die allererste Zuordnung ist die beste
        int indexBesteZuordnung=0;
        double zuordnungsGuete=zuordnungsverwaltung.getZuordnung(0).getZuordnungsGuete();
        
        // Nun schauen wir, ob wir nicht noch bessere Zuordnungen finden
        for (int i=0;i<anzahlZuordnungen;i++)
        {   
            double zuVergleichendeZuordnungsGuete=zuordnungsverwaltung.getZuordnung(i).getZuordnungsGuete();
            if (zuVergleichendeZuordnungsGuete>zuordnungsGuete)
            {
                indexBesteZuordnung=i;
                zuordnungsGuete=zuVergleichendeZuordnungsGuete;
            }
        }
        System.out.println("***** Die beste Ausstellung stellt Zuordnung Nr. " + indexBesteZuordnung +" *****");
        System.out.println("(*_*)");
        System.out.println("<> <> <> <> <> <> <> <> <> ");
        return indexBesteZuordnung;
    }       
    
    private void setzeInvalideMinimalloesungenNull()
    {
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            if (!zuordnungsverwaltung.getZuordnung(z).wurdeMinimalloesungErreicht())
            {
                zuordnungsverwaltung.setzeZuordnungNull(z);
            }
        }
    }

    // ==========================================================================
    // === Getter/Setter-Methoden für Schwerpunkt, Kostengrenze, Qualitätsgewicht
    // ==========================================================================
    
    /**
     * Methode um Schwerpunktthema zu setzen. 
     * 
     * @param schwerpunktthema  Wenn kein Schwerpunkt gesetzt werden soll, kann null übergeben werden.
     * 
     */   
    public void setSchwerpunktthema(String in_schwerpunktthema)
    {
        schwerpunktthema=in_schwerpunktthema;
    }
        
    /**
     * Methode um Kostenobergrenze zu setzen.
     * @param kostenobergrenze   Wenn keine Kostenobergrenze gesetzt werden soll, dann z.B. den Wert von ca 1 Milliarde (neun Mal die 9) übergeben. 
     */   
    public void setKostenobergrenze(double in_kostenobergrenze)
    {
        kostenobergrenze=in_kostenobergrenze;
    }
    
    /**
     * Methode um Qualitaetsgewichtung für die Planung zu setzen.
     * @param qualitaetsgewicht   Gewichtung der Qualität (Werte von 0 bis 1; das Gewicht der Quantität ergibt sich umgekehrt als 1 minus qualitaetsgewicht). Default 0.5.
     */   
    public void setQualitaetsgewicht(double in_qualitaetsgewicht)
    {
        qualitaetsgewicht=in_qualitaetsgewicht;
    }

    /**
     * Hierüber kann das Schwerpunktthema der Ausstellung abgefragt werden.
     * 
     * @return schwerpunktthema   Wert des Attributtes schwerpunktthema
     */
    public String getSchwerpunktthema() 
    {
        return schwerpunktthema;
    }
       
    /**
     * Methode zum Abfragen der Kostenobergrenze
     * 
     * @return kostenobergrenze   Wert des Attributs kostenobergrenze
     */
    public double getKostenobergrenze()
    {
        return kostenobergrenze;
    }
    
     /**
     * Hierüber kann das Qualitätsgewicht, das der Ausstellungsplanung zugrunde gelegt wird, abgefragt werden.
     * 
     * @return qualitaetsgewicht   Wert des Attributtes qualitaetsgewicht
     */
    public double getQualitaetsgewicht() 
    {
        return qualitaetsgewicht;
    }
}
  
    
    
  
    
