import java.util.Vector;
import java.util.*;
import java.lang.Math;

/**
 * Die Klasse Raumverwaltung ist eine Containerklasse und dient zur Verwaltung von Objekten der Klasse "Raum". 
 * Zur Verwaltung wird die Java Klasse „Vector“ benutzt.  
 * 
 * Die Raumverwaltung kann Raeume aufnehmen, entfernen und nach Index im Vector ermitteln. 
 * 
 * Zur Unterstuetzung anderer Klassen verfuegt die Raumverwaltung ueber Methoden zur: 
 *      -	Ermittlung der Anzahl der enthaltenen Raeume und der Anzahl der Haelfte der enthaltenen Raeume 
 *      -	Ermittlung eines zufaelligen leeren Raums 
 *      -	Textuellen Beschreibung aller Raeume 
 * 
 * Die Klasse Raumverwaltung steht in einer Aggregation zu der Klasse "Raum". 
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
    /** Konstruktor fuer Objekte der Klasse Raum. 
     *  Dieser Konstruktor erzeugt eine leere Raumverwaltung.
     */
    public Raumverwaltung()
    {
    // Anlegen eines neuen Vectorobjektes
        raumVector = new Vector<Raum>();
    }
       
    //Methoden der Klasse Raumverwaltung   
    /** Ermittelt den Vector der Raumverwaltung.
     *  @return  Den Vector der Raumverwaltung.
     */
    public Vector<Raum> getRaumVector()
    { 
        return raumVector;
    }
    
    /** Fügt einen Raum der Raumverwaltung zu.
     *  @param  inRaum  Ein Objekt der Klasse Raum, das hinzugefuegt werden soll
     */
    public void addRaum(Raum inRaum)
    {
       // Ein Objekt Raum kann nur hinzugefuegt werden wenn nicht bereits enthalten
       if (!raumVector.contains(inRaum))
       {
           raumVector.addElement(inRaum);
       }
       else 
       { 
           System.out.println("Raum ist bereits in der Raumverwaltung enthalten. Er kann nicht erneut hinzugefuegt werden");
       }
    }
       
    /** Entfernt einen Raum aus der Raumverwaltung.
     *  @param  inRaum  Ein Objekt der Klasse Raum, das entfernt werden soll
     */
    public void removeRaum(Raum inRaum)
    {
        if (raumVector.contains(inRaum))
        {
            raumVector.removeElement(inRaum);
        }
        else 
        {
            System.out.println("Der Raum ist nicht in der Raumverwaltung enhalten. Er kann nicht entfernt werden.");
        }
    }
    
    /** Loescht alle Raeume aus der Raumverwaltung.
     * 
     */
    public void clearRaumverwaltung()
    {
        raumVector.clear();
    }
    
    /** Ermittelt die Anzahl der Raeume in der Raumverwaltung.
     *  @return     Die Anzahl der Objekte der Klasse Raum in der Raumverwaltung.
     */
    public int anzahlRaeume()
    {
        return raumVector.size();
    }
    
    /** Ermittelt die Anzahl der Haelfte der Raeume in der Raumverwaltung (aufgerundet).
     *  @return     Die Anzahl der der Haelfte der Objekte der Klasse Raum in der Raumverwaltung.
     */
    public int anzahlHaelfteRaeume()
    {
        // liest die Anzahl der Raeume in der Raumverwaltung
        int anzahlRaeume = raumVector.size();
        // teilt die Anzahl der Raeume durch 2 
        double anzahlHaelfteRaeumeGenau = (double)anzahlRaeume / 2;
        // rundet das Ergbenis auf
        int haelfteRaeumeAufgerundet = (int)Math.ceil(anzahlHaelfteRaeumeGenau);
        // gibt das gerundete Ergebnis aus
        return haelfteRaeumeAufgerundet;
    }
    
    /** Gibt einen Raum nach seiner Indexnummer in der Raumverwaltung zurueck.
     *  @param  index   Indexnummer in der Raumverwaltung (int)
     *  @return         Raum mit dieser Indexnummer
     */
    public Raum getRaum(int index)
    {
        return  (Raum)raumVector.elementAt(index);
    }
           
    /** Sucht einen zufaelligen leeren Raum aus.
     *  @param  inRaeumeSchonBelegt     ArrayList, die alle belegten Raeume enthaelt
     *  @return                         Zufaelliger leerer Raum
     */
    public Raum zufealligerLeererRaum(ArrayList <Raum> inRaeumeSchonBelegt)
    {
        // erstellt einen Klon des Raumvektors (Klon: um ursprünglichen Raumvektor nicht zu veraendern)
        Vector<Raum> raumVectorKlon = (Vector<Raum>) raumVector.clone(); 
        // wandelt den Klon in eine ArrayList um
        ArrayList<Raum> raumArrayList = new ArrayList<Raum>(raumVectorKlon); 
        // entfernt alle belegten Raeume aus der ArrayList, die komplett unbelegten Raeume bleiben enthalten
        raumArrayList.removeAll(inRaeumeSchonBelegt); 
        // erstellt einen Zufallsnummer-Generator
        Random rand = new Random();
        // ermittelt eine zufaellige ganzzahlige Nummer zwischen 0 (erstes Element der ArrayList) und der Länge d. 
        // ArrayList -1 (letztes Element). Da bei nextInt(int bound) der hintere Randwert exklusive ist,
        // wird eine "+1" eingefuegt. Somit sind beide Randwerte bei der Ermittlung inklusive.
        int randomNum = rand.nextInt(raumArrayList.size() -1 + 1); 
        // gibt den Raum an der Stelle der ermittelten zufealligen Nummer in der ArrayList aus
        return raumArrayList.get(randomNum);
    }
    
    /** Gibt eine textuelle Beschreibung aller Räume aus.
     * 
     */
    public void showRaeume()
    {
        for(Raum raum : raumVector) 
        {
            System.out.println(raum);
        }
    }
    
    
}
