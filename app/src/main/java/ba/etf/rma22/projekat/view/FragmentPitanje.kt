package ba.etf.rma22.projekat.view

import android.annotation.SuppressLint
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
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.TrenutnaAnketaRepository
import ba.etf.rma22.projekat.viewmodel.*
import com.example.spirala1.R


class FragmentPitanje(val pitanje : Pitanje) : Fragment() {

    private lateinit var textPitanja : TextView
    private lateinit var listaOdgovora : ListView
    private lateinit var dugme : Button
    private lateinit var adapterZaListu : MojAdapterZaListu
    private var pitanjaAnketaViewModel = PitanjeAnketaViewModel()
    private var odgovorViewModel = OdgovorViewModel()
//    private val anketaPitanje = pitanjaAnketaViewModel
//        .dajPitanjeAnketuPoNazivuPitanja(pitanje.naziv, TrenutnaAnketaRepository.dajAnketu().naziv, TrenutnaAnketaRepository.dajAnketu().nazivIstrazivanja)

    private var progres : Float = 1.0F/TrenutnaAnketaRepository.dajBrojPitanja()
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
        adapterZaListu = MojAdapterZaListu(view.context, android.R.layout.simple_list_item_1, pitanje.opcije)
        textPitanja.text = pitanje.tekstPitanja
        listaOdgovora.adapter = adapterZaListu

        listaOdgovora.isEnabled = true
        odgovorViewModel.dajOdgovoreNaAnketu(TrenutnaAnketaRepository.dajAnketu().AnketumId){
            if(it != null){
                for(i in it.indices) {
                    if (it[i].pitanjeId == pitanje.id){
                        listaOdgovora.isEnabled = false
                    }
                }
            }
        }
        //if(TrenutnaAnketaRepository.dajAnketu().getStatus() == "plava" || TrenutnaAnketaRepository.dajAnketu().getStatus() == "crvena") listaOdgovora.isEnabled = false
        //else if(anketaPitanje?.dajOdgovor() != null) listaOdgovora.isEnabled = false


        listaOdgovora.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>?, view1: View?, position: Int, p3: Long) {
                val odg = position
                val text : TextView? = view1?.findViewById(android.R.id.text1)
                if(stariView != null && stariView!=text) stariView!!.setTextColor(Color.parseColor("#FF000000"))
                stariView = text
                //if(brojac == 0) TrenutnaAnketaRepository.brojac = 1
                text!!.setTextColor(Color.parseColor("#0000FF"))
                odgovorViewModel.upisiOdgovor(TrenutnaAnketaRepository.dajAnketu().id, pitanje.id, odg){
                    //vraca novi progres
                    TrenutnaAnketaRepository.postaviProgres(it)
                    //TrenutnaAnketaRepository.brojac = 0
                    println("Novi progres je "+it)
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



    inner class MojAdapterZaListu(context : Context, @LayoutRes private val layoutRes : Int, private val lista : List<String>)
        : ArrayAdapter<String>(context, layoutRes, lista){

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var novi = convertView
            novi = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
            val textView : TextView = novi.findViewById(android.R.id.text1)
            val opcija = lista[position]
            textView.text = opcija
            odgovorViewModel.dajOdgovoreNaAnketu(TrenutnaAnketaRepository.dajAnketu().AnketumId){
                if(it != null){
                    TrenutnaAnketaRepository.postaviProgres(TrenutnaAnketaRepository.dajAnketu().progres)
                    for(i in it.indices){
                        if(it[i].pitanjeId == pitanje.id && it[i].odgovoreno == position) {
                            textView.setTextColor(Color.parseColor("#0000FF"))
                            break
                        }
                    }
                }
            }
            return novi
            }

        }

    companion object {

    }

}
