
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
    
    private String schwerpunktthema = null; //Variable um das Schwerpunktthema der Ausstellung festzulegen
    private double kostenobergrenze = 999999999; //Legt die Kostenobergrenze der Ausstellung fest.
    private Ausleihverwaltung ausleihverwaltung; //Ausleihverwaltung, um bis zwei Ausstellungskandidaten zu speichern/vergleichen. 

    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
        
    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung.
     * 
     * @param kostenobergrenze   Kostenobergrenze als Double. Wenn keine Kostenobergrenze gesetzt werden soll, dann z.B. den Wert von
     *                                                        ca 1 Milliarde (neun Mal die 9) übergeben. 
     * @param schwerpunktthema   Schwerpunktthema als String. Wenn kein Schwerpunkt gesetzt werden soll, kann null übergeben werden.
     */
    public Ausstellungsplanung(String in_schwerpunktthema, double in_kostenobergrenze)
    {
        // Instanzvariable initialisieren
        schwerpunktthema = in_schwerpunktthema; 
        kostenobergrenze = in_kostenobergrenze;
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
     */       
      
    public void generiereAusstellung()
    {
        
        // Lege eine neue Ausleihverwaltung an:
        ausleihverwaltung = new Ausleihverwaltung();
        
        // Hier werden dann ein bis mehrere Methoden der Klasse Ausleihe aufgerufen.
        //ausleihverwaltung[0].
    }
    
    /**
     * Es kann nötig werden, dass das Mapping/die Zuordnung von Räumen und Bildern der vorgesehen Ausstellung nicht alleine "in place" im ersten Objekt "Ausleihe" optimiert werden kann.
     * Dies kann zum Beispiel der Fall sein, wenn man komplexere Vertauschungen in der Zuordnung vornimmt, die sich jedoch als schlechter als die bisherige Lösung herausstellen, sodass man
     * die letzte Zuordnung wiederherstellen möchte. Jedenfalls bietet sich hier die Möglichkeit verschiedene Planungszustände zu speichern; es kann umgesetzt werden, dass man Planungszustände 
     * miteinander vergleicht und schlechtere Planungen verwirft.
     * [aktuell deutet sich an, dass wir ohne diese Methode auskommen können]
     *  
     */       
    public void optimiereAusstellung()
    {
        // ...
        //ausleihverwaltung[0].
        //ausleihverwaltung[1].
        //drop die schlechtere
    }   
    
}
  
    
    
  
    
