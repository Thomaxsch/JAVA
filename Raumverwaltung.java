
/**
 * Die Klasse Raumverwaltung ist eine Containerklasse und dient zur Verwaltung von Objekten der Klasse Raum.
 * Sie ermöglicht das Suchen und das Zugreifen auf bestimmte Objekte der Klasse Raum.
 * Sie steht in einer Aggregation zu der Klasse Raum. Sie verfügt ueber keine eigenen Objekte.
 * 
 * 
 * @author Carla Saradeth 
 * @version Dez 2022
 */
public abstract class Raumverwaltung
{
    // Attribute der Klasse Raumverwaltung
    /** 
     * Die Klasse Raumverwaltung hat die Klasse Raum als Attribut, um auf Objekte der Klasse Raum zugreifen zu koennen.
     */
    private Raum raum;
    
    

    
    //Methoden der Klasse Raumverwaltung
    //Methoden technische Aspekte
    /** Ermittelt ein Objekt der Klasse Raum, dessen Hoehe größer ist als die Hoehe eines Kunstgegenstanden oder
     *  Kunstinstallation unter Berücksichtigung eine Mindestabstandes von 2m.
     *  (hoeheRaumInCm - 2m >= Hoehe eines Kunstgegenstanden oder Kunstinstallation)
     *  @return     Objekt der Klasse Raum, das Kriterien in Hoehe erfüllt.
     */
    public Raum getRaumNachHoehe()
    {
        // tragen Sie hier den Code ein
        return raum;
    }
    
    /** Ermittelt ein Objekt der Klasse Raum, dessen Laenge größer ist als die Laenge eines Kunstgegenstanden oder
     *  Kunstinstallation unter Berücksichtigung eine Mindestabstandes von 2m.
     *  (laengeRaumInCm - 2m >= Laenge eines Kunstgegenstanden oder Kunstinstallation)
     *  @return     Objekt der Klasse Raum, das Kriterien in Laenge erfüllt.
     */
    public Raum getRaumNachLaenge()
    {
        // tragen Sie hier den Code ein
        return raum;
    }
    
    /** Ermittelt ein Objekt der Klasse Raum, dessen Breite größer ist als die Breite eines Kunstgegenstanden oder
     *  Kunstinstallation unter Berücksichtigung eine Mindestabstandes von 2m.
     *  (breiteRaumInCm - 2m >= Breite eines Kunstgegenstanden oder Kunstinstallation)
     *  @return     Objekt der Klasse Raum, das Kriterien in Breite erfüllt.
     */
    public Raum getRaumNachBreite()
    {
        // tragen Sie hier den Code ein
        return raum;
    }
   
    //Methoden organisatorische Aspekte
    
    
    
    
    
}
