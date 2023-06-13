package gbp.firebase.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import gbp.firebase.databinding.ActivityDetailsBinding
import gbp.firebase.model.infoCharacter
import gbp.firebase.R

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val character: infoCharacter?

        character = bundle?.getParcelable<infoCharacter>("character")
        binding.pbConexion.visibility = View.GONE
        if (character != null) {
            binding.tvName.text = character.name
            binding.tvSpecies.text = getString(R.string.specie) + " " + character.species
            binding.tvAncestry.text = getString(R.string.ancestry) + " " + character.ancestry
            binding.tvWood.text = getString(R.string.wood) + " " + character.wood
            binding.tvCore.text = getString(R.string.core) + " " + character.core
            binding.tvPatronus.text = getString(R.string.patronus) + " " + character.patronus
            binding.tvbirthday.text = getString(R.string.birthday) + " " + character.birthday
        }
    }
}