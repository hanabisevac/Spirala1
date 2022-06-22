package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AccViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun upisiStudenta(grupa : String, porukica : (str : String)->Unit) {
        scope.launch {
            val grupe = IstrazivanjeIGrupaRepository.getGrupe()
            var id = 0
            for(i in grupe!!.indices){
                if(grupe[i].naziv == grupa){
                    id=grupe[i].id
                    break
                }
            }
            val response = IstrazivanjeIGrupaRepository.upisiUGrupu(id)
            if(response == true) porukica.invoke("Student "+AccountRepository.student.student+" je upisan u grupu "+grupa)
            else porukica.invoke("Nije moguce upisati")
        }
    }

    fun postaviHash(s : String) {
        scope.launch {
            AccountRepository.postaviHash(s)
        }
    }
}