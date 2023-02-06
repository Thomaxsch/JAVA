PROJEKTBEZEICHNUNG: 
Ausstellungsplanung des VAWi-Museums Essen - Gruppe 06 

PROJEKTZWECK: 
Planung einer attraktiven und möglichst kostengünstigen Ausstellung für das VAWi-Museum Essen anhand von spezifischen Ausleihlisten von Kunstwerken anderer kooperiender Museen unter Berücksichtigung der vorhandenen Raumbedingungen des VAWi-Museums.
Als Ergebnis werden drei auf den Zweck angepasste Ausgabedateien für den Anwender ausgegeben:
    - Ausleihdatei
    - Raumdatei
    - Museumsführer
Diese sind unterschiedlich gegliedert, um dem jeweiligen Leser der Dateien einen möglichst passenden Überblick über alle für ihn relevanten Informationen zu geben. 

VERSION oder DATUM: 
06.02.2023

AUTOR(EN): 
    Carla Saradeth:
    - Raum
    - Raumverwaltung

    Mischa Paul Marchlewski: 
    - Museum
    - Ausgabedatei
    
    Thomas Scheidt:
    - Ausstellungsplanung
    - Zuordnung
    - Zuordnungsverwaltung

    Alexander Kipry:
    - Kunstwerk
    - Kunstinstallation, Bild, Kunstgegenstand
    - Kunstwerkverwaltung

WIE IST DAS PROJEKT ZU STARTEN:
Das Projekt lässt sich über die Konsole mittels des Befehls "java -jar PlanungVAWiMuseumEssen.jar" starten. 
Im Anschluss wird über die Konsole ein Menü aufgerufen über welches einige Auswahlmöglichkeiten eingeblendet werden:
--> Auf die Menüpunkte kann unter Eingabe der jeweiligen Nummer und einem anschließenden "Enter" zugegriffen werden. 
Die einzelnen Begrifflichkeiten werden unter dem Punkt "Benutzerhinweise" genauer erläutert. 

    (1) Räume einlesen
Unter dieser Option lassen sich ".csv" Dateien der Räume in das Programm einlesen. Dabei kann durch den Anwender ein Pfad ausgewählt werden, aus welchem die Dateien importiert werden sollen. Die Ausgabedateien werden ebenfalls dort abgelegt.
Die Eingabe des Pfades hat in folgendem Format zu erfolgen: "/" zu Beginn und "/" am Ende der Eingabe des Pfades.
    -> Beispiel: "/home/MaxMustermann/Dokumente/Museumsplanung/Februar2023/"
    
    
    (2) Kunstwerk einlesen 
Unter dieser Option lassen sich ".csv" Dateien der Kunstwerke in das Programm einlesen. Dabei kann durch den Anwender ein Pfad ausgewählt werden, aus welchem die Dateien importiert werden sollen. Die Ausgabedateien werden ebenfalls dort abgelegt.
Die Eingabe des Pfades hat in folgendem Format zu erfolgen: "/" zu Beginn und "/" am Ende der Eingabe des Pfades.    
    -> Beispiel: "/home/MaxMustermann/Dokumente/Museumsplanung/Februar2023/"


    (3) Planung starten
Falls die anderen Menüoptionen zuvor nicht angepasst wurden wird unter den Standardbedingungen (ohne Schwerpunkt und einem Qualitätsgewicht  von 50%) die Planung durchgeführt.
Nach erfolgter Planung, wird der Benutzer aufgefordert einen Dateipfad anzugeben. An der Stelle des Dateipfads werden die drei Ausgabedateien  (Ausleihdatei, Raumdatei und Museumsfueherer) als CSV- und HTML-Datei abgespeichert.

    (4) Schwerpunktthema angeben/ändern
Es besteht die Möglichkeit für die Ausstellung ein Schwerpunktthema anzugeben oder falls bereits festgelegt zu ändern. Hierfür tippen Sie einfach das zu setzende Thema ein und bestätigen die Eingabe mit "Enter". Wenn im Anschluss unter "(1)" die Planung durchgeführt wird, wird in diesem Fall das gesetzte Schwerpunktthema berücksichtigt. 

    (5) Kostenobergrenze angeben/ändern
