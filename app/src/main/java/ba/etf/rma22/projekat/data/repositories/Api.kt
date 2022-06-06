package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*
import retrofit2.Response
import retrofit2.http.*

interface Api {

//ISTRAZIVANJA
    //vraca sva istrazivanja, vraća se prvih 5 istrazivanja za offset=1, offset=2 sljedecih 5, stranica koja nema tačno 5 je posljednja
    @GET("/istrazivanje")
    suspend fun getIstrazivanje(@Query("offset") offest : Int ?) : Response<List<Istrazivanje>>


    //bez offseta
    @GET("/istrazivanje")
    suspend fun getIstrazivanje() : Response<List<Istrazivanje>>

    //vraca istrazivanje sa zadanim id-em
    @GET("/istrazivanje/{id}")
    suspend fun getIstrazivanjeSaId(@Path("id") id : Int) : Response<Istrazivanje>


    //Vraca istrazivanja za grupu sa zadanim id-em
    @GET("/grupa/{gid}/istrazivanje")
    suspend fun getIstrazivanjeZaGrupu(@Path("gid")  gid : Int) : Response<List<Istrazivanje>>

//GRUPE
    //vraca grupe u kojima je anketa dostupna
    @GET("/anketa/{id}/grupa")
    suspend fun getGrupeZaDostupneAnkete(@Path("id") id : Int) : Response<List<Grupa>>


    //dodaje studenta sa hashom id u grupu sa id gid
    @POST("/grupa/{gid}/student/{id}")
    suspend fun dodajStudenta(@Path("gid") gid : Int, @Path("id") id : String) : Response<ResponseMessage>


    //vraca grupe u koje je student upisan
    @GET("/student/{id}/grupa")
    suspend fun getStudentoveGrupe(@Path("id") id : String) : Response<List<Grupa>>


    //vraca sve grupe
    @GET("/grupa")
    suspend fun getAllGrupe() : Response<List<Grupa>>


    //vraca grupu sa zadanim id-em
    @GET("/grupa/{id}")
    suspend fun getGrupuSaId(@Path("id") id : Int) : Response<Grupa>

//ANKETE
    //vraca sve ankete vraća se prvih 5 anketa za offset=1, offset=2 sljedecih 5, stranica koja nema tačno 5 je posljednja
    @GET("/anketa")
    suspend fun getAnkete(@Query("offset") offset : Int) : Response<List<Anketa>>
    //@Query("_sort") sort : String, @Query("_order") order : String

    //bez offset
    @GET("/anketa")
    suspend fun getAnkete() : Response<List<Anketa>>


    //vraca ankete sa id-em
    @GET("/anketa/{id}")
    suspend fun getAnketaSaId(@Path("id") id : Int) : Response<Anketa>


    //vraca ankete koje su dodijeljenje ovoj grupi
    @GET("/grupa/{id}/ankete")
    suspend fun getAnketeSaGrupom(@Path("id") id : Int) : Response<List<Anketa>>

//ODGOVORI
    //vraca listu odgovora za pokusaj rjesavanja ankete sa idem ktid i studenta sa zadanim id
    @GET("/student/{id}/anketataken/{ktid}/odgovori")
    suspend fun getPokusajRjesavanja(@Path("id") id : String, @Path("ktid") ktid : Int) : Response<List<Odgovor> ?>


    //dodaje odgovor za pokusaj rjesavanja ankete sa idem ktid za studenta sa hasnom id
    @POST("/student/{id}/anketataken/{ktid}/odgovor")
    suspend fun addOdgovor(@Path("id") id : String, @Path("ktid") ktid : Int,
    @Body odg : OdgovorBody) : Response<Odgovor>

//ANKETATAKEN
    //vraca listu pokusaja za ankete sa zadanim id
    @GET("/student/{id}/anketataken")
    suspend fun getPokusajAnketa(@Path("id") id : String) : Response<List<AnketaTaken> ?>


    //zapocinje odgovaranja studenta sa hash id na anketu sa kid id-em
    @POST("/student/{id}/anketa/{kid}")
    suspend fun pocniOdg(@Path("id") id : String, @Path("kid") kid : Int) : Response<AnketaTaken>


    //vraca account studenta koji ima zadan hash id
    @GET("/student/{id}")
    suspend fun getStudenta(@Path("id") id : String) : Response<Account>


    //brise sve podatke povezane sa korisnikom koji ima hash id
    @DELETE("/student/{id}/upisugrupeipokusaji")
    suspend fun brisiSve(@Path("id") id : String)

//PITANJE
    //vraca pitanja na anketi
    @GET("/anketa/{id}/pitanja")
    suspend fun getPitanja(@Path("id") id : Int) : Response<List<Pitanje>>


}
