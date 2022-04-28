package ba.etf.rma22.projekat

import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma22.projekat.UtilTestClass.Companion.hasItemCount
import ba.etf.rma22.projekat.UtilTestClass.Companion.itemTest
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import com.example.spirala1.R
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.`is` as Is


@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {

    @get:Rule
    val intentsTestRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    //zadatak1
    @Test
    fun upisiSeNaIstrazivanje() {
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(1))
        onView(withId(R.id.odabirGodina)).perform(click())
        onData(allOf(Is(instanceOf(String::class.java)), Is("2"))).perform(click())
        onView(withId(R.id.odabirIstrazivanja)).perform(click())
        onData(allOf(Is(instanceOf(String::class.java)), Is("Istrazivanje 2"))).perform(click())
        onView(withId(R.id.odabirGrupa)).perform(click())
        onData(allOf(Is(instanceOf(String::class.java)), Is("Grupa 3"))).perform(click())
        onView(withId(R.id.dodajIstrazivanjeDugme)).perform(click())
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))
        onView(withId(R.id.filterAnketa)).perform(click())
        onData(allOf(Is(instanceOf(String::class.java)), Is("Sve moje ankete"))).perform(click())
        val ankete = AnketaRepository.getMyAnkete()
        onView(withId(R.id.listaAnketa)).check(hasItemCount(ankete.size))
        for (anketa in ankete) {
            itemTest(R.id.listaAnketa, anketa)
        }
    }

    //zadatak 2
    /*@Test
    fun popuniAnketu() {
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))
        onView(withId(R.id.filterAnketa)).perform(click())
        onData(allOf(Is(instanceOf(String::class.java)), Is("Sve moje ankete"))).perform(click())
        val ankete = AnketaRepository.getMyAnkete()
        onView(withId(R.id.listaAnketa)).perform(
            actionOnItem<RecyclerView.ViewHolder>(
                allOf(hasDescendant(withText(ankete[0].naziv)),
            hasDescendant(withText(ankete[0].nazivIstrazivanja))), click())
        )
        val pitanja = PitanjeAnketaRepository.getPitanja(ankete[0].naziv, ankete[0].nazivIstrazivanja)

        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))
        //onView(withId(R.id.odgovoriLista))
        //onData(allOf(Is(instanceOf(ListView::class.java)), Is("Green mile"))).perform(click())

        onData(anything()).inAdapterView(allOf(Is(instanceOf(TextView::class.java)))).atPosition(0).perform(click())
        //Thread.sleep(5000)



    }*/


}