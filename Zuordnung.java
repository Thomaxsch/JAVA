import java.util.*;
import java.util.ArrayList;

/**
 * Die vorliegende Klasse ist eine Datenklasse für die einzelne Zuordnung von Raum und Kunstwerk(en). Sie enthält den Planungszustand. 
 * 
 * Sie enthält in diesem Zusammenhang nicht nur das Mapping Kunstwerke-Räume, sondern eine ganze Reihe von Datenstrukturen in Form listenartiger Objekte (verschachtelter) ArrayList und Array. 
 * Hierbei kommt als wesentlicher Unterschied zum Tragen, dass ArrayLists eine flexible Länge haben (z.B. beliebig viele Kunstwerke eines Raumes). Dafür können sie nicht direkt 
 * Primitives aufnehmen (z.B. integer-Wert der erlaubten Feuchte im Raum). So kommt es, dass wir manchmal Arrays und manchmal ArrayListen verwenden. Die listenartigen Objekte sind weiter
 * unten erklärt, wo sie deklariert werden ("Attribute" der Klasse) und sind konsequent sprechend benannt.
 * 
 * Zusätzlich sind in der Klasse mehrere Aspekte der Geschäftslogik im Detail implementiert. Die Klasse wird hierbei auch unterstützt durch die Klassen
 * Kunstwerkverwaltung und Raumverwaltung (insbesonders Auswahl eines zufälligen leeren Raumes sowie Suche des bestes Kunstwerkes in gegebener Situation).
 * <pre>
 * Die Klasse besteht aus folgenden Methoden, die wir hier als kurze Übersicht aufführen ("+" für public und "-" für private Methoden):
 * 
 *  A) Methoden, die die Zuordnung bilden
 *          + versucheMinimalloesungZuFinden bildet die Suche nach einer Minimallösung (siehe unten) ab
 *          + versucheLoesungserweiterung bildet die Suche nach einer Erweiterungslösung (siehe unten) ab
 *          - aktualisiereParameterNachSetzen eines Kunstwerkes in einem Raum ist nach jeder Platzierung zu setzen, um diverse Parameter zu aktualisieren (z.B. verfügbares Restbudget)
 *          
 *  B) Validierungsmethoden prüfen zur Sicherheit ab, ob die Suchergebnisse von der Kunstwerkverwaltung plausibel sind
 *          - passtKunstwerkHoehe
 *          - passtIblockiertNicht (ob es stimmt, dass kein Kunstinstallation den Raum blockiert
 *          - passtKunstwerkAnWandOderInFläche (ob es stimmt, dass das Kunstwerk an die Wand bzw. in die Fläche passt)
 *          - passtIWirdNichtBlockert durch bereits ein G oder I im Raum
 *          - passtKWValidierung (enthält die vier Validierungen in einer Methode und zählt Validierungsprobleme mit)
 *          
 *  C) Methoden für die Steuerung (insbesondere auch durch die Ausstellungsplanung)
 *          + wurdeMinimalloesungErreicht zur Frage ob eine Minimallösung vorliegt (Schwerpunkt in mindestens der Hälfte der Räume
 *          + getDenRaeumenZugeordneteKunstwerke gibt der Klasse Ausgabedatei das Mapping Kunstwerke-Räume in die Hand
 *          + getRangesFeuchtenTemperaturen gibt analog die aktuell erlaubten Werte für die minimale/maximale Feuchte und Temperatur in den Räumen
 *          + getZuordnungsGuete gibt die kombinierte Zuordnungsgüte der gesamten Zuordnung
 *          + getZuordnungsErgebnisse bündelt einige Ergebniseigenschaften der Zuordnung für Ausstellungsplanung und Zuordnungsverwaltung (zwecks Konsolenanzeige)
 *          + ausgebenZuordnungAufKonsole erstellt auf der Konsole einen Überblick über die Räume und darin platzierten Kunstwerke (d.h. das Mapping)
 *          + wieVieleKunstwerkePlatziert beantwortet die Frage wie viele Kunstwerke beliebigen Typs und Themas schon platziert wurden
 *          + getAnzahlValidierungsprobleme gibt zurück, wie oft die Validierungsmethoden das Ergebnis der KW-Verwaltung zur Suche eines passenden KW nicht bestätigt haben
 *                                        
 *  D) Methoden zur Bestimmung der Güte der Raumbelegung sowie der Bedürftigkeit eines Raumes im Rahmen der Lösungserweiterung
 *          - gueteRaumBelegung ermittelt für einen Raum den Anteil der aktuellen Wandbelegung mit Bildern
 *          - gueteRaumAttraktivitaet ermittelt für einen Raum den aktuellen Durchschnitt der Attraktivität der Kunstwerke.
 *          - gueteRaumKombiniert mithilfe der beiden vorherigen Methoden den kombinierten Gütewert aus Attraktivitätsmittelwert & Anteil Wandbelegung mit Bilden
 *          - maxBedarfIndex ermittelt den Raumindex mit dem größten Optimierungsbedarf (sind es mehrere Räume dann ein zufälliger davon), worauf sich die Lösungserweiterung stützt.
 *  
 * Die Planungssoftware soll ein Optimierungsproblem lösen können. Unser Ansatz ist, dass nach dem Zufallsprinzip verschiedene Zuordnungen 
 * von Kunstwerken zu Räumen versucht werden. Die Klasse Ausstellungsplanung kann dann diese Zuordnungen vergleichen und die beste Zuordnung auswählen,
 * für die dann entsprechende Ausgabedateien erzeugt werden.
 * 
 * Eine Kernfrage unserer Zuordnung ist, was der nächste Raum ist, in den man welches Kunstwerk als nächstes zuordnet.
 * 
 * Die Grundstruktur unserer Lösungsfindung stellt sich wie folgt dar:
 *  
 *       1. Suche Minimallösung (Hälfte der Räume mit Schwerpunktkunstwerk)
 *               ○ Zufälliger noch leerer Raum
 *               ○ Versuche das bestmögliche noch verfügbare Schwerpunkt-Kunstwerk zu platzieren
 *               ○ Breche ab, wenn eine Minimallösung gefunden wurde, oder aber spätestens wenn alle Räume einmalig durchlaufen wurden
 *       2. Lösungserweiterung
 *               ○ Raum mit aktuell größtem Verbesserungsbedarf suchen (wenn mehrere Räume Gleichstand haben, nehme davon einen zufälligen)
 *               ○ Versuche das bestmögliche noch verfügbare Kunstwerk zu platzieren und wiederhole den vorherigen Schritt der Raumsuche
 *               ○ Passt kein Kunstwerk mehr in den Raum oder ist der Raum perfekt gefüllt, durchlaufe diesen Raum nicht mehr
 *               
 * Sobald und sofern eine minimale Lösung erreicht wurde, gehen wir zur Lösungserweiterung über. 
 * Wenn kein Schwerpunktthema vorgegeben wird (Wert ""), dann wird direkt mit der Lösungserweiterung begonnen.
 * 
 * Die Qualitätsgewichtung fließt bei unserer Lösungsfindung auf zweierlei Weisen ein:
 *    - Bei der Suche nach dem jeweils noch besten möglichen Kunstwerk für einen Raum. Hierbei bestimmt das Gewicht den Einfluss folgender Gütekriterien:
 *        ○ Attraktivität des Kunstwerkes
 *        ○ Attraktivität pro Kosten des Kunstwerkes
 *    - Bei der Lösungserweiterung (s.o. unter 2.) wird immer wieder der Raum mit dem größten Verbesserungsbedarf gesucht. Hierbei gewichten wir folgende Bedürftigkeitskriterien:
 *        ○ Geringer Mittelwert der Attraktivität der Kunstwerke (leere Räume berücksichtigen wir mit einem Attraktivitätswert von 0)
 *        ○ Geringer Anteil der belegten Wandfläche des Raumes
 * 
 * Diese Aspekte der Gewichtung steuern wir mit einem einzigen Parameter. Eine hohe Qualitätsgewichtung rückt die Attraktivität in den Mittelpunkt,
 * sodass tendenziell die Raumbelegung (Quantität) abnimmt, und umgekehrt.
 * 
 * Darüber hinaus steuern wir ohne Parametrisierungsmöglichkeit, dass …
 *    - … Kunstwerke nur für die Platzierung im Raum in Frage kommen, wenn sie mindestens so viel Attraktivität aufweisen wie der aktuelle Mittelwert im Raum,
 *      also eine echte Attraktivitätsverbesserung versprechen. Dies zahlt auf den Qualitätsaspekt ein.
 *    - Jedoch dürfen in Räumen mit einer aktuellen Wandbelegung unter 60%  Bilder trotzdem platziert werden, 
 *      d.h. selbst wenn dadurch der reine Attraktvitätsmittelwert des Raums sinken würde. Dies zahlt auf den Quantitätsaspekt ein.   
 *    
 * Die folgende Tabelle zeigt unser Verständnis des Optimierungsproblems auf. 
 * Ganz rechts ist dargestellt, was bei der Minimal- bzw. der Erweiterungslösung zu beachten ist.    
 *|-----------------------------------|-------------------------------------------------------------------------|-------------|------------------------|--------------|
 *| #                                 | Optimierungsproblem                                                     | Bezug       | Relevanz Min           | Relevanz Erw |
 *|-----------------------------------|-------------------------------------------------------------------------|-------------|------------------------|--------------|
 *| Ziel                              | Maximale Attraktivität                                                  | Ausstellung | ja                     | ja           |
 *| Ziel                              | Wenig freie Wandfläche                                                  | Ausstellung | ja                     | ja           |
 *| Restriktion 1                     | Kostenobergrenze einhalten                                              | Ausstellung | ja                     | ja           |
 *| Restriktion 2                     | Schwerpunktthema in mindestens der Hälfte der Räume vertreten           | Ausstellung | ja als Zwischenziel    | Nein!        |
 *| Restriktion 3                     | in einem Raum höchstens drei verschiedene Themen                        | Raum        | nein (da <= 1 KW/Raum) | ja           |
 *| Restriktion 4                     | Bilder hinsichtlich Temperatur & Feuchte ohne Widerspruch               | Raum        | nein (da <= 1 KW/Raum) | ja           |
 *| Restriktion 5                     | Für alle Kunstwerke muss die Höhe zum Raum passen                       | Raum        | ja                     | ja           |
 *| Restriktion 6                     | Bilder Abstand min 1m an Wand                                           | Raum        | ja                     | ja           |
 *| Restriktion 7                     | G & I Abstand min 2m zu Wand, G untereinander 1m                        | Raum        | ja                     | ja           |
 *| Restriktion 8                     | I alleine im Raum, d.h. sonst keine Bilder oder G                       | Raum        | nein (da <= 1 KW/Raum) | ja           |
 *| Restriktion 9                     | I dominieren nicht, d.h. höchstens ein Drittel der Räume mit I          | Ausstellung | ja                     | ja           |
 *| Gemischter / heuristischer Aspekt | eine Verschlechterung der Raumattraktivität wird nur für Bilder in Kauf | Raum        | nein (da <= 1 KW/Raum) | ja           |
 *|                                   | genommen, und zwar wenn noch weniger als 60% der Raumfläche belegt sind |             |                        |              |
 *|-----------------------------------|-------------------------------------------------------------------------|-------------|------------------------|--------------|
 *</pre>
 * 
 * @author Thomas Scheidt 
 * @version 2023
 */
