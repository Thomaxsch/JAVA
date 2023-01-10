import java.util.*;
/**
 * Die Klasse Ausleihe ist die zentrale Logikklasse. Hier wird eine Ausstellung/Ausleihe gesucht und optimiert. Ein Schwerpunktthema und eine Kostenobergrenze 
 * aus der Klasse Ausstellungsplanung werden dabei berücksichtigt.  
 * 
 * @author Thomas Scheidt 
 * @version 19.12.2022
 */
public class Zuordnung
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
    
    /////attraktivitaetSumme=0;
    /////kostenSumme=
    
    /**
     * Map,die Räume und ihre im Rahmen der Planung zugeordneten Kunstwerke enthält. Der Raum wird 
     * als Schlüssel genommen (da eindeutig und nicht mehrmals vorhanden) und die
     * zugeordneten Kunstwerke als Liste von Kunstwerken zu einem Raum als Wert gespeichert.
     * Ob bei der Programmierung letztendlich eine HashMap realisiert wird, muss sich bei der Programmierung in SL3 erweisen. 
     * Auf jeden Fall wird aber ein Konstrukt benötigt, welches die CopyOfZuordnung der Kunstwerke zu einem Raum speichert. 
     */
   
    private Kunstwerk [] kunstwerkeArray; // um die Referenzen auf die Kunstwerke vom Import aufzunehmen (alle Kunstwerke)
    private Raum [] raeumeArray; // um die Referenzen auf die Räume vom Import aufzunehmen (alle Räume)
    
    private ArrayList <ArrayList <Kunstwerk >> denRaeumenZugeordneteKunstwerke = new ArrayList <ArrayList <Kunstwerk >>(); // Liste von (Kunstwerke im Raum) pro Raum. Nested array list.
        /**demRaumZugeordneteKunstwerke.add(Kunstwerk); 
    demRaumZugeordneteKunstwerke.get(0);
    */
    
    
    //relevant für B:
    private int [] verfuegbarWandWest; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandOst;  // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandNord; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandSued; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    //relevant für KG und KI:
    private int [] verfuegbarLaengeRaum; // Liste von (noch verfuegbarer Raumdistanz) pro Raum
    private int [] verfuegbarBreiteRaum; // Liste von (noch verfuegbarer Raumdistanz) pro Raum
    
    private int [] verfuegbarHoeheRaum; // Liste von (noch verfuegbarer Raumdistanz) pro Raum
    
    private ArrayList <Kunstwerk > kunstwerkeSchonZugeordnet = new ArrayList <Kunstwerk >() ; // Liste aller Kunstwerke, die schon einem Raum zugeordnet wurden ^<--- hier dann als Array!!!
    

    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
      
    /**
     * Konstruktor für Objekte der Klasse Ausleihe
     */
    public Zuordnung(Vector<Kunstwerk> in_kunstwerke,Vector<Raum> in_raeume)
    {
        // Den eingegebenen Vector in Array umwandeln
        kunstwerkeArray = new Kunstwerk [in_kunstwerke.size()];
        int i = 0;
        for (Kunstwerk k: in_kunstwerke) {
            kunstwerkeArray[i]=(Kunstwerk) k; // cast
            i++;
        }
        
        // Den dreingegebenen Vector in Array umwandeln
        raeumeArray = new Raum [in_raeume.size()];
        i = 0;
        for (Raum r: in_raeume) {
            raeumeArray[i]=(Raum) r; // cast
            i++;
        }
        
        // Initialisierungen von Arrays, welche ihrer Natur nach immer eine feste Länge haben. Array Länge ist die Anzahl der Räume:
        verfuegbarWandWest = new int [raeumeArray.length];
        verfuegbarWandOst = new int [raeumeArray.length];
        verfuegbarWandNord = new int [raeumeArray.length];
        verfuegbarWandSued = new int [raeumeArray.length];
        verfuegbarLaengeRaum = new int [raeumeArray.length];
        verfuegbarBreiteRaum = new int [raeumeArray.length];
        verfuegbarHoeheRaum = new int [raeumeArray.length];
        
        // Rufe die konkreten verfügbaren Distanzen ab:
        
        for (i=0;i<raeumeArray.length;i++)
        {
            verfuegbarWandWest[i]=raeumeArray[i].getWandWest();  // TO DO: netto-Wert abrufen
            verfuegbarWandOst[i]=raeumeArray[i].getWandOst();  // TO DO: netto-Wert abrufen
            verfuegbarWandNord[i]=raeumeArray[i].getWandNord();  // TO DO: netto-Wert abrufen
            verfuegbarWandSued[i]=raeumeArray[i].getWandSued();  // TO DO: netto-Wert abrufen
            verfuegbarLaengeRaum[i]=raeumeArray[i].getWandNord();// TO DO: netto-Wert abrufen
            verfuegbarBreiteRaum[i]=raeumeArray[i].getWandOst();// TO DO: netto-Wert abrufen
            verfuegbarHoeheRaum[i]=raeumeArray[i].getHoeheRaum();
            
        }
    }

    // ==========================================================================
    // === Methoden
    // ==========================================================================    
    
    /**
     * 
     */
    public boolean passtKunstwerkNochInRaum(Kunstwerk in_Kunstwerk, int i) // Raum i
    {
        boolean passtHoehe = (in_Kunstwerk.getHoehe()<verfuegbarHoeheRaum[i]);
        if (passtHoehe==false)
        {
            return false;
        }
        
        if (in_Kunstwerk.getArt()=='B'|| in_Kunstwerk.getArt()=='G')
        {
            boolean blockiertDurchKunstinstallation = (denRaeumenZugeordneteKunstwerke.get(0).get(0).getArt()=='I');
            if (blockiertDurchKunstinstallation == true)
            {
                return false;
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
            
            boolean nochKeinAnderesKunstwerkImRaum = denRaeumenZugeordneteKunstwerke.get(i).isEmpty();
            
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
        // Bausteine für die Umsetzung:
        
        /**
        raumverwaltung.getKunstwerkVector(); // Vector der angebotenenKunstwerke
        angebotsverwaltung.getRaumVector(); // Vector der Räume
        
        raumverwaltung.anzahl(); // Anzahl Räume
        angebotsverwaltung.sizeAngebotsverwaltung(); // Anzahl angebotene Kunstwerke
        
        kunstwerk.setVerplant();   ====> TO DO? // habe ich eingepflegt - Alex
        kunstwerk.getVerplant();   ====> TO DO? // habe ich eingepflegt - Alex

        kunstwerk.getAttraktivitaet(); // Attraktivität Kunstwerk IN PROZENT [Ziel]
        this.mittelwertAttr(); // hier umzusetzen als private Methode ODER in der Angebotsverwaltung <=== TO DO in Teil 3 der SL
                               
        kunstwerk.getKosten(); // Kosten des Kunstwerks [Restriktion 1]
        ausstellungsplanung.get_kostenobergrenze(); // [Restriktion 1]
        this.kostenCounter // wschl. kann im Sinne eines Counters Kosten hier in privater Variable mitgezählt/kumuliert werden [Restriktion1]
        kunstwerk.setPlaziert();
        kunstwerk.getPlaziert();
        

        raumverwaltung.pruefeVertretungThema(); // ob Schwerpunktthema in mindestens der Hälfte der Räume [Restriktion2]
        raumverwaltung.pruefeMin1Schwerpuntkthema(); // min. 1 Schwerpunktthema im Raum? [Restriktion2]
        ausstellungsplanung.get_schwerpunktthema();  // falls hier in der methode noch erforderlich [Restriktion2]
        
        raumverwaltung.pruefeMax3Themen(); // sind max. 3 unterschiedliche Themen im Raum? [Restriktion3]
        raumverwaltung.pruefeWeiteresThema(); // darf noch 1 weiteres Thema in Raum? [Restriktion3]
        
        this.checkTempFeu // Temp und Feuchte ohne Widerspruch. hier private umsetzen // perspektivisch ggf. im Raum [Restriktion 4]
        bild.setMinTemp();
        bild.setMaxTemp();
        bild.setMinLuft();
        bild.setMaxLuft();
        bild.getMinTemp();
        bild.getMaxTemp();
        bild.getMinLuft();
        bild.getMaxLuft();
        
        raumverwaltung.getHoeheRaum(); //  [Restriktion 5]
        this.pruefeRaumHoehe; //noch in private Methode umzusetzen
        
        raumverwaltung.setWandNord(); // Restplatz für Bild, ist nach jeder CopyOfZuordnung zu aktualisieren [Restriktion 6]
        raumverwaltung.setWandOst();
        raumverwaltung.setWandSued();
        raumverwaltung.setWandWest();
        raumverwaltung.getWandNord();
        raumverwaltung.getWandOst();
        raumverwaltung.getWandSued();
        raumverwaltung.getWandWest();    
        
        raumverwaltung.setVerfuegbareLaenge(); // Restplatz für Kunstgegenstand/KI, ist nach jeder CopyOfZuordnung zu aktualisieren [Restriktion 7]
        raumverwaltung.setVerfuegbareBreite();
        raumverwaltung.getVerfuegbareLaenge();
        raumverwaltung.getVerfuegbareBreite();
        
        kunstwerk.thema(); // KI alleine im Raum, dh. sonst keine Bilder oder KI --> bei Zuteilung beachten[Restriktion 8]
        
        kunstwerk.getArt(); // Typ von Kunstwerk B / G / I
        
        this.setzePlanungZurueck();
        this.ordneZu();
        kunstwerk.setPlaziert();
        kunstwerk.getPlaziert();
        
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
