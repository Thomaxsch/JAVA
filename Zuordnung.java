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
   
    private Kunstwerk [] kunstwerkeArray;           // um die Referenzen auf die Kunstwerke vom Import aufzunehmen (alle Kunstwerke)
    private Raum [] raeumeArray;                    // um die Referenzen auf die Räume vom Import aufzunehmen (alle Räume)
    private Kunstwerkverwaltung kunstwerkverwaltung;//Damit wir später auf Methoden der Kunstwerk- bzw. Raumverwaltung zugreifen können
    private Raumverwaltung raumverwaltung;          //Damit wir später auf Methoden der Kunstwerk- bzw. Raumverwaltung zugreifen können
    
    private ArrayList <ArrayList <Kunstwerk >> denRaeumenZugeordneteKunstwerke = new ArrayList <ArrayList <Kunstwerk >>(); // Liste von (Kunstwerke im Raum) pro Raum. Nested array list.
    // Die äußere ArrayList wird später so viele Elemente haben wie es Räume gibt,
    // während die innere ArrayList die Anzahl der Kunstwerke in einem konkreten Raum angibt (Anzahl Elemente der inneren Arraylist: von keins ... bis theroetisch max Anzahl Kunstwerke)
    
    //// Auch die folgenden Arrays zu Raumdimensionen werden später so viele Elemente habe wie es Räume gibt (d.h. je ein Wert von z.B. Höhe pro Raum)
    //relevant für B:
    private int [] verfuegbarWandWest; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandOst;  // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandNord; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] verfuegbarWandSued; // Liste von (noch verfuegbarer Wandplatz) pro Raum
    private int [] minFeuchteRaum; // Liste von (aufgrund platzierter Bilder erlaubte Feuchte) pro Raum
    private int [] maxFeuchteRaum; // Liste von (aufgrund platzierter Bilder erlaubte Feuchte) pro Raum
    private int [] minTempRaum; // Liste von (aufgrund platzierter Bilder erlaubte Temperatur) pro Raum
    private int [] maxTempRaum; // Liste von (aufgrund platzierter Bilder erlaubte Temperatur) pro Raum

    //relevant für KG und KI:
    private int [] verfuegbarLaengeRaum; // Liste von (noch verfuegbarer Raumdistanz) pro Raum
    private int [] verfuegbarBreiteRaum; // Liste von (noch verfuegbarer Raumdistanz) pro Raum
    //relevant für alle KW:
    private int [] verfuegbarHoeheRaum; // Liste von (Höhe) pro Raum
    
    private String schwerpunktthema; //Vorgegebenes Schwerpunktthema der Ausstellung
    private double kostenobergrenze; //Vorgegebene Kostenobergrenze der Ausstellung
    
    private ArrayList <Kunstwerk> kunstwerkeSchonZugeordnet = new ArrayList <Kunstwerk >(); // Liste aller Kunstwerke, die schon einem Raum zugeordnet wurden. Arraylist mit flex. Länge
    private ArrayList <Raum> raeumeSchonBelegt = new ArrayList <Raum>(); // Initalisierung der Liste aller Räume, denen schon ein KW zugeordnet wurde. Arraylist mit flex. Länge
    private double restbudget; // noch verbliebenes Budget
    
    private int wieOftWurdeSchonEinSchwerpunktKunstwertPlatziert = 0;
    
    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
      
    /**
     * Konstruktor für Objekte der Klasse Ausleihe
     */
    public Zuordnung(Kunstwerkverwaltung in_kunstwerkverwaltung,Raumverwaltung in_raumverwaltung,String in_schwerpunktthema,double in_kostenobergrenze)
    {
        // Damit wir später auf Methoden der Kunstwerk- bzw. Raumverwaltung zugreifen können, speichern wir uns die Referenz auf die Verwaltungen ab:
        kunstwerkverwaltung = in_kunstwerkverwaltung;
        raumverwaltung = in_raumverwaltung;
        
        // Aus der Kunstwerkverwaltung den Vector zu Kunstwerken abrufen und in Array umwandeln
        kunstwerkeArray = new Kunstwerk [in_kunstwerkverwaltung.sizeAngebotsverwaltung()];
        int i = 0;
        for (Kunstwerk k: in_kunstwerkverwaltung.sortAttraktivitaet()) {
            kunstwerkeArray[i]=(Kunstwerk) k; // cast glaub ich unnötig?
            i++;
        }
        
        // Aus der Raumverwaltung den Vector der Räume abrufen und in Array umwandeln
        raeumeArray = new Raum [in_raumverwaltung.anzahlRaeume()];
        i = 0;
        for (Raum r: in_raumverwaltung.getRaumVector()) {
            raeumeArray[i]=(Raum) r; // cast glaub ich unnötig?
            i++;
        }
        
        // Schwerpunktthema und Kostenobergrenze zwischenspeichern
        schwerpunktthema=in_schwerpunktthema;
        kostenobergrenze=in_kostenobergrenze;
        
        // Initialisierungen von Arrays, welche ihrer Natur nach immer eine feste Länge haben. Array Länge ist die Anzahl der Räume!
        verfuegbarWandWest = new int [raeumeArray.length];
        verfuegbarWandOst = new int [raeumeArray.length];
        verfuegbarWandNord = new int [raeumeArray.length];
        verfuegbarWandSued = new int [raeumeArray.length];
        verfuegbarLaengeRaum = new int [raeumeArray.length];
        verfuegbarBreiteRaum = new int [raeumeArray.length];
        verfuegbarHoeheRaum = new int [raeumeArray.length];
        minFeuchteRaum = new int [raeumeArray.length];
        maxFeuchteRaum = new int [raeumeArray.length];
        minTempRaum = new int [raeumeArray.length];
        maxTempRaum = new int [raeumeArray.length];
        
        // Rufe die konkreten verfügbaren Distanzen via Methode der Klasse Raum ab:
        for (i=0;i<raeumeArray.length;i++)
        {
            verfuegbarWandWest[i]=raeumeArray[i].showWandWest();
            verfuegbarWandOst[i]=raeumeArray[i].showWandOst();
            verfuegbarWandNord[i]=raeumeArray[i].showWandNord();  
            verfuegbarWandSued[i]=raeumeArray[i].showWandSued();  
            verfuegbarLaengeRaum[i]=raeumeArray[i].showVerfuegbareLaenge();
            verfuegbarBreiteRaum[i]=raeumeArray[i].showVerfuegbareBreite();
            verfuegbarHoeheRaum[i]=raeumeArray[i].showVerfuegbareHoehe();
        }
        
        // Initialisiere die äußere Arraylist von denRaeumenZugeordneteKunstwerke
        for (i=0;i<raeumeArray.length;i++)
        {
            denRaeumenZugeordneteKunstwerke.add(new ArrayList<Kunstwerk>()); 
            // somit gibt es je Raum eine Arraylist der Kunstwerke, wobei aktuell bei allen Räumen noch keine Kunstwerke darin sind
        }
        
        //Restbudget mit dem vollen Betrag belegen
        restbudget=kostenobergrenze;
    }

    // ==========================================================================
    // === Methoden, die die Zuordnung bilden
    // ==========================================================================    
    
    /**
     * Kernfragen unserer Zuordnung sind, was der nächste (bzw. nächste beste) Raum ist, dem man welches nächste (bzw. nächste beste) Kunstwerk zuordnet.
     * 
     * Eine Minimalloesung bedeutet, dass genau ein Kunstwerk des Schwerpunktthemas in genau der Hälfte der Räume vertreten ist (also Restriktion 2 erfüllt ist)
     * 
     *   Hierbei müssen wir also erstmal nur die Restriktionen 5,6,7 (Höhen und Abstände) und 1 (globale Kostenobergrenze) berücksichtigen,
     * 
     *   während die Restriktionen 8 (KI alleine im Raum), 4 (mehrere Bilder im Raum ohne Temp/Feuchte Widerspruch), 3 (max 3 versch Themen im Raum)
     *   noch KEINE Bedeutung haben.
     * 
     * Wir setzen an, dass wir einem zufälligen noch leeren Raum ein möglichst gutes KW zuordnen, dann dem nächsten etc..
     * Die zufällige Raumauswahl bewirkt, dass wir Pfadabhängigkeiten aufgrund der Reihenfolge der KW-Platzierung vermeiden und möglichst diverse Konstellationen
     * als minimale Zuordnungen erhalten. Später versuchen wir dann die minimalen Loesungen noch zu verbessern.
     */
    public void versucheMinimalloesungZuFinden () throws Exception
    {
        for (int i=0;i<raeumeArray.length;i++) // d.h. potentiell für jeden Raum wenn die Schleife vorher nicht abgebrochen wird
        {
            // Unser Etappenziel ist erreicht, wenn die Hälfte der Räume mit Schwerpunktkunstwerk versehen wurde. Dann soll die Schleife abbrechen:
            if (wieOftWurdeSchonEinSchwerpunktKunstwertPlatziert == raumverwaltung.anzahlHaelfteRaeume())
            {
                break; // Anweisung im Schleifenkörper führt zum sofortigen Abbruch der Schleife
            }
            
            // Wir benötigen einen zufällig ausgewählten noch leeren Raum, um dort zu versuchen ein KW zu platzieren:
            Raum unserAktuellerRaum = raumverwaltung.zufealligerRaum(raeumeSchonBelegt);
            
            // Wir brauchen auch den Index dieses Raums, damit wir in unseren Listen später die richtigen Werte zum Raumindex finden können: 
            int unserAktuellerRaumIndex = 0;
            for (Raum r: raeumeArray)
            {
                if (r == unserAktuellerRaum)
                {
                    break; // breche die Schleife ab, weil unserAktuellerRaumIndex bereits den richtigen Wert hat
                }
                else
                {
                    unserAktuellerRaumIndex++; // erhoehe unserAktuellerRaumIndex um 1 und schaue ob der nächste Raum der mit dem gesuchten Index ist
                }
            }
            
            // Das Folgende gibt uns das als nächstes zu setzende Kunstwerk in die Hand,
            // das vom Schwerpunktthema ist, die Restriktionen 5,6,7 (im Raum) erfüllt, sodass auch 1 (global) erfüllt ist. 
            // Und bei all dem ist es das KW mit höchster Attraktivität.
            
            Kunstwerk zuSetzendesKW;
            try {
                zuSetzendesKW = kunstwerkverwaltung.naechstesZuSetzendesKunstwerk(
                    schwerpunktthema,
                    verfuegbarWandWest[i],verfuegbarWandOst[i],verfuegbarWandNord[i],verfuegbarWandSued[i], // relevant für Bilder (vier Wände)
                    verfuegbarLaengeRaum[i],verfuegbarBreiteRaum[i],                                        // relevant für G und I (laengs/quer bzw Raumfläche)
                    verfuegbarHoeheRaum[i],                                                                 // relevant für alle KW
                    restbudget,                                                                             // verfügbares Restbudget
                    kunstwerkeSchonZugeordnet                                                               // bisher platzierte Kunstwerke
                ); 
            }
            catch (Exception e){ 
                // Mit exception e werden alle, nicht nur spezielle Fehler abgefangen.
                // Ein Fehlerfall könnte sein, wenn es schlichtweg kein passendes Kunstwerk gibt, weil z.B.
                // die Kostengrenze überschritten wurde oder die Maße einfach zu allen Räumen inkompatibel sind
                // (?? (oder wird dann quasi leeres KW gegeben, ich meine nicht sondern es kommt zum Fehler)
                
                continue; // Anweisung beendet die aktuelle Ausführung des Schleifenkörpers, aber die Schleife wird mit dem nächsten Durchlauf (d.h. nächster Raum) fortgesetzt 
            }

            // Jetzt validieren wir einer Methode der Klasse "Zuordnung", ob das Kunstwerk wirklich passt, oder ob sich in der Implementierung ein Fehler eingeschlichen hat:
            if (!passtKunstwerkDimensionalInRaum(zuSetzendesKW,unserAktuellerRaumIndex))
            {throw new Exception ("es konnte nicht validiert werden, dass das KW in den Raum passt. Widerspruch");}
            
            // Wenn wir bis hierhin ohne break/continue/throw gekommen sind, können wir das ausgewählte Kunstwerk im Raum platzieren:
            denRaeumenZugeordneteKunstwerke.get(unserAktuellerRaumIndex).add(zuSetzendesKW);
            aktualisiereParameterNachSetzen(zuSetzendesKW,unserAktuellerRaumIndex); //Nach jedem Setzen sind einige Parameter zu aktualisieren:
            wieOftWurdeSchonEinSchwerpunktKunstwertPlatziert++;
                
            /**
            später auch mit anderer Methode  versuchen:
            - naechstesZuSetzendesKunstwerkMODUS2 (statt beste Attraktivität die beste Relation aus Attraktivität und Kosten(*Volumen); sowie nicht mehr als 1/3 ges.-Kostenobergrenze pro Kunstwerk?)
            - naechstesZuSetzendesKunstwerkMODUS3 (stattdessen rein zufällige Zuordnung)
            - naechstesZuSetzendesKunstwerkMODUS4 (kostenpfad berücksichtigen: z.B. 40% der Hälfte der Räume schon gesetzt, liegen proportional aber bei 60% Kostenausschöpfung...)
            */
        }
    }
    
    /**
     * Diese Methode muss man nach dem Setzen aufrufen, denn sie aktualisiert folgende Parameter, die sich durch das Platzieren des Kunstwerks im Raum ändern:
     *      - 6x räumliche Dimensionen (4 wände, 2 Raumachsen)
     *      - restbudget
     *      - kunstwerkeSchonZugeordnet
     *      - raeumeSchonBelegt
     *      - Temp Min/Max aufgrund Bildern im Raum
     *      - Feuchte Min/Max aufgrund Bildern im Raum
     *      
     * @param in_r Raumindex
     */
    
    public void aktualisiereParameterNachSetzen(Kunstwerk kw, int r)
    {
        // -------------------
        // Aktualisiere räumliche Dimensionen
        // -------------------

        // Bei Bildern: 
        // Wir prüfen aktuell, welche Wand die beste Wand für die Platzierung ist, indem wir diejenige nehmen, wo der Rest nach Platzierung am kleinsten ist.
        // Die Überlegung ist, damit manchmal perfekte Passungen zu erreichen bzw.  generell möglichst viel Freiraum "am Stück" zu lassen.
        //
        // Bleibt nach der Platzierung an der besten Wand an dieser dann noch Platz über, der größer als ein Meter wäre,
        // muss noch ein Meter extra abgezogen werden, da dies der Puffer zum nächsten Bild wäre
        // und entsprechenden den effektiv verfügbaren Platz für das nächste Bild reduziert:
        
        if (kw.getArt()=='B')
        {
            ArrayList<Integer> verfuegbarkeitenWaende = new ArrayList<Integer>();
            verfuegbarkeitenWaende.add(verfuegbarWandWest[r]-kw.getBreite());
            verfuegbarkeitenWaende.add(verfuegbarWandOst[r]-kw.getBreite());
            verfuegbarkeitenWaende.add(verfuegbarWandNord[r]-kw.getBreite());
            verfuegbarkeitenWaende.add(verfuegbarWandSued[r]-kw.getBreite());
            int indexVerfWand = verfuegbarkeitenWaende.indexOf(Collections.min(verfuegbarkeitenWaende)); // Index der Wand mit meistem Platz
            if (indexVerfWand==0) // West
            {
                if (verfuegbarWandWest[r]-(kw.getBreite())>=1)
                {verfuegbarWandWest[r]-=(kw.getBreite()+1);}
                else
                {verfuegbarWandWest[r]-=kw.getBreite();}
            }
            else if (indexVerfWand==1) // ost
            {
                if (verfuegbarWandOst[r]-(kw.getBreite())>=1)
                {verfuegbarWandOst[r]-=(kw.getBreite()+1);}
                else
                {verfuegbarWandOst[r]-=kw.getBreite();}
            }
            else if (indexVerfWand==2) // Nord
            {
                if (verfuegbarWandNord[r]-(kw.getBreite())>=1)
                {verfuegbarWandNord[r]-=(kw.getBreite()+1);}
                else
                {verfuegbarWandNord[r]-=kw.getBreite();}
            }
            else if (indexVerfWand==3) // sued
            {
                if (verfuegbarWandSued[r]-(kw.getBreite())>=1)
                {verfuegbarWandSued[r]-=(kw.getBreite()+1);}
                else
                {verfuegbarWandSued[r]-=kw.getBreite();}
            }
        }
        
        // Bei G oder I:  
        // Bei Kunstgegenständen müssen noch zwei Meter als Puffer extra abgezogen werden, wenn noch ein Rest übrig bliebe. Bei I ist es im Grunde egal,
        // weil sowieso sonst nichts mehr in den Raum passt, aber es schadet nicht, die selbe Logik durchzuhalten.
        // Bei Kunstgegenständen ist die Frage, ob die Platzierung "laengs" oder "quer" die beste ist, sofern beide Optionen möglich sind. Wir nehmen 
        // in solchen Fällen die Platzierungsoption,bei der am meisten FLÄCHE ungenutzt leibt.
        
        if (kw.getArt()=='G'| kw.getArt()=='I')
        {
            Kunstgegenstand in_Kunstgegenstand = (Kunstgegenstand) kw; // die Parentclass "Kunstwerk" hat nicht die Methode getLaenge, die wir aber gleich benötigen, daher casten wir
            int l=in_Kunstgegenstand.getLaenge();
            int b=in_Kunstgegenstand.getBreite();
            
            int restl1=verfuegbarLaengeRaum[r]-l; // Fall laengs
            int restl2=verfuegbarBreiteRaum[r]-b; // Fall laengs
            int restq1=verfuegbarLaengeRaum[r]-b; // Fall quer
            int restq2=verfuegbarBreiteRaum[r]-l; // Fall quer
            
            // Wenn sowohl laengs als auch quere Positionierung ist möglich:
            if (restl1>=0 &&restl2>=0 && restq1>=0 && restq2>=0)
            {
                if (restl1*restl2>=restq1*restq2) //Platzierung laengs wird vorgenommen
                {
                    if (restl1>=2 && restl2>=2) // nur wenn die Reste beide größer gleich 2 sind, können wir zwei Meter Puffer abziehen
                    {
                        verfuegbarLaengeRaum[r]-=(l+2); 
                        verfuegbarBreiteRaum[r]-=(b+2);
                    }
                    else // sonst macht es keinen Sinn 2 Meter Puffer abzuziehen
                    {
                        verfuegbarLaengeRaum[r]-=(l);
                        verfuegbarBreiteRaum[r]-=(b);
                    }
                }
                else if (restl1*restl2<restq1*restq2) // Platzierung quer wird vorgenommen
                {
                    if (restq1>=2 && restq2>=2) // nur wenn die Reste beide größer gleich 2 sind, können wir zwei Meter Puffer abziehen
                    {
                        verfuegbarLaengeRaum[r]-=(b+2); 
                        verfuegbarBreiteRaum[r]-=(l+2);
                    }
                    else // sonst macht es keinen Sinn 2 Meter Puffer abzuziehen
                    {
                        verfuegbarLaengeRaum[r]-=(b);
                        verfuegbarBreiteRaum[r]-=(l);
                    }
                }
            }
            
            // Wenn nur laengs Positionierung möglich:
            if (restl1>=0 && restl2>=0 && (restq1<0 | restq2<0))
            {
                //Platzierung laengs wird vorgenommen
                    if (restl1>=2 && restl2>=2) // nur wenn die Rest beide größer gleich 2 sind, können wir zwei Meter Puffer abziehen
                    {
                        verfuegbarLaengeRaum[r]-=(l+2); 
                        verfuegbarBreiteRaum[r]-=(b+2);
                    }
                    else // sonst macht es keinen Sinn 2 Meter Puffer abzuziehen
                    {
                        verfuegbarLaengeRaum[r]-=(l);
                        verfuegbarBreiteRaum[r]-=(b);
                    }

            }
            
            // Wenn nur quere Positionierung möglich:
            if ((restl1<0 | restl2<0) && restq1>=0 && restq2>=0)
            {
                // Platzierung quer wird vorgenommen
                if (restq1>=2 && restq2>=2) // nur wenn die Reste beide größer gleich 2 sind, können wir zwei Meter Puffer abziehen
                    {
                        verfuegbarLaengeRaum[r]-=(b+2); 
                        verfuegbarBreiteRaum[r]-=(l+2);
                    }
                    else // sonst macht es keinen Sinn 2 Meter Puffer abzuziehen
                    {
                        verfuegbarLaengeRaum[r]-=(b);
                        verfuegbarBreiteRaum[r]-=(l);
                    }
            }
        }
        
        //////////////////////////////////////////////////////////////////////////
        // Aktualisiere Budget
        
        restbudget = restbudget - kw.getKosten(); // noch verbliebenes Budget
    
        //////////////////////////////////////////////////////////////////////////
        // Aktualisiere Arraylist der belegten Räume
        
        if (denRaeumenZugeordneteKunstwerke.get(r).isEmpty())
        {   
            raeumeSchonBelegt.add(raeumeArray[r]);// dies muss nur einmal geschehen wenn erstmals ein KW in den Raum gesetzt wird.
        }
        
        /////////////////////////////////////////////////////////////////////////
        // Aktualisiere Arraylist der platzierten Kunstwerke
        
        kunstwerkeSchonZugeordnet.add(kw);

        /////////////////////////////////////////////////////////////////////////
        // Aktualisiere die Arrays der im Raum erlaubten Temperaturen & Feuchten (für Bilder relevant)
        
        if (kw.getArt()=='B')
        {
            Bild b = (Bild) kw; // die Parentclass "Kunstwerk" hat nicht die Methoden/Eigenschaften für Temp & Feuchte, die wir aber gleich benötigen, daher casten wir
            
            if ((minFeuchteRaum[r]==0) | (minFeuchteRaum[r]<b.getMinLuft())) // wenn noch kein Eintrag da, oder wenn die bisherige min Grenze zu niedrig ist für das neue Bild
            {
                minFeuchteRaum[r]=b.getMinLuft();
            }
            if ((minTempRaum[r]==0) | (minTempRaum[r]<b.getMinTemp())) // wenn noch kein Eintrag da, oder wenn die bisherige min Grenze zu niedrig ist für das neue Bild
            {
                minTempRaum[r]=b.getMinTemp();
            }
            if ((maxFeuchteRaum[r]==0) | (maxFeuchteRaum[r]>b.getMaxLuft())) // wenn noch kein Eintrag da, oder wenn die bisherige max Grenze zu hoch ist für das neue Bild
            {
                maxFeuchteRaum[r]=b.getMaxLuft();
            }
            if ((maxTempRaum[r]==0) | (maxTempRaum[r]>b.getMaxTemp())) // wenn noch kein Eintrag da, oder wenn die bisherige max Grenze zu hoch ist für das neue Bild
            {
                maxTempRaum[r]=b.getMaxTemp();
            }
        }
    }
    
    // ==========================================================================
    // === Raumbezogene Prüfmethoden
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
            boolean blockiertDurchKunstinstallation = (denRaeumenZugeordneteKunstwerke.get(0).get(0).getArt()=='I'); // get 0 get 0 bzgl verschachtelter array list 2 dim
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
        private void getRaumThema1()
    {
        //Code einfuegen
        //return Vector<Raum> v; //raumVector;
    }
    
    /** Ermittelt alle Objekte der Klasse Raum, die kein Kunstwerk entsprechend dem Schwerpunktthema enthalten.
     *  @return     Objekte der Klasse Raum, das Kriterien in Schwerpunktthema nicht erfüllt.
     */
        private void getRaumThema2()
    {
        //Code einfuegen
        //return Vector<Raum> v;// raumVector;
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
    
    /**
     * Bisher platzierte Kunstwerke. Erlaubt der Kunstwerkverwaltung bestimmte noch nicht platzierte Kunstwerke auszuwählen, die wir versuchen als nächstes zu platzieren.
     */
    public ArrayList <Kunstwerk> ermittlePlatzierteKunstwerke()
    {
        //Zu befüllen und returnieren:
        ArrayList <Kunstwerk> kunstwerkeSchonZugeordnet = new ArrayList <Kunstwerk >(); // Liste aller Kunstwerke, die schon einem Raum zugeordnet wurden. Arraylist mit flex. Länge
        
        // Zur Erinnerung: so sieht die Struktur vom nested Array aus:
        //private ArrayList <ArrayList <Kunstwerk >> denRaeumenZugeordneteKunstwerke = new ArrayList <ArrayList <Kunstwerk >>();
        
        // => aus dem nested Array müssen wir jetzt die Elemente in kunstwerkeSchonZugeordnet packen:
        
        for (ArrayList <Kunstwerk> kunstwerkeImRaum: denRaeumenZugeordneteKunstwerke) {
            for (Kunstwerk kunstwerkImRaum : kunstwerkeImRaum)
            {
                kunstwerkeSchonZugeordnet.add(kunstwerkImRaum);
            }
        }
        return kunstwerkeSchonZugeordnet;
    }
    
    /**
     * Welche Räume sind schon belegt? Erlaubt u.a. der Raumverwaltung zufällig noch leere Räume auszuwählen.
     */
    public ArrayList <Raum> ermittleBelegteRaeume()
    {
        //Zu befüllen und returnieren:
        ArrayList <Raum> raeumeSchonBelegt = new ArrayList <Raum>(); // Initalisierung der Liste aller Räume, denen schon ein KW zugeordnet wurde. Arraylist mit flex. Länge
        
        // Zur Erinnerung: so sieht die Struktur vom nested Array aus:
        //private ArrayList <ArrayList <Kunstwerk >> denRaeumenZugeordneteKunstwerke = new ArrayList <ArrayList <Kunstwerk >>();
        
        // => wir müssen schauen, bei welchem Index des äußeren Array das innere Array leer ist, dann in diesen Fällen den Raum zum äußeren Index zu raeumeSchonBelegt hinzufügen:
        int i=0;
        for (ArrayList <Kunstwerk> kunstwerkeImRaum: denRaeumenZugeordneteKunstwerke) {
            if (kunstwerkeImRaum.isEmpty())
            {
                raeumeSchonBelegt.add(raeumeArray[i]);
            }
            i++;
        }
        return raeumeSchonBelegt;
    }
    
    /**
     * Wie viele Räume sind schon belegt?
     */
    public int ermittleBelegteRaeumeAnzahl()
    {
        return ermittleBelegteRaeume().size();
    }
    
    /**
     * Summe der Kosten der bisher platzierten Kunstwerke. 
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
     * Restbudget d.h. Kostenobergrenze minus aktuell angelaufene Kosten
     */
    public int ermittlePlatzierteKunstwerkeRestbudget()
    {
        return 5; // kostenobergrenze; // - ermittlePlatzierteKunstwerkeKosten();
    }
    
    
    
    /**
     * Erweiterung der Minimalloesung
     * 
     * Ideen:
- A) versuchen, in Räumen ohne Kunstinstallation noch weitere unzugeordnete Bilder und/oder KG beliebigen Themas unterzubringen (muss alle Restriktion außer Nr. 2 beachten)
- B) SPÄTER  Prüfe, ob es möglich und vorteilhaft ist, einen mit X Bildern und Y Kunstgegenständen gefüllten Raum durch eine (bisher nicht zugeordnete) KI zu ersetzen
- C) SPÄTER Kunstwerk durch ein nicht zugeteiltes eindeutig besseres Kunstwerk ersetzen

     *  Bei Idee A) ist Restriktion 2 (Schwerpunktthema in mind. der Hälfte der Räume) erfüllt - sofern es eine Minimalloesung gab.
     *  Jetzt sind also nur noch zu berücksichtigen:
     *  
     *  Restriktionen 5,6,7 (Höhen und Abstände),8 (KI alleine im Raum)  => methode passtKunstwerkDimensionalInRaum (wie auch bei Minimalloesung). 
     *  1 (globale Kostenobergrenze) => 
     *  4 (mehrere Bilder im Raum ohne Temp/Feuchte Widerspruch) => methode
     *  3 (max 3 versch Themen im Raum) =>
     *  
     *  => brauchen eine naechstesZuSetzendesKunstwerkERWEITERUNG ():
     *          ---> diese muss NICHT mehr auf das Schwerpunktthema achten (2)
     *          ---> Budget R1 spielt weiterhin die selbe Rolle => wieder mit Restbudget
     *          ---> Raumdimensionen  R5-7 => wie bisher
     *          ---> Temp und Feuchte SPIELEN JETZT EINE ROLLE R4 => Array wird übergeben welcheBildTempFeuchteImRaum
     *          ---> hinsichtlich R3 sind ggf. nur manche Themen für den Raum noch erlaubt => Arraylist wird übergeben welcheThemenDuerfenNochInRaum
     *          ---> R8 ist vor Methodenaufruf zu prüfen, also ob eine KI für weitere KW alles blockiert
     *                  => mache unten also aus blockiertDurchKunstinstallation in passtKunstwerkDimensionalInRaum (s. unten) ggf. eine eigene Methode
     */
    public void versucheLoesungserweiterung()
    {
        //
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
    
}