public class Zuordnung
{
    // ==========================================================================
    // === Attribute
    // ==========================================================================
   
    // Damit wir später auf Methoden der Kunstwerk- bzw. Raumverwaltung sowie Ausstellungsplanung zugreifen können:
    private Kunstwerkverwaltung kunstwerkverwaltung;
    private Raumverwaltung raumverwaltung;
    private Ausstellungsplanung ap;
    
    // Um die Referenzen auf die Kunstwerke bzw. Räume vom Import aufzunehmen (alle Kunstwerke/Räume):
    private Kunstwerk [] kunstwerkeArray;           
    private Raum [] raeumeArray;
    
    // Die Zuordnung der Kunstwerke zu Räumen. Liste von Kunstwerke im Raum pro Raum(nested ArrayList):
    private ArrayList <ArrayList <Kunstwerk >> denRaeumenZugeordneteKunstwerke = new ArrayList <ArrayList <Kunstwerk >>();
    // - Die äußere ArrayList wird so viele Elemente haben wie es Räume gibt. Sie enthät nicht direkt Räume,aber anhand der Position kann man in raeumeArray den entsprechenden Raum finden.
    // - Die innere ArrayList enthält Referenzen auf die Kunstwerke in einem konkreten Raum (Anzahl Elemente der inneren ArrayList: von keins ... bis theroetisch max Anzahl Kunstwerke)
    
    // Ebenso haben wir eine verschachtelte ArrayList, in der für jeden Raumindex (Position in äußerer ArrayList) festgehalten wird, welche Themen im Raum erlaubt sind:
    private ArrayList <ArrayList <String >> welcheThemenDuerfenNochInRaum = new ArrayList <ArrayList <String >> (); 
    // - Entweder sind genau drei Themen als Strings für den Raum aufgefüht. Dann sind nur noch Kunstwerke mit einem dieser drei Themen im Raum erlaubt
    // - Oder die ArrayList des Raums hat weniger als drei Einträge, dann ist noch alles erlaubt an Themen.
     
    // Liste, welche Typen noch in den Raum dürfen (pro Raum) - mit den erlaubten Werten "BIG" oder "BG" oder "": 
    private ArrayList <String> welcheTypenDuerfenNochInRaum = new ArrayList <String>();
    // - noch B/I/G (kein Bild oder Gegenstand bisher im Raum) oder
    // - nur noch weitere B/G im Raum platziert werden kann (ein Bild oder Gegenstand im Raum) oder 
    // - nichts mehr, weil schon eine I im Raum ist 
    
    // Liste aller Kunstwerke, die schon einem Raum zugeordnet wurden: 
    private ArrayList <Kunstwerk> kunstwerkeSchonZugeordnet = new ArrayList <Kunstwerk >(); 
    
    // Liste aller Räume, denen im Rahmen der Minimallösung schon ein KW zugeordnet wurde, oder die nicht mit einem Schwerpunktthema belegt werden konnten.
    private ArrayList <Raum> raeumeSchonFertigEinSchwerpunktKW = new ArrayList <Raum>(); 
    
    // Array der Bedarfswerte pro Raum, was im Rahmen der Erweiterungslösung wichtig ist, um zu entscheiden welcher Raum als nächstes für die Platzierung in Frage kommt:
    private double [] raeumeBedarfWeitereKunstwerke;
    // - je näher Richtung 1, desto bedürftiger ist der Raum mit diesem Index. 
    // - bei einem Wert von 0 ist der Raum perfekt gefüllt (Güte 1) oder nicht weiter füllbar (Güte kann auch kleiner 1 sein).
    
    // Auch die folgenden Arrays zu Raumdimensionen, Temperatur und Feuchte werden später so viele Elemente habe wie es Räume gibt (d.h. je ein Wert von z.B. Höhe pro Raum)
    // - relevant für B:
    private int [] verfuegbarWandWest; // Liste von (noch verfügbarer Wandplatz West) pro Raum
    private int [] verfuegbarWandOst;  // Liste von (noch verfügbarer Wandplatz Ost) pro Raum
    private int [] verfuegbarWandNord; // Liste von (noch verfügbarer Wandplatz Nord) pro Raum
    private int [] verfuegbarWandSued; // Liste von (noch verfügbarer Wandplatz Süd) pro Raum
    private int [] minFeuchteRaum; // Liste von (aufgrund platzierter Bilder erlaubte Feuchte) pro Raum
    private int [] maxFeuchteRaum; // Liste von (aufgrund platzierter Bilder erlaubte Feuchte) pro Raum
    private int [] minTempRaum; // Liste von (aufgrund platzierter Bilder erlaubte Temperatur) pro Raum
    private int [] maxTempRaum; // Liste von (aufgrund platzierter Bilder erlaubte Temperatur) pro Raum
    private int [] verfuegbarHoeheRaumBilder; // Liste von (noch verfügbarer Höhe) pro Raum im Falle von Bildern
    // - relevant für G und I:
    private int [] verfuegbarLaengeRaum; // Liste von (noch verfügbarer Raumdistanz der einen Raumachse) pro Raum
    private int [] verfuegbarBreiteRaum; // Liste von (noch verfügbarer Raumdistanz der anderen Raumachse) pro Raum
    private int [] verfuegbarHoeheRaum; // Liste von (noch verfügbarer Höhe) pro Raum im Falle von I und G

    private double qualitaetsgewicht; // Vorgegebene Qualitätsgewichtung (Werte von 0 bis 1; das Gewicht der Quantität ergibt sich umgekehrt als 1 minus Qualitaetsgewicht). Default-Wert 0.5. 
    private String schwerpunktthema; // Vorgegebenes Schwerpunktthema der Ausstellung. Default "" für keines.
    private double kostenobergrenze; // Vorgegebene Kostenobergrenze der Ausstellung. Default 999999999.
    private double restbudget; // Noch verbliebenes Budget, was mit der voranschreitenden Platzierung von Kunstwerken immer weiter abnimmt.
    private int wieOftWurdeSchonEinSchwerpunktKunstwertPlatziert = 0; // Wie oft über alle Räume schon ein Schwerpunktkunstwerk platziert wurde.
    private int wieOftWurdeSchonEineInstallationPlatziert = 0; // Wie oft über alle Räume schon eine Installation platziert wurde.
    private int wieOftGabEsValidierungsprobleme = 0; // Wie oft die Validierungsmethode das Eregbnis der Methode der Kunstwerkverwaltung zur Suche eines passenden KW nicht bestätigt
    
    // ==========================================================================
    // === Konstruktoren
    // ==========================================================================
      
