package ba.etf.rma22.projekat.data.repositories



import ba.etf.rma22.projekat.data.models.AnketaTaken
import java.lang.Math.round
import java.util.*

class TrenutnaAnketaRepository {

    companion object{
        private lateinit var anketaPamti : AnketaTaken
        private var brojPitanja : Int = 0
        private var progres : Int =0

        fun postaviAnketu(anketa : AnketaTaken) {
            anketaPamti = anketa
        }

        fun dajAnketu() : AnketaTaken{
            return anketaPamti
        }


        fun postaviBrojPitanja(broj : Int) {
            this.brojPitanja = broj
        }

        fun dajBrojPitanja() : Int{
            return this.brojPitanja
        }

        fun postaviProgres(p : Int){
            this.progres = p
        }

        fun dajProgres() : Int{
            return this.progres
        }

        /*fun dajProgres() : Int{
           return this.progres
        }

        fun postaviProgres(progres : Float){
            val prog: Float = progres
            var p = (round(prog * 10) * 10).toInt()
            if ((round(prog * 10)).toInt() % 2 != 0) p += 10
            //p = ankete[position].progres!!
            if(p>100) p =100
            this.progres = p
        }*/
    }

}