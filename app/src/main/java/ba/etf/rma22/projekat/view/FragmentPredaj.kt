package ba.etf.rma22.projekat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ba.etf.rma22.projekat.Communicator
import ba.etf.rma22.projekat.data.models.PredanaAnketa
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
        val prog = PredanaAnketa.dajAnketu().progres
        var p : Int = (round(prog*10) *10).toInt()
        if((round(prog*10)).toInt() %2 != 0) p +=10
        progresText.text = ""+ p + "%"
        communicator = activity as Communicator
        dugme.isEnabled = true
        if(PredanaAnketa.dajAnketu().getStatus()=="plava" || PredanaAnketa.dajAnketu().getStatus()=="crvena") dugme.isEnabled = false
        dugme.setOnClickListener{
            PredanaAnketa.postaviDatum(Date())
            val poruka = "Završili ste anketu "+ PredanaAnketa.dajAnketu().naziv + " u okviru istraživanja " + PredanaAnketa.dajAnketu().nazivIstrazivanja
            communicator.prebaciFragment(poruka)
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        val prog = PredanaAnketa.dajAnketu().progres
        var p : Int = (round(prog*10) *10).toInt()
        if((round(prog*10)).toInt() %2 != 0) p +=10
        progresText.text = ""+ p + "%"
    }

    companion object {
        fun newInstance() : FragmentPredaj = FragmentPredaj()

    }
}