    /**
     * Konstruktor für Objekte der Klasse Ausleihe
     * 
     *  @param in_kunstwerkverwaltung       Referenz auf die Kunstwerkverwaltung
     *  @param in_raumverwaltung            Referenz auf die Raumverwaltung
     *  @param in_ausstellungsplanung       Referenz auf die in_ausstellungsplanung
     */
    public Zuordnung(Kunstwerkverwaltung in_kunstwerkverwaltung,Raumverwaltung in_raumverwaltung,Ausstellungsplanung in_ausstellungsplanung)
    {
        // Damit wir später auf Methoden der Kunstwerk- bzw. Raumverwaltung zugreifen können, speichern wir uns die Referenzen auf die Verwaltungen ab:
        kunstwerkverwaltung = in_kunstwerkverwaltung;
        raumverwaltung = in_raumverwaltung;
        ap = in_ausstellungsplanung;
        
        // Aus der Kunstwerkverwaltung den Vector zu Kunstwerken abrufen und in Array umwandeln. Der Array wird an mehreren Stellen benötigt.
        kunstwerkeArray = new Kunstwerk [in_kunstwerkverwaltung.sizeKunstwerkverwaltung()];
        int i = 0;
        for (Kunstwerk kw: in_kunstwerkverwaltung.getKunstwerkVector()) {
            kunstwerkeArray[i] = kw;
            i++;
        }
        
        // Aus der Raumverwaltung den Vector der Räume abrufen und in Array umwandeln. Der Array wird an mehreren Stellen benötigt.
        raeumeArray = new Raum [in_raumverwaltung.anzahlRaeume()];
        i = 0;
        for (Raum r: in_raumverwaltung.getRaumVector()) {
            raeumeArray[i] =r ;
            i++;
        }
        
        // Initialisierungen von Arrays. Arrays haben ihrer Natur nach immer eine feste Länge. Array Länge ist hier immer die Anzahl der Räume!
        raeumeBedarfWeitereKunstwerke = new double [raeumeArray.length];
        verfuegbarWandWest = new int [raeumeArray.length];
        verfuegbarWandOst = new int [raeumeArray.length];
        verfuegbarWandNord = new int [raeumeArray.length];
        verfuegbarWandSued = new int [raeumeArray.length];
        verfuegbarLaengeRaum = new int [raeumeArray.length];
        verfuegbarBreiteRaum = new int [raeumeArray.length];
        verfuegbarHoeheRaum = new int [raeumeArray.length];
        verfuegbarHoeheRaumBilder = new int [raeumeArray.length];
        minFeuchteRaum = new int [raeumeArray.length];
        maxFeuchteRaum = new int [raeumeArray.length];
        minTempRaum = new int [raeumeArray.length];
        maxTempRaum = new int [raeumeArray.length];
        
        // Rufe die effektiv verfügbaren Raumdistanzen via Methode der Klasse Raum ab und übernehme sie hier:
        for (i=0;i<raeumeArray.length;i++)
        {
            verfuegbarWandWest[i]=raeumeArray[i].showWandWest();
            verfuegbarWandOst[i]=raeumeArray[i].showWandOst();
            verfuegbarWandNord[i]=raeumeArray[i].showWandNord();  
            verfuegbarWandSued[i]=raeumeArray[i].showWandSued();  
            verfuegbarLaengeRaum[i]=raeumeArray[i].showVerfuegbareLaenge();
            verfuegbarBreiteRaum[i]=raeumeArray[i].showVerfuegbareBreite();
            verfuegbarHoeheRaum[i]=raeumeArray[i].showVerfuegbareHoehe();
            verfuegbarHoeheRaumBilder[i]=raeumeArray[i].showVerfuegbareHoeheBilder();
        }
        
        //Setze arbiträre Anfangswerte für die erlaubten Temperaturen und Feuchten der Räume (für Bilder relevant)
        for (i=0;i<raeumeArray.length;i++)
        {
        minFeuchteRaum[i] = -999;
        maxFeuchteRaum[i] = +999;
        minTempRaum[i] = -999;
        maxTempRaum[i] = +999;
        }
        
        // Initialisiere die äußere Arraylisten von denRaeumenZugeordneteKunstwerke sowie welcheThemenDuerfenNochInRaum; setzte Anfangswerte je Raum für welcheTypenDuerfenNochInRaum
        for (i=0;i<raeumeArray.length;i++)
        {
            // Hierdurch gibt es je Raum eine Arraylist der Kunstwerke, wobei aktuell bei allen Räumen noch keine Kunstwerke darin sind
            denRaeumenZugeordneteKunstwerke.add(new ArrayList<Kunstwerk>()); 
            
            // Und analog für Themen im Raum:
            welcheThemenDuerfenNochInRaum.add(new ArrayList<String>());
            
            // Anfangs dürfen boch B I G in den Raum bis ein I gesetzt wurde (dann nichts mehr) oder B oder ein G (dann nur noch BG)
            welcheTypenDuerfenNochInRaum.add("BIG");
        }
        
        // Schwerpunktthema und Kostenobergrenze und Qualitätsgewicht zwischenspeichern
        schwerpunktthema=ap.getSchwerpunktthema();
        kostenobergrenze=ap.getKostenobergrenze();
        qualitaetsgewicht=ap.getQualitaetsgewicht();
        
        //Restbudget initial mit dem vollen Betrag belegen
        restbudget = kostenobergrenze;
    }

    // ==========================================================================
    // === Methoden, die die Zuordnung bilden
    // ==========================================================================    
    
    /**
     * Kernfragen unserer Zuordnung sind, was der nächste (beste) Raum ist, dem man welches nächste (beste) Kunstwerk zuordnet.
     * <pre>
     * Eine Minimalloesung bedeutet, dass genau ein Kunstwerk des Schwerpunktthemas in genau der Hälfte der Räume vertreten ist (also Restriktion #2 erfüllt ist)
     * 
     *      - Hierbei müssen wir also erstmal nur berücksichtigen die Restriktionen
     *              #5,#6,#7 (Höhen und Abstände) und
     *              #1 (globale Kostenobergrenze) und 
     *              #9 (höchstens ein Drittel der Räume abgerundet mit Installation)
     *      - während noch KEINE Bedeutung haben die Restriktionen 
     *              #8 (I alleine im Raum),
     *              #4 (mehrere Bilder im Raum ohne Temp/Feuchte Widerspruch),
     *              #3 (max drei verschiedene Themen im Raum)
     * </pre>             
     * Wir setzen an, dass wir einem zufälligen noch leeren Raum ein möglichst gutes KW zuordnen, dann dem nächsten etc..
     * Die zufällige Raumauswahl bewirkt, dass wir Pfadabhängigkeiten aufgrund der Reihenfolge der KW-Platzierung vermeiden und möglichst diverse Konstellationen
     * als minimale Zuordnungen erhalten. Später versuchen wir dann die minimalen Loesungen im Rahmen der Lösungserweiterung noch zu verbessern.
     */
    public void versucheMinimalloesungZuFinden ()
    {
        ap.printLog("\n---Beginne Suche nach einer Minimallösung ---");
        
        for (int n=0;n<raeumeArray.length;n++) // d.h. potentiell für jeden Raum wenn die Schleife vorher nicht abgebrochen wird
        {
            // Unser Etappenziel ist erreicht, wenn die Hälfte der Räume mit Schwerpunktkunstwerk versehen wurde. Dann soll die Schleife abbrechen:
            if (wieOftWurdeSchonEinSchwerpunktKunstwertPlatziert == raumverwaltung.anzahlHaelfteRaeume())
            {
                break; // Anweisung im Schleifenkörper führt zum sofortigen Abbruch der Schleife
            }
            
            // Wir benötigen einen zufällig ausgewählten noch leeren Raum, um dort zu versuchen ein KW zu platzieren:
            ap.printLog("noch " + (raeumeArray.length - raeumeSchonFertigEinSchwerpunktKW.size()) + " Räume zu prüfen.");
            Raum unserAktuellerRaum = raumverwaltung.zufealligerLeererRaum(raeumeSchonFertigEinSchwerpunktKW);
            
            // Wir brauchen auch den Index dieses Raums, damit wir in unseren Listen später die richtigen Werte zum Raumindex finden können: 
            int r = 0;
            for (Raum raum: raeumeArray)
            {
                if (raum.equals(unserAktuellerRaum))
                {
                    break; // breche die Schleife ab, weil unserAktuellerRaumIndex bereits den richtigen Wert hat
                }
                else
                {
                    r++; // erhoehe unserAktuellerRaumIndex um 1 und schaue ob der nächste Raum der mit dem gesuchten Index ist
                }
            }
            
            ap.printLog("-> Haben wir ein geeignetes Kunstwerk für Raum " + raeumeArray[r].getNummer() + " ?");
            // Das Folgende gibt uns das als nächstes zu setzende Kunstwerk in die Hand,
            // das vom Schwerpunktthema (R2) ist, die Restriktionen 5,6,7 (im Raum) erfüllt, sodass auch R1 (global Kosten) und R9 (nicht zu viele I) erfüllt sind. 
            // Und bei all dem ist es das KW mit bestem Wert von Attraktivität & Attraktivitäts-Kosten-Verhältnis (die Gewichtung wurde in der Klassenbeschreibung erklärt).
            
            short laufendeNummer = kunstwerkverwaltung.naechstesZuSetzendesKunstwerk
                (
                schwerpunktthema,
                verfuegbarWandWest[r],verfuegbarWandOst[r],verfuegbarWandNord[r],verfuegbarWandSued[r], // relevant für Bilder (vier Wände)
                verfuegbarLaengeRaum[r],verfuegbarBreiteRaum[r],                                        // relevant für G und I (laengs/quer bzw Raumfläche)
                verfuegbarHoeheRaum[r],                                                                 // relevant für G und I
                verfuegbarHoeheRaumBilder[r],                                                           // relevant für Bilder
                restbudget,                                                                             // verfügbares Restbudget (double)
                kunstwerkeSchonZugeordnet,                                                              // bisher platzierte Kunstwerke (Arraylist)
                ((double) wieOftWurdeSchonEineInstallationPlatziert)/raeumeArray.length,                // Anteil der mit I belegten Räume. (cast/conversion für die Division nötig)
                qualitaetsgewicht                                                                       // Gewichtung von Qualität und Quantität
                ); 
            ap.printLog("verfuegbarWandWest: " + verfuegbarWandWest[r] + " Ost: " + verfuegbarWandOst[r] + " Nord: " + verfuegbarWandNord[r] + " Sued: " + verfuegbarWandSued[r]);
            ap.printLog("verfuegbarLaengeRaum: " + verfuegbarLaengeRaum[r] + " verfuegbarBreiteRaum: " + verfuegbarBreiteRaum[r]);
            ap.printLog("verfuegbarHoeheRaum: " + verfuegbarHoeheRaum[r] + " verfuegbarHoeheRaumBilder: " + verfuegbarHoeheRaumBilder[r]);
            ap.printLog("restbudget: " + (int) restbudget);
            ap.printLog("wie viele KW zugeordnet: " + kunstwerkeSchonZugeordnet.size());
            ap.printLog("anteilI: " + ((double) wieOftWurdeSchonEineInstallationPlatziert)/raeumeArray.length);
            ap.printLog("qualitaetsgewicht: " + qualitaetsgewicht);
            
            Kunstwerk zuSetzendesKW = null;
            if (laufendeNummer==-1) // die Kunstwerkverwaltung gibt -1 zurück, wenn KEIN Kunstwerk in den Raum passt (z.B. wegen verfügbarer Fläche oder Restbudget zu klein)
            {
                raeumeSchonFertigEinSchwerpunktKW.add(raeumeArray[r]); // um zu vermeiden, dass wir den Raum erneut überprüfen
                ap.printLog("Leider passt kein geeignetes Schwerpunktkunstwerk in diesen Raum\n");
                continue;// Anweisung beendet die aktuelle Ausführung des Schleifenkörpers, aber die Schleife wird mit dem nächsten Durchlauf (d.h. nächster Raum) fortgesetzt 
            }
            else if (laufendeNummer>=0) // Ermittlung des zu setzenden Kunstwerkes
            {
                zuSetzendesKW = kunstwerkverwaltung.showKunstwerkZuLaufendeNummer(laufendeNummer);
                ap.printLog("Attraktivität: " + zuSetzendesKW.getAttraktivitaet() + ", Thema: " + zuSetzendesKW.getThema() + ", Nr: " + zuSetzendesKW.getLaufendeNummer() + 
                            ", Art des Kunstwerks: " + zuSetzendesKW.getArt() + ", Bezeichnung: " + zuSetzendesKW.getBezeichnung());
                ap.printLog("Index bestes KW: " + laufendeNummer);
            }

            // Jetzt validieren wir mit einer Methode unserer Klasse, ob das Kunstwerk wirklich passt, oder ob sich in der Implementierung ein Fehler eingeschlichen haben könnte
            boolean problem = (passtKWValidierung (zuSetzendesKW,r) != (laufendeNummer>=0));
            if (problem)
            {
                ap.printLog("XXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxACHTUNG WIDERSPRUCH:" +
                                    "es konnte nicht validiert werden, dass das KW in den Raum" + raeumeArray[r].getNummer() + " passt.");
            }
            
            // Wenn wir bis hierhin im Schleifenkörper ohne break/continue gekommen sind, können wir das ausgewählte Kunstwerk im Raum platzieren (= das eigentliche Setzen):
            denRaeumenZugeordneteKunstwerke.get(r).add(zuSetzendesKW);
            ap.printLog("---> Es wurde belegt Raum " + raeumeArray[r] + " !" );
            
            // Setzen heißt, dass unmittelbar danach einige Parameter zu aktualisieren sind:
            aktualisiereParameterNachSetzen(zuSetzendesKW,r); 
            
            ap.printLog("wie viele Räume haben nun genau ein Schwerpunktkunstwerk:" + wieOftWurdeSchonEinSchwerpunktKunstwertPlatziert + "\n");
        }
        ausgebenZuordnungAufKonsole();
    }
    
