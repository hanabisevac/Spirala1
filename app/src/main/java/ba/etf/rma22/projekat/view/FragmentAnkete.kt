package ba.etf.rma22.projekat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.Communicator
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import com.example.spirala1.R

class FragmentAnkete : Fragment() {

    private lateinit var anketeAdapter: AnketaAdapter
    private lateinit var anketeRecyclerView: RecyclerView
    private lateinit var spiner: Spinner
    private val anketeViewModel = AnketaViewModel()
    private lateinit var communicator: Communicator




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ankete, container, false)
        communicator = activity as Communicator
        anketeRecyclerView = view.findViewById(R.id.listaAnketa)
        anketeRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        anketeAdapter = AnketaAdapter(listOf<Anketa>()) { anketa -> communicator.otvoriNoviFragment(anketa)}
        anketeRecyclerView.adapter = anketeAdapter
        spiner = view.findViewById(R.id.filterAnketa)
        anketeViewModel.dajSve { anketeAdapter.updateAnkete(it) }

        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                val izbor : String = adapterView?.getItemAtPosition(position).toString()
                brojac = position
                if(izbor == "Sve moje ankete") anketeViewModel.getUpisane { anketeAdapter.updateAnkete(it) }

                else if(izbor == "Urađene ankete")  anketeViewModel.getUradjene { anketeAdapter.updateAnkete(it) }

                else if(izbor == "Buduće ankete")  anketeViewModel.getBuduce { anketeAdapter.updateAnkete(it) }

                //else if(izbor == "Prošle ankete")  anketeViewModel.getAll(onSuccess = ::onSuccess)
                    //anketeAdapter.updateAnkete(anketeViewModel.getZavrseneAnkete())

                else anketeViewModel.dajSve { anketeAdapter.updateAnkete(it) }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        return view
    }


    override fun onResume() {
        super.onResume()
        spiner.setSelection(0)
        anketeViewModel.dajSve {
            anketeAdapter.updateAnkete(it)
        }
        //anketeAdapter.updateProgress()
    }

    companion object {
        fun newInstance() : FragmentAnkete = FragmentAnkete()
        var brojac : Int = 0

    }


}
