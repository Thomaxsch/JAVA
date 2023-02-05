import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Die Klasse Ausgabe ist für die Ausgabe der geforderten Dateien verantwortlich und erzeugt eine txt- und HTML-Ausgabe.
 * 
 * @author Mischa Paul Marchlewski
 * @version 05.02.2023
 */
public class Ausgabedatei
{
    /**
    * Der Name der Datei mit Pfadinformationen (bsp. C:/temp/datei.txt)
    */
    private String file;
    
    /**
    * Der Pfad der Datei (bsp. C:/temp/)
    */
    private String pfad;
    
    /**
     * Objekt der Klasse Ausstellungsplanung, auf dessen Grundlage die Ausleih-, Ausstellungs- und Museumsführer-Datei erstellt werden können
     */ 
    private Ausstellungsplanung planung;
    
    /**
     * Array-List der Kunstwerke aus dem besten Planungsergebnis
     */     
    private ArrayList <ArrayList <Kunstwerk>> zugeordneteKunstwerke;
    
    private Raumverwaltung raeume;
    private Kunstwerkverwaltung kws;
      
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
     * @param file                     Dateiname für die zu erstellende Outputdatei 
     * @param auszugebendeKunstwerke   Array-List der Kunstwerke aus dem besten Planungsergebnis
     * @param planung                  Objekt der Klasse Ausstellungsplanung
     * @param raeume                   in der Ausstellungsplanung verwendete Räume
     */
    /*public Ausgabedatei(String file, ArrayList <ArrayList <Kunstwerk>> auszugebendeKunstwerke, Ausstellungsplanung planung, Raumverwaltung raeume, Kunstwerkverwaltung kws) 
    {
        this.file = file;
        this.zugeordneteKunstwerke = auszugebendeKunstwerke;
        this.raeume = raeume;
        this.planung = planung;
        this.kws = kws;
    }*/
    
     public Ausgabedatei(String pfad, ArrayList <ArrayList <Kunstwerk>> auszugebendeKunstwerke, Ausstellungsplanung planung, Raumverwaltung raeume, Kunstwerkverwaltung kws) 
    {
        this.pfad = pfad;
        this.zugeordneteKunstwerke = auszugebendeKunstwerke;
        this.raeume = raeume;
        this.planung = planung;
        this.kws = kws;
    }
    
    /**
     * Erstellt eine Datei (Ausleihdatei), die nach Quelle sortiert angibt 
     * welche Kunstwerke wo ausgeliehen werden sollen, einschließlich der 
     * dabei entstehenden Kosten. Die Methode nutzt dazu die Ausstellungs-
     * planung
     */
    public void schreibeAusleihen()
    {
        // eine ArrayList k wird deklariert und ihr eine Referenz auf eine neun initialisierte ArrayList zugewiesen
        ArrayList<Kunstwerk> k = new ArrayList<Kunstwerk>();
        
        // Variable gesamtKosten vom Datentyp double wird deklariert und mit dem Wert 0.0 initialisiert
        double gesamtKosten = 0.0;
        
        // Variable kostenProMueseum vom Datentyp double wird deklariert 
        double kostenProMuseum;
        
        Locale locale = new Locale("de", "DE");
        NumberFormat waehrung = NumberFormat.getCurrencyInstance(locale);
        
        // der Rückgabewert der Methode getAllePartnermuseen wird dem String-Array museen zugewiesen
        ArrayList<String> museen = kws.getAllePartnermuseen();
        
        try
        {        
            // die ArrayList zugeordneteKunstwerke wird zum bis Ende durchgelaufen
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                // in der zweiten Dimension der ArrayList sind die Kunstwerke enthalten
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    // zur einfacheren Bearbeitung werden die Kunstwerke in die ArrayList k mit nur einer Dimension hinzugefügt
                    k.add(zugeordneteKunstwerke.get(i).get(j));
                }
            }
            
            // Objekt meinWriter der Klasse BufferedWriter (gepufferter Ausgabestrom) wird deklariert
            // dem Objekt wird eine Referenz auf ein initialisiertes Objekt der Klasse BufferedWriter, dazu wird dem Konstruktor als Eingabeparameter ein Objekt der Klasse FileWriter übergeben,
            // der wiederum ein als Eingabeparameter eine String übergeben wird, der den Namen für die Auleihdaten widerspiegelt
            BufferedWriter meinWriter = new BufferedWriter(new FileWriter(pfad + "ausleihdatei.txt"));
            
