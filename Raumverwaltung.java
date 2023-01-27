// Importiert die Klasse Vector
import java.util.Vector;
import java.util.*;
import java.lang.Math;
import java.util.Arrays;

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
    
    /** In diesem Attribut werden die nach Suchkriterien ausgewählten Raeume mittels der Containerklasse Vector verwaltet. */
    private Vector<Raum> raumSuche;
           
    //Konstruktor der Klasse Raumverwaltung
    /** Konstruktor fuer Objekte der Klasse Raum. 
     *  Dieser Konstruktor erzeugt eine leere Raumverwaltung.
     */
        public Raumverwaltung()
    {
    // Anlegen eines neuen Vectorobjektes
        raumVector = new Vector<Raum>();
    }
       
    //=============================================================
    //Methoden zur Verwaltung der Klasse Raumverwaltung
    //=============================================================
    
    /** Ermittelt den Vector der Raumverwaltung.
     *  @return  Den Vector der Raumverwaltung.
     */
    
    public Vector<Raum> getRaumVector()
    { 
        return raumVector;
    }
    
    /** Fügt einen Raum der Raumverwaltung zu.
     *  @param  inRaum  Ein Objekt der Klasse Raum, das hinzugefuegt werden soll. 
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
       
    /** Entfernt einen Raum aus der Raumverwaltung.
     *  @param  inRaum  Ein Objekt der Klasse Raum, das entfernt werden soll.
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
    
    /** Loescht alle Raeume aus der Raumverwaltung.
     * 
     */
    public void clearRaumverwaltung()
    {
        raumVector.clear();
    }
    
    /** Ermittelt die Anzahl der Raeume in der Raumverwaltung.
     *  @return Die Anzahl der Objekte der Klasse Raum in der Raumverwaltung.
     */
    public int anzahlRaeume()
    {
        return raumVector.size();
    }
    
    /** Ermittelt die Anzahl der Haelfte der Raeume in der Raumverwaltung (aufgerundet).
     *  @return Die Anzahl der der Haelfte der Objekte der Klasse Raum in der Raumverwaltung.
     */
    public int anzahlHaelfteRaeume()
    {
        // liest die Anzahl der Raeume in der Raumverwaltung
        int anzahlRaeume = raumVector.size();
        // teilt die Anzahl der Raeume durch 2 
        double anzahlRaeumeGenau = (double)anzahlRaeume / 2;
        // rundet das Ergbenis auf
        int haelfteRaeumeAufgerundet = (int)Math.ceil(anzahlRaeumeGenau);
        // gibt das Ergebnis aus
        return haelfteRaeumeAufgerundet;
    }
    
    /** Gibt einen Raum nach seiner Indexnummer in der Raumverwaltung zurueck.
     *  @param      Indexnummer in der Raumverwaltung
     *  @return     Raum mit dieser Indexnummer
     */
    public Raum getRaum(int index)
    {
        return  (Raum)raumVector.elementAt(index);
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
        double zahlRandom = Math.random();
        // berechnet die zufaellige Nummer,die die Position des Elements im Vector angibt.
        int auswahlRaumNr = (int)(zahlRandom * anzahlRaeume);
        // liest das entsprechende Element aus dem Vector
        return inRaumverwaltung.getRaum(auswahlRaumNr);
    }
    
        public Raum zufealligerLeererRaum(ArrayList <Raum> inRaeumeSchonBelegt)
    {
        // Die belegten Räume kommen als ArrayList an. Wir erzeugen für den nachfolgenden Schritt eine ArrayList aus dem raumVector, der alle Räume enthält
        
        Vector<Raum> raumVectorKlon = (Vector<Raum>) raumVector.clone(); // wir machen einen Klon vom Raumvektor, aus dem wir die belegten Räume später entfernen.
                                                                         // Ein Klon, damit wir nicht unseren ursprünglichen Raumvektor kaputt machen.
        ArrayList<Raum> raumArrayList = new ArrayList<Raum>(raumVectorKlon); // aus dem Klon machen wir dann die ArrayList
        
        // Jetzt entnehmen wir daraus alle Räume, die schon belegt sind. Das heißt wir führen in raumArrayList nur noch die komplett unbelegten Räume
        raumArrayList.removeAll(inRaeumeSchonBelegt);
        System.out.println("noch " + raumArrayList.size() + " Räume zu prüfen.");
        
        // Um zufällig einen freien Raum daraus abrufen zu können, suchen wir als Index eine ganzzahlige Zufallszahl zwischen
        // erstes Element von raumArrayList (d.h. der Wert 0 ) und 
        // letztes Element von raumArrayList ( d.h. der Länge von raumArrayList minus 1), 
        // wobei diese beiden Randwerte selbst eingeschlossen sein müssen ("inclusive")
        
        // Wie auf https://docs.oracle.com/javase/8/docs/api/java/util/Random.html nachgelesen werden kann, sollte uns die nextInt-Methode Folgendes zurückgeben:
        // "a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive), drawn from this random number generator's sequence"
        
        Random rand = new Random();
        int randomNum = rand.nextInt(raumArrayList.size() -1 + 1); 
        //// int randomNum = rand.nextInt((max - min) + 1) + min  gäbe uns Werte zwischen min und max jeweils einschließlich der beiden Grenzen
        
        // Wir geben den zufällig ausgewählten Raum aus der ArrayList der noch freien Räume zurück:
        return raumArrayList.get(randomNum);
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
