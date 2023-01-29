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
 * 
 * TTO DO* : PLUS DIE BEIDEN VARIATIOSNANALYSE-METHODEN
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
    
    private int anzahlZuordnungen = 10;
    
    private boolean erweiterungsloesungAbgeschlossen = false; // nachdem wir eine Lösungserweiterung vorgenommen haben, wird dies hier vermerkt
    
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
        erweiterungsloesungAbgeschlossen = false; // falls aus Vorzyklus dieser Wert noch true war.
        
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
                zuordnungsverwaltung.ausgebenZuordnungsGuetenAufKonsole();// Zusammenfassung der Ergebnisse aller Zuordnungen
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
            zuordnungsverwaltung.ausgebenZuordnungsGuetenAufKonsole();// Zusammenfassung der Ergebnisse aller Zuordnungen
        }
        
        
    }
    
    /**
     *
     */       
      
    private void findeMinimaleAusstellungskandidaten() 
    {
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            System.out.println("\n-------- Unser " + z +". Versuch eine Minimallösung zu finden ---");
            zuordnungsverwaltung.getZuordnung(z).versucheMinimalloesungZuFinden();
            if (zuordnungsverwaltung.getZuordnung(z).wurdeMinimalloesungErreicht())
            {
                System.out.println("--- Eine Minimallösung liegt in dieser Zuordnung vor.");
            }
            else
            {
                System.out.println("--- Eine Minimallösung liegt in dieser Zuordnung NICHT vor.");
            }
            System.out.println("***** Zuordnungsversuch " + z +" abgeschlossen *****");
        }
    }
    
    /**
     *  ...
     */
    
    private boolean wurdeMinimaleAusstellungGefunden()
    {
        boolean wurdeGefunden=false;
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            if (zuordnungsverwaltung.getZuordnung(z).wurdeMinimalloesungErreicht()) 
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
        
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            if (zuordnungsverwaltung.getZuordnung(z)!=null) // die Prüfung erfolgt, damit wir Zuordnungen ohne Minimallösung nicht weiter auszubauen versuchen
            {
                System.out.println("\n-------- Wir versuchen die Ausstellung zu optimieren für Zuordnung " + z +" ---");
                zuordnungsverwaltung.getZuordnung(z).versucheLoesungserweiterung();
                System.out.println("***** Ausstellungsoptimierung für Zuordnung " + z +" beendet *****");
            }

        }
        erweiterungsloesungAbgeschlossen = true; 
    }
    
    /**
     * Die Klasse Ausgabedatei benötigt Zugang zum besten Mapping Räume-Kunstwerke, wozu wir diese Methode anbieten. Gibt null aus, wenn keine Minimallösung gefunden wurde.
     */
    
    public ArrayList <ArrayList <Kunstwerk>> getBestesMapping ()
    {
        if (erweiterungsloesungAbgeschlossen == true) // Die Auswahl des besten Vorgangs erfolgt nur aus den erweiterten Lösungen.
        {
            return zuordnungsverwaltung.getZuordnung(vergleicheAusstellungskandidatenWaehleBeste()) // den Index der besten Zuordnung ansteuern
                                       .getDenRaeumenZugeordneteKunstwerke(); // und sich von dieser besten Zuordnung nur das Mapping ausgeben lassen
        }
        return null;
    }
    
    /**
     * Suche nach dem (Index des) besten Mappings
     */
    
    private int vergleicheAusstellungskandidatenWaehleBeste()
    {
        if (erweiterungsloesungAbgeschlossen == true) // Die Auswahl des besten Vorgangs erfolgt nur aus den erweiterten Lösungen.
        {
            System.out.println("\n-------- Suche nach bester Ausstellung ---");
            
            // Initialsierung
            int indexBesteZuordnung = -1;
            double zuordnungsGuete = 0;
            
            // Nun schauen wir, welche Zuordnung die beste Zuordnungsgüte hat
            for (int i=0;i<anzahlZuordnungen;i++)
            {   
                double zuVergleichendeZuordnungsGuete = 0; // Initialisierung
                
                if (zuordnungsverwaltung.getZuordnung(i)!=null)
                {
                    zuVergleichendeZuordnungsGuete = zuordnungsverwaltung.getZuordnung(i).getZuordnungsGuete();
                }
                else if (zuordnungsverwaltung.getZuordnung(i)==null)
                {
                    zuVergleichendeZuordnungsGuete = -1; // Wenn es keine Minimallösung gab wurde die Zuordnung Null gesetzt. Dann nehmen wir sie hier aus dem Vergleich
                }
                
                if (zuVergleichendeZuordnungsGuete>zuordnungsGuete)
                {
                    indexBesteZuordnung = i;
                    zuordnungsGuete = zuVergleichendeZuordnungsGuete;
                }
            }
            System.out.println("***** Die beste Ausstellung stellt Zuordnung Nr. " + indexBesteZuordnung +" *****");
            System.out.println("(*_*)");
            System.out.println("<> <> <> <> <> <> <> <> <> ");
            return indexBesteZuordnung;
        }
        return -1;
    }       
    
    private void setzeInvalideMinimalloesungenNull()
    {
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            if (!zuordnungsverwaltung.getZuordnung(z).wurdeMinimalloesungErreicht())
            {
                zuordnungsverwaltung.setzeZuordnungNull(z);
                System.out.println("Habe Zuordnung " + z + " geleert, da für diese keine Minimallösung gefunden wurde. Somit erweitern wir diese Lösung auch nicht.");
            }
        }
    }
    
    public void variationsAnalyse()
    {
        System.out.println("*** Starte Variationsanalyse ***");
        
        String vorherEingestelltesSchwerpunktthema = schwerpunktthema;//vermerke bisher eingstellten Wert, damit wir ihn nach Variation wieder auf diesen Wert rücksetzen können
        
        ArrayList <String> vorkommendeThemen = kunstwerkverwaltung.getVorkommendeThemen(); // wir werden jedes der vorkommenden Thema als Vorgabe eines Variationsdurchlaufs machen
        vorkommendeThemen.add(""); // wir möchten auch den Fall analysieren, dass kein Schwerpunktthema vorgegeben wurde
        
        ArrayList <String> ausgabeJeThema = new ArrayList <String> ();
        
        for (String thema : vorkommendeThemen)
        {
            setSchwerpunktthema(thema); //"Aktmalerei" "Rokoko" "" ...
            
            generiereAusstellungen(); // Bildung mehrerer Zuordnungen und jeweils Lösungssuche
            int i = vergleicheAusstellungskandidatenWaehleBeste(); // gibt Index der besten Zuordnung, falls Erweiterungslösung abgeschlossen wurde; sonst -1
            
            // damit die Ausgabe auf der Konsole schön leserlich wird, müssen wir kürzere Themen mit Whitespace am Ende verlängern
            thema = fuelleSpacesEin(thema,vorkommendeThemen);
            
            if (i>=0)
            {
                double [] ergebnisseBesteAusstellung = zuordnungsverwaltung.getZuordnung(i).getZuordnungsErgebnisse();            
                ausgabeJeThema.add(thema + "|" + 
                                    "#KWplatziert: " + zuordnungsverwaltung.getZuordnung(i).wieVieleKunstwerkePlatziert() +  "/" + kunstwerkverwaltung.sizeKunstwerkverwaltung()+  "|" +
                                    "ØKombinierteGuete%: " +  Math.floor(ergebnisseBesteAusstellung[2]*1000)/10 + "|" +
                                    "ØGueteRaumBelegung%: " +  Math.floor(ergebnisseBesteAusstellung[0]*1000)/10 +  "|" +        
                                    "ØGueteRaumAttraktivitaet%: " +  Math.floor(ergebnisseBesteAusstellung[1]*1000)/10 +  "|" + 
                                    // Math.floor rundet ab zum nächsten Integer. Durch obige Konstruktion können wir den double Wert auf 1 Nachkommastelle darstellen. 
                                    "#Valid.-prob.:" + zuordnungsverwaltung.getZuordnung(i).getAnzahlValidierungsprobleme() +  "|" +
                                    "Budgetverbrauch: " + (int) ergebnisseBesteAusstellung[3] + " €");
                                     
            }
            else if (i<0)
            {
                ausgabeJeThema.add(thema + "|" +
                                    "Keine Minimalzuordnung gefunden.");
            }
        }
        
        System.out.println("\n\n-------- Ergebnisse der Variationsanalyse--------\n");
        System.out.println("Wir geben je Schwerpunkt die - an der kombinierten Güte gemessen - beste erreichte Zuordnung an.");
        System.out.println("Unter anderem wird aufgeführt, wie viele Kunstwerke ingesamt (beliebigen Typs und Themas) platziert wurden und wieviel Budget dafür verbraucht wurde.");
        System.out.println("Es gibt " + (vorkommendeThemen.size()-1) + " mögliche Schwerpunktthemen; die letzte Zeile zeigt den Fall, dass KEIN Schwerpunktthema vorgegeben wird:\n");
        
        // Nun die Ausgabe je Thema je Konsolenzeile:
        for (int t=0; t<ausgabeJeThema.size();t++)
        {
            System.out.println(ausgabeJeThema.get(t));
        }
        
        schwerpunktthema = vorherEingestelltesSchwerpunktthema; // Thema auf das Thema vor der Variation zurücksetzen
        System.out.println("\n*** Variationsanalyse beendet ***");
    }
    
    private String fuelleSpacesEin (String in_thema, ArrayList <String> in_vorkommendeThemen)
    {
        // Zuerst die Zahl der Character des längsten Themas bestimmen
        int maxThemenLaenge=0;
        for (String thema: in_vorkommendeThemen)
        {
            if (thema.length()>maxThemenLaenge)
            {
                maxThemenLaenge=thema.length();
            }
        }
        
        // Nun entsprechend Whitespaces anfügen
        String thema = in_thema;
        while (thema.length()<maxThemenLaenge)
        {
            thema+= " ";
        }

        return thema;
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
  
    
    
  
    
