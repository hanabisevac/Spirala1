
package ba.etf.rma22.projekat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ba.etf.rma22.projekat.Communicator
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.KonekcijaRepository
import ba.etf.rma22.projekat.data.repositories.TrenutnaAnketaRepository
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import com.example.spirala1.R
import java.util.*
import kotlin.math.round

class FragmentPredaj(val anketa : Anketa) : Fragment() {

    private lateinit var progresText : TextView
    private lateinit var dugme : Button
    private lateinit var communicator: Communicator
    private val anketaViewModel = AnketaViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_predaj, container, false)
        progresText = view.findViewById(R.id.progresTekst)
        dugme = view.findViewById(R.id.dugmePredaj)
        val prog = TrenutnaAnketaRepository.dajProgres()
        progresText.text = ""+ prog + "%"
        communicator = activity as Communicator
        dugme.isEnabled = true
        if(KonekcijaRepository.getKonekcija()){
            anketaViewModel.getUradjene {
                if(it.isEmpty()) dugme.isEnabled = true
                else{
                    if(it.contains(anketa)) dugme.isEnabled = false
                }
            }
        }
        else dugme.isEnabled = false

        dugme.setOnClickListener{
            //val poruka = "Završili ste anketu "+ TrenutnaAnketaRepository.dajAnketu().naziv + " u okviru istraživanja " + TrenutnaAnketaRepository.dajAnketu().nazivIstrazivanja
            val poruka = "Zavrsili ste anketu "+anketa.naziv +" u sklopu istraživanja "+anketa.nazivIstrazivanja+" grupe "+anketa.nazivGrupe
            communicator.prebaciFragment(poruka)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        val p = TrenutnaAnketaRepository.dajProgres()
        progresText.text = ""+ p + "%"
    }

    companion object {

    }
}
