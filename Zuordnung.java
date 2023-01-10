import java.util.*;
/**

 * 
 * @author Thomas Scheidt 
 * @version 19.12.2022
 */
public class Zuordnung
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
   
    private Kunstwerk [] kunstwerkeArray; // um die Referenzen auf die Kunstwerke vom Import aufzunehmen (alle Kunstwerke)
    private Raum [] raeumeArray; // um die Referenzen auf die Räume vom Import aufzunehmen (alle Räume)
    
    private ArrayList <ArrayList <Kunstwerk >> denRaeumenZugeordneteKunstwerke = new ArrayList <ArrayList <Kunstwerk >>(); // Liste von (Kunstwerke im Raum) pro Raum. Nested array list.
    //  die äußere ArrayList wird später so viele Elemente haben wie es Räume gibt
    // während die innere ArrayList die Anzahl der Kunstwerke in einem konkreten Raum angibt (Anzahl Elemente der inneren Arraylist: von keins ... bis theroetisch max Anzahl Kunstwerke)
    
    //// Auch die folgenden Arrays zu Raumdimensionen werden später so viele Elemente habe wie es Räume gibt (d.h. je ein Wert von z.B. Höhe pro Raum)
    //relevant für B:
    private int [] verfuegbarWandWest; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandOst;  // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandNord; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandSued; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    //relevant für KG und KI:
    private int [] verfuegbarLaengeRaum; // Liste von (noch verfuegbarer Raumdistanz) pro Raum
    private int [] verfuegbarBreiteRaum; // Liste von (noch verfuegbarer Raumdistanz) pro Raum
    //relevant für alle KW:
    private int [] verfuegbarHoeheRaum; // Liste von (Höhe) pro Raum
    
    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
      
    /**
     * Konstruktor für Objekte der Klasse Ausleihe
     */
    public Zuordnung(Vector<Kunstwerk> in_kunstwerke,Vector<Raum> in_raeume)
    {
        // Den eingegebenen Vector zu Kunstwerken in Array umwandeln
        kunstwerkeArray = new Kunstwerk [in_kunstwerke.size()];
        int i = 0;
        for (Kunstwerk k: in_kunstwerke) {
            kunstwerkeArray[i]=(Kunstwerk) k; // cast glaub ich unnötig?
            i++;
        }
        
        // Den dreingegebenen Vector zu Räumen in Array umwandeln
        raeumeArray = new Raum [in_raeume.size()];
        i = 0;
        for (Raum r: in_raeume) {
            raeumeArray[i]=(Raum) r; // cast glaub ich unnötig?
            i++;
        }
        
        // Initialisierungen von Arrays, welche ihrer Natur nach immer eine feste Länge haben. Array Länge ist die Anzahl der Räume!
        verfuegbarWandWest = new int [raeumeArray.length];
        verfuegbarWandOst = new int [raeumeArray.length];
        verfuegbarWandNord = new int [raeumeArray.length];
        verfuegbarWandSued = new int [raeumeArray.length];
        verfuegbarLaengeRaum = new int [raeumeArray.length];
        verfuegbarBreiteRaum = new int [raeumeArray.length];
        verfuegbarHoeheRaum = new int [raeumeArray.length];
        
        // Rufe die konkreten verfügbaren Distanzen via Methode der Klasse Raum ab:
        for (i=0;i<raeumeArray.length;i++)
        {
            verfuegbarWandWest[i]=raeumeArray[i].getWandWest();  // TO DO: netto-Wert abrufen    ===> in KLASSE Raum/Raumverwaltung
            verfuegbarWandOst[i]=raeumeArray[i].getWandOst();  // TO DO: netto-Wert abrufen
            verfuegbarWandNord[i]=raeumeArray[i].getWandNord();  // TO DO: netto-Wert abrufen
            verfuegbarWandSued[i]=raeumeArray[i].getWandSued();  // TO DO: netto-Wert abrufen
            verfuegbarLaengeRaum[i]=raeumeArray[i].getWandNord();// TO DO: netto-Wert abrufen
            verfuegbarBreiteRaum[i]=raeumeArray[i].getWandOst();// TO DO: netto-Wert abrufen
            verfuegbarHoeheRaum[i]=raeumeArray[i].getHoeheRaum();
            
        }
        
        // Initialisiere die äußere Arraylist von denRaeumenZugeordneteKunstwerke
        for (i=0;i<raeumeArray.length;i++)
        {
            denRaeumenZugeordneteKunstwerke.add(new ArrayList<Kunstwerk>()); 
            // somit gibt es je Raum eine Arraylist der Kunstwerke, wobei aktuell bei allen Räumen noch keine Kunstwerke darin sind
        }

    }

    // ==========================================================================
    // === Methoden
    // ==========================================================================    
    
    /**
     * Passt Kunstwerk von den Dimensionen in den Raum (Restriktionen 5, 6, 7)
     * 
     * Gewährleistet auch Restriktion 8, dass KI alleine in Raum, d.h. sonst keine Bilder oder KG
     */
    public boolean passtKunstwerkDimensionalInRaum(Kunstwerk in_Kunstwerk, int i) // Raum i
    {
        boolean passtHoehe = (in_Kunstwerk.getHoehe()<verfuegbarHoeheRaum[i]);
        if (passtHoehe==false)
        {
            return false;
        }
        
        if (in_Kunstwerk.getArt()=='B'|| in_Kunstwerk.getArt()=='G')
        {
            boolean schonAnderesKunstwerkImRaum = !denRaeumenZugeordneteKunstwerke.get(i).isEmpty(); // ! für "nicht"
            if (schonAnderesKunstwerkImRaum)
            {
                boolean blockiertDurchKunstinstallation = (denRaeumenZugeordneteKunstwerke.get(0).get(0).getArt()=='I'); // get 0 get 0 bzgl verschachtelter array list 2 dim
                if (blockiertDurchKunstinstallation == true)
                {
                    return false;
                }
            }

        }
        
        if (in_Kunstwerk.getArt()=='B')
        {
            boolean passtNord = (verfuegbarWandNord[i]-in_Kunstwerk.getBreite()>=0);
            boolean passtSued =(verfuegbarWandSued[i]-in_Kunstwerk.getBreite()>=0);
            boolean passtOst =(verfuegbarWandOst[i]-in_Kunstwerk.getBreite()>=0);
            boolean passtWest =(verfuegbarWandWest[i]-in_Kunstwerk.getBreite()>=0);
            boolean passtBildAnEineWand = passtNord || passtSued  ||  passtOst || passtWest;
            if (passtBildAnEineWand)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        
        if (in_Kunstwerk.getArt()=='G')
        {
            Kunstgegenstand in_Kunstgegenstand = (Kunstgegenstand) in_Kunstwerk; // die Parentclass "Kunstwerk" hat nicht die Methode getLaenge, die wir aber gleich benötigen, daher casten wir
            
            boolean passtQuerA = (verfuegbarLaengeRaum[i]-in_Kunstwerk.getBreite()>=0); //einmal quer im Raum platzieren, wobei quer aktuell beliebig (?) aber orthoginal zu laengs definiert ist
            boolean passtQuerB = (verfuegbarBreiteRaum[i]-in_Kunstgegenstand.getLaenge()>=0);
            boolean passtQuer = passtQuerA && passtQuerB;
            boolean passtLaengsA = (verfuegbarLaengeRaum[i]-in_Kunstgegenstand.getLaenge()>=0); //einmal laengs im Raum platzieren
            boolean passtLaengsB = (verfuegbarBreiteRaum[i]-in_Kunstwerk.getBreite()>=0);
            boolean passtLaengs = passtLaengsA && passtLaengsB;
            
            if (passtQuer||passtLaengs)
            {return true;}
            else
            {return false;}
        }
            
        if (in_Kunstwerk.getArt()=='I') 
        {
            Kunstinstallation in_Kunstinstallation = (Kunstinstallation) in_Kunstwerk;// die Parentclass "Kunstwerk" hat nicht die Methode getLaenge, die wir aber gleich benötigen, daher casten wir
            
            boolean schonAnderesKunstwerkImRaum = !denRaeumenZugeordneteKunstwerke.get(i).isEmpty(); // ! für "nicht"
            
            if (schonAnderesKunstwerkImRaum)
            {return false;}
            
            boolean passtQuerA = (verfuegbarLaengeRaum[i]-in_Kunstwerk.getBreite()>=0); //einmal quer im Raum platzieren, wobei quer aktuell beliebig (?) aber orthoginal zu laengs definiert ist
            boolean passtQuerB = (verfuegbarBreiteRaum[i]-in_Kunstinstallation.getLaenge()>=0);
            boolean passtQuer = passtQuerA && passtQuerB;
            boolean passtLaengsA = (verfuegbarLaengeRaum[i]-in_Kunstinstallation.getLaenge()>=0); //einmal laengs im Raum platzieren
            boolean passtLaengsB = (verfuegbarBreiteRaum[i]-in_Kunstwerk.getBreite()>=0);
            boolean passtLaengs = passtLaengsA && passtLaengsB;
            
            if (passtQuer||passtLaengs)
            {return true;}
            else
            {return false;}
        }
        return false; // Java meckert wenn dies nicht da steht am Ende, aber macht das überhaupt Sinn?!?! => try Formulierung?
            
    }
    
    /**
     * Bilder im Raum müssen hinsichtlich Temperatur und Feuchte ohne Widerspruch sein (Restriktion 4)
     */
    public boolean passtBildFeuchteTemparaturZuBildernInRaum ()
    {
        /**
         
        bild.setMinTemp();
        bild.setMaxTemp();
        bild.setMinLuft();
        bild.setMaxLuft();
        bild.getMinTemp();
        bild.getMaxTemp();
        bild.getMinLuft();
        bild.getMaxLuft();
         */
        return true;
    }
    
    /**
     * in einem Raum dürfen höchstens drei verschiedene Themen sein (Restriktion 3)
     * 
     * Ueberprueft, ob noch ein weiteres Thema in einen Raum passt.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
    public boolean passtKunstwerkHoechstensDreiThemenInRaum ()
    {
        //raumverwaltung.pruefeMax3Themen(); // sind max. 3 unterschiedliche Themen im Raum? [Restriktion3]
        
        
        //// alternativer Name der Methode: pruefeWeiteresThema()
        //Code einfuegen
        //Zugriff auf public Methoden von Klasse Bild, Kunstgegenstand und Kunstinstallation durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausleihe durch Punktoperator 
        //"true" falls verschiedene Themen pro Raum <= 2
        //"false" falls verschiedene Themen pro Raum > 2
        
        return true;
    }
    
    /**
     * 
     */
    public void setzeKunstwerkInRaum ()
    {
        //aktualisiereNochVerfügbarenPlatzImRaumNachSetzen => siehe private Hilfsmethode
        
        /**
        this.ordneZu();
        kunstwerk.setPlaziert();
        kunstwerk.getPlaziert();
        
        bei Bildern: welche der bis 4 wände ist die beste
        bei G: welcher der Ausrichtungen quer oder laengs ist die beste
        
        demRaumZugeordneteKunstwerke.add(Kunstwerk); 
        demRaumZugeordneteKunstwerke.get(0);
    
        try catch macht hier Sinn!
        */
       
        //kunstwerk.getKosten(); // Kosten des Kunstwerks [Restriktion 1]
        //ausstellungsplanung.get_kostenobergrenze(); // [Restriktion 1]
        //this.kostenCounter // wschl. kann im Sinne eines Counters Kosten hier in privater Variable mitgezählt/kumuliert werden [Restriktion1]
    }
    
    /**
     * private hilfsmethode für setzeKunstwerkInRaum
     */
    
    private void aktualisiereNochVerfügbarenPlatzImRaumNachSetzen()
    {
        /// ist nach jedem zugeordneten Kunstwerk zu aktualisieren:
        /// wschl noch extra 1 m oder 2 m abziehen
        //verfuegbarWandWest[i]
        //verfuegbarWandOst[i]
        //verfuegbarWandNord[i]
        //verfuegbarWandSued[i]
        //verfuegbarLaengeRaum[i]
        //verfuegbarBreiteRaum[i]
    }
    
    /**
     * für Alex / Kunstwerkverwaltung: bisher platzierte KW. damit er ggf. bestimmte noch nicht platzierte Kunstwerke wählen kann, die wir versuchen als nächstes zu platzieren
     */
    public ArrayList <Kunstwerk> ermittlePlatzierteKunstwerke()
    {
        //Zu befüllen und returnieren:
        ArrayList <Kunstwerk> kunstwerkeSchonZugeordnet = new ArrayList <Kunstwerk >(); // Liste aller Kunstwerke, die schon einem Raum zugeordnet wurden. Arraylist mit flex. Länge
        
        // Zur Erinnerung: so sieht die Struktur vom nested Array aus:
        //private ArrayList <ArrayList <Kunstwerk >> denRaeumenZugeordneteKunstwerke = new ArrayList <ArrayList <Kunstwerk >>();
        
        // => aus dem nested Array müssen wir jetzt die Elemente in kunstwerkeSchonZugeordnet packen:
        
        for (ArrayList <Kunstwerk> kunstwerkeImRaum: denRaeumenZugeordneteKunstwerke) {
            //denRaeumenZugeordneteKunstwerke;
            for (Kunstwerk kunstwerkImRaum : kunstwerkeImRaum)
            {
                kunstwerkeSchonZugeordnet.add(kunstwerkImRaum);
            }
            
        }
        return kunstwerkeSchonZugeordnet;
    }
    
    /**
     * Summe der Kosten der bisher platzierten KW. 
     * 
     * TODO: Kann das ggf. die Kunstwerkeverwaltung unterstützen/umsetzen?
     */
    public int ermittlePlatzierteKunstwerkeKosten()
    {
        ermittlePlatzierteKunstwerke(); // liefert eine einfache Arraylist der schon platzierten KW
        // TO DO
        ////kunstwerk.getAttraktivitaet();
        int kostenSumme=0;
        return kostenSumme;
    }
    
    /**
     * Mittelwert (?) der Attraktvität % der bisher platzierten KW. 
     * 
     * TODO:
     * Kann das ggf. die Kunstwerkeverwaltung unterstützen/umsetzen?
     * Sind die Attraktivitäten ggf. pro Raum zu berechnen?
     */
    public int ermittlePlatzierteKunstwerkeAttraktivitaet()
    {
        ermittlePlatzierteKunstwerke(); // liefert eine einfache Arraylist der schon platzierten KW
        // TO DO
        int attraktivitaetSumme=0;
        return attraktivitaetSumme;
    }
    
    
    // ==========================================================================
    // === ALTE METHODEN ANSÄTZE / ÜBERLEGUNGEN 
    
    //(OBSOLET!!!) https://www.computerweekly.com/de/definition/veraltet-deprecated
    // ==========================================================================    
   
    
    /** Ueberprueft, ob mindestens ein Kunstwerk im Raum dem Schwerpunktthema enstpricht.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
        public boolean pruefeMin1Schwerpuntkthema()
    {
        //Code einfuegen
        //Zugriff auf public Methoden von Klasse Bild, Kunstgegenstand und Kunstinstallation durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausleihe durch Punktoperator 
        //"true" falls Schwerpunktthema pro Raum >= 1
        //"false" falls verschiedene Themen pro Raum < 1
        return true;
    }
    /** Ueberprueft, ob die Kunstwerke im Raum maximal drei verschiedene Themen vetreten.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
        public boolean pruefeMax3Themen()
    {
        //Code einfuegen
        //Zugriff auf public Methoden von Klasse Bild, Kunstgegenstand und Kunstinstallation durch Punktoperator
        //Zugriff auf public Methoden von Klasse Ausleihe durch Punktoperator 
        //"true" falls verschiedene Themen pro Raum <= 3
        //"false" falls verschiedene Themen pro Raum > 3
        return true;
    }
    
    
    /** Ermittelt alle Objekte der Klasse Raum, die mindestens ein Kunstwerk entsprechend dem Schwerpunktthema enthalten.
     *  @return     Objekte der Klasse Raum, das Kriterien in Schwerpunktthema erfüllt.
     */
        private Vector<Raum> getRaumThema1()
    {
        //Code einfuegen
        return raumVector;
    }
    
    /** Ermittelt alle Objekte der Klasse Raum, die kein Kunstwerk entsprechend dem Schwerpunktthema enthalten.
     *  @return     Objekte der Klasse Raum, das Kriterien in Schwerpunktthema nicht erfüllt.
     */
        private Vector<Raum> getRaumThema2()
    {
        //Code einfuegen
        return raumVector;
    }
    
    /** Ueberprueft, ob mindestens die Hälfte aller Raeume mindestens ein Kunstwerk mit dem Schwerpunktthema enthalten.
     *  @return     Wahrheitswert, ob Bedingung erfuellt ist.
     */
        public boolean pruefeVertretungThema()
    {
        //Code einfuegen
        //"true" falls size raumVector der Methode getRaumThema1 >= size raumVector der Methode getRaumThema2
        //"false" falls size raumVector der Methode getRaumThema1 < size raumVector der Methode getRaumThema2
        return true;
    }
    
        /**
     * Map,die Räume und ihre im Rahmen der Planung zugeordneten Kunstwerke enthält. Der Raum wird 
     * als Schlüssel genommen (da eindeutig und nicht mehrmals vorhanden) und die
     * zugeordneten Kunstwerke als Liste von Kunstwerken zu einem Raum als Wert gespeichert.
     * Ob bei der Programmierung letztendlich eine HashMap realisiert wird, muss sich bei der Programmierung in SL3 erweisen. 
     * Auf jeden Fall wird aber ein Konstrukt benötigt, welches die CopyOfZuordnung der Kunstwerke zu einem Raum speichert. 
     *
     *Die Klasse Zuordnung ist die zentrale Logikklasse. Hier wird eine Ausstellung/Ausleihe gesucht und optimiert. Ein Schwerpunktthema und eine Kostenobergrenze 
 * aus der Klasse Ausstellungsplanung werden dabei berücksichtigt.  
 */
    
    /**
     * Die Methode dient dazu, eine Minimallösung für unser Optimierungsproblem zu finden.
     * 
     * Sie versucht basierend auf den Angeboten der Partnermuseen und Räumen des Museums 
     * eine CopyOfZuordnung ausgewählter Kunstwerke vorzunehmen, sodass genau die Hälfte der Räume mit dem Schwerpunktthema besetzt sind.
     * Dabei erfolgt u.a. eine Prüfung ob die Kapazitäten der Räume ausreichend sind.
     * Der aktuelle Planungszustand wird im Attribut zugeordneteRaeumeKunstwerke gespeichert. 
     * 
     */
    public void zuordnenRaumMinimal()
    {
        /////////////////////////////////// Ideen und Übersicht
        /**                   

        raumverwaltung.pruefeVertretungThema(); // ob Schwerpunktthema in mindestens der Hälfte der Räume [Restriktion2]
        raumverwaltung.pruefeMin1Schwerpuntkthema(); // min. 1 Schwerpunktthema im Raum? [Restriktion2]
        ausstellungsplanung.get_schwerpunktthema();  // falls hier in der methode noch erforderlich [Restriktion2]
    
        this.setzePlanungZurueck(); ODER BRECHE PLANUNG AB (???) ---> ist nicht mehr nötig wenn wir mehere Zuordnungen haben? dann evtl. etwas wie "next"/"continue"

        
        */

    }
        
    /**
     * Die Methode dient dazu, die Minimallösung für unser Optimierungsproblem zu optimieren. Für jeden Raum wird u.a. geprüft, 
     * ob noch weitere Kunstwerke in diesen Raum aufgenommen werden können.
     *  
     */
    public void zuordnenRaumOptimieren()
    {
        /////////////////////////////////// Ideen und Übersicht
        //siehe den Fundus oben bei zuordnenRaumMinimal

    }
        
    /**
     * Hierüber können andere Klassen eine Referenz auf den aktuellen Planungszustand in Form der CopyOfZuordnung Räume-Kunstwerke bekommen.
     * Hierbei wird kein Abbild übergeben, sondern es handelt sich um pass-by-reference. Das heißt es ist davon auszugehen, dass andere 
     * Klassen über das get in der Lage sind, die Werte der HashMap zu ändern. Dies werden wir jedoch nicht vornehmen, es geht uns in den 
     * anderen Klassen nur um die Möglichkeit für das Lesen.
     * 
     * @return zugeordneteRaeumeKunstwerke   aktuelle Raum-Kunstwerk-Zuordnung
     */
    public ArrayList <ArrayList <Kunstwerk >>  get_zugeordneteRaeumeKunstwerke() 
    {
        return denRaeumenZugeordneteKunstwerke;
    }
      
}
