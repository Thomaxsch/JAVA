// Importiert die Klasse Vector
import java.util.Vector;

/**
 * Die Klasse Raumverwaltung ist eine Containerklasse und dient zur Verwaltung von Objekten der Klasse "Raum".
 * Diese Klasse kann Raeume aufnehmen, entfernen und Listen von Raeumen erstellen. Sie ermöglicht das Suchen 
 * und das Zugreifen auf bestimmte Objekte der Klasse "Raum". Sie steht in einer Aggregation zu der Klasse "Raum".
 * 
 * Die Klasse verfuegt ueber Methoden zur Ueberpruefung der vorgegebenen thematischen Anforderungen.
 * Dafuer benutzt die Klasse Informationen aus den Klassen "Raum", "Bild", "Kunstgegenstand", "Kunstinstallation" und 
 * "Ausleihe".
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
    //=============================================================
    //Methoden zur Verwaltung der Klasse Raumverwaltung
    //=============================================================
/** Fügt einen Raum der Raumverwaltung zu.
*  @param  raum  Ein Objekt der Klasse Raum, das hinzugefuegt werden soll. 
*/
        public void addRaum(Raum raum)

    {
        //Code einfuegen
    }
        
/** Entfernt einen Raum aus der Raumverwaltung.
*  @param  raum  Ein Objekt der Klasse Raum, das entfernt werden soll.
*/
    public void removeRaum(Raum raum)
    {
        
    }

    
    /** Loescht alle Raeume aus der Raumverwaltung.
     * 
     */
        public void clearRaumverwaltung()
    {
        //Code einfuegen
    }
    
    //=============================================================
    //Methoden zur Unterstützung der Klasse Aussleihe - organisatorische Aspekte
    //=============================================================
    /** Den Vector der Raumverwaltung ermittlen.
     *  @return  Den Vector der Raumverwaltung.
     */
    
        public Vector<Raum> getRaumVector()
    { 
        return raumVector;
    }
    
    /** Ermittelt alle Objekte der Klasse Raum, die mindestens ein Kunstwerk entsprechend dem Schwerpunktthema enthalten.
     *  @return     Objekte der Klasse Raum, das Kriterien in Schwerpunktthema erfüllt.
     */
        private Vector<Raum> getRaumThema1()
    {
        //Code einfuegen
        return raumVector;
    }
    
    /** Ermittelt alle Objekte der Klasse Raum, die kein Kunstwerk entsprechend dem Schwerpunktthema enthalten.
     *  @return     Objekte der Klasse Raum, das Kriterien in Schwerpunktthema nicht erfüllt.
     */
        private Vector<Raum> getRaumThema2()
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
