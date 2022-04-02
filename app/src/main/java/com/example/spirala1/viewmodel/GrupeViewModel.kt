package com.example.spirala1.viewmodel

import com.example.spirala1.data.Grupa
import com.example.spirala1.data.GrupaRepository

class GrupeViewModel {

    fun getGrupeByIstrazivanje(str : String) : List<Grupa> {
        return GrupaRepository.getGroupByIstrazivanje(str)
    }
}