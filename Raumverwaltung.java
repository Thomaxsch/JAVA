
/**
 * Die Klasse Raumverwaltung ist eine Containerklasse und dient zur 
 * Verwaltung von Objekten der Klasse Raum. Sie ermöglicht das Suchen 
 * und das Zugreifen auf bestimmte Objekte der Klasse Raum.
 * 
 * Über diese Klasse werden die von Herr Schneider bereitgestellten Details
 * zu den Räume des VAWI- Museums in Form einer csv-Datei eingelesen und auf
 * deren Basis Objekte der Klasse Raum erstellt.
 * 
 * @author Carla Saradeth 
 * @version Dez 2022
 */
public class Raumverwaltung
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private int x;

    /**
     * Konstruktor für Objekte der Klasse Raumverwaltung
     */
    public Raumverwaltung()
    {
        // Instanzvariable initialisieren
        x = 0;
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public int beispielMethode(int y)
    {
        // tragen Sie hier den Code ein
        return x + y;
    }
    
    /* Noch zu erstellende Methoden: (??)
     * 
     * Lese csv-Datei und importiere Daten.
     * 
     * Erstelle Objekte der Klasse Raum.
     * 
     * Lösche alle Objekte der Klasse Raum.
     * 
     * Aktualisiere alle Objekte der Klasse Raum.
     * 
     * Suche ein Objekt der Klasse Raum, welches folgende Erfüllung trifft:
     * - hoeheRaumInCm >= Hoehe eines Kunstgegenstandes/installation
     * - laengeRaumInCm >= Laenge eines Kunstgegenstandes/installation
     * - breiteRaumInCm >= Breite eines Kunstgegenstandes/installation
     * 
     * 
     * 
     */
}
