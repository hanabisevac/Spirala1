package ba.etf.rma22.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.PredanaAnketa
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import com.example.spirala1.R

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var viewPager : ViewPager2
    private lateinit var viewPagerAdapter : ViewPagerAdapter
    private lateinit var viewPagerAdapter2 : ViewPagerAdapter

    private val pitanjaAnketaViewModel = PitanjeAnketaViewModel()
    private val fragmentPredaja = FragmentPredaj.newInstance()
    val fragments = mutableListOf<Fragment>(FragmentAnkete(), FragmentIstrazivanje())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)
        viewPager.offscreenPageLimit = 3
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)
        viewPager.adapter = viewPagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if(position == 0 && fragments[0].hashCode().toLong() == viewPagerAdapter.getItemId(position)){
                    viewPagerAdapter.refreshFragment(1, FragmentIstrazivanje())
                }
                super.onPageSelected(position)
            }
        })
        //refreshSecondFragment()
    }




    /*private fun refreshSecondFragment() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewPagerAdapter.refreshFragment(
                1,
                fragments[1]
            )
        }, 5000)
    }*/

    override fun otvoriNoviFragment(anketa: Anketa) {
        PredanaAnketa.postaviAnketu(anketa)
        val pitanjaZaAnketu = pitanjaAnketaViewModel.getPitanjaZaAnketu(anketa)
        val lista = mutableListOf<Fragment>()
        for(i in pitanjaZaAnketu.indices){
            lista.add(FragmentPitanje(pitanjaZaAnketu[i], pitanjaZaAnketu.size))
        }
        lista.add(fragmentPredaja)
        viewPagerAdapter2 = ViewPagerAdapter(supportFragmentManager, lista, lifecycle)
        viewPager.adapter = viewPagerAdapter2

    }

    override fun prebaciFragment(poruka : String) {
        viewPagerAdapter.refreshFragment(1, FragmentPoruka(poruka))
        viewPager.adapter = viewPagerAdapter
        viewPager.setCurrentItem(1)
    }

    override fun vratiNaPocetnu() {
        viewPager.adapter = viewPagerAdapter
    }




}