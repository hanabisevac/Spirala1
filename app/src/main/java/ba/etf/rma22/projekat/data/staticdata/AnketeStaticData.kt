package com.example.spirala1.data

import ba.etf.rma22.projekat.data.models.Anketa
import java.util.*

/*
val anketa1 = Anketa("Anketa 1", "Istrazivanje 1", datumi(2019, 2, 8), datumi(2020, 4, 9 ), datumi(2019, 10, 10),  5, "Grupa 2", 1.0F)
val anketa2 = Anketa("Anketa 2", "Istrazivanje 2", datumi(2019, 3, 7), datumi(2022, 10, 10),  datumi(2019, 9, 21), 5, "Grupa 5", 1.0F)
val anketa3 = Anketa("Anketa 3", "Istrazivanje 3", datumi(2020, 9, 8), datumi(2021, 10, 9 ), datumi(2020, 9, 11), 5, "Grupa 7", 1.0F)
val anketa4 = Anketa("Anketa 4", "Istrazivanje 4", datumi(2021, 3, 3), datumi(2021, 4, 3 ), null, 5, "Grupa 8", 0.6F)
val anketa5 = Anketa("Anketa 5", "Istrazivanje 5", datumi(2021, 4, 3), datumi(2021, 5, 3 ), null, 5, "Grupa 1", 0.7F)
val anketa6 = Anketa("Anketa 6", "Istrazivanje 6", datumi(2022, 4, 3), datumi(2022, 9, 5), null, 5, "Grupa 3", 0.6F)
val anketa7 = Anketa("Anketa 7", "Istrazivanje 4", datumi(2022, 1, 19), datumi(2022, 12, 21), null, 5, "Grupa 4", 0.99F)
val anketa8 = Anketa("Anketa 8", "Istrazivanje 5", datumi(2022, 10, 10), datumi(2022, 12, 12), null, 5, "Grupa 6", 0.0F)
val anketa9 = Anketa("Anketa 9", "Istrazivanje 3", datumi(2022, 11, 11), datumi(2022, 12, 23), null, 5, "Grupa 9", 0.0F)
val anketa10 = Anketa("Anketa 10", "Istrazivanje 6", datumi(2022, 1, 20), datumi(2022, 2, 14), null, 5, "Grupa 10", 0.33F)
val anketa11 = Anketa("Anketa 11", "Istrazivanje 7", datumi(2022, 1, 28), datumi(2022, 8, 8), null, 5, "Grupa 11",0.46F)
val anketa12 = Anketa("Anketa 12", "Istrazivanje 8", datumi(2022, 9, 9), datumi(2022, 11, 4), null, 4, "Grupa 11", 0.0F)
*/

val anketa1 = Anketa("Ocjena nastavnickog rada", "Istrazivanje o kvaliteti nastave", datumi(2020, 9, 1), datumi(2021, 7, 21), datumi(2021, 5, 24),  5, "Grupa 1", 1.0F)
val anketa2 = Anketa("Politicka opredjeljenost", "Istrazivanje javnog mnjenja", datumi(2022, 10, 1), datumi(2022, 11, 1 ), null,  5, "Grupa 2", 0.0F)
val anketa3 = Anketa("Rezultati izbora", "Istrazivanje javnog mnjenja", datumi(2022, 10, 2), datumi(2022, 10, 2 ), null,  5, "Grupa 3", 0.0F)
val anketa4 = Anketa("Izbjeglice u BiH", "Drustveno istrazivanje", datumi(2019, 12, 21), datumi(2020, 12, 21), null,  5, "Grupa 4", 0.33F)
val anketa5 = Anketa("Psi lutalice", "Istrazivanje u svrhu zastite zivotinja", datumi(2022, 4, 15), datumi(2020, 11, 21 ), null,  5, "Grupa 5", 0.53F)
val anketa6 = Anketa("Lov", "Istrazivanje u svrhu zastite zivotinja", datumi(2021, 3, 14), datumi(2021, 4, 10 ), datumi(2021, 3, 16),  5, "Grupa 6", 1.0F)
val anketa7 = Anketa("Odrzavanje online nastave", "Istrazivanje o kvaliteti nastave", datumi(2022, 2, 23), datumi(2022, 3, 4), null,  5, "Grupa 7", 0.71F)
val anketa8 = Anketa("Odlazak mladih iz BiH", "Drustveno istrazivanje", datumi(2022, 7, 15), datumi(2022, 8, 16), null,  5, "Grupa 8", 0.0F)
val anketa9 = Anketa("Zabrana pusenja", "Drustveno istrazivanje", datumi(2022, 3, 30), datumi(2022, 4, 1), null,  5, "Grupa 9", 0.45F)
val anketa10 = Anketa("Kucni ljubimci", "Istrazivanje o zivotinjama", datumi(2022, 2, 14), datumi(2022, 9, 7), null,  5, "Grupa 10", 0.87F)
val anketa11 = Anketa("Sigurnost na internetu", "Istrazivanje o cyberbullying-u", datumi(2022, 1, 1), datumi(2022, 2, 17 ),null,  5, "Grupa 11", 0.21F)
val anketa12 = Anketa("Cyber nasilje", "Istrazivanje o cyberbullying-u", datumi(2022, 3, 12), datumi(2022, 9, 18), datumi(2022, 3, 21),  5, "Grupa 12", 1.0F)
val anketa13 = Anketa("Divlje zivotinje", "Istrazivanje o zivotinjama", datumi(2022, 3, 30), datumi(2022, 6, 25), null,  5, "Grupa 13", 0.67F)
val anketa14 = Anketa("Izborni predmeti", "Istrazivanje o studentskim preferencijama", datumi(2021, 9, 1), datumi(2022, 9, 1 ), datumi(2021, 10, 2),  5, "Grupa 14", 1.0F)
val anketa15 = Anketa("Drustvene mreze", "Istrazivanje o zastupljenosti drustvenih mreza", datumi(2022, 9, 21), datumi(2022, 12, 21 ), null,  5, "Grupa 15", 0.0F)
val anketa16 = Anketa("Izvori informacija", "Istrazivanje o uticaju medija", datumi(2021, 1, 24), datumi(2022, 3, 25), datumi(2021, 4, 13), 5, "Grupa 16", 1.0F)


fun listaAnketa() : List<Anketa> {
    return listOf(
            anketa2, anketa5, anketa10, anketa16, anketa1, anketa4, anketa3, anketa12, anketa8, anketa7, anketa6, anketa11, anketa9,  anketa13, anketa14, anketa15
    )
}

fun datumi(year: Int, month: Int, day: Int): Date {
        val cal: Calendar = Calendar.getInstance()
        cal.set(year, month-1, day)
        return cal.time;
}