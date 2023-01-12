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
    public void addRaum(Raum inRaum)
    {
       // Ein Objekt Raum kann nur hinzugefuegt werden wenn nicht bereits enthalten
       if (!raumVector.contains(inRaum)){
           raumVector.addElement(inRaum);
       }
       else { 
           System.out.println("Raum ist bereits in der Raumverwaltung enthalten. Er kann nicht erneut hinzugefuegt werden");
       }
    }
    
    //muss noch gemacht werden/ noetig?
    /** Fügt alle Raume der Raumverwaltung zu.
     *  @param  raum  alle Objekt der Klasse Raum, die hinzugefuegt werden sollen. 
     */
    public void addAll(Raum raum)
    {
       raumVector.addElement(raum);
       // Code einfuegen: Schleife
       // Code einfuegen: Ein Objekt Raum kann nur hinzugefuegt werden wenn nicht bereits enthalten
       
    }
    
    /** Entfernt einen Raum aus der Raumverwaltung.
     *  @param  raum  Ein Objekt der Klasse Raum, das entfernt werden soll.
     */
    public void removeRaum(Raum inRaum)
    {
        if (raumVector.contains(inRaum)){
            raumVector.removeElement(inRaum);
        }
        else {
            System.out.println("Der Raum ist nicht in der Raumverwaltung enhalten. Er kann nicht entfernt werden.");
        }
    }
    
    /** Ermittelt die Anzahl an Raeumen in der Raumverwaltung.
     *  @return Die Anzahl der Objekte der Klasse Raum in der Raumverwaltung.
     */
    public int anzahlRaeume()
    {
        return raumVector.size();
    }
    
    //muss noch gemacht werden
    /** Ermittelt die Anzahl der Haelfte der Raeume in der Raumverwaltung (aufgerundet).
     *  @return Die Anzahl der der Haelfte der Objekte der Klasse Raum in der Raumverwaltung.
     */
    public int anzahlHaelfteRaeume()
    {
        return raumVector.size();
    }
    
    /** Loescht alle Raeume aus der Raumverwaltung.
     * 
     */
    public void clearRaumverwaltung()
    {
        raumVector.clear();
    }
    
    //uebernommen und angepasst von "Wald"-Beispiel/ muss noch gemacht werden
    //benoetigt fuer die Methode zufaelligerRaum
    /** Gibt einen Raum nach seiner Indexnummer in der Raumverwaltung zurueck.
     *  @param      Indexnummer in der Raumverwaltung
     *  @return     Raum mit dieser Indexnummer
     */
    public Raum getRaum(int index)
    {
        return  (Raum)raumVector.elementAt(index);
    }
    
    //muss noch gemacht werden
    /** Ermittelt den Vector der Raumverwaltung.
     *  @return  Den Vector der Raumverwaltung.
     */
    
    public Vector<Raum> getRaumVector()
    { 
        return raumVector;
    }
    
    //uebernommen und angepasst von "Wald"-Beispiel/ muss noch gemacht werden
    /** Sucht einen zufaelligen Raum aus der Raumverwaltung aus.
     *  @return Zufaelliger Raum aus der Raumverwaltung
     */
    public Raum zufealligerRaum(Raumverwaltung inRaumverwaltung)
    {
        // liest die Anzahl der Raeume in der Raumverwaltung
        int anzahlRaeume = raumVector.size();
        // ermittelt Zufallszahl zwischen 0.0 und 1.0
        double zw_random = Math.random();
        // berechnet die zufaellige Nummer,die die Position des Elements im Vector angibt.
        int auswahlRaumNr = (int)(zw_random * anzahlRaeume);
        // liest das entsprechende Element aus dem Vector
        return inRaumverwaltung.getRaum(auswahlRaumNr);
    }
    
    //muss noch gemacht werden
    // Für alle Kunstwerke relevant:Raeume raussuchen nach verfuegbareHoehe 
    /** Ermittelt alle (oder ein?) Objekte der Klasse Raum, dessen Hoehe größer ist als ein anzugebender Wert.
     *  @return     Objekte der Klasse Raum, welche das Kriterium in Hoehe erfüllen.
     */
    public Vector<Raum> showRaumNachHoehe(int hoeheKriterium)
    {
        //Code einfuegen
        return raumVector;
    }
    
    //muss noch gemacht werden
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
    
    //muss noch gemacht werden
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