    /**
     * Erweiterung der Minimalloesung: Wir versuchen, in Räumen ohne Kunstinstallation noch weitere unzugeordnete Bilder und Kunstgegenstände beliebigen Themas unterzubringen,
     * wobei in leeren Räumen auch eine Kunstinstallation in Frage kommt. Dabei müssen alle Restriktionen erfüllt werden mit Ausnahme von R2, weil
     * <pre>
     *      a) wenn kein Schwerpunktthema gesetzt wurde, dann ist R2 egal und wir starten direkt hier, ohne die Methode versucheMinimalloesungZuFinden() vorher ausgeführt zu haben
     * 
     *      b) die Methode versucheMinimalloesungZuFinden haben wir zuerst ausgeführt, sodass Restriktion 2 (Schwerpunktthema in mind. der Hälfte der Räume) erfüllt ist,
     *         zumindest sofern es eine Minimalloesung gab. (Gab es keine Minimallösung verlangt die Ausstellungsplanung, dass ein anderes Schwerpunktthema oder keines gewählt wird.)
     *  
     * Jetzt sind also nur noch zu berücksichtigen:
     * 
     *   - im Prinzip wie bei versucheMinimalloesungZuFinden analog zum Einsatz der Methode kunstwerkverwaltung.naechstesZuSetzendesKunstwerk():
     *        -- Restriktionen 5,6,7 (Höhen und Abstände)
     *        -- R1 (globale Kostenobergrenze)
     *        -- R9 (höchstens ein Drittel der Räume (abgerundet) mit Installation)
     *        -- Der Ansatz, dass wir versuchen das beste Kunstwerk zu platzieren, als dasjenige mit höchster Mischung aus Attrakvität und Attrakvitität-pro-Kosten (s.o.)
     *   - aber NICHT mehr:
     *        -- R2 (Schwerpunktthema in min. der Hälfte der Räume)
     *   - nun neu:
     *        -- R4 (mehrere Bilder im Raum ohne Temp/Feuchte Widerspruch) => wir stellen unsere hiesigen Arrays zu Temp & Feuchte bereit
     *        -- R3 (max 3 versch Themen im Raum) => ggf. nur manche Themen für den Raum noch erlaubt => wir übergeben welcheThemenDuerfenNochInRaum
     *        -- R8 (I alleine im Raum) => wir übergeben, ob noch B/I/G (kein Kunstwerk bisher im Raum) oder nur noch B/G geht (B/G schon im Raum) => wir geben welcheTypenDuerfenNochInRaum
     *        -- gemischter Restriktions-/Zielaspekt (eine Verschlechterung der Raumattraktivität wird für Bilder in Kauf genommen, wenn noch weniger als 60% der Raumfläche belegt sind)   
     * </pre>
     * Dafür implementieren wir in der Kunstwerkverwaltung eine Methode naechstesZuSetzendesKunstwerkErweiterung() zur Ermittlung des nächsten zu platzierenden Kunstwerkes.
     * Wir prüfen zudem hier in der Methode bereits vorab, ob schon eine I im Raum ist - in dem Fall rufen wir die Methode gar nicht erst auf und gehen zum nächsten Raum über. Ansonsten wurde das Prinzip bereits in der Klassenbeschreibung
     * das Vorgehen der Raumbelegung erklärt, nämlich dass wir nach jeder Platzierung untersuchen welcher Raum noch bedürftigsten ist, und wir aufhören, wenn wir keinen Raum mehr verbessern
     * können.
     */
    public void versucheLoesungserweiterung()
    {
        // Im folgenden Array sammeln wir, wie bedürftig Räume aktuell noch nach weiteren Kunstwerken sind. Dies basiert auf unserer Definition von Güte,
        // die wir mittels der Methode gueteRaum(int r) für einen Raum berechnen.
        // Befarfswerte Richtung 1 bedeuten, dass der Raum noch relativ unattraktiv ist bzw. viel frei Wandfläche hat, entsprechend einer Güte Richtung 0. 
        // Ein Befarfswert von 0 bedeutet, dass der Raum perfekt ist (Güte von 1) oder wir setzen den Bedarf auch auf 0, wenn der Raum nicht weiter verbessert werden kann.
        
        for (int r=0;r<raeumeArray.length;r++)
        {
            raeumeBedarfWeitereKunstwerke[r] = 1 - gueteRaumKombiniert(r);
            
            if (welcheTypenDuerfenNochInRaum.get(r).equals("")) // dann sind im Raum keine weiteren KW möglich, weil eine Installation aus der Minimallösung diesen blockiert
            {
                raeumeBedarfWeitereKunstwerke[r] = 0; // daher gilt der Raum für uns von Anfang an als abgeschlossen.
            }
        }
        
        // Wir versuchen jetzt immer wieder in dieser Schrittfolge
        //      1. unter allen Räumen mit Bedarf größer 1 den Raum  zu ermitteln, der noch am bedürftigsten ist d.h. den kleinsten Gütewert hat
        //      2. und ein Kunstwerk in diesen Raum zu platzieren sowie danach in diesem Raum den Bedarf zu aktualisieren,
        // bis in allen Räumen keine Platzierung mehr möglich ist, weil
        //      - entweder jeder Raum perfekt gefüllt ist mit Güte 1 und demnach natürlicherweise keinen Bedarf an Optimierung mehr hat (Bedarf = 0).
        //      - oder in jedem Raum kein Kunstwerk mehr passt (z.B. wegen Dimensionen, z.B. weil bereits sämtliche Kunstwerke in anderen Räumen platziert wurden). 
        //        Passt kein Kunstwerk mehr in einen Raum, setzen wir den Bedarf in diesem Raum auf 0, sodass er nicht mehr durchlaufen wird.
        // Wir sind fertig, wenn alle Räume den Bedarfswert 0 haben.
        
        double maxBedarf = raeumeBedarfWeitereKunstwerke[maxBedarfIndex()];
        while (maxBedarf>0) // solange es also noch in irgendeinem Raum einen Bedarf > 0 gibt
        {
            // Bedürftigster Raum (s.o. unter 1.):
            int r = maxBedarfIndex();
            
            // Versuchen Kunstwerk in diesen Raum zu platzieren (s.o. unter 2.):
            short zuSetzendesKunstwerkLaufendeNummer = kunstwerkverwaltung.erweitereRaumlösung
                (
                verfuegbarWandWest[r],verfuegbarWandOst[r],verfuegbarWandNord[r],verfuegbarWandSued[r], // relevant für Bilder (vier Wände)
                verfuegbarLaengeRaum[r],verfuegbarBreiteRaum[r],                                        // relevant für G und I (laengs/quer bzw Raumfläche)
                verfuegbarHoeheRaum[r],                                                                 // relevant für G und I
                verfuegbarHoeheRaumBilder[r],                                                           // relevant für Bilder
                restbudget,                                                                             // verfügbares Restbudget (double)
                kunstwerkeSchonZugeordnet,                                                              // bisher platzierte Kunstwerke (Arraylist)
                ((double) wieOftWurdeSchonEineInstallationPlatziert)/raeumeArray.length,                // Anteil der mit I belegten Räume. (cast/conversion für die Division nötig)
                qualitaetsgewicht,                                                                      // Gewichtung von Qualität und Quantität
                minFeuchteRaum[r], maxFeuchteRaum[r],minTempRaum[r], maxTempRaum[r],                    // relevant für Bilder
                welcheThemenDuerfenNochInRaum.get(r),  // falls diese ArrayList genau (!) drei Elemente enthält, sind nur noch KW mit einem dieser Themen erlaubt
                welcheTypenDuerfenNochInRaum.get(r),   // es wird "BIG" oder "BG" übergeben (ob der Typ egal ist oder es nur noch B/G sein darf)
                gueteRaumAttraktivitaet(r)*100,  // Aktueller Durchschnitt der Attraktivität der Kunstwerke im Raum (Mal 100 um die Normierung rückgängig zu machen)
                                                 // -> wir platzieren nur KW mit höherem Attraktivitätswert...
                                                 // -> ... es sei denn es geht um ein Bild und die Raumbelegung (gueteRaumBelegung(r)) ist noch < 60%
                gueteRaumBelegung(r)             // Anteil der aktuellen Wandbelegung des Raumes mit Bildern          
                );
            
            ap.printLog("\nIndex bestes KW: "+ zuSetzendesKunstwerkLaufendeNummer);    
            if (zuSetzendesKunstwerkLaufendeNummer>=0) // Die Kunstwerkverwaltung konnte für uns ein freies passendes ideales Kunstwerk für diesen Raum finden
            {
                Kunstwerk zuSetzendesKW = kunstwerkverwaltung.showKunstwerkZuLaufendeNummer(zuSetzendesKunstwerkLaufendeNummer);
                
                // Jetzt validieren wir, ob das Kunstwerk wirklich passt, oder ob sich in der Implementierung ein Fehler eingeschlichen haben könnte
                boolean problem = (passtKWValidierung (zuSetzendesKW,r) != true);
                if (problem)
                {
                    ap.printLog("XXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxACHTUNG WIDERSPRUCH:" +
                                    "es konnte nicht validiert werden, dass das KW in den Raum" + raeumeArray[r].getNummer() + " passt.");
                }
                
                // Wir setzen das Kunstwerk
                denRaeumenZugeordneteKunstwerke.get(r).add(zuSetzendesKW);
                ap.printLog("---> Es wurde belegt Raum " + raeumeArray[r] + " !" );
                aktualisiereParameterNachSetzen(zuSetzendesKW,r); 
                
                // Aktualisieren des Bedarfes vom Raum
                if (welcheTypenDuerfenNochInRaum.get(r).equals("BG")) // wenn schon ein Bild oder ein Kunstgegenstand im Raum ist, dürfen noch weitere B oder G in den Raum
                {
                    // Aktualisieren des Bedarfs in diesem Raum mit dem nun aktuellen Wert
                    raeumeBedarfWeitereKunstwerke[r] = 1 - gueteRaumKombiniert(r); 
                }
                else if (welcheTypenDuerfenNochInRaum.get(r).equals("")) // wenn eine Installation im Raum gesetzt wurde
                {
                    // Wenn eine Kunstinstallation platziert wurde, wissen wir sicher, dass sonst nichts mehr in den Raum herein passt und schließen den Raum ab (Bedarf 0).
                    raeumeBedarfWeitereKunstwerke[r] = 0;
                    ap.printLog("---> Optimierung wurde abgeschlossen für die Belegung von Raum" + raeumeArray[r].getNummer() + " !" );
                }
            }
            
            else if (zuSetzendesKunstwerkLaufendeNummer<0) // Laut Kunstwerkverwaltung war kein freies passendes Kunstwerk mehr für den Raum zu finden
            {
                // Wir setzen den Bedarf auf 0, damit dieser Raum abgeschlossen ist
                raeumeBedarfWeitereKunstwerke[r] = 0;
                ap.printLog("---> Optimierung wurde abgeschlossen für die Belegung von Raum" + raeumeArray[r].getNummer() + " !" );
            }
            
            // Anschließend ermitteln wir den nun aktuellen maximalen Bedarf, damit die while-Schleife ggf. abbrechen kann, wenn es keinerlei Bedarf mehr gibt
            maxBedarf = raeumeBedarfWeitereKunstwerke[maxBedarfIndex()];
        }
        
        
        // Abschließend können wir in den Log nochmals die Bedarfe der Räume schreiben, die wir nun durchgängig mit 0 erwarten würden    
        for (int r=0;r<raeumeArray.length;r++)
        {
            ap.printLog("Bedarf für Raum" + raeumeArray[r].getNummer() + " = " + raeumeBedarfWeitereKunstwerke[r]);
        }
        ausgebenZuordnungAufKonsole(); // die erreichte Zuordnung kann ebenfalls in den Log
    }
    
