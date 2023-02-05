import java.io.*;
import java.util.*;

/**
 * Die Klasse Museum bildet die Einstiegsklasse. Aus ihr wird das Programm gestartet. Sie dient auch dem Einlesen der Kunstwerke und Räume und bietet ein Konsolenmenü an.
 * @author Mischa Paul Marchlewski 
 * @version 30.01.2023
 */
public class Museum
{
    // Deklaration der Objektvariablen der Klasse Raumverwaltung
    private Raumverwaltung raeume;
    
    // Deklaration der Objektvariablen der Klasse Kunstwerksverwaltung
    private Kunstwerkverwaltung kunstwerke;
    
    // Deklaration der Objektvariablen der Klasse Ausstellungsplanung
    private Ausstellungsplanung planung;
    
    /**
     * parameterloser Konstrukur der Klasse Museum
     */
    public Museum() 
    {
        // eine neue Instanz der Klasse Raumverwaltung wird generiert und der Objektvariablen raeume zugewiesen
        raeume = new Raumverwaltung();
        
        // eine neue Instanz der Klasse Kunstwerkverwaltung wird generiert und der Objektvariablen kunstwerke zugewiesen
        kunstwerke = new Kunstwerkverwaltung();
        
        // eine neue Instanz der Klasse Ausstellungsplanung wird generiert und der Objektvariablen planung zugewiesen
        // als Parameter werden zwei Objekte der Klassen Raumverwaltung und Kunstwerksverwaltung übergeben
        planung = new Ausstellungsplanung(raeume, kunstwerke); 
        
        // Ausgabe des Konsolenmenüs der Klasse Museum
        gebeMenuAus(); 
    }

