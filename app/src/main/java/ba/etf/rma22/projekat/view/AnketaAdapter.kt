package ba.etf.rma22.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.Communicator
import com.example.spirala1.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.staticdata.p1
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil
import kotlin.math.round

class AnketaAdapter(private var ankete : List<Anketa>, private val onItemClicked : (anketa : Anketa) -> Unit) : RecyclerView.Adapter<AnketaAdapter.CustomViewHolder>() {


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
        val id = context.resources.getIdentifier(boja, "drawable", context.packageName)
        holder.slika.setImageResource(id)
        //datum
        val date : Date = getDate(position)
        val datum = SimpleDateFormat("dd/MM/yyyy")
        val strDate : String = datum.format(date)
        holder.datum.text = getText(boja)+strDate
        //progres
        val prog : Float = ankete[position].progres
        var p : Int = (round(prog*10)*10).toInt()
        if((round(prog*10)).toInt() %2 != 0) p +=10
        holder.prog.progress = p

        val pom = AnketaViewModel()
        val lista = pom.getMyAnkete()
        if((boja == "zelena" || boja=="plava") && lista.contains(ankete[position])) {
            holder.itemView.setOnClickListener { onItemClicked(ankete[position]) }
        }
    }


    fun getDate(position : Int) : Date {
        if(ankete[position].datumRada != null) return ankete[position].datumRada!!
        else if(ankete[position].datumKraj.before(Date())) return ankete[position].datumKraj
        else if(ankete[position].datumPocetak.after(Date())) return ankete[position].datumPocetak
        return ankete[position].datumKraj
    }

    fun getText(boja : String) : String {
        if(boja == "plava") return "Anketa uradjena: "
        else if(boja == "zelena") return "Vrijeme zatvaranja: "
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