    /**
     * Diese Methode muss man nach dem Setzen aufrufen, denn sie aktualisiert folgende Parameter, die sich durch das Platzieren des Kunstwerks im Raum ändern:<pre>
     *      - 6x räumliche Dimensionen (4 wände, 2 Raumachsen). Hier vereinfachen wir für Kunstgegenstände die Flächenfüllung, indem wir Kunstgegenstände 
     *        nur "in einer Reihe" platzieren, wobei wir allerdings sowohl eine Positionierung laengs als auch quer zulassen und berücksichtigen.
     *      - restbudget
     *      - kunstwerkeSchonZugeordnet
     *      - raeumeSchonFertigEinSchwerpunktKW (Räume mit einem Schwerpunktkunstwerk, relevant für Minimallösung)
     *      - Temp/Feuchte Min/Max aufgrund Bildern im Raum
     *      - wenn es schon drei Themen im Raum gibt, welches das sind, weil dann nur noch diese erlaubt sind für weitere KW (Themen werden ab 1. Thema erfasst)
     *      - ob noch B/I/G (kein Kunstwerk bisher im Raum) oder nur noch B/G im Raum platziert werden kann (B/G im Raum) oder nichts mehr (I im Raum)
     *      - wie oft schon über alle Räume ein Kunstwerk des Schwerpunktthemas gesetzt wurde
     *      - wie oft schon eine Installation gesetzt wurde</pre>
     *      
     * @param r     Raumindex, wo das Kunstwerk zu platzieren ist
     * @param kw    zu setzendes Kunstwerk
     */
     
