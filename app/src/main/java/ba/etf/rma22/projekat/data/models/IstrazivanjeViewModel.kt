package ba.etf.rma22.projekat.data.models

import ba.etf.rma22.projekat.data.istrazivanja.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

class IstrazivanjeViewModel {

    fun getIstrazivanjePoGodini(g : Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivanjeByGodina(g)
    }
}