// Importiert die Klasse Vector
import java.util.Vector;
import java.util.*;
import java.util.Comparator;

/**
 * Bei der Klasse Kunstwerkverwaltung handelt es sich um eine Verwaltungsklasse, welche diverse Funktionen in Bezug auf die Klassen "Kunstinstallationen", "Bild" und "Kunstgegenstand" durchführen kann. 
 * So kann diese die o.g. Klassen nach Objekten durchsuchen und gezielt darauf zugreifen.  
 * Die Kunstwerkverwaltung ist zudem geeignet die Logikklassen gezielt durch Methoden zu unterstützen. 
 * Aufgrunddessen ist sie in der Lage die Klasse der "Ausstellungsplanung" zu entlasten. 
 * 
 * Die Ausstellungsplanung kann sich folglich auf die Kunstwerkverwaltung beziehen, um daraus die richtige Auswahl der Ausstellung und Ausleihe zu treffen. 
 * 
 * @author (Alexander Kipry) 
 * @version (12.01.2023)
*/

public class Kunstwerkverwaltung
{
    /** In diesem Attribut werden die einzelnen Kunstwerke aus dem Angebot mittels der der Containerklasse Vektor verwaltet. */
    private Vector<Kunstwerk> kunstwerkVector;
   
    /**
     * Konstruktor für Objekte der Klasse Kunstwerkverwaltung. Dieser Konstruktor erzeugt ein leeren Vector / Angebot, welches später über Methoden mit Kunstwerken befüllt werden kann. 
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
     * Methode zum Ermitteln eines Kunstwerkes anhand seiner laufendenNummer
     * 
     * @return das Kunstwerk zur laufendenNummer
     */
    public Kunstwerk showKunstwerkZuLaufendeNummer(short in_laufendeNummer) {
    boolean found = false;
    Kunstwerk kw_out = null;
    for (Kunstwerk kw : kunstwerkVector) {
        
        if (kw.getLaufendeNummer() == in_laufendeNummer) 
        {
            System.out.println(kw);
            found = true;
            kw_out=kw;
        }   
    }   
    if(!found)
        {
        System.out.println("Es wurde kein Kunstwerk zur LaufendenNummer gefunden");
        return null;
        }
        return kw_out;
    }  
    
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
    
    private boolean überprüfeKunstwerkzuRaumdimension(
        int verfuegbarWandWest,int verfuegbarWandOst,int verfuegbarWandNord,int verfuegbarWandSued,  // relevant für Bilder (vier Wände)
        int verfuegbarLaengeRaum,int verfuegbarBreiteRaum,                                           // relevant für G und I (laengs/quer bzw Raumfläche)
        int verfuegbarHoeheRaum,                                                                     // relevant für alle KW)
        Kunstwerk kw
        )                                                                     
    {
            boolean passtDimension=false;
            if (kw.getArt()=='B')
            {
                
                if (kw.getBreite()<=verfuegbarWandWest | kw.getBreite()<=verfuegbarWandOst  | kw.getBreite()<=verfuegbarWandNord  | kw.getBreite()<=verfuegbarWandSued)
                {
                    passtDimension=true;
                }
            }
            if (kw.getArt()=='G'| kw.getArt()=='I')
            {
                int dimy=kw.getBreite();
                int dimx=0;
                if (kw.getArt()=='G')
                {
                    dimx=((Kunstgegenstand) kw).getLaenge();// cast, sonst können wir die Länge nicht abrufen
                }
                if (kw.getArt()=='G')
                {
                    dimx=((Kunstgegenstand) kw).getLaenge();// cast, sonst können wir die Länge nicht abrufen
                }
                
                if ((dimx<=verfuegbarBreiteRaum & dimy<=verfuegbarLaengeRaum) | 
                    (dimy<=verfuegbarLaengeRaum & dimx<=verfuegbarBreiteRaum)) // entweder laengs oder quer
                {
                    passtDimension=true;
                    
                }
            }
             
            if (!(kw.getHoehe()<=verfuegbarHoeheRaum))
            {
                passtDimension=false;
            }
            return passtDimension;
        } 
        