Es besteht die Möglichkeit für die Ausstellung eine Kostenobergrenze anzugeben oder falls bereits festgelegt zu ändern.  Hierfür tippen Sie einfach die zu setzende Kostenobergrenze ein und bestätigen die Eingabe mit "Enter". Wenn im Anschluss unter "(1)" die Planung durchgeführt wird, wird in diesem Fall die gesetzte Kostenobergrenze berücksichtigt. 

    (6) Qualitätsgewicht angeben/ändern
Es besteht die Möglichkeit für die Ausstellung ein Qualitätsgewicht anzugeben oder falls bereits festgelegt zu ändern. 
Hierfür tippen Sie einfach die gewünschte Gewichtung z.B. "0.6" ein und bestätigen die Eingabe mit "Enter". Durch diese Methode erhält der Anwender die Möglichkeit seine Planung an die individuellen Bedürfnisse und den jeweiligen Gegebenheiten genauer anzupassen. Eine genauere Erläuterung hierzu sind den Benutzerhinweisen zu entnehmen. 
Wenn im Anschluss unter "(1)" die Planung durchgeführt wird, wird in diesem Fall die gesetzte Gewichtung berücksichtigt.
    
    (7) Aktuelles Schwerpunktthema anzeigen
Bei Aufruf dieser Option wird angezeigt, ob ein Schwerpunktthema für die Planung gesetzt wurde. Falls dies zutrifft, wird dieses angezeigt.
    


    (8) Aktuelle Kostenobergrenze anzeigen
Bei Aufruf dieser Option wird angezeigt, ob eine Kostenobergrenze für die Planung gesetzt wurde. Falls dies zutrifft, wird diese angezeigt.
    
    (9) Aktuelles Qualitätsgewicht anzeigen
Bei Aufruf dieser Option wird angezeigt, welches Qualitätsgewicht für die Planung gesetzt wurde. 
     
    (10) Variationsanalyse
Unter dieser Option wird eine Variationsanalyse durchgeführt. Das bedeutet, dass anhand der eingelesenen Kunstwerke und Räume die bestmöglichen Zuordnungen für alle Schwerpunktthemen ausgegeben werden. Diese Option eignet sich sehr gut, um sich einen Überblick, über alle Möglichkeiten für die Planung mit den gegebenen Bedingungen, zu verschaffen. 
Somit ist es möglich anhand der Variationsanalyse und des erhaltenen Überblicks einzuschätzen mit welchen Schwerpunktthemen eine Planung sinnvoll erscheint und welche schon auszuschließen sind. 
    
    (11) Logmodus für Planung und Variationsanalyse an/aus
Unter dieser Option lässt sich der Logmodus für die Planung und Variationsanalyse an, bzw. ausschalten. Standardmäßig ist dieser ausgeschaltet. Dies bedeutet, dass für den Anwender lediglich die Ergebnisse der Planung und Variationsanalyse ausgegeben werden. 
Wird in diesem Fall der Logmodus aktiviert, so wird bei Betätigen der Option (3) Planung oder (10) Variationsanalyse der Log, also die Durchführung der Planung durchgespielt und für den Anwender angezeigt. Dies kommt für Sie vor allem dann in Betracht, wenn Sie als Anwender nachvollziehen wollen, wie die einzelnen Zuordnungen zustande gekommen sind und weshalb diese in dieser Art durch die Software durchgeführt wurden. 
    
    (12) Anwendung beenden
Über diese Option schließt sich die Anwendung. 
    
BENUTZERHINWEISE:
Die Software stellt mehrere Zuordnungsmöglichkeiten der auszuleihenden Kunstwerke in die jeweiligen Räume zusammen und gibt anschließend die bestmögliche Zuordnung aus.


Dabei werden diverse Bedingungen, wie:
    -die individuellen Eigenschaften der Kunstwerke (z.B. maximale Raumtemperatur bei Bildern)
    -die gesetzte Kostenobergrenze
    -das gesetzte Schwerpunktthema 
    -das gesetzte Qualitätsgewicht
  
