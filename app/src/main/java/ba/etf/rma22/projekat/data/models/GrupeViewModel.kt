package ba.etf.rma22.projekat.data.models

import ba.etf.rma22.projekat.data.grupe.Grupa
import ba.etf.rma22.projekat.data.repositories.GrupaRepository

class GrupeViewModel {

    fun getGrupeByIstrazivanje(str : String) : List<Grupa> {
        return GrupaRepository.getGroupByIstrazivanje(str)
    }
}