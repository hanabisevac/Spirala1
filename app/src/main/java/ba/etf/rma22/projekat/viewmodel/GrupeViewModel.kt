package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.repositories.GrupaRepository

class GrupeViewModel {

    fun getGrupeByIstrazivanje(str : String) : List<Grupa> {
        return GrupaRepository.getGroupsByIstrazivanje(str)
    }
}