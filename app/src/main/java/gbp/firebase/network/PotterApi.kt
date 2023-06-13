package gbp.firebase.network

import gbp.firebase.model.CharacterDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PotterApi {
    //https://hp-api.onrender.com/api/character/:id
    //https://hp-api.onrender.com/api/characters/students
    @GET("api/characters/{id}")
    fun getCharacterDetail(
        @Path("id") id: String?

    ): Call<ArrayList<CharacterDetails>>
}