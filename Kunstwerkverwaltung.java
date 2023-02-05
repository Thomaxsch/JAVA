// Importiert die Klasse Vector
import java.util.Vector;
import java.util.*;
import java.util.Comparator;

/**
 * Bei der Klasse Kunstwerkverwaltung handelt es sich um eine Verwaltungsklasse, welche diverse Funktionen in Bezug auf die Klassen 
 * "Kunstinstallationen", "Bild" und "Kunstgegenstand" durchführen kann. 
 * So kann diese die o.g. Klassen nach Objekten durchsuchen und gezielt darauf zugreifen.  
 * Die Kunstwerkverwaltung ist zudem geeignet die Logikklassen gezielt durch Methoden zu unterstützen. 
 * Aufgrunddessen ist sie in der Lage die Klasse der "Ausstellungsplanung" zu entlasten. 
 * 
 * Die Ausstellungsplanung kann sich folglich auf die Kunstwerkverwaltung beziehen, um daraus die richtige Auswahl der Ausstellung 
 * und Ausleihe zu treffen. 
 * 
 * @author (Alexander Kipry) 
 * @version (05.02.2023)
*/

public class Kunstwerkverwaltung
{   //Attribute der Klasse Kunstwerkverwaltung
    /** In diesem Attribut werden die einzelnen Kunstwerke aus dem Angebot mittels der der Containerklasse Vektor verwaltet. */
    private Vector<Kunstwerk> kunstwerkVector;
    
    //Konstruktoren der Klasse Kunstwerkverwaltung
    /**
     * Konstruktor für Objekte der Klasse Kunstwerkverwaltung. Dieser Konstruktor erzeugt ein leeren Vector / Angebot, 
     * welches später über Methoden mit Kunstwerken befüllt werden kann. 
     */
    public Kunstwerkverwaltung()
    {
       //anlegen eines neuen Vectorobjektes
       kunstwerkVector = new Vector<Kunstwerk>();
    }
    
    //Methoden für die Vektorliste der Klasse Kunstwerkverwaltung 
    /**
     * Diese Methode fügt dem Vector der Kunstwerkverwaltung ein Kunstwerk hinzu.
     * 
     * @param kunstwerk  Kunstwerk (Kunsinstallation, Bild, Kunstgegenstand) der hinzugefügt werden soll
     */
    public void addKunstwerk(Kunstwerk in_kunstwerk)
    {
        if (!kunstwerkVector.contains(in_kunstwerk)){ // das Kunstwerk kann sich nur einmal im Vector befinden. 
            kunstwerkVector.add(in_kunstwerk);
        }
        else {
            System.out.println("Kunstwerk ist bereits in der Kunstwerkverwaltung enthalten. Es kann nicht erneut hinzugefuegt werden");
        }
        
    }
    
    /** 
     * Diese Methode entfernt aus dem Vektor der Kunstwerkverwaltung ein Kunstwerk. 
     * 
     * @param kunstwerk Kunstwerk das gelöscht werden soll
     */
    public void removeKunstwerk(Kunstwerk in_kunstwerk)
    {
        if (kunstwerkVector.contains(in_kunstwerk)){ // das Kunstwerk muss bereits im Vector vorhanden sein, um es daraus zu entfernen.
            kunstwerkVector.removeElement(in_kunstwerk);
        }
        else {
            System.out.println("Kunstwerk ist nicht in der Kunstwerkverwaltung enthalten. Es kann nicht entfernt werden");
        }
    }   
    
    /** Ermittelt die Anzahl an Kunstwerken in der Vektorliste der Kunstwerkverwaltung .
     *  
     *  @return Die Anzahl der Objekte der Kunstwerk-Klassen in der Kunstwerkverwaltung. 
     */
    public int sizeKunstwerkverwaltung()
    {
        return kunstwerkVector.size();
    }
    
    /** 
     * Loescht alle Kunstwerke aus dem Vektor der Kunstwerkverwaltung.
     */
    public void clearKunstwerkverwaltung()
    {
        kunstwerkVector.clear();
    }
    
    /**
     * Methode zur Umsetzung des Aufrufens aller Kunstwerke innerhalb der Vektorliste
     * 
     * @return den Vector der Kunstwerkverwaltung
     */
    public Vector <Kunstwerk> getKunstwerkVector()
    {
        return kunstwerkVector;
    }
    