    public void aktualisiereParameterNachSetzen(Kunstwerk kw, int r)
    {
        /** --------------------------------------------
        // Aktualisiere räumliche Dimensionen
        */

        // Bei Bildern: 
        // Wir suchen für ein Bild die Wand mit dem meisten Platz aus. Bliebe nach der Platzierung an dan dieser Wand weniger als 100 cm Platz, 
        // dann kann der Puffer zum nächsten Bild nicht eingehalten werden, sodass die Wand in dem Fall keinen Platz mehr für weitere Kunstwerke hat. 
        // Bliebe mehr als 100 cm Platz, muss noch ein Meter extra abgezogen werden, da dies der Puffer zum nächsten Bild darstellt und entsprechend den effektiv verfügbaren 
        // Platz für das nächste Bild reduziert:
        
        if (kw.getArt()=='B')
        {
            ArrayList<Integer> verfuegbarkeitenWaende = new ArrayList<Integer>();
            verfuegbarkeitenWaende.add(verfuegbarWandWest[r]);
            verfuegbarkeitenWaende.add(verfuegbarWandOst[r]);
            verfuegbarkeitenWaende.add(verfuegbarWandNord[r]);
            verfuegbarkeitenWaende.add(verfuegbarWandSued[r]);
            int indexVerfWand = verfuegbarkeitenWaende.indexOf(Collections.max(verfuegbarkeitenWaende)); // Index der Wand mit meistem Platz
            if (indexVerfWand==0) // West
            {
                if (verfuegbarWandWest[r]-(kw.getBreite())>=100)
                {verfuegbarWandWest[r]-=(kw.getBreite()+100);}
                else
                {verfuegbarWandWest[r]=0;}
            }
            else if (indexVerfWand==1) // ost
            {
                if (verfuegbarWandOst[r]-(kw.getBreite())>=100)
                {verfuegbarWandOst[r]-=(kw.getBreite()+100);}
                else
                {verfuegbarWandOst[r]=0;}
            }
            else if (indexVerfWand==2) // Nord
            {
                if (verfuegbarWandNord[r]-(kw.getBreite())>=100)
                {verfuegbarWandNord[r]-=(kw.getBreite()+100);}
                else
                {verfuegbarWandNord[r]=0;}
            }
            else if (indexVerfWand==3) // sued
            {
                if (verfuegbarWandSued[r]-(kw.getBreite())>=100)
                {verfuegbarWandSued[r]-=(kw.getBreite()+100);}
                else
                {verfuegbarWandSued[r]=0;}
            }
        }
        
        // Bei Gegenständen oder Installationen:  
        // Bei Kunstgegenständen muss ein Meter als Puffer zu anderen G abgezogen werden, wenn noch ein Rest größer 100 cm übrig bliebe (andernfalls passt kein G mehr).
        // Bei I ist es im Grunde egal, weil sowieso sonst nichts mehr in den Raum passt, aber es schadet nicht, die selbe Logik durchzuhalten.
        // Bei Kunstgegenständen ist die Frage, ob die Platzierung "laengs" oder "quer" die beste ist, sofern beide Optionen möglich sind. Wir nehmen 
        // in solchen Fällen die Platzierungsoption,bei der am meisten FLÄCHE ungenutzt leibt.
        
        if (kw.getArt()=='G'| kw.getArt()=='I')
        {
            int l = 0;
            int b = 0;
            // die Parentclass "Kunstwerk" hat nicht die Methode getLaenge(), die wir aber hier benötigen, daher casten wir
            if (kw.getArt()=='G')
            {Kunstgegenstand g = (Kunstgegenstand) kw;
            l=g.getLaenge();
            b=g.getBreite();
            }
            else if (kw.getArt()=='I')
            {Kunstinstallation i = (Kunstinstallation) kw;
            l=i.getLaenge();
            b=i.getBreite();
            }
            int restl1=verfuegbarLaengeRaum[r]-l; // Fall laengs
            int restl2=verfuegbarBreiteRaum[r]-b; // Fall laengs
            int restq1=verfuegbarLaengeRaum[r]-b; // Fall quer
            int restq2=verfuegbarBreiteRaum[r]-l; // Fall quer
            
            // Wenn sowohl laengs als auch quere Positionierung ist möglich:
            if (restl1>=0 && restl2>=0 && restq1>=0 && restq2>=0)
            {
                if (restl1*restl2>=restq1*restq2) //Platzierung laengs wird vorgenommen
                {
                    if (restl1>=100 && restl2>=100) // nur wenn die Reste beide größer gleich 1m sind, können wir ein Meter Puffer abziehen
                    {
                        verfuegbarLaengeRaum[r]-=(l+100); 
                        verfuegbarBreiteRaum[r]-=(b+100);
                    }
                    else // sonst ist Ende Gelände
                    {
                        verfuegbarLaengeRaum[r]=0;
                        verfuegbarBreiteRaum[r]=0;
                    }
                }
                else if (restl1*restl2<restq1*restq2) // Platzierung quer wird vorgenommen
                {
                    if (restq1>=100 && restq2>=100) // nur wenn die Reste beide größer gleich 1m sind, können wir ein Meter Puffer abziehen
                    {
                        verfuegbarLaengeRaum[r]-=(b+100); 
                        verfuegbarBreiteRaum[r]-=(l+100);
                    }
                    else // sonst ist Ende Gelände
                    {
                        verfuegbarLaengeRaum[r]=0;
                        verfuegbarBreiteRaum[r]=0;
                    }
                }
            }
            
            // Wenn nur laengs Positionierung möglich:
            if (restl1>=0 && restl2>=0 && (restq1<0 | restq2<0))
            {
                //Platzierung laengs wird vorgenommen
                    if (restl1>=100 && restl2>=100) // nur wenn die Rest beide größer gleich 1m sind, können wir 1 Meter Puffer abziehen
                    {
                        verfuegbarLaengeRaum[r]-=(l+100); 
                        verfuegbarBreiteRaum[r]-=(b+100);
                    }
                    else // sonst ist Ende Gelände
                    {
                        verfuegbarLaengeRaum[r]=0;
                        verfuegbarBreiteRaum[r]=0;
                    }

            }
            
            // Wenn nur quere Positionierung möglich:
            if ((restl1<0 | restl2<0) && restq1>=0 && restq2>=0)
            {
                // Platzierung quer wird vorgenommen
                if (restq1>=100 && restq2>=100) // nur wenn die Reste beide größer gleich 1m sind, können wir 1 Meter Puffer abziehen
                    {
                        verfuegbarLaengeRaum[r]-=(b+100); 
                        verfuegbarBreiteRaum[r]-=(l+100);
                    }
                    else // sonst ist Ende Gelände
                    {
                        verfuegbarLaengeRaum[r]=0;
                        verfuegbarBreiteRaum[r]=0;
                    }
            }
        }
        
        /** ------------------
        // Aktualisiere Budget
        */
       
        restbudget = restbudget - kw.getKosten(); // noch verbliebenes Budget
    
        /** ----------------------------------------
        // Aktualisiere Arraylist der belegten Räume
        */
        
        if (!raeumeSchonFertigEinSchwerpunktKW.contains(raeumeArray[r])) // wenn ein Raum bisher noch völlig ohne Kunstwerk ist
        {   
            raeumeSchonFertigEinSchwerpunktKW.add(raeumeArray[r]);
            
        }
        
        /** ------------------------------------------------
        // Aktualisiere Arraylist der platzierten Kunstwerke
        */
        
        kunstwerkeSchonZugeordnet.add(kw);

        /** ------------------------------------------------------------------------------------------
        // Aktualisiere die Arrays der im Raum erlaubten Temperaturen & Feuchten (für Bilder relevant)
        */
        // Schauen wir uns zuerst den Fall der Temperaturen an. 
        // Ein Bild hat eine erlaubte Range zwischen Mindest- und Maximalwert. Kürzen wir die Grenzen mit bmin und bmax ab.
        // Ein Raum hat zunächst noch keine Grenzwerte (wir nehmen willkürlich -999/+999).
        // Der Raum nimmt bei der Platzierung des ersten (!) Bildes im Raum dessen Grenzen an. Nennen wir die Grenzen rmin und rmax.
        
        // Ein weiteres Bild passt dann, wenn es einen Überlapp der Range von bmin und bmax sowie rmin und rmax gibt.
        // Ansonsten können die Werte im Raum nicht eingstellt werden, weil die Heizung nicht sowohl die Ranges des neuen Bildes als auch
        // der bisherigen Bilder erfüllen kann, wie die folgende Skizze zeigt:
        //                           rmin                   rmax      bmin...bmax 
        
        // In drei Fallkonstellationen gibt es einen Überlapp:
        //                           rmin    bmin-----------rmax......bmax     
        //                  bmin.....rmin-----------bmax    rmax
        //                           rmin   bmin------bmax  rmax
        
        // Fall A: (rmin <= bmin <= rmax <= bmax)   -> rmin ist auf bmin einzustellen, da es sonst zu kalt sein kann für das neue Bild
        // Fall B: (bmin <= rmin <= bmax <= rmax)   -> rmax ist auf bmax einzustellen, da es sonst zu warm sen kann für das neue Bild
        // Fall C: (rmin <= bmin <= bmax <= rmax)   -> rmin ist auf bmin einzustellen und rmax auf bmax, da es sonst zu kalt oder warm sein kann für das neue Bild
        
        // Praktischerweise liegt Fall C auch vor, wenn wir für einen leeren Raum rmin = -999 setzen sowie rmax = +999. Das haben wir anfangs auch so initialisiert.
        // Ebenfalls ist praktisch, dass obige Überlegung nicht nur für die Temperaturen greift, sondern genau so auch für die Feuchten.
        
        // Wir können noch obiges Schema noch weiter vereinfachen:
        //      - Wenn (rmin <= bmin <= rmax), dann ist rmin auf bmin einzustellen, da es sonst zu kalt sein kann für das neue Bild
        //      - Wenn (rmin <= bmax <= rmax), dann ist rmax auf bmax einzustellen, da es sonst zu warm sein kann für das neue Bild
        //      - Beides kann auch zusammen passieren.
        //      - Liegt mindestens eine der beiden Bdeingungen vor, gibt es einen Überlapp und demnach ist das Bild platzierbar.
        
        if (kw.getArt()=='B')
        {
            Bild b = (Bild) kw; // die Parentclass "Kunstwerk" hat nicht die Methoden/Eigenschaften für Temp & Feuchte, die wir aber gleich benötigen, daher casten wir
            
            // Umsetzung obiger Logik
            if ((minFeuchteRaum[r] <= b.getMinLuft()) & (b.getMinLuft() <= maxFeuchteRaum[r]))
            {
                minFeuchteRaum[r] = b.getMinLuft();
            }
            if ((minTempRaum[r] <= b.getMinTemp()) & (b.getMinTemp() <= maxTempRaum[r]))
            {
                minTempRaum[r] = b.getMinTemp();
            }
            if ((minFeuchteRaum[r] <= b.getMaxLuft()) & (b.getMaxLuft() <= maxFeuchteRaum[r]))
            {
                maxFeuchteRaum[r] = b.getMaxLuft();
            }
            if ((minTempRaum[r] <= b.getMaxTemp()) & (b.getMaxTemp() <= maxTempRaum[r]))
            {
                maxTempRaum[r] = b.getMaxTemp();
            }
        }
        
        /** ------------------------------------------------------------------------------------------
        // Aktualisiere, welche drei Themen im Raum erlaubt sind 
        // --- bei potentiell mehr als 3 Themen im Raum relevant dafür, dass nur noch diese 3 Themen für weitere KW erlaubt sind
        // --- welcheThemenDuerfenNochInRaum zeigt die 3 erlaubten Themen in einem Raum; sind es weniger als 3 Einträge für den Raum, dann sind noch alle Themen erlaubt
        */
        if (welcheThemenDuerfenNochInRaum.get(r).size()<=2) {  // wenn es kleiner gleich 2 unique Themen im Raum vorher gab
            if (!welcheThemenDuerfenNochInRaum.get(r).contains(kw.getThema()))  // wenn das Thema bisher noch nicht im Raum vertreten ist
            {
                welcheThemenDuerfenNochInRaum.get(r).add(kw.getThema());
            }
        }

        /** ------------------------------------------------------------------------------------------
        // Aktualisiere erlaubte Typen im Raum, also ob
        // --- noch B/I/G (kein Bild oder Gegenstand bisher im Raum) oder
        // --- nur noch weitere B/G im Raum platziert werden kann (ein Bild oder Gegenstand im Raum) oder 
        // --- nichts mehr, weil schon eine I im Raum ist
        */
        if (kw.getArt()=='G'| kw.getArt()=='B')
        {
            if (welcheTypenDuerfenNochInRaum.get(r).equals("BIG")) // per Initialisierung steht dort "BIG" als Eintrag, danach muss es in diesem Fall permanent "BG" bleiben
            {
                welcheTypenDuerfenNochInRaum.set(r,"BG");
            }
        }
        else if(kw.getArt()=='I')
        {
            if (welcheTypenDuerfenNochInRaum.get(r).equals("BIG")) // per Initialisierung steht dort "BIG" als Eintrag, danach muss es in diesem Fall permanent "" bleiben
            {
                welcheTypenDuerfenNochInRaum.set(r,"");
            }
        }
        
        /** ------------------------------------------------------------------------------------------
        // Aktualisiere, wie oft schon ein Schwerpunktkunstwerk gesetzt wurde
        */
        if (kw.getThema().equals(schwerpunktthema))
        {
            wieOftWurdeSchonEinSchwerpunktKunstwertPlatziert++;
        }
        
        /** ------------------------------------------------------------------------------------------
        // Aktualisiere, wie oft schon über alle Räume hinweg eine Installation gesetzt wurde 
        */        
        if (kw.getArt()=='I')
        {
            wieOftWurdeSchonEineInstallationPlatziert++;
        }
    }
    
    // ==========================================================================
    // === Validierungsmethoden
    // ==========================================================================  
    
    /**
     * Passt Kunstwerk hinsichtlich Höhe in den Raum
     * 
     * @param r                 Raumindex r
     * @param in_Kunstwerk      Kunstwerk
     * @return                  Ob die Höhe passt
     */
    private Boolean passtKunstwerkHoehe (Kunstwerk in_Kunstwerk, int r)
    { 
        boolean passtHoehe = false;
        if (in_Kunstwerk.getArt()=='B')
        {
            passtHoehe = (in_Kunstwerk.getHoehe()<=verfuegbarHoeheRaumBilder[r]);
        }
        else
        {
            passtHoehe = (in_Kunstwerk.getHoehe()<=verfuegbarHoeheRaum[r]); // für I und G gilt eine leicht andere Höhe
        }
        ap.printLog("-Höhe passt in Raum " + passtHoehe );
        return passtHoehe;
    }
    
    /**
     * Passt Kunstwerk in den Raum hinsichtlich der Frage, ob eine Installation im Raum die weitere Platzierung von KW verhindert
     * 
     * @param r                 Raumindex r
     * @param in_Kunstwerk      Kunstwerk
     * @return                  Ob I die weitere Platzierung blockiert
     */
    private Boolean passtIblockiertNicht (Kunstwerk in_Kunstwerk, int r)
    {
        boolean passtIblockiertNicht = true;
        if (in_Kunstwerk.getArt()=='B'| in_Kunstwerk.getArt()=='G')
        {
            //Wie folgt prüfen wir ab, ob es irgendein KW der Art I im Raum r gibt:
            for (Kunstwerk k: denRaeumenZugeordneteKunstwerke.get(r))
            {
                if (k.getArt()=='I')
                {
                    passtIblockiertNicht =false;
                }
            }
        }
        ap.printLog("-Raum ist nicht bereits durch eine I blockiert " + passtIblockiertNicht);
        return passtIblockiertNicht;
    }
    
