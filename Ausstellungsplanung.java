import java.util.*;

/**
 * Die Klasse Ausstellungsplanung entlastet vor allem die zentrale Logikklasse "Ausleihe", indem sie das Schwerpunktthema und die Kostenobergrenze aufnimmt.
 * Außerdem wird hier die Suche nach einer (optimalen) Ausstellung gesteuert.
 * 
 * Die Planungslogik würde sich im Modell aber erst eine Ebene darüber befinden,
in einer Geschäftslogikklasse, die die Beschriebene Logik implementiert.
Sie kann dann Zuordnungsverwaltung initiieren,
darin Zuordnungen erzeugen oder ändern
oder Zuordnungen vergleichen.
 * 
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
        // Initieerung der Zuordnungsverwaltung
        zuordnungsverwaltung = new Zuordnungsverwaltung(anzahlZuordnungen);
        
        raumverwaltung=in_raumverwaltung;
        kunstwerkverwaltung=in_kunstwerkverwaltung;
    }
    
    // ==========================================================================
    // === Methoden
    // ==========================================================================
    
    /**
     * Methode, um anzustoßen und auf hohem Level zu steuern, dass Zuordnungskandidaten für die Ausstellung erzeugt werden, wonach
     * aus ihnen die im Verlgeich beste Lösung ausgesucht wird. 
     * 
     *  Die Klasse Zuordnungsverwaltung verwaltet Zuordnungen (d.h. die Kandidaten / verschiedene Planungszustände und insbesondere deren Mappings) in einem Array.
     *  Dieses kann so viele einzelne Zuordnungen aufnehmen,wie in obigem Attribut anzahlZuordnungen bestimmt wird.
     *  
     *  Die Klasse Ausgabedatei benötigt Zugang zum Mapping Räume-Kunstwerke, daher geben wir dies am Ende aus.
     */
    
    public ArrayList <ArrayList <Kunstwerk>> generiereAusstellung()
    {
        for (int i=0;i<anzahlZuordnungen;i++){
            // erzeuge eine neue Zuordnung als Position i der Zuordnungsliste, übergebe dafür Referenzen auf alle Kunstwerke und alle Räume.
            // Schwerpunktthema und Kostenobergrenze sind Default, wenn nicht anders vorgegegeben.
            zuordnungsverwaltung.addZuordnung(i,kunstwerkverwaltung,raumverwaltung,schwerpunktthema,kostenobergrenze,qualitaetsgewicht);
            
            System.out.println(
               "Lege an Zuordnung Nr."+Integer.toString(i) +
               " für bis zu " + Integer.toString(kunstwerkverwaltung.sizeKunstwerkverwaltung()) +
               " Kunstwerke und " + Integer.toString(raumverwaltung.anzahlRaeume()) + " Räume");
        }
                
        if (schwerpunktthema!="") // mit Schwerpunktthema müssen wir erst versuchen, eine minimale Zuordnung zu finden
        {
            System.out.println("Ein Schwerpunktthema wurde vorgegeben. Wir müssen zuerst versuchen, mindestens eine minimale Zuordnung zu finden");
            
            findeMinimaleAusstellungskandidaten(); // <- die Methode versucht für jede Zuordnung eine Minimallösung zu finden
            wurdeMinimaleAusstellungGefunden(); // <- die Methode prüft, ob es mindestens eine Minimallösung gab
            
            if (wurdeMinimaleAusstellungGefunden()){
                System.out.println("ERFOLG: Es war uns möglich, mindestens eine minimale Zuordnung zu finden");
                System.out.println("Versuche Ausstellung zu erweitern.");
                erweitereAusstellungskandidaten();
            }
            else
            {
                System.out.println("MISSERFOLG: Wir konnten überhaupt keine minimale Zuordnung erreichen.");
                System.out.println("Bitte wählen Sie ein anderes Schwerpunktthema oder geben Sie keines vor");
            }
        }
        else // ohne Schwerpunktthema können wir direkt versuchen, die Ausstellung auszubauen
        {
            System.out.println("ohne Schwerpunktthema können wir direkt versuchen, die Ausstellung auszubauen");
            erweitereAusstellungskandidaten();
        }
        
        return zuordnungsverwaltung.getZuordnung(vergleicheAusstellungskandidatenWaehleBeste()) // den Index der besten Zuordnung ermitteln
                                    .getDenRaeumenZugeordneteKunstwerke(); // und sich von dieser besten Zuordnung nur das Mapping ausgeben lassen
    }
    
    /**
     *
     */       
      
    private void findeMinimaleAusstellungskandidaten() 
    {
        for (int i=0;i<anzahlZuordnungen;i++)
        {
            zuordnungsverwaltung.getZuordnung(i).versucheMinimalloesungZuFinden();
        }
    }
    
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
    
    private void erweitereAusstellungskandidaten()
    {
        System.out.println("Wir suchen nun nach der bestmöglichen Ausstellung");
        for (int i=0;i<anzahlZuordnungen;i++)
        {
            zuordnungsverwaltung.getZuordnung(i).versucheLoesungserweiterung();
        }
    }
    
    private int vergleicheAusstellungskandidatenWaehleBeste()
    {
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
        return indexBesteZuordnung;
    }       
    
    
       
    /**
     * METHODE IST GLAUBE ICH nicht nötig DA ES EIN ARRAY IST DER EINFACH ÜBERSCHRIEBEN WERDEN KANN MIT NEUEN ZUORDNUNGEN!
     */
    public void setzeAusstellungsplanungZurueck(){
        for (int i=0;i<anzahlZuordnungen;i++)
        { 
            zuordnungsverwaltung.deleteZuordnung(i); // löscht alle bisherigen Zuordnungen. Da Elemente sich nicht aus Arrays entfernen lassen, werden sie mit null überschrieben
        }
    }
    
    
    
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
  
    
    
  
    
