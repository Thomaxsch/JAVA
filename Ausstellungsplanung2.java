import java.util.*;

/**
 * Beschreiben Sie hier die Klasse Ausstellungsplanung2.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Ausstellungsplanung2
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private HashMap<Raum, List<Kunstwerk>> zugeordneteKunstwerke;
    private Raumverwaltung raeume;
    private Kunstwerkverwaltung kunstwerke;

    /**
     * Konstruktor für Objekte der Klasse Ausstellungsplanung2
     */
    public Ausstellungsplanung2(Raumverwaltung raeume, Kunstwerkverwaltung kunstwerke)
    {
        this.raeume = raeume;
        this.kunstwerke = kunstwerke; 
        zugeordneteKunstwerke = new HashMap<Raum, List<Kunstwerk>>();
        
        generiereAusstellung();
        gebeAus();
    }
    
    
    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        die Summe aus x und y
     */
    public void generiereAusstellung()
    {
        Vector<Raum> raeume2 = (Vector<Raum>) raeume.getRaumVector().clone();
        
        //Comparator<Raum>
        Vector<Kunstwerk> kunstwerke2 = (Vector<Kunstwerk>) kunstwerke.getKunstwerkVector().clone();
             
        // Abstand der Kunstwerke von den Ecken eines Raums muss 1 Meter (100 cm) entsprechen
        int abstandEcke = 100;
        
    for(Raum raum : raeume2) 
    {
        List<Kunstwerk> kw = new ArrayList<Kunstwerk>();  
        
        // zählt die Anzahl der Themen pro Raum, Vorgabe maximal 3 pro Raum
        ArrayList<String> themenProRaum = new ArrayList<String>();
        int anzahlThemenProRaum = 0;
        
        // Höhe des Raums ermitteln
        int hoeheRaum = raum.getHoeheRaum();
        
        //Anzahl der Kunstinstallationen pro Raum
        int zugeordneteInstallationenProRaum = 0;
        int zugeordneteKunstwerkeProRaum = 0;
        
        ListIterator<Kunstwerk> kunstiter = kunstwerke2.listIterator();
            
        // verfügbare Nettolänge einer Wand berechnen: Nettolänge = Bruttolänge - (abstandEcke * 2) 
        //- Türbreite
        int verfuegbareLaengeNord = raum.getLaengeRaum() - (abstandEcke * 2) - raum.getTuerNord();
        int verfuegbareLaengeSued = raum.getLaengeRaum() - (abstandEcke * 2) - raum.getTuerSued();
        int verfuegbareBreiteOst  = raum.getBreiteRaum() - (abstandEcke * 2) - raum.getTuerOst();
        int verfuegbareBreiteWest = raum.getBreiteRaum() - (abstandEcke * 2) - raum.getTuerWest();
        
        // Länge des Raums ermitteln um mehrere Kunstgegenstände anzuordnen, dabei werden für die Länge und Breite des Raums jeweils schon 1 Meter abgezogen (Mindestabstand zu den Wänden)
        double nettoFlaecheRaum = (raum.getBreiteRaum() - 200) * (raum.getLaengeRaum() - 200);
                         
        while(kunstiter.hasNext())
        {   
            Kunstwerk temp = kunstiter.next();
            
            // wenn die Anzahl der zugeordneten Kunstinstallation den Wert 1 überschreitet, dann braucht die Aufnahme weiterer Kunstwerke in einem Raum nicht geprüft werden
            // wenn die Anzahl der unterschiedlichen Themen pro Raum den Wert > 3 überschreitet, dann kann das Kunstwerk nicht mehr aufgenommen werden
            if(zugeordneteInstallationenProRaum < 1)
            {
                // Prüft ob eine Kunstinstallation der Ausstellung hinzugefügt werden kann
                if(temp.getArt() == 'I')
                {
                  if(validiereKunstinstallation((Kunstinstallation)temp, raum) && zugeordneteKunstwerkeProRaum < 1)
                  {
                    kw.add(temp);
                    kunstiter.remove();
                    zugeordneteInstallationenProRaum++; 
                    zugeordneteKunstwerkeProRaum++; 
                    continue;
                  }
                }
                
                // Prüft ob ein Bild der Ausstellung hinzugefügt werden kann
                if(temp.getArt() == 'B')
                {
                  /*
                   * Prüfung folgender Bedingungen vor Aufnahme eines Bildes in den Raum
                   * 1. wenn das Thema des Bildes schon vorhanden ist, dann kann ein weiteres Bild mit dem gleichen Thema aufgenommen werden
                   */
                  if(themenProRaum.contains(temp.getThema()))
                  {                       
                      boolean gueltigeHoehe = false;
                      
                      if(temp.getHoehe() <= hoeheRaum)
                          gueltigeHoehe = true;
              
                      // Prüft ob noch Platz auf der nördlichen Wand ist und fügt das Kunstwerk bei Platz hinzu
                      if(temp.getBreite() <= verfuegbareLaengeNord && gueltigeHoehe)
                      {
                        kw.add(temp);
                        kunstiter.remove();
                        verfuegbareLaengeNord -= temp.getBreite() - 1;
                        zugeordneteKunstwerkeProRaum++; 
                      }
                   
                      // Prüft ob noch Platz auf der südlichen Wand ist und fügt das Kunstwerk bei Platz hinzu
                      if(temp.getBreite() <= verfuegbareLaengeSued && gueltigeHoehe)
                      {
                        kw.add(temp);
                        kunstiter.remove();
                        verfuegbareLaengeSued -= temp.getBreite() - 1;
                        zugeordneteKunstwerkeProRaum++; 
                        continue;
                      } 
              
                      // Prüft ob noch Platz auf der östlichen Wand ist und fügt das Kunstwerk bei Platz hinzu
                      if(temp.getBreite() <= verfuegbareBreiteOst && gueltigeHoehe)
                      {
                        kw.add(temp);
                        kunstiter.remove();
                        verfuegbareBreiteOst -= temp.getBreite() - 1;
                        zugeordneteKunstwerkeProRaum++; 
                        continue;
                      } 
              
                      // Prüft ob noch Platz auf der westlichen Wand ist und fügt das Kunstwerk bei Platz hinzu
                      if(temp.getBreite() <= verfuegbareBreiteWest && gueltigeHoehe)
                      {
                        kw.add(temp);
                        kunstiter.remove();
                        verfuegbareBreiteWest -= temp.getBreite() - 1;
                        zugeordneteKunstwerkeProRaum++; 
                        continue;
                      } 
                  }
                }
                // Prüft ob ein Kunstgegenstand der Ausstellung hinzugefügt werden kann
                if(temp.getArt() == 'G')
                {
                    Kunstgegenstand kg = (Kunstgegenstand) temp;
                    
                    // berechnet die benötigte Fläche eines Kunstgegenstandes, auf die Breite und Länge werden 200 cm addiert um den Abstand von 1 Meter zu anderen 
                    // Kunstgegenständen zu gewährleisten bzw. den Gesamtabstand von 2 Meter zu der jeweiligen Wand zu gewährleisten, wenn der Kunstgegenstand direkt 
                    // in der Nähe einer Wand platziert wird
                    double benoetigteFlaecheKunstgegenstand = (kg.getBreite() + 200) * (kg.getLaenge() + 200);
                    
                    if(benoetigteFlaecheKunstgegenstand <= nettoFlaecheRaum)
                    {
                        kw.add(temp);
                        kunstiter.remove();
                        nettoFlaecheRaum -= benoetigteFlaecheKunstgegenstand;
                    }
                    continue;
                }  
                continue;
            }
        }
         zugeordneteKunstwerke.put(raum, kw);
        }
       
    
}

