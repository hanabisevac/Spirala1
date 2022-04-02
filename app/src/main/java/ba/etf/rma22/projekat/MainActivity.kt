package ba.etf.rma22.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spirala1.R
import ba.etf.rma22.projekat.view.AnketaAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var anketaAdapter: AnketaAdapter
    private lateinit var anketaRecyclerView: RecyclerView
    private lateinit var dugme : FloatingActionButton
    private lateinit var spiner : Spinner
    private val anketaViewModel = AnketaViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anketaRecyclerView = findViewById(R.id.listaAnketa)
        anketaRecyclerView.layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        anketaAdapter = AnketaAdapter(anketaViewModel.getSveAnkete())
        anketaRecyclerView.adapter = anketaAdapter

        spiner = findViewById(R.id.filterAnketa)


        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val izbor : String = adapterView?.getItemAtPosition(position).toString()
                if(izbor == "Urađene ankete") anketaAdapter.updateAnkete(anketaViewModel.getUradjeneAnkete())

                else if(izbor == "Buduće ankete") anketaAdapter.updateAnkete(anketaViewModel.getSljedeceAnkete())

                else if(izbor == "Prošle ankete") anketaAdapter.updateAnkete(anketaViewModel.getZavrseneAnkete())

                else if(izbor == "Sve moje ankete") anketaAdapter.updateAnkete(anketaViewModel.getMyAnkete())

                else anketaAdapter.updateAnkete(anketaViewModel.getSveAnkete())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

        }
        dugme = findViewById(R.id.upisDugme)

        dugme.setOnClickListener {
            val intent = Intent(this, UpisIstrazivanje::class.java)
            startActivity(intent)
        }


    }




}