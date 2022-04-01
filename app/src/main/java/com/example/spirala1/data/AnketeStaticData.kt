package com.example.spirala1.data

import java.util.*

fun listaAnketa() : List<Anketa> {
    return listOf(
            Anketa("Anketa 1", "Istrazivanje broj 1", datumi(2021, 4, 2), datumi(2021, 5, 2 ), datumi(2021, 4, 3),  5, "Grupa 1", 1.0F),
            Anketa("Anketa 2", "Istrazivanje broj 2", datumi(2021, 6, 5), datumi(2021, 7, 5 ),  datumi(2021, 6, 7), 5, "Grupa 2", 1.0F),
            Anketa("Anketa 3", "Istrazivanje broj 3", datumi(2022, 3, 3), datumi(2022, 3, 30 ), null, 5, "Grupa 3", 0.5F),
            Anketa("Anketa 4", "Istrazivanje broj 4", datumi(2020, 1, 5), datumi(2020, 2, 5 ), null, 5, "Grupa 4", 0.6F),
            Anketa("Anketa 5", "Istrazivanje broj 5", datumi(2022, 3, 18), datumi(2022, 4, 4 ), null, 5, "Grupa 5", 0.7F),
            Anketa("Anketa 6", "Istrazivanje broj 6", datumi(2022, 4, 17), datumi(2022, 5, 18), null, 5, "Grupa 6", 0.8F),
            Anketa("Anketa 7", "Istrazivanje broj 7", datumi(2019, 11, 7), datumi(2022, 11, 7 ), datumi(2020, 3, 18), 5, "Grupa 7", 0.99F),
            Anketa("Anketa 8", "Istrazivanje broj 8", datumi(2022, 8, 20), datumi(2022, 9, 20), null, 5, "Grupa 8", 0.4F) ,
            Anketa("Anketa 9", "Istrazivanje broj 9", datumi(2022, 3, 31), datumi(2022, 9, 20 ), null, 5, "Grupa 9", 0.0F)
//            Anketa("Anketa 10", "Istrazivanje broj 10", datumi(2019, 5, 19), datumi(2022, 3, 31 ), null, 5, "Grupa 10", 0.8F),
            )
}

fun datumi(year: Int, month: Int, day: Int): Date {
        val cal: Calendar = Calendar.getInstance()
        cal.set(year, month-1, day)
        return cal.time;
}