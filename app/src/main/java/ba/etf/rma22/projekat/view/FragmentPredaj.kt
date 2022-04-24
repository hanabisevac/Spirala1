package ba.etf.rma22.projekat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ba.etf.rma22.projekat.Communicator
import com.example.spirala1.R

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
        communicator = activity as Communicator
        dugme.setOnClickListener{
            communicator.vratiNaPocetnu()
        }
        return view
    }

    companion object {
        fun newInstance() : FragmentPredaj = FragmentPredaj()

    }
}