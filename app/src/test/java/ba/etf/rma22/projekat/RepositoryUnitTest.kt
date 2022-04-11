package ba.etf.rma22.projekat



import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import org.hamcrest.CoreMatchers.`is` as Is
import org.hamcrest.MatcherAssert.*
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.not
import org.hamcrest.beans.HasPropertyWithValue.hasProperty
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryUnitTest {

    @Test
    fun testGetAll(){
        //ankete
        val ankete = AnketaRepository.getAll()
        assertEquals(ankete.size, 14)
        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Odlazak mladih iz BiH"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("naziv", Is("Anketa 13")))))
        //grupe
        val grupe = GrupaRepository.getAll()
        assertEquals(grupe.size, 14)
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 8"))))
        assertThat(grupe, hasItem<Grupa>(hasProperty("nazivIstrazivanja", Is("Istrazivanje javnog mnjenja"))))
        assertThat(grupe, not(hasItem<Grupa>(hasProperty("naziv", Is("Grupa 15")))))
        //istrazivanja
        val istrazivanja = IstrazivanjeRepository.getAll()
        assertEquals(istrazivanja.size, 6)
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istrazivanje o cyberbullying-u"))))
        assertThat(istrazivanja, hasItem<Istrazivanje>(hasProperty("godina", Is(5))))
        assertThat(istrazivanja, not(hasItem<Istrazivanje>(hasProperty("godina", Is(6)))))
    }

    @Test
    fun testGetDone() {
        val gotove = AnketaRepository.getDone()
        assertEquals(gotove.size, 1)
        assertThat(gotove, hasItem<Anketa>(hasProperty("naziv", Is("Hackovanje"))))
        assertThat(gotove, not(hasItem<Anketa>(hasProperty("naziv", Is("Sigurnost na internetu")))))
        assertThat(gotove, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa 3"))))
    }

    @Test
    fun testGetNotTaken(){
        val zavrsene = AnketaRepository.getNotTaken()
        assertEquals(zavrsene.size, 1)
        assertThat(zavrsene, hasItem<Anketa>(hasProperty("naziv", Is("Zabrana pusenja"))))
        assertThat(zavrsene, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa 3"))))
    }

    @Test
    fun testGetMyAnkete() {
        val moje = AnketaRepository.getMyAnkete()
        assertEquals(moje.size, 1)
        assertThat(moje, hasItem<Anketa>(hasProperty("naziv", Is("Hackovanje"))))
        assertThat(moje, not(hasItem<Anketa>(hasProperty("naziv", Is("Sigurnost na internetu")))))
        assertThat(moje, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa 3"))))
        assertThat(moje, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Istrazivanje o cyberbullying-u"))))
    }

    @Test
    fun testGetFuture() {
        val buduce = AnketaRepository.getFuture()
        assertEquals(buduce.size, 0)
    }

    @Test
    fun testGetAktivneAnkete() {
        val aktivne = AnketaRepository.aktivneAnkete()
        assertEquals(aktivne.size, 0)
    }

    @Test
    fun testDajSveBezMojih() {
        val ankete = AnketaRepository.dajSveBezMojih()
        assertEquals(ankete.size, 13)
    }

    @Test
    fun testGetGroupsByIstrazivanje() {
        val g1 = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje o cyberbullying-u")
        assertEquals(g1.size, 3)
        assertThat(g1, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 1"))))
        assertThat(g1, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 2"))))
        assertThat(g1, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 3"))))
        val g2 = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje o kvaliteti nastave")
        assertEquals(g2.size, 2)
        assertThat(g2, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 1"))))
        assertThat(g2, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 2"))))
        val g3 = GrupaRepository.getGroupsByIstrazivanje("Drustveno istrazivanje")
        assertEquals(g3.size, 2)
        assertThat(g3, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 4"))))
        assertThat(g3, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 3"))))
        val g4 = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje o zivotinjama")
        assertEquals(g4.size, 3)
        assertThat(g4, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 5"))))
        assertThat(g4, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 2"))))
        assertThat(g4, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 6"))))
        val g5 = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje o uticaju medija")
        assertEquals(g5.size, 2)
        assertThat(g5, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 7"))))
        assertThat(g5, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 8"))))
        val g6 = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje javnog mnjenja")
        assertEquals(g6.size, 2)
        assertThat(g6, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 7"))))
        assertThat(g6, hasItem<Grupa>(hasProperty("naziv", Is("Grupa 5"))))
    }

    @Test
    fun testGetIstrazivanjeByGodina() {
        val i1 = IstrazivanjeRepository.getIstrazivanjeByGodina(1)
        assertEquals(i1.size, 1)
        assertThat(i1, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istrazivanje o kvaliteti nastave"))))
        val i2 = IstrazivanjeRepository.getIstrazivanjeByGodina(2)
        assertEquals(i2.size, 2)
        assertThat(i2, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istrazivanje javnog mnjenja"))))
        assertThat(i2, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istrazivanje o cyberbullying-u"))))
        val i3 = IstrazivanjeRepository.getIstrazivanjeByGodina(3)
        assertEquals(i3.size, 1)
        assertThat(i3, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istrazivanje o zivotinjama"))))
        val i4 = IstrazivanjeRepository.getIstrazivanjeByGodina(4)
        assertEquals(i4.size, 1)
        assertThat(i4, hasItem<Istrazivanje>(hasProperty("naziv", Is("Drustveno istrazivanje"))))
        val i5 = IstrazivanjeRepository.getIstrazivanjeByGodina(5)
        assertEquals(i5.size, 1)
        assertThat(i5, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istrazivanje o uticaju medija"))))
    }

    @Test
    fun testGetUpisani() {
        val upisan = IstrazivanjeRepository.getUpisani()
        assertEquals(upisan.size, 1)
        assertThat(upisan, hasItem<Istrazivanje>(hasProperty("naziv", Is("Istrazivanje o cyberbullying-u"))))
    }


}