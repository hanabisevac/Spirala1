package ba.etf.rma22.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spirala1.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.repositories.KonekcijaRepository
import ba.etf.rma22.projekat.viewmodel.AnketaTakenViewModel
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import java.text.SimpleDateFormat
import java.util.*

class AnketaAdapter(private var ankete : List<Anketa>, private val onItemClicked : (anketa : Anketa) -> Unit) : RecyclerView.Adapter<AnketaAdapter.CustomViewHolder>() {


    private val anketaViewModel = AnketaViewModel()
    private val anketaTakenViewModel = AnketaTakenViewModel()

    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val imeAnkete : TextView = itemView.findViewById(R.id.ime_ankete)
        val istrazivanje : TextView = itemView.findViewById(R.id.istrazivanje)
        val slika : ImageView = itemView.findViewById(R.id.slika)
        val datum : TextView = itemView.findViewById(R.id.datum)
        val prog : ProgressBar = itemView.findViewById(R.id.progresZavrsetak)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_izgled, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.imeAnkete.text = ankete[position].naziv
        holder.istrazivanje.text = ankete[position].nazivIstrazivanja
        val context : Context = holder.slika.context

        //slika
        val boja : String = ankete[position].getStatus()
        var boja2 = boja
        if(boja=="zelena1") boja2 = "zelena"
        val id = context.resources.getIdentifier(boja2, "drawable", context.packageName)
        holder.slika.setImageResource(id)

        //datum
        val date : Date = getDate(position)
        val datum = SimpleDateFormat("dd/MM/yyyy")
        val strDate : String = datum.format(date)
        holder.datum.text = getText(boja)+strDate

        //progres
        holder.prog.progress = ankete[position].progres!!

        holder.itemView.isEnabled = false
        if(KonekcijaRepository.getKonekcija()){
            anketaViewModel.getUpisane {
                if(it.contains(ankete[position]) && boja!="zuta"){
                    holder.itemView.isEnabled = true
                }
            }
            holder.itemView.setOnClickListener { onItemClicked(ankete[position]) }
        }
        else{
            anketaTakenViewModel.dajPocete() {
                val ima = it.find { a -> a.AnketumId == ankete[position].id }
                if(ima != null) {
                    holder.itemView.isEnabled = true
                    holder.itemView.setOnClickListener { onItemClicked(ankete[position]) }
                }
            }
        }

    }



    fun getDate(position : Int) : Date {
        if(ankete[position].dajDatumRada() != null) return ankete[position].dajDatumRada()!!
        else if(ankete[position].datumKraj!=null && ankete[position].dajDatumKraj()!!.before(Date())) return ankete[position].dajDatumKraj()!!
        else if(ankete[position].datumKraj == null && ankete[position].dajDatumPocetak().before(Date())) return ankete[position].dajDatumPocetak()
        else if(ankete[position].dajDatumPocetak().after(Date())) return ankete[position].dajDatumPocetak() //buduce
        return ankete[position].dajDatumKraj()!! //prosle
    }

    fun getText(boja : String) : String {
        if(boja == "plava") return "Anketa uradjena: "
        else if(boja == "zelena") return "Vrijeme zatvaranja: "
        else if(boja == "zelena1") return "Anketa aktivirana: "
        else if(boja == "crvena") return  "Anketa zatvorena: "
        return "Vrijeme aktiviranje: "
    }

    override fun getItemCount(): Int {
        return ankete.size
    }

    fun updateProgress() {
        notifyDataSetChanged()
    }

    fun updateAnkete(novaLista : List<Anketa>) {
        this.ankete = novaLista
        notifyDataSetChanged()
    }


}