    private boolean überprüfeKunstwerkWeitereParameter(        
        double restbudget,                                                                           // verfügbares Restbudget (double)
        ArrayList<Kunstwerk> kunstwerkeSchonZugeordnet,                                              // bisher platzierte Kunstwerke (Arraylist)
        double anteilI,                                                                              // Anteil der mit I belegten Räume. (double))
        Kunstwerk kw)
    {
        boolean passtBudget=(kw.getKosten()<=restbudget); // TO DO: ist es ein Problem das wir Kosten als INT, aber Budget/Obergrenze als DOUBLE?
        boolean passtZuordnenbar = (!kunstwerkeSchonZugeordnet.contains(kw));
        boolean passtInstallationAnteilI=true;
        
        if (anteilI>0.33) // bei sonst zu großem Anteil an Installationen in der Ausstellung
            {
            if (kw.getArt()=='I')
            {
            passtInstallationAnteilI=false;
            }
            }
        return passtBudget & passtZuordnenbar & passtInstallationAnteilI;
    }
        
    public short naechstesZuSetzendesKunstwerk(
        String schwerpunktthema,
        int verfuegbarWandWest,int verfuegbarWandOst,int verfuegbarWandNord,int verfuegbarWandSued,  // relevant für Bilder (vier Wände)
        int verfuegbarLaengeRaum,int verfuegbarBreiteRaum,                                           // relevant für G und I (laengs/quer bzw Raumfläche)
        int verfuegbarHoeheRaum,                                                                     // relevant für alle KW
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
            boolean passtSchwerpunkt=(kw.getThema().equals(schwerpunktthema));
            boolean passtDimension= überprüfeKunstwerkzuRaumdimension(verfuegbarWandWest, verfuegbarWandOst, verfuegbarWandNord, verfuegbarWandSued, verfuegbarLaengeRaum, verfuegbarBreiteRaum, verfuegbarHoeheRaum, kw);
            
            if (passtSchwerpunkt & passtDimension & überprüfeKunstwerkWeitereParameter(restbudget, kunstwerkeSchonZugeordnet, anteilI, kw))
            {
               bestes_kw_lfd_nr = kw.getLaufendeNummer();
               break; // die Schleife endet, wenn das erste Mal ein KW passt
            } 
        } 
       
       System.out.println("Index bestes KW:"+bestes_kw_lfd_nr);
        
       return bestes_kw_lfd_nr; // -1 wenn keins gefunden wurde
        
    }   
    
    public short erweitereRaumlösung(
        int verfuegbarWandWest,int verfuegbarWandOst,int verfuegbarWandNord,int verfuegbarWandSued,  // relevant für Bilder (vier Wände)
        int verfuegbarLaengeRaum,int verfuegbarBreiteRaum,                                           // relevant für G und I (laengs/quer bzw Raumfläche)
        int verfuegbarHoeheRaum,                                                                     // relevant für alle KW
        double restbudget,                                                                           // verfügbares Restbudget (double)
        ArrayList<Kunstwerk> kunstwerkeSchonZugeordnet,                                              // bisher platzierte Kunstwerke (Arraylist)
        double anteilI,                                                                              // Anteil der mit I belegten Räume. (double)
        double qualitaetsgewicht,                                                                    // Gewichtung von Qualität und Quantität
        int minFeuchteRaum, int maxFeuchteRaum,int minTempRaum, int maxTempRaum,                     // relevant nur für Bilder. Bild muss innerhalb dieser Grenzen sein.
        ArrayList<String> welcheThemenDuerfenNochInRaum,     // falls diese ArrayList genau (!) drei Elemente enthält, sind nur noch KW mit einem dieser Themen erlaubt                                       
        String welcheTypenDuerfenNochInRaum                  // es wird "BIG" oder "BG" übergeben (ob der Typ egal ist oder es nur noch B/G sein darf)
        ) 
        {
       short bestes_kw_lfd_nr = -1; // wir suchen das beste Kunstwerk. Wenn wir keins finden, geben wir den Wert "-1" zurück.
       for (Kunstwerk kw : bildeKriteriumsliste(qualitaetsgewicht)) {
            boolean passtDimension= überprüfeKunstwerkzuRaumdimension(verfuegbarWandWest, verfuegbarWandOst, verfuegbarWandNord, verfuegbarWandSued, verfuegbarLaengeRaum, verfuegbarBreiteRaum, verfuegbarHoeheRaum, kw);
            
            if  (passtDimension & überprüfeKunstwerkWeitereParameter(restbudget, kunstwerkeSchonZugeordnet, anteilI, kw))
            {
               bestes_kw_lfd_nr = kw.getLaufendeNummer();
               break; // die Schleife endet, wenn das erste Mal ein KW passt
            } 
        } 
       
       System.out.println("Index bestes KW:"+bestes_kw_lfd_nr);
        
       return bestes_kw_lfd_nr; // -1 wenn keins gefunden wurde
    }   
}
    



