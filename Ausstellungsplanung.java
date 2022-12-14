import java.util.*;

/**
 * Die Klasse Ausstellungsplanung entlastet vor allem die zentrale Logikklasse "Ausleihe", indem sie das Schwerpunktthema und die Kostenobergrenze aufnimmt.
 * Außerdem wird hier die Suche nach einer (optimalen) Ausstellung gesteuert.
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
    
    private String schwerpunktthema = ""; //Variable um das Schwerpunktthema der Ausstellung festzulegen
    private double kostenobergrenze = 999999999; //Legt die Kostenobergrenze der Ausstellung fest.
    private Raumverwaltung raeume;
    private Kunstwerkverwaltung kunstwerke;
    private int anzahlZuordnungen = 1;
    
    private Zuordnungsverwaltung zuordnungen;
    private Zuordnung zuordnungAktuell;
    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
        
    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung.
     * 
     * @param kostenobergrenze   Kostenobergrenze als Double. Wenn keine Kostenobergrenze gesetzt werden soll, dann z.B. den Wert von
     *                                                        ca 1 Milliarde (neun Mal die 9) übergeben. 
     * @param schwerpunktthema   Schwerpunktthema als String. Wenn kein Schwerpunkt gesetzt werden soll, kann null oder leer ("") übergeben werden.
     */
    public Ausstellungsplanung(String in_schwerpunktthema, double in_kostenobergrenze)
    {
        // Instanzvariable initialisieren
        schwerpunktthema = in_schwerpunktthema; 
        kostenobergrenze = in_kostenobergrenze;
    }
    
    /**
     * Konstruktor für Objekte der Klasse Austellungsplanung ohne Parameter.,.,,..,,.
     */
    public Ausstellungsplanung(Raumverwaltung in_raeume, Kunstwerkverwaltung in_kunstwerke) 
    {
        // Initieerung der Zuordnungsverwaltung
        zuordnungen = new Zuordnungsverwaltung(anzahlZuordnungen);
        
        raeume=in_raeume;
        kunstwerke=in_kunstwerke;
    }
    
    // ==========================================================================
    // === Methoden
    // ==========================================================================
    
    /**
     * Methode um Schwerpunktthema zu setzen. 
     * 
     * @param schwerpunktthema  Wenn kein Schwerpunkt gesetzt werden soll, kann null übergeben werden.
     * 
     */   
    public void set_schwerpunktthema(String in_schwerpunktthema)
    {
        // tragen Sie hier den Code ein
        schwerpunktthema=in_schwerpunktthema;
    }
        
    /**
     * Methode um Kostenobergrenze zu setzen.
     * @param kostenobergrenze   Wenn keine Kostenobergrenze gesetzt werden soll, dann z.B. den Wert von ca 1 Milliarde (neun Mal die 9) übergeben. 
     */   
    public void set_kostenobergrenze(double in_kostenobergrenze)
    {
        // tragen Sie hier den Code ein
        kostenobergrenze=in_kostenobergrenze;
    }
    
    /**
     * Hierüber kann das Schwerpunktthema der Ausstellung abgefragt werden.
     * 
     * @return schwerpunktthema   Wert des Attributtes schwerpunktthema
     */
    public String get_schwerpunktthema() 
    {
        return schwerpunktthema;
    }
       
    /**
     * Methode zum Abfragen der Kostenobergrenze
     * 
     * @return kostenobergrenze   Wert des Attributs kostenobergrenze
     */
    public double get_kostenobergrenze()
    {
        return kostenobergrenze;
    }
    
    /**
     * Methode, um anzustoßen (und ggf. auf hohem Level zu steuern), dass ein Kandidat für die Ausstellung erzeugt und optimiert wird. 
     * Solche Kandidaten werden in der Klasse Ausleihverwaltung in einem array mit (aktuell vorgesehen) zwei Einträgen
     * verwaltet. Das erste Ausleihe-Objekt des Arrays dient zur Umsetzung der Kandidatensuche und enthält auch das Mapping/die Zuordnung von Räumen und Bildern der 
     * vorgesehen Ausstellung. Das zweite (oder weitere) Ausleihe-Objekte des Arrays der Ausleihverwaltung bleiben hier ungenutzt, aber können von der Methode "optimiereAusstellung"
     * genutzt werden.
     *   
     *   Die Planungslogik würde sich im Modell aber erst eine Ebene darüber befinden,
 in einer Geschäftslogikklasse, die die Beschriebene Logik implementiert.
 Sie kann dann Zuordnungsverwaltung initiieren,
 darin Zuordnungen erzeugen oder ändern
 oder Zuordnungen vergleichen.
     */       
      
    public void generiereAusstellungskandidaten()
    {
       for (int i=0;i<anzahlZuordnungen;i++)
       {
           // erzeuge eine neue Zuordnung als Position i der Zuordnungsliste, übergebe dafür Referenzen auf alle Kunstwerke und alle Räume.
           
           System.out.println("Lege an Zuordnung Nr."+Integer.toString(i)
           +" für bis zu " + Integer.toString(kunstwerke.sizeAngebotsverwaltung()) + " Kunstwerke und " );
           System.out.println(Integer.toString(raeume.anzahl()) + " Räume");
           
           zuordnungen.addZuordnung(i,kunstwerke.sortAttraktivitaet(),raeume.getRaumVector());
           
           // rufe die Zuordnung der Position i der Zuordnungsliste ab
           ////zuordnungAktuell = zuordnungen.getZuordnung(i);
           
           //Test: 
           zuordnungAktuell = zuordnungen.getZuordnung(0);
           System.out.println("test: welche Kunstwerke passen in erstes Raumobjekt der Zuordnung?");
           
           for (Kunstwerk k: kunstwerke.sortAttraktivitaet() )
           {
               System.out.println("KW Ldf Nr:" + k.getLaufendeNummer()+" : " +
               zuordnungAktuell.passtKunstwerkDimensionalInRaum(k,1) + " KW Art: " + k.getArt());
           }

        
           // ändere die bestehende Zuordnung
           //////zuordnungen.setZuordnung(i,zuordnungAktuell);
       
        }
        // Hier werden dann ein bis mehrere Methoden der Klasse Ausleihe aufgerufen.
        //ausleihverwaltung[0].
    }
    
    public void vergleicheAusstellungskandidaten()
    {
        // ...
  
    }       
    
    
    /**
     * Es kann nötig werden, dass das Mapping/die Zuordnung von Räumen und Bildern der vorgesehen Ausstellung nicht alleine "in place" im ersten Objekt "Ausleihe" optimiert werden kann.
     * Dies kann zum Beispiel der Fall sein, wenn man komplexere Vertauschungen in der Zuordnung vornimmt, die sich jedoch als schlechter als die bisherige Lösung herausstellen, sodass man
     * die letzte Zuordnung wiederherstellen möchte. Jedenfalls bietet sich hier die Möglichkeit verschiedene Planungszustände zu speichern; es kann umgesetzt werden, dass man Planungszustände 
     * miteinander vergleicht und schlechtere Planungen verwirft.
     * [aktuell deutet sich an, dass wir ohne diese Methode auskommen können]
     *  
     */       
    public void optimiereOderVergleicheAusstellung()
    {
        // ...
        //ausleihverwaltung.get_AusleiheVonAusleihverwaltung(0).
        //ausleihverwaltung.get_AusleiheVonAusleihverwaltung(1).
        //ausleihverwaltung.drop_schlechtesteAusleihe(); //drop die schlechtere, sodass die best Zuordnung an Stelle [0] ist
    }   
    
    /**
     * Die Klasse Ausgabedatei benötigt Zugang zur Zuordnung Räume-Kunstwerke, wie sie in Ausleihverwaltung[0] vorliegt.
     *  
     */       
    public void get_besteZuordnung()
    {
        
        //return Zuordnungsverwaltung.get_Zuordnung(0).get_zugeordneteRaeumeKunstwerke();
    }   
    
       
    
}
  
    
    
  
    