     /**
     * Gibt eine textuelle Beschreibung aller Kunstwerke im Vector aus 
     */
    public void showKunstwerke()
    {
        for(Kunstwerk kw : kunstwerkVector) 
        {
            System.out.println(kw); // hierbei wird automatisch auf die "toString" Methode aus der Klasse Kunstwerk zugegriffen
        }
    } 
    
    //Methoden für die Zuordnung
    /**
     * Methode zum Sortieren der Kunstwerke aus dem Vector nach deren Attrakvitaet. 
     * 
     * @return den Vector sortiert nach der Attraktvitaet in absteigender Reihenfolge. 
     */
    public Vector <Kunstwerk> sortAttraktivitaet()
    {
        Collections.sort(kunstwerkVector, Collections.reverseOrder()); 
        //das "reverseOrder" sorgt dafür, dass das Kunstwerk mit der höchsten Attraktivitaet zu erst angezeigt wird. 
        return kunstwerkVector;
        }   

    /**
     * Methode, die alle vorkommenden Themen innerhalb des Kunstwerkvectors ausgibt
     * 
     * @return alle vorkommenden Themen
     */
    public ArrayList <String> getVorkommendeThemen ()
    {
        ArrayList <String> vorkommendeThemen = new ArrayList <String>();
        for(Kunstwerk kw : kunstwerkVector)     
        {
            if (!vorkommendeThemen.contains(kw.getThema()))     /*hierbei werden mittels einer Schleife alle Kunstwerke innerhalb des Vector
            Kunstwerks durchlaufen. Dabei wird das Thema des jeweiligen Kunstwerkes an die ArrayList "vorkommendeThemen" übergeben und falls noch nicht 
            vorhanden dieser hinzugefügt. Falls es bereits vorhanden ist, wird das Thema des nächstes Kw abgerufen. 
            Ist der Kunstwerkvector durchlaufen, so können am Ende alle vorkommendeThemen über die ArrayList "vorkommendeThemen"
            abgerufen werden  */
            {
                vorkommendeThemen.add(kw.getThema());
            }
        } 
        return vorkommendeThemen; //Themen der bereits hinzugefügten Kunstwerke in einer ArrayList gesammelt. 
    }
    
    /**
     * Methode, die alle Partnermuseen innerhalb des KunstwerkVectors ausgibt
     * 
     * @return alle Partnermuseeen 
     */
    
    public ArrayList <String> getAllePartnermuseen()
    {
        ArrayList <String> allePartnermuseen = new ArrayList <String>();
        for(Kunstwerk kw : kunstwerkVector) /*gleiche Vorgehensweise wie bei der ArrayList "vorkommendeThemen" nur auf die 
        Partnermuseen bezogen. */
        {
            if (!allePartnermuseen.contains(kw.getVerleihendesMuseum()))
            {
                allePartnermuseen.add(kw.getVerleihendesMuseum());
            }
        }
        return allePartnermuseen;
    }
        
    
    /**
     * Methode zum Ermitteln eines Kunstwerkes anhand seiner laufendenNummer
     * 
     * @param in_laufendeNummer die laufendeNummer, zu welchem das Kunstwerk ermittelt werden soll. 
     * @return das Kunstwerk zur laufendenNummer
     */
    public Kunstwerk showKunstwerkZuLaufendeNummer(short in_laufendeNummer) {
    boolean found = false; //boolean um anzugeben, ob es ein Kw zu der lfd. Nummer gibt. 
    Kunstwerk kw_out = null; //Instanziieren eines Kunstwerks auf null. 
    for (Kunstwerk kw : kunstwerkVector) { /* ebenso wird hier der kunstwerVector durchlaufen. Falls die eingegebene laufende Nummer
        der laufendenNummer eines Kunstwerkes entspricht wird found auf true gesetzt und das kw_out dem passenden Kw gleichgesetzt.*/
        
        if (kw.getLaufendeNummer() == in_laufendeNummer) 
        {
            found = true;
            kw_out=kw;
        }   
    }   
    if(!found) //gab es kein Kunstwerk zu der lfd. Nummer so kann keines ausgegeben werden. 
        {
        System.out.println("Es wurde kein Kunstwerk zur LaufendenNummer gefunden");
        return null;
        }
        return kw_out; //Ausgabe des Kunstwerkes, welches der laufenden Nummer entsprochen hat. 
    }  
    
