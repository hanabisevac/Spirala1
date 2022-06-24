package ba.etf.rma22.projekat


import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.AppDatabase
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.ContextRepo
import ba.etf.rma22.projekat.data.repositories.KonekcijaRepository
import ba.etf.rma22.projekat.data.repositories.TrenutnaAnketaRepository
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.AccViewModel
import ba.etf.rma22.projekat.viewmodel.AnketaTakenViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import com.example.spirala1.R
import java.util.*

class MainActivity : AppCompatActivity(), Communicator {
    private lateinit var viewPager : ViewPager2
    private lateinit var viewPagerAdapter : ViewPagerAdapter
    private lateinit var viewPagerAdapter2 : ViewPagerAdapter

    private val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
    private val br = MyBroadcastReceiver()

    private val anketaTakenViewModel = AnketaTakenViewModel()
    private val pitanjaAnketaViewModel = PitanjeAnketaViewModel()
    //private val fragmentPredaja = FragmentPredaj.newInstance()
    val fragments = mutableListOf<Fragment>(FragmentAnkete(), FragmentIstrazivanje())
    //val fragments1 = mutableListOf<Fragment>(FragmentAnkete())
    private var usao : Boolean = false
    var lista = mutableListOf<Anketa>()


    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)

        ContextRepo.setContext(this)
        registerReceiver(br, filter)

        if(intent.action == Intent.ACTION_VIEW && intent.data != null){
            intent.getStringExtra("payload").let { AccViewModel().postaviHash(it!!) }
        }

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
        val lista = mutableListOf<Fragment>()
        pitanjaAnketaViewModel.getPitanjaZaAnketu(anketa.id) {
            TrenutnaAnketaRepository.postaviBrojPitanja(it!!.size)
            for (i in it.indices) {
                lista.add(FragmentPitanje(it[i]))
            }
            lista.add(FragmentPredaj(anketa))
            if(KonekcijaRepository.getKonekcija()){
                anketaTakenViewModel.zapocniAnketu(anketa.id) { it ->
                    TrenutnaAnketaRepository.postaviAnketu(it)
                    TrenutnaAnketaRepository.postaviProgres(it.progres)
                    viewPagerAdapter2 = ViewPagerAdapter(supportFragmentManager, lista, lifecycle)
                    viewPager.adapter = viewPagerAdapter2
                    usao = true
                }
            }
            else{
                anketaTakenViewModel.getPocetu(anketa.id){
                    TrenutnaAnketaRepository.postaviAnketu(it)
                    TrenutnaAnketaRepository.postaviProgres(it.progres)
                    viewPagerAdapter2 = ViewPagerAdapter(supportFragmentManager, lista, lifecycle)
                    viewPager.adapter = viewPagerAdapter2
                    usao = true
                }
            }
        }
    }


    override fun prebaciFragment(poruka : String) {
        viewPagerAdapter.refreshFragment(1, FragmentPoruka(poruka))
        if(usao) {
            viewPager.adapter = viewPagerAdapter
            viewPager.currentItem = 1
            usao = false
        }
    }

    override fun vratiNaPocetnu() {
        viewPager.adapter = viewPagerAdapter
    }

    /*override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }*/

}