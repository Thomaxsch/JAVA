import java.util.*;

/**
 * Diese Klasse enthält die Planungslogik auf hohem Level und ist daher eine Geschäftslogikklasse 
 * (während sich die Klasse Zuordnung um die meisten Details kümmert - teils unterstützt durch Kunstwerkveraltung und Raumverwaltung).<pre>
 * 
 * - Hier wird die Zuordnungsverwaltung (ZV) initiert und angefordert, dass die ZV so viele Zuordnungen anlegt, wie hier im Parameter anzahlZuordnungen spezifiziert sind.
 * - Es wird gesteuert, dass nach Zuordnungsmappings für die Minimallösung bzw. die Lösungserweiterung gesucht wird:
 * -- Minimallösungen werden gesucht, wenn ein Schwerpunktthema angegeben ist (Schwerpunkt in genau der Hälfte der Räume). 
 * -- Danach werden die Minimallösungen erweitert, indem weitere Platzierungen in den Räumen versuchen werden. Dies wird ohne Schwerpunktthema unmittelbar begonnen.
 * - Schließlich wird eine Methode zum Vergleich von Zuordnungen implementiert, um im eher wahrscheinlichen Fall mehrerer Lösungen die optimale Lösung auszuwählen.
 * - Die Klasse Ausgabedatei benötigt Zugang zum besten Mapping Räume-Kunstwerke, wozu wir eine public Methode anbieten.
 * - Ähnlich gibt es eine Methode, die die Klasse Ausgabedatei nutzen kann, um für die beste Lösung die Bandbreiten der erlaubten Feuchten/Temperaturen abzurufen
 * - Eine Variationsanalyse, die verschiedene Schwerpunktthemen durchspielt und jeweils die beste Zuordnung ermittelt, ist mit variationsAnalyse und fuelleSpacesEin umgesetzt
 * - Schließlich sorgt die Methode printLog dafür, dass die Klassen Ausstellungsplanung, Zuordnungsverwaltung sowie Zuordnung nur im Debugmode ihr Log auf die Konsole werfen.
 * - Mit einer weiteren Methode switchlogModus schaltet man den Log-Modus an und aus (Default: aus).
 * - Außerdem gibt es hier in Form von getter und setter die Verwaltung für folgende Parameter: 
 *          1) Schwerpunktthema
 *          2) Kostenobergrenze
 *          3) Qualitätsgewicht</pre> 
 * 
 * @author Thomas Scheidt
 * @version 2023
 */