    /**
     * Methode, die einen Vektor ausgibt, welcher nach dem Kriterium absteigend sortiert ist. 
     * 
     * @param qualitaetsgewicht Uebergabe eines Qualitaetsgewichts, nach welchem die Liste gebildet wird.
     * @return eine nach dem Kriterium ausgerichtete Liste. 
     */
    private Vector<Kunstwerk> bildeKriteriumsliste(double qualitaetsgewicht)
    {
     //Bilde für jedes Kunstwerk ein Attraktivitäts-Kriterium und ein AttraktivitätProKosten-Kriterium
        Vector<Double> kriteriumAttrVector = new Vector<Double>();
        Vector<Double> kriteriumAttrProKostenVector = new Vector<Double>(); // wir können hier nicht double nehmen, weil es ein Vector ist, und der erlaubt keine primitives, daher Double
        for (Kunstwerk kw : kunstwerkVector) {
            double kriterium = (double) kw.getAttraktivitaet();
            kriteriumAttrVector.add(kriterium);
        }
        for (Kunstwerk kw : kunstwerkVector) {
            double kriterium = (double) kw.getAttraktivitaet()/ (double) kw.getKosten();
            kriteriumAttrProKostenVector.add(kriterium);
        }
        
        // Wir normalisieren die beiden Kriteriumsvektoren, sodass sie beide jeweils auf einer Skala von Null bis Eins gehen
        double maxAttr=Collections.max(kriteriumAttrVector);
        double maxAttrProKosten=Collections.max(kriteriumAttrProKostenVector);
        
        for (int i = 0; i < kriteriumAttrVector.size(); i++)
        {
            kriteriumAttrVector.set(i, kriteriumAttrVector.get(i)/maxAttr);
        }
        for (int i = 0; i < kriteriumAttrProKostenVector.size(); i++)
        {
            kriteriumAttrProKostenVector.set(i, kriteriumAttrProKostenVector.get(i)/maxAttrProKosten);
        }
        
        // Wir fusionieren die Kriterien zu einem einzigen gewichteten Kriterium (welches Werte von 0 bis 2 hat)
        Vector<Double> kriteriumVector = new Vector<Double>(); // wir können hier nicht double nehmen, weil es ein Vector ist, und der erlaubt keine primitives, daher Double
        
        for (int i = 0; i < kunstwerkVector.size(); i++)
        {
            kriteriumVector.add(kriteriumAttrVector.get(i)*qualitaetsgewicht + kriteriumAttrProKostenVector.get(i)*(1-qualitaetsgewicht));
        }
    
        /**
         * wir haben nun eine Liste mit den Kritriumswerten je Kunstwerk.
        */
        // Wir bilden nun eine nach dem Kriterium absteigend sortierte Liste aller Kunstwerke:
        
        Vector<Kunstwerk> kunstwerkeSortiertVector = new Vector<Kunstwerk>(); // für unser Ergebnis
        
        Vector<Kunstwerk> kunstwerkVectorKlon = (Vector<Kunstwerk>) kunstwerkVector.clone(); // wir machen einen Klon vom Kunstwerkvektor, den wir später Element für Element reduzieren.
                                                                                            // Ein Klon, damit wir nicht unseren ursprünglichen Kunstwerkvektor kaputt machen.
        Vector<Double> kriteriumVectorKlon = (Vector<Double>) kriteriumVector.clone();   // Ebenso ein Klon vom Kriteriumsvektor. Double statt double, weil Vectors keine primitives erlauben.
        
        for (Integer i = 0; i < kunstwerkVector.size(); i++) { // wir gehen alle Kunstwerke durch
            
            Double naechstBesterKriteriumsWert = Collections.max(kriteriumVectorKlon); // größter Wert im Kriteriumsvektor
            int indexBesterWert = kriteriumVectorKlon.indexOf(naechstBesterKriteriumsWert); // dessen Index
            
            /* Zum testen:
            System.out.println(naechstBesterKriteriumsWert);
            System.out.println(indexBesterWert);
            System.out.println(kunstwerkVectorKlon.size());
            System.out.println(kriteriumVectorKlon.size());
            System.out.println(kunstwerkVectorKlon.get(indexBesterWert));
            */
           
            Kunstwerk naechstBestesKunstwerk = kunstwerkVectorKlon.get(indexBesterWert);
            kunstwerkeSortiertVector.add(naechstBestesKunstwerk);// wir schreiben das beste Kunstwerk als nächstes Element in kunstwerkeSortiertVector
            
            //dann nehmen wir sowohl aus den Klonen vom kriteriumVector wie auch vom kunstwerkVector dieses Element heraus, sodass
            // wir in der der nächsten Iterationen aus den restlichen Kunstwerken den besten Kandidaten bestimmen usw usw. Somit brauchen sich die Klone komplett auf und am Ende sind
            // gar keine Werte mehr erhalten. Dafür haben wir 
            kunstwerkVectorKlon.removeElement(naechstBestesKunstwerk);
            kriteriumVectorKlon.removeElement(naechstBesterKriteriumsWert);
            
            /* Zum testen:
            System.out.println(kunstwerkVectorKlon.size());
            System.out.println(kriteriumVectorKlon.size());
            */
        }
            
        /**
         * wir haben nun eine nach dem Kriterium absteigend sortierte Liste aller Kunstwerke.
         */ 
        return kunstwerkeSortiertVector;
    }
    
