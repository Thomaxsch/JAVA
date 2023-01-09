import java.io.*;
import java.util.Scanner;

/**
 * Die Klasse Museum bildet die Einstiegsklasse. Aus ihr wird das Programm
 * gestartet. 
 * 
 * @author Mischa Paul Marchlewski 
 * @version 06.01.2023
 */
public class Museum
{
    private Raumverwaltung raeume;
    private Kunstwerkverwaltung kunstwerke;
    
    public Museum() 
    {
        raeume = new Raumverwaltung();
        kunstwerke = new Kunstwerkverwaltung();
        
        importiereKunstwerke("kunstwerke.csv");
        importiereRaeume("raeume.csv"); 
        
        raeume.showRaeume();
        kunstwerke.showKunstwerke();
        
        Ausstellungsplanung planung = new Ausstellungsplanung(); 
        Ausstellungsplanung2 planung2 = new Ausstellungsplanung2(raeume, kunstwerke);
    }

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
        Museum meinMuseum = new Museum();
    }
    
    /**
     * Liest die Kunstwerke aus der Datei kunstwerke.csv ein und erstellt
     * daraus Objeke der Klasse Bild, Kunstinstallation bzw. Kunstgegenstand, die dann
     * in der Klasse Angebotsverwaltung verwaltet werden
     * 
     * @param name  Name (Pfad) der Datei, die eingelesen werden soll
     */
    public void importiereKunstwerke(String name)
    {              
        Kunstinstallation ki;
        Bild bi;
        Kunstgegenstand kg;
        
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
                    * array[0]  = Laufende Nummer
                    * array[1]  = Art des Kunstwerks
                    * array[2]  = Bezeichnung
                    * array[3]  = Künstlername
                    * array[4]  = Jahresangabe
                    * array[5]  = Thema
                    * array[6]  = Attraktivität in Prozent
                    * array[7]  = Kosten in Euro
                    * array[8]  = Name des Museums
                    * array[9]  = Anschrift des Museums
                    * array[10] = Höhe in cm
                    * array[11] = Breite in cm
                    * array[12] = Länge in cm (nur bei Kunstgegenstand oder Installation)  /Minimaltemperatur in C (nur bei Bildern)
                    * array[13] = Gewicht in kg (nur bei Kunstgegenstand oder Installation)/Maximaltemperatur in C (nur bei Bildern)
                    * array[14] = Minimale Luftfeuchtigkeit in C (nur bei Bildern)
                    * array[15] = Maximale Luftfeuchtigkeit in C (nur bei Bildern)
                    */ 
                   
                   if(array[1].equals("B")) 
                   {
                        bi = new Bild(Short.parseShort(array[0]),
                                     array[1].charAt(0),
                                     array[2],
                                     array[3],
                                     array[4],
                                     array[5],
                                     Integer.parseInt(array[6]),
                                     Integer.parseInt(array[7]),
                                     array[8],
                                     array[9],         
                                     Integer.parseInt(array[10]),
                                     Integer.parseInt(array[11]),
                                     Integer.parseInt(array[12]),
                                     Integer.parseInt(array[13]),
                                     Integer.parseInt(array[14]),
                                     Integer.parseInt(array[15]));
                                     
                        kunstwerke.addKunstwerk(bi);
                   }
                   
                   if(array[1].equals("I"))
                   {
                         ki = new Kunstinstallation(Short.parseShort(array[0]),
                                     array[1].charAt(0),
                                     array[2],
                                     array[3],
                                     array[4],
                                     array[5],
                                     Integer.parseInt(array[6]),
                                     Integer.parseInt(array[7]),
                                     array[8],
                                     array[9],         
                                     Integer.parseInt(array[10]),
                                     Integer.parseInt(array[11]),
                                     Integer.parseInt(array[12]),
                                     Integer.parseInt(array[13]));
                                     
                        kunstwerke.addKunstwerk(ki);
                   }
                   
                   if(array[1].equals("G"))
                   {
                         kg = new Kunstgegenstand(Short.parseShort(array[0]),
                                     array[1].charAt(0),
                                     array[2],
                                     array[3],
                                     array[4],
                                     array[5],
                                     Integer.parseInt(array[6]),
                                     Integer.parseInt(array[7]),
                                     array[8],
                                     array[9],         
                                     Integer.parseInt(array[10]),
                                     Integer.parseInt(array[11]),
                                     Integer.parseInt(array[12]),
                                     Integer.parseInt(array[13]));
                                     
                        kunstwerke.addKunstwerk(kg);
                      }
                }
                
                if (zw_in == null)
                {
                    eof=true;
                }
            }
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
    
    /**
     * Liest die Räume aus der Datei raeume.csv ein und erstellt daraus
     * Objekte der Klasse Raum, die in der Klasse Raumverwaltung verwaltet 
     * werden.
     * 
     * @param name Name (Pfad) der Datei, die eingelesen werden soll
     */
    public void importiereRaeume(String name)
    {
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
                   
                    raeume.addRaum(raum);                
                }
                
                if (zw_in == null)
                {
                    eof=true;
                }
            }
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
    
    public Raumverwaltung raeume()
    {
        return this.raeume;
    }
    
    
    
    
    
    
}
