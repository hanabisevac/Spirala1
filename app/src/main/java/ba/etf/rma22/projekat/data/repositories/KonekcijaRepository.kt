package ba.etf.rma22.projekat.data.repositories



class KonekcijaRepository {
    companion object {
        private var konekcija : Boolean = true

        fun getKonekcija() : Boolean {
            return konekcija
        }

        fun setKonekcija(b : Boolean) {
            this.konekcija = b
        }
        
    }

}