    /**
     * Methode, welche anhand eines Wahrheitswertes ausgibt, ob das Kunstwerk in den Raum passt
     * 
     * @param verfuegbarWandWest                        restliche Verfügbare Fläche auf der Wand west. 
     * @param verfuegbarWandOst                         restliche Verfügbare Fläche auf der Wand Ost.
     * @param verfuegbarWandNord                        restliche Verfügbare Fläche auf der Wand Nord.
     * @param verfuegbarWandSued                        restliche Verfügbare Fläche auf der Wand Sued.
     * @param verfuegbarLaengeRaum                      restliche Verfügbare Länge im Raum.
     * @param verfuegbarBreiteRaum                      restliche verfügbare Breite im Raum.
     * @param verfuegbarHoeheRaum                       restliche verfügbare Höhe im Raum.
     * @param verfuegbarHoeheRaumBilder                 restliche verfügbare Raumhöhe für Bilder.
     * @param kw                                        übergebenes Kunstwerk, welches diesbezüglich überprüft wird.
     * 
     * @return true, falls das Kw in den Raum passt. 
     */
    private boolean überprüfeKunstwerkzuRaumdimension(
        int verfuegbarWandWest,int verfuegbarWandOst,int verfuegbarWandNord,int verfuegbarWandSued,  // relevant für Bilder (vier Wände)
        int verfuegbarLaengeRaum,int verfuegbarBreiteRaum,                                           // relevant für G und I (laengs/quer bzw Raumfläche)
        int verfuegbarHoeheRaum,                                                                     // relevant für G und I
        int verfuegbarHoeheRaumBilder,                                                               // relevant für B
        Kunstwerk kw)                                                                     
    {
        boolean passtBildWandbreite=false;      //standard, dass es nicht passt.
        boolean passtBildHoehe=false;
        
        /*die folgenden If Methoden sind nach Bild "B" und Kunstgegenstand "G" und Kunstinstallation "I" aufgeteilt, da diese unterschiedliche
        Anforderungen an den Raum haben.*/
        if (kw.getArt()=='B') // dies wird nur überprüft, wenn das Kunstwerk ein Bild ist. 
        {
            if (kw.getBreite()<=verfuegbarWandWest | kw.getBreite()<=verfuegbarWandOst  | kw.getBreite()<=verfuegbarWandNord  | 
            kw.getBreite()<=verfuegbarWandSued) 
            {
                passtBildWandbreite=true;
            }
            if (kw.getHoehe()<=verfuegbarHoeheRaumBilder)
            {
                passtBildHoehe=true; 
            }
            return (passtBildWandbreite & passtBildHoehe);
        }
        
        boolean passtIGRaumflaeche=false;
        boolean passtIGHoehe=false;
        if (kw.getArt()=='G'| kw.getArt()=='I') //Überprüfung für Kunstgegenstand und Kunstinstallation. 
        {
            if (kw.getHoehe()<=verfuegbarHoeheRaum)
            {
                passtIGHoehe=true; 
            }
            
            int dimy=kw.getBreite();
            int dimx=0;
            if (kw.getArt()=='G')
            {
                dimx=((Kunstgegenstand) kw).getLaenge();// cast, sonst können wir die Länge nicht abrufen
            }
            if (kw.getArt()=='I')
            {
                dimx=((Kunstinstallation) kw).getLaenge();// cast, sonst können wir die Länge nicht abrufen
            }
            
            if ((dimx<=verfuegbarBreiteRaum & dimy<=verfuegbarLaengeRaum) | 
                (dimy<=verfuegbarLaengeRaum & dimx<=verfuegbarBreiteRaum)) // entweder laengs oder quer
            {
                passtIGRaumflaeche=true;
            }
        }
        return (passtIGRaumflaeche & passtIGHoehe);
    } 
    
