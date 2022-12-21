import java.io.*;

/**
 * Die Klasse Datei kann Strings als Zeilen in einer Datei ausgeben.
 * 
 * @author Mischa Paul Marchlewski
 * @version 19.12.2022
 */
public class Ausgabedatei
{
    /**
    * Der Name der Datei mit Pfadinformationen (bsp. C:/temp/datei.txt)
    */
    private String file;
    /**
     * Objekt der Klasse Ausstellungsplanung, auf dessen Grundlage die Ausleih-, Ausstellungs- und Museumsführer-Datei erstellt werden können
     */
    private Ausstellungsplanung planung;
    /**
     * Objekt der Java-Klasse PrintWriter zum Schreiben von Zeichenketten
     */
    private PrintWriter outpoutDatei;
    /**
     * Objekt der Java-Klasse BufferedReader zum Lesen von Dateien
     */
    private BufferedReader inputDatei;
    /**
     * Konstruktor für Objekte der Klasse Ausgabedatei mit Parameternamen für den Dateinamen
     * @param file Dateiname für die zu erstellende Outputdatei 
     */
    public Ausgabedatei(String file)
    {
       this.file = file;
    }
    
    /**
     * Konstruktor für Objekte der Klasse Ausgabedatei mit Parameternn für den Dateinamen und die Ausstellungsplanung
     * @param file      Dateiname für die zu erstellende Outputdatei 
     * @param planung   Objekt der Klasse Ausstellungsplanung
     */
    public Ausgabedatei(String file, Ausstellungsplanung planung) 
    {
        
    }
    
    /**
     * Methode zum nachträglichen Setzen eines Objekts der Klasse Ausstellungsplanung
     * @param planung   Objekt der Klasse Ausstellungsplanung die nachträglich gesetzt werden kann
     */
    public void setAusstellungsplanung(Ausstellungsplanung planung)
    {
        
    }

    /**
     * Erstellt eine Datei (Ausleihdatei), die nach Quelle sortiert angibt 
     * welche Kunstwerke wo ausgeliehen werden sollen, einschließlich der 
     * dabei entstehenden Kosten. Die Methode nutzt dazu die Ausstellungs-
     * planung
     */
    public void schreibeAusleihen()
    {
    
    }
    
    /**
     * Erstellt eine Datei (Ausstellungsdatei), die nach Räumen aufgegliedert
     * auflistet,welches Kunstwerk wo ausgestellt werden soll. Sie nutzt dazu
     * die Ausstellungsplanung.
     */
    public void schreibeAusstellungen()
    {
        
    }
    
    /**
     * Erstellt eine Datei, die für die Besucher eine kurze Übersicht zu
     * den Räumen und wichtigsten Kunstwerken enthält. Sie nutzt dazu die 
     * Ausstellungsplanung.
     */
    public void schreibeMuseumsfuehrer()
    {
        
    }
    
    /**
     * Öffnet eine Output-Datei mit dem Namen des Attributs file
     */
    private void openFile() throws IOException 
    {
        
    }
    
    /**
     * Schreibt eine Textzeile in eine  Ausgabedatei
     * @param str Text der in eine Teile der Ausgabedatei geschrieben werden soll
     */
    private void writeLine(String str) 
    {
        
    }
    
    
    
    
}
