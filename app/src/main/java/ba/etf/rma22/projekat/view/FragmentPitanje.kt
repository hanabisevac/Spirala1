package ba.etf.rma22.projekat.view

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.Communicator
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PredanaAnketa
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import com.example.spirala1.R


class FragmentPitanje(val pitanje : Pitanje, val size : Int) : Fragment() {

    private lateinit var textPitanja : TextView
    private lateinit var listaOdgovora : ListView
    private lateinit var dugme : Button
    private lateinit var adapterZaListu : MojAdapterZaListu
    private var pitanjaAnketaViewModel = PitanjeAnketaViewModel()
    //private val anketaViewModel = AnketaViewModel()
    private val anketaPitanje = pitanjaAnketaViewModel.dajPitanjeAnketuPoNazivuPitanja(pitanje.naziv)

    private var progres : Float = 1.0F/size
    private var brojac : Int = 0
    private lateinit var communicator : Communicator
    var stariView : TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pitanje, container, false)
        textPitanja = view.findViewById(R.id.tekstPitanja)
        listaOdgovora = view.findViewById(R.id.odgovoriLista)
        dugme = view.findViewById(R.id.dugmeZaustavi)
        adapterZaListu = MojAdapterZaListu(listaOdgovora.context, R.layout.odgovor, pitanje.opcije)
        textPitanja.text = pitanje.tekst
        listaOdgovora.adapter = adapterZaListu

        if(PredanaAnketa.dajAnketu().getStatus() != "plava" && PredanaAnketa.dajAnketu().getStatus() != "crvena"  && anketaPitanje?.dajOdgovor() == null){
            listaOdgovora.onItemClickListener = object : AdapterView.OnItemClickListener {
                override fun onItemClick(adapterView: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                    val odg = adapterView?.getItemAtPosition(position).toString()
                    val text : TextView = view?.findViewById(R.id.odgovori)!!
                    if(stariView != null && stariView!=text) stariView!!.setTextColor(Color.parseColor("#FF000000"))
                    stariView = text
                    if(brojac == 0) updateProgres()
                    text.setTextColor(Color.parseColor("#0000FF"))
                    anketaPitanje?.postaviOdgovor(odg)
                    brojac++
                }
            }
        }

        communicator = activity as Communicator
        dugme.setOnClickListener {
            communicator.vratiNaPocetnu()
        }

        return view
    }

    fun updateProgres() {
        //val anketa : Anketa? = anketaViewModel.getAnketu(anketaPitanje!!.anketa, anketaPitanje.istrazivanje)
        //anketa!!.setProgress(progres)
        PredanaAnketa.postaviProgres(progres)

    }

    inner class MojAdapterZaListu(context : Context, @LayoutRes private val layoutRes : Int, private val lista : List<String>)
        : ArrayAdapter<String>(context, layoutRes, lista){

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var novi = convertView
            novi = LayoutInflater.from(context).inflate(R.layout.odgovor, parent, false)
            val textView : TextView = novi.findViewById(R.id.odgovori)
            val opcija = lista[position]
            textView.text = opcija
            if(anketaPitanje!!.dajOdgovor()!=null && opcija == anketaPitanje.dajOdgovor()) textView.setTextColor(Color.parseColor("#0000FF"))
            return novi
            }

        }

    companion object {

    }

}