    /**
     * Methode, welche überprüft, ob folgende weitere Parameter für das ausgewählte Kunstwerk zutreffen:
     * 
     * @param restbudget                                    Kosten für das Kunstwerk passen ins Restbudget?
     * @param kunstwerkeSchonZugeordnet                     bisher plazierte Kunstwerke
     * @param anteilI                                       Anteil der mit I belegten Räume
     * @param kw                                            Kunstwerk, welches überprüft wird.
     * 
     * return true, falls das Kunstwerk auf diese Parameter zutrifft. 
     */
    private boolean überprüfeKunstwerkWeitereParameter(        
        double restbudget,                                                                           
        ArrayList<Kunstwerk> kunstwerkeSchonZugeordnet,                                            
        double anteilI,                               
        Kunstwerk kw)
    {
        boolean passtBudget=(kw.getKosten()<=restbudget); //Überprüfung: Passen die Kosten für das Kunstwerk ins Restbudget?
        boolean passtZuordnenbar = (!kunstwerkeSchonZugeordnet.contains(kw)); //Ist das Kunstwerk nicht bereits zugeordnet?
        boolean passtInstallationAnteilI=true; 
        
        if (anteilI>0.33) // bei sonst zu großem Anteil an Installationen in der Ausstellung - es sollen also maximal 33% aus Installation bestehen.
            {
            if (kw.getArt()=='I')
            {
            passtInstallationAnteilI=false;
            }
            }
        return passtBudget & passtZuordnenbar & passtInstallationAnteilI;
    }
    