    /**
     * Mit der Main-Methode wird das Programm gestartet. Es wird eine Objektvariable meinMuseum der Klasse Museum deklariert und eine neue Instanz der Klasse Museum generiert
     * @param  args    Stringparameter, die übergeben werden können
     */
    public static void main(String[] args)
    {
        // Objektvariable meinMuseum der Klasse Museum wird deklariert und mit new ein neu erzeugtes Museums-Objekt zugewiesen
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
        // Deklaration von drei Objektvariablen vom Typ Kunstinstallation, Bild und Kunstgegenstand        
        Kunstinstallation ki;
        Bild bi;
        Kunstgegenstand kg;
        
        // Beginn Try-Block
        try 
        {
            // Deklaration der Variablen eof vom Typ boolean und Initialisierung mit dem Wert false 
            boolean eof = false;
            
            BufferedReader dEin = new BufferedReader(new FileReader(name));
            
            // While-Schleife soll solange durchlaufen werden, solange die Variable nicht den Wert true hat
            while(!eof)
            {
               // es wird eine Zeile aus der Eingabedatei gelesen und in der String-Variablen zw_in gespeichert
                String zw_in = dEin.readLine(); 
               
               // Prüfung, ob der Varianle zw_in ein String-Objekt zugeordnet ist
               if(zw_in != null)
               {
                   // Werte in der String-Variablen zw_in wird beim Auftreten eines Kommas getrennt, getrennte Werte werden in einem String-Array gespeichert
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
                   
                   // Wert an der zweiten Stelle des Array entspricht der Art des Kunstwerks 
                   // If-Zweig wird ausgeführt, wenn es sich um ein Bild handelt
                   if(array[1].equals("B")) 
                   {
                       /* Werte des String-Arrays bilden die Eingabeparameter für den Konstruktor der Klasse Bild
                        * die Referenz auf das neu erzeugte Bild-Objekt wird in der Variablen bi gespeichert 
                        * Konstruktor erwartet bei einigen Eingabeparametern Integer-Werte, mit der Methode parseInt erfolgt die Umwandlung
                       */  
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
                        
                        // Referenz auf das neu erzeugte Bild-Objekt wird der Containerklasse kunstwerke hinzugefügt
                        kunstwerke.addKunstwerk(bi);
                   }
                   
                   // If-Zweig wird ausgeführt, wenn es sich um eine Kunstinstallation handelt
                   if(array[1].equals("I"))
                   {
                        /* Werte des String-Arrays bilden die Eingabeparameter für den Konstruktor der Klasse Kunstinstallation
                        * die Referenz auf die neu erzeugte Kunstinstallation wird in der Variablen ki gespeichert 
                        * Konstruktor erwartet bei einigen Eingabeparametern Integer-Werte, mit der Methode parseInt erfolgt die Umwandlung
                       */  
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
                        
                        // Referenz auf die neu erzeugte Installation wird der Containerklasse kunstwerke hinzugefügt
                        kunstwerke.addKunstwerk(ki);
                   }
                   
                   // If-Zweig wird ausgeführt, wenn es sich um einen Kunstgegenstand handelt
                   if(array[1].equals("G"))
                   {
                       /* Werte des String-Arrays bilden die Eingabeparameter für den Konstruktor der Klasse Kunstgegenstand
                        * die Referenz auf den neu erzeugten Kunstgegenstand wird in der Variablenkg gespeichert 
                        * Konstruktor erwartet bei einigen Eingabeparametern Integer-Werte, mit der Methode parseInt erfolgt die Umwandlung
                       */    
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
                        
                        // Referenz auf die neu erzeugte Installation wird der Containerklasse kunstwerke hinzugefügt
                        kunstwerke.addKunstwerk(kg);
                      }
                }
                
                // wenn keine Zeile mehr vorhanden ist, die gelesen werden kann, wird die Boolean-Variable eof auf den Wert true gesetzt
                if (zw_in == null)
                {
                    eof=true;
                }
            }
            
            // Ausgabe an den Benutzer das Kunstwerke erfolgreich eingelesen werden konnten
            System.out.println("Es wurden " + kunstwerke.sizeKunstwerkverwaltung() + " Kunstwerke eingelesen!");
        }
        //wird ausgeführt, wenn die Datei nicht gefunden wurde
        catch(FileNotFoundException e)
        {
            System.out.println("Die Datei" + name + "existiert nicht.");
        }
        // wird ausgeführt, wenn es beim Lesen der Datei zu einem Fehler kommt
        catch(IOException e)
        {
            System.out.println("Fehler beim Zugriff auf die Datei!");
            System.out.println("Folgender Fehler ist aufgetreten " + e.getMessage());
        }
        catch(NumberFormatException e)
        {
             System.out.println("Fehler in der zugrundeliegenden Datei: " + e.getMessage());
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
                   
                    // Raum wird der Raumverwaltung hinzugefügt
                    raeume.addRaum(raum);                
                }
                
                // wenn keine Zeile mehr vorhanden ist, die gelesen werden kann, wird die Boolean-Variable eof auf den Wert true gesetzt
                if (zw_in == null)
                {
                    eof=true;
                }
            }
            