            // mehrere Zeichen werden in den gepufferten Augabestrrom geschrieben
            meinWriter.write("------------------------------------------\n");
            meinWriter.write("Auszuleihende Kunstwerke                  \n");
            meinWriter.write("------------------------------------------\n\n");
            
            // in der äußeren Schleife werden alle Museen aus der ArrayList museen durchlaufen            
            for(int i=0; i<museen.size(); i++)
            {
                // Kosten pro Museum müssen beim ersten Durchlauf mit neuem Museum auf den Wert 0.0 gesetzt werden
                kostenProMuseum = 0.0;
                
                // mehrere Zeichen werden in den gepufferten Augabestrom geschrieben, u.a. der Name des Museums
                meinWriter.write("------------------------------------------\n");
                meinWriter.write(museen.get(i) + "\n");
                meinWriter.write("------------------------------------------\n");
                
                // in der inneren Schleife werden alle Kunstwerke durchlaufen, die ausgeliehen werden sollen
                for(int j=0; j < k.size(); j++)
                {
                    // Prüfung im If-Zweig ob das dem Kunstwerk zugeordnete Museum dem Museum aus der äußeren Schleife entspricht
                    if(museen.get(i).equals(k.get(j).getVerleihendesMuseum()))
                    {
                        // sind beide Museen identisch, dann wird das Kunstwerk mit der Methode write in den Ausgabestrom geschrieben.
                        meinWriter.write(k.get(j).outputAusleihdatei() + "\n");
                        
                        // Aufruf der Methode kostenProMuseum des jeweiligen Kunstwerks und Addition der Ausleihkosten des Kunstwerks zu den Kosten des jeweiligen Museum
                        kostenProMuseum += k.get(j).getKosten();
                        
                        // Aufruf der Methode getKosten des jeweiligen Kunstwerks und Addition der Ausleihkosten des Kunstwerks zu den Gesamtkosten
                        gesamtKosten += k.get(j).getKosten();
                    }
                }
                
                // Ausgabe der Kosten pro Museum mit Formatierung im Währungsformat Euro
                meinWriter.write("------------------------------------------\n");
                meinWriter.write("Kosten pro Museum: " + waehrung.format(kostenProMuseum) + "\n\n");
            }
            
            // mehrere Zeichen werden in den gepufferten Augabestrom geschrieben, u.a. die gesamten Ausleihkosten
            meinWriter.write("--------------------------------------------\n");
            meinWriter.write("Gesamtkosten: " + waehrung.format(gesamtKosten));
            
            // Ausgabestrom wird geschlossen
            meinWriter.close();
            
            // Ende Textausgabe
            // Beginn HTML-Ausgabe
            
            gesamtKosten = 0;
            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            sb.append("<h1>Auszuleihende Kunstwerke (inkl. Gesamtkosten)</h1>");
            
            // in der äußeren Schleife werden alle Museen aus der ArrayList museen durchlaufen            
            for(int i=0; i<museen.size(); i++)
            {
                // Kosten pro Museum müssen beim ersten Durchlauf mit neuem Museum auf den Wert 0.0 gesetzt werden
                kostenProMuseum = 0.0;
                
                // Ausgabe des Museums
                sb.append("<h3>" + museen.get(i) + "</h3>");
                sb.append("<table width='100%' border='1px'");
                sb.append("<tr><td>Nr.</td><td>Beschreibung des Kunstwerks</td><td>Kosten</td>");
                
                for(int j=0; j < k.size(); j++)
                {                   
                    // Prüfung im If-Zweig ob das dem Kunstwerk zugeordnete Museum dem Museum aus der äußeren Schleife entspricht
                    if(museen.get(i).equals(k.get(j).getVerleihendesMuseum()))
                    {
                        sb.append("<tr><td>" + (j+1) + "</td>");
                        sb.append("<td>" + k.get(j).toString() + "</td>");
                        sb.append("<td>" + waehrung.format(k.get(j).getKosten()) + "</td></tr>");
                        
                        // Aufruf der Methode kostenProMuseum des jeweiligen Kunstwerks und Addition der Ausleihkosten des Kunstwerks zu den Kosten des jeweiligen Museum
                        kostenProMuseum += k.get(j).getKosten();
                        
                        // Aufruf der Methode getKosten des jeweiligen Kunstwerks und Addition der Ausleihkosten des Kunstwerks zu den Gesamtkosten
                        gesamtKosten += k.get(j).getKosten(); 
                    }
                }
                
                sb.append("<tr><td colspan='2'>Kosten pro Museum</td>");
                sb.append("<td>" + waehrung.format(kostenProMuseum) + "</td></tr>");
                sb.append("</table>");
            }
            
            
            //sb.append("<tr><td colspan='2'>Gesamtkosten</td>");
            //sb.append("<td>" + waehrung.format(gesamtKosten) + "</td></tr>");
            //sb.append("</table>");
            