    /**
     * Passt Kunstwerk von den Dimensionen an die Wände (für Bilder) bzw. in die Fläche (für I/G) des Raumes
     * 
     * @param r                 Raumindex r
     * @param in_Kunstwerk      Kunstwerk
     * @return                  Ob das KW von den Dimensionen an die Wände bzw. in die Fläche passt
     */
    private Boolean passtKunstwerkAnWandOderInFläche(Kunstwerk in_Kunstwerk, int r) 
    {
        boolean passtDim = false;
        
        if (in_Kunstwerk.getArt()=='B')
        {
            boolean passtNord = (verfuegbarWandNord[r]-in_Kunstwerk.getBreite()>=0);
            boolean passtSued =(verfuegbarWandSued[r]-in_Kunstwerk.getBreite()>=0);
            boolean passtOst =(verfuegbarWandOst[r]-in_Kunstwerk.getBreite()>=0);
            boolean passtWest =(verfuegbarWandWest[r]-in_Kunstwerk.getBreite()>=0);
            passtDim = passtNord | passtSued  |  passtOst | passtWest;
            ap.printLog("-Bild passt an eine Wand " + passtDim);
        }
        
        if (in_Kunstwerk.getArt()=='G')
        {
            // die Parentclass "Kunstwerk" hat nicht die Methode getLaenge, die wir aber gleich benötigen, daher casten wir
            Kunstgegenstand in_Kunstgegenstand = (Kunstgegenstand) in_Kunstwerk; 
            
            boolean passtQuerA = (verfuegbarLaengeRaum[r]-in_Kunstwerk.getBreite()>=0); //einmal quer im Raum platzieren, wobei quer beliebig aber orthoginal zu laengs definiert ist
            boolean passtQuerB = (verfuegbarBreiteRaum[r]-in_Kunstgegenstand.getLaenge()>=0);
            boolean passtQuer = passtQuerA && passtQuerB;
            boolean passtLaengsA = (verfuegbarLaengeRaum[r]-in_Kunstgegenstand.getLaenge()>=0); //einmal laengs im Raum platzieren
            boolean passtLaengsB = (verfuegbarBreiteRaum[r]-in_Kunstwerk.getBreite()>=0);
            boolean passtLaengs = passtLaengsA && passtLaengsB;
            
            passtDim = (passtQuer|passtLaengs);
            ap.printLog("-G passt in Raumbreite und Raumlänge " + passtDim);
        }
            
        if (in_Kunstwerk.getArt()=='I') 
        {
            // die Parentclass "Kunstwerk" hat nicht die Methode getLaenge, die wir aber gleich benötigen, daher casten wir
            Kunstinstallation in_Kunstinstallation = (Kunstinstallation) in_Kunstwerk;
            
            boolean passtQuerA = (verfuegbarLaengeRaum[r]-in_Kunstwerk.getBreite()>=0); //einmal quer im Raum platzieren, wobei quer orthoginal zu laengs definiert ist
            boolean passtQuerB = (verfuegbarBreiteRaum[r]-in_Kunstinstallation.getLaenge()>=0);
            boolean passtQuer = passtQuerA && passtQuerB;
            boolean passtLaengsA = (verfuegbarLaengeRaum[r]-in_Kunstinstallation.getLaenge()>=0); //einmal laengs im Raum platzieren
            boolean passtLaengsB = (verfuegbarBreiteRaum[r]-in_Kunstwerk.getBreite()>=0);
            boolean passtLaengs = passtLaengsA && passtLaengsB;
            
            passtDim = (passtQuer|passtLaengs);
            ap.printLog("-I passt in Raumbreite und Raumlänge " + passtDim);
        }
        return passtDim;
    }
        
    /**
     * Darf in den Raum noch eine I? Nein, wenn bereits irgendein KW im Raum platziert ist?
     * 
     * @param r                 Raumindex r
     * @param in_Kunstwerk      Kunstwerk
     * @return                  Ob das KW, wenn es eine I ist, noch in den Raum kann (d.h. der Raum bisher leer ist)
     */
    private Boolean passtIWirdNichtBlockert (Kunstwerk in_Kunstwerk, int r)
    {
        boolean schonAnderesKunstwerkImRaum = !denRaeumenZugeordneteKunstwerke.get(r).isEmpty(); // ! für "nicht"
        boolean passtIWirdNichtBlockert = true;
        if (in_Kunstwerk.getArt()=='I') 
        {   
            if (schonAnderesKunstwerkImRaum)
            {
                ap.printLog("- I darf nicht platziert werden, weil es schon ein KW im Raum gibt!");
                passtIWirdNichtBlockert = false;
            }
        }
        return passtIWirdNichtBlockert;
    }
    
    /**
     * Fasst das Validierungsergebnis zusammen, ob ein KW in den Raum passt.
     * 
     * @param r                 Raumindex r
     * @param in_Kunstwerk      Kunstwerk
     * @return                  Zusammengefasstes Validierungsergebnis hinsichtlich der geprüften Aspekte (passt/passt nicht)
     */
    private Boolean passtKWValidierung (Kunstwerk in_Kunstwerk, int r)
    {
        boolean validierungsErgebnis = (passtKunstwerkHoehe(in_Kunstwerk,r) & 
                                        passtIblockiertNicht(in_Kunstwerk,r) &
                                        passtKunstwerkAnWandOderInFläche(in_Kunstwerk,r) &
                                        passtIWirdNichtBlockert(in_Kunstwerk,r));
        if (validierungsErgebnis == false ){
            wieOftGabEsValidierungsprobleme ++;
        }                
        return validierungsErgebnis;
    }

    
    // ====================================================================================
    // === Methoden für die Steuerung (insbesondere auch durch die Ausstellungsplanung)
    // ====================================================================================
    
    /**
     * Die Methode prüft, ob in mindestens der Hälfte der Räume mindestens ein Kunstwerk vom Schwerpunkt platziert ist. Dann ist eine Minimallösung erreicht.
     * 
     * @return      Ob in mindestens der Hälfte der Räume mindestens ein Kunstwerk vom Schwerpunkt platziert ist
     */
    
    public boolean wurdeMinimalloesungErreicht (){
        boolean wurdeMinimalErreicht=false;
        if (wieOftWurdeSchonEinSchwerpunktKunstwertPlatziert >= raumverwaltung.anzahlHaelfteRaeume())
        {
            wurdeMinimalErreicht=true;
        }
        return wurdeMinimalErreicht;
    }
    
    /**
     * Gibt das Mapping Kunstwerke-Räume der Zuordnung aus. Dieses Mapping ist die Zuordnung im engeren Sinne und wird von der Klasse Ausgabedatei gebraucht,
     * um die Ausgabedateien zu erzeugen.
     * 
     * Erläuterung zum Mapping: die Räume werden durch die äußere Arraylist von ArrayList <ArrayList <Kunstwerk >>() dargestellt. Das heißt, hieraus ist tatsächlich nur die
     * Reihenfolge der Räume abzulesen (woraus dann in Form der inneren Arraylist aber entnommen werden kann, welche 0..n Kunstwerk-Objekte zugeordnet sind). Die Reihenfolge 
     * der Räume ist dieselbe, wie sie mit raumverwaltung.getRaumVector() vorliegt.
     * 
     * @return      Mapping Kunstwerke-Räume
     */
    public ArrayList <ArrayList <Kunstwerk >> getDenRaeumenZugeordneteKunstwerke()
    {
        return denRaeumenZugeordneteKunstwerke; // die Einträge liegen in derselben Reihenfolge vor, wie die Räume in der Raumverwaltung sortiert sind. Ein Eintrag pro Raum.
    }
    
    /**
     * Über diese Methode können die aktuell erlaubten Werte für die minimale/maximale Feuchte und Temperatur abgerufen werden. Diese liegen in Form von vier Arrays vor. Die Arrays
     * haben jeweils einen Eintrag pro Raum. Die Reihenfolge der Räume ist dieselbe, wie sie mit raumverwaltung.getRaumVector() vorliegt.
     * 
     * Es muss jeweils spezifiziert werden, welcher der vier Arrays gewünscht ist. Die Angaben werden von der Klasse Ausgabedatei gebraucht, um die Ausgabedateien zu erzeugen.
     * 
     * @param welcherAspekt      ob min oder max gewünscht ist von Temp oder von Feuchte ("minFeuchteRaum"/"maxFeuchteRaum"/"minTempRaum"/"maxTempRaum")
     * @return                   aktuell erlaubten Werte für die minimale/maximale Feuchte und Temperatur als int-Array
     */
    public int []  getRangesFeuchtenTemperaturen (String welcherAspekt)
    {
        int [] ausgabe = minFeuchteRaum;
        if (welcherAspekt.equals( "minFeuchteRaum"))
        {
            ausgabe = minFeuchteRaum;
        }
        else if (welcherAspekt.equals( "maxFeuchteRaum"))
        {
            ausgabe = maxFeuchteRaum;
        }
        else if (welcherAspekt.equals( "minTempRaum"))
        {
            ausgabe = minTempRaum;
        }
        else if (welcherAspekt.equals( "maxTempRaum"))
        {
            ausgabe = maxTempRaum;
        }
        return ausgabe; // die Einträge liegen in derselben Reihenfolge vor, wie die Räume in der Raumverwaltung sortiert sind. Ein Eintrag pro Raum.
    }
    
    /**
     * Gibt die Güte der gesamten Zuordnung.  
     * Die Güte wird mittels der Methode gueteRaumKombiniert() gebildet, die wiederum auf gueteRaumBelegung() und gueteRaumAttraktivitaet() basiert - diese Methoden sind privat.
     * 
     * @return      die Güte der gesamten Zuordnung als double
     */
    
    public double getZuordnungsGuete()
    {
        // Wir bilden den Mittelwert über die Güten der Räume
        int anzahl=0;
        double gueteSumme=0.0;
        
        for (int r=0;r<raeumeArray.length;r++){
           gueteSumme += gueteRaumKombiniert(r);
           anzahl++;
        }
        
        return gueteSumme/anzahl;
    }
    