    /**
     * Methode, welche überprüft, ob die Raumfeuchtigkeits und Temperaturbedingungen auf die Voraussetzung des Bildes zutreffen. 
     *  
     * @param minFeuchteRaum                                        minFeuchte des Raumes
     * @param maxFeuchteRaum                                        maxFeuchte des Raumes
     * @param minTempRaum                                           minTemp des Raumes 
     * @param maxTempRaum                                           maxTemp des Raumes
     * @param kw                                                    Kunstwerk, welches auf die Bedingungen überprüft werden soll. 
     * 
     * @return true, falls der Raum die Voraussetzungen des Bildes erfüllt. 
     */
    private boolean checkRaumFeuchteundTemp(int minFeuchteRaum, int maxFeuchteRaum,int minTempRaum, int maxTempRaum, Kunstwerk kw)
    {
        /**
         * Wahrheitswert der angibt, ob die vorhandene Luftfeuchtigkeit für das im Raum zum plazierende Bild kompatibel ist. 
         */
        boolean passtRaumFeuchte; 
        /**
         * Wahrheitswert der angibt, ob die vorhandene Raumtemperatur für das im Raum zum plazierende Bild kompatibel ist. 
         */
        boolean passtRaumTemp;
        
        boolean passtFeuchteUndTemp = false; // es wird zunächst davon ausgegangen, dass die Bedingungen nicht stimmig sind. 
        /**
         * // hier wird ein Kunstwerk in ein Bild umgewandelt. Dies wird gemacht, um auf die "getMethoden" wie "getMinTemp" zugreifen zu können.
         */
        Bild b;
        if (kw.getArt()=='B') 
        {
            b = (Bild) kw;
        }
        else
        {
            return true;
        }
         
        // Das folgende wird nur für Bilder durchlaufen
        
        // Wenn (rmin <= bmin <= rmax) | (rmin <= bmax <= rmax) ist das Bild hinsichtlich Temperatur (bzw. Feuchte) platzierbar, 
        // weil die Bandbreite des Bildes dann einen Überlapp hergibt mit den bisher im Raum platzierten Bildern.
        // Die Überlegung dahinter ist in der Methode zuordnung.aktualisiereParameterNachSetzen ausführlicher erläutert.
        if (
            ((minFeuchteRaum <= b.getMinLuft()) & (b.getMinLuft() <= maxFeuchteRaum)) |
            ((minFeuchteRaum <= b.getMaxLuft()) & (b.getMaxLuft() <= maxFeuchteRaum))
           )
        {
            passtRaumFeuchte = true;
        }
        else
        {
            //Das Bild kann nicht in den Raum plaziert werden, da die Luftfeuchtigkeit zu hoch oder zu niedrig sein müsste
            passtRaumFeuchte = false;
        }
        if (
            ((minTempRaum <= b.getMinTemp()) & (b.getMinTemp() <= maxTempRaum)) |
            ((minTempRaum <= b.getMaxTemp()) & (b.getMaxTemp() <= maxTempRaum))
           )
        {
            passtRaumTemp = true;
        }
        else
        {
            //Das Bild kann nicht in den Raum plaziert werden, da die Raumtemperatur zu hoch oder zu niedrig sein müsste
            passtRaumTemp = false;
        }

        passtFeuchteUndTemp = passtRaumFeuchte && passtRaumTemp;

        return passtFeuchteUndTemp;
    }
    /**
     * Methode, welche das nächste, jedoch nach den Kritierien beste, zu setzende Kunstwerk ausgibt.
     * 
     * @param schwerpunktthema                          Schwerpunktthema daes Kunstwerks
     * @param verfuegbarWandWest                        restliche Verfügbare Fläche auf der Wand west. 
     * @param verfuegbarWandOst                         restliche Verfügbare Fläche auf der Wand Ost.
     * @param verfuegbarWandNord                        restliche Verfügbare Fläche auf der Wand Nord.
     * @param verfuegbarWandSued                        restliche Verfügbare Fläche auf der Wand Sued.
     * @param verfuegbarLaengeRaum                      restliche Verfügbare Länge im Raum.
     * @param verfuegbarBreiteRaum                      restliche verfügbare Breite im Raum.
     * @param verfuegbarHoeheRaum                       restliche verfügbare Höhe im Raum.
     * @param verfuegbarHoeheRaumBilder                 restliche verfügbare Raumhöhe für Bilder.
     * @param restbudget                                verfuegbares restbudget
     * @param kunstwerkeSchonZugeordnet                 ArrayList der bereits plazierten Kunstwerke
     * @param anteilI                                   Anteil der Kunstinstallationen in der Ausstellungen (soll nicht höher als 33% sein).
     * @param qualitaetsgewicht                         Qualitaetsgewicht, nach welcher die Ausstellung aufgebaut wird. 
     * 
     * return die laufendeNummer des nächstbesten zu setzenden Kunstwerk.         
     */
    public short naechstesZuSetzendesKunstwerk(
        String schwerpunktthema,
        int verfuegbarWandWest,int verfuegbarWandOst,int verfuegbarWandNord,int verfuegbarWandSued,  // relevant für Bilder (vier Wände)
        int verfuegbarLaengeRaum,int verfuegbarBreiteRaum,                                           // relevant für G und I (laengs/quer bzw Raumfläche)
        int verfuegbarHoeheRaum,                                                                     // relevant für G und I
        int verfuegbarHoeheRaumBilder,                                                               // relevant für B
        double restbudget,                                                                           // verfügbares Restbudget (double)
        ArrayList<Kunstwerk> kunstwerkeSchonZugeordnet,                                              // bisher platzierte Kunstwerke (Arraylist)
        double anteilI,                                                                              // Anteil der mit I belegten Räume. (double)
        double qualitaetsgewicht)                                                                    // Gewichtung der Qualitaet vs Quantität
    {
        /**
         * Als nächstes gehen wir diese von oben nach unten durch und nehmen das erste passende Kunstwerk, das noch nicht platziert wurde
        */

       short bestes_kw_lfd_nr = -1; // wir suchen das beste Kunstwerk. Wenn wir keins finden, geben wir den Wert "-1" zurück.
       
       for (Kunstwerk kw : bildeKriteriumsliste(qualitaetsgewicht)) {
            boolean passtSchwerpunkt=(kw.getThema().equals(schwerpunktthema)); // entspricht das Thema des KW dem Schwerpunktthema?
            boolean passtDimension= überprüfeKunstwerkzuRaumdimension(verfuegbarWandWest, verfuegbarWandOst, verfuegbarWandNord, verfuegbarWandSued,
                                                                    verfuegbarLaengeRaum, verfuegbarBreiteRaum, verfuegbarHoeheRaum,verfuegbarHoeheRaumBilder, kw);

            if (passtSchwerpunkt & passtDimension & überprüfeKunstwerkWeitereParameter(restbudget, kunstwerkeSchonZugeordnet, anteilI, kw))
            {
               bestes_kw_lfd_nr = kw.getLaufendeNummer();
               break; // die Schleife endet, wenn das erste Mal ein KW passt
            } 
        } 
        
       return bestes_kw_lfd_nr; // -1 wenn keins gefunden wurde
        
    }   
    
