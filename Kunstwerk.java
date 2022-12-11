
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
    public String bezeichnung; 
    /** 
     * Name des Künstlers, der das Kunstwerk erstellt hat 
     */
    public String kuenstler;
    /** 
     * Jahr, in welchem das Kunstwerk hergestellt wurde
     */
    public String jahr; 
    /** 
     * Thema, in welches Herr Schneider das Kunstwerk eingeordnet hat
     */
    public String thema;
    /**
     * Bewertung der Attraktivität des Kunstwerkes durch Herrn Schneider in %
     */
    public int attraktivitaetswert;
    /** 
     * Kosten für die Ausleihe des Kunstwerkes in Euro
     */
    public int kosten; 
    /**
     * Angabe des Museums, welches das Kunstwerk zur Verfügung stellt 
     */
    public String ausleihendesMuseum;
    /**
     * Höhe des Kunstwerkes in cm
     */
    public int hoehe;
    /**
     * Breite des Kunstwerkes in cm;
     */
    public int breite; 
}
