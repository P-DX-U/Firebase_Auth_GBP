package gbp.firebase.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import gbp.firebase.databinding.ActivityStudentStaffReciclerViewBinding
import gbp.firebase.model.CharacterDetails
import gbp.firebase.model.infoCharacter
import gbp.firebase.network.PotterApi
import gbp.firebase.view.adapters.CharactersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import gbp.firebase.R

class StudentStaffReciclerView : AppCompatActivity() {
    private lateinit var binding: ActivityStudentStaffReciclerViewBinding
    var listType = "students"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentStaffReciclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bundle = intent.extras
        if (bundle!=null) {
            listType = bundle!!.getString("Type").toString()
        }

        val call = retrofit.create(PotterApi::class.java)
            .getCharacterDetail(listType)

        call.enqueue(object: Callback<ArrayList<CharacterDetails>> {
            override fun onResponse(
                call: Call<ArrayList<CharacterDetails>>,
                response: Response<ArrayList<CharacterDetails>>
            ) {
                binding.pbConnection.visibility = View.GONE

                //Log.d("RESPONSE", "RespuestaRecicler> ${response.toString()}")
                //Log.d("RESPONSE", "DatosRecicler> ${response.body().toString()}")
                //Log.d("TypeString", listType)
                binding.rvMenu.layoutManager = LinearLayoutManager(this@StudentStaffReciclerView)
                binding.rvMenu.adapter = CharactersAdapter(this@StudentStaffReciclerView, response.body()!!, {selectedCharacter: CharacterDetails -> profileClick(selectedCharacter)})
            }

            override fun onFailure(call: Call<ArrayList<CharacterDetails>>, t: Throwable) {
                binding.pbConnection.visibility = View.GONE
                Toast.makeText(this@StudentStaffReciclerView, R.string.noConnection, Toast.LENGTH_SHORT)
            }
        })
    }

    private fun profileClick(characterDetails: CharacterDetails){
        val intent = Intent(this, DetailsActivity::class.java)
        val charDetails = infoCharacter(
            characterDetails.name,
            characterDetails.species,
            characterDetails.ancestry,
            characterDetails.wand.wood,
            characterDetails.wand.core,
            characterDetails.patronus,
            characterDetails.dateOfBirth
        )
        val bundle = Bundle()
        bundle.putParcelable("character", charDetails)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}