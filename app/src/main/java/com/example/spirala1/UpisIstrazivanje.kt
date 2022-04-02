package com.example.spirala1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.spirala1.viewmodel.IstrazivanjeViewModel
import android.widget.ArrayAdapter
import com.example.spirala1.viewmodel.GrupeViewModel

class UpisIstrazivanje : AppCompatActivity() {
    lateinit var spinZaGodine : Spinner
    lateinit var spinZaIstrazivanja : Spinner
    lateinit var spinZaGrupe : Spinner

    private val istrazivanjeViewModel = IstrazivanjeViewModel()
    private val grupeViewModel = GrupeViewModel()
    private lateinit var adapterZaSpinner : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_istrazivanje)
        spinZaGodine = findViewById(R.id.odabirGodina)
        spinZaIstrazivanja = findViewById(R.id.odabirIstrazivanja)
        spinZaGrupe = findViewById(R.id.odabirGrupa)

//        adapterZaSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf())
//        adapterZaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinZaIstrazivanja.adapter = adapterZaSpinner

        spinZaGodine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                val godina = str.toInt()
                val lista = getStrings(godina)
                napuniSpinnerIstrazivanje(lista)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //kasnije
            }

            fun getStrings(godina : Int) : List<String> {
                val lista = istrazivanjeViewModel.getIstrazivanjePoGodini(godina)
                val nova = mutableListOf<String>()
                for (i in 0..lista.size-1){
                    nova.add(lista[i].naziv)
                }
                return nova
            }

        }

        spinZaIstrazivanja.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                val lista = getGrupe(str)
                napuniSpinnerGrupe(lista)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            fun getGrupe(str : String) : List<String>{
                val lista = grupeViewModel.getGrupeByIstrazivanje(str)
                val nova = mutableListOf<String>()
                for (i in 0..lista.size-1){
                    nova.add(lista[i].naziv)
                }
                return nova
            }

        }
    }

    fun napuniSpinnerIstrazivanje(lista : List<String>) {
        adapterZaSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapterZaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinZaIstrazivanja.adapter = adapterZaSpinner
    }
    fun napuniSpinnerGrupe(lista : List<String>) {
        adapterZaSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        adapterZaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinZaGrupe.adapter = adapterZaSpinner
    }
}