            FileWriter fstream = new FileWriter(pfad + "ausleihdatei.html");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(sb.toString());
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("Beim Schreiben in die Datei ist etwas schief gegangen.");
        }
        
    }
    
    /**
     * Erstellt eine Datei (Raumdatei), die nach Räumen aufgegliedert
     * auflistet, welches Kunstwerk wo ausgestellt werden soll. Sie nutzt dazu
     * das Array zugeordneteKunstwerke.
     */
    public void schreibeAusstellungen()
    {
        try
        {
            // Beginn Ausgabe in Textdatei 
            // Objekt meinWriter der Klasse BufferedWriter zur Ausgabepufferung, und anonymes File-WriteWriter-Objekt zur eigentlichen Ausgabe
            BufferedWriter meinWriter = new BufferedWriter(new FileWriter(pfad + "raumdatei.txt"));
            
            // erste Dimension der ArrayList zugeordneteKunstwerke wird bis zu deren Ende durchlaufen, entspricht den Räumen
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                //die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                meinWriter.write("-------------------------------------------------------------------------------------------------------\n");
                meinWriter.write("Raum: " + raeume.getRaum(i).toString() + "\n");
                meinWriter.write("Minimale Raumtemperatur: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("minTempRaum")[i] + "\n");
                meinWriter.write("Maximale Raumtemperatur: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("maxTempRaum")[i] + "\n");
                meinWriter.write("Minimale Luftfeuchte: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("minFeuchteRaum")[i] + "\n");
                meinWriter.write("Maximale Luftfeuchte: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("maxFeuchteRaum")[i] + "\n");
                meinWriter.write("-------------------------------------------------------------------------------------------------------\n");
                
                // zweite Dimension der Arraylist wird bis zum Ende durchlaufen,enthält die einzelnen Kunstwerke deren String-Repräsentation als Byte in den Ausgabestrom geschrieben wird
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    meinWriter.write(zugeordneteKunstwerke.get(i).get(j).outputRaumdatei() + "\n");
                }
                
                meinWriter.write("\n");
            }
            
            // Ausgabepuffer wird durch die Methode close geschlossen
            meinWriter.close();
            
            // Ende Ausgabe in Textdatei
            // Beginn Ausgabe in HTML-Datei            
            
            // Objekt sb der Klasse StringBuilder wird deklariert und Generierung einer neuen Instanz der Klasse StringBuilder mit new, dier Objektvariablen sb zugewiesen wird
            StringBuilder sb = new StringBuilder();
            
            // mit der Methode append werde im weiteren Verlauf mehrere Strings aneinandergehängt
            sb.append("<html>");
            sb.append("<h1>Auflistung nach Räumen (zur Aufstellung der Kunstwerke für Museumsmitarbeiter)</h1>");
            
            // in der äußeren For-Schleife wird das Array zugeordneteKunstwerke durchlaufen, entspricht der Anzahl der Räume
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                // für jeden Raum wird eine String-Repräsentation durch Aufruf der Methode toString ausgegeben
                sb.append("<h1>Raum: " + raeume.getRaum(i).toString() + "</h1>");
                sb.append("Minimale Raumtemperatur: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("minTempRaum")[i] + "</br>");
                sb.append("Maximale Raumtemperatur: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("maxTempRaum")[i] + "</br>");
                sb.append("Minimale Luftfeuchte: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("minFeuchteRaum")[i] + "</br>");
                sb.append("Maximale Luftfeuchte: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("maxFeuchteRaum")[i] + "</br></br/>");
                sb.append("<table border='1px' width='100%'>");
                sb.append("<tr><td>Nr.</td>");
                sb.append("<td>Kunstwerk</td></tr>");
                
                // in der inneren For-Schleife werden alle einem Raum zugeordneten Kunstwerke durchlaufen
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    // String-Repräsentation der Kunstwerke erfolgt mit der Methode 
                    sb.append("<tr>");
                    sb.append("<td>" + (j+1) + "</td>");
                    sb.append("<td>" + zugeordneteKunstwerke.get(i).get(j).outputRaumdatei() + "</td>");
                    sb.append("</tr>");
                }
                
                sb.append("</table>");
                
            }
            
            sb.append("</html>");
            
            // Objekt fstream der Klasse FileWriter wird deklariert und eine Instanz der Klasse FileWriter mit new zugewiesen
            // bei der Instanzierung wird als Eingabeparameter der Name der HTML-Datei als String übergeben
            FileWriter fstream = new FileWriter(pfad + "raumdatei.html");
            
            // Objekt out der Klasse BufferedWriter wird deklariert und eine neue Instanz der Klasse BufferedWriter zugewiesen
            // bei der Instanzierung wird als Eingabeparameter das Objekt fstream übergeben
            BufferedWriter out = new BufferedWriter(fstream);
            
            // mit der Methode write werden die Daten in die HTML-Datei geschrieben
            out.write(sb.toString());
            
            // gepufferter Ausgabestrom wird mit der Methode close geschlossen
            out.close();
            
            // Ende Ausgabe in HTML-Datei
        }
        catch(IOException e)
        {
            System.out.println("Beim Schreiben in die Datei ist etwas schief gegangen.");
        }
    
    }
    
    /**
     * Erstellt eine Datei, die für die Besucher eine kurze Übersicht zu
     * den Räumen und wichtigsten Kunstwerken enthält. Sie nutzt dazu die 
     * Ausstellungsplanung.
     */
    public void schreibeMuseumsfuehrer()
    {
         try
        {
            // Objekt meinWriter der Klasse BufferedWriter zur Ausgabepufferung, und anonymes File-WriteWriter-Objekt zur eigentlichen Ausgabe
            BufferedWriter meinWriter = new BufferedWriter(new FileWriter(pfad + "museumsfuehrer.txt"));
            
            // erste Dimension der ArrayList zugeordneteKunstwerke wird bis zu deren Ende durchlaufen, entspricht den Räumen
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                //die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                meinWriter.write("------------------------------------------\n");
                meinWriter.write("Raum: " + raeume.getRaum(i).toStringKurz() + "\n");
                meinWriter.write("------------------------------------------\n");
                
                // zweite Dimension der Arraylist wird bis zum Ende durchlaufen,enthält die einzelnen Kunstwerke deren String-Repräsentation als Byte in den Ausgabestrom geschrieben wird
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    meinWriter.write(zugeordneteKunstwerke.get(i).get(j).outputMuseumsführer() + "\n");
                }
                
                meinWriter.write("\n");
            }
    
            // Ausgabepuffer wird durch die Methode close geschlossen
            meinWriter.close();
                      
            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            sb.append("<h1>Museumsführer</h1>");
                    
              for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                //die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                sb.append("<h1>Raum: " + raeume.getRaum(i).toStringKurz() + "</h1>");
                sb.append("<table border='1px' width='100%'>");
                sb.append("<tr><td>Nr.</td>");
                sb.append("<td>Kunstwerk</td></tr>");
                
                // zweite Dimension der Arraylist wird bis zum Ende durchlaufen,enthält die einzelnen Kunstwerke deren String-Repräsentation als Byte in den Ausgabestrom geschrieben wird
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    sb.append("<tr>");
                    sb.append("<td>" + (j+1) + "</td>");
                    sb.append("<td>" + zugeordneteKunstwerke.get(i).get(j).outputMuseumsführer() + "</td>");
                    sb.append("</tr>");
                }
                
                sb.append("</table>");
            }
            
            sb.append("</html>");
            FileWriter fstream = new FileWriter(pfad + "museumsfuehrer.html");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(sb.toString());
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("Beim Schreiben in die Datei ist etwas schief gegangen.");
        }
    }
}
