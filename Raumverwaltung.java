// Importiert die Klasse Vector
import java.util.Vector;

/**
 * Die Klasse Raumverwaltung ist eine Containerklasse und dient zur Verwaltung von Objekten der Klasse "Raum".
 * Diese Klasse kann Raeume aufnehmen, entfernen und Listen von Raeume nach bestimmten Kriterien erstellen.
 * Sie ermöglicht das Suchen und das Zugreifen auf bestimmte Objekte der Klasse "Raum".
 * Sie steht in einer Aggregation zu der Klasse "Raum".
 * 
 * Mittels der erstellten Listen unterstuetzt sie die Klasse "Ausstellungsplanung".
 * 
 * Dafuer benutzt die Klasse Informationen aus den Klassen "Raum", "Bild", "Kunstgegenstand", "Kunstinstallation" und 
 * "Ausstellungsplanung".
 * 
 * 
 * @author Carla Saradeth 
 * @version Dez 2022
 */
public class Raumverwaltung
{
    // Attribute der Klasse Raumverwaltung
    /** In diesem Attribut werden die einzelnen Baeume mittels der Containerklasse Vector verwaltet.*/
    private Vector<Raum> raumVector;
        
    //Konstruktor der Klasse Raumverwaltung
    /**
     * Konstruktor fuer Objekte der Klasse Raum. Dieser Konstrukter erzeugt eine leere Raumverwaltung.
     */
    public Raumverwaltung()
    {
    // Anlegen eines neuen Vectorobjektes
        raumVector = new Vector<Raum>();
    }
    
    //Methoden der Klasse Raumverwaltung
    //Methoden zur Verwaltung der Klasse Raumverwaltung
    /** Fügt einen Raum der Raumverwaltung zu.
     *  @param  inRaum  Ein Objekt der Klasse Raum. 
     */
    public void addRaum()
    {
        //Code einfuegen
    }
    
    /** Entfernt einen Raum aus der Raumverwaltung.
     *  @param  inRaum  Ein Objekt der Klasse Raum.
     */
    public void removeRaum()
    {
        //Code einfuegen
    }
    
    /** Ermittelt die Anzahl an Raeumen in der Raumverwaltung.
     *  @return Die Anzahl der Objekte der Klasse Raum in der Raumverwaltung.
     */
    public int sizeRaumverwaltung()
    {
        return raumVector.size();
    }
    
    /** Loescht alle Raeume aus der Raumverwaltung.
     * 
     */
    public void clearRaumverwaltung()
    {
        //Code einfuegen
    }
    
    //Methoden zur Unterstützung der Klasse Ausstellungplanung - technischen Aspekte
    /** Ermittelt alle Objekte der Klasse Raum, dessen Hoehe größer ist als die Hoehe eines Bildes 
     *  unter Berücksichtigung eines Mindestabstandes von 1m. 
     *  (hoeheRaumInCm - 1m >= Hoehe eines Bildes)
     *  @return     Objekte der Klasse Raum, das Kriterien in Hoehe erfüllt.
     */
    public Vector<Raum> getRaumNachHoehe1()
    {
        //Code einfuegen
        return raumVector;
    }
    /** Ermittelt alle Objekte der Klasse Raum, dessen Hoehe größer ist als die Hoehe eines Kunstgegenstanden/Kunstinstallation 
     *  unter Berücksichtigung eines Mindestabstandes von 2m. 
     *  (hoeheRaumInCm - 2m >= Hoehe eines Kunstgegenstanden oder Kunstinstallation)
     *  @return     Objekte der Klasse Raum, das Kriterien in Hoehe erfüllt.
     */
    public Vector<Raum> getRaumNachHoehe2()
    {
        //Code einfuegen
        return raumVector;
    }
    
    /** Ermittelt alle Objekte der Klasse Raum, dessen Laenge größer ist als die Laenge eines Bildes 
     *  unter Berücksichtigung eines Mindestabstandes von 1m.
     *  (laengeRaumInCm - 1m >= Laenge eines Bildes)
     *  @return     Objekte der Klasse Raum, das Kriterien in Laenge erfüllt.
     */
    public Vector<Raum> getRaumNachLaenge1()
    {
        //Code einfuegen
        return raumVector;
    }
    /** Ermittelt alle Objekte der Klasse Raum, dessen Laenge größer ist als die Laenge eines Kunstgegenstanden/Kunstinstallation
     *  unter Berücksichtigung eines Mindestabstandes von 2m.
     *  (laengeRaumInCm - 2m >= Laenge eines Kunstgegenstanden oder Kunstinstallation)
     *  @return     Objekte der Klasse Raum, das Kriterien in Laenge erfüllt.
     */
    public Vector<Raum> getRaumNachLaenge2()
    {
        //Code einfuegen
        return raumVector;
    }
      
    /** Ermittelt alle Objekt der Klasse Raum, dessen Breite größer ist als die Breite eines Bildes 
     *  unter Berücksichtigung eines Mindestabstandes von 1m.
     *  (breiteRaumInCm - 1m >= Breite eines Bildes)
     *  @return     Objekte der Klasse Raum, das Kriterien in Breite erfüllt.
     */
    public Vector<Raum> getRaumNachBreite1()
    {
        //Code einfuegen
        return raumVector;
    }
    /** Ermittelt alle Objekte der Klasse Raum, dessen Breite größer ist als die Breite eines Kunstgegenstanden/Kunstinstallation 
     *  unter Berücksichtigung eines Mindestabstandes von 2m.
     *  (breiteRaumInCm - 2m >= Breite eines Kunstgegenstanden oder Kunstinstallation)
     *  @return     Objekte der Klasse Raum, das Kriterien in Breite erfüllt.
     */
    public Vector<Raum> getRaumNachBreite2()
    {
        //Code einfuegen
        return raumVector;
    }
   
    //Methoden zur Unterstützung der Klasse Ausstellungplanung - organisatorische Aspekte
    /** Ermittelt alle Objekte der Klasse Raum, die mindestens ein Kunstwerk entsprechend dem Schwerpunktthema enthalten.
     *  @return     Objekte der Klasse Raum, das Kriterien in Schwerpunktthema erfüllt.
     */
    public Vector<Raum> getRaumThema1()
    {
        //Code einfuegen
        return raumVector;
    }
    
    /** Ermittelt alle Objekte der Klasse Raum, die kein Kunstwerk entsprechend dem Schwerpunktthema enthalten.
     *  @return     Objekte der Klasse Raum, das Kriterien in Schwerpunktthema nicht erfüllt.
     */
     public Vector<Raum> getRaumThema2()
    {
        //Code einfuegen
        return raumVector;
    }
    
    /** Ueberprueft, ob mindestens die Hälfte aller Raeume mindestens ein Kunstwerk mit dem Schwerpunktthema enthalten.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
        public boolean pruefeVertretungThema()
    {
        //Code einfuegen
        //"true" falls size raumVector der Methode getRaumThema1 >= size raumVector der Methode getRaumThema2
        //"false" falls size raumVector der Methode getRaumThema1 < size raumVector der Methode getRaumThema2
        return true;
    }
    
    
    
}
