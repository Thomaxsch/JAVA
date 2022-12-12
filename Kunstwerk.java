
/**
 * Bei dieser Klasse handelt es sich um eine abstrakte Parent-Klasse, welche die wesentlichen Informationen aller Kunstwerke beeinhaltet. 
 * Auf die Parent-Klasse wird durch die Child-Klassen zugegriffen, um die jeweiligen Objekte zu erstellen. 
 * 
 * @author (Alexander Kipry) 
 * @version (11.12.2022)
 */
 
    abstract class Kunstwerk
{
    /** 
     * Bezeichnung, bzw Name des jeweiligen Kunstwerkes 
     */
    private String bezeichnung; 
    /** 
     * Name des Künstlers, der das Kunstwerk erstellt hat 
     */
    private String kuenstler;
    /** 
     * Jahr, in welchem das Kunstwerk hergestellt wurde
     */
    private String jahr; 
    /** 
     * Thema, in welches Herr Schneider das Kunstwerk eingeordnet hat
     */
    private String thema;
    /**
     * Bewertung der Attraktivität des Kunstwerkes durch Herrn Schneider in %
     */
    private int attraktivitaetswert;
    /** 
     * Kosten für die Ausleihe des Kunstwerkes in Euro
     */
    private int kosten; 
    /**
     * Angabe des Museums, welches das Kunstwerk zur Verfügung stellt 
     */
    private String ausleihendesMuseum;
    /**
     * Höhe des Kunstwerkes in cm
     */
    private int hoehe;
    /**
     * Breite des Kunstwerkes in cm;
     */
    private int breite; 

    /**
     * Konstruktor für Objekte der Klasse Kunstwerk
     */
    public Kunstwerk()
    {
        // Instanzvariable initialisieren
        
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
}
