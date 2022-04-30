package ba.etf.rma22.projekat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import ba.etf.rma22.projekat.Communicator
import ba.etf.rma22.projekat.data.repositories.KorisnikRepository
import ba.etf.rma22.projekat.viewmodel.GrupeViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import com.example.spirala1.R


class FragmentIstrazivanje : Fragment() {
    lateinit var spinZaGodine : Spinner
    lateinit var spinZaIstrazivanja : Spinner
    lateinit var spinZaGrupe : Spinner
    lateinit var dugme : Button

    private val istrazivanjeViewModel = IstrazivanjeViewModel()
    private val grupeViewModel = GrupeViewModel()
    private lateinit var adapterZaSpinner : ArrayAdapter<String>

    private lateinit var communicator : Communicator

    companion object{
        private var pozicija : Int = 0

        fun update(broj : Int){
            this.pozicija = broj
        }
        fun dajPoz() : Int = this.pozicija

        fun newInstance() : FragmentIstrazivanje = FragmentIstrazivanje()
    }

    private var izbor1 : String = ""
    private var izbor2 : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_istrazivanje, container, false)
        spinZaGodine = view.findViewById(R.id.odabirGodina)
        spinZaIstrazivanja = view.findViewById(R.id.odabirIstrazivanja)
        spinZaGrupe = view.findViewById(R.id.odabirGrupa)
        dugme = view.findViewById(R.id.dodajIstrazivanjeDugme)

        spinZaGodine.setSelection(dajPoz())
        dugme.isClickable = false
        dugme.isEnabled = false

        spinZaGodine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, p3: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                val godina = str.toInt()
                val lista = getStrings(godina)
                napuniSpinnerIstrazivanje(lista, view)
                spinZaIstrazivanja.setSelection(0)
                dugme.isEnabled = false
                dugme.isClickable = false
                update(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


            fun getStrings(godina : Int) : List<String> {
                val lista = istrazivanjeViewModel.getIstrazivanjePoGodini(godina)
                val mojaIstrazivanja = istrazivanjeViewModel.getMojaIstrazivanja()

                val nova = mutableListOf<String>()
                nova.add(0, "Nista nije selectovano")
                for (i in lista.indices){
                    if(mojaIstrazivanja.contains(lista[i])) continue
                    nova.add(lista[i].naziv)
                }

                return nova
            }

        }

        spinZaIstrazivanja.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                val lista = getGrupe(str)
                napuniSpinnerGrupe(lista, view)
                spinZaGrupe.setSelection(0)
                izbor1 = str


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            fun getGrupe(str : String) : List<String>{
                val lista = grupeViewModel.getGrupeByIstrazivanje(str)
                val nova = mutableListOf<String>()
                for (element in lista){
                    nova.add(element.naziv)
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

        communicator = activity as Communicator
        dugme.setOnClickListener {
            val grupe = grupeViewModel.getSveGrupe()
            for(i in grupe.indices){
                if(grupe[i].naziv==izbor2 && grupe[i].nazivIstrazivanja==izbor1){
                    KorisnikRepository.addGrupu(grupe[i])
                    break
                }
            }
            val poruka = "Uspješno ste upisani u grupu "+izbor2+" istraživanja " +izbor1+"!"
            communicator.prebaciFragment(poruka)

        }

        return view
    }

    fun napuniSpinnerIstrazivanje(lista : List<String>, view : View) {
        adapterZaSpinner = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, lista)
        adapterZaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinZaIstrazivanja.adapter = adapterZaSpinner
    }


    fun napuniSpinnerGrupe(lista : List<String>, view : View) {
        adapterZaSpinner = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, lista)
        adapterZaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinZaGrupe.adapter = adapterZaSpinner
    }


}