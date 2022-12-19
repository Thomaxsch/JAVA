
/**
 * Die Klasse Museum bildet die Einstiegsklasse. Aus ihr wird das Programm
 * gestartet. 
 * 
 * @author Mischa Paul Marchlewski 
 * @version 13.12.2022
 */
public class Museum
{
    /**
     * speichert ein Objekt der Klasse Ausstellungsplanung
     */
    private Ausstellungsplanung planung;

    /**
     * In der Main-Methode werden alle Anweisungen aufgeführt, die beim Start
     * des Programms ausgeführt werden sollen. Von hier aus wird die zentrale
     * Geschäftslogikklasse Ausstellungsplanung aufgerufen. (<- alt)
     * 
     * In der Main-Methode werden alle Anweisungen aufgeführt, die beim Start
     * des Programms ausgeführt werden sollen. Hier wird der Import der Kunstwerke und 
     * der Räume veranlasst. Danach wird ein Objekt "Ausstellungsplanung" erzeugt. Bei 
     * diesem Objekt können dann manuelle Eingaben vorgenommen werden (Schwerpunktthema, 
     * Kostenobergrenze), woraufhin man manuell die Planung mit einer Methode dieses Objekts
     * anstoßen kann (mittels der Methode "planung").  (<-vorschlag)
     * 
     * @param  args    Beispielparameter für eine Methode
     */
    public static void main(String[] args)
    {
        this.importiereKunstwerke();
        this.importiereRaeume();
        planung= new Ausstellungsplanung();
    }
    
    /**
     * Liest die Kunstwerke aus der Datei kunstwerke.csv ein und erstellt
     * daraus Objeke der Klasse Bild, Kunstinstallation bzw. Kunstgegenstand
     */
    public static void importiereKunstwerke()
    {
        Kunstinstallationen kunstinstallation= new Kunstinstallationen();
        Bild bild= new Bild();
        Kunstgegenstand kunstgegenstand= new Kunstgegenstand();
    }
    
    /**
     * Liest die Räume aus der Datei raeume.csv ein und erstellt daraus
     * Objekte der Klasse Raum,die in der Klasse Raumverwaltung verwaltet 
     * werden.
     */
    public static void importiereRaeume()
    {
        Raum raum = new Raum();
    }
    
    
    
    
    
    
}
