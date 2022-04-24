package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.repositories.PitanjaAnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjaRepository
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjaAnketaViewModel
import com.example.spirala1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.ArrayList

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var viewPager : ViewPager2
    private lateinit var viewPagerAdapter : ViewPagerAdapter
    private lateinit var viewPagerAdapter2 : ViewPagerAdapter

    private val pitanjaAnketaViewModel = PitanjaAnketaViewModel()
//    private val fragmentAnkete = FragmentAnkete.newInstance()
//    private val fragmentIstrazivanje = FragmentIstrazivanje.newInstance()
    private val fragmentPredaja = FragmentPredaj.newInstance()
    val fragments = mutableListOf<Fragment>(FragmentAnkete(), FragmentIstrazivanje())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)
        viewPager.offscreenPageLimit = 3
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        viewPager.adapter = viewPagerAdapter
        /*viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == 0 && fragments[0].hashCode().toLong() == viewPagerAdapter.getItemId(position)){
                    viewPagerAdapter.refreshFragment(1, FragmentIstrazivanje())
                }
                super.onPageSelected(position)
            }
        })*/
        //refreshSecondFragment()
    }




    /*private fun refreshSecondFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewPagerAdapter.refreshFragment(
                0,
                fragments[0]
            )
        }, 5000)
    }*/

    override fun otvoriNoviFragment(anketa: Anketa) {
        val pitanjaZaAnketu = pitanjaAnketaViewModel.getPitanjaZaAnketu(anketa)
        val lista = mutableListOf<Fragment>()
        for(i in pitanjaZaAnketu.indices){
            lista.add(FragmentPitanje(pitanjaZaAnketu[i], pitanjaZaAnketu.size))
        }
        lista.add(fragmentPredaja)
        viewPagerAdapter2 = ViewPagerAdapter(supportFragmentManager, lista, lifecycle)
        viewPager.adapter = viewPagerAdapter2

    }

    override fun prebaciFragment() {
        viewPagerAdapter.remove(1)
        viewPagerAdapter.add(1, FragmentPoruka())
    }

    override fun vratiNaPocetnu() {
        viewPager.adapter = viewPagerAdapter
    }


}