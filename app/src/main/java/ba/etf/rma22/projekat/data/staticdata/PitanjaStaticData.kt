package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Pitanje
//1
val p1 = Pitanje("Pitanje 1", "Najdraza zivotinja", listOf("Macka", "Pas"))
val p2 = Pitanje("Pitanje 2", "Imate li kucnog ljubimca?", listOf("Da", "Ne"))
val p3 = Pitanje("Pitanje 3", "Koliko ljubimaca imate?", listOf("0", "1", "Vise od 1"))
val p4 = Pitanje("Pitanje 4", "Da li bi ste usvojili tigra?", listOf("Da", "Ne"))
//2
val p5 = Pitanje("Pitanje 5", "Najbolji film?", listOf("Green mile", "Godfather", "Lion King"))
val p6 = Pitanje("Pitanje 6", "Najdrazi glumac:", listOf("Leonardo diCaprio", "Jhonny Depp", "Brad Pitt"))
val p7 = Pitanje("Pitanje 7", "Najdraza glumica:", listOf("Angelina Joile", "Scarlett Johansson", "Meryl Streep"))
//3
val p8 = Pitanje("Pitanje 8", "Koja ste Hogwarts house", listOf("Gryffindor", "Slytherin", "Ravenclaw", "Hufflepuff"))
val p9 = Pitanje("Pitanje 9", "Najdrazi lik iz Hp", listOf("Harry Potter", "Draco Malfoy", "Ron Weasly", "Hermione Granger"))
val p10 = Pitanje("Pitanje 10", "Najbolji Weasly?", listOf("Bill", "Charlie", "Percy", "Fred", "George", "Ron", "Ginny"))
//4
val p11 = Pitanje("Pitanje 11", "Najbolji Skywalker?", listOf("Luke", "Leia", "Anakin", "Padme"))
val p12 = Pitanje("Pitanje 12", "Da li su bolji...", listOf("Prequels", "Sequels"))
//5
val p13 = Pitanje("Pitanje 13", "Najljepsi grad na svijetu", listOf("Sarajevo", "New York", "Rim", "Pariz"))
val p14 = Pitanje("Pitanje 14", "Najljepsi bosanskohercegovacki grad?", listOf("Sarajevo", "Mostar", "Jajce", "Neum"))
val p15 = Pitanje("Pitanje 15", "Najbolji cevapi su u...", listOf("Travnik", "Sarajevo"))
//6
val p16 = Pitanje("Pitanje 16", "Koliko je 2*2?", listOf("4", "6", "2"))
val p17 = Pitanje("Pitanje 17", "Koja zivotinja leti?", listOf("Pingvin", "Lasta", "Kokos"))
//7
val p18 = Pitanje("Pitanje 18", "Team?", listOf("Edward", "Jacob"))
val p19 = Pitanje("Pitanje 19", "Team?", listOf("Stefan", "Damon"))
val p20 = Pitanje("Pitanje 20", "Team?", listOf("Pacey !!", "Dawson"))
//8
val p21 = Pitanje("Pitanje 21", "Najbolji ljubavni trouglovi u kdramama?", listOf("True Beauty", "The heirs", "Start Up", "Strong woman", "Just between lovers", "Love alarm"))
val p22 = Pitanje("Pitanje 22", "Najbolja ozbiljna kdrama?", listOf("Youth of may", "Crash landing on you", "Flowers of evil"))
val p23 = Pitanje("Pitanje 23", "Najbolja teen kdrama?", listOf("True Beauty", "Weightlifting fairy", "The heirs"))
//9
val p24 = Pitanje("Pitanje 24", "Ko je porazio Thanosa?", listOf("Iron Man", "Hulk", "Diskutabilno"))
val p25 = Pitanje("Pitanje 25", "Ko je Tony Stark?", listOf("Batman", "Spiderman", "Iron man"))
val p26 = Pitanje("Pitanje 26", "Najbolji spiderman?", listOf("Toby Maguire", "Andrew Garfield", "Tom Holland"))
//10
val p27 = Pitanje("Pitanje 27", "Zima ili ljeto?", listOf("Ljeto", "Zima"))
val p28 = Pitanje("Pitanje 28", "Kafa ili caj?", listOf("Caj", "Kafa"))
val p29 = Pitanje("Pitanje 29", "Bijela ili tamna cokolada?", listOf("Bijela", "Tamna"))


fun dajListuPitanja() : List<Pitanje> {
    return listOf(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21,
    p22, p23, p24, p25, p26, p27, p28, p29)
}