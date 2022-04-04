package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

class IstrazivanjeViewModel {

    fun getIstrazivanjePoGodini(g : Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivanjeByGodina(g)
    }

    fun getMojaIstrazivanja() : List<Istrazivanje> {
        return IstrazivanjeRepository.getUpisani()
    }
}