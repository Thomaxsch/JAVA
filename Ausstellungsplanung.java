
/**
 * Beschreiben Sie hier die Klasse Ausstellungsplanung.
 * 
 * @author (Thomas Scheidt) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Ausstellungsplanung
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
    
    private String schwerpunktthema; //Variable um das Schwerpunktthema der Ausstellung festzulegen.
    private double kostenobergrenze; //Legt die Kostenobergrenze der Ausstellung fest.
    private Ausleihverwaltung ausleihverwaltung; //Ausleihverwaltung, um bis zwei Ausstellungskandidaten zu speichern/vergleichen. 

    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
        
    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung.
     * 
     * @param kostenobergrenze   Kostenobergrenze als Double. Wenn keine Kostenobergrenze gesetzt werden soll, dann z.B. den Wert von
     *                                                        ca 1 Milliarde (neun Mal die 9) übergeben. 
     */
    public Ausstellungsplanung(String in_schwerpunktthema, double in_kostenobergrenze)
    {
        // Instanzvariable initialisieren
        schwerpunktthema = null; //default
        kostenobergrenze = 999999999; //default
    }
    
    // ==========================================================================
    // === Methoden
    // ==========================================================================
    
    /**
     * Methode um Schwerpunktthema zu setzen.
     * @param schwerpunktthema   Schwerpunktthema als String. Wenn kein Schwerpunkt gesetzt werden soll, kann null übergeben werden.
     */   
    public void set_schwerpunktthema(String in_schwerpunktthema)
    {
        // tragen Sie hier den Code ein
        schwerpunktthema=in_schwerpunktthema;
    }
        
    /**
     * Methode um Kostenobergrenze zu setzen.
     * @param kostenobergrenze   Kostenobergrenze als Double. Wenn keine Kostenobergrenze gesetzt werden soll, dann z.B. den Wert von
     *                                                        ca 1 Milliarde (neun Mal die 9) übergeben. 
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
    public String get_Schwerpunktthema() 
    {
        return schwerpunktthema;
    }
       
    /**
     * Methode zum Abfragen der Kostenobergrenze
     * 
     * @return kostenobergrenze   Wert des Attributs kostenobergrenze
     */
    public double get_Kostenobergrenze()
    {
        return kostenobergrenze;
    }
}
  
    
    
  
    
    public void generiereAusstellung(int y)
    {
        
        // Lege eine neue Ausleihverwaltung an:
        ausleihverwaltung = new Ausleihverwaltung();
        
        // tragen Sie hier den Code ein
        //ausleihverwaltung[0].
    }
    public void optimiereAusstellung(int y)
    {
        // tragen Sie hier den Code ein
        //ausleihverwaltung[0].
        //ausleihverwaltung[1].
        //drop die schlechtere
    }   