
/**
 * Die Klasse Museum bildet die Einstiegsklasse. Aus ihr wird das Programm
 * gestartet. 
 * 
 * @author Mischa Paul Marchlewski 
 * @version 19.12.2022
 */
public class Museum
{
    /**
     * speichert ein Objekt der Klasse Ausstellungsplanung
     */
    private Ausstellungsplanung planung;

    /**
     * In der Main-Methode werden alle Anweisungen ausgeführt, die beim Start
     * des Programms benötigt werden (z.B. Import der Räume und
     * Kunstwerke aus den CSV-Dateien). Weiterhin wird ein Objekt der zentralen
     * Geschäftslogikklasse Ausstellungsplanung erstellt.
     * 
     * @param  args    Stringparameter, die übergeben werden können
     */
    public static void main(String[] args)
    {
        Museum.importiereKunstwerke();
        Museum.importiereRaeume();
        planung= new Ausstellungsplanung();
    }
    
    /**
     * Liest die Kunstwerke aus der Datei kunstwerke.csv ein und erstellt
     * daraus Objeke der Klasse Bild, Kunstinstallation bzw. Kunstgegenstand, die dann
     * inder Klasse Angebotsverwaltung verwaltet werden
     */
    public static void importiereKunstwerke()
    {
        Angebotsverwaltung verw = new Angebotsverwaltung();
        
        Kunstinstallationen kunstinstallation= new Kunstinstallationen();
        verw.add(kunstinstallation);
        
        Bild bild= new Bild();
        verw.add(bild);
        
        Kunstgegenstand kunstgegenstand= new Kunstgegenstand();
        verw.add(kunstgegenstand);
    }
    
    /**
     * Liest die Räume aus der Datei raeume.csv ein und erstellt daraus
     * Objekte der Klasse Raum, die in der Klasse Raumverwaltung verwaltet 
     * werden.
     */
    public static void importiereRaeume()
    {
        Raumverwaltung raumv = new Raumverwaltung();
        Raum raum = new Raum();
        raumv.addRaum(raum);
    }
    
    
    
    
    
    
}
