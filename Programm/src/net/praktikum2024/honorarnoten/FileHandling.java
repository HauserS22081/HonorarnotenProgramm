package net.praktikum2024.honorarnoten;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileHandling {
    private static List<String> input;
    private static Map<String, Line> output = new HashMap();
    private static List<String> fixDaten;
    private static List<String> honorarnoteAnzahl;


    public static final String INPUTFILENAME = "Abr_202409.csv";
    public static final String OUTPUTFILENAME = "output_UTF8.csv";
    public static final String FIXDATENFILENAME = "fixDaten.csv";
    public static final String HONORARNOTEANZAHLFILENAME = "honorarnoteAnzahl.csv";


    public static void openAndReadInFiles() {
        input = CSVHandling.readOutFile(INPUTFILENAME);
//        outputLines = CSVHandling.readOutFile(OUTPUTFILENAME);
        fixDaten = CSVHandling.readOutFile(FIXDATENFILENAME);
        honorarnoteAnzahl = CSVHandling.readOutFile(HONORARNOTEANZAHLFILENAME);
    }


    private static void updateHonorarnoteAnzahl(int neueAnzahl) {
        CSVHandling.writeInHonorarnotenanzahlFile("Honorarnotenanzahl", String.valueOf((neueAnzahl)), HONORARNOTEANZAHLFILENAME);
    }

    public static Map<String, Line> createOutputMap() throws Exception {
        int temp = Integer.parseInt(honorarnoteAnzahl.getFirst());

        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split(";");

            if (parts.length < 10) {
                System.out.println("Inputfehler: nicht genügend Einträge in einer Zeile");
                throw new Exception();
            }

            String reStellung = parts[0];
            String auftraggeber = parts[1];
            String ansprechpartner = parts[2];
            String projektOderAuftrag = parts[3];
            String auftragsProjektID = parts[4];
            String leistungszeitraum = parts[5];
            double stunden = Double.parseDouble(parts[6].replace(",", "."));
            String leistungsKategorie = parts[7];
            double stundensatz = Double.parseDouble(parts[8]);
            double betrag = stunden * stundensatz;
            // betrag mit komma -> sprache auf englisch deswegen wird es nicht genommen
            String zahlungsbedingungen = parts[10];
            String ust = parts[11];
            int honorarnotenAnzahltemp = temp;
            temp += 1;

            Line line = new Line(honorarnotenAnzahltemp, reStellung, auftraggeber, ansprechpartner, projektOderAuftrag, auftragsProjektID, leistungszeitraum, stunden,
                    leistungsKategorie, stundensatz, betrag, zahlungsbedingungen, ust);

            String id = auftragsProjektID;
            int idForIDLess = 0;
            if (auftragsProjektID.isEmpty()) {
                id = String.valueOf(idForIDLess++);
            }

            if (!output.containsKey(id)) output.put(id, line);
            else output.get(id).addSecondLine(stunden, leistungsKategorie, stundensatz, betrag);

        }

        updateHonorarnoteAnzahl(temp);
        return output;
    }

    public static void writeInOutputFile() {
        CSVHandling.writeOutputFile("Name" + ";" + "Staße" + ";" + "Ort" + ";" + "Land" + ";" + "UID" + ";" + "A-/P-ID_P" + ";" + "Auftrags-/Projekt-ID" + ";" +"AnspP_P" + ";" + "Ansprechpartner" + ";" + "HonorarnoteAnzahl" + ";" + "Belegdatum" + ";" + "Leistungszeitraum" + ";" + "h_Z1" + ";" + "Bez_Z1" + ";" + "EUR/h_Z1" + ";" + "Betr_Z1" + ";" + "h_Z2" + ";" + "Bez_Z2" + ";" + "EUR/h_Z2" + ";" + "Betr_Z2" + ";" + "h_ges" + ";" + "GesBetrag_N_P" + ";" + "GesBetrag_N" + ";" + "MWST20%_P" + ";" + "MWST_20%" + ";" + "GesBetrag_P" + ";" + "GesBetrag" + ";" + "ReverseCharge" + ";" + "Zahlungsfrist/bedingung");

        for (Map.Entry<String, Line> entry : output.entrySet()) {
            try {
                CSVHandling.writeOutputFile(entry.getValue().serialize(fixDaten));
            } catch (Exception e) {
                System.out.println("Error in serialize: fixDaten nicht gefunden");
                System.exit(-1);
            }
        }
    }

}
