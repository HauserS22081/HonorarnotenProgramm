package net.praktikum2024.honorarnoten;

import java.util.List;

public class Line {
    private String reStellung;
    private String auftraggeber;
    private String ansprechpartner;
    private String projektOderAuftrag;
    private String id;
    private String leistungszeitraum;
    private double stunden;
    private String leistungsKategorie;
    private double stundensatz;
    private double betrag;
    private String zahlungsbedingungen;
    private String ust;
    private double stundenZ2;
    private String leistungsKategorieZ2;
    private double stundensatzZ2;
    private double betragZ2;
    private int honorarnotenAnzahl;


    // Line line = new Line(honorarnotenAnzahl, kunde, auftraggeber, ansprechpartner, projektOderAuftrag, auftragsProjektID, leistungszeitraum, stunden,
    //                    leistungsKategorie, stundensatz, betrag, zahlungsbedingungen, ust);
    public Line(int honorarnotenAnzahl, String reStellung, String auftraggeber, String ansprechpartner, String projektOderAuftrag, String id, String leistungszeitraum,
                double stunden, String leistungsKategorie, double stundensatz, double betrag, String zahlungsbedingungen, String ust) {
        this.honorarnotenAnzahl = honorarnotenAnzahl;
        this.reStellung = reStellung;
        this.auftraggeber = auftraggeber;
        this.ansprechpartner = ansprechpartner;
        this.projektOderAuftrag = projektOderAuftrag;
        this.id = id;
        this.leistungszeitraum = leistungszeitraum;
        this.stunden = stunden;
        this.leistungsKategorie = leistungsKategorie;
        this.stundensatz = stundensatz;
        this.betrag = betrag;
        this.zahlungsbedingungen = zahlungsbedingungen;
        this.ust = ust;
    }
    public void addSecondLine(double stunden, String leistungsKategorie, double stundensatz, double betrag) {
        stundenZ2 = stunden;
        leistungsKategorieZ2 = leistungsKategorie;
        stundensatzZ2 = stundensatz;
        betragZ2 = betrag;
    }

//    public String serialize(List<String> fixDaten) throws Exception {
//
//
//        // int honorarnotenAnzahl, String reStellung, String auftraggeber, String ansprechpartner, String projektOderAuftrag, String id, String leistungszeitraum,
//        //                double stunden, String leistungsKategorie, double stundensatz, double betrag, String zahlungsbedingungen, String ust
//
//
//
//
//
//
//
//        // fixDaten: Staße	Ort	Land UID
//
//        String line = "";
//
//        for (String s : fixDaten) {
//            String[] parts = s.split(",");
//
//            if (!auftraggeber.equals(parts[0])) continue;
//
//            line = auftraggeber + ";";
//
//            // street
//            line += parts[1] + ";";
//
//            // ort
//            line += parts[2] + ";";
//
//            //land
//            line += parts[3] + ";";
//
//            //uid
//            line += parts[4] + ";";
//
//            break;
//        }
//
//        if (line.isEmpty()) throw new Exception();
//
//        // auftrags oder projekt id im fixen string?
//
//        line += (id.isEmpty() ? ";;" : "Ihre Auftrags-ID: " + ";" + id) + ";" + (ansprechpartner.isEmpty() ? ";;" : "Ihr Ansprechpartner: " + ";" + ansprechpartner) + ";";
//
//        line += honorarnotenAnzahl + ";";
//
//        // belegdatum: =heute()
//
//        line += leistungszeitraum + ";" + stunden + ";" + leistungsKategorie + ";" + stundensatz + ";" + betrag + ";";
//
//        if (leistungsKategorieZ2 != null) {
//            line += stundenZ2 + ";" + leistungsKategorieZ2 + ";" + stundensatzZ2 + ";" + betragZ2 + ";";
//        } else {
//            line += ";;;;";
//        }
//
//
//        double stundenGesamt = stunden + stundenZ2;
//
//        double betragGesamtNetto = betrag + betragZ2;
//
//        double mwstxProzent = 0;
//
//        boolean bmwst20Prozent = ust.equals("20%");
//        boolean bmwst14TageNetto = ust.equals("14 Tage netto");
//        boolean bmwst28TageNetto = ust.equals("28 Tage netto");
//        boolean bmwstReverseCharge = ust.equals("Reverse Charge");
//
//        if (bmwst20Prozent) {
//            mwstxProzent = betragGesamtNetto * 0.2;
//        }
//
//        double betragGesamtBrutto = betragGesamtNetto + mwstxProzent;
//
//        line += stundenGesamt + ";" + "Gesamtbetrag netto" + ";" + betragGesamtNetto + ";";
//
//        if (bmwst20Prozent) {
//            line += "Mehrwertssteuer 20%" + ";";
//        } else {
//            line += "Mehrwertssteuer 0%" + ";";
//        }
//
//
//        line += mwstxProzent + ";" + "Gesamtbetrag" + ";" + betragGesamtBrutto;
//
//
//
//        if (bmwstReverseCharge) {
//            line += ";" + "Reverse Charge - Übergang der Steuerschuld auf den Leistungsempfänger";
//        } else {
//            line += ";";
//        }
//
//
//        if (bmwst14TageNetto) {
//            line += ";" + "Zahlungsfrist: 14 Tage netto ab Rechnungseingang ohne Abzug.";
//        } else if (bmwst28TageNetto) {
//            line += ";" + "Zahlungsbedingung: 28 Tage netto ohne Abzug.";
//        } else {
//            line += ";";
//        }
//
//
//
//        return line;
//    }

