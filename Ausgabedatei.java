import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Die Klasse Datei kann Strings als Zeilen in einer Datei ausgeben.
 * 
 * @author Mischa Paul Marchlewski
 * @version 19.12.2022
 */
public class Ausgabedatei
{
    /**
    * Der Name der Datei mit Pfadinformationen (bsp. C:/temp/datei.txt)
    */
    private String file;
    /**
     * Objekt der Klasse Ausstellungsplanung, auf dessen Grundlage die Ausleih-, Ausstellungs- und Museumsführer-Datei erstellt werden können
     */
    // private Ausstellungsplanung planung;
       
    private ArrayList <ArrayList <Kunstwerk>> zugeordneteKunstwerke;
    /**
     * Objekt der Java-Klasse PrintWriter zum Schreiben von Zeichenketten
     */
    
    private Raumverwaltung raeume;
    
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
     * @param auszugebendeKunstwerke   HashMap mit den Räumen und Objekten die ausgegeben werden sollen
     */
    public Ausgabedatei(String file, ArrayList <ArrayList <Kunstwerk>> auszugebendeKunstwerke, Raumverwaltung raeume) 
    {
        this.file = file;
        this.zugeordneteKunstwerke = auszugebendeKunstwerke;
        this.raeume = raeume;
    }
    
    /**
     * Erstellt eine Datei (Ausleihdatei), die nach Quelle sortiert angibt 
     * welche Kunstwerke wo ausgeliehen werden sollen, einschließlich der 
     * dabei entstehenden Kosten. Die Methode nutzt dazu die Ausstellungs-
     * planung
     */
    public void schreibeAusleihen()
    {
        ArrayList<Kunstwerk> k = new ArrayList<Kunstwerk>();
        double gesamtKosten = 0.0;
        
        try
        {          
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    k.add(zugeordneteKunstwerke.get(i).get(j));
                }
            }
            
            BufferedWriter meinWriter = new BufferedWriter(new FileWriter(file));
            
            meinWriter.write("------------------------------------------\n");
            meinWriter.write("Auszuleihende Kunstwerke                  \n");
            meinWriter.write("------------------------------------------\n");
            
            for(int i=0; i < k.size(); i++)
            {
                meinWriter.write(k.get(i).toString() + "\n");
                gesamtKosten += k.get(i).getKosten();
            }
            
            meinWriter.write("------------------------------------------\n");
            Locale locale = new Locale("de", "DE");
            NumberFormat waehrung = NumberFormat.getCurrencyInstance(locale);
            meinWriter.write("Gesamtkosten: " + waehrung.format(gesamtKosten));
            meinWriter.write("------------------------------------------\n");
            
            meinWriter.close();
            
            gesamtKosten = 0;
            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            sb.append("<h1>Auszuleihende Kunstwerke (inkl. Gesamtkosten)</h1>");
            sb.append("<table border='1px' width='100%'>");
            sb.append("<tr><td>Nr.</td>");
            sb.append("<td>Kunstwerk</td>");
            sb.append("<td>Kosten</td></tr>");                
            
            for(int i=0; i < k.size(); i++)
            {
                sb.append("<tr><td>" + (i+1) + "</td>");
                sb.append("<td>" + k.get(i).toString() + "</td>");
                sb.append("<td>" + waehrung.format(k.get(i).getKosten()) + "</td></tr>");
                gesamtKosten += k.get(i).getKosten();
            }
            sb.append("<tr><td colspan='2'>Gesamtkosten</td>");
            sb.append("<td>" + waehrung.format(gesamtKosten) + "</td></tr>");
            sb.append("</table>");
            
            FileWriter fstream = new FileWriter("ausleihdatei.html");
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
     * Erstellt eine Datei (Ausstellungsdatei), die nach Räumen aufgegliedert
     * auflistet,welches Kunstwerk wo ausgestellt werden soll. Sie nutzt dazu
     * die Ausstellungsplanung.
     */
    public void schreibeAusstellungen()
    {
        try
        {
            // Objekt meinWriter der Klasse BufferedWriter zur Ausgabepufferung, und anonymes File-WriteWriter-Objekt zur eigentlichen Ausgabe
            BufferedWriter meinWriter = new BufferedWriter(new FileWriter(file));
            
            // erste Dimension der ArrayList zugeordneteKunstwerke wird bis zu deren Ende durchlaufen, entspricht den Räumen
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                //die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                meinWriter.write("------------------------------------------\n");
                meinWriter.write("Raum: " + raeume.getRaum(i).toString() + "\n");
                meinWriter.write("------------------------------------------\n");
                
                // zweite Dimension der Arraylist wird bis zum Ende durchlaufen,enthält die einzelnen Kunstwerke deren String-Repräsentation als Byte in den Ausgabestrom geschrieben wird
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    meinWriter.write(zugeordneteKunstwerke.get(i).get(j).toString() + "\n");
                }
                
                meinWriter.write("\n");
            }
            
