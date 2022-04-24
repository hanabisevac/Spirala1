package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa

interface Communicator {
    fun otvoriNoviFragment(anketa : Anketa)

    fun prebaciFragment()

    fun vratiNaPocetnu()
}