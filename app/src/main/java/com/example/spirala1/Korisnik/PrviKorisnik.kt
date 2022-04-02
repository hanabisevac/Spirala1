package com.example.spirala1.Korisnik

fun dajKorisnika() : Korisnik {
    return Korisnik("Hana", listaZaIstrazivanja())
}

fun listaZaIstrazivanja() : List<String> {
    return listOf<String>(
        "Istrazivanje 1",
        "Istrazivanje 2",
        "Istrazivanje 7"
    )
}