    public String serialize(List<String> fixDaten) throws Exception {

        // Initialize the output line
        String line = "";

        // Loop through fixDaten to find the correct entry based on auftraggeber
        for (String s : fixDaten) {
            String[] parts = s.split(",");

            if (!auftraggeber.equals(parts[0])) continue;

            // Append the relevant parts to the line in the correct order
            line = auftraggeber + ";" + parts[1] + ";" + parts[2] + ";" + parts[3] + ";" + parts[4] + ";"; // Name; Straße; Ort; Land; UID
            break;
        }

        if (line.isEmpty()) {
            throw new Exception("Auftraggeber not found in fixDaten");
        }

        // Auftrags-/Projekt-ID and Ansprechpartner
        line += (id.isEmpty() ? " ;" : "Ihre Auftrags-ID: ;" + id + ";");
        line += (ansprechpartner.isEmpty() ? " ;" : "Ihr Ansprechpartner: ;" + ansprechpartner + ";");

        // Honorarnote Anzahl and Leistungszeitraum
        line += honorarnotenAnzahl + ";" + leistungszeitraum + ";";

        // Details for Z1
        line += stunden + ";" + leistungsKategorie + ";" + stundensatz + ";" + betrag + ";";

        // Details for Z2 (with null checks)
        if (leistungsKategorieZ2 != null) {
            line += stundenZ2 + ";" + leistungsKategorieZ2 + ";" + stundensatzZ2 + ";" + betragZ2 + ";";
        } else {
            line += " ; ; ; ;"; // Placeholder for Z2 fields if not present
        }

        // Gesamtstunden and Gesamtbetrag netto
        double stundenGesamt = stunden + (stundenZ2 != 0 ? stundenZ2 : 0);
        double betragGesamtNetto = betrag + (betragZ2 != 0 ? betragZ2 : 0);
        line += stundenGesamt + ";" + "Gesamtbetrag netto" + ";" + betragGesamtNetto + ";";

        // Mehrwertsteuer calculation
        double mwstxProzent = 0;
        if (ust.equals("20%")) {
            mwstxProzent = betragGesamtNetto * 0.2;
            line += "Mehrwertsteuer 20%;" + mwstxProzent + ";";
        } else {
            line += "Mehrwertsteuer 0%;0.0;";
        }

        // Gesamtbetrag Brutto
        double betragGesamtBrutto = betragGesamtNetto + mwstxProzent;
        line += "Gesamtbetrag;" + betragGesamtBrutto + ";";

        // Reverse Charge Placeholder
        line += (ust.equals("Reverse Charge") ? "Reverse Charge - Übergang der Steuerschuld auf den Leistungsempfänger;" : " ;");

        // Zahlungsbedingungen
        if (ust.equals("14 Tage netto")) {
            line += "Zahlungsfrist: 14 Tage netto ab Rechnungseingang ohne Abzug.;";
        } else if (ust.equals("28 Tage netto")) {
            line += "Zahlungsbedingung: 28 Tage netto ohne Abzug.;";
        } else {
            line += " ;"; // Placeholder for other cases
        }

        return line;
    }




}