public void gebeAus()
{
    for(Raum key : zugeordneteKunstwerke.keySet())
    {
            System.out.println("------------------------------------");
            System.out.print("Raum: " + key + " - " + "\n");
            System.out.println("------------------------------------");
            List<Kunstwerk> temp = zugeordneteKunstwerke.get(key);
            
            for(Kunstwerk k : temp)
            {
                System.out.println(k);
            } 
    }
}

/**
 * Prüft ob ein Bild mit der geforderten Temperatur und Luftfeuchte in den Raum aufgenommen werden darf
 */
private boolean validiereTemperaturLuftfeuchte(Kunstwerk kw, Raum raum)
{
    return true;
}

    /**
     * prüft ob eine Kunstinstallation genügend Abstand zu den Wänden hat 
     * @param ki Kunstinstallation die geprüft werden soll
     * @param raum Raum dem die Kunstinstallation zugeordnet werden soll
     * @return liefert ein booleschen Wert zurück ob die Kunstinstallation in den Raum passt
     */
    private boolean validiereKunstinstallation(Kunstinstallation ki, Raum raum)
    {
        int abstandBreite = raum.getBreiteRaum()- ki.getBreite();
        int abstandLaenge = raum.getLaengeRaum()- ki.getLaenge();
    
        // Abstand zu jeder Wand mindestens 2 Meter (= 200 cm) betragen, d.h 400 cm insgesamt
        if(abstandBreite > 400 && abstandLaenge > 400)
        {
            return true;
        }
        else
        {
            return false;    
        }
    }
}