            // Ausgabe an den Benutzer das Räume erfolgreich eingelesen werden konnten
            System.out.println("Es wurden " + raeume.anzahlRaeume() + " Räume eingelesen!");
        }
        // behandelt den Fall, dass der angegebene Pfad/Datei nicht korrekt ist
        catch(FileNotFoundException e)
        {
            System.out.println("Die Datei" + name + "existiert nicht. Bitte wählen Sie eine gültige Datei.");
        }
        // behandelt den Fall, dass beim Zugriff auf die Datei ein Fehler aufgetreten, z.B. Datei ist schreibgeschützt
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
        
        try 
        {
           // Do-Schleife damit das Menü nach einer Eingabe immer wieder angezeigt wird
            do 
           {
            // Einfaches Benutzermenü
            System.out.println("-------------------------------------------------------------------");
            System.out.println("VAWi-Museum Essen Ausstellungsplanung");
            System.out.println("-------------------------------------------------------------------");
            System.out.println("(1) Räume einlesen");
            System.out.println("(2) Kunstwerk einlesen");
            System.out.println("(3) Planung starten");
            System.out.println("(4) Schwerpunktthema angeben/ändern");
            System.out.println("(5) Kostenobergrenze angeben/ändern");
            System.out.println("(6) Qualitätsgewicht angeben/ändern");
            System.out.println("(7) Aktuelles Schwerpunktthema anzeigen");
            System.out.println("(8) Aktuelle Kostenobergrenze anzeigen");
            System.out.println("(9) Aktuelles Qualitätsgewicht anzeigen");
            System.out.println("(10) Variationsanalyse");
            System.out.println("(11) Logmodus für Planung und Variationsanalyse an/aus");
            System.out.println("(12) Anwendung beenden"); 
            System.out.println("Bitte eine Auswahl treffen (z.B. 2 um ein Schwerpunktthema zu setzen)"); 
            
            // nimmt ausgewählten Menüpunkt entgegen
            eingabe = console.readLine();
            
             // behandelt die Auswahl einer einzulesenden Datei mit Räumen
             if(eingabe.equals("1"))
            {
                // Info an den Anwender, welche Eingabe erwartet wird 
                System.out.print("Bitte geben Sie den Pfad mit den zu verwendenden Räumen an:");
                
                // liest den angegebenen Pfad von der Konsole und speichert ihn in der Variablen eingabe
                eingabe = console.readLine();
                
                 // bevor neue Räume eingelesen erden, werden evtl. vorher eingelesene Räume gelöscht
                raeume.clearRaumverwaltung();
                
                // die Variable eingabe mit der Pfadangabe dient als Eingabeparameter für die Methode importiereRaeume
                importiereRaeume(eingabe);
            }
            
            // behandelt die Auswahl einer einzulesenden Datei mit Kunstwerken
             if(eingabe.equals("2"))
            {
                // Info an den Anwender, welche Eingabe erwartet wird 
                System.out.print("Bitte geben Sie den Pfad mit den zu verwendenden Kunstwerken an:");
                
                // liest den angegebenen Pfad von der Konsole und speichert ihn in der Variablen eingabe
                eingabe = console.readLine();
                
                // bevor neue Kunstweerke eingelesen werden, werden evtl. vorher eingelesene Räume gelöscht
                kunstwerke.clearKunstwerkverwaltung();
                
                // die Variable eingabe mit der Pfadangabe dient als Eingabeparameter für die Methode importiereKunstwerke
                importiereKunstwerke(eingabe);
            }
            
            // behandelt die Generierung der Ausstellung
            if(eingabe.equals("3"))
            { 
                // prüft ob Räume für die Ausstellungsplanung vorhanden sind, ansonsten Abbruch der Generierung durch continue
                if(raeume.anzahlRaeume() == 0) 
                {
                    System.out.println("Bitte lesen Sie erst einige Räume für die Ausstellungsplanung ein.");
                    continue;
                }
                
                // prüft ob Kunstwerke für die Ausstellungsplanung vorhanden sind, ansonsten Abbruch der Generierung durch continue
                if(kunstwerke.sizeKunstwerkverwaltung() == 0) 
                {
                    System.out.println("Bitte lesen Sie erst einige Kunstwerke für die Ausstellungsplanung ein.");
                    continue;
                }
                
                 // Aufruf der Methode generiereAusstellungen des Objekts planung der Klasse Ausstellungsplanung 
                planung.generiereAusstellungen();
                
                // gibt entweder das beste Mapping oder Null, wenn es keine Minimallösung gab
                ArrayList <ArrayList <Kunstwerk>> planungsErgebnis = planung.getBestesMapping(); 
                               
                if (planungsErgebnis != null)
                {
                    System.out.println("\nWir haben nach Lösungen gesucht und können nun die beste ausgeben.\n");
                    System.out.print("Bitte geben Sie einen Pfad für die Ausgabe der txt- und HTML-Dateien an.");
                    eingabe = console.readLine();
                    
                    File dir = new File(eingabe);
                    if(dir.exists())
                    {
                        // Deklaration und Initialisierung einer Objektvariablen der Klasse Ausgabedatei
                        Ausgabedatei ausgabe = new Ausgabedatei(eingabe, planungsErgebnis, planung, raeume, kunstwerke);
                        ausgabe.schreibeAusleihen();
                        ausgabe.schreibeAusstellungen();
                        ausgabe.schreibeMuseumsfuehrer();
                    }
                    else 
                    {
                        System.out.println("Angegebener Pfad ist nicht gültig! Bitte starten Sie die Planung neu.");
                    }
                }
                else
                {
                    System.out.print("\nKeine Minimallösung gefunden. Bitte prüfen Sie die Konfigurationsparameter (Kostenobergrenze, Schwerpunktthema, Qualitätsgewicht).\n");
                }
                
            }
            
            // behandelt die Eingabe eines Schwerpunktthemas
            if(eingabe.equals("4"))
            {
                // Liste der Themen wird direkt bei der Erstellung mit Hilfe der Methode asList der Klasse Arrays initialisiert
                List<String> themen = Arrays.asList("Aktmalerei", "Barock", "Bauhaus", "Expressionismus", "Hyperrealismus", "Impressionismus", "Klassizismus", "Landschaftmalerei", 
                    "Neo-Expressionismus", "Pflanzen", "Portraits", "Religion", "Renaissance", "Rokoko", "Romantik", "Sonstiges", "Stilleben", "Surrealismus", "Tiere");
                
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
            if(eingabe.equals("5"))
            {
                // Info an Benutzer welche Eingabe erforderlich ist
                System.out.println("Bitte geben Sie eine Kostenobergrenze an: ");
                
                // nimmt die Eingabe von der Konsole entgegen und speichert Sie in der Variablen eingabe
                eingabe = console.readLine();
                
                // Try-Block
                try
                {                
                    // Aufruf der Methode setKostenobergrenze des Objekts planung der Klasse Ausstellungsplanung
                    // Eingaben von der Konsole sind immer Strings und müssen deshalb erstmal mit der Methode parseDouble der Klasse Double umgewandelt werden
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
            
            // behandelt die Eingabe eines Qualitätsgewichts
            if(eingabe.equals("6"))
            {
                // Info an Benutzer welche Eingabe erforderlich ist
                System.out.println("Bitte geben Sie ein Qualitätsgewicht an: ");
                
                // nimmt die Eingabe von der Konsole entgegen und speichert Sie in der Variablen eingabe
                eingabe = console.readLine();
                
                // Try-Block
                try
                {                
                    // Aufruf der Methode setQualitaetsgewicht des Objekts planung der Klasse Ausstellungsplanung
                    // Eingaben von der Konsole sind immer Strings und müssen deshalb erstmal mit der Methode parseDouble der Klasse Double umgewandelt werden
                    planung.setQualitaetsgewicht(Double.parseDouble(eingabe));
                                        
                    // Info für den Benutzer wenn Kostenobergrenze erfolgreich gesetzt werden konnte
                    System.out.println("Qualitätsgewicht " + planung.getQualitaetsgewicht() + " wurde erfolgreich gesetzt");
                }
                // Catch-Block fängt Fehler ab, wenn z.B. ein Text statt einer Zahl eingegeben wurde
                catch(NumberFormatException e)
                {
                    System.out.println("Fehler beim Setzen des Qualitätsgewichtes. Kein gültiger Wert!");
                }
            }
            
            // behandelt die Anzeige des aktuellen Schwerpunktthemas
             if(eingabe.equals("7"))
            {
                //Methode getSchwerpunktthema des Ojekts planung der Klasse Ausstellungsplanung wird aufgerufen
                System.out.println("Ihr aktuelles Schwerpunktthema: " + planung.getSchwerpunktthema());
            }
            
            // behandelt die Anzeige der aktuellen Kostenobergrenze
             if(eingabe.equals("8"))
            {
                //Methode getKostenobergrenze des Ojekts planung der Klasse Ausstellungsplanung wird aufgerufen
                System.out.println("Ihre aktuelle Kostenobergrenze: " + planung.getKostenobergrenze());
            }
            
            // behandelt die Anzeige der aktuellen Qualitätsgewichtes
             if(eingabe.equals("9"))
            {
                //Methode getQualitaetsgewicht des Ojekts planung der Klasse Ausstellungsplanung wird aufgerufen
                System.out.println("Ihre aktuelles Qualitätsgewicht: " + planung.getQualitaetsgewicht());
            }
                        
            // behandelt das Durchführen einer Variationsanalyse und Anzeige der Ergebnisse
             if(eingabe.equals("10"))
            {
                planung.variationsAnalyse();
            }
            
            // schaltet den Log-Modus für die Planung sowie Variationsanalyse an/aus
             if(eingabe.equals("11"))
            {
                planung.switchlogModus();
            }
            
            // beendet die Anwendung
            if(eingabe.equals("12"))
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