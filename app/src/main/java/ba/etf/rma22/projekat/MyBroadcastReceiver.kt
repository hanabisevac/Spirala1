package ba.etf.rma22.projekat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import ba.etf.rma22.projekat.data.repositories.KonekcijaRepository

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent : Intent) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork
        val capabilities = cm.getNetworkCapabilities(network)
        if(capabilities == null) {
            val toast = Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT)
            KonekcijaRepository.setKonekcija(false)
            toast.show()
        }
        else{
            val toast = Toast.makeText(context, "Connected", Toast.LENGTH_SHORT)
            KonekcijaRepository.setKonekcija(true)
            toast.show()
        }

    }
}