    /**
     * Methode, welche nach der Minimallösung die Räume weiter befüllt und weiterhin diverse zuvor definierte Kritierien berücksichtigt.
     * 
     * @param schwerpunktthema                          Schwerpunktthema daes Kunstwerks
     * @param verfuegbarWandWest                        restliche Verfügbare Fläche auf der Wand west. 
     * @param verfuegbarWandOst                         restliche Verfügbare Fläche auf der Wand Ost.
     * @param verfuegbarWandNord                        restliche Verfügbare Fläche auf der Wand Nord.
     * @param verfuegbarWandSued                        restliche Verfügbare Fläche auf der Wand Sued.
     * @param verfuegbarLaengeRaum                      restliche Verfügbare Länge im Raum.
     * @param verfuegbarBreiteRaum                      restliche verfügbare Breite im Raum.
     * @param verfuegbarHoeheRaum                       restliche verfügbare Höhe im Raum.
     * @param verfuegbarHoeheRaumBilder                 restliche verfügbare Raumhöhe für Bilder.
     * @param restbudget                                verfuegbares restbudget
     * @param kunstwerkeSchonZugeordnet                 ArrayList der bereits plazierten Kunstwerke
     * @param anteilI                                   Anteil der Kunstinstallationen in der Ausstellungen (soll nicht höher als 33% sein).
     * @param qualitaetsgewicht                         Qualitaetsgewicht, nach welcher die Ausstellung aufgebaut wird.
     * @param minFeuchteRaum                            minFeuchte des Raumes
     * @param maxFeuchteRaum                            maxFeuchte des Raumes
     * @param minTempRaum                               minTemp des Raumes 
     * @param maxTempRaum                               maxTemp des Raumes
     * @param welcheThemenDuerfenNochInRaum             ArrayList, welche angibt, welche Themen noch in den Raum dürfen
     * @param welcheTypenDuerfenNochInRaum              String welcher Ausgibt, welche Art von KW noch im Raum plaziert werden darf.
     * @param gueteRaumAttraktivitaet                   aktueller Durchschnitt der KW im Raum. 
     * @param gueteRaumBelegung                         Anteil der aktuellen Belegung des Raumes. 
     * 
     * @return die lfd. Nummer des zu plazierenden Kunstwerkes.
     */
    public short erweitereRaumlösung(
        int verfuegbarWandWest,int verfuegbarWandOst,int verfuegbarWandNord,int verfuegbarWandSued,  // relevant für Bilder (vier Wände)
        int verfuegbarLaengeRaum,int verfuegbarBreiteRaum,                                           // relevant für G und I (laengs/quer bzw Raumfläche)
        int verfuegbarHoeheRaum,                                                                     // relevant für G und I
        int verfuegbarHoeheRaumBilder,                                                               // relevant für B
        double restbudget,                                                                           // verfügbares Restbudget (double)
        ArrayList<Kunstwerk> kunstwerkeSchonZugeordnet,                                              // bisher platzierte Kunstwerke (Arraylist)
        double anteilI,                                                                              // Anteil der mit I belegten Räume. (double)
        double qualitaetsgewicht,                                                                    // Gewichtung von Qualität und Quantität
        int minFeuchteRaum, int maxFeuchteRaum,int minTempRaum, int maxTempRaum,                     // relevant nur für Bilder. Bild muss innerhalb dieser Grenzen sein.
        ArrayList<String> welcheThemenDuerfenNochInRaum,     // falls diese ArrayList genau (!) drei Elemente enthält, sind nur noch KW mit einem dieser Themen erlaubt                                       
        String welcheTypenDuerfenNochInRaum,                 // es wird "BIG" oder "BG" übergeben (ob der Typ egal ist oder es nur noch B/G sein darf)
        double gueteRaumAttraktivitaet,            // Aktueller Durchschnitt der Attraktivität der Kunstwerke im Raum -> wir platzieren nur KW mit höherem Attraktivitätswert...
        double gueteRaumBelegung)                  // Anteil der aktuellen Wandbelegung des Raumes mit Bildern -> ... es sei denn es geht um ein Bild und die Raumbelegung ist noch < 60%
        {
       short bestes_kw_lfd_nr = -1; // wir suchen das beste Kunstwerk. Wenn wir keins finden, geben wir den Wert "-1" zurück.
       for (Kunstwerk kw : bildeKriteriumsliste(qualitaetsgewicht)) { //durchlaufen der ArrayList bildeKritieriumsListe, nach den zu setzenden Kw. 
           /** 1. Raumbedinungen für Bilder passend? */
           boolean passtRaumFeuchteUndTemp = true;  
        
             if (kw.getArt()=='B' & passtRaumFeuchteUndTemp)
            {
                passtRaumFeuchteUndTemp = checkRaumFeuchteundTemp(minFeuchteRaum, maxFeuchteRaum, minTempRaum, maxTempRaum, kw);
                // das Kunstwerk wird nicht plaziert, wenn die Raumbedingungen nicht mit dem Bild kompatibel sind. 
            }   
            
            /** 2. Thema des Kunstwerkes mit Themenvielfalt kompatibel? */
            boolean passtKunstwerkInThemenvielfalt = true;
            
            if (welcheThemenDuerfenNochInRaum.size() == 3)
            {
                passtKunstwerkInThemenvielfalt = welcheThemenDuerfenNochInRaum.contains(kw.getThema());
                //das Kunstwerk wird nicht plaziert, falls das Kunstwerk eine Thema hat, welches noch nicht in dem Raum plaziert wurde, da sonst ein 4. neues Thema gesetzt werden würde. 
            }
            
            /** 3. Art des Kunstwerkes noch mit Typem im Raum kompatibel? */
            boolean passtKunstwerkTypInRaum = true;
            
            if (kw.getArt() == 'I')
            {
                passtKunstwerkTypInRaum = welcheTypenDuerfenNochInRaum.equals("BIG");
                // hiermit wird verdeutlicht, dass Kunstinstallationen nur plaziert werden können, wenn der Raum leer ist, also "BIG" übergeben wurde. 
            }
            
            /** 4. Verbessert das Kunstwerk noch den Raum? Allgemein: 
             * Bei Bildern unter 60% Belegung = JA!  Damit der Raum nicht zu leer steht.
             * Sobald 60% belegt sind, werden Bilder nur noch plaziert, wenn sie die Attraktivitaet des Raumes nicht herunterstufen.
               Kunstgegenstände werden allgemein nur plaziert solange sie den Attraktivitaetsmittelwert nicht herabsenken.
               Kunstinstallationen stehen sowieso alleine im Raum. */
               
            boolean verbessertKunstwerkRaum = true;
            
            if ((kw.getArt() == 'B')     &  ((gueteRaumAttraktivitaet <= kw.getAttraktivitaet()) | (gueteRaumBelegung <= 0.6)))
            {
                verbessertKunstwerkRaum = true;
                //wenn der Raum unter 60% belegt ist, kann das Bild noch in den Raum belegt werden, damit dieser nicht zu leer steht. 
            }
            else if (((kw.getArt() == 'I') | (kw.getArt() == 'G'))        &  (gueteRaumAttraktivitaet <= kw.getAttraktivitaet()))
            {
                verbessertKunstwerkRaum = true;
                //hier wird auch nochmal geprüft, ob auch die Installationen oder Kunstgegenstände den Raum verbessern. Bei Installationen ist jedoch immer davon auszugehen,
                //da diese sowieso nur alleine in einem Raum stehen können. 
            }
            
            else 
            {
                verbessertKunstwerkRaum = false;
                /*Es wird false ausgegeben, wenn folgende Bedingungen erfüllt sind:
                 * Bei Bildern: Der Raum ist bereits über 60% belegt und das Bild würde die Attraktivitaet des Raumes verschlechtern.
                 * Bei Kunstgegenständen: Der Kunstgegenstand liegt unter dem Attraktivitaetsmittelwert und würde folglich auch den Raum verschlechtern. 
                */
            }
            
            boolean passtDimension= überprüfeKunstwerkzuRaumdimension(verfuegbarWandWest, verfuegbarWandOst, verfuegbarWandNord, verfuegbarWandSued, 
            verfuegbarLaengeRaum, verfuegbarBreiteRaum, verfuegbarHoeheRaum, verfuegbarHoeheRaumBilder, kw);
            
            if  (passtDimension & überprüfeKunstwerkWeitereParameter(restbudget, kunstwerkeSchonZugeordnet, anteilI, kw) & passtRaumFeuchteUndTemp
            & passtKunstwerkInThemenvielfalt & passtKunstwerkTypInRaum & verbessertKunstwerkRaum) 
            {
               bestes_kw_lfd_nr = kw.getLaufendeNummer();
               break; // die Schleife endet, wenn das erste Mal ein KW passt
            } 
        
        }
    
       return bestes_kw_lfd_nr; // -1 wenn keins gefunden wurde
    }    
}
    