public class Ausstellungsplanung
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
    private String schwerpunktthema = "";          // Variable um das Schwerpunktthema der Ausstellung festzulegen (mit Default kein Schwerpunktthema).
                                                   // Wenn kein Schwerpunkt gesetzt werden soll, kann leer ("")  übergeben werden.
    private double kostenobergrenze = 999999999;   // Legt die Kostenobergrenze der Ausstellung fest (mit Default eine Milliarde)
                                                   // Wenn keine Kostenobergrenze gesetzt werden soll, dann den Wert von ca 1 Milliarde (neun Mal die 9) übergeben.
    private double qualitaetsgewicht = 0.5;        // Gewichtung der Qualität (Werte von 0 bis 1; das Gewicht der Quantität ergibt sich umgekehrt als 1 minus qualitaetsgewicht). Default 0.5.
    
    private Raumverwaltung raumverwaltung;
    private Kunstwerkverwaltung kunstwerkverwaltung;
    private Zuordnungsverwaltung zuordnungsverwaltung;
    
    private int anzahlZuordnungen = 10; // aus Laufzeitengründen passen wir die Zahl in der Methode generiereAusstellungen() noch an
    
    private boolean erweiterungsloesungAbgeschlossen = false; // nachdem wir die Lösungserweiterung vorgenommen haben, wird dies hier vermerkt
    private boolean logModus = false; // Wenn true, werfen die Klassen Ausstellungsplanung, Zuordnungsverwaltung sowie Zuordnung ihr Log auf die Konsole. Sonst nicht.
    
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
        zuordnungsverwaltung = new Zuordnungsverwaltung(raumverwaltung,kunstwerkverwaltung,this); // die Zurordnungsverwaltung nutzt von hier z.B. die Methode printLog
    }
    
    // ==========================================================================
    // === Methoden
    // ==========================================================================
    
    /**
     * Methode, um anzustoßen und auf hohem Level zu steuern, dass Zuordnungskandidaten für die Ausstellung erzeugt werden
     * 
     * Die Klasse Zuordnungsverwaltung verwaltet Zuordnungen (d.h. die Kandidaten / verschiedene Planungszustände und insbesondere deren Mappings) in einer 
     * verschachtelten ArrayList. Wir versuchen unabhängig voneinander so viele Mappings erzeugen zu lassen, wie im hiesigen Attribut anzahlZuordnungen bestimmt wird.
     */
    
    public void generiereAusstellungen()
    {
        // Falls aus vorherigen Durchläufen der Methode schon Zuordnungen bestehen, verwerfen wir diese und fangen wieder ganz von vorne an:
        zuordnungsverwaltung.deleteZuordnungen();
        erweiterungsloesungAbgeschlossen = false; // falls aus Vorzyklus dieser Wert noch true war.
        
        // Aus Performancegründen senken wir die Zahl der Zuordnungen, wenn es sehr viel Kunstwerke gibt, damit die Laufzeit im Rahmen bleibt
        if (kunstwerkverwaltung.sizeKunstwerkverwaltung()>2000)
        {
            anzahlZuordnungen = 2; // bei ca. 7000 KW (Datensatz 7)benötigte: die Variationsanalyse ohne Log ca ?? Minuten / Suche ohne Schwerpunkt mit Log ca 4 Min
                                   // bei ca. 2700 KW (Datensatz 6) benötigte: die Variationsanalyse ohne Log ca 15 Minuten / Suche ohne Schwerpunkt mit Log ca 1.5 Minuten
        }
        else if (kunstwerkverwaltung.sizeKunstwerkverwaltung()>500)
        {
            anzahlZuordnungen = 3; // bei ca. 1300 KW (Datensatz 5) benötigte: die Variationsanalyse ohne Log ca 5 Minuten / Suche ohne Schwerpunkt mit Log einige Sekunden 
        }
        else
        {
            anzahlZuordnungen = 5;
        }
        
        // Erzeuge anzahlZuordnungen neue Zuordnungen in der Zuordnungsverwaltung. Diese Zuordnungen enthalten zum jetzigen Zeitpunkt noch kein Mapping Kunstwerke-Räume.
        zuordnungsverwaltung.fuelleZuordnungsverwaltung(anzahlZuordnungen);
        
        // Jetzt suchen wir nach Mappings Kunstwerke-Räume:
        
        if (schwerpunktthema!="") // mit Schwerpunktthema müssen wir erst versuchen, eine minimale Zuordnung zu finden
        {
            printLog("Ein Schwerpunktthema wurde vorgegeben. Wir müssen zuerst versuchen, mindestens eine minimale Zuordnung zu finden");
            
            findeMinimaleAusstellungskandidaten(); // <- die Methode versucht für jede Zuordnung eine Minimallösung zu finden
            wurdeMinimaleAusstellungGefunden(); // <- die Methode prüft, ob es mindestens eine Minimallösung gab
            
            if (wurdeMinimaleAusstellungGefunden()){
                printLog("\nERFOLG: Es war uns möglich, mindestens eine minimale Zuordnung zu finden");
                setzeInvalideMinimalloesungenNull(); // Zuordnungen die keine Minimallösung darstellen setzen wir zu null, um diese nachfolgend zu überspringen
                printLog("Wir versuchen alle gefundenen Minimallösungen zu erweitern.");
                erweitereAusstellungskandidaten();
                zuordnungsverwaltung.ausgebenZuordnungsGuetenAufKonsole();// Zusammenfassung der Ergebnisse aller Zuordnungen
            }
            else
            {
                printLog("\nMISSERFOLG: Wir konnten überhaupt keine minimale Zuordnung erreichen.");
                printLog("Bitte wählen Sie ein anderes Schwerpunktthema oder geben Sie keines vor"); // hiernach ist unsere Methode neu aufzurufen
            }
        }
        else // ohne Schwerpunktthema können wir direkt versuchen, die Ausstellung auszubauen
        {
            printLog("ohne Schwerpunktthema können wir direkt versuchen, die Ausstellung auszubauen");
            erweitereAusstellungskandidaten();
            zuordnungsverwaltung.ausgebenZuordnungsGuetenAufKonsole();// Zusammenfassung der Ergebnisse aller Zuordnungen
        }
    }
    
    /**
     * Hier wird die Suche nach Minimallösungen gesteuert.
     */       
      
    private void findeMinimaleAusstellungskandidaten() 
    {
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            printLog("\n-------- Unser " + z +". Versuch eine Minimallösung zu finden ---");
            zuordnungsverwaltung.getZuordnung(z).versucheMinimalloesungZuFinden();
            if (zuordnungsverwaltung.getZuordnung(z).wurdeMinimalloesungErreicht())
            {
                printLog("\n--- Eine Minimallösung liegt in dieser Zuordnung vor.");
            }
            else
            {
                printLog("\n--- Eine Minimallösung liegt in dieser Zuordnung NICHT vor.");
            }
            printLog("***** Zuordnungsversuch " + z +" abgeschlossen *****");
        }
    }
    
    /**
     *  Prüfung, ob mindestens eine Minimallösung gefunden wurde
     *  
     *  @return   ob mindestens eine Minimallösung gefunden wurde
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
     * Hier wird gesteuert, dass Mappings Kunstwerke-Räume, die eine Minimallösung darstellen noch so weit es geht optimiert werden (Lösungserweiterung).
     */
    
    private void erweitereAusstellungskandidaten()
    {
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            if (zuordnungsverwaltung.getZuordnung(z)!=null) // die Prüfung erfolgt, damit wir Zuordnungen ohne Minimallösung nicht weiter auszubauen versuchen
            {
                printLog("\n-------- Wir versuchen die Ausstellung zu optimieren für Zuordnung " + z +" ---");
                zuordnungsverwaltung.getZuordnung(z).versucheLoesungserweiterung();
                printLog("***** Ausstellungsoptimierung für Zuordnung " + z +" beendet *****");
            }
        }
        erweiterungsloesungAbgeschlossen = true; 
    }
    
    /**
     * Die Klasse Ausgabedatei benötigt Zugang zum besten Mapping Räume-Kunstwerke, wozu wir diese Methode anbieten.
     * 
     * Gibt null aus, wenn keine Minimallösung gefunden wurde, weil dann erweitereAusstellungskandidaten() im Ablauf der public method generiereAusstellungen()
     * nicht aufgerufen wurde und daher erweiterungsloesungAbgeschlossen = false. Ansonsten wird das beste gefundene Mapping zurückgegeben.
     * 
     * @return      Mapping Räume-Kunstwerke
     */
    
    public ArrayList <ArrayList <Kunstwerk>> getBestesMapping ()
    {
        if (erweiterungsloesungAbgeschlossen == true) // Die Auswahl des besten Vorgangs erfolgt nur aus den erweiterten Lösungen.
        {
            printLog("\n-------- Suche nach bester Ausstellung ---");
            
            int indexBesteZuordnung = vergleicheAusstellungskandidatenWaehleBeste();
                    
            printLog("***** Die beste Ausstellung stellt Zuordnung Nr. " + indexBesteZuordnung +" *****");
            printLog("(*_*)");
            printLog("<> <> <> <> <> <> <> <> <> ");
            
            return zuordnungsverwaltung.getZuordnung(indexBesteZuordnung) // den Index der besten Zuordnung ansteuern
                                       .getDenRaeumenZugeordneteKunstwerke(); // und sich von dieser besten Zuordnung nur das Mapping ausgeben lassen
        }
        return null;
    }
    
    /**
     * Die Klasse Ausgabedatei benötigt für die beste Lösung die Bandbreiten der erlaubten Feuchten/Temperaturen der Räume, wozu wir diese Methode anbieten.  
     * Gibt null aus, wenn keine Minimallösung gefunden wurde. Die Methode wird im selben Zusammenhang mit getBestesMapping () aufgerufen.
     * 
     * Als Input wird ein Element aus ["minFeuchteRaum", "maxFeuchteRaum","minTempRaum","maxTempRaum"] erwartet.
     * 
     * @param welcherAspekt     ein Element aus ["minFeuchteRaum", "maxFeuchteRaum","minTempRaum","maxTempRaum"]
     * @return                  das entsprechende int array für min/max Feuchte/Temp 
     */
    
    public int[] getBestesMappingErlaubteFeuchtenTemperaturen (String welcherAspekt)
    {
        if (erweiterungsloesungAbgeschlossen == true) // Die Auswahl des besten Vorgangs erfolgt nur aus den erweiterten Lösungen.
        {
            return zuordnungsverwaltung.getZuordnung(vergleicheAusstellungskandidatenWaehleBeste()) // den Index der besten Zuordnung ansteuern
                                       .getRangesFeuchtenTemperaturen(welcherAspekt); // und sich von dieser besten Zuordnung nur min/max Feuchte/Temp ausgeben lassen
        }
        return null;
    }
    
    /**
     * Suche nach dem (Index des) besten Mappings
     * 
     * @return Zuodnungsindex des besten Mappings
     */
    private int vergleicheAusstellungskandidatenWaehleBeste()
    {
        if (erweiterungsloesungAbgeschlossen == true) // Die Auswahl des besten Vorgangs erfolgt nur nachdem die Lösungserweiterung für alle Zuordnungen mit Minimallösung abgeschlossen wurde
        {
            // Initialisierung
            int indexBesteZuordnung = -1;
            double zuordnungsGuete = 0;
            
            // Nun schauen wir, welche Zuordnung die beste Zuordnungsgüte hat
            for (int i=0;i<anzahlZuordnungen;i++)
            {   
                double zuVergleichendeZuordnungsGuete = 0; // Initialisierung
                
                if (zuordnungsverwaltung.getZuordnung(i)!=null) // Die Auswahl des besten Vorgangs erfolgt nur aus den erweiterten Lösungen.
                {
                    zuVergleichendeZuordnungsGuete = zuordnungsverwaltung.getZuordnung(i).getZuordnungsGuete();
                }
                else if (zuordnungsverwaltung.getZuordnung(i)==null)
                {
                    zuVergleichendeZuordnungsGuete = -1000; // Wenn es keine Minimallösung gab, wurde die Zuordnung Null gesetzt. Dann nehmen wir sie hier aus dem Vergleich
                }
                
                if (zuVergleichendeZuordnungsGuete>zuordnungsGuete) // Wir suchen ja den Index mit der Zuordnung der besten Güte (klassische Maximumsbestimmung)
                {
                    indexBesteZuordnung = i;
                    zuordnungsGuete = zuVergleichendeZuordnungsGuete;
                }
            }
            return indexBesteZuordnung; // ist als positiver Wert zu erwarten
        }
        return -2; // es ist nicht zu erwarten, dass dies Eintritt
    }       
    
    /**
     * Hier wird gesteuert, dass Zuordnungen, für die keine Minimallösung gefunden wurden, durch Null ersetzt werden (Referenz wird entfernt, ab ins Nirvana).
     * 
     */
    
    private void setzeInvalideMinimalloesungenNull()
    {
        for (int z=0;z<anzahlZuordnungen;z++)
        {
            if (!zuordnungsverwaltung.getZuordnung(z).wurdeMinimalloesungErreicht())
            {
                zuordnungsverwaltung.setzeZuordnungNull(z);
                printLog("Habe Zuordnung " + z + " geleert, da für diese keine Minimallösung gefunden wurde. Somit erweitern wir diese Lösung auch nicht.");
            }
        }
    }
    
    /**
     * Eine Variationsanalyse, die verschiedene Schwerpunktthemen durchspielt und jeweils die beste Zuordnung ermittelt, ist mit variationsAnalyse und fuelleSpacesEin umgesetzt.
     */
    
    public void variationsAnalyse()
    {
        printLog("*** Starte Variationsanalyse ***");
        
        String vorherEingestelltesSchwerpunktthema = schwerpunktthema;//vermerke bisher eingstellten Wert, damit wir ihn nach Variation wieder auf diesen Wert rücksetzen können
        
        ArrayList <String> vorkommendeThemen = kunstwerkverwaltung.getVorkommendeThemen(); // wir werden jedes der vorkommenden Thema als Vorgabe eines Variationsdurchlaufs machen
        vorkommendeThemen.add(""); // wir möchten zusätzlich auch den Fall analysieren, dass kein Schwerpunktthema vorgegeben wurde
        
        ArrayList <String> ausgabeJeThema = new ArrayList <String> (); // was wir als Zusammenfassung mit einer Zeile je Themenszenario nachher im Log sehen möchten sammeln wir hier
        
        for (String thema : vorkommendeThemen)
        {
            setSchwerpunktthema(thema); //"Aktmalerei", "Rokoko", ... , "" 
            
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
        
        // Nun als Zusammenfassung der Variationsanalyse die Ausgabe je Thema je Konsolenzeile:
        for (int t=0; t<ausgabeJeThema.size();t++)
        {
            System.out.println(ausgabeJeThema.get(t));
        }
        
        schwerpunktthema = vorherEingestelltesSchwerpunktthema; // Thema auf das Thema vor der Variation zurücksetzen
        System.out.println("\n-------------------------------------------------\n");
        printLog("*** Variationsanalyse beendet ***");
    }
    
    /**
     * Damit die Ausgabe auf der Konsole schön leserlich wird, müssen wir kürzere Themen mit Whitespace am Ende verlängern. Nur für Zwecke der Darstellung im Log (der Konsole) im Rahmen 
     * der public method variationsAnalyse().
     * 
     * @param in_thema               Thema als String (also gegebenes Schwerpunktthema-Szenario der Variationsanalyse)
     * @param in_vorkommendeThemen   Liste der möglichen Themen, in der die Themen unterschiedliche Längen haben
     * @return                       Thema als String, bei dem die fehlenden Zeichen bis zur Characterlänge des längsten Themas mit Space angefügt wurden
     */
    
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
        
        // Nun entsprechend Whitespaces anfügen, damit kürzere Themen mit Whitespace verlängert werden - sodass sie untereinander im Log eine schöne Tabellenspalte hergeben
        String thema = in_thema;
        while (thema.length()<maxThemenLaenge)
        {
            thema+= " ";
        }

        return thema;
    }
    
    /**
     * Sorgt dafür, dass die Klassen Ausstellungsplanung, Zuordnungsverwaltung sowie Zuordnung nur im Logmodus/Debugmode ihr Log auf die Konsole werfen. Die vorliegende Methode wird
     * daher in den Klassen aufgerufen, anstatt direkt System.out.println. 
     * 
     * @param text    String der im Moment des Programmablaufes auf die Konsole soll, sofern der Logmodus an ist
     */
    public void printLog(String text)
    {
        if (logModus)
        {
            System.out.println(text);
        }
    }
    
    /**
     * Hierüber kann der Log-Modus an/ausgeschaltet werden. Wenn aktiviert (true), werfen die Klassen Ausstellungsplanung, Zuordnungsverwaltung sowie Zuordnung 
     * ihr Log auf die Konsole. Sonst nicht. Default ist false.
     */
    public void switchlogModus() 
    {
        logModus = (!logModus); // Umkehr der bisherigen Einstellung
    }
    
    // ==========================================================================
    // === Getter/Setter-Methoden für Schwerpunkt, Kostengrenze, Qualitätsgewicht
    // ==========================================================================
    
    /**
     * Methode um Schwerpunktthema zu setzen. 
     * 
     * @param in_schwerpunktthema  Wenn kein Schwerpunkt gesetzt werden soll, kann "" übergeben werden.
     */   
    public void setSchwerpunktthema(String in_schwerpunktthema)
    {
        schwerpunktthema=in_schwerpunktthema;
    }
        
    /**
     * Methode um Kostenobergrenze zu setzen.
     * 
     * @param in_kostenobergrenze   Wenn keine Kostenobergrenze gesetzt werden soll, dann kann der Wert von einer Milliarde übergeben werden (neun mal hintereinander die "9"). 
     */   
    public void setKostenobergrenze(double in_kostenobergrenze)
    {
        kostenobergrenze=in_kostenobergrenze;
    }
    
    /**
     * Methode um Qualitaetsgewichtung für die Planung zu setzen.
     * 
     * @param in_qualitaetsgewicht   Gewichtung der Qualität (Werte von 0 bis 1; das Gewicht der Quantität ergibt sich umgekehrt als 1 minus qualitaetsgewicht). Default 0.5.
     */   
    public void setQualitaetsgewicht(double in_qualitaetsgewicht)
    {
        qualitaetsgewicht=in_qualitaetsgewicht;
    }
    
    /**
     * Hierüber kann das Schwerpunktthema der Ausstellung abgefragt werden.
     * 
     * @return    Wert des Attributtes schwerpunktthema
     */
    public String getSchwerpunktthema() 
    {
        return schwerpunktthema;
    }
       
    /**
     * Methode zum Abfragen der Kostenobergrenze
     * 
     * @return    Wert des Attributs kostenobergrenze
     */
    public double getKostenobergrenze()
    {
        return kostenobergrenze;
    }
    
    /**
     * Hierüber kann das Qualitätsgewicht, das der Ausstellungsplanung zugrunde gelegt wird, abgefragt werden.
     * 
     * @return    Wert des Attributtes qualitaetsgewicht
     */
    public double getQualitaetsgewicht() 
    {
        return qualitaetsgewicht;
    }
}
  
    
    
  
    
