
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden.
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Kunstinstallationen erfasst. 
 * Es lassen sich hierrüber manuell Objekte anlegen/ändern.
 * Jedoch sollen über die Einstiegsklasse "Museum" über einen Datenimport die Objekte erstellt werden, welches sich hierbei den Konstruktoren bedienen.
 * 
 * @author (Alexander Kipry) 
 * @version (03.02.2023)
 */
public class Kunstinstallation extends Kunstwerk
{
    //Attribute der Klasse Kunstinstallationen
   /** Länge der Kunstinstallation in cm */
    private int laenge;
    
    /** Gewicht der Kunstinstallation in KG */
    private int gewicht;
    
    //Konstruktoren der Klasse Kunstinstallationen
    /**
     * Konstruktor für ein Objekt der Klasse Kunstinstallationen ohne Parameter
     */
    public Kunstinstallation ()
    {
    }
    
    /**
     * Konstruktor für ein Objekt der Klasse Kunstinstallationen mit Parametern
     * 
     * @param in_laufendeNummer            Dieser short entspricht der laufenden Nummer des Kunstwerkes
     * @param in_art                       Dieser char entspricht der Art des Kunstwerkes (Bild "B", Kunstinstallation "I", Kunstgegenstand "G")
     * @param in_bezeichnung               Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param in_kuenstler                 Dieser String entspricht dem Namen des Künstlers.
     * @param in_jahr                      Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param in_thema                     Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param in_attraktivitaetswert       Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider).
     * @param in_kosten                    Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param in_verleihendesMuseum        Dieser String entpricht dem Namen des verleihenden Museums.
     * @param in_anschrift                 Dieser String entspricht der Anschrift des verleihenden Museums (Straße, Hnr, Plz, Ort).
     * @param in_hoehe                     Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param in_breite                    Dieser int entspricht der Breite des Kunstwerkes in cm. 
     * @param in_laenge                    Dieser int entspricht der Länge der Kunstinstallation in cm.
     * @param in_gewicht                   Dieser int entspricht dem Gewicht der Kunstinstallation in KG.
     */
    public Kunstinstallation (short in_laufendeNummer, char in_art, String in_bezeichnung, String in_kuenstler, String in_jahr, String in_thema,
    int in_attraktivitaetswert, int in_kosten, String in_verleihendesMuseum,String in_anschrift, int in_hoehe, int in_breite, int in_laenge, int in_gewicht)
    {
        super(in_laufendeNummer, in_art, in_bezeichnung, in_kuenstler, in_jahr, in_thema, in_attraktivitaetswert, in_kosten, in_verleihendesMuseum, in_anschrift, in_hoehe, in_breite);
        setLaenge(in_laenge);
        setGewicht(in_gewicht);
    }
    
     //setter - Methoden
    /**
     * Diese Methode ueberschreibt das laenge Attribut einer Kunstinstallation
     * 
     * @param in_laenge Laenge der Kunstinstallation in cm
     */
    
    public void setLaenge(int in_laenge)
    {
        laenge = in_laenge;
    }
    
    /**
     * Diese Methode ueberschreibt das gewicht Attribut einer Kunstinstallation
     * 
     * @param in_gewicht Gewicht der Kunstinstallation in KG
     */
    
    public void setGewicht(int in_gewicht)
    {
        gewicht = in_gewicht;
    }
    
    //getter - Methoden
    /**
     * Gibt die Laenge der Kunstinstallation an.
     * 
     * @return die Laenge der Kunstinstallation in cm.
     */
    public int getLaenge()
    {
        return laenge;
    }
    
    /**
     * Gibt das Gewicht der Kunstinstallation an.
     * 
     * @return das Gewicht der Kunstinstallation in kg.
     */
    public int getGewicht()
    {
        return gewicht;
    }
    /**
     * Gibt die Maße einer Kunstinstallation aus. 
     * 
     * @return eine textuelle Ausgabe der Maße der Kunstinstallation in einem String. 
     */
    @Override //weil die eigentliche Methode hierzu in der Klasse Kunstwerk definiert wird und hierrüber auf die Klasse Kunstinstallation angepasst wird. 
    public String outputMaße()
    {
        return ", Hoehe: " + super.getHoehe() + ", Breite: " + super.getBreite() + ", Laenge: " + getLaenge() + ", Gewicht in kg: " +getGewicht();
    }
}
