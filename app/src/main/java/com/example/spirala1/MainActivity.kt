package com.example.spirala1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spirala1.data.listaAnketa
import com.example.spirala1.view.AnketaAdapter
import com.example.spirala1.viewmodel.AnketaViewModel
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
                if(izbor == "Uradjene getAll") anketaAdapter.updateAnkete(anketaViewModel.getUradjeneAnkete())

                else if(izbor == "Buduce getAll") anketaAdapter.updateAnkete(anketaViewModel.getSljedeceAnkete())

                else if(izbor == "Prosle (neuradjene) getAll") anketaAdapter.updateAnkete(anketaViewModel.getZavrseneAnkete())

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