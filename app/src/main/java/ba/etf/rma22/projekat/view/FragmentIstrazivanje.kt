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
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.KonekcijaRepository
import ba.etf.rma22.projekat.viewmodel.*
import com.example.spirala1.R


class FragmentIstrazivanje : Fragment() {
    //lateinit var spinZaGodine : Spinner
    //lateinit var spinZaIstrazivanja : Spinner
    lateinit var spinZaGrupe : Spinner
    lateinit var dugme : Button

    private val istrazivanjeViewModel = IstrazivanjeViewModel()
    private val grupeViewModel = GrupeViewModel()
    private lateinit var adapterZaSpinner : ArrayAdapter<String>
    private val accViewModel = AccViewModel()

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
        //spinZaGodine = view.findViewById(R.id.odabirGodina)
        //spinZaIstrazivanja = view.findViewById(R.id.odabirIstrazivanja)
        spinZaGrupe = view.findViewById(R.id.odabirGrupa)
        dugme = view.findViewById(R.id.dodajIstrazivanjeDugme)

        //spinZaGodine.setSelection(dajPoz())
        dugme.isClickable = false
        dugme.isEnabled = false

        /*spinZaGodine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, p3: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                val godina = str.toInt()
                istrazivanjeViewModel.getIstrazivanjePoGodini(godina, istrazivanjePoGodini = ::istrazivanjePoGodini)
                spinZaIstrazivanja.setSelection(0)
                dugme.isEnabled = false
                dugme.isClickable = false
                update(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            fun istrazivanjePoGodini(istrazivanja  : List<Istrazivanje>){
                istrazivanjeViewModel.getSlobodnaIstrazivanja(istrazivanja, slobodnaIstrazivanja = ::slobodnaIstrazivanja)
            }

            fun slobodnaIstrazivanja(istrazivanja: List<Istrazivanje>){
                val lista = mutableListOf<String>()
                lista.add(0, "Nista nije selectovano")
                for(i in istrazivanja.indices){
                    lista.add(istrazivanja[i].naziv)
                }
                napuniSpinnerIstrazivanje(lista, view)
            }

        }

        spinZaIstrazivanja.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                //val lista = getGrupe(str)
                grupeViewModel.getGrupeByIstrazivanje(str, getGrupeZaIstrazivanja = ::getGrupeZaIstrazivanja)
                //napuniSpinnerGrupe(lista, view)
                spinZaGrupe.setSelection(0)
                izbor1 = str

            }

            fun getGrupeZaIstrazivanja(grupe : List<Grupa>){
                grupeViewModel.getSlobodneGrupe(grupe, nisuStudentoveGrupe = ::nisuStudentoveGrupe)
            }

            fun nisuStudentoveGrupe(grupe : List<Grupa>){
                val lista = mutableListOf<String>()
                lista.add(0, "Nista nije selectovano")
                for(i in grupe.indices){
                    lista.add(grupe[i].naziv)
                }
                napuniSpinnerGrupe(lista, view)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }*/

        grupeViewModel.getSlobodneGrupe(){
            val string = mutableListOf<String>()
            for (i in it.indices) {
                string.add(it[i].naziv)
            }
            string.add(0, "Nista nije selectovano")
            napuniSpinnerGrupe(string, view)
        }

        spinZaGrupe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val str : String = adapterView?.getItemAtPosition(position).toString()
                izbor2 = str
                dugme.isEnabled = false
                dugme.isClickable = false
                if(izbor2 != "Nista nije selectovano" && izbor2!="" && KonekcijaRepository.getKonekcija()){
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
            //ovo sve preko upisi studenta
            accViewModel.upisiStudenta(izbor2){
                communicator.prebaciFragment(it)
            }

            /*val grupe = grupeViewModel.getSveGrupe()
            for(i in grupe.indices){
                if(grupe[i].naziv==izbor2 && grupe[i].nazivIstrazivanja==izbor1){
                    KorisnikRepository.addGrupu(grupe[i])
                    break
                }
            }
            val poruka = "Uspje??no ste upisani u grupu "+izbor2+" istra??ivanja " +izbor1+"!"*/
            //communicator.prebaciFragment(poruka)

        }

        return view
    }

    /*fun porukica(str : String){
        communicator.prebaciFragment(str)
    }*/



    /*fun napuniSpinnerIstrazivanje(lista : List<String>, view : View) {
        adapterZaSpinner = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, lista)
        adapterZaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinZaIstrazivanja.adapter = adapterZaSpinner
    }*/


    fun napuniSpinnerGrupe(lista : List<String>, view : View) {
        adapterZaSpinner = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, lista)
        adapterZaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinZaGrupe.adapter = adapterZaSpinner
    }


}
