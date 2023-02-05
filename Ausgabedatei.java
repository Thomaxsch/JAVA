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
    * Der Pfad der Datei (bsp. C:/temp/)
    */
    private String pfad;
    
     /**
     * Array-List um Kunstwerke aus dem besten Planungsergebnis auzunehmen
     */     
    private ArrayList <ArrayList <Kunstwerk>> zugeordneteKunstwerke;
    
    /**
     * Objekt der Klasse Ausstellungsplanung, auf dessen Grundlage die Ausleih-, Ausstellungs- und Museumsführer-Datei erstellt werden können
     */ 
    private Ausstellungsplanung planung;
    
    /**
     * Objekt der Klasse Raumverwaltung, zur Verwaltung der Referenz auf die Räume in der Ausstellungsplanung
     */ 
    private Raumverwaltung raeume;
    
    /**
     * Objekt der Klasse Kunstwerkverwaltung, zur Verwaltung der Referenz auf die Kunsttwerke in der Ausstellungsplanung
     */
    private Kunstwerkverwaltung kws;
      
    /**
     * Konstruktor für Objekte der Klasse Ausgabedatei mit mehreren Eingabeparametern
     * @param pfad                     Pfadangabe unter der die Ausgabedateien im txt- und HTML-Format abgelegt werden sollen 
     * @param auszugebendeKunstwerke   Array-List der Kunstwerke aus dem besten Planungsergebnis
     * @param planung                  Refeenz auf ein Objekt der Klasse Ausstellungsplanung
     * @param raeume                   Referenz auf ein Objekt der Klasse Raumverwaltung 
     * @param kws                      Referenz auf ein Objekt der Klasse Kunstwerkverwaltung
     */
       
     public Ausgabedatei(String pfad, ArrayList <ArrayList <Kunstwerk>> auszugebendeKunstwerke, Ausstellungsplanung planung, Raumverwaltung raeume, Kunstwerkverwaltung kws) 
    {
        this.pfad = pfad;
        this.zugeordneteKunstwerke = auszugebendeKunstwerke;
        this.planung = planung;
        this.raeume = raeume;
        this.kws = kws;
    }
    
    /**
     * Erstellt eine Datei (Ausleihdatei), die nach Quelle sortiert angibt welche Kunstwerke wo ausgeliehen werden sollen, einschließlich der dabei entstehenden Kosten. 
     */
    public void schreibeAusleihen()
    {
        // eine ArrayList k wird deklariert um ihr eine Referenz auf eine ArrayList zugewiesen
        ArrayList<Kunstwerk> k = new ArrayList<Kunstwerk>();
        
        // Variable gesamtKosten vom Datentyp double wird deklariert und mit dem Wert 0.0 initialisiert
        double gesamtKosten = 0.0;
        
        // Variable kostenProMueseum vom Datentyp double wird deklariert und mit Wer 0.0 intialisiert
        double kostenProMuseum = 0.0;
        
        /* der Rückgabewert der Methode getAllePartnermuseen des Objekts kws wird dem ArrayList museen zugewiesen
         * damit werden alle in der Kunstwerkverwaltung vorhandenen Museen ermittelt, die die Grundlage für die nach Quelle (Museum) sortierte Ausleihdatei bilden
         */
        ArrayList<String> museen = kws.getAllePartnermuseen();
        
        try
        {        
            // die ArrayList zugeordneteKunstwerke wird zum bis Ende durchgelaufen
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                // in der zweiten Dimension der ArrayList sind die Kunstwerke enthalten
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    // zur einfacheren Bearbeitung für die Ausleihdaten werden die Kunstwerke in die ArrayList k mit nur einer Dimension hinzugefügt
                    k.add(zugeordneteKunstwerke.get(i).get(j));
                }
            }
            
            // txt-Ausgabe - Objekt meinWriter der Klasse BufferedWriter für txt-Ausgaben wird deklariert und eine Referenz auf ein Objekt der Klasse BufferedWriter zugewiesen
            BufferedWriter meinWriter = erstelleAusgabeStrom(pfad, "ausleihdatei.txt");          
            
            // txt-Ausgabe - mehrere Zeichen werden in den gepufferten Augabestrrom geschrieben
            meinWriter.write("------------------------------------------\n");
            meinWriter.write("Auszuleihende Kunstwerke                  \n");
            meinWriter.write("------------------------------------------\n\n");
            
            // HTML-Ausgabe - Objekt sb der Klasse StringBuilder für HTML-Ausgaben wird deklariert und eine Referenz auf ein Objekt der Klasse StringBuilder zugewiesen
            StringBuilder sb = new StringBuilder();
            
            // HTML-Ausgabe - mit der Methode append werden im weiteren Verlauf mehrere Strings aneinandergehängt
            sb.append("<html>");
            sb.append("<h1>Auszuleihende Kunstwerke (inkl. Gesamtkosten)</h1>");
            
            // in der äußeren Schleife werden alle Museen aus der ArrayList museen durchlaufen            
            for(int i=0; i<museen.size(); i++)
            {
                // Kosten pro Museum müssen beim ersten/erneuten Durchlauf mit neuem Museum auf den Wert 0.0 gesetzt werden
                kostenProMuseum = 0.0;
                
                // txt-Ausgabe - mehrere Zeichen werden in den gepufferten Augabestrom geschrieben, u.a. der Name des Museums
                meinWriter.write("------------------------------------------\n");
                meinWriter.write(museen.get(i) + "\n");
                meinWriter.write("------------------------------------------\n");
                
                // HTML-Ausgabe - Ausgabe des Museums
                sb.append("<h3>" + museen.get(i) + "</h3>");
                sb.append("<table width='100%' border='1px'");
                sb.append("<tr><td>Nr.</td><td>Beschreibung des Kunstwerks</td><td>Kosten</td>");
                
                // in der inneren Schleife werden alle Kunstwerke durchlaufen, die ausgeliehen werden sollen
                for(int j=0; j < k.size(); j++)
                {
                    // Prüfung im If-Zweig ob das dem Kunstwerk zugeordnete Museum dem Museum aus der äußeren Schleife entspricht
                    if(museen.get(i).equals(k.get(j).getVerleihendesMuseum()))
                    {
                        // txt-Datei - sind beide Museen identisch, dann wird das Kunstwerk mit der Methode write in den Ausgabestrom geschrieben.
                        meinWriter.write(k.get(j).outputAusleihdatei() + "\n");
                        
                        // HTML-Ausgabe der String-Repräsentation des jeweiligen Kunstwerks 
                        sb.append("<tr><td>" + (j+1) + "</td>");
                        sb.append("<td>" + k.get(j).toString() + "</td>");
                        
                        // HTML-Ausgabe der Kosten des jeweiligen Kunstwerks durch Aufruf Methode getKosten
                        sb.append("<td>" + legeWaehrungsFormatFest(k.get(j).getKosten()) + "</td></tr>");
                        
                        // Aufruf der Methode getKosten des jeweiligen Kunstwerks und Addition der Ausleihkosten des Kunstwerks zu den Kosten des jeweiligen Museum
                        kostenProMuseum += k.get(j).getKosten();
                        
                        // Aufruf der Methode getKosten des jeweiligen Kunstwerks und Addition der Ausleihkosten des Kunstwerks zu den Gesamtkosten
                        gesamtKosten += k.get(j).getKosten();
                    }
                }
                
                // txt-Ausgabe - Ausgabe der Kosten pro Museum mit Formatierung im Währungsformat Euro
                meinWriter.write("------------------------------------------\n");
                meinWriter.write("Kosten pro Museum: " + legeWaehrungsFormatFest(kostenProMuseum) + "\n\n");
                
                // HTML-Ausgabe - Ausgabe der Kosten pro Museum mit Formatierung im Währungsformat Euro
                sb.append("<tr><td colspan='2'>Kosten pro Museum</td>");
                sb.append("<td>" + legeWaehrungsFormatFest(kostenProMuseum) + "</td></tr>");
                sb.append("</table>");
            }
            
            // txt-Ausgabe - mehrere Zeichen werden in den gepufferten Augabestrom geschrieben, u.a. die gesamten Ausleihkosten
            meinWriter.write("--------------------------------------------\n");
            meinWriter.write("Gesamtkosten: " + legeWaehrungsFormatFest(gesamtKosten));
            
            // txt-Ausgabe - Ausgabestrom wird geschlossen
            meinWriter.close();
            
            // HTML-Ausgabe - Methodenaufruf zum Schreiben der HTML-Ausgabe
            schreibeHTMLAusgabe(sb, pfad, "ausleihdaten.html");
        }
        catch(IOException e)
        {
            System.out.println("Beim Schreiben in die Datei ist etwas schief gegangen.");
        }
        
    }
    
    /**
     * Erstellt eine Datei (Raumdatei), die nach Räumen aufgegliedertauflistet, welches Kunstwerk wo ausgestellt werden soll. 
     */
    public void schreibeAusstellungen()
    {
        try
        {
            // txt-Ausgabe - Objekt meinWriter der Klasse BufferedWriter für txt-Ausgaben wird deklariert und eine Referenz auf ein Objekt der Klasse BufferedWriter zugewiesen
            BufferedWriter meinWriter = erstelleAusgabeStrom(pfad, "raumdatei.txt");  
            
           // HTML-Ausgabe - Objekt sb der Klasse StringBuilder für HTML-Ausgaben wird deklariert und eine Referenz auf ein Objekt der Klasse StringBuilder zugewiesen
            StringBuilder sb = new StringBuilder();
            
            // HTML-Ausgabe - mit der Methode append werde im weiteren Verlauf mehrere Strings aneinandergehängt
            sb.append("<html>");
            sb.append("<h1>Auflistung nach Räumen (zur Aufstellung der Kunstwerke für Museumsmitarbeiter)</h1>");
            
            // erste Dimension der ArrayList zugeordneteKunstwerke wird bis zu deren Ende durchlaufen, entspricht den Räumen
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                /* txt-Ausgabe die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                 * Ausgabe der minimalen und maximalen Raumtemperatur und Luftfeuchte für den jeweiligen Raum auf Grundlage des Planungsergebnisses
                 */
                meinWriter.write("-------------------------------------------------------------------------------------------------------\n");
                meinWriter.write("Raum: " + raeume.getRaum(i).toString() + "\n");
                meinWriter.write("Minimale Raumtemperatur: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("minTempRaum")[i] + "\n");
                meinWriter.write("Maximale Raumtemperatur: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("maxTempRaum")[i] + "\n");
                meinWriter.write("Minimale Luftfeuchte: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("minFeuchteRaum")[i] + "\n");
                meinWriter.write("Maximale Luftfeuchte: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("maxFeuchteRaum")[i] + "\n");
                meinWriter.write("-------------------------------------------------------------------------------------------------------\n");
                
                // HTML-Ausgabbe - für jeden Raum wird eine String-Repräsentation durch Aufruf der Methode toString ausgegeben
                sb.append("<h1>Raum: " + raeume.getRaum(i).toString() + "</h1>");
                
                /* HTML-Ausgabe - Strings werden mit der Methode append an das StringBuilder-Objekt sb angehängt
                 * Ausgabe der minimalen und maximalen Raumtemperatur und Luftfeuchte für den jeweiligen Raum auf Grundlage des Planungsergebnisses
                 */
                sb.append("Minimale Raumtemperatur: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("minTempRaum")[i] + "</br>");
                sb.append("Maximale Raumtemperatur: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("maxTempRaum")[i] + "</br>");
                sb.append("Minimale Luftfeuchte: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("minFeuchteRaum")[i] + "</br>");
                sb.append("Maximale Luftfeuchte: " + planung.getBestesMappingErlaubteFeuchtenTemperaturen("maxFeuchteRaum")[i] + "</br></br/>");
                
                // HTML-Ausgabe - Erstellung einer Tabelle in der die Kunstwerke für den aktuellen Raum dargestellt werden
                sb.append("<table border='1px' width='100%'>");
                sb.append("<tr><td>Nr.</td>");
                sb.append("<td>Kunstwerk</td></tr>");
                
                // zweite Dimension der Arraylist wird bis zum Ende durchlaufen,enthält die einzelnen Kunstwerke deren String-Repräsentation als Byte in den Ausgabestrom geschrieben wird
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    // txt-Ausgabe - String-Repräsentationen der einzelnen Kunstwerke werden als Byte in den Ausgabestrom geschrieben wird
                    meinWriter.write(zugeordneteKunstwerke.get(i).get(j).outputRaumdatei() + "\n");
                    
                    // HTML-Ausgabe der String-Repräsentation eines Kunstwerks
                    sb.append("<tr>");
                    sb.append("<td>" + (j+1) + "</td>");
                    sb.append("<td>" + zugeordneteKunstwerke.get(i).get(j).outputRaumdatei() + "</td>");
                    sb.append("</tr>");
                }
                
                sb.append("</table></html>");
                meinWriter.write("\n");
            }
            
            // txt-Ausgabe - Ausgabepuffer wird durch die Methode close geschlossen
            meinWriter.close();
  
            // HTML-Ausgabe - Methodenaufruf zum Schreiben der HTML-Ausgabe
            schreibeHTMLAusgabe(sb, pfad, "raumdatei.html");
        }
        catch(IOException e)
        {
            System.out.println("Beim Schreiben in die Datei ist etwas schief gegangen.");
        }
    
    }
    
    /**
     * Erstellt eine txt- und HTML-Datei, die für die Besucher eine kurze Übersicht zu den Räumen und wichtigsten Kunstwerken enthält. 
     */
    public void schreibeMuseumsfuehrer()
    {
        try
        {           
            // txt-Ausgabe - Objekt meinWriter der Klasse BufferedWriter für txt-Ausgaben wird deklariert und eine Referenz auf ein Objekt der Klasse BufferedWriter zugewiesen
            BufferedWriter meinWriter = erstelleAusgabeStrom(pfad, "museumsfuehrer.txt"); 
            
            // HTML-Ausgabe - Objekt sb der Klasse StringBuilder für HTML-Ausgaben wird deklariert und eine Referenz auf ein Objekt der Klasse StringBuilder zugewiesen
            StringBuilder sb = new StringBuilder();
            
            // HTML-Ausgabe - Strings werden mit der Methode append an das StringBuilder-Objekt sb angehängt
            sb.append("<html>");
            sb.append("<h1>Museumsführer</h1>");
            
            // erste Dimension der ArrayList zugeordneteKunstwerke wird bis zu deren Ende durchlaufen, entspricht den Räumen
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                // txt-Ausgabe des aktuellen Raums - die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                meinWriter.write("------------------------------------------    \n");
                meinWriter.write("Raum: " + raeume.getRaum(i).toStringKurz() + "\n");
                meinWriter.write("------------------------------------------    \n");
                
                // HTML-Ausgabe der String-Repräsentation des Raums 
                sb.append("<h1>Raum: " + raeume.getRaum(i).toStringKurz() + "</h1>");
                
                // HTML-Ausgabe - Erstellung einer Tabelle in der die Kunstwerke für den aktuellen Raum dargestellt werden
                sb.append("<table border='1px' width='100%'>");
                sb.append("<tr><td>Nr.</td>");
                sb.append("<td>Kunstwerk</td></tr>");
                
                // zweite Dimension der Arraylist mit den Kunstwerken wird bis zum Ende durchlaufen
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    // txt-Ausgabe - String-Repräsentationen der einzelnen Kunstwerke werden als Byte in den Ausgabestrom geschrieben wird
                    meinWriter.write(zugeordneteKunstwerke.get(i).get(j).outputMuseumsführer() + "\n");
                    
                    // HTML-Ausgabe der String-Repräsentation eines Kunstwerks
                    sb.append("<tr>");
                    sb.append("<td>" + (j+1) + "</td>");
                    sb.append("<td>" + zugeordneteKunstwerke.get(i).get(j).outputMuseumsführer() + "</td>");
                    sb.append("</tr>");
                }
            
                sb.append("</table></html>");
                meinWriter.write("\n");
            }
    
            // txt-Ausgabe - Ausgabepuffer wird durch die Methode close geschlossen
            meinWriter.close();
            
            // HTML-Ausgabe - Methodenaufruf zum Schreiben der HTML-Ausgabe
            schreibeHTMLAusgabe(sb, pfad, "museumsfuehrer.html");
        }
        catch(IOException e)
        {
            System.out.println("Beim Schreiben in die Datei ist etwas schief gegangen.");
        }
    }
        
    /**
     * Methode, die ein Objekt der Klasse StringBuilder sowie einen Dateipfad und Dateinamen entgegennimmt um eine HTML-Ausgabe zu erzeugen
     * @param sb        Objekt der Klasse StringBuilder das in eine HTML-Datei geschrieben werden soll
     * @param pfad      Pfad für die Ausgabe der HTML-Datei, z.B. C:\temp\
     * @param dateiname Name der Datei die in den angegebeen Pfad geschrieben werden soll
     */
    private void schreibeHTMLAusgabe(StringBuilder sb, String pfad, String dateiname)
    {
        // Try-/Catch-Block um Ausnahmen beim Schreiben der HTML-Datei abzufangen
        try
        {
            /* Objekt fstream der Klasse FileWriter wird deklariert und eine Instanz der Klasse FileWriter mit new zugewiesen
             * bei der Instanzierung wird dem Konstrukor der Klasse FileWriter als Eingabeparameter der Name Pfad und der Dateiname übergeben
             */ 
            FileWriter fstream = new FileWriter(pfad + dateiname);
            
            /* Objekt out der Klasse BufferedWriter wird deklariert und eine neue Instanz der Klasse BufferedWriter zugewiesen
             * bei der Instanzierung wird dem Konstruktor der Klasse BufferedWriter als Eingabeparameter das Objekt fstream übergeben 
             */
            BufferedWriter out = new BufferedWriter(fstream);
            
            // mit der Methode write der Klasse BufferedWriter werden die Daten gepuffert in die HTML-Datei geschrieben
            out.write(sb.toString());
            
            // gepufferter Ausgabestrom wird mit der Methode close der Klasse BufferedWriter geschlossen
            out.close();
        }
        catch(IOException e)
        {
            System.out.println("Beim Schreiben in die Datei ist etwas schief gegangen. Bitte probieren Sie es erneut.");
        }
        
    }
    
    /**
     * Methode um einen Ausgabestrom für die txt-Ausgabe zu erstellen
     * @param pfad        Pfad in der die Ausgabedatei geschrieben werden soll
     * @param dateiname   Dateiname der txt-Datei
     * @return meinWriter gepufferter Ausgabestrom zum Schreiben der Datei
     */
    private BufferedWriter erstelleAusgabeStrom(String pfad, String dateiname)
    {
        try
        {
            BufferedWriter meinWriter = new BufferedWriter(new FileWriter(pfad + dateiname));
            return meinWriter;
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim Erstellen der zu schreibenden Datei");
            return null;
        }
    }
    
    /**
     * Methode, die das Währungsformat für die verschiedenen Ausgabedateien und -formate festlegt 
     * @param  betrag Betrag der formatiert werden soll
     * @return formatierter Betrag als String
     */
    private String legeWaehrungsFormatFest(double betrag)
    {
        /* Deklaration des Objekts locale der Klasse Locale und Zuweisung einer neuen Referenz auf ein Objekt locale
         * Konstruktor der Klasse Locale bekommt als ersten Eingabeparameter die Sprache "de" nach ISO 693 und als zweiten Eingabeparameter das Land "DE nach ISO-3166-Standard
         */
        Locale locale = new Locale("de", "DE");
        
        /* Deklaration des Objekts waehrung der Klasse NumberFormat
         * Aufruf der statischen Methode getCurrencyInstance und Übergabe des Objekts locale, 
         * Währungsformat Euro wird als Rückgabewert in der Variablen waherung gespeichert 
         */
        NumberFormat waehrung = NumberFormat.getCurrencyInstance(locale);
        
        /* Aufruf der Methode format des Objekts waehrung und Übergabe des zu formatierenden Betrags an die Methode format
         * Rückgabe des formatierten Strings an die Aufrufstelle der Methode 
         */
        return waehrung.format(betrag);
    }
}
