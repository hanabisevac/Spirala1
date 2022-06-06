package ba.etf.rma22.projekat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ba.etf.rma22.projekat.data.models.Grupa
import com.example.spirala1.R

class FragmentPoruka(val poruka : String) : Fragment() {
    lateinit var textPoruka : TextView

/*override fun onCreate(savedInstanceState: Bundle?) {
    }*/



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_poruka, container, false)
        textPoruka = view.findViewById(R.id.tvPoruka)
        //val vel = KorisnikRepository.getGrupe().size
        //val grupa : Grupa = KorisnikRepository.getGrupe()[vel-1]
        textPoruka.text = poruka
        return view
    }

    companion object {

    }
}
