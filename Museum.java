import java.io.*;
import java.util.*;

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
    private Ausstellungsplanung planung;
    
    public Museum() 
    {
        raeume = new Raumverwaltung();
        kunstwerke = new Kunstwerkverwaltung();

        importiereKunstwerke("kunstwerke.csv");
        importiereRaeume("raeume.csv");
        
        Ausstellungsplanung2 planung2 = new Ausstellungsplanung2(raeume, kunstwerke); // <--kann das hier weg?
        
        planung = new Ausstellungsplanung(raeume, kunstwerke); 
        
        /* ###############################
         * ---- TEST--------------------*/ 
        planung.setKostenobergrenze(5000);
        planung.setSchwerpunktthema("Expressionismus"); //"Aktmalerei" "Rokoko" ""
        planung.setQualitaetsgewicht(0.5);
        planung.generiereAusstellungen();
        
        ArrayList <ArrayList <Kunstwerk>> planungsergebnis = planung.getBestesMapping(); // gibt entweder das beste Mapping oder Null, wenn es keine Minimallösung gab
        if (planungsergebnis==null)
        {
            // hier sollte automatisch die Konsole aufrufen
            System.out.println("TEST: back to Konsole");
        } else
        {
            // hier kann die Ausgabedatei angestoßen weren
            System.out.println("TEST: hier kann die Ausgabedatei angestoßen weren");
        };
        // --- TEST ENDE -----------------
        // ###############################
        
        /*
        gebeMenuAus();   
        */
        
        
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
    
    /**
     * Methode, die ein einfaches Benutzermenü erstellt und Validierungen vornimmt
     */    
    private void gebeMenuAus()
    {
        // Variable vom Typ boolean, die mit dem Wert true initialisiert wird
        boolean weiter= true;
        
        // Objekt console der Klasse BuffferedReader um Eingaben von der Konsole lesen zu können
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        
        // Variable zur Speicherung der Eingbe von der Konsole
        String eingabe = "";
        
        // Objekt planung der Klasse Austellungsplanung wird erstellt
        Ausstellungsplanung2 planung2 = new Ausstellungsplanung2(raeume, kunstwerke); // <-- wird für die Ausstellungsplanung im Konstruktor oben erzeugt
        
        
        try 
        {
           // Do-Schleife damit das Menü nach einer Eingabe immer wieder angezeigt wird
            do 
           {
            // Einfaches Benutzermenü
            System.out.println("----------------------------------------");
            System.out.println("VAWi-Museum Essen Ausstellungsplanung");
            System.out.println("----------------------------------------");
            System.out.println("(1) Planung starten");
            System.out.println("(2) Schwerpunktthema angeben/ändern");
            System.out.println("(3) Kostenobergrenze angeben/ändern");
            System.out.println("(4) Aktuelles Schwerpunktthema anzeigen");
            System.out.println("(5) Aktuelle Kostenobergrenze anzeigen");
            System.out.println("(6) Räume einlesen");
            System.out.println("(7) Kunstwerk einlesen");
            System.out.println("(8) Anwendung beenden"); 
            System.out.println("Bitte eine Auswahl treffen (z.B. 2 um ein Schwerpunktthema zu setzen)"); 
            
            // nimmt ausgewählten Menüpunkt entgegen
            eingabe = console.readLine();
            
            // behandelt die Generierung der Ausstellung
            if(eingabe.equals("1"))
            {
                // Aufruf der Methode generiereAusstellung der Klasse Ausstellungsplanung 
                planung2.generiereAusstellung();
                //// planung.generiereAusstellung(); <-- die Methode ist schon da, wird aber im moment nicht durchlaufen
            }
            
            // behandelt die Eingabe eines Schwerpunktthemas
            if(eingabe.equals("2"))
            {
                // Liste der Themen wird direkt bei der Erstellung mit Hilfe der Methode asList der Klasse Arrays initialisiert
                List<String> themen = Arrays.asList("Aktmalerei", "Barock", "Bauhaus", "Expressionismus", "Hyperrealismus", "Impressionismus", "Klassizismus", "Landschaftmalerei", "Neo-Expressionismus",
                    "Pflanzen", "Portraits", "Religion", "Renaissance", "Rokoko", "Romantik", "Sonstiges", "Stilleben", "Surrealismus", "Tiere");
                
                // Info an Benutzer welche Eingabe erforderlich ist
                System.out.print("Ihr Schwerpunktthema: ");
                
                // nimmt die Eingabe von der Konsole entgegen und speichert Sie in der Variablen eingabe
                eingabe = console.readLine();
                
                // If-Zweig wird ausgeführt, wenn das eingegebene Thema ein gültiges Thema aus der o.g. Liste ist
                // dazu wird mithilfe der Methode contains der der List themen geprüft, ob das eingegebene Thema enthalten, 
                // wenn das Thema enthalten ist, dann ist es gültig
                if(themen.contains(eingabe))
                {
                    // Aufruf der Methode setSchwerpunktthema des Objekts planung der Klasse Ausstellungsplanung
                    planung.setSchwerpunktthema(eingabe);
                    
                    // Info für den Benutzer wenn Kostenobergrenze erfolgreich gesetzt werden konnte
                    System.out.println("Schwerpunktthema " + planung.getSchwerpunktthema() + " wurde erfolgreich gesetzt");
                }
                // Else-Zweig wird ausgeührt, wenn eingegebenes Thema nicht vorhanden ist
                else 
                {
                    // Benutzer wird informiert, dass das eingegebene Thema nicht entalten ist
                    System.out.println("Eingegebenes Thema " + eingabe + " ist nicht gültig! Bitte versuchen Sie es erneut.");
                }
            }
            
            // behandelt die Eingabe einer Kostenobergrenze
            if(eingabe.equals("3"))
            {
                // Info an Benutzer welche Eingabe erforderlich ist
                System.out.println("Bitte geben Sie eine Kostenobergrenze an: ");
                
                // nimmt die Eingabe von der Konsole entgegen und speichert Sie in der Variablen eingabe
                eingabe = console.readLine();
                
                // Try-Block
                try
                {                
                    // Aufruf der Methode setKostenobergrenze des Objekts planung der Klasse Ausstellungsplanung
                    // Eingaben von der Konsole sind immer Strings und müssen deshalb erstmal mit der Methoder parseDouble der Klasse Double umgewandelt werden
                    planung.setKostenobergrenze(Double.parseDouble(eingabe));
                    
                    // Info für den Benutzer wenn Kostenobergrenze erfolgreich gesetzt werden konnte
                    System.out.println("Kostenobergrenze " + planung.getKostenobergrenze() + " wurde erfolgreich gesetzt");
                }
                // Catch-Block fängt Fehler ab, wenn z.B. ein Text statt einer Zahl eingegeben wurde
                catch(NumberFormatException e)
                {
                    System.out.println("Fehler beim Setzen der Kostenobergrenze. Kein gültiger Wert!");
                }
            }
            
            // behandelt die Anzeige des aktuellen Schwerpunktthemas
             if(eingabe.equals("4"))
            {
                //Methode getSchwerpunktthema des Ojekts planung der Klasse Ausstellungsplanung wird aufgerufen
                System.out.println("Ihr aktuelles Schwerpunktthema: " + planung.getSchwerpunktthema());
            }
            
            // behandelt die Anzeige der aktuellen Kostenobergrenze
             if(eingabe.equals("5"))
            {
                //Methode getKostenobergrenze des Ojekts planung der Klasse Ausstellungsplanung wird aufgerufen
                System.out.println("Ihre aktuelle Kostenobergrenze: " + planung.getKostenobergrenze());
            }
            
            // behandelt die Auswahl einer einzulesenden Datei mit Räumen
             if(eingabe.equals("6"))
            {
                
            }
            
            // behandelt die Auswahl einer einzulesenden Datei mit Kunstwerken
             if(eingabe.equals("7"))
            {
                
            }
            
            // beendet die Anwendung
            if(eingabe.equals("8"))
            {
                weiter = false;
                
                // Methode exit der Klasse System beendet die Anwendung
                System.exit(0);
            }
            
           } while(weiter);
        } 
        // Catch-Block wird ausgeführt wenn Fehler bei der Generierung des Menüs auftreten
        catch(Exception e) 
        {
            e.printStackTrace();
        }  
    }
    
    
    
    
    
    
}