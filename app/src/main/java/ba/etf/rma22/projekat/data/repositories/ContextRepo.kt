package ba.etf.rma22.projekat.data.repositories

import android.content.Context

class ContextRepo {
    companion object{
        private lateinit var context: Context

        fun setContext(context: Context){
            this.context = context
        }

        fun getContext() : Context {
            return this.context
        }
    }
}