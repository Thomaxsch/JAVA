
/**
 * Hierbei handelt es sich um eine Child-Klasse der Klasse "Kunstwerk", aus welcher die wesentlichen Attribute vererbt werden. 
 * Zudem werden unter den Attributen die spezifischen Eigenschaften der Kunstgegenstände erfasst.
 * Es lassen sich hierrüber manuell Objekte anlegen/ändern, jedoch sollen über die Einstiegsklasse "Museum" über einen Datenimport die Objekte erstellt werden.
 * 
 * @author (Alexander Kipry) 
 * @version (20.12.2022)
 */
        
public class Kunstgegenstand extends Kunstwerk
{
    //Attribute der Klasse Kunstgegenstand
   /** Länge der Kunstinstallation oder des Kunstgegenstandes in cm */
    private int laenge;
    
    /** Gewicht der Kunstinstallation oder des Kunstgegenstandes in KG */
    private int gewicht;    
    
    //Konstruktoren der Klasse Kunstgegenstand
    /**
     * Konstruktor für Objekte der Klasse Kunstgegenstand ohne Parameter
     */
    public Kunstgegenstand()
    {
    }
    /**
     * Konstruktor für Objekte der Klasse Kunstgegenstand mit Parameter
     * 
     * @param in_ laufendeNummer           Dieser short entspricht der laufenden Nummer des Kunstwerkes
     * @param in_art                       Dieser char entspricht der Art des Kunstwerkes (Bild "B", Kunstinstallation "I", Kunstgegenstand "G")
     * @param in_bezeichnung               Dieser String entspricht der Bezeichnung des Kunstwerkes.
     * @param in_kuenstler                 Dieser String entspricht dem Namen des Künstlers.
     * @param in_jahr                      Dieser String entspricht dem Jahr, in dem das Kunstwerk erstellt wurde.
     * @param in_thema                     Dieser String entspricht dem Thema, welches dem Kunstwerk zugeordnet wurde.
     * @param in_attraktivitaetswert       Dieser int entspricht der Attraktivität des Objekts in % (Bewertung durch Herrn Schneider).
     * @param in_kosten                    Dieser int entspricht den Kosten für die Ausleihe des Kunstwerkes in €.
     * @param in_verleihendesMuseum        Dieser String entpricht dem Namen des verleihenden Museums
     * @param in_anschrift                 Dieser String entspricht der Anschrift des verleihenden Museums (Straße, Hnr, Plz, Ort). 
     * @param in_hoehe                     Dieser int entspricht der Höhe des Kunstwerkes in cm.
     * @param in_breite                    Dieser int entspricht der Breite des Kunstwerkes in cm. 
     * @param in_laenge                    Dieser int entspricht der Länge der Kunstgegenstandes in cm.
     * @param in_gewicht                   Dieser int entspricht dem Gewicht des Kunstgegenstandes in KG.
     */
    public Kunstgegenstand (short in_laufendeNummer, char in_art, String in_bezeichnung, String in_kuenstler, String in_jahr, String in_thema, 
    int in_attraktivitaetswert, int in_kosten, String in_verleihendesMuseum, String in_anschrift,int in_hoehe, int in_breite,
    int in_laenge, int in_gewicht)
    {
        setLaufendeNummer(in_laufendeNummer);
        setArt(in_art);
        setBezeichnung(in_bezeichnung);
        setKuenstler(in_kuenstler);
        setJahr(in_jahr);
        setThema(in_thema);
        setAttraktivitaet(in_attraktivitaetswert);
        setKosten(in_kosten);
        setVerleihendesMuseum(in_verleihendesMuseum);
        setAnschrift(in_anschrift);
        setHoehe(in_hoehe);
        setBreite(in_breite);
        setLaenge(in_laenge);
        setGewicht(in_gewicht);
    }
    
    //Methoden der Klasse Kunstgegenstand
    //Setter-Methoden
    /**
     * Diese Methode ueberschreibt das laenge Attribut eines Kunstgegenstandes
     * 
     * @param in_laenge Laenge des Kunstgegenstandes in cm
     */
    
    public void setLaenge(int in_laenge)
    {
        laenge = in_laenge;
    }
    
    /**
     * Diese Methode ueberschreibt das gewicht Attribut eines Kunstgegenstandes
     * 
     * @param in_gewicht Gewicht des Kunstgegenstandes in KG
     */
    
    public void setGewicht(int in_gewicht)
    {
        gewicht = in_gewicht;
    }
    
    //getter-Methoden
    /**
     * Gibt die Laenge des Kunstgegenstandes an.
     * 
     * @return  die Laenge des Kunstgegenstandes in cm.
     */
    public int getLaenge()
    {
        return laenge;
    }
    
     /**
     * Gibt das Gewicht des Kunstgegenstandes an.
     * 
     * @return das Gewicht des Kunstgegenstandes in KG.
     */
    public int getGewicht()
    {
        return gewicht;
    }
    
    /**
     * Gibt die Maße des Kunstgegenstandes und einer Kunstinstallation aus. 
     * 
     * @return die Maße des jeweiligen Kunstwerkes
     */
    @Override
    public String outputMaße()
    {
        return ", Höhe: " + super.getHoehe() + ", Breite: " + super.getBreite() + ", Länge: " + getLaenge() + ", Gewicht: " +getGewicht();
    }
}