    /**
     * Stellt einige Aspekte des Zuordnungsergebnisses als Array zusammen:<pre>
     * - mittlere Güte der Raumbelegung (basierend auf gueteRaumBelegung(r))
     * - mittlere Güte der Raumattraktivitaet (basierend auf gueteRaumAttraktivitaet(r))
     * - mittlere kombinierte Güte der Räume (basierend auf getZuordnungsGuete())
     * - das verbrauchte Budget</pre>
     * Die Ausstellungsplanung und die Zuordnungsverwaltung nutzen diese Informationen, um sie auf der Konsolde darzustellen.
     * 
     * @return      vier Aspekte des Zuordnungsergebnisses als Array von doubles
     */
    public double [] getZuordnungsErgebnisse()
    {
        double [] zuordnungsErgebnisse = new double [4];
        
        double summeMittlereGueteRaumBelegung = 0;
        double summeMittlereGueteRaumAttraktivitaet = 0;
        for (int r=0;r<raeumeArray.length;r++)
        {
            summeMittlereGueteRaumBelegung += gueteRaumBelegung(r);
            summeMittlereGueteRaumAttraktivitaet += gueteRaumAttraktivitaet(r);
        }
        
        // I führt zu Wandbelegungsgüte von 0 und solche Räume zählen daher nicht rein bei mittlerer Wandbelegung. Bei mittlerer Attraktivität zählen die Räume mit I aber: 
        zuordnungsErgebnisse[0] = summeMittlereGueteRaumBelegung / (raeumeArray.length - wieOftWurdeSchonEineInstallationPlatziert);
        zuordnungsErgebnisse[1] = summeMittlereGueteRaumAttraktivitaet / raeumeArray.length;
        
        zuordnungsErgebnisse[2] = getZuordnungsGuete();
        zuordnungsErgebnisse[3] = kostenobergrenze - restbudget; // dies ist das verbrauchte Budget (verbrauchtes Budet + restbudget = kostenobergrenze)
        
        return zuordnungsErgebnisse;
    }
    
    /**
     * Die Methode gibt einen Überblick über die Räume auf der Konsole aus, wobei sie die platzierten Kunstwerke aufführt.
     */
    
    public void ausgebenZuordnungAufKonsole()
    {
        // Zu Log- und Testzwecken die gefundene Zuordnung in die Konsole ausgeben:
        ap.printLog("\nSo sieht die Zuordnung nun aus:");
        for (int r=0;r<raeumeArray.length;r++)
        {
            ap.printLog("------------------------------------");
            ap.printLog("\n" + "# Raum " + raeumeArray[r].getNummer() );
            ap.printLog("------------------------------------");

            for(Kunstwerk kw : denRaeumenZugeordneteKunstwerke.get(r))
            {
                ap.printLog("Attraktivität: " + kw.getAttraktivitaet() + ", Thema: " + kw.getThema()+ ", Nr: " + kw.getLaufendeNummer() 
                            +", Art des Kunstwerks: " + kw.getArt() + ", Bezeichnung: " + kw.getBezeichnung() + ", Breite " + kw.getBreite());
            } 
        }    
    }
    
    /**
     * Ermittelt wie viele Kunstwerke im Rahmen der Zuordnung platziert wurden.
     * 
     * @return  wie viele Kunstwerke im Rahmen der Zuordnung platziert wurden
     */
    public int wieVieleKunstwerkePlatziert()
    {
        return kunstwerkeSchonZugeordnet.size();
    }
    
    /**
     * Gibt aus, wie oft es zu Validierungsproblemen kam, also wie oft die Validierungsmethoden das Ergebnis der KW-Verwaltung zur Suche eines passenden KW nicht bestätigt.
     * 
     * @return  wie oft es zu Validierungsproblemen kam
     */
    public int getAnzahlValidierungsprobleme()
    {
        return wieOftGabEsValidierungsprobleme; 
    }
    
    
    // ===========================================================================================================================
    // === Methoden zur Bestimmung der Güte der Raumbelegung sowie der Bedürftigkeit eines Raumes im Rahmen der Lösungserweiterung
    // ===========================================================================================================================
    /**
     * Ermittelt für einen Raum den Anteil der aktuellen Wandbelegung mit Bildern
     * 
     * @param r      der Raum als Index
     * @return       für einen Raum den Anteil der aktuellen Wandbelegung mit Bildern
     */
    private double gueteRaumBelegung(int r)
    {
        //Summe ursprüngliche Wandflächen des Raumes
        int distOrig = raeumeArray[r].showWandWest()+raeumeArray[r].showWandOst()+raeumeArray[r].showWandNord()+raeumeArray[r].showWandSued();
        //Summe der noch freien Wandflächen des Raumes
        int distNowFrei = verfuegbarWandWest[r]+verfuegbarWandOst[r]+verfuegbarWandNord[r]+verfuegbarWandSued[r];
            
        double distNowBelegt = distOrig - distNowFrei;
            
        return distNowBelegt/distOrig;
    }
    
    /**
     * Ermittelt für einen Raum den aktuellen Durchschnitt der Attraktivität der Kunstwerke. Dieser wird normiert auf Werte von 0 .. bis 1, indem
     * der Ausgangswert durch 100 geteilt wird (100 ist der maximale Attraktivitätswert).
     * 
     * @param r      der Raum als Index
     * @return       den aktuellen Durchschnitt der Attraktivität der Kunstwerke
     */
    private double gueteRaumAttraktivitaet(int r)
    {
        int anzahl=0;
        double attrSumme=0.0;
        for (Kunstwerk kw: denRaeumenZugeordneteKunstwerke.get(r)) // wir iterieren über die im Raum r platzierten Kunstwerke 
        {
           attrSumme += kw.getAttraktivitaet();
           anzahl++;
        }
        
        double mittelwert=0.0; // wenn ein Raum noch keine Kunstwerke hat, betrachten wir 0 als den Mittelwert und geben diesen zurück, ansonsten:
        if (anzahl>0){
            mittelwert=0.01*attrSumme/anzahl; //Division durch hundert, um die Skala auf 0..1 zu normieren
        }
        
        return mittelwert;
    }
    
    /**
     * Ermittelt für einen Raum den kombinierten Gütewert aus Attraktivitätsmittelwert & Anteil Wandbelegung mit Bildern. 
     * 
     * Diese kombinierte Güte kann Werte zwischen 0 und 1 annehmen, da auch die beiden Augangs-Skalen Werte von 0 bis 1 enthalten. 
     * Wir nehmen eine Qualitätsgewichtung bei der Kombination vor, weil der Attraktivitätsmittelwert ein Qualitätsaspekt ist, während
     * die Raumbelegung ein Quantitätsaspekt darstellt.
     * 
     * @param r      der Raum als Index
     * @return       für einen Raum den kombinierten Gütewert aus Attraktivitätsmittelwert & Anteil Wandbelegung mit Bildern
     * 
     */
    private double gueteRaumKombiniert(int r)
    {
        return (gueteRaumAttraktivitaet(r)*qualitaetsgewicht + gueteRaumBelegung(r)*(1-qualitaetsgewicht));
    }
    
    /**
     * Ermittelt den Raum(index) mit dem größten Optimierungsbedarf, worauf sich die Lösungserweiterung stützt. Bei Gleichstand der Bedarfs, besonders bei leeren Räumen, wird aus den Räumen
     * mit dem höchsten Bedarf ein zufälliger gezogen.
     * 
     * @return  Index des Raumes mit dem größten Optimierungsbedarf
     */
    
    private int maxBedarfIndex()
    {
        // Suche nach einem Index in raeumeBedarfWeitereKunstwerke (bzw. aufgrund unserer Datenstruktur ebenso dem raeumeArray), an dem der Bedarf maximal ist, 
        // also klassische Maximumbestimmung:
        double max = raeumeBedarfWeitereKunstwerke[0];
        int indexMaxBedarf = 0;
        for (int r = 0; r < raeumeArray.length; r++) {
          if (raeumeBedarfWeitereKunstwerke[r] > max) {
            max = raeumeBedarfWeitereKunstwerke[r];
            indexMaxBedarf = r;
            }
        }
        
        // Insbesondere wenn kein Schwerpunktthema gesetzt ist, sowie ggf. ansonsten in besonderen Fällen würden 
        // wir deterministisch vorgehen und bei jeder Lösungserweiterung dieselbe Zuordnung erreichen.
        // Um das Potential alternativer Zuordnungswege ausschöpfen zu können, führen wir an dieser Stelle ein Zufallselement ein: 
        // Sollte es kein klares Maximum geben, sonder zwei oder mehr Räume mit höchstem Wert bedürftig sein, wählen wir daraus einen zufälligen Raum.
        // Der Zufall wird bei dieser Konstruktion vor allem dann Einfluss nehmen, wenn noch alle oder einige Räume komplett leer sind.
        // Wir haben den Ansatz in einem kleinen Szenario mit 10 Zuordnungen ohne Schwerpunktthema getestet:
        // - Hier war die Güte ohne Zufall statisch bei 0.394.
        // - Mit Zufall hatten wir Gütewerte zwischen 0.393 und und 0.414. Da wir am Ende nur die beste Güte herausfiltern, ist dies durchaus eine Verbesserung.
        
        ArrayList <Integer> welcheRaeumeHabenMaximalenBedarf = new ArrayList <Integer>(); // <- jeweils auch Index des Raumes in raeumeArray! (wird z.B. in der Schleife mit 3,7,9 gefüllt)
        for (int r = 0; r < raeumeArray.length; r++) {
          if (raeumeBedarfWeitereKunstwerke[r] == raeumeBedarfWeitereKunstwerke[indexMaxBedarf]) // da wo der Bedarf maximal ist
            {
                welcheRaeumeHabenMaximalenBedarf.add((Integer)r); // wir speichern den Raum Index ab, wenn der Bedarf in diesem Raum auf höchstem Niveau ist
            }
        }
        
        // Folgendes gibt uns aus welcheRaeumeHabenMaximalenBedarf genau eine zufällige Position (siehe Erklärung im Kommentar der Methode Raumverwaltung.zufealligerLeererRaum). 
        // Es könnte im obigen Beispiel die Position 0, 1 oder 2 sein.
    
        Random rand = new Random();
        int zufaelligePositionAusWelcheRaeumeHabenMaximalenBedarf = rand.nextInt(welcheRaeumeHabenMaximalenBedarf.size());
        
        // Wir können dann nachschauen, welcher Index aus dem raeumeArrray in welcheRaeumeHabenMaximalenBedarf an dieser Position hinterlegt ist
        indexMaxBedarf = welcheRaeumeHabenMaximalenBedarf.get(zufaelligePositionAusWelcheRaeumeHabenMaximalenBedarf);
        
        return indexMaxBedarf; // <- dieser Index im raeumeArray interessiert uns, weil damit die aufrufende Methode den Raum bekommen kann (Referenz auf das Raum-Objekt)
    }
}