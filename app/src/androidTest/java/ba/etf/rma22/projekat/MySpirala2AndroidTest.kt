package ba.etf.rma22.projekat

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma22.projekat.UtilTestClass.Companion.hasItemCount
import ba.etf.rma22.projekat.UtilTestClass.Companion.itemTest
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import com.example.spirala1.R
import org.hamcrest.CoreMatchers.*
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
   @Test
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

        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))
        onData(anything()).inAdapterView(allOf(withId(R.id.odgovoriLista), isDisplayed())).atPosition(0).perform(click())

        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(1))
        onData(anything()).inAdapterView(allOf(withId(R.id.odgovoriLista), isDisplayed())).atPosition(1).perform(click())

        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(2))
        onData(anything()).inAdapterView(allOf(withId(R.id.odgovoriLista), isDisplayed())).atPosition(1).perform(click())

        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToLast())
        onView(withId(R.id.dugmePredaj)).perform(click())
        onView(withSubstring("Završili ste anketu")).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))
        onView(withId(R.id.filterAnketa)).perform(click())
        onData(allOf(Is(instanceOf(String::class.java)), Is("Urađene ankete"))).perform(click())
        val ankete1 = AnketaRepository.getDone()
        onView(withId(R.id.listaAnketa)).check(hasItemCount(ankete1.size))
        for (anketa in ankete1) {
            itemTest(R.id.listaAnketa, anketa)
        }



    }


}