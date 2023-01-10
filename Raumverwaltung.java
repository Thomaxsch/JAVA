// Importiert die Klasse Vector
import java.util.Vector;
import java.util.*;

/**
 * Die Klasse Raumverwaltung ist eine Containerklasse und dient zur Verwaltung von Objekten der Klasse "Raum".
 * Diese Klasse kann Raeume aufnehmen, entfernen und Listen von Raeumen erstellen. Sie ermöglicht das Suchen 
 * und das Zugreifen auf bestimmte Objekte der Klasse "Raum". Sie steht in einer Aggregation zu der Klasse "Raum".
 * 
 * 
 * @author Carla Saradeth 
 * @version Dez 2022
 */
public class Raumverwaltung
{
    // Attribute der Klasse Raumverwaltung
    /** In diesem Attribut werden die einzelnen Raeume mittels der Containerklasse Vector verwaltet. */
    private Vector<Raum> raumVector;
        
    //Konstruktor der Klasse Raumverwaltung
    /** Konstruktor fuer Objekte der Klasse Raum. Dieser Konstrukter erzeugt eine leere Raumverwaltung.
     */
        public Raumverwaltung()
    {
    // Anlegen eines neuen Vectorobjektes
        raumVector = new Vector<Raum>();
    }
    
    //=============================================================
    //Methoden zur Verwaltung der Klasse Raumverwaltung
    //=============================================================
    /** Fügt einen Raum der Raumverwaltung zu.
     *  @param  raum  Ein Objekt der Klasse Raum, das hinzugefuegt werden soll. 
     */
    public void addRaum(Raum raum)
    {
       raumVector.addElement(raum);
    }
         
    /** Entfernt einen Raum aus der Raumverwaltung.
     *  @param  raum  Ein Objekt der Klasse Raum, das entfernt werden soll.
     */
    public void removeRaum(Raum raum)
    {
        
    }
    
    /** Ermittelt die Anzahl an Raeumen in der Raumverwaltung.
     *  @return Die Anzahl der Objekte der Klasse Raum in der Raumverwaltung.
     */
        public int anzahl()
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
    
    //=============================================================
    //Methoden zur Unterstützung der Klasse Ausstellungsplanung 
    //=============================================================
    /** Ermittelt den Vector der Raumverwaltung.
     *  @return  Den Vector der Raumverwaltung.
     */
    
        public Vector<Raum> getRaumVector()
    { 
        return raumVector;
    }
    
    // Für alle Kunstwerke relevant:Raeume raussuchen nach verfuegbareHoehe 
    /** Ermittelt alle (oder ein?) Objekte der Klasse Raum, dessen Hoehe größer ist als ein anzugebender Wert.
     *  @return     Objekte der Klasse Raum, welche das Kriterium in Hoehe erfüllen.
     */
    public Vector<Raum> showRaumNachHoehe(int hoeheKriterium)
    {
        //Code einfuegen
        return raumVector;
    }
    
    // Für KI/KG relevant: Raeume raussuchen nach verfuegbareLaenge, verfuegbareBreite
    /** Ermittelt alle (oder ein?) Objekte der Klasse Raum, dessen Laenge größer ist als ein anzugebender Wert.
     *  @return     Objekte der Klasse Raum, welche das Kriterium in Laenge erfüllen.
     */
    public Vector<Raum> showRaumNachLaenge(int laengeKriterium)
    {
        //Code einfuegen
        return raumVector;
    }
    /** Ermittelt alle (oder ein?) Objekt der Klasse Raum, dessen Breite größer ist als ein anzugebender Wert.
     *  @return     Objekte der Klasse Raum, das Kriterien in Breite erfüllt.
     */
    public Vector<Raum> showRaumNachBreite(int breiteKriterium)
    {
        //Code einfuegen
        return raumVector;
    }
    
    // Für Bilder relevant: Raeume raussuchen nach wandNord, wandOst, wandSued, wandWest
    /** Ermittelt alle (oder ein?) Objekt der Klasse Raum, dessen wandNord größer ist als ein anzugebender Wert.
     *  @return     Objekte der Klasse Raum, das Kriterien in wandNord erfüllt.
     */
    public Vector<Raum> showRaumNachWandNord(int wandNordKriterium)
    {
        //Code einfuegen
        return raumVector;
    }
    /** Ermittelt alle (oder ein?) Objekt der Klasse Raum, dessen wandOst größer ist als ein anzugebender Wert.
     *  @return     Objekte der Klasse Raum, das Kriterien in wandOst erfüllt.
     */
    public Vector<Raum> showRaumNachWandOst(int wandOstKriterium)
    {
        //Code einfuegen
        return raumVector;
    }
    /** Ermittelt alle (oder ein?) Objekt der Klasse Raum, dessen wandSued größer ist als ein anzugebender Wert.
     *  @return     Objekte der Klasse Raum, das Kriterien in wandSued erfüllt.
     */
    public Vector<Raum> showRaumNachWandSued(int wandSuedKriterium)
    {
        //Code einfuegen
        return raumVector;
    }
    /** Ermittelt alle (oder ein?) Objekt der Klasse Raum, dessen wandWest größer ist als ein anzugebender Wert.
     *  @return     Objekte der Klasse Raum, das Kriterien in wandWest erfüllt.
     */
    public Vector<Raum> showRaumNachWandWest(int wandWestKriterium)
    {
        //Code einfuegen
        return raumVector;
    }
    //=======================================
    // Weitere Methoden
    //=======================================
    /**
     * Mischa, 06.01.2023
     * Gibt eine textuelle Beschreibung aller Räume aus 
     */
    public void showRaeume()
    {
        for(Raum raum : raumVector) 
        {
            System.out.println(raum);
        }
    }
    
    
    
    
    
}