            // Ausgabepuffer wird durch die Methode close geschlossen
            meinWriter.close();
                      
            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            sb.append("<h1>Auflistung nach Räumen (zur Aufstellung der Kunstwerke für Museumsmitarbeiter)</h1>");
                    
              for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                //die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                sb.append("<h1>Raum: " + raeume.getRaum(i).toString() + "</h1>");
                sb.append("<table border='1px' width='100%'>");
                sb.append("<tr><td>Nr.</td>");
                sb.append("<td>Kunstwerk</td></tr>");
                
                // zweite Dimension der Arraylist wird bis zum Ende durchlaufen,enthält die einzelnen Kunstwerke deren String-Repräsentation als Byte in den Ausgabestrom geschrieben wird
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    sb.append("<tr>");
                    sb.append("<td>" + (j+1) + "</td>");
                    sb.append("<td>" + zugeordneteKunstwerke.get(i).get(j).toString() + "</td>");
                    sb.append("</tr>");
                }
                
                sb.append("</table>");
                
            }
            
            sb.append("</html>");
            FileWriter fstream = new FileWriter("raumdatei.html");
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
     * Erstellt eine Datei, die für die Besucher eine kurze Übersicht zu
     * den Räumen und wichtigsten Kunstwerken enthält. Sie nutzt dazu die 
     * Ausstellungsplanung.
     */
    public void schreibeMuseumsfuehrer()
    {
         try
        {
            // Objekt meinWriter der Klasse BufferedWriter zur Ausgabepufferung, und anonymes File-WriteWriter-Objekt zur eigentlichen Ausgabe
            BufferedWriter meinWriter = new BufferedWriter(new FileWriter(file));
            
            // erste Dimension der ArrayList zugeordneteKunstwerke wird bis zu deren Ende durchlaufen, entspricht den Räumen
            for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                //die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                meinWriter.write("------------------------------------------\n");
                meinWriter.write("Raum: " + raeume.getRaum(i).toString() + "\n");
                meinWriter.write("------------------------------------------\n");
                
                // zweite Dimension der Arraylist wird bis zum Ende durchlaufen,enthält die einzelnen Kunstwerke deren String-Repräsentation als Byte in den Ausgabestrom geschrieben wird
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    meinWriter.write(zugeordneteKunstwerke.get(i).get(j).toString() + "\n");
                }
                
                meinWriter.write("\n");
            }
            
            // Ausgabepuffer wird durch die Methode close geschlossen
            meinWriter.close();
                      
            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            sb.append("<h1>Museumsführer (mit den fünf attraktivsten Kunstwerk pro Raum</h1>");
                    
              for(int i=0; i < zugeordneteKunstwerke.size(); i++)
            {
                //die Methode write schreibt den String-Eingabeparameter als Byte in den Ausgabestrom
                sb.append("<h1>Raum: " + raeume.getRaum(i).toString() + "</h1>");
                sb.append("<table border='1px' width='100%'>");
                sb.append("<tr><td>Nr.</td>");
                sb.append("<td>Kunstwerk</td></tr>");
                
                // zweite Dimension der Arraylist wird bis zum Ende durchlaufen,enthält die einzelnen Kunstwerke deren String-Repräsentation als Byte in den Ausgabestrom geschrieben wird
                for(int j=0; j < zugeordneteKunstwerke.get(i).size(); j++)
                {
                    sb.append("<tr>");
                    sb.append("<td>" + (j+1) + "</td>");
                    sb.append("<td>" + zugeordneteKunstwerke.get(i).get(j).toString() + "</td>");
                    sb.append("</tr>");
                }
                
                sb.append("</table>");
            }
            
            sb.append("</html>");
            FileWriter fstream = new FileWriter("museumsfuehrer.html");
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
     * Schreibt eine Textzeile in eine  Ausgabedatei
     * @param str Text der in eine Teile der Ausgabedatei geschrieben werden soll
     */
    private void writeLine(String str) 
    {
        
    } 
}
