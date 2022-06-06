
package ba.etf.rma22.projekat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ba.etf.rma22.projekat.Communicator
import ba.etf.rma22.projekat.data.repositories.TrenutnaAnketaRepository
import com.example.spirala1.R
import java.util.*
import kotlin.math.round

class FragmentPredaj : Fragment() {
    private lateinit var progresText : TextView
    private lateinit var dugme : Button
    private lateinit var communicator: Communicator


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
        //if(TrenutnaAnketaRepository.dajAnketu().getStatus()=="plava" || TrenutnaAnketaRepository.dajAnketu().getStatus()=="crvena") dugme.isEnabled = false
        dugme.setOnClickListener{
            //TrenutnaAnketaRepository.postaviDatum(Date())
            //val poruka = "Završili ste anketu "+ TrenutnaAnketaRepository.dajAnketu().naziv + " u okviru istraživanja " + TrenutnaAnketaRepository.dajAnketu().nazivIstrazivanja
            val poruka = "Zavrsili ste anketu"
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
        fun newInstance() : FragmentPredaj = FragmentPredaj()

    }
}
