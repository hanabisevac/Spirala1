package ba.etf.rma22.projekat


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import android.widget.ArrayAdapter
import android.widget.Button
import ba.etf.rma22.projekat.data.repositories.KorisnikRepository
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import com.example.spirala1.R
import ba.etf.rma22.projekat.viewmodel.GrupeViewModel

class UpisIstrazivanje : AppCompatActivity() {
    lateinit var spinZaGodine : Spinner
    lateinit var spinZaIstrazivanja : Spinner
    lateinit var spinZaGrupe : Spinner
    lateinit var dugme : Button

    private val istrazivanjeViewModel = IstrazivanjeViewModel()
    private val grupeViewModel = GrupeViewModel()
    private val anketeViewModel = AnketaViewModel()
    private lateinit var adapterZaSpinner : ArrayAdapter<String>

    companion object{
        private var pozicija : Int = 0

        fun update(broj : Int){
            this.pozicija = broj
        }
        fun dajPoz() : Int = this.pozicija
    }

    private var izbor1 : String = ""
    private var izbor2 : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_istrazivanje)
        spinZaGodine = findViewById(R.id.odabirGodina)
        spinZaIstrazivanja = findViewById(R.id.odabirIstrazivanja)
        spinZaGrupe = findViewById(R.id.odabirGrupa)
        dugme = findViewById(R.id.dodajIstrazivanjeDugme)

        spinZaGodine.setSelection(dajPoz())
        dugme.isClickable = false
        dugme.isEnabled = false




        spinZaGodine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                val godina = str.toInt()
                val lista = getStrings(godina)
                napuniSpinnerIstrazivanje(lista)
                spinZaIstrazivanja.setSelection(0)
                dugme.isClickable = false
                dugme.isEnabled = false
                update(position)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //kasnije
            }
            fun getStrings(godina : Int) : List<String> {
                val lista = istrazivanjeViewModel.getIstrazivanjePoGodini(godina)
                val mojaIstrazivanja = istrazivanjeViewModel.getMojaIstrazivanja()

                val nova = mutableListOf<String>()
                nova.add(0, "Nista nije selectovano")
                for (i in 0..lista.size-1){
                    if(mojaIstrazivanja.contains(lista[i])) continue
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
                spinZaGrupe.setSelection(0)
                izbor1 = str

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
                nova.add(0, "Nista nije selectovano")
                return nova
            }

        }

        spinZaGrupe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                izbor2 = str
                dugme.isEnabled = false
                dugme.isClickable = false
                if(izbor2 != "Nista nije selectovano" && izbor2!="" && izbor1 != "Nista nije selectovano" && izbor1 != ""){
                    dugme.isEnabled = true
                    dugme.isClickable = true
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        dugme.setOnClickListener {
            val grupe = grupeViewModel.getSveGrupe()
            for(i in 0..grupe.size-1){
                if(grupe[i].naziv==izbor2 && grupe[i].nazivIstrazivanja==izbor1){
                    KorisnikRepository.addGrupu(grupe[i])
                }
            }
            finish()
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