berücksichtigt. Im folgenden werden die einzelnen Begrifflichkeiten der festzulegenden Bedingungen näher erläutert: 

    Schwerpunktthema:
Das Programm ermöglicht es, ein Schwerpunktthema für die zu planende Ausstellung festzulegen. Ein Festlegen hat direkten Einfluss auf das Ergebnis der Planung. Ist ein Schwerpunktthema angegeben, so ist dieses in mindestens der Hälfte aller Räume vertreten. 
    
    Kostenobergrenze:
Das Programm ermöglicht es, eine Kostenobergrenze für die zu planende Ausstellung festzulegen. Ist eine Kostenobergrenze angegeben, so wird dies in der Planung berücksichtigt, damit diese nicht überschritten wird. 
    
    Qualitätsgewicht:
Über das Qualitätsgewicht lässt sich die Relevanz der Attraktivität der gesetzten Kunstwerke (Qualität) im Verhältnis zu der Dichte der gesetzten Kunstwerke an  der Wand (Quantität) für die zu planende Ausstellung festlegen. Dabei ist immer ein Wert zwischen 0 & 1 anzugeben.  
Ist der Wert somit näher an der 1, so wird bei der Aufstellung der Fokus auf möglichst attraktive Kunstwerke gelegt. Dafür wird jedoch möglicherweise die Anzahl der auszustellenden Kunstwerke sinken und folglich werden die Räume gegebenenfalls weniger gefüllt. Durch die Auswahl des Qualitätsgewichts wird zudem die Auswahl des Raumes für das jeweilige Kunstwerk beeinflusst. 
Die Qualitätsgewichtung fließt bei unserer Lösungsfindung auf zweierlei Weisen ein:
    - Bei der Suche nach dem jeweils noch besten möglichen Kunstwerk für einen Raum. Hierbei bestimmt das Gewicht den Einfluss folgender Gütekriterien:
        ○ Attraktivität des Kunstwerkes
        ○ Attraktivität pro Kosten des Kunstwerkes
    - Bei der Lösungserweiterung wird immer wieder der Raum mit dem größten Verbesserungsbedarf gesucht.  Hierbei gewichten wir folgende Bedürftigkeitskriterien:
        ○ Geringer Mittelwert der Attraktivität der Kunstwerke (leere Räume berücksichtigen wir mit einem Attraktivitätswert von 0)
        ○ Geringer Anteil der belegten Wandfläche des Raumes
     Diese Aspekte der Gewichtung steuern wir mit einem einzigen Parameter. Eine hohe Qualitätsgewichtung rückt die Attraktivität in den Mittelpunkt,sodass tendenziell die Raumbelegung (Quantität) abnimmt, und umgekehrt.
    --> Beispiel: "0.7" - Priorisierung attraktiverer Kunstwerke unter Berücksichtigung, dass die Räume ggf. weniger dicht belegt werden. 
Als Standard ist der Wert "0.5" angegeben, sodass es sich um ein ausgeglichenes Attraktivitäts-und Raumbelegungsverhältnis handelt.
    
    
Einige Mehrwerte unserer Lösung hinsichtlich der Minimalanforderungen der Studienleistung:
- Menüsteuerung
- HTML-Ausgabe
- Zufalls-basierter Ansatz bei der Raumauswahl und Auswahl der besten Option (somit tendenziell bessere Lösungsfindung)
- Validierungsmethoden innerhalb der Klasse Zuordnung (erhöht die Verlässlichkeit der Lösungsfindung)
- Möglichkeit für Kostenobergrenze als Konfigurationsparameter
- Abbildung komplexerer Evaluation bei der Raum- bzw. Kunstwerkauswahl (gewichtete Güte als Konfigurationsparameter)
- Variationsanalyse als praktisches Feature, um z.B. ein geeignetes oder das beste Schwerpunktthema zu finden
- Log-Modus für Planung/Variationsanalyse (an-/ausschaltbar)
    
