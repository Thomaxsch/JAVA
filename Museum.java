import java.io.*;
import java.util.Scanner;

/**
 * Die Klasse Museum bildet die Einstiegsklasse. Aus ihr wird das Programm
 * gestartet. 
 * 
 * @author Mischa Paul Marchlewski 
 * @version 19.12.2022
 */
public class Museum
{
    /**
     * speichert ein Objekt der Klasse Ausstellungsplanung
     */
    private static Ausstellungsplanung planung;

    /**
     * In der Main-Methode werden alle Anweisungen ausgeführt, die beim Start
     * des Programms benötigt werden (z.B. Import der Räume und
     * Kunstwerke aus den CSV-Dateien). Weiterhin wird ein Objekt der 
     * Klasse Ausstellungsplanung erstellt und dann deren Methode "generiereAusstellung"
     * aufgerufen.
     * 
     * @param  args    Stringparameter, die übergeben werden können
     */
    public static void main(String[] args)
    {
        planung = new Ausstellungsplanung();
        //Museum.importiereKunstwerke();
        Museum.importiereRaeume("raeume.csv");
        //Ausstellungsplanung.generiereAusstellung();
    }
    
    /**
     * Liest die Kunstwerke aus der Datei kunstwerke.csv ein und erstellt
     * daraus Objeke der Klasse Bild, Kunstinstallation bzw. Kunstgegenstand, die dann
     * inder Klasse Angebotsverwaltung verwaltet werden
     * 
     * @param name  Name (Pfad) der Datei, die eingelesen werden soll
     */
    public static void importiereKunstwerke(String name)
    {
        Angebotsverwaltung verw = new Angebotsverwaltung();
        
        Kunstinstallationen kunstinstallation= new Kunstinstallationen();
        verw.addKunstwerk(kunstinstallation);
        
        Bild bild= new Bild();
        verw.addKunstwerk(bild);
        
        Kunstgegenstand kunstgegenstand= new Kunstgegenstand();
        verw.addKunstwerk(kunstgegenstand);
    }
    
    /**
     * Liest die Räume aus der Datei raeume.csv ein und erstellt daraus
     * Objekte der Klasse Raum, die in der Klasse Raumverwaltung verwaltet 
     * werden.
     * 
     * @param name Name (Pfad) der Datei, die eingelesen werden soll
     */
    public static void importiereRaeume(String name)
    {
        Raumverwaltung raumv = new Raumverwaltung();
        Raum raum;
        
        try 
        {
            boolean eof = false;
            BufferedReader dEin = new BufferedReader(new FileReader(name));
            
            while(!eof)
            {
               String zw_in = dEin.readLine(); 
               
               if(zw_in != null)
               {
                   String[] array = zw_in.split(",");
               
                   /*
                    * array[0] = Laufende Nummer
                    * array[1] = Bezeichnung
                    * array[2] = Länge  (der Nord- und Südwand) in cm
                    * array[3] = Breite (der Ost- und Westwand) in cm
                    * array[4] = Höhe in cm
                    * array[5] = Türbreite Nordseite in cm
                    * array[6] = Türbreite Ostseite in cm
                    * array[7] = Türbreite Südseite in cm
                    * array[8] = Türbreite Westseite in cm
                    */ 
                         
                   raum = new Raum(Integer.parseInt(array[0]), 
                                   array[1], 
                                   Integer.parseInt(array[2]),
                                   Integer.parseInt(array[3]),
                                   Integer.parseInt(array[4]), 
                                   Integer.parseInt(array[5]),
                                   Integer.parseInt(array[6]),
                                   Integer.parseInt(array[7]),
                                   Integer.parseInt(array[8]));
                   
                    raumv.addRaum(raum);                
                    System.out.println(raum.toString());
                }
                
                if (zw_in == null)
                {
                    eof=true;
                }
            }
            
            System.out.println(raumv.anzahl());
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Die Datei" + name + "existiert nicht.");
        }
        catch(IOException e)
        {
            System.out.println("Fehler beim Zugriff auf die Datei!");
            System.out.println("Folgender Fehler ist aufgetreten " + e.getMessage());
        }
    }
    
    
    
    